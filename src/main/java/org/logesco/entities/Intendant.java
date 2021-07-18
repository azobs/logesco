/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name="intendant")
@DiscriminatorValue("intendant")
public class Intendant extends Proffesseurs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(unique=true)
	private int numeroInt;

	/**
	 * 
	 */
	public Intendant() {
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
	 * @param etatCompteUsers
	 */
	public Intendant(String numcniPers, Date datenaissPers, String diplomePers, String emailPers, String gradePers,
			String lieunaissPers, String nationalitePers, String nomsPers, String numtel1Pers, String numtel2Pers,
			String photoPers, String prenomsPers, String quartierPers, String sexePers, String statutPers,
			String villePers, String username, String password, boolean etatCompteUsers, String specialiteProf, 
			int numeroInt) {
		super(numcniPers, datenaissPers, diplomePers, emailPers, gradePers, lieunaissPers, nationalitePers, nomsPers,
				numtel1Pers, numtel2Pers, photoPers, prenomsPers, quartierPers, sexePers, statutPers, villePers,
				username, password, etatCompteUsers, specialiteProf);
		this.numeroInt = numeroInt;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 * @param password
	 * @param etatCompteUsers
	 */
	public Intendant(String username, String password, boolean etatCompteUsers) {
		super(username, password, etatCompteUsers);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param numeroInt
	 */
	public Intendant(int numeroInt) {
		super();
		this.numeroInt = numeroInt;
	}
	
	

	/**
	 * @return the numeroInt
	 */
	public int getNumeroInt() {
		return numeroInt;
	}

	/**
	 * @param numeroInt the numeroInt to set
	 */
	public void setNumeroInt(int numeroInt) {
		this.numeroInt = numeroInt;
	}

	
	

}
