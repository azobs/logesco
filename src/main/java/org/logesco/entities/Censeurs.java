/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="censeurs")
@DiscriminatorValue("censeurs")
public class Censeurs extends Proffesseurs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(unique=true) 
	private int numeroCens;
	
	public Censeurs(){
		
	}

	/**
	 * @param numeroCens
	 */
	public Censeurs(int numeroCens) {
		super();
		this.numeroCens = numeroCens;
	}

	/**
	 * @return the numeroCens
	 */
	public int getNumeroCens() {
		return numeroCens;
	}

	/**
	 * @param numeroCens the numeroCens to set
	 */
	public void setNumeroCens(int numeroCens) {
		this.numeroCens = numeroCens;
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
	 * @param int numeroCens
	 */
	public Censeurs(String numcniPers, java.util.Date datenaissPers, String diplomePers, String emailPers,
			String gradePers, String lieunaissPers, String nationalitePers, String nomsPers, String numtel1Pers,
			String numtel2Pers, String photoPers, String prenomsPers, String quartierPers, String sexePers,
			String statutPers, String villePers, String username, String password, boolean enabled,
			String specialiteProf, int numeroCens) {
		super(numcniPers, datenaissPers, diplomePers, emailPers, gradePers, lieunaissPers, nationalitePers, nomsPers,
				numtel1Pers, numtel2Pers, photoPers, prenomsPers, quartierPers, sexePers, statutPers, villePers, username,
				password, enabled, specialiteProf);
		this.numeroCens=numeroCens;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 * @param password
	 * @param etatCompteUsers
	 */
	public Censeurs(String username, String password, boolean etatCompteUsers) {
		super(username, password, etatCompteUsers);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param specialiteProf
	 */
	public Censeurs(String specialiteProf) {
		super(specialiteProf);
		// TODO Auto-generated constructor stub
	}

	

	
	
	
	
	

}
