CREATE OR REPLACE PROCEDURE PortsInformation (month INTEGER, year INTEGER, outString OUT CLOB) IS

    p INTEGER;
    ships INTEGER;
    cWare INTEGER:=0;
    num_of_days INTEGER;
    contador INTEGER:=0;
    cml INTEGER;
    tid INTEGER;
    pName VARCHAR(255);
    Aux VARCHAR(255);
    auxDate TIMESTAMP;
    auxC INTEGER;
    auxContador INTEGER;
    numerator INTEGER :=0;
    ocR FLOAT;
    cap INTEGER;
    Lastdesiredcml INTEGER;
    output VARCHAR2(30000);



    CURSOR PortsAux IS
    SELECT id
    FROM Ports;

    BEGIN

        dbms_lob.createTemporary(outString, true);

        OPEN PortsAux;
        LOOP
        FETCH PortsAux INTO p;
        EXIT WHEN PortsAux%notfound;

        SELECT name INTO pName
        FROM Ports
        WHERE id = p;

        output:= 'Ports: ' || pName || chr(10);
        dbms_lob.append(outString, output);

        SELECT maximumShip,capacity into ships,cap
        FROM Ports
        WHERE id=p;

        SELECT EXTRACT(DAY FROM LAST_DAY(to_date('01.'||month||'.'||year||'','DD.MM.YYYY')))into num_of_days FROM DUAL;

        FOR var2 IN 1..num_of_days
            LOOP
            contador:=0;
            auxContador:=0;
            numerator :=0;
            FOR LOOP
            IN(
            SELECT id,destination,origin, cargomanifestloadid, expectedArrivalDate,expectedDepartureDate
            FROM Phases
            WHERE destination = pName or origin = pName)
            LOOP
                SELECT COUNT(*) INTO auxC
                FROM Phases
                WHERE cargomanifestloadid = LOOP.cargomanifestloadid;

                IF LOOP.id-1 > 0 THEN
                    SELECT destination, expectedArrivalDate INTO aux, auxDate
                    FROM Phases
                    WHERE id = LOOP.id-1
                    AND cargomanifestloadid = LOOP.cargomanifestloadid;

                    IF auxC = LOOP.id THEN
                        IF LOOP.destination = pName AND var2>=EXTRACT(Day FROM (LOOP.expectedArrivalDate)) AND month = EXTRACT(MONTH From LOOP.expectedArrivalDate) THEN

                            SELECT COUNT(*) INTO auxContador
                            FROM Phases
                            INNER JOIN cargoManifestLoad
                            ON(Phases.cargoManifestLoadId = cargoManifestLoad.id)
                            WHERE cargoManifestLoad.shipMmsiCode = (SELECT shipMmsiCode FROM cargoManifestLoad WHERE id = LOOP.cargoManifestLoadId)
                            AND Phases.expectedDepartureDate > LOOP.expectedArrivalDate
                            AND Extract(Day FROM Phases.expectedDepartureDate) + 1 <= var2;

                            IF auxContador=0 THEN
                                contador := contador + 1;
                            END IF;

                        ELSIF LOOP.origin = pName AND LOOP.origin = aux AND var2 >= EXTRACT(Day FROM (auxDate)) AND var2 <= EXTRACT(Day FROM (LOOP.expectedDepartureDate))AND month = EXTRACT(MONTH From LOOP.expectedArrivalDate) AND EXTRACT(Year from loop.expectedArrivalDate) <= year THEN
                            contador := contador + 1;
                        END IF;

                        ELSIF LOOP.origin = pName AND LOOP.origin = aux AND var2 > EXTRACT(Day FROM (auxDate)) AND var2 <= EXTRACT(Day FROM (LOOP.expectedDepartureDate))AND month = EXTRACT(MONTH From LOOP.expectedArrivalDate) AND EXTRACT(Year from loop.expectedArrivalDate) <= year THEN
                            contador:=contador+1;

                        END IF;

                    ELSE
                        IF auxC = 1 THEN
                            IF LOOP.destination = pName AND var2 >= EXTRACT(Day FROM (LOOP.expectedArrivalDate)) AND month = EXTRACT(MONTH FROM LOOP.expectedArrivalDate) AND EXTRACT(Year from loop.expectedArrivalDate) <= year THEN

                            SELECT COUNT(*) INTO auxContador
                            FROM Phases
                            INNER JOIN cargoManifestLoad
                            ON (Phases.cargoManifestLoadId = cargoManifestLoad.id)
                            WHERE cargoManifestLoad.shipMmsiCode = (SELECT shipMmsiCode FROM cargoManifestLoad WHERE id = 1)
                            AND Phases.expectedDepartureDate > LOOP.expectedArrivalDate
                            AND Extract(Day FROM Phases.expectedDepartureDate) <= var2;

                            IF auxContador = 0 THEN
                                contador := contador + 1;
                            END IF;
                        END IF;
                        ELSE
                            IF LOOP.destination = pName AND var2 = EXTRACT(Day FROM (LOOP.expectedArrivalDate)) AND month = EXTRACT(MONTH FROM LOOP.expectedArrivalDate) AND EXTRACT(Year from loop.expectedArrivalDate) <= year THEN
                                contador := contador + 1;
                            END IF;

                    END IF;
                END IF;

            END LOOP;

            output:= 'Number of ships docked: ' ||contador ||chr(10);
            dbms_lob.append(outString, output);
            output:= 'Occupancy of ships docked: ' ||contador/ships||' in day: ' || var2 || chr(10);
            dbms_lob.append(outString, output);

            FOR PortsAuxOc
            IN (SELECT cargoManifestUnload.id , cargoManifestUnload.phasescargoManifestLoadId, phasesId
            FROM cargoManifestUnload
            INNER JOIN Phases
            ON (cargoManifestUnload.phasescargoManifestLoadId = Phases.cargoManifestLoadId AND cargoManifestUnload.phasesId = Phases.id)
            WHERE PortId = p
            AND EXTRACT(Day FROM Phases.expectedArrivalDate) <= VAR2
            AND EXTRACT(Month FROM Phases.expectedArrivalDate) <= month AND EXTRACT(Year from phases.expectedArrivalDate) = year OR EXTRACT(Year from phases.expectedArrivalDate) < year)
            LOOP

                FOR containers
                IN(SELECT ContainerNumberId
                FROM CargoManifestContainer
                WHERE CargoManifestUnloadId = PortsAuxOc.id AND CargoManifestLoadId = PortsAuxOc.phasescargoManifestLoadId AND phasesId = PortsAuxOc.phasesId)
                LOOP

                    SELECT MAX(CargoManifestContainer.phasescargoManifestLoadId) INTO lastDesiredcml
                    FROM CargoManifestContainer
                    INNER JOIN Phases
                    ON (CargoManifestContainer.phasescargoManifestLoadId = Phases.cargoManifestLoadId)
                    WHERE ContainerNumberId = containers.ContainerNumberId
                    AND EXTRACT(Day FROM Phases.expectedDepartureDate) <= VAR2
                    AND EXTRACT(Month FROM Phases.expectedDepartureDate) <= month AND EXTRACT(Year from phases.expectedarrivaldate) = year OR EXTRACT(Year from phases.expectedarrivaldate) < year;

                        IF PortsAuxOC.phasesCargoManifestLoadId = lastDesiredcml THEN
                            numerator := numerator + 1;
                        END IF;
                    END LOOP;
                END LOOP;

                ocR:=(numerator/cap)*100;

                output:='Occupancy of Ports ' || pName || ':' || ocR ||' in day ' || var2 || chr(10);
                dbms_lob.append(outString, output);
            END LOOP;

            SELECT COUNT(*) INTO cWare
            FROM Warehouse
            WHERE portId = p;

            output := 'Number of warehouses: ' || cWare || chr(10);
            dbms_lob.append(outString, output);
        END LOOP;
    END;