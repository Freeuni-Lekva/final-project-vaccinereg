USE uni_oop_project;

DROP TABLE IF EXISTS vaccine_centers;

CREATE TABLE vaccine_centers (
    id BIGINT PRIMARY KEY,
    region_name CHAR(64) NOT NULL,
    city_name CHAR(64) NOT NULL,
    district_name CHAR(64) NOT NULL,
    people_limit INT
);

INSERT INTO vaccine_centers VALUES
(1, "Tbilisi", "Tbilisi", "Saburtalo", 400),
(2, "Batumi", "Adjara", "idk", 350),
(3, "Kutaisi", "Imereti", "idk2", 300);