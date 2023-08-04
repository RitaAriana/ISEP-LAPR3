#include "containerStruct.h"
#include "printResult.h"

float calculateEnergy(cMContainer *containerArray, short totalSlots, float externalTemp, float desiredTemp){

    float requiredEnergy = 0, area = 72.5;
    
    for(short i = 0; i<totalSlots; i++){
        cMContainer container = *(containerArray + i);

        char isRefrigerated = container.isRefrigerated;

        if(isRefrigerated == 1){

            float totalResistivity = 0;
            float outerThickness = container.outerThickness, outerCapacity = container.outerCapacity;
            float middleThickness = container.middleThickness, middleCapacity = container.middleCapacity;
            float innerThickness = container.innerThickness, innerCapacity = container.innerCapacity;

            totalResistivity += (outerThickness/(outerCapacity*area));
            totalResistivity += (middleThickness/(middleCapacity*area));
            totalResistivity += (innerThickness/(innerCapacity*area));

            float temp = (((externalTemp - desiredTemp)/totalResistivity) * 3600);

            if(temp < 0)
                temp = -temp;

            requiredEnergy += temp;
        }
    }
    return requiredEnergy;
}