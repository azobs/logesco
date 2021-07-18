/**
 * 
 */
package org.logesco.form;



import javax.validation.constraints.NotNull;

/**
 * @author cedrickiadjeu
 *
 */
public class UpdateEvalForm {
	private Long idSequenceConcerne;
	private Long idClassesConcerne;
	private Long idCoursConcerne;
	/*
	 * Puisqu'on peut avoir au plus 2 évaluations par cours pour une séquence
	 * on va donc dupliquer les paramètres de l'évaluation pour prévoir maximum 
	 * 2 évaluations au niveau du formulaire
	 */
	private String contenuEval1;
	
	@NotNull
	private int proportionEval1;//Proportion du DS = Devoir Surveillé
	
	private String contenuEval2;

	@NotNull
	private int proportionEval2;//Proportion du CC == Contrôle continu
	
	
	
	
	/**
	 * @return the idSequenceConcerne
	 */
	public Long getIdSequenceConcerne() {
		return idSequenceConcerne;
	}
	/**
	 * @param idSequenceConcerne the idSequenceConcerne to set
	 */
	public void setIdSequenceConcerne(Long idSequenceConcerne) {
		this.idSequenceConcerne = idSequenceConcerne;
	}
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
	 * @return the idCoursConcerne
	 */
	public Long getIdCoursConcerne() {
		return idCoursConcerne;
	}
	/**
	 * @param idCoursConcerne the idCoursConcerne to set
	 */
	public void setIdCoursConcerne(Long idCoursConcerne) {
		this.idCoursConcerne = idCoursConcerne;
	}
	/**
	 * @return the contenuEval1
	 */
	public String getContenuEval1() {
		return contenuEval1;
	}
	/**
	 * @param contenuEval1 the contenuEval1 to set
	 */
	public void setContenuEval1(String contenuEval1) {
		this.contenuEval1 = contenuEval1;
	}
	
	
	/**
	 * @return the proportionEval1
	 */
	public int getProportionEval1() {
		return proportionEval1;
	}
	/**
	 * @param proportionEval1 the proportionEval1 to set
	 */
	public void setProportionEval1(int proportionEval1) {
		this.proportionEval1 = proportionEval1;
	}
	
	
	/**
	 * @return the contenuEval2
	 */
	public String getContenuEval2() {
		return contenuEval2;
	}
	/**
	 * @param contenuEval2 the contenuEval2 to set
	 */
	public void setContenuEval2(String contenuEval2) {
		this.contenuEval2 = contenuEval2;
	}
	
	
	/**
	 * @return the proportionEval2
	 */
	public int getProportionEval2() {
		return proportionEval2;
	}
	/**
	 * @param proportionEval2 the proportionEval2 to set
	 */
	public void setProportionEval2(int proportionEval2) {
		this.proportionEval2 = proportionEval2;
	}
	
	
	
	
	
	
	
}
