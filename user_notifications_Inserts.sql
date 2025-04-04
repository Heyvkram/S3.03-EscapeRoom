USE escape_room;
INSERT INTO `users` (
    `user_id`, `user_nick_name`, `user_name`, `user_surname`, `user_idCard`,
    `user_address_street`, `user_address_number`, `user_address_floor`, `user_address_door`,
    `user_city`, `user_zip_code`, `user_country`, `user_phone`, `user_mail`
) VALUES (
    1, 'johndoe', 'John', 'Doe', '12345678A',
    'Main Street', 123, '3rd', 'A',
    'New York', '10001', 'USA', '+15551234567', 'john.doe@example.com'
);

INSERT INTO `users` (
    `user_id`, `user_nick_name`, `user_name`, `user_surname`, `user_idCard`,
    `user_address_street`, `user_address_number`, `user_address_floor`, `user_address_door`,
    `user_city`, `user_zip_code`, `user_country`, `user_phone`, `user_mail`
) VALUES (
    2, 'janeSmith', 'Jane', 'Smith', '87654321B',
    'Oak Avenue', 45, '2nd', 'B',
    'London', 'SW1A 2AA', 'UK', '+442079460991', 'jane.smith@example.co.uk'
);

INSERT INTO `users` (
    `user_id`, `user_nick_name`, `user_name`, `user_surname`, `user_idCard`,
    `user_address_street`, `user_address_number`, `user_address_floor`, `user_address_door`,
    `user_city`, `user_zip_code`, `user_country`, `user_phone`, `user_mail`
) VALUES (
    3, 'pedroG', 'Pedro', 'García', 'X1234567C',
    'Calle Mayor', 789, '1º', 'C',
    'Madrid', '28001', 'Spain', '+34911234567', 'pedro.garcia@example.es'
);

INSERT INTO `users` (
    `user_id`, `user_nick_name`, `user_name`, `user_surname`, `user_idCard`,
    `user_address_street`, `user_address_number`, `user_address_floor`, `user_address_door`,
    `user_city`, `user_zip_code`, `user_country`, `user_phone`, `user_mail`
) VALUES (
    4, 'yukikoT', 'Yukiko', 'Tanaka', 'Z7654321D',
    'Sakura Street', 10, '4th', 'D',
    'Tokyo', '100-0005', 'Japan', '+81312345678', 'yukiko.tanaka@example.jp'
);

INSERT INTO `users` (
    `user_id`, `user_nick_name`, `user_name`, `user_surname`, `user_idCard`,
    `user_address_street`, `user_address_number`, `user_address_floor`, `user_address_door`,
    `user_city`, `user_zip_code`, `user_country`, `user_phone`, `user_mail`
) VALUES (
    5, 'marieL', 'Marie', 'Laurent', 'Y1234567E',
    'Rue de la Paix', 22, '5ème', 'E',
    'Paris', '75001', 'France', '+33112345678', 'marie.laurent@example.fr'
);

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
