 --SELECT pavadinimas, leidykla, verte FROM stud.knyga case when verte BETWEEN 10 and 20 or verte is null ORDER BY verte, pavadinimas DESC;
-- DIDEJANCIAI PAGAL VERTE, MAZEJANCIAI PAGAL PAVADINIMA IR JEI NENURODYTI TAI RODYTU 17
 SELECT pavadinimas, leidykla, case 
 		when verte is null 
 		then 17 
 		else verte end as verte 
 	FROM stud.knyga where (verte between 10 and 20 or verte is null) and pavadinimas like 'Inf% s%' order by verte, pavadinimas DESC;
 -- paklaust ar case gali buti kitur 
 -- ~ maches regular expression, case sensetive http://www.postgresql.org/docs/7.4/static/functions-matching.html