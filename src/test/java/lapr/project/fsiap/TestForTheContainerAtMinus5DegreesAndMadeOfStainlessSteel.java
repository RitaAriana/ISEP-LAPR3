package lapr.project.fsiap;

import lapr.project.controller.App;
import lapr.project.data.HeatFlow;
import lapr.project.data.ImportContainers;
import lapr.project.model.Container;
import lapr.project.utils.WriteForAFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForTheContainerAtMinus5DegreesAndMadeOfStainlessSteel {

    private HeatFlow heatFlow;
    private final double OUTER_LAYER_THICKNESS = 0.05;
    private final double MIDDLE_LAYER_THICKNESS = 0.10;
    private final double INNER_LAYER_THICKNESS = 0.05;
    private final double OUTER_LAYER_THERMAL_CONDUCTIVITY = 15;
    private final double MIDDLE_LAYER_THERMAL_CONDUCTIVITY = 0.02;
    private final double INNER_LAYER_THERMAL_CONDUCTIVITY = 0.21;
    private final double OUTSIDE_TEMPERATURE_US412 = 20;
    private final double OUTSIDE_TEMPERATURE_FIRST_STRETCH = 30;
    private final int TIME_TRAVELED_FIRST_STRETCH = 7200;
    private final double OUTSIDE_TEMPERATURE_SECOND_STRETCH = 25;
    private final int TIME_TRAVELED_SECOND_STRETCH = 5600;
    private final double OUTSIDE_TEMPERATURE_THIRD_STRETCH = 10;
    private final int TIME_TRAVELED_THIRD_STRETCH = 5200;
    private final int NUMBER_OF_CONTAINERS = 20;

    @Test
    public void us412() throws IOException {
        ImportContainers importContainers = new ImportContainers();
        importContainers.getFile("ContainersUs413.csv");
        importContainers.convertContainers();
        Container container = App.getInstance().getCompany().getContainerStore().getContainerByIsoCode("3725-2:BO");
        heatFlow = new HeatFlow();
        heatFlow.calculateArea(container.getLength(), container.getHeight(), container.getWidth());
        heatFlow.calculateNumerator(OUTSIDE_TEMPERATURE_US412, container.getTemperature());
        heatFlow.calculateDenominator(OUTER_LAYER_THICKNESS, OUTER_LAYER_THERMAL_CONDUCTIVITY, MIDDLE_LAYER_THICKNESS, MIDDLE_LAYER_THERMAL_CONDUCTIVITY, INNER_LAYER_THICKNESS, INNER_LAYER_THERMAL_CONDUCTIVITY);
        heatFlow.calculateHeatFlow();
        heatFlow.totalEnergy(9000);
        double expectedResult = 3112224.04;
        double result = heatFlow.getTotalEnergy();
        WriteForAFile writeForAFile = new WriteForAFile();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(container.toString() + "\n");
        stringBuilder.append("->Area of container: " + heatFlow.getArea() + "\n");
        stringBuilder.append("->Outside temperature: " + OUTSIDE_TEMPERATURE_US412 + "ºC\n");
        stringBuilder.append("->Outer layer material: stainless steel\n");
        stringBuilder.append("->Outer layer thickness: " + OUTER_LAYER_THICKNESS + "m" + "\n");
        stringBuilder.append("->Outer layer thermal conductivity: " + OUTER_LAYER_THERMAL_CONDUCTIVITY + "K/W" + "\n");
        stringBuilder.append("->Middle layer material: polyurethane\n");
        stringBuilder.append("->Middle layer thickness: " + MIDDLE_LAYER_THICKNESS + "m" + "\n");
        stringBuilder.append("->Middle layer thermal conductivity: " + MIDDLE_LAYER_THERMAL_CONDUCTIVITY + "K/W" + "\n");
        stringBuilder.append("->Middle layer material: PVC\n");
        stringBuilder.append("->Inner layer thickness: " + INNER_LAYER_THICKNESS + "m" + "\n");
        stringBuilder.append("->Inner layer thermal conductivity: " + INNER_LAYER_THERMAL_CONDUCTIVITY + "K/W" + "\n");
        stringBuilder.append("->Time traveled: 9000s (2h 30min)\n");
        stringBuilder.append("->Energy necessary: " + heatFlow.getTotalEnergy() + "J\n");

        writeForAFile.writeForAFile(stringBuilder.toString(), "US412_ContainerAt" + container.getTemperature() + "ºC", new File(".\\outputs\\US412"), false);


        assertEquals(result, expectedResult, 0.01);
    }

    @Test
    public void us413() throws IOException {
        ImportContainers importContainers = new ImportContainers();
        importContainers.getFile("ContainersUs413.csv");
        importContainers.convertContainers();
        Container container = App.getInstance().getCompany().getContainerStore().getContainerByIsoCode("0919-5:ZI");
        heatFlow = new HeatFlow();
        heatFlow.calculateArea(container.getLength(), container.getHeight(), container.getWidth());
        Map<Integer, Double> temperaturesForATrip = new TreeMap<>();
        temperaturesForATrip.put(TIME_TRAVELED_FIRST_STRETCH, OUTSIDE_TEMPERATURE_FIRST_STRETCH);
        temperaturesForATrip.put(TIME_TRAVELED_SECOND_STRETCH, OUTSIDE_TEMPERATURE_SECOND_STRETCH);
        temperaturesForATrip.put(TIME_TRAVELED_THIRD_STRETCH, OUTSIDE_TEMPERATURE_THIRD_STRETCH);
        double meanTemperature = heatFlow.calculateMeanTemperatureForATrip(temperaturesForATrip);
        heatFlow.calculateNumerator(meanTemperature, container.getTemperature());
        heatFlow.calculateDenominator(OUTER_LAYER_THICKNESS, OUTER_LAYER_THERMAL_CONDUCTIVITY, MIDDLE_LAYER_THICKNESS, MIDDLE_LAYER_THERMAL_CONDUCTIVITY, INNER_LAYER_THICKNESS, INNER_LAYER_THERMAL_CONDUCTIVITY);
        heatFlow.calculateHeatFlowForContainers(NUMBER_OF_CONTAINERS);
        heatFlow.totalEnergy(heatFlow.getTotalTimeTraveled());
        double expectedResult = 137767784.13736716;
        double result = heatFlow.getTotalEnergy();

        WriteForAFile writeForAFile = new WriteForAFile();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(container.toString() + "\n");
        stringBuilder.append("->Area of container: " + heatFlow.getArea() + "\n");
        stringBuilder.append("->Average external temperature:  " + meanTemperature + "ºC\n");
        stringBuilder.append("->Outer layer material: stainless steel\n");
        stringBuilder.append("->Outer layer thickness: " + OUTER_LAYER_THICKNESS + "m" + "\n");
        stringBuilder.append("->Outer layer thermal conductivity: " + OUTER_LAYER_THERMAL_CONDUCTIVITY + "K/W" + "\n");
        stringBuilder.append("->Middle layer material: polyurethane\n");
        stringBuilder.append("->Middle layer thickness: " + MIDDLE_LAYER_THICKNESS + "m" + "\n");
        stringBuilder.append("->Middle layer thermal conductivity: " + MIDDLE_LAYER_THERMAL_CONDUCTIVITY + "K/W" + "\n");
        stringBuilder.append("->Middle layer material: PVC\n");
        stringBuilder.append("->Inner layer thickness: " + INNER_LAYER_THICKNESS + "m" + "\n");
        stringBuilder.append("->Inner layer thermal conductivity: " + INNER_LAYER_THERMAL_CONDUCTIVITY + "K/W" + "\n");
        stringBuilder.append("->Time traveled: 18000s (5h)\n");
        stringBuilder.append("->Energy necessary: " + heatFlow.getTotalEnergy() + "J\n");

        writeForAFile.writeForAFile(stringBuilder.toString(), "US413_ContainerAt" + container.getTemperature() + "ºC", new File(".\\outputs\\US413"), false);



        assertEquals(result, expectedResult, 0.01);
    }

}