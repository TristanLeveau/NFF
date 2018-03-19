package services;

import daos.ParticipantDao;
import daos.LivraisonDao;
import pojos.Livraison;
//import pojos.Participant;
import pojos.ParticipantALivrer;
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
	
	public Livraison getLivraison(Integer id) {
		return livraisonDao.getLivraison(id);
	}

	public void addLivraison(Livraison newLivraison) throws IOException {
		livraisonDao.addLivraison(newLivraison);
	}



	public void supprimerLivraison(Integer id){
		livraisonDao.supprimerLivraison(id);
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

	public void addParticipantALivrer(ParticipantALivrer newParticipantLivraison, Integer idLivraison) {
		participantDao.addParticipantALivrer(newParticipantLivraison,idLivraison);
	}

	public List<ParticipantALivrer> ListeParticipantsALivrer(Integer idLivraison){
		List participantsALivrer = participantDao.ListeParticipantsALivrer(idLivraison);
		return participantsALivrer;
	}

	public void addAbonnement5(ParticipantALivrer newParticipantALivrer){
		participantDao.addAbonnement5(newParticipantALivrer);
	}
}