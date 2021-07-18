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
public class UpdateCoursForm {

	private Long idCours;
	
	private Long idMatiereAssocie;
	
	private Long idUsersAssocie;
	
	private Long idClasseSelect;
	
	@NotNull
	@NotEmpty
	private String codeCours;
	@NotNull
	@NotEmpty
	private String intituleCours;
	@NotNull
	private double coefCours;
	
	/***
	 * Debut des ajouts du 19-08-2018
	 */
	private String intitule2langueCours;
	
	@NotNull
	@NotEmpty
	private String groupeCours;
	@NotNull
	private int numerogroupeCours;
	/***
	 * Fin des ajouts du 19-08-2018
	 */
	
	/**
	 * @return the idCours
	 */
	public Long getIdCours() {
		return idCours;
	}
	/**
	 * @param idCours the idCours to set
	 */
	public void setIdCours(Long idCours) {
		this.idCours = idCours;
	}
	/**
	 * @return the idMatiereAssocie
	 */
	public Long getIdMatiereAssocie() {
		return idMatiereAssocie;
	}
	/**
	 * @param idMatiereAssocie the idMatiereAssocie to set
	 */
	public void setIdMatiereAssocie(Long idMatiereAssocie) {
		this.idMatiereAssocie = idMatiereAssocie;
	}
	/**
	 * @return the idUsersAssocie
	 */
	public Long getIdUsersAssocie() {
		return idUsersAssocie;
	}
	/**
	 * @param idUsersAssocie the idUsersAssocie to set
	 */
	public void setIdUsersAssocie(Long idUsersAssocie) {
		this.idUsersAssocie = idUsersAssocie;
	}
	
	/**
	 * @return the idClasseAssocie
	 */
	/*public Long getIdClasseAssocie() {
		return idClasseAssocie;
	}*/
	/**
	 * @param idClasseAssocie the idClasseAssocie to set
	 */
	/*public void setIdClasseAssocie(Long idClasseAssocie) {
		this.idClasseAssocie = idClasseAssocie;
	}*/
	
	/**
	 * @return the codeCours
	 */
	public String getCodeCours() {
		return codeCours;
	}
	/**
	 * @return the idClasseSelect
	 */
	public Long getIdClasseSelect() {
		return idClasseSelect;
	}
	/**
	 * @param idClasseSelect the idClasseSelect to set
	 */
	public void setIdClasseSelect(Long idClasseSelect) {
		this.idClasseSelect = idClasseSelect;
	}
	/**
	 * @param codeCours the codeCours to set
	 */
	public void setCodeCours(String codeCours) {
		this.codeCours = codeCours;
	}
	/**
	 * @return the intituleCours
	 */
	public String getIntituleCours() {
		return intituleCours;
	}
	/**
	 * @param intituleCours the intituleCours to set
	 */
	public void setIntituleCours(String intituleCours) {
		this.intituleCours = intituleCours;
	}
	/**
	 * @return the coefCours
	 */
	public double getCoefCours() {
		return coefCours;
	}
	/**
	 * @param coefCours the coefCours to set
	 */
	public void setCoefCours(double coefCours) {
		this.coefCours = coefCours;
	}
	/**
	 * @return the intitule2langueCours
	 */
	public String getIntitule2langueCours() {
		return intitule2langueCours;
	}
	/**
	 * @param intitule2langueCours the intitule2langueCours to set
	 */
	public void setIntitule2langueCours(String intitule2langueCours) {
		this.intitule2langueCours = intitule2langueCours;
	}
	/**
	 * @return the groupeCours
	 */
	public String getGroupeCours() {
		return groupeCours;
	}
	/**
	 * @param groupeCours the groupeCours to set
	 */
	public void setGroupeCours(String groupeCours) {
		this.groupeCours = groupeCours;
	}
	/**
	 * @return the numerogroupeCours
	 */
	public int getNumerogroupeCours() {
		return numerogroupeCours;
	}
	/**
	 * @param numerogroupeCours the numerogroupeCours to set
	 */
	public void setNumerogroupeCours(int numerogroupeCours) {
		this.numerogroupeCours = numerogroupeCours;
	}
	
	
	
	
}
