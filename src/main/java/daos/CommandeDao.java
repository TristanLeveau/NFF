package daos;

import exceptions.NFFRuntimeException;
import pojos.Commande;
import pojos.CommandeParLivraison;
import pojos.CommandeParUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeDao {


    // Ajout d'une commande
    public void addCommande(Integer idUser, Integer idLivraison){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO commande(idusercommande, idlivraisoncommande) VALUES (?, ?)")) {

            statement.setInt(1, idUser);
            statement.setInt(2, idLivraison);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de l'ajout de la participation...", e);
        }

    }

    // Liste des commandes par livraison
   public List<CommandeParLivraison> listCommandeByIdLivraison (Integer idLivraison){

        List<CommandeParLivraison> ListeCommandeParLivraison = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM commande a, user b WHERE a.idusercommande = b.iduser AND a.idlivraisoncommande = ? AND a.supprime=false")) {
                    statement.setInt(1, idLivraison);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            ListeCommandeParLivraison.add(new CommandeParLivraison(
                                    resultSet.getInt("idcommande"),
                                    resultSet.getInt("iduser"),
                                    resultSet.getString("nom"),
                                    resultSet.getString("prenom"),
                                    resultSet.getString("email")

                            ));
                }
                }
                }catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la liste",e);
        }
        return ListeCommandeParLivraison;

   }

    // Liste des commandes par utilisateur
    public List<CommandeParUser> listCommandeByUser (Integer idUser){

        List<CommandeParUser> ListeCommandeParLivraison = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM commande a, livraison b WHERE a.idlivraisoncommande = b.idlivraison AND a.idusercommande = ? AND a.supprime=false")) {
            statement.setInt(1, idUser);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ListeCommandeParLivraison.add(new CommandeParUser(
                            resultSet.getInt("idcommande"),
                            resultSet.getInt("idlivraison"),
                            resultSet.getString("date"),
                            resultSet.getString("contenu")

                    ));
                }
            }
        }catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la liste",e);
        }
        return ListeCommandeParLivraison;

    }

    // Supprime une commande
    public void supprimerCommande(Integer idCommande) {
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE commande SET supprime=true WHERE idcommande = ?")) {
            statement.setInt(1, idCommande);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
