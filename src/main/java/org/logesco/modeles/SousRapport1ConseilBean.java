/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class SousRapport1ConseilBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nom_discipline;
	private String nom_professeur;
	
	
	
	/**
	 * 
	 */
	public SousRapport1ConseilBean() {
		// TODO Auto-generated constructor stub
	}



	/**
	 * @return the nom_discipline
	 */
	public String getNom_discipline() {
		return nom_discipline;
	}



	/**
	 * @param nom_discipline the nom_discipline to set
	 */
	public void setNom_discipline(String nom_discipline) {
		this.nom_discipline = nom_discipline;
	}



	/**
	 * @return the nom_professeur
	 */
	public String getNom_professeur() {
		return nom_professeur;
	}



	/**
	 * @param nom_professeur the nom_professeur to set
	 */
	public void setNom_professeur(String nom_professeur) {
		this.nom_professeur = nom_professeur;
	}
	
	

}
