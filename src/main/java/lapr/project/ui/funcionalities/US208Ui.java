package lapr.project.ui.funcionalities;

import lapr.project.data.US208Handler;
import lapr.project.ui.Utils;

import java.io.IOException;

public class US208Ui implements Runnable{


    @Override
    public void run() {
        int mmsiCode = Utils.readIntegerFromConsole("Type the cargo manifest id:\n->");

        try {
            new US208Handler(mmsiCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
