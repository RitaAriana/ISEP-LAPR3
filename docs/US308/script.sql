SET SERVEROUTPUT ON;
CREATE OR REPLACE Trigger US308 

Before Insert
ON CargoManifestContainer
FOR EACH ROW

DECLARE

shipCap INTEGER;
totalContainers INTEGER :=0;
cmcode Integer;
cmNumberOfContainers INTEGER;
forTruck INTEGER;

CURSOR cm IS
SELECT id
FROM CargoManifestLoad
WHERE CargoManifestLoad.isConcluded IS NULL AND shipMmsiCode = (SELECT shipMmsiCode FROM CargoManifestLoad WHERE id=:NEW.CargoManifestLoadId);

BEGIN
    OPEN cm;
    LOOP
    FETCH cm INTO cmcode;
    EXIT WHEN cm%notfound;
    SELECT capacity INTO shipCap
    FROM Ship
    WHERE MmsiCode = (SELECT shipMmsiCode FROM CargoManifestLoad WHERE id=:NEW.CargoManifestLoadId);
    
    SELECT COUNT(*) INTO cmNumberOfContainers
    FROM CargoManifestContainer
    WHERE cargoManifestLoadId = cmcode;
    
    totalContainers:=totalContainers+cmNumberOfContainers;
    
    dbms_output.put_line('teste ' || cmNumberOfContainers);
    IF totalContainers+1 > shipCap THEN
        raise_application_error( -20000, 'Insufficient capacity');
    END IF;
    END LOOP;
    CLOSE cm;
END;