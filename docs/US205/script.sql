
CREATE OR REPLACE PROCEDURE US205 (mmsiCode in Varchar, outString out Varchar)
IS
    cmunload Integer;
    phases Integer;
    cmcode Integer;
    x Integer;
    y Integer;
    z Integer;
    port_counter Integer :=0;
    nextLocation varchar(255);
    iso_type varChar (255);
    nextPort Varchar (255);
    proxLoc Varchar (255);
    weight_load float;

Cursor cm IS
    SELECT id
    FROM CargoManifestLoad
    WHERE shipMmsiCode=mmsiCode 
    ORDER BY id;

BEGIN
OPEN cm;
    LOOP 
        FETCH cm INTO cmcode;
        Exit WHEN cm%notfound;

        

        SELECT COUNT(Phases.cargoManifestLoadId) INTO phases 
        FROM Phases
        WHERE Phases.cargoManifestLoadId = cmcode;
        dbms_output.put_line(phases);    

        SELECT COUNT(CargoManifestUnload.PhasesCargoManifestLoadId) INTO cmunload 
        FROM CargoManifestUnload
        WHERE CargoManifestUnload.PhasesCargoManifestLoadId=cmcode;                                          
        dbms_output.put_line(cmunload);

        IF phases = cmunload THEN
            dbms_output.put_line('Delivery finished ' ||cmcode);
                
        ELSE
            SELECT destination INTO proxLoc
            FROM Phases                    
            WHERE id=cmunload+1
            AND cargoManifestLoadId= cmcode;

            
            port_counter:= port_counter + 1;
            
            IF port_counter=1 THEN
                nextPort:= proxLoc;
            End IF;
            
            IF proxLoc=nextPort THEN
                FOR containers_toExitInDestination 
                IN(Select CargoManifestContainer.containerNumberId 
                    FROM CargoManifestContainer 
                    INNER JOIN Phases
                    ON(CargoManifestContainer.CargoManifestLoadId = Phases.CargoManifestLoadId)
                    WHERE CargoManifestContainer.CargoManifestLoadId=cmcode 
                    AND CargoManifestContainer.PhasesId=cmunload+1
                    AND Phases.destination=proxLoc)
                LOOP
                    SELECT xContainer, yContainer, zContainer INTO x, y, z FROM CargoManifestContainer WHERE containerNumberId=containers_toExitInDestination.containerNumberId;
                    SELECT isoCode, weight INTO iso_type, weight_load FROM Container WHERE numberId=containers_toExitInDestination.containerNumberId;

                    outString:=outString||containers_toExitInDestination.containerNumberId||','||x||y||z||','||iso_type||','||weight_load||chr(10);
                END LOOP;
            END IF;
        END IF;
    END LOOP;
    CLOSE cm;
END;



