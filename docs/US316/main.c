#include <stdio.h>
#include <stdlib.h>

#include "zeroArray.h"
#include "printResult.h"
#include "asm.h"

#define COORDINATES_PER_POSITION 3

int maxX, maxY, maxZ, maxSize;
int *containerLocation, *indexes;

int main(){

    char *fileName = "containers.csv";
    FILE *containerFile = fopen(fileName, "r");
    
    if (!containerFile){ // Verifica se o ficheiro existe
        printf("No file found\n"); 
        exit(0); 
    } 

    fscanf(containerFile, "%d,%d,%d", &maxX, &maxY, &maxZ);

    int shipAllocation[maxX][maxY][maxZ];
    zeroArray(maxX, maxY, maxZ, shipAllocation);
    containerLocation = &shipAllocation[0][0][0];

    while(!feof(containerFile)){ //leitura de cada linha do ficheiro
        int containerID, x, y, z;
        fscanf(containerFile, "%d,%d,%d,%d", &containerID, &x, &y, &z);
        shipAllocation[x][y][z] = containerID;
    }

    fclose(containerFile);

    int numPositions = 3;
    int arraySize = numPositions * COORDINATES_PER_POSITION;
    int positionArray[arraySize];
    positionArray[0] = 10; positionArray[1] = 20; positionArray[2] = 5;
    positionArray[3] = 0; positionArray[4] = 18; positionArray[5] = 9;
    positionArray[6] = 5; positionArray[7] = 20; positionArray[8] = 4;

    indexes = &positionArray[0];

    char resultado = verifiesList();
    printResult(resultado, numPositions);
    
    return 0;
}