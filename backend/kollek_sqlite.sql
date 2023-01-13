
/*******************************************************************************
   Chinook Database - Version 1.4
   Script: Chinook_Sqlite.sql
   Description: Creates and populates the Chinook database.
   DB Server: Sqlite
   Author: Luis Rocha
   License: http://www.codeplex.com/ChinookDatabase/license
********************************************************************************/

/*******************************************************************************
   Drop Foreign Keys Constraints
********************************************************************************/

/*******************************************************************************
   Drop Tables
********************************************************************************/
DROP TABLE IF EXISTS [Users];

DROP TABLE IF EXISTS [Categories;

DROP TABLE IF EXISTS [Minerals];


/*******************************************************************************
   Create Tables
********************************************************************************/
CREATE TABLE [Users]
(
    [UserId] INTEGER  NOT NULL,
    [Login] NVARCHAR(60)  NOT NULL,
    [Password] NVARCHAR(60)  NOT NULL,
    CONSTRAINT [PK_Users] PRIMARY KEY  ([UserId])
);

CREATE TABLE [Categories]
(
    [CategoryId] INTEGER  NOT NULL,
    [Name] NVARCHAR(120)  NOT NULL,
    [Color] NVARCHAR(40),
    CONSTRAINT [PK_Category] PRIMARY KEY  ([CategoryId])
);

CREATE TABLE [Minerals]
(
    [MineralId] INTEGER  NOT NULL,
    [Name] NVARCHAR(40)  NOT NULL,
    [Country] NVARCHAR(40),
    [Image] NVARCHAR(10000),
    [CategoryId] INTEGER  NOT NULL,
    CONSTRAINT [PK_Mineral] PRIMARY KEY  ([MineralId]),
    FOREIGN KEY ([CategoryId]) REFERENCES [Categories] ([CategoryId]) 
		ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE Table [Collections]
(
    [UserId] INTEGER  NOT NULL,
    [MineralId] INTEGER  NOT NULL,
    FOREIGN KEY ([UserId]) REFERENCES [Users] ([UserId]) 
		ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY ([MineralId]) REFERENCES [Minerals] ([MineralId]) 
		ON DELETE NO ACTION ON UPDATE NO ACTION
);


/*******************************************************************************
   Create Primary Key Unique Indexes
********************************************************************************/

/*******************************************************************************
   Create Foreign Keys
********************************************************************************/
CREATE INDEX [IFK_UserId] ON [Collections] ([UserId]);

CREATE INDEX [IFK_MineralId] ON [Collections] ([MineralId]);

CREATE INDEX [IFK_CaategotyId] ON [Minerals] ([CategoryId]);


/*******************************************************************************
   Populate Tables
********************************************************************************/
INSERT INTO [Users] ([UserId], [Login], [Password]) VALUES (1, 'admin', 'admin');
INSERT INTO [Users] ([UserId], [Login], [Password]) VALUES (2, 'alice', 'aaa');
INSERT INTO [Users] ([UserId], [Login], [Password]) VALUES (3, 'bob', 'bbb');
INSERT INTO [Users] ([UserId], [Login], [Password]) VALUES (4, 'chloe', 'ccc');
INSERT INTO [Users] ([UserId], [Login], [Password]) VALUES (5, 'damien', 'ddd');

INSERT INTO [Categories] ([CategoryId], [Name], [Color]) VALUES (1, 'rubis', 'rouge');
INSERT INTO [Categories] ([CategoryId], [Name], [Color]) VALUES (2, 'saphire', 'bleu');
INSERT INTO [Categories] ([CategoryId], [Name], [Color]) VALUES (3, 'emeraude', 'vert');
INSERT INTO [Categories] ([CategoryId], [Name], [Color]) VALUES (4, 'diamant', 'magenta');
INSERT INTO [Categories] ([CategoryId], [Name], [Color]) VALUES (5, 'or', 'jaune');
INSERT INTO [Categories] ([CategoryId], [Name], [Color]) VALUES (6, 'opal', 'cyan');
INSERT INTO [Categories] ([CategoryId], [Name], [Color]) VALUES (7, 'quartz', 'blanc');

INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (1, 'Mon Gros Rubis', 1);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (2, 'Mon Petit Rubis', 1);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (3, 'Cailloux', 2);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (4, 'Ceci est joli', 3);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (5, 'Mon chat', 4);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (6, 'Mon chien', 5);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (7, 'Mon lapin', 6);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (8, 'Mon poney', 7);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (9, 'Aquaponey', 1);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (10, 'Une pomme', 2);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (11, 'Une Poire', 3);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (12, 'Mon Peche', 4);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (13, 'Mon Abricot', 5);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (14, 'Il y en a un en trop', 6);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (15, 'Ceci est', 7);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (16, 'Ceci n est pas', 1);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (17, 'test', 2);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (18, 'Hello World', 3);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (19, 'How to SQL inject', 4);
INSERT INTO [Minerals] ([MineralId], [Name], [CategoryId]) VALUES (20, 'gg wp', 5);

INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (1, 1);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (1, 1);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (1, 2);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (1, 3);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (2, 4);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (2, 5);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (2, 6);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (3, 7);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (3, 8);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (3, 9);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (3, 10);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (3, 11);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (3, 12);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (3, 13);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (3, 14);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (4, 15);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (4, 16);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (4, 17);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (4, 18);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (4, 19);
INSERT INTO [Collections] ([UserId], [MineralId]) VALUES (5, 20);


