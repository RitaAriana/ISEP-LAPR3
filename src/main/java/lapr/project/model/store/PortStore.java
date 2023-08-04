package lapr.project.model.store;

import lapr.project.model.Country;
import lapr.project.model.PlaceLocation;
import lapr.project.model.Ports;
import lapr.project.model.Ports2DTree;

import java.util.ArrayList;
import java.util.List;

/**
 * The different ports existing in a company.
 *
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class PortStore {

    /**
     * List containing all categories of parameters existing in the Company.
     */
    private List<Ports> portsLst;

    /**
     * Represents an instance of the Ports2DTree
     */
    private Ports2DTree ports2DTree;

    /**
     * Instantiates a new ParameterCategoryStore.
     */
    public PortStore(){
        portsLst=new ArrayList();
        ports2DTree = new Ports2DTree();
    }

    public Ports2DTree getPorts2DTree(){
        return ports2DTree;
    }

    public void setPorts2DTree(Ports2DTree tree){
        this.ports2DTree = tree;
    }


    public Ports createPort(Country country, int code, String portName, PlaceLocation coordinates) {
        return new Ports(country,code,portName,coordinates);
    }

    /**
     * Returns the list of existing ports.
     *
     * @return the list of ports stored in the store.
     */
    public List<Ports> getPortsLst() {
        return portsLst;
    }

    public boolean savePort(Ports p) {
        if(!portsLst.contains(p)){
            portsLst.add(p);
            return true;
        }
        return false;
    }

    public Ports getPortByName(String name){
        for (Ports p : portsLst) {
            if (p.getPortName().equals(name)) {
                return p;
            }
        }
        return null;
    }


}
