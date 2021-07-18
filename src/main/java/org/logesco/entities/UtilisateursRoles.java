/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="utilisateurs_roles", 
uniqueConstraints = {@UniqueConstraint(
	columnNames = {"users_id_users", "role_associe_role"})})/*users_username*/
public class UtilisateursRoles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idUsersRoles;
	
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Utilisateurs
	 ******************************************/
	@ManyToOne
	Utilisateurs users;
	
	/*
	 * Association avec la table Roles
	 ******************************************/
	@ManyToOne
	Roles roleAssocie;

	/**
	 * 
	 */
	public UtilisateursRoles() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param users
	 * @param roleAssocie
	 */
	public UtilisateursRoles(Utilisateurs users, Roles roleAssocie) {
		super();
		this.users = users;
		this.roleAssocie = roleAssocie;
	}

	/**
	 * @return the users
	 */
	public Utilisateurs getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Utilisateurs users) {
		this.users = users;
	}

	/**
	 * @return the roleAssocie
	 */
	public Roles getRoleAssocie() {
		return roleAssocie;
	}

	/**
	 * @param roleAssocie the roleAssocie to set
	 */
	public void setRoleAssocie(Roles roleAssocie) {
		this.roleAssocie = roleAssocie;
	}

	/**
	 * @return the idUsersRoles
	 */
	public Long getIdUsersRoles() {
		return idUsersRoles;
	}

	/**
	 * @param idUsersRoles the idUsersRoles to set
	 */
	public void setIdUsersRoles(Long idUsersRoles) {
		this.idUsersRoles = idUsersRoles;
	}

	
	

}
