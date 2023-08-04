CREATE OR REPLACE PROCEDURE US207 (compareYear in INTEGER, mmsiCode in VARCHAR, totalCargoManifest out INTEGER, mediaOfContainerPerCargoManifest out FLOAT) IS

    maxPhase INTEGER;
    actualYear INTEGER;
    containerPerCargoManifest INTEGER := 0;
    totalContainers INTEGER := 0;
    idCargoManifest INTEGER;
    finalDate DATE;

    CURSOR cargoManifests IS
    SELECT id
    FROM cargoManifestLoad
    WHERE shipMmsiCode = mmsiCode
    AND isConcluded = 1;

    BEGIN

    totalCargoManifest := 0;
    mediaOfContainerPerCargoManifest := 0;


    OPEN cargoManifests;
    LOOP

        FETCH cargoManifests INTO idCargoManifest;
        EXIT WHEN cargoManifests%NOTFOUND;

        SELECT MAX (id) INTO maxPhase
        FROM Phases
        WHERE cargoManifestLoadId = idCargoManifest;

        SELECT realArrivalDate into finalDate
        FROM Phases
        WHERE cargoManifestLoadId = idCargoManifest
        AND id = maxPhase;


        SELECT EXTRACT(YEAR FROM TO_DATE(finalDate,  'YY.MM.DD HH24:MI:SS')) INTO actualYear
        FROM DUAL;

        IF actualYear = compareYear THEN

            totalCargoManifest := totalCargoManifest + 1;

            SELECT COUNT (*) INTO containerPerCargoManifest
            FROM cargoManifestContainer
            WHERE cargoManifestLoadId = idCargoManifest;

            totalContainers := totalContainers + containerPerCargoManifest;
        END IF;
    END LOOP;

    IF totalContainers != 0 AND totalCargoManifest != 0 THEN
        mediaOfContainerPerCargoManifest := totalContainers/totalCargoManifest;
    END IF;

END;
