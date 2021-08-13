USE uni_oop_project;

DROP TABLE IF EXISTS reservations;

CREATE TABLE reservations (
    id BIGINT PRIMARY KEY,
    start_time_one DATETIME NOT NULL,
    start_time_two DATETIME NOT NULL,
    reservation_time DATETIME NOT NULL,
    vaccination_time DATETIME NOT NULL,
    location_vaccine_amount_id BIGINT,

    FOREIGN KEY (location_vaccine_amount_id) REFERENCES location_vaccine_amounts(id)
);
INSERT INTO reservations VALUES
(1, '2021-08-13 16:55:00', '2021-08-13 16:55:00', '2021-08-13 16:55:00', '2021-08-13 16:55:00', 1),
(2, '2021-08-13 16:52:00', '2021-08-13 16:52:00', '2021-08-13 16:52:00', '2021-08-13 16:52:00', 2),
(3, '2021-08-13 12:42:00', '2021-08-13 12:42:00', '2021-08-13 12:42:00', '2021-08-13 12:42:00', 3);
