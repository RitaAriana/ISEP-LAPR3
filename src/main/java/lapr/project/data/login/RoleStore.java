package lapr.project.data.login;

import lapr.project.controller.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Manuela Leite <1200720@isep.ipp.pt>
 */
public class RoleStore {

    private Connection connectionDatabase;
    

    public boolean roleExist(int id, String description) throws SQLException {

        boolean isRoleOnDatabase = false;
        ResultSet userResultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            connectionDatabase = App.getInstance().getDatabaseConnection().getConnection();

            String sqlCommand = "select id from Role where id = ? and designation = ?";

            preparedStatement = connectionDatabase.prepareStatement(sqlCommand);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, description);

            try {
                userResultSet = preparedStatement.executeQuery();
                isRoleOnDatabase = userResultSet.next();
            }catch (SQLException e){
                e.printStackTrace();
            } finally {
                assert userResultSet != null;
                userResultSet.close();
            }
        }finally {
            assert preparedStatement != null;
            preparedStatement.close();
        }

        return isRoleOnDatabase;
    }

    public int getRoleId(String roleName) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet userResultSet = null;
        int roleId;
        try {
            connectionDatabase = App.getInstance().getDatabaseConnection().getConnection();

            String sqlCommand = "select id from Role where designation = ?";

            preparedStatement = connectionDatabase.prepareStatement(sqlCommand);

            preparedStatement.setString(1, roleName);

            try {
                userResultSet = preparedStatement.executeQuery();
                userResultSet.next();
                roleId = userResultSet.getInt(1);

            } finally {
                assert userResultSet != null;
                userResultSet.close();
            }
        }finally {
            assert preparedStatement != null;
            preparedStatement.close();
        }

        return roleId;
    }

    public String getRoleName(int id) throws SQLException {
        String roleName = null;
        PreparedStatement preparedStatement = null;
        ResultSet userResultSet = null;

        try {
            connectionDatabase = App.getInstance().getDatabaseConnection().getConnection();

            String sqlCommand = "select designation from Role where id = ?";

            preparedStatement = connectionDatabase.prepareStatement(sqlCommand);

            preparedStatement.setInt(1,id);

            try {
                userResultSet = preparedStatement.executeQuery();
                userResultSet.next();
                roleName = userResultSet.getString("designation");

            } finally {
                assert userResultSet != null;
                userResultSet.close();

            }
        }finally {
            assert preparedStatement != null;
            preparedStatement.close();
        }

        return roleName;
    }



}
