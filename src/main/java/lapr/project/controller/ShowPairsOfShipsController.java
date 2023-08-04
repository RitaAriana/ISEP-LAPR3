package lapr.project.controller;

import lapr.project.model.BstShip;
import lapr.project.model.Company;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Class responsible for making the connection between the UI and the system when viewing a pairs of ships
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class ShowPairsOfShipsController {

    /**
     * Represents an instance of app.
     */
    private final App app;

    /**
     * Represents a instance of Company
     */
    private final Company company;

    /**
     * Represents a instance of BstShip
     */
    private final BstShip bstShip;

    /**
     * Represents an instance of Write for a file
     */
    private final WriteForAFile writeForAFile;

    /**
     * Initialize the controller
     */
    public ShowPairsOfShipsController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.bstShip=company.getBstShip();
        this.writeForAFile = new WriteForAFile();

    }

    /**
     * Initialize the controller receiving a company
     */
    public ShowPairsOfShipsController(Company company) {
        this.app = App.getInstance();
        this.company = company;
        this.bstShip=company.getBstShip();
        this.writeForAFile = new WriteForAFile();
    }

    /**
     * Get the company associated with the controller
     * @return company associated with the controller
     */
    public Company getCompany(){
        return company;
    }

    /**
     * Get the List with pairs of ships  with routes with close departure/arrival coordinates  and with different Travelled Distance
     * @return List with pairs of ships  with routes with close departure/arrival coordinates  and with different Travelled Distance
     */
    public List<TreeMap<Double,String>> getPairsOfShip() throws IOException {
        List<TreeMap<Double,String>> list = bstShip.getIntendedPairsOfShips();
        File file = new File(".\\outputs\\Pair Of Ships");
        writeForAFile.writeForAFile("Ship1 MMSI  Ship2 MMSI   TravelDistance difference\n" , "pairs_of_ships", file, true);

        for(TreeMap<Double,String> lista : list){
            if(lista.size()!=0){
                writeForAFile.writeForAFile("             \n" , "pairs_of_ships", file, true);
                Iterator iterator = lista.entrySet().iterator();

                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    writeForAFile.writeForAFile(entry.getValue() + "      " + entry.getKey() +"\n" , "pairs_of_ships", file, true);
                }
            }
        }
        writeForAFile.writeForAFile("             \n" , "pairs_of_ships", file, true);
        return  list;
    }
}
