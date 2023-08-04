package lapr.project.controller;

import lapr.project.model.CentroMassa;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddContainersWithoutChangingComController {

    public List<Double> addContainerWithoutChangingCom(int numeroPartes, double massaTotal, double[] xInicial, double[] xFinal, double[] yInicial, double[] yFinal, String[] formasGeometricas, double massaAcrescentar) throws IOException {
        List<Double> centroDeMassa = CentroMassa.getCenterMass(numeroPartes, massaTotal, xInicial, xFinal, yInicial, yFinal, formasGeometricas);

        double centroMassaX = centroDeMassa.get(0);
        double centroMassaY = centroDeMassa.get(1);

        double centroMassaXPosContentor = CentroMassa.adicionarContentoresSemAlterarCentroMassaX(numeroPartes,massaTotal,xInicial,xFinal,yInicial,yFinal,formasGeometricas,massaAcrescentar, centroMassaX);
        double centroMassaYPosContentor = CentroMassa.adicionarContentoresSemAlterarCentroMassaY(numeroPartes,massaTotal,xInicial,xFinal,yInicial,yFinal,formasGeometricas,massaAcrescentar, centroMassaY);

        WriteForAFile writeForAFile = new WriteForAFile();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Coordinates of the Container's Center of Mass\n");
        stringBuilder.append("->X: " + String.format("%.2f",centroMassaXPosContentor) + " m\n");
        stringBuilder.append("->Y: " +  String.format("%.2f",centroMassaYPosContentor) + " m\n");
        writeForAFile.writeForAFile(stringBuilder.toString(), "US419_CentroDeMassa", new File(".\\outputs\\US419"), false);
        List<Double> centroDeMassaPosAdicionar = new ArrayList<>();

        centroDeMassaPosAdicionar.add(centroMassaXPosContentor);
        centroDeMassaPosAdicionar.add(centroMassaYPosContentor);

        return centroDeMassaPosAdicionar;
    }
}
