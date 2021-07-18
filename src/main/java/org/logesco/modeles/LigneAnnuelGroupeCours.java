/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class LigneAnnuelGroupeCours implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double moyenneAnElevePourGroupeCours;
	private double totalCoefElevePourGroupeCours;
	private double totalNoteAnElevePourGroupeCours;
	

	/**
	 * 
	 */
	public LigneAnnuelGroupeCours() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the moyenneAnElevePourGroupeCours
	 */
	public double getMoyenneAnElevePourGroupeCours() {
		return moyenneAnElevePourGroupeCours;
	}


	/**
	 * @param moyenneAnElevePourGroupeCours the moyenneAnElevePourGroupeCours to set
	 */
	public void setMoyenneAnElevePourGroupeCours(double moyenneAnElevePourGroupeCours) {
		this.moyenneAnElevePourGroupeCours = moyenneAnElevePourGroupeCours;
	}


	/**
	 * @return the totalCoefElevePourGroupeCours
	 */
	public double getTotalCoefElevePourGroupeCours() {
		return totalCoefElevePourGroupeCours;
	}


	/**
	 * @param totalCoefElevePourGroupeCours the totalCoefElevePourGroupeCours to set
	 */
	public void setTotalCoefElevePourGroupeCours(double totalCoefElevePourGroupeCours) {
		this.totalCoefElevePourGroupeCours = totalCoefElevePourGroupeCours;
	}


	/**
	 * @return the totalNoteAnElevePourGroupeCours
	 */
	public double getTotalNoteAnElevePourGroupeCours() {
		return totalNoteAnElevePourGroupeCours;
	}


	/**
	 * @param totalNoteAnElevePourGroupeCours the totalNoteAnElevePourGroupeCours to set
	 */
	public void setTotalNoteAnElevePourGroupeCours(double totalNoteAnElevePourGroupeCours) {
		this.totalNoteAnElevePourGroupeCours = totalNoteAnElevePourGroupeCours;
	}
	
	

}
