use driver;

CREATE TABLE driver
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `age` INT NOT NULL,
    `company_name` VARCHAR(100) NOT NULL,
    PRIMARY KEY ( id )
);

CREATE TABLE company
(
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY ( name )
);