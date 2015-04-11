Select vardas, pavarde, 
(count(CASE WHEN paimta is not null THEN true END)) as "paimti egzemplioriai",
count(CASE WHEN paimta is null THEN true END) as "nepaimti egzemplioriai",
count(true) as "visi egzemplioriai",
count(distinct(egzempliorius.isbn)) as "skirtingos knygos"
from stud.autorius, stud.egzempliorius 
where autorius.isbn = egzempliorius.isbn
group by vardas, pavarde having count(paimta) > 2;

-- Kiekvienam autoriui (vardas ir pavardė) visų jo knygų visų paimtų egzempliorių skaičius.
-- ir neapimtu, visu egzemplioriu, skirtingu autoriaus knygu
