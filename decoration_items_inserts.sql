USE escape_room;

-- Insertar 5 registros en la tabla 'rooms'
INSERT INTO `rooms` (`room_name`, `room_theme`, `room_level`, `room_status`, `room_max_players`) VALUES
('La Mansión Embrujada', 'Terror', 'Hard', 'Available', 6),
('La Nave Espacial Perdida', 'Fiction', 'Intermediate', 'Available', 4),
('El Bosque Encantado', 'Fantasy', 'Easy', 'Available', 5),
('El Laboratorio Secreto', 'Fiction', 'Hard', 'Not available', 3),
('El Circo Maldito', 'Terror', 'Intermediate', 'Available', 6);

-- Insertar 5 registros en la tabla 'clues'
INSERT INTO `clues` (`clue_id`, `clue_name`, `clue_theme`, `clue_level`, `clue_game_phase`, `clue_price`, `clue_value`, `room_id`) VALUES
(1, 'El Diario del Fantasma', 'Terror', 'Hard', 'Inicio', 10, 15, 1),
(2, 'El Mapa de la Galaxia', 'Fiction', 'Intermediate', 'Medio', 8, 12, 2),
(3, 'La Poción de la Invisibilidad', 'Fantasy', 'Easy', 'Final', 5, 8, 3),
(4, 'El Código de Seguridad', 'Fiction', 'Hard', 'Medio', 12, 18, 4),
(5, 'El Espejo Distorsionado', 'Terror', 'Intermediate', 'Inicio', 9, 14, 5);

-- Insertar 5 registros en la tabla 'decoration_items'
INSERT INTO `decoration_items` (`decoration_item_id`, `decoration_item_name`, `decoration_item_description`, `decoration_item_theme`, `decoration_item_price`, `decoration_item_clue_valor`, `decoration_item_img`, `clue_id`, `room_id`) VALUES
(1, 'Espejo Roto', 'Un espejo antiguo con grietas extrañas.', 'Terror', 35.00, 10, 'espejo_roto.jpg', 1, 1),
(2, 'Panel Estelar', 'Un panel con constelaciones brillantes.', 'Fiction', 42.50, 12, 'panel_estelar.png', 2, 2),
(3, 'Libro de Hechizos', 'Un libro antiguo con símbolos mágicos.', 'Fantasy', 30.00, 8, 'libro_hechizos.svg', 3, 3),
(4, 'Terminal de Datos', 'Una terminal con información encriptada.', 'Fiction', 50.00, 18, 'terminal_datos.gif', 4, 4),
(5, 'Muñeca Poseída', 'Una muñeca de porcelana con ojos rojos.', 'Terror', 40.00, 14, 'muneca_poseida.jpeg', 5, 5);