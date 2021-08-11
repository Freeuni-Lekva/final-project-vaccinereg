USE uni_oop_project;

DROP TABLE IF EXISTS location_vaccine_amounts;

CREATE TABLE location_vaccine_amounts (
    id BIGINT PRIMARY KEY,
    vaccine_center_id BIGINT,
    vaccine_name CHAR(64) NOT NULL,
    amount INT NOT NULL,

    FOREIGN KEY (vaccine_center_id) REFERENCES vaccine_centers(id)
);