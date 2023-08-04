package lapr.project.model;

/**
 * Represents a Container
 * @author Manuela Leite <1200720@isep.ipp.pt>
 */
public class Container {

    /**
     * The number that identify the container
     */
    private final String number;

    /**
     * The x coordinate of the container
     */
    private final int x;

    /**
     * The y coordinate of the container
     */
    private final int y;

    /**
     * The z coordinate of the container
     */
    private final int z;

    /**
     * The check digit that identify the container
     */
    private final int checkDigit;

    /**
     * The isoCode that identify the container
     */
    private final String isoCode;

    /**
     * The maximum weight a container can carry
     */
    private final float maximumWeight;

    /**
     * The payload of a container
     */
    private final float payload;

    /**
     * The tare of a container
     */
    private final float tare;

    /**
     * The weight of a container
     */
    private final float weight;

    /**
     * The maximum weight that a container can pack
     */
    private final float maxWeightPacked;

    /**
     * The maximum volume that a container can pack
     */
    private final float maxVolumePacked;

    /**
     * The repair recommendation of a container
     */
    private final String repairRecommendation;

    /**
     * The certificate of a container
     */
    private final String certificate;
    /**
     * The temperature of a container
     */
    private final double temperature;

    private final double length;

    private final double width;

    private final double height;


    public Container(String number, int x, int y, int z, int checkDigit, String isoCode, float maximumWeight, float payload, float tare, float weight, float maxWeightPacked, float maxVolumePacked, String repairRecommendation, String certificate, double temperature, double length, double width, double height) {
        this.number = number;
        this.x = x;
        this.y = y;
        this.z = z;
        this.checkDigit = checkDigit;
        this.isoCode = isoCode;
        this.maximumWeight = maximumWeight;
        this.payload = payload;
        this.tare = tare;
        this.weight = weight;
        this.maxWeightPacked = maxWeightPacked;
        this.maxVolumePacked = maxVolumePacked;
        this.repairRecommendation = repairRecommendation;
        this.certificate = certificate;
        this.temperature = temperature;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates an instance of Container
     * @param number
     * @param x
     * @param y
     * @param z
     * @param checkDigit
     * @param isoCode
     * @param maximumWeight
     * @param payload
     * @param tare
     * @param weight
     * @param maxWeightPacked
     * @param maxVolumePacked
     * @param repairRecommendation
     * @param certificate
     */
    public Container(String number, int x, int y, int z, int checkDigit, String isoCode, float maximumWeight, float payload, float tare, float weight, float maxWeightPacked, float maxVolumePacked, String repairRecommendation, String certificate) {
        this.number = number;
        this.x = x;
        this.y = y;
        this.z = z;
        this.checkDigit = checkDigit;
        this.isoCode = isoCode;
        this.maximumWeight = maximumWeight;
        this.payload = payload;
        this.tare = tare;
        this.weight = weight;
        this.maxWeightPacked = maxWeightPacked;
        this.maxVolumePacked = maxVolumePacked;
        this.repairRecommendation = repairRecommendation;
        this.certificate = certificate;
        this.temperature = 10;
        this.length = 6;
        this.width = 2.5;
        this.height = 2.5;
    }

    /**
     * Creates an instance of Container
     * @param container
     * @param temperature
     */
    public Container(Container container, double temperature) {
        this.number = container.getNumber();
        this.x = container.getX();
        this.y = container.getY();
        this.z = container.getZ();
        this.checkDigit = container.getCheckDigit();
        this.isoCode = container.getIsoCode();
        this.maximumWeight = container.getMaximumWeight();
        this.payload = container.getPayload();
        this.tare = container.getTare();
        this.weight = container.getWeight();
        this.maxWeightPacked = container.getMaxWeightPacked();
        this.maxVolumePacked = container.getMaxVolumePacked();
        this.repairRecommendation = container.getRepairRecommendation();
        this.certificate = container.getCertificate();
        this.temperature = temperature;
        this.length = 6;
        this.width = 2.5;
        this.height = 2.5;
    }

    /**
     * get the number of a container
     * @return the container number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @return the x coordinate of a container
     */
    public int getX(){
        return x;
    }

    /**
     * @return the y coordinate of a container
     */
    public int getY(){
        return y;
    }

    /**
     * @return the z coordinate of a container
     */
    public int getZ(){
        return z;
    }

    /**
     * get the check digit of a container
     * @return the check digit of the container
     */
    public int getCheckDigit() {
        return checkDigit;
    }

    /**
     * get the iso code of a container
     * @return the iso code
     */
    public String getIsoCode() {
        return isoCode;
    }

    /**
     * get the maximum weight of a container
     * @return the maximum weight
     */
    public float getMaximumWeight() {
        return maximumWeight;
    }

    /**
     * get the payload of a container
     * @return the payload
     */
    public float getPayload() {
        return payload;
    }

    /**
     * get the tare of a container
     * @return the container
     */
    public float getTare() {
        return tare;
    }

    /**
     * get the weight of a container
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * get the maximum weight packed by a container
     * @return the maximum weight packed
     */
    public float getMaxWeightPacked() {
        return maxWeightPacked;
    }

    /**
     * get the maximum volume packed by a container
     * @return the maximum value packed
     */
    public float getMaxVolumePacked() {
        return maxVolumePacked;
    }

    /**
     * get the repair recommendation of a container
     * @return the repair recommendation
     */
    public String getRepairRecommendation() {
        return repairRecommendation;
    }

    /**
     * get the certificate of a container
     * @return the certificate of a container
     */
    public String getCertificate() {
        return certificate;
    }

    /**
     * get the temperature of a container
     * @return the temperatures of a container
     */
    public double getTemperature() {
        return temperature;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "----------* Container Selected *----------\n" +
                "->Number: " + number + "\n" +
                "->x: " + x + "\n" +
                "->y: " + y + "\n" +
                "->z: " + z + "\n" +
                "->Check Digit: " + checkDigit + "\n" +
                "->IsoCode: " + isoCode + "\n" +
                "->Maximum Weight: " + maximumWeight + "Kg\n" +
                "->Payload: " + payload + "Kg\n" +
                "->Tare: " + tare + "Kg\n" +
                "->Weight: " + weight + "Kg\n" +
                "->Maximum Weight Packed: " + maxWeightPacked + "Kg\n" +
                "->Maximum Volume Packed: " + maxVolumePacked + "Kg\n" +
                "->Repair Recommendation: " + repairRecommendation + "\n" +
                "->Certificate: " + certificate + "\n" +
                "->Temperature: " + temperature + "ºC\n" +
                "->Length: " + length + "m\n" +
                "->Width: " + width + "m\n" +
                "->Height: " + height + "m";
    }
}
