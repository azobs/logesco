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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="specialites")
public class Specialites implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idSpecialite;
	@NotNull
	@NotEmpty
	@Column(unique=true)
	private String codeSpecialite;
	@NotNull
	@NotEmpty
	private String libelleSpecialite;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Classes
	 ******************************************/
	@OneToMany(mappedBy="specialite")
	Collection<Classes> listofClasses;

	/**
	 * 
	 */
	public Specialites() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codeSpecialite
	 * @param libelleSpecialite
	 */
	public Specialites(String codeSpecialite, String libelleSpecialite) {
		super();
		this.codeSpecialite = codeSpecialite;
		this.libelleSpecialite = libelleSpecialite;
	}

	/**
	 * @return the idSpecialite
	 */
	public Long getIdSpecialite() {
		return idSpecialite;
	}

	/**
	 * @param idSpecialite the idSpecialite to set
	 */
	public void setIdSpecialite(Long idSpecialite) {
		this.idSpecialite = idSpecialite;
	}

	/**
	 * @return the codeSpecialite
	 */
	public String getCodeSpecialite() {
		return codeSpecialite;
	}

	/**
	 * @param codeSpecialite the codeSpecialite to set
	 */
	public void setCodeSpecialite(String codeSpecialite) {
		this.codeSpecialite = codeSpecialite;
	}

	/**
	 * @return the libelleSpecialite
	 */
	public String getLibelleSpecialite() {
		return libelleSpecialite;
	}

	/**
	 * @param libelleSpecialite the libelleSpecialite to set
	 */
	public void setLibelleSpecialite(String libelleSpecialite) {
		this.libelleSpecialite = libelleSpecialite;
	}

	/**
	 * @return the listofClasses
	 */
	public Collection<Classes> getListofClasses() {
		return listofClasses;
	}

	/**
	 * @param listofClasses the listofClasses to set
	 */
	public void setListofClasses(Collection<Classes> listofClasses) {
		this.listofClasses = listofClasses;
	}
	
	

}
