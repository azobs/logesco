/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class PV_SequenceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numero;
	private String matricule;
	private String noms_prenoms;
	private String date_naissance;
	private double note_cc;
	private double note_ds;
	private double note_finale;

	/**
	 * 
	 */
	public PV_SequenceBean() {
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
	 * @return the note_cc
	 */
	public double getNote_cc() {
		return note_cc;
	}

	/**
	 * @param note_cc the note_cc to set
	 */
	public void setNote_cc(double note_cc) {
		this.note_cc = note_cc;
	}

	/**
	 * @return the note_ds
	 */
	public double getNote_ds() {
		return note_ds;
	}

	/**
	 * @param note_ds the note_ds to set
	 */
	public void setNote_ds(double note_ds) {
		this.note_ds = note_ds;
	}

	/**
	 * @return the note_finale
	 */
	public double getNote_finale() {
		return note_finale;
	}

	/**
	 * @param note_finale the note_finale to set
	 */
	public void setNote_finale(double note_finale) {
		this.note_finale = note_finale;
	}
	
	

}
