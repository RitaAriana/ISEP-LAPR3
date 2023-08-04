package lapr.project.ui.funcionalities;

import lapr.project.data.US210Handler;

import java.io.IOException;

public class US210Ui implements Runnable {

    @Override
    public void run() {
        try {
            new US210Handler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
