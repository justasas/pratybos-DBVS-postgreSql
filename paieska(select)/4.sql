--WITH visosPaimtos(pavadinimas, isbn, ak) as(Select knyga.pavadinimas, knyga.isbn, skaitytojas.ak, --skaitytojas.gimimas from stud.knyga, stud.egzempliorius, stud.skaitytojas where --egzempliorius.skaitytojas = skaitytojas.nr)
--Select * from visosPaimtos;--pavadinimas, isbn, skaitytojas.ak, min(gimimas) from visosPaimtos group by pavadinimas, isbn;
-- Kiekvienai knygai (pavadinimas, ISBN) jauniausio jos skaitytojo AK ir gimimo data.

--WITH paimtiEgz(pavadinimas, isbn, ak, gimimoData) 
--as 
--(Select knyga.pavadinimas, knyga.isbn, skaitytojas.ak, skaitytojas.gimimas
-- from stud.egzempliorius
-- inner join stud.skaitytojas on egzempliorius.skaitytojas = skaitytojas.nr 
-- inner join stud.knyga on knyga.isbn = egzempliorius.isbn),
--atsBeAK(pavadinimas, isbn, gimimoData) 
--as 
--(Select pavadinimas, isbn, min(gimimoData) from paimtiEgz group by pavadinimas, isbn)
--Select atsBeAK.pavadinimas, atsBeAK.isbn, paimtiEgz.ak, atsBeAK.gimimoData as "gimimo data"
-- from atsBeAK, paimtiEgz where atsBeAK.isbn = paimtiEgz.isbn and atsBeAK.pavadinimas = -- paimtiEgz.pavadinimas 
--							     and atsBeAk.gimimoData = paimtiEgz.gimimoData;

WITH Egzemplioriai(pavadinimas, isbn, ak, gimimoData) 
as 
(Select knyga.pavadinimas, knyga.isbn, skaitytojas.ak, skaitytojas.gimimas
from stud.egzempliorius
left outer join stud.skaitytojas on egzempliorius.skaitytojas = skaitytojas.nr 
inner join stud.knyga on knyga.isbn = egzempliorius.isbn),
atsBeAK(pavadinimas, isbn, gimimoData) 
as 
(Select pavadinimas, isbn, max(gimimoData) from Egzemplioriai group by pavadinimas, isbn)
Select atsBeAK.pavadinimas, atsBeAK.isbn, Egzemplioriai.ak, atsBeAK.gimimoData as "gimimo data"
 from Egzemplioriai right outer join atsBeAK on atsBeAK.isbn = Egzemplioriai.isbn and atsBeAK.pavadinimas = Egzemplioriai.pavadinimas
							     and Egzemplioriai.gimimoData = atsBeAK.gimimoData;
