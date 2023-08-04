CREATE OR REPLACE PROCEDURE US406 (initialTime IN TIMESTAMP, endTime IN TIMESTAMP, mmsi IN VARCHAR, outString OUT VARCHAR)
IS
    phase INTEGER;
    cmlid INTEGER;
    numerator INTEGER :=0;
    denominator INTEGER :=0;
    operation FLOAT;
    totalContainers INTEGER :=0;

CURSOR cm IS
    SELECT CargoManifestLoadId
    FROM Phases
    INNER JOIN CargoManifestLoad
    ON (CargoManifestLoad.id=Phases.CargoManifestLoadId)
    WHERE expectedDepartureDate>=initialTime AND expectedArrivalDate<=endTime AND CargoManifestLoad.ShipmmsiCode=mmsi AND CargoManifestLoad.isConcluded IS NOT NULL
    GROUP BY CargoManifestLoadId;
  
CURSOR p IS
    SELECT Phases.id
    FROM Phases
    INNER JOIN CargoManifestLoad
    ON (CargoManifestLoad.id=Phases.CargoManifestLoadId)
    WHERE expectedDepartureDate>=initialTime AND expectedArrivalDate<=endTime AND CargoManifestLoadId=cmlid;
    
BEGIN 
    OPEN cm;
        SELECT capacity INTO denominator
        FROM Ship
        WHERE mmsiCode=mmsi;
        LOOP   
            fetch cm INTO cmlid;
            Exit WHEN cm%notfound;
            OPEN p;
                LOOP
                    fetch p INTO phase;
                    Exit WHEN p%notfound;
                    
                    SELECT COUNT(*) INTO numerator
                    FROM CargoManifestContainer
                    WHERE PhasesId=phase AND CargoManifestLoadId=cmlid; 
                    totalContainers:=totalContainers+numerator;
                END LOOP;
                
                operation := (totalContainers/denominator)*100;
                
                IF operation >= 66 THEN 
                    outString:=outString || 'CargoManifest: ' || cmlid || ' ' || ' Occupancy rate:' || operation || '%' || chr(10);
                END IF;
                totalContainers:=0;
            CLOSE p;
        END LOOP;
    CLOSE cm;
END;

set serveroutput on;
DECLARE
output Varchar2(2550);
BEGIN
    US406(to_timestamp('21.10.01 18:44:33,000000000'),to_timestamp('21.12.01 18:44:33,000000000'),'121212121', output);
    dbms_output.put_line(output);
END;
    