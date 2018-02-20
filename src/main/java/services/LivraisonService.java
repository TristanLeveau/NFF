package services;

import daos.ParticipantDao;
import daos.LivraisonDao;
import pojos.Livraison;
import pojos.Participant;
import pojos.Semestre;

import javax.servlet.http.Part;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LivraisonService {


	private static final String CHEMIN_IMAGE = "/Users/tristanleveau/Desktop/images";

	private LivraisonDao livraisonDao = new LivraisonDao();
	private ParticipantDao participantDao = new ParticipantDao();
	private static class LivraisonServiceHolder {
		private static LivraisonService instance = new LivraisonService();
	}
	
	public static LivraisonService getInstance() {
		return LivraisonServiceHolder.instance;
	}

	private LivraisonService() { }
	
	public List<Livraison> listAllLivraisons() {
		return livraisonDao.listLivraisons();

	}
	
	public Livraison getLivraison(Integer id) {
		if(id == null) {
			throw new IllegalArgumentException("L'id de la livraison doit être renseigné.");
		}
		return livraisonDao.getLivraison(id);
	}

	public void addLivraison(Livraison newLivraison, Part image) throws IOException {

		if(newLivraison == null){
			throw new IllegalArgumentException("Une livraison doit être renseignée.");
		}
		if(newLivraison.getDate() == null || "".equals(newLivraison.getDate())) {
			throw new IllegalArgumentException("Une date doit être renseignée.");
		}

		livraisonDao.addLivraison(newLivraison);
	}



	public void supprimerLivraison(Integer id){
		livraisonDao.supprimerLivraison(id);
	}


	public void addParticipant(Participant participant){
		if(participant.getNom() == null || "".equals(participant.getNom())) {
			throw new IllegalArgumentException("Un nom doit être renseigné.");
		}
		if (participant.getPrenom()==null|| "".equals(participant.getPrenom())){
			throw new IllegalArgumentException("Un prénom doit être spécifié");
		}
		if (participant.getEmail()==null|| "".equals(participant.getEmail())){
			throw new IllegalArgumentException("Un email doit être spécifié");
		}
		participantDao.addParticipant(participant);
	}

	public List<Participant> ListeParticipants(){
		List participants = participantDao.ListeParticipants();
		return participants;
	}

	public void updateLivraison(Livraison newLivraison, Integer id){
		livraisonDao.updateLivraison(newLivraison);
	}

}
