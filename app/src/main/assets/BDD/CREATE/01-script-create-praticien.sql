CREATE TABLE IF NOT EXISTS "Praticien" (
	"pra_num"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"pra_nom"	TEXT,
	"pra_prenom"	TEXT,
	"pra_rue"	TEXT,
	"pra_cp"	TEXT,
	"pra_ville"	TEXT
);