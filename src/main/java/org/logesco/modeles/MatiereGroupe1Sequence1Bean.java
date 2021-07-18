/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class MatiereGroupe1Sequence1Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nom_g1;//nom du groupe
	private String discipline_g1;//nom du cours ou de la matiere
	private String prof_g1;//nom du prof qui enseigne le cours
	private Double note_seq_g1;//note finale obtenu par l'eleve pour la sequence
	private double coef_g1;//coefficient du cours
	private Double total_seq_g1; // champ calculé au niveau du bean dans le getter 
	private String rang_g1;//rang de l'élève qui correspond a la note obtenu
	private Double moy_classe_g1;//moyenne de l'eleve pour le groupe 
	private String appreciation_g1;//champ calcule dans le getter et qui dependra de la note obtenu
	
	private Double notedernier_g1;//derniere note pour le cours
	private Double notepremier_g1;//derniere note pour le cours
	private Double tauxreussite_g1;//taux de reussite pour le cours
	
	private Double mg_classe_g1;// moyenne generale pour le groupe
	private double total_coef_g1; // Somme des coef des cours du groupe
	private Double total_g1; // Total des notes du groupe
	private String rang_total_g1;//rang de l'eleve pour le groupe
	private Double moyenne_g1; // moyenne de l'eleve pour le groupe

	/**
	 * 
	 */
	public MatiereGroupe1Sequence1Bean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the nom_g1
	 */
	public String getNom_g1() {
		return nom_g1;
	}

	/**
	 * @param nom_g1 the nom_g1 to set
	 */
	public void setNom_g1(String nom_g1) {
		this.nom_g1 = nom_g1;
	}

	/**
	 * @return the discipline_g1
	 */
	public String getDiscipline_g1() {
		return discipline_g1;
	}

	/**
	 * @param discipline_g1 the discipline_g1 to set
	 */
	public void setDiscipline_g1(String discipline_g1) {
		this.discipline_g1 = discipline_g1;
	}

	/**
	 * @return the prof_g1
	 */
	public String getProf_g1() {
		return prof_g1;
	}

	/**
	 * @param prof_g1 the prof_g1 to set
	 */
	public void setProf_g1(String prof_g1) {
		this.prof_g1 = prof_g1;
	}

	/**
	 * @return the note_seq_g1
	 */
	public Double getNote_seq_g1() {
		return note_seq_g1;
	}

	/**
	 * @param note_seq_g1 the note_seq_g1 to set
	 */
	public void setNote_seq_g1(Double note_seq_g1) {
		if(note_seq_g1>=0) this.note_seq_g1 = note_seq_g1;
	}

	/**
	 * @return the coef_g1
	 */
	public double getCoef_g1() {
		return coef_g1;
	}

	/**
	 * @param coef_g1 the coef_g1 to set
	 */
	public void setCoef_g1(double coef_g1) {
		if(coef_g1>0) this.coef_g1 = coef_g1;
	}

	/**
	 * @return the total_seq_g1
	 */
	public Double getTotal_seq_g1() {
		if(getNote_seq_g1()!=null){
			if(getNote_seq_g1()>=0) total_seq_g1 = getNote_seq_g1()*getCoef_g1();
		}
		return  total_seq_g1;
	}

	/**
	 * @param total_seq_g1 the total_seq_g1 to set
	 */
	public void setTotal_seq_g1(Double total_seq_g1) {
		if(total_seq_g1!=null){
			if(total_seq_g1>=0) this.total_seq_g1 = total_seq_g1;
		}
	}

	/**
	 * @return the rang_g1
	 */
	public String getRang_g1() {
		return rang_g1;
	}

	/**
	 * @param rang_g1 the rang_g1 to set
	 */
	public void setRang_g1(String rang_g1) {
		this.rang_g1 = rang_g1;
	}

	/**
	 * @return the moy_classe_g1
	 */
	public Double getMoy_classe_g1() {
		return moy_classe_g1;
	}

	/**
	 * @param moy_classe_g1 the moy_classe_g1 to set
	 */
	public void setMoy_classe_g1(Double moy_classe_g1) {
		if(moy_classe_g1>=0) this.moy_classe_g1 = moy_classe_g1;
	}

	/**
	 * @return the appreciation_g1
	 */
	public String getAppreciation_g1() {
		return appreciation_g1;
	}

	/**
	 * @param appreciation_g1 the appreciation_g1 to set
	 */
	public void setAppreciation_g1(String appreciation_g1) {
		this.appreciation_g1 = appreciation_g1;
	}
	
	

	/**
	 * @return the notedernier_g1
	 */
	public Double getNotedernier_g1() {
		return notedernier_g1;
	}

	/**
	 * @param notedernier_g1 the notedernier_g1 to set
	 */
	public void setNotedernier_g1(Double notedernier_g1) {
		if(notedernier_g1>=0) this.notedernier_g1 = notedernier_g1;
	}

	/**
	 * @return the notepremier_g1
	 */
	public Double getNotepremier_g1() {
		return notepremier_g1;
	}

	/**
	 * @param notepremier_g1 the notepremier_g1 to set
	 */
	public void setNotepremier_g1(Double notepremier_g1) {
		if(notepremier_g1>=0) this.notepremier_g1 = notepremier_g1;
	}

	/**
	 * @return the tauxreussite_g1
	 */
	public Double getTauxreussite_g1() {
		return tauxreussite_g1;
	}

	/**
	 * @param tauxreussite_g1 the tauxreussite_g1 to set
	 */
	public void setTauxreussite_g1(Double tauxreussite_g1) {
		this.tauxreussite_g1 = tauxreussite_g1;
	}

	/**
	 * @return the mg_classe_g1
	 */
	public Double getMg_classe_g1() {
		return mg_classe_g1;
	}

	/**
	 * @param mg_classe_g1 the mg_classe_g1 to set
	 */
	public void setMg_classe_g1(Double mg_classe_g1) {
		if(mg_classe_g1>=0) this.mg_classe_g1 = mg_classe_g1;
	}

	/**
	 * @return the total_coef_g1
	 */
	public double getTotal_coef_g1() {
		return total_coef_g1;
	}

	/**
	 * @param total_coef_g1 the total_coef_g1 to set
	 */
	public void setTotal_coef_g1(double total_coef_g1) {
		if(total_coef_g1>0) this.total_coef_g1 = total_coef_g1;
	}

	/**
	 * @return the total_g1
	 */
	public Double getTotal_g1() {
		return total_g1;
	}

	/**
	 * @param total_g1 the total_g1 to set
	 */
	public void setTotal_g1(Double total_g1) {
		if(total_g1>=0) this.total_g1 = total_g1;
	}

	/**
	 * @return the rang_total_g1
	 */
	public String getRang_total_g1() {
		return rang_total_g1;
	}

	/**
	 * @param rang_total_g1 the rang_total_g1 to set
	 */
	public void setRang_total_g1(String rang_total_g1) {
		this.rang_total_g1 = rang_total_g1;
	}

	/**
	 * @return the moyenne_g1
	 */
	public Double getMoyenne_g1() {
		return moyenne_g1;
	}

	/**
	 * @param moyenne_g1 the moyenne_g1 to set
	 */
	public void setMoyenne_g1(Double moyenne_g1) {
		if(moyenne_g1>=0) this.moyenne_g1 = moyenne_g1;
	}
	
	
	

}
