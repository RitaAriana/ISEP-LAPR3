CREATE OR REPLACE PROCEDURE US208 (idCargoManifest in INTEGER, occupancyRate out FLOAT) IS

    totalContainers INTEGER;
    mmsiCodeShip VARCHAR(255);
    capacityShip FLOAT;

BEGIN

    occupancyRate:=0;
    totalContainers:=0;

    SELECT COUNT (*) INTO totalContainers
    FROM CargoManifestContainer
    WHERE CargoManifestContainer.cargoManifestLoadId = idCargoManifest;

    SELECT shipMmsiCode INTO mmsiCodeShip
    FROM CargoManifestLoad
    WHERE CargoManifestLoad.id = idCargoManifest;

    SELECT capacity INTO capacityShip
    FROM Ship
    WHERE Ship.mmsiCode = mmsiCodeShip;

    IF totalContainers != 0 OR capacityShip != 0 THEN
        occupancyRate:= (totalContainers/capacityShip)*100;
    END IF;
END;



