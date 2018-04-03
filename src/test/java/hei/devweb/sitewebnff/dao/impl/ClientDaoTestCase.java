package hei.devweb.sitewebnff.dao.impl;

import daos.ClientDao;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import pojos.Client;
import pojos.Livraison;
import pojos.Participant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDaoTestCase extends AbstractDaoTestCase {

    ClientDao clientDao = new ClientDao();

    @Override
    public void insertDataSet(Statement statement) throws Exception {
        statement.executeUpdate("INSERT INTO livraison(idlivraison, contenu, date) VALUES (1,'salade','03/04/2018')");
        statement.executeUpdate("INSERT INTO livraison(idlivraison, contenu, date) VALUES (2,'tomate','12/05/2018')");
        statement.executeUpdate("INSERT INTO livraison(idlivraison, contenu, date) VALUES (3,'oignon','20/06/2018')");
        statement.executeUpdate("INSERT INTO participant(idparticipant,nom, prenom, email, motDePasse, livraison) VALUES (1, 'nom1', 'prenom1','email1@email1.fr', 'mdp1', 1)");
        statement.executeUpdate("INSERT INTO participant(idparticipant,nom, prenom, email, motDePasse, livraison) VALUES (2, 'nom1', 'prenom1','email1@email1.fr', 'mdp1', 1)");
        statement.executeUpdate("INSERT INTO participant(idparticipant,nom, prenom, email, motDePasse, livraison) VALUES (3, 'nom1', 'prenom1','email1@email1.fr', 'mdp1', 1)");
        statement.executeUpdate("INSERT INTO client(idclient,nom, prenom, email, motDePasse) VALUES (1, 'nom1', 'prenom1','email1@email1.fr', 'mdp1')");
        statement.executeUpdate("INSERT INTO client(idclient,nom, prenom, email, motDePasse) VALUES (2, 'nom4', 'prenom4','email4@email4.fr', 'mdp4')");
        statement.executeUpdate("INSERT INTO client(idclient,nom, prenom, email, motDePasse) VALUES (3, 'nom5', 'prenom5','email5@email5.fr', 'mdp5')");

    }

    @Test
    public void shouldAddClient() throws Exception{
        //GIVEN
        Client client = new Client(null, "nom5", "prenom5", "email5@email5.fr","mdp5");
        //WHEN
        clientDao.addClient(client);
        //THEN
        try (Connection connection = clientDao.getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE nom = 'nom5'")) {
                Assertions.assertThat(rs.next()).isTrue();
                Assertions.assertThat(rs.getInt("idclient")).isGreaterThan(0);
                Assertions.assertThat(rs.getString("nom")).isEqualTo("nom5");
                Assertions.assertThat(rs.getString("prenom")).isEqualTo("prenom5");
                Assertions.assertThat(rs.getString("email")).isEqualTo("email5@email5.fr");
                Assertions.assertThat(rs.getString("motDePasse")).isEqualTo("mdp5");
                Assertions.assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldGetClientByEmailMdp() throws Exception {
        //WHEN
        Client client = clientDao.getClientByEmailMdp("email1@email1.fr","mdp1");
        //THEN

        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getIdClient()).isEqualTo(1);
        Assertions.assertThat(client.getNom()).isEqualTo("nom1");
        Assertions.assertThat(client.getPrenom()).isEqualTo("prenom1");
        Assertions.assertThat(client.getEmail()).isEqualTo("email1@email1.fr");

    }

    @Test
    public void shouldClientToParticipant() throws Exception{
        //GIVEN
        Participant participant = new Participant(null,null,null,null,null);
        //WHEN
        clientDao.ClientToParticipant("email20@email2.fr","mdp2",3, participant);
        //THEN
        try (Connection connection = clientDao.getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM participant WHERE email = 'email2@email2.fr' AND motDePasse ='mdp2' AND livraison = 3")) {
                Assertions.assertThat(rs.next()).isTrue();
                Assertions.assertThat(rs.getInt("idclient")).isGreaterThan(0);
                Assertions.assertThat(rs.getString("nom")).isEqualTo("nom2");
                Assertions.assertThat(rs.getString("prenom")).isEqualTo("prenom2");
                Assertions.assertThat(rs.getString("email")).isEqualTo("email2@email2.fr");
                Assertions.assertThat(rs.getString("motDePasse")).isEqualTo("mdp2");
                Assertions.assertThat(rs.next()).isFalse();
            }
        }

    }
}
