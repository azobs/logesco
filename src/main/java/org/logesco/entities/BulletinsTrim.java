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
@DiscriminatorValue("trimestriel")
public class BulletinsTrim extends Bulletins implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Trimestres
	 ******************************************/
	@ManyToOne
	Trimestres trimestre; 
	
	/*
	 * Association avec la table BulletinsSeq
	 ******************************************/
	@OneToMany(mappedBy="bulletinTrim")
	Collection<BulletinsSeq> listofbulletinsSeq;
	
	/*
	 * Association avec la table BulletinsAn
	 ******************************************/
	@ManyToOne
	BulletinsAn bulletinAn;
	
	public BulletinsTrim(){
		
	}

	/**
	 * @return the trimestre
	 */
	public Trimestres getTrimestre() {
		return trimestre;
	}

	/**
	 * @param trimestre the trimestre to set
	 */
	public void setTrimestre(Trimestres trimestre) {
		this.trimestre = trimestre;
	}

	/**
	 * @return the listofbulletinsSeq
	 */
	public Collection<BulletinsSeq> getListofbulletinsSeq() {
		return listofbulletinsSeq;
	}

	/**
	 * @param listofbulletinsSeq the listofbulletinsSeq to set
	 */
	public void setListofbulletinsSeq(Collection<BulletinsSeq> listofbulletinsSeq) {
		this.listofbulletinsSeq = listofbulletinsSeq;
	}

	/**
	 * @return the bulletinAn
	 */
	public BulletinsAn getBulletinAn() {
		return bulletinAn;
	}

	/**
	 * @param bulletinAn the bulletinAn to set
	 */
	public void setBulletinAn(BulletinsAn bulletinAn) {
		this.bulletinAn = bulletinAn;
	}

}
