package lapr.project.model.store;

import lapr.project.model.CargoManifest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A store of Cargo Manifest
 * @author Manuela Leite <1200720@isep.ipp.pt>
 */
public class CargoManifestStore {

    /**
     * List that contain all the Cargo Manifest of a Company
     */
    private List<CargoManifest> cargoManifestList;

    /**
     * Instantiates a new Cargo Manifest Store
     */
    public CargoManifestStore(){
        cargoManifestList = new ArrayList<>();
    }

    /**
     * Create a new Cargo manifest
     * @param date the date of the cargo manifest
     * @param numberOfContainer the number of container associated with a cargo manifest
     * @param mmsiCode the mmsi code associated with cargo manifest
     * @param destination the destination of the cargo manifest
     * @return the cargo manifest created
     */
    public CargoManifest createCargoManifest(Date date, int numberOfContainer, String mmsiCode, CargoManifest.Destination destination){
        return new CargoManifest(date, numberOfContainer, mmsiCode, destination);
    }

    /**
     * Save the cargo manifest
     * @param cargoManifest the cargo manifest we intend to save
     * @return true, if the operation was successful, false otherwise
     */
    public boolean saveCargoManifest(CargoManifest cargoManifest){
        if (!cargoManifestList.contains(cargoManifest)) return cargoManifestList.add(cargoManifest);
        return false;
    }

    /**
     * Get the cargo manifests associated with a mmsi code
     * @param mmsiCode the mmsi code
     * @return the cargo manifests associated with the mmsi code
     */
    public List<CargoManifest> getCargoManifestByMmsiCode(String mmsiCode){
        List<CargoManifest> cargoManifests = new ArrayList<>();
        for (CargoManifest cm : cargoManifestList){
            if (cm.getMmsiCodeShip().equals(mmsiCode)){
                cargoManifests.add(cm);
            }
        }
        return cargoManifests;
    }

    /**
     * Get the mean number of containers per cargo manifests
     * @param cargoManifestList the cargo manifests
     * @return the mean number of container per cargo manifest
     */
    public float getMeanContainersPerCargoManifest(List<CargoManifest> cargoManifestList){
        float sum = 0;
        if (cargoManifestList.size() != 0){
            for (CargoManifest cm : cargoManifestList){
                sum+= cm.getTotalNumberOfContainers();
            }
            sum/= cargoManifestList.size();
        }
        return sum;

    }

    /**
     * Get the cargo manifest per year
     * @param cargoManifestList the list of cargo manifests
     * @param year the year for search the cargo manifests
     * @return the total number of cargo manifest in the year
     */
    public int getTheCargoManifestPerYear(List<CargoManifest> cargoManifestList, int year){
        int sum = 0;
        if (cargoManifestList.size() != 0){
            for (CargoManifest cm : cargoManifestList){
                if (cm.getDate().getYear() == year){
                    sum += 1;
                }
            }
        }
        return sum;
    }


}

