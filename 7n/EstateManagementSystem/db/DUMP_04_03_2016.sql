-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema estates
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `estates` ;

-- -----------------------------------------------------
-- Schema estates
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `estates` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `estates` ;

-- -----------------------------------------------------
-- Table `estates`.`facility`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`facility` ;

CREATE TABLE IF NOT EXISTS `estates`.`facility` (
  `idfacility` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(1000) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `type` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `address_idaddress` INT(11) NOT NULL,
  `area` INT NULL,
  PRIMARY KEY (`idfacility`),
  INDEX `fk_facility_address1_idx` (`address_idaddress` ASC),
  CONSTRAINT `fk_facility_address1`
    FOREIGN KEY (`address_idaddress`)
    REFERENCES `estates`.`address` (`idaddress`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `estates`.`place`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`place` ;

CREATE TABLE IF NOT EXISTS `estates`.`place` (
  `idplace` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(1000) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `facility_idfacility` INT(11) NOT NULL,
  `address_idaddress` INT(11) NOT NULL,
  `area` INT NULL,
  `residentCount` INT NULL,
  PRIMARY KEY (`idplace`),
  INDEX `fk_place_facility1_idx` (`facility_idfacility` ASC),
  INDEX `fk_place_address1_idx` (`address_idaddress` ASC),
  CONSTRAINT `fk_place_address1`
    FOREIGN KEY (`address_idaddress`)
    REFERENCES `estates`.`address` (`idaddress`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_place_facility1`
    FOREIGN KEY (`facility_idfacility`)
    REFERENCES `estates`.`facility` (`idfacility`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


-- -----------------------------------------------------
-- Table `estates`.`placeuser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`placeuser` ;

CREATE TABLE IF NOT EXISTS `estates`.`placeuser` (
  `idplaceuser` INT(11) NOT NULL AUTO_INCREMENT,
  `usageType` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `startdate` DATE NULL DEFAULT NULL,
  `enddate` DATE NULL DEFAULT NULL,
  `place_idplace` INT(11) NULL DEFAULT NULL,
  `place_address_idaddress` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idplaceuser`),
  INDEX `fk_placeuser_place1_idx` (`place_idplace` ASC, `place_address_idaddress` ASC),
  CONSTRAINT `fk_placeuser_place1`
    FOREIGN KEY (`place_idplace`)
    REFERENCES `estates`.`place` (`idplace`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `estates`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`user` ;

CREATE TABLE IF NOT EXISTS `estates`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `surname` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `mail` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `login` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `password` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `phoneNo` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `icon` VARCHAR(300) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `regdate` DATE NOT NULL,
  `lastLogDate` DATE NULL DEFAULT NULL,
  `placeuser_idplaceuser` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  INDEX `fk_user_placeuser1_idx` (`placeuser_idplaceuser` ASC),
  CONSTRAINT `fk_user_placeuser1`
    FOREIGN KEY (`placeuser_idplaceuser`)
    REFERENCES `estates`.`placeuser` (`idplaceuser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `estates`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`address` ;

CREATE TABLE IF NOT EXISTS `estates`.`address` (
  `idaddress` INT(11) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT 'Polska',
  `countryCode` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT '0048',
  `cityName` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `cityCode` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `streetName` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `districtName` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `buildingNo` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `officeNo` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `user_iduser` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idaddress`),
  INDEX `fk_address_user1_idx` (`user_iduser` ASC),
  CONSTRAINT `fk_address_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `estates`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `estates`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`role` ;

CREATE TABLE IF NOT EXISTS `estates`.`role` (
  `idrole` INT(11) NOT NULL,
  `roleCode` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `roleName` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `roleDescription` VARCHAR(300) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`idrole`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `estates`.`attribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`attribute` ;

CREATE TABLE IF NOT EXISTS `estates`.`attribute` (
  `idattribute` INT(11) NOT NULL AUTO_INCREMENT,
  `attributeName` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `role_idrole` INT(11) NULL,
  PRIMARY KEY (`idattribute`),
  INDEX `fk_attribute_role1_idx` (`role_idrole` ASC),
  CONSTRAINT `fk_attribute_role1`
    FOREIGN KEY (`role_idrole`)
    REFERENCES `estates`.`role` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `estates`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`category` ;

CREATE TABLE IF NOT EXISTS `estates`.`category` (
  `idcategory` INT(11) NOT NULL AUTO_INCREMENT,
  `categoryName` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL,
  `categoryDescriptionShort` VARCHAR(500) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `categoryDescriptionLong` VARCHAR(1500) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `categoryIcon` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `categoryType` VARCHAR(45) NULL,
  PRIMARY KEY (`idcategory`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `estates`.`classifiedad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`classifiedad` ;

CREATE TABLE IF NOT EXISTS `estates`.`classifiedad` (
  `idclassifiedAd` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(300) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL,
  `shortDescription` VARCHAR(500) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `longDescription` VARCHAR(1500) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `photolink` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `category_idcategory` INT(11) NOT NULL,
  `user_iduser` INT(11) NOT NULL,
  PRIMARY KEY (`idclassifiedAd`),
  INDEX `fk_classifiedAd_category1_idx` (`category_idcategory` ASC),
  INDEX `fk_classifiedAd_user1_idx` (`user_iduser` ASC),
  CONSTRAINT `fk_classifiedAd_category1`
    FOREIGN KEY (`category_idcategory`)
    REFERENCES `estates`.`category` (`idcategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_classifiedAd_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `estates`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `estates`.`media`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`media` ;

CREATE TABLE IF NOT EXISTS `estates`.`media` (
  `idmedia` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `medianame` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `measureunit` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `dateregistered` DATE NULL DEFAULT NULL,
  `facility_idfacility` INT(11) NOT NULL,
  PRIMARY KEY (`idmedia`),
  INDEX `fk_media_facility1_idx` (`facility_idfacility` ASC),
  CONSTRAINT `fk_media_facility1`
    FOREIGN KEY (`facility_idfacility`)
    REFERENCES `estates`.`facility` (`idfacility`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `estates`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`payment` ;

CREATE TABLE IF NOT EXISTS `estates`.`payment` (
  `idpayment` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `duedate` DATE NULL DEFAULT NULL,
  `dateregistered` DATE NULL DEFAULT NULL,
  `description` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `amount` DOUBLE NULL DEFAULT NULL,
  `account` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_polish_ci' NULL DEFAULT NULL,
  `place_idplace` INT(11) NOT NULL,
  `place_address_idaddress` INT(11) NOT NULL,
  PRIMARY KEY (`idpayment`),
  INDEX `fk_payment_place1_idx` (`place_idplace` ASC, `place_address_idaddress` ASC),
  CONSTRAINT `fk_payment_place1`
    FOREIGN KEY (`place_idplace`)
    REFERENCES `estates`.`place` (`idplace`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `estates`.`media_counter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`media_counter` ;

CREATE TABLE IF NOT EXISTS `estates`.`media_counter` (
  `idmedia_counter` INT NOT NULL AUTO_INCREMENT,
  `mediaType` VARCHAR(100) NOT NULL,
  `counterType` VARCHAR(100) NULL,
  `registryNumber` VARCHAR(100) NULL,
  `dateRegistered` DATETIME NULL,
  `dateUpdated` DATETIME NULL,
  `totalAmount` DOUBLE NULL,
  `media_idmedia` INT(11) NOT NULL,
  `place_idplace` INT(11) NULL,
  PRIMARY KEY (`idmedia_counter`),
  INDEX `fk_media_counter_media1_idx` (`media_idmedia` ASC),
  INDEX `fk_media_counter_place1_idx` (`place_idplace` ASC),
  CONSTRAINT `fk_media_counter_media1`
    FOREIGN KEY (`media_idmedia`)
    REFERENCES `estates`.`media` (`idmedia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_media_counter_place1`
    FOREIGN KEY (`place_idplace`)
    REFERENCES `estates`.`place` (`idplace`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estates`.`report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`report` ;

CREATE TABLE IF NOT EXISTS `estates`.`report` (
  `idreport` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATE NULL DEFAULT NULL,
  `type` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `amount` DOUBLE NULL DEFAULT NULL,
  `comment` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `placeuser_idplaceuser` INT(11) NOT NULL,
  `media_counter_idmedia_counter` INT NOT NULL,
  PRIMARY KEY (`idreport`),
  INDEX `fk_report_placeuser1_idx` (`placeuser_idplaceuser` ASC),
  INDEX `fk_report_media_counter1_idx` (`media_counter_idmedia_counter` ASC),
  CONSTRAINT `fk_report_placeuser1`
    FOREIGN KEY (`placeuser_idplaceuser`)
    REFERENCES `estates`.`placeuser` (`idplaceuser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_report_media_counter1`
    FOREIGN KEY (`media_counter_idmedia_counter`)
    REFERENCES `estates`.`media_counter` (`idmedia_counter`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `estates`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`user_role` ;

CREATE TABLE IF NOT EXISTS `estates`.`user_role` (
  `iduser_role` INT(11) NOT NULL AUTO_INCREMENT,
  `role_idrole` INT(11) NULL DEFAULT NULL,
  `user_iduser` INT(11) NULL DEFAULT NULL,
  `placeowner_idplaceowner` INT(11) NULL,
  `placetenant_idplacetenant` INT(11) NULL,
  PRIMARY KEY (`iduser_role`),
  INDEX `fk_user_role_role1_idx` (`role_idrole` ASC),
  INDEX `fk_user_role_user1_idx` (`user_iduser` ASC),
  CONSTRAINT `fk_user_role_role1`
    FOREIGN KEY (`role_idrole`)
    REFERENCES `estates`.`role` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `estates`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `estates`.`expense`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`expense` ;

CREATE TABLE IF NOT EXISTS `estates`.`expense` (
  `idexpense` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `registered` DATETIME NULL,
  `amount` DOUBLE NULL,
  `description` VARCHAR(200) NULL,
  `occurence` VARCHAR(45) NULL,
  `calculationPeriod` INT NULL,
  `payment_idpayment` INT(11) NULL,
  `facility_idfacility` INT(11) NOT NULL,
  `media_counter_idmedia_counter` INT NULL,
  PRIMARY KEY (`idexpense`),
  INDEX `fk_expense_payment1_idx` (`payment_idpayment` ASC),
  INDEX `fk_expense_facility1_idx` (`facility_idfacility` ASC),
  INDEX `fk_expense_media_counter1_idx` (`media_counter_idmedia_counter` ASC),
  CONSTRAINT `fk_expense_payment1`
    FOREIGN KEY (`payment_idpayment`)
    REFERENCES `estates`.`payment` (`idpayment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_expense_facility1`
    FOREIGN KEY (`facility_idfacility`)
    REFERENCES `estates`.`facility` (`idfacility`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_expense_media_counter1`
    FOREIGN KEY (`media_counter_idmedia_counter`)
    REFERENCES `estates`.`media_counter` (`idmedia_counter`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estates`.`category_attribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estates`.`category_attribute` ;

CREATE TABLE IF NOT EXISTS `estates`.`category_attribute` (
  `idcategory_attribute` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NULL,
  `attribute_idattribute` INT(11) NOT NULL,
  `category_idcategory` INT(11) NOT NULL,
  PRIMARY KEY (`idcategory_attribute`),
  INDEX `fk_category_attribute_attribute1_idx` (`attribute_idattribute` ASC),
  INDEX `fk_category_attribute_category1_idx` (`category_idcategory` ASC),
  CONSTRAINT `fk_category_attribute_attribute1`
    FOREIGN KEY (`attribute_idattribute`)
    REFERENCES `estates`.`attribute` (`idattribute`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_attribute_category1`
    FOREIGN KEY (`category_idcategory`)
    REFERENCES `estates`.`category` (`idcategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

use estates;
INSERT INTO `user` VALUES (1,'Paweł','Obrębski','obrebski1984@gmail.com','admin','admin','662083176',NULL,'2015-10-25',NULL,NULL),(2,'admin','admin','admin@admin.admin','admin','admin','662083176',NULL,'2015-10-25',NULL,NULL);
INSERT INTO `role` VALUES (1,'ADMIN','ROLE_ADMIN','ADMINISTRATOR:VIEW,EDIT,DELETE,INSERT'),(2,'USER','ROLE_USER','USER:VIEW,EDIT,INSERT');
INSERT INTO `role` VALUES (3,'OWNER','ROLE_OWNER','OWNER:PLACE OWNER'),(4,'RENTER','ROLE_RENTER','RENTER:PLACE RENTER');
INSERT INTO `user_role` VALUES (1,1,1,0,0),(2,1,2,0,0);
