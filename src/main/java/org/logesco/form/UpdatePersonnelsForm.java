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
public class UpdatePersonnelsForm {
	/*
	 * Identifiant du personnel en cas de modification de ses données
	 */
	private Long idPersonnels;//
	private String numcniOuUsernamePersAModif;//
	
	/*
	 * Données personnelles
	 */
	@NotNull
	@NotEmpty
	@Size(min = 9, max = 17)
	private String numcniPers;//
	@NotNull
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datenaissPers; //
	@NotNull
	@Size(min = 2, max = 20)
	private String diplomePers;//
	@Email
	private String emailPers;//
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 7)
	private String gradePers;//
	@NotNull
	@NotEmpty
	@Size(min = 2, max = 20)
	private String lieunaissPers;//
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 20)
	private String nationalitePers;//
	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50)
	private String nomsPers;//
	@NotNull
	@NotEmpty
	@Size(min = 9, max = 13)
	private String numtel1Pers;//
	private String numtel2Pers;//
	private String photoPers;
	@Size(min = 2, max = 50)
	private String prenomsPers;//
	@Size(min = 2, max = 20)
	private String quartierPers;//
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 8)
	private String sexePers;//
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 13)
	private String statutPers;//ECI ou Vacataire ou alors Fonctionnaire//
	@Size(min = 2, max = 20)
	private String villePers;//
	
	/*
	 * Données utilisateurs
	 */
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 20)
	private String username;//
	@NotNull
	@NotEmpty
	private String password;//
	
	/*
	 * Données specifique à la fonction
	 */
	@NotNull
	@NotEmpty
	@Size(min = 2, max = 100)
	private String specialiteProf;//
	
	/*
	 * Numero au sein de la fonction
	 */
	
	private int numeroPers;//
	
	/*
	 * Roles vis à vis du système
	 */
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
	private int roleCode;//
	
	private int roleCodeAModif;//
	
	/*paramètre qui seront utilisé pour réaffichage de getConsulterPersonnel afin que l'user reste sur les pages qu'il
	consultait*/
	
	 int numPageCenseur;
	 int numPageSg;
	 int numPageIntendant;
	 int numPageEns;
	/**
	 * @return the idPersonnels
	 */
	public Long getIdPersonnels() {
		return idPersonnels;
	}

	/**
	 * @param idPersonnels the idPersonnels to set
	 */
	public void setIdPersonnels(Long idPersonnels) {
		this.idPersonnels = idPersonnels;
	}

	/**
	 * @return the numcniPers
	 */
	public String getNumcniPers() {
		return numcniPers;
	}

	/**
	 * @param numcniPers the numcniPers to set
	 */
	public void setNumcniPers(String numcniPers) {
		this.numcniPers = numcniPers;
	}

	/**
	 * @return the datenaissPers
	 */
	public Date getDatenaissPers() {
		return datenaissPers;
	}

	/**
	 * @param datenaissPers the datenaissPers to set
	 */
	public void setDatenaissPers(Date datenaissPers) {
		this.datenaissPers = datenaissPers;
	}

	/**
	 * @return the diplomePers
	 */
	public String getDiplomePers() {
		return diplomePers;
	}

	/**
	 * @param diplomePers the diplomePers to set
	 */
	public void setDiplomePers(String diplomePers) {
		this.diplomePers = diplomePers;
	}

	/**
	 * @return the emailPers
	 */
	public String getEmailPers() {
		return emailPers;
	}

	/**
	 * @param emailPers the emailPers to set
	 */
	public void setEmailPers(String emailPers) {
		this.emailPers = emailPers;
	}

	/**
	 * @return the gradePers
	 */
	public String getGradePers() {
		return gradePers;
	}

	/**
	 * @param gradePers the gradePers to set
	 */
	public void setGradePers(String gradePers) {
		this.gradePers = gradePers;
	}

	/**
	 * @return the lieunaissPers
	 */
	public String getLieunaissPers() {
		return lieunaissPers;
	}

	/**
	 * @param lieunaissPers the lieunaissPers to set
	 */
	public void setLieunaissPers(String lieunaissPers) {
		this.lieunaissPers = lieunaissPers;
	}

	/**
	 * @return the nationalitePers
	 */
	public String getNationalitePers() {
		return nationalitePers;
	}

	/**
	 * @param nationalitePers the nationalitePers to set
	 */
	public void setNationalitePers(String nationalitePers) {
		this.nationalitePers = nationalitePers;
	}

	/**
	 * @return the nomsPers
	 */
	public String getNomsPers() {
		return nomsPers;
	}

	/**
	 * @param nomsPers the nomsPers to set
	 */
	public void setNomsPers(String nomsPers) {
		this.nomsPers = nomsPers;
	}

	/**
	 * @return the numtel1Pers
	 */
	public String getNumtel1Pers() {
		return numtel1Pers;
	}

	/**
	 * @param numtel1Pers the numtel1Pers to set
	 */
	public void setNumtel1Pers(String numtel1Pers) {
		this.numtel1Pers = numtel1Pers;
	}

	/**
	 * @return the numtel2Pers
	 */
	public String getNumtel2Pers() {
		return numtel2Pers;
	}

	/**
	 * @param numtel2Pers the numtel2Pers to set
	 */
	public void setNumtel2Pers(String numtel2Pers) {
		this.numtel2Pers = numtel2Pers;
	}

	/**
	 * @return the prenomsPers
	 */
	public String getPrenomsPers() {
		return prenomsPers;
	}

	/**
	 * @param prenomsPers the prenomsPers to set
	 */
	public void setPrenomsPers(String prenomsPers) {
		this.prenomsPers = prenomsPers;
	}

	/**
	 * @return the quartierPers
	 */
	public String getQuartierPers() {
		return quartierPers;
	}

	/**
	 * @param quartierPers the quartierPers to set
	 */
	public void setQuartierPers(String quartierPers) {
		this.quartierPers = quartierPers;
	}

	/**
	 * @return the sexePers
	 */
	public String getSexePers() {
		return sexePers;
	}

	/**
	 * @param sexePers the sexePers to set
	 */
	public void setSexePers(String sexePers) {
		this.sexePers = sexePers;
	}

	/**
	 * @return the statutPers
	 */
	public String getStatutPers() {
		return statutPers;
	}

	/**
	 * @param statutPers the statutPers to set
	 */
	public void setStatutPers(String statutPers) {
		this.statutPers = statutPers;
	}

	/**
	 * @return the villePers
	 */
	public String getVillePers() {
		return villePers;
	}

	/**
	 * @param villePers the villePers to set
	 */
	public void setVillePers(String villePers) {
		this.villePers = villePers;
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

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the specialiteProf
	 */
	public String getSpecialiteProf() {
		return specialiteProf;
	}

	/**
	 * @param specialiteProf the specialiteProf to set
	 */
	public void setSpecialiteProf(String specialiteProf) {
		this.specialiteProf = specialiteProf;
	}

	/**
	 * @return the numeroInt
	 */
	public int getNumeroPers() {
		return numeroPers;
	}

	/**
	 * @param numeroInt the numeroInt to set
	 */
	public void setNumeroPers(int numeroPers) {
		this.numeroPers = numeroPers;
	}

	/**
	 * @return the roleCode
	 */
	public int getRoleCode() {
		return roleCode;
	}

	/**
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(int roleCode) {
		this.roleCode = roleCode;
	}

	
	

	/**
	 * @return the numcniOuUsernamePersAModif
	 */
	public String getNumcniOuUsernamePersAModif() {
		return numcniOuUsernamePersAModif;
	}

	/**
	 * @param numcniOuUsernamePersAModif the numcniOuUsernamePersAModif to set
	 */
	public void setNumcniOuUsernamePersAModif(String numcniOuUsernamePersAModif) {
		this.numcniOuUsernamePersAModif = numcniOuUsernamePersAModif;
	}

	
	
	/**
	 * @return the photoPers
	 */
	public String getPhotoPers() {
		return photoPers;
	}

	/**
	 * @param photoPers the photoPers to set
	 */
	public void setPhotoPers(String photoPers) {
		this.photoPers = photoPers;
	}
	

	/**
	 * @return the roleCodeAModif
	 */
	public int getRoleCodeAModif() {
		return roleCodeAModif;
	}

	/**
	 * @param roleCodeAModif the roleCodeAModif to set
	 */
	public void setRoleCodeAModif(int roleCodeAModif) {
		this.roleCodeAModif = roleCodeAModif;
	}

	/**
	 * @return the numPageCenseur
	 */
	public int getNumPageCenseur() {
		return numPageCenseur;
	}

	/**
	 * @param numPageCenseur the numPageCenseur to set
	 */
	public void setNumPageCenseur(int numPageCenseur) {
		this.numPageCenseur = numPageCenseur;
	}

	/**
	 * @return the numPageSg
	 */
	public int getNumPageSg() {
		return numPageSg;
	}

	/**
	 * @param numPageSg the numPageSg to set
	 */
	public void setNumPageSg(int numPageSg) {
		this.numPageSg = numPageSg;
	}

	/**
	 * @return the numPageIntendant
	 */
	public int getNumPageIntendant() {
		return numPageIntendant;
	}

	/**
	 * @param numPageIntendant the numPageIntendant to set
	 */
	public void setNumPageIntendant(int numPageIntendant) {
		this.numPageIntendant = numPageIntendant;
	}

	/**
	 * @return the numPageEns
	 */
	public int getNumPageEns() {
		return numPageEns;
	}

	/**
	 * @param numPageEns the numPageEns to set
	 */
	public void setNumPageEns(int numPageEns) {
		this.numPageEns = numPageEns;
	}

	
	
	
	
	
	
	
}
