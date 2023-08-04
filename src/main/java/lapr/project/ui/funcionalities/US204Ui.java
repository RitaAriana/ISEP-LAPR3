package lapr.project.ui.funcionalities;

import lapr.project.data.US204Handler;
import lapr.project.ui.Utils;

import java.io.IOException;

public class US204Ui implements Runnable {

    private final US204Handler us204Handler;

    public US204Ui(){
        us204Handler = new US204Handler();
    }

    @Override
    public void run() {

        int containerNumber = Utils.readIntegerFromConsole("Type the container number:\n->");

        try {
            us204Handler.getContainerLocation(containerNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
