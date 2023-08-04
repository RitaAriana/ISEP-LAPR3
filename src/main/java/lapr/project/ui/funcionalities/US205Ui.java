package lapr.project.ui.funcionalities;

import lapr.project.data.US205Handler;
import lapr.project.ui.Utils;

import java.io.IOException;

public class US205Ui implements Runnable {

    @Override
    public void run() {

        String mmsiCode = Utils.readLineFromConsole("Type the mmsi code of the ship:\n->");

        try {
            new US205Handler(mmsiCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
