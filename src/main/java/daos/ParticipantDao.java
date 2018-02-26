package daos;

import exceptions.NFFRuntimeException;
import pojos.Livraison;
import pojos.Participant;
import pojos.ParticipantALivrer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDao {

    // Inscription

    public void addParticipant(Participant newParticipant) {
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

    public Participant getParticipant(Integer id) {
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

    public ParticipantALivrer verificationInscription(String email, String motDePasse){

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM participant WHERE (email=? AND motDePasse=?)")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return new ParticipantALivrer(null,null,
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

    public void addParticipantALivrer(ParticipantALivrer newParticipantALivrer, int idLivraison,String dateLivraison){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO participantALivrer(idLivraison,nom, prenom,dateLivraison) VALUES (?,?,?,?)")) {
            statement.setInt(1,idLivraison);
            statement.setInt(2, newParticipantALivrer.getIdParticipant());
            statement.setString(3, newParticipantALivrer.getPrenom());
            statement.setString(4,dateLivraison);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des données", e);
        }
    }

    public List<ParticipantALivrer> ListeParticipantsALivrer() {
        List<ParticipantALivrer> participantLivraisonList = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM participantALivrer ORDER BY idInstance DESC")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    participantLivraisonList.add(new ParticipantALivrer(
                            resultSet.getInt("idInstance"),
                            resultSet.getInt("idLivraison"),
                            resultSet.getInt("idParticipant"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("dateLivraison")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de l'ajout...",e);
        }
        return participantLivraisonList;
    }
}

