DROP DATABASE IF EXISTS MovieLibraryDB;

CREATE DATABASE IF NOT EXISTS MovieLibraryDB;

USE MovieLibraryDB;

CREATE TABLE dvdLibrary (
	title VARCHAR(50) NOT NULL PRIMARY KEY,
    releaseDate DATE,
    MPAArating VARCHAR(20),
    director VARCHAR(20),
    studio VARCHAR(50),
    userRating TEXT
);