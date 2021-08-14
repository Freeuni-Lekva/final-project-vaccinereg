USE uni_oop_project;

DROP TABLE IF EXISTS reservations;

CREATE TABLE reservations (
    id BIGINT PRIMARY KEY,
    reservation_time DATETIME NOT NULL,
    vaccination_time DATETIME NOT NULL,
    location_vaccine_amount_id BIGINT,
    user_id BIGINT,

    FOREIGN KEY (location_vaccine_amount_id) REFERENCES location_vaccine_amounts(id),
    FOREIGN KEY (user_id) REFERENCES users(private_num)
);
INSERT INTO reservations VALUES
(1, '2021-08-13 16:55:00', '2021-08-13 16:55:00', 1, 111231455),
(2, '2021-08-13 16:52:00', '2021-08-13 16:52:00', 2, 221231455),
(3, '2021-08-13 12:42:00', '2021-08-13 12:42:00', 3, 291291495);
