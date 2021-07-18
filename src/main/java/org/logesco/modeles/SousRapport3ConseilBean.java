/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class SousRapport3ConseilBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nom_matiere;
	private int nbrepresent_mat;
	private int nbre_moy;
	private double pourcentage;

	/**
	 * 
	 */
	public SousRapport3ConseilBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the nom_matiere
	 */
	public String getNom_matiere() {
		return nom_matiere;
	}

	/**
	 * @param nom_matiere the nom_matiere to set
	 */
	public void setNom_matiere(String nom_matiere) {
		this.nom_matiere = nom_matiere;
	}

	/**
	 * @return the nbrepresent_mat
	 */
	public int getNbrepresent_mat() {
		return nbrepresent_mat;
	}

	/**
	 * @param nbrepresent_mat the nbrepresent_mat to set
	 */
	public void setNbrepresent_mat(int nbrepresent_mat) {
		this.nbrepresent_mat = nbrepresent_mat;
	}

	/**
	 * @return the nbre_moy
	 */
	public int getNbre_moy() {
		return nbre_moy;
	}

	/**
	 * @param nbre_moy the nbre_moy to set
	 */
	public void setNbre_moy(int nbre_moy) {
		this.nbre_moy = nbre_moy;
	}

	/**
	 * @return the pourcentage
	 */
	public double getPourcentage() {
		return pourcentage;
	}

	/**
	 * @param pourcentage the pourcentage to set
	 */
	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}
	
	

}
