package hei.devweb.sitewebnff.dao.impl;

import daos.DataSourceProvider;
import daos.LivraisonDao;
import exceptions.NFFRuntimeException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import pojos.Livraison;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivraisonDaoTestCase extends AbstractDaoTestCase {

    LivraisonDao livraisonDao = new LivraisonDao();

    @Override
    public void insertDataSet(Statement statement) throws Exception {
        statement.executeUpdate("DELETE FROM user");
        statement.executeUpdate("DELETE FROM livraison");
        statement.executeUpdate("INSERT INTO livraison VALUES (1, 'Salade Tomate Oignon','S1', '29/09/18',0);");
        statement.executeUpdate("INSERT INTO livraison VALUES (2, 'Salade Tomate Oignon','S1', '11/11/18',0);");
        statement.executeUpdate("INSERT INTO livraison VALUES (3, 'Salade Tomate Oignon','S1', '21/11/18',0);");
        statement.executeUpdate("INSERT INTO livraison VALUES (4, 'Salade Tomate Oignon','S1', '05/12/18',0);");

    }

    @Test
    public void shouldListLivraison(){
        //WHEN
        List<Livraison> livraisons = livraisonDao.listLivraisons();
        //THEN
        Assertions.assertThat(livraisons).hasSize(4);
        Assertions.assertThat(livraisons).extracting("contenu","date").containsOnly(
                Assertions.tuple("Salade Tomate Oignon","29/09/18"),
                Assertions.tuple("Salade Tomate Oignon","11/11/18"),
                Assertions.tuple("Salade Tomate Oignon","21/11/18"),
                Assertions.tuple("Salade Tomate Oignon","05/12/18")


        );
    }

    @Test
    public void shouldAddLivraison() throws SQLException {
        //GIVEN
        Livraison livraison = new Livraison(null, "21/01/2018", "Salade Tomates Poireaux");
        //WHEN
        livraisonDao.addLivraison(livraison);
        //THEN
        try (Connection connection = DataSourceProvider.getInstance().getDatasource().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM livraison WHERE contenu = 'Salade Tomates Poireaux'")) {
                Assertions.assertThat(rs.getInt("idlivraison")).isGreaterThan(0);
                Assertions.assertThat(rs.getString("date")).isEqualTo("21/01/2018");
                Assertions.assertThat(rs.getString("contenu")).isEqualTo("Salade Tomates Poireaux");
                Assertions.assertThat(rs.next()).isFalse();
            }
        }


    @Test
    public void shouldGetLivraison() throws SQLException {
        //WHEN
        Livraison livraison = livraisonDao.getLivraison(3);
        //THEN
        Assertions.assertThat(livraison).isNotNull();
        Assertions.assertThat(livraison.getIdLivraison()).isEqualTo(3);
        Assertions.assertThat(livraison.getDate()).isEqualTo("21/11/18");
        Assertions.assertThat(livraison.getContenu()).isEqualTo("Salade Tomate Oignon");

    }




    @Test
    public void shouldSupprimerLivraison() {
        //WHEN
        livraisonDao.supprimerLivraison(1);
        //THEN
        try (Connection connection = DataSourceProvider.getInstance().getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM livraison WHERE idlivraison = 1")) {
                Assertions.assertThat(rs.getInt("idLivraison")).isNull();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    @Test
    public void shouldListParticipantByEmailMdp(){

        String email = "email1@emai1.fr";
        String motDePasse = "mdp1";

        List<Participant> participants = livraisonDao.ListeParticipantsByEmailMdp(email,motDePasse);
        Assertions.assertThat(participants).hasSize(1);
        Assertions.assertThat(participants).extracting( "nom", "prenom", "email", "motDePasse").containsOnly(

                tuple("nom1", "prenom1", "email1", "mdp1")

        );

    }
*/

}
