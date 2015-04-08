package fr.qra.myProject.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 368875986784459499L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column
	@Size(min = 0, max = 50)
	private String nom;
	
	@Column
	@Size(min = 0, max = 50)
	private String prenom;

	@Column
	@NotNull
	@Size(min = 0, max = 50)
	private String password;
	
	@Column
	@Size(min = 0, max = 50)
	private String role = "user";

	@NotNull
	@Size(min = 0, max = 50)
	@Transient
	private String confirmPassword;

	@Column
	@NotNull
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userHasScenarioId.user", cascade=CascadeType.ALL)
	private List<UserHasScenario> mesScenarii = new ArrayList<UserHasScenario>();
	
	public User() {
	}

	public User(long id, String nom, String prenom, String email, String role,
			List<UserHasScenario> mesScenario) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.role = role;
		this.mesScenarii = mesScenario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public List<UserHasScenario> getMesScenarii() {
		return mesScenarii;
	}

	public void setMesScenarii(List<UserHasScenario> mesScenarii) {
		this.mesScenarii = mesScenarii;
	}

	@Override
	public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom +
                ", prenom='" + prenom +
                ", email=" + email +
                '}';
	}
}
