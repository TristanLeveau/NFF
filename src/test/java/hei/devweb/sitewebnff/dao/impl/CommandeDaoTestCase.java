package hei.devweb.sitewebnff.dao.impl;

import daos.CommandeDao;
import daos.DataSourceProvider;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import pojos.Commande;
import pojos.CommandeParLivraison;
import pojos.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CommandeDaoTestCase extends AbstractDaoTestCase {

    CommandeDao commandeDao = new CommandeDao();

    @Override
    public void insertDataSet(Statement statement) throws Exception {

       statement.executeUpdate("DELETE FROM user");
       statement.executeUpdate("DELETE FROM livraison");
       statement.executeUpdate("DELETE FROM commande");


       statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(1, 'Jean', 'Jacques','jean@jacques.fr','jean123')");
       statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(2, 'John', 'Jill','john@jill.fr','john123')");
       statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(3, 'James', 'Jonas','james@jonas.fr','james123')");
       statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(4, 'Marc', 'McMarky','Marc@Marc.fr','marc123')");
       statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(5, 'Clémentine', 'LAbricot','clementine@clementine.fr','clementine123')");
       statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(6, 'Gertrude', 'Zuchinni','gertrude@gertrude.fr','gertrude123')");
       statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(7, 'Jacob', 'Pois-Raux','jacob@jacob.fr','jacob123')");
       statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(8, 'Tristan', 'Leveau','tristan.leveau@hei.yncrea.fr','tristan123')");
       statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(9, 'Lucie', 'Le Comte','lucie@lucie.fr','lucie123')");
       statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(10, 'Paul', 'Echaiz','paul@echaiz.fr','paul123')");

    /*  INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(1, 'Jean', 'Jacques','jean@jacques.fr','jean123');
        INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(2, 'John', 'Jill','john@jill.fr','john123');
        INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(3, 'James', 'Jonas','james@jonas.fr','james123');
        INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(4, 'Marc', 'McMarky','Marc@Marc.fr','marc123');
        INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(5, 'Clémentine', 'LAbricot','clementine@clementine.fr','clementine123');
        INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(6, 'Gertrude', 'Zuchinni','gertrude@gertrude.fr','gertrude123');
        INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(7, 'Jacob', 'Pois-Raux','jacob@jacob.fr','jacob123');
        INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(8, 'Tristan', 'Leveau','tristan.leveau@hei.yncrea.fr','tristan123');
        INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(9, 'Lucie', 'Le Comte','lucie@lucie.fr','lucie123');
        INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(10, 'Paul', 'Echaiz','paul@echaiz.fr','paul123');

        INSERT INTO livraison VALUES (1, 'Salade Tomate Oignon','S1', '29/09/18',0);
        INSERT INTO livraison VALUES (2, 'Pommes poires carrottes','S1', '11/11/18',0);
        INSERT INTO livraison VALUES (3, 'Melon Oignons Fraises','S1', '21/11/18',0);
        INSERT INTO livraison VALUES (4, 'Panets Courges Champignons','S1', '05/12/18',0);
        INSERT INTO livraison VALUES (5, 'Tomates Haricots Potiron','S1', '15/12/18',0);
        INSERT INTO livraison VALUES (6, 'Echalottes Bettrave Cornichons','S2', '28/12/18',0);
        INSERT INTO livraison VALUES (7, 'Morilles Pommes de terre Carottes','S2', '05/01/19',0);
        INSERT INTO livraison VALUES (8, 'Navets Potimarron Artichauts','S2', '15/01/19',0);
        INSERT INTO livraison VALUES (9, 'Champignons Carottes Marrons','S2', '28/01/19',0);
        INSERT INTO livraison VALUES (10, 'Cerise Courgettes Concombre','S2', '05/02/19',0);

        INSERT INTO commande VALUES (1, 1, 1, 1);
        INSERT INTO commande VALUES (2, 1, 10, 1);
        INSERT INTO commande VALUES (3, 2, 2, 2);
        INSERT INTO commande VALUES (4, 2, 9, 2);
        INSERT INTO commande VALUES (5, 3, 3, 2);
        INSERT INTO commande VALUES (6, 3, 7, 2);
        INSERT INTO commande VALUES (7, 4, 4, 2);
        INSERT INTO commande VALUES (8, 5, 6, 2);
        INSERT INTO commande VALUES (9, 6, 5, 2);
        INSERT INTO commande VALUES (10, 6, 5, 2);
        INSERT INTO commande VALUES (11, 7, 6, 2);
        INSERT INTO commande VALUES (12, 7, 4, 2);
        INSERT INTO commande VALUES (13, 8, 7, 2);
        INSERT INTO commande VALUES (14, 9, 3, 2);
        INSERT INTO commande VALUES (15, 10, 8, 2);*/

       statement.executeUpdate("INSERT INTO livraison VALUES (1, 'Salade Tomate Oignon','S1', '29/09/18',0);");
       statement.executeUpdate("INSERT INTO livraison VALUES (2, 'Pommes poires carrottes','S1', '11/11/18',0);");
       statement.executeUpdate("INSERT INTO livraison VALUES (3, 'Melon Oignons Fraises','S1', '21/11/18',0);");
       statement.executeUpdate("INSERT INTO livraison VALUES (4, 'Panets Courges Champignons','S1', '05/12/18',0);");
       statement.executeUpdate("INSERT INTO livraison VALUES (5, 'Tomates Haricots Potiron','S1', '15/12/18',0);");
       statement.executeUpdate("INSERT INTO livraison VALUES (6, 'Echalottes Bettrave Cornichons','S2', '28/12/18',0);");
       statement.executeUpdate("INSERT INTO livraison VALUES (7, 'Morilles Pommes de terre Carottes','S2', '05/01/19',0);");
       statement.executeUpdate("INSERT INTO livraison VALUES (8, 'Navets Potimarron Artichauts','S2', '15/01/19',0);");
       statement.executeUpdate("INSERT INTO livraison VALUES (9, 'Champignons Carottes Marrons','S2', '28/01/19',0);");
       statement.executeUpdate("INSERT INTO livraison VALUES (10, 'Cerise Courgettes Concombre','S2', '05/02/19',0);");

       statement.executeUpdate("INSERT INTO commande VALUES (1, 1, 1, 1);");
       statement.executeUpdate("INSERT INTO commande VALUES (2, 1, 10, 1);");
       statement.executeUpdate("INSERT INTO commande VALUES (3, 2, 2, 2);");
       statement.executeUpdate("INSERT INTO commande VALUES (4, 2, 9, 2);");
        statement.executeUpdate("INSERT INTO commande VALUES (5, 3, 3, 2);");
        statement.executeUpdate("INSERT INTO commande VALUES (6, 3, 7, 2);");
        statement.executeUpdate("INSERT INTO commande VALUES (7, 4, 4, 2);");
        statement.executeUpdate("INSERT INTO commande VALUES (8, 5, 6, 2);");
        statement.executeUpdate("INSERT INTO commande VALUES (9, 6, 5, 2);");
        statement.executeUpdate("INSERT INTO commande VALUES (10, 6, 5, 2);");
        statement.executeUpdate("INSERT INTO commande VALUES (11, 7, 6, 2);");
        statement.executeUpdate("INSERT INTO commande VALUES (12, 7, 4, 2);");
        statement.executeUpdate("INSERT INTO commande VALUES (13, 8, 7, 2);");
        statement.executeUpdate("INSERT INTO commande VALUES (14, 9, 3, 2);");
        statement.executeUpdate("INSERT INTO commande VALUES (15, 10, 8, 2);");

    }

    @Test
    public void shouldAddCommande() throws Exception{

        Integer idLivraison = 4;
        Integer idUser = 3;

        commandeDao.addCommande(idLivraison,idUser);

        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM commande WHERE idLivraison=4")){
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("idcommande")).isNotNull();
            Assertions.assertThat(resultSet.getInt("idlivraisoncommande")).isEqualTo(4);
            Assertions.assertThat(resultSet.getInt("idusercommande")).isEqualTo(3);
            Assertions.assertThat(resultSet.next()).isFalse();

        }

    }

    @Test
    public void shouldListCommandeByIdLivraison() throws SQLException {

        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement statement = connection.createStatement();

        Integer idLivraison = 1;
        List<CommandeParLivraison> commandes = commandeDao.listCommandeByIdLivraison(idLivraison);

        Assertions.assertThat(commandes).hasSize(2);
        Assertions.assertThat(commandes).extracting("idCommande").containsOnly(1,4);


    }
}
