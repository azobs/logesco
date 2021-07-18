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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="matieres")
public class Matieres implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idMatiere;
	@NotNull
	@NotEmpty
	@Column(unique=true)
	private String codeMatiere;
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 100)
	private String intituleMatiere;
	
	/***
	 * Debut des ajouts du 19-08-2018
	 */
	@NotEmpty
	@Size(min= 2, max= 100)
	private String intitule2langueMatiere;
	/***
	 * Fin des ajouts du 19-08-2018
	 */
	
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Cours
	 ******************************************/
	@OneToMany(mappedBy="matiere")
	Collection<Cours> listofCours;

	/**
	 * 
	 */
	public Matieres() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codeMatiere
	 * @param intituleMatiere
	 */
	public Matieres(String codeMatiere, String intituleMatiere) {
		super();
		this.codeMatiere = codeMatiere;
		this.intituleMatiere = intituleMatiere;
	}

	/**
	 * @return the idMatiere
	 */
	public Long getIdMatiere() {
		return idMatiere;
	}

	/**
	 * @param idMatiere the idMatiere to set
	 */
	public void setIdMatiere(Long idMatiere) {
		this.idMatiere = idMatiere;
	}

	/**
	 * @return the codeMatiere
	 */
	public String getCodeMatiere() {
		return codeMatiere;
	}

	/**
	 * @param codeMatiere the codeMatiere to set
	 */
	public void setCodeMatiere(String codeMatiere) {
		this.codeMatiere = codeMatiere;
	}

	/**
	 * @return the intituleMatiere
	 */
	public String getIntituleMatiere() {
		return intituleMatiere;
	}

	/**
	 * @param intituleMatiere the intituleMatiere to set
	 */
	public void setIntituleMatiere(String intituleMatiere) {
		this.intituleMatiere = intituleMatiere;
	}

	/**
	 * @return the listofCours
	 */
	public Collection<Cours> getListofCours() {
		return listofCours;
	}

	/**
	 * @param listofCours the listofCours to set
	 */
	public void setListofCours(Collection<Cours> listofCours) {
		this.listofCours = listofCours;
	}

	/**
	 * @return the intitule2langueMatiere
	 */
	public String getIntitule2langueMatiere() {
		return intitule2langueMatiere;
	}

	/**
	 * @param intitule2langueMatiere the intitule2langueMatiere to set
	 */
	public void setIntitule2langueMatiere(String intitule2langueMatiere) {
		this.intitule2langueMatiere = intitule2langueMatiere;
	}

	
	
}
