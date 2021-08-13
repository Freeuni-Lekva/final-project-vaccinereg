-- TODO: change to your db
USE uni_oop_project;

DROP TABLE IF EXISTS users;
-- remove table if it already exists and start from scratch

CREATE TABLE users (
    private_num 	BIGINT 		PRIMARY KEY,
    name 			CHAR(64) 	NOT NULL,
    last_name 		CHAR(64) 	NOT NULL,
    gender 			CHAR(1)		NOT NULL,
    birth_date 		DATE 		NOT NULL,
    email 			CHAR(128) 	NOT NULL UNIQUE,
    password 		CHAR(64) 	NOT NULL,
    is_admin		BOOLEAN		NOT NULL,
    reservation_id  BIGINT,

    FOREIGN KEY (reservation_id) REFERENCES reservations(id)
);

INSERT INTO users VALUES
(111231455, "name1", "lastname1", "m", '2001-06-23', "test1@freeuni.edu.ge", "test1", 1, 1),
(221231455, "name2", "lastname2", "f", '1980-08-15', "test2@freeuni.edu.ge", "test2", 0, 2),
(291291495, "name3", "lastname3", "m", '1995-11-01', "test3@freeuni.edu.ge", "test3", 0, 3);
