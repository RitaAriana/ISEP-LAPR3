package lapr.project.controller;


import lapr.project.model.Company;
import lapr.project.model.ImportPort;

/**
 * The ImportPortController, controller responsible for managing the Import Port class, which allows the Port Manager to import ports from a .csv file
 *
 *  @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class ImportPortController {

    /**
     * The ImportPort class
     */
    private final ImportPort importPort;

    public ImportPortController(){
        importPort = new ImportPort();
    }

    public ImportPortController(Company company){
        importPort = new ImportPort(company);
    }

    public ImportPort getImportPort(){
        return importPort;
    }

    /**
     * @param fileName The name of the file
     *
     * Allows importing of the file
     *
     * @return the success of the operation
     */
    public boolean importFile(String fileName){
        return importPort.loadFile(fileName);
    }

    /**
     * Allows the system to import the ports.
     */
    public void importPorts(){
        importPort.convertPorts();
    }

}
