package lapr.project.ui.funcionalities;

import lapr.project.controller.FindClosestPortController;
import lapr.project.ui.Utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FindClosestPortUi implements Runnable {

    private final FindClosestPortController findClosestPortController;

    public FindClosestPortUi(){
        findClosestPortController = new FindClosestPortController();
    }

    @Override
    public void run() {

        String callSign = Utils.readLineFromConsole("Type the CallSign of the Ship:\n->");
        String date = Utils.readLineFromConsole("Type the date:\n->");

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        try {
            findClosestPortController.findClosestPort(callSign, dateFormatter.parse(date));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
