package lapr.project.data;

import lapr.project.controller.App;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateCargoManifest {

    private Connection connection;

    public UpdateCargoManifest(int containerNumber, float gross, String userName){

        try {
            connection = App.getInstance().getDatabaseConnection().getConnection();
            CallableStatement statement = connection.prepareCall("call updateCargoManifestContainer(?, ?, ?)");

            statement.setInt(1, containerNumber);
            statement.setFloat(2, gross);
            statement.setString(3, userName);

            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
