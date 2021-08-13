USE uni_oop_project;

DROP TABLE IF EXISTS location_vaccine_amounts;

CREATE TABLE location_vaccine_amounts (
    id BIGINT PRIMARY KEY,
    vaccine_center_id BIGINT,
    vaccine_name CHAR(64) NOT NULL,
    amount INT NOT NULL,

    FOREIGN KEY (vaccine_center_id) REFERENCES vaccine_centers(id)
);

INSERT INTO location_vaccine_amounts VALUES
(1, 1, "ABC", 300),
(2, 1, "XYZ", 100),
(3, 2, "ABC", 200),
(4, 2, "XYZ", 150),
(5, 3, "ABC", 250),
(6, 3, "XYZ", 50);
