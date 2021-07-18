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
public class UpdateFonctionPersonnelsForm {

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 20)
	private String username;
	/*****
	 * DIFFERENTES COMBINAISONS DE ROLES POSSIBLES ET LEUR NUMERO ASSOCIE
	 * 1==Censeur et enseignant
	 * 2==Censeur uniquement
	 * 3==Surveillant général et enseignant
	 * 4==Surveillant général uniquement
	 * 5==Intendant et enseignant
	 * 6==Intendant uniquement
	 * 7==Enseignant uniquement
	 *******/
	@NotNull
	private int newroleCode;
	/*****
	 * DIFFERENTES COMBINAISONS DE ROLES POSSIBLES ET LEUR NUMERO ASSOCIE
	 * 1==Censeur et enseignant
	 * 2==Censeur uniquement
	 * 3==Surveillant général et enseignant
	 * 4==Surveillant général uniquement
	 * 5==Intendant et enseignant
	 * 6==Intendant uniquement
	 * 7==Enseignant uniquement
	 *******/
	@NotNull
	private int roleCodeCourant;//
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
	/**
	 * @return the newroleCode
	 */
	public int getNewroleCode() {
		return newroleCode;
	}
	/**
	 * @param newroleCode the newroleCode to set
	 */
	public void setNewroleCode(int newroleCode) {
		this.newroleCode = newroleCode;
	}
	/**
	 * @return the roleCodeCourant
	 */
	public int getRoleCodeCourant() {
		return roleCodeCourant;
	}
	/**
	 * @param roleCodeCourant the roleCodeCourant to set
	 */
	public void setRoleCodeCourant(int roleCodeCourant) {
		this.roleCodeCourant = roleCodeCourant;
	}
	
	
	
	
}
