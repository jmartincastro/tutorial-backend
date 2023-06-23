INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO client(name) VALUES ('Pepe');
INSERT INTO client(name) VALUES ('Juan');
INSERT INTO client(name) VALUES ('Celia');
INSERT INTO client(name) VALUES ('Mariam');
INSERT INTO client(name) VALUES ('Jose');
INSERT INTO client(name) VALUES ('Juan');
INSERT INTO client(name) VALUES ('Javier');
INSERT INTO client(name) VALUES ('Daniela');
INSERT INTO client(name) VALUES ('Pepi');

INSERT INTO loan(client_id, game_id, start_date, end_date) VALUES (1, 2, '2022-12-10', '2022-12-24');
INSERT INTO loan(client_id, game_id, start_date, end_date) VALUES (2, 1, '2022-12-10', '2022-12-24');
INSERT INTO loan(client_id, game_id, start_date, end_date) VALUES (3, 3, '2022-12-10', '2022-12-24');
INSERT INTO loan(client_id, game_id, start_date, end_date) VALUES (4, 1, '2022-05-10', '2022-05-24');
INSERT INTO loan(client_id, game_id, start_date, end_date) VALUES (5, 4, '2022-12-10', '2022-12-24');
INSERT INTO loan(client_id, game_id, start_date, end_date) VALUES (6, 5, '2022-12-10', '2022-12-24');
INSERT INTO loan(client_id, game_id, start_date, end_date) VALUES (7, 6, '2022-12-10', '2022-12-24');
INSERT INTO loan(client_id, game_id, start_date, end_date) VALUES (8, 3, '2022-05-10', '2022-05-24');
INSERT INTO loan(client_id, game_id, start_date, end_date) VALUES (9, 2, '2022-05-10', '2022-05-24');


