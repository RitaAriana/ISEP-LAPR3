package lapr.project.ui.employees;

import lapr.project.ui.MenuItem;
import lapr.project.ui.funcionalities.ImportPortUi;
import lapr.project.ui.funcionalities.US207Ui;
import lapr.project.ui.Utils;

import java.util.ArrayList;
import java.util.List;

public class PortManagerUi implements Runnable{


    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem(" import ports from a text file and create a 2D-tree\n" +
                "with port locations", new ImportPortUi()));
        options.add(new MenuItem(" know how many cargo manifests I have transported \n" +
                "during a given year and the average number of containers per manifest", new US207Ui()));


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
