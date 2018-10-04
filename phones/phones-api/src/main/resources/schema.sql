DROP SCHEMA PHONES CASCADE;

CREATE SCHEMA PHONES;

USE PHONES;

CREATE TABLE PHONES (
   ID INT AUTO_INCREMENT,
   IMAGE_PATH VARCHAR(50) NOT NULL,
   NAME VARCHAR(20) NOT NULL,
   DESCRIPTION VARCHAR(100),
   PRICE DECIMAL(5, 2),
   CONSTRAINT PK_PHONES PRIMARY KEY (ID)
);
