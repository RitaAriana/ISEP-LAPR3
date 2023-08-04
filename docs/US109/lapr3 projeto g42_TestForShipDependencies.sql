--TESTS FOR SHIP AND THEIR RELATION WITH SHIP POSITION

INSERT INTO Ship(mmsiCode, imoCode, numberOfEnergyGenerators, generatorOutput, callSign, draft, shipName, vesselTypeId, shipLength, width, cargo)
values('210950000', 'IMO9395044', 2, 4, 'C4SQ2', 9.5, 'VARAMO', 70, 166, 25, 'NA');

--expected: pass, Do not exist any ship in the system with this atributtes

INSERT INTO Ship(mmsiCode, imoCode, numberOfEnergyGenerators, generatorOutput, callSign, draft, shipName, vesselTypeId, shipLength, width, cargo)
values('210950000', 'IMO9395044', 2, 4, 'C4SQ2', 9.5, 'VARAMO', 70, 166, 25, 'NA');

--expected: fail, Already exist a ship with this MMSI Code in the system, this means, how IMO Code it's a primary key of Ship need to be unique for each ship 

INSERT INTO ShipPosition(shipMmsiCode, baseDateTime, latitude, longitude, sog, cog, heading, position, transceiver)
values ('210950000', '2004-11-03 18:44:33', '90', '180', 110, 4, '355', 3, 'NA');

--expected: pass, Exist a ship in the system with this MMSI Code to be associated with this Ship Location, otherwise, the test would fail because MMSI Code it's a Foreign Key in Ship Position that's associated with the MMSI Code of a Ship

INSERT INTO ShipPosition(shipMmsiCode, baseDateTime, latitude, longitude, sog, cog, heading, position, transceiver)
values ('210950001', '2004-11-03 18:44:33', '90', '180', 110, 4, '355', 3, 'NA');

--expected: fail, Do not exist a ship in the system with this MMSI Code to be associated with this Ship Location, because who MMSI Code in Ship Position it's a Foreign Key that's associated with the MMSI Code of a Ship, and this ship does not exist in the system the test will fail

INSERT INTO ShipPosition(shipMmsiCode, baseDateTime, latitude, longitude, sog, cog, heading, position, transceiver)
values ('210950000', '2004-11-03 18:44:33', '90', '180', 110, 4, '355', 3, 'NA');

--expected: fail, How in Ship Position the Primary key it's composed by the base DateTime and the MMSI code, the test will fail because already exist an instance of ship location with these attributes in the system

DELETE FROM ship WHERE mmsicode = '210950000';

--expected: fail,  Because as the ship's mmsi code are associated ship locations, this cannot be erased until this dependency is broken

DELETE FROM shipPosition WHERE shipMmsicode = '210950000';
DELETE FROM ship WHERE mmsicode = '210950000';

--expected: pass, Now as when we delete the ship there is no longer any ship location associated with its MMSI code, we can delete without problems because we do not violate the foreign key integrity restriction
