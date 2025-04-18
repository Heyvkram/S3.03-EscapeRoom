USE escape_room;

INSERT INTO `decoration_items` (`decoration_item_name`, `decoration_item_description`, `decoration_item_theme`, `decoration_item_price`, `decoration_item_clue_valor`, `decoration_item_img`) VALUES
('Calavera', 'Calavera antigua con ojos brillantes.', 'Terror', 25.50, 10, 'calavera.jpg'),
('Mapa Estelar', 'Mapa detallado de constelaciones.', 'Fantasy', 30.00, 15, 'mapa_estelar.png'),
('Libro Antiguo', 'Libro con símbolos extraños.', 'Fiction', 18.75, 8, 'libro_antiguo.pdf'),
('Espada Oxidada', 'Espada de metal con grabados.', 'Fantasy', 40.20, 20, 'espada_oxidada.jpg'),
('Muñeca Rota', 'Muñeca de porcelana con marcas extrañas.', 'Terror', 22.90, 12, 'muneca_rota.png');

INSERT INTO `clues` (`clue_title`, `clue_description_user`, `clue_description_admin`, `clue_theme`, `clue_level`, `clue_game_phase`, `clue_price`, `clue_value`) VALUES
('El Secreto del Mapa', 'Descifra los símbolos del mapa.', 'El mapa contiene coordenadas encriptadas.', 'Fantasy', 'Intermediate', 'Fase 2', 15, 15),
('La Calavera Parlante', 'Encuentra la frase oculta en la calavera.', 'La calavera responde a preguntas específicas.', 'Terror', 'Hard', 'Fase 3', 20, 20),
('El Código del Libro', 'Resuelve el enigma del libro.', 'El libro tiene un cifrado de sustitución.', 'Fiction', 'Easy', 'Fase 1', 10, 8),
('La Espada Mágica', 'Descubre el poder de la espada.', 'La espada activa un mecanismo oculto.', 'Fantasy', 'Hard', 'Fase 4', 25, 25),
('El Misterio de la Muñeca', 'Encuentra el mensaje oculto en la muñeca.', 'La muñeca tiene un compartimento secreto.', 'Terror', 'Intermediate', 'Fase 2', 18, 12);

INSERT INTO `rooms` (`room_name`, `room_theme`, `room_level`, `room_status`, `room_max_players`) VALUES
('La Cripta Maldita', 'Terror', 'Hard', 'Available', 4),
('El Jardín de las Estrellas', 'Fantasy', 'Intermediate', 'Available', 6),
('El Laboratorio Secreto', 'Fiction', 'Easy', 'Available', 5),
('La Forja del Dragón', 'Fantasy', 'Hard', 'Not available', 3),
('La Mansión de los Espejos', 'Terror', 'Intermediate', 'Available', 4);