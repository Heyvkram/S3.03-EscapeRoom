DROP DATABASE IF EXISTS escaperoom;
CREATE DATABASE escaperoom;
USE escaperoom;

SET NAMES utf8;
SET character_set_client = utf8mb4;

DROP TABLE IF EXISTS object;
CREATE TABLE object(
	id INT NOT NULL AUTO_INCREMENT,	
    name VARCHAR(20) NOT NULL UNIQUE,
    description VARCHAR(200) NOT NULL,
    theme ENUM("Terror","Fiction","Fantasy") NOT NULL,
    price double NOT NULL,
    clueValor INT,
    img VARCHAR(100),  
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

INSERT INTO object (name, description, theme, price, clueValor, img)
VALUES
('Libro Antiguo', 'Un libro de historia con páginas amarillentas.', 'Terror', 25.50, 10, 'libro_antiguo.jpg'),
('Mapa del Tesoro', 'Un mapa antiguo que muestra la ubicación de un tesoro oculto.', 'Fiction', 50.00, 25, 'mapa_tesoro.png'),
('Poción Mágica', 'Una poción que otorga poderes temporales.', 'Fantasy', 100.00, 50, 'pocion_magica.jpg');

DROP TABLE IF EXISTS user;
CREATE TABLE user(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL,
	adress_street VARCHAR(100),
	adress_number INT,
	adress_floor CHAR(20),
	adress_door CHAR(20),
	city VARCHAR(30),
	zip_code CHAR(20),
	country VARCHAR(30),
	phone CHAR(50) UNIQUE,
	mail VARCHAR(50) UNIQUE,
	PRIMARY KEY (id)
);

INSERT INTO user (name, adress_street, adress_number, adress_floor, adress_door, city, zip_code, country, phone, mail)
VALUES 
('Ana García', 'Calle Mayor', 123, '3', 'A', 'Madrid', '28001', 'España', '+34612345678', 'ana.garcia@email.com'),
('John Smith', 'Main Street', 456, '2', 'B', 'New York', '10001', 'USA', '+15551234567', 'john.smith@email.com'),
('Marie Dupont', 'Rue de la Paix', 789, '1', 'C', 'Paris', '75001', 'France', '+33123456789', 'marie.dupont@email.com');