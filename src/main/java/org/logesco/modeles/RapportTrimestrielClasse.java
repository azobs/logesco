/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class RapportTrimestrielClasse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double valeurMoyennePremierDansTrim;
	private double valeurMoyenneDernierDansTrim;
	private int nbreMoyennePourTrim;
	private double tauxReussiteTrimestriel;
	private double moyenneGeneralTrimestre;
	private int effectifEleveRegulier;

	/**
	 * 
	 */
	public RapportTrimestrielClasse() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the valeurMoyennePremierDansTrim
	 */
	public double getValeurMoyennePremierDansTrim() {
		return valeurMoyennePremierDansTrim;
	}

	/**
	 * @param valeurMoyennePremierDansTrim the valeurMoyennePremierDansTrim to set
	 */
	public void setValeurMoyennePremierDansTrim(double valeurMoyennePremierDansTrim) {
		this.valeurMoyennePremierDansTrim = valeurMoyennePremierDansTrim;
	}

	/**
	 * @return the valeurMoyenneDernierDansTrim
	 */
	public double getValeurMoyenneDernierDansTrim() {
		return valeurMoyenneDernierDansTrim;
	}

	/**
	 * @param valeurMoyenneDernierDansTrim the valeurMoyenneDernierDansTrim to set
	 */
	public void setValeurMoyenneDernierDansTrim(double valeurMoyenneDernierDansTrim) {
		this.valeurMoyenneDernierDansTrim = valeurMoyenneDernierDansTrim;
	}

	/**
	 * @return the nbreMoyennePourTrim
	 */
	public int getNbreMoyennePourTrim() {
		return nbreMoyennePourTrim;
	}

	/**
	 * @param nbreMoyennePourTrim the nbreMoyennePourTrim to set
	 */
	public void setNbreMoyennePourTrim(int nbreMoyennePourTrim) {
		this.nbreMoyennePourTrim = nbreMoyennePourTrim;
	}

	/**
	 * @return the tauxReussiteTrimestriel
	 */
	public double getTauxReussiteTrimestriel() {
		return tauxReussiteTrimestriel;
	}

	/**
	 * @param tauxReussiteTrimestriel the tauxReussiteTrimestriel to set
	 */
	public void setTauxReussiteTrimestriel(double tauxReussiteTrimestriel) {
		this.tauxReussiteTrimestriel = tauxReussiteTrimestriel;
	}

	/**
	 * @return the moyenneGeneralTrimestre
	 */
	public double getMoyenneGeneralTrimestre() {
		return moyenneGeneralTrimestre;
	}

	/**
	 * @param moyenneGeneralTrimestre the moyenneGeneralTrimestre to set
	 */
	public void setMoyenneGeneralTrimestre(double moyenneGeneralTrimestre) {
		this.moyenneGeneralTrimestre = moyenneGeneralTrimestre;
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
