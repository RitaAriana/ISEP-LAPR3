package lapr.project.model;


import lapr.project.controller.App;
import lapr.project.model.esinf.KDTree;
import lapr.project.model.store.CountryStore;
import lapr.project.model.store.PortStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The ImportPort class, which allows the user to import ports from a .csv file
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class ImportPort {

    /**
     * Represents an instance of Company
     */
    private final Company company;

    /**
     * Represents an instance of PortStore
     */
    private final PortStore portStore;

    /**
     * The file to be imported
     */
    private File file;

    /**
     * The Scanner which reads the file
     */
    private Scanner read;

    /**
     * Represents an instance of CountryStore
     */
    private final CountryStore str;

    private List<KDTree.Node<Ports>> lst;


    /**
     * The class constructor
     */
    public ImportPort() {
        this.company=App.getInstance().getCompany();
        this.str=company.getCountryStr();
        this.portStore=company.getPortStr();
        lst = new ArrayList<>();
    }

    /**
     * The class constructor
     */
    public ImportPort(Company company) {
        this.company=company;
        this.str=company.getCountryStr();
        this.portStore=company.getPortStr();
        lst = new ArrayList<>();
    }

    public PortStore getPortStore(){
        return portStore;
    }

        public CountryStore getCountryStore(){
        return str;
    }

    /**
     * @param nameOfTheFileToBeImported The file name
     *
     * Allows the class to fetch the file desired by the user
     *
     * @return the success of the operation
     */
    public boolean loadFile(String nameOfTheFileToBeImported ){
        file = new File(nameOfTheFileToBeImported);
        if(file.exists()) {
            try {
                read = new Scanner(file);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public void convertPorts(){
        try {
            read.nextLine();
            while(read.hasNext()){
                String line = read.nextLine();
                String[] portArray = line.split(",");
                Country country = str.getCountryByName(portArray[1]);
                if(country == null){
                    country = str.createCountry(portArray[1],portArray[0]);
                    str.saveCountry(country);
                }
                PlaceLocation placeLocation = new PlaceLocation(Double.parseDouble(portArray[4]),Double.parseDouble(portArray[5]));
                Ports port = portStore.createPort(country,Integer.parseInt(portArray[2]),portArray[3],placeLocation);
                if (portStore.savePort(port)){
                    lst.add(new Ports2DTree.Node(port,port.getLatitude(),port.getLongitude()));
                }
            }
            Ports2DTree ports2DTree = new Ports2DTree(lst);
            portStore.setPorts2DTree(ports2DTree);
            read.close();
        } catch (Exception e) {
            return ;
        }
    }

    public List<Ports2DTree.Node<Ports>> getLst(){
        return lst;
    }
}
