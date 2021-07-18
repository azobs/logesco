/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="rapportDAbsence")
public class RapportDAbsence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idRabs;
	@NotNull
	private int nbreheureJustifie;
	@NotNull
	private int nbreheureNJustifie;
	private boolean avertConduite;
	private boolean blameConduite;
	private boolean conseilDiscipline;
	private int consigne;
	private int jourExclusion;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Sequences
	 ******************************************/
	@ManyToOne
	Sequences sequence;
	
	/*
	 * Association avec la table Eleves
	 ******************************************/
	@ManyToOne
	Eleves eleve;
	
	

	/**
	 * 
	 */
	public RapportDAbsence() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nbreheureJustifie
	 * @param nbreheureNJustifie
	 */
	public RapportDAbsence(int nbreheureJustifie, int nbreheureNJustifie) {
		super();
		this.nbreheureJustifie = nbreheureJustifie;
		this.nbreheureNJustifie = nbreheureNJustifie;
	}

	/**
	 * @return the idRabs
	 */
	public Long getIdRabs() {
		return idRabs;
	}

	/**
	 * @param idRabs the idRabs to set
	 */
	public void setIdRabs(Long idRabs) {
		this.idRabs = idRabs;
	}

	/**
	 * @return the nbreheureJustifie
	 */
	public int getNbreheureJustifie() {
		return nbreheureJustifie;
	}

	/**
	 * @param nbreheureJustifie the nbreheureJustifie to set
	 */
	public void setNbreheureJustifie(int nbreheureJustifie) {
		this.nbreheureJustifie = nbreheureJustifie;
	}

	/**
	 * @return the nbreheureNJustifie
	 */
	public int getNbreheureNJustifie() {
		return nbreheureNJustifie;
	}

	/**
	 * @param nbreheureNJustifie the nbreheureNJustifie to set
	 */
	public void setNbreheureNJustifie(int nbreheureNJustifie) {
		this.nbreheureNJustifie = nbreheureNJustifie;
	}

	/**
	 * @return the sequence
	 */
	public Sequences getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Sequences sequence) {
		this.sequence = sequence;
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
	 * @return the avertConduite
	 */
	public boolean isAvertConduite() {
		return avertConduite;
	}

	/**
	 * @param avertConduite the avertConduite to set
	 */
	public void setAvertConduite(boolean avertConduite) {
		this.avertConduite = avertConduite;
	}

	/**
	 * @return the blameConduite
	 */
	public boolean isBlameConduite() {
		return blameConduite;
	}

	/**
	 * @param blameConduite the blameConduite to set
	 */
	public void setBlameConduite(boolean blameConduite) {
		this.blameConduite = blameConduite;
	}

	/**
	 * @return the conseilDiscipline
	 */
	public boolean isConseilDiscipline() {
		return conseilDiscipline;
	}

	/**
	 * @param conseilDiscipline the conseilDiscipline to set
	 */
	public void setConseilDiscipline(boolean conseilDiscipline) {
		this.conseilDiscipline = conseilDiscipline;
	}

	/**
	 * @return the consigne
	 */
	public int getConsigne() {
		return consigne;
	}

	/**
	 * @param consigne the consigne to set
	 */
	public void setConsigne(int consigne) {
		this.consigne = consigne;
	}

	/**
	 * @return the jourExclusion
	 */
	public int getJourExclusion() {
		return jourExclusion;
	}

	/**
	 * @param jourExclusion the jourExclusion to set
	 */
	public void setJourExclusion(int jourExclusion) {
		this.jourExclusion = jourExclusion;
	}
	
	

}
