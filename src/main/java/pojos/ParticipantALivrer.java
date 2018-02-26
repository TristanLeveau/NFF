package pojos;

public class ParticipantALivrer {

    private Integer idInstance;
    private Integer idLivraison;
    private Integer idParticipant;

    private String nom;
    private String prenom;
    private String date;


    public ParticipantALivrer(Integer idInstance, Integer idLivraison, Integer idParticipant, String nom, String prenom, String date) {
        this.idInstance = idInstance;
        this.idLivraison = idLivraison;
        this.idParticipant = idParticipant;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
    }


    public Integer getIdInstance() {
        return idInstance;
    }



    public void setIdInstance(Integer idInstance) {
        this.idInstance = idInstance;
    }

    public Integer getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(Integer idLivraison) {
        this.idLivraison = idLivraison;
    }

    public Integer getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(Integer idParticipant) {
        this.idParticipant = idParticipant;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }




}
