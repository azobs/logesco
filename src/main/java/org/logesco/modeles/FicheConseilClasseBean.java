/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;
import java.util.List;

/**
 * @author cedrickiadjeu
 *
 */
public class FicheConseilClasseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 	//Titre entete de page
		//partie entete
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
	   //Partie titre
	    private String titre_fiche;
	    private String annee;
	    private String periode;
	    private String enseignant;
	    private String classe;
	    //tableau entete de page
	    private int g_ins;
	    private int f_ins;
	    private int t_ins;
	    private int g_pre;
	    private int f_pre;
	    private int t_pre;
	    
	    //Liste des matières passant dans la classe
	    private List<SousRapport1ConseilBean> sous_rapport1_conseil;
	    
	    //resultat d'ensemble
	    private int g_classe;
	    private int f_classe;
	    private int t_classe;
	    private int g_nonclasse;
	    private int f_nonclasse;
	    private int t_nonclasse;
	    private int g_nbremoy;
	    private int f_nbremoy;
	    private int t_nbremoy;
	    private double g_pourcentage;
	    private double f_pourcentage;
	    private double t_pourcentage;
	    private double mg_classe;
	    
	    //Classement
	    private String nom_1ere;
	    private double moy_1ere;
	    private String nom_2eme;
	    private double moy_2eme;
	    private String nom_3eme;
	    private double moy_3eme;
	    private String nom_4eme;
	    private double moy_4eme;
	    private String nom_5eme;
	    private double moy_5eme;
	    private String rang_dernier1;
	    private String nom_dernier1;
	    private double moy_dernier1;
	    private String rang_dernier2;
	    private String nom_dernier2;
	    private double moy_dernier2;
	    private String rang_dernier3;
	    private String nom_dernier3;
	    private double moy_dernier3;
	    private String rang_dernier4;
	    private String nom_dernier4;
	    private double moy_dernier4;
	    private String rang_dernier5;
	    private String nom_dernier5;
	    private double moy_dernier5;
	    
	    //Statistiques globales
	    private int nbre_moy5;
	    private int nbre_moy7;
	    private int nbre_moy8;
	    private int nbre_moy9;
	    private int nbre_moy10;
	    private int nbre_moy12;
	    private int nbre_moy13;
	    private int nbre_moy14;
	    private int nbre_moy15;
	    
	    //Taux de reussite par discipline
	    private List<SousRapport2ConseilBean> sous_rapport2_conseil;
	    private List<SousRapport3ConseilBean> sous_rapport3_conseil;
	    
	    //Ville
	    private String ville;

	/**
	 * 
	 */
	public FicheConseilClasseBean() {
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
		return t_pre;
	}

	/**
	 * @param t_pre the t_pre to set
	 */
	public void setT_pre(int t_pre) {
		this.t_pre = t_pre;
	}

	/**
	 * @return the sous_rapport1_conseil
	 */
	public List<SousRapport1ConseilBean> getSous_rapport1_conseil() {
		return sous_rapport1_conseil;
	}

	/**
	 * @param sous_rapport1_conseil the sous_rapport1_conseil to set
	 */
	public void setSous_rapport1_conseil(List<SousRapport1ConseilBean> sous_rapport1_conseil) {
		this.sous_rapport1_conseil = sous_rapport1_conseil;
	}

	/**
	 * @return the g_classe
	 */
	public int getG_classe() {
		return g_classe;
	}

	/**
	 * @param g_classe the g_classe to set
	 */
	public void setG_classe(int g_classe) {
		this.g_classe = g_classe;
	}

	/**
	 * @return the f_classe
	 */
	public int getF_classe() {
		return f_classe;
	}

	/**
	 * @param f_classe the f_classe to set
	 */
	public void setF_classe(int f_classe) {
		this.f_classe = f_classe;
	}

	/**
	 * @return the t_classe
	 */
	public int getT_classe() {
		return t_classe;
	}

	/**
	 * @param t_classe the t_classe to set
	 */
	public void setT_classe(int t_classe) {
		this.t_classe = t_classe;
	}

	/**
	 * @return the g_nonclasse
	 */
	public int getG_nonclasse() {
		return g_nonclasse;
	}

	/**
	 * @param g_nonclasse the g_nonclasse to set
	 */
	public void setG_nonclasse(int g_nonclasse) {
		this.g_nonclasse = g_nonclasse;
	}

	/**
	 * @return the f_nonclasse
	 */
	public int getF_nonclasse() {
		return f_nonclasse;
	}

	/**
	 * @param f_nonclasse the f_nonclasse to set
	 */
	public void setF_nonclasse(int f_nonclasse) {
		this.f_nonclasse = f_nonclasse;
	}

	/**
	 * @return the t_nonclasse
	 */
	public int getT_nonclasse() {
		return t_nonclasse;
	}

	/**
	 * @param t_nonclasse the t_nonclasse to set
	 */
	public void setT_nonclasse(int t_nonclasse) {
		this.t_nonclasse = t_nonclasse;
	}

	/**
	 * @return the g_nbremoy
	 */
	public int getG_nbremoy() {
		return g_nbremoy;
	}

	/**
	 * @param g_nbremoy the g_nbremoy to set
	 */
	public void setG_nbremoy(int g_nbremoy) {
		this.g_nbremoy = g_nbremoy;
	}

	/**
	 * @return the f_nbremoy
	 */
	public int getF_nbremoy() {
		return f_nbremoy;
	}

	/**
	 * @param f_nbremoy the f_nbremoy to set
	 */
	public void setF_nbremoy(int f_nbremoy) {
		this.f_nbremoy = f_nbremoy;
	}

	/**
	 * @return the t_nbremoy
	 */
	public int getT_nbremoy() {
		return t_nbremoy;
	}

	/**
	 * @param t_nbremoy the t_nbremoy to set
	 */
	public void setT_nbremoy(int t_nbremoy) {
		this.t_nbremoy = t_nbremoy;
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
	 * @return the nom_1ere
	 */
	public String getNom_1ere() {
		return nom_1ere;
	}

	/**
	 * @param nom_1ere the nom_1ere to set
	 */
	public void setNom_1ere(String nom_1ere) {
		this.nom_1ere = nom_1ere;
	}

	/**
	 * @return the moy_1ere
	 */
	public double getMoy_1ere() {
		return moy_1ere;
	}

	/**
	 * @param moy_1ere the moy_1ere to set
	 */
	public void setMoy_1ere(double moy_1ere) {
		this.moy_1ere = moy_1ere;
	}

	/**
	 * @return the nom_2eme
	 */
	public String getNom_2eme() {
		return nom_2eme;
	}

	/**
	 * @param nom_2eme the nom_2eme to set
	 */
	public void setNom_2eme(String nom_2eme) {
		this.nom_2eme = nom_2eme;
	}

	/**
	 * @return the moy_2eme
	 */
	public double getMoy_2eme() {
		return moy_2eme;
	}

	/**
	 * @param moy_2eme the moy_2eme to set
	 */
	public void setMoy_2eme(double moy_2eme) {
		this.moy_2eme = moy_2eme;
	}

	/**
	 * @return the nom_3eme
	 */
	public String getNom_3eme() {
		return nom_3eme;
	}

	/**
	 * @param nom_3eme the nom_3eme to set
	 */
	public void setNom_3eme(String nom_3eme) {
		this.nom_3eme = nom_3eme;
	}

	/**
	 * @return the moy_3eme
	 */
	public double getMoy_3eme() {
		return moy_3eme;
	}

	/**
	 * @param moy_3eme the moy_3eme to set
	 */
	public void setMoy_3eme(double moy_3eme) {
		this.moy_3eme = moy_3eme;
	}

	/**
	 * @return the nom_4eme
	 */
	public String getNom_4eme() {
		return nom_4eme;
	}

	/**
	 * @param nom_4eme the nom_4eme to set
	 */
	public void setNom_4eme(String nom_4eme) {
		this.nom_4eme = nom_4eme;
	}

	/**
	 * @return the moy_4eme
	 */
	public double getMoy_4eme() {
		return moy_4eme;
	}

	/**
	 * @param moy_4eme the moy_4eme to set
	 */
	public void setMoy_4eme(double moy_4eme) {
		this.moy_4eme = moy_4eme;
	}

	/**
	 * @return the nom_5eme
	 */
	public String getNom_5eme() {
		return nom_5eme;
	}

	/**
	 * @param nom_5eme the nom_5eme to set
	 */
	public void setNom_5eme(String nom_5eme) {
		this.nom_5eme = nom_5eme;
	}

	/**
	 * @return the moy_5eme
	 */
	public double getMoy_5eme() {
		return moy_5eme;
	}

	/**
	 * @param moy_5eme the moy_5eme to set
	 */
	public void setMoy_5eme(double moy_5eme) {
		this.moy_5eme = moy_5eme;
	}

	/**
	 * @return the rang_dernier1
	 */
	public String getRang_dernier1() {
		return rang_dernier1;
	}

	/**
	 * @param rang_dernier1 the rang_dernier1 to set
	 */
	public void setRang_dernier1(String rang_dernier1) {
		this.rang_dernier1 = rang_dernier1;
	}

	/**
	 * @return the nom_dernier1
	 */
	public String getNom_dernier1() {
		return nom_dernier1;
	}

	/**
	 * @param nom_dernier1 the nom_dernier1 to set
	 */
	public void setNom_dernier1(String nom_dernier1) {
		this.nom_dernier1 = nom_dernier1;
	}

	/**
	 * @return the moy_dernier1
	 */
	public double getMoy_dernier1() {
		return moy_dernier1;
	}

	/**
	 * @param moy_dernier1 the moy_dernier1 to set
	 */
	public void setMoy_dernier1(double moy_dernier1) {
		this.moy_dernier1 = moy_dernier1;
	}

	/**
	 * @return the rang_dernier2
	 */
	public String getRang_dernier2() {
		return rang_dernier2;
	}

	/**
	 * @param rang_dernier2 the rang_dernier2 to set
	 */
	public void setRang_dernier2(String rang_dernier2) {
		this.rang_dernier2 = rang_dernier2;
	}

	/**
	 * @return the nom_dernier2
	 */
	public String getNom_dernier2() {
		return nom_dernier2;
	}

	/**
	 * @param nom_dernier2 the nom_dernier2 to set
	 */
	public void setNom_dernier2(String nom_dernier2) {
		this.nom_dernier2 = nom_dernier2;
	}

	/**
	 * @return the moy_dernier2
	 */
	public double getMoy_dernier2() {
		return moy_dernier2;
	}

	/**
	 * @param moy_dernier2 the moy_dernier2 to set
	 */
	public void setMoy_dernier2(double moy_dernier2) {
		this.moy_dernier2 = moy_dernier2;
	}

	/**
	 * @return the rang_dernier3
	 */
	public String getRang_dernier3() {
		return rang_dernier3;
	}

	/**
	 * @param rang_dernier3 the rang_dernier3 to set
	 */
	public void setRang_dernier3(String rang_dernier3) {
		this.rang_dernier3 = rang_dernier3;
	}

	/**
	 * @return the nom_dernier3
	 */
	public String getNom_dernier3() {
		return nom_dernier3;
	}

	/**
	 * @param nom_dernier3 the nom_dernier3 to set
	 */
	public void setNom_dernier3(String nom_dernier3) {
		this.nom_dernier3 = nom_dernier3;
	}

	/**
	 * @return the moy_dernier3
	 */
	public double getMoy_dernier3() {
		return moy_dernier3;
	}

	/**
	 * @param moy_dernier3 the moy_dernier3 to set
	 */
	public void setMoy_dernier3(double moy_dernier3) {
		this.moy_dernier3 = moy_dernier3;
	}

	/**
	 * @return the rang_dernier4
	 */
	public String getRang_dernier4() {
		return rang_dernier4;
	}

	/**
	 * @param rang_dernier4 the rang_dernier4 to set
	 */
	public void setRang_dernier4(String rang_dernier4) {
		this.rang_dernier4 = rang_dernier4;
	}

	/**
	 * @return the nom_dernier4
	 */
	public String getNom_dernier4() {
		return nom_dernier4;
	}

	/**
	 * @param nom_dernier4 the nom_dernier4 to set
	 */
	public void setNom_dernier4(String nom_dernier4) {
		this.nom_dernier4 = nom_dernier4;
	}

	/**
	 * @return the moy_dernier4
	 */
	public double getMoy_dernier4() {
		return moy_dernier4;
	}

	/**
	 * @param moy_dernier4 the moy_dernier4 to set
	 */
	public void setMoy_dernier4(double moy_dernier4) {
		this.moy_dernier4 = moy_dernier4;
	}

	/**
	 * @return the rang_dernier5
	 */
	public String getRang_dernier5() {
		return rang_dernier5;
	}

	/**
	 * @param rang_dernier5 the rang_dernier5 to set
	 */
	public void setRang_dernier5(String rang_dernier5) {
		this.rang_dernier5 = rang_dernier5;
	}

	/**
	 * @return the nom_dernier5
	 */
	public String getNom_dernier5() {
		return nom_dernier5;
	}

	/**
	 * @param nom_dernier5 the nom_dernier5 to set
	 */
	public void setNom_dernier5(String nom_dernier5) {
		this.nom_dernier5 = nom_dernier5;
	}

	/**
	 * @return the moy_dernier5
	 */
	public double getMoy_dernier5() {
		return moy_dernier5;
	}

	/**
	 * @param moy_dernier5 the moy_dernier5 to set
	 */
	public void setMoy_dernier5(double moy_dernier5) {
		this.moy_dernier5 = moy_dernier5;
	}

	/**
	 * @return the nbre_moy5
	 */
	public int getNbre_moy5() {
		return nbre_moy5;
	}

	/**
	 * @param nbre_moy5 the nbre_moy5 to set
	 */
	public void setNbre_moy5(int nbre_moy5) {
		this.nbre_moy5 = nbre_moy5;
	}

	/**
	 * @return the nbre_moy7
	 */
	public int getNbre_moy7() {
		return nbre_moy7;
	}

	/**
	 * @param nbre_moy7 the nbre_moy7 to set
	 */
	public void setNbre_moy7(int nbre_moy7) {
		this.nbre_moy7 = nbre_moy7;
	}

	/**
	 * @return the nbre_moy8
	 */
	public int getNbre_moy8() {
		return nbre_moy8;
	}

	/**
	 * @param nbre_moy8 the nbre_moy8 to set
	 */
	public void setNbre_moy8(int nbre_moy8) {
		this.nbre_moy8 = nbre_moy8;
	}

	/**
	 * @return the nbre_moy10
	 */
	public int getNbre_moy10() {
		return nbre_moy10;
	}

	/**
	 * @param nbre_moy10 the nbre_moy10 to set
	 */
	public void setNbre_moy10(int nbre_moy10) {
		this.nbre_moy10 = nbre_moy10;
	}

	/**
	 * @return the nbre_moy12
	 */
	public int getNbre_moy12() {
		return nbre_moy12;
	}

	/**
	 * @param nbre_moy12 the nbre_moy12 to set
	 */
	public void setNbre_moy12(int nbre_moy12) {
		this.nbre_moy12 = nbre_moy12;
	}

	/**
	 * @return the nbre_moy13
	 */
	public int getNbre_moy13() {
		return nbre_moy13;
	}

	/**
	 * @param nbre_moy13 the nbre_moy13 to set
	 */
	public void setNbre_moy13(int nbre_moy13) {
		this.nbre_moy13 = nbre_moy13;
	}

	/**
	 * @return the nbre_moy14
	 */
	public int getNbre_moy14() {
		return nbre_moy14;
	}

	/**
	 * @param nbre_moy14 the nbre_moy14 to set
	 */
	public void setNbre_moy14(int nbre_moy14) {
		this.nbre_moy14 = nbre_moy14;
	}

	/**
	 * @return the nbre_moy15
	 */
	public int getNbre_moy15() {
		return nbre_moy15;
	}

	/**
	 * @param nbre_moy15 the nbre_moy15 to set
	 */
	public void setNbre_moy15(int nbre_moy15) {
		this.nbre_moy15 = nbre_moy15;
	}

	/**
	 * @return the sous_rapport2_conseil
	 */
	public List<SousRapport2ConseilBean> getSous_rapport2_conseil() {
		return sous_rapport2_conseil;
	}

	/**
	 * @param sous_rapport2_conseil the sous_rapport2_conseil to set
	 */
	public void setSous_rapport2_conseil(List<SousRapport2ConseilBean> sous_rapport2_conseil) {
		this.sous_rapport2_conseil = sous_rapport2_conseil;
	}

	/**
	 * @return the sous_rapport3_conseil
	 */
	public List<SousRapport3ConseilBean> getSous_rapport3_conseil() {
		return sous_rapport3_conseil;
	}

	/**
	 * @param sous_rapport3_conseil the sous_rapport3_conseil to set
	 */
	public void setSous_rapport3_conseil(List<SousRapport3ConseilBean> sous_rapport3_conseil) {
		this.sous_rapport3_conseil = sous_rapport3_conseil;
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
	 * @return the nbre_moy9
	 */
	public int getNbre_moy9() {
		return nbre_moy9;
	}

	/**
	 * @param nbre_moy9 the nbre_moy9 to set
	 */
	public void setNbre_moy9(int nbre_moy9) {
		this.nbre_moy9 = nbre_moy9;
	}
	
	

}
