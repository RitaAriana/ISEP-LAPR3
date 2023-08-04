package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.login.AuthFacade;
import lapr.project.data.login.UserSession;
import lapr.project.model.Company;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Class that represents a app.
 *
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 * @author Francisco Redol <1201239@isep.ipp.pt>
 */
public class App {

    /**
     * Represents a instance of company.
     */
    private final Company company;

    private  final AuthFacade authFacade;

    /**
     * Represents the App's connection to the database
     */
    private final DatabaseConnection databaseConnection;

    private App(){


        this.databaseConnection = initializeConnection();
        company = new Company();
        authFacade = company.getAuthFacade();
    }

    /**
     * Allows the user to get the instance of the running App's company.
     * @return the instance of the running App company
     */

    public Company getCompany(){
        return company;
    }

    //############# Singleton #############
    private static App singleton = null;

    public static App getInstance() {

        if (singleton == null) {

            synchronized (App.class) {
                singleton = new App();
            }
        }

        return singleton;
    }

    /**
     * Method responsible for initializing the database connection.
     * @return the database connection
     */
    private DatabaseConnection initializeConnection(){
        DatabaseConnection databaseConnection1 = null;
        try {
            databaseConnection1 = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return databaseConnection1;
    }

    /**
     * Allows the user to get the instance of the running App's database connection.
     * @return the App's database connection
     */
    public DatabaseConnection getDatabaseConnection(){
        return databaseConnection;
    }


    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String username, String pwd) throws SQLException {
        return this.authFacade.doLogin(username,pwd).isLoggedIn();
    }

    public void doLogout()
    {
        this.authFacade.doLogout();
    }
}
