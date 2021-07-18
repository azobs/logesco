/**
 * 
 */
package org.logesco.form;

import java.util.Date;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author cedrickiadjeu
 *
 */
public class UpdateElevesForm {
	/*
	 * Identifiant de l'élève en cas de modification de ses données
	 */
	private Long idEleves;
	
	@NotNull
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datenaissEleves;//
	@Email
	private String emailParent;//
	@Size(min= 7, max= 11)
	private String etatInscEleves;//inscrit non_inscrit
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 20)
	private String lieunaissEleves;//
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 20)
	private String nationaliteEleves;//
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 50)
	private String nomsEleves;//
	@NotNull
	@NotEmpty
	@Size(min= 9, max= 13)
	private String numtel1Parent;//
	private String photoEleves;//
	@Size(max= 50)
	private String prenomsEleves;//
	@Size(max= 20)
	private String quartierEleves;//
	@Size(min= 3, max= 3)
	private String redoublant;//oui non
	@NotNull
	@NotEmpty
	@Size(min= 1, max= 20)
	private String sexeEleves;//
	@Size(min= 5, max= 7)
	private String statutEleves;//ancien nouveau exclu
	@Size(min= 2, max= 20)
	private String villeEleves;//
	private String matriculeEleves;//
	
	/*
	 * identifiant de la classe d'appartenance
	 */
	private Long idClasse;//
	
	/*
	 * identifiant de la classe concerne par la recherche de l'élève à modifier
	 */
	private Long idClasseConcerne;

	/**
	 * @return the idEleves
	 */
	public Long getIdEleves() {
		return idEleves;
	}

	/**
	 * @param idEleves the idEleves to set
	 */
	public void setIdEleves(Long idEleves) {
		this.idEleves = idEleves;
	}

	/**
	 * @return the datenaissEleves
	 */
	public Date getDatenaissEleves() {
		return datenaissEleves;
	}

	/**
	 * @param datenaissEleves the datenaissEleves to set
	 */
	public void setDatenaissEleves(Date datenaissEleves) {
		this.datenaissEleves = datenaissEleves;
	}

	/**
	 * @return the emailParent
	 */
	public String getEmailParent() {
		return emailParent;
	}

	/**
	 * @param emailParent the emailParent to set
	 */
	public void setEmailParent(String emailParent) {
		this.emailParent = emailParent;
	}

	
	/**
	 * @return the etatInscEleves
	 */
	public String getEtatInscEleves() {
		return etatInscEleves;
	}

	/**
	 * @param etatInscEleves the etatInscEleves to set
	 */
	public void setEtatInscEleves(String etatInscEleves) {
		this.etatInscEleves = etatInscEleves;
	}

	/**
	 * @return the lieunaissEleves
	 */
	public String getLieunaissEleves() {
		return lieunaissEleves;
	}

	/**
	 * @param lieunaissEleves the lieunaissEleves to set
	 */
	public void setLieunaissEleves(String lieunaissEleves) {
		this.lieunaissEleves = lieunaissEleves;
	}

	/**
	 * @return the nationaliteEleves
	 */
	public String getNationaliteEleves() {
		return nationaliteEleves;
	}

	/**
	 * @param nationaliteEleves the nationaliteEleves to set
	 */
	public void setNationaliteEleves(String nationaliteEleves) {
		this.nationaliteEleves = nationaliteEleves;
	}

	/**
	 * @return the nomsEleves
	 */
	public String getNomsEleves() {
		return nomsEleves;
	}

	/**
	 * @param nomsEleves the nomsEleves to set
	 */
	public void setNomsEleves(String nomsEleves) {
		this.nomsEleves = nomsEleves;
	}



	

	/**
	 * @return the numtel1Parent
	 */
	public String getNumtel1Parent() {
		return numtel1Parent;
	}

	/**
	 * @param numtel1Parent the numtel1Parent to set
	 */
	public void setNumtel1Parent(String numtel1Parent) {
		this.numtel1Parent = numtel1Parent;
	}

	/**
	 * @return the photoEleves
	 */
	public String getPhotoEleves() {
		return photoEleves;
	}

	/**
	 * @param photoEleves the photoEleves to set
	 */
	public void setPhotoEleves(String photoEleves) {
		this.photoEleves = photoEleves;
	}

	/**
	 * @return the prenomsEleves
	 */
	public String getPrenomsEleves() {
		return prenomsEleves;
	}

	/**
	 * @param prenomsEleves the prenomsEleves to set
	 */
	public void setPrenomsEleves(String prenomsEleves) {
		this.prenomsEleves = prenomsEleves;
	}

	/**
	 * @return the quartierEleves
	 */
	public String getQuartierEleves() {
		return quartierEleves;
	}

	/**
	 * @param quartierEleves the quartierEleves to set
	 */
	public void setQuartierEleves(String quartierEleves) {
		this.quartierEleves = quartierEleves;
	}

	/**
	 * @return the redoublant
	 */
	public String getRedoublant() {
		return redoublant;
	}

	/**
	 * @param redoublant the redoublant to set
	 */
	public void setRedoublant(String redoublant) {
		this.redoublant = redoublant;
	}

	/**
	 * @return the sexeEleves
	 */
	public String getSexeEleves() {
		return sexeEleves;
	}

	/**
	 * @param sexeEleves the sexeEleves to set
	 */
	public void setSexeEleves(String sexeEleves) {
		this.sexeEleves = sexeEleves;
	}

	/**
	 * @return the statutEleves
	 */
	public String getStatutEleves() {
		return statutEleves;
	}

	/**
	 * @param statutEleves the statutEleves to set
	 */
	public void setStatutEleves(String statutEleves) {
		this.statutEleves = statutEleves;
	}

	/**
	 * @return the villeEleves
	 */
	public String getVilleEleves() {
		return villeEleves;
	}

	/**
	 * @param villeEleves the villeEleves to set
	 */
	public void setVilleEleves(String villeEleves) {
		this.villeEleves = villeEleves;
	}

	/**
	 * @return the matriculeEleves
	 */
	public String getMatriculeEleves() {
		return matriculeEleves;
	}

	/**
	 * @param matriculeEleves the matriculeEleves to set
	 */
	public void setMatriculeEleves(String matriculeEleves) {
		this.matriculeEleves = matriculeEleves;
	}

	/**
	 * @return the idClasse
	 */
	public Long getIdClasse() {
		return idClasse;
	}

	/**
	 * @param idClasse the idClasse to set
	 */
	public void setIdClasse(Long idClasse) {
		this.idClasse = idClasse;
	}
	
	

	/**
	 * @return the idClasseConcerne
	 */
	public Long getIdClasseConcerne() {
		return idClasseConcerne;
	}

	/**
	 * @param idClasseConcerne the idClasseConcerne to set
	 */
	public void setIdClasseConcerne(Long idClasseConcerne) {
		this.idClasseConcerne = idClasseConcerne;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UpdateElevesForm [idEleves=" + idEleves + ", datenaissEleves=" + datenaissEleves + ", emailParent="
				+ emailParent + ", etatInscEleves=" + etatInscEleves + ", lieunaissEleves=" + lieunaissEleves
				+ ", nationaliteEleves=" + nationaliteEleves + ", nomsEleves=" + nomsEleves + ", numtel1Parent="
				+ numtel1Parent + ", photoEleves=" + photoEleves + ", prenomsEleves=" + prenomsEleves
				+ ", quartierEleves=" + quartierEleves + ", redoublant=" + redoublant + ", sexeEleves=" + sexeEleves
				+ ", statutEleves=" + statutEleves + ", villeEleves=" + villeEleves + ", matriculeEleves="
				+ matriculeEleves + ", idClasse=" + idClasse + "]";
	}

	

	
	
}
