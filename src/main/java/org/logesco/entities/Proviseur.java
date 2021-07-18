/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="proviseur")
@DiscriminatorValue("proviseur")
public class Proviseur extends Proffesseurs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Proviseur(){
		
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
	 * @param specialiteProf
	 */
	public Proviseur(String numcniPers, Date datenaissPers, String diplomePers, String emailPers, String gradePers,
			String lieunaissPers, String nationalitePers, String nomsPers, String numtel1Pers, String numtel2Pers,
			String photoPers, String prenomsPers, String quartierPers, String sexePers, String statutPers,
			String villePers, String username, String password, boolean enabled, String specialiteProf) {
		
		super(numcniPers, datenaissPers, diplomePers, emailPers, gradePers, lieunaissPers, nationalitePers, nomsPers,
				numtel1Pers, numtel2Pers, photoPers, prenomsPers, quartierPers, sexePers, statutPers, villePers, username,
				password, enabled, specialiteProf);
		// TODO Auto-generated constructor stub
	}
	
	

}
