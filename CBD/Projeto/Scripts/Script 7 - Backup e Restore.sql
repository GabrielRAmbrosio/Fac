USE ProjetoCBD

/*Backup*/

USE [master]
--Backup Full
BACKUP DATABASE [ProjetoCBD] TO [BackupDevice]
WITH NOFORMAT, NOINIT,  NAME = N'ProjetoCBD-Full Database Backup'
GO

--Backup Differential
BACKUP DATABASE [ProjetoCBD] TO  [BackupDevice]
WITH  DIFFERENTIAL , NOFORMAT, NOINIT,  NAME = N'ProjetoCBD-Full Database Backup'
GO

--Backup Transaction Log
BACKUP LOG [ProjetoCBD] TO  [BackupDevice]
WITH NOFORMAT, NOINIT,  NAME = N'ProjetoCBD-Full Database Backup', NORECOVERY
GO

/*Restore*/

USE [master]
--drop database ProjetoCBD

RESTORE DATABASE [ProjetoCBD] FROM  [BackupDevice] WITH  FILE = 1,  NORECOVERY
RESTORE DATABASE [ProjetoCBD] FROM  [BackupDevice] WITH  FILE = 2,  NORECOVERY
RESTORE LOG [ProjetoCBD] FROM  [BackupDevice] WITH  FILE = 3
GO



------------DEBUG--------------

RESTORE DATABASE ProjetoCBD WITH RECOVERY

SELECT
    s.database_name,s.backup_finish_date,y.physical_device_name
FROM
    msdb..backupset AS s INNER JOIN
    msdb..backupfile AS f ON f.backup_set_id = s.backup_set_id INNER JOIN
    msdb..backupmediaset AS m ON s.media_set_id = m.media_set_id INNER JOIN
    msdb..backupmediafamily AS y ON m.media_set_id = y.media_set_id
WHERE
    (s.database_name = 'ProjetoCBD')
ORDER BY
    s.backup_finish_date DESC;
go


SELECT name, recovery_model_desc  
FROM 
	sys.databases  
WHERE
	name = 'model' ;  
GO 

select top 1 b1.[type], 
                 b1.first_lsn, 
                 b1.last_lsn, 
                 b3.physical_device_name
    from msdb..backupset as b1 
        join msdb..backupmediaset as b2 
            on b1.media_set_id = b2.media_set_id
        join msdb..backupmediafamily as b3 
            on b2.media_set_id = b3.media_set_id
    where database_name = 'ProjetoCBD' and (last_lsn) < 41000000102400001
    order by last_lsn desc;
go

SELECT name, state_desc from sys.databases 
GO
