#include <stdio.h>

void printArray(int x, int y, int z, int (*shipAllocation)[y][z]){
    
    char *outputFileName = "US313.csv";
    FILE *outputFile = fopen(outputFileName, "a");

    fprintf(outputFile, "The Containers are on the following coordinates:\n");
    for(int i = 0; i < x; i++){
        for(int j = 0; j < y; j++){
            for(int k = 0; k < z; k++){
                if(shipAllocation[i][j][k] != 0)
                    fprintf(outputFile, "Contentor: %d está localizado na posição x: %d y: %d z: %d\n", shipAllocation[i][j][k], i, j, k);
            }
        }
    }
    fprintf(outputFile, "\n");

    fclose(outputFile);
}