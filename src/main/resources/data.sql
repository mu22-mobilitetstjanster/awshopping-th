DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id int AUTO_INCREMENT primary key,
    username varchar(255),
    created_at date,
    created_by varchar(32)
);


INSERT INTO users (username, created_at, created_by) VALUES ("Yves", now(), "CONSOLE");
INSERT INTO users (username, created_at, created_by) VALUES ("Bertil", now(), "CONSOLE");
INSERT INTO users (username, created_at, created_by) VALUES ("Gertrude", now(), "CONSOLE");
INSERT INTO users (username, created_at, created_by) VALUES ("Todd", now(), "CONSOLE");