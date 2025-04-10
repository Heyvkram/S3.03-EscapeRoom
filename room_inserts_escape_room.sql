SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

DROP DATABASE IF EXISTS escape_room;
CREATE DATABASE escape_room;
USE escape_room;

CREATE TABLE `clues` (
  `clue_id` int(11) NOT NULL,
  `clue_title` varchar(100) NOT NULL,
  `clue_description_user` varchar(300) NOT NULL,
  `clue_description_admin` varchar(300) NOT NULL,
  `clue_theme` enum('Terror','Fiction','Fantasy') DEFAULT NULL,
  `clue_level` enum('Easy','Intermediate','Hard') DEFAULT NULL,
  `clue_game_phase` varchar(100) DEFAULT NULL,
  `clue_date_reg` timestamp NOT NULL DEFAULT current_timestamp(),
  `clue_date_modify` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  `clue_price` int(11) DEFAULT NULL,
  `clue_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `decoration_items` (
  `decoration_item_id` int(11) NOT NULL,
  `decoration_item_name` varchar(20) NOT NULL,
  `decoration_item_description` varchar(200) NOT NULL,
  `decoration_item_theme` enum('Terror','Fiction','Fantasy') DEFAULT NULL,
  `decoration_item_price` double DEFAULT NULL,
  `decoration_item_clue_valor` int(11) DEFAULT NULL,
  `decoration_item_img` varchar(100) DEFAULT NULL,
  `decoration_item_creation_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `decoration_item_modification_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `clue_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `game_sessions` (
  `game_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `payment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `accepted` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `generic_notifications_register` (
  `notification_register_id` int(11) NOT NULL,
  `notification_id` int(11) NOT NULL,
  `notification_register_level` enum('Informative','Important','Critical') DEFAULT NULL,
  `notification_register_status` enum('Sended','Pending','Canceled') DEFAULT NULL,
  `notification_register_date_reg` timestamp NOT NULL DEFAULT current_timestamp(),
  `notification_register_date_modify` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `notifications` (
  `notification_id` int(11) NOT NULL,
  `notification_title` varchar(50) NOT NULL,
  `notification_short_description` varchar(100) NOT NULL,
  `notification_message` varchar(500) NOT NULL,
  `notification_shipping_type` varchar(20) DEFAULT NULL,
  `notification_type` varchar(20) DEFAULT NULL,
  `notification_level` varchar(20) DEFAULT NULL,
  `notification_date_reg` timestamp NOT NULL DEFAULT current_timestamp(),
  `notification_date_modify` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `notifications_game` (
  `notification_register_id` int(11) NOT NULL,
  `notification_id` int(11) NOT NULL,
  `game_id` int(11) DEFAULT NULL,
  `notification_register_status` enum('Sended','Pending','Canceled') DEFAULT NULL,
  `notification_register_date_reg` timestamp NOT NULL DEFAULT current_timestamp(),
  `notification_register_date_modify` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `notifications_user` (
  `notification_user_id` int(11) NOT NULL,
  `notification_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `notification_register_status` enum('Sended','Pending','Canceled') DEFAULT NULL,
  `notification_register_date_reg` timestamp NOT NULL DEFAULT current_timestamp(),
  `notification_register_date_modify` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL,
  `payment_mode` enum('Credit card','Bizum','PayPal') DEFAULT NULL,
  `payment_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `payment_price` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `relation_clue_room` (
  `relation_clue_room_id` int(11) NOT NULL,
  `clue_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `relation_decoration_item_room` (
  `relation_decoration_item_room_id` int(11) NOT NULL,
  `decoration_item_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `relation_user_game` (
  `relation_user_game_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `rooms` (
  `room_id` int(11) NOT NULL,
  `room_name` varchar(100) NOT NULL,
  `room_theme` enum('Terror','Fiction','Fantasy') DEFAULT NULL,
  `room_level` enum('Easy','Intermediate','Hard') DEFAULT NULL,
  `room_status` enum('Available','Not available') DEFAULT NULL,
  `room_max_players` int(11) NOT NULL,
  `room_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT INTO `rooms` (`room_id`, `room_name`, `room_theme`, `room_level`, `room_status`, `room_max_players`, `room_date`) VALUES
(1, 'The Crypt of Horror', 'Terror', 'Hard', 'Available', 6, '2025-04-06 11:42:21'),
(2, 'Starship Pegasus', 'Fiction', 'Intermediate', 'Not available', 5, '2025-04-06 11:42:21'),
(3, 'Enchanted Forest', 'Fantasy', 'Easy', 'Available', 4, '2025-04-06 11:42:21'),
(4, 'The Cursed Laboratory', 'Terror', 'Intermediate', 'Available', 6, '2025-04-06 11:42:21'),
(5, 'Shattered World', 'Fiction', 'Hard', 'Available', 8, '2025-04-06 11:42:21');



CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_nick_name` varchar(20) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `user_surname` varchar(100) NOT NULL,
  `user_mail` varchar(50) DEFAULT NULL,
  `user_idCard` varchar(100) DEFAULT NULL,
  `user_address_street` varchar(100) DEFAULT NULL,
  `user_address_number` smallint(5) UNSIGNED DEFAULT NULL,
  `user_address_floor` varchar(10) DEFAULT NULL,
  `user_address_door` varchar(10) DEFAULT NULL,
  `user_city` varchar(30) DEFAULT NULL,
  `user_zip_code` varchar(20) DEFAULT NULL,
  `user_country` varchar(150) DEFAULT NULL,
  `user_phone` varchar(50) DEFAULT NULL,
  `user_notifiable` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `user_pssw` (
  `user_pssw_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_pssw_code` varchar(256) NOT NULL,
  `user_pssw_date_reg` timestamp NOT NULL DEFAULT current_timestamp(),
  `user_pssw_date_modify` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



ALTER TABLE `clues`
  ADD PRIMARY KEY (`clue_id`);

ALTER TABLE `decoration_items`
  ADD PRIMARY KEY (`decoration_item_id`),
  ADD UNIQUE KEY `clue_id` (`clue_id`);

ALTER TABLE `game_sessions`
  ADD PRIMARY KEY (`game_id`),
  ADD KEY `fk_game_user` (`user_id`),
  ADD KEY `fk_game_room` (`room_id`),
  ADD KEY `fk_game_payment` (`payment_id`);

ALTER TABLE `generic_notifications_register`
  ADD PRIMARY KEY (`notification_register_id`),
  ADD KEY `fk_generic_notifications_register` (`notification_id`);

ALTER TABLE `notifications`
  ADD PRIMARY KEY (`notification_id`);

ALTER TABLE `notifications_game`
  ADD PRIMARY KEY (`notification_register_id`),
  ADD KEY `fk_notifications_game` (`game_id`),
  ADD KEY `fk_notifications_game_notif` (`notification_id`);

ALTER TABLE `notifications_user`
  ADD PRIMARY KEY (`notification_user_id`),
  ADD KEY `fk_notifications_user` (`user_id`),
  ADD KEY `fk_notifications_user_notif` (`notification_id`);

ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `fk_payment_user` (`user_id`);

ALTER TABLE `relation_clue_room`
  ADD PRIMARY KEY (`relation_clue_room_id`),
  ADD KEY `fk_clue_room` (`clue_id`),
  ADD KEY `fk_roomclue_id` (`room_id`);

ALTER TABLE `relation_decoration_item_room`
  ADD PRIMARY KEY (`relation_decoration_item_room_id`),
  ADD KEY `fk_deco_id` (`decoration_item_id`),
  ADD KEY `fk_room_deco_id` (`room_id`);

ALTER TABLE `relation_user_game`
  ADD PRIMARY KEY (`relation_user_game_id`),
  ADD KEY `fk_usergame` (`user_id`),
  ADD KEY `fk_game_id` (`game_id`);

ALTER TABLE `rooms`
  ADD PRIMARY KEY (`room_id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_nick_name` (`user_nick_name`),
  ADD UNIQUE KEY `user_mail` (`user_mail`),
  ADD KEY `user_mail_2` (`user_mail`),
  ADD KEY `user_nick_name_2` (`user_nick_name`),
  ADD KEY `user_surname` (`user_surname`),
  ADD KEY `user_idCard` (`user_idCard`);

ALTER TABLE `user_pssw`
  ADD PRIMARY KEY (`user_pssw_id`),
  ADD KEY `fk_user_pssw` (`user_id`);



ALTER TABLE `clues`
  MODIFY `clue_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `decoration_items`
  MODIFY `decoration_item_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `game_sessions`
  MODIFY `game_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `generic_notifications_register`
  MODIFY `notification_register_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `notifications`
  MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `notifications_game`
  MODIFY `notification_register_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `notifications_user`
  MODIFY `notification_user_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `payments`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `relation_clue_room`
  MODIFY `relation_clue_room_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `relation_decoration_item_room`
  MODIFY `relation_decoration_item_room_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `relation_user_game`
  MODIFY `relation_user_game_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `rooms`
  MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `user_pssw`
  MODIFY `user_pssw_id` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `decoration_items`
  ADD CONSTRAINT `fk_clue_decoration` FOREIGN KEY (`clue_id`) REFERENCES `clues` (`clue_id`);

ALTER TABLE `game_sessions`
  ADD CONSTRAINT `fk_game_payment` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`payment_id`),
  ADD CONSTRAINT `fk_game_room` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`);

ALTER TABLE `generic_notifications_register`
  ADD CONSTRAINT `fk_generic_notifications_register` FOREIGN KEY (`notification_id`) REFERENCES `notifications` (`notification_id`);

ALTER TABLE `notifications_game`
  ADD CONSTRAINT `fk_notifications_game` FOREIGN KEY (`game_id`) REFERENCES `game_sessions` (`game_id`),
  ADD CONSTRAINT `fk_notifications_game_notif` FOREIGN KEY (`notification_id`) REFERENCES `notifications` (`notification_id`);

ALTER TABLE `notifications_user`
  ADD CONSTRAINT `fk_notifications_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `fk_notifications_user_notif` FOREIGN KEY (`notification_id`) REFERENCES `notifications` (`notification_id`);

ALTER TABLE `payments`
  ADD CONSTRAINT `fk_payment_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `relation_clue_room`
  ADD CONSTRAINT `fk_clue_room` FOREIGN KEY (`clue_id`) REFERENCES `clues` (`clue_id`),
  ADD CONSTRAINT `fk_roomclue_id` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`);

ALTER TABLE `relation_decoration_item_room`
  ADD CONSTRAINT `fk_deco_id` FOREIGN KEY (`decoration_item_id`) REFERENCES `decoration_items` (`decoration_item_id`),
  ADD CONSTRAINT `fk_room_deco_id` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`);

ALTER TABLE `relation_user_game`
  ADD CONSTRAINT `fk_game_id` FOREIGN KEY (`game_id`) REFERENCES `game_sessions` (`game_id`),
  ADD CONSTRAINT `fk_usergame` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `user_pssw`
  ADD CONSTRAINT `fk_user_pssw` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;
