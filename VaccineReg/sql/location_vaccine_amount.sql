CREATE TABLE location_vaccine_amount (
    id BIGINT PRIMARY KEY,
    vaccine_center_id BIGINT FOREIGN KEY,
    vaccine_name CHAR(64) NOT NULL,
    amount INT NOT NULL
);