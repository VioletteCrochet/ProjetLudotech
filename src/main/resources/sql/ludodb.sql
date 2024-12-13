DROP TABLE IF EXISTS  ExemplaireJeux, Clients, Jeux, Genres, Jeux_Genres, Utilisateurs;

-- CREATE USER ludouser WITH PASSWORD 'password';
-- GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO ludouser;
-- ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO ludouser;


CREATE TABLE Clients (
    id serial Constraint PK_Clents primary key,
    nom VARCHAR(50)NOT NULL, 
    prenom VARCHAR(50)NOT NULL, 
    email VARCHAR(50) CONSTRAINT UK_email_unique UNIQUE, 
    numTel VARCHAR(50)NULL,
    rue VARCHAR(50)NOT NULL, 
    cpo VARCHAR(50)NOT NULL, 
    ville VARCHAR(50)NOT NULL 
	);
	
CREATE TABLE Jeux (
    id serial Constraint PK_Jeux PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    reference VARCHAR(255) NOT NULL,
    description TEXT NULL,
    tarifJour DECIMAL(10, 2) NOT NULL,
    ageMin INT NOT NULL,
    duree INT
);
CREATE TABLE Genres (
    id serial Constraint PK_Genres PRIMARY KEY,
    libelle VARCHAR(255) CONSTRAINT UK_libelle_unique UNIQUE NOT NULL
);

CREATE TABLE Jeux_Genres (
    jeu_id INT,
    genre_id INT,
    PRIMARY KEY (jeu_id, genre_id),
    FOREIGN KEY (jeu_id) REFERENCES Jeux(id) on delete cascade,
    FOREIGN KEY (genre_id) REFERENCES Genres(id) on delete cascade
);

CREATE TABLE ExemplaireJeux (
	id serial Constraint PK_ExemplaireJeux PRIMARY KEY,
	codeBarre VARCHAR(50) NOT NULL,
	louable BOOLEAN NOT NULL, 
	jeuId INT NOT NULL,
	FOREIGN KEY (jeuId) REFERENCES Jeux(id)
);

CREATE TABLE Utilisateurs (
	id serial Constraint PK_Utilisateurs PRIMARY KEY,
    userName VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
	role VARCHAR(20) NOT NULL
	
);

create table Locations (
	id serial Constraint PK_Locations,
	DateDebutLocation date,
	Payed boolean,
	totalPrice Decimal(10,2),
	
	
)