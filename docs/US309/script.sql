CREATE OR REPLACE Trigger US309

Before Insert
ON CargoManifestContainer
FOR EACH ROW

DECLARE

cmCode INTEGER;
expDepDate TIMESTAMP;
realDepDate TIMESTAMP;
realArrDate TIMESTAMP;

CURSOR cm IS
SELECT id
FROM CargoManifestLoad
WHERE CargoManifestLoad.isConcluded IS NULL AND shipMmsiCode = (SELECT shipMmsiCode FROM CargoManifestLoad WHERE id=:NEW.CargoManifestLoadId);


BEGIN
    OPEN cm;
    LOOP
    FETCH cm INTO cmCode;
    EXIT WHEN cm%notfound;
    
        SELECT expectedDepartureDate INTO expDepDate 
        FROM Phases
        WHERE CargoManifestLoadId=:NEW.CargoManifestLoadId 
        AND id=:NEW.PhasesId;
        
        FOR seeDatePhases
        IN (SELECT realDepartureDate, realArrivalDate FROM Phases WHERE CargoManifestLoadId = cmCode)
        LOOP        
        IF expDepDate >= seeDatePhases.realDepartureDate OR expDepDate <= seeDatePhases.realArrivalDate THEN
            raise_application_error( -20000, 'Cargo Manifest registration ERROR');
        END IF;
        END LOOP;
    END LOOP;
    CLOSE CM; 
END;