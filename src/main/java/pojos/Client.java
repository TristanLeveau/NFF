package pojos;

public class Client {

    private Integer idClient;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;


    public Client(Integer idClient, String nom, String prenom, String email, String motDePasse) {
        super();
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }


    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idParticipant) { this.idClient = idParticipant; }

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
