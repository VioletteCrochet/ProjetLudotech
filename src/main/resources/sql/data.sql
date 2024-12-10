 

INSERT INTO Clients (nom, prenom, email, numTel, rue, cpo, ville) 
VALUES 
    ('Stalis', 'Jayce', 'jayce.stalis@gmail.com', '0214253698', 'avenue du roi', '44660', 'Piltover'),
    ('Durand', 'Viktor', 'viktor.durand@example.com', '0612345678', 'rue des Fleurs', '75015', 'Piltover'),
    ('Medarda', 'Mel', 'mel.medarda@example.com', '0711223344', 'boulevard Haussmann', '75008', 'Piltover'),
    ('Dubois', 'Silco', 'silco.dubois@example.com', '0414556677', 'place de la République', '69001', 'Zaun'),
    ('Medarda', 'Ambessa', 'ambessa.medarda@example.com', '0516778899', 'chemin des Écoliers', '33000', 'Noxus');
	
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
	('jinx', '$2a$10$tfz/GAIC1rPZRjkwy.ihluCa1lWdC8ruHY5rweoOjEEXAyrC4G32W','ADMIN'),
	('vi',  '$2a$10$tfz/GAIC1rPZRjkwy.ihluCa1lWdC8ruHY5rweoOjEEXAyrC4G32W', 'USER'),
	('ekko', '$2a$10$tfz/GAIC1rPZRjkwy.ihluCa1lWdC8ruHY5rweoOjEEXAyrC4G32W', 'CLIENT');
