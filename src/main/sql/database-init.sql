CREATE TABLE livraison (
  idlivraison int(11) NOT NULL AUTO_INCREMENT,
  contenu mediumtext DEFAULT NULL,
  semestre varchar(40) DEFAULT NULL,
  date char(10) DEFAULT NULL,
  supprime bit(1) NULL DEFAULT 0,
  PRIMARY KEY (idlivraison)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE participant (
   idparticipant int(11) NOT NULL AUTO_INCREMENT,
  nom varchar(45) DEFAULT NULL,
  prenom varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  motDePasse varchar(45) DEFAULT NULL,
   livraison INT NULL,
  PRIMARY KEY (idparticipant),
  INDEX fk_participant_livraison_idx (livraison ASC),
  CONSTRAINT fk_participant_livraison FOREIGN KEY (livraison) REFERENCES livraison(idlivraison))ENGINE=InnoDB DEFAULT CHARSET=utf8;



