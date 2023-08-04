#include <stdio.h>

void printResult(char resultado, int desiredX, int desiredY, int desiredZ){
    
    char *outputFileName = "US315.csv";
    FILE *outputFile = fopen(outputFileName, "a");

    fprintf(outputFile, "Result for coordinates x: %d, y: %d, z: %d is: %d\n", desiredX, desiredY, desiredZ, resultado);
    fprintf(outputFile, "\n");

    fclose(outputFile);
}