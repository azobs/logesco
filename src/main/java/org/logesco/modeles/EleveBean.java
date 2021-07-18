/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class EleveBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numero;
    private String matricule;
    private String noms_prenoms;
    private String sexe;
    private String statut;
    private String date_naissance;
    private String lieu_naissance;

	/**
	 * 
	 */
	public EleveBean() {
		// TODO Auto-generated constructor stub
	}

	public EleveBean(int numero, String matricule, String noms_prenoms, String sexe, String statut, String date_naissance, String lieu_naissance) {
        this.numero = numero;
        this.matricule = matricule;
        this.noms_prenoms = noms_prenoms;
        this.sexe = sexe;
        this.statut = statut;
        this.date_naissance = date_naissance;
        this.lieu_naissance = lieu_naissance;
    }

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * @return the noms_prenoms
	 */
	public String getNoms_prenoms() {
		return noms_prenoms;
	}

	/**
	 * @param noms_prenoms the noms_prenoms to set
	 */
	public void setNoms_prenoms(String noms_prenoms) {
		this.noms_prenoms = noms_prenoms;
	}

	/**
	 * @return the sexe
	 */
	public String getSexe() {
		return sexe;
	}

	/**
	 * @param sexe the sexe to set
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	/**
	 * @return the statut
	 */
	public String getStatut() {
		return statut;
	}

	/**
	 * @param statut the statut to set
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}

	/**
	 * @return the date_naissance
	 */
	public String getDate_naissance() {
		return date_naissance;
	}

	/**
	 * @param date_naissance the date_naissance to set
	 */
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}

	/**
	 * @return the lieu_naissance
	 */
	public String getLieu_naissance() {
		return lieu_naissance;
	}

	/**
	 * @param lieu_naissance the lieu_naissance to set
	 */
	public void setLieu_naissance(String lieu_naissance) {
		this.lieu_naissance = lieu_naissance;
	}
	
	
	
	
	
	
}
