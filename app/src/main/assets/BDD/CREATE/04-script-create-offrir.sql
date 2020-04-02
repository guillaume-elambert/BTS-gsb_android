CREATE TABLE IF NOT EXISTS "Offrir" (
	"pra_num"	INTEGER NOT NULL,
	"med_depotLegal"	TEXT,
	"qte"	INTEGER,
	CONSTRAINT "offrir_Praticien_FK" FOREIGN KEY("pra_num") REFERENCES "Praticien"("pra_num"),
	CONSTRAINT "offrir_PK" PRIMARY KEY("pra_num","med_depotLegal"),
	CONSTRAINT "offrir_Medicament_FK" FOREIGN KEY("med_depotLegal") REFERENCES "Medicament"("med_depotLegal")
);