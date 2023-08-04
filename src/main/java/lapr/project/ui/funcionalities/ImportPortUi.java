package lapr.project.ui.funcionalities;

import lapr.project.controller.ImportPortController;
import lapr.project.ui.Utils;

public class ImportPortUi implements Runnable {

    private final ImportPortController importPortController;

    public ImportPortUi(){
        importPortController = new ImportPortController();
    }

    @Override
    public void run() {

        String fileName;

        do {
            fileName = Utils.readLineFromConsole("Type the file name:\n->");
        }while (!importPortController.importFile(fileName));

        importPortController.importPorts();
    }
}
