use ProjetoCBD
use OldData

/*  Popular Subject   */

go
create or alter procedure sp_importSubjects
as

declare @name NVARCHAR(100)
declare getname CURSOR FOR
select TABLE_NAME
from INFORMATION_SCHEMA.TABLES
where TABLE_TYPE = 'BASE TABLE' AND TABLE_CATALOG='OldData'

open getname
fetch next
from getname into @name

while @@FETCH_STATUS = 0
begin

declare @SubjectName nvarchar(10)
set @SubjectName = (select right(@name, charindex('-', reverse(@name)) - 1))

if not exists (select * from ProjetoCBD.dbo.Subject where @SubjectName = SubjectName)
insert into ProjetoCBD.dbo.Subject(
	SubjectName
) values(
	@SubjectName
)

fetch next
from getname into @name

end

close getname
deallocate getname

go



/*  Popular Student Address  */

go
create or alter procedure sp_populateStudentAddress(@auxStudentID int, @auxAddressType nvarchar(1))
as

declare @auxAddressID int
set @auxAddressID = 1 + (select COUNT(*) from ProjetoCBD.dbo.Address)

insert into ProjetoCBD.dbo.Address(AddressType, AddressDetails, isMainAddress) values(@auxAddressType, CONCAT('Morada', CAST(@auxAddressID as nvarchar(10))), 1)
declare @addressID int
set @addressID = SCOPE_IDENTITY()
insert into ProjetoCBD.dbo.Student_Address(AddressID, StudentID) values(@addressID, @auxStudentID)

go

/*  Popular Parent Address  */

go
create or alter procedure sp_populateParentAddress(@auxParentID1 int, @auxParentID2 int, @auxAddressType nvarchar(1), @studentAddress int)
as

declare @auxAddressID int
set @auxAddressID = 1 + (select COUNT(*) from ProjetoCBD.dbo.Address)

declare @addressID int
set @addressID = SCOPE_IDENTITY()
insert into ProjetoCBD.dbo.Parent_Address(AddressID, ParentID) values(@studentAddress, @auxParentID1)
insert into ProjetoCBD.dbo.Parent_Address(AddressID, ParentID) values(@studentAddress, @auxParentID2)


go


/*  Popular School  */

go
create or alter procedure sp_importSchools
as

if not exists (select * from ProjetoCBD.dbo.School where SchoolName = 'Gabriel Pereira' or SchoolName = 'Mousinho da Silveira')
begin
if not exists (select * from ProjetoCBD.dbo.Address where AddressDetails = 'Morada1' or AddressDetails = 'Morada2')
begin
insert into ProjetoCBD.dbo.Address(AddressType, AddressDetails, isMainAddress) values('U', 'Morada1', 1)
insert into ProjetoCBD.dbo.Address(AddressType, AddressDetails, isMainAddress) values('U', 'Morada2', 1)
insert into ProjetoCBD.dbo.School(SchoolName, AddressID) values('Gabriel Pereira', 1)
insert into ProjetoCBD.dbo.School(SchoolName, AddressID) values('Mousinho da Silveira', 2)
end
end
go


/*  Popular Student_Subject   */
go
create or alter procedure sp_populateStudent_Subject(@auxStudent int,@auxtablename nvarchar(50), @calendarYear int, @p1 tinyint,@p2 tinyint,@p3 tinyint)
as

declare @SubjectName nvarchar(10)
set @SubjectName = (select right(@auxtablename, charindex('-', reverse(@auxtablename)) - 1))

if @SubjectName = 'BD'
begin
	if not exists (SELECT * FROM ProjetoCBD.dbo.Student_Subject WHERE StudentID = @auxStudent and SubjectID = 1 and CalendarYear = @calendarYear)
	insert into ProjetoCBD.dbo.Student_Subject(StudentID, SubjectID, CalendarYear,P1,P2,P3) values (@auxStudent,1 , @calendarYear, @p1, @p2, @p3)
end
else
begin
	if @SubjectName = 'CBD'
	begin
	if not exists (SELECT * FROM ProjetoCBD.dbo.Student_Subject WHERE StudentID = @auxStudent and SubjectID = 2 and CalendarYear = @calendarYear)
	insert into ProjetoCBD.dbo.Student_Subject(StudentID, SubjectID, CalendarYear,P1,P2,P3) values (@auxStudent,2 , @calendarYear, @p1, @p2, @p3)
	end
	else
	begin
		if @SubjectName = 'MAT1'
		begin
		if not exists (SELECT * FROM ProjetoCBD.dbo.Student_Subject WHERE StudentID = @auxStudent and SubjectID = 3 and CalendarYear = @calendarYear)
		insert into ProjetoCBD.dbo.Student_Subject(StudentID, SubjectID, CalendarYear,P1,P2,P3) values (@auxStudent,3 , @calendarYear, @p1, @p2, @p3)
		end
	end
end

go


/*  Popular Student e Student_Address*/

go
create or alter procedure sp_importStudents(@tablename nvarchar(50))
as
declare
	@StudentID int,
	@SchoolID nvarchar(2),
	@year int,
	@EmailAddress nvarchar(50),
	@Password nvarchar(50),
	@FirstName nvarchar(50),
	@MiddleName nvarchar(50),
	@LastName nvarchar(50),
	@BirthDate date,
	@address nvarchar(1),
	@Gender nvarchar(1),
	@Familysize nvarchar(3),
	@Reason nvarchar(15),
	@Guardian nvarchar(10),
	@Traveltime tinyint,
	@Studytime tinyint,
	@Failures tinyint,
	@Schoolsupport nvarchar(3),
	@Familysupport  nvarchar(3),
	@Paid nvarchar(3),
	@Activities nvarchar(3),
	@Nursery nvarchar(3),
	@HigherEducation nvarchar(3),
	@Internet nvarchar(3),
	@Romantic nvarchar(3),
	@Familyrelationship tinyint,
	@Freetime tinyint,
	@Goout tinyint,
	@Dalcohol tinyint,
	@Walcohol tinyint,
	@Health tinyint,
	@Absences tinyint,
	@1P tinyint,
	@2P tinyint,
	@3P tinyint

if (EXISTS (select *
from INFORMATION_SCHEMA.TABLES
where TABLE_SCHEMA = 'dbo'
and TABLE_NAME = @tablename))
begin

declare @DynamicSQL nvarchar(4000)
set @DynamicSQL = N'declare curStudent cursor for
	select 
	Student_Number,
	school,
	year,
	Birth_Date,
	address,
	sex,
	famsize,
	Reason,
	Guardian,
	Traveltime,
	Studytime,
	Failures,
	schoolsup,
	famsup,
	Paid,
	Activities,
	Nursery,
	higher,
	Internet,
	Romantic,
	famrel,
	Freetime,
	Goout,
	Dalc,
	Walc,
	Health,
	Absences,
	P1,
	P2,
	P3
	from OldData.[dbo].[' + @TableName + ']'

execute sp_executesql @DynamicSQL

open curStudent
fetch next from curStudent into
	@StudentID,
	@SchoolID,
	@year,
	@BirthDate,
	@address,
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
	@1P,
	@2P,
	@3P

--Dummy Data
declare @number int
set @number = 1 +(select count(*) from ProjetoCBD.dbo.Student)
set @FirstName = 'Nome'
set @MiddleName = 'Aluno'
set @Password = '123456'
declare @auxSchoolID int

while @@FETCH_STATUS = 0
begin

set @LastName = CAST(@number as nvarchar(10))
set @EmailAddress = CONCAT(@FirstName,@MiddleName,@LastName, '@gmail.com')

if @SchoolID = 'GP' set @auxSchoolID = 1
else set @auxSchoolID = 2;

if not exists (select * from ProjetoCBD.dbo.Student where StudentID = @StudentID)
begin 
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
	@StudentID,
	@EmailAddress,
	@Password,
	@FirstName,
	@MiddleName,
	@LastName,
	@BirthDate,
	@Gender,
	convert( bit,
          case @Familysize
            when 'GT3' then 1
            when 'LE3' then 0
			else 0 end),
	@Reason,
	@Guardian,
	@Traveltime,
	@Studytime,
	@Failures,
	CAST(case @Schoolsupport
        when 'yes' then 1
        when 'no'  then 0
        else 0
    end as bit),
	CAST(case @Familysupport
        when 'yes' then 1
        when 'no'  then 0
        else 0
    end as bit),
	CAST(case @Paid
        when 'yes' then 1
        when 'no'  then 0
        else 0
    end as bit),
	CAST(case @Activities
        when 'yes' then 1
        when 'no'  then 0
        else 0
    end as bit),
	CAST(case @Nursery
        when 'yes' then 1
        when 'no'  then 0
        else 0
    end as bit),
	CAST(case @HigherEducation
        when 'yes' then 1
        when 'no'  then 0
        else 0
    end as bit),
	CAST(case @Internet
        when 'yes' then 1
        when 'no'  then 0
        else 0
    end as bit),
	CAST(case @Romantic
        when 'yes' then 1
        when 'no'  then 0
        else 0
    end as bit),
	@Familyrelationship,
	@Freetime,
	@Goout,
	@Dalcohol,
	@Walcohol,
	@Health,
	@Absences,
	@auxSchoolID
)
exec sp_populateStudentAddress @auxStudentID = @StudentID, @auxAddressType = @address
end;

exec sp_populateStudent_Subject @auxStudent = @StudentID, @auxtablename = @tablename, @calendarYear = @year, @p1 = @1P ,@p2 = @2P, @p3 = @3P


Set @number = @number + 1

fetch next from curStudent into
	@StudentID,
	@SchoolID,
	@year,
	@BirthDate,
	@address,
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
	@1P,
	@2P,
	@3P
end

close curStudent
deallocate curStudent
end

go


/*  Popular Parent  Parent_Address */
/*Cria 2 encarregados falsos para cada aluno, pode dar problemas se for corrido mais do que uma vez*/

go
create or alter procedure sp_populateParent(@tablename nvarchar(50))
as
declare
	@StudentID int,
	@address nvarchar(1),
	@EmailAddress nvarchar(50),
	@Password nvarchar(50),
	@FirstName nvarchar(50),
	@MiddleName nvarchar(50),
	@LastName nvarchar(50),
	@Gender nvarchar(1),
	@Pstatus nvarchar(1),

	@Guardian nvarchar(50),
	@Fjob nvarchar(50),
	@Mjob nvarchar(50),
	@Fedu nvarchar(50),
	@Medu nvarchar(50)

	if (EXISTS (select *
	from INFORMATION_SCHEMA.TABLES
	where TABLE_SCHEMA = 'dbo'
	AND TABLE_NAME = @tablename))
	begin

declare @DynamicSQL nvarchar(4000)
set @DynamicSQL = N'declare curStudent cursor for
	select 
	Student_Number,
	address,
	Pstatus,
	Medu,
	Fedu,
	Mjob,
	Fjob,
	Guardian
	from OldData.[dbo].[' + @TableName + ']'

execute sp_executesql @DynamicSQL

open curStudent
fetch next from curStudent into
	@StudentID,
	@address,
	@Pstatus,
	@Medu,
	@Fedu,
	@Mjob,
	@Fjob,
	@Guardian

--aux id do parent
declare @auxParentID int
declare @auxGuardian int
set @auxParentID = 1 +(select count(*) from ProjetoCBD.dbo.Parent)
set @FirstName = 'Parent'
set @MiddleName = 'Number'
set @Password = '123456'

while @@FETCH_STATUS = 0
begin

set @LastName = CAST(@auxParentID as nvarchar(10))
set @EmailAddress = CONCAT(@FirstName,@MiddleName,@LastName, '@gmail.com')

if @Guardian = 'mother' set @auxGuardian = 1
else set @auxGuardian = 0;

if not exists (select * from ProjetoCBD.dbo.Student_Parent where StudentID = @StudentID)
if @auxGuardian = 1
begin;
	if not exists (select * from ProjetoCBD.dbo.Parent where ParentID = @auxParentID)
	begin
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
	) values(
		@EmailAddress,
		@Password,
		@FirstName,
		@MiddleName,
		@LastName,
		'M',
		@Pstatus,
		@Medu,
		@Mjob,
		0
	)

	declare @auxID1 int
	set @auxID1 = SCOPE_IDENTITY()

	insert into ProjetoCBD.dbo.Student_Parent(StudentID, ParentID) values(@StudentID, @auxID1)

	set @auxParentID = @auxParentID + 1
	set @LastName = CAST(@auxParentID as nvarchar(10))
	set @EmailAddress = CONCAT(@FirstName,@MiddleName,@LastName, '@gmail.com')
	end

	if not exists (select * from ProjetoCBD.dbo.Parent where ParentID = @auxParentID)
	begin
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
		'F',
		@Pstatus,
		@Fedu,
		@Fjob,
		1
	)

	declare @auxID2 int
	set @auxID2 = SCOPE_IDENTITY()
	insert into ProjetoCBD.dbo.Student_Parent(StudentID, ParentID) values(@StudentID, @auxID2)
	declare @i int
	set @i = (select addressid from ProjetoCBD.dbo.Student_Address where StudentID = @StudentID)
	execute sp_populateParentAddress @auxParentID1 = @auxID1, @auxParentID2 = @auxID2, @auxAddressType = @address, @studentAddress = @i
	end
end;
else--
begin;
	if not exists (select * from ProjetoCBD.dbo.Parent where ParentID = @auxParentID)
	begin
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
		'M',
		@Pstatus,
		@Medu,
		@Mjob,
		1
	)
	set @auxID1 = SCOPE_IDENTITY()
	insert into ProjetoCBD.dbo.Student_Parent(StudentID, ParentID) values(@StudentID, @auxID1)


	set @auxParentID = @auxParentID + 1
	set @LastName = CAST(@auxParentID as nvarchar(10))
	set @EmailAddress = CONCAT(@FirstName,@MiddleName,@LastName, '@gmail.com')
	end

	if not exists (select * from ProjetoCBD.dbo.Parent where ParentID = @auxParentID)
	begin
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
		'F',
		@Pstatus,
		@Fedu,
		@Fjob,
		0
	);
	set @auxID2 = SCOPE_IDENTITY()
	insert into ProjetoCBD.dbo.Student_Parent(StudentID, ParentID) values(@StudentID, @auxID2)
	set @i = (select addressid from ProjetoCBD.dbo.Student_Address where StudentID = @StudentID)
	execute sp_populateParentAddress @auxParentID1 = @auxID1, @auxParentID2 = @auxID2, @auxAddressType = @address, @studentAddress = @i
	end
end
--
Set @auxParentID = @auxParentID + 1

fetch next from curStudent into
	@StudentID,
	@address,
	@Pstatus,
	@Medu,
	@Fedu,
	@Mjob,
	@Fjob,
	@Guardian
end

close curStudent
deallocate curStudent
end

go



/*  Popular Subject_School   */


go
create or alter procedure sp_populateSubject_School
as

declare @id int
declare getid CURSOR FOR
select SchoolID from ProjetoCBD.dbo.School

open getid
fetch next
from getid into @id
while @@FETCH_STATUS = 0

begin

if not exists (SELECT * FROM ProjetoCBD.dbo.Subject_School WHERE SubjectID = 1 and SchoolID = @id)
insert into ProjetoCBD.dbo.Subject_School(SubjectID, SchoolID) values (1, @id)
if not exists (SELECT * FROM ProjetoCBD.dbo.Subject_School WHERE SubjectID = 2 and SchoolID = @id)
insert into ProjetoCBD.dbo.Subject_School(SubjectID, SchoolID) values (2, @id)
if not exists (SELECT * FROM ProjetoCBD.dbo.Subject_School WHERE SubjectID = 3 and SchoolID = @id)
insert into ProjetoCBD.dbo.Subject_School(SubjectID, SchoolID) values (3, @id)


fetch next
from getid into @id

end

close getid
deallocate getid

go


exec sp_importSubjects
exec sp_importSchools

exec sp_importStudents @tablename = '2017 student-BD'
exec sp_importStudents @tablename = '2017 student-CBD'
exec sp_importStudents @tablename = '2017 student-MAT1'
exec sp_importStudents @tablename = '2018 student-BD'
exec sp_importStudents @tablename = '2018 student-CBD'
exec sp_importStudents @tablename = '2018 student-MAT1'
exec sp_importStudents @tablename = '2019 student-BD'
exec sp_importStudents @tablename = '2019 student-CBD'
exec sp_importStudents @tablename = '2019 student-MAT1'

exec sp_populateParent @tablename = '2017 student-BD'
exec sp_populateParent @tablename = '2017 student-CBD'
exec sp_populateParent @tablename = '2017 student-MAT1'
exec sp_populateParent @tablename = '2018 student-BD'
exec sp_populateParent @tablename = '2018 student-CBD'
exec sp_populateParent @tablename = '2018 student-MAT1'
exec sp_populateParent @tablename = '2019 student-BD'
exec sp_populateParent @tablename = '2019 student-CBD'
exec sp_populateParent @tablename = '2019 student-MAT1'

exec sp_populateSubject_School