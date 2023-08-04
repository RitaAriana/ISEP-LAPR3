package lapr.project.model;

import lapr.project.data.ContainerStore;
import lapr.project.data.TransferFromDataBase;
import lapr.project.data.login.AuthFacade;
import lapr.project.model.esinf.FreightNetwork;
import lapr.project.model.store.*;

/**
 * Class that represents a company.
 *
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 * @author Francisco Redol <1201239@isep.ipp.pt>
 */
public class Company {

    /**
     * Represents an instance of the BstShip
     */
    private final BstShip bstShip;

    /**
     * List containing all countries existing in the Company.
     */
    private final CountryStore countryStr;

    /**
     * List containing all ports existing in the Company.
     */
    private final PortStore portStr;

    /**
     * List containing all Capitals existing in the Company.
     */
    private final CapitalStore capitalStr;

    /**
     * List containing all Seadists existing in the Company.
     */
    private final SeadistStore seadistStr;

    /**
     * List containing all Borders existing in the Company.
     */
    private final BorderStore borderStr;

    /**
     * Represents an instance of Cargo Manifest Store
     */
    private final CargoManifestStore cargoManifestStore;

    private final AuthFacade authFacade;

    /**
     * Represents an instance of the TransferFromDataBase object
     */
    private TransferFromDataBase transferFromDataBase;

    private final FreightNetwork freightNetwork;

    private final ContainerStore containerStore;

    /**
     * Creates an instance of Company
     */
    public Company(){
        bstShip = new AvlShip();
        countryStr = new CountryStore();
        portStr = new PortStore();
        cargoManifestStore = new CargoManifestStore();
        capitalStr = new CapitalStore();
        seadistStr = new SeadistStore();
        borderStr = new BorderStore();
        authFacade=new AuthFacade();
        freightNetwork = new FreightNetwork();
        containerStore = new ContainerStore();
    }

    /**
     * Get the instance of Cargo Manifest store
     * @return the cargo manifest store
     */
    public CargoManifestStore getCargoManifestStore() {
        return cargoManifestStore;
    }

    public AuthFacade getAuthFacade(){return authFacade;}
    /**
     * Get the instance of BstShip
     * @return the bstShip
     */
    public BstShip getBstShip() {
        return bstShip;
    }

    /**
     * Get the store containing all countries existing in the Company.
     * @return The store containing all countries existing in the Company.
     */
    public CountryStore getCountryStr() { return countryStr; }

    /**
     * Get the store containing all ports existing in the Company.
     * @return The store containing all ports existing in the Company.
     */
    public PortStore getPortStr() { return portStr; }

    /**
     * Get the store containing all Capitals existent in the Company.
     * @return The store containing all Capitals existent in the Company.
     */
    public CapitalStore getCapitalStr() { return capitalStr; }

    /**
     * Get the store containing all Seadists existent in the Company.
     * @return The store containing all Seadists existent in the Company.
     */
    public SeadistStore getSeadistStr() { return seadistStr; }

    /**
     * Get the store containing all Borders existent in the Company.
     * @return The store containing all Borders existent in the Company.
     */
    public BorderStore getBorderStr(){
        return borderStr;
    }

    /**
     * Returns an instance of the object which allows the Java system to import data from the database
     *
     * @return an instance of the GetTransferFromDatabase Object
     */
    public TransferFromDataBase getTransferFromDatabase(){
        if(transferFromDataBase == null)
            this.transferFromDataBase = new TransferFromDataBase();

        return transferFromDataBase;
    }

    public FreightNetwork getFreightNetwork(){
        return freightNetwork;
    }

    public ContainerStore getContainerStore() {
        return containerStore;
    }
}
