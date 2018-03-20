package hei.devweb.sitewebnff.dao.impl;

import org.junit.Assert.*;
import org.assertj.core.api.Assertions;
import daos.LivraisonDao;
import org.junit.Before;
import org.junit.Test;
import pojos.Livraison;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class LivraisonDaoTestCase extends AbstractDaoTestCase {

    private LivraisonDao livraisonDao = new LivraisonDao();


    @Override
    public void insertDataSet(Statement statement) throws Exception {
        statement.executeUpdate("INSERT INTO livraison(id, contenu, date) VALUES (1,'salade','03/04/2018')");
        statement.executeUpdate("INSERT INTO livraison(id, contenu, date) VALUES (2,'tomate','03/04/2018')");
        statement.executeUpdate("INSERT INTO livraison(id, contenu, date) VALUES (3,'oignon','03/04/2018')");
    }


    @Test
    public void shouldListLivraison(){
        //WHEN
        List<Livraison> livraisons = livraisonDao.listLivraisons();
        //THEN
        Assertions.assertThat(livraisons).hasSize(3);
        Assertions.assertThat(livraisons).extracting("id","contenu","date").containsOnly(
                Assertions.tuple(1,"salade","03/04/2018"),
                Assertions.tuple(2,"tomate","03/04/2018"),
                Assertions.tuple(3,"oignon","03/04/2018")
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
                Assertions.assertThat(rs.getInt("id")).isGreaterThan(0);
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
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM livraison WHERE id = 3")){
                Assertions.assertThat(rs.next()).isTrue();
                Assertions.assertThat(rs.getInt("id")).isEqualTo(3);
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
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM livraison WHERE idAnnonce = 1")) {
                Assertions.assertThat(rs.getInt("id")).isNull();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
