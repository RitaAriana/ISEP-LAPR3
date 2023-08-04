package lapr.project.controller;

import lapr.project.model.CentroMassa;

import java.io.IOException;
import java.util.List;

public class GetCenterOfMassForAVesselController {

    public List<Double> getCenterOfMass(int numeroPartes, double massaTotal, double[] xInicial, double[] xFinal, double[] yInicial, double[] yFinal, String[] formasGeometricas) throws IOException {
        return CentroMassa.getCenterMass(numeroPartes, massaTotal, xInicial, xFinal, yInicial, yFinal, formasGeometricas);
    }
}
