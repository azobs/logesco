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
public class UpdateSectionsForm {
	@NotNull
	@NotEmpty
	private String codeSections;
	@NotNull
	@NotEmpty
	private String intituleSections;
	
	@NotNull
	@NotEmpty
	private String codeSectionsAModif;
	
	@NotNull
	@NotEmpty
	private String codeSections_en;
	@NotNull
	@NotEmpty
	private String intituleSections_en;
	@NotNull
	@NotEmpty
	private String enregOrmodif;
	
	/**
	 * @return the codeSections
	 */
	public String getCodeSections() {
		return codeSections;
	}
	/**
	 * @param codeSections the codeSections to set
	 */
	public void setCodeSections(String codeSections) {
		this.codeSections = codeSections;
	}
	/**
	 * @return the intituleSections
	 */
	public String getIntituleSections() {
		return intituleSections;
	}
	/**
	 * @param intituleSections the intituleSections to set
	 */
	public void setIntituleSections(String intituleSections) {
		this.intituleSections = intituleSections;
	}
	
	/**
	 * @return the codeSectionsAModif
	 */
	public String getCodeSectionsAModif() {
		return codeSectionsAModif;
	}
	/**
	 * @param codeSectionsAModif the codeSectionsAModif to set
	 */
	public void setCodeSectionsAModif(String codeSectionsAModif) {
		this.codeSectionsAModif = codeSectionsAModif;
	}
	/**
	 * @return the codeSections_en
	 */
	public String getCodeSections_en() {
		return codeSections_en;
	}
	/**
	 * @param codeSections_en the codeSections_en to set
	 */
	public void setCodeSections_en(String codeSections_en) {
		this.codeSections_en = codeSections_en;
	}
	/**
	 * @return the intituleSections_en
	 */
	public String getIntituleSections_en() {
		return intituleSections_en;
	}
	/**
	 * @param intituleSections_en the intituleSections_en to set
	 */
	public void setIntituleSections_en(String intituleSections_en) {
		this.intituleSections_en = intituleSections_en;
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
	
	
	
}
