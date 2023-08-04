package lapr.project.data.login;

import java.util.Objects;

/**
 * @author Manuela Leite <1200720@isep.ipp.pt>
 */
public class User {

    private final String username;
    private final String password;
    private final Role userRole;

    public User(String username, String password, Role userRole) {

        this.username = username;
        this.password = password;
        this.userRole = userRole;

    }

    public String getUsername() {
        return username;
    }

    public Role getUserRole() {
        return userRole;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return username.equals(user.username) && password.equals(user.password) && userRole.equals(user.userRole);
    }

}
