-- Setup of Persons table

CREATE TABLE Persons (
    PersonID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
    PersonName varchar(255) NOT NULL,
    PersonAddress varchar(255) NOT NULL,
	HairID int NOT NULL,
	Favorite bit,
	PersonTlf varchar(50),
	Interest1 int,
	Interest2 int,
	Interest3 int,
	Interest4 int
);

GO

-- Setup of Haircolors table
CREATE TABLE Haircolors (
    HairColID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	HairColor varchar(20)
);

GO
-- Setup of Interests table
CREATE TABLE Interests (
    IntrestID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	Interests varchar(100)
);

GO

-- Sets foreign key constraints on interests 1-4
ALTER TABLE Persons
	ADD CONSTRAINT [FK_Interest1] FOREIGN KEY (Interest1) REFERENCES Interests (IntrestID);
GO

ALTER TABLE Persons
	ADD CONSTRAINT [FK_Interest2] FOREIGN KEY (Interest2) REFERENCES Interests (IntrestID);
GO

ALTER TABLE Persons
	ADD CONSTRAINT [FK_Interest3] FOREIGN KEY (Interest3) REFERENCES Interests (IntrestID);
GO

ALTER TABLE Persons
	ADD CONSTRAINT [FK_Interest4] FOREIGN KEY (Interest4) REFERENCES Interests (IntrestID);
GO

-- Adds data to Interests table
INSERT INTO Interests (Interests)
VALUES ('Hiking'),('Dancing'),('Gaming'),('Sports'),('Art'),('Music'),('Kitesurfing'),('Farming');

GO

-- Adds data to Persons table
--
-- Person 1
INSERT INTO Persons (PersonName, PersonAddress, HairId, PersonTlf, Favorite, Interest1, Interest2) VALUES
(('Jorn Tena'),
('Cameroon Avenue 25'),
(SELECT HairColID from Haircolors WHERE Haircolor='Blond'),
('555-2164-1221'),
(1),
(SELECT IntrestID from Interests WHERE Interests='Sports'),
(SELECT IntrestID from Interests WHERE Interests='Hiking'));

GO
-- Person 2
INSERT INTO Persons (PersonName, PersonAddress, HairId, PersonTlf, Favorite, Interest1, Interest2, Interest3) VALUES
(('Bob Ronson'),
('Camel Drive 11'),
(SELECT HairColID from Haircolors WHERE Haircolor='Black'),
('555-587-5412'),
(0),
(SELECT IntrestID from Interests WHERE Interests='Dancing'),
(SELECT IntrestID from Interests WHERE Interests='Music'),
(SELECT IntrestID from Interests WHERE Interests='Kitesurfing'));

GO

-- Person 3
INSERT INTO Persons (PersonName, PersonAddress, HairId, PersonTlf, Favorite, Interest1, Interest2) VALUES
(('Christina Varru'),
('Typhoon Street 55a'),
(SELECT HairColID from Haircolors WHERE Haircolor='Red'),
('555-1121-9168'),
(0),
(SELECT IntrestID from Interests WHERE Interests='Art'),
(SELECT IntrestID from Interests WHERE Interests='Sports'));

GO

-- Person 4
INSERT INTO Persons (PersonName, PersonAddress, HairId, PersonTlf, Favorite, Interest1, Interest2, Interest3) VALUES
(('Regina Thomson'),
('Carrenbar Valley 2'),
(SELECT HairColID from Haircolors WHERE Haircolor='Brown'),
('555-6981-2287'),
(1),
(SELECT IntrestID from Interests WHERE Interests='Farming'),
(SELECT IntrestID from Interests WHERE Interests='Art'),
(SELECT IntrestID from Interests WHERE Interests='Gaming'));

GO

-- Person 5
INSERT INTO Persons (PersonName, PersonAddress, HairId, PersonTlf, Favorite, Interest1, Interest2, Interest3, Interest4) VALUES
(('Tom Barreston'),
('Donkeyvale Street 23'),
(SELECT HairColID from Haircolors WHERE Haircolor='Grey'),
('555-6981-9952'),
(0),
(SELECT IntrestID from Interests WHERE Interests='Dancing'),
(SELECT IntrestID from Interests WHERE Interests='Kitesurfing'),
(SELECT IntrestID from Interests WHERE Interests='Music'),
(SELECT IntrestID from Interests WHERE Interests='Hiking'));

Go

--TRUNCATE TABLE Persons 

SELECT * from Persons where PersonID = 2;