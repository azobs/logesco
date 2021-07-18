/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
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
@Table(name="cycles")
public class Cycles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idCycles;
	@NotNull
	@Column(unique=true)
	private String codeCycles;
	
	@NotNull
	private String codeCycles_en;
	
	@NotNull
	@Column(unique=true)
	private int numeroOrdreCycles;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Niveaux
	 ******************************************/
	@OneToMany(mappedBy="cycle")
	Collection<Niveaux> listofNiveaux;

	/**
	 * 
	 */
	public Cycles() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codeCycles
	 */
	public Cycles(String codeCycles) {
		super();
		this.codeCycles = codeCycles;
	}

	/**
	 * @return the idCycles
	 */
	public Long getIdCycles() {
		return idCycles;
	}

	/**
	 * @param idCycles the idCycles to set
	 */
	public void setIdCycles(Long idCycles) {
		this.idCycles = idCycles;
	}

	/**
	 * @return the codeCycles
	 */
	public String getCodeCycles() {
		return codeCycles;
	}

	/**
	 * @param codeCycles the codeCycles to set
	 */
	public void setCodeCycles(String codeCycles) {
		this.codeCycles = codeCycles;
	}

	/**
	 * @return the listofNiveaux
	 */
	public Collection<Niveaux> getListofNiveaux() {
		return listofNiveaux;
	}

	/**
	 * @param listofNiveaux the listofNiveaux to set
	 */
	public void setListofNiveaux(Collection<Niveaux> listofNiveaux) {
		this.listofNiveaux = listofNiveaux;
	}

	/**
	 * @return the numeroOrdreCycles
	 */
	public int getNumeroOrdreCycles() {
		return numeroOrdreCycles;
	}

	/**
	 * @param numeroOrdreCycles the numeroOrdreCycles to set
	 */
	public void setNumeroOrdreCycles(int numeroOrdreCycles) {
		this.numeroOrdreCycles = numeroOrdreCycles;
	}

	/**
	 * @return the codeCycles_en
	 */
	public String getCodeCycles_en() {
		return codeCycles_en;
	}

	/**
	 * @param codeCycles_en the codeCycles_en to set
	 */
	public void setCodeCycles_en(String codeCycles_en) {
		this.codeCycles_en = codeCycles_en;
	}
	
	

}
