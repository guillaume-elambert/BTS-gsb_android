CREATE TABLE IF NOT EXISTS "Medicament" (
	"med_depotLegal"	TEXT NOT NULL,
	"med_nomCommercial"	TEXT,
	"med_prixEchantillon"	REAL,
	"fam_code"	TEXT NOT NULL,
	CONSTRAINT "Medicament_PK" PRIMARY KEY("med_depotLegal"),
	CONSTRAINT "Medicament_Famille_FK" FOREIGN KEY("fam_code") REFERENCES "Famille"("fam_code")
);