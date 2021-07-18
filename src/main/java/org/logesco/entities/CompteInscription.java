/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="compteinscription")
public class CompteInscription implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idCompteinscription;
	@NotNull
	private Double montant;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Operations
	 ******************************************/
	@OneToMany(mappedBy="compteinscription")
	Collection<Operations> listofOperation;

	/**
	 * 
	 */
	public CompteInscription() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param montant
	 */
	public CompteInscription(Double montant) {
		super();
		this.montant = montant;
	}

	/**
	 * @return the idCompteinscription
	 */
	public Long getIdCompteinscription() {
		return idCompteinscription;
	}

	/**
	 * @param idCompteinscription the idCompteinscription to set
	 */
	public void setIdCompteinscription(Long idCompteinscription) {
		this.idCompteinscription = idCompteinscription;
	}

	/**
	 * @return the montant
	 */
	public Double getMontant() {
		return montant;
	}

	/**
	 * @param montant the montant to set
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	/**
	 * @return the listofOperation
	 */
	public Collection<Operations> getListofOperation() {
		return listofOperation;
	}

	/**
	 * @param listofOperation the listofOperation to set
	 */
	public void setListofOperation(Collection<Operations> listofOperation) {
		this.listofOperation = listofOperation;
	}

	
	
}
