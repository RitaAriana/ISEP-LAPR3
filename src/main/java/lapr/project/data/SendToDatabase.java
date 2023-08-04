package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.*;

import java.sql.*;

/**
 * Class Responsible for extracting the objects' data and sending it to the database. US111
 *
 * Francisco Redol <1201239@isep.ipp.pt>
 */
public class SendToDatabase implements Persistable {

    /**
     * The database Connection
     */
    DatabaseConnection databaseConnection;

    /**
     * The database Company
     */
    Company company;

    /**
     * Send to Database Connection
     */
    public SendToDatabase() {
        this.databaseConnection = App.getInstance().getDatabaseConnection();
        if(!databaseConnection.connectionWorking())
            databaseConnection = null;
        this.company = App.getInstance().getCompany();
    }

    /**
     * Company inserted constructor
     *
     * @param company for testing purposes
     */
    public SendToDatabase(Company company) {
        this.databaseConnection = App.getInstance().getDatabaseConnection();
        if(!databaseConnection.connectionWorking())
            databaseConnection = null;

        this.company = company;
    }

    //################################# Object fetching RELATED #####################################

    /**
     * Method responsible for sending the Ship and its locations to the database
     */
    public void sendShipsAndLocationsToDatabase() {
        if (databaseConnection != null) {
            for (Object objectShip : company.getBstShip().inOrder()) {
                Ship ship = (Ship) objectShip;
                saveShip(databaseConnection, ship);

                for (Object objectLocation : ship.getShipPosition().inOrder()) {
                    ShipLocation shipLocation = (ShipLocation) objectLocation;
                    saveLocation(databaseConnection, shipLocation);
                }
            }
        }
    }

    /**
     * Method responsible for saving Ports, PlaceLocations and Countries to the database
     */
    public void sendPortsToDatabase() {
        if (databaseConnection != null) {
            for(Ports port : company.getPortStr().getPortsLst())
                savePort(databaseConnection, port);

        }
    }

    /**
     * Method responsible for saving Containers to the database
     */
    public void sendContainersToDatabase() {
        if (databaseConnection != null) {
            Container container = new Container("748323899",1,2,3, 5033407, "justo", 2.4f, 1.5f, 181.7f, 118.5f, 89.9f, 1.1f, "#REPAIRRECOMMENDATION", "CERTIFICATE");
            saveContainer(databaseConnection, container);

        }
    }

    //################################# SHIP RELATED #####################################

    /**
     * Method responsible for saving an object to the database.
     *
     * @param databaseConnection to the database
     * @param object             that is going to be saved
     */
    @Override
    public void saveShip(DatabaseConnection databaseConnection, Object object) {
        Ship ship = (Ship) object;

        try {
            saveShipToDatabase(databaseConnection, ship);

        } catch (SQLException ex) {
            databaseConnection.registerError(ex);
        }
    }

    /**
     * Checks is a Ship is already registered on the database. If the Ship
     * is registered, it updates it. If it is not, it inserts a new one.
     *
     * @param databaseConnection to the database
     * @param ship               that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void saveShipToDatabase(DatabaseConnection databaseConnection, Ship ship)
            throws SQLException {
        if (isShipOnDatabase(databaseConnection, ship))
            updateShipOnDatabase(databaseConnection, ship);
        else
            insertShipOnDatabase(databaseConnection, ship);
    }

    /**
     * Checks if a ship is registered on the Database by its ID.
     *
     * @param databaseConnection to the database
     * @param ship               that is related to the database
     * @return True if the ship is registered, False if otherwise.
     * @throws SQLException in case an error with the database occurs
     */
    private boolean isShipOnDatabase(DatabaseConnection databaseConnection, Ship ship)
            throws SQLException {

        Connection connection = databaseConnection.getConnection();

        boolean isShipOnDatabase;

        String sqlCommand = "select * from ship where mmsiCode = ?";

        PreparedStatement getShipsPreparedStatement = null;

        try {
            getShipsPreparedStatement = connection.prepareStatement(sqlCommand);
            getShipsPreparedStatement.setString(1, ship.getMMSI());
            ResultSet shipsResultSet = getShipsPreparedStatement.executeQuery();
            isShipOnDatabase = shipsResultSet.next();
        }finally {
            assert !(null == getShipsPreparedStatement);
            getShipsPreparedStatement.close();
        }

        return isShipOnDatabase;
    }

    /**
     * Updates an existing ship record on the database.
     *
     * @param databaseConnection to the database
     * @param ship               that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void updateShipOnDatabase(DatabaseConnection databaseConnection, Ship ship)
            throws SQLException {
        String sqlCommand =
                "update ship set imoCode = ?, numberEnergyGenerators = ?, generatorOutput = ?, callSign = ?, draft = ?, shipName = ?, vesselTypeId = ?, shipLength = ?, width = ?, cargo = ?, capacity = ? where mmsiCode = " + ship.getMMSI();

        executeShipStatementOnDatabase(databaseConnection, ship, sqlCommand);
    }

    /**
     * Adds a new ship record to the database.
     *
     * @param databaseConnection to the database
     * @param ship               that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void insertShipOnDatabase(DatabaseConnection databaseConnection, Ship ship)
            throws SQLException {
        String sqlCommand =
                "insert into ship(mmsiCode, imoCode, numberEnergyGenerators, generatorOutput, callSign, draft, shipName, vesselTypeId, shipLength, width, cargo, capacity) values (" + ship.getMMSI() + ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        executeShipStatementOnDatabase(databaseConnection, ship, sqlCommand);
    }

    /**
     * Executes the save Ship Statement.
     *
     * @param databaseConnection to the database
     * @param ship               that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void executeShipStatementOnDatabase(DatabaseConnection databaseConnection, Ship ship, String sqlCommand)
            throws SQLException {
        PreparedStatement saveShipPreparedStatement = null;
        try {
            Connection connection = databaseConnection.getConnection();

             saveShipPreparedStatement = connection.prepareStatement(sqlCommand);

            saveShipPreparedStatement.setString(1, ship.getShipID());
            saveShipPreparedStatement.setInt(2, ship.getEnergyGenerators());
            saveShipPreparedStatement.setFloat(3, ship.getGeneratorOutput());
            saveShipPreparedStatement.setString(4, ship.getCallSign());
            saveShipPreparedStatement.setFloat(5, ship.getDraft());
            saveShipPreparedStatement.setString(6, ship.getName());
            saveShipPreparedStatement.setInt(7, ship.getVesselType());
            saveShipPreparedStatement.setFloat(8, ship.getLength());
            saveShipPreparedStatement.setFloat(9, ship.getWidth());
            saveShipPreparedStatement.setString(10, ship.getCargo());
            saveShipPreparedStatement.setFloat(11, ship.getCapacity());
            saveShipPreparedStatement.executeUpdate();

        }catch (SQLException e){
            databaseConnection.registerError(e);
        }finally {
            assert saveShipPreparedStatement != null;
            saveShipPreparedStatement.close();
        }


    }

    //################################# POSITION RELATED #####################################

    /**
     * Method responsible for saving an object to the database.
     *
     * @param databaseConnection to the database
     * @param object             that is going to be saved
     */
    @Override
    public void saveLocation(DatabaseConnection databaseConnection, Object object) {
        ShipLocation shipLocation = (ShipLocation) object;

        try {
            saveLocationToDatabase(databaseConnection, shipLocation);

        } catch (SQLException e) {
            databaseConnection.registerError(e);
        }
    }

    /**
     * Checks is a ShipLocation is already registered on the database. If the ShipLocation
     * is registered, it updates it. If it is not, it inserts a new one.
     *
     * @param databaseConnection to the database
     * @param shipLocation       that contains the location of a ship
     * @throws SQLException in case an error with the database occurs
     */
    private void saveLocationToDatabase(DatabaseConnection databaseConnection, ShipLocation shipLocation)
            throws SQLException {
        if (isLocationOnDatabase(databaseConnection, shipLocation))
            updateLocationOnDatabase(databaseConnection, shipLocation);
        else
            insertLocationOnDatabase(databaseConnection, shipLocation);
    }

    /**
     * Method responsible for verifying if a certain Location exists on the database
     *
     * @param databaseConnection to the database
     * @param shipLocation       that is related to the database
     * @return if a location exists on the database
     * @throws SQLException in case an error with the database occurs
     */
    private boolean isLocationOnDatabase(DatabaseConnection databaseConnection, ShipLocation shipLocation)
            throws SQLException {
        PreparedStatement getShipLocationPreparedStatement = null;
        boolean isShipOnDatabase;
        try {
            Connection connection = databaseConnection.getConnection();

            String sqlCommand = "select * from ShipLocation where shipMmsiCode = ? and baseDateTime = ?";

            getShipLocationPreparedStatement = connection.prepareStatement(sqlCommand);

            getShipLocationPreparedStatement.setString(1, shipLocation.getMMSI());
            getShipLocationPreparedStatement.setTimestamp(2, new Timestamp(shipLocation.getMessageTime().getTime()));
            ResultSet shipLocationResultSet = getShipLocationPreparedStatement.executeQuery();
            isShipOnDatabase = shipLocationResultSet.next();

        } finally {
            assert getShipLocationPreparedStatement != null;
            getShipLocationPreparedStatement.close();
        }
        return isShipOnDatabase;
    }

    /**
     * Updates an existing Position record on the database.
     *
     * @param databaseConnection to the database
     * @param shipLocation       that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void updateLocationOnDatabase(DatabaseConnection databaseConnection, ShipLocation shipLocation)
            throws SQLException {
        String sqlCommand =
                "update ShipLocation set latitude = ?, longitude = ?, sog = ?, cog = ?, heading = ?, position = ?, transceiver = ? where shipMmsiCode = ? and baseDateTime = ?";

        executeShipLocationStatementOnDatabase(databaseConnection, shipLocation, sqlCommand, false);
    }

    /**
     * Adds a new shipLocation record to the database.
     *
     * @param databaseConnection to the database
     * @param shipLocation       that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void insertLocationOnDatabase(DatabaseConnection databaseConnection, ShipLocation shipLocation)
            throws SQLException {

        String sqlCommand = "insert into ShipLocation(shipMmsiCode, baseDateTime, latitude, longitude, sog, cog, heading, position, transceiver) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        executeShipLocationStatementOnDatabase(databaseConnection, shipLocation, sqlCommand, true);
    }

    /**
     * Executes the save ShipLocation Statement.
     *
     * @param databaseConnection connection to the database
     * @param shipLocation       that is related to the database
     * @param type               of insert that is being made
     * @throws SQLException in case an error with the database occurs
     */
    private void executeShipLocationStatementOnDatabase(DatabaseConnection databaseConnection, ShipLocation shipLocation, String sqlCommand, boolean type)
            throws SQLException {
        PreparedStatement getShipLocationPreparedStatement = null;
        try {
            Connection connection = databaseConnection.getConnection();
            getShipLocationPreparedStatement = connection.prepareStatement(sqlCommand);

            if (type) {
                getShipLocationPreparedStatement.setString(1, shipLocation.getMMSI());
                getShipLocationPreparedStatement.setTimestamp(2, new Timestamp(shipLocation.getMessageTime().getTime()));
                getShipLocationPreparedStatement.setString(3, shipLocation.getLatitude());
                getShipLocationPreparedStatement.setString(4, shipLocation.getLongitude());
                getShipLocationPreparedStatement.setFloat(5, shipLocation.getSOG());
                getShipLocationPreparedStatement.setFloat(6, shipLocation.getCOG());
                getShipLocationPreparedStatement.setString(7, shipLocation.getHeading());
                getShipLocationPreparedStatement.setString(8, shipLocation.getPosition());
                getShipLocationPreparedStatement.setString(9, shipLocation.getTransceiverClass());
            } else {
                getShipLocationPreparedStatement.setString(1, shipLocation.getLatitude());
                getShipLocationPreparedStatement.setString(2, shipLocation.getLongitude());
                getShipLocationPreparedStatement.setFloat(3, shipLocation.getSOG());
                getShipLocationPreparedStatement.setFloat(4, shipLocation.getCOG());
                getShipLocationPreparedStatement.setString(5, shipLocation.getHeading());
                getShipLocationPreparedStatement.setString(6, shipLocation.getPosition());
                getShipLocationPreparedStatement.setString(7, shipLocation.getTransceiverClass());
                getShipLocationPreparedStatement.setString(8, shipLocation.getMMSI());
                getShipLocationPreparedStatement.setTimestamp(9, new Timestamp(shipLocation.getMessageTime().getTime()));

            }
            getShipLocationPreparedStatement.executeUpdate();

        }catch (SQLException e){
            databaseConnection.registerError(e);
        } finally {
            assert getShipLocationPreparedStatement != null;
            getShipLocationPreparedStatement.close();
        }

    }

    //################################# Port Related ###########################################

    /**
     * Method responsible for saving an object to the database.
     *
     * @param databaseConnection to the database
     * @param object             that is going to be saved
     */
    @Override
    public void savePort(DatabaseConnection databaseConnection, Object object) {
        Ports port = (Ports) object;
        try {
            savePortToDatabase(databaseConnection, port);

        } catch (SQLException ex) {
            databaseConnection.registerError(ex);
        }
    }

    /**
     * Checks is a Port is already registered on the database. If the Port
     * is registered, it updates it. If it is not, it inserts a new one.
     *
     * @param databaseConnection to the database
     * @param port               that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void savePortToDatabase(DatabaseConnection databaseConnection, Ports port)
            throws SQLException {

        if (!isCountryOnDatabase(databaseConnection, port))
            insertCountryOnDatabase(databaseConnection, port);

        if (!isPlaceLocationOnDatabase(databaseConnection, port))
            insertPlaceLocationOnDatabase(databaseConnection, port);

        if (isPortOnDatabase(databaseConnection, port))
            updatePortOnDatabase(databaseConnection, port);
        else
            insertPortOnDatabase(databaseConnection, port);
    }

    /**
     * Checks if a port is registered on the Database by its ID.
     *
     * @param databaseConnection to the database
     * @param port               that is related to the database
     * @return True if the ship is registered, False if otherwise.
     * @throws SQLException in case an error with the database occurs
     */
    private boolean isPortOnDatabase(DatabaseConnection databaseConnection, Ports port)
            throws SQLException {

        boolean isPortOnDatabase;
        PreparedStatement getPortsPreparedStatement = null;
        ResultSet portsResultSet = null;

        try {
            Connection connection = databaseConnection.getConnection();

            String sqlCommand = "select * from ports where id = ?";

            getPortsPreparedStatement = connection.prepareStatement(sqlCommand);

            getPortsPreparedStatement.setInt(1, port.getCode());

            portsResultSet = getPortsPreparedStatement.executeQuery();
            isPortOnDatabase = portsResultSet.next();



        }finally {
            assert portsResultSet != null;
            portsResultSet.close();
            getPortsPreparedStatement.close();
        }

        return isPortOnDatabase;
    }

    /**
     * Updates an existing port record on the database.
     *
     * @param databaseConnection to the database
     * @param port               that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void updatePortOnDatabase(DatabaseConnection databaseConnection, Ports port)
            throws SQLException {
        String sqlCommand =
                "update ports set name = ?, placeLocationLatitude = ?, placeLocationLongitude = ? where id = " + port.getCode();

        executePortStatementOnDatabase(databaseConnection, port, sqlCommand);
    }

    /**
     * Adds a new port record to the database.
     *
     * @param databaseConnection to the database
     * @param port               that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void insertPortOnDatabase(DatabaseConnection databaseConnection, Ports port)
            throws SQLException {
        String sqlCommand =
                "insert into ports(id, name, placeLocationLatitude, placeLocationLongitude) values (" + port.getCode() + ", ?, ?, ?)";

        executePortStatementOnDatabase(databaseConnection, port, sqlCommand);
    }

    /**
     * Executes the save Port Statement.
     *
     * @param databaseConnection to the database
     * @param port               that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void executePortStatementOnDatabase(DatabaseConnection databaseConnection, Ports port, String sqlCommand)
            throws SQLException {

        Connection connection = databaseConnection.getConnection();

        PreparedStatement savePortPreparedStatement = connection.prepareStatement(sqlCommand);

        savePortPreparedStatement.setString(1, port.getPortName());
        savePortPreparedStatement.setString(2, String.format("%.2f", port.getLatitude()));
        savePortPreparedStatement.setString(3, String.format("%.2f", port.getLongitude()));

        try {
            savePortPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            databaseConnection.registerError(e);
        } finally {
            savePortPreparedStatement.close();
        }
    }

    //################################# Place Location Related ##################################

    /**
     * Method that serves the purpose of verifying if a certain Place Location exists on the database.
     *
     * @param databaseConnection to the database
     * @param port               that contains the Place Location Data
     * @return if the Place Location is on the database
     * @throws SQLException in case an error with the database occurs
     */
    private boolean isPlaceLocationOnDatabase(DatabaseConnection databaseConnection, Ports port)
            throws SQLException {

        Connection connection = databaseConnection.getConnection();

        boolean isPlaceLocationOnDatabase;

        String sqlCommand = "select * from PlaceLocation where countryName = ? and latitude = ? and longitude = ?";

        PreparedStatement getPlaceLocationPreparedStatement = connection.prepareStatement(sqlCommand);

        getPlaceLocationPreparedStatement.setString(1, port.getCountryName());
        getPlaceLocationPreparedStatement.setString(2, String.format("%.2f", port.getLatitude()));
        getPlaceLocationPreparedStatement.setString(3, String.format("%.2f", port.getLongitude()));

        try (ResultSet placeLocationResultSet = getPlaceLocationPreparedStatement.executeQuery()) {

            isPlaceLocationOnDatabase = placeLocationResultSet.next();
            placeLocationResultSet.close();

        } finally {
            getPlaceLocationPreparedStatement.close();
        }
        return isPlaceLocationOnDatabase;
    }

    /**
     * Method responsible for inserting a certain Place Location on the database.
     *
     * @param databaseConnection to the database
     * @param port               that contains the Place Location Data
     * @throws SQLException in case an error with the database occurs
     */
    private void insertPlaceLocationOnDatabase(DatabaseConnection databaseConnection, Ports port)
            throws SQLException {
        String sqlCommand =
                "insert into PlaceLocation(countryName, latitude, longitude) values (?, ?, ?)";

        executePlaceLocationStatementOnDatabase(databaseConnection, port, sqlCommand);
    }

    /**
     * Method responsible for executing the PlaceLocationPreparedStatement
     *
     * @param databaseConnection to the database
     * @param port               that contains the Place Location Data
     * @param sqlCommand         that is going to be executed on the database
     */
    private void executePlaceLocationStatementOnDatabase(DatabaseConnection databaseConnection, Ports port, String sqlCommand)
            throws SQLException {

        Connection connection = databaseConnection.getConnection();

        PreparedStatement savePlaceLocationPreparedStatement = connection.prepareStatement(sqlCommand);

        savePlaceLocationPreparedStatement.setString(1, port.getCountryName());
        savePlaceLocationPreparedStatement.setString(2, String.format("%.2f", port.getLatitude()));
        savePlaceLocationPreparedStatement.setString(3, String.format("%.2f", port.getLongitude()));

        try {
            savePlaceLocationPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            databaseConnection.registerError(e);
        } finally {
            savePlaceLocationPreparedStatement.close();
        }
    }

    //####################################### Country Related ###################################

    /**
     * Method that verifies if a certain country is on the database.
     *
     * @param databaseConnection to the database
     * @param port               that contains the Place Location Data
     * @return if the selected Country is on the database
     * @throws SQLException in case an error with the database occurs
     */
    private boolean isCountryOnDatabase(DatabaseConnection databaseConnection, Ports port)
            throws SQLException {

        Connection connection = databaseConnection.getConnection();

        boolean isCountryOnDatabase;

        String sqlCommand = "select * from Country where countryName = ?";

        PreparedStatement getCountryPreparedStatement = connection.prepareStatement(sqlCommand);

        getCountryPreparedStatement.setString(1, port.getCountryName());

        try (ResultSet placeLocationResultSet = getCountryPreparedStatement.executeQuery()) {

            isCountryOnDatabase = placeLocationResultSet.next();
            placeLocationResultSet.close();

        } finally {
            getCountryPreparedStatement.close();
        }
        return isCountryOnDatabase;
    }

    /**
     * Method that inserts a certain Country on the database.
     *
     * @param databaseConnection to the database
     * @param port               that contains the Place Location Data
     * @throws SQLException in case an error with the database occurs
     */
    private void insertCountryOnDatabase(DatabaseConnection databaseConnection, Ports port)
            throws SQLException {

        Connection connection = databaseConnection.getConnection();

        String sqlCommand = "insert into Country (countryName, continent) values (?, ?)";

        PreparedStatement saveCountryPreparedStatement = connection.prepareStatement(sqlCommand);

        saveCountryPreparedStatement.setString(1, port.getCountryName());
        saveCountryPreparedStatement.setString(2, port.getContinent());

        try {
            saveCountryPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error when importing the Country named" + port.getCountryName() + ".");
            databaseConnection.registerError(e);
        } finally {
            saveCountryPreparedStatement.close();
        }
    }

    //####################################### Container Related #################################

    /**
     * Method responsible for invoking the methods which will verify if a certain Container exists, update it or insert it.
     *
     * @param databaseConnection the database connection
     * @param object             the object that is going to be saved
     */
    @Override
    public void saveContainer(DatabaseConnection databaseConnection, Object object) {
        Container container = (Container) object;

        try {
            saveContainerToDatabase(databaseConnection, container);

        } catch (SQLException ex) {
            databaseConnection.registerError(ex);
        }
    }

    /**
     * Method responsible for verifying if a certain container exists, updates it or inserts it.
     *
     * @param databaseConnection to the database
     * @param container          that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void saveContainerToDatabase(DatabaseConnection databaseConnection, Container container)
            throws SQLException {

        if (isContainerOnDatabase(databaseConnection, container))
            updateContainerOnDatabase(databaseConnection, container);
        else
            insertContainerOnDatabase(databaseConnection, container);
    }

    /**
     * Checks if a container is registered on the Database by its container.
     *
     * @param databaseConnection to the database
     * @param container          that is related to the database
     * @return True if the ship is registered, False if otherwise.
     * @throws SQLException in case an error with the database occurs
     */
    private boolean isContainerOnDatabase(DatabaseConnection databaseConnection, Container container)
            throws SQLException {

        Connection connection = databaseConnection.getConnection();

        boolean isContainerOnDatabase;

        String sqlCommand = "select * from Container where numberId = ?";

        PreparedStatement getContainerPreparedStatement =
                connection.prepareStatement(sqlCommand);

        getContainerPreparedStatement.setInt(1, Integer.parseInt(container.getNumber()));

        try (ResultSet containerResultSet = getContainerPreparedStatement.executeQuery()) {

            isContainerOnDatabase = containerResultSet.next();
            containerResultSet.close();

        } finally {
            getContainerPreparedStatement.close();
        }
        return isContainerOnDatabase;
    }

    /**
     * Updates an existing container record on the database.
     *
     * @param databaseConnection to the database
     * @param container          that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void updateContainerOnDatabase(DatabaseConnection databaseConnection, Container container)
            throws SQLException {
        String sqlCommand =
                "update container set checkDigit = ?, isoCode = ?, maxWeight = ?, payload = ?, tare = ?, weight = ?, maxWeightPacked = ?, maxVolumePacked = ?, repairRecommendation = ?, certificate = ? where numberId = '" + container.getNumber() + "'";

        executeContainerStatementOnDatabase(databaseConnection, container, sqlCommand);
    }

    /**
     * Adds a new container record to the database.
     *
     * @param databaseConnection to the database
     * @param container          that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void insertContainerOnDatabase(DatabaseConnection databaseConnection, Container container)
            throws SQLException {
        String sqlCommand =
                "insert into container(numberId, checkDigit, isoCode, maxWeight, payload, tare, weight, maxWeightPacked, maxVolumePacked, repairRecommendation, certificate) values ('" + container.getNumber() + "', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        executeContainerStatementOnDatabase(databaseConnection, container, sqlCommand);
    }

    /**
     * Executes the save Container Statement.
     *
     * @param databaseConnection to the database
     * @param container          that is related to the database
     * @throws SQLException in case an error with the database occurs
     */
    private void executeContainerStatementOnDatabase(DatabaseConnection databaseConnection, Container container, String sqlCommand)
            throws SQLException {

        Connection connection = databaseConnection.getConnection();

        PreparedStatement saveContainerPreparedStatement = connection.prepareStatement(sqlCommand);

        saveContainerPreparedStatement.setInt(1, container.getCheckDigit());
        saveContainerPreparedStatement.setString(2, container.getIsoCode());
        saveContainerPreparedStatement.setFloat(3, container.getMaximumWeight());
        saveContainerPreparedStatement.setFloat(4, container.getPayload());
        saveContainerPreparedStatement.setFloat(5, container.getTare());
        saveContainerPreparedStatement.setFloat(6, container.getWeight());
        saveContainerPreparedStatement.setFloat(7, container.getMaxWeightPacked());
        saveContainerPreparedStatement.setFloat(8, container.getMaxVolumePacked());
        saveContainerPreparedStatement.setString(9, container.getRepairRecommendation());
        saveContainerPreparedStatement.setString(10, container.getCertificate());

        try {
            saveContainerPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            databaseConnection.registerError(e);
        } finally {
            saveContainerPreparedStatement.close();
        }
    }

}