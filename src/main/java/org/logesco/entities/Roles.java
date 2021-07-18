/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="roles")
public class Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String role;
	@NotEmpty
	@Size(min = 1, max = 17)
	private String aliasRole;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table UtilisateursRoles
	 ******************************************/
	@OneToMany(mappedBy="roleAssocie")
	Collection<UtilisateursRoles> listofrolesUsers; 
	
	
	
	/**
	 * 
	 */
	public Roles() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param role
	 */
	public Roles(String role) {
		super();
		this.role = role;
		this.aliasRole = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the aliasRole
	 */
	public String getAliasRole() {
		return aliasRole;
	}

	/**
	 * @param aliasRole the aliasRole to set
	 */
	public void setAliasRole(String aliasRole) {
		this.aliasRole = aliasRole;
	}

	/**
	 * @return the listofrolesUsers
	 */
	public Collection<UtilisateursRoles> getListofrolesUsers() {
		return listofrolesUsers;
	}

	/**
	 * @param listofrolesUsers the listofrolesUsers to set
	 */
	public void setListofrolesUsers(Collection<UtilisateursRoles> listofrolesUsers) {
		this.listofrolesUsers = listofrolesUsers;
	}

	
	
}
