use master

if exists (select * from sys.databases where name = 'ProjetoCBD')
drop database ProjetoCBD

create database ProjetoCBD
on
primary
(name = Projeto_dat,
filename = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Projeto_dat.mdf',
Size = 50,
Maxsize = 250,
Filegrowth = 10)

Log On

(name = Projeto_log,
filename = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Projeto_log.ldf',
Size = 20,
Maxsize = 250,
Filegrowth = 10);
go

use ProjetoCBD
go

alter Database ProjetoCBD
add Filegroup PersonFG;

alter Database ProjetoCBD
add File
(name = Projeto_Persons,
filename = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Projeto_Persons.ndf',
Size = 50,
Maxsize = 100,
Filegrowth = 10)
to filegroup PersonFG;

go

alter database ProjetoCBD
add filegroup SchoolFG;

alter database ProjetoCBD
add file
(name = Projeto_School,
filename = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Projeto_School.ndf',
Size = 50,
Maxsize = 100,
Filegrowth = 10)
to filegroup SchoolFG;

go