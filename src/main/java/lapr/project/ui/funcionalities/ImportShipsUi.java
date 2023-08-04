package lapr.project.ui.funcionalities;

import lapr.project.controller.ImportShipController;
import lapr.project.ui.Utils;

public class ImportShipsUi implements Runnable{

    private final ImportShipController importShipController;

    public ImportShipsUi(){
        importShipController = new ImportShipController();
    }
    @Override
    public void run() {
        String fileName;
        do {
            fileName = Utils.readLineFromConsole("Enter the name of the file from which you want to import the ships \n->");
        }while (!importShipController.importFile(fileName));
        importShipController.importFile(fileName);

    }
}
