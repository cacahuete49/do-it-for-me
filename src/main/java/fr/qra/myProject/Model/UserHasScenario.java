/**
 * 
 */
package fr.qra.myProject.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author quentin
 *
 */
@Entity
@Table(name = "user_has_scenario")
@AssociationOverrides({
		@AssociationOverride(name = "userHasScenarioId.scenario", joinColumns = @JoinColumn(name = "scenario_id")),
		@AssociationOverride(name = "userHasScenarioId.user", joinColumns = @JoinColumn(name = "user_id")) })
public class UserHasScenario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2302591026332869446L;

	private UserHasScenarioId userHasScenarioId = new UserHasScenarioId();
	
	private Date createdDate;
	
	private float prixAchat;
	
	private Integer noteUser;

	@EmbeddedId
	public UserHasScenarioId getUserHasScenarioId() {
		return userHasScenarioId;
	}

	public void setUserHasScenarioId(UserHasScenarioId userHasScenarioId) {
		this.userHasScenarioId = userHasScenarioId;
	}

	@Transient
	public User getUser() {
		return userHasScenarioId.getUser();
	}

	public void setUser(User user) {
		userHasScenarioId.setUser(user);
	}

	@Transient
	public Scenario getScenario() {
		return userHasScenarioId.getScenario();
	}

	public void setScenario(Scenario scenario) {
		userHasScenarioId.setScenario(scenario);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_achat", nullable = false, length = 10)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "prix_achat")
	public float getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = prixAchat;
	}

	@Column( name = "user_note")
	public Integer getNoteUser() {
		return noteUser;
	}

	public Integer setNoteUser(Integer noteUser) {
		return this.noteUser = noteUser;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserHasScenario that = (UserHasScenario) o;

		if (getUserHasScenarioId() != null ? !getUserHasScenarioId().equals(
				that.getUserHasScenarioId()) : that.getUserHasScenarioId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getUserHasScenarioId() != null ? getUserHasScenarioId().hashCode() : 0);
	}
}
