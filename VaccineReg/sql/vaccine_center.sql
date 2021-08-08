CREATE TABLE vaccine_center (
    id BIGINT PRIMARY KEY,
    region_name CHAR(64) NOT NULL,
    city_name CHAR(64) NOT NULL,
    district_name CHAR(64) NOT NULL,
    people_limit_per_vaccine_at_same_time INT
);