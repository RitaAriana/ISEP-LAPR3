package lapr.project.ui.employees;

import lapr.project.ui.MenuItem;
import lapr.project.ui.funcionalities.*;
import lapr.project.ui.Utils;

import java.util.ArrayList;
import java.util.List;

public class TrafficManagerUi implements Runnable{
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Import ships from a text file into a BST", new ImportShipsUi()));
        options.add(new MenuItem("Search the details of a ship using any of its codes: \n" +
                "MMSI, IMO or Call Sign", new SearchDetailsUi()));
        options.add(new MenuItem("Have the positional messages temporally \n" +
                "organized and associated with each of the ships", new ShowPositionalMessagesUi()));
        options.add(new MenuItem("make a Summary of a ship's movements", new MovementsSummaryUI()));
        options.add(new MenuItem(" list for all ships the MMSI, the total number of \n" +
                "movements, Travelled Distance and Delta Distance", new ListSomeShipDataUi()));

        options.add(new MenuItem(" find the closest port of a ship given its CallSign, \n" +
                "on a certain DateTime", new FindClosestPortUi()));

        options.add(new MenuItem("o know which ships will be available on Monday \n" +
                "next week and their location", new US210Ui()));
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
