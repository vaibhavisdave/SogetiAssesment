CREATE TABLE CAR (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  model VARCHAR(250) NOT NULL,
  make VARCHAR(250) NULL,
  version int NOT NULL,
  no_Of_Doors int  NULL,
  co2_Emission VARCHAR(250)  NULL,
  gross_Price DECIMAL NOT NULL,
  nett_price DECIMAL NOT NULL,
  mileage DECIMAL NOT NULL,
    duration INT NOT NULL,
  startDate DATE NOT NULL,
  interestRate DECIMAL NOT NULL,
  leaseRate DECIMAL NOT NULL,
);


CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;

#SELECT * FROM CAR ;