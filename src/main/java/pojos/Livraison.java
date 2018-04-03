package pojos;


public class Livraison {

	private Integer idLivraison;
	private String date;
	private String contenu;

	public Livraison(Integer idLivraison, String date, String contenu) {
		super();
		this.idLivraison = idLivraison;
		this.date = date;
		this.contenu = contenu;

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

