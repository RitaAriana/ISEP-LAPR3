SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE ShipsIdleUS404 (outString OUT CLOB) IS

    allShipsMmsiCode VARCHAR(9);
    cargoManifestsOfAShipAux INTEGER;
    flag BOOLEAN;
    idleTime FLOAT := 0;
    allCml INTEGER;
    output VARCHAR2(30000);

    CURSOR allShips IS
    SELECT mmsiCode
    FROM Ship;

    CURSOR cargoManifestsOfAShip IS
    SELECT CargoManifestLoad.id
    FROM CargoManifestLoad
    INNER JOIN Phases
    ON (cargoManifestLoad.Id = Phases.CargoManifestLoadId)
    WHERE shipMmsiCode = allShipsMmsiCode
    AND (EXTRACT (YEAR FROM expectedArrivalDate) = EXTRACT(YEAR FROM SYSDATE)
    OR EXTRACT (YEAR FROM expectedDepartureDate) = EXTRACT(YEAR FROM SYSDATE))
    GROUP BY CargoManifestLoad.id;

    BEGIN
        dbms_lob.createTemporary(outString, true);
        OPEN allShips;
        LOOP
            FETCH allShips INTO allShipsMmsiCode;
            EXIT WHEN allShips%notFound;
        
            flag := false;
            OPEN cargoManifestsOfAShip;
            LOOP
                FETCH cargoManifestsOfAShip INTO cargoManifestsOfAShipAux;
                EXIT WHEN cargoManifestsOfAShip%notFound;

                flag := true;

                FOR phasesPerYear IN
                (SELECT id, expectedDepartureDate, expectedArrivalDate
                FROM Phases
                WHERE cargoManifestLoadId = cargoManifestsOfAShipAux
                AND EXTRACT(YEAR FROM expectedDepartureDate) = EXTRACT(YEAR FROM SYSDATE))
                LOOP
                    SELECT CAST(phasesPerYear.expectedArrivalDate AS DATE) - CAST(phasesPerYear.expectedDepartureDate AS DATE) + idleTime
                    INTO idleTime FROM dual;

                END LOOP;

            END LOOP;

            CLOSE cargoManifestsOfAShip;

            IF flag = false THEN

                SELECT COUNT(*) INTO allCml
                FROM CargoManifestLoad
                WHERE shipMmsiCode = allShipsMmsiCode
                AND isConcluded = 1;

                IF allCml = 0 THEN
                    output := 'The ship with the mmsi code: ' || allShipsMmsiCode || ', was idle for the entire year.' || chr(10);
                    dbms_lob.append(outString, output);
                ELSE
                    output := 'The ship with the mmsi code: ' || allShipsMmsiCode || ', was not in idle in this year.' || chr(10);
                    dbms_lob.append(outString, output);
                END IF;

            ELSE
                output := 'The ship with the mmsi code: ' || allShipsMmsiCode || ', was idle for : ' || (365-idleTime) ||' days.' ||  chr(10);
                dbms_lob.append(outString, output);
            END IF;

        END LOOP;
        CLOSE allShips;

    END;

DECLARE
    output VARCHAR2(2555);
BEGIN
    shipsidleus404(output);
    dbms_output.put_line(output);
END;