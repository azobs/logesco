/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

import org.logesco.entities.Cours;

/**
 * @author cedrickiadjeu
 *
 */
public class NoteFinaleCours implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cours cours;
	private Long idCours;
	private double valeurNote;
	/**
	 * 
	 */
	public NoteFinaleCours() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param idCours
	 * @param valeurNote
	 */
	public NoteFinaleCours(Long idCours, double valeurNote) {
		super();
		this.idCours = idCours;
		this.valeurNote = valeurNote;
	}
	/**
	 * @return the idCours
	 */
	public Long getIdCours() {
		return idCours;
	}
	/**
	 * @param idCours the idCours to set
	 */
	public void setIdCours(Long idCours) {
		this.idCours = idCours;
	}
	/**
	 * @return the valeurNote
	 */
	public double getValeurNote() {
		return valeurNote;
	}
	/**
	 * @param valeurNote the valeurNote to set
	 */
	public void setValeurNote(double valeurNote) {
		this.valeurNote = valeurNote;
	}
	/**
	 * @return the cours
	 */
	public Cours getCours() {
		return cours;
	}
	/**
	 * @param cours the cours to set
	 */
	public void setCours(Cours cours) {
		this.cours = cours;
	}
	
	

}
