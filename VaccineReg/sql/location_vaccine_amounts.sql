USE uni_oop_project;

DROP TABLE IF EXISTS location_vaccine_amounts;

CREATE TABLE location_vaccine_amounts (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    vaccine_center_id BIGINT,
    vaccine_name CHAR(64) NOT NULL,
    amount INT NOT NULL,

    FOREIGN KEY (vaccine_center_id) REFERENCES vaccine_centers(id)
);