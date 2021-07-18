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
public class UpdateCyclesForm {

	@NotNull
	@NotEmpty
	private String codeCyclesAModif;
	
	@NotNull
	@NotEmpty
	private String codeCycles;
	
	@NotNull
	private int numeroOrdreCycles;
	
	@NotNull
	private String codeCycles_en;
	
	@NotNull
	@NotEmpty
	private String enregOrmodif;

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
	 * @return the numeroOrdreCycles
	 */
	public int getNumeroOrdreCycles() {
		return numeroOrdreCycles;
	}

	/**
	 * @param numeroOrdreCycles the numeroOrdreCycles to set
	 */
	public void setNumeroOrdreCycles(int numeroOrdreCycles) {
		this.numeroOrdreCycles = numeroOrdreCycles;
	}

	/**
	 * @return the codeCycles_en
	 */
	public String getCodeCycles_en() {
		return codeCycles_en;
	}

	/**
	 * @param codeCycles_en the codeCycles_en to set
	 */
	public void setCodeCycles_en(String codeCycles_en) {
		this.codeCycles_en = codeCycles_en;
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
	 * @return the codeCyclesAModif
	 */
	public String getCodeCyclesAModif() {
		return codeCyclesAModif;
	}

	/**
	 * @param codeCyclesAModif the codeCyclesAModif to set
	 */
	public void setCodeCyclesAModif(String codeCyclesAModif) {
		this.codeCyclesAModif = codeCyclesAModif;
	}
	
	
	
}
