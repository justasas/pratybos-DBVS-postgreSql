-- Zaideju lenteles sutrumpinimas

--CREATE VIEW juru0847.Zaidejai AS
--    SELECT ID_Z, 
--           Vardas, 
--           Pavarde
--    FROM   juru0847.Zaidejas;


CREATE VIEW juru0847.Darbuotojai AS
    SELECT ID_P, 
           Vardas, 
           Pavarde
    FROM   juru0847.Personalas;
	
-- Vidutinis zaideju ugis

--CREATE VIEW juru0847.ZaidejoVidUgis AS
--    SELECT AVG(ugis) as "vidutinis zaidejo ugis"
--    FROM   juru0847.Zaidejas;

-- Komandos darbuotoju bendri atlyginimai

CREATE VIEW juru0847.Atlyginimai AS
    SELECT   pavadinimas      AS klubas, 
             biudzetas,
             SUM(atlyginimas) AS atlyginimu_suma             
    FROM     juru0847.Klubas,
             juru0847.Personalas,
             juru0847.Komandu_sudetys
    WHERE    ((Klubas.ID_Klubo = Komandu_sudetys.ID_Klubo) AND 
             (Komandu_sudetys.ID_P = Personalas.ID_P))
    GROUP BY pavadinimas, biudzetas;

-- Darbuotoju lenteles sutrumpinimas
