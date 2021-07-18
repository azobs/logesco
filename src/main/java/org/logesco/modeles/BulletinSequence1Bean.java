/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

/**
 * @author cedrickiadjeu
 *
 */
public class BulletinSequence1Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

//	Declaration des proprietes du bulletin
    private String ministere_fr;
    private String ministere_en;
    private String delegation_fr;
    private String delegation_en;
    private String etablissement_fr;
    private String etablissement_en;
    private String adresse;
    private String annee_scolaire_fr;
    private String annee_scolaire_en;
    private String devise_fr;
    private String devise_en;
    private String ville;
    private String prof_principal;
    private String numero;
    private String sexe;
    private String nom_eleve;
    private String prenom_eleve;
    private String date_naissance_eleve;
    private String lieu_naissance_eleve;
    private String classe_eleve;
    private String matricule_eleve;
    private int effectif_classe;
    private int effectif_presente;
    private String redoublant;
    private double total_coef;
    private Double total_points;
    private Double result_tt_points;
    private Double moyenne_seq;
    private double result_tt_coef;
    private String result_rang_seq;
    private Double result_moy_seq;
    private Double moy_premier;
    private Double moy_dernier;
    private int nbre_moyennes;
    private Double taux_reussite;
    private Double moy_gen_classe;
    private int absence_NJ;
    private int absence_J;
    private String consigne;
    private String exclusion;
    private String avertissement;
    private String blame_conduite;
    private Double r_moy_seq1;
    private String r_rang_seq;
    private String appreciation;
    private String tableau_hon;
    private String tableau_enc;
    private String tableau_fel;
    private String decision_conseil;
    private String effort_matiere1;
    private String effort_matiere2;
    private String effort_matiere3;
    private String dateJour;
    private String photo;

//  Listes alimentant les groupes de matières du bulletin sequentiel
    private List<MatiereGroupe1Sequence1Bean> matieresSequence1Groupe1;
    private List<MatiereGroupe2Sequence1Bean> matieresSequence1Groupe2;
    private List<MatiereGroupe3Sequence1Bean> matieresSequence1Groupe3;


	/**
	 * 
	 */
	public BulletinSequence1Bean() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the ministere_fr
	 */
	public String getMinistere_fr() {
		return ministere_fr;
	}


	/**
	 * @param ministere_fr the ministere_fr to set
	 */
	public void setMinistere_fr(String ministere_fr) {
		this.ministere_fr = ministere_fr;
	}


	/**
	 * @return the ministere_en
	 */
	public String getMinistere_en() {
		return ministere_en;
	}


	/**
	 * @param ministere_en the ministere_en to set
	 */
	public void setMinistere_en(String ministere_en) {
		this.ministere_en = ministere_en;
	}


	/**
	 * @return the delegation_fr
	 */
	public String getDelegation_fr() {
		return delegation_fr;
	}


	/**
	 * @param delegation_fr the delegation_fr to set
	 */
	public void setDelegation_fr(String delegation_fr) {
		this.delegation_fr = delegation_fr;
	}


	/**
	 * @return the delegation_en
	 */
	public String getDelegation_en() {
		return delegation_en;
	}


	/**
	 * @param delegation_en the delegation_en to set
	 */
	public void setDelegation_en(String delegation_en) {
		this.delegation_en = delegation_en;
	}


	/**
	 * @return the etablissement_fr
	 */
	public String getEtablissement_fr() {
		return etablissement_fr;
	}


	/**
	 * @param etablissement_fr the etablissement_fr to set
	 */
	public void setEtablissement_fr(String etablissement_fr) {
		this.etablissement_fr = etablissement_fr;
	}


	/**
	 * @return the etablissement_en
	 */
	public String getEtablissement_en() {
		return etablissement_en;
	}


	/**
	 * @param etablissement_en the etablissement_en to set
	 */
	public void setEtablissement_en(String etablissement_en) {
		this.etablissement_en = etablissement_en;
	}


	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}


	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	/**
	 * @return the annee_scolaire_fr
	 */
	public String getAnnee_scolaire_fr() {
		return annee_scolaire_fr;
	}


	/**
	 * @param annee_scolaire_fr the annee_scolaire_fr to set
	 */
	public void setAnnee_scolaire_fr(String annee_scolaire_fr) {
		this.annee_scolaire_fr = annee_scolaire_fr;
	}


	/**
	 * @return the annee_scolaire_en
	 */
	public String getAnnee_scolaire_en() {
		return annee_scolaire_en;
	}


	/**
	 * @param annee_scolaire_en the annee_scolaire_en to set
	 */
	public void setAnnee_scolaire_en(String annee_scolaire_en) {
		this.annee_scolaire_en = annee_scolaire_en;
	}


	/**
	 * @return the devise_fr
	 */
	public String getDevise_fr() {
		return devise_fr;
	}


	/**
	 * @param devise_fr the devise_fr to set
	 */
	public void setDevise_fr(String devise_fr) {
		this.devise_fr = devise_fr;
	}


	/**
	 * @return the devise_en
	 */
	public String getDevise_en() {
		return devise_en;
	}


	/**
	 * @param devise_en the devise_en to set
	 */
	public void setDevise_en(String devise_en) {
		this.devise_en = devise_en;
	}


	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}


	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}


	/**
	 * @return the prof_principal
	 */
	public String getProf_principal() {
		return prof_principal;
	}


	/**
	 * @param prof_principal the prof_principal to set
	 */
	public void setProf_principal(String prof_principal) {
		this.prof_principal = prof_principal;
	}


	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}


	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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
	 * @return the nom_eleve
	 */
	public String getNom_eleve() {
		return nom_eleve;
	}


	/**
	 * @param nom_eleve the nom_eleve to set
	 */
	public void setNom_eleve(String nom_eleve) {
		this.nom_eleve = nom_eleve;
	}


	/**
	 * @return the prenom_eleve
	 */
	public String getPrenom_eleve() {
		return prenom_eleve;
	}


	/**
	 * @param prenom_eleve the prenom_eleve to set
	 */
	public void setPrenom_eleve(String prenom_eleve) {
		this.prenom_eleve = prenom_eleve;
	}


	/**
	 * @return the date_naissance_eleve
	 */
	public String getDate_naissance_eleve() {
		return date_naissance_eleve;
	}


	/**
	 * @param date_naissance_eleve the date_naissance_eleve to set
	 */
	public void setDate_naissance_eleve(String date_naissance_eleve) {
		this.date_naissance_eleve = date_naissance_eleve;
	}


	/**
	 * @return the lieu_naissance_eleve
	 */
	public String getLieu_naissance_eleve() {
		return lieu_naissance_eleve;
	}


	/**
	 * @param lieu_naissance_eleve the lieu_naissance_eleve to set
	 */
	public void setLieu_naissance_eleve(String lieu_naissance_eleve) {
		this.lieu_naissance_eleve = lieu_naissance_eleve;
	}


	/**
	 * @return the classe_eleve
	 */
	public String getClasse_eleve() {
		return classe_eleve;
	}


	/**
	 * @param classe_eleve the classe_eleve to set
	 */
	public void setClasse_eleve(String classe_eleve) {
		this.classe_eleve = classe_eleve;
	}


	/**
	 * @return the matricule_eleve
	 */
	public String getMatricule_eleve() {
		return matricule_eleve;
	}


	/**
	 * @param matricule_eleve the matricule_eleve to set
	 */
	public void setMatricule_eleve(String matricule_eleve) {
		this.matricule_eleve = matricule_eleve;
	}


	/**
	 * @return the effectif_classe
	 */
	public int getEffectif_classe() {
		return effectif_classe;
	}


	/**
	 * @param effectif_classe the effectif_classe to set
	 */
	public void setEffectif_classe(int effectif_classe) {
		this.effectif_classe = effectif_classe;
	}


	/**
	 * @return the effectif_presente
	 */
	public int getEffectif_presente() {
		return effectif_presente;
	}


	/**
	 * @param effectif_presente the effectif_presente to set
	 */
	public void setEffectif_presente(int effectif_presente) {
		this.effectif_presente = effectif_presente;
	}


	/**
	 * @return the redoublant
	 */
	public String getRedoublant() {
		return redoublant;
	}


	/**
	 * @param redoublant the redoublant to set
	 */
	public void setRedoublant(String redoublant) {
		this.redoublant = redoublant;
	}


	/**
	 * @return the total_coef
	 */
	public double getTotal_coef() {
		return total_coef;
	}


	/**
	 * @param total_coef the total_coef to set
	 */
	public void setTotal_coef(double total_coef) {
		this.total_coef = total_coef;
	}


	/**
	 * @return the total_points
	 */
	public Double getTotal_points() {
		return total_points;
	}


	/**
	 * @param total_points the total_points to set
	 */
	public void setTotal_points(Double total_points) {
		this.total_points = total_points;
	}


	/**
	 * @return the result_tt_points
	 */
	public Double getResult_tt_points() {
		return result_tt_points;
	}


	/**
	 * @param result_tt_points the result_tt_points to set
	 */
	public void setResult_tt_points(Double result_tt_points) {
		this.result_tt_points = result_tt_points;
	}


	/**
	 * @return the moyenne_seq
	 */
	public Double getMoyenne_seq() {
		moyenne_seq = getTotal_points() / getTotal_coef();
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moyenne_seq =df.parse(df.format(moyenne_seq)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return moyenne_seq;
	}


	/**
	 * @param moyenne_seq the moyenne_seq to set
	 */
	public void setMoyenne_seq() {
		this.moyenne_seq = getTotal_points() / getTotal_coef();
	}


	/**
	 * @return the result_tt_coef
	 */
	public double getResult_tt_coef() {
		return result_tt_coef;
	}


	/**
	 * @param result_tt_coef the result_tt_coef to set
	 */
	public void setResult_tt_coef(double result_tt_coef) {
		this.result_tt_coef = getTotal_coef();
	}


	/**
	 * @return the result_rang_seq
	 */
	public String getResult_rang_seq() {
		return result_rang_seq;
	}


	/**
	 * @param result_rang_seq the result_rang_seq to set
	 */
	public void setResult_rang_seq(String result_rang_seq) {
		this.result_rang_seq = result_rang_seq;
	}


	/**
	 * @return the result_moy_seq
	 */
	public Double getResult_moy_seq() {
		return result_moy_seq;
	}


	/**
	 * @param result_moy_seq the result_moy_seq to set
	 */
	public void setResult_moy_seq() {
		this.result_moy_seq = getMoyenne_seq();
	}


	/**
	 * @return the moy_premier
	 */
	public Double getMoy_premier() {
		return moy_premier;
	}


	/**
	 * @param moy_premier the moy_premier to set
	 */
	public void setMoy_premier(Double moy_premier) {
		this.moy_premier = moy_premier;
	}


	/**
	 * @return the moy_dernier
	 */
	public Double getMoy_dernier() {
		return moy_dernier;
	}


	/**
	 * @param moy_dernier the moy_dernier to set
	 */
	public void setMoy_dernier(Double moy_dernier) {
		this.moy_dernier = moy_dernier;
	}


	/**
	 * @return the nbre_moyennes
	 */
	public int getNbre_moyennes() {
		return nbre_moyennes;
	}


	/**
	 * @param nbre_moyennes the nbre_moyennes to set
	 */
	public void setNbre_moyennes(int nbre_moyennes) {
		this.nbre_moyennes = nbre_moyennes;
	}


	/**
	 * @return the taux_reussite
	 */
	public Double getTaux_reussite() {
		return taux_reussite;
	}


	/**
	 * @param taux_reussite the taux_reussite to set
	 */
	public void setTaux_reussite(Double taux_reussite) {
		this.taux_reussite = taux_reussite;
	}


	/**
	 * @return the moy_gen_classe
	 */
	public Double getMoy_gen_classe() {
		return moy_gen_classe;
	}


	/**
	 * @param moy_gen_classe the moy_gen_classe to set
	 */
	public void setMoy_gen_classe(Double moy_gen_classe) {
		this.moy_gen_classe = moy_gen_classe;
	}


	/**
	 * @return the absence_NJ
	 */
	public int getAbsence_NJ() {
		return absence_NJ;
	}


	/**
	 * @param absence_NJ the absence_NJ to set
	 */
	public void setAbsence_NJ(int absence_NJ) {
		this.absence_NJ = absence_NJ;
	}


	/**
	 * @return the absence_J
	 */
	public int getAbsence_J() {
		return absence_J;
	}


	/**
	 * @param absence_J the absence_J to set
	 */
	public void setAbsence_J(int absence_J) {
		this.absence_J = absence_J;
	}


	/**
	 * @return the consigne
	 */
	public String getConsigne() {
		return consigne;
	}


	/**
	 * @param consigne the consigne to set
	 */
	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}


	/**
	 * @return the exclusion
	 */
	public String getExclusion() {
		return exclusion;
	}


	/**
	 * @param exclusion the exclusion to set
	 */
	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
	}


	/**
	 * @return the avertissement
	 */
	public String getAvertissement() {
		return avertissement;
	}


	/**
	 * @param avertissement the avertissement to set
	 */
	public void setAvertissement(String avertissement) {
		this.avertissement = avertissement;
	}


	/**
	 * @return the blame_conduite
	 */
	public String getBlame_conduite() {
		return blame_conduite;
	}


	/**
	 * @param blame_conduite the blame_conduite to set
	 */
	public void setBlame_conduite(String blame_conduite) {
		this.blame_conduite = blame_conduite;
	}


	/**
	 * @return the r_moy_seq1
	 */
	public Double getR_moy_seq1() {
		return r_moy_seq1;
	}


	/**
	 * @param r_moy_seq1 the r_moy_seq1 to set
	 */
	public void setR_moy_seq1(Double r_moy_seq1) {
		this.r_moy_seq1 = r_moy_seq1;
	}


	/**
	 * @return the r_rang_seq
	 */
	public String getR_rang_seq() {
		return r_rang_seq;
	}


	/**
	 * @param r_rang_seq the r_rang_seq to set
	 */
	public void setR_rang_seq(String r_rang_seq) {
		this.r_rang_seq = r_rang_seq;
	}


	/**
	 * @return the appreciation
	 */
	public String getAppreciation() {
		return appreciation;
	}


	/**
	 * @param appreciation the appreciation to set
	 */
	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}


	/**
	 * @return the tableau_hon
	 */
	public String getTableau_hon() {
		return tableau_hon;
	}


	/**
	 * @param tableau_hon the tableau_hon to set
	 */
	public void setTableau_hon(String tableau_hon) {
		this.tableau_hon = tableau_hon;
	}


	/**
	 * @return the tableau_enc
	 */
	public String getTableau_enc() {
		return tableau_enc;
	}


	/**
	 * @param tableau_enc the tableau_enc to set
	 */
	public void setTableau_enc(String tableau_enc) {
		this.tableau_enc = tableau_enc;
	}


	/**
	 * @return the tableau_fel
	 */
	public String getTableau_fel() {
		return tableau_fel;
	}


	/**
	 * @param tableau_fel the tableau_fel to set
	 */
	public void setTableau_fel(String tableau_fel) {
		this.tableau_fel = tableau_fel;
	}


	/**
	 * @return the decision_conseil
	 */
	public String getDecision_conseil() {
		return decision_conseil;
	}


	/**
	 * @param decision_conseil the decision_conseil to set
	 */
	public void setDecision_conseil(String decision_conseil) {
		this.decision_conseil = decision_conseil;
	}


	/**
	 * @return the effort_matiere1
	 */
	public String getEffort_matiere1() {
		return effort_matiere1;
	}


	/**
	 * @param effort_matiere1 the effort_matiere1 to set
	 */
	public void setEffort_matiere1(String effort_matiere1) {
		this.effort_matiere1 = effort_matiere1;
	}


	/**
	 * @return the effort_matiere2
	 */
	public String getEffort_matiere2() {
		return effort_matiere2;
	}


	/**
	 * @param effort_matiere2 the effort_matiere2 to set
	 */
	public void setEffort_matiere2(String effort_matiere2) {
		this.effort_matiere2 = effort_matiere2;
	}


	/**
	 * @return the effort_matiere3
	 */
	public String getEffort_matiere3() {
		return effort_matiere3;
	}


	/**
	 * @param effort_matiere3 the effort_matiere3 to set
	 */
	public void setEffort_matiere3(String effort_matiere3) {
		this.effort_matiere3 = effort_matiere3;
	}


	/**
	 * @return the dateJour
	 */
	public String getDateJour() {
		return dateJour;
	}


	/**
	 * @param dateJour the dateJour to set
	 */
	public void setDateJour(String dateJour) {
		this.dateJour = dateJour;
	}


	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}


	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}


	/**
	 * @return the matieresSequence1Groupe1
	 */
	public List<MatiereGroupe1Sequence1Bean> getMatieresSequence1Groupe1() {
		return matieresSequence1Groupe1;
	}


	/**
	 * @param matieresSequence1Groupe1 the matieresSequence1Groupe1 to set
	 */
	public void setMatieresSequence1Groupe1(List<MatiereGroupe1Sequence1Bean> matieresSequence1Groupe1) {
		this.matieresSequence1Groupe1 = matieresSequence1Groupe1;
	}


	/**
	 * @return the matieresSequence1Groupe2
	 */
	public List<MatiereGroupe2Sequence1Bean> getMatieresSequence1Groupe2() {
		return matieresSequence1Groupe2;
	}


	/**
	 * @param matieresSequence1Groupe2 the matieresSequence1Groupe2 to set
	 */
	public void setMatieresSequence1Groupe2(List<MatiereGroupe2Sequence1Bean> matieresSequence1Groupe2) {
		this.matieresSequence1Groupe2 = matieresSequence1Groupe2;
	}


	/**
	 * @return the matieresSequence1Groupe3
	 */
	public List<MatiereGroupe3Sequence1Bean> getMatieresSequence1Groupe3() {
		return matieresSequence1Groupe3;
	}


	/**
	 * @param matieresSequence1Groupe3 the matieresSequence1Groupe3 to set
	 */
	public void setMatieresSequence1Groupe3(List<MatiereGroupe3Sequence1Bean> matieresSequence1Groupe3) {
		this.matieresSequence1Groupe3 = matieresSequence1Groupe3;
	}
	
	
	
	
	
	
	

}
