
CREATE OR REPLACE PROCEDURE US209 (mmsiCodeShip in VARCHAR, actualDate in TIMESTAMP, occupancyRate out FLOAT) IS

    totalContainers INTEGER;
    capacityShip FLOAT;
    cargoManifestsId CargoManifestLoad.Id%type;
    date1 TIMESTAMP;
    date2 TIMESTAMP;
    idOfPhase INTEGER;
    finalContainer INTEGER:=0;
    cont INTEGER;

    CURSOR cargoManifests IS
    SELECT id
    FROM CargoManifestLoad
    WHERE shipMmsiCode = mmsiCodeShip;

BEGIN

    occupancyRate:=0;
    totalContainers:=0;
    OPEN cargoManifests;

    LOOP
        FETCH cargoManifests INTO cargoManifestsId;
        EXIT WHEN cargoManifests%NOTFOUND;

        FOR phasesInCargoManifest IN
        (SELECT Phases.id
        FROM Phases
        WHERE Phases.cargoManifestLoadId = cargoManifestsId)
        LOOP
            SELECT realDepartureDate, realArrivalDate, id INTO date1, date2, idOfPhase
            FROM Phases
            WHERE Phases.id = phasesInCargoManifest.id
            AND cargoManifestLoadId = cargoManifestsId;

            IF (date1 <= actualDate AND date2 >= actualDate) THEN

                SELECT COUNT (*) INTO totalContainers
                FROM CargoManifestContainer
                WHERE phasesId >= idOfPhase
                AND cargoManifestLoadId = cargoManifestsId;
                finalContainer:= finalContainer + totalContainers;
            END IF;
        END LOOP;
    END LOOP;

    SELECT capacity INTO capacityShip
    FROM Ship
    WHERE Ship.mmsiCode = mmsiCodeShip;

    IF finalContainer != 0 OR capacityShip != 0 THEN
        occupancyRate:= (finalContainer/capacityShip)*100;
    END IF;
END;