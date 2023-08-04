package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class US406Handler {

    private final Connection databaseConnection;
    private String initialTime;
    private String endTime;
    private String mmsi;
    private String outputInfo;
    private final WriteForAFile writeForAFile;

    public US406Handler(String initialTime, String endTime, String mmsi) throws IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        writeForAFile = new WriteForAFile();
        try {
            initialize(initialTime, endTime, mmsi);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize(String initialTime, String endTime, String mmsi) throws IOException, SQLException {
        CallableStatement statement = null;
        try {
            statement = databaseConnection.prepareCall("{call US406(?, ?, ?, ?)}");
            statement.registerOutParameter(4, Types.VARCHAR);



            SimpleDateFormat dateFormat = new SimpleDateFormat("yy.MM.dd hh:mm:ss");
            java.util.Date parsedDate = dateFormat.parse(initialTime);
            Timestamp init = new Timestamp(parsedDate.getTime());

            java.util.Date parsedDate1 = dateFormat.parse(endTime);
            Timestamp endt = new Timestamp(parsedDate1.getTime());

            statement.setTimestamp(1,init);
            statement.setTimestamp(2,endt);
            statement.setString(3, mmsi);

            statement.execute();

            this.initialTime=initialTime;
            this.endTime=endTime;
            this.mmsi=mmsi;
            this.outputInfo=statement.getString(4);

            writeForAFile.writeForAFile(toString(), "US406_" + mmsi, new File(".\\outputs\\US406"), false);

        }catch (Exception e){
            e.printStackTrace();
            writeForAFile.writeForAFile("Something went wrong", "US406_" + mmsi, new File(".\\outputs\\US406"), false);
        }finally {
            assert statement != null;
            statement.close();
        }

    }

    @Override
    public String toString() {
        return String.format("Manifests of the ship %s with an occupancy rate above 66 during %s to %s:%n%n%s", mmsi, initialTime, endTime, outputInfo);
    }
}
