/**
 * 
 */
package org.logesco.form;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
public class ModifpasswordForm {

	@NotNull
	@NotEmpty
    private String passwordCourant;
	@NotNull
	@NotEmpty
    private String newPassword;
	@NotNull
	@NotEmpty
    private String newPasswordConfirm;
	@NotNull
	@NotEmpty
	private String username;
	/**
	 * @return the passwordCourant
	 */
	public String getPasswordCourant() {
		return passwordCourant;
	}
	/**
	 * @param passwordCourant the passwordCourant to set
	 */
	public void setPasswordCourant(String passwordCourant) {
		this.passwordCourant = passwordCourant;
	}
	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	/**
	 * @return the newPasswordConfirm
	 */
	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}
	/**
	 * @param newPasswordConfirm the newPasswordConfirm to set
	 */
	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
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
