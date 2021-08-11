USE uni_oop_project;

DROP TABLE IF EXISTS reservations;

CREATE TABLE reservations (
    id BIGINT PRIMARY KEY,
    start_time_one DATETIME NOT NULL,
    start_time_two DATETIME NOT NULL,
    location_vaccine_amount_id BIGINT,

    FOREIGN KEY (location_vaccine_amount_id) REFERENCES location_vaccine_amounts(id)
);