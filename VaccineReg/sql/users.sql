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
    
