/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class RapportSequentielCours implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double valeurNoteDernier;
	private double valeurNotePremier;
	private double tauxReussiteCoursSeq;
	private double moyenneGeneralCoursSeq;

	/**
	 * 
	 */
	public RapportSequentielCours() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the valeurNoteDernier
	 */
	public double getValeurNoteDernier() {
		return valeurNoteDernier;
	}

	/**
	 * @param valeurNoteDernier the valeurNoteDernier to set
	 */
	public void setValeurNoteDernier(double valeurNoteDernier) {
		this.valeurNoteDernier = valeurNoteDernier;
	}

	/**
	 * @return the valeurNotePremier
	 */
	public double getValeurNotePremier() {
		return valeurNotePremier;
	}

	/**
	 * @param valeurNotePremier the valeurNotePremier to set
	 */
	public void setValeurNotePremier(double valeurNotePremier) {
		this.valeurNotePremier = valeurNotePremier;
	}

	/**
	 * @return the tauxReussiteCoursSeq
	 */
	public double getTauxReussiteCoursSeq() {
		return tauxReussiteCoursSeq;
	}

	/**
	 * @param tauxReussiteCoursSeq the tauxReussiteCoursSeq to set
	 */
	public void setTauxReussiteCoursSeq(double tauxReussiteCoursSeq) {
		this.tauxReussiteCoursSeq = tauxReussiteCoursSeq;
	}

	/**
	 * @return the moyenneGeneralCoursSeq
	 */
	public double getMoyenneGeneralCoursSeq() {
		return moyenneGeneralCoursSeq;
	}

	/**
	 * @param moyenneGeneralCoursSeq the moyenneGeneralCoursSeq to set
	 */
	public void setMoyenneGeneralCoursSeq(double moyenneGeneralCoursSeq) {
		this.moyenneGeneralCoursSeq = moyenneGeneralCoursSeq;
	}
	
	

}
