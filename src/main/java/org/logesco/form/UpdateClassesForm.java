/**
 * 
 */
package org.logesco.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
public class UpdateClassesForm {
	
	private String codeClasseAModif;
	private String numeroClasseAModif;
	private Long idSpecialiteAModif;
	
	@NotNull
	@NotEmpty
	@Size(min= 1, max= 7)
	private String codeClasse;
	@NotNull
	private String numeroClasse;
	@NotNull
	@NotEmpty
	private String codeSpecialiteClasse;
	@NotNull
	private int numeroOrdreNiveauClasse;
	@NotNull
	@NotEmpty
	private String codeSectionClasse;
	@NotNull
	@NotEmpty
	private String enregOrmodif;
	
	
	/****
	 * Debut des Ajouts du 19-08-2018
	 */
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 3)
	private String langueClasses;
	
	/**
	 * @return the soussystemeClasses
	 */
	public String getLangueClasses() {
		return langueClasses;
	}
	/**
	 * @param soussystemeClasses the soussystemeClasses to set
	 */
	public void setLangueClasses(String soussystemeClasses) {
		this.langueClasses = soussystemeClasses;
	}
	
	/****
	 * fin des Ajouts du 19-08-2018
	 */
	
	
	/**
	 * @return the codeClasseAModif
	 */
	public String getCodeClasseAModif() {
		return codeClasseAModif;
	}
	/**
	 * @param codeClasseAModif the codeClasseAModif to set
	 */
	public void setCodeClasseAModif(String codeClasseAModif) {
		this.codeClasseAModif = codeClasseAModif;
	}
	/**
	 * @return the numeroClasseAModif
	 */
	public String getNumeroClasseAModif() {
		return numeroClasseAModif;
	}
	/**
	 * @param numeroClasseAModif the numeroClasseAModif to set
	 */
	public void setNumeroClasseAModif(String numeroClasseAModif) {
		this.numeroClasseAModif = numeroClasseAModif;
	}
	/**
	 * @return the codeClasse
	 */
	public String getCodeClasse() {
		return codeClasse;
	}
	/**
	 * @param codeClasse the codeClasse to set
	 */
	public void setCodeClasse(String codeClasse) {
		this.codeClasse = codeClasse;
	}
	/**
	 * @return the numeroClasse
	 */
	public String getNumeroClasse() {
		return numeroClasse;
	}
	/**
	 * @param numeroClasse the numeroClasse to set
	 */
	public void setNumeroClasse(String numeroClasse) {
		this.numeroClasse = numeroClasse;
	}
	/**
	 * @return the codeSpecialiteClasse
	 */
	public String getCodeSpecialiteClasse() {
		return codeSpecialiteClasse;
	}
	/**
	 * @param codeSpecialiteClasse the codeSpecialiteClasse to set
	 */
	public void setCodeSpecialiteClasse(String codeSpecialiteClasse) {
		this.codeSpecialiteClasse = codeSpecialiteClasse;
	}
	
	/**
	 * @return the numeroOrdreNiveauClasse
	 */
	public int getNumeroOrdreNiveauClasse() {
		return numeroOrdreNiveauClasse;
	}
	/**
	 * @param numeroOrdreNiveauClasse the numeroOrdreNiveauClasse to set
	 */
	public void setNumeroOrdreNiveauClasse(int numeroOrdreNiveauClasse) {
		this.numeroOrdreNiveauClasse = numeroOrdreNiveauClasse;
	}
	/**
	 * @return the codeSectionClasse
	 */
	public String getCodeSectionClasse() {
		return codeSectionClasse;
	}
	/**
	 * @param codeSectionClasse the codeSectionClasse to set
	 */
	public void setCodeSectionClasse(String codeSectionClasse) {
		this.codeSectionClasse = codeSectionClasse;
	}
	/**
	 * @return the enregOrmodif
	 */
	public String getEnregOrmodif() {
		return enregOrmodif;
	}
	/**
	 * @param enregOrmodif the enregOrmodif to set
	 */
	public void setEnregOrmodif(String enregOrmodif) {
		this.enregOrmodif = enregOrmodif;
	}
	/**
	 * @return the idSpecialiteAModif
	 */
	public Long getIdSpecialiteAModif() {
		return idSpecialiteAModif;
	}
	/**
	 * @param idSpecialiteAModif the idSpecialiteAModif to set
	 */
	public void setIdSpecialiteAModif(Long idSpecialiteAModif) {
		this.idSpecialiteAModif = idSpecialiteAModif;
	}
	
	
	
}
