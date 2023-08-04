package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Place;
import lapr.project.model.esinf.FreightNetwork;
import lapr.project.model.store.CountryStore;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClosenessLocalsController {

    /**
     * Represents an instance of app.
     */
    private final App app;

    /**
     * Represents an instance of Company
     */
    private final Company company;

    private final FreightNetwork freightNetwork;

    private final CountryStore countryStore;

    /**
     * Represents an instance of Write for a file
     */
    private final WriteForAFile writeForAFile;

    public ClosenessLocalsController(){
        this.app=App.getInstance();
        this.company=app.getCompany();
        this.freightNetwork=company.getFreightNetwork();
        this.countryStore=company.getCountryStr();
        this.writeForAFile = new WriteForAFile();
    }



    /**
     * Initialize the controller receiving a company
     */
    public ClosenessLocalsController(Company company){
        this.app=App.getInstance();
        this.company=company;
        this.freightNetwork=company.getFreightNetwork();
        this.countryStore=company.getCountryStr();
        this.writeForAFile = new WriteForAFile();
    }

    public Map<String, List<Place>> closenessLocals(int n) throws IOException {
        Map<String,List<Place>> result = freightNetwork.mostCenteredCities(n,countryStore);

        File file = new File(".\\outputs\\Closeness Locals");
        writeForAFile.writeForAFile("", "ClosenessLocals",file,false);
        Iterator iterator = result.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            writeForAFile.writeForAFile(entry.getKey() +"\n" , "ClosenessLocals",file,true);
            for(Place place : (List<Place>) entry.getValue()){
                writeForAFile.writeForAFile(place +"\n" , "ClosenessLocals",file,true);
            }
        }

        return result;
    }
}
