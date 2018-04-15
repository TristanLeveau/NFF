package services;

import daos.CommandeDao;
import pojos.Commande;
import pojos.CommandeParLivraison;
import pojos.CommandeParUser;
import pojos.User;

import java.util.List;

// Gestion des services de commande
public class CommandeService {

    CommandeDao commandeDao = new CommandeDao();

    private static class CommandeServiceHolder { private static CommandeService instance = new CommandeService();}

    public static CommandeService getInstance() {
        return CommandeServiceHolder.instance;
    }

    private CommandeService() { }

    public List<CommandeParLivraison> listCommandeByIdLivraison(Integer idLivraison) { return commandeDao.listCommandeByIdLivraison(idLivraison); }

    public List<CommandeParUser> listCommandeByUser (Integer idUser) {
        return commandeDao.listCommandeByUser(idUser);
    }

    public void addCommande(Integer idUser, Integer idLivraison){
        commandeDao.addCommande(idUser, idLivraison);
    }

    public void supprimerCommande(Integer idCommande){
        commandeDao.supprimerCommande(idCommande);
    }


}
