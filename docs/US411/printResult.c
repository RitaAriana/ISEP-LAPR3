#include <stdio.h>
#include <stdlib.h>

#include "containerStruct.h"

void printResult(float requiredEnergy){
    
    char *outputFileName = "US411.csv";
    FILE *outputFile = fopen(outputFileName, "a");

    if(requiredEnergy <= 0){
        fprintf(outputFile, "For the moment, the generators can output the energy to refrigerate the containers. No Alerts for the moment.");
    } else
        fprintf(outputFile, "ALERT: THE GENERATOR OUTPUT IS %.2fJ INFERIOR TO THE REQUIRED ENERGY. PLEASE INCREASE THE ENERGY LEVELS.", requiredEnergy);
    
    fprintf(outputFile, "\n");

    fclose(outputFile);
}