-- TEST FOR RELATIONSHIPS BETWEEN CONTAINER AND CARGO MANIFEST

INSERT INTO Ship(mmsiCode, imoCode, numberOfEnergyGenerators, generatorOutput, callSign, draft, shipName, vesselTypeId, shipLength, width, cargo)
values('210950000', 'IMO9395044', 2, 4, 'C4SQ2', 9.5, 'VARAMO', 70, 166, 25, '5AN');

INSERT INTO container (numberId, checkDigit, isoCode, maxWeight, payload, tare, weight, maxWeightPacked, maxVolumePacked, repairRecommendation, certificate)
values(3, 4, '20G0', 400, 200, 300, 100, 450, 350, 'necessary', 'certificate');

INSERT INTO cargoManifestShip(id, shipMmsiCode)
values ('ID5005', '210950000');

INSERT INTO cargoManifestContainer(containerNumberId, cargoManifestId, xContainer, yContainer, zContainer, grossContainer)
values(3, 'ID5005', 0, 0, 0, 53);

--expected: pass, as CargoManifestContainer depends so much on the foreign key who comes from Cargo Manifest and container, and the Container, in turn, depends on the foreign key coming from a ship's mmsi code, the CargoManifestContainer will be created without any problem because none of these integrity restrictions will be violated 

INSERT INTO cargoManifestContainer(containerNumberId, cargoManifestId, xContainer, yContainer, zContainer, grossContainer)
values(3, 'ID5005', 0, 0, 0, 53);

--expected: fail, as the CargoManifestContainer depends so much on the foreign key coming from the Cargo Manifest and the Container, and the Container, in turn, depends on the foreign key coming from a ship's mmsi code, the CargoManifestContainer will not be created, as it will not there is no ship created in the system with the MMSI code '210950001' therefore there cannot be a container with this MMSI code, therefore, it is not possible to create a CargoManifestContainer as it would violate the integrity rules stipulated by the foreign keys

INSERT INTO cargoManifestContainer(containerNumberId, cargoManifestId, xContainer, yContainer, zContainer, grossContainer)
values(3, 'ID5004', 0, 0, 0, 53);

--expected: fail, as the CargoManifestContainer depends so much on the foreign key coming from the Cargo Manifest and the Container, and there is no Cargo Manifest with this ID, you cannot create a CargoManifestContainer with this ID as it would violate the integrity rules of foreign keys

INSERT INTO cargoManifestContainer(containerNumberId, cargoManifestId, xContainer, yContainer, zContainer, grossContainer)
values(2, 'ID5005', 0, 0, 0, 53);

--expected: failure, as the CargoManifestContainer depends on the foreign key coming from the Cargo and Container Manifest, and as there is no container with the Id that is intended to create the CargoManifestController, the foreign keys integrity principle would be violated