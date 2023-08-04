package lapr.project.model.store;

import lapr.project.model.Border;

import java.util.ArrayList;
import java.util.List;

/**
 * Border Store, which saves Borders and creates them.
 * @author Francisco Redol <1201239@isep.ipp.pt>
 */
public class BorderStore {

    /**
     * List containing all Borders in the company
     */
    private final List<Border> borderLst;

    /**
     * Instantiates a new Border Store
     */
    public BorderStore(){
        borderLst=new ArrayList<>();
    }

    /**
     * Returns a list of Borders
     *
     * @return a list of borders
     */
    public List<Border> getBorderLst(){
        return borderLst;
    }

    /**
     * Creates a new Border
     * @param countryName of the first country
     * @param countryName2 of the second country
     * @return The created Border
     */
    public Border createBorder(String countryName, String countryName2){
        return new Border(countryName, countryName2);
    }

    /**
     * Saves the parameter Border in the Object List (borderLst)
     * @param border that we intend to save.
     */
    public void saveBorder(Border border) {
        borderLst.add(border);
    }

    /**
     * Gets list of borders associated to the Country
     * @param name the name of one of the countries of the border.
     * @return the list of borders associated with that country name.
     */
    public List<Border> getBorderByName(String name){
        List<Border> borderFoundLst = new ArrayList<>();
        for (Border border : borderLst) {
            if (border.getCountryName().equals(name) || border.getCountryName2().equals(name)) {
                borderFoundLst.add(border);
            }
        }
        if(borderFoundLst.isEmpty())
            return null;
        return borderFoundLst;
    }
}
