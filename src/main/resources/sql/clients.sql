DROP TABLE IF EXISTS Clients, Jeux, Genres, Jeux_Genres;

CREATE TABLE Clients (
    id serial Constraint PK_Clents primary key,
    nom VARCHAR(50), -- Taille entre 3 et 50
    prenom VARCHAR(50), -- Taille entre 3 et 50
    email VARCHAR(50), -- Taille entre 3 et 50, validation email côté applicatif
    numTel VARCHAR(50), -- Optionnel, taille entre 3 et 50
    rue VARCHAR(50), -- Taille entre 3 et 50
    cpo VARCHAR(50), -- Taille entre 3 et 50
    ville VARCHAR(50) 
	);
	
CREATE TABLE Jeux (
    id serial Constraint PK_Jeux PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    reference VARCHAR(255) NOT NULL,
    description TEXT,
    tarifJour DECIMAL(10, 2) NOT NULL,
    ageMin INT NOT NULL,
    duree INT
);
CREATE TABLE Genres (
    id serial Constraint PK_Genres PRIMARY KEY,
    libelle VARCHAR(255) NOT NULL
);

CREATE TABLE Jeux_Genres (
    jeu_id INT,
    genre_id INT,
    PRIMARY KEY (jeu_id, genre_id),
    FOREIGN KEY (jeu_id) REFERENCES Jeu(id),
    FOREIGN KEY (genre_id) REFERENCES Genre(id)
);

INSERT INTO Clients (nom, prenom, email, numTel, rue, cpo, ville) 
VALUES 
    ('Leroy', 'Merlin', 'leroy.merlin@gmail.com', '0214253698', 'avenue du roi', '44660', 'kaamelott'),
    ('Durand', 'Marie', 'marie.durand@example.com', '0612345678', 'rue des Fleurs', '75015', 'Paris'),
    ('Martin', 'Jean', 'jean.martin@example.com', '0711223344', 'boulevard Haussmann', '75008', 'Paris'),
    ('Dubois', 'Lucie', 'lucie.dubois@example.com', '0414556677', 'place de la République', '69001', 'Lyon'),
    ('Petit', 'Alain', 'alain.petit@example.com', '0516778899', 'chemin des Écoliers', '33000', 'Bordeaux');

select * from Clients;
