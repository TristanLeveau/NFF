CREATE TABLE `livraison` (
  `idlivraison` int(11) NOT NULL AUTO_INCREMENT,
  `contenu` mediumtext DEFAULT NULL,
  `semestre` varchar(40) DEFAULT NULL,
  `date` char(10) DEFAULT NULL,
  `supprime` bit(1) NULL DEFAULT 0,
  PRIMARY KEY (`idlivraison`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
   `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `motDePasse` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `commande` (
  `idusercommande` int(11) NOT NULL,
  `idlivraisoncommande` int(11) NOT NULL,
  CONSTRAINT `idcommande` PRIMARY KEY (`idusercommande`,`idlivraisoncommande`),
  CONSTRAINT `iduser` FOREIGN KEY,
  CONSTRAINT `idlivraison`
  CONSTRAINT fk_user_commande FOREIGN KEY (idusercommande) REFERENCES user(iduser),
  CONSTRAINT fk_livraison_commande FOREIGN KEY (idlivraisoncommande) REFERENCES livraison(idlivraison))ENGINE=InnoDB DEFAULT CHARSET=utf8;





