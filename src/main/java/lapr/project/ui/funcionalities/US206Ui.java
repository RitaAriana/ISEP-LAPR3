package lapr.project.ui.funcionalities;

import lapr.project.data.US206Handler;
import lapr.project.ui.Utils;

import java.io.IOException;

public class US206Ui implements Runnable{

    @Override
    public void run() {
        String mmsiCode = Utils.readLineFromConsole("Type the mmsi code of the ship:\n->");

        try {
            new US206Handler(mmsiCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
