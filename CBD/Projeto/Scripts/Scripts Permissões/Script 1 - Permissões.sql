USE ProjetoCBD

-- Criar roles

GO
CREATE ROLE  AdminRole; --Administrador
GO
GO
CREATE ROLE UserRole; --Aluno/Encarregado
GO
GO
CREATE ROLE SchoolRole; --Escola
GO

-- Criar utilizadores

GO
CREATE USER Admin WITHOUT LOGIN;
GO
GO
CREATE USER Student WITHOUT LOGIN;
GO
GO
CREATE USER Parent WITHOUT LOGIN;
GO

GO
CREATE USER GP WITHOUT LOGIN;
GO

GO
CREATE USER MS WITHOUT LOGIN;
GO

-- Adicionar utilizadores aos roles

GO
EXEC sp_addrolemember  @membername = 'Admin', @rolename = 'AdminRole';
GO
GO
EXEC sp_addrolemember  @membername = 'Student', @rolename = 'UserRole';
GO
GO
EXEC sp_addrolemember  @membername = 'Parent', @rolename = 'UserRole';
GO
GO
EXEC sp_addrolemember  @membername = 'GP', @rolename = 'SchoolRole';
GO
GO
EXEC sp_addrolemember  @membername = 'MS', @rolename = 'SchoolRole';
GO

--Atribuições de permissões ao role de Administrador
GO
GRANT SELECT, INSERT, DELETE, UPDATE, EXECUTE on SCHEMA::dbo TO AdminRole
GO

--Atribuições de permissões ao role de Utilizador
GO
GRANT SELECT on SCHEMA::dbo TO UserRole
GO
GO
GRANT SELECT ON OBJECT::Student TO UserRole;  
GO
GO
GRANT SELECT ON OBJECT::School TO UserRole;  
GO
GO
GRANT SELECT ON OBJECT::Student_Subject TO UserRole;  
GO
GO
GRANT SELECT ON OBJECT::Subject_School TO UserRole;  
GO
GO
DENY SELECT ON OBJECT::Address TO UserRole;  
GO

--Atribuições de permissões ao role da Escola
GO
GRANT SELECT on OBJECT::Student TO SchoolRole
GO

go
DENY DELETE ON OBJECT::Student_Subject TO SchoolRole;  
GO