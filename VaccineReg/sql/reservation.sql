CREATE TABLE reservation (
    id BIGINT PRIMARY KEY,
    start_time TIMESTAMP NOT NULL,
    location_vaccine_amount_id BIGINT FOREIGN KEY
);