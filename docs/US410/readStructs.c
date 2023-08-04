#include <stdio.h>

#include "containerStruct.h"

void readStructs(FILE *containerFile, cMContainer *containerArray, char fileNum){
    int index = 0;
    while(!feof(containerFile)){
        cMContainer container;

        if(fileNum == 1){
            fscanf(containerFile, "%d,%d,%d,%d,%hhd,%d", &container.containerId, &container.x, &container.y, &container.z, 
            &container.isRefrigerated, &container.isoCode);
            
            *(containerArray + index) = container;
        }
    
        if(fileNum == 2){
            container = *(containerArray + index);
            fscanf(containerFile, "%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f", &container.grossContainer, &container.maxWeight, &container.weight, &container.tare, 
            &container.maxVolume, &container.outerThickness, &container.middleThickness, &container.innerThickness,
            &container.outerCapacity, &container.middleCapacity, &container.innerCapacity);
            
            *(containerArray + index) = container;
        }
        
        index++;
    }
}