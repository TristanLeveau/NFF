package hei.devweb.sitewebnff.dao.impl;

import daos.DataSourceProvider;
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
        statement.executeUpdate("INSERT INTO livraison(idlivraison, contenu, date) VALUES (2,'tomate','12/05/2018')");
        statement.executeUpdate("INSERT INTO livraison(idlivraison, contenu, date) VALUES (3,'oignon','20/06/2018')");
        statement.executeUpdate("INSERT INTO participant(idparticipant,nom, prenom, email, motDePasse, livraison) VALUES (1, 'nom1', 'prenom1','email1@email1.fr', 'mdp1', 1)");
        statement.executeUpdate("INSERT INTO participant(idparticipant,nom, prenom, email, motDePasse, livraison) VALUES (2, 'nom1', 'prenom1','email1@email1.fr', 'mdp1', 1)");
        statement.executeUpdate("INSERT INTO participant(idparticipant,nom, prenom, email, motDePasse, livraison) VALUES (3, 'nom1', 'prenom1','email1@email1.fr', 'mdp1', 1)");
        statement.executeUpdate("INSERT INTO client(idclient,nom, prenom, email, motDePasse) VALUES (1, 'nom1', 'prenom1','email1@email1.fr', 'mdp1')");
        statement.executeUpdate("INSERT INTO client(idclient,nom, prenom, email, motDePasse) VALUES (2, 'nom2', 'prenom2','email2@email2.fr', 'mdp2')");
        statement.executeUpdate("INSERT INTO client(idclient,nom, prenom, email, motDePasse) VALUES (3, 'nom3', 'prenom3','email2@email3.fr', 'mdp3')");

    }

    @Test
    public void shouldListLivraison(){
        //WHEN
        List<Livraison> livraisons = livraisonDao.listLivraisons();
        //THEN
        Assertions.assertThat(livraisons).hasSize(3);
        Assertions.assertThat(livraisons).extracting("contenu","date").containsOnly(
                Assertions.tuple("salade","03/04/2018"),
                Assertions.tuple("tomate","12/05/2018"),
                Assertions.tuple("oignon","20/06/2018")
        );
    }

    @Test
    public void shouldAddLivraison() throws SQLException {
        //GIVEN
        Livraison livraison = new Livraison(null, "2018-04-12", "Concombre");
        //WHEN
        livraisonDao.addLivraison(livraison);
        //THEN
        try (Connection connection = DataSourceProvider.getInstance().getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM livraison WHERE date = '2018-04-12'")) {
                Assertions.assertThat(rs.next()).isTrue();
                Assertions.assertThat(rs.getInt("idlivraison")).isGreaterThan(0);
                Assertions.assertThat(rs.getString("date")).isEqualTo("2018-04-12");
                Assertions.assertThat(rs.getString("contenu")).isEqualTo("Concombre");
                Assertions.assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldGetLivraison() throws SQLException {
        //WHEN
        livraisonDao.getLivraison(3);
        //THEN
        try (Connection connection = DataSourceProvider.getInstance().getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM livraison WHERE idlivraison = 3")){
                Assertions.assertThat(rs.next()).isTrue();
                Assertions.assertThat(rs.getInt("idlivraison")).isEqualTo(3);
                Assertions.assertThat(rs.getString("date")).isEqualTo("20/06/2018");
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
        try (Connection connection = DataSourceProvider.getInstance().getDatasource().getConnection();
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

        String email = "email1@emai1.fr";
        String motDePasse = "mdp1";

        List<Participant> participants = livraisonDao.ListeParticipantsByEmailMdp(email,motDePasse);
        Assertions.assertThat(participants).hasSize(1);
        Assertions.assertThat(participants).extracting( "nom", "prenom", "email", "motDePasse").containsOnly(

                tuple("nom1", "prenom1", "email1", "mdp1")

        );

    }

}
