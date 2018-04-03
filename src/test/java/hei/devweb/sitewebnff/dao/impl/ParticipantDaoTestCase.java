package hei.devweb.sitewebnff.dao.impl;


import daos.ParticipantDao;
import org.junit.Assert.*;
import org.assertj.core.api.Assertions;
import daos.LivraisonDao;
import org.junit.Before;
import org.junit.Test;
import pojos.Client;
import pojos.Livraison;
import pojos.Participant;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class ParticipantDaoTestCase extends AbstractDaoTestCase {

    private ParticipantDao participantDao = new ParticipantDao();

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
    public void shouldAddParticipant(){
        //GIVEN
        Participant participant = new Participant(null, "nom4", "prenom4", "email4@email4.fr","mdp4");
        //WHEN
        participantDao.addParticipant(participant,1,"03/04/2018" );
        //THEN
        try (Connection connection = participantDao.getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE nom = 'nom4'")) {
                Assertions.assertThat(rs.next()).isTrue();
                Assertions.assertThat(rs.getInt("idclient")).isGreaterThan(0);
                Assertions.assertThat(rs.getString("nom")).isEqualTo("nom4");
                Assertions.assertThat(rs.getString("prenom")).isEqualTo("prenom4");
                Assertions.assertThat(rs.getString("email")).isEqualTo("email4@email4.fr");
                Assertions.assertThat(rs.getString("motDePasse")).isEqualTo("mdp4");
                Assertions.assertThat(rs.next()).isFalse();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldListMorceaux() {
        //WHEN
        List<Participant> participants = participantDao.ListeParticipants(1);
        //THEN
        //assertThat(participants).hasSize(1);
        assertThat(participants).extracting("idParticipant", "nom", "prenom", "email", "motDePasse").containsOnly(

                tuple(1, "nom1", "prenom1", "email1", "mdp1")

        );

    }

    @Test
    public void shouldGetDateParticipant(){
        String dateLivraison = participantDao.getDateParticipant(1);
        Assertions.assertThat(dateLivraison).isEqualTo("03/04/2018");
    }


}
