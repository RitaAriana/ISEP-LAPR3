package lapr.project.controller;


import lapr.project.data.TransferFromDataBase;
import lapr.project.model.Company;
import lapr.project.model.esinf.FreightNetwork;
import lapr.project.model.store.*;


/**
 * Class responsible for making the connection between the UI and the system when creating a freight network
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class CreateFreightNetworkController {

    /**
     * Represents an instance of app.
     */
    private final App app;

    /**
     * Represents an instance of Company
     */
    private final Company company;

    private final TransferFromDataBase transferFromDataBase;

    private final FreightNetwork freightNetwork;


    public CreateFreightNetworkController(){
        this.app=App.getInstance();
        this.company=app.getCompany();
        this.transferFromDataBase=company.getTransferFromDatabase();
        this.freightNetwork=company.getFreightNetwork();
    }



    /**
     * Initialize the controller receiving a company
     */
    public CreateFreightNetworkController(Company company){
        this.app=App.getInstance();
        this.company=company;
        this.transferFromDataBase=company.getTransferFromDatabase();
        this.freightNetwork=company.getFreightNetwork();
    }


    public void createFreightNetwork(int n){
        transferFromDataBase.importPorts();
        transferFromDataBase.importSeadists();
        transferFromDataBase.importCountries();
        transferFromDataBase.importCapitals();
        transferFromDataBase.importBorders();
        PortStore portStore = company.getPortStr();
        SeadistStore seadistStore = company.getSeadistStr();
        CapitalStore capitalStore = company.getCapitalStr();
        BorderStore borderStore = company.getBorderStr();
        freightNetwork.addNewInformation(capitalStore, portStore, seadistStore, borderStore,n);
    }



}
