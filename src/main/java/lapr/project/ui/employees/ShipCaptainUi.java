package lapr.project.ui.employees;

import lapr.project.ui.MenuItem;
import lapr.project.ui.funcionalities.*;
import lapr.project.ui.Utils;

import java.util.ArrayList;
import java.util.List;

public class ShipCaptainUi implements Runnable{


    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("know the current situation of a specific container being used \n" +
                "to transport my goods", new US204Ui()));
        options.add(new MenuItem("list of containers to be offloaded in the next port, \n" +
                "including container identifier, type, position, and load.\n", new US205Ui()));
        options.add(new MenuItem(" list of containers to be loaded in the next port, \n" +
                "including container identifier, type, and load", new US206Ui()));
        options.add(new MenuItem(" know how many cargo manifests I have transported \n" +
                "during a given year and the average number of containers per manifest", new US207Ui()));
        options.add(new MenuItem("I want to know the occupancy rate (percentage) of a given ship \n" +
                "for a given cargo manifest", new US208Ui()));
        options.add(new MenuItem(" I want to know the occupancy rate of a given ship at a given \n" +
                "moment", new US209Ui()));

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
