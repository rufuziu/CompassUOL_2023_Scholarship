CREATE DATABASE question04 ;

\c question04

CREATE TABLE Address (
  addressId SERIAL PRIMARY KEY ,
  zip VARCHAR(16) NOT NULL,
  street VARCHAR(128) NOT NULL,
  complement VARCHAR(64) ,
  neighborhood VARCHAR(32) NOT NULL,
  city VARCHAR(16) NOT NULL,
  state VARCHAR(16) NOT NULL,
  country VARCHAR(32) NOT NULL
);

CREATE TABLE Person (
  person_id SERIAL PRIMARY Key ,
  name VARCHAR(64) NOT NULL,
  birthday DATE NOT NULL ,
  age INTERVAL ,
  phone VARCHAR(16) ,
  height DECIMAL(4,2) ,
  email VARCHAR(128) NOT NULL UNIQUE,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  address_id INTEGER REFERENCES Address(addressId)
);

INSERT INTO address (zip, street, complement, neighborhood, city, state, country)
VALUES (
  '840000000' ,
  'RUA TESTE, 5' ,
  'COMEÇO DA RUA' ,
  'CIDADE AZUL' ,
  'VILA VERDE' ,
  'PARANA' , 
  'BRAZIL' );

INSERT INTO person (name, birthday, age, phone, height, email, cpf, address_id)
VALUES (
  'IURY DE MATTOS' ,
  '1995-11-07' ,
  AGE(TIMESTAMP '1995-11-07') ,
  '5542999254823' ,
  1.83,
  'IURY.MATTOS.PB@COMPASS.COM.BR' , 
  '00000000000' ,
  1
);

SELECT Person.*, Address.* FROM Person
INNER JOIN Address ON Person.address_id = Address.addressId;

DROP TABLE Address CASCADE;
DROP TABLE Person;

\c postgres

DROP DATABASE question04;