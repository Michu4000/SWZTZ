--SQL SERVER 2012

--------------------------------------------------------------------------------------------------
--TABELE
--------------------------------------------------------------------------------------------------
CREATE TABLE [dbo].[ADMINISTRATOR] (
	[id_administrator] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[PESEL] numeric(11, 0) NOT NULL, 
	[imie] varchar(MAX) NOT NULL, 
	[nazwisko] varchar(MAX) NOT NULL, 
	[uprawnienia] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[STUDENT] (
	[id_student] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_grupa] int NOT NULL, 
	[PESEL] numeric(11, 0) NOT NULL, 
	[imie] varchar(MAX) NOT NULL, 
	[nazwisko] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[GRUPA] (
	[id_grupa] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[nazwa] varchar(MAX) NOT NULL, 
	[kierunek] varchar(MAX) NOT NULL, 
	[rok] int NOT NULL,
	[id_starosta] int
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ANKIETA] (
	[id_ankieta] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_grupa] int NOT NULL, 
	[pytanie] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ANKIETA_ODPOWIEDZI] (
	[id_ankieta_odpowiedzi] int NOT NULL IDENTITY(1,1) PRIMARY KEY CLUSTERED,
	[id_ankieta] int NOT NULL,
	[id_odpowiedz] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ODPOWIEDZ_ANKIETA] (
	[id_odpowiedz] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_student] int NOT NULL, 
	[tresc_odpowiedz] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[UZYTKOWNIK] (
	[id_uzytkownik] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_planista] int, 
	[id_administrator] int, 
	[id_dyrektor] int, 
	[id_prowadzacy] int, 
	[id_student] int, 
	[hash_hasla] varchar(MAX) NOT NULL, 
	[email] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[BLAD] (
	[id_blad] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_uzytkownik] int NOT NULL, 
	[temat] varchar(MAX) NOT NULL, 
	[opis] varchar(MAX) NOT NULL, 
	[data_zgloszenia] datetime NOT NULL, 
	[rozpatrzono] bit NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[WYDZIAL] (
	[id_wydzial] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[nazwa_wydzial] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[INSTYTUT] (
	[id_instytut] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_wydzial] int NOT NULL, 
	[nazwa_instytut] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[DYREKTOR] (
	[id_dyrektor] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_instytut] int NOT NULL, 
	[PESEL] numeric(11, 0) NOT NULL, 
	[imie] varchar(MAX) NOT NULL, 
	[nazwisko] varchar(MAX) NOT NULL, 
	[pokoj] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ZAJECIA] (
	[id_zajecia] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED,
	[data] date NOT NULL, 
	[nr_bloku] int NOT NULL, 
	[id_sala] int NOT NULL, 
	[id_przedmiot] int NOT NULL, 
	[typ_zajec] varchar(MAX) NOT NULL, 
	[id_prowadzacy] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[INSPEKCJA] (
	[id_inspekcja] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_zajecia] int NOT NULL, 
	[id_dyrektor] int NOT NULL, 
	[komentarz_inspekcja] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[PROWADZACY] (
	[id_prowadzacy] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[PESEL] numeric(11, 0) NOT NULL, 
	[imie] varchar(MAX) NOT NULL, 
	[nazwisko] varchar(MAX) NOT NULL, 
	[stopien] varchar(MAX) NOT NULL, 
	[pokoj] varchar(MAX)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[SALA] (
	[id_sala] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[nr_sala] varchar(MAX) NOT NULL, 
	[budynek] varchar(MAX) NOT NULL, 
	[typ_sala] varchar(MAX) NOT NULL, 
	[ilosc_miejsc] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[KONSULTACJA] (
	[id_konsultacja] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED,
	[data] date NOT NULL, 
	[nr_bloku] int NOT NULL,
	[id_sala] int, 
	[id_prowadzacy] int NOT NULL, 
	[komentarz] varchar(MAX)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[PLANISTA] (
	[id_planista] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_wydzial] int NOT NULL, 
	[PESEL] numeric(11, 0) NOT NULL, 
	[imie] varchar(MAX) NOT NULL, 
	[nazwisko] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[PRZEDMIOT] (
	[id_przedmiot] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_instytut] int NOT NULL, 
	[nazwa_przedmiot] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[URLOP] (
	[id_urlop] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_prowadzacy] int NOT NULL, 
	[data_rozpoczecia] date NOT NULL, 
	[data_zakonczenia] date NOT NULL, 
	[powod] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[WIADOMOSC] (
	[id_wiadomosc] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_nadawca] int NOT NULL, 
	[temat] varchar(MAX) NOT NULL, 
	[tresc] varchar(MAX) NOT NULL, 
	[data_wyslania] datetime NOT NULL,
	[id_odbiorca] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[WIADOMOSC_ODBIORCY] (
	[id_wiadomosc_odbiorcy] int NOT NULL IDENTITY(1,1) PRIMARY KEY CLUSTERED,
	[id_wiadomosc] int NOT NULL,
	[id_uzytkownik] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[WNIOSEK] (
	[id_wniosek] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_prowadzacy] int NOT NULL, 
	[id_dyrektor] int NOT NULL, 
	[tresc_wniosek] varchar(MAX) NOT NULL, 
	[decyzja] bit NOT NULL, 
	[zmieniono_plan] bit NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ZAJECIA_GRUPY] (
	[id_zajecia_grupy] int NOT NULL IDENTITY(1,1) PRIMARY KEY CLUSTERED,
	[id_zajecia] int NOT NULL,
	[id_grupa] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ZAJECIA_OBECNOSC] (
	[id_zajecia_obecnosc] int NOT NULL IDENTITY(1,1) PRIMARY KEY CLUSTERED,
	[id_zajecia] int NOT NULL,
	[id_student] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ZAPYTANIE] (
	[id_zapytanie] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_student] int NOT NULL, 
	[id_prowadzacy] int NOT NULL, 
	[tresc_zapytanie] varchar(MAX) NOT NULL, 
	[decyzja] bit
) ON [PRIMARY]
GO

--------------------------------------------------------------------------------------------------
--KLUCZE OBCE
--------------------------------------------------------------------------------------------------

ALTER TABLE [dbo].[PRZEDMIOT]
ADD CONSTRAINT FK_PRZEDMIOT_INSTYTUT FOREIGN KEY ([id_instytut])
		REFERENCES [dbo].[INSTYTUT] ([id_instytut])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[URLOP]
ADD CONSTRAINT FK_URLOP_PROWADZACY FOREIGN KEY ([id_prowadzacy])
		REFERENCES [dbo].[PROWADZACY] ([id_prowadzacy])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[WIADOMOSC]
ADD CONSTRAINT FK_WIADOMOSC_NADAWCA FOREIGN KEY ([id_nadawca])
		REFERENCES [dbo].[UZYTKOWNIK] ([id_uzytkownik])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[WIADOMOSC]
ADD CONSTRAINT FK_WIADOMOSC_NADAWCA FOREIGN KEY ([id_odbiorca])
		REFERENCES [dbo].[UZYTKOWNIK] ([id_uzytkownik])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[WIADOMOSC_ODBIORCY]
ADD CONSTRAINT FK_WIADOMOSC_ODBIORCY_WIADOMOSC FOREIGN KEY ([id_wiadomosc])
		REFERENCES [dbo].[WIADOMOSC] ([id_wiadomosc])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[WIADOMOSC_ODBIORCY]
ADD CONSTRAINT FK_WIADOMOSC_ODBIORCY_UZYTKOWNIK FOREIGN KEY ([id_uzytkownik])
		REFERENCES [dbo].[UZYTKOWNIK] ([id_uzytkownik])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[WNIOSEK]
ADD CONSTRAINT FK_WNIOSEK_DYREKTOR FOREIGN KEY ([id_dyrektor])
		REFERENCES [dbo].[DYREKTOR] ([id_dyrektor])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION 
	;
GO

ALTER TABLE [dbo].[WNIOSEK]
ADD CONSTRAINT FK_WNIOSEK_PROWADZACY FOREIGN KEY ([id_prowadzacy])
		REFERENCES [dbo].[PROWADZACY] ([id_prowadzacy])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[ZAJECIA_GRUPY]
ADD CONSTRAINT FK_ZAJECIA_GRUPY_ZAJECIA FOREIGN KEY ([id_zajecia])
		REFERENCES [dbo].[ZAJECIA] ([id_zajecia])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[ZAJECIA_GRUPY]
ADD CONSTRAINT FK_ZAJECIA_GRUPY_GRUPA FOREIGN KEY ([id_grupa])
		REFERENCES [dbo].[GRUPA] ([id_grupa])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[ZAJECIA_OBECNOSC]
ADD CONSTRAINT FK_ZAJECIA_OBECNOSC_STUDENT FOREIGN KEY ([id_student])
		REFERENCES [dbo].[STUDENT] ([id_student])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[ZAJECIA_OBECNOSC]
ADD CONSTRAINT FK_ZAJECIA_OBECNOSC_ZAJECIA FOREIGN KEY ([id_zajecia])
		REFERENCES [dbo].[ZAJECIA] ([id_zajecia])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[ZAPYTANIE]
ADD CONSTRAINT FK_ZAPYTANIE_PROWADZACY FOREIGN KEY ([id_prowadzacy])
		REFERENCES [dbo].[PROWADZACY] ([id_prowadzacy])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[ZAPYTANIE]
ADD CONSTRAINT FK_ZAPYTANIE_STUDENT FOREIGN KEY ([id_student])
		REFERENCES [dbo].[STUDENT] ([id_student])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[STUDENT]
ADD CONSTRAINT FK_STUDENT_GRUPA FOREIGN KEY ([id_grupa]) 
    REFERENCES [dbo].[GRUPA] ([id_grupa]) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
;
GO

ALTER TABLE [dbo].[GRUPA]
ADD CONSTRAINT FK_GRUPA_STUDENT FOREIGN KEY ([id_starosta]) 
    REFERENCES [dbo].[STUDENT] ([id_student]) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
;
GO

ALTER TABLE [dbo].[ANKIETA]
ADD CONSTRAINT FK_ANKIETA_GRUPA FOREIGN KEY ([id_grupa])
	REFERENCES [dbo].[GRUPA] ([id_grupa])
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;
GO

ALTER TABLE [dbo].[ODPOWIEDZ_ANKIETA]
ADD CONSTRAINT FK_ODPOWIEDZ_ANKIETA_STUDENT FOREIGN KEY ([id_student])
	REFERENCES [dbo].[STUDENT] ([id_student])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;
GO

ALTER TABLE [dbo].[ANKIETA_ODPOWIEDZI]
ADD CONSTRAINT FK_ANKIETA_ODPOWIEDZI_ODPOWIEDZ_ANKIETA FOREIGN KEY ([id_odpowiedz])
	REFERENCES [dbo].[ODPOWIEDZ_ANKIETA] ([id_odpowiedz])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[ANKIETA_ODPOWIEDZI]
ADD CONSTRAINT FK_ANKIETA_ODPOWIEDZI_ANKIETA FOREIGN KEY ([id_ankieta])
	REFERENCES [dbo].[ANKIETA] ([id_ankieta])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[UZYTKOWNIK]
ADD CONSTRAINT FK_UZYTKOWNIK_ADMINISTRATOR FOREIGN KEY ([id_administrator])
	REFERENCES [dbo].[ADMINISTRATOR] ([id_administrator])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[UZYTKOWNIK]
ADD CONSTRAINT FK_UZYTKOWNIK_PLANISTA FOREIGN KEY ([id_planista])
	REFERENCES [dbo].[PLANISTA] ([id_planista])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[UZYTKOWNIK]
ADD CONSTRAINT FK_UZYTKOWNIK_STUDENT FOREIGN KEY ([id_student])
	REFERENCES [dbo].[STUDENT] ([id_student])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[UZYTKOWNIK]
ADD CONSTRAINT FK_UZYTKOWNIK_PROWADZACY FOREIGN KEY ([id_prowadzacy])
	REFERENCES [dbo].[PROWADZACY] ([id_prowadzacy])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[UZYTKOWNIK]
ADD CONSTRAINT FK_UZYTKOWNIK_DYREKTOR FOREIGN KEY ([id_dyrektor])
	REFERENCES [dbo].[DYREKTOR] ([id_dyrektor])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[BLAD]
ADD CONSTRAINT FK_BLAD_UZYTKOWNIK FOREIGN KEY ([id_uzytkownik])
	REFERENCES [dbo].[UZYTKOWNIK] ([id_uzytkownik])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[INSTYTUT]
ADD CONSTRAINT FK_INSTYTUT_WYDZIAL FOREIGN KEY ([id_wydzial])
	REFERENCES [dbo].[WYDZIAL] ([id_wydzial])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[DYREKTOR]
ADD CONSTRAINT FK_DYREKTOR_INSTYTUT FOREIGN KEY ([id_instytut])
	REFERENCES [dbo].[INSTYTUT] ([id_instytut])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[ZAJECIA]
ADD CONSTRAINT FK_ZAJECIA_SALA FOREIGN KEY ([id_sala])
	REFERENCES [dbo].[SALA] ([id_sala])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[ZAJECIA]
ADD CONSTRAINT FK_ZAJECIA_PRZEDMIOT FOREIGN KEY ([id_przedmiot])
	REFERENCES [dbo].[PRZEDMIOT] ([id_przedmiot])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[ZAJECIA]
ADD CONSTRAINT FK_ZAJECIA_PROWADZACY FOREIGN KEY ([id_prowadzacy])
	REFERENCES [dbo].[PROWADZACY] ([id_prowadzacy])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[INSPEKCJA]
ADD CONSTRAINT FK_INSPEKCJA_DYREKTOR FOREIGN KEY ([id_dyrektor])
	REFERENCES [dbo].[DYREKTOR] ([id_dyrektor])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[INSPEKCJA]
ADD CONSTRAINT FK_INSPEKCJA_ZAJECIA FOREIGN KEY ([id_zajecia])
	REFERENCES [dbo].[ZAJECIA] ([id_zajecia])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[KONSULTACJA]
ADD CONSTRAINT FK_KONSULTACJA_PROWADZACY FOREIGN KEY ([id_prowadzacy])
	REFERENCES [dbo].[PROWADZACY] ([id_prowadzacy])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[KONSULTACJA]
ADD CONSTRAINT FK_KONSULTACJA_SALA FOREIGN KEY ([id_sala])
	REFERENCES [dbo].[SALA] ([id_sala])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [dbo].[PLANISTA]
ADD CONSTRAINT FK_PLANISTA_WYDZIAL FOREIGN KEY ([id_wydzial])
		REFERENCES [dbo].[WYDZIAL] ([id_wydzial])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

--------------------------------------------------------------------------------------------------
--FUNKCJE
--------------------------------------------------------------------------------------------------

CREATE FUNCTION [dbo].[getGrupyZajecia] (@x int)
RETURNS varchar(MAX)
AS
BEGIN
	DECLARE @result varchar(MAX)
	SET @result = ''
	SELECT @result = @result + nazwa + ', ' FROM GRUPA AS g JOIN ZAJECIA_GRUPY AS zg ON g.id_grupa = zg.id_grupa WHERE zg.id_zajecia = @x 

	SELECT @result = CASE
		WHEN len(@result) > 0 THEN substring(@result, 0, len(@result)) -- trim extra ", " at end
		WHEN len(@result) = 0 THEN ''
	END
		
	RETURN @result
END;

--------------------------------------------------------------------------------------------------
--WIDOKI
--------------------------------------------------------------------------------------------------

CREATE VIEW [dbo].[BLAD_VIEW] AS
SELECT b.id_blad as id, b.temat, b.opis, b.data_zgloszenia, b.rozpatrzono, s.imie + ' ' + s.nazwisko as zglaszajacy
FROM [dbo].BLAD AS b
JOIN [dbo].UZYTKOWNIK as u
ON b.id_uzytkownik = u.id_uzytkownik
JOIN [dbo].STUDENT as s
ON u.id_student = s.id_student
UNION
SELECT b.id_blad as id, b.temat, b.opis, b.data_zgloszenia, b.rozpatrzono, pr.imie + ' ' + pr.nazwisko as zglaszajacy
FROM [dbo].BLAD AS b
JOIN [dbo].UZYTKOWNIK as u
ON b.id_uzytkownik = u.id_uzytkownik
JOIN [dbo].PROWADZACY as pr
ON u.id_prowadzacy = pr.id_prowadzacy
UNION
SELECT b.id_blad as id, b.temat, b.opis, b.data_zgloszenia, b.rozpatrzono, pl.imie + ' ' + pl.nazwisko as zglaszajacy
FROM [dbo].BLAD AS b
JOIN [dbo].UZYTKOWNIK as u
ON b.id_uzytkownik = u.id_uzytkownik
JOIN [dbo].PLANISTA as pl
ON u.id_planista = pl.id_planista
UNION
SELECT b.id_blad as id, b.temat, b.opis, b.data_zgloszenia, b.rozpatrzono, a.imie + ' ' + a.nazwisko as zglaszajacy
FROM [dbo].BLAD AS b
JOIN [dbo].UZYTKOWNIK as u
ON b.id_uzytkownik = u.id_uzytkownik
JOIN [dbo].ADMINISTRATOR as a
ON u.id_administrator = a.id_administrator
UNION
SELECT b.id_blad as id, b.temat, b.opis, b.data_zgloszenia, b.rozpatrzono, d.imie + ' ' + d.nazwisko as zglaszajacy
FROM [dbo].BLAD AS b
JOIN [dbo].UZYTKOWNIK as u
ON b.id_uzytkownik = u.id_uzytkownik
JOIN [dbo].DYREKTOR as d
ON u.id_dyrektor = d.id_dyrektor;
GO

CREATE VIEW [dbo].[UZYTKOWNIK_VIEW] AS
SELECT u.id_uzytkownik as id, 'student' as typ, s.id_student as id_ztypu, s.imie, s.nazwisko, s.PESEL, u.email, u.hash_hasla
FROM [dbo].UZYTKOWNIK AS u
JOIN [dbo].STUDENT as s
ON u.id_student = s.id_student
UNION
SELECT u.id_uzytkownik as id, 'prowadzacy' as typ, pr.id_prowadzacy as id_ztypu, pr.imie, pr.nazwisko, pr.PESEL, u.email, u.hash_hasla
FROM [dbo].UZYTKOWNIK AS u
JOIN [dbo].PROWADZACY as pr
ON u.id_prowadzacy = pr.id_prowadzacy
UNION
SELECT u.id_uzytkownik as id, 'planista' as typ, pl.id_planista as id_ztypu, pl.imie, pl.nazwisko, pl.PESEL, u.email, u.hash_hasla
FROM [dbo].UZYTKOWNIK AS u
JOIN [dbo].PLANISTA as pl
ON u.id_planista = pl.id_planista
UNION
SELECT u.id_uzytkownik as id, 'administrator' as typ, a.id_administrator as id_ztypu, a.imie, a.nazwisko, a.PESEL, u.email, u.hash_hasla
FROM [dbo].UZYTKOWNIK AS u
JOIN [dbo].ADMINISTRATOR as a
ON u.id_administrator = a.id_administrator
UNION
SELECT u.id_uzytkownik as id, 'dyrektor' as typ, d.id_dyrektor as id_ztypu, d.imie, d.nazwisko, d.PESEL, u.email, u.hash_hasla
FROM [dbo].UZYTKOWNIK AS u
JOIN [dbo].DYREKTOR as d
ON u.id_dyrektor = d.id_dyrektor;
GO

CREATE VIEW [dbo].[DYREKTOR_VIEW] AS
SELECT d.id_dyrektor as id, i.nazwa_instytut, d.PESEL, d.imie, d.nazwisko, d.pokoj
FROM [dbo].DYREKTOR AS d
JOIN [dbo].INSTYTUT AS i
ON d.id_instytut = i.id_instytut;
GO

CREATE VIEW [dbo].[INSTYTUT_VIEW] AS
SELECT i.id_instytut as id, w.nazwa_wydzial, i.nazwa_instytut
FROM [dbo].INSTYTUT AS i
JOIN [dbo].WYDZIAL AS w
ON i.id_wydzial = w.id_wydzial;
GO

CREATE VIEW [dbo].[ZAJECIA_VIEW] AS
SELECT z.id_zajecia as id, z.data, z.nr_bloku, s.nr_sala + ' ' + s.budynek AS sala, prz.nazwa_przedmiot, z.typ_zajec,
	pro.stopien + ' ' + pro.imie + ' ' + pro.nazwisko AS prowadzacy, [dbo].[getGrupyZajecia] (z.id_zajecia) AS grupy
FROM [dbo].ZAJECIA AS z
JOIN [dbo].PRZEDMIOT AS prz
ON z.id_przedmiot = prz.id_przedmiot
JOIN [dbo].SALA AS s
ON z.id_sala = s.id_sala
JOIN [dbo].PROWADZACY AS pro
ON z.id_prowadzacy = pro.id_prowadzacy;
GO

CREATE VIEW [dbo].[INSPEKCJA_VIEW] AS
SELECT i.id_inspekcja as id, z.data, z.nr_bloku, z.sala, z.nazwa_przedmiot, z.typ_zajec, z.prowadzacy, z.grupy,
	d.imie + ' ' + d.nazwisko + ' - ' + d.nazwa_instytut AS dyrektor_instytutu, i.komentarz_inspekcja
FROM [dbo].INSPEKCJA AS i
JOIN [dbo].ZAJECIA_VIEW AS z
ON i.id_zajecia = z.id
JOIN [dbo].DYREKTOR_VIEW AS d
ON i.id_dyrektor = d.id;
GO

CREATE VIEW [dbo].[GRUPA_VIEW] AS
SELECT g.id_grupa as id, g.nazwa, g.kierunek, g.rok, u.imie + ' ' + u.nazwisko as starosta
FROM [dbo].GRUPA AS g
LEFT JOIN [dbo].STUDENT as u
ON g.id_starosta = u.id_student;
GO

CREATE VIEW [dbo].[ADMINISTRATOR_VIEW] AS
SELECT a.id_administrator as id, a.PESEL, a.imie, a.nazwisko, a.uprawnienia
FROM ADMINISTRATOR as a;
GO

CREATE VIEW [dbo].[PLANISTA_VIEW] AS
SELECT p.id_planista as id, w.nazwa_wydzial, p.PESEL, p.imie, p.nazwisko
FROM [dbo].PLANISTA AS p
JOIN [dbo].WYDZIAL AS w
ON p.id_wydzial = w.id_wydzial;
GO

CREATE VIEW [dbo].[PROWADZACY_VIEW] as
SELECT id_prowadzacy as id, PESEL, imie, nazwisko, stopien, pokoj 
FROM dbo.prowadzacy;
GO

CREATE VIEW [dbo].[SALA_VIEW] AS
SELECT s.id_sala as id, s.nr_sala, s.budynek, s.typ_sala, s.ilosc_miejsc
FROM [dbo].SALA AS s;
GO;

CREATE VIEW [dbo].[STUDENT_VIEW] as
SELECT s.id_student as id, g.nazwa as nazwa_grupy, s.pesel, s.imie, s.nazwisko
FROM STUDENT s
JOIN GRUPA g
ON s.id_grupa=g.id_grupa;
GO

CREATE VIEW [dbo].[WYDZIAL_VIEW] AS
SELECT w.id_wydzial as id, w.nazwa_wydzial
FROM [dbo].WYDZIAL AS w;
GO

CREATE VIEW [dbo].[ZAPYTANIE_VIEW] AS 
SELECT z.id_zapytanie as id, s.imie + ' ' + s.nazwisko as student, p.stopien + ' ' + p.imie + ' ' + p.nazwisko as prowadzacy, z.tresc_zapytanie, z.decyzja
FROM [dbo].ZAPYTANIE AS z
JOIN [dbo].STUDENT AS s
ON z.id_student = s.id_student
JOIN [dbo].PROWADZACY AS p
ON z.id_prowadzacy = p.id_prowadzacy;
GO

CREATE VIEW [dbo].[WNIOSEK_VIEW] AS
SELECT w.id_wniosek as id, p.stopien + ' ' + p.imie + ' ' + p.nazwisko as prowadzacy, d.imie + ' ' + d.nazwisko + ' - ' + d.nazwa_instytut as dyrektor, w.tresc_wniosek , w.decyzja, w.zmieniono_plan
FROM [dbo].WNIOSEK AS w
JOIN [dbo].PROWADZACY AS p
ON w.id_prowadzacy = p.id_prowadzacy
JOIN [dbo].DYREKTOR_VIEW AS d
ON d.id = w.id_dyrektor;
GO

CREATE VIEW [dbo].[URLOP_VIEW] AS
SELECT u.id_prowadzacy as id_prowadzacy,u.id_urlop as id, p.stopien + ' ' + p.imie + ' ' +  p.nazwisko as prowadzacy, u.data_rozpoczecia, u.data_zakonczenia, u.powod 
FROM [dbo].URLOP as u
JOIN [dbo].PROWADZACY as p
ON u.id_prowadzacy = p.id_prowadzacy;
GO

CREATE VIEW [dbo].[PRZEDMIOT_VIEW] .PRZEDMIOT_VIEW AS
SELECT p.id_przedmiot as id, p.nazwa_przedmiot, i.nazwa_instytut
FROM Przedmiot as p
JOIN Instytut as i
ON p.id_instytut = i.id_instytut;
GO

CREATE VIEW [dbo].[KONSULTACJA_VIEW] AS 
SELECT k.id_konsultacja AS id, k.data, k.nr_bloku, s.nr_sala + ' ' + s.budynek AS sala, p.stopien + ' ' + p.imie + ' ' + p.nazwisko AS prowadzacy, k.komentarz
FROM KONSULTACJA AS k
JOIN PROWADZACY AS p
ON k.id_prowadzacy = p.id_prowadzacy
JOIN SALA AS s
ON k.id_sala = s.id_sala
UNION
SELECT k.id_konsultacja AS id, k.data, k.nr_bloku, ' ' AS sala, p.stopien + ' ' + p.imie + ' ' + p.nazwisko AS prowadzacy, k.komentarz
FROM KONSULTACJA AS k
JOIN PROWADZACY AS p
ON k.id_prowadzacy = p.id_prowadzacy
WHERE k.id_sala IS NULL;
GO

CREATE VIEW [dbo].[WIADOMOSC_VIEW] AS
SELECT w.id_wiadomosc as id, n.imie + ' ' + n.nazwisko as nadawca, o.imie + ' ' + o.nazwisko as odbiorca, w.temat, w.tresc, w.data_wyslania
FROM [dbo].WIADOMOSC AS w
JOIN [dbo].UZYTKOWNIK_VIEW AS n
ON w.id_nadawca = n.id
JOIN [dbo].UZYTKOWNIK_VIEW AS o
ON w.id_odbiorca = o.id;
GO