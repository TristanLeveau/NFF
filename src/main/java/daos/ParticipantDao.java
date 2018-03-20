package daos;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import exceptions.NFFRuntimeException;
import pojos.Livraison;
//import pojos.Participant;
import pojos.ParticipantALivrer;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDao {



    // Inscription

  /*  public void addParticipant(Participant newParticipant) {
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO participant(nom, prenom,email,motDePasse) VALUES (?,?,?,?)")) {
            statement.setString(1, newParticipant.getNom());
            statement.setString(2, newParticipant.getPrenom());
            statement.setString(3, newParticipant.getEmail());
            statement.setString(4, newParticipant.getMotDePasse());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des données", e);
        }
    }

    // Liste des participants

    public List<Participant> ListeParticipants() {
        List<Participant> participantList = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM participant ORDER BY id DESC")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    participantList.add(new Participant(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("email"),
                            resultSet.getString("motDePasse")


                    ));
                }
            }
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de l'ajout...",e);
        }
        return participantList;
    }

    // Récupération d'un participant
*/
  /*  public Participant getParticipant(Integer id) {
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM participant WHERE id = ? AND supprime=false")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return new Participant(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("email"),
                            resultSet.getString("motDePasse")
                    );
                }
            }
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des livraisons", e);
        }
        return null;
    }
*/
/*    public ParticipantALivrer verificationInscription(String email, String motDePasse){

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM participant WHERE (email=? AND motDePasse=?) LIMIT 1;")) {
            statement.setString(1, email);
            statement.setString(2, motDePasse);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return new ParticipantALivrer(0,0,
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            null

                    );

                }
            }
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de l'ajout...",e);
        }
        return null;
    }
*/


    public void addParticipantALivrer(ParticipantALivrer newParticipantALivrer, int idLivraison){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO participant(idlivraison, prenom,nom, email, motDePasse) VALUES (?,?,?,?,?)")) {
            statement.setInt(1,idLivraison);
            statement.setString(2, newParticipantALivrer.getPrenom());
            statement.setString(3, newParticipantALivrer.getNom());
            statement.setString(4, newParticipantALivrer.getEmail());
            statement.setString(5, newParticipantALivrer.getMotDePasse());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des données", e);
        }
    }

    public List<ParticipantALivrer> ListeParticipantsALivrer(Integer idLivraison) {
        List<ParticipantALivrer> participantLivraisonList = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM participant WHERE idlivraison=? ORDER BY id DESC")) {
            statement.setInt(1, idLivraison);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    participantLivraisonList.add(new ParticipantALivrer(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("email"),
                            resultSet.getString("motDePasse")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la liste",e);
        }
        return participantLivraisonList;
    }


    public void addAbonnement5(ParticipantALivrer newParticipantALivrer){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO participantALivrer(nom,prenom,email,motDePasse,idlivraison) VALUES (?,?,?,?,1),(?,?,?,?,2),(?,?,?,?,3),(?,?,?,?,4),(?,?,?,?,5)"
        )){
            statement.setString(1, newParticipantALivrer.getNom());
            statement.setString(2, newParticipantALivrer.getPrenom());
            statement.setString(3, newParticipantALivrer.getEmail());
            statement.setString(4, newParticipantALivrer.getMotDePasse());
            statement.setString(5, newParticipantALivrer.getNom());
            statement.setString(6, newParticipantALivrer.getPrenom());
            statement.setString(7, newParticipantALivrer.getEmail());
            statement.setString(8, newParticipantALivrer.getMotDePasse());
            statement.setString(19, newParticipantALivrer.getNom());
            statement.setString(10, newParticipantALivrer.getPrenom());
            statement.setString(11, newParticipantALivrer.getEmail());
            statement.setString(12, newParticipantALivrer.getMotDePasse());
            statement.setString(13, newParticipantALivrer.getNom());
            statement.setString(14, newParticipantALivrer.getPrenom());
            statement.setString(15, newParticipantALivrer.getEmail());
            statement.setString(16, newParticipantALivrer.getMotDePasse());
            statement.setString(17, newParticipantALivrer.getNom());
            statement.setString(18, newParticipantALivrer.getPrenom());
            statement.setString(19, newParticipantALivrer.getEmail());
            statement.setString(20, newParticipantALivrer.getMotDePasse());

        }catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des données", e);
        }
    }
}

