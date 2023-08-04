package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class ShipsIdleHandler {

    private final Connection databaseConnection;
    private Clob informationOutput;
    private final WriteForAFile writeForAFile;

    public ShipsIdleHandler() throws IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        writeForAFile = new WriteForAFile();
        try {
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws IOException, SQLException {
        CallableStatement statement = null;
        try {
            statement = databaseConnection.prepareCall("{ call ShipsIdleUs404(?) }");
            statement.registerOutParameter(1, Types.CLOB);
            statement.execute();

            this.informationOutput = statement.getClob(1);

            Reader reader = informationOutput.getCharacterStream();

            StringBuilder buffer = new StringBuilder();

            int ch;
            while ((ch = reader.read()) != -1){
                buffer.append(""+(char)ch);
            }

            writeForAFile.writeForAFile(buffer.toString(), "US404", new File(".\\outputs\\US404"), false);

        }catch (SQLException e){
            e.printStackTrace();
            writeForAFile.writeForAFile("Something went wrong", "US404", new File(".\\outputs\\US404"), false);
        }finally {
            assert statement != null;
            statement.close();
        }

    }
}
