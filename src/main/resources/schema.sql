SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS member, cafe, cafe_positions, cafe_benefits CASCADE;

CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE cafe (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    latitude DOUBLE,
    longitude DOUBLE
);

CREATE TABLE cafe_positions (
    cafe_id BIGINT,
    positions BIGINT,
    FOREIGN KEY (cafe_id) REFERENCES cafe(id)
);

CREATE TABLE cafe_benefits (
    cafe_id BIGINT,
    benefits_key VARCHAR(255) NOT NULL,
    benefits DOUBLE
);

SET FOREIGN_KEY_CHECKS = 1;