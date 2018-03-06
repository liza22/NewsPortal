CREATE USER liza@localhost identified BY '1234';
GRANT usage ON *.* TO liza@localhost identified BY '1234';
CREATE DATABASE IF NOT EXISTS newsportal;
GRANT ALL privileges ON newsportal.* TO liza@localhost;
USE newsportal;
CREATE TABLE NEWS
  (
     id        INT PRIMARY KEY AUTO_INCREMENT,
     title     VARCHAR(40),
     body      VARCHAR(800),
     category  VARCHAR(40),
     created   TIMESTAMP DEFAULT NOW(),
     category_id INT(10) FOREIGN KEY REFERENCES categories (id)
  );
CREATE TABLE categories
  (
     id        INT PRIMARY KEY AUTO_INCREMENT,
     name      VARCHAR(40)
  );