SELECT autorius.vardas, autorius.pavarde, COUNT(paimta) as "paimtu egzemplioriai kiekis"
FROM stud.autorius, stud.egzempliorius, stud.knyga
WHERE autorius.ISBN = egzempliorius.ISBN and egzempliorius.ISBN = knyga.ISBN group by autorius.vardas, autorius.pavarde;
