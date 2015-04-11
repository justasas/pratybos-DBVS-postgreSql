--SELECT AVG(puslapiai)
--FROM stud.knyga, stud.autorius 
--WHERE autorius.ISBN = knyga.ISBN and Vardas = 'Jonas' and Pavarde = 'Jonaitis';
-- ne knygu, o visu egzemplioriu puslapiu vidurki, suapvalinta iki vieno skaiciaus po kablelio

SELECT ROUND(AVG(puslapiai), 1)  as "egzemplioriu vidurkis"
FROM stud.autorius, stud.egzempliorius, stud.knyga
WHERE autorius.Vardas = 'Jonas' and autorius.Pavarde = 'Jonaitis' and autorius.ISBN = egzempliorius.ISBN 
				and egzempliorius.ISBN = knyga.ISBN;

