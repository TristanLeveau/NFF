package daos;

import exceptions.NFFRuntimeException;
import pojos.Livraison;
import pojos.Semestre;

import javax.servlet.http.Part;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivraisonDao {

	public List<Livraison> listLivraisons() {
		List<Livraison> livraisons = new ArrayList<>();

		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM livraison WHERE supprime=false ORDER BY id")) {
			while (resultSet.next()) {
				livraisons.add(
						new Livraison(
								resultSet.getInt("id"),
								resultSet.getString("date"),
								resultSet.getString("contenu"),
								Semestre.valueOf(resultSet.getString("semestre"))
						));


			}
		} catch (SQLException e) {
			throw new NFFRuntimeException("Erreur lors de la récupération des livraisons", e);
		}

		return livraisons;
	}

	public List<Livraison> listLivraisonsSemestre(Semestre semestre) {
		List<Livraison> livraisons = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			 PreparedStatement statement = connection.prepareStatement("SELECT * FROM livraison WHERE semestre = ? AND supprime=false ORDER BY id ")){
			statement.setString(1, semestre.name());
			try (ResultSet resultSet = statement.executeQuery()){
				while (resultSet.next()){
					livraisons.add(
							new Livraison(
									resultSet.getInt("id"),
									resultSet.getString("date"),
									resultSet.getString("contenu"),
									Semestre.valueOf(resultSet.getString("semestre"))
							)
					);



				}
			}
		}catch (SQLException e){
			 	throw new NFFRuntimeException("Erreur lors de l'accès a la base de données", e);
		}
		return livraisons;
	}

	public Livraison getLivraison(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM livraison WHERE id = ? AND supprime=false")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					return new Livraison(
							resultSet.getInt("id"),
							resultSet.getString("date"),
							resultSet.getString("contenu"),
							Semestre.valueOf(resultSet.getString("semestre"))
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
				PreparedStatement statement = connection.prepareStatement("INSERT INTO livraison(date, contenu, semestre) VALUES (?, ?,?)")) {
			statement.setString(1, newLivraison.getDate());
			statement.setString(2, newLivraison.getContenu());
			statement.setString(3, newLivraison.getSemestre().name());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new NFFRuntimeException("Erreur lors de la récupération des livraisons", e);
		}
	}

	public void supprimerLivraison(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			 PreparedStatement statement = connection.prepareStatement("UPDATE livraison SET supprime=true WHERE id = ?")) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateLivraison (Livraison newLivraison) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			 PreparedStatement statement = connection.prepareStatement("UPDATE livraison SET date=?, contenu=?, semestre=? WHERE id=?")) {
			statement.setString(1, newLivraison.getDate());
			statement.setString(2, newLivraison.getContenu());
			statement.setString(3, newLivraison.getSemestre().name());
			statement.setInt(4, newLivraison.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new NFFRuntimeException("Erreur lors de la récupération des livraisons", e);
		}
	}
}
