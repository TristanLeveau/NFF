package services;

import daos.CommandeDao;
import pojos.Commande;
import pojos.User;

import java.util.List;

public class CommandeService {

    CommandeDao commandeDao = new CommandeDao();

    private static class CommandeServiceHolder {
        private static CommandeService instance = new CommandeService();
    }

    public static CommandeService getInstance() {
        return CommandeServiceHolder.instance;
    }

    private CommandeService() { }

    public List<Commande> listCommandeByIdLivraison(Integer idLivraison) {
        return commandeDao.listCommandeByIdLivraison(idLivraison);
    }

}
