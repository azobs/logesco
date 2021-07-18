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
public class UpdateNiveauxForm {

	@NotNull
	private int numeroOrdreNiveaux;
	@NotNull
	@NotEmpty
	private String codeNiveaux;
	@NotNull
	@NotEmpty
	private String codeNiveaux_en;
	@NotNull
	@NotEmpty
	private String codeCycles;
	@NotNull
	@NotEmpty
	private String codeNiveauxSup;
	
	@NotNull
	@NotEmpty
	private String enregOrmodif;
	@NotNull
	@NotEmpty
	private String codeNiveauxAModif;
	
	/**
	 * @return the numeroOrdreNiveaux
	 */
	public int getNumeroOrdreNiveaux() {
		return numeroOrdreNiveaux;
	}
	/**
	 * @param numeroOrdreNiveaux the numeroOrdreNiveaux to set
	 */
	public void setNumeroOrdreNiveaux(int numeroOrdreNiveaux) {
		this.numeroOrdreNiveaux = numeroOrdreNiveaux;
	}
	/**
	 * @return the codeNiveaux
	 */
	public String getCodeNiveaux() {
		return codeNiveaux;
	}
	/**
	 * @param codeNiveaux the codeNiveaux to set
	 */
	public void setCodeNiveaux(String codeNiveaux) {
		this.codeNiveaux = codeNiveaux;
	}
	/**
	 * @return the codeCycles
	 */
	public String getCodeCycles() {
		return codeCycles;
	}
	/**
	 * @param codeCycles the codeCycles to set
	 */
	public void setCodeCycles(String codeCycles) {
		this.codeCycles = codeCycles;
	}
	/**
	 * @return the codeNiveauxSup
	 */
	public String getCodeNiveauxSup() {
		return codeNiveauxSup;
	}
	/**
	 * @param codeNiveauxSup the codeNiveauxSup to set
	 */
	public void setCodeNiveauxSup(String codeNiveauxSup) {
		this.codeNiveauxSup = codeNiveauxSup;
	}
	/**
	 * @return the codeNiveaux_en
	 */
	public String getCodeNiveaux_en() {
		return codeNiveaux_en;
	}
	/**
	 * @param codeNiveaux_en the codeNiveaux_en to set
	 */
	public void setCodeNiveaux_en(String codeNiveaux_en) {
		this.codeNiveaux_en = codeNiveaux_en;
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
	 * @return the codeNiveauxAModif
	 */
	public String getCodeNiveauxAModif() {
		return codeNiveauxAModif;
	}
	/**
	 * @param codeNiveauxAModif the codeNiveauxAModif to set
	 */
	public void setCodeNiveauxAModif(String codeNiveauxAModif) {
		this.codeNiveauxAModif = codeNiveauxAModif;
	}
	
	
	
}
