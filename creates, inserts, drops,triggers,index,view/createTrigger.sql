-- Dalykine taisykle, uztikrinanti, kad personalo darbuotojas nedirbtu daugiau nei 2 klubuose.

CREATE FUNCTION klubaiMax() RETURNS "trigger" AS $$

DECLARE 
    maxSk     CONSTANT SMALLINT := 2;
    turimasSk          SMALLINT;
BEGIN
    /* Suskaiciuojame, keliose komandose darbuotojas jau dirba */
    SELECT COUNT(*) INTO turimasSk 
    FROM   juru0847.komandu_sudetys
    WHERE  id_p = NEW.id_p;

    IF turimasSk + 1 > maxSk
        THEN RAISE EXCEPTION 'Personalo darbuotojas negali dirbti daugiau nei % klubuose', maxSk;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER darbuotojuSkMax
    BEFORE INSERT OR UPDATE ON juru0847.Komandu_sudetys
    FOR EACH ROW EXECUTE PROCEDURE klubaiMax();

		
-- Dalykine taisykle, uztikrinanti, kad personalo darbuotoju atlyginimai nevirsytu biudzeto.

CREATE FUNCTION biudzetoTikr() RETURNS "trigger" AS $$
DECLARE 
    limitas    INT;
    darbuotojo INT;
    esamas     INT;
BEGIN
    /* Naujo darbuotojo atlyginimas */
    SELECT atlyginimas INTO darbuotojo 
    FROM   juru0847.Personalas 
    WHERE  id_p = NEW.id_p;

    /* Gauname komandos biudzeta */
    SELECT biudzetas INTO limitas
    FROM   juru0847.klubas
    WHERE  ID_Klubo = NEW.ID_Klubo;

    /* Gauname dabartine atlyginimu suma */
    SELECT atlyginimu_suma INTO esamas 
    FROM   juru0847.Atlyginimai,
           juru0847.Klubas
    WHERE  ((ID_Klubo = NEW.ID_Klubo) AND 
           (atlyginimai.klubas = klubas.pavadinimas));
        
    IF esamas + darbuotojo > limitas
        THEN RAISE EXCEPTION 'Priskirti darbuotojo negalima nes butu virsytas biudzetas';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER biudzetasMax
    BEFORE INSERT OR UPDATE ON juru0847.Komandu_sudetys
    FOR EACH ROW EXECUTE PROCEDURE biudzetoTikr();