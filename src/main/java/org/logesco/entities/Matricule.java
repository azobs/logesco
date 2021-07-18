/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@DiscriminatorValue("matricule")
public class Matricule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long numero;
	@NotNull
	private int valeur;

	/**
	 * 
	 */
	public Matricule() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the numero
	 */
	public Long getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Long numero) {
		this.numero = numero;
	}

	/**
	 * @return the valeur
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * @param valeur the valeur to set
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	

}
