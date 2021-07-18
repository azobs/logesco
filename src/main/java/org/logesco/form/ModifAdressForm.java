/**
 * 
 */
package org.logesco.form;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
public class ModifAdressForm {

	@NotNull
	@NotEmpty
	private String username;
	@NotNull
	@Email
	private String emailAdmin;
	@NotNull
	@NotEmpty
	@Size(min = 9, max = 13)
	private String numtel1Admin;
	private String numtel2Admin;
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
	 * @return the emailAdmin
	 */
	public String getEmailAdmin() {
		return emailAdmin;
	}
	/**
	 * @param emailAdmin the emailAdmin to set
	 */
	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}
	/**
	 * @return the numtel1Admin
	 */
	public String getNumtel1Admin() {
		return numtel1Admin;
	}
	/**
	 * @param numtel1Admin the numtel1Admin to set
	 */
	public void setNumtel1Admin(String numtel1Admin) {
		this.numtel1Admin = numtel1Admin;
	}
	/**
	 * @return the numtel2Admin
	 */
	public String getNumtel2Admin() {
		return numtel2Admin;
	}
	/**
	 * @param numtel2Admin the numtel2Admin to set
	 */
	public void setNumtel2Admin(String numtel2Admin) {
		this.numtel2Admin = numtel2Admin;
	}
	
	
	
}
