#include <stdio.h>

void printResult(long resultado){
    
    char *outputFileName = "US314.csv";
    FILE *outputFile = fopen(outputFileName, "a");

    int *numPointer = (int*) &resultado;

    fprintf(outputFile, "Number of free locations: %d\n", *numPointer);
    numPointer++;
    fprintf(outputFile, "Número of occupied locations: %d\n", *numPointer);
    fprintf(outputFile, "\n");

    fclose(outputFile);
}