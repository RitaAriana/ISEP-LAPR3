package lapr.project.ui;

import lapr.project.data.login.AuthController;
import lapr.project.data.login.Role;
import lapr.project.ui.employees.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Manuela Leite <1200720@isep.ipp.pt>
 */

public class AuthUI implements Runnable{

    private final AuthController ctrl;

    public AuthUI()
    {
        ctrl = new AuthController();
    }

    public void run()
    {
        boolean success = doLogin();

        if (success)
        {
            Role role = this.ctrl.getUserRole();

            if ((role == null))
            {
                System.out.println("User has not any role assigned.");
            }
            else
            {
                if (!Objects.isNull(role))
                {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI,role);
                }
                else
                {
                    System.out.println("User did not select a role.");
                }
            }
        }
        this.logout();
    }

    private List<MenuItem> getMenuItemForRoles()
    {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem("Client", new ClientUi()));
        rolesUI.add(new MenuItem("Fleet Manager", new FleetManagerUi()));
        rolesUI.add(new MenuItem("Traffic manager", new TrafficManagerUi()));
        rolesUI.add(new MenuItem("Warehouse staff", new WareHouseStaffUi()));
        rolesUI.add(new MenuItem("Warehouse manager", new WarehouseManagerUi()));
        rolesUI.add(new MenuItem("Port staff", new PortStaffUi()));
        rolesUI.add(new MenuItem("Port manager", new PortManagerUi()));
        rolesUI.add(new MenuItem("Ship captain", new ShipCaptainUi()));
        rolesUI.add(new MenuItem("Ship chief electrical engineer", new ShipChiefElectricalEngineerUi()));
        rolesUI.add(new MenuItem("Truck driver", new TruckDriverUi()));

        return rolesUI;
    }

    private boolean doLogin()
    {
        System.out.println("\nLogin UI:");

        int maxAttempts = 3;
        boolean success = false;
        do
        {
            maxAttempts--;
            String id = Utils.readLineFromConsole("Enter UserId/Email: ");
            String pwd = Utils.readLineFromConsole("Enter Password: ");

            success = ctrl.doLogin(id, pwd);
            if (!success)
            {
                System.out.println("Invalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
            }

        } while (!success && maxAttempts > 0);
        return success;
    }

    public void logout()
    {
        ctrl.doLogout();
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, Role role)
    {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found)
        {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                item.run();
            }
        }
        if (!found)
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
    }

}
