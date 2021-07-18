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
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@DiscriminatorValue("annee")
public class Annee extends Periodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotEmpty
	@Size(min = 9, max = 9)
	@Column(unique=true)
	private String intituleAnnee;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table BulletinAn
	 ******************************************/
	@OneToMany(mappedBy="annee")
	Collection<BulletinsAn> listofbulletinsAn;
	
	/*
	 * Association avec la table Trimestres
	 ******************************************/
	@OneToMany(mappedBy="annee")
	Collection<Trimestres> listoftrimestre;

	/**
	 * @param intituleAnnee
	 */
	public Annee(String intituleAnnee) {
		super();
		this.intituleAnnee = intituleAnnee;
	}

	/**
	 * 
	 */
	public Annee() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the intituleAnnee
	 */
	public String getIntituleAnnee() {
		return intituleAnnee;
	}

	/**
	 * @param intituleAnnee the intituleAnnee to set
	 */
	public void setIntituleAnnee(String intituleAnnee) {
		this.intituleAnnee = intituleAnnee;
	}

	/**
	 * @return the listofbulletinsAn
	 */
	public Collection<BulletinsAn> getListofbulletinsAn() {
		return listofbulletinsAn;
	}

	/**
	 * @param listofbulletinsAn the listofbulletinsAn to set
	 */
	public void setListofbulletinsAn(Collection<BulletinsAn> listofbulletinsAn) {
		this.listofbulletinsAn = listofbulletinsAn;
	}

	/**
	 * @return the listoftrimestre
	 */
	public Collection<Trimestres> getListoftrimestre() {
		Comparator<Trimestres> monComparator = new Comparator<Trimestres>() {

			@Override
			public int compare(Trimestres arg0, Trimestres arg1) {
				int n = 0;
				if(arg0.getNumeroTrim()<arg1.getNumeroTrim()) n = -1;
				if(arg0.getNumeroTrim()>arg1.getNumeroTrim()) n = 1;
				return n;
			}
			
		};
		Collections.sort((List<Trimestres>)this.listoftrimestre, monComparator);
		return listoftrimestre;
	}

	/**
	 * @param listoftrimestre the listoftrimestre to set
	 */
	public void setListoftrimestre(Collection<Trimestres> listoftrimestre) {
		this.listoftrimestre = listoftrimestre;
	}

	

}
