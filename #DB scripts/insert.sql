﻿INSERT INTO [WYDZIAL] ([nazwa_wydzial]) VALUES (N'WCY');
INSERT INTO [WYDZIAL] ([nazwa_wydzial]) VALUES (N'WEL');
INSERT INTO [WYDZIAL] ([nazwa_wydzial]) VALUES (N'WNT');
INSERT INTO [WYDZIAL] ([nazwa_wydzial]) VALUES (N'LOLz');
INSERT INTO [WYDZIAL] ([nazwa_wydzial]) VALUES (N'WIG');

﻿INSERT INTO [INSTYTUT] ([id_wydzial],[nazwa_instytut]) VALUES (5,N'Rysunków');
INSERT INTO [INSTYTUT] ([id_wydzial],[nazwa_instytut]) VALUES (5,N'PIP');
INSERT INTO [INSTYTUT] ([id_wydzial],[nazwa_instytut]) VALUES (3,N'IMK');
INSERT INTO [INSTYTUT] ([id_wydzial],[nazwa_instytut]) VALUES (2,N'ITA');
INSERT INTO [INSTYTUT] ([id_wydzial],[nazwa_instytut]) VALUES (1,N'ISI');
INSERT INTO [INSTYTUT] ([id_wydzial],[nazwa_instytut]) VALUES (1,N'IMiK');

﻿INSERT INTO [DYREKTOR] ([id_instytut],[PESEL],[imie],[nazwisko],[pokoj]) VALUES (1,76052114589,N'Janusz',N'Panasewicz',N'111 65');
INSERT INTO [DYREKTOR] ([id_instytut],[PESEL],[imie],[nazwisko],[pokoj]) VALUES (4,87041367345,N'Tadeusz',N'Borówka',N'222 100');
INSERT INTO [DYREKTOR] ([id_instytut],[PESEL],[imie],[nazwisko],[pokoj]) VALUES (5,60101043132,N'Józef',N'Pan',N'134 65');
INSERT INTO [DYREKTOR] ([id_instytut],[PESEL],[imie],[nazwisko],[pokoj]) VALUES (2,88021349786,N'Bozydar',N'Iwanow',N'200 13');
INSERT INTO [DYREKTOR] ([id_instytut],[PESEL],[imie],[nazwisko],[pokoj]) VALUES (3,70121203453,N'Andrzej',N'Boryna',N'111 25');

﻿INSERT INTO [SALA] ([nr_sala],[budynek],[typ_sala],[ilosc_miejsc]) VALUES (N'120',N'65',N'l',30);
INSERT INTO [SALA] ([nr_sala],[budynek],[typ_sala],[ilosc_miejsc]) VALUES (N'121',N'65',N'l',20);
INSERT INTO [SALA] ([nr_sala],[budynek],[typ_sala],[ilosc_miejsc]) VALUES (N'151',N'65',N'c',25);

﻿INSERT INTO [GRUPA] ([id_starosta],[nazwa],[kierunek],[rok]) VALUES (NULL,N'I4G3S1',N'Informatyka',2014);
INSERT INTO [GRUPA] ([id_starosta],[nazwa],[kierunek],[rok]) VALUES (NULL,N'I4G2S1',N'Informatyka',2014);
INSERT INTO [GRUPA] ([id_starosta],[nazwa],[kierunek],[rok]) VALUES (NULL,N'I4M1S1',N'Informatyka',2014);
INSERT INTO [GRUPA] ([id_starosta],[nazwa],[kierunek],[rok]) VALUES (NULL,N'I4B1S1',N'Informatyka',2014);

﻿INSERT INTO [STUDENT] ([id_grupa],[PESEL],[imie],[nazwisko]) VALUES (3,93021745678,N'Stanislaw',N'Adamski');
INSERT INTO [STUDENT] ([id_grupa],[PESEL],[imie],[nazwisko]) VALUES (3,94121212145,N'Janusz',N'Tracz');
INSERT INTO [STUDENT] ([id_grupa],[PESEL],[imie],[nazwisko]) VALUES (3,45645645442,N'Franek',N'Plebs');
INSERT INTO [STUDENT] ([id_grupa],[PESEL],[imie],[nazwisko]) VALUES (9,95122503636,N'Arek',N'Korek');

INSERT INTO [ADMINISTRATOR] ([PESEL],[imie],[nazwisko],[uprawnienia]) VALUES (84012378945,N'Adam',N'Router',N'www');
INSERT INTO [ADMINISTRATOR] ([PESEL],[imie],[nazwisko],[uprawnienia]) VALUES (45023186043,N'Twój',N'Stary',N'w');
INSERT INTO [ADMINISTRATOR] ([PESEL],[imie],[nazwisko],[uprawnienia]) VALUES (90130287645,N'Tomasz',N'Comasz',N'r');
INSERT INTO [ADMINISTRATOR] ([PESEL],[imie],[nazwisko],[uprawnienia]) VALUES (12345678921,N'Adam',N'Roslon',N'wr');
INSERT INTO [ADMINISTRATOR] ([PESEL],[imie],[nazwisko],[uprawnienia]) VALUES (12221345645,N'Jeheszki',N'Eloszki',N'xd');
INSERT INTO [ADMINISTRATOR] ([PESEL],[imie],[nazwisko],[uprawnienia]) VALUES (90122704938,N'Kamil',N'Cugowski',N'w');

﻿INSERT INTO [PROWADZACY] ([PESEL],[imie],[nazwisko],[stopien],[pokoj]) VALUES (83042378651,N'Ryszard',N'Sweter',N'Inz.',N'000');
INSERT INTO [PROWADZACY] ([PESEL],[imie],[nazwisko],[stopien],[pokoj]) VALUES (73210378945,N'Tadeusz',N'Zuczek',N'Dok.',N'456');
INSERT INTO [PROWADZACY] ([PESEL],[imie],[nazwisko],[stopien],[pokoj]) VALUES (86022816789,N'Stefan',N'Kamyk',N'Mgr.',N'');
INSERT INTO [PROWADZACY] ([PESEL],[imie],[nazwisko],[stopien],[pokoj]) VALUES (92522216655,N'Ula',N'Bzdura',N'Mgr.',N'137');
INSERT INTO [PROWADZACY] ([PESEL],[imie],[nazwisko],[stopien],[pokoj]) VALUES (22254645612,N'Andrzej',N'Stasiak',N'Dok.',NULL);
INSERT INTO [PROWADZACY] ([PESEL],[imie],[nazwisko],[stopien],[pokoj]) VALUES (85122703939,N'Jan',N'Wisla',N'Mgr.',N'120 65');
INSERT INTO [PROWADZACY] ([PESEL],[imie],[nazwisko],[stopien],[pokoj]) VALUES (85122207863,N'Andrzej',N'Wajda',N'inz.',N'111 65');

﻿INSERT INTO [KONSULTACJA] ([id_prowadzacy],[id_sala],[data],[nr_bloku],[komentarz]) VALUES (1,1,'20180102',3,N'ppp');
INSERT INTO [KONSULTACJA] ([id_prowadzacy],[id_sala],[data],[nr_bloku],[komentarz]) VALUES (2,NULL,'20180125',2,N'lolz');
INSERT INTO [KONSULTACJA] ([id_prowadzacy],[id_sala],[data],[nr_bloku],[komentarz]) VALUES (3,2,'20180107',5,N'llI');
INSERT INTO [KONSULTACJA] ([id_prowadzacy],[id_sala],[data],[nr_bloku],[komentarz]) VALUES (13,1,'20180127',1,N'dodatkowe konsultacje');
INSERT INTO [KONSULTACJA] ([id_prowadzacy],[id_sala],[data],[nr_bloku],[komentarz]) VALUES (3,NULL,'20180115',4,N'');
INSERT INTO [KONSULTACJA] ([id_prowadzacy],[id_sala],[data],[nr_bloku],[komentarz]) VALUES (7,2,'20180117',5,N'');

﻿INSERT INTO [PLANISTA] ([id_wydzial],[PESEL],[imie],[nazwisko]) VALUES (2,12345678945,N'Jan',N'Komar');
INSERT INTO [PLANISTA] ([id_wydzial],[PESEL],[imie],[nazwisko]) VALUES (1,74121287412,N'Pawel',N'Kos');
INSERT INTO [PLANISTA] ([id_wydzial],[PESEL],[imie],[nazwisko]) VALUES (3,70101203935,N'Karol',N'Koc');

﻿INSERT INTO [PRZEDMIOT] ([id_instytut],[nazwa_przedmiot]) VALUES (4,N'TPST');
INSERT INTO [PRZEDMIOT] ([id_instytut],[nazwa_przedmiot]) VALUES (5,N'medy');
INSERT INTO [PRZEDMIOT] ([id_instytut],[nazwa_przedmiot]) VALUES (4,N'kdm');
INSERT INTO [PRZEDMIOT] ([id_instytut],[nazwa_przedmiot]) VALUES (5,N'ALS');
INSERT INTO [PRZEDMIOT] ([id_instytut],[nazwa_przedmiot]) VALUES (4,N'Projekt Zespolowy');

﻿INSERT INTO [URLOP] ([id_prowadzacy],[data_rozpoczecia],[data_zakonczenia],[powod]) VALUES (2,'20180108','20180116',N'Delegacja');
INSERT INTO [URLOP] ([id_prowadzacy],[data_rozpoczecia],[data_zakonczenia],[powod]) VALUES (2,'20180101','20180105',N'Test aplikacji Andrzejek');

﻿INSERT INTO [UZYTKOWNIK] ([id_planista],[id_administrator],[id_dyrektor],[id_prowadzacy],[id_student],[hash_hasla],[email]) VALUES (NULL,1,NULL,NULL,NULL,N'regej',N'test2@testowy.com');
INSERT INTO [UZYTKOWNIK] ([id_planista],[id_administrator],[id_dyrektor],[id_prowadzacy],[id_student],[hash_hasla],[email]) VALUES (NULL,NULL,NULL,1,NULL,N'hjor67u43',N'test3@testowy.com');
INSERT INTO [UZYTKOWNIK] ([id_planista],[id_administrator],[id_dyrektor],[id_prowadzacy],[id_student],[hash_hasla],[email]) VALUES (NULL,NULL,NULL,NULL,2,N'klgilf',N'test1@testowy.com');
INSERT INTO [UZYTKOWNIK] ([id_planista],[id_administrator],[id_dyrektor],[id_prowadzacy],[id_student],[hash_hasla],[email]) VALUES (NULL,NULL,NULL,2,NULL,N'adswad',N'gbzinkowski@gmail.com');
INSERT INTO [UZYTKOWNIK] ([id_planista],[id_administrator],[id_dyrektor],[id_prowadzacy],[id_student],[hash_hasla],[email]) VALUES (NULL,NULL,NULL,3,NULL,N'asda',N'ptk.adamek@gmail.com');
INSERT INTO [UZYTKOWNIK] ([id_planista],[id_administrator],[id_dyrektor],[id_prowadzacy],[id_student],[hash_hasla],[email]) VALUES (NULL,NULL,NULL,7,NULL,N'sadas',N'tomasz.roslon@student.wat.edu.pl');
INSERT INTO [UZYTKOWNIK] ([id_planista],[id_administrator],[id_dyrektor],[id_prowadzacy],[id_student],[hash_hasla],[email]) VALUES (NULL,NULL,NULL,13,NULL,N'asdasdas',N'michal.krysztofik@student.wat.edu.pl');
INSERT INTO [UZYTKOWNIK] ([id_planista],[id_administrator],[id_dyrektor],[id_prowadzacy],[id_student],[hash_hasla],[email]) VALUES (NULL,NULL,NULL,14,NULL,N'asda',N'konrad.czarkowski@student.wat.edu.pl');
INSERT INTO [UZYTKOWNIK] ([id_planista],[id_administrator],[id_dyrektor],[id_prowadzacy],[id_student],[hash_hasla],[email]) VALUES (NULL,NULL,NULL,17,NULL,N'asdasdad',N'andrzej.stasiak@wat.edu.pl');

﻿INSERT INTO [BLAD] ([id_uzytkownik],[temat],[opis],[data_zgloszenia],[rozpatrzono]) VALUES (2,N'Plan zajec',N'Brak mozliwosci podgladu planu zajec','20171003 15:15:14.557',1);
INSERT INTO [BLAD] ([id_uzytkownik],[temat],[opis],[data_zgloszenia],[rozpatrzono]) VALUES (4,N'Logowanie',N'Problem z zalogowaniem do systemu','20171205 12:35:29.123',0);
INSERT INTO [BLAD] ([id_uzytkownik],[temat],[opis],[data_zgloszenia],[rozpatrzono]) VALUES (3,N'Logowanie',N'nie dziala','20171227 22:13:02.190',0);
INSERT INTO [BLAD] ([id_uzytkownik],[temat],[opis],[data_zgloszenia],[rozpatrzono]) VALUES (2,N'Ankieta',N'nie ma','20171230 10:12:16.257',0);
INSERT INTO [BLAD] ([id_uzytkownik],[temat],[opis],[data_zgloszenia],[rozpatrzono]) VALUES (4,N'listy obecnosci',N'brak listy obecnosci','20180104 02:59:12.143',0);
INSERT INTO [BLAD] ([id_uzytkownik],[temat],[opis],[data_zgloszenia],[rozpatrzono]) VALUES (4,N'usuwanie konsultacji',N'nie dziala prawidlowo','20180102 21:03:40.803',0);

﻿INSERT INTO [WIADOMOSC] ([id_nadawca],[temat],[tresc],[data_wyslania],[id_odbiorca]) VALUES (2,N'Przelozenie zajec',N'Prosba o przelozenie zajec','20180103 21:46:00',3);
INSERT INTO [WIADOMOSC] ([id_nadawca],[temat],[tresc],[data_wyslania],[id_odbiorca]) VALUES (3,N'Przelozenie zajec',N'Zezwalam','20180103 21:47:04',2);
INSERT INTO [WIADOMOSC] ([id_nadawca],[temat],[tresc],[data_wyslania],[id_odbiorca]) VALUES (2,N'tet',N'tet','20180103 22:25:58.203',3);
INSERT INTO [WIADOMOSC] ([id_nadawca],[temat],[tresc],[data_wyslania],[id_odbiorca]) VALUES (2,N'TEST',N'TEST','20180104 09:41:19.410',2);
INSERT INTO [WIADOMOSC] ([id_nadawca],[temat],[tresc],[data_wyslania],[id_odbiorca]) VALUES (2,N'Nowa wiadomosc',N'Witam, o zdrowie pytam','20180104 09:42:31.797',4);

﻿INSERT INTO [WNIOSEK] ([id_prowadzacy],[id_dyrektor],[tresc_wniosek],[decyzja],[zmieniono_plan]) VALUES (1,3,N'tresc',1,0);
INSERT INTO [WNIOSEK] ([id_prowadzacy],[id_dyrektor],[tresc_wniosek],[decyzja],[zmieniono_plan]) VALUES (2,3,N'nowy wniosek',0,0);
INSERT INTO [WNIOSEK] ([id_prowadzacy],[id_dyrektor],[tresc_wniosek],[decyzja],[zmieniono_plan]) VALUES (1,3,N'TAK',0,1);
INSERT INTO [WNIOSEK] ([id_prowadzacy],[id_dyrektor],[tresc_wniosek],[decyzja],[zmieniono_plan]) VALUES (1,3,N'TEST',0,1);

﻿INSERT INTO [ZAJECIA] ([id_przedmiot],[id_sala],[id_prowadzacy],[nr_bloku],[typ_zajec],[data]) VALUES (1,1,1,1,N'l','20180120');
INSERT INTO [ZAJECIA] ([id_przedmiot],[id_sala],[id_prowadzacy],[nr_bloku],[typ_zajec],[data]) VALUES (3,1,2,3,N'l','20180118');
INSERT INTO [ZAJECIA] ([id_przedmiot],[id_sala],[id_prowadzacy],[nr_bloku],[typ_zajec],[data]) VALUES (2,2,3,3,N'c','20180118');
INSERT INTO [ZAJECIA] ([id_przedmiot],[id_sala],[id_prowadzacy],[nr_bloku],[typ_zajec],[data]) VALUES (2,2,2,1,N'lab','20180119');
INSERT INTO [ZAJECIA] ([id_przedmiot],[id_sala],[id_prowadzacy],[nr_bloku],[typ_zajec],[data]) VALUES (3,1,3,6,N'cw','20180117');
INSERT INTO [ZAJECIA] ([id_przedmiot],[id_sala],[id_prowadzacy],[nr_bloku],[typ_zajec],[data]) VALUES (1,2,2,1,N'w','20180128');
INSERT INTO [ZAJECIA] ([id_przedmiot],[id_sala],[id_prowadzacy],[nr_bloku],[typ_zajec],[data]) VALUES (8,2,14,1,N'c','20180109');

﻿INSERT INTO [INSPEKCJA] ([id_zajecia],[id_dyrektor],[komentarz_inspekcja]) VALUES (2,1,N'szybka kontrola');
INSERT INTO [INSPEKCJA] ([id_zajecia],[id_dyrektor],[komentarz_inspekcja]) VALUES (4,3,N'brak');
INSERT INTO [INSPEKCJA] ([id_zajecia],[id_dyrektor],[komentarz_inspekcja]) VALUES (4,3,N'brak');
INSERT INTO [INSPEKCJA] ([id_zajecia],[id_dyrektor],[komentarz_inspekcja]) VALUES (7,2,N'TEST');

﻿INSERT INTO [ZAJECIA_GRUPY] ([id_grupa],[id_zajecia]) VALUES (10,7);
INSERT INTO [ZAJECIA_GRUPY] ([id_grupa],[id_zajecia]) VALUES (3,7);
INSERT INTO [ZAJECIA_GRUPY] ([id_grupa],[id_zajecia]) VALUES (9,7);
INSERT INTO [ZAJECIA_GRUPY] ([id_grupa],[id_zajecia]) VALUES (9,6);
INSERT INTO [ZAJECIA_GRUPY] ([id_grupa],[id_zajecia]) VALUES (9,2);
INSERT INTO [ZAJECIA_GRUPY] ([id_grupa],[id_zajecia]) VALUES (3,2);
INSERT INTO [ZAJECIA_GRUPY] ([id_grupa],[id_zajecia]) VALUES (10,3);
INSERT INTO [ZAJECIA_GRUPY] ([id_grupa],[id_zajecia]) VALUES (9,4);
INSERT INTO [ZAJECIA_GRUPY] ([id_grupa],[id_zajecia]) VALUES (3,5);
INSERT INTO [ZAJECIA_GRUPY] ([id_grupa],[id_zajecia]) VALUES (13,9);

﻿INSERT INTO [ZAPYTANIE] ([id_student],[id_prowadzacy],[tresc_zapytanie],[decyzja]) VALUES (2,1,N'przelozenie zajec z poniedzialku',0);
INSERT INTO [ZAPYTANIE] ([id_student],[id_prowadzacy],[tresc_zapytanie],[decyzja]) VALUES (2,2,N'przelozenie zajec z czwartku',0);
INSERT INTO [ZAPYTANIE] ([id_student],[id_prowadzacy],[tresc_zapytanie],[decyzja]) VALUES (2,1,N'czy mozna przelozyc egzamin',1);
INSERT INTO [ZAPYTANIE] ([id_student],[id_prowadzacy],[tresc_zapytanie],[decyzja]) VALUES (2,3,N'byc albo nie byc',0);
INSERT INTO [ZAPYTANIE] ([id_student],[id_prowadzacy],[tresc_zapytanie],[decyzja]) VALUES (8,17,N'Czy mozemy dostac dobra ocene?',0);
INSERT INTO [ZAPYTANIE] ([id_student],[id_prowadzacy],[tresc_zapytanie],[decyzja]) VALUES (8,2,N'Przelozenie obrony projektu',0);
INSERT INTO [ZAPYTANIE] ([id_student],[id_prowadzacy],[tresc_zapytanie],[decyzja]) VALUES (8,2,N'Przelozenie zajec z wtorku',0);
INSERT INTO [ZAPYTANIE] ([id_student],[id_prowadzacy],[tresc_zapytanie],[decyzja]) VALUES (2,14,N'Czy przewidywany jest dodatkowy termin ',1);