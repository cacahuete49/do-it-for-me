/**
 * 
 */
package fr.qra.myProject.Model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * @author quentin
 *
 */
@Embeddable
public class UserHasScenarioId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7507378652058471135L;

	
	private User user;
	
	private Scenario scenario;

	@ManyToOne
	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserHasScenarioId that = ( UserHasScenarioId ) o;
		
		if (scenario != null ? scenario.equals(that.getScenario()) : that.getScenario() != null)
			return false;
		if (user != null ? user.equals(that.getUser()) : that.getUser() != null)
			return false;
		return true;
	
	}
	
	 public int hashCode() {
	        int result;
	        result = (user != null ? user.hashCode() : 0);
	        result = 31 * result + (scenario != null ? scenario.hashCode() : 0);
	        return result;
	 }
}
