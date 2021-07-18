/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="utilisateurs")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Utilisateurs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*@Id
	@Size(min = 3, max = 20) 
	private String username;*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idUsers;
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 20)
	@Column(unique=true)
	private String username;
	@NotNull
	@NotEmpty
	private String password;
	@NotNull
	private boolean enabled;
	
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table UtilisateursRoles
	 ******************************************/
	@OneToMany(mappedBy="users")
	Collection<UtilisateursRoles> listofusersRoles; 
	 
	
	/**
	 *   
	 */
	public Utilisateurs() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param username
	 * @param password
	 * @param etatCompteUsers
	 */
	public Utilisateurs(String username, String password, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * @return the idUsers
	 */
	public Long getIdUsers() {
		return idUsers;
	}
	/**
	 * @param idUsers the idUsers to set
	 */
	public void setIdUsers(Long idUsers) {
		this.idUsers = idUsers;
	}
	
	
	/**
	 * @return the listofusersRoles
	 */
	public Collection<UtilisateursRoles> getListofusersRoles() {
		return listofusersRoles;
	}
	/**
	 * @param listofusersRoles the listofusersRoles to set
	 */
	public void setListofusersRoles(Collection<UtilisateursRoles> listofusersRoles) {
		this.listofusersRoles = listofusersRoles;
	}
	
	
	
	

}
