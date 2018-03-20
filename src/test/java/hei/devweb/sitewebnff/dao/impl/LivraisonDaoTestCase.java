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
        // Assertions.assertThat(livraisons).hasSize(3);
        Assertions.assertThat(livraisons).extracting("id","contenu","date").containsOnly(
                Assertions.tuple(1,"salade","03/04/2018"),
                Assertions.tuple(2,"tomate","03/04/2018"),
                Assertions.tuple(3,"oignon","03/04/2018")
        );
    }


}
