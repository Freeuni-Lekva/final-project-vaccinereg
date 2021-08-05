-- TODO: change to your db
USE uni_oop_project;

DROP TABLE IF EXISTS users;
 -- remove table if it already exists and start from scratch

CREATE TABLE users (
	private_num 	BIGINT 		PRIMARY KEY,
    name 			CHAR(64) 	NOT NULL,
    last_name 		CHAR(64) 	NOT NULL,
    gender 			CHAR(1)		NOT NULL,
    age 			INT 		NOT NULL,
    email 			CHAR(128) 	NOT NULL UNIQUE,
    password 		CHAR(64) 	NOT NULL,
    is_admin		BOOLEAN		NOT NULL,
    vaccination_count INT       NOT NULL
    
    #vaccination_id INT 		FOREIGN KEY
);

INSERT INTO users VALUES
	(111231455, "Archil", "Ksovreli", "m", 20, "aksov19@freeuni.edu.ge", "achiachi", true, 1),
    (221231455, "name1", "lastname1", "f", 45, "test1@freeuni.edu.ge", "test1", false, 0),
    (291291495, "name2", "lastname2", "m", 27, "test2@gmail.com", "test2", false, 0),
    (221239999, "name3", "lastname3", "f", 21, "test3@gmail.com", "test3", false, 0);
    
