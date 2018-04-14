package daos;

import exceptions.NFFRuntimeException;
import pojos.Livraison;
import pojos.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivraisonDao {

    // Liste des livraisons
    public List<Livraison> listLivraisons() {
        List<Livraison> livraisons = new ArrayList<>();

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM livraison WHERE supprime=false ORDER BY idlivraison")) {
            while (resultSet.next()) {
                livraisons.add(
                        new Livraison(
                                resultSet.getInt("idlivraison"),
                                resultSet.getString("date"),
                                resultSet.getString("contenu")
                        ));


            }
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des livraisons", e);
        }

        return livraisons;
    }


    // Retourne la livraison dont l'ID est demandé
    public Livraison getLivraison(Integer id) {
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM livraison WHERE idlivraison = ? AND supprime=false")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return new Livraison(
                            resultSet.getInt("idlivraison"),
                            resultSet.getString("date"),
                            resultSet.getString("contenu")
                    );
                }
            }
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des livraisons", e);
        }
        return null;
    }

    // Ajoute une livraison
    public void addLivraison(Livraison livraison) {

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO livraison(date, contenu,semestre,supprime) VALUES (?, ?,'S1',0)")) {

            statement.setString(1, livraison.getDate());
            statement.setString(2, livraison.getContenu());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des livraisons", e);
        }
    }

    // Supprime une livraison
    public void supprimerLivraison(Integer id) {
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE livraison SET supprime=true WHERE idlivraison = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fonction de mise à jour d'une livraison ( non-utilisée)
    public void updateLivraison(Livraison newLivraison) {
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE livraison SET date=?, contenu=? WHERE idlivraison=?")) {
            statement.setString(1, newLivraison.getDate());
            statement.setString(2, newLivraison.getContenu());
            statement.setInt(3, newLivraison.getIdLivraison());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des livraisons", e);
        }
    }


   /* public List<User> ListeParticipantsByEmailMdp(String email, String motDePasse) {
        List<User> participantLivraisonList = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE email=? AND motDePasse=? ORDER BY iduser DESC")) {
            statement.setString(1, email);
            statement.setString(2, motDePasse);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    participantLivraisonList.add(new User(
                            resultSet.getInt("idUser"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("email"),
                            resultSet.getString("motDePasse")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la liste", e);
        }
        return participantLivraisonList;
    }*/

}
