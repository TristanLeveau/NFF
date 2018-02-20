package daos;

import exceptions.NFFRuntimeException;
import pojos.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDao {

    public void addParticipant(Participant newParticipant) {
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO participant(nom, prenom,email,motDePasse) VALUES (?, ?,?,?)")) {
            statement.setString(1, newParticipant.getNom());
            statement.setString(2, newParticipant.getPrenom());
            statement.setString(3, newParticipant.getEmail());
            statement.setString(4, newParticipant.getMotDePasse());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des données", e);
        }
    }


    public List<Participant> ListeParticipants() {
        List<Participant> participantList = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM participant ORDER BY nom DESC")) {
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
}