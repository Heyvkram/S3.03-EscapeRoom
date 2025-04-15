USE escape_room;
INSERT INTO `users` (
    `user_nick_name`, `user_name`, `user_surname`, `user_idCard`,
    `user_address_street`, `user_address_number`, `user_address_floor`, `user_address_door`,
    `user_city`, `user_zip_code`, `user_country`, `user_phone`, `user_mail`, `user_notifiable`
) VALUES (
    'johndoe', 'John', 'Doe', '12345678A',
    'Main Street', 123, '3rd', 'A',
    'New York', '10001', 'USA', '+15551234567', 'john.doe@example.com', 'y'
);

INSERT INTO `users` (
     `user_nick_name`, `user_name`, `user_surname`, `user_idCard`,
    `user_address_street`, `user_address_number`, `user_address_floor`, `user_address_door`,
    `user_city`, `user_zip_code`, `user_country`, `user_phone`, `user_mail`, `user_notifiable`
) VALUES (
    'janeSmith', 'Jane', 'Smith', '87654321B',
    'Oak Avenue', 45, '2nd', 'B',
    'London', 'SW1A 2AA', 'UK', '+442079460991', 'jane.smith@example.co.uk', 'y'
);

INSERT INTO `users` (
    `user_nick_name`, `user_name`, `user_surname`, `user_idCard`,
    `user_address_street`, `user_address_number`, `user_address_floor`, `user_address_door`,
    `user_city`, `user_zip_code`, `user_country`, `user_phone`, `user_mail`
) VALUES (
    'pedroG', 'Pedro', 'García', 'X1234567C',
    'Calle Mayor', 789, '1º', 'C',
    'Madrid', '28001', 'Spain', '+34911234567', 'pedro.garcia@example.es'
);

INSERT INTO `users` (
    `user_nick_name`, `user_name`, `user_surname`, `user_idCard`,
    `user_address_street`, `user_address_number`, `user_address_floor`, `user_address_door`,
    `user_city`, `user_zip_code`, `user_country`, `user_phone`, `user_mail`
) VALUES (
    'yukikoT', 'Yukiko', 'Tanaka', 'Z7654321D',
    'Sakura Street', 10, '4th', 'D',
    'Tokyo', '100-0005', 'Japan', '+81312345678', 'yukiko.tanaka@example.jp'
);

INSERT INTO `users` (
    `user_nick_name`, `user_name`, `user_surname`, `user_idCard`,
    `user_address_street`, `user_address_number`, `user_address_floor`, `user_address_door`,
    `user_city`, `user_zip_code`, `user_country`, `user_phone`, `user_mail`, `user_notifiable`
) VALUES (
    'marieL', 'Marie', 'Laurent', 'Y1234567E',
    'Rue de la Paix', 22, '5ème', 'E',
    'Paris', '75001', 'France', '+33112345678', 'marie.laurent@example.fr','y'
);

INSERT INTO `users` (`user_nick_name`, `user_name`, `user_surname`, `user_mail`, `user_idCard`, `user_address_street`, `user_address_number`, `user_address_floor`, `user_address_door`, `user_city`, `user_zip_code`, `user_country`, `user_phone`, `user_notifiable`) VALUES
( 'juan doe', 'John', 'Doe', 'john.doe2000@example.com', '12345678A', 'Carrer Fictici', 10, '3', 'B', 'Barcelona', '08001', 'Espanya', '+34 931234567', 'y'),
( 'Paula smith', 'Jane', 'Smith', 'jane.smithsss@example.com', '98765432Z', 'Avinguda Exemple', 25, '1', 'A', 'Madrid', '28002', 'Espanya', '+34 919876543', 'n'),
( 'petersen jones', 'Peter', 'Jones', 'peter.jonesssss@example.com', '45678912X', 'Plaça Central', 5, NULL, NULL, 'València', '46003', 'Espanya', '+34 666555444', 'y'),
( 'sara_williams', 'Sara', 'Williams', 'sara.williamsss@example.com', '78912345Y', 'Ronda Universitat', 15, '2', 'C', 'Sevilla', '41004', 'Espanya', '+34 654321789', NULL),
( 'mike_brown', 'Mike', 'Brown', 'mike.browssss@example.com', '32165498W', 'Travessera de Gràcia', 30, NULL, NULL, 'Bilbao', '48005', 'Espanya', '+34 944112233', 'y');

INSERT INTO `users` (`user_nick_name`, `user_name`, `user_surname`, `user_mail`, `user_idCard`, `user_address_street`, `user_address_number`, `user_address_floor`, `user_address_door`, `user_city`, `user_zip_code`, `user_country`, `user_phone`, `user_notifiable`) VALUES
('lisa_green', 'Lisa', 'Green', 'lisa.green2000@example.com', '54321876B', 'Carrer de la Pau', 42, '4', 'A', 'Girona', '17001', 'Espanya', '+34 972112233', 'n'),
('david_white', 'David', 'White', 'david.whitewhite@example.com', '21436587C', 'Rambla Nova', 18, 'baixos', 'D', 'Tarragona', '43002', 'Espanya', '+34 600112233', 'n'),
('emily_clark', 'Emily', 'Clark', 'emily.clarkkk@example.com', '87654321D', 'Avinguda Diagonal', 60, '7', NULL, 'Lleida', '25003', 'Espanya', '+34 973445566', NULL),
('kevin_hall', 'Kevin', 'Hall', 'kevin.hallfff@example.com', '65432178E', 'Passeig de Gràcia', 100, NULL, NULL, 'Sabadell', '08201', 'Espanya', '+34 678901234', 'y'),
('olivia_adams', 'Olivia', 'Adams', 'olivia.adamsffff@example.com', '13579246F', 'Gran Via', 75, '1', 'B', 'Terrassa', '08221', 'Espanya', '+34 937890123', 'n');

INSERT INTO `notifications` (
    `notification_title`,
    `notification_short_description`,
    `notification_message`,
    `notification_type`
) VALUES
(
    'Avís de Manteniment',
    'Manteniment programat del sistema',
    'El sistema estarà en manteniment el proper diumenge a partir de les 02:00. Disculpeu les molèsties.',
    'Generic'
),
(
    'Recordatori de Pagament',
    'Recordatori de pagament pendent',
    'El pagament de la factura número 1234 està pendent. Si us plau, realitzi el pagament abans del 30 de juny.',
    'Personal'
),
(
    'Nova Versió Disponible',
    'Actualització del software',
    'Hi ha una nova versió del software disponible. Si us plau, actualitzi el software per obtenir les últimes funcions i correccions de seguretat.',
    'Generic'
),
(
    'Missatge Personalitzat',
    'Missatge personalitzat per a l\'usuari',
    'Hola usuari, tenim una oferta especial per a tu. Si estàs interessat, fes clic aquí: [enllaç]',
    'Personal'
),
(
    'Alerta de Seguretat',
    'Alerta de seguretat important',
    'S\'ha detectat una activitat sospitosa al teu compte. Si no has fet aquesta activitat, canvia la teva contrasenya immediatament.',
    'Generic'
);

INSERT INTO `rooms` (`room_name`, `room_theme`, `room_level`, `room_status`, `room_max_players`, `room_date`) VALUES
('La Mansió Embruixada', 'Terror', 'Intermediate', 'Available', 6, NOW()),
('El Laboratori Secret', 'Fiction', 'Hard', 'Not available', 4, NOW()),
( 'El Bosc Màgic', 'Fantasy', 'Easy', 'Available', 8, NOW()),
( 'La Nau Estelar Perduda', 'Fiction', 'Intermediate', 'Available', 5, NOW()),
( 'El Castell del Drac', 'Fantasy', 'Hard', 'Not available', 3, NOW());


INSERT INTO payments ( payment_mode, payment_price, user_id)
VALUES ('Credit card', 55, 1);


INSERT INTO payments (payment_mode, payment_price, user_id)
VALUES ('Bizum', 28, 5);


INSERT INTO payments (payment_mode, payment_price, user_id)
VALUES ( 'PayPal', 72, 1);


INSERT INTO payments (payment_mode, payment_price, user_id)
VALUES ( 'Credit card', 39, 3);


INSERT INTO payments (payment_mode, payment_price, user_id)
VALUES ( 'Bizum', 15, 4);

INSERT INTO `game_sessions` (`room_id`, `payment_id`, `user_id`, `accepted`) VALUES
(1, 1, 1001, 1),
(2, 2, 1002, 0),
(1, 3, 1003, 1),
(3, 4, 1004, 1),
(2, 5, 1005, 0);




