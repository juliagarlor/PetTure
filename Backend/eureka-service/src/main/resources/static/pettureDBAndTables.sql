-- User database. Tables: user, user_buddies and request_to_user

DROP SCHEMA IF EXISTS `user`;
CREATE SCHEMA `user`;
USE `user`;

CREATE TABLE `user`(
user_name VARCHAR(255) NOT NULL,
`password` VARCHAR(255),
profile_picture VARCHAR(255),
visibility VARCHAR(255),
`role` VARCHAR(255),
PRIMARY KEY(user_name)
);

CREATE TABLE user_buddies(
`current_user` VARCHAR(255),
buddy VARCHAR(255),
FOREIGN KEY(`current_user`) REFERENCES `user`(user_name),
FOREIGN KEY(buddy) REFERENCES `user`(user_name)
);

CREATE TABLE request_to_user(
requested_user VARCHAR(255),
requesting_user VARCHAR(255),
FOREIGN KEY(requested_user) REFERENCES `user`(user_name),
FOREIGN KEY(requesting_user) REFERENCES `user`(user_name)
);

INSERT INTO `user` (user_name, `password`, profile_picture, visibility, `role`) VALUES 
-- Contraseña perry: malditaSeaPerryElOrnitorrinco
('perryThePlatypus', '$2a$10$D4RylHFq71wpbaufSeL.2uULHmpz.Gtf6yO5kFnC7h8h6kv6zw5U6', 'assets/images/perry.jpg', 'PUBLIC', 'USER'),
-- contraseña garfield: lasaña
('garfieldLovesMondays', '$2a$10$cFlNgE2vRoTuDAc3/rjEI.uizoiKOY/3r2Xq6MoRinOZ5WWoYD5NS', 'assets/images/garfield.jpg', 'PUBLIC', 'USER');

INSERT INTO request_to_user(requested_user, requesting_user) VALUES
('perryThePlatypus', 'garfieldLovesMondays');

INSERT INTO user_buddies(`current_user`, buddy) VALUES
('perryThePlatypus', 'garfieldLovesMondays'), 
('garfieldLovesMondays','perryThePlatypus');

-- Picture database. Tables: picture

DROP SCHEMA IF EXISTS picture;
CREATE SCHEMA picture;
USE picture;

CREATE TABLE picture(
id BIGINT NOT NULL AUTO_INCREMENT,
picture_name VARCHAR(255),
user_name VARCHAR(255),
licks INT,
PRIMARY KEY(id)
);

INSERT INTO picture(picture_name, user_name, licks) VALUES
('assets/images/perry.jpg', 'perryThePlatypus', 10),
('assets/images/garfield.jpg', 'garfieldLovesMondays', 12);

-- Post database. Tables: post and commentary

DROP SCHEMA IF EXISTS post;
CREATE SCHEMA post;
USE post;

CREATE TABLE post(
id BIGINT NOT NULL AUTO_INCREMENT,
body VARCHAR(255),
picture_id BIGINT,
PRIMARY KEY(id)
);

CREATE TABLE commentary(
id BIGINT NOT NULL AUTO_INCREMENT,
user_name VARCHAR(255),
body VARCHAR(255),
post_id BIGINT,
PRIMARY KEY(id),
FOREIGN KEY(post_id) REFERENCES post(id)
);

INSERT INTO post(body, picture_id) VALUES
('Fighting evil in my Vespa', 1),
('It\'s Friday, guys!', 2);

INSERT INTO commentary(user_name, body, post_id) VALUES 
('garfieldLovesMondays', 'Who', 1),
('garfieldLovesMondays', 'the hell', 1),
('garfieldLovesMondays', 'is driving that thing?', 1),
('perryThePlatypus', 'Chubby cat', 2)
;