USE uni_oop_project;

DROP TABLE IF EXISTS vaccine_centers;

CREATE TABLE vaccine_centers (
    id BIGINT PRIMARY KEY,
    region_name CHAR(64) NOT NULL,
    city_name CHAR(64) NOT NULL,
    district_name CHAR(64) NOT NULL,
    center_name CHAR(64) NOT NULL,
    people_limit_per_vaccine_at_same_time INT
);



INSERT INTO vaccine_centers VALUES
    (1, "Tbilisi", "Tbilisi", "Saburtalo", "Respublikuri", 400),
    (2, "Adjara", "Batumi", "idk", "Batumi Respublikuri", 350),
    (3, "Imereti", "Kutaisi", "idk2", "Kutaisi Respublikuri", 300);

