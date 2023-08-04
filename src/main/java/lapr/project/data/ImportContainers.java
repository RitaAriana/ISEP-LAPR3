package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Company;
import lapr.project.model.Container;

import java.io.File;
import java.util.Scanner;

public class ImportContainers {

    /**
     * The file to be imported
     */
    File file;

    /**
     * The Scanner which reads the file
     */
    Scanner readFile;
    /**
     * Represents an instance of Company
     */
    private final Company company;

    private final ContainerStore containerStore;

    /**
     * The class constructor
     */
    public ImportContainers() {
        this.company= App.getInstance().getCompany();
        containerStore = company.getContainerStore();
    }

    public ImportContainers(Company company){
        this.company=company;
        containerStore = company.getContainerStore();
    }


    /**
     * @param fileName The file name
     *
     * Allows the class to fetch the file desired by the user
     *
     * @return the success of the operation
     */
    public boolean getFile(String fileName) {
        file = new File(fileName);
        if (file.exists()) {
            try {
                readFile = new Scanner(file);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Manages the lines which are sent to getLineArray and createShip.
     *
     * @return The number of ships which were not imported
     */
    public int convertContainers() {
        int containersNotConverted = 0;

        String line = readFile.nextLine();
        String[] containersArray = getLineArray(line);
        containerStore.setX(Integer.parseInt(containersArray[0]));
        containerStore.setY(Integer.parseInt(containersArray[1]));
        containerStore.setZ(Integer.parseInt(containersArray[2]));
        while (readFile.hasNext()) {
            line = readFile.nextLine();
            containersArray = getLineArray(line);
            if (containersArray.length == 18){
                containersNotConverted += createContainer(containersArray);
            }

            else
                containersNotConverted += 1;
        }
        readFile.close();

        return containersNotConverted;
    }

    /**
     * @param containerLine Each line of the .csv file
     * @return the line split as an array
     */
    public String[] getLineArray(String containerLine) {
        return containerLine.split(",");
    }

    /**
     * @param containersArray containing all the line data
     *
     * Creates the container
     *
     * @return 1 if the ship was not added or 0 if it was
     */
    public int createContainer(String[] containersArray) {
        try {

            String number = containersArray[0];

            if (containerStore.getContainerByNumber(number) == null){
                int x  = Integer.parseInt(containersArray[1]);
                int y  = Integer.parseInt(containersArray[2]);
                int z  = Integer.parseInt(containersArray[3]);
                int checkDigit = Integer.parseInt(containersArray[4]);
                String isoCode = containersArray[5];
                float maxWeight = Float.parseFloat(containersArray[6]);
                float payload = Float.parseFloat(containersArray[7]);
                float tare = Float.parseFloat(containersArray[8]);
                float weight = Float.parseFloat(containersArray[9]);
                float maxWeightPacked = Float.parseFloat(containersArray[10]);
                float maxVolumePacked= Float.parseFloat(containersArray[11]);
                String repairRecommendation = containersArray[12];
                String certificate = containersArray[13];
                double temperature = Double.parseDouble(containersArray[14]);
                double length = Double.parseDouble(containersArray[15]);
                double width = Double.parseDouble(containersArray[16]);
                double height = Double.parseDouble(containersArray[17]);
                Container container = new Container(number, x, y, z, checkDigit, isoCode, maxWeight, payload, tare, weight, maxWeightPacked,maxVolumePacked, repairRecommendation, certificate, temperature, length, width, height);
                containerStore.saveContainer(container);
            }

        } catch (Exception e) {
            return 1;
        }
        return 0;
    }
}
