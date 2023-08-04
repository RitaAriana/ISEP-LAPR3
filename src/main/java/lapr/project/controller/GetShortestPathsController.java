package lapr.project.controller;

import lapr.project.model.Capital;
import lapr.project.model.Company;
import lapr.project.model.Place;
import lapr.project.model.Ports;
import lapr.project.model.esinf.FreightNetwork;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GetShortestPathsController {
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

    public GetShortestPathsController(){
        this.app=App.getInstance();
        this.company=app.getCompany();
        this.freightNetwork=company.getFreightNetwork();
        this.writeForAFile = new WriteForAFile();
    }

    public List<List<Place>> getShortestPaths(String origem, String destino,List<String> passagensObrigatorias) throws IOException {
        List<List<Place>> result =freightNetwork.getShortestPaths(origem,destino,passagensObrigatorias);
        File file = new File(".\\outputs\\Shortest Paths");
        writeForAFile.writeForAFile("", "Shortest Paths",file,false);
        for (List<Place> place : result){
            writeForAFile.writeForAFile("--- Caminho ---\n" , "Shortest Paths",file,true);
            if (place == null){
                writeForAFile.writeForAFile("Não foi possível determinar este caminho.\n" , "Shortest Paths",file,true);
            } else {
                for (Place place1: place){
                    if (place1 instanceof Ports){
                        writeForAFile.writeForAFile("Porto "+((Ports) place1).getPortName() +"\n" , "Shortest Paths",file,true);
                    } else if (place1 instanceof Capital){
                        writeForAFile.writeForAFile("Capital "+((Capital) place1).getName() +"\n" , "Shortest Paths",file,true);
                    }

                }
            }


        }
        return result;
    }
}
