
    CREATE OR REPLACE TRIGGER AuditTrailsTrigger

        AFTER
        UPDATE OR DELETE OR INSERT
        ON CargoManifestContainer
        FOR EACH ROW

        DECLARE
        idAuditTrails Integer;
        transactionStatus VARCHAR2(10);

        BEGIN
            transactionStatus := CASE
            WHEN UPDATING THEN 2
            WHEN DELETING THEN 3
            WHEN INSERTING THEN 1
        END;

        SELECT COUNT(*) INTO idAuditTrails FROM AuditTrails;
        idAuditTrails := idAuditTrails + 1;

        INSERT INTO AuditTrails
        VALUES(:NEW.userResponsibleForChanges,transactionStatus,:NEW.containerNumberId,:NEW.cargoManifestLoadId,idAuditTrails,sysdate);

    END;




