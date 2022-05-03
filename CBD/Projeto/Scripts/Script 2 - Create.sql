--drop database if exists ProjetoCBD
--create database ProjetoCBD

use ProjetoCBD


drop table if exists Student_Parent
drop table if exists Student_Subject
drop table if exists Subject_School
drop table if exists Student_Address
drop table if exists Parent_Address

drop table if exists Student
drop table if exists School
drop table if exists Subject
drop table if exists Parent
drop table if exists Address

drop table if exists Error
drop table if exists SendErrorMessage

										--Tabelas
--Aluno
create table Student(
	StudentID int not null,
	EmailAddress nvarchar(50) unique not null,
	Password nvarchar(50) not null,
	FirstName nvarchar(50) not null,
	MiddleName nvarchar(50),
	LastName nvarchar(50) not null,
	BirthDate date not null,
	Gender nvarchar(1) not null,

	Familysize bit,
	Reason nvarchar(15),
	Guardian nvarchar(10),
	Traveltime tinyint,
	Studytime tinyint,
	Failures tinyint,
	Schoolsupport bit,
	Familysupport  bit,
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

	SchoolID int not null,
	primary key (StudentID)
);

--Guardião
create table Parent(
	ParentID int not null identity(1,1),
	EmailAddress nvarchar(50) unique not null,
	Password nvarchar(50) not null,
	FirstName nvarchar(50) not null,
	MiddleName nvarchar(50),
	LastName nvarchar(50) not null,
	Gender nvarchar(1) not null,

	Pstatus nvarchar(1),
	Education tinyint,
	Job nvarchar(50),

	isGuardian bit not null,
	primary key (ParentID)
);

--Morada
create table Address (
	AddressID int not null identity(1,1),
	AddressType nvarchar(1),
	AddressDetails nvarchar(50) unique not null,
	isMainAddress bit not null,
	primary key (AddressID)
);

--Disciplina
create table Subject(
	SubjectID int not null identity(1,1),
	SubjectName nvarchar(50) not null,
	primary key (SubjectID)
);


--Escola
create table School(
	SchoolID int not null identity(1,1),
	SchoolName nvarchar(50),
	AddressID int not null,
	primary key (SchoolID)
);

--Relação Aluno-Guardião
create table Student_Parent(
	StudentID int,
	ParentID int,
	primary key (StudentID, ParentID)
);

--Relação Aluno-Disciplina
create table Student_Subject(
	StudentID int not null,
	SubjectID int not null,
	CalendarYear int not null,
	P1 tinyint,
	P2 tinyint,
	P3 tinyint,
	primary key (StudentID, SubjectID, CalendarYear)
);

--Relação Disciplina-Escola
create table Subject_School(
	SubjectID int,
	SchoolID int,
	primary key (SubjectID, SchoolID)
);

--Relação Aluno-Morada
create table Student_Address(
	StudentID int not null,
	AddressID int not null,
	primary key (StudentID, AddressID)
);

--Relação Guardião-Morada
create table Parent_Address(
	ParentID int not null,
	AddressID int not null,
	primary key (ParentID, AddressID)
);

-- Tabelas relacionadas com o tratamento de erros
create table Error (
	ErrorID int not null identity(1,1),
	ErrorMessage varchar(400),
	primary key (ErrorID)
);

create table SendErrorMessage (
	ErrorMessageID int not null identity(1,1),
	UserName nvarchar(50),
	Message varchar(400),
	ErrorDateTime date,
	primary key (ErrorMessageID)
);

										--Foreign Keys


--Aluno-Guardião
alter table Student_Parent 
	add constraint StudentParent_Student_FK
	foreign key (StudentID)
	references Student (StudentID)
go

alter table Student_Parent 
	add constraint StudentParent_Parent_FK
	foreign key (ParentID)
	references Parent (ParentID)
go

--Aluno-Disciplina
alter table Student_Subject 
	add constraint StudentSubject_Student_FK
	foreign key (StudentID)
	references Student (StudentID)
go

alter table Student_Subject 
	add constraint StudentSubject_Parent_FK
	foreign key (SubjectID)
	references Subject (SubjectID)
go

--Disciplina-Escola
alter table Subject_School 
	add constraint SubjectSchool_Subject_FK
	foreign key (SubjectID)
	references Subject (SubjectID)
go

alter table Subject_School
	add constraint SubjectSchool_School_FK
	foreign key (SchoolID)
	references School (SchoolID)
go

--Aluno-Morada
alter table Student_Address
	add constraint StudentAddress_Student_FK
	foreign key (StudentID)
	references Student (StudentID)
go

alter table Student_Address
	add constraint StudentAddressd_Address_FK
	foreign key (AddressID)
	references Address (AddressID)
go

--Guardião-Morada
alter table Parent_Address
	add constraint ParentAddress_Parent_FK
	foreign key (ParentID)
	references Parent (ParentID)
go

alter table Parent_Address
	add constraint ParentAddressd_Address_FK
	foreign key (AddressID)
	references Address (AddressID)
go

--Aluno
alter table Student
	add constraint StudentSchool_School_FK
	foreign key (SchoolID)
	references School (SchoolID)
go

--Escola-
alter table School
	add constraint SchoolAddress_Address_FK
	foreign key (AddressID)
	references Address (AddressID)
go