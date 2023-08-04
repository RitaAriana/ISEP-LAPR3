package lapr.project.model.store;

import lapr.project.model.Seadist;

import java.util.ArrayList;
import java.util.List;

/**
 * Seadist Store, which saves seadist objects and creates them
 * @author Francisco Redol <1201239@isep.ipp.pt>
 */
public class SeadistStore {

    /**
     * List containing all Seadists in the company
     */
    private final List<Seadist> seadistLst;

    /**
     * Instantiates a new Seadist Store
     */
    public SeadistStore(){
        seadistLst=new ArrayList<>();
    }

    /**
     * Returns a list of seadists
     *
     * @return a list of seadists
     */
    public List<Seadist> getSeadistLst(){
        return seadistLst;
    }

    /**
     * Creates a Seadist object
     *
     * @param portId1 The ID of the first port
     * @param portId2 The ID of the second port
     * @param seaDistance The distance between the two pots
     * @param portName1 The name of the first port
     * @param portName2 The name of the second port
     * @param countryName1 The name of the first port Country
     * @param countryName2 The name of the second port Country
     * @return a Seadist object
     */
    public Seadist createSeadist(Integer portId1, Integer portId2, float seaDistance, String portName1, String portName2, String countryName1, String countryName2){
        return new Seadist(portId1, portId2, seaDistance, portName1, portName2, countryName1, countryName2);
    }

    /**
     * Saves the parameter Seadist in the Instance List (seadistLst)
     * @param seadist that we intend to save.
     */
    public void saveSeadist(Seadist seadist) {
        seadistLst.add(seadist);
    }

    /**
     * Gets Seadist through ID's.
     * @param portId of the first port
     * @return the country associated with the name
     */
    public List<Seadist> getSeadistsByID(Integer portId){
        List<Seadist> seadistReturnLst = new ArrayList<>();
        for (Seadist seadist : seadistLst) {
            if (seadist.getPortId1() == portId || seadist.getPortId2() == portId) {
                seadistReturnLst.add(seadist);
            }
        }
        if(seadistReturnLst.isEmpty())
            return null;
        return seadistReturnLst;
    }
}
