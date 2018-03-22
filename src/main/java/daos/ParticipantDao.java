package daos;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import exceptions.NFFRuntimeException;
import pojos.Participant;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDao {

    public DataSource getDatasource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("northFreshFarmers");
        dataSource.setUser("root");
        dataSource.setPassword("AZEpoi77!");

        return dataSource;
    }

    // Ajout d'un participant

    public void addParticipant(Participant newParticipant, int idLivraison){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO participant(prenom,nom, email, motDePasse,livraison) VALUES (?,?,?,?,?)")) {
            statement.setString(1, newParticipant.getPrenom());
            statement.setString(2, newParticipant.getNom());
            statement.setString(3, newParticipant.getEmail());
            statement.setString(4, newParticipant.getMotDePasse());
            statement.setInt(5,idLivraison);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des données", e);
        }
    }

    // Liste des participants

    public List<Participant> ListeParticipants(Integer idLivraison) {
        List<Participant> participantLivraisonList = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM participant WHERE livraison=? ORDER BY idparticipant DESC")) {
            statement.setInt(1, idLivraison);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    participantLivraisonList.add(new Participant(
                            resultSet.getInt("idparticipant"),
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

    // Ajout d'un abonnnement

    public void addAbonnement5(Participant newParticipant){
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO participant(nom,prenom,email,motDePasse,livraison) VALUES (?,?,?,?,1),(?,?,?,?,2),(?,?,?,?,3),(?,?,?,?,4),(?,?,?,?,5)"
             )){
            statement.setString(1, newParticipant.getNom());
            statement.setString(2, newParticipant.getPrenom());
            statement.setString(3, newParticipant.getEmail());
            statement.setString(4, newParticipant.getMotDePasse());

            statement.setString(5, newParticipant.getNom());
            statement.setString(6, newParticipant.getPrenom());
            statement.setString(7, newParticipant.getEmail());
            statement.setString(8, newParticipant.getMotDePasse());

            statement.setString(19, newParticipant.getNom());
            statement.setString(10, newParticipant.getPrenom());
            statement.setString(11, newParticipant.getEmail());
            statement.setString(12, newParticipant.getMotDePasse());

            statement.setString(13, newParticipant.getNom());
            statement.setString(14, newParticipant.getPrenom());
            statement.setString(15, newParticipant.getEmail());
            statement.setString(16, newParticipant.getMotDePasse());

            statement.setString(17, newParticipant.getNom());
            statement.setString(18, newParticipant.getPrenom());
            statement.setString(19, newParticipant.getEmail());
            statement.setString(20, newParticipant.getMotDePasse());

            statement.executeUpdate();


        }catch (SQLException e) {
            throw new NFFRuntimeException("Erreur lors de la récupération des données", e);
        }
    }

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



}

