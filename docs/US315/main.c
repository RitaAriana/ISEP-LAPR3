#include <stdio.h>
#include <stdlib.h>

#include "zeroArray.h"
#include "printResult.h"
#include "asm.h"

int maxX, maxY, maxZ;

int main(){

    char *fileName = "containers.csv";
    FILE *containerFile = fopen(fileName, "r");
    
    if (!containerFile){ // Verifica se o ficheiro existe
        printf("No file found\n"); 
        exit(0); 
    } 

    fscanf(containerFile, "%d,%d,%d", &maxX, &maxY, &maxZ);

    int shipAllocation[maxX][maxY][maxZ];
    int *containerLocation = &shipAllocation[0][0][0];

    zeroArray(maxX, maxY, maxZ, shipAllocation);
    
    while(!feof(containerFile)){ //leitura de cada linha do ficheiro
        int containerID, x, y, z;
        fscanf(containerFile, "%d,%d,%d,%d", &containerID, &x, &y, &z);
        shipAllocation[x][y][z] = containerID;
    }

    fclose(containerFile);

    int desiredX = 11, desiredY = 2, desiredZ = 1;
    char resultado = isContainerThere(desiredX, desiredY, desiredZ, containerLocation);
    printResult(resultado, desiredX, desiredY, desiredZ);
    
    return 0;
}