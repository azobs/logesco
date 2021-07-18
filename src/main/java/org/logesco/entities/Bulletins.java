/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="bulletins")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typeBulletins", 
discriminatorType=DiscriminatorType.STRING, length=15)
public abstract class Bulletins implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idBulletins;
	private String decisionBulletins;//C'est en fait un resumé  des decisions prises lors du conseil de classe. 
	private double moyenneBulletins;
	private double totalcoef;
	private double totalnotecoef;
	private int rangBulletins;

	/****
	 * Ajout du 19-08-2018
	 */
	private boolean avertconduiteBulletins;
	private boolean blameconduiteBulletins;
	private int nbreheureconsigneBulletins;
	private int nbrejoursexclusionBulletins;
	
	
	public Bulletins(){
		
	}

	/**
	 * @param decisionBulletins
	 * @param moyenneBulletins
	 * @param rangBulletins
	 */
	public Bulletins(String decisionBulletins, double moyenneBulletins, int rangBulletins) {
		super();
		this.decisionBulletins = decisionBulletins;
		this.moyenneBulletins = moyenneBulletins;
		this.rangBulletins = rangBulletins;
	}

	/**
	 * @return the idBulletins
	 */
	public Long getIdBulletins() {
		return idBulletins;
	}

	/**
	 * @param idBulletins the idBulletins to set
	 */
	public void setIdBulletins(Long idBulletins) {
		this.idBulletins = idBulletins;
	}

	/**
	 * @return the decisionBulletins
	 */
	public String getDecisionBulletins() {
		return decisionBulletins;
	}

	/**
	 * @param decisionBulletins the decisionBulletins to set
	 */
	public void setDecisionBulletins(String decisionBulletins) {
		this.decisionBulletins = decisionBulletins;
	}

	/**
	 * @return the moyenneBulletins
	 */
	public double getMoyenneBulletins() {
		return moyenneBulletins;
	}

	/**
	 * @param moyenneBulletins the moyenneBulletins to set
	 */
	public void setMoyenneBulletins(double moyenneBulletins) {
		this.moyenneBulletins = moyenneBulletins;
	}

	/**
	 * @return the rangBulletins
	 */
	public int getRangBulletins() {
		return rangBulletins;
	}

	/**
	 * @param rangBulletins the rangBulletins to set
	 */
	public void setRangBulletins(int rangBulletins) {
		this.rangBulletins = rangBulletins;
	}

	/**
	 * @return the avertconduiteBulletins
	 */
	public boolean isAvertconduiteBulletins() {
		return avertconduiteBulletins;
	}

	/**
	 * @param avertconduiteBulletins the avertconduiteBulletins to set
	 */
	public void setAvertconduiteBulletins(boolean avertconduiteBulletins) {
		this.avertconduiteBulletins = avertconduiteBulletins;
	}

	/**
	 * @return the blameconduiteBulletins
	 */
	public boolean isBlameconduiteBulletins() {
		return blameconduiteBulletins;
	}

	/**
	 * @param blameconduiteBulletins the blameconduiteBulletins to set
	 */
	public void setBlameconduiteBulletins(boolean blameconduiteBulletins) {
		this.blameconduiteBulletins = blameconduiteBulletins;
	}

	/**
	 * @return the nbreheureconsigneBulletins
	 */
	public int getNbreheureconsigneBulletins() {
		return nbreheureconsigneBulletins;
	}

	/**
	 * @param nbreheureconsigneBulletins the nbreheureconsigneBulletins to set
	 */
	public void setNbreheureconsigneBulletins(int nbreheureconsigneBulletins) {
		this.nbreheureconsigneBulletins = nbreheureconsigneBulletins;
	}

	/**
	 * @return the nbrejoursexclusionBulletins
	 */
	public int getNbrejoursexclusionBulletins() {
		return nbrejoursexclusionBulletins;
	}

	/**
	 * @param nbrejoursexclusionBulletins the nbrejoursexclusionBulletins to set
	 */
	public void setNbrejoursexclusionBulletins(int nbrejoursexclusionBulletins) {
		this.nbrejoursexclusionBulletins = nbrejoursexclusionBulletins;
	}

	/**
	 * @return the totalcoef
	 */
	public double getTotalcoef() {
		return totalcoef;
	}

	/**
	 * @param totalcoef the totalcoef to set
	 */
	public void setTotalcoef(double totalcoef) {
		this.totalcoef = totalcoef;
	}

	/**
	 * @return the totalnotecoef
	 */
	public double getTotalnotecoef() {
		return totalnotecoef;
	}

	/**
	 * @param totalnotecoef the totalnotecoef to set
	 */
	public void setTotalnotecoef(double totalnotecoef) {
		this.totalnotecoef = totalnotecoef;
	}

	
	
	
	
	

}
