--drop database if exists OldData

--create database OldData

use OldData


create table Student(
	StudentID int not null identity(1,1),
	School nvarchar(2),
	Gender nvarchar(1),
	BirthDate date,
	Address nvarchar(1),
	Famsize nvarchar(3),
	Pstatus nvarchar(1),
	Medu tinyint,
	Fedu tinyint,
	Mjob nvarchar(50),
	Fjob nvarchar(50),
	Reason nvarchar(50),
	Guardian nvarchar(50),
	Traveltime tinyint,
	Studytime tinyint,
	Failures tinyint,
	Schoolsupport bit,
	Familysupport bit,
	Paid bit,
	Activities bit,
	Nursery bit,
	HigherEducation bit,
	Internet bit,
	Romantic bit,
	Familyrelationship tinyint,
	Freetime tinyint,
	Goout tinyint,
	Dalcohol tinyint,
	Walcohol tinyint,
	Health tinyint,
	Absences tinyint,
	P1 tinyint,
	P2 tinyint,
	P3 tinyint,
	primary key (StudentID)
);