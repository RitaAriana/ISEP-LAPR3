package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class US209Handler {

    private final Connection databaseConnection;
    private float occupancyRate;
    private final WriteForAFile writeForAFile;

    public US209Handler(String mmsiCode, Timestamp actualDate) throws IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        writeForAFile = new WriteForAFile();
        try {
            initialize(mmsiCode, actualDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize(String mmsiCode, Timestamp actualDate) throws IOException, SQLException {
        CallableStatement statement = null;
        try {
            statement = databaseConnection.prepareCall("{CALL US209(?, ?, ?)}");
            statement.registerOutParameter(3, Types.FLOAT);

            statement.setString(1, mmsiCode);
            statement.setTimestamp(2, actualDate);

            statement.execute();

            this.occupancyRate = statement.getFloat(3);

            writeForAFile.writeForAFile(toString(), "US209_" + mmsiCode, new File(".\\outputs\\US209"), false);

        }catch (Exception e){
            writeForAFile.writeForAFile("Something went wrong", "US209_" + mmsiCode, new File(".\\outputs\\US209"), false);
        }finally {
            statement.close();
        }

    }

    @Override
    public String toString() {
        return String.format("Occupancy Rate%n" +
                            "%.2f", occupancyRate);
    }
}
