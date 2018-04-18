package pojos;

public class Participant {

    private Integer idParticipant;
    private String nomParticipant;
    private String prenomParticipant;
    private String email;
    private String mdpParticipant;
    private Integer administrateur;

    public Participant(Integer idParticipant, String nomParticipant, String prenomParticipant, String email, String mdpParticipant, Integer administrateur) {
        this.idParticipant = idParticipant;
        this.nomParticipant = nomParticipant;
        this.prenomParticipant = prenomParticipant;
        this.email = email;
        this.mdpParticipant = mdpParticipant;
        this.administrateur = administrateur;
    }

    public Integer getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(Integer idParticipant) {
        this.idParticipant = idParticipant;
    }

    public String getNomParticipant() {
        return nomParticipant;
    }

    public void setNomParticipant(String nomParticipant) {
        this.nomParticipant = nomParticipant;
    }

    public String getPrenomParticipant() {
        return prenomParticipant;
    }

    public void setPrenomParticipant(String prenomParticipant) {
        this.prenomParticipant = prenomParticipant;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdpParticipant() {
        return mdpParticipant;
    }

    public void setMdpParticipant(String mdpParticipant) {
        this.mdpParticipant = mdpParticipant;
    }

    public Integer getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Integer administrateur) {
        this.administrateur = administrateur;
    }
}
