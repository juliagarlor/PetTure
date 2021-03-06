-- User database. Tables: user, user_buddies and request_to_user

DROP SCHEMA IF EXISTS `user`;
CREATE SCHEMA `user`;
USE `user`;

CREATE TABLE `user`(
user_name VARCHAR(255) NOT NULL,
`password` VARCHAR(255),
profile_picture VARCHAR(255),
visibility VARCHAR(255),
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