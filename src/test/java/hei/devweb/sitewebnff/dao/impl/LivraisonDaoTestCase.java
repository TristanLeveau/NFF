package hei.devweb.sitewebnff.dao.impl;

import org.junit.Assert.*;
import org.assertj.core.api.Assertions;
import daos.LivraisonDao;
import org.junit.Before;
import org.junit.Test;
import pojos.Livraison;
import pojos.Participant;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class LivraisonDaoTestCase extends AbstractDaoTestCase {

    private LivraisonDao livraisonDao = new LivraisonDao();


    @Override
    public void insertDataSet(Statement statement) throws Exception {
        statement.executeUpdate("INSERT INTO livraison(idlivraison, contenu, date) VALUES (1,'salade','03/04/2018')");
        statement.executeUpdate("INSERT INTO livraison(idlivraison, contenu, date) VALUES (2,'tomate','03/04/2018')");
        statement.executeUpdate("INSERT INTO livraison(idlivraison, contenu, date) VALUES (3,'oignon','03/04/2018')");
        statement.executeUpdate("INSERT INTO participant(idparticipant,nom, prenom, email, motDePasse, livraison) VALUES (1, 'nom1', 'prenom1','email1', 'mdp1', 1)");

    }


    @Test
    public void shouldListLivraison(){
        //WHEN
        List<Livraison> livraisons = livraisonDao.listLivraisons();
        //THEN
        Assertions.assertThat(livraisons).hasSize(3);
        Assertions.assertThat(livraisons).extracting("contenu","date").containsOnly(
                Assertions.tuple("salade","03/04/2018"),
                Assertions.tuple("tomate","03/04/2018"),
                Assertions.tuple("oignon","03/04/2018")
        );
    }

    @Test
    public void shouldAddLivraison() throws SQLException {
        //GIVEN
        Livraison livraison = new Livraison(null, "20/03/2018", "double salade");
        //WHEN
        livraisonDao.addLivraison(livraison);
        //THEN
        try (Connection connection = livraisonDao.getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM livraison WHERE date = '20/03/2018'")) {
                Assertions.assertThat(rs.next()).isTrue();
                Assertions.assertThat(rs.getInt("idlivraison")).isGreaterThan(0);
                Assertions.assertThat(rs.getString("date")).isEqualTo("20/03/2018");
                Assertions.assertThat(rs.getString("contenu")).isEqualTo("double salade");
                Assertions.assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldGetLivraison() throws SQLException {
        //WHEN
        livraisonDao.getLivraison(3);
        //THEN
        try (Connection connection = livraisonDao.getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM livraison WHERE idlivraison = 3")){
                Assertions.assertThat(rs.next()).isTrue();
                Assertions.assertThat(rs.getInt("idlivraison")).isEqualTo(3);
                Assertions.assertThat(rs.getString("date")).isEqualTo("03/04/2018");
                Assertions.assertThat(rs.getString("contenu")).isEqualTo("oignon");
                Assertions.assertThat(rs.next()).isFalse();
            }
        }

    }

    @Test
    public void shouldSupprimerLivraison() {
        //WHEN
        livraisonDao.supprimerLivraison(1);
        //THEN
        try (Connection connection = livraisonDao.getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM livraison WHERE idlivraison = 1")) {
                Assertions.assertThat(rs.getInt("idlivraison")).isNull();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldListParticipantByEmailMdp(){

        String email = "email1";
        String motDePasse = "mdp1";

        List<Participant> participants = livraisonDao.ListeParticipantsByEmailMdp(email,motDePasse);
        Assertions.assertThat(participants).hasSize(1);
        Assertions.assertThat(participants).extracting( "nom", "prenom", "email", "motDePasse").containsOnly(

                tuple("nom1", "prenom1", "email1", "mdp1")

        );

    }

}
