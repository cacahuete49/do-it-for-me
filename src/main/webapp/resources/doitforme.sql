-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(50) NULL,
  `prenom` VARCHAR(50) NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `role` VARCHAR(50) DEFAULT  "user",
  PRIMARY KEY (`email`),
  KEY(`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `scenario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scenario` ;

CREATE TABLE IF NOT EXISTS `scenario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `xmlFlux` LONGTEXT NULL,
  `description` LONGTEXT NULL,
  `prix` FLOAT NULL DEFAULT 0,
  `note` TINYINT(4) NOT NULL DEFAULT -1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
delete from scenario where description is NULL;
insert into scenario (xmlFlux,description,prix) values ("<xml></xml>","connexion automatique à l'autoradio BT via NFC",1.99);
insert into scenario (xmlFlux,description,prix) values ("<xml></xml>","Passage en mode pseudo avion sans root user quand je me connecte au chargeur",0.99);


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message` ;

CREATE TABLE IF NOT EXISTS `message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `objet` VARCHAR(255) NOT NULL,
  `message` LONGTEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_scenario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_scenario` ;

CREATE TABLE IF NOT EXISTS `user_has_scenario` (
  `scenario_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `date_achat` DATETIME NOT NULL,
  `prix_achat` FLOAT NOT NULL,
  `user_note` TINYINT(4) NOT NULL DEFAULT -1,
  PRIMARY KEY (`scenario_id`, `user_id`),
  INDEX `fk_scenario_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_scenario_has_user_scenario_idx` (`scenario_id` ASC),
  CONSTRAINT `fk_scenario_has_user_scenario`
    FOREIGN KEY (`scenario_id`)
    REFERENCES `scenario` (`id`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_scenario_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB;