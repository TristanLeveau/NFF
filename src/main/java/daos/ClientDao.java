package daos;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import exceptions.NFFRuntimeException;
import pojos.Client;
import pojos.Participant;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao {

    public DataSource getDatasource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("northFreshFarmers3");
        dataSource.setUser("root");
        dataSource.setPassword("tristan123");

        return dataSource;
    }

    public void addClient(Client newClient) throws Exception{
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO client(prenom,nom, email, motDePasse) VALUES (?,?,?,?)")) {
            statement.setString(1, newClient.getPrenom());
            statement.setString(2, newClient.getNom());
            statement.setString(3, newClient.getEmail());
            statement.setString(4, newClient.getMotDePasse());

            statement.executeUpdate();
        }
    }

    public Client getClientByEmailMdp(String email, String motDePasse) throws Exception {
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM client WHERE email = ? AND motDePasse = ?")) {

            statement.setString(1, email);
            statement.setString(2, motDePasse);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return new Client(
                                resultSet.getInt("idclient"),
                                resultSet.getString("nom"),
                                resultSet.getString("prenom"),
                                resultSet.getString("email"),
                                resultSet.getString("motDePasse")
                        );
                    }
                }
            }
            return null;
        }


    public void ClientToParticipant (String email, String motDePasse, Integer idLivraison, Participant participant) throws Exception {

        Client client = getClientByEmailMdp(email,motDePasse);

        String nom = client.getNom();
        String prenom = client.getPrenom();

        participant.setNom(nom);
        participant.setPrenom(prenom);
        participant.setEmail(email);
        participant.setMotDePasse(motDePasse);

        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO participant(prenom,nom, email, motDePasse, livraison) VALUES (?,?,?,?,?)")) {
            statement.setString(1, participant.getPrenom());
            statement.setString(2, participant.getNom());
            statement.setString(3, participant.getEmail());
            statement.setString(4, participant.getMotDePasse());
            statement.setInt(5, idLivraison);

            statement.executeUpdate();
        }
    }

}
