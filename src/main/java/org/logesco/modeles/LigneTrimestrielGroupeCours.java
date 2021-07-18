/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class LigneTrimestrielGroupeCours implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double moyenneTrimElevePourGroupeCours;
	private double totalCoefElevePourGroupeCours;
	private double totalNoteTrimElevePourGroupeCours;

	/**
	 * 
	 */
	public LigneTrimestrielGroupeCours() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the moyenneTrimElevePourGroupeCours
	 */
	public double getMoyenneTrimElevePourGroupeCours() {
		return moyenneTrimElevePourGroupeCours;
	}

	/**
	 * @param moyenneTrimElevePourGroupeCours the moyenneTrimElevePourGroupeCours to set
	 */
	public void setMoyenneTrimElevePourGroupeCours(double moyenneTrimElevePourGroupeCours) {
		this.moyenneTrimElevePourGroupeCours = moyenneTrimElevePourGroupeCours;
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
	 * @return the totalNoteTrimElevePourGroupeCours
	 */
	public double getTotalNoteTrimElevePourGroupeCours() {
		return totalNoteTrimElevePourGroupeCours;
	}

	/**
	 * @param totalNoteTrimElevePourGroupeCours the totalNoteTrimElevePourGroupeCours to set
	 */
	public void setTotalNoteTrimElevePourGroupeCours(double totalNoteTrimElevePourGroupeCours) {
		this.totalNoteTrimElevePourGroupeCours = totalNoteTrimElevePourGroupeCours;
	}
	
	

}
