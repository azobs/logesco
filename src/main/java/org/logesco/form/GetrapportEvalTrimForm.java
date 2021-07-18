/**
 * 
 */
package org.logesco.form;

import javax.validation.constraints.NotNull;

/**
 * @author cedrickiadjeu
 *
 */
public class GetrapportEvalTrimForm {

	@NotNull
	private long idclasseRapport;
	@NotNull
	private long idtrimestreRapport;
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
	 * @return the idtrimestreRapport
	 */
	public long getIdtrimestreRapport() {
		return idtrimestreRapport;
	}
	/**
	 * @param idtrimestreRapport the idtrimestreRapport to set
	 */
	public void setIdtrimestreRapport(long idtrimestreRapport) {
		this.idtrimestreRapport = idtrimestreRapport;
	}
	
	

}
