package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;


public class US208Handler {

    private final Connection databaseConnection;
    private float ratio;
    private final WriteForAFile writeForAFile;

    public US208Handler(int cargoManifestId) throws IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        writeForAFile = new WriteForAFile();
        try {
            initialize(cargoManifestId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize(int cargoManifestId) throws IOException, SQLException {
        CallableStatement statement = null;
        try {
             statement = databaseConnection.prepareCall("{call US208(?, ?)}");
            statement.registerOutParameter(2, Types.FLOAT);

            statement.setInt(1, cargoManifestId);

            statement.execute();

            this.ratio = statement.getFloat(2);

            writeForAFile.writeForAFile(toString(), "US208_" + cargoManifestId, new File(".\\outputs\\US208"), false);
        }catch (Exception e){
            writeForAFile.writeForAFile("Something went wrong", "US208_" + cargoManifestId, new File(".\\outputs\\US208"), false);
        }finally {
            statement.close();

        }

    }

    public float getRatio() {
        return ratio;
    }

    @Override
    public String toString() {
        return String.format("ratio%n" +
                "%.2f%n", ratio);
    }
}
