package lapr.project.controller;

import lapr.project.model.Capital;
import lapr.project.model.Company;
import lapr.project.model.Place;
import lapr.project.model.Ports;
import lapr.project.model.esinf.FreightNetwork;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class GetTheMostEfficientCircuitController {

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

    public GetTheMostEfficientCircuitController(){
        this.app=App.getInstance();
        this.company=app.getCompany();
        this.freightNetwork=company.getFreightNetwork();
        this.writeForAFile = new WriteForAFile();
    }

    public LinkedList<Place> getTheMostEfficientCircuit(String origem) throws IOException {
        LinkedList<Place> result =freightNetwork.mostEfficientCircuit(origem);
        File file = new File(".\\outputs\\Efficient Circuit");
        writeForAFile.writeForAFile("", "Efficient Circuit",file,false);
        writeForAFile.writeForAFile("--- Circuito ---\n" , "Efficient Circuit",file,true);
        for (Place place1: result){
            if (place1 instanceof Ports){
                writeForAFile.writeForAFile("Porto "+((Ports) place1).getPortName() +"\n" , "Efficient Circuit",file,true);
            } else if (place1 instanceof Capital){
                writeForAFile.writeForAFile("Capital "+((Capital) place1).getName() +"\n" , "Efficient Circuit",file,true);
            }

        }
        return result;
    }

}
