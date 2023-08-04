CREATE OR REPLACE PROCEDURE UpdateCargoManifestContainer(cnId in INTEGER, gross in FLOAT, userResp in VARCHAR)
IS

BEGIN

        UPDATE CargoManifestContainer
        SET grossContainer = gross,
        userResponsibleForChanges = userResp
        WHERE containerNumberId = cnId;

END;


