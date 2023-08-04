
CREATE OR REPLACE PROCEDURE US210 (information out VARCHAR2) IS

    idShip VARCHAR(255);
    nextMonday DATE;
    idCargoManifest INTEGER;
    totalPhases INTEGER;
    arrivalDate DATE;
    arrivalPosition VARCHAR(255);
    flag BOOLEAN;
    latitudeShip VARCHAR(255);
    longitudeShip VARCHAR(255);
    totalCargoManifests INTEGER;
    isFirst INTEGER;
    maxDate DATE;
    finalPosition VARCHAR(255);
    cont INTEGER;
    maxBaseDateTime TIMESTAMP;

    CURSOR allShips IS
    SELECT mmsiCode FROM Ship;

    CURSOR allCargoManifests IS
    SELECT id FROM CargoManifestLoad
    WHERE shipMmsiCode = idShip;


BEGIN

    SELECT NEXT_DAY(sysdate,'SEGUNDA') INTO nextMonday FROM dual;

    information := information || '----------* Ships ready in next Monday (' || nextMonday || ') *----------' || chr(10);

    OPEN allShips;

    LOOP
        FETCH allShips INTO idShip;
        EXIT WHEN allShips%NOTFOUND;

        flag := true;
        totalPhases := 0;
        cont := 0;

        OPEN allCargoManifests;

        LOOP
            FETCH allCargoManifests into idCargoManifest;
            EXIT WHEN allCargoManifests%NOTFOUND;

            flag := false;


            SELECT COUNT (*) INTO totalPhases
            FROM Phases
            WHERE cargoManifestLoadId = idCargoManifest;
            dbms_output.put_line(totalPhases);


            SELECT expectedArrivalDate INTO arrivalDate
            FROM Phases
            WHERE id = totalPhases
            AND cargoManifestLoadId = idCargoManifest;

            IF (arrivalDate < nextMonday) THEN
                cont := cont + 1;
            END IF;
        END LOOP;
        CLOSE allCargoManifests;

        IF flag = false THEN
            SELECT COUNT (*) INTO totalCargoManifests
            FROM CargoManifestLoad
            WHERE shipMmsiCode = idShip;

            IF cont = totalCargoManifests THEN
                isFirst := 0;

                OPEN allCargoManifests;
                LOOP

                    FETCH allCargoManifests into idCargoManifest;
                    EXIT WHEN allCargoManifests%NOTFOUND;

                    SELECT COUNT (*) INTO totalPhases
                    FROM Phases
                    WHERE cargoManifestLoadId = idCargoManifest;


                    SELECT expectedArrivalDate, destination INTO arrivalDate, arrivalPosition
                    FROM Phases
                    WHERE id = totalPhases
                    AND cargoManifestLoadId = idCargoManifest;

                    isFirst := isFirst + 1;


                    IF isFirst = 1 THEN
                        maxDate := arrivalDate;
                        finalPosition := arrivalPosition;
                    END IF;

                    IF isFirst != 1 AND arrivalDate > maxDate THEN
                        maxDate := arrivalDate;
                        finalPosition := arrivalPosition;
                    END IF;
                END LOOP;
                CLOSE allCargoManifests;
    
                 information:= information || idShip || '--> ' ||finalPosition|| chr(10);
    
            END IF;
        END IF;


        IF flag = true THEN
        
            SELECT MAX (baseDateTime) INTO maxBaseDateTime
            FROM ShipLocation
            WHERE shipMmsiCode = idShip;

            SELECT latitude, longitude INTO latitudeShip, longitudeShip
            FROM ShipLocation
            WHERE shipMmsiCode = idShip
            AND baseDateTime = maxBaseDateTime;
            
            information := information || idShip || '--> Latitude: ' || latitudeShip || '--> Longitude: ' || longitudeShip || chr(10);
        END IF;
        
    END LOOP;
    CLOSE allShips;
END;