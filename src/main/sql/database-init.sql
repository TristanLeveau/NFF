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
   `livraison` INT NULL,
  PRIMARY KEY (`id`),
  INDEX fk_participant_soiree_idx (livraison ASC),
  CONSTRAINT fk_participant_soiree FOREIGN KEY (livraison) REFERENCES livraison(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `participantALivrer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `motDePasse` varchar(45) DEFAULT NULL,
  `livraison` INT NULL,
  `livraisonUnique` INT NULL,
  `abonnement5A` BIT(1)  NULL DEFAULT 0 ,
  `abonnement5B` BIT(1)  NULL DEFAULT 0,
  `abonnement10` BIT(1)  NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX fk_participant_soiree_idx (soiree ASC),
  CONSTRAINT fk_participant_soiree FOREIGN KEY (livraison) REFERENCES soiree(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;

