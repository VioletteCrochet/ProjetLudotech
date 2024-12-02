DROP TABLE IF EXISTS Client;

CREATE TABLE Client (
    idClient INT A
    nom VARCHAR(50), -- Taille entre 3 et 50
    prenom VARCHAR(50), -- Taille entre 3 et 50
    email VARCHAR(50), -- Taille entre 3 et 50, validation email côté applicatif
    numTel VARCHAR(50), -- Optionnel, taille entre 3 et 50
    rue VARCHAR(50), -- Taille entre 3 et 50
    cpo VARCHAR(50), -- Taille entre 3 et 50
    ville VARCHAR(50) 
