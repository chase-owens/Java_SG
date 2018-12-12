DROP DATABASE IF EXISTS todoDB;

CREATE DATABASE todoDB;

USE todoDB;

CREATE TABLE todo (
	id INT PRIMARY KEY AUTO_INCREMENT,
    todo VARCHAR(40) NOT NULL,
    note VARCHAR(255),
    Finished BOOLEAN DEFAULT FALSE
);

INSERT INTO todo(id, todo, note, finshed)
	VALUES
		(1, "Wash car", " ", false),
		(2, "Laundry", "Jeans", false),
		(3, "Wash dishes", " ", true);