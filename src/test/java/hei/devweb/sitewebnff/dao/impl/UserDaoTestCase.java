package hei.devweb.sitewebnff.dao.impl;

import daos.DataSourceProvider;
import daos.UserDao;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import pojos.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class UserDaoTestCase extends AbstractDaoTestCase {

    UserDao userDao = new UserDao();

    @Override
    public void insertDataSet(Statement statement) throws Exception {
        statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(1, 'Jean', 'Jacques','jean@jacques.fr','jean123')");
        statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(2, 'John', 'Jill','john@jill.fr','john123')");
        statement.executeUpdate("INSERT INTO user(iduser, prenom, nom, email, motDePasse) VALUES(3, 'James', 'Jonas','james@jonas.fr','james123')");
    }

    @Test
    public void shouldAddUser() throws Exception {

        User user = new User(null,"Leveau","Tristan","tristan.leveau@hei.yncrea.fr","tristan123");

        userDao.addUser(user);

        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE prenom='Tristan'")){
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("iduser")).isNotNull();
            Assertions.assertThat(resultSet.getString("nom")).isEqualTo("Leveau");
            Assertions.assertThat(resultSet.getString("email")).isEqualTo("tristan.leveau@hei.yncrea.fr");
            Assertions.assertThat(resultSet.getString("motDePasse")).isEqualTo("tristan123");
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }

    // Test de liste de utilisateurs
    @Test
    public void shouldListUsers() throws Exception {
        // WHEN
        List<User> users = userDao.listUsers();
        // THEN
        Assertions.assertThat(users).hasSize(3);
        Assertions.assertThat(users).extracting("idUser", "prenom", "nom","email","motDePasse").containsOnly(
                Assertions.tuple(1, "Jean", "Jacques","jean@jacques.fr","jean123"),
                Assertions.tuple(2, "John", "Jill","john@jill.fr","john123"),
                Assertions.tuple(3,"James", "Jonas","james@jonas.fr","james123")
        );
    }

    // Test pour retrouver un utilisateur inscrit avec son mail et mdp (connexionn)
    @Test
    public void shouldGetUserByEmailMDP() throws Exception {

        User user = userDao.getUserByEmailMDP("jean@jacques.fr","jean123");

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getIdUser()).isEqualTo(1);
        Assertions.assertThat(user.getNom()).isEqualTo("Jacques");
        Assertions.assertThat(user.getPrenom()).isEqualTo("Jean");
        Assertions.assertThat(user.getEmail()).isEqualTo("jean@jacques.fr");
        Assertions.assertThat(user.getMotDePasse()).isEqualTo("jean123");



    }
}
