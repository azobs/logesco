/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class LigneSequentielCours implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double totalPointsSequentiel;
	private double soeCoefCoursCompose;
	private double valeurNoteFinaleEleve;

	/**
	 * 
	 */
	public LigneSequentielCours() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the totalPointsSequentiel
	 */
	public double getTotalPointsSequentiel() {
		return totalPointsSequentiel;
	}

	/**
	 * @param totalPointsSequentiel the totalPointsSequentiel to set
	 */
	public void setTotalPointsSequentiel(double totalPointsSequentiel) {
		this.totalPointsSequentiel = totalPointsSequentiel;
	}

	/**
	 * @return the soeCoefCoursCompose
	 */
	public double getSoeCoefCoursCompose() {
		return soeCoefCoursCompose;
	}

	/**
	 * @param soeCoefCoursCompose the soeCoefCoursCompose to set
	 */
	public void setSoeCoefCoursCompose(double soeCoefCoursCompose) {
		this.soeCoefCoursCompose = soeCoefCoursCompose;
	}

	/**
	 * @return the valeurNoteFinaleEleve
	 */
	public double getValeurNoteFinaleEleve() {
		return valeurNoteFinaleEleve;
	}

	/**
	 * @param valeurNoteFinaleEleve the valeurNoteFinaleEleve to set
	 */
	public void setValeurNoteFinaleEleve(double valeurNoteFinaleEleve) {
		this.valeurNoteFinaleEleve = valeurNoteFinaleEleve;
	}

	
	
}
