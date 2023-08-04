package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US204Handler {

    private Connection databaseConnection;

    private final WriteForAFile writeForAFile;

    private String containerLocation;

    public US204Handler(){
        writeForAFile = new WriteForAFile();
    }

    public String getContainerLocation(int containerNumber) throws IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        try(CallableStatement callStmt = databaseConnection.prepareCall("{ ? = call get_container_position_US204 (?)}")) {

            callStmt.setInt(2, containerNumber);


            callStmt.registerOutParameter(1, Types.LONGNVARCHAR);

            callStmt.execute();

            this.containerLocation = callStmt.getString(1);


            writeForAFile.writeForAFile(containerLocation, "US204_" + containerNumber, new File(".\\outputs\\US204"), false);

            return containerLocation;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
