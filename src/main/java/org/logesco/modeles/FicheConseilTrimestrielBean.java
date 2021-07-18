/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;

/**
 * @author cedrickiadjeu
 *
 */
public class FicheConseilTrimestrielBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Informations sommaires de la fiche
    private String delegation_fr;
    private String delegation_en;
    private String etablissement_fr;
    private String etablissement_en;
    private String adresse;
    private String annee_scolaire_fr;
    private String annee_scolaire_en;
    private String ministere_fr;
    private String ministere_en;
    private String devise_fr;
    private String devise_en;
    private String classe;
    private String annee;
    private String periode;
    private String titre_fiche;
    
    // Informations statistiques d'entête
    private int g_ins;
    private int f_ins;
    private int t_ins;
    
    private int g_pre;
    private int f_pre;
    private int T_pre;
    
    private int g_moyenne_1;
    private int f_moyenne_1;
    private int t_moyenne_1;
    
    private int g_moyenne_2;
    private int f_moyenne_2;
    private int t_moyenne_2;
    
    private int g_moyenne_3;
    private int f_moyenne_3;
    private int t_moyenne_3;
    
    private double g_pourcentage;
    private double f_pourcentage;
    private double t_pourcentage;
    
    private double mg_classe;
    
    // Informations liées aux statistiques même
    private String n_dernier_1;
    private String n_dernier_2;
    private String n_dernier_3;
    private String n_dernier_4;
    private String n_dernier_5;
    
    private String nom_premier;
    private String nom_deuxieme;
    private String nom_troisieme;
    private String nom_quatrieme;
    private String nom_cinquieme;
    
    private String nom_dernier_1;
    private String nom_dernier_2;
    private String nom_dernier_3;
    private String nom_dernier_4;
    private String nom_dernier_5;
    
    private String moyenne_premier;
    private String moyenne_deuxieme;
    private String moyenne_troisieme;
    private String moyenne_quatrieme;
    private String moyenne_cinquieme;
    
    private String moyenne_dernier_1;
    private String moyenne_dernier_2;
    private String moyenne_dernier_3;
    private String moyenne_dernier_4;
    private String moyenne_dernier_5;
    
    // Informations de bas de page
    private String ville;
    private String enseignant;
    


	

	/**
	 * 
	 */
	public FicheConseilTrimestrielBean() {
		// TODO Auto-generated constructor stub
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
	 * @return the classe
	 */
	public String getClasse() {
		return classe;
	}





	/**
	 * @param classe the classe to set
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}





	/**
	 * @return the annee
	 */
	public String getAnnee() {
		return annee;
	}





	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(String annee) {
		this.annee = annee;
	}





	/**
	 * @return the periode
	 */
	public String getPeriode() {
		return periode;
	}





	/**
	 * @param periode the periode to set
	 */
	public void setPeriode(String periode) {
		this.periode = periode;
	}





	/**
	 * @return the titre_fiche
	 */
	public String getTitre_fiche() {
		return titre_fiche;
	}





	/**
	 * @param titre_fiche the titre_fiche to set
	 */
	public void setTitre_fiche(String titre_fiche) {
		this.titre_fiche = titre_fiche;
	}





	/**
	 * @return the g_ins
	 */
	public int getG_ins() {
		return g_ins;
	}





	/**
	 * @param g_ins the g_ins to set
	 */
	public void setG_ins(int g_ins) {
		this.g_ins = g_ins;
	}





	/**
	 * @return the f_ins
	 */
	public int getF_ins() {
		return f_ins;
	}





	/**
	 * @param f_ins the f_ins to set
	 */
	public void setF_ins(int f_ins) {
		this.f_ins = f_ins;
	}





	/**
	 * @return the t_ins
	 */
	public int getT_ins() {
		return t_ins;
	}





	/**
	 * @param t_ins the t_ins to set
	 */
	public void setT_ins(int t_ins) {
		this.t_ins = t_ins;
	}





	/**
	 * @return the g_pre
	 */
	public int getG_pre() {
		return g_pre;
	}





	/**
	 * @param g_pre the g_pre to set
	 */
	public void setG_pre(int g_pre) {
		this.g_pre = g_pre;
	}





	/**
	 * @return the f_pre
	 */
	public int getF_pre() {
		return f_pre;
	}





	/**
	 * @param f_pre the f_pre to set
	 */
	public void setF_pre(int f_pre) {
		this.f_pre = f_pre;
	}





	/**
	 * @return the t_pre
	 */
	public int getT_pre() {
		return T_pre;
	}





	/**
	 * @param t_pre the t_pre to set
	 */
	public void setT_pre(int t_pre) {
		T_pre = t_pre;
	}





	/**
	 * @return the g_moyenne_1
	 */
	public int getG_moyenne_1() {
		return g_moyenne_1;
	}





	/**
	 * @param g_moyenne_1 the g_moyenne_1 to set
	 */
	public void setG_moyenne_1(int g_moyenne_1) {
		this.g_moyenne_1 = g_moyenne_1;
	}





	/**
	 * @return the f_moyenne_1
	 */
	public int getF_moyenne_1() {
		return f_moyenne_1;
	}





	/**
	 * @param f_moyenne_1 the f_moyenne_1 to set
	 */
	public void setF_moyenne_1(int f_moyenne_1) {
		this.f_moyenne_1 = f_moyenne_1;
	}





	/**
	 * @return the t_moyenne_1
	 */
	public int getT_moyenne_1() {
		return t_moyenne_1;
	}





	/**
	 * @param t_moyenne_1 the t_moyenne_1 to set
	 */
	public void setT_moyenne_1(int t_moyenne_1) {
		this.t_moyenne_1 = t_moyenne_1;
	}





	/**
	 * @return the g_moyenne_2
	 */
	public int getG_moyenne_2() {
		return g_moyenne_2;
	}





	/**
	 * @param g_moyenne_2 the g_moyenne_2 to set
	 */
	public void setG_moyenne_2(int g_moyenne_2) {
		this.g_moyenne_2 = g_moyenne_2;
	}





	/**
	 * @return the f_moyenne_2
	 */
	public int getF_moyenne_2() {
		return f_moyenne_2;
	}





	/**
	 * @param f_moyenne_2 the f_moyenne_2 to set
	 */
	public void setF_moyenne_2(int f_moyenne_2) {
		this.f_moyenne_2 = f_moyenne_2;
	}





	/**
	 * @return the t_moyenne_2
	 */
	public int getT_moyenne_2() {
		return t_moyenne_2;
	}





	/**
	 * @param t_moyenne_2 the t_moyenne_2 to set
	 */
	public void setT_moyenne_2(int t_moyenne_2) {
		this.t_moyenne_2 = t_moyenne_2;
	}





	/**
	 * @return the g_moyenne_3
	 */
	public int getG_moyenne_3() {
		return g_moyenne_3;
	}





	/**
	 * @param g_moyenne_3 the g_moyenne_3 to set
	 */
	public void setG_moyenne_3(int g_moyenne_3) {
		this.g_moyenne_3 = g_moyenne_3;
	}





	/**
	 * @return the f_moyenne_3
	 */
	public int getF_moyenne_3() {
		return f_moyenne_3;
	}





	/**
	 * @param f_moyenne_3 the f_moyenne_3 to set
	 */
	public void setF_moyenne_3(int f_moyenne_3) {
		this.f_moyenne_3 = f_moyenne_3;
	}





	/**
	 * @return the t_moyenne_3
	 */
	public int getT_moyenne_3() {
		return t_moyenne_3;
	}





	/**
	 * @param t_moyenne_3 the t_moyenne_3 to set
	 */
	public void setT_moyenne_3(int t_moyenne_3) {
		this.t_moyenne_3 = t_moyenne_3;
	}





	/**
	 * @return the g_pourcentage
	 */
	public double getG_pourcentage() {
		return g_pourcentage;
	}





	/**
	 * @param g_pourcentage the g_pourcentage to set
	 */
	public void setG_pourcentage(double g_pourcentage) {
		this.g_pourcentage = g_pourcentage;
	}





	/**
	 * @return the f_pourcentage
	 */
	public double getF_pourcentage() {
		return f_pourcentage;
	}





	/**
	 * @param f_pourcentage the f_pourcentage to set
	 */
	public void setF_pourcentage(double f_pourcentage) {
		this.f_pourcentage = f_pourcentage;
	}





	/**
	 * @return the t_pourcentage
	 */
	public double getT_pourcentage() {
		return t_pourcentage;
	}





	/**
	 * @param t_pourcentage the t_pourcentage to set
	 */
	public void setT_pourcentage(double t_pourcentage) {
		this.t_pourcentage = t_pourcentage;
	}





	/**
	 * @return the mg_classe
	 */
	public double getMg_classe() {
		return mg_classe;
	}





	/**
	 * @param mg_classe the mg_classe to set
	 */
	public void setMg_classe(double mg_classe) {
		this.mg_classe = mg_classe;
	}





	/**
	 * @return the n_dernier_1
	 */
	public String getN_dernier_1() {
		return n_dernier_1;
	}





	/**
	 * @param n_dernier_1 the n_dernier_1 to set
	 */
	public void setN_dernier_1(String n_dernier_1) {
		this.n_dernier_1 = n_dernier_1;
	}





	/**
	 * @return the n_dernier_2
	 */
	public String getN_dernier_2() {
		return n_dernier_2;
	}





	/**
	 * @param n_dernier_2 the n_dernier_2 to set
	 */
	public void setN_dernier_2(String n_dernier_2) {
		this.n_dernier_2 = n_dernier_2;
	}





	/**
	 * @return the n_dernier_3
	 */
	public String getN_dernier_3() {
		return n_dernier_3;
	}





	/**
	 * @param n_dernier_3 the n_dernier_3 to set
	 */
	public void setN_dernier_3(String n_dernier_3) {
		this.n_dernier_3 = n_dernier_3;
	}





	/**
	 * @return the n_dernier_4
	 */
	public String getN_dernier_4() {
		return n_dernier_4;
	}





	/**
	 * @param n_dernier_4 the n_dernier_4 to set
	 */
	public void setN_dernier_4(String n_dernier_4) {
		this.n_dernier_4 = n_dernier_4;
	}





	/**
	 * @return the n_dernier_5
	 */
	public String getN_dernier_5() {
		return n_dernier_5;
	}





	/**
	 * @param n_dernier_5 the n_dernier_5 to set
	 */
	public void setN_dernier_5(String n_dernier_5) {
		this.n_dernier_5 = n_dernier_5;
	}





	/**
	 * @return the nom_premier
	 */
	public String getNom_premier() {
		return nom_premier;
	}





	/**
	 * @param nom_premier the nom_premier to set
	 */
	public void setNom_premier(String nom_premier) {
		this.nom_premier = nom_premier;
	}





	/**
	 * @return the nom_deuxieme
	 */
	public String getNom_deuxieme() {
		return nom_deuxieme;
	}





	/**
	 * @param nom_deuxieme the nom_deuxieme to set
	 */
	public void setNom_deuxieme(String nom_deuxieme) {
		this.nom_deuxieme = nom_deuxieme;
	}





	/**
	 * @return the nom_troisieme
	 */
	public String getNom_troisieme() {
		return nom_troisieme;
	}





	/**
	 * @param nom_troisieme the nom_troisieme to set
	 */
	public void setNom_troisieme(String nom_troisieme) {
		this.nom_troisieme = nom_troisieme;
	}





	/**
	 * @return the nom_quatrieme
	 */
	public String getNom_quatrieme() {
		return nom_quatrieme;
	}





	/**
	 * @param nom_quatrieme the nom_quatrieme to set
	 */
	public void setNom_quatrieme(String nom_quatrieme) {
		this.nom_quatrieme = nom_quatrieme;
	}





	/**
	 * @return the nom_cinquieme
	 */
	public String getNom_cinquieme() {
		return nom_cinquieme;
	}





	/**
	 * @param nom_cinquieme the nom_cinquieme to set
	 */
	public void setNom_cinquieme(String nom_cinquieme) {
		this.nom_cinquieme = nom_cinquieme;
	}





	/**
	 * @return the nom_dernier_1
	 */
	public String getNom_dernier_1() {
		return nom_dernier_1;
	}





	/**
	 * @param nom_dernier_1 the nom_dernier_1 to set
	 */
	public void setNom_dernier_1(String nom_dernier_1) {
		this.nom_dernier_1 = nom_dernier_1;
	}





	/**
	 * @return the nom_dernier_2
	 */
	public String getNom_dernier_2() {
		return nom_dernier_2;
	}





	/**
	 * @param nom_dernier_2 the nom_dernier_2 to set
	 */
	public void setNom_dernier_2(String nom_dernier_2) {
		this.nom_dernier_2 = nom_dernier_2;
	}





	/**
	 * @return the nom_dernier_3
	 */
	public String getNom_dernier_3() {
		return nom_dernier_3;
	}





	/**
	 * @param nom_dernier_3 the nom_dernier_3 to set
	 */
	public void setNom_dernier_3(String nom_dernier_3) {
		this.nom_dernier_3 = nom_dernier_3;
	}





	/**
	 * @return the nom_dernier_4
	 */
	public String getNom_dernier_4() {
		return nom_dernier_4;
	}





	/**
	 * @param nom_dernier_4 the nom_dernier_4 to set
	 */
	public void setNom_dernier_4(String nom_dernier_4) {
		this.nom_dernier_4 = nom_dernier_4;
	}





	/**
	 * @return the nom_dernier_5
	 */
	public String getNom_dernier_5() {
		return nom_dernier_5;
	}





	/**
	 * @param nom_dernier_5 the nom_dernier_5 to set
	 */
	public void setNom_dernier_5(String nom_dernier_5) {
		this.nom_dernier_5 = nom_dernier_5;
	}





	/**
	 * @return the moyenne_premier
	 */
	public String getMoyenne_premier() {
		return moyenne_premier;
	}





	/**
	 * @param moyenne_premier the moyenne_premier to set
	 */
	public void setMoyenne_premier(String moyenne_premier) {
		this.moyenne_premier = moyenne_premier;
	}





	/**
	 * @return the moyenne_deuxieme
	 */
	public String getMoyenne_deuxieme() {
		return moyenne_deuxieme;
	}





	/**
	 * @param moyenne_deuxieme the moyenne_deuxieme to set
	 */
	public void setMoyenne_deuxieme(String moyenne_deuxieme) {
		this.moyenne_deuxieme = moyenne_deuxieme;
	}





	/**
	 * @return the moyenne_troisieme
	 */
	public String getMoyenne_troisieme() {
		return moyenne_troisieme;
	}





	/**
	 * @param moyenne_troisieme the moyenne_troisieme to set
	 */
	public void setMoyenne_troisieme(String moyenne_troisieme) {
		this.moyenne_troisieme = moyenne_troisieme;
	}





	/**
	 * @return the moyenne_quatrieme
	 */
	public String getMoyenne_quatrieme() {
		return moyenne_quatrieme;
	}





	/**
	 * @param moyenne_quatrieme the moyenne_quatrieme to set
	 */
	public void setMoyenne_quatrieme(String moyenne_quatrieme) {
		this.moyenne_quatrieme = moyenne_quatrieme;
	}





	/**
	 * @return the moyenne_cinquieme
	 */
	public String getMoyenne_cinquieme() {
		return moyenne_cinquieme;
	}





	/**
	 * @param moyenne_cinquieme the moyenne_cinquieme to set
	 */
	public void setMoyenne_cinquieme(String moyenne_cinquieme) {
		this.moyenne_cinquieme = moyenne_cinquieme;
	}





	/**
	 * @return the moyenne_dernier_1
	 */
	public String getMoyenne_dernier_1() {
		return moyenne_dernier_1;
	}





	/**
	 * @param moyenne_dernier_1 the moyenne_dernier_1 to set
	 */
	public void setMoyenne_dernier_1(String moyenne_dernier_1) {
		this.moyenne_dernier_1 = moyenne_dernier_1;
	}





	/**
	 * @return the moyenne_dernier_2
	 */
	public String getMoyenne_dernier_2() {
		return moyenne_dernier_2;
	}





	/**
	 * @param moyenne_dernier_2 the moyenne_dernier_2 to set
	 */
	public void setMoyenne_dernier_2(String moyenne_dernier_2) {
		this.moyenne_dernier_2 = moyenne_dernier_2;
	}





	/**
	 * @return the moyenne_dernier_3
	 */
	public String getMoyenne_dernier_3() {
		return moyenne_dernier_3;
	}





	/**
	 * @param moyenne_dernier_3 the moyenne_dernier_3 to set
	 */
	public void setMoyenne_dernier_3(String moyenne_dernier_3) {
		this.moyenne_dernier_3 = moyenne_dernier_3;
	}





	/**
	 * @return the moyenne_dernier_4
	 */
	public String getMoyenne_dernier_4() {
		return moyenne_dernier_4;
	}





	/**
	 * @param moyenne_dernier_4 the moyenne_dernier_4 to set
	 */
	public void setMoyenne_dernier_4(String moyenne_dernier_4) {
		this.moyenne_dernier_4 = moyenne_dernier_4;
	}





	/**
	 * @return the moyenne_dernier_5
	 */
	public String getMoyenne_dernier_5() {
		return moyenne_dernier_5;
	}





	/**
	 * @param moyenne_dernier_5 the moyenne_dernier_5 to set
	 */
	public void setMoyenne_dernier_5(String moyenne_dernier_5) {
		this.moyenne_dernier_5 = moyenne_dernier_5;
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
	 * @return the enseignant
	 */
	public String getEnseignant() {
		return enseignant;
	}





	/**
	 * @param enseignant the enseignant to set
	 */
	public void setEnseignant(String enseignant) {
		this.enseignant = enseignant;
	}

	
	
	
	
}
