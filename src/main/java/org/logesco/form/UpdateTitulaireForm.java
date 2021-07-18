/**
 * 
 */
package org.logesco.form;

import javax.validation.constraints.NotNull;

/**
 * @author cedrickiadjeu
 *
 */
public class UpdateTitulaireForm {
	@NotNull
	private Long idClassesConcerne;
	@NotNull
	private Long idUsersTitulaire;
	
	private int numPageNiveaux;
	/**
	 * @return the idClassesConcerne
	 */
	public Long getIdClassesConcerne() {
		return idClassesConcerne;
	}
	/**
	 * @param idClassesConcerne the idClassesConcerne to set
	 */
	public void setIdClassesConcerne(Long idClassesConcerne) {
		this.idClassesConcerne = idClassesConcerne;
	}
	/**
	 * @return the idUsersTitulaire
	 */
	public Long getIdUsersTitulaire() {
		return idUsersTitulaire;
	}
	/**
	 * @param idUsersTitulaire the idUsersTitulaire to set
	 */
	public void setIdUsersTitulaire(Long idUsersTitulaire) {
		this.idUsersTitulaire = idUsersTitulaire;
	}
	/**
	 * @return the numPageNiveaux
	 */
	public int getNumPageNiveaux() {
		return numPageNiveaux;
	}
	/**
	 * @param numPageNiveaux the numPageNiveaux to set
	 */
	public void setNumPageNiveaux(int numPageNiveaux) {
		this.numPageNiveaux = numPageNiveaux;
	}
	
	
	
}
