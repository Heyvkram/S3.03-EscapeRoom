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

