/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="administrateurs")
public class Administrateurs extends Utilisateurs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Email
	private String emailAdmin;
	@NotNull
	@NotEmpty
	@Size(min = 9, max = 13)
	private String numtel1Admin;
	private String numtel2Admin;

	/**
	 * 
	 */
	public Administrateurs() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param emailAdmin
	 * @param numtel1Admin
	 * @param numtel2Admin
	 */
	public Administrateurs(String emailAdmin, String numtel1Admin, String numtel2Admin, 
			String username, String password, boolean etatCompteUsers) {
		super(username, password, etatCompteUsers);
		this.emailAdmin = emailAdmin;
		this.numtel1Admin = numtel1Admin;
		this.numtel2Admin = numtel2Admin;
	}



	public String getEmailAdmin() {
		return emailAdmin;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

	public String getNumtel1Admin() {
		return numtel1Admin;
	}

	public void setNumtel1Admin(String numtel1Admin) {
		this.numtel1Admin = numtel1Admin;
	}

	public String getNumtel2Admin() {
		return numtel2Admin;
	}

	public void setNumtel2Admin(String numtel2Admin) {
		this.numtel2Admin = numtel2Admin;
	}
	
	

}
