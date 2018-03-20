CREATE TABLE `livraison` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contenu` mediumtext DEFAULT NULL,
  `semestre` varchar(40) DEFAULT NULL,
  `date` char(10) DEFAULT NULL,
  `supprime` bit(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `participant` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `motDePasse` varchar(45) DEFAULT NULL,
   `idlivraison` INT NULL,
  PRIMARY KEY (`id`),
  INDEX fk_participant_livraison_idx (livraison ASC),
  CONSTRAINT fk_participant_livraison FOREIGN KEY (livraison) REFERENCES livraison(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*CREATE TABLE `participantALivrer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `motDePasse` varchar(45) DEFAULT NULL,
  `livraison` INT NULL,
  PRIMARY KEY (`id`),
<<<<<<< HEAD
  INDEX fk_participant_livraison_idx (livraison ASC),
  CONSTRAINT fk_participant_livraison FOREIGN KEY (livraison) REFERENCES livraison(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;
=======
  INDEX fk_participant_soiree_idx (soiree ASC),
  CONSTRAINT fk_participant_soiree FOREIGN KEY (livraison) REFERENCES soiree(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;*/
>>>>>>> 9f3ac0f9f6a248f2f0bc7165053105819ca21cba

