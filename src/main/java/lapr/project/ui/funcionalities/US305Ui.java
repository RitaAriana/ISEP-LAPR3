package lapr.project.ui.funcionalities;

import lapr.project.data.US305Handler;
import lapr.project.ui.Utils;

import java.io.IOException;

public class US305Ui implements Runnable {

    @Override
    public void run() {

        int registrationCode = Utils.readIntegerFromConsole("Type the registration code:\n->");
        int containerId = Utils.readIntegerFromConsole("Type the container number id:\n->");
        try {
            new US305Handler(registrationCode, containerId);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
