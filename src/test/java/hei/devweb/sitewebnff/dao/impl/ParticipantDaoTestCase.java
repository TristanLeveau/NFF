package hei.devweb.sitewebnff.dao.impl;


import daos.ParticipantDao;
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

public class ParticipantDaoTestCase {
    private ParticipantDao participantDao = new ParticipantDao();

    @Before
    public void initDatabase() throws SQLException {
        try (Connection connection = participantDao.getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.executeUpdate("DELETE FROM participant");
            stmt.executeUpdate("DELETE FROM livraison");
            stmt.executeUpdate("INSERT INTO livraison(id,date, contenu ) VALUES (1,'04/03/2018','tacos')");
            stmt.executeUpdate("INSERT INTO livraison(id,date, contenu ) VALUES (2,'04/04/2018','kebab')");
            stmt.executeUpdate(
                    "INSERT INTO participant(idParticipant,nom, prenom, email, motDePasse, id) "
                            + "VALUES (1, 'nom1', 'prenom1','email1', 'mdp1', 1)");
            stmt.executeUpdate(
                    "INSERT INTO participant(idParticipant,nom, prenom, email, motDePasse, id) "
                            + "VALUES (2, 'nom2', 'prenom2','email2', 'mdp2', 1)");
        }

    }

    @Test
    public void shouldListMorceaux() {
        //WHEN
        List<Participant> participants = participantDao.ListeParticipants(1);
        //THEN
        assertThat(participants).hasSize(1);
        assertThat(participants).extracting("idParticipant", "nom", "prenom", "email", "motDePasse", "livraison.id", "livraison.date","livraison.contenu").containsOnly(

                tuple(1, "nom1", "prenom1", "prenom1", "email1", "mdp1", 1,"04/03/2018","tacos")

        );

    }
}
