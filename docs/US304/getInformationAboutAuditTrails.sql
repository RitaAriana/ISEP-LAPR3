set serveroutput on;
    CREATE OR REPLACE PROCEDURE getInformationAboutAuditTrails(cargoManifestId IN INTEGER, containerId IN INTEGER, outString OUT CLOB)
    IS
        opName VARCHAR(6);
        out VARCHAR2(2555);

    BEGIN

        dbms_lob.createTemporary(outString, true);

        FOR getInformation
        IN (SELECT userUserName, operationsId, cargoManifestContainerNumberId, cargoManifestContainerCargoManifestLoadId, id,dateOfChange
        FROM AuditTrails
        WHERE cargoManifestContainerCargoManifestLoadId = cargoManifestId
        AND cargoManifestContainerNumberId = containerId
        ORDER BY dateOfChange)

        LOOP
            SELECT operationName INTO opName
            FROM Operations
            WHERE id = getInformation.operationsId;

            out := 'Cargo Manifest Id - ' || getInformation.cargoManifestContainerCargoManifestLoadId || ' Container ID - ' || getInformation.cargoManifestContainerNumberId || ' User who made the modification - ' || getInformation.userUserName || ' Operation made - ' || opName || ' Date of the operation - ' || getInformation.dateOfChange || chr(10);
            dbms_lob.append(outString, out);
        END LOOP;

    END;


