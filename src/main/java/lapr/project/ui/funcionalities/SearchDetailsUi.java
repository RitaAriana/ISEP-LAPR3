package lapr.project.ui.funcionalities;

import lapr.project.controller.SearchDetailsController;
import lapr.project.model.Ship;
import lapr.project.ui.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchDetailsUi implements Runnable{
    private final SearchDetailsController searchDetailsController;

    public SearchDetailsUi(){
        searchDetailsController = new SearchDetailsController();
    }


    @Override
    public void run() {
        List<String> options = new ArrayList<>();
        options.add("IMO CODE");
        options.add("MMSI CODE");
        options.add("CALL SIGN");
        int option = Utils.showAndSelectIndex(options, "Write which code you want to search for the boat");
        String code = Utils.readLineFromConsole("Type the code\n->");
        Ship ship = null;
        boolean flag = false;
        do {
            if (option == 1){
                ship = searchDetailsController.shipExistByIMO(code);
                if (ship != null){
                    searchDetailsController.getShipDetails();
                    try {
                        searchDetailsController.writeDataSearchedByIMO();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                flag = true;
            }
            if (option == 2) {
                flag = searchDetailsController.shipExistByMMSI(code);
                if (flag) searchDetailsController.getShipDetails();
            }
            if (option == 3){
                ship = searchDetailsController.shipExistByCallSign(code);
                if (ship != null){
                    searchDetailsController.getShipDetails();
                    try {
                        searchDetailsController.writeDataSearchedByCallsign();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                flag = true;
            }

        }while (ship == null || !flag);

    }
}
