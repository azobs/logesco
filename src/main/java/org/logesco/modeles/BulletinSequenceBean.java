package org.logesco.modeles;


import java.util.List;

public class BulletinSequenceBean {

    /*
     * Proprietes du Bean Bulletin Sequentiel
     */
    // Informations d'entete du bulletin
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
    private String titre_bulletin;

    // Informations sur les labels d'entete des notes
    private String label_sequence;
    private String label_seq_x_coef;

    // Informations personnelles de l'eleve
    private String nom_eleve;
    private String prenom_eleve;
    private String date_naissance_eleve;
    private String lieu_naissance_eleve;
    private String sexe;
    private String classe_eleve;
    private String prof_principal;
    private String matricule_eleve;
    private String numero;
    private int effectif_classe;
    private int effectif_presente;
    private String redoublant;
    private String photo;

    //  Listes alimentant les sous-rapports
    private List<MatiereGroupe1SequenceBean> matieresGroupe1Sequence;
    private List<MatiereGroupe2SequenceBean> matieresGroupe2Sequence;
    private List<MatiereGroupe3SequenceBean> matieresGroupe3Sequence;

    // Informations liées au sommaire de chaque groupe de matière
    // Premier groupe de matière
    private String nom_g1;
    private double total_coef_g1;
    private double total_g1;
    //Concerne un seul cours parmi les cours du groupe
    private String total_extreme_g1;
    private String total_rang_g1;
    private double mg_classe_g1;
    private double total_pourcentage_g1;
    private double moyenne_g1;

    // Deuxième groupe de matière
    private String nom_g2;
    private double total_coef_g2;
    private double total_g2;
  //Concerne un seul cours parmi les cours du groupe
    private String total_extreme_g2;
    private String total_rang_g2;
    private double mg_classe_g2;
    private double total_pourcentage_g2;
    private double moyenne_g2;
    
    // Troisième groupe de matière
    private String nom_g3;
    private double total_coef_g3;
    private double total_g3;
  //Concerne un seul cours parmi les cours du groupe
    private String total_extreme_g3;
    private String total_rang_g3;
    private double mg_classe_g3;
    private double total_pourcentage_g3;
    private double moyenne_g3;
    
    // Informations sur les totaux
    private double total_coef;
    private double total_points;

    // Informations sur le résultat séquentiel de l'élève
    private double result_tt_points;
    private double result_tt_coef;
    private String result_rang_seq;

    // Informations sur le profil de la classe
    private double moy_premier;
    private double moy_dernier;
    private int nbre_moyennes;
    private double taux_reussite;
    private double moy_gen_classe;

    // Informations sur la conduite sequentielle de l'eleve
    private int absence_NJ;
    private int absence_J;
    private String consigne;
    private String exclusion;
    private String avertissement;
    private String blame_conduite;

    // Informations sur les rappels des moyennes des sequences antérieures
    private String rappel_1;
    private double r_moy_1;
    private String r_rang_1;

    // Informations sur l'appreciation du travail de l'eleve
    private String appreciation;
    private String tableau_hon;
    private String tableau_enc;
    private String tableau_fel;

    // Décision du conseil de classe
    private String decision_conseil;
    private String effort_matiere1;
    private String effort_matiere2;
    private String effort_matiere3;

    // Informations de l'espace visa
    private String ville;

    //  Constructeur sans paramètre de la classe
    public BulletinSequenceBean() {
    }

    // Accesseurs et modificateurs
    public String getMinistere_fr() {
        return ministere_fr;
    }

    public void setMinistere_fr(String ministere_fr) {
        this.ministere_fr = ministere_fr;
    }

    public String getMinistere_en() {
        return ministere_en;
    }

    public void setMinistere_en(String ministere_en) {
        this.ministere_en = ministere_en;
    }

    public String getDelegation_fr() {
        return delegation_fr;
    }

    public void setDelegation_fr(String delegation_fr) {
        this.delegation_fr = delegation_fr;
    }

    public String getDelegation_en() {
        return delegation_en;
    }

    public void setDelegation_en(String delegation_en) {
        this.delegation_en = delegation_en;
    }

    public String getEtablissement_fr() {
        return etablissement_fr;
    }

    public void setEtablissement_fr(String etablissement_fr) {
        this.etablissement_fr = etablissement_fr;
    }

    public String getEtablissement_en() {
        return etablissement_en;
    }

    public void setEtablissement_en(String etablissement_en) {
        this.etablissement_en = etablissement_en;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAnnee_scolaire_fr() {
        return annee_scolaire_fr;
    }

    public void setAnnee_scolaire_fr(String annee_scolaire_fr) {
        this.annee_scolaire_fr = annee_scolaire_fr;
    }

    public String getAnnee_scolaire_en() {
        return annee_scolaire_en;
    }

    public void setAnnee_scolaire_en(String annee_scolaire_en) {
        this.annee_scolaire_en = annee_scolaire_en;
    }

    public String getDevise_fr() {
        return devise_fr;
    }

    public void setDevise_fr(String devise_fr) {
        this.devise_fr = devise_fr;
    }

    public String getDevise_en() {
        return devise_en;
    }

    public void setDevise_en(String devise_en) {
        this.devise_en = devise_en;
    }

    public String getTitre_bulletin() {
        return titre_bulletin;
    }

    public void setTitre_bulletin(String titre_bulletin) {
        this.titre_bulletin = titre_bulletin;
    }

    public String getNom_eleve() {
        return nom_eleve;
    }

    public void setNom_eleve(String nom_eleve) {
        this.nom_eleve = nom_eleve;
    }

    public String getPrenom_eleve() {
        return prenom_eleve;
    }

    public void setPrenom_eleve(String prenom_eleve) {
        this.prenom_eleve = prenom_eleve;
    }

    public String getDate_naissance_eleve() {
        return date_naissance_eleve;
    }

    public void setDate_naissance_eleve(String date_naissance_eleve) {
        this.date_naissance_eleve = date_naissance_eleve;
    }

    public String getLieu_naissance_eleve() {
        return lieu_naissance_eleve;
    }

    public void setLieu_naissance_eleve(String lieu_naissance_eleve) {
        this.lieu_naissance_eleve = lieu_naissance_eleve;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getClasse_eleve() {
        return classe_eleve;
    }

    public void setClasse_eleve(String classe_eleve) {
        this.classe_eleve = classe_eleve;
    }

    public String getProf_principal() {
        return prof_principal;
    }

    public void setProf_principal(String prof_principal) {
        this.prof_principal = prof_principal;
    }

    public String getMatricule_eleve() {
        return matricule_eleve;
    }

    public void setMatricule_eleve(String matricule_eleve) {
        this.matricule_eleve = matricule_eleve;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getEffectif_classe() {
        return effectif_classe;
    }

    public void setEffectif_classe(int effectif_classe) {
        this.effectif_classe = effectif_classe;
    }

    public int getEffectif_presente() {
        return effectif_presente;
    }

    public void setEffectif_presente(int effectif_presente) {
        this.effectif_presente = effectif_presente;
    }

    public String getRedoublant() {
        return redoublant;
    }

    public void setRedoublant(String redoublant) {
        this.redoublant = redoublant;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<MatiereGroupe1SequenceBean> getMatieresGroupe1Sequence() {
        return matieresGroupe1Sequence;
    }

    public void setMatieresGroupe1Sequence(List<MatiereGroupe1SequenceBean> matieresGroupe1Sequence) {
        this.matieresGroupe1Sequence = matieresGroupe1Sequence;
    }

    public List<MatiereGroupe2SequenceBean> getMatieresGroupe2Sequence() {
        return matieresGroupe2Sequence;
    }

    public void setMatieresGroupe2Sequence(List<MatiereGroupe2SequenceBean> matieresGroupe2Sequence) {
        this.matieresGroupe2Sequence = matieresGroupe2Sequence;
    }

    public List<MatiereGroupe3SequenceBean> getMatieresGroupe3Sequence() {
        return matieresGroupe3Sequence;
    }

    public void setMatieresGroupe3Sequence(List<MatiereGroupe3SequenceBean> matieresGroupe3Sequence) {
        this.matieresGroupe3Sequence = matieresGroupe3Sequence;
    }

    public double getTotal_coef() {
        return total_coef;
    }

    public void setTotal_coef(double total_coef) {
        this.total_coef = total_coef;
    }

    public double getTotal_points() {
        return total_points;
    }

    public void setTotal_points(double total_points) {
        this.total_points = total_points;
    }

    public double getResult_tt_points() {
        return result_tt_points;
    }

    public void setResult_tt_points(double result_tt_points) {
        this.result_tt_points = result_tt_points;
    }

    public double getResult_tt_coef() {
        return result_tt_coef;
    }

    public void setResult_tt_coef(double result_tt_coef) {
        this.result_tt_coef = result_tt_coef;
    }

    public double getMoy_premier() {
        return moy_premier;
    }

    public void setMoy_premier(double moy_premier) {
        this.moy_premier = moy_premier;
    }

    public double getMoy_dernier() {
        return moy_dernier;
    }

    public void setMoy_dernier(double moy_dernier) {
        this.moy_dernier = moy_dernier;
    }

    public int getNbre_moyennes() {
        return nbre_moyennes;
    }

    public void setNbre_moyennes(int nbre_moyennes) {
        this.nbre_moyennes = nbre_moyennes;
    }

    public double getTaux_reussite() {
        return taux_reussite;
    }

    public void setTaux_reussite(double taux_reussite) {
        this.taux_reussite = taux_reussite;
    }

    public double getMoy_gen_classe() {
        return moy_gen_classe;
    }

    public void setMoy_gen_classe(double moy_gen_classe) {
        this.moy_gen_classe = moy_gen_classe;
    }

    public int getAbsence_NJ() {
        return absence_NJ;
    }

    public void setAbsence_NJ(int absence_NJ) {
        this.absence_NJ = absence_NJ;
    }

    public int getAbsence_J() {
        return absence_J;
    }

    public void setAbsence_J(int absence_J) {
        this.absence_J = absence_J;
    }

    public String getConsigne() {
        return consigne;
    }

    public void setConsigne(String consigne) {
        this.consigne = consigne;
    }

    public String getExclusion() {
        return exclusion;
    }

    public void setExclusion(String exclusion) {
        this.exclusion = exclusion;
    }

    public String getAvertissement() {
        return avertissement;
    }

    public void setAvertissement(String avertissement) {
        this.avertissement = avertissement;
    }

    public String getBlame_conduite() {
        return blame_conduite;
    }

    public void setBlame_conduite(String blame_conduite) {
        this.blame_conduite = blame_conduite;
    }

    public String getRappel_1() {
        return rappel_1;
    }

    public void setRappel_1(String rappel_1) {
        this.rappel_1 = rappel_1;
    }

    public double getR_moy_1() {
        return r_moy_1;
    }

    public void setR_moy_1(double r_moy_1) {
        this.r_moy_1 = r_moy_1;
    }

    public String getR_rang_1() {
        return r_rang_1;
    }

    public void setR_rang_1(String r_rang_1) {
        this.r_rang_1 = r_rang_1;
    }
    
    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getTableau_hon() {
        return tableau_hon;
    }

    public void setTableau_hon(String tableau_hon) {
        this.tableau_hon = tableau_hon;
    }

    public String getTableau_enc() {
        return tableau_enc;
    }

    public void setTableau_enc(String tableau_enc) {
        this.tableau_enc = tableau_enc;
    }

    public String getTableau_fel() {
        return tableau_fel;
    }

    public void setTableau_fel(String tableau_fel) {
        this.tableau_fel = tableau_fel;
    }

    public String getDecision_conseil() {
        return decision_conseil;
    }

    public void setDecision_conseil(String decision_conseil) {
        this.decision_conseil = decision_conseil;
    }

    public String getEffort_matiere1() {
        return effort_matiere1;
    }

    public void setEffort_matiere1(String effort_matiere1) {
        this.effort_matiere1 = effort_matiere1;
    }

    public String getEffort_matiere2() {
        return effort_matiere2;
    }

    public void setEffort_matiere2(String effort_matiere2) {
        this.effort_matiere2 = effort_matiere2;
    }

    public String getEffort_matiere3() {
        return effort_matiere3;
    }

    public void setEffort_matiere3(String effort_matiere3) {
        this.effort_matiere3 = effort_matiere3;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    
    
    // Accesseurs et modificateurs des propriétés sommatives de chaque rubrique de notes

    public String getNom_g1() {
        return nom_g1;
    }

    public void setNom_g1(String nom_g1) {
        this.nom_g1 = nom_g1;
    }

    public double getTotal_coef_g1() {
        return total_coef_g1;
    }

    public void setTotal_coef_g1(double total_coef_g1) {
        this.total_coef_g1 = total_coef_g1;
    }

    public double getTotal_g1() {
        return total_g1;
    }

    public void setTotal_g1(double total_g1) {
        this.total_g1 = total_g1;
    }

    public String getTotal_extreme_g1() {
        return total_extreme_g1;
    }

    public void setTotal_extreme_g1(String total_extreme_g1) {
        this.total_extreme_g1 = total_extreme_g1;
    }

    public String getTotal_rang_g1() {
        return total_rang_g1;
    }

    public void setTotal_rang_g1(String total_rang_g1) {
        this.total_rang_g1 = total_rang_g1;
    }

    public double getMg_classe_g1() {
        return mg_classe_g1;
    }

    public void setMg_classe_g1(double mg_classe_g1) {
        this.mg_classe_g1 = mg_classe_g1;
    }

    public double getTotal_pourcentage_g1() {
        return total_pourcentage_g1;
    }

    public void setTotal_pourcentage_g1(double total_pourcentage_g1) {
        this.total_pourcentage_g1 = total_pourcentage_g1;
    }


    public String getNom_g2() {
        return nom_g2;
    }

    public void setNom_g2(String nom_g2) {
        this.nom_g2 = nom_g2;
    }

    public double getTotal_coef_g2() {
        return total_coef_g2;
    }

    public void setTotal_coef_g2(double total_coef_g2) {
        this.total_coef_g2 = total_coef_g2;
    }

    public double getTotal_g2() {
        return total_g2;
    }

    public void setTotal_g2(double total_g2) {
        this.total_g2 = total_g2;
    }

    public String getTotal_extreme_g2() {
        return total_extreme_g2;
    }

    public void setTotal_extreme_g2(String total_extreme_g2) {
        this.total_extreme_g2 = total_extreme_g2;
    }

    public String getTotal_rang_g2() {
        return total_rang_g2;
    }

    public void setTotal_rang_g2(String total_rang_g2) {
        this.total_rang_g2 = total_rang_g2;
    }

    public double getMg_classe_g2() {
        return mg_classe_g2;
    }

    public void setMg_classe_g2(double mg_classe_g2) {
        this.mg_classe_g2 = mg_classe_g2;
    }

    public double getTotal_pourcentage_g2() {
        return total_pourcentage_g2;
    }

    public void setTotal_pourcentage_g2(double total_pourcentage_g2) {
        this.total_pourcentage_g2 = total_pourcentage_g2;
    }

    public String getNom_g3() {
        return nom_g3;
    }

    public void setNom_g3(String nom_g3) {
        this.nom_g3 = nom_g3;
    }

    public double getTotal_coef_g3() {
        return total_coef_g3;
    }

    public void setTotal_coef_g3(double total_coef_g3) {
        this.total_coef_g3 = total_coef_g3;
    }

    public double getTotal_g3() {
        return total_g3;
    }

    public void setTotal_g3(double total_g3) {
        this.total_g3 = total_g3;
    }

    public String getTotal_extreme_g3() {
        return total_extreme_g3;
    }

    public void setTotal_extreme_g3(String total_extreme_g3) {
        this.total_extreme_g3 = total_extreme_g3;
    }

    public String getTotal_rang_g3() {
        return total_rang_g3;
    }

    public void setTotal_rang_g3(String total_rang_g3) {
        this.total_rang_g3 = total_rang_g3;
    }

    public double getMg_classe_g3() {
        return mg_classe_g3;
    }

    public void setMg_classe_g3(double mg_classe_g3) {
        this.mg_classe_g3 = mg_classe_g3;
    }

    public double getTotal_pourcentage_g3() {
        return total_pourcentage_g3;
    }

    public void setTotal_pourcentage_g3(double total_pourcentage_g3) {
        this.total_pourcentage_g3 = total_pourcentage_g3;
    }
    
    // Accesseurs et modificateur des moyennes par groupe de matières

    public double getMoyenne_g1() {
        return moyenne_g1;
    }

    public void setMoyenne_g1(double moyenne_g1) {
        this.moyenne_g1 = moyenne_g1;
    }

    public double getMoyenne_g2() {
        return moyenne_g2;
    }

    public void setMoyenne_g2(double moyenne_g2) {
        this.moyenne_g2 = moyenne_g2;
    }

    public double getMoyenne_g3() {
        return moyenne_g3;
    }

    public void setMoyenne_g3(double moyenne_g3) {
        this.moyenne_g3 = moyenne_g3;
    }

    public String getLabel_sequence() {
        return label_sequence;
    }

    public void setLabel_sequence(String label_sequence) {
        this.label_sequence = label_sequence;
    }

    public String getLabel_seq_x_coef() {
        return label_seq_x_coef;
    }

    public void setLabel_seq_x_coef(String label_seq_x_coef) {
        this.label_seq_x_coef = label_seq_x_coef;
    }

    public String getResult_rang_seq() {
        return result_rang_seq;
    }

    public void setResult_rang_seq(String result_rang_seq) {
        this.result_rang_seq = result_rang_seq;
    }
    
    
    

}
