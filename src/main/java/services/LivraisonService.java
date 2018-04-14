package services;

import daos.LivraisonDao;
import pojos.Livraison;

import java.io.IOException;
import java.util.List;


// Gestion des services de livraison

public class LivraisonService {

    private LivraisonDao livraisonDao = new LivraisonDao();

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
}
