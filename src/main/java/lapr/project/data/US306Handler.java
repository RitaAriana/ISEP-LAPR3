package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US306Handler {

    private final Connection databaseConnection;
    private String informationOutput;
    private final WriteForAFile writeForAFile;

    public US306Handler() throws IOException {
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
            statement = databaseConnection.prepareCall("{call getOccupancyOfWarehouse(?) }");
            statement.registerOutParameter(1, Types.LONGNVARCHAR);
            statement.execute();

            this.informationOutput = statement.getString(1);

            writeForAFile.writeForAFile(informationOutput, "US306_Containers_Warehouses", new File(".\\outputs\\US306"), false);

        }catch (SQLException e){
            e.printStackTrace();
            writeForAFile.writeForAFile("Something went wrong", "US306_Containers_Warehouses", new File(".\\outputs\\US306"), false);
        }finally {
            assert statement != null;
            statement.close();
        }

    }
}
