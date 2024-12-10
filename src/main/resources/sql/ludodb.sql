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
 

INSERT INTO Clients (nom, prenom, email, numTel, rue, cpo, ville) 
VALUES 
    ('Leroy', 'Merlin', 'leroy.merlin@gmail.com', '0214253698', 'avenue du roi', '44660', 'kaamelott'),
    ('Durand', 'Marie', 'marie.durand@example.com', '0612345678', 'rue des Fleurs', '75015', 'Paris'),
    ('Martin', 'Jean', 'jean.martin@example.com', '0711223344', 'boulevard Haussmann', '75008', 'Paris'),
    ('Dubois', 'Lucie', 'lucie.dubois@example.com', '0414556677', 'place de la République', '69001', 'Lyon'),
    ('Petit', 'Alain', 'alain.petit@example.com', '0516778899', 'chemin des Écoliers', '33000', 'Bordeaux');

INSERT INTO Jeux (titre, reference, description, tarifJour, ageMin, duree)
VALUES 
    ('Monopoly', 'MON123', 'Jeu de société classique', 10.00, 8, 120),
    ('Catan', 'CAT456', 'Jeu de stratégie sur une île', 12.50, 12, 90);


INSERT INTO Genres (libelle) 
VALUES 
    ('Stratégie'),
    ('Aventure'),
    ('Famille');

INSERT INTO Jeux_Genres (jeu_id, genre_id)
VALUES 
    ((SELECT id FROM Jeux WHERE reference = 'MON123'), (SELECT id FROM Genres WHERE libelle = 'Stratégie')),  -- Monopoly - Stratégie
    ((SELECT id FROM Jeux WHERE reference = 'MON123'), (SELECT id FROM Genres WHERE libelle = 'Famille')),   -- Monopoly - Famille
    ((SELECT id FROM Jeux WHERE reference = 'CAT456'), (SELECT id FROM Genres WHERE libelle = 'Aventure'));  -- Catan - Aventure

Insert into ExemplaireJeux (codeBarre, louable, jeuId)
values
	('1254789634516', true, 1),
	('1254785474516', true, 1),
	('1255748634516', true, 1),
	('1254789631478', false, 1),
	('1254789634516', true, 1),
	('1254789637566', true, 2),
	('1254758474516', true, 2),
	('1478748634516', false, 2);
	
insert into Utilisateurs (userName, password, role)
values 
	('bob', '$2a$10$tfz/GAIC1rPZRjkwy.ihluCa1lWdC8ruHY5rweoOjEEXAyrC4G32W','admin');
	
select * from Utilisateurs
