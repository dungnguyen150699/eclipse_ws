CREATE DATABASE  IF NOT EXISTS `hibernate-demo`;

CREATE TABLE `hibernate-demo`.`customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
