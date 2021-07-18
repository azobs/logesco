package org.logesco.modeles;

public class MatiereGroupe1SequenceBean {

    // Informations sur le groupe de matieres
    private String matiere_g1;
    private String prof_g1;

    // Informations sur une ligne de matière
    private double note_seq_g1;
    private double total_seq_g1;
    private double coef_g1;
    private String extreme_g1;
    private String rang_g1;
    private double moy_classe_g1;
    private double pourcentage_g1;
    private String appreciation_g1;
    

    public MatiereGroupe1SequenceBean() {
    }

   
    public String getMatiere_g1() {
        return matiere_g1;
    }

    public void setMatiere_g1(String matiere_g1) {
        this.matiere_g1 = matiere_g1;
    }

    public String getProf_g1() {
        return prof_g1;
    }

    public void setProf_g1(String prof_g1) {
        this.prof_g1 = prof_g1;
    }
    
   
    public double getCoef_g1() {
        return coef_g1;
    }

    public void setCoef_g1(double coef_g1) {
        this.coef_g1 = coef_g1;
    }

    public String getExtreme_g1() {
        return extreme_g1;
    }

    public void setExtreme_g1(String extreme_g1) {
        this.extreme_g1 = extreme_g1;
    }

    public String getRang_g1() {
        return rang_g1;
    }

    public void setRang_g1(String rang_g1) {
        this.rang_g1 = rang_g1;
    }

    public double getMoy_classe_g1() {
        return moy_classe_g1;
    }

    public void setMoy_classe_g1(double moy_classe_g1) {
        this.moy_classe_g1 = moy_classe_g1;
    }

    public double getPourcentage_g1() {
        return pourcentage_g1;
    }

    public void setPourcentage_g1(double pourcentage_g1) {
        this.pourcentage_g1 = pourcentage_g1;
    }

    public String getAppreciation_g1() {
        return appreciation_g1;
    }

    public void setAppreciation_g1(String appreciation_g1) {
        this.appreciation_g1 = appreciation_g1;
    }

    public double getNote_seq_g1() {
        return note_seq_g1;
    }

    public void setNote_seq_g1(double note_seq_g1) {
        this.note_seq_g1 = note_seq_g1;
    }

    public double getTotal_seq_g1() {
    	if(getNote_seq_g1()>=0) total_seq_g1 = getNote_seq_g1()*getCoef_g1();
		return  total_seq_g1;
    }

    public void setTotal_seq_g1(double total_seq_g1) {
        this.total_seq_g1 = total_seq_g1;
    }

   
    
    
    
    

}
