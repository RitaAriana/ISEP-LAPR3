package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class US304Handler {

    private final Connection databaseConnection;
    private Clob informationOutput;
    private final WriteForAFile writeForAFile;

    public US304Handler(int cargoManifestId, int containerId) throws IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        writeForAFile = new WriteForAFile();
        try {
            initialize(cargoManifestId, containerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize(int cargoManifestId, int containerId) throws IOException, SQLException {
        CallableStatement statement = null;
        try {
            statement = databaseConnection.prepareCall("{ call getInformationAboutAuditTrails(?, ?, ?) }");
            statement.registerOutParameter(3, Types.CLOB);
            statement.setInt(1, cargoManifestId);
            statement.setInt(2, containerId);
            statement.execute();

            this.informationOutput = statement.getClob(3);

            Reader reader = informationOutput.getCharacterStream();

            StringBuilder buffer = new StringBuilder();

            int ch;
            while ((ch = reader.read()) != -1){
                buffer.append(""+(char)ch);
            }

            writeForAFile.writeForAFile(buffer.toString(), "US304_" + containerId, new File(".\\outputs\\US304"), false);

        }catch (SQLException e){
            e.printStackTrace();
            writeForAFile.writeForAFile("Something went wrong", "US304_" + containerId, new File(".\\outputs\\US304"), false);
        }finally {
            assert statement != null;
            statement.close();
        }

    }
}
