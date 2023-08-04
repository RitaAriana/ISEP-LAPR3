package lapr.project.ui.funcionalities;

import lapr.project.controller.ShowPositionalMessagesController;
import lapr.project.ui.Utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ShowPositionalMessagesUi implements Runnable {

    private final ShowPositionalMessagesController showPositionalMessagesController;

    public ShowPositionalMessagesUi(){

        showPositionalMessagesController = new ShowPositionalMessagesController();
    }

    @Override
    public void run() {

        String mmsi;

        do {
            mmsi = Utils.readLineFromConsole("Insert the MMSI code of the ship:\n ->");
        }while (!showPositionalMessagesController.shipExist(mmsi));

        String initialDate = Utils.readLineFromConsole("Insert the initial date:\n->");
        String finalDate = Utils.readLineFromConsole("Insert the final date:\n->");

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        try {
            showPositionalMessagesController.showPositionalMessages(dateFormatter.parse(initialDate), dateFormatter.parse(finalDate));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
