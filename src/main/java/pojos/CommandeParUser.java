package pojos;

public class CommandeParUser {

    private Integer idCommande;
    private Integer idLivraison;
    private String date;
    private String contenu;

    public CommandeParUser(Integer idCommande,Integer idLivraison, String date, String contenu) {
        this.idCommande = idCommande;
        this.idLivraison = idLivraison;
        this.date = date;
        this.contenu = contenu;

    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public Integer getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(Integer idLivraison) {
        this.idLivraison = idLivraison;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
