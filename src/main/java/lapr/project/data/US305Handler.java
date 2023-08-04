package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US305Handler {

    private final Connection databaseConnection;
    private String containerPathInfo;
    private final WriteForAFile writeForAFile;

    public US305Handler(int registerCode, int containerId) throws IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        writeForAFile = new WriteForAFile();
        try {
            initialize(registerCode, containerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize(int registerCode, int containerId) throws IOException, SQLException {
        CallableStatement statement = null;
        try {
            statement = databaseConnection.prepareCall("{call US305(?, ?, ?)}");
            statement.registerOutParameter(3, Types.VARCHAR);

            statement.setInt(1, registerCode);
            statement.setInt(2, containerId);

            statement.execute();

            this.containerPathInfo=statement.getString(3);

            writeForAFile.writeForAFile(toString(), "US305_" + registerCode, new File(".\\outputs\\US305"), false);

        }catch (Exception e){
            e.printStackTrace();
            writeForAFile.writeForAFile("Something went wrong", "US305_" + registerCode, new File(".\\outputs\\US305"), false);
        }finally {
            assert statement != null;
            statement.close();
        }

    }

    @Override
    public String toString() {
        return String.format("Origin, Departure Date, Destination, Arrival Date%n%s", containerPathInfo);
    }
}
