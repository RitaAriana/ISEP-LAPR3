DROP TABLE Ship CASCADE CONSTRAINTS PURGE;
DROP TABLE ShipLocation CASCADE CONSTRAINTS PURGE;
DROP TABLE Container CASCADE CONSTRAINTS PURGE;
DROP TABLE Ports CASCADE CONSTRAINTS PURGE;
DROP TABLE PlaceLocation CASCADE CONSTRAINTS PURGE;
DROP TABLE Country CASCADE CONSTRAINTS PURGE;
DROP TABLE Ship_Port CASCADE CONSTRAINTS PURGE;
DROP TABLE CargoManifestLoad CASCADE CONSTRAINTS PURGE;
DROP TABLE Phases CASCADE CONSTRAINTS PURGE;
DROP TABLE CargoManifestUnload CASCADE CONSTRAINTS PURGE;
DROP TABLE CargoManifestContainer CASCADE CONSTRAINTS PURGE;
DROP TABLE Warehouse CASCADE CONSTRAINTS PURGE;
DROP TABLE Warehouse_Truck CASCADE CONSTRAINTS PURGE;
DROP TABLE Role CASCADE CONSTRAINTS PURGE;
DROP TABLE User CASCADE CONSTRAINTS PURGE;
DROP TABLE AuditTrails CASCADE CONSTRAINTS PURGE;


CREATE TABLE Role(
id INTEGER,
designation VARCHAR(255) UNIQUE,

CONSTRAINT pk_Role PRIMARY KEY (id)
);

CREATE TABLE User(
username VARCHAR(255),
password VARCHAR(255),
roleId INTEGER,

CONSTRAINT pk_User PRIMARY KEY (username),
CONSTRAINT fk_User_Role FOREIGN KEY (roleId) references Role(id)

);

CREATE TABLE Operations(
id INTEGER,
operationName VARCHAR(6) UNIQUE,

CONSTRAINT pk_Operations PRIMARY KEY (id)

);

CREATE TABLE AuditTrails(
userUserName VARCHAR(255),
operationsId INTEGER,
cargoManifestContainerNumberId INTEGER,
cargoManifestContainerCargoManifestLoadId INTEGER,
id INTEGER,

CONSTRAINT pk_AuditTrails PRIMARY KEY (id),
CONSTRAINT fk_AuditTrails_CargoManifestContainer FOREIGN KEY (cargoManifestContainerNumberId, cargoManifestContainerCargoManifestLoadId) REFERENCES CargoManifestContainer(containerNumberId, cargoManifestLoadId),
CONSTRAINT fk_AuditTrails_Operations FOREIGN KEY (operationsId) REFERENCES Operations(id),
CONSTRAINT fk_AuditTrails_User FOREIGN KEY (userUserName) REFERENCES User(username)

);


CREATE TABLE Ship(
mmsiCode VARCHAR(9),
imoCode VARCHAR(10) NOT NULL UNIQUE,
numberEnergyGenerators INTEGER,
generatorOutput float(10),
callSign VARCHAR(255) NOT NULL UNIQUE,
draft FLOAT NOT NULL,
shipName VARCHAR(255) UNIQUE NOT NULL,
vesselTypeId INTEGER NOT NULL,
shipLength FLOAT NOT NULL,
width FLOAT NOT NULL,
cargo VARCHAR(255),
capacity FLOAT(10),

CONSTRAINT pk_Ship PRIMARY KEY(mmsiCode)
);

CREATE TABLE ShipLocation(
shipMmsiCode VARCHAR(9),
baseDateTime TIMESTAMP NOT NULL,
latitude VARCHAR(255) NOT NULL,
longitude VARCHAR(255) NOT NULL,
sog FLOAT NOT NULL,
cog FLOAT NOT NULL,
heading VARCHAR(255) NOT NULL,
position VARCHAR(255) NOT NULL,
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
CREATE TABLE Ports(
id INTEGER,
name VARCHAR(255) NOT NULL,
placeLocationLatitude VARCHAR(255),
placeLocationLongitude VARCHAR(255),

CONSTRAINT pk_Port PRIMARY KEY (id),

CONSTRAINT fk_Port_PlaceLocation FOREIGN KEY (placeLocationLatitude, placeLocationLongitude) references PlaceLocation(latitude, longitude)

);

CREATE TABLE Ship_Port(
portId INTEGER,
shipMmsiCode VARCHAR(9),

CONSTRAINT pk_ShipPort PRIMARY KEY (portId, shipMmsiCode),
CONSTRAINT fk_Port FOREIGN KEY (portId) references Ports(id),
CONSTRAINT fk_Ship FOREIGN KEY (shipMmsiCode) references Ship(mmsiCode)

);


CREATE TABLE CargoManifestLoad(
id INTEGER,
shipMmsiCode VARCHAR(9) NOT NULL,
portId INTEGER,
isConcluded INTEGER,

CONSTRAINT pk_CargoManifestLoad PRIMARY KEY (id),
CONSTRAINT fk_CargoManifestLoad_Ship FOREIGN KEY (shipMmsiCode) references Ship(mmsiCode),
CONSTRAINT fk_CargoManifestLoad_Port FOREIGN KEY (portId) references Ports(id)
);

CREATE TABLE Phases(
cargoManifestLoadId INTEGER,
id INTEGER,
origin VARCHAR(255) NOT NULL,
destination VARCHAR(255) NOT NULL,
expectedDepartureDate TIMESTAMP NOT NULL,
expectedArrivalDate TIMESTAMP NOT NULL,
realDepartureDate TIMESTAMP NOT NULL,
realArrivalDate TIMESTAMP NOT NULL,

CONSTRAINT pk_Phases PRIMARY KEY (cargoManifestLoadId, id),
CONSTRAINT fk_Phases_CargoManifestLoad FOREIGN KEY (cargoManifestLoadId) references CargoManifestLoad(id)

);

CREATE TABLE CargoManifestUnload(
Id INTEGER,
phasesId INTEGER NOT NULL,
portId INTEGER NOT NULL,
PhasesCargoManifestLoadId INTEGER NOT NULL,

CONSTRAINT pk_CargoManifestUnload PRIMARY KEY (Id),
CONSTRAINT fk_CargoManifestUnload_Phases FOREIGN KEY (phasesCargoManifestLoadId, phasesId) references Phases(cargoManifestLoadId, id)
);


CREATE TABLE CargoManifestContainer(
containerNumberId INTEGER,
cargoManifestLoadId INTEGER,
completedPhase INTEGER,
xContainer INTEGER NOT NULL,
yContainer INTEGER NOT NULL,
zContainer INTEGER NOT NULL,
grossContainer FLOAT NOT NULL,
PhasesId INTEGER NOT NULL,
PhasesCargoManifestLoadId INTEGER NOT NULL,
CargoManifestUnloadId INTEGER,

CONSTRAINT pk_CargoManifest_Container PRIMARY KEY (containerNumberId, cargoManifestLoadId),

CONSTRAINT fk_CargoManifest_Container FOREIGN KEY(containerNumberId) references Container(numberId),

CONSTRAINT fk_CargoManifest_Load FOREIGN KEY(PhasesCargoManifestLoadId) references CargoManifestLoad(id),

CONSTRAINT fk_CargoManifest_Unload FOREIGN KEY(CargoManifestUnloadId) references CargoManifestUnload(Id)
);

CREATE TABLE Warehouse(
id INTEGER,
name VARCHAR(255),
placeLocationLatitude VARCHAR(255),
placeLocationLongitude VARCHAR(255),

CONSTRAINT pk_Warehouse PRIMARY KEY (id),
CONSTRAINT fk_Warehouse_PlaceLocation FOREIGN KEY (placeLocationLatitude, placeLocationLongitude) references PlaceLocation(latitude, longitude)

);

CREATE TABLE Warehouse_Truck(
warehouseId INTEGER,

CONSTRAINT pk_WarehouseTruck PRIMARY KEY (warehouseId),
CONSTRAINT fk_WarehouseTruck_Warehouse FOREIGN KEY (warehouseId) references Warehouse(id)
);
