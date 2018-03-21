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

public class ParticipantDaoTestCase extends AbstractDaoTestCase {
    private ParticipantDao participantDao = new ParticipantDao();


    @Override
    public void insertDataSet(Statement statement) throws Exception {
        statement.executeUpdate("INSERT INTO livraison(id, contenu, date) VALUES (1,'salade','03/04/2018')");
        statement.executeUpdate("INSERT INTO livraison(id, contenu, date) VALUES (2,'tomate','03/04/2018')");
        statement.executeUpdate("INSERT INTO livraison(id, contenu, date) VALUES (3,'oignon','03/04/2018')");
        statement.executeUpdate("INSERT INTO participant(idparticipant,nom, prenom, email, motDePasse, livraison) VALUES (1, 'nom1', 'prenom1','email1', 'mdp1', 1)");
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
}
