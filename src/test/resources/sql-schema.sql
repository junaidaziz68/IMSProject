CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `item` VARCHAR(40) DEFAULT NULL,
    `price` DECIMAL(5,2) DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `customer_id` INT DEFAULT NULL,
    PRIMARY KEY (`id`),
	FOREIGN KEY (`customer_id`) REFERENCES `ims`.`customers`(`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `order_id` INT DEFAULT NULL,
    `item_id` INT DEFAULT NULL,
    PRIMARY KEY (`id`),
	FOREIGN KEY (`order_id`) REFERENCES `ims`.`orders`(`id`) ON DELETE CASCADE,
	FOREIGN KEY (`item_id`) REFERENCES `ims`.`items`(`id`) ON DELETE CASCADE
);

