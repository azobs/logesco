/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class RapportAnnuelClasse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double valeurMoyennePremierDansAn;
	private double valeurMoyenneDernierDansAn;
	private int nbreMoyennePourAn;
	private double tauxReussiteAnnuel;
	private double moyenneGeneralAnnuel;
	private int effectifEleveRegulier;

	/**
	 * 
	 */
	public RapportAnnuelClasse() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the valeurMoyennePremierDansAn
	 */
	public double getValeurMoyennePremierDansAn() {
		return valeurMoyennePremierDansAn;
	}

	/**
	 * @param valeurMoyennePremierDansAn the valeurMoyennePremierDansAn to set
	 */
	public void setValeurMoyennePremierDansAn(double valeurMoyennePremierDansAn) {
		this.valeurMoyennePremierDansAn = valeurMoyennePremierDansAn;
	}

	/**
	 * @return the valeurMoyenneDernierDansAn
	 */
	public double getValeurMoyenneDernierDansAn() {
		return valeurMoyenneDernierDansAn;
	}

	/**
	 * @param valeurMoyenneDernierDansAn the valeurMoyenneDernierDansAn to set
	 */
	public void setValeurMoyenneDernierDansAn(double valeurMoyenneDernierDansAn) {
		this.valeurMoyenneDernierDansAn = valeurMoyenneDernierDansAn;
	}

	/**
	 * @return the nbreMoyennePourAn
	 */
	public int getNbreMoyennePourAn() {
		return nbreMoyennePourAn;
	}

	/**
	 * @param nbreMoyennePourAn the nbreMoyennePourAn to set
	 */
	public void setNbreMoyennePourAn(int nbreMoyennePourAn) {
		this.nbreMoyennePourAn = nbreMoyennePourAn;
	}

	/**
	 * @return the tauxReussiteAnnuel
	 */
	public double getTauxReussiteAnnuel() {
		return tauxReussiteAnnuel;
	}

	/**
	 * @param tauxReussiteAnnuel the tauxReussiteAnnuel to set
	 */
	public void setTauxReussiteAnnuel(double tauxReussiteAnnuel) {
		this.tauxReussiteAnnuel = tauxReussiteAnnuel;
	}

	/**
	 * @return the moyenneGeneralAnnuel
	 */
	public double getMoyenneGeneralAnnuel() {
		return moyenneGeneralAnnuel;
	}

	/**
	 * @param moyenneGeneralAnnuel the moyenneGeneralAnnuel to set
	 */
	public void setMoyenneGeneralAnnuel(double moyenneGeneralAnnuel) {
		this.moyenneGeneralAnnuel = moyenneGeneralAnnuel;
	}

	/**
	 * @return the effectifEleveRegulier
	 */
	public int getEffectifEleveRegulier() {
		return effectifEleveRegulier;
	}

	/**
	 * @param effectifEleveRegulier the effectifEleveRegulier to set
	 */
	public void setEffectifEleveRegulier(int effectifEleveRegulier) {
		this.effectifEleveRegulier = effectifEleveRegulier;
	}
	
	

}
