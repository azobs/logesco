/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class RapportSequentielClasse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double valeurMoyennePremierDansSeq;
	private double valeurMoyenneDernierDansSeq;
	private int nbreMoyennePourSeq;
	private double tauxReussiteSequentiel;
	private double moyenneGeneralSequence;
	private int effectifEleveRegulier;

	/**
	 * 
	 */
	public RapportSequentielClasse() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the valeurMoyennePremierDansSeq
	 */
	public double getValeurMoyennePremierDansSeq() {
		return valeurMoyennePremierDansSeq;
	}

	/**
	 * @param valeurMoyennePremierDansSeq the valeurMoyennePremierDansSeq to set
	 */
	public void setValeurMoyennePremierDansSeq(double valeurMoyennePremierDansSeq) {
		this.valeurMoyennePremierDansSeq = valeurMoyennePremierDansSeq;
	}

	/**
	 * @return the valeurMoyenneDernierDansSeq
	 */
	public double getValeurMoyenneDernierDansSeq() {
		return valeurMoyenneDernierDansSeq;
	}

	/**
	 * @param valeurMoyenneDernierDansSeq the valeurMoyenneDernierDansSeq to set
	 */
	public void setValeurMoyenneDernierDansSeq(double valeurMoyenneDernierDansSeq) {
		this.valeurMoyenneDernierDansSeq = valeurMoyenneDernierDansSeq;
	}

	/**
	 * @return the nbreMoyennePourSeq
	 */
	public int getNbreMoyennePourSeq() {
		return nbreMoyennePourSeq;
	}

	/**
	 * @param nbreMoyennePourSeq the nbreMoyennePourSeq to set
	 */
	public void setNbreMoyennePourSeq(int nbreMoyennePourSeq) {
		this.nbreMoyennePourSeq = nbreMoyennePourSeq;
	}

	/**
	 * @return the tauxReussiteSequentiel
	 */
	public double getTauxReussiteSequentiel() {
		return tauxReussiteSequentiel;
	}

	/**
	 * @param tauxReussiteSequentiel the tauxReussiteSequentiel to set
	 */
	public void setTauxReussiteSequentiel(double tauxReussiteSequentiel) {
		this.tauxReussiteSequentiel = tauxReussiteSequentiel;
	}

	/**
	 * @return the moyenneGeneralSequence
	 */
	public double getMoyenneGeneralSequence() {
		return moyenneGeneralSequence;
	}

	/**
	 * @param moyenneGeneralSequence the moyenneGeneralSequence to set
	 */
	public void setMoyenneGeneralSequence(double moyenneGeneralSequence) {
		this.moyenneGeneralSequence = moyenneGeneralSequence;
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
