/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class LigneSequentielGroupeCours implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double moyenneSeqElevePourGroupeCours;
	private double totalCoefElevePourGroupeCours;
	private double totalNoteSeqElevePourGroupeCours;

	/**
	 * 
	 */
	public LigneSequentielGroupeCours() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the moyenneSeqElevePourGroupeCours
	 */
	public double getMoyenneSeqElevePourGroupeCours() {
		return moyenneSeqElevePourGroupeCours;
	}

	/**
	 * @param moyenneSeqElevePourGroupeCours the moyenneSeqElevePourGroupeCours to set
	 */
	public void setMoyenneSeqElevePourGroupeCours(double moyenneSeqElevePourGroupeCours) {
		this.moyenneSeqElevePourGroupeCours = moyenneSeqElevePourGroupeCours;
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
	 * @return the totalNoteSeqElevePourGroupeCours
	 */
	public double getTotalNoteSeqElevePourGroupeCours() {
		return totalNoteSeqElevePourGroupeCours;
	}

	/**
	 * @param totalNoteSeqElevePourGroupeCours the totalNoteSeqElevePourGroupeCours to set
	 */
	public void setTotalNoteSeqElevePourGroupeCours(double totalNoteSeqElevePourGroupeCours) {
		this.totalNoteSeqElevePourGroupeCours = totalNoteSeqElevePourGroupeCours;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LigneSequentielGroupeCours [moyenneSeqElevePourGroupeCours=" + moyenneSeqElevePourGroupeCours
				+ ", totalCoefElevePourGroupeCours=" + totalCoefElevePourGroupeCours
				+ ", totalNoteSeqElevePourGroupeCours=" + totalNoteSeqElevePourGroupeCours + "]";
	}
	
	

}
