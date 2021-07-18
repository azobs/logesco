/**
 * 
 */
package org.logesco.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;


/**
 * @author cedrickiadjeu
 *
 */
public class SaveListElevesForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long idClasseConcerne;
	private Byte[] cheminFichier;

	/**
	 * 
	 */
	public SaveListElevesForm() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the idClasseConcerne
	 */
	public Long getIdClasseConcerne() {
		return idClasseConcerne;
	}

	/**
	 * @param idClasseConcerne the idClasseConcerne to set
	 */
	public void setIdClasseConcerne(Long idClasseConcerne) {
		this.idClasseConcerne = idClasseConcerne;
	}

	/**
	 * @return the cheminFichier
	 */
	public Byte[] getCheminFichier() {
		return cheminFichier;
	}

	/**
	 * @param cheminFichier the cheminFichier to set
	 */
	public void setCheminFichier(Byte[] cheminFichier) {
		this.cheminFichier = cheminFichier;
	}

	
	
	

}
