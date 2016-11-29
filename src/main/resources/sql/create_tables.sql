DROP TABLE IF EXISTS Supplier, Product, ProductCategory;

CREATE TABLE Supplier
(
id integer PRIMARY KEY,
name varchar(40),
description varchar(100)
);

CREATE TABLE ProductCategory
(
  id integer PRIMARY KEY,
  name VARCHAR(30),
  department varchar(250)
);

CREATE TABLE Product
(
id integer PRIMARY KEY,
name varchar(40),
defaultPrice DECIMAL(19,4),
defaultCurrency varchar(10),
description varchar(250),
Supplier INTEGER REFERENCES Supplier,
ProductCategory INTEGER REFERENCES ProductCategory
);

