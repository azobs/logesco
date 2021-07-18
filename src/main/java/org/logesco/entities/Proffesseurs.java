/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="proffesseurs")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="fonction", 
discriminatorType=DiscriminatorType.STRING, length=15)
public abstract class Proffesseurs extends Personnels implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotEmpty
	@Size(min = 2, max = 100)
	private String specialiteProf;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Cours
	 ******************************************/
	@OneToMany(mappedBy="proffesseur")
	Collection<Cours> listofCours;
	
	/*
	 * Association avec la table Classes
	 ******************************************/
	@OneToMany(mappedBy="proffesseur")
	Collection<Classes> listofClassesTitulaire;

	/**
	 * 
	 */
	public Proffesseurs() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param numcniPers
	 * @param datenaissPers
	 * @param diplomePers
	 * @param emailPers
	 * @param gradePers
	 * @param lieunaissPers
	 * @param nationalitePers
	 * @param nomsPers
	 * @param numtel1Pers
	 * @param numtel2Pers
	 * @param photoPers
	 * @param prenomsPers
	 * @param quartierPers
	 * @param sexePers
	 * @param statutPers
	 * @param villePers
	 * @param username
	 * @param password
	 * @param enabled
	 */
	public Proffesseurs(String numcniPers, Date datenaissPers, String diplomePers, String emailPers, String gradePers,
			String lieunaissPers, String nationalitePers, String nomsPers, String numtel1Pers, String numtel2Pers,
			String photoPers, String prenomsPers, String quartierPers, String sexePers, String statutPers,
			String villePers, String username, String password, boolean enabled, String specialiteProf) {
		
		super(numcniPers, datenaissPers, diplomePers, emailPers, gradePers, lieunaissPers, nationalitePers, nomsPers,
				numtel1Pers, numtel2Pers, photoPers, prenomsPers, quartierPers, sexePers, statutPers, villePers,
				username, password, enabled);
		this.specialiteProf = specialiteProf;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 * @param password
	 * @param etatCompteUsers
	 */
	public Proffesseurs(String username, String password, boolean etatCompteUsers) {
		super(username, password, etatCompteUsers);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the specialiteProf
	 */
	public String getSpecialiteProf() {
		return specialiteProf;
	}

	/**
	 * @param specialiteProf the specialiteProf to set
	 */
	public void setSpecialiteProf(String specialiteProf) {
		this.specialiteProf = specialiteProf;
	}

	/**
	 * @param specialiteProf
	 */
	public Proffesseurs(String specialiteProf) {
		super();
		this.specialiteProf = specialiteProf;
	}

	/**
	 * @return the listofCours
	 */
	public Collection<Cours> getListofCours() {
		return listofCours;
	}

	/**
	 * @param listofCours the listofCours to set
	 */
	public void setListofCours(Collection<Cours> listofCours) {
		this.listofCours = listofCours;
	}

	/**
	 * @return the listofClassesTitulaire
	 */
	public Collection<Classes> getListofClassesTitulaire() {
		return listofClassesTitulaire;
	}

	/**
	 * @param listofClassesTitulaire the listofClassesTitulaire to set
	 */
	public void setListofClassesTitulaire(Collection<Classes> listofClassesTitulaire) {
		this.listofClassesTitulaire = listofClassesTitulaire;
	}

	
	
	

}
