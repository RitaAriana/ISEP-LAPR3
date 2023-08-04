package lapr.project.ui.funcionalities;

import lapr.project.data.US207Handler;
import lapr.project.ui.Utils;

import java.io.IOException;

public class US207Ui implements Runnable{


    @Override
    public void run() {

        String mmsiCode = Utils.readLineFromConsole("Type the mmsi code of the ship:\n->");

        int year = Utils.readIntegerFromConsole("Type the year:\n->");

        try {
            new US207Handler(mmsiCode, year);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
