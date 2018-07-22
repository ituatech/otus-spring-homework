
DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENRES;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS BOOK_TO_AUTHOR;
DROP TABLE IF EXISTS BOOK_TO_GENRE;


CREATE TABLE AUTHORS(ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                  AUTHOR_NAME VARCHAR(255) NOT NULL UNIQUE );
CREATE TABLE GENRES(ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                  GENRE_NAME VARCHAR(255) NOT NULL UNIQUE );
CREATE TABLE BOOKS(ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                  TITLE VARCHAR(255) NOT NULL UNIQUE ,
                  DESCRIPTION VARCHAR(255));
CREATE TABLE BOOK_TO_AUTHOR(BOOK_ID INT NOT NULL,
                  AUTHOR_ID INT NOT NULL,
                  PRIMARY KEY(BOOK_ID, AUTHOR_ID));
CREATE TABLE BOOK_TO_GENRE(BOOK_ID INT NOT NULL,
                  GENRE_ID INT NOT NULL,
                  PRIMARY KEY(BOOK_ID, GENRE_ID));

