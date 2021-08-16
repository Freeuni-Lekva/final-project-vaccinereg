USE uni_oop_project;

DROP TABLE IF EXISTS reservations;

CREATE TABLE reservations (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    reservation_time DATETIME NOT NULL,
    vaccination_time DATETIME NOT NULL,
    location_vaccine_amount_id BIGINT,
    user_id BIGINT,

    FOREIGN KEY (location_vaccine_amount_id) REFERENCES location_vaccine_amounts(id),
    FOREIGN KEY (user_id) REFERENCES users(private_num)
);
