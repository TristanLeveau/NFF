package pojos;


public class Livraison {

	private Integer id;
	private String date;
	private String contenu;

	public Livraison(Integer id, String date, String contenu) {
		super();
		this.id = id;
		this.date = date;
		this.contenu = contenu;

	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

