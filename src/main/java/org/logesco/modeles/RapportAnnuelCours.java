/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class RapportAnnuelCours implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double valeurNoteDernier;
	private double valeurNotePremier;
	private double tauxReussiteCoursAn;
	private double moyenneGeneralCoursAn;

	/**
	 * 
	 */
	public RapportAnnuelCours() {
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
	 * @return the tauxReussiteCoursAn
	 */
	public double getTauxReussiteCoursAn() {
		return tauxReussiteCoursAn;
	}

	/**
	 * @param tauxReussiteCoursAn the tauxReussiteCoursAn to set
	 */
	public void setTauxReussiteCoursAn(double tauxReussiteCoursAn) {
		this.tauxReussiteCoursAn = tauxReussiteCoursAn;
	}

	/**
	 * @return the moyenneGeneralCoursAn
	 */
	public double getMoyenneGeneralCoursAn() {
		return moyenneGeneralCoursAn;
	}

	/**
	 * @param moyenneGeneralCoursAn the moyenneGeneralCoursAn to set
	 */
	public void setMoyenneGeneralCoursAn(double moyenneGeneralCoursAn) {
		this.moyenneGeneralCoursAn = moyenneGeneralCoursAn;
	}
	
	

}
