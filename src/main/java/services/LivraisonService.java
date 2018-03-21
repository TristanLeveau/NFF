package services;

import daos.ParticipantDao;
import daos.LivraisonDao;
import pojos.Livraison;
import pojos.Participant;
import java.io.IOException;
import java.util.List;

public class LivraisonService {


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
	
	public Livraison getLivraison(Integer idLivraison) {
		return livraisonDao.getLivraison(idLivraison);
	}

	public void addLivraison(Livraison newLivraison) throws IOException {
		livraisonDao.addLivraison(newLivraison);
	}



	public void supprimerLivraison(Integer idLivraison){
		livraisonDao.supprimerLivraison(idLivraison);
	}


	/*public void addParticipant(Participant participant){
		participantDao.addParticipant(participant);
	}

	public List<Participant> ListeParticipants(){
		List participants = participantDao.ListeParticipants();
		return participants;
	}

	public void updateLivraison(Livraison newLivraison, Integer id){
		livraisonDao.updateLivraison(newLivraison);
	}


	public ParticipantALivrer verificationInscription (String email, String motDePasse){
	ParticipantALivrer newParticipantLivraison = participantDao.addParticipantALivrer(email,motDePasse);
	return newParticipantLivraison;
}*/

	public void addParticipant (Participant newParticipant, Integer idLivraison) {
		if(newParticipant.getNom() == null || "".equals(newParticipant.getNom())) {
			throw new IllegalArgumentException("Un nom doit être renseigné.");
		}
		if (newParticipant.getPrenom()==null|| "".equals(newParticipant.getPrenom())){
			throw new IllegalArgumentException("Un prénom doit être spécifié");
		}
		if (newParticipant.getEmail()==null|| "".equals(newParticipant.getEmail())){
			throw new IllegalArgumentException("Un email doit être spécifié");
		}
		participantDao.addParticipant(newParticipant,idLivraison);
	}

	public List<Participant> ListeParticipants(Integer idLivraison){
		List participants = participantDao.ListeParticipants(idLivraison);
		return participants;
	}

	public void addAbonnement5(Participant newParticipant){
		participantDao.addAbonnement5(newParticipant);
	}
}