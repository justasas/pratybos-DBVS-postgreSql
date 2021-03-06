--Kiekvienai duomenų bazės lentelei - skaitinio tipo stulpelių skaičius.

--SELECT count(*) 
--FROM information_schema.columns where table_name = 'egzempliorius';

--SELECT Table_Name, count(case when Data_Type = 'integer' then true end) as "skaitinio tipo stulp skaicius"
--FROM Information_Schema.Columns
--GROUP BY Table_Name

--WITH AtsBe0(Table_Name, "skaitinio tipo stulp skaicius") 
--as
--(SELECT Table_Name, count(*)
--FROM Information_Schema.Columns
--WHERE DATA_Type = 'integer' and table_schema = 'stud'
--GROUP BY Table_Name)
--Select Columns.Table_Name as "Lenteles vardas", 0 as "skaitinio tipo stulp skaicius"
--from Information_Schema.Columns inner join AtsBe0
--ON AtsBe0.Table_Name != Columns.Table_Name
--GROUP BY Columns.Table_Name
--Union
--Select * from AtsBe0;

--skaitinio tipo stulpeliu vidurki, didziausia skaiciu

With A(Table_Name, "integer stulp kiekis")
as
(SELECT Table_Name, count(case when Data_Type = 'integer' then true end) as "skaitinio tipo stulp skaicius"
FROM Information_Schema.Columns
GROUP BY Table_Name)
Select round(avg("integer stulp kiekis"), 1) as "vidurkis",  max("integer stulp kiekis") as "didziausias" from A

