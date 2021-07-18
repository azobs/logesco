/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * @author cedrickiadjeu
 *
 */
@Entity
@DiscriminatorValue("annuel")
public class BulletinsAn extends Bulletins implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table BulletinTrim
	 ******************************************/
	@OneToMany(mappedBy="bulletinAn")
	Collection<BulletinsTrim> listofbulletinsTrim;
	
	/*
	 * Association avec la table Annee
	 ******************************************/
	@ManyToOne
	Annee annee;
	
	public BulletinsAn(){
		
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

}
