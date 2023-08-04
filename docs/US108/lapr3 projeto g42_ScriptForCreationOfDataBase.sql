DROP TABLE Ship CASCADE CONSTRAINTS PURGE;
DROP TABLE ShipPosition CASCADE CONSTRAINTS PURGE;
DROP TABLE Container CASCADE CONSTRAINTS PURGE;
DROP TABLE CargoManifestContainer CASCADE CONSTRAINTS PURGE;
DROP TABLE CargoManifestShip CASCADE CONSTRAINTS PURGE;
DROP TABLE Destination CASCADE CONSTRAINTS PURGE;
DROP TABLE PlaceLocation CASCADE CONSTRAINTS PURGE;
DROP TABLE Country CASCADE CONSTRAINTS PURGE;


CREATE TABLE Ship(
mmsiCode VARCHAR(9), 
imoCode VARCHAR(10) NOT NULL UNIQUE,
numberOfEnergyGenerators INTEGER,
generatorType VARCHAR(255),
callSign VARCHAR(255) NOT NULL UNIQUE,
draft FLOAT NOT NULL,
shipName VARCHAR(255) UNIQUE NOT NULL,
vesselTypeId INTEGER NOT NULL,
shipLength FLOAT NOT NULL,
width FLOAT NOT NULL,
shipCapacity FLOAT,

CONSTRAINT pk_Ship PRIMARY KEY(mmsiCode)
);

CREATE TABLE ShipPosition(
shipMmsiCode VARCHAR(9),
baseDateTime TIMESTAMP NOT NULL,
latitude VARCHAR(255) NOT NULL,
longitude VARCHAR(255) NOT NULL,
sog FLOAT NOT NULL,
cog FLOAT NOT NULL, 
heading VARCHAR(255) NOT NULL,
position INTEGER NOT NULL,
transceiver VARCHAR(255) NOT NULL,

CONSTRAINT pk_timeMessage PRIMARY KEY(baseDateTime,shipMmsiCode),
CONSTRAINT fk_Ship_ShipLocation FOREIGN KEY(shipMmsiCode) references Ship(mmsiCode)

);

CREATE TABLE Container(
numberId INTEGER,
checkDigit INTEGER NOT NULL,
isoCode VARCHAR(255) NOT NULL,
maxWeight FLOAT NOT NULL,
payload FLOAT NOT NULL,
tare Float  NOT NULL,
weight FLOAT NOT NULL,
maxWeightPacked FLOAT NOT NULL,
maxVolumePacked FLOAT NOT NULL,
repairRecommendation VARCHAR(255) NOT NULL,
certificate VARCHAR(255) NOT NULL,

CONSTRAINT pk_Container PRIMARY KEY(numberId)
);

CREATE TABLE CargoManifestShip(
id VARCHAR(255),
shipMmsiCode VARCHAR(9) NOT NULL,

CONSTRAINT pk_CargoManifest PRIMARY KEY (id),
CONSTRAINT fk_CargoManifest FOREIGN KEY (shipMmsiCode) references Ship(mmsiCode)
);

CREATE TABLE CargoManifestContainer(
containerNumberId INTEGER,
cargoManifestId VARCHAR(255),
xContainer INTEGER NOT NULL,
yContainer INTEGER NOT NULL, 
zContainer INTEGER NOT NULL, 
grossContainer FLOAT NOT NULL,

CONSTRAINT pk_CargoManifest_Container PRIMARY KEY (containerNumberId, cargoManifestId),

CONSTRAINT fk_CargoManifest_Container FOREIGN KEY(containerNumberId) references Container(numberId),

CONSTRAINT fk_Cargo_Manifest FOREIGN KEY(cargoManifestId) references CargoManifestShip(id)

);

CREATE TABLE Country(
countryName VARCHAR(255),
continent VARCHAR(255),

CONSTRAINT pk_Country PRIMARY KEY(countryName)
);

CREATE TABLE PlaceLocation(
countryName VARCHAR(255),
latitude VARCHAR(255),
longitude VARCHAR (255),

CONSTRAINT pk_PlaceLocation PRIMARY KEY(latitude, longitude),

CONSTRAINT fk_PlaceLocation_Country FOREIGN KEY(countryName)references Country(countryName)

);


CREATE TABLE Destination(
id INTEGER,
destinationName VARCHAR(255) NOT NULL,
placeLocationLongitude VARCHAR(255),
placeLocationLatitude VARCHAR(255),

CONSTRAINT pk_Destination PRIMARY KEY (id, placeLocationLongitude, placeLocationLatitude),

CONSTRAINT fk_Destination_Latitude FOREIGN KEY (placeLocationLatitude, placeLocationLongitude) references PlaceLocation(latitude, longitude)


);
