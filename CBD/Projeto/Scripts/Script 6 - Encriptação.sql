use ProjetoCBD

-- Criar chave master 
go
create master key encryption by password = '123456789'


-- Definir o certificado
go
create certificate EncryptCertificate
with subject = 'Protect Data'


-- Criar chaves simétricas

CREATE SYMMETRIC KEY passwordKey
WITH ALGORITHM = AES_256  
ENCRYPTION BY CERTIFICATE EncryptCertificate;  
GO  

-- Adicionar as colunas que irão conter os campos encriptados

-- Email
go
alter table Student
add encryptEmail varbinary(256)

go
alter table Parent
add encryptEmail varbinary(256)

-- Password
go
alter table Student
add encryptPassword varbinary(256)

go
alter table Parent
add encryptPassword varbinary(256)


-- Encriptar campos password
go
open symmetric key passwordKey decryption
by certificate EncryptCertificate
update Student
set encryptPassword =
ENCRYPTBYKEY(KEY_GUID('passwordKey'),Password)
go

go
open symmetric key passwordKey decryption
by certificate EncryptCertificate
update Parent
set encryptPassword =
ENCRYPTBYKEY(KEY_GUID('passwordKey'),Password)
go


-- Encriptar campo email
go
open symmetric key passwordKey decryption
by certificate EncryptCertificate
update Student
set encryptEmail =
ENCRYPTBYKEY(KEY_GUID('passwordKey'),EmailAddress)
go

go
open symmetric key passwordKey decryption
by certificate EncryptCertificate
update Parent
set encryptEmail =
ENCRYPTBYKEY(KEY_GUID('passwordKey'),EmailAddress)
go


--inserts para teste
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

exec dbo.sp_createStudent @SchoolID = 1,
	@EmailAddress = 'joao.1234566@gmail.com',
	@Password = '1rrrr33',
	@FirstName = 'João',
	@MiddleName = 'Luis',
	@LastName = 'Antonio',
	@BirthDate = '2005-09-18',
	@Gender = 'M'

/*
drop function if exists dbo.fnCodificaPassword;

go
create function dbo.fnCodificaPassword(@pass VARCHAR(50))
returns VARBINARY(256)
begin
	
	declare @ret VARBINARY(1)
	select @ret = HASHBYTES('SHA1', @pass) 
	return @ret
end
go
*/


/*Triggers para codificar password/email quando se insere um aluno ou guardião*/
go
create or alter trigger trigger_codificarStudentPassword
on Student
after insert
as
begin
	/*
	update Student
	set Student.password = dbo.fnCodificaPassword((SELECT Student.password FROM Student, INSERTED WHERE Student.StudentID = INSERTED.StudentID))
	where Student.StudentID = (SELECT Student.StudentID FROM Student, INSERTED  WHERE Student.StudentID = INSERTED.StudentID)
	*/
	open symmetric key passwordKey decryption
	by certificate EncryptCertificate
	update Student
	set encryptPassword =
	ENCRYPTBYKEY(KEY_GUID('passwordKey'),Password)
	where Student.StudentID = (SELECT Student.StudentID FROM Student, INSERTED  WHERE Student.StudentID = INSERTED.StudentID)
end

go

create or alter trigger trigger_codificarStudentEmail
on Student
after insert
as
begin
	open symmetric key passwordKey decryption
	by certificate EncryptCertificate
	update Student
	set encryptEmail =
	ENCRYPTBYKEY(KEY_GUID('passwordKey'),EmailAddress)
	where Student.StudentID = (SELECT Student.StudentID FROM Student, INSERTED  WHERE Student.StudentID = INSERTED.StudentID)
end

go

go
create or alter trigger trigger_codificarParentPassword
on Parent
after insert
as
begin
	open symmetric key passwordKey decryption
	by certificate EncryptCertificate
	update Parent
	set encryptPassword =
	ENCRYPTBYKEY(KEY_GUID('passwordKey'),Password)
	where Parent.ParentID = (SELECT Parent.ParentID FROM Parent, INSERTED  WHERE Parent.ParentID = INSERTED.ParentID)
end

go

create or alter trigger trigger_codificarParentEmail
on Parent
after insert
as
begin
	open symmetric key passwordKey decryption
	by certificate EncryptCertificate
	update Parent
	set encryptEmail =
	ENCRYPTBYKEY(KEY_GUID('passwordKey'),EmailAddress)
	where Parent.ParentID = (SELECT Parent.ParentID FROM Parent, INSERTED  WHERE Parent.ParentID = INSERTED.ParentID)
end

go


-- Decript campos email
go
open symmetric key passwordKey decryption
by certificate EncryptCertificate
select convert(nvarchar(50), DECRYPTBYKEY(encryptEmail))
as DecryptEmail
from Student
go

go
close symmetric key passwordKey
go

go
open symmetric key passwordKey decryption
by certificate EncryptCertificate
select convert(nvarchar(50), DECRYPTBYKEY(encryptEmail))
as DecryptEmail
from Parent
go

go
close symmetric key passwordKey
go


-- Decript campo password
go
open symmetric key passwordKey decryption
by certificate EncryptCertificate
select convert(nvarchar(50), DECRYPTBYKEY(encryptPassword))
as DecryptPassword
from Student
go

go
close symmetric key passwordKey
go

go
open symmetric key passwordKey decryption
by certificate EncryptCertificate
select convert(nvarchar(50), DECRYPTBYKEY(encryptPassword))
as DecryptPassword
from Parent
go

go
close symmetric key passwordKey
go


--testes
select EmailAddress, encryptEmail from Student


select Password, encryptPassword from Student