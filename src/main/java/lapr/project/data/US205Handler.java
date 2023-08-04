package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US205Handler {

    private final Connection databaseConnection;
    private String listOfContainers;
    private final WriteForAFile writeForAFile;

    public US205Handler(String mmsiCode) throws IOException{
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        writeForAFile = new WriteForAFile();
        try {
            initialize(mmsiCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize(String mmsiCode) throws IOException, SQLException {
        CallableStatement statement = null;
        try {
            statement = databaseConnection.prepareCall("{call US205(?, ?)}");

            statement.registerOutParameter(2, Types.VARCHAR);

            statement.setString(1, mmsiCode);

            statement.execute();

            this.listOfContainers=statement.getString(2);

            writeForAFile.writeForAFile(toString(), "US205_" + mmsiCode, new File(".\\outputs\\US205"), false);
            statement.close();
        }catch (Exception e){
            writeForAFile.writeForAFile("Something went wrong", "US205_" + mmsiCode, new File(".\\outputs\\US205"), false);
        }finally {
            statement.close();
        }

    }

    @Override
    public String toString() {
        return String.format("ContainerNumberId, Positions, Type, Load%n%s", listOfContainers);
    }
}
