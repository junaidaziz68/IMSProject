DROP SCHEMA ims;
CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `item_id` INT NOT NULL AUTO_INCREMENT,
    `item` VARCHAR(40) DEFAULT NULL,
    `price` DECIMAL(5,2) DEFAULT NULL,
    `quantity` INT,
     PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`order_item` (
    `id` INT NOT NULL,
    `item_id` INT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`item_id`) REFERENCES `ims`.`items`(`item_id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `customer_id` INT,
    `order_item_id` INT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`customer_id`) REFERENCES  `ims`.`customers`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`order_item_id`) REFERENCES  `ims`.`order_item`(`id`) ON DELETE CASCADE
);

