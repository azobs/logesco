/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class MatiereGroupe2Sequence1Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nom_g2;//nom du groupe
	private String discipline_g2;//nom du cours ou de la matiere
	private String prof_g2;//nom du prof qui enseigne le cours
	private Double note_seq_g2;//note finale obtenu par l'eleve pour la sequence
	private double coef_g2;//coefficient du cours
	private Double total_seq_g2; // champ calculé au niveau du bean dans le getter 
	private String rang_g2;//rang de l'élève qui correspond a la note obtenu
	private Double moy_classe_g2;//moyenne generale de la classe pour le cours 
	private String appreciation_g2;//champ calcule dans le getter et qui dependra de la note obtenu
	
	private Double notedernier_g2;//derniere note pour le cours
	private Double notepremier_g2;//derniere note pour le cours
	private Double tauxreussite_g2;//taux de reussite pour le cours
	
	private Double mg_classe_g2;// moyenne de l'eleve pour le groupe
	private double total_coef_g2; // champ calcule
	private Double total_g2; // champ calcule
	private String rang_total_g2;//rang de l'eleve pour le groupe
	private Double moyenne_g2; // champ calcule



	/**
	 * 
	 */
	public MatiereGroupe2Sequence1Bean() {
		// TODO Auto-generated constructor stub
	}



	/**
	 * @return the nom_g2
	 */
	public String getNom_g2() {
		return nom_g2;
	}



	/**
	 * @param nom_g2 the nom_g2 to set
	 */
	public void setNom_g2(String nom_g2) {
		this.nom_g2 = nom_g2;
	}



	/**
	 * @return the discipline_g2
	 */
	public String getDiscipline_g2() {
		return discipline_g2;
	}



	/**
	 * @param discipline_g2 the discipline_g2 to set
	 */
	public void setDiscipline_g2(String discipline_g2) {
		this.discipline_g2 = discipline_g2;
	}



	/**
	 * @return the prof_g2
	 */
	public String getProf_g2() {
		return prof_g2;
	}



	/**
	 * @param prof_g2 the prof_g2 to set
	 */
	public void setProf_g2(String prof_g2) {
		this.prof_g2 = prof_g2;
	}



	/**
	 * @return the note_seq_g2
	 */
	public Double getNote_seq_g2() {
		return note_seq_g2;
	}



	/**
	 * @param note_seq_g2 the note_seq_g2 to set
	 */
	public void setNote_seq_g2(Double note_seq_g2) {
		if(note_seq_g2>=0) this.note_seq_g2 = note_seq_g2;
	}



	/**
	 * @return the coef_g2
	 */
	public double getCoef_g2() {
		return coef_g2;
	}



	/**
	 * @param coef_g2 the coef_g2 to set
	 */
	public void setCoef_g2(double coef_g2) {
		if(coef_g2>0) this.coef_g2 = coef_g2;
	}



	/**
	 * @return the total_seq_g2
	 */
	public Double getTotal_seq_g2() {
		if(getNote_seq_g2()!=null){
			if(getNote_seq_g2()>=0) total_seq_g2 = getNote_seq_g2()*getCoef_g2();
		}
		return  total_seq_g2;
	}



	/**
	 * @param total_seq_g2 the total_seq_g2 to set
	 */
	public void setTotal_seq_g2(Double total_seq_g2) {
		if(total_seq_g2!=null){
			if(total_seq_g2>=0) this.total_seq_g2 = total_seq_g2;
		}
	}



	/**
	 * @return the rang_g2
	 */
	public String getRang_g2() {
		return rang_g2;
	}



	/**
	 * @param rang_g2 the rang_g2 to set
	 */
	public void setRang_g2(String rang_g2) {
		this.rang_g2 = rang_g2;
	}



	/**
	 * @return the moy_classe_g2
	 */
	public Double getMoy_classe_g2() {
		return moy_classe_g2;
	}



	/**
	 * @param moy_classe_g2 the moy_classe_g2 to set
	 */
	public void setMoy_classe_g2(Double moy_classe_g2) {
		if(moy_classe_g2>=0) this.moy_classe_g2 = moy_classe_g2;
	}



	/**
	 * @return the appreciation_g2
	 */
	public String getAppreciation_g2() {
		return appreciation_g2;
	}



	/**
	 * @param appreciation_g2 the appreciation_g2 to set
	 */
	public void setAppreciation_g2(String appreciation_g2) {
		this.appreciation_g2 = appreciation_g2;
	}



	/**
	 * @return the notedernier_g2
	 */
	public Double getNotedernier_g2() {
		return notedernier_g2;
	}



	/**
	 * @param notedernier_g2 the notedernier_g2 to set
	 */
	public void setNotedernier_g2(Double notedernier_g2) {
		if(notedernier_g2>=0) this.notedernier_g2 = notedernier_g2;
	}



	/**
	 * @return the notepremier_g2
	 */
	public Double getNotepremier_g2() {
		return notepremier_g2;
	}



	/**
	 * @param notepremier_g2 the notepremier_g2 to set
	 */
	public void setNotepremier_g2(Double notepremier_g2) {
		if(notepremier_g2>=0) this.notepremier_g2 = notepremier_g2;
	}



	/**
	 * @return the tauxreussite_g2
	 */
	public Double getTauxreussite_g2() {
		return tauxreussite_g2;
	}



	/**
	 * @param tauxreussite_g2 the tauxreussite_g2 to set
	 */
	public void setTauxreussite_g2(Double tauxreussite_g2) {
		this.tauxreussite_g2 = tauxreussite_g2;
	}



	/**
	 * @return the mg_classe_g2
	 */
	public Double getMg_classe_g2() {
		return mg_classe_g2;
	}



	/**
	 * @param mg_classe_g2 the mg_classe_g2 to set
	 */
	public void setMg_classe_g2(Double mg_classe_g2) {
		if(mg_classe_g2>=0) this.mg_classe_g2 = mg_classe_g2;
	}



	/**
	 * @return the total_coef_g2
	 */
	public double getTotal_coef_g2() {
		return total_coef_g2;
	}



	/**
	 * @param total_coef_g2 the total_coef_g2 to set
	 */
	public void setTotal_coef_g2(double total_coef_g2) {
		if(total_coef_g2>0) this.total_coef_g2 = total_coef_g2;
	}



	/**
	 * @return the total_g2
	 */
	public Double getTotal_g2() {
		return  total_g2;
	}



	/**
	 * @param total_g2 the total_g2 to set
	 */
	public void setTotal_g2(Double total_g2) {
		if(total_g2>=0) this.total_g2 = total_g2;
	}



	/**
	 * @return the rang_total_g2
	 */
	public String getRang_total_g2() {
		return rang_total_g2;
	}



	/**
	 * @param rang_total_g2 the rang_total_g2 to set
	 */
	public void setRang_total_g2(String rang_total_g2) {
		this.rang_total_g2 = rang_total_g2;
	}



	/**
	 * @return the moyenne_g2
	 */
	public Double getMoyenne_g2() {
		return moyenne_g2;
	}



	/**
	 * @param moyenne_g2 the moyenne_g2 to set
	 */
	public void setMoyenne_g2(Double moyenne_g2) {
		if(moyenne_g2>=0) this.moyenne_g2 = moyenne_g2;
	}
	
	

}
