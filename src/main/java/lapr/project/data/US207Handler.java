package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author Manuela Leite <1200720@isep.ipp.pt>
 */
public class US207Handler {

    /**
     * Represents an instance of Connection
     */
    private final Connection databaseConnection;

    /**
     * Represents the Total of containers in a cargo manifest
     */
    private int totalCargoManifestsPerYear;

    /**
     * Represents the mean Container per cargo manifest
     */
    private float meanContainerPerCargoManifest;

    /**
     * Represents an instance of WriteForAFile
     */
    private final WriteForAFile writeForAFile;

    /**
     * Constructor of the class
     * @param mmsiCode
     * @param year
     * @throws SQLException
     * @throws IOException
     */
    public US207Handler(String mmsiCode, int year) throws IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        writeForAFile = new WriteForAFile();
        try {
            initialize(mmsiCode, year);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the connection with the database and run the script
     * @param mmsiCode
     * @param year
     * @throws SQLException
     * @throws IOException
     */
    private void initialize(String mmsiCode, int year) throws IOException, SQLException {
        CallableStatement statement = null;
        try {
            statement = databaseConnection.prepareCall("{call US207(?, ?, ?, ?)}");
            statement.registerOutParameter(3, Types.INTEGER);
            statement.registerOutParameter(4, Types.FLOAT);

            statement.setInt(1, year);
            statement.setString(2, mmsiCode);

            statement.execute();

            this.totalCargoManifestsPerYear = statement.getInt(3);
            this.meanContainerPerCargoManifest = statement.getFloat(4);

            writeForAFile.writeForAFile(toString(), "US207_" + mmsiCode, new File(".\\outputs\\US207"), false);

        }catch (Exception e){
            writeForAFile.writeForAFile("Something went wrong", "US207_" + mmsiCode, new File(".\\outputs\\US207"), false);

        }finally {
            statement.close();
        }

    }

    /**
     * get the total container per cargo manifest
     * @return the total container per cargo manifest
     */
    public int getTotalCargoManifestsPerYear() {
        return totalCargoManifestsPerYear;
    }

    /**
     * get the mean container per cargo manifest
     * @return the mean container per cargo manifest
     */
    public float getMeanContainerPerCargoManifest() {
        return meanContainerPerCargoManifest;
    }

    /**
     * Textual information
     * @return the textual information
     */
    @Override
    public String toString() {
        return String.format("totalCargoManifestPerYear, meanContainerPerCargoManifest%n" +
                "%d, %.2f%n", totalCargoManifestsPerYear, meanContainerPerCargoManifest);
    }
}
