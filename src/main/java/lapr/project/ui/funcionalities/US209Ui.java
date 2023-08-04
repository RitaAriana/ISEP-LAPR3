package lapr.project.ui.funcionalities;

import lapr.project.data.US209Handler;
import lapr.project.ui.Utils;
import org.xml.sax.helpers.AttributesImpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class US209Ui implements Runnable {
    @Override
    public void run() {

        String mmsiCode = Utils.readLineFromConsole("Type the mmsi code of the ship:\n->");
        String date = Utils.readLineFromConsole("Type the date:\n->");


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy hh:mm:ss");
        Timestamp timestamp = null;
        try {
            Date date1 = dateFormat.parse(date);
            timestamp = new java.sql.Timestamp(date1.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            new US209Handler(mmsiCode, timestamp);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
