/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class PersonnelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String noms_prenoms;
	private String date_naiss;
	private String lieu_naiss;
	private String numcni;
	private String sexe;
	private String nationalite;
	private String grade;
	private String statut;
	private String diplome;
	private String specialite;
	private String fonction;
	private String adresse;
	private String numtel1;
	private String numtel2;
	

	/**
	 * 
	 */
	public PersonnelBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the noms_prenoms
	 */
	public String getNoms_prenoms() {
		return noms_prenoms;
	}

	/**
	 * @param noms_prenoms the noms_prenoms to set
	 */
	public void setNoms_prenoms(String noms_prenoms) {
		this.noms_prenoms = noms_prenoms;
	}

	

	/**
	 * @return the numcni
	 */
	public String getNumcni() {
		return numcni;
	}

	/**
	 * @param numcni the numcni to set
	 */
	public void setNumcni(String numcni) {
		this.numcni = numcni;
	}

	
	/**
	 * @return the fonction
	 */
	public String getFonction() {
		return fonction;
	}

	/**
	 * @param fonction the fonction to set
	 */
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the date_naiss
	 */
	public String getDate_naiss() {
		return date_naiss;
	}

	/**
	 * @param date_naiss the date_naiss to set
	 */
	public void setDate_naiss(String date_naiss) {
		this.date_naiss = date_naiss;
	}

	/**
	 * @return the lieu_naiss
	 */
	public String getLieu_naiss() {
		return lieu_naiss;
	}

	/**
	 * @param lieu_naiss the lieu_naiss to set
	 */
	public void setLieu_naiss(String lieu_naiss) {
		this.lieu_naiss = lieu_naiss;
	}

	/**
	 * @return the sexe
	 */
	public String getSexe() {
		return sexe;
	}

	/**
	 * @param sexe the sexe to set
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	/**
	 * @return the nationalite
	 */
	public String getNationalite() {
		return nationalite;
	}

	/**
	 * @param nationalite the nationalite to set
	 */
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the statut
	 */
	public String getStatut() {
		return statut;
	}

	/**
	 * @param statut the statut to set
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}

	/**
	 * @return the diplome
	 */
	public String getDiplome() {
		return diplome;
	}

	/**
	 * @param diplome the diplome to set
	 */
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	/**
	 * @return the specialite
	 */
	public String getSpecialite() {
		return specialite;
	}

	/**
	 * @param specialite the specialite to set
	 */
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	/**
	 * @return the numtel1
	 */
	public String getNumtel1() {
		return numtel1;
	}

	/**
	 * @param numtel1 the numtel1 to set
	 */
	public void setNumtel1(String numtel1) {
		this.numtel1 = numtel1;
	}

	/**
	 * @return the numtel2
	 */
	public String getNumtel2() {
		return numtel2;
	}

	/**
	 * @param numtel2 the numtel2 to set
	 */
	public void setNumtel2(String numtel2) {
		this.numtel2 = numtel2;
	}


	
	
}
