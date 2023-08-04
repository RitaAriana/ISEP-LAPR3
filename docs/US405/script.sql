CREATE OR REPLACE PROCEDURE US405 (initialTime IN TIMESTAMP, endTime IN TIMESTAMP, mmsi IN VARCHAR, outString OUT VARCHAR)
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
    WHERE expectedDepartureDate>=initialTime AND expectedArrivalDate<=endTime AND CargoManifestLoad.ShipmmsiCode=mmsi
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
                    dbms_output.put_line(numerator);
                    dbms_output.put_line('Phase: ' || phase || 'CargoManifestLoad: ' || cmlid );
                    totalContainers:=totalContainers+numerator;
                END LOOP;
                
                operation := (totalContainers/denominator)*100;
                dbms_output.put_line('CargoManifest: ' || cmlid || ' Occupancy rate:' || operation); 
                outString:=outString || 'CargoManifest: ' || cmlid || ' ' || ' Occupancy rate:' || operation || '%' || chr(10);
                totalContainers:=0;
            CLOSE p;
        END LOOP;
    CLOSE cm;
END;
    
set serveroutput on;
DECLARE
output Varchar2(2550);
BEGIN
    US405(to_timestamp('21.10.02 18:44:33,000000000'),to_timestamp('21.12.30 18:44:33,000000000'),'210950000', output);
     dbms_output.put_line('-');
    dbms_output.put_line(output);
END;

