package pojos;

public class ParticipantALivrer {

    private Integer idParticipantALivrer;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;


    public ParticipantALivrer(Integer idParticipantALivrer, String nom, String prenom, String email, String motDePasse) {
        this.idParticipantALivrer = idParticipantALivrer;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }


    public Integer getIdParticipantALivrer() {
        return idParticipantALivrer;
    }

    public void setIdParticipantALivrer(Integer idParticipantALivrer) {
        this.idParticipantALivrer = idParticipantALivrer;
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

    public String getEmail(){return email;}

    public void setEmail(String email){this.email=email;}

    public String getMotDePasse(){return motDePasse;}

    public void setMotDePasse(String motDePasse){this.email=motDePasse;}

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }






}
