set serveroutput on;

    CREATE OR REPLACE PROCEDURE getOccupancyOfWarehouse(outString OUT VARCHAR2) IS
    
        finalDate DATE;
        ocp FLOAT;
        storeHouse VARCHAR(255);


        CURSOR warehouses IS
        SELECT name
        FROM Warehouse;

        BEGIN

            finalDate:=sysdate+30;
            OPEN warehouses;

            LOOP
                FETCH warehouses INTO storehouse;
                EXIT WHEN warehouses%notfound;

                SELECT occupancy INTO ocp
                FROM warehouse
                WHERE name = storehouse;

                outString := outString || 'Warehouse Name - ' || storehouse || ', Occupancy - ' || ocp || '% ' || chr(10);

                FOR LOOP2
                IN(SELECT cargoManifestLoadId, Id, expectedDepartureDate
                FROM Phases
                WHERE Phases.origin = storehouse
                AND Phases.expectedDepartureDate >= sysdate
                AND Phases.expectedDepartureDate <= finalDate)
                LOOP

                    FOR containersLoop
                    IN(SELECT ContainerNumberId
                    FROM CargoManifestContainer
                    WHERE CargoManifestLoadId=LOOP2.cargoManifestLoadId AND PhasesId=LOOP2.id)
                    LOOP
                        outString:=outString || 'Container - ' || containersLoop.ContainerNumberId || ', Expected Departure Date - ' || LOOP2.expectedDepartureDate || chr(10);
                    END LOOP;
                    
                END LOOP;
            END LOOP;

        END;
        
        
DECLARE
    stringOut VARCHAR2(2555);
BEGIN
    
    getOccupancyOfWarehouse(stringOut);
    dbms_output.put_line(stringOut); 
END;


