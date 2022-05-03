use ProjetoCBD

--Criar e adicionar aluno
/*Invalid objact name? A procedure é criada e funciona quando é chamada. Mas dá erro quando se faz run da query toda? A SP a seguir a esta é identica e não dá problema*/
go
create or alter procedure sp_createStudent(
	@SchoolID int,
	@EmailAddress nvarchar(50),
	@Password nvarchar(50),
	@FirstName nvarchar(50),
	@MiddleName nvarchar(50),
	@LastName nvarchar(50),
	@BirthDate date,
	@Gender nvarchar(1),
	@Familysize nvarchar(3) = null,
	@Reason nvarchar(15) = null,
	@Guardian nvarchar(10) = null,
	@Traveltime tinyint = null,
	@Studytime tinyint = null,
	@Failures tinyint = null,
	@Schoolsupport nvarchar(3) = null,
	@Familysupport  nvarchar(3) = null,
	@Paid nvarchar(3) = null,
	@Activities nvarchar(3) = null,
	@Nursery nvarchar(3) = null,
	@HigherEducation nvarchar(3) = null,
	@Internet nvarchar(3) = null,
	@Romantic nvarchar(3) = null,
	@Familyrelationship tinyint = null,
	@Freetime tinyint = null,
	@Goout tinyint = null,
	@Dalcohol tinyint = null,
	@Walcohol tinyint = null,
	@Health tinyint = null,
	@Absences tinyint = null
)
as
begin

declare @auxID int
set @auxID = 1 + (select count (StudentID) from ProjetoCBD.dbo.Student)

if not exists (select * from ProjetoCBD.dbo.Student where EmailAddress = @EmailAddress)
insert into ProjetoCBD.dbo.Student(
	StudentID,
	EmailAddress,
	Password,
	FirstName,
	MiddleName,
	LastName,
	BirthDate,
	Gender,
	Familysize,
	Reason,
	Guardian,
	Traveltime,
	Studytime,
	Failures,
	Schoolsupport,
	Familysupport,
	Paid,
	Activities,
	Nursery,
	HigherEducation,
	Internet,
	Romantic,
	Familyrelationship,
	Freetime,
	Goout,
	Dalcohol,
	Walcohol,
	Health,
	Absences,
	SchoolID
)values(
	@auxID,
	@EmailAddress,
	@Password,
	@FirstName,
	@MiddleName,
	@LastName,
	@BirthDate,
	@Gender,
	@Familysize,
	@Reason,
	@Guardian,
	@Traveltime,
	@Studytime,
	@Failures,
	@Schoolsupport,
	@Familysupport,
	@Paid,
	@Activities,
	@Nursery,
	@HigherEducation,
	@Internet,
	@Romantic,
	@Familyrelationship,
	@Freetime,
	@Goout,
	@Dalcohol,
	@Walcohol,
	@Health,
	@Absences,
	@SchoolID
)
end
go

exec sp_createStudent @SchoolID = 1,
	@EmailAddress = 'abc@hotmail.com',
	@Password = 'abcpassword',
	@FirstName = 'a',
	@MiddleName = 'b',
	@LastName = 'c',
	@BirthDate = '2008-03-22',
	@Gender = 'M'


--Criar e adicionar encarregado
go
create or alter procedure sp_createGuardian(
	@EmailAddress nvarchar(50),
	@Password nvarchar(50),
	@FirstName nvarchar(50),
	@MiddleName nvarchar(50),
	@LastName nvarchar(50),
	@Gender nvarchar(1),
	@Pstatus nvarchar(1) = null,
	@Education tinyint = null,
	@Job nvarchar(50) = null,
	@isGuardian bit
)
as
begin

declare @auxID int
set @auxID = 1 + (select count (ParentID) from ProjetoCBD.dbo.Parent)

if not exists (select * from ProjetoCBD.dbo.Parent where EmailAddress = @EmailAddress)
insert into ProjetoCBD.dbo.Parent(
	EmailAddress,
	Password,
	FirstName,
	MiddleName,
	LastName,
	Gender,
	Pstatus,
	Education,
	Job,
	isGuardian
)values(
	@EmailAddress,
	@Password,
	@FirstName,
	@MiddleName,
	@LastName,
	@Gender,
	@Pstatus,
	@Education,
	@Job,
	@isGuardian
)
end
go

exec sp_createGuardian
	@EmailAddress = 'abc@hotmail.com',
	@Password = 'abc',
	@FirstName = 'a',
	@MiddleName = 'b',
	@LastName = 'c',
	@Pstatus = 'T',
	@Gender = 'M',
	@isGuardian = 1


-- Criar parentesco entre aluno e encarregado
go
create or alter procedure sp_createRelationship(
	@StudentID int,
	@ParentID int
)
as
begin

if exists (select * from ProjetoCBD.dbo.Student where StudentID = @StudentID)
if exists (select * from ProjetoCBD.dbo.Parent where ParentID = @ParentID)
if not exists (select * from ProjetoCBD.dbo.Student_Parent where StudentID = @StudentID and ParentID = @ParentID)
insert into Student_Parent(StudentID, ParentID) values (@StudentID, @ParentID)

end
go

--criar morada
go
create or alter procedure sp_createAddress(
	@AddressDetails nvarchar(50),
	@AddressType nvarchar(1) = null,
	@isMainAddress bit
)
as
begin

if not exists (select * from ProjetoCBD.dbo.Address where AddressDetails = @AddressDetails)
insert into Address(AddressDetails, isMainAddress, AddressType) values (@AddressDetails, @isMainAddress, @AddressType)

end
go

--Criar escola
go
create or alter procedure sp_createSchool(
	@SchoolName nvarchar(50) = null,
	@AddressID int
)
as
begin

if exists (select * from ProjetoCBD.dbo.Address where AddressID = @AddressID)
if not exists (select * from ProjetoCBD.dbo.School where SchoolID = @SchoolName and AddressID = @AddressID)
insert into School(AddressID, SchoolName) values (@AddressID, @SchoolName)

end
go

--Criar Disciplina
go
create or alter procedure sp_createSubject(
	@SubjectName nvarchar(50)
)
as
begin

if not exists (select * from ProjetoCBD.dbo.Subject where SubjectName = @SubjectName)
insert into Subject(SubjectName) values (@SubjectName)

end
go

--Adicionar Disciplina a uma Escola
go
create or alter procedure sp_teachSubject(
	@SubjectID int,
	@SchoolID int
)
as
begin

if not exists (select * from ProjetoCBD.dbo.Subject_School where SubjectID = @SubjectID and SchoolID = @SchoolID)
insert into Subject_School(SchoolID, SubjectID) values (@SchoolID, @SubjectID)

end
go

--Adicionar morada a aluno ou encarregado
go
create or alter procedure sp_addStudentAddress(
	@StudentID int,
	@AddressID int
)
as
begin

if not exists (select * from ProjetoCBD.dbo.Student_Address where StudentID = @StudentID and AddressID = @AddressID)
insert into Student_Address(AddressID, StudentID) values (@AddressID, @StudentID)

end
go

go
create or alter procedure sp_addParentAddress(
	@ParentID int,
	@AddressID int
)
as
begin

if not exists (select * from ProjetoCBD.dbo.Parent_Address where ParentID = @ParentID and AddressID = @AddressID)
insert into Parent_Address(AddressID, ParentID) values (@AddressID, @ParentID)

end
go

use ProjetoCBD;
go
--Aluno/Pai aceder as notas
CREATE or alter FUNCTION viewGrades
(   
    @StudentID INT
)
RETURNS TABLE 
AS
RETURN 
(
    select * from Student_Subject where Student_Subject.StudentID = @StudentID
)
GO

--Notas do aluno com id=1
select CONCAT(Student.FirstName, ' ',Student.MiddleName,' ',Student.LastName) as 'Student Name', SubjectName as 'Subject', CalendarYear as 'Year' , P1 as '1º Period', P2 as '2º Period', P3 as '3º Period' from viewGrades(1) as t
inner join Student
on Student.StudentID = t.StudentID
inner join Subject
on Subject.SubjectID = t.SubjectID



--COMPLEMENTAR

--Inscrever aluno numa disciplina
/*Mesma situação que a primeira SP. SP é criada e funciona como pretendida, mas da erro na compilação*/
go
create or alter procedure sp_enrollStudent(
	@StudentID int,
	@SubjectID int,
	@CalendarYear int
)
as
begin

if exists (select * from ProjetoCBD.dbo.Student where StudentID = @StudentID)
if exists (select * from ProjetoCBD.dbo.Subject where SubjectID = @SubjectID)
if not exists (select * from ProjetoCBD.dbo.Student_Subject where StudentID = @StudentID and SubjectID = @SubjectID and CalendarYear = @CalendarYear)
insert into Student_Subject(StudentID, SubjectID, CalendarYear) values (@StudentID, @SubjectID, @CalendarYear)

end
go


if not exists (select * from ProjetoCBD.dbo.Student where StudentID = 99996)
insert into Student (StudentID , EmailAddress, FirstName, MiddleName, LastName, Password, BirthDate, Gender, SchoolID)
values (99996, 'miguelribeiro@gmail.com', 'Miguel', 'Silva', 'Ribeiro', 'palavra123', '2008-09-18', 'M', 1)

exec sp_enrollStudent @StudentID = 99996, @SubjectID = 1, @CalendarYear = 2017


--Alterar nota de um aluno numa disciplina
go
create or alter procedure sp_updateGrade(
	@StudentID int,
	@SubjectID int,
	@CalendarYear int,
	@Period tinyint,
	@Grade tinyint
)
as
begin

if @Period = 1
begin
	update Student_Subject
	set P1 = @Grade 
	where StudentID = @StudentID and SubjectID = @SubjectID and CalendarYear = @CalendarYear
end
if @Period = 2
begin
	update Student_Subject
	set P2 = @Grade 
	where StudentID = @StudentID and SubjectID = @SubjectID and CalendarYear = @CalendarYear
end
if @Period = 3
begin
	update Student_Subject
	set P3 = @Grade 
	where StudentID = @StudentID and SubjectID = @SubjectID and CalendarYear = @CalendarYear
end

end
go

exec sp_updateGrade @StudentID = 99996, @SubjectID = 1, @CalendarYear = 2017, @Period = 1, @Grade= 20
exec sp_updateGrade @StudentID = 99996, @SubjectID = 1, @CalendarYear = 2017, @Period = 2, @Grade = 20
exec sp_updateGrade @StudentID = 99996, @SubjectID = 1, @CalendarYear = 2017, @Period = 3, @Grade = 20