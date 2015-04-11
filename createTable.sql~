CREATE TABLE juru0847.Personalas
( 
    ID_P         	SERIAL   	NOT NULL	CHECK (ID_P > 0),
    Vardas       	VARCHAR(15)   	NOT NULL,
    Pavarde     	VARCHAR(20)   	NOT NULL, 	 
    Gimimas        	DATE          	NOT NULL        CHECK (Gimimas < current_date), 
    Atlyginimas		smallint	NOT NULL Default 0		CHECK (Atlyginimas >= 0),
    PRIMARY KEY  (ID_P)
);

CREATE TABLE juru0847.Klubas
( 
    ID_Klubo         	SERIAL   	NOT NULL	CHECK (ID_Klubo > 0),
    Pavadinimas       	VARCHAR(20)   	NOT NULL, 
    Biudzetas		smallint	NOT NULL Default 0		CHECK (Biudzetas >= 0),

    PRIMARY KEY  (ID_Klubo)
);

CREATE TABLE juru0847.Zaidejas 
( 
    ID_Z         	SERIAL   	NOT NULL	CHECK (ID_Z > 0),
    Vardas       	VARCHAR(15)   	NOT NULL,
    Pavarde      	VARCHAR(20)   	NOT NULL, 	
    Ugis         	SMALLINT	NOT NULL 	CHECK (Ugis > 0),
    Svoris       	SMALLINT	NOT NULL 	CHECK (Svoris > 0),
    Numeris      	SMALLINT, 
    ID_Klubo	 	SMALLINT,	
    Gimimas        	DATE          	NOT NULL   	CHECK (Gimimas < current_date), 
    PRIMARY KEY  (ID_Z),
	FOREIGN KEY	 (ID_Klubo) REFERENCES juru0847.Klubas ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE juru0847.Komandu_sudetys (    
    ID_Klubo   				SMALLINT    	NOT NULL,
    ID_P 				SMALLINT    	NOT NULL, 
    PRIMARY KEY (ID_Klubo, ID_P),
    FOREIGN KEY (ID_Klubo) 	REFERENCES Klubas       	ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ID_P) 		REFERENCES Personalas		ON DELETE CASCADE ON UPDATE CASCADE
);
