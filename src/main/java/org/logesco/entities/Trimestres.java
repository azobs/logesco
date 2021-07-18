/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@DiscriminatorValue("trimestre")
public class Trimestres extends Periodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private int numeroTrim;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table BulletinsTrim
	 ******************************************/
	@OneToMany(mappedBy="trimestre")
	Collection<BulletinsTrim> listofbulletinsTrim;
	
	/*
	 * Association avec la table Annee
	 ******************************************/
	@ManyToOne
	Annee annee;
	
	/*
	 * Association avec la table Sequences
	 ******************************************/
	@OneToMany(mappedBy="trimestre")
	Collection<Sequences> listofsequence;

	/**
	 * 
	 */
	public Trimestres() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param numeroTrim
	 */
	public Trimestres(int numeroTrim) {
		super();
		this.numeroTrim = numeroTrim;
	}

	/**
	 * @return the numeroTrim
	 */
	public int getNumeroTrim() {
		return numeroTrim;
	}

	/**
	 * @param numeroTrim the numeroTrim to set
	 */
	public void setNumeroTrim(int numeroTrim) {
		this.numeroTrim = numeroTrim;
	}

	/**
	 * @return the listofbulletinsTrim
	 */
	public Collection<BulletinsTrim> getListofbulletinsTrim() {
		return listofbulletinsTrim;
	}

	/**
	 * @param listofbulletinsTrim the listofbulletinsTrim to set
	 */
	public void setListofbulletinsTrim(Collection<BulletinsTrim> listofbulletinsTrim) {
		this.listofbulletinsTrim = listofbulletinsTrim;
	}

	/**
	 * @return the annee
	 */
	public Annee getAnnee() {
		return annee;
	}

	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(Annee annee) {
		this.annee = annee;
	}

	/**
	 * @return the listofsequence
	 */
	public Collection<Sequences> getListofsequence() {
		Comparator<Sequences> monComparator = new Comparator<Sequences>() {

			@Override
			public int compare(Sequences arg0, Sequences arg1) {
				int n = 0;
				if(arg0.getNumeroSeq()<arg1.getNumeroSeq()) n = -1;
				if(arg0.getNumeroSeq()>arg1.getNumeroSeq()) n = 1;
				return n;
			}
			
		};
		Collections.sort((List<Sequences>)this.listofsequence, monComparator);
		return listofsequence;
	}

	/**
	 * @param listofsequence the listofsequence to set
	 */
	public void setListofsequence(Collection<Sequences> listofsequence) {
		this.listofsequence = listofsequence;
	}

	

}
