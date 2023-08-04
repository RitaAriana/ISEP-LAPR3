
CREATE OR REPLACE PROCEDURE US206 (mmsiCode in Varchar, outString out Varchar)
IS
    cmUnload Integer;
    countPhases Integer;
    cmcode Integer;
    cargoId Integer;
    cmGeneratedLoad Integer;
    nextLocation varchar(255);
    iso_type varchar(255);
    weight_load float;

CURSOR cm IS
    SELECT id
    FROM CargoManifestLoad
    WHERE shipMmsiCode=mmsiCode
    ORDER BY id;

BEGIN
    OPEN cm;
        LOOP
            fetch cm INTO cmcode;
            Exit When cm%notfound;


            SELECT COUNT(cargoManifestLoadId) INTO countPhases
            FROM Phases
            WHERE cargoManifestLoadId=cmcode;
            dbms_output.put_line(countPhases);


            SELECT COUNT(PhasesCargoManifestLoadId) INTO cmUnload
            FROM CargoManifestUnload
            WHERE PhasesCargoManifestLoadId=cmcode;


            IF countPhases!=cmUnload THEN
                SELECT destination INTO nextLocation
                FROM Phases
                WHERE id=cmUnload+1
                AND cargoManifestLoadId=cmcode;


                SELECT Phases.cargoManifestLoadId INTO cmGeneratedLoad
                FROM Phases
                INNER JOIN CargoManifestLoad
                ON (Phases.cargoManifestLoadId=CargoManifestLoad.id)
                WHERE Phases.origin=nextLocation
                AND Phases.id=1 AND Phases.cargoManifestLoadId>cmcode AND CargoManifestLoad.shipMmsiCode=mmsiCode;

                For containers_from_cm_generatedLoad
                IN(select containerNumberId FROM CargoManifestContainer WHERE cargoManifestLoadId=cmGeneratedLoad)
                LOOP
                    SELECT isoCode, weight INTO iso_type, weight_load FROM Container WHERE numberId=containers_from_cm_generatedLoad.ContainerNumberId;
                    outString:=outString ||containers_from_cm_generatedLoad.containerNumberId ||',' ||iso_type ||',' ||weight_load || chr(10);
                END LOOP;
                EXIT;
            END IF;
        END LOOP;
    CLOSE cm;
END;