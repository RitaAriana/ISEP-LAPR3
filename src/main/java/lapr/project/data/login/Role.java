package lapr.project.data.login;

import java.util.Objects;

/**
 * @author Manuela Leite <1200720@isep.ipp.pt>
 */
public class Role {

    private final int id;
    private final String description;

    public Role(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return id == role.id && description.equals(role.description);
    }

}
