USE uni_oop_project;

DROP TABLE IF EXISTS vaccine_centers;

CREATE TABLE vaccine_centers (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    region_name CHAR(64) NOT NULL,
    city_name CHAR(64) NOT NULL,
    district_name CHAR(64) NOT NULL,
    center_name CHAR(64) NOT NULL,
    people_limit INT
);
