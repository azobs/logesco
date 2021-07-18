/**
 * 
 */
package org.logesco.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
public class UpdateSpecialitesForm {

	
	private String codeSpeAModif;
	@NotNull
	@NotEmpty
	private String codeSpecialite;
	@NotNull
	@NotEmpty
	private String libelleSpecialite;
	@NotNull
	@NotEmpty
	private String enregOrmodif;
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
	 * @return the codeSpeAModif
	 */
	public String getCodeSpeAModif() {
		return codeSpeAModif;
	}
	/**
	 * @param codeSpeAModif the codeSpeAModif to set
	 */
	public void setCodeSpeAModif(String codeSpeAModif) {
		this.codeSpeAModif = codeSpeAModif;
	}
	
	
	
}
