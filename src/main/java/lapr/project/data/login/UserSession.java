package lapr.project.data.login;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Manuela Leite <1200720@isep.ipp.pt>
 */
public class UserSession{

    private User user = null;

    public UserSession()
    {
        this.user = null;
    }

    public UserSession(User user)
    {
        if (user == null)
            throw new IllegalArgumentException("Argument cannot be null.");
        this.user = user;
    }

    public void doLogout()
    {
        this.user = null;
    }

    public boolean isLoggedIn()
    {
        return this.user != null;
    }

    public String getUserNam()
    {
        if (isLoggedIn())
            this.user.getUsername();
        return null;
    }

    public Role getUserRole()
    {
        if (isLoggedIn()) {
            return user.getUserRole();
        }
        return null;
    }

}
