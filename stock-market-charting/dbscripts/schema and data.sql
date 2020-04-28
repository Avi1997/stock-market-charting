-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema stock_market_charting
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `stock_market_charting` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `stock_market_charting` ;

-- -----------------------------------------------------
-- Schema stock_market_charting
-- -----------------------------------------------------
USE `stock_market_charting` ;

-- -----------------------------------------------------
-- Table `stock_market_charting`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stock_market_charting`.`role` (
  `ro_id` INT(11) NOT NULL AUTO_INCREMENT,
  `ro_name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`ro_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `stock_market_charting`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stock_market_charting`.`user` (
  `us_id` INT(11) NOT NULL AUTO_INCREMENT,
  `us_username` VARCHAR(60) NOT NULL,
  `us_password` VARCHAR(60) NOT NULL,
  `us_contact_number` VARCHAR(10) NOT NULL,
  `us_email` VARCHAR(50) NOT NULL,
  `us_confirm` boolean NOT NULL,
  PRIMARY KEY (`us_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `stock_market_charting`.`user_has_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stock_market_charting`.`user_has_role` (
  `ur_id` INT(11) NOT NULL AUTO_INCREMENT,
  `ur_us_id` INT(11) NOT NULL,
  `ur_ro_id` INT(11) NOT NULL,
  PRIMARY KEY (`ur_id`),
  INDEX `fk_user_has_role_role1_idx` (`ur_ro_id` ASC),
  INDEX `fk_user_has_role_user_idx` (`ur_us_id` ASC),
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`ur_ro_id`)
    REFERENCES `stock_market_charting`.`role` (`ro_id`),
  CONSTRAINT `fk_user_has_role_user`
    FOREIGN KEY (`ur_us_id`)
    REFERENCES `stock_market_charting`.`user` (`us_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4;



-- --------------------------------------------
-- -                Confirm email      --------
--  --------------------------------------------



CREATE TABLE IF NOT EXISTS `stock_market_charting`.`confirm_email` (
  `ce_id` INT(11) NOT NULL AUTO_INCREMENT,
  `ce_token` VARCHAR(450) NOT NULL,
  `ce_us_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ce_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4;





-- -----------------------------------------------------
-- Table `stock_market_charting`.`stock_price`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `stock_market_charting`.`stock_price` (
  `sp_id` INT NOT NULL AUTO_INCREMENT,
  `sp_code` BIGINT NOT NULL,
  `sp_stock_exchange` VARCHAR(30) NOT NULL,
  `sp_current_price` BIGINT NOT NULL,
  `sp_date` DATE NOT NULL,
  `sp_time` TIME(0) NOT NULL ,
   PRIMARY KEY (`sp_id`) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `stock_market_charting`.`ipo`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `stock_market_charting`.`ipo` (
  `ipo_id` INT NOT NULL AUTO_INCREMENT,
  `ipo_company_name` VARCHAR(30) NOT NULL,
  `ipo_stock_exchange` VARCHAR(30) NOT NULL,
  `ipo_share_price` BIGINT NOT NULL,
  `ipo_total_shares` INT NOT NULL,
  `ipo_date` DATETIME NOT NULL,
  `ipo_remarks` VARCHAR(400) NULL,
   PRIMARY KEY (`ipo_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `stock_market_charting`.`sector`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `stock_market_charting`.`sector` (
  `se_id` INT NOT NULL AUTO_INCREMENT,
  `se_sector_name` VARCHAR(30) NOT NULL,
  `se_brief` VARCHAR(400) NOT NULL,
  PRIMARY KEY (`se_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `stock_market_charting`.`company`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `stock_market_charting`.`stock_exchange` (
  `ex_id` INT NOT NULL AUTO_INCREMENT,
  `ex_stock_exchange` VARCHAR(30) NOT NULL,
  `ex_brief` VARCHAR(400) NOT NULL,
  `ex_address` VARCHAR(200) NOT NULL,
  `ex_remarks` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`ex_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `stock_market_charting`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stock_market_charting`.`company` (
  `cp_id` INT NOT NULL AUTO_INCREMENT,
  `cp_code` BIGINT NOT NULL,
  `cp_name` VARCHAR(30) NOT NULL,
  `cp_turnover` BIGINT NOT NULL,
  `cp_ceo` VARCHAR(30) NOT NULL,
  `cp_listed` BOOLEAN DEFAULT FALSE,
  `cp_se_id` INT NOT NULL,
  `cp_brief` VARCHAR(600) NOT NULL,
  PRIMARY KEY (`cp_id`),
  INDEX `cp_se_fk_idx` (`cp_se_id` ASC),
  CONSTRAINT `cp_se_fk`
    FOREIGN KEY (`cp_se_id`)
    REFERENCES `stock_market_charting`.`sector` (`se_id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `stock_market_charting`.`company_stock` (
	`cs_id` INT NOT NULL AUTO_INCREMENT,
	`cs_cp_id` INT NULL,
	`cs_ex_id` INT NULL,
	PRIMARY KEY (`cs_id`),
	INDEX `cs_cp_fk_idx` (`cs_cp_id` ASC),
	INDEX `cs_ex_fk_idx` (`cs_ex_id` ASC),
	CONSTRAINT `cs_cp_fk`
		FOREIGN KEY (`cs_cp_id`)
		REFERENCES `stock_market_charting`.`company` (`cp_id`)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	CONSTRAINT `cs_ex_fk`
		FOREIGN KEY (`cs_ex_id`)
		REFERENCES `stock_market_charting`.`stock_exchange` (`ex_id`)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION)
	ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `stock_market_charting`.`board_members`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stock_market_charting`.`board_members` (
  `bm_id` INT NOT NULL AUTO_INCREMENT,
  `bm_cp_name` VARCHAR(30) NOT NULL,
  `bm_cp_id` INT NOT NULL,
  PRIMARY KEY (`bm_id`),
  INDEX `bm_cp_fk_idx` (`bm_cp_id` ASC),
   CONSTRAINT `bm_cp_fk`
    FOREIGN KEY (`bm_cp_id`)
    REFERENCES `stock_market_charting`.`company` (`cp_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  )
  
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


insert into role (ro_id,ro_name)
values (1,"ADMIN"),(2,"USER");

select * from role;

select * from user_has_role;


select * from user;
select * from stock_price;


INSERT INTO `stock_market_charting`.`sector` (`se_id`, `se_sector_name`, `se_brief`) VALUES ('2', 'Software', 'IT');
INSERT INTO `stock_market_charting`.`sector` (`se_id`, `se_sector_name`, `se_brief`) VALUES ('1', 'Banking', 'banks sector');

INSERT INTO `stock_market_charting`.`company` (`cp_id`, `cp_code`, `cp_name`, `cp_turnover`, `cp_ceo`, `cp_listed`, `cp_se_id`, `cp_brief`) VALUES ('1', '500112', 'BOI', '54685', 'GURU', '1', '1', 'bank of India');
INSERT INTO `stock_market_charting`.`company` (`cp_id`, `cp_code`, `cp_name`, `cp_turnover`, `cp_ceo`, `cp_listed`, `cp_se_id`, `cp_brief`) VALUES ('2', '500113', 'SBI', '54688', 'Zain', '1', '1', 'statebank of India');
INSERT INTO `stock_market_charting`.`company` (`cp_id`, `cp_code`, `cp_name`, `cp_turnover`, `cp_ceo`, `cp_listed`, `cp_se_id`, `cp_brief`) VALUES ('3', '500114', 'Google', '546899', 'Rohith', '1', '2', 'Search engine company');
INSERT INTO `stock_market_charting`.`company` (`cp_id`, `cp_code`, `cp_name`, `cp_turnover`, `cp_ceo`, `cp_listed`, `cp_se_id`, `cp_brief`) VALUES ('4', '500115', 'Microsoft', '546898', 'Avinash', '0', '2', 'laptop company');
INSERT INTO `stock_market_charting`.`stock_exchange` (`ex_id`, `ex_stock_exchange`, `ex_brief`, `ex_address`, `ex_remarks`) VALUES ('1', 'BSE', 'british', 'kuruku theru', 'nil');


INSERT INTO `stock_market_charting`.`board_members`(`bm_cp_name`,`bm_cp_id`) values("john",1);
INSERT INTO `stock_market_charting`.`board_members`(`bm_cp_name`,`bm_cp_id`) values("sam",1);
INSERT INTO `stock_market_charting`.`board_members`(`bm_cp_name`,`bm_cp_id`) values("Elon",2);
INSERT INTO `stock_market_charting`.`board_members`(`bm_cp_name`,`bm_cp_id`) values("Pichai",2);
INSERT INTO `stock_market_charting`.`board_members`(`bm_cp_name`,`bm_cp_id`) values("Satya",3);
INSERT INTO `stock_market_charting`.`board_members`(`bm_cp_name`,`bm_cp_id`) values("Premji",3);
INSERT INTO `stock_market_charting`.`board_members`(`bm_cp_name`,`bm_cp_id`) values("Rick",4);
INSERT INTO `stock_market_charting`.`board_members`(`bm_cp_name`,`bm_cp_id`) values("Morty",4);


INSERT INTO `stock_market_charting`.`stock_exchange` (`ex_id`, `ex_stock_exchange`, `ex_brief`, `ex_address`, `ex_remarks`) VALUES ('2', 'CSE', 'british', 'kuruku theru', 'nil');
INSERT INTO `stock_market_charting`.`stock_exchange` (`ex_id`, `ex_stock_exchange`, `ex_brief`, `ex_address`, `ex_remarks`) VALUES ('3', 'NSE', 'british', 'kuruku theru', 'nil');
INSERT INTO `stock_market_charting`.`company_stock` (`cs_id`, `cs_cp_id`, `cs_ex_id`) VALUES ('1', '1', '1');
INSERT INTO `stock_market_charting`.`company_stock` (`cs_id`, `cs_cp_id`, `cs_ex_id`) VALUES ('2', '1', '2');
INSERT INTO `stock_market_charting`.`company_stock` (`cs_id`, `cs_cp_id`, `cs_ex_id`) VALUES ('3', '2', '1');
INSERT INTO `stock_market_charting`.`company_stock` (`cs_id`, `cs_cp_id`, `cs_ex_id`) VALUES ('4', '3', '3');
INSERT INTO `stock_market_charting`.`company_stock` (`cs_id`, `cs_cp_id`, `cs_ex_id`) VALUES ('5', '4', '2');
INSERT INTO `stock_market_charting`.`company_stock` (`cs_id`, `cs_cp_id`, `cs_ex_id`) VALUES ('6', '4', '1');
