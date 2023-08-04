--TESTS FOR DESTINATION AND THEIR RELATIONS

INSERT INTO country(countryName, continent)
values('Portugal', 'European');

INSERT INTO placeLocation(latitude, longitude, countryName)
values('50', '120', 'Spain');

--expected: fail, because itsn't exist a Country Spain inserted in the database 


INSERT INTO placeLocation(latitude, longitude, countryName)
values('50', '120', 'Portugal');

--expected: pass, because Country Portugal it's already inserted in the database 

INSERT INTO Destination(id, destinationName, placeLocationLatitude, placeLocationLongitude)
values(5, 'Spain', '55', '110');

--expected: fail, because Place Location Netherland don't exist in the database

INSERT INTO Destination(id, destinationName, placeLocationLatitude, placeLocationLongitude)
values(5, 'Spain', '50', '120');

--expected: pass, because Place Location Portugal exist in the database 

DELETE FROM country WHERE countryName = 'Portugal';
DELETE FROM placeLocation WHERE countryName = 'Portugal';

--expected: fail, because the country name is a foreign key in PlaceLocation, we cannot delete a country that is referenced in a PlaceLocation without first deleting the PlaceLocation so as not to violate the principle of foreign keys

DELETE FROM Destination WHERE id = 5;
DELETE FROM placeLocation WHERE countryName = 'Portugal';
DELETE FROM country WHERE countryName = 'Portugal';

--expected: pass, as we have already deleted the target associated with a particular PlaceLocation and therefore deleted the PlaceLocation associated with a parent the test will be successful because we delete the information so as not to violate the restrictions of a foreign key