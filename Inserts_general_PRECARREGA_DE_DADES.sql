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
('kevin_hall', 'Kevin', 'Hall', 'kevin.hallfff@example.com', '65432178E', 'Passeig de Gràcia', 100, NULL, NULL, 'Girona', '08201', 'Espanya', '+34 678901234', 'y'),
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
),
(
    'Missatge sobre la Sessió',
    'Missatge personalitzat per a l\'usuari',
    'Hola usuari, s\'ha modificat l\'estat de la seva sessió a ',
    'Personal'
);

INSERT INTO `rooms` (`room_name`, `room_theme`, `room_level`, `room_status`, 
`room_max_players`, `room_date`, `room_price`, `room_cost_value` ) VALUES
('La Mansió Embruixada', 'Terror', 'Intermediate', 'Available', 6, NOW(),10.7,60.7),
('El Laboratori Secret', 'Fiction', 'Hard', 'Not available', 4, NOW(),6.7,34.7),
( 'El Bosc Màgic', 'Fantasy', 'Easy', 'Available', 8, NOW(),0.7,109.7),
( 'La Nau Estelar Perduda', 'Fiction', 'Intermediate', 'Available', 5, NOW(),1.7,100.7),
( 'El Castell del Drac', 'Fantasy', 'Hard', 'Not available', 3, NOW(),6.7,60.0);


INSERT INTO game_sessions (
    room_id,
    room_name,
    user_id,
    user_nick_name,
    room_price,
    payment_type,
    status
) VALUES
(
    1,
    'La Mansió Embruixada',
    1,
    'AventuraHeroi',
    45.00,
    'Credit Card',
    'Consumed'
),
(
    1,
    'La Mansió Embruixada',
    2,
    'MestreClau',
    50.00,
    'Cash',
    'Consumed'
),
(
    1,
    'La Mansió Embruixada',
    3,
    'NavegantEstelar',
    48.00,
    'PayPal',
    'Consumed'
),
(
    2,
    'El Laboratori Secret',
    4,
    'Enigmatic',
    42.50,
    'Bizum',
    'Consumed'
),
(
    2,
    'El Laboratori Secret',
    5,
    'CercadorTresors',
    55.00,
    'Credit Card',
    'Consumed'
),
(
    3,
    'La Nau Estelar Perduda',
    1,
    'DrMisteri',
    47.00,
    'Cash',
    'Consumed'
),
(
    3,
    'La Nau Estelar Perduda',
    2,
    'CapitàEnigma',
    52.00,
    'PayPal',
    'Consumed'
),
(
    3,
    'La Nau Estelar Perduda',
    3,
    'ExploradorLunar',
    49.90,
    'Bizum',
    'Consumed'
),
(
    3,
    'La Nau Estelar Perduda',
    4,
    'AgentSecret',
    46.00,
    'Credit Card',
    'Consumed'
),
(
    3,
    'La Nau Estelar Perduda',
    5,
    'GuardiàPortes',
    51.50,
    'Cash',
    'Unpaied'
);

USE escape_room;

INSERT INTO `decoration_items` (`decoration_item_name`, `decoration_item_description`, `decoration_item_theme`, `decoration_item_price`, `decoration_item_clue_valor`, `decoration_item_img`) VALUES
('Crani Realista', 'Crani humà de mida natural amb detalls esgarrifosos. Perfecte per a decoracions embruixades.', 'Terror', 29.99, 7, 'crani.jpg'),
('Aranya Gegant', 'Aranya peluda de grans dimensions amb ulls brillants. Ideal per penjar al sostre o col·locar en una cantonada.', 'Terror', 39.95, 9, 'aranya_gegant.png'),
('Mà Tallada', 'Rèplica realista d\'una mà tallada amb efectes de sang. Un element impactant per a qualsevol escena de terror.', 'Terror', 19.50, 6, 'ma_tallada.jpeg'),
('Ninot Possessed', 'Ninot antic d\'aspecte inquietant amb la mirada fixa. Sembla que et segueix amb els ulls...', 'Terror', 45.00, 8, 'ninot_possessed.gif'),
('Tela d\'Aranya', 'Gran tela d\'aranya artificial amb teranyines fines i resistents. Cobreix grans espais per crear un ambient abandonat i terrorífic.', 'Terror', 12.75, 5, 'tela_aranya.svg');

INSERT INTO `decoration_items` (`decoration_item_name`, `decoration_item_description`, `decoration_item_theme`, `decoration_item_price`, `decoration_item_clue_valor`, `decoration_item_img`) VALUES
('Casc Espacial Retro', 'Casc d\'astronauta d\'estil vintage amb visera daurada. Ideal per a una decoració amb toc nostàlgic futurista.', 'Fiction', 59.99, 10, 'casc_retro.png'),
('Làser de Joguina', 'Rèplica d\'un làser espacial amb efectes de llum i so. Perfecte per a afegir un toc de ciència-ficció a qualsevol espai.', 'Fiction', 24.50, 6, 'laser_joguina.jpg'),
('Nau Espacial Miniatura', 'Model detallat d\'una nau espacial clàssica. Un objecte de col·leccionista per a amants de la ficció.', 'Fiction', 35.00, 8, 'nau_mini.gif'),
('Pòster Galàxia Nebula', 'Pòster d\'alta qualitat amb una imatge impressionant d\'una nebulosa galàctica amb colors vibrants.', 'Fiction', 15.99, 5, 'poster_nebula.svg'),
('Robot de Taula', 'Petita figura de robot amb disseny futurista i detalls articulats. Un element decoratiu modern i tecnològic.', 'Fiction', 42.00, 7, 'robot_taula.jpeg');

INSERT INTO `decoration_items` (`decoration_item_name`, `decoration_item_description`, `decoration_item_theme`, `decoration_item_price`, `decoration_item_clue_valor`, `decoration_item_img`) VALUES
('Espasa de Drac Petita', 'Rèplica detallada d\'una espasa amb empunyadura amb forma de cap de drac. Ideal per a un toc de fantasia medieval.', 'Fantasy', 32.50, 7, 'espasa_drac.png'),
('Poció Màgica Il·luminada', 'Ampolla d\'aspecte antic amb un líquid brillant de color misteriós. Amb llum LED interior per a un efecte màgic.', 'Fantasy', 28.99, 9, 'pocio_magica.gif'),
('Mapa del Tresor Antic', 'Mapa de pergamí amb marques i símbols antics que semblen indicar la ubicació d\'un tresor amagat.', 'Fantasy', 18.75, 6, 'mapa_tresor.jpeg'),
('Estàtua de Unicorn', 'Petita estàtua elegant d\'un unicorn amb crinera i cua brillants. Un símbol de màgia i puresa.', 'Fantasy', 40.00, 8, 'estatua_unicorn.svg'),
('Llibre d\'Encanteris', 'Rèplica d\'un llibre antic amb símbols misteriosos a la coberta. Perfecte per a crear un ambient de biblioteca encantada.', 'Fantasy', 21.25, 5, 'llibre_encanteris.jpg');

INSERT INTO `clues` (`clue_title`, `clue_description_user`, `clue_description_admin`, `clue_theme`, `clue_level`, `clue_game_phase`, `clue_price`, `clue_value`) VALUES
('El Murmur a les Fosques', 'Sento un murmuri constant que prové de les parets. Què vol dir?', 'Un so preenregistrat d\'un murmuri inquietant s\'activa en una zona específica. Pot indicar la presència d\'un esperit o un altre element.', 'Terror', 'Intermediate', 'Ambientació', 15, 8),
('L\'Ombra que Es Mou', 'He vist una ombra allargada que s\'ha mogut sola en aquesta habitació. Estic imaginant coses?', 'Un efecte de llum projecta una ombra que es mou lentament per la paret o el terra, creant una il·lusió de presència.', 'Terror', 'Easy', 'Observació', 10, 5),
('El Diari Sagnant', 'He trobat un diari amb pàgines escrites amb el que sembla sang. Què hi diu?', 'Un llibre antic amb pàgines tacades amb pintura vermella simulant sang. Algunes frases clau poden estar subratllades o marcades.', 'Terror', 'Hard', 'Text', 20, 12),
('La Nina Perduda', 'Hi ha una nina antiga asseguda en una cadira. Té una mirada molt estranya...', 'Una nina de porcellana amb un aspecte inquietant es col·loca estratègicament. Pot contenir una petita clau amagada o un missatge escrit a la base.', 'Terror', 'Intermediate', 'Objecte', 18, 9),
('El Crit Silenciat', 'Sembla que algú va intentar cridar aquí, però el so es va quedar atrapat. Puc sentir una presència freda.', 'Un efecte de so molt subtil d\'un crit ofegat es reprodueix en un moment determinat. La temperatura d\'una petita àrea pot baixar lleugerament amb un dispositiu.', 'Terror', 'Easy', 'Ambientació/Sensorial', 12, 6);

INSERT INTO `clues` (`clue_title`, `clue_description_user`, `clue_description_admin`, `clue_theme`, `clue_level`, `clue_game_phase`, `clue_price`, `clue_value`) VALUES
('Comunicació Interestel·lar', 'He rebut un missatge amb patrons estranys. Sembla un intent de comunicació...', 'Un enregistrament de sons sintetitzats amb patrons repetitius es reprodueix. Un decodificador visual o auditiu pot ser necessari.', 'Fiction', 'Hard', 'Àudio/Visual', 22, 13),
('Coordenades Desconegudes', 'Hi ha una sèrie de números i símbols que no reconec. Podrien ser coordenades?', 'Una seqüència de números i símbols (hexadecimal, binari, etc.) es troba escrita o projectada. Potser cal convertir-los a coordenades geogràfiques o d\'un altre sistema.', 'Fiction', 'Intermediate', 'Codi/Número', 17, 10),
('Artefacte Tecnològic Antic', 'He trobat un objecte amb una tecnologia que no sembla de la nostra època. Quina funció tenia?', 'Un objecte amb llums intermitents, botons sense etiquetar o parts mòbils es col·loca a la vista. Potser cal manipular-lo d\'una manera específica.', 'Fiction', 'Intermediate', 'Objecte/Mecànic', 19, 11),
('El Holograma Fragmentat', 'Una imatge parpellejant apareix en l\'aire, però està incompleta. Què intenta mostrar?', 'Un projector mostra un holograma parcial o amb errors. Les parts visibles poden donar pistes sobre un codi, una ubicació o un personatge.', 'Fiction', 'Easy', 'Visual', 14, 7),
('Registre de la Nau Perduda', 'He trobat un arxiu de dades amb entrades sobre una missió espacial que va desaparèixer. Què va passar?', 'Un document digital o imprès simula un registre de bitàcora amb dates, ubicacions i esdeveniments. Algunes entrades poden estar censurades o contenir missatges ocults.', 'Fiction', 'Hard', 'Text/Digital', 25, 15);

INSERT INTO `clues` (`clue_title`, `clue_description_user`, `clue_description_admin`, `clue_theme`, `clue_level`, `clue_game_phase`, `clue_price`, `clue_value`) VALUES
('El Enigma de la Runa Antiga', 'He trobat una pedra amb un símbol estrany gravat. Què significa?', 'Una pedra o objecte amb una runa específica gravada. Un diccionari de runes o una altra pista pot ser necessari per traduir-la.', 'Fantasy', 'Hard', 'Símbol/Objecte', 21, 14),
('La Cançó del Bosc Encantat', 'Sento una melodia suau que sembla provenir del bosc. Em sona familiar...', 'Una petita gravació d\'una melodia etèria es reprodueix en una àrea específica. Potser cal identificar la melodia o trobar la seva partitura.', 'Fantasy', 'Intermediate', 'Àudio', 16, 9),
('El Fragment del Mapa Màgic', 'Tinc un tros d\'un mapa amb dibuixos de criatures mítiques i llocs desconeguts. On em porta?', 'Un fragment d\'un mapa il·lustrat amb elements de fantasia. En combinar-lo amb altres fragments (si n\'hi ha), pot revelar una ubicació.', 'Fantasy', 'Intermediate', 'Visual/Puzzle', 18, 11),
('El Jeroglífic de la Criatura Alada', 'Hi ha un escrit amb símbols que representen animals fantàstics. Puc desxifrar-lo?', 'Una sèrie de jeroglífics o pictogrames que representen criatures mítiques (dracs, grifons, etc.). Cada símbol pot correspondre a una lletra o un número.', 'Fantasy', 'Hard', 'Símbol/Text', 23, 12),
('L\'Objecte Imbuït de Màgia', 'He trobat un objecte que em transmet una energia estranya. Quines propietats té?', 'Un objecte (un anell, un amulet, etc.) amb un aspecte distintiu. Pot reaccionar a la llum ultraviolada revelant un missatge ocult, o tenir un mecanisme senzill amagat.', 'Fantasy', 'Easy', 'Objecte/Sensorial', 13, 7);

INSERT INTO decoration_items (
    decoration_item_name,
    decoration_item_description,
    decoration_item_theme,
    decoration_item_price,
    decoration_item_clue_valor,
    decoration_item_img
) VALUES
('Crani Lluentor', 'Crani humà amb ulls LED vermells brillants.', 'Terror', 29.99, 10, 'crani_lluentor.jpg'),
('Aranya Gegant de Peluix', 'Aranya de peluix gegant amb potes articulades i so esgarrifós.', 'Terror', 39.95, 12, 'aranya_peluix.png'),
('Mirall Embruixat', 'Mirall antic que mostra reflexos distorsionats i figures fantasmals.', 'Terror', 49.99, 15, 'mirall_embruixat.jpeg'),
('Calabaza Possessa', 'Calabaza amb llum interior i expressió malévola, que sembla moure\'s sola.', 'Terror', 34.99, 11, 'calabaza_possessa.gif'),
('Làpida Emergent', 'Làpida de làtex que emergeix del terra, amb efectes de so de cementiri.', 'Terror', 59.95, 14, 'lapida_emergent.jpg'),
('Espasa de Llum', 'Rèplica d\'espasa de llum amb efectes de so i llum.', 'Fiction', 79.99, 10, 'espasa_llum.jpg'),
('Robot Androide', 'Figura d\'androide amb articulacions mòbils i ulls brillants.', 'Fiction', 99.95, 12, 'robot_androide.png'),
('Nau Espacial', 'Model detallat de nau espacial amb llums i sons.', 'Fiction', 149.99, 15, 'nau_espacial.jpeg'),
('Portal Dimensional', 'Rèplica de portal dimensional amb efectes de llum i fum.', 'Fiction', 129.99, 11, 'portal_dimensional.gif'),
('Mapa Estel·lar', 'Mapa detallat de la galàxia amb constel·lacions i planetes.', 'Fiction', 49.95, 14, 'mapa_estellar.jpg'),
('Espasa Màgica', 'Espasa amb gravats rúnics brillants i empunyadura ornamentada.', 'Fantasy', 89.99, 11, 'espasa_magica.jpg'),
( 'Drac Petit', 'Figura de drac detallada amb ales articulades i escates brillants.', 'Fantasy', 129.95, 14, 'drac_petit.png'),
('Bosc Encantat en Ampolla', 'Rèplica d\'un bosc en miniatura dins d\'una ampolla de vidre amb llum suau.', 'Fantasy', 99.99, 13, 'bosc_ampolla.jpeg'),
('Mapa del Tresor Perdut', 'Mapa antic amb símbols misteriosos i marques que indiquen un tresor amagat.', 'Fantasy', 69.99, 10, 'mapa_tresor.gif'),
('Calze de l\'Elixir', 'Calze daurat amb incrustacions de gemmes, per contenir pocions màgiques.', 'Fantasy', 79.95, 12, 'calze_elixir.jpg');

INSERT INTO clues (
    clue_title,
    clue_description_user,
    clue_description_admin,
    clue_theme, clue_level,
    clue_game_phase, clue_price
) VALUES
    ('Passos a la Foscor',
    'Sento passos que s\'acosten, però no veig ningú. Què està passant?',
    'S\'activa un efecte de so de passos apropant-se lentament a la foscor.',
    'Terror', 'Easy', 'So', 17.00),
    ('Ombra Misteriosa',
    'Veig una ombra estranya movent-se per la paret. No sé d\'on ve.',
    'Es projecta una ombra amb una forma humanoide que es mou de forma erràtica.',
    'Terror', 'Easy', 'Visual', 1.00),
    ('Objecte que Cau',
    'Un objecte ha caigut de cop sense motiu aparent. Em fa mala espina.',
    'Es programa la caiguda d\'un objecte lleuger (un llibre, un gerro petit) mitjançant un mecanisme remot.',
    'Terror', 'Easy', 'Objecte', 1.00),
    ('Murmuri Llunyà',
    'Sento un murmuri feble que sembla cridar-me. No entenc què diu.',
    'Es reprodueix un murmuri inintel·ligible a baix volum, amb reverberació, des d\'un altaveu ocult.',
    'Terror', 'Easy', 'So', 1.00),
    ('Fred Intens',
    'De sobte, sento un fred que m\'arriba fins als ossos en aquesta habitació.',
    'S\'activa un sistema d\'aire condicionat localitzat per crear una sensació de fred sobtat.',
    'Terror', 'Easy', 'Sensorial', 7.00),
    ('El So Distorsionat',
    'Un so gutural i distorsionat ressona a la foscor, sembla venir de totes bandes.',
    'Es reprodueix una gravació de baixa freqüència amb efectes de distorsió i reverberació, creant una sensació envoltant i inquietant.',
    'Terror', 'Easy', 'So', 17.00),
    ('La Visió Perifèrica',
    'Veig moviments estranys amb el rabillo de l\'ull, però quan giro el cap no hi ha res.',
    'S\'utilitzen llums estroboscòpiques de baixa intensitat i col·locació estratègica per crear la il·lusió de moviment a la perifèria de la visió.',
    'Terror', 'Intermediate', 'Visual', 19.50),
    ('El Tacte Gelat',
    'Sento un frec fred i humit a la meva pell, com si alguna cosa invisible em toqués.',
    'S\'utilitza un dispositiu que allibera una ràfega d\'aire fred i humit en moments específics, combinat amb un so subtil de frec.',
    'Terror', 'Intermediate', 'Sensorial', 22.00),
    ('L\'Objecte Maleït',
    'Un objecte antic i deteriorat emana una energia estranya. Sento que alguna cosa dolenta passarà.',
    'Es col·loca un objecte amb una història inventada de malediccions o successos tràgics, i se li afegeixen elements com llums tènues, vibracions o un lleuger augment de la temperatura.',
    'Terror', 'Intermediate', 'Objecte', 25.00),
    ('El Malson Lúcid',
    'Em sento atrapat en un somni del qual no puc despertar. Tot sembla real, però sé que no ho és.',
    'Es combinen efectes de llums parpellejants, sons onírics distorsionats i una narració en veu baixa que descriu un malson, creant una sensació de desorientació i angoixa.',
    'Terror', 'Intermediate', 'Ambientació', 21.00),
    ('El Laberint Sonor',
    'Els sons canvien constantment, creant una desorientació total. No sé d\'on vénen ni cap a on vaig.',
    'S\'utilitza un sistema d\'àudio multicanal amb sons binaurals i freqüències variables per generar una sensació de laberint acústic.',
    'Terror', 'Hard', 'So', 27.00),
    ('La Il·lusió Òptica',
    'El que veig desafia tota lògica. Les dimensions canvien i els objectes es distorsionen davant dels meus ulls.',
    'Es combinen tècniques de realitat augmentada, miralls distorsionats i projeccions hologràfiques per crear il·lusions òptiques complexes i pertorbadores.',
    'Terror', 'Hard', 'Visual', 30.00),
    ('L\'Enigma Tàctil',
    'Sento textures i formes estranyes que no puc identificar. Algunes es mouen i canvien sota el meu tacte.',
    'S\'utilitzen guants háptics amb retroalimentació de força i materials amb propietats inusuals (gel que canvia de forma, superfícies amb vibracions complexes) per crear sensacions tàctils ambigües i desconcertants.',
    'Terror', 'Hard', 'Sensorial', 32.50),
    ('La Presència Incorpòria',
    'Sento una presència invisible que m\'observa i em segueix. Puc percebre el seu alè i la seva proximitat.',
    'Es combinen efectes de so 3D, canvis de temperatura localitzats i lleugeres ràfegues d\'aire per simular la sensació d\'una presència invisible.',
    'Terror', 'Hard', 'Ambientació', 29.00),
    ('El Record Alterat',
    'Els meus records es barregen i es distorsionen. No sé què és real i què és una invenció de la meva ment.',
    'S\'utilitzen tècniques de suggestió hipnòtica, realitat virtual amb escenaris onírics i manipulació de la memòria a curt termini per crear una experiència de confusió i pèrdua d\'identitat.',
    'Terror', 'Hard', 'Mental', 35.00);


INSERT INTO clues (
    clue_title,
    clue_description_user,
    clue_description_admin,
    clue_theme, clue_level,
    clue_game_phase, clue_price, clue_value
) VALUES
(
    'La Clau Estel·lar',
    'Un mapa estrany amb símbols galàctics apareix davant teu. On et porta?',
    'El jugador ha d\'interpretar un senzill mapa estel·lar per identificar la ubicació d\'una "clau" oculta a la sala.',
    'Fiction',
    'Easy',
    'Exploració Espacial',
    8.50,
    10
),
(
    'El Missatge Xifrat',
    'Una vella ràdio emet un so repetitiu. Sembla un codi.',
    'Es reprodueix un codi morse o un xifrat simple (com un Caesar shift petit) que el jugador ha de desxifrar per obtenir una paraula clau.',
    'Fiction',
    'Intermediate', -- Nota: En l'original posava Easy, ho he deixat Intermediate com en l'exemple anterior
    'Comunicació',
    7.00,
    15
),
(
    'Artefacte Temporal',
    'Trobes un dispositiu que brilla suaument i emet un lleuger brunzit. Què fa?',
    'El jugador ha d\'identificar la funció bàsica d\'un objecte fictici (per exemple, "activar la projecció hologràfica") mitjançant una interacció simple.',
    'Fiction',
    'Intermediate', -- Nota: En l'original posava Easy, ho he deixat Intermediate com en l'exemple anterior
    'Tecnologia',
    9.00,
    12
),
(
    'Petjades Estranyes',
    'Hi ha rastres inusuals al terra, diferents als de qualsevol criatura coneguda.',
    'El jugador ha de seguir una sèrie de marques visuals (petjades, esgarrapades) que porten a un objecte o lloc ocult.',
    'Fiction',
    'Hard', -- Nota: En l'original posava Easy, ho he deixat Hard com en l'exemple anterior
    'Seguiment',
    6.50,
    8
),
(
    'La Porta Dimensional',
    'Una estranya anomalia visual distorsiona l\'aire, amb forma de portal inestable.',
    'El jugador ha de realitzar una acció simple (ex. col·locar un objecte a prop) per "estabilitzar" o "activar" el portal i revelar alguna cosa darrere.',
    'Fiction',
    'Hard', -- Nota: En l'original posava Easy, ho he deixat Hard com en l'exemple anterior
    'Portals',
    10.00,
    20
);

-- Tercer bloc (Fantasy), que ja estava en català
INSERT INTO clues (
    clue_title,
    clue_description_user,
    clue_description_admin,
    clue_theme,
    clue_level,
    clue_game_phase,
    clue_price,
    clue_value
) VALUES
(
    'El Cristall Murmurant',
    'Un cristall que emet una feble llum i sembla xiuxiuejar paraules oblidades.',
    'El jugador ha d\'orientar el cristall cap a un símbol correcte perquè deixi de xiuxiuejar i mostri una runa.',
    'Fantasy',
    'Easy',
    'Màgia',
    7.50,
    10
),
(
    'Les Runes Antigues',
    'Trobes una tauleta de pedra coberta de símbols estranys.',
    'El jugador ha de fer coincidir 3-4 runes amb una clau de traducció simple proporcionada per trobar una paraula.',
    'Fantasy',
    'Hard',
    'Llenguatge',
    8.00,
    12
),
(
    'El Follet Curios',
    'Un petit follet s\'amaga darrere d\'una planta, mirant-te amb ulls brillants.',
    'El jugador ha d\'oferir un objecte simple (que estigui a la vista) al follet per obtenir a canvi una pista o clau.',
    'Fantasy',
    'Easy',
    'Criatures',
    6.00,
    8
),
(
    'L\'Endevinalla del Guardià',
    'Una estàtua amb un aire savi et presenta una endevinalla senzilla.',
    'El jugador ha de resoldre una endevinalla bàsica (pista textual o sonora) per desbloquejar alguna cosa.',
    'Fantasy',
    'Easy',
    'Enigmes',
    9.50,
    15
),
(
    'La Font dels Elements',
    'Una petita font on l\'aigua sembla canviar de color.',
    'El jugador ha d\'interactuar amb la font en un ordre simple (basat en colors o símbols) per activar-la.',
    'Fantasy',
    'Hard',
    'Elements',
    8.50,
    11
);
