package lapr.project.ui.funcionalities;

import lapr.project.controller.ListSomeShipDataController;
import lapr.project.model.BriefSummary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an interface with the Traffic Manager to view for all ships the MMSI, the total number of movements,
 * Travelled Distance and Delta Distance.
 *
 * @author Manuela Leite <1200720@isep.ipp.pt>
 * @author Pedro Rocha <1201382@isep.ipp.pt>
 */
public class ListSomeShipDataUi implements Runnable{

    /**
     * Represents an instance of List Some Ship Data Controller
     */
    private final ListSomeShipDataController listSomeShipDataController;

    /**
     *
     */
    private List<BriefSummary> briefSummaries;
    /**
     *
     */
    private final Scanner scanner;

    /**
     * Initializes the controller
     */
    public ListSomeShipDataUi(){
        listSomeShipDataController = new ListSomeShipDataController();
        briefSummaries = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    /**
     * Invokes the necessary methods for the interface to function.
     */
    @Override
    public void run() {
        System.out.println();
        System.out.println("Select how do you want to ordered th list of ships:");
        System.out.println("1- Order by descending travelled distance");
        System.out.println("2- Order by ascending travelled distance");

        int option = scanner.nextInt();


        System.out.printf("%nList of Summaries%n");
        if (option == 1) {
            try {
                briefSummaries = listSomeShipDataController.organizeByDescendingOrder();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (option == 2){
            try {
                briefSummaries = listSomeShipDataController.organizeByAscendingOrder();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (BriefSummary bs : briefSummaries){
            System.out.println(bs);
        }

    }
}
