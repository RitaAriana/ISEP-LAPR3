package lapr.project.fsiap;

import lapr.project.controller.App;
import lapr.project.data.ContainerStore;
import lapr.project.data.HeatFlow;
import lapr.project.data.ImportContainers;
import lapr.project.model.Container;
import lapr.project.utils.WriteForAFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class US414 {
    private HeatFlow heatFlow;
    private final double TEMPERATURE1 = 7;
    private final double TEMPERATURE2 = -5;
    private final double OUTER_LAYER_THICKNESS_TEMPERATURE1 = 0.05;
    private final double MIDDLE_LAYER_THICKNESS_TEMPERATURE1 = 0.12;
    private final double INNER_LAYER_THICKNESS_TEMPERATURE1 = 0.05;
    private final double OUTER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE1 = 15;
    private final double MIDDLE_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE1 = 0.035;
    private final double INNER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE1 = 0.21;
    private final double OUTER_LAYER_THICKNESS_TEMPERATURE2 = 0.05;
    private final double MIDDLE_LAYER_THICKNESS_TEMPERATURE2 = 0.10;
    private final double INNER_LAYER_THICKNESS_TEMPERATURE2 = 0.05;
    private final double OUTER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE2 = 15;
    private final double MIDDLE_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE2 = 0.02;
    private final double INNER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE2 = 0.21;
    private final double OUTSIDE_TEMPERATURE_FIRST_STRETCH = 16;
    private final int TIME_TRAVELED_FIRST_STRETCH = 7200;
    private final double OUTSIDE_TEMPERATURE_SECOND_STRETCH = 23;
    private final int TIME_TRAVELED_SECOND_STRETCH = 3600;
    private final double GENERATOR_POWER = 7500;

    @Test
    public void  us414() throws IOException {
        ImportContainers importContainers = new ImportContainers();
        importContainers.getFile("ContainersUs413.csv");
        importContainers.convertContainers();
        double totalAreaExposedTemperature1 = 0;
        double totalAreaNotExposedTemperature1 = 0;
        double totalAreaExposedTemperature2 = 0;
        double totalAreaNotExposedTemperature2 = 0;
        Container container;
        ContainerStore containerStore = App.getInstance().getCompany().getContainerStore();
        int[][][] matrix = containerStore.matrixOfPositions();

        for (int i=0; i<matrix.length; i++){
            for (int h=0; h<matrix[0].length; h++){
                for (int j=0; j<matrix[0][0].length; j++){
                    if (matrix[i][h][j] == 1){
                        container = containerStore.getContainerAtAPosition(i, h, j);
                        if (container.getTemperature() == TEMPERATURE1){
                            if (i - 1 >= 0 ){
                                if (matrix[i - 1][h][j] == 0){
                                    totalAreaExposedTemperature1 += container.getHeight() * container.getLength();
                                }
                                else totalAreaNotExposedTemperature1 += container.getHeight() * container.getLength();
                            }else totalAreaExposedTemperature1 += container.getHeight() * container.getLength();

                            if (i + 1 < matrix.length){
                                if (matrix[i + 1][h][j] == 0 ){
                                    totalAreaExposedTemperature1 += container.getHeight() * container.getLength();
                                }
                                else totalAreaNotExposedTemperature1 += container.getHeight() * container.getLength();

                            }else totalAreaExposedTemperature1 += container.getHeight() * container.getLength();

                            if (h - 1 >= 0){
                                if (matrix[i][h-1][j] == 0){
                                    totalAreaExposedTemperature1 += container.getHeight() * container.getWidth();
                                }
                                totalAreaNotExposedTemperature1 += container.getHeight() * container.getWidth();
                            }else totalAreaExposedTemperature1 += container.getHeight() * container.getWidth();

                            if (h + 1 < matrix[0].length){
                                if (matrix[i][h+1][j] == 0){
                                    totalAreaExposedTemperature1 += container.getHeight() * container.getWidth();
                                }else totalAreaNotExposedTemperature1 += container.getHeight() * container.getWidth();
                            } else totalAreaExposedTemperature1 += container.getHeight() * container.getWidth();

                            if ( j - 1 >= 0){
                                if (matrix[i][h][j-1] == 0 ){
                                    totalAreaExposedTemperature1 += container.getWidth() * container.getLength();
                                }else totalAreaNotExposedTemperature1 += container.getWidth() * container.getLength();

                            } else totalAreaNotExposedTemperature1 +=  container.getWidth() * container.getLength();
                            if (j + 1 < matrix[0][0].length){
                                if (matrix[i][h][j+1] == 0 ){
                                    totalAreaExposedTemperature1 += container.getWidth() * container.getLength();
                                }else totalAreaNotExposedTemperature1 += container.getWidth() * container.getLength();

                            } else totalAreaExposedTemperature1 += container.getWidth() * container.getLength();

                        }
                        if (container.getTemperature() == TEMPERATURE2){
                            if (i - 1 >= 0 ){
                                if (matrix[i - 1][h][j] == 0){
                                    totalAreaExposedTemperature2 += container.getHeight() * container.getLength();
                                }
                                else totalAreaNotExposedTemperature2 += container.getHeight() * container.getLength();
                            }else totalAreaExposedTemperature2 += container.getHeight() * container.getLength();

                            if (i + 1 < matrix.length){
                                if (matrix[i + 1][h][j] == 0 ){
                                    totalAreaExposedTemperature2 += container.getHeight() * container.getLength();
                                }
                                else totalAreaNotExposedTemperature2 += container.getHeight() * container.getLength();

                            }else totalAreaExposedTemperature2 += container.getHeight() * container.getLength();

                            if (h - 1 >= 0){
                                if (matrix[i][h-1][j] == 0){
                                    totalAreaExposedTemperature2 += container.getHeight() * container.getWidth();
                                }
                                totalAreaNotExposedTemperature2 += container.getHeight() * container.getWidth();
                            }else totalAreaExposedTemperature2 += container.getHeight() * container.getWidth();

                            if (h + 1 < matrix[0].length){
                                if (matrix[i][h+1][j] == 0){
                                    totalAreaExposedTemperature2 += container.getHeight() * container.getWidth();
                                }else totalAreaNotExposedTemperature2 += container.getHeight() * container.getWidth();
                            } else totalAreaExposedTemperature2 += container.getHeight() * container.getWidth();

                            if ( j - 1 >= 0){
                                if (matrix[i][h][j-1] == 0 ){
                                    totalAreaExposedTemperature2 += container.getWidth() * container.getLength();
                                }else totalAreaNotExposedTemperature2 += container.getWidth() * container.getLength();

                            } else totalAreaNotExposedTemperature2 +=  container.getWidth() * container.getLength();
                            if (j + 1 < matrix[0][0].length){
                                if (matrix[i][h][j+1] == 0 ){
                                    totalAreaExposedTemperature2 += container.getWidth() * container.getLength();
                                }else totalAreaNotExposedTemperature2 += container.getWidth() * container.getLength();

                            } else totalAreaExposedTemperature2 += container.getWidth() * container.getLength();

                        }

                    }

                }
            }
        }
        heatFlow = new HeatFlow();
        Map<Integer, Double> temperaturesForATrip = new TreeMap<>();
        temperaturesForATrip.put(TIME_TRAVELED_FIRST_STRETCH, OUTSIDE_TEMPERATURE_FIRST_STRETCH);
        temperaturesForATrip.put(TIME_TRAVELED_SECOND_STRETCH, OUTSIDE_TEMPERATURE_SECOND_STRETCH);
        double meanTemperature = heatFlow.calculateMeanTemperatureForATrip(temperaturesForATrip);

        double temperature7Exposed = (heatFlow.calculateNumerator2(meanTemperature, TEMPERATURE1) / heatFlow.calculateDenominator2(OUTER_LAYER_THICKNESS_TEMPERATURE1, OUTER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE1, MIDDLE_LAYER_THICKNESS_TEMPERATURE1, MIDDLE_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE1, INNER_LAYER_THICKNESS_TEMPERATURE1, INNER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE1, totalAreaExposedTemperature1) ) * heatFlow.getTotalTimeTraveled();

        double temperature7NotExposed = heatFlow.calculateNumerator2(OUTSIDE_TEMPERATURE_FIRST_STRETCH, TEMPERATURE1) / heatFlow.calculateDenominator2(OUTER_LAYER_THICKNESS_TEMPERATURE1, OUTER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE1, MIDDLE_LAYER_THICKNESS_TEMPERATURE1, MIDDLE_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE1, INNER_LAYER_THICKNESS_TEMPERATURE1, INNER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE1, totalAreaNotExposedTemperature1)  * heatFlow.getTotalTimeTraveled();

        double temperature5Exposed = heatFlow.calculateNumerator2(meanTemperature, TEMPERATURE2) / heatFlow.calculateDenominator2(OUTER_LAYER_THICKNESS_TEMPERATURE2, OUTER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE2, MIDDLE_LAYER_THICKNESS_TEMPERATURE2, MIDDLE_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE2, INNER_LAYER_THICKNESS_TEMPERATURE2, INNER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE2, totalAreaExposedTemperature2)  * heatFlow.getTotalTimeTraveled();

        double temperature5NotExposed = heatFlow.calculateNumerator2(OUTSIDE_TEMPERATURE_FIRST_STRETCH, TEMPERATURE2) / heatFlow.calculateDenominator2(OUTER_LAYER_THICKNESS_TEMPERATURE2, OUTER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE2, MIDDLE_LAYER_THICKNESS_TEMPERATURE2, MIDDLE_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE2, INNER_LAYER_THICKNESS_TEMPERATURE2, INNER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE2, totalAreaNotExposedTemperature2)  * heatFlow.getTotalTimeTraveled();

        double totalEnergy = temperature5Exposed + temperature5NotExposed + temperature7Exposed + temperature7NotExposed;

        WriteForAFile writeForAFile = new WriteForAFile();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("->Area of containers exposed: " + (temperature5Exposed + temperature7Exposed) + "m\n");
        stringBuilder.append("->Area of containers not exposed: " + (temperature5NotExposed + temperature7NotExposed) + "m2\n");
        stringBuilder.append("->Average external temperature: " + meanTemperature + "ºC\n");
        stringBuilder.append("->External temperature: " +TEMPERATURE1 +"ºC\n");
        stringBuilder.append("->Outer layer material: stainless steel\n");
        stringBuilder.append("->Outer layer thickness: " + OUTER_LAYER_THICKNESS_TEMPERATURE1 + "m" + "\n");
        stringBuilder.append("->Outer layer thermal conductivity: " + OUTER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE1 + "K/W" + "\n");
        stringBuilder.append("->Middle layer material: extruded polystyrene\n");
        stringBuilder.append("->Middle layer thickness: " + MIDDLE_LAYER_THICKNESS_TEMPERATURE1+ "m" + "\n");
        stringBuilder.append("->Middle layer thermal conductivity: " + MIDDLE_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE1 + "K/W" + "\n");
        stringBuilder.append("->Middle layer material: PVC\n");
        stringBuilder.append("->Inner layer thickness: " + INNER_LAYER_THICKNESS_TEMPERATURE1+ "m" + "\n");
        stringBuilder.append("->Inner layer thermal conductivity: " + INNER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE1 + "K/W" + "\n");
        stringBuilder.append("->Outer layer material: stainless steel\n");
        stringBuilder.append("->Outer layer thickness: " + OUTER_LAYER_THICKNESS_TEMPERATURE2 + "m" + "\n");
        stringBuilder.append("->Outer layer thermal conductivity: " + OUTER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE2 + "K/W" + "\n");
        stringBuilder.append("->Middle layer material: polyurethane\n");
        stringBuilder.append("->Middle layer thickness: " + MIDDLE_LAYER_THICKNESS_TEMPERATURE2 + "m" + "\n");
        stringBuilder.append("->Middle layer thermal conductivity: " + MIDDLE_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE2+ "K/W" + "\n");
        stringBuilder.append("->Middle layer material: PVC\n");
        stringBuilder.append("->Inner layer thickness: " + INNER_LAYER_THICKNESS_TEMPERATURE2 + "m" + "\n");
        stringBuilder.append("->Inner layer thermal conductivity: " + INNER_LAYER_THERMAL_CONDUCTIVITY_TEMPERATURE2 + "K/W" + "\n");
        stringBuilder.append("->Time traveled: 10800s (3h)\n");
        stringBuilder.append("->Energy necessary: " + totalEnergy + "J\n");
        stringBuilder.append("->Generators needed: " + (int)((totalEnergy/heatFlow.getTotalTimeTraveled())/GENERATOR_POWER));
        writeForAFile.writeForAFile(stringBuilder.toString(), "US414_US415", new File(".\\outputs\\US414_US415"), false);


    }
}
