package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class US407Handler {
    private final Connection databaseConnection;
    private Clob information;
    private final WriteForAFile writeForAFile;

    public US407Handler(int portId) throws IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        writeForAFile = new WriteForAFile();
        try {
            initialize(portId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize(int portId) throws IOException, SQLException {
        CallableStatement statement = null;
        try {
            statement = databaseConnection.prepareCall("{call resourcesForNextWeek(?, ?)}");
            statement.registerOutParameter(2, Types.CLOB);

            statement.setInt(1, portId);
            statement.execute();

            this.information= statement.getClob(2);

            Reader reader = information.getCharacterStream();

            StringBuffer buffer = new StringBuffer();

            int ch;
            while ((ch = reader.read()) != -1){
                buffer.append(""+(char)ch);
            }

            writeForAFile.writeForAFile(buffer.toString(), "US407_Port" + portId , new File(".\\outputs\\US407"), false);
        }catch (Exception e){
            e.printStackTrace();
            writeForAFile.writeForAFile("Something went wrong", "US407_Port" + portId, new File(".\\outputs\\US407"), false);
        }finally {
            assert statement != null;
            statement.close();
        }

    }
}
