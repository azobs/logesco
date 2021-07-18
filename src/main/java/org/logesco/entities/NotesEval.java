/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="notesEval")
public class NotesEval implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idnoteEval;
	@NotNull
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateenregnoteEval;
	@NotNull
	private double valeurnoteEval;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Eleves
	 ******************************************/
	@ManyToOne
	Eleves eleve;
	
	/*
	 * Association avec la table BulletinsSeq
	 ******************************************/
	@ManyToOne
	BulletinsSeq bulletinSeq;
	
	/*
	 * Association avec la table Evaluations
	 ******************************************/
	@ManyToOne
	Evaluations evaluation;

	/**
	 * 
	 */
	public NotesEval() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param dateenregnoteEval
	 * @param valeurnoteEval
	 */
	public NotesEval(Date dateenregnoteEval, double valeurnoteEval) {
		super();
		this.dateenregnoteEval = dateenregnoteEval;
		this.valeurnoteEval = valeurnoteEval;
	}

	/**
	 * @return the idnoteEval
	 */
	public Long getIdnoteEval() {
		return idnoteEval;
	}

	/**
	 * @param idnoteEval the idnoteEval to set
	 */
	public void setIdnoteEval(Long idnoteEval) {
		this.idnoteEval = idnoteEval;
	}

	/**
	 * @return the dateenregnoteEval
	 */
	public Date getDateenregnoteEval() {
		return dateenregnoteEval;
	}

	/**
	 * @param dateenregnoteEval the dateenregnoteEval to set
	 */
	public void setDateenregnoteEval(Date dateenregnoteEval) {
		this.dateenregnoteEval = dateenregnoteEval;
	}

	/**
	 * @return the valeurnoteEval
	 */
	public double getValeurnoteEval() {
		return valeurnoteEval;
	}

	/**
	 * @param valeurnoteEval the valeurnoteEval to set
	 */
	public void setValeurnoteEval(double valeurnoteEval) {
		this.valeurnoteEval = valeurnoteEval;
	}

	/**
	 * @return the eleve
	 */
	public Eleves getEleve() {
		return eleve;
	}

	/**
	 * @param eleve the eleve to set
	 */
	public void setEleve(Eleves eleve) {
		this.eleve = eleve;
	}

	/**
	 * @return the bulletinSeq
	 */
	public BulletinsSeq getBulletinSeq() {
		return bulletinSeq;
	}

	/**
	 * @param bulletinSeq the bulletinSeq to set
	 */
	public void setBulletinSeq(BulletinsSeq bulletinSeq) {
		this.bulletinSeq = bulletinSeq;
	}

	/**
	 * @return the evaluation
	 */
	public Evaluations getEvaluation() {
		return evaluation;
	}

	/**
	 * @param evaluation the evaluation to set
	 */
	public void setEvaluation(Evaluations evaluation) {
		this.evaluation = evaluation;
	}
	
	

}
