package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

public class US210Handler {

    private final Connection databaseConnection;
    private String information;
    private final WriteForAFile writeForAFile;


    public US210Handler() throws IOException {
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
            statement = databaseConnection.prepareCall("{call US210(?)}");
            statement.registerOutParameter(1, Types.LONGNVARCHAR);


            statement.execute();

            this.information= statement.getString(1);

            writeForAFile.writeForAFile(information, "US210" , new File(".\\outputs\\US210"), false);

        }catch (Exception e){
            writeForAFile.writeForAFile("Something went wrong", "US210" , new File(".\\outputs\\US210"), false);
        }finally {
            statement.close();
        }

    }


}
