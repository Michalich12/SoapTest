USE employeeTestDb
GO

IF DB_NAME() <> N'employeeTestDb' SET NOEXEC ON
GO

--
-- Создать таблицу [dbo].[Employee]
--
PRINT (N'Создать таблицу [dbo].[Employee]')
GO
CREATE TABLE dbo.Employee (
  ID int IDENTITY,
  Firstname nvarchar(250) NULL,
  Lastname nvarchar(250) NULL,
  Phone nvarchar(250) NULL,
  DepartmentID int NULL,
  CONSTRAINT PK_Employee_ID PRIMARY KEY CLUSTERED (ID)
)
ON [PRIMARY]
GO

--
-- Создать таблицу [dbo].[Department]
--
PRINT (N'Создать таблицу [dbo].[Department]')
GO
CREATE TABLE dbo.Department (
  ID int IDENTITY,
  Name nvarchar(250) NULL,
  CONSTRAINT PK_Department_ID PRIMARY KEY CLUSTERED (ID),
  UNIQUE (Name)
)
ON [PRIMARY]
GO

--
-- Создать пользователя [test]
--
PRINT (N'Создать пользователя [test]')
GO
CREATE USER test
  WITHOUT LOGIN
GO
-- 
-- Вывод данных для таблицы Department
--
SET IDENTITY_INSERT dbo.Department ON
GO
INSERT dbo.Department(ID, Name) VALUES (3, N'ИТ отдел')
INSERT dbo.Department(ID, Name) VALUES (2, N'Коммерческий отдел')
INSERT dbo.Department(ID, Name) VALUES (5, N'Отдел веб разработки')
INSERT dbo.Department(ID, Name) VALUES (1, N'Отдел снабжения')
INSERT dbo.Department(ID, Name) VALUES (4, N'Отдел тестирования')
GO
SET IDENTITY_INSERT dbo.Department OFF
GO
-- 
-- Вывод данных для таблицы Employee
--
-- Таблица employeeTestDb.dbo.Employee не содержит данных

USE employeeTestDb
GO

IF DB_NAME() <> N'employeeTestDb' SET NOEXEC ON
GO

--
-- Создать внешний ключ [FK_Employee_DepartmentID] для объекта типа таблица [dbo].[Employee]
--
PRINT (N'Создать внешний ключ [FK_Employee_DepartmentID] для объекта типа таблица [dbo].[Employee]')
GO
ALTER TABLE dbo.Employee
  ADD CONSTRAINT FK_Employee_DepartmentID FOREIGN KEY (DepartmentID) REFERENCES dbo.Department (ID)
GO
SET NOEXEC OFF
GO