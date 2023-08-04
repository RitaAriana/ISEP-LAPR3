package lapr.project.controller;

import lapr.project.model.BriefSummary;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.utils.WriteForAFile;


import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class responsible for making the connection between the UI and the system when the user
 * wants to list for all ships the MMSI, the total number of movements, Travelled Distance and Delta Distance.
 *
 * @author Manuela Leite <1200720@isep.ipp.pt>
 * @author Pedro Rocha <1201382@isep.ipp.pt>
 */
public class ListSomeShipDataController {

    /**
     * Represents an instance of Company
     */
    private final Company company;

    /**
     * Represents a list of the Ships
     */
    private Iterable<Ship> inOrder;

    /**
     * Represents a list of the Brief Summaries
     */
    private List<BriefSummary> briefSummaries;

    /**
     * Represents an instance of Write for a file
     */
    private final WriteForAFile writeForAFile;

    /**
     * Represents an instance of file
     */
    private final File file;

    /**
     * Initialize the controller
     */
    public ListSomeShipDataController(){
        company = App.getInstance().getCompany();
        writeForAFile = new WriteForAFile();
        file = new File(".\\outputs\\SomeShipData");
    }

    private void initialize(){
        briefSummaries = new ArrayList<>();
        inOrder = this.company.getBstShip().inOrder();

        for (Ship s : inOrder){
            BriefSummary briefSummary = new BriefSummary(s.getMMSI(), s.getShipPosition().getTotalMovements(), s.getShipPosition().getDeltaDistance(), s.getShipPosition().getTravelledDistance());
            briefSummaries.add(briefSummary);
        }

    }

    /**
     * Organize the list of brief summaries by ascending order of travelled distance
     * @return the ordered list
     */
    public List<BriefSummary> organizeByDescendingOrder() throws IOException {
        initialize();
        Collections.sort(briefSummaries, new Comparator<BriefSummary>() {
            @Override
            public int compare(BriefSummary o1, BriefSummary o2) {
                if (o1.getTravelledDistance() < o2.getTravelledDistance()) return 1;
                if (o1.getTravelledDistance() > o2.getTravelledDistance()) return -1;
                else return 0;
            }
        });
        writeForAFile.writeForAFile(briefSummaries.toString(), "OrderedByDescendingOrder", file, false);
        return briefSummaries;
    }

    /**
     * Organize the list of brief summaries by descending order of number of movements
     * @return the ordered list
     */
    public List<BriefSummary> organizeByAscendingOrder() throws IOException {
        initialize();
        Collections.sort(briefSummaries, new Comparator<BriefSummary>() {
            @Override
            public int compare(BriefSummary o1, BriefSummary o2) {
                if (o1.getTotalNumberOfMovements() > o2.getTotalNumberOfMovements()) return 1;
                if (o1.getTotalNumberOfMovements() < o2.getTotalNumberOfMovements()) return -1;
                else  return 0;
            }
        });
        writeForAFile.writeForAFile(briefSummaries.toString(), "OrderedByAscendingOrder", file, false);
        return briefSummaries;
    }



}
