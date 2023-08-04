CREATE OR REPLACE PROCEDURE resourcesForNextWeek(idOfAPort IN INTEGER, outString OUT CLOB) IS

    nextSunday DATE;
    nextSaturday DATE;
    cml INTEGER;
    portName VARCHAR(255);
    containersDeparting INTEGER;
    containersArriving INTEGER;
    containersDepartingAux INTEGER := 0;
    containersArrivingAux INTEGER := 0;
    output VARCHAR2(5000);
    CONT INTEGER := 0;
    currentDay INTEGER;


    CURSOR cargoManifestsLoad IS
    SELECT cargoManifestLoad.id
    FROM CargoManifestLoad
    INNER JOIN Phases
    ON (cargoManifestLoad.Id = Phases.CargoManifestLoadId)
    WHERE expectedArrivalDate <= nextSaturday
    AND expectedArrivalDate >= nextSunday
    GROUP BY cargoManifestLoad.id;


    BEGIN
        dbms_lob.createTemporary(outString, true);
        SELECT NEXT_DAY(sysdate,'Domingo') INTO nextSunday FROM DUAL;
        SELECT NEXT_DAY(nextSunday,'Sábado') INTO nextSaturday FROM DUAL;

        SELECT name INTO portName FROM Ports WHERE id = idOfAPort;
        output := 'In the port with the name ' || portName || ' in the week of ' || nextsunday || ' until ' || nextsaturday || ': ' || chr(10);
        dbms_lob.append(outString, output);

        WHILE CONT < 7
        LOOP
            currentDay := EXTRACT (DAY FROM nextSunday) + CONT;

            IF currentDay > 31 THEN
                currentDay := currentDay - 31;
            END IF;

            output := 'Day: ' || currentDay || chr(10);
            dbms_lob.append(outString, output);

            OPEN cargoManifestsLoad;
            LOOP

                FETCH cargoManifestsLoad INTO cml;
                EXIT WHEN cargoManifestsLoad%notFound;

                FOR phasesInACargoManifest IN
                (SELECT origin, destination, id, expectedArrivalDate, expectedDepartureDate
                FROM Phases
                WHERE cargoManifestLoadId = cml
                AND EXTRACT (DAY FROM expectedArrivalDate) = currentDay)
                LOOP

                    IF phasesInACargoManifest.destination = portName THEN

                        SELECT COUNT(*) INTO containersArriving
                        FROM CargoManifestContainer
                        WHERE cargoManifestLoadId = cml
                        AND phasesId = phasesInACargoManifest.id;

                        containersArrivingAux := containersArrivingAux + containersArriving;

                        FOR allContainersArriving IN
                        (SELECT containerNumberId
                        FROM CargoManifestContainer
                        WHERE cargoManifestLoadId = cml
                        AND phasesId = phasesInACargoManifest.id)
                        LOOP
                            output := 'Container number id: ' || allContainersArriving.containerNumberId || ' (Arrival)' || chr(10);
                            dbms_lob.append(outString, output);
                        END LOOP;
                    END IF;
                END LOOP;

                FOR phasesInACargoManifest2 IN
                (SELECT origin, destination, id, expectedArrivalDate, expectedDepartureDate
                FROM Phases
                WHERE cargoManifestLoadId = cml
                AND EXTRACT (DAY FROM expectedDepartureDate) = currentDay)
                LOOP
                    IF phasesInACargoManifest2.origin = portName THEN

                        SELECT COUNT(*) INTO containersDeparting
                        FROM CargoManifestContainer
                        WHERE cargoManifestLoadId = cml
                        AND phasesId = phasesInACargoManifest2.id;

                        containersDepartingAux := containersDepartingAux + containersDeparting;

                        FOR allContainersDeparting IN
                        (SELECT containerNumberId
                        FROM CargoManifestContainer
                        WHERE cargoManifestLoadId = cml
                        AND phasesId = phasesInACargoManifest2.id)
                        LOOP
                            output := 'Container number id: ' || allContainersDeparting.containerNumberId || ', Departure with destination: ' || phasesInACargoManifest2.destination || chr(10);
                            dbms_lob.append(outString, output);
                        END LOOP;
                    END IF;
                END LOOP;

            END LOOP;

            output := 'The total containers who arrived was: '|| containersArrivingAux || chr(10);
            dbms_lob.append(outString, output);
            output := 'The total containers who left was: ' || containersDepartingAux || chr(10);
            dbms_lob.append(outString, output);
            containersDepartingAux := 0;
            containersArrivingAux := 0;
            CLOSE cargoManifestsLoad;

            CONT := CONT + 1;

        END LOOP;

    END;
    
    





