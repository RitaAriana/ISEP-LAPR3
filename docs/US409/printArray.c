#include <stdio.h>
#include <stdlib.h>

#include "containerStruct.h"

void printArray(short totalSlots, cMContainer *containerArray){
    
    char *outputFileName = "US409.csv";
    FILE *outputFile = fopen(outputFileName, "a");

    fprintf(outputFile, "The Containers info is the following:\n");
    for(int i = 0; i < totalSlots; i++){
        cMContainer container = *(containerArray + i);
        if(container.containerId != 0)
            fprintf(outputFile, "Contentor: %d está localizado na posição x: %d y: %d z: %d. ISO code: %d, Refrigeration: %hhd, Gross Container: %.2f, Max Weight: %.2f, Actual Weight: %.2f, Tare: %.2f, Max Volume: %.2f, Outer Thickness: %.2f, Middle Thickness: %.2f, Inner Thickness: %.2f, Outer Capacity: %.2f, Middle Capacity: %.2f, Inner Capacity: %.2f\n",
    	    container.containerId, container.x, container.y, container.z, container.isoCode, container.isRefrigerated, container.grossContainer, container.maxWeight, container.weight, container.tare, container.maxVolume, 
            container.outerThickness, container.middleThickness, container.innerThickness,container.outerCapacity, container.middleCapacity, container.innerCapacity);
    }

    fprintf(outputFile, "\n");

    fclose(outputFile);
}