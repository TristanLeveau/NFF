package hei.devweb.sitewebnff.dao.impl;

import static org.junit.Assert.*;

import daos.LivraisonDao;
import org.junit.Before;
import org.junit.Test;
import pojos.Livraison;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class LivraisonDaoTestCase {
    private LivraisonDao livraisonDao = new LivraisonDao();

    @Before
    public void initDatabase() throws SQLException {
        try (Connection connection = livraisonDao.getDatasource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM participant");
            stmt.executeUpdate("DELETE FROM livraison");
            stmt.executeUpdate("INSERT INTO livraison(id, contenu, date) VALUES (1,'salade','03/04/2018')");
            stmt.executeUpdate("INSERT INTO livraison(id, contenu, date) VALUES (2,'tomate','03/04/2018')");
            stmt.executeUpdate("INSERT INTO livraison(id, contenu, date) VALUES (3,'oignon','03/04/2018')");


        }

    }

    @Test
    public void shouldListLivraison(){
        //WHEN
        List<Livraison> livraisons = livraisonDao.listLivraisons();
        //THEN
        assertThat(livraisons).hasSize(3);
        assertThat(livraisons).extracting("id","contenu","date").containsOnly(
                tuple(1,"salade","03/04/2018"),
                tuple(2,"tomate","03/04/2018"),
                tuple(3,"oignon","03/04/2018")
        );
    }


}
