/**
 * 
 */
package org.logesco.form;

import javax.validation.constraints.NotNull;

/**
 * @author cedrickiadjeu
 *
 */
public class GetrapportEvalSeqForm {

	@NotNull
	private long idclasseRapport;
	@NotNull
	private long idsequenceRapport;
	/**
	 * @return the idclasseRapport
	 */
	public long getIdclasseRapport() {
		return idclasseRapport;
	}
	/**
	 * @param idclasseRapport the idclasseRapport to set
	 */
	public void setIdclasseRapport(long idclasseRapport) {
		this.idclasseRapport = idclasseRapport;
	}
	/**
	 * @return the idsequenceRapport
	 */
	public long getIdsequenceRapport() {
		return idsequenceRapport;
	}
	/**
	 * @param idsequenceRapport the idsequenceRapport to set
	 */
	public void setIdsequenceRapport(long idsequenceRapport) {
		this.idsequenceRapport = idsequenceRapport;
	}
	
	

}
