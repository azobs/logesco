/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="niveaux")
public class Niveaux implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idNiveaux;
	@NotNull
	@Column(unique=true)
	private String codeNiveaux;
	@NotNull
	@Column(unique=true)
	private int numeroOrdreNiveaux;
	
	@NotNull
	private String codeNiveaux_en;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Classes
	 ******************************************/
	@OneToMany(mappedBy="niveau")
	Collection<Classes> listofClasses;
	
	/*
	 * Association avec la table Cycles
	 ******************************************/
	@ManyToOne
	Cycles cycle;
	
	/*
	 * Association reflexive avec la table Niveaux
	 ******************************************/
	@OneToOne
	Niveaux niveau;  

	/**
	 * 
	 */
	public Niveaux() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codeNiveaux
	 */
	public Niveaux(String codeNiveaux) {
		super();
		this.codeNiveaux = codeNiveaux;
	}

	/**
	 * @return the idNiveaux
	 */
	public Long getIdNiveaux() {
		return idNiveaux;
	}

	/**
	 * @param idNiveaux the idNiveaux to set
	 */
	public void setIdNiveaux(Long idNiveaux) {
		this.idNiveaux = idNiveaux;
	}

	/**
	 * @return the codeNiveaux
	 */
	public String getCodeNiveaux() {
		return codeNiveaux;
	}

	/**
	 * @param codeNiveaux the codeNiveaux to set
	 */
	public void setCodeNiveaux(String codeNiveaux) {
		this.codeNiveaux = codeNiveaux;
	}

	

	/**
	 * @return the listofClasses
	 */
	public Collection<Classes> getListofClasses() {
		
		Comparator<Classes> monComparator = new Comparator<Classes>() {
			@Override
			public int compare(Classes c1, Classes c2) {
				int n=0;
				//System.err.println("YESYES ");
				if(c1.getCodeClasses().compareTo(c2.getCodeClasses()) < 0) n=-1;
				if(c1.getCodeClasses().compareTo(c2.getCodeClasses()) > 0) n=1;
				if(c1.getCodeClasses().compareTo(c2.getCodeClasses()) == 0) {
					if(c1.getSpecialite().getCodeSpecialite().compareTo(c2.getSpecialite().getCodeSpecialite()) < 0) n=-1;
					if(c1.getSpecialite().getCodeSpecialite().compareTo(c2.getSpecialite().getCodeSpecialite()) > 0) n=1;
					if(c1.getSpecialite().getCodeSpecialite().compareTo(c2.getSpecialite().getCodeSpecialite()) == 0) {
						if(c1.getNumeroClasses().compareTo(c2.getNumeroClasses()) < 0) n=-1;
						if(c1.getNumeroClasses().compareTo(c2.getNumeroClasses()) > 0) n=1;
					}
					
				}
				//System.err.println("YESYES "+n);
				return n;
			}
		};
		
		
		Collections.sort((List<Classes>)this.listofClasses, monComparator);
		
		return listofClasses;
	}

	/**
	 * @param listofClasses the listofClasses to set
	 */
	public void setListofClasses(Collection<Classes> listofClasses) {
		this.listofClasses = listofClasses;
	}

	/**
	 * @return the cycle
	 */
	public Cycles getCycle() {
		return cycle;
	}

	/**
	 * @param cycle the cycle to set
	 */
	public void setCycle(Cycles cycle) {
		this.cycle = cycle;
	}

	/**
	 * @return the numeroOrdreNiveaux
	 */
	public int getNumeroOrdreNiveaux() {
		return numeroOrdreNiveaux;
	}

	/**
	 * @param numeroOrdreNiveaux the numeroOrdreNiveaux to set
	 */
	public void setNumeroOrdreNiveaux(int numeroOrdreNiveaux) {
		this.numeroOrdreNiveaux = numeroOrdreNiveaux;
	}

	/**
	 * @return the niveau
	 */
	public Niveaux getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(Niveaux niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return the codeNiveaux_en
	 */
	public String getCodeNiveaux_en() {
		return codeNiveaux_en;
	}

	/**
	 * @param codeNiveaux_en the codeNiveaux_en to set
	 */
	public void setCodeNiveaux_en(String codeNiveaux_en) {
		this.codeNiveaux_en = codeNiveaux_en;
	}

	

}
