-- MySQL Script generated by MySQL Workbench
-- 01/08/16 13:27:11
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema LePrAtos_DB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema LePrAtos_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `LePrAtos_DB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `LePrAtos_DB` ;

-- -----------------------------------------------------
-- Table `LePrAtos_DB`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LePrAtos_DB`.`User` (
  `ID_User` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `username` VARCHAR(32) NOT NULL COMMENT '',
  `password` VARCHAR(128) NOT NULL COMMENT '',
  PRIMARY KEY (`ID_User`)  COMMENT '',
  UNIQUE INDEX `ID_USER_UNIQUE` (`ID_User` ASC)  COMMENT '');


-- -----------------------------------------------------
-- Table `LePrAtos_DB`.`Games`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LePrAtos_DB`.`Games` (
  `ID_Games` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Start` DATETIME NOT NULL COMMENT '',
  `End` DATETIME NULL COMMENT '',
  PRIMARY KEY (`ID_Games`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LePrAtos_DB`.`Stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LePrAtos_DB`.`Stats` (
  `ID_Stats` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `AmmountWhool` INT NULL COMMENT '',
  `AmmountIron` INT NULL COMMENT '',
  `AmmountWood` INT NULL COMMENT '',
  `AmmountGrain` INT NULL COMMENT '',
  `AmmountHouses` INT NULL COMMENT '',
  `AmmountTowns` INT NULL COMMENT '',
  `AmmountStreets` INT NULL COMMENT '',
  PRIMARY KEY (`ID_Stats`)  COMMENT '',
  UNIQUE INDEX `ID_Stats_UNIQUE` (`ID_Stats` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LePrAtos_DB`.`Participants`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LePrAtos_DB`.`Participants` (
  `ID_Participant` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `FK_User` INT NOT NULL COMMENT '',
  `FK_Games` INT NOT NULL COMMENT '',
  `FK_Stats` INT NOT NULL COMMENT '',
  PRIMARY KEY (`ID_Participant`)  COMMENT '',
  UNIQUE INDEX `ID_Participant_UNIQUE` (`ID_Participant` ASC)  COMMENT '',
  INDEX `fk_Participants_User_idx` (`FK_User` ASC)  COMMENT '',
  INDEX `fk_Participants_Games1_idx` (`FK_Games` ASC)  COMMENT '',
  INDEX `fk_Participants_Stats1_idx` (`FK_Stats` ASC)  COMMENT '',
  CONSTRAINT `fk_Participants_User`
    FOREIGN KEY (`FK_User`)
    REFERENCES `LePrAtos_DB`.`User` (`ID_User`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Participants_Games1`
    FOREIGN KEY (`FK_Games`)
    REFERENCES `LePrAtos_DB`.`Games` (`ID_Games`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Participants_Stats1`
    FOREIGN KEY (`FK_Stats`)
    REFERENCES `LePrAtos_DB`.`Stats` (`ID_Stats`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LePrAtos_DB`.`Cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LePrAtos_DB`.`Cards` (
  `ID_Card` VARCHAR(5) NOT NULL COMMENT '',
  `Ammount` INT NOT NULL COMMENT '',
  PRIMARY KEY (`ID_Card`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LePrAtos_DB`.`Buildigs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LePrAtos_DB`.`Buildigs` (
  `ID_Card` VARCHAR(5) NOT NULL COMMENT '',
  `Ammount` INT NOT NULL COMMENT '',
  PRIMARY KEY (`ID_Card`)  COMMENT '')
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
