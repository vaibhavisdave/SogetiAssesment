CREATE TABLE CUSTOMER (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  street VARCHAR(250) NULL,
  phNumber VARCHAR(250) NOT NULL,
  houseNo int  NULL,
  zipCode VARCHAR(250)  NULL,
  place VARCHAR(250)  NULL,
  email VARCHAR(250) NOT NULL
);

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;

