#include <stdio.h>
#include <stdlib.h>

#include "containerStruct.h"

void printResult(int x, int y, int z, float requiredEnergy){
    
    char *outputFileName = "US410.csv";
    FILE *outputFile = fopen(outputFileName, "a");

    fprintf(outputFile, "For the Container located at x: %d, y: %d, z: %d to be maintained at the desired temperature, it is required %.2fJ of energy.", x, y, z, requiredEnergy);
    
    fprintf(outputFile, "\n");

    fclose(outputFile);
}