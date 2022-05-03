/*
Não foi possivel retirar os dados do ficheiro Excel com estas SP
Dava erro e o SQL SERVER começava a ficar lento e a não responder
*/

EXEC sp_configure 'show advanced option', '1';
RECONFIGURE;
EXEC sp_configure 'Ad Hoc Distributed Queries', 1;
RECONFIGURE;

EXEC master.[sys].[sp_MSset_oledb_prop] N'Microsoft.ACE.OLEDB.12.0', N'AllowInProcess', 1

USE OldData;
GO

SELECT * 
FROM OPENROWSET('Microsoft.ACE.OLEDB.12.0', 
   'Excel 12.0 Xml;Database=C:\Users\gabri\Desktop\dataset;', 2017_student-BD$);

SELECT * FROM OPENDATASOURCE('Microsoft.ACE.OLEDB.12.0', 
   'Data Source=C:\data\simple.xlsx;Extended Properties=EXCEL 12.0')...[2017_student-BD$];
   
GO