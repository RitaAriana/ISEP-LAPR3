package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class US310Handler {

    private final Connection databaseConnection;
    private Clob information;
    private final WriteForAFile writeForAFile;


    public US310Handler(int month, int year) throws IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        writeForAFile = new WriteForAFile();
        try {
            initialize(month, year);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize(int month, int year) throws IOException, SQLException {
        CallableStatement statement = null;
        try {
            statement = databaseConnection.prepareCall("{call PortsInformation(?, ?, ?)}");
            statement.registerOutParameter(3, Types.CLOB);
            statement.setInt(1, month);
            statement.setInt(2, year);

            statement.execute();

            this.information= statement.getClob(3);

            Reader reader = information.getCharacterStream();

            StringBuffer buffer = new StringBuffer();

            int ch;
            while ((ch = reader.read()) != -1){
                buffer.append(""+(char)ch);
            }

            writeForAFile.writeForAFile(buffer.toString(), "US310" , new File(".\\outputs\\US310"), false);

        }catch (Exception e){
            e.printStackTrace();
            writeForAFile.writeForAFile("Something went wrong", "US310" , new File(".\\outputs\\US310"), false);
        }finally {
            assert statement != null;
            statement.close();
        }

    }

}
