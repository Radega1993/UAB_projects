package nucli;

import java.io.Serializable;


public class Informacio implements Serializable {

	private int id;

	public int getId() {
		return id;
	}

	private void setId(int id) {
	// private perque nomes el constructor pot assignar l'id, per
	// garantir que sigui unic
		this.id = id;
	}

	private String nom = "";

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	private String descripcio = "";

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public Informacio(String nom, String descr) {
		setId(GeneradorIds.Instance().getId());
		setNom(nom);
		setDescripcio(descr);
	}

}
