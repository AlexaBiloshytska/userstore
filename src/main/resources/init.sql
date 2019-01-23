CREATE table USERS(
  id BIGINT auto_increment PRIMARY KEY,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  age INT,
  dateOfBirth DATE ,
  salary INT
);

INSERT INTO USERS (first_name,last_name,age,dateOfBirth,salary)
VALUES ('Alex', 'Bernard', 13,'2018-08-15', 200);

INSERT INTO USERS (first_name,last_name,age,dateOfBirth,salary)
VALUES ('Max', 'Shopper', 13,'2018-08-15', 200);