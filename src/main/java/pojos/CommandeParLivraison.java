package pojos;

import java.util.List;

public class CommandeParLivraison {

    private Integer idCommande;
    private Integer idUser;
    private String nom;
    private String prenom;
    private String email;

    public CommandeParLivraison(Integer idCommande,Integer idUser, String nom, String prenom, String email) {
        this.idCommande = idCommande;
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;

    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
