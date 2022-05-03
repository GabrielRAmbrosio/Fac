use OldData
use ProjetoCBD

/* ------------------------------------------------------------------------------------------------ OLDDATA */

--Toda a informação da bd OldData
GO
CREATE OR ALTER VIEW oldDataInfo
AS
	(SELECT * FROM OldData.dbo.[2017 student-BD]
	UNION
	SELECT * FROM OldData.dbo.[2017 student-CBD]
	UNION
	SELECT * FROM OldData.dbo.[2017 student-MAT1]
	UNION
	SELECT * FROM OldData.dbo.[2018 student-BD]
	UNION
	SELECT * FROM OldData.dbo.[2018 student-CBD]
	UNION
	SELECT * FROM OldData.dbo.[2018 student-MAT1]
	UNION
	SELECT * FROM OldData.dbo.[2019 student-BD]
	UNION
	SELECT * FROM OldData.dbo.[2019 student-CBD]
	UNION
	SELECT * FROM OldData.dbo.[2019 student-MAT1])
go

select * from oldDataInfo;

--Queries auxiliares

--Nome das tabelas da bd OldData
select distinct TABLE_NAME from INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE'

--Media dos alunos no ano especifico de uma disciplina especifica

--2017
select Student_Number, year, ((cast(P1 + P2 + P3 as decimal))/3) as 'Average BD' from (SELECT * FROM OldData.dbo.[2017 student-BD]) as t
order by Student_Number

select Student_Number, year, ((cast(P1 + P2 + P3 as decimal))/3) as 'Average CBD' from (SELECT * FROM OldData.dbo.[2017 student-CBD]) as t
order by Student_Number

select Student_Number, year, ((cast(P1 + P2 + P3 as decimal))/3) as 'Average MAT1' from (SELECT * FROM OldData.dbo.[2017 student-MAT1]) as t
order by Student_Number

--2018
select Student_Number, year, ((cast(P1 + P2 + P3 as decimal))/3) as 'Average BD' from (SELECT * FROM OldData.dbo.[2018 student-BD]) as t
order by Student_Number

select Student_Number, year, ((cast(P1 + P2 + P3 as decimal))/3) as 'Average CBD' from (SELECT * FROM OldData.dbo.[2018 student-CBD]) as t
order by Student_Number

select Student_Number, year, ((cast(P1 + P2 + P3 as decimal))/3) as 'Average MAT1' from (SELECT * FROM OldData.dbo.[2018 student-MAT1]) as t
order by Student_Number

--2019
select Student_Number, year, ((cast(P1 + P2 + P3 as decimal))/3) as 'Average BD' from (SELECT * FROM OldData.dbo.[2019 student-BD]) as t
order by Student_Number

select Student_Number, year, ((cast(P1 + P2 + P3 as decimal))/3) as 'Average CBD' from (SELECT * FROM OldData.dbo.[2019 student-CBD]) as t
order by Student_Number

select Student_Number, year, ((cast(P1 + P2 + P3 as decimal))/3) as 'Average MAT1' from (SELECT * FROM OldData.dbo.[2019 student-MAT1]) as t
order by Student_Number


--Media dos alunos por ano por disciplina
select  Student_Number, year, ((cast(P1 + P2 + P3 as decimal))/3) as 'Average' from oldDataInfo as tabela
order by year


--Media de um ano por disciplina
select year, AVG(notas) as 'BD Average' from (select year, ((cast(P1 + P2 + P3 as decimal))/3) as notas from (SELECT * FROM OldData.dbo.[2017 student-BD]) as t) as p group by year
select year, AVG(notas) as 'CBD Average' from (select year, ((cast(P1 + P2 + P3 as decimal))/3) as notas from (SELECT * FROM OldData.dbo.[2017 student-CBD]) as t) as p group by year
select year, AVG(notas) as 'MAT1 Average' from (select year, ((cast(P1 + P2 + P3 as decimal))/3) as notas from (SELECT * FROM OldData.dbo.[2017 student-MAT1]) as t) as p group by year

select year, AVG(notas) as 'BD Average' from (select year, ((cast(P1 + P2 + P3 as decimal))/3) as notas from (SELECT * FROM OldData.dbo.[2018 student-BD]) as t) as p group by year
select year, AVG(notas) as 'CBD Average' from (select year, ((cast(P1 + P2 + P3 as decimal))/3) as notas from (SELECT * FROM OldData.dbo.[2018 student-CBD]) as t) as p group by year
select year, AVG(notas) as 'MAT1 Average' from (select year, ((cast(P1 + P2 + P3 as decimal))/3) as notas from (SELECT * FROM OldData.dbo.[2018 student-MAT1]) as t) as p group by year

select year, AVG(notas) as 'BD Average' from (select year, ((cast(P1 + P2 + P3 as decimal))/3) as notas from (SELECT * FROM OldData.dbo.[2019 student-BD]) as t) as p group by year
select year, AVG(notas) as 'CBD Average' from (select year, ((cast(P1 + P2 + P3 as decimal))/3) as notas from (SELECT * FROM OldData.dbo.[2019 student-CBD]) as t) as p group by year
select year, AVG(notas) as 'MAT1 Average' from (select year, ((cast(P1 + P2 + P3 as decimal))/3) as notas from (SELECT * FROM OldData.dbo.[2019 student-MAT1]) as t) as p group by year

--Media por escola
GO
CREATE OR ALTER VIEW OldDataSchoolAverage
AS
	(select school, AVG(notas) as 'Average' from (select school,((cast(P1 + P2 + P3 as decimal))/3) as notas from oldDataInfo as tabela) as p 
	group by school)
GO

select * from OldDataSchoolAverage;


--Media de notas por ano
GO
CREATE OR ALTER VIEW OldDataYearAverage
AS
	select year, AVG(notas) as 'Average' from (select year, ((cast(P1 + P2 + P3 as decimal))/3) as notas from oldDataInfo as tabela) as p group by year
GO

select * from OldDataYearAverage;


-- 1ºREQUISITO  Média de notas no ano letivo por escola
GO
CREATE OR ALTER VIEW OldDataSchoolYearAverage
AS
	select year,school , AVG(notas) as 'Average' from (select year, school,((cast(P1 + P2 + P3 as decimal))/3) as notas from oldDataInfo as tabela) as p group by year, school
GO

select * from OldDataSchoolYearAverage order by year, school;


-- 2ºREQUISITO  Média de notas por disciplina

GO
CREATE OR ALTER VIEW BD2017Average
AS
	(select AVG(notas) as 'BD Average' from (select ((cast(P1 + P2 + P3 as decimal))/3) as notas from (SELECT * FROM OldData.dbo.[2017 student-BD]
	UNION
	SELECT * FROM OldData.dbo.[2018 student-BD]
	UNION
	SELECT * FROM OldData.dbo.[2019 student-BD]) as tabela) as t)
GO

select * from BD2017Average;

GO
CREATE OR ALTER VIEW CBD2017Average
AS
	(select AVG(notas) as 'CBD Average' from (select ((cast(P1 + P2 + P3 as decimal))/3) as notas from (SELECT * FROM OldData.dbo.[2017 student-CBD]
	UNION
	SELECT * FROM OldData.dbo.[2018 student-CBD]
	UNION
	SELECT * FROM OldData.dbo.[2019 student-CBD]) as tabela) as t)
GO

select * from CBD2017Average;


GO
CREATE OR ALTER VIEW MAT12017Average
AS
	(select AVG(notas) as 'MAT1 Average' from (select ((cast(P1 + P2 + P3 as decimal))/3) as notas from (SELECT * FROM OldData.dbo.[2017 student-MAT1]
	UNION
	SELECT * FROM OldData.dbo.[2018 student-MAT1]
	UNION
	SELECT * FROM OldData.dbo.[2019 student-MAT1]) as tabela) as t)
GO

select * from MAT12017Average;


-- 3ºREQUISITO  Média de notas por disciplina/escola
--BD
select school , AVG(notas) as 'BD Average' from (select school,((cast(P1 + P2 + P3 as decimal))/3) as notas from 
(SELECT * FROM OldData.dbo.[2017 student-BD]
UNION
SELECT * FROM OldData.dbo.[2018 student-BD]
UNION
SELECT * FROM OldData.dbo.[2019 student-BD]) as tabela) as p 
group by school
order by school

--CBD
select school , AVG(notas) as 'CBD Average' from (select school,((cast(P1 + P2 + P3 as decimal))/3) as notas from 
(SELECT * FROM OldData.dbo.[2017 student-CBD]
UNION
SELECT * FROM OldData.dbo.[2018 student-CBD]
UNION
SELECT * FROM OldData.dbo.[2019 student-CBD]) as tabela) as p 
group by school
order by school

--MAT1
select school , AVG(notas) as 'MAT1 Average' from (select school,((cast(P1 + P2 + P3 as decimal))/3) as notas from 
(SELECT * FROM OldData.dbo.[2017 student-MAT1]
UNION
SELECT * FROM OldData.dbo.[2018 student-MAT1]
UNION
SELECT * FROM OldData.dbo.[2019 student-MAT1]) as tabela) as p 
group by school
order by school

-- 4ºREQUISITO Total de alunos por escola/ano letivo

GO
CREATE OR ALTER VIEW OldDataStudentTotalPerSchoolYear
AS
	select year, school, count(distinct t.Student_Number) as 'Student Count' from oldDataInfo as t
	group by school, year
	
GO

select * from OldDataStudentTotalPerSchoolYear order by year;

/* ------------------------------------------------------------------------------------------------ ProjetoCBD */
USE ProjetoCBD

--Queries auxiliares

-- Media por disciplina por ano
select  SubjectID, CalendarYear, avg(notas) as 'Average' from (select StudentID,SubjectID, CalendarYear, ((cast(P1 + P2 + P3 as decimal))/3) as notas from ProjetoCBD.dbo.Student_Subject) as t
group by SubjectID, CalendarYear
order by SubjectID, CalendarYear

select  t.SubjectID, SubjectName, CalendarYear, avg(notas) as 'Average' from (select StudentID,SubjectID, CalendarYear, ((cast(P1 + P2 + P3 as decimal))/3) as notas from ProjetoCBD.dbo.Student_Subject) as t
inner join Subject
on t.SubjectID = subject.SubjectID
group by t.SubjectID, CalendarYear, SubjectName
order by SubjectID, CalendarYear

--Media de todos os alunos por ano por disciplina
GO
CREATE OR ALTER VIEW StudentAveragePerYearSubjectSchool
AS
	select Student.StudentID, t.SubjectID, CalendarYear, ((cast(P1 + P2 + P3 as decimal))/3) as notas, SchoolName from ProjetoCBD.dbo.Student_Subject as t
	inner join Subject_School
	on t.SubjectID = Subject_School.SubjectID
	inner join School
	on School.SchoolID = Subject_School.SchoolID
	inner join Student
	on Student.SchoolID = School.SchoolID and Student.StudentID = t.StudentID
GO

select * from StudentAveragePerYearSubjectSchool;

-- 1ºREQUISITO DONE  Média de notas no ano letivo por escola
GO
CREATE OR ALTER VIEW SchoolYearAverage
AS
	select CalendarYear, SchoolName, AVG(notas) as 'Average' from (select Student.StudentID, t.SubjectID, CalendarYear, ((cast(P1 + P2 + P3 as decimal))/3) as notas, SchoolName from ProjetoCBD.dbo.Student_Subject as t
	inner join Subject_School
	on t.SubjectID = Subject_School.SubjectID
	inner join School
	on School.SchoolID = Subject_School.SchoolID
	inner join Student
	on Student.SchoolID = School.SchoolID and Student.StudentID = t.StudentID) as g
	group by CalendarYear, SchoolName
GO

select * from SchoolYearAverage order by CalendarYear, SchoolName;


-- 2ºREQUISITO DONE  Média de notas por disciplina
GO
CREATE OR ALTER VIEW SubjectAverage
AS
	select  t.SubjectID, SubjectName, avg(notas) as 'Average' from (select StudentID,SubjectID, ((cast(P1 + P2 + P3 as decimal))/3) as notas from ProjetoCBD.dbo.Student_Subject) as t
	inner join Subject
	on t.SubjectID = subject.SubjectID
	group by t.SubjectID, SubjectName
	
GO

select * from SubjectAverage order by SubjectID;


-- 3ºREQUISITO DONE  Média de notas por disciplina/escola

GO
CREATE OR ALTER VIEW SubjectSchoolAverage
AS
	select  t.SubjectID, SubjectName, avg(notas) as 'Average', SchoolName from (select StudentID,SubjectID, ((cast(P1 + P2 + P3 as decimal))/3) as notas from ProjetoCBD.dbo.Student_Subject) as t
	inner join Subject
	on t.SubjectID = subject.SubjectID
	inner join Subject_School
	on t.SubjectID = Subject_School.SubjectID
	inner join School
	on School.SchoolID = Subject_School.SchoolID
	inner join Student
	on Student.SchoolID = School.SchoolID and Student.StudentID = t.StudentID
	group by t.SubjectID, SubjectName, SchoolName
GO

select * from SubjectSchoolAverage order by SubjectID;


-- 4ºREQUISITO DONE Total de alunos por escola/ano letivo

GO
CREATE OR ALTER VIEW StudentTotalPerSchoolYear
AS
	select Student_Subject.CalendarYear, SchoolName, count(distinct Student.StudentID) as 'Student Count'  from ProjetoCBD.dbo.Student
	inner join Student_Subject
	on Student_Subject.StudentID = Student.StudentID
	inner join School
	on School.SchoolID = Student.SchoolID
	inner join Subject_School
	on Subject_School.SchoolID = School.SchoolID
	group by SchoolName,Student_Subject.CalendarYear
GO

select * from StudentTotalPerSchoolYear order by CalendarYear, SchoolName;


--Alternativa
select CalendarYear, SchoolName, count(distinct Student.StudentID) as 'Student Count'  from ProjetoCBD.dbo.Student_Subject as t
inner join Subject_School
on t.SubjectID = Subject_School.SubjectID
inner join School
on School.SchoolID = Subject_School.SchoolID
inner join Student
on Student.SchoolID = School.SchoolID and Student.StudentID = t.StudentID
group by SchoolName,CalendarYear
order by CalendarYear, SchoolName