use company;

CREATE TABLE company
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `number_of_trucks` INT NOT NULL,
    PRIMARY KEY ( id )
);