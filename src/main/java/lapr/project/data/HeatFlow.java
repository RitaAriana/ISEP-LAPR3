package lapr.project.data;

import java.util.Map;
import java.util.Set;

public class HeatFlow {

    private double denominator;
    private double numerator;
    private double heatFlowValue;
    private double totalEnergy;
    private int totalTimeTraveled;
    private double area;

    public HeatFlow() {
        this.denominator = 0;
        this.numerator = 0;
        this.heatFlowValue = 0;
        this.totalEnergy = 0;
        this.totalTimeTraveled = 0;
        this.area = 0;

    }

    public double calculateMeanTemperatureForATrip(Map<Integer, Double> temperatureAndTimeTraveled){

        double meanTemperature = 0;

        calculateTotalTimeOfATrip(temperatureAndTimeTraveled.keySet());

        for (int timeTraveled : temperatureAndTimeTraveled.keySet()){
            meanTemperature += ((double) timeTraveled/this.totalTimeTraveled) * temperatureAndTimeTraveled.get(timeTraveled);
        }

        return meanTemperature;
    }

    private void calculateTotalTimeOfATrip(Set<Integer> timesOfPhasesOfATrip){
        for (int timeTraveled : timesOfPhasesOfATrip){
            this.totalTimeTraveled += timeTraveled;
        }

    }

    public int getTotalTimeTraveled() {
        return totalTimeTraveled;
    }

    public void calculateDenominator(double l1, double k1, double l2, double k2, double l3, double k3){
        /*
            L1 - outer layer thickness
            L2 - middle layer thickness
            L3 - inner layer thickness
            K1 - outer layer thermal conductivity
            K2 - middle layer thermal conductivity
            K3 - inner layer thermal conductivity
            Denominator = L1/K1 + L2/K2 + L3/K3
         */

        this.denominator = (l1/(k1*area) + l2/(k2*area) + l3/(k3*area));
    }
    public double calculateDenominator2(double l1, double k1, double l2, double k2, double l3, double k3, double area){
        /*
            L1 - outer layer thickness
            L2 - middle layer thickness
            L3 - inner layer thickness
            K1 - outer layer thermal conductivity
            K2 - middle layer thermal conductivity
            K3 - inner layer thermal conductivity
            Denominator = L1/K1 + L2/K2 + L3/K3
         */

        return (l1/(k1*area) + l2/(k2*area) + l3/(k3*area));
    }

    public void calculateNumerator(double outsideTemperature, double insideTemperature){

        /*
            temperature variation calculation
         */
        this.numerator = (outsideTemperature - insideTemperature);

    }
    public double calculateNumerator2(double outsideTemperature, double insideTemperature){

        /*
            temperature variation calculation
         */
        return (outsideTemperature - insideTemperature);

    }

    public double getHeatFlowValue() {
        return heatFlowValue;
    }

    public void calculateHeatFlow(){

        this.heatFlowValue = (numerator/denominator);
    }


    public void totalEnergy(int time){
        this.totalEnergy = (heatFlowValue * time);
    }

    public double getTotalEnergy() {
        return totalEnergy;
    }

    public void calculateHeatFlowForContainers(int numberOfContainers){
        calculateHeatFlow();
        this.heatFlowValue = (heatFlowValue * numberOfContainers);

    }


    public void calculateArea(double length, double height, double width){
        this.area = (4 * length * height) + (2 * height * width);
    }

    public double getArea() {
        return area;
    }
}
