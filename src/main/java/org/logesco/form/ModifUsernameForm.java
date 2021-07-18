/**
 * 
 */
package org.logesco.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
public class ModifUsernameForm {

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 20)
	private String activeUsername;
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 20)
	private String newUsername;
	
	
	/**
	 * @return the activeUsername
	 */
	public String getActiveUsername() {
		return activeUsername;
	}
	/**
	 * @param activeUsername the activeUsername to set
	 */
	public void setActiveUsername(String activeUsername) {
		this.activeUsername = activeUsername;
	}
	/**
	 * @return the newUsername
	 */
	public String getNewUsername() {
		return newUsername;
	}
	/**
	 * @param newUsername the newUsername to set
	 */
	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}
	
	
	
}
