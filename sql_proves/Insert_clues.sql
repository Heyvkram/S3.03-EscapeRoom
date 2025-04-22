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