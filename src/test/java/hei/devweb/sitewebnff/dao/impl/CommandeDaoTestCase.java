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

       //statement.executeUpdate("DELETE FROM user");
       //statement.executeUpdate("DELETE FROM livraison");
       statement.executeUpdate("DELETE FROM commande");


       // statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(1, 'Jean', 'Jacques','jean@jacques.fr','jean123')");
        //statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(2, 'John', 'Jill','john@jill.fr','john123')");
        //statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(3, 'James', 'Jonas','james@jonas.fr','james123')");

        //statement.executeUpdate("INSERT INTO livraison VALUES (1, 'Salade Tomate Oignon','S1', '29/09/18',0);");
       // statement.executeUpdate("INSERT INTO livraison VALUES (2, 'Salade Tomate Oignon','S1', '11/11/18',0);");
        //statement.executeUpdate("INSERT INTO livraison VALUES (3, 'Salade Tomate Oignon','S1', '21/11/18',0);");
       // statement.executeUpdate("INSERT INTO livraison VALUES (4, 'Salade Tomate Oignon','S1', '05/12/18',0);");

       // statement.executeUpdate("INSERT INTO commande VALUES (1, 1, 1);");
       // statement.executeUpdate("INSERT INTO commande VALUES (2, 1, 2);");
       // statement.executeUpdate("INSERT INTO commande VALUES (3, 2, 3);");
       // statement.executeUpdate("INSERT INTO commande VALUES (4, 3, 1);");


    }

    @Test
    public void shouldAddCommande() throws Exception{

        Integer idLivraison = 20;
        Integer idUser = 20;
        commandeDao.addCommande(idLivraison,idUser);

        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM commande WHERE")){
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("idcommande")).isNotNull();
            Assertions.assertThat(resultSet.getInt("idlivraisoncommande")).isEqualTo(2);
            Assertions.assertThat(resultSet.getInt("idusercommande")).isEqualTo(2);
            Assertions.assertThat(resultSet.next()).isFalse();

    }

}

    @Test
    public void shouldListCommandeByIdLivraison() throws SQLException {
        Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO commande VALUES (5, 1, 1);");
        statement.executeUpdate("INSERT INTO commande VALUES (6, 1, 2);");
        statement.executeUpdate("INSERT INTO commande VALUES (7, 2, 3);");
        statement.executeUpdate("INSERT INTO commande VALUES (8, 3, 1);");

        Integer idLivraison = 1;
        List<CommandeParLivraison> commandes = commandeDao.listCommandeByIdLivraison(idLivraison);

        Assertions.assertThat(commandes).hasSize(2);
        Assertions.assertThat(commandes).extracting("idCommande").containsOnly(5,8);


    }
}
