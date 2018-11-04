--SQL SERVER 2012

--------------------------------------------------------------------------------------------------
--TABELE
--------------------------------------------------------------------------------------------------
CREATE TABLE [ADMINISTRATOR] (
	[id_administrator] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[PESEL] numeric(11, 0) NOT NULL, 
	[imie] varchar(MAX) NOT NULL, 
	[nazwisko] varchar(MAX) NOT NULL, 
	[uprawnienia] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [STUDENT] (
	[id_student] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_grupa] int NOT NULL, 
	[PESEL] numeric(11, 0) NOT NULL, 
	[imie] varchar(MAX) NOT NULL, 
	[nazwisko] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [GRUPA] (
	[id_grupa] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[nazwa] varchar(MAX) NOT NULL, 
	[kierunek] varchar(MAX) NOT NULL, 
	[rok] int NOT NULL,
	[id_starosta] int
) ON [PRIMARY]
GO

CREATE TABLE [ANKIETA] (
	[id_ankieta] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_grupa] int NOT NULL, 
	[pytanie] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [ANKIETA_ODPOWIEDZI] (
	[id_ankieta_odpowiedzi] int NOT NULL IDENTITY(1,1) PRIMARY KEY CLUSTERED,
	[id_ankieta] int NOT NULL,
	[id_odpowiedz] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [ODPOWIEDZ_ANKIETA] (
	[id_odpowiedz] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_student] int NOT NULL, 
	[tresc_odpowiedz] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [UZYTKOWNIK] (
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

CREATE TABLE [BLAD] (
	[id_blad] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_uzytkownik] int NOT NULL, 
	[temat] varchar(MAX) NOT NULL, 
	[opis] varchar(MAX) NOT NULL, 
	[data_zgloszenia] datetime NOT NULL, 
	[rozpatrzono] bit NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [WYDZIAL] (
	[id_wydzial] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[nazwa_wydzial] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [INSTYTUT] (
	[id_instytut] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_wydzial] int NOT NULL, 
	[nazwa_instytut] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [DYREKTOR] (
	[id_dyrektor] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_instytut] int NOT NULL, 
	[PESEL] numeric(11, 0) NOT NULL, 
	[imie] varchar(MAX) NOT NULL, 
	[nazwisko] varchar(MAX) NOT NULL, 
	[pokoj] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [ZAJECIA] (
	[id_zajecia] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED,
	[data] date NOT NULL, 
	[nr_bloku] int NOT NULL, 
	[id_sala] int NOT NULL, 
	[id_przedmiot] int NOT NULL, 
	[typ_zajec] varchar(MAX) NOT NULL, 
	[id_prowadzacy] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [INSPEKCJA] (
	[id_inspekcja] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_zajecia] int NOT NULL, 
	[id_dyrektor] int NOT NULL, 
	[komentarz_inspekcja] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [PROWADZACY] (
	[id_prowadzacy] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[PESEL] numeric(11, 0) NOT NULL, 
	[imie] varchar(MAX) NOT NULL, 
	[nazwisko] varchar(MAX) NOT NULL, 
	[stopien] varchar(MAX) NOT NULL, 
	[pokoj] varchar(MAX)
) ON [PRIMARY]
GO

CREATE TABLE [SALA] (
	[id_sala] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[nr_sala] varchar(MAX) NOT NULL, 
	[budynek] varchar(MAX) NOT NULL, 
	[typ_sala] varchar(MAX) NOT NULL, 
	[ilosc_miejsc] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [KONSULTACJA] (
	[id_konsultacja] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED,
	[data] date NOT NULL, 
	[nr_bloku] int NOT NULL,
	[id_sala] int, 
	[id_prowadzacy] int NOT NULL, 
	[komentarz] varchar(MAX)
) ON [PRIMARY]
GO

CREATE TABLE [PLANISTA] (
	[id_planista] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_wydzial] int NOT NULL, 
	[PESEL] numeric(11, 0) NOT NULL, 
	[imie] varchar(MAX) NOT NULL, 
	[nazwisko] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [PRZEDMIOT] (
	[id_przedmiot] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_instytut] int NOT NULL, 
	[nazwa_przedmiot] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [URLOP] (
	[id_urlop] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_prowadzacy] int NOT NULL, 
	[data_rozpoczecia] date NOT NULL, 
	[data_zakonczenia] date NOT NULL, 
	[powod] varchar(MAX) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [WIADOMOSC] (
	[id_wiadomosc] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_nadawca] int NOT NULL, 
	[temat] varchar(MAX) NOT NULL, 
	[tresc] varchar(MAX) NOT NULL, 
	[data_wyslania] datetime NOT NULL,
	[id_odbiorca] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [WIADOMOSC_ODBIORCY] (
	[id_wiadomosc_odbiorcy] int NOT NULL IDENTITY(1,1) PRIMARY KEY CLUSTERED,
	[id_wiadomosc] int NOT NULL,
	[id_uzytkownik] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [WNIOSEK] (
	[id_wniosek] int NOT NULL IDENTITY(1,1) PRIMARY KEY NONCLUSTERED, 
	[id_prowadzacy] int NOT NULL, 
	[id_dyrektor] int NOT NULL, 
	[tresc_wniosek] varchar(MAX) NOT NULL, 
	[decyzja] bit NOT NULL, 
	[zmieniono_plan] bit NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [ZAJECIA_GRUPY] (
	[id_zajecia_grupy] int NOT NULL IDENTITY(1,1) PRIMARY KEY CLUSTERED,
	[id_zajecia] int NOT NULL,
	[id_grupa] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [ZAJECIA_OBECNOSC] (
	[id_zajecia_obecnosc] int NOT NULL IDENTITY(1,1) PRIMARY KEY CLUSTERED,
	[id_zajecia] int NOT NULL,
	[id_student] int NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [ZAPYTANIE] (
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

ALTER TABLE [PRZEDMIOT]
ADD CONSTRAINT FK_PRZEDMIOT_INSTYTUT FOREIGN KEY ([id_instytut])
		REFERENCES [INSTYTUT] ([id_instytut])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [URLOP]
ADD CONSTRAINT FK_URLOP_PROWADZACY FOREIGN KEY ([id_prowadzacy])
		REFERENCES [PROWADZACY] ([id_prowadzacy])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [WIADOMOSC]
ADD CONSTRAINT FK_WIADOMOSC_NADAWCA FOREIGN KEY ([id_nadawca])
		REFERENCES [UZYTKOWNIK] ([id_uzytkownik])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [WIADOMOSC]
ADD CONSTRAINT FK_WIADOMOSC_ODBIORCA FOREIGN KEY ([id_odbiorca])
		REFERENCES [UZYTKOWNIK] ([id_uzytkownik])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [WIADOMOSC_ODBIORCY]
ADD CONSTRAINT FK_WIADOMOSC_ODBIORCY_WIADOMOSC FOREIGN KEY ([id_wiadomosc])
		REFERENCES [WIADOMOSC] ([id_wiadomosc])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [WIADOMOSC_ODBIORCY]
ADD CONSTRAINT FK_WIADOMOSC_ODBIORCY_UZYTKOWNIK FOREIGN KEY ([id_uzytkownik])
		REFERENCES [UZYTKOWNIK] ([id_uzytkownik])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [WNIOSEK]
ADD CONSTRAINT FK_WNIOSEK_DYREKTOR FOREIGN KEY ([id_dyrektor])
		REFERENCES [DYREKTOR] ([id_dyrektor])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION 
	;
GO

ALTER TABLE [WNIOSEK]
ADD CONSTRAINT FK_WNIOSEK_PROWADZACY FOREIGN KEY ([id_prowadzacy])
		REFERENCES [PROWADZACY] ([id_prowadzacy])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [ZAJECIA_GRUPY]
ADD CONSTRAINT FK_ZAJECIA_GRUPY_ZAJECIA FOREIGN KEY ([id_zajecia])
		REFERENCES [ZAJECIA] ([id_zajecia])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [ZAJECIA_GRUPY]
ADD CONSTRAINT FK_ZAJECIA_GRUPY_GRUPA FOREIGN KEY ([id_grupa])
		REFERENCES [GRUPA] ([id_grupa])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [ZAJECIA_OBECNOSC]
ADD CONSTRAINT FK_ZAJECIA_OBECNOSC_STUDENT FOREIGN KEY ([id_student])
		REFERENCES [STUDENT] ([id_student])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [ZAJECIA_OBECNOSC]
ADD CONSTRAINT FK_ZAJECIA_OBECNOSC_ZAJECIA FOREIGN KEY ([id_zajecia])
		REFERENCES [ZAJECIA] ([id_zajecia])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [ZAPYTANIE]
ADD CONSTRAINT FK_ZAPYTANIE_PROWADZACY FOREIGN KEY ([id_prowadzacy])
		REFERENCES [PROWADZACY] ([id_prowadzacy])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [ZAPYTANIE]
ADD CONSTRAINT FK_ZAPYTANIE_STUDENT FOREIGN KEY ([id_student])
		REFERENCES [STUDENT] ([id_student])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

ALTER TABLE [STUDENT]
ADD CONSTRAINT FK_STUDENT_GRUPA FOREIGN KEY ([id_grupa]) 
    REFERENCES [GRUPA] ([id_grupa]) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
;
GO

ALTER TABLE [GRUPA]
ADD CONSTRAINT FK_GRUPA_STUDENT FOREIGN KEY ([id_starosta]) 
    REFERENCES [STUDENT] ([id_student]) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
;
GO

ALTER TABLE [ANKIETA]
ADD CONSTRAINT FK_ANKIETA_GRUPA FOREIGN KEY ([id_grupa])
	REFERENCES [GRUPA] ([id_grupa])
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
;
GO

ALTER TABLE [ODPOWIEDZ_ANKIETA]
ADD CONSTRAINT FK_ODPOWIEDZ_ANKIETA_STUDENT FOREIGN KEY ([id_student])
	REFERENCES [STUDENT] ([id_student])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;
GO

ALTER TABLE [ANKIETA_ODPOWIEDZI]
ADD CONSTRAINT FK_ANKIETA_ODPOWIEDZI_ODPOWIEDZ_ANKIETA FOREIGN KEY ([id_odpowiedz])
	REFERENCES [ODPOWIEDZ_ANKIETA] ([id_odpowiedz])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [ANKIETA_ODPOWIEDZI]
ADD CONSTRAINT FK_ANKIETA_ODPOWIEDZI_ANKIETA FOREIGN KEY ([id_ankieta])
	REFERENCES [ANKIETA] ([id_ankieta])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [UZYTKOWNIK]
ADD CONSTRAINT FK_UZYTKOWNIK_ADMINISTRATOR FOREIGN KEY ([id_administrator])
	REFERENCES [ADMINISTRATOR] ([id_administrator])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [UZYTKOWNIK]
ADD CONSTRAINT FK_UZYTKOWNIK_PLANISTA FOREIGN KEY ([id_planista])
	REFERENCES [PLANISTA] ([id_planista])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [UZYTKOWNIK]
ADD CONSTRAINT FK_UZYTKOWNIK_STUDENT FOREIGN KEY ([id_student])
	REFERENCES [STUDENT] ([id_student])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [UZYTKOWNIK]
ADD CONSTRAINT FK_UZYTKOWNIK_PROWADZACY FOREIGN KEY ([id_prowadzacy])
	REFERENCES [PROWADZACY] ([id_prowadzacy])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [UZYTKOWNIK]
ADD CONSTRAINT FK_UZYTKOWNIK_DYREKTOR FOREIGN KEY ([id_dyrektor])
	REFERENCES [DYREKTOR] ([id_dyrektor])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [BLAD]
ADD CONSTRAINT FK_BLAD_UZYTKOWNIK FOREIGN KEY ([id_uzytkownik])
	REFERENCES [UZYTKOWNIK] ([id_uzytkownik])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [INSTYTUT]
ADD CONSTRAINT FK_INSTYTUT_WYDZIAL FOREIGN KEY ([id_wydzial])
	REFERENCES [WYDZIAL] ([id_wydzial])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [DYREKTOR]
ADD CONSTRAINT FK_DYREKTOR_INSTYTUT FOREIGN KEY ([id_instytut])
	REFERENCES [INSTYTUT] ([id_instytut])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [ZAJECIA]
ADD CONSTRAINT FK_ZAJECIA_SALA FOREIGN KEY ([id_sala])
	REFERENCES [SALA] ([id_sala])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [ZAJECIA]
ADD CONSTRAINT FK_ZAJECIA_PRZEDMIOT FOREIGN KEY ([id_przedmiot])
	REFERENCES [PRZEDMIOT] ([id_przedmiot])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [ZAJECIA]
ADD CONSTRAINT FK_ZAJECIA_PROWADZACY FOREIGN KEY ([id_prowadzacy])
	REFERENCES [PROWADZACY] ([id_prowadzacy])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [INSPEKCJA]
ADD CONSTRAINT FK_INSPEKCJA_DYREKTOR FOREIGN KEY ([id_dyrektor])
	REFERENCES [DYREKTOR] ([id_dyrektor])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [INSPEKCJA]
ADD CONSTRAINT FK_INSPEKCJA_ZAJECIA FOREIGN KEY ([id_zajecia])
	REFERENCES [ZAJECIA] ([id_zajecia])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [KONSULTACJA]
ADD CONSTRAINT FK_KONSULTACJA_PROWADZACY FOREIGN KEY ([id_prowadzacy])
	REFERENCES [PROWADZACY] ([id_prowadzacy])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [KONSULTACJA]
ADD CONSTRAINT FK_KONSULTACJA_SALA FOREIGN KEY ([id_sala])
	REFERENCES [SALA] ([id_sala])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	;
GO

ALTER TABLE [PLANISTA]
ADD CONSTRAINT FK_PLANISTA_WYDZIAL FOREIGN KEY ([id_wydzial])
		REFERENCES [WYDZIAL] ([id_wydzial])
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
	;
GO

--------------------------------------------------------------------------------------------------
--FUNKCJE
--------------------------------------------------------------------------------------------------

CREATE FUNCTION [getGrupyZajecia] (@x int)
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
GO

--------------------------------------------------------------------------------------------------
--WIDOKI
--------------------------------------------------------------------------------------------------

CREATE VIEW [BLAD_VIEW] AS
SELECT b.id_blad as id, b.temat, b.opis, b.data_zgloszenia, b.rozpatrzono, s.imie + ' ' + s.nazwisko as zglaszajacy
FROM BLAD AS b
JOIN UZYTKOWNIK as u
ON b.id_uzytkownik = u.id_uzytkownik
JOIN STUDENT as s
ON u.id_student = s.id_student
UNION
SELECT b.id_blad as id, b.temat, b.opis, b.data_zgloszenia, b.rozpatrzono, pr.imie + ' ' + pr.nazwisko as zglaszajacy
FROM BLAD AS b
JOIN UZYTKOWNIK as u
ON b.id_uzytkownik = u.id_uzytkownik
JOIN PROWADZACY as pr
ON u.id_prowadzacy = pr.id_prowadzacy
UNION
SELECT b.id_blad as id, b.temat, b.opis, b.data_zgloszenia, b.rozpatrzono, pl.imie + ' ' + pl.nazwisko as zglaszajacy
FROM BLAD AS b
JOIN UZYTKOWNIK as u
ON b.id_uzytkownik = u.id_uzytkownik
JOIN PLANISTA as pl
ON u.id_planista = pl.id_planista
UNION
SELECT b.id_blad as id, b.temat, b.opis, b.data_zgloszenia, b.rozpatrzono, a.imie + ' ' + a.nazwisko as zglaszajacy
FROM BLAD AS b
JOIN UZYTKOWNIK as u
ON b.id_uzytkownik = u.id_uzytkownik
JOIN ADMINISTRATOR as a
ON u.id_administrator = a.id_administrator
UNION
SELECT b.id_blad as id, b.temat, b.opis, b.data_zgloszenia, b.rozpatrzono, d.imie + ' ' + d.nazwisko as zglaszajacy
FROM BLAD AS b
JOIN UZYTKOWNIK as u
ON b.id_uzytkownik = u.id_uzytkownik
JOIN DYREKTOR as d
ON u.id_dyrektor = d.id_dyrektor;
GO

CREATE VIEW [UZYTKOWNIK_VIEW] AS
SELECT u.id_uzytkownik as id, 'student' as typ, s.id_student as id_ztypu, s.imie, s.nazwisko, s.PESEL, u.email, u.hash_hasla
FROM UZYTKOWNIK AS u
JOIN STUDENT as s
ON u.id_student = s.id_student
UNION
SELECT u.id_uzytkownik as id, 'prowadzacy' as typ, pr.id_prowadzacy as id_ztypu, pr.imie, pr.nazwisko, pr.PESEL, u.email, u.hash_hasla
FROM UZYTKOWNIK AS u
JOIN PROWADZACY as pr
ON u.id_prowadzacy = pr.id_prowadzacy
UNION
SELECT u.id_uzytkownik as id, 'planista' as typ, pl.id_planista as id_ztypu, pl.imie, pl.nazwisko, pl.PESEL, u.email, u.hash_hasla
FROM UZYTKOWNIK AS u
JOIN PLANISTA as pl
ON u.id_planista = pl.id_planista
UNION
SELECT u.id_uzytkownik as id, 'administrator' as typ, a.id_administrator as id_ztypu, a.imie, a.nazwisko, a.PESEL, u.email, u.hash_hasla
FROM UZYTKOWNIK AS u
JOIN ADMINISTRATOR as a
ON u.id_administrator = a.id_administrator
UNION
SELECT u.id_uzytkownik as id, 'dyrektor' as typ, d.id_dyrektor as id_ztypu, d.imie, d.nazwisko, d.PESEL, u.email, u.hash_hasla
FROM UZYTKOWNIK AS u
JOIN DYREKTOR as d
ON u.id_dyrektor = d.id_dyrektor;
GO

CREATE VIEW [DYREKTOR_VIEW] AS
SELECT d.id_dyrektor as id, i.nazwa_instytut, d.PESEL, d.imie, d.nazwisko, d.pokoj
FROM DYREKTOR AS d
JOIN INSTYTUT AS i
ON d.id_instytut = i.id_instytut;
GO

CREATE VIEW [INSTYTUT_VIEW] AS
SELECT i.id_instytut as id, w.nazwa_wydzial, i.nazwa_instytut
FROM INSTYTUT AS i
JOIN WYDZIAL AS w
ON i.id_wydzial = w.id_wydzial;
GO

CREATE VIEW [ZAJECIA_VIEW] AS
SELECT z.id_zajecia as id, z.data, z.nr_bloku, s.nr_sala + ' ' + s.budynek AS sala, prz.nazwa_przedmiot, z.typ_zajec,
	pro.stopien + ' ' + pro.imie + ' ' + pro.nazwisko AS prowadzacy, dbo.getGrupyZajecia (z.id_zajecia) AS grupy
FROM ZAJECIA AS z
JOIN PRZEDMIOT AS prz
ON z.id_przedmiot = prz.id_przedmiot
JOIN SALA AS s
ON z.id_sala = s.id_sala
JOIN PROWADZACY AS pro
ON z.id_prowadzacy = pro.id_prowadzacy;
GO

CREATE VIEW [INSPEKCJA_VIEW] AS
SELECT i.id_inspekcja as id, z.data, z.nr_bloku, z.sala, z.nazwa_przedmiot, z.typ_zajec, z.prowadzacy, z.grupy,
	d.imie + ' ' + d.nazwisko + ' - ' + d.nazwa_instytut AS dyrektor_instytutu, i.komentarz_inspekcja
FROM INSPEKCJA AS i
JOIN ZAJECIA_VIEW AS z
ON i.id_zajecia = z.id
JOIN DYREKTOR_VIEW AS d
ON i.id_dyrektor = d.id;
GO

CREATE VIEW [GRUPA_VIEW] AS
SELECT g.id_grupa as id, g.nazwa, g.kierunek, g.rok, u.imie + ' ' + u.nazwisko as starosta
FROM GRUPA AS g
LEFT JOIN STUDENT as u
ON g.id_starosta = u.id_student;
GO

CREATE VIEW [ADMINISTRATOR_VIEW] AS
SELECT a.id_administrator as id, a.PESEL, a.imie, a.nazwisko, a.uprawnienia
FROM ADMINISTRATOR as a;
GO

CREATE VIEW [PLANISTA_VIEW] AS
SELECT p.id_planista as id, w.nazwa_wydzial, p.PESEL, p.imie, p.nazwisko
FROM PLANISTA AS p
JOIN WYDZIAL AS w
ON p.id_wydzial = w.id_wydzial;
GO

CREATE VIEW [PROWADZACY_VIEW] as
SELECT id_prowadzacy as id, PESEL, imie, nazwisko, stopien, pokoj 
FROM PROWADZACY;
GO

CREATE VIEW [SALA_VIEW] AS
SELECT s.id_sala as id, s.nr_sala, s.budynek, s.typ_sala, s.ilosc_miejsc
FROM SALA AS s;
GO

CREATE VIEW [STUDENT_VIEW] as
SELECT s.id_student as id, g.nazwa as nazwa_grupy, s.pesel, s.imie, s.nazwisko
FROM STUDENT s
JOIN GRUPA g
ON s.id_grupa=g.id_grupa;
GO

CREATE VIEW [WYDZIAL_VIEW] AS
SELECT w.id_wydzial as id, w.nazwa_wydzial
FROM WYDZIAL AS w;
GO

CREATE VIEW [ZAPYTANIE_VIEW] AS 
SELECT z.id_zapytanie as id, s.imie + ' ' + s.nazwisko as student, p.stopien + ' ' + p.imie + ' ' + p.nazwisko as prowadzacy, z.tresc_zapytanie, z.decyzja
FROM ZAPYTANIE AS z
JOIN STUDENT AS s
ON z.id_student = s.id_student
JOIN PROWADZACY AS p
ON z.id_prowadzacy = p.id_prowadzacy;
GO

CREATE VIEW [WNIOSEK_VIEW] AS
SELECT w.id_wniosek as id, p.stopien + ' ' + p.imie + ' ' + p.nazwisko as prowadzacy, d.imie + ' ' + d.nazwisko + ' - ' + d.nazwa_instytut as dyrektor, w.tresc_wniosek , w.decyzja, w.zmieniono_plan
FROM WNIOSEK AS w
JOIN PROWADZACY AS p
ON w.id_prowadzacy = p.id_prowadzacy
JOIN DYREKTOR_VIEW AS d
ON d.id = w.id_dyrektor;
GO

CREATE VIEW [URLOP_VIEW] AS
SELECT u.id_prowadzacy as id_prowadzacy,u.id_urlop as id, p.stopien + ' ' + p.imie + ' ' +  p.nazwisko as prowadzacy, u.data_rozpoczecia, u.data_zakonczenia, u.powod 
FROM URLOP as u
JOIN PROWADZACY as p
ON u.id_prowadzacy = p.id_prowadzacy;
GO

CREATE VIEW [PRZEDMIOT_VIEW] AS
SELECT p.id_przedmiot as id, p.nazwa_przedmiot, i.nazwa_instytut
FROM Przedmiot as p
JOIN Instytut as i
ON p.id_instytut = i.id_instytut;
GO

CREATE VIEW [KONSULTACJA_VIEW] AS 
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

CREATE VIEW [WIADOMOSC_VIEW] AS
SELECT w.id_wiadomosc as id, n.imie + ' ' + n.nazwisko as nadawca, o.imie + ' ' + o.nazwisko as odbiorca, w.temat, w.tresc, w.data_wyslania
FROM WIADOMOSC AS w
JOIN UZYTKOWNIK_VIEW AS n
ON w.id_nadawca = n.id
JOIN UZYTKOWNIK_VIEW AS o
ON w.id_odbiorca = o.id;
GO