package pojos;

// Création de l'objet utilisateur

public class User {


        private Integer idUser;

        private String nom;
        private String prenom;
        private String email;
        private String motDePasse;


        public User(Integer idUser, String nom, String prenom, String email, String motDePasse) {
            super();
            this.idUser = idUser;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.motDePasse = motDePasse;
        }


        public Integer getIdUser() {
            return idUser;
        }

        public void setIdUser(Integer idUser) { this.idUser = idUser; }

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


