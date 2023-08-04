package lapr.project.ui.funcionalities;

import lapr.project.controller.MovementsSummaryController;
import lapr.project.mapper.dto.SummaryDto;
import lapr.project.model.Ship;
import lapr.project.ui.Utils;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;

/**
 * Represents an interface with the Traffic Manager to view the summary associated with a ship
 *
 * @author Manuela Leite <1200720@isep.ipp.pt>
 */

public class MovementsSummaryUI implements Runnable{

    /**
     * Represents an instance of Movements Summary Controller
     */
    private final MovementsSummaryController movementsSummaryController;

    private final WriteForAFile writeForAFile;

    /**
     * Initializes the controller
     */
    public MovementsSummaryUI(){
        movementsSummaryController = new MovementsSummaryController();
        writeForAFile = new WriteForAFile();
    }

    /**
     * Invokes the necessary methods for the interface to function.
     */
    @Override
    public void run() {

        System.out.printf("%nMake a Summary of movements for a ship%n");

        String mmsiCode = Utils.readLineFromConsole("Enter the MMSI code of the vessel you wish to obtain information from:");
        if( mmsiCode == null ||mmsiCode.length() != 9) {
            System.out.println("The ship MMSI code must be 9-digit long.");
        } else{
            if (movementsSummaryController.shipExist(mmsiCode)){
                Ship ship = movementsSummaryController.getShipByMmsiCode(mmsiCode);
                lapr.project.model.Summary summary = movementsSummaryController.createSummaryForShip(ship);
                SummaryDto summaryDto = null;
                try {
                    summaryDto = movementsSummaryController.createSummaryDto(summary);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                System.out.println(summaryDto);
                System.out.println();
                try {
                    File file = new File("targe\\generated-sources\\annotations\\Summaries");
                    if (writeForAFile.writeForAFile(summaryDto.toString(), "Summary_" + summaryDto.getMmsiCodeDto(),file, false))
                        System.out.println("Your summary was saved in the file named: Summary_" + summaryDto.getMmsiCodeDto() + ".txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.printf("%nThere is no ship in the system with this MMSI code%n");
            }
        }


    }
}
