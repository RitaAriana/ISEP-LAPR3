CREATE OR REPLACE TRIGGER WarehouseOccupancy

BEFORE INSERT OR DELETE
ON CargoManifestContainer
FOR EACH ROW

    DECLARE

        dest INTEGER;
        numerator INTEGER := 0;
        lastDesiredCml INTEGER;
        cmLoad INTEGER;
        cmuCode INTEGER;
        ocRate FLOAT;
        cap INTEGER;
        destAux VARCHAR(255);
        contAux INTEGER;
        flag INTEGER;
        total INTEGER :=0;
        tripAux INTEGER;


    CURSOR cmu IS
    SELECT id
    FROM CargoManifestUnload
    WHERE CargoManifestUnload.WarehouseId=dest;

    BEGIN

    IF INSERTING THEN

        SELECT destination INTO destAux
        FROM Phases
        WHERE id=:NEW.PhasesId AND cargoManifestLoadId=:NEW.cargoManifestLoadId;


    ELSE

        SELECT destination INTO destAux
        FROM Phases
        WHERE id=:OLD.PhasesId AND cargoManifestLoadId=:OLD.cargoManifestLoadId;

    END IF;

    SELECT COUNT(*) INTO flag
    FROM Warehouse
    WHERE name = destAux;

    IF flag!=0 THEN

        SELECT id INTO dest
        FROM Warehouse
        WHERE name=destAux;

        OPEN cmu;

        LOOP
                FETCH cmu INTO cmuCode;
                EXIT WHEN cmu%notfound;

                SELECT PhasesCargoManifestLoadId, PhasesId INTO cmLoad,tripAux
                FROM CargoManifestUnload
                WHERE warehouseId = dest AND id = cmuCode;

                FOR containers
                IN(SELECT ContainerNumberId, cargoManifestLoadId
                FROM CargoManifestContainer
                WHERE CargoManifestUnloadId = cmuCode AND CargoManifestLoadId = cmLoad AND PhasesId = tripAux)
                LOOP

                    SELECT MAX(CargoManifestLoad.Id) INTO lastDesiredCml
                    FROM CargoManifestContainer
                    INNER JOIN CargoManifestLoad
                    ON (CargoManifestLoad.id = CargoManifestContainer.cargoManifestLoadId)
                    WHERE ContainerNumberId = containers.ContainerNumberId;


                    IF containers.CargoManifestLoadId=lastDesiredCml THEN
                        numerator:=numerator+1;
                    END IF;

                END LOOP;
        END LOOP;



        FOR cml
        IN(SELECT id
        FROM cargoManifestLoad
        WHERE isConcluded IS NULL)
        LOOP
            FOR trips
            IN(SELECT id
            FROM Phases
            WHERE cargoManifestLoadId=cml.id
            AND destination=destAux)
            LOOP
                SELECT COUNT(containerNumberId) INTO contAux
                FROM cargoManifestContainer
                WHERE cargoManifestLoadId=cml.id
                AND PhasesId=trips.id;

                total:=total+contAux;

            END LOOP;
        END LOOP;


        SELECT capacity INTO cap
        FROM warehouse
        WHERE id=dest;

        ocRate:=((numerator+total+1)/cap)*100;
        
        IF ocRate <= 100 THEN 
            UPDATE Warehouse
            SET occupancy = ocRate
            WHERE id = dest;
        ELSE
               raise_application_error(-20000, 'The warehouse with ID ' || dest ||' it is fully occupy' );
        END IF;
        
    END IF;

END;
