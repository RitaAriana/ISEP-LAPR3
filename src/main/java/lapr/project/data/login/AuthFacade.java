package lapr.project.data.login;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author Manuela Leite <1200720@isep.ipp.pt>
 */
public class AuthFacade {
    private UserSession userSession;
    private final UserStore userStore;

    public AuthFacade()
    {
        this.userSession = new UserSession();
        this.userStore = new UserStore();
    }


    public boolean addUserWithRole(String username, String password, String roleName) throws SQLException {

        return this.userStore.createUser(username, password, roleName);

    }

    public UserSession doLogin(String username, String password) throws SQLException {

        User user = this.userStore.getByUsername(username,password);

        if (user.getUsername().equals(username)){
            this.userSession = new UserSession(user);
        }

        return this.userSession;
    }

    public void doLogout()
    {
        this.userSession.doLogout();
    }

    public UserSession getCurrentUserSession()
    {
        return this.userSession;
    }

}
