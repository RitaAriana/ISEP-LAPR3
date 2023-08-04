package lapr.project.controller;

import lapr.project.model.CentroMassa;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddContainersWithoutChangingComControllerTest {

    @Test
    void addContainerWithoutChangingCom() throws IOException {
        AddContainersWithoutChangingComController controller = new AddContainersWithoutChangingComController();
        int numeroPartes = 3;
        double massaTotal = 152000000;
        double[] xInicial = new double[numeroPartes];
        xInicial[0]=0;
        xInicial[1]=10;
        xInicial[2]=10;
        double[] yInicial = new double[numeroPartes];
        yInicial[0]=0;
        yInicial[1]=16;
        yInicial[2]=11;
        double[] xFinal = new double[numeroPartes];
        xFinal[0]=342;
        xFinal[1]=20;
        xFinal[2]=30;
        double[] yFinal = new double[numeroPartes];
        yFinal[0]=42;
        yFinal[1]=26;
        yFinal[2]=31;
        String[] formasGeometricas = new String[numeroPartes];
        formasGeometricas[0]="Retangulo";
        formasGeometricas[1]="Retangulo";
        formasGeometricas[2]="Retangulo";
        List<Double> resultado = CentroMassa.getCenterMass(numeroPartes,massaTotal,xInicial,xFinal,yInicial,yFinal,formasGeometricas);
        assertEquals(165.88697524219592,resultado.get(0));
        assertEquals(21.0,resultado.get(1));

        List<Double> posContentor = controller.addContainerWithoutChangingCom(numeroPartes,massaTotal,xInicial,xFinal,yInicial,yFinal,formasGeometricas,50000);

        assertEquals(165.88697524223326, posContentor.get(0));
        assertEquals(21.0, posContentor.get(1));
    }
}