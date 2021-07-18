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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
public class Operations implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idOperation;
	@NotNull
	@NotEmpty
	private String typeOperation;
	@NotNull
	private Double montantOperation;
	@NotNull
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateOperation;
	
	/*
	 * Association avec la table CompteInscription
	 ******************************************/
	@ManyToOne
	CompteInscription compteinscription;

	/**
	 * @param typeOperation
	 * @param montantOperation
	 * @param dateOperation
	 */
	public Operations(String typeOperation, Double montantOperation, Date dateOperation) {
		super();
		this.typeOperation = typeOperation;
		this.montantOperation = montantOperation;
		this.dateOperation = dateOperation;
	}

	/**
	 * 
	 */
	public Operations() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the idOperation
	 */
	public Long getIdOperation() {
		return idOperation;
	}

	/**
	 * @param idOperation the idOperation to set
	 */
	public void setIdOperation(Long idOperation) {
		this.idOperation = idOperation;
	}

	/**
	 * @return the typeOperation
	 */
	public String getTypeOperation() {
		return typeOperation;
	}

	/**
	 * @param typeOperation the typeOperation to set
	 */
	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}

	/**
	 * @return the montantOperation
	 */
	public Double getMontantOperation() {
		return montantOperation;
	}

	/**
	 * @param montantOperation the montantOperation to set
	 */
	public void setMontantOperation(Double montantOperation) {
		this.montantOperation = montantOperation;
	}

	/**
	 * @return the dateOperation
	 */
	public Date getDateOperation() {
		return dateOperation;
	}

	/**
	 * @param dateOperation the dateOperation to set
	 */
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	/**
	 * @return the compteinscription
	 */
	public CompteInscription getCompteinscription() {
		return compteinscription;
	}

	/**
	 * @param compteinscription the compteinscription to set
	 */
	public void setCompteinscription(CompteInscription compteinscription) {
		this.compteinscription = compteinscription;
	}

	
	
}
