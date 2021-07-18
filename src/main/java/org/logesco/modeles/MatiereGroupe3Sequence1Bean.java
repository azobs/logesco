/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class MatiereGroupe3Sequence1Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nom_g3;//nom du groupe
	private String discipline_g3;//nom du cours ou de la matiere
	private String prof_g3;//nom du prof qui enseigne le cours
	private Double note_seq_g3;//note finale obtenu par l'eleve pour la sequence
	private double coef_g3;//coefficient du cours
	private Double total_seq_g3; // champ calculé au niveau du bean dans le getter 
	private String rang_g3;//rang de l'élève qui correspond a la note obtenu
	private Double moy_classe_g3;//moyenne generale de la classe pour le cours 
	private String appreciation_g3;//champ calcule dans le getter et qui dependra de la note obtenu
	
	private Double notedernier_g3;//derniere note pour le cours
	private Double notepremier_g3;//derniere note pour le cours
	private Double tauxreussite_g3;//taux de reussite pour le cours
	
	private Double mg_classe_g3;// moyenne de l'eleve pour le groupe
	private double total_coef_g3; // champ calcule
	private Double total_g3; // champ calcule
	private String rang_total_g3;//rang de l'eleve pour le groupe
	private Double moyenne_g3; // champ calcule




	/**
	 * 
	 */
	public MatiereGroupe3Sequence1Bean() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	/**
	 * @return the nom_g3
	 */
	public String getNom_g3() {
		return nom_g3;
	}





	/**
	 * @param nom_g3 the nom_g3 to set
	 */
	public void setNom_g3(String nom_g3) {
		this.nom_g3 = nom_g3;
	}





	/**
	 * @return the discipline_g3
	 */
	public String getDiscipline_g3() {
		return discipline_g3;
	}





	/**
	 * @param discipline_g3 the discipline_g3 to set
	 */
	public void setDiscipline_g3(String discipline_g3) {
		this.discipline_g3 = discipline_g3;
	}





	/**
	 * @return the prof_g3
	 */
	public String getProf_g3() {
		return prof_g3;
	}





	/**
	 * @param prof_g3 the prof_g3 to set
	 */
	public void setProf_g3(String prof_g3) {
		this.prof_g3 = prof_g3;
	}





	/**
	 * @return the note_seq_g3
	 */
	public Double getNote_seq_g3() {
		return note_seq_g3;
	}





	/**
	 * @param note_seq_g3 the note_seq_g3 to set
	 */
	public void setNote_seq_g3(Double note_seq_g3) {
		if(note_seq_g3>=0) this.note_seq_g3 = note_seq_g3;
	}





	/**
	 * @return the coef_g3
	 */
	public double getCoef_g3() {
		return coef_g3;
	}





	/**
	 * @param coef_g3 the coef_g3 to set
	 */
	public void setCoef_g3(double coef_g3) {
		if(coef_g3>0) this.coef_g3 = coef_g3;
	}





	/**
	 * @return the rang_g3
	 */
	public String getRang_g3() {
		return rang_g3;
	}





	/**
	 * @param rang_g3 the rang_g3 to set
	 */
	public void setRang_g3(String rang_g3) {
		this.rang_g3 = rang_g3;
	}





	/**
	 * @return the moy_classe_g3
	 */
	public Double getMoy_classe_g3() {
		return moy_classe_g3;
	}





	/**
	 * @param moy_classe_g3 the moy_classe_g3 to set
	 */
	public void setMoy_classe_g3(Double moy_classe_g3) {
		if(moy_classe_g3>=0) this.moy_classe_g3 = moy_classe_g3;
	}





	/**
	 * @return the notedernier_g3
	 */
	public Double getNotedernier_g3() {
		return notedernier_g3;
	}





	/**
	 * @param notedernier_g3 the notedernier_g3 to set
	 */
	public void setNotedernier_g3(Double notedernier_g3) {
		if(notedernier_g3>=0) this.notedernier_g3 = notedernier_g3;
	}





	/**
	 * @return the notepremier_g3
	 */
	public Double getNotepremier_g3() {
		return notepremier_g3;
	}





	/**
	 * @param notepremier_g3 the notepremier_g3 to set
	 */
	public void setNotepremier_g3(Double notepremier_g3) {
		if(notepremier_g3>=0) this.notepremier_g3 = notepremier_g3;
	}





	/**
	 * @return the tauxreussite_g3
	 */
	public Double getTauxreussite_g3() {
		return tauxreussite_g3;
	}





	/**
	 * @param tauxreussite_g3 the tauxreussite_g3 to set
	 */
	public void setTauxreussite_g3(Double tauxreussite_g3) {
		this.tauxreussite_g3 = tauxreussite_g3;
	}





	/**
	 * @return the mg_classe_g3
	 */
	public Double getMg_classe_g3() {
		return mg_classe_g3;
	}





	/**
	 * @param mg_classe_g3 the mg_classe_g3 to set
	 */
	public void setMg_classe_g3(Double mg_classe_g3) {
		if(mg_classe_g3>=0) this.mg_classe_g3 = mg_classe_g3;
	}





	/**
	 * @return the total_coef_g3
	 */
	public double getTotal_coef_g3() {
		return total_coef_g3;
	}





	/**
	 * @param total_coef_g3 the total_coef_g3 to set
	 */
	public void setTotal_coef_g3(double total_coef_g3) {
		if(total_coef_g3>0) this.total_coef_g3 = total_coef_g3;
	}





	/**
	 * @return the total_g3
	 */
	public Double getTotal_g3() {
		return total_g3;
	}





	/**
	 * @param total_g3 the total_g3 to set
	 */
	public void setTotal_g3(Double total_g3) {
		if(total_g3>=0) this.total_g3 = total_g3;
	}





	/**
	 * @return the rang_total_g3
	 */
	public String getRang_total_g3() {
		return rang_total_g3;
	}





	/**
	 * @param rang_total_g3 the rang_total_g3 to set
	 */
	public void setRang_total_g3(String rang_total_g3) {
		this.rang_total_g3 = rang_total_g3;
	}





	/**
	 * @return the moyenne_g3
	 */
	public Double getMoyenne_g3() {
		return moyenne_g3;
	}





	/**
	 * @param moyenne_g3 the moyenne_g3 to set
	 */
	public void setMoyenne_g3(Double moyenne_g3) {
		if(moyenne_g3>=0) this.moyenne_g3 = moyenne_g3;
	}





	/**
	 * @param total_seq_g3 the total_seq_g3 to set
	 */
	public void setTotal_seq_g3(Double total_seq_g3) {
		if(total_seq_g3!=null){
			if(total_seq_g3>=0) this.total_seq_g3 = total_seq_g3;
		}
	}





	/**
	 * @param appreciation_g3 the appreciation_g3 to set
	 */
	public void setAppreciation_g3(String appreciation_g3) {
		this.appreciation_g3 = appreciation_g3;
	}





	/**
	 * @return the total_seq_g3
	 */
	public Double getTotal_seq_g3() {
		if(getNote_seq_g3()!=null){
			if(getNote_seq_g3()>=0) total_seq_g3 = getNote_seq_g3()*getCoef_g3();
		}
		return  total_seq_g3;
	}
	
	/**
	 * @return the appreciation_g3
	 */
	public String getAppreciation_g3() {
		return appreciation_g3;
	}


	
}
