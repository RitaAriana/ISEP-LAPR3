#include <stdio.h>

void printResult(char resultado, int numEnviados){
    
    char *outputFileName = "US316.csv";
    FILE *outputFile = fopen(outputFileName, "a");

    fprintf(outputFile, "Out of the %d inserted Containers, %d were found.\n", numEnviados, resultado);
    fprintf(outputFile, "\n");

    fclose(outputFile);
}