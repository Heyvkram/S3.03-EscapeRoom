DROP DATABASE IF EXISTS escaperoom;
CREATE DATABASE escaperoom;
USE escaperoom;

SET NAMES utf8;
SET character_set_client = utf8mb4;

CREATE TABLE `rooms` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(100) NOT NULL,
 `theme` enum('Terror','Fiction','Fantasy') NOT NULL,
 `level` enum('Easy','Intermediate','Hard') NOT NULL,
 `status` enum('Available','Not available') NOT NULL,
 `max_players` int(11) NOT NULL,
 `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS users;
CREATE TABLE users(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) NOT NULL,
    first_lastName VARCHAR(30) NOT NULL,
    seccond_lastName VARCHAR(30) NOT NULL,
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

CREATE TABLE `games` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(100),
 `room_id` int(11) NOT NULL,
 `user_id` int(11)NOT NULL,
 `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
 FOREIGN KEY(room_id) REFERENCES rooms(id),
 FOREIGN KEY(user_id) REFERENCES users(id),
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO users (name,first_lastName,seccond_lastName, adress_street, adress_number, adress_floor, adress_door, city, zip_code, country, phone, mail) VALUES 
('Ana','García', 'García','Calle Mayor', 123, '3', 'A', 'Madrid', '28001', 'España', '+34612345678', 'ana.garcia@email.com'),
('John','Smith','Dupont', 'Main Street', 456, '2', 'B', 'New York', '10001', 'USA', '+15551234567', 'john.smith@email.com'),
('Marie','Dupont','Dupont', 'Rue de la Paix', 789, '1', 'C', 'Paris', '75001', 'France', '+33123456789', 'marie.dupont@email.com'),

('Joan', 'Garcia', 'Perez', 'Carrer Major', 12, '3r', 'A', 'Barcelona', '08001', 'Espanya', '+34612345448', 'joan.garcia@example.com'),
('Maria', 'Lopez', 'Martinez', 'Avinguda Diagonal', 45, '1r', 'B', 'Madrid', '28001', 'Espanya', '+34698765432', 'maria.lopez@example.com'),
('Pere', 'Sanchez', 'Ruiz', 'Passeig de Gràcia', 7, '2n', 'C', 'València', '46001', 'Espanya', '+34655555555', 'pere.sanchez@example.com'),
('Laura', 'Fernandez', 'Gonzalez', 'Rambla Catalunya', 23, '4t', 'D', 'Sevilla', '41001', 'Espanya', '+34644444444', 'laura.fernandez@example.com'),
('Carles', 'Martinez', 'Garcia', 'Gran Via', 88, '5è', 'E', 'Bilbao', '48001', 'Espanya', '+34633333333', 'carles.martinez@example.com');

INSERT INTO `rooms` (`name`, `theme`, `level`, `status`, `max_players`) VALUES
('La Mansió Embruixada', 'Terror', 'Hard', 'Available', 6),
('El Laboratori Secret', 'Fiction', 'Intermediate', 'Available', 4),
('El Bosc Encantat', 'Fantasy', 'Easy', 'Not available', 5),
('La Cripta Perduda', 'Terror', 'Intermediate', 'Available', 3),
('El Viatge Intergalàctic', 'Fiction', 'Hard', 'Not available', 8);

INSERT INTO `games` (`name`,`room_id`,`user_id`) VALUES
(NULL,1,1),
('The Boys',1,2),
(NULL,1,3),
('Doomsday',2,1),
(NULL,3,1),
('Comiat de Sandra',3,5);



 