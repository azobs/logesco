/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class RapportTrimestrielCours implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double valeurNoteDernier;
	private double valeurNotePremier;
	private double tauxReussiteCoursTrim;
	private double moyenneGeneralCoursTrim;
	
	/**
	 * 
	 */
	public RapportTrimestrielCours() {
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
	 * @return the tauxReussiteCoursTrim
	 */
	public double getTauxReussiteCoursTrim() {
		return tauxReussiteCoursTrim;
	}

	/**
	 * @param tauxReussiteCoursTrim the tauxReussiteCoursTrim to set
	 */
	public void setTauxReussiteCoursTrim(double tauxReussiteCoursTrim) {
		this.tauxReussiteCoursTrim = tauxReussiteCoursTrim;
	}

	/**
	 * @return the moyenneGeneralCoursTrim
	 */
	public double getMoyenneGeneralCoursTrim() {
		return moyenneGeneralCoursTrim;
	}

	/**
	 * @param moyenneGeneralCoursTrim the moyenneGeneralCoursTrim to set
	 */
	public void setMoyenneGeneralCoursTrim(double moyenneGeneralCoursTrim) {
		this.moyenneGeneralCoursTrim = moyenneGeneralCoursTrim;
	}
	
	
	
	

}
