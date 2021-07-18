/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class PV_TrimestreBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numero;
	private String matricule;
	private String noms_prenoms;
	private String date_naissance;
	private double noteSeq1;
	private double noteSeq2;
	private double note_finale;

	/**
	 * 
	 */
	public PV_TrimestreBean() {
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
	 * @return the noteSeq1
	 */
	public double getNoteSeq1() {
		return noteSeq1;
	}

	/**
	 * @param noteSeq1 the noteSeq1 to set
	 */
	public void setNoteSeq1(double noteSeq1) {
		this.noteSeq1 = noteSeq1;
	}

	/**
	 * @return the noteSeq2
	 */
	public double getNoteSeq2() {
		return noteSeq2;
	}

	/**
	 * @param noteSeq2 the noteSeq2 to set
	 */
	public void setNoteSeq2(double noteSeq2) {
		this.noteSeq2 = noteSeq2;
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
