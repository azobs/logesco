/**
 * 
 */
package org.logesco.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
public class ConsulterPersonnelsForm {

	@NotEmpty
	private String numcniPers;
	@NotEmpty
	private String username;
	/**
	 * @return the numcniPers
	 */
	public String getNumcniPers() {
		return numcniPers;
	}
	/**
	 * @param numcniPers the numcniPers to set
	 */
	public void setNumcniPers(String numcniPers) {
		this.numcniPers = numcniPers;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
