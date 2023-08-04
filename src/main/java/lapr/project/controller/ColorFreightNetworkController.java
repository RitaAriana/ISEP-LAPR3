package lapr.project.controller;

import lapr.project.model.Capital;
import lapr.project.model.Company;
import lapr.project.model.esinf.FreightNetwork;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class ColorFreightNetworkController {

    /**
     * Represents an instance of app.
     */
    private final App app;

    /**
     * Represents an instance of Company
     */
    private final Company company;

    private final FreightNetwork freightNetwork;

    /**
     * Represents an instance of Write for a file
     */
    private final WriteForAFile writeForAFile;


    public ColorFreightNetworkController(){
        this.app=App.getInstance();
        this.company=app.getCompany();
        this.freightNetwork=company.getFreightNetwork();
        this.writeForAFile = new WriteForAFile();
    }



    /**
     * Initialize the controller receiving a company
     */
    public ColorFreightNetworkController(Company company){
        this.app=App.getInstance();
        this.company=company;
        this.freightNetwork=company.getFreightNetwork();
        this.writeForAFile = new WriteForAFile();
    }

    public Map<Capital, Integer> colorNetwork() throws IOException {
        Map<Capital, Integer> result = freightNetwork.colorNetwork();
        File file = new File(".\\outputs\\Map Coloured");
        writeForAFile.writeForAFile("", "MapColoured",file,false);
        Iterator iterator = result.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            writeForAFile.writeForAFile(entry.getKey()+ "," + entry.getValue()  +"\n" , "MapColoured",file,true);
        }

        return result;
    }

    public FreightNetwork getFreightNetwork(){
        return freightNetwork;
    }
}
