-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`system_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`system_users` ;

CREATE TABLE IF NOT EXISTS `mydb`.`system_users` (
  `id_system_user` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_system_user`),
  UNIQUE INDEX `id_system_user_UNIQUE` (`id_system_user` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`system_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`system_roles` ;

CREATE TABLE IF NOT EXISTS `mydb`.`system_roles` (
  `id_system_roles` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(300) NULL,
  PRIMARY KEY (`id_system_roles`),
  UNIQUE INDEX `id_system_roles_UNIQUE` (`id_system_roles` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`system_user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`system_user_roles` ;

CREATE TABLE IF NOT EXISTS `mydb`.`system_user_roles` (
  `id_system_user_roles` INT NOT NULL AUTO_INCREMENT,
  `system_users_id_system_user` INT NOT NULL,
  `system_roles_id_system_roles` INT NOT NULL,
  PRIMARY KEY (`id_system_user_roles`),
  UNIQUE INDEX `id_system_user_roles_UNIQUE` (`id_system_user_roles` ASC),
  INDEX `fk_system_user_roles_system_users_idx` (`system_users_id_system_user` ASC),
  INDEX `fk_system_user_roles_system_roles1_idx` (`system_roles_id_system_roles` ASC),
  CONSTRAINT `fk_system_user_roles_system_users`
    FOREIGN KEY (`system_users_id_system_user`)
    REFERENCES `mydb`.`system_users` (`id_system_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_system_user_roles_system_roles1`
    FOREIGN KEY (`system_roles_id_system_roles`)
    REFERENCES `mydb`.`system_roles` (`id_system_roles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
