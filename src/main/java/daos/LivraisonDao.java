package daos;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import exceptions.NFFRuntimeException;
import pojos.Livraison;
import pojos.Participant;
import pojos.Semestre;
import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivraisonDao {

	public DataSource getDatasource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
		dataSource.setDatabaseName("northFreshFarmers3");
		dataSource.setUser("root");
		dataSource.setPassword("tristan123");

		return dataSource;
	}

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

	public void addLivraison(Livraison newLivraison) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			 PreparedStatement statement = connection.prepareStatement("INSERT INTO livraison(date, contenu) VALUES (?, ?)")) {
			statement.setString(1, newLivraison.getDate());
			statement.setString(2, newLivraison.getContenu());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new NFFRuntimeException("Erreur lors de la récupération des livraisons", e);
		}
	}

	public void supprimerLivraison(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			 PreparedStatement statement = connection.prepareStatement("UPDATE livraison SET supprime=true WHERE idlivraison = ?")) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateLivraison(Livraison newLivraison) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			 PreparedStatement statement = connection.prepareStatement("UPDATE livraison SET date=?, contenu=? WHERE idlivraison=?")) {
			statement.setString(1, newLivraison.getDate());
			statement.setString(2, newLivraison.getContenu());
			statement.setInt(3, newLivraison.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new NFFRuntimeException("Erreur lors de la récupération des livraisons", e);
		}
	}


	public List<Participant> ListeParticipantsByEmailMdp(String emailParticipant, String motDePasse) {
		List<Participant> participantLivraisonList = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			 PreparedStatement statement = connection.prepareStatement("SELECT * FROM participant WHERE email=? AND motDePasse=? ORDER BY idparticipant DESC")) {
			statement.setString(1, emailParticipant);
			statement.setString(2, motDePasse);
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
			throw new NFFRuntimeException("Erreur lors de la liste", e);
		}
		return participantLivraisonList;
	}
}