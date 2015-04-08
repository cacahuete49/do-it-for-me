package fr.qra.myProject.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "scenario")
public class Scenario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4423153369546211433L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	private String xmlFlux;
	private String description;
	private float prix;
	private int note;
	private int nbTelechargement;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userHasScenarioId.scenario")
	private List<UserHasScenario> listeUser = new ArrayList<UserHasScenario>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getXmlFlux() {
		return xmlFlux;
	}

	public void setXmlFlux(String xmlFlux) {
		this.xmlFlux = xmlFlux;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public int getNbTelechargement() {
		return nbTelechargement;
	}

	public void setNbTelechargement(int nbTelechargement) {
		this.nbTelechargement = nbTelechargement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserHasScenario> getListeUser() {
		return listeUser;
	}

	public void setListeUser(List<UserHasScenario> listeUser) {
		this.listeUser = listeUser;
	}
}
