package lapr.project.model;

import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CentroMassa {

    public static List<Double> getCenterMass(int numeroPartes, double massaTotal, double[] xInicial, double[] xFinal, double[] yInicial, double[] yFinal, String[] formasGeometricas) throws IOException {
        WriteForAFile writeForAFile = new WriteForAFile();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Coordinates of the Ship's Center of Mass\n");

        double[] areas = obterAreas(numeroPartes,formasGeometricas,xInicial,xFinal,yInicial,yFinal);
        double areaTotal =0.0;
        for(int i=0; i<numeroPartes; i++){
            areaTotal+=areas[i];
        }
        double[] massas = obterMassas(numeroPartes,massaTotal,areas,areaTotal);
        double[] centroMassaX = obterCentrosMassas(numeroPartes,xInicial,xFinal);
        double[] centroMassaY = obterCentrosMassas(numeroPartes,yInicial,yFinal);

        double valorCentroMassaX = obterNumCentroMassaX(centroMassaX,massas)/massaTotal;
        double valorCentroMassaY = obterNumCentroMassaY(centroMassaY,massas)/massaTotal;

        List<Double> resultado = new ArrayList<>();
        resultado.add(valorCentroMassaX);
        resultado.add(valorCentroMassaY);

        stringBuilder.append("->X: " + String.format("%.2f",valorCentroMassaX) + " m\n");
        stringBuilder.append("->Y: " +  String.format("%.2f",valorCentroMassaY) + " m\n");
        writeForAFile.writeForAFile(stringBuilder.toString(), "US418_CentroDeMassa", new File(".\\outputs\\US418"), false);

        return resultado;


    }

    public static double[] obterAreas(int numeroPartes,String[] formasGeometricas, double[] xInicial, double[] xFinal, double[] yInicial, double[] yFinal){
        double[] areas = new double[numeroPartes];
        for(int i=0; i<numeroPartes; i++){
            areas[i] = calcularArea(formasGeometricas[i],xInicial[i],xFinal[i],yInicial[i],yFinal[i]);
        }
        return areas;
    }

    public static double[] obterMassas(int numeroPartes,double massaTotal, double[] areas, double areaTotal){
        double[] massas = new double[numeroPartes];
        for (int i=0; i<numeroPartes;i++){
            massas[i] = calcularMassa(massaTotal,areas[i],areaTotal);
        }
        return massas;
    }


    public static double[] obterCentrosMassas(int numeroPartes,double[] inicial, double[] finais){
        double[] centroMassa = new double[numeroPartes];
        for (int i=0; i<numeroPartes;i++){
            double temp = finais[i]-inicial[i];
            double aux = temp/2;
            centroMassa[i]=finais[i]-aux;

        }
        return centroMassa;
    }




    public static double calcularArea(String forma, double xInicial, double xFinal, double yInicial, double yFinal){
        if (forma.equalsIgnoreCase("Retangulo")){
            double x = xFinal-xInicial;
            double y = yFinal-yInicial;
            return  x*y;
        }
        return 0;
    }

    public static double calcularMassa(double massaTotal, double areaIndividual, double areaTotal){
        double num = areaIndividual*massaTotal;
        return num/areaTotal;
    }

    public static double obterNumCentroMassaX(double[] centroMassaX, double[] massas){
        double num = 0.0;

        for(int i=0; i<centroMassaX.length;i++){
            num += massas[i] * centroMassaX[i];
        }
        return num;
    }

    public static double obterNumCentroMassaY(double[] centroMassaY, double[] massas){
        double num = 0.0;

        for(int i=0; i<centroMassaY.length;i++){
            num += massas[i] * centroMassaY[i];
        }
        return num;
    }

    public static double adicionarContentoresSemAlterarCentroMassaX(int numeroPartes, double massaTotal, double[] xInicial, double[] xFinal, double[] yInicial, double[] yFinal, String[] formasGeometricas, double massaAcrescentar, double centroMassaX) {
        double massa = massaTotal + massaAcrescentar;
        double aux = centroMassaX * massa;

        double[] areas = obterAreas(numeroPartes,formasGeometricas,xInicial,xFinal,yInicial,yFinal);
        double areaTotal =0.0;
        for(int i=0; i<numeroPartes; i++){
            areaTotal+=areas[i];
        }
        double[] massas = obterMassas(numeroPartes,massaTotal,areas,areaTotal);
        double[] centrosMassaX = obterCentrosMassas(numeroPartes,xInicial,xFinal);

        double valorAuxCentroMassaX = obterNumCentroMassaX(centrosMassaX,massas);

        double temp = aux - valorAuxCentroMassaX;

        return temp/massaAcrescentar;
    }

    public static double adicionarContentoresSemAlterarCentroMassaY(int numeroPartes, double massaTotal, double[] xInicial, double[] xFinal, double[] yInicial, double[] yFinal, String[] formasGeometricas, double massaAcrescentar, double centroMassaY) {
        double massa = massaTotal + massaAcrescentar;
        double aux = centroMassaY * massa;

        double[] areas = obterAreas(numeroPartes,formasGeometricas,xInicial,xFinal,yInicial,yFinal);
        double areaTotal =0.0;
        for(int i=0; i<numeroPartes; i++){
            areaTotal+=areas[i];
        }
        double[] massas = obterMassas(numeroPartes,massaTotal,areas,areaTotal);
        double[] centrosMassaY = obterCentrosMassas(numeroPartes,yInicial,yFinal);

        double valorAuxCentroMassaY = obterNumCentroMassaX(centrosMassaY,massas);

        double temp = aux - valorAuxCentroMassaY;

        return temp/massaAcrescentar;

    }


}
