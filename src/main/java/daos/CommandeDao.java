package daos;

import exceptions.NFFRuntimeException;
import pojos.Commande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeDao {

    public void addCommande(Integer idUser, Integer idLivraison){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO commande(idusercommande, idlivraisoncommande) VALUES (?, ?)")) {

            statement.setInt(1, idUser);
            statement.setInt(2, idLivraison);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des livraisons", e);
        }

    }

   public List<Commande> listCommandeByIdLivraison (Integer idLivraison){
   List<Commande> Listecommande = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM commande WHERE idlivraisoncommande=? ORDER BY idcommande DESC")) {
                    statement.setInt(1, idLivraison);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            Listecommande.add(new Commande(
                                    resultSet.getInt("idcommande")
                            ));
                }
                }
                }catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la liste",e);
        }
        return Listecommande;

   }
}
