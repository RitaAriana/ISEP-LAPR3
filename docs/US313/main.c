#include <stdio.h>
#include <stdlib.h>

#include "zeroArray.h"
#include "printArray.h"

int maxX, maxY, maxZ;

int main(){

    char *containerFileName = "containers.csv";
    FILE *containerFile = fopen(containerFileName, "r");
    
    if (!containerFile){ // Verifica se o ficheiro existe
        printf("No file found\n"); 
        exit(-1); 
    } 

    fscanf(containerFile, "%d,%d,%d", &maxX, &maxY, &maxZ);
    int shipAllocation[maxX][maxY][maxZ];

    zeroArray(maxX, maxY, maxZ, shipAllocation);

    while(!feof(containerFile)){ //leitura de cada linha do ficheiro
        int x, y, z, containerID;

        fscanf(containerFile, "%d,%d,%d,%d", &containerID, &x, &y, &z);

        shipAllocation[x][y][z] = containerID;
    }

    fclose(containerFile);

    printArray(maxX,maxY,maxZ, shipAllocation);
            
    return 0;
}