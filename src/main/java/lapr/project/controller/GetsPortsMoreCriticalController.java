package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.esinf.FreightNetwork;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GetsPortsMoreCriticalController {

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

    public GetsPortsMoreCriticalController(){
        this.app=App.getInstance();
        this.company=app.getCompany();
        this.freightNetwork=company.getFreightNetwork();
        this.writeForAFile = new WriteForAFile();
    }

    public  List<String> getsPortsMoreCritical(int n) throws IOException {
        List<String> result =freightNetwork.getsPortsMoreCritical(n);
        File file = new File(".\\outputs\\Ports More Critical");
        writeForAFile.writeForAFile("", "Ports More Critical",file,false);
        for (String place : result){
            writeForAFile.writeForAFile(place +"\n" , "Ports More Critical",file,true);
        }
        return result;
    }




}
