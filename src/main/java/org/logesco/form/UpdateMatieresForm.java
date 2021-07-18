/**
 * 
 */
package org.logesco.form;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
public class UpdateMatieresForm {
	
	private Long idMatiere;
	@NotNull
	@NotEmpty
	@Column(unique=true)
	private String codeMatiere;
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 100)
	private String intituleMatiere;
	
	/****
	 * Debut des ajouts du 19-08-2018
	 */
	@NotEmpty
	@Size(min= 2, max= 100)
	private String intitule2langueMatiere;
	/****
	 * Fin des ajouts du 19-08-2018
	 */
	
	
	
	/**
	 * @return the idMatiere
	 */
	public Long getIdMatiere() {
		return idMatiere;
	}
	/**
	 * @return the intitule2langueMatiere
	 */
	public String getIntitule2langueMatiere() {
		return intitule2langueMatiere;
	}
	/**
	 * @param intitule2langueMatiere the intitule2langueMatiere to set
	 */
	public void setIntitule2langueMatiere(String intitule2langueMatiere) {
		this.intitule2langueMatiere = intitule2langueMatiere;
	}
	/**
	 * @param idMatiere the idMatiere to set
	 */
	public void setIdMatiere(Long idMatiere) {
		this.idMatiere = idMatiere;
	}
	/**
	 * @return the codeMatiere
	 */
	public String getCodeMatiere() {
		return codeMatiere;
	}
	/**
	 * @param codeMatiere the codeMatiere to set
	 */
	public void setCodeMatiere(String codeMatiere) {
		this.codeMatiere = codeMatiere;
	}
	/**
	 * @return the intituleMatiere
	 */
	public String getIntituleMatiere() {
		return intituleMatiere;
	}
	/**
	 * @param intituleMatiere the intituleMatiere to set
	 */
	public void setIntituleMatiere(String intituleMatiere) {
		this.intituleMatiere = intituleMatiere;
	}
	
	
	
}
