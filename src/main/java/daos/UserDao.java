package daos;

import exceptions.NFFRuntimeException;
import pojos.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {


    // Inscription d'un nouvel utilisateur

    public void addUser(User user){

        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user(nom,prenom,email,motDePasse) VALUES(?,?,?,?)")) {

                statement.setString(1,user.getNom());
                statement.setString(2,user.getPrenom());
                statement.setString(3,user.getEmail());
                statement.setString(4,user.getMotDePasse());

                statement.executeUpdate();

        }catch (SQLException e){
            throw new NFFRuntimeException("Erreur lors de l'inscription", e);
        }
    }

    // Liste des utilisateurs inscrits
    public List<User> listUsers() {
        List<User> users = new ArrayList<User>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM user ORDER BY nom")) {
            while (resultSet.next()) {
                users.add(
                        new User(resultSet.getInt("iduser"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email"), resultSet.getString("motDePasse")));
            }
        } catch (SQLException e) {
            throw new NFFRuntimeException("Error when getting users", e);
        }

        return users;
    }


    // Récupération de l'utilisateur à l'aide du mot de passe et du mail

    public User getUserByEmailMDP(String email, String motDePasse){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND motDePasse=?")) {
            statement.setString(1, email);
            statement.setString(2,motDePasse);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return new User(resultSet.getInt("idUser"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("email"),resultSet.getString("motDePasse"));
                }
            }
        } catch (SQLException e) {
            throw new NFFRuntimeException("Error when getting user...", e);
        }
        return null;
    }


}
