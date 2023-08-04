package lapr.project.ui.employees;

import lapr.project.ui.MenuItem;
import lapr.project.ui.Utils;
import lapr.project.ui.funcionalities.ImportPortUi;
import lapr.project.ui.funcionalities.US207Ui;
import lapr.project.ui.funcionalities.US305Ui;

import java.util.ArrayList;
import java.util.List;

public class ClientUi implements Runnable{


    @Override
    public void run() {

        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("know the route of a specific container", new US305Ui()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nShip Captain Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }

        } while (option != -1 );

    }
}
