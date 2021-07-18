/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="enseignants")
@DiscriminatorValue("enseignants")
public class Enseignants extends Proffesseurs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Enseignants(){
		
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
	 * @param etatCompteUsers
	 * @param specialiteProf
	 */
	public Enseignants(String numcniPers, Date datenaissPers, String diplomePers, String emailPers, String gradePers,
			String lieunaissPers, String nationalitePers, String nomsPers, String numtel1Pers, String numtel2Pers,
			String photoPers, String prenomsPers, String quartierPers, String sexePers, String statutPers,
			String villePers, String username, String password, boolean etatCompteUsers, String specialiteProf) {
		super(numcniPers, datenaissPers, diplomePers, emailPers, gradePers, lieunaissPers, nationalitePers, nomsPers,
				numtel1Pers, numtel2Pers, photoPers, prenomsPers, quartierPers, sexePers, statutPers, villePers, username,
				password, etatCompteUsers, specialiteProf);
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
