SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

DROP DATABASE IF EXISTS escape_room;
CREATE DATABASE escape_room;
USE escape_room;

CREATE TABLE `clues` (
  `clue_id` int(11) NOT NULL,
  `clue_name` varchar(100) NOT NULL,
  `clue_theme` enum('Terror','Fiction','Fantasy') NOT NULL,
  `clue_level` enum('Easy','Intermediate','Hard') NOT NULL,
  `clue_game_phase` varchar(100) NOT NULL,
  `clue_date_reg` timestamp NOT NULL DEFAULT current_timestamp(),
  `clue_date_modify` timestamp ON UPDATE current_timestamp(),
  `clue_price` int(11),
  `clue_value` int(11),
  `room_id` int(11)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `decoration_items` (
  `decoration_item_id` int(11) NOT NULL,
  `decoration_item_name` varchar(20) NOT NULL,
  `decoration_item_description` varchar(200) NOT NULL,
  `decoration_item_theme` enum('Terror','Fiction','Fantasy') NOT NULL,
  `decoration_item_price` double NOT NULL,
  `decoration_item_clue_valor` int(11) DEFAULT NULL,
  `decoration_item_img` varchar(100) DEFAULT NULL,
  `decoration_item_creation_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `decoration_item_modification_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `clue_id` int(11),
  `room_id` int(11)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `game_sessions` (
  `game_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `payment_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `notifications` (
  `notification_id` int(11) NOT NULL,
  `notification_type` enum('Generic','Personal','') DEFAULT NULL,
  `notification_message` varchar(200) NOT NULL,
  `notification_date_reg` timestamp NOT NULL DEFAULT current_timestamp(),
  `notification_date_modify` timestamp UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL,
  `payment_mode` enum('Credit card','Bizum','PayPal') DEFAULT NULL,
  `payment_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `payment_price` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `rooms` (
  `room_id` int(11) NOT NULL,
  `room_name` varchar(100) NOT NULL,
  `room_theme` enum('Terror','Fiction','Fantasy') NOT NULL,
  `room_level` enum('Easy','Intermediate','Hard') NOT NULL,
  `room_status` enum('Available','Not available') NOT NULL,
  `room_max_players` int(11) NOT NULL,
  `room_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_nick_name` varchar(20) UNIQUE NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `user_surname` varchar(100) NOT NULL,
  `user_mail` varchar(50) UNIQUE,
  `user_idCard` varchar(100),
  `user_address_street` varchar(100),
  `user_address_number` smallint UNSIGNED,
  `user_address_floor` varchar(10),
  `user_address_door` varchar(10),
  `user_city` varchar(30),
  `user_zip_code` varchar(20),
  `user_country` varchar(150),
  `user_phone` varchar(50),
  INDEX (user_mail),
  INDEX (user_nick_name),
  INDEX (user_surname),
  INDEX (user_idCard)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


ALTER TABLE `clues`
  ADD PRIMARY KEY (`clue_id`),
  ADD KEY `fk_clues_room` (`room_id`);

ALTER TABLE `decoration_items`
  ADD PRIMARY KEY (`decoration_item_id`),
  ADD UNIQUE KEY `clue_id` (`clue_id`),
  ADD KEY `fk_room_decoration` (`room_id`);

ALTER TABLE `game_sessions`
  ADD PRIMARY KEY (`game_id`),
  ADD KEY `fk_game_user` (`user_id`),
  ADD KEY `fk_game_room` (`room_id`),
  ADD KEY `fk_game_payment` (`payment_id`);

ALTER TABLE `notifications`
  ADD PRIMARY KEY (`notification_id`),
  ADD KEY `fk_notification_user` (`user_id`);

ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `fk_payment_user` (`user_id`);

ALTER TABLE `rooms`
  ADD PRIMARY KEY (`room_id`);

ALTER TABLE `rooms`
  MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `clues`
  ADD CONSTRAINT `fk_clues_room` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`);

ALTER TABLE `decoration_items`
  ADD CONSTRAINT `fk_room_decoration` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`),
  ADD CONSTRAINT `fk_clue_decoration` FOREIGN KEY (`clue_id`) REFERENCES `clues` (`clue_id`);

ALTER TABLE `game_sessions`
  ADD CONSTRAINT `fk_game_payment` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`payment_id`),
  ADD CONSTRAINT `fk_game_room` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`),
  ADD CONSTRAINT `fk_game_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `notifications`
  ADD CONSTRAINT `fk_notification_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `payments`
  ADD CONSTRAINT `fk_payment_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;
