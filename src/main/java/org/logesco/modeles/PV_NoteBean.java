/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class PV_NoteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numero;
	private String matricule;
	private String noms_prenoms;
	private String date_naissance;
	private double note_eval;

	/**
	 * 
	 */
	public PV_NoteBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * @return the noms_prenoms
	 */
	public String getNoms_prenoms() {
		return noms_prenoms;
	}

	/**
	 * @param noms_prenoms the noms_prenoms to set
	 */
	public void setNoms_prenoms(String noms_prenoms) {
		this.noms_prenoms = noms_prenoms;
	}

	/**
	 * @return the date_naissance
	 */
	public String getDate_naissance() {
		return date_naissance;
	}

	/**
	 * @param date_naissance the date_naissance to set
	 */
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}

	/**
	 * @return the note_eval
	 */
	public double getNote_eval() {
		return note_eval;
	}

	/**
	 * @param note_eval the note_eval to set
	 */
	public void setNote_eval(double note_eval) {
		this.note_eval = note_eval;
	}
	
	

}
