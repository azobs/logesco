/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;  
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.logesco.modeles.RapportSequentielClasse;
import org.logesco.modeles.RapportSequentielCours;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="classes", 
uniqueConstraints = {@UniqueConstraint(
		columnNames = {"codeClasses", "numeroClasses", "specialite_id_specialite"})})
public class Classes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idClasses;
	@NotNull
	@NotEmpty
	@Size(min= 1, max= 7)
	private String codeClasses;
	@NotNull
	private String numeroClasses;
	private double montantScolarite;

	/****
	 * Ajout du 19-08-2018
	 */
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 3)
	private String langueClasses;

	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Eleves
	 ******************************************/
	@OneToMany(mappedBy="classe")
	Collection<Eleves> listofEleves;
	

	/*
	 * Association avec la table Cours
	 ******************************************/
	@OneToMany(mappedBy="classe")
	Collection<Cours> listofCours;

	/*
	 * Association avec la table Niveaux
	 ******************************************/
	@ManyToOne
	Niveaux niveau;

	/*
	 * Association avec la table Sections
	 ******************************************/
	@ManyToOne
	Sections section;

	/*
	 * Association avec la table Specialites
	 ******************************************/
	@ManyToOne
	@JoinColumn(name="specialite_id_specialite")
	Specialites specialite;

	/*
	 * Association avec la table Proffesseurs: il s'agit du proffesseur titulaire de la classe
	 ************************************************************************/
	@ManyToOne
	Proffesseurs proffesseur;

	/**
	 * 
	 */
	public Classes() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codeClasses
	 * @param numeroClasses
	 */
	public Classes(String codeClasses, String numeroClasses) {
		super();
		this.codeClasses = codeClasses;
		this.numeroClasses = numeroClasses;
	}

	/**
	 * @return the idClasses
	 */
	public Long getIdClasses() {
		return idClasses;
	}

	/**
	 * @param idClasses the idClasses to set
	 */
	public void setIdClasses(Long idClasses) {
		this.idClasses = idClasses;
	}

	/**
	 * @return the codeClasses
	 */
	public String getCodeClasses() {
		return codeClasses;
	}

	/**
	 * @param codeClasses the codeClasses to set
	 */
	public void setCodeClasses(String codeClasses) {
		this.codeClasses = codeClasses;
	}

	/**
	 * @return the numeroClasses
	 */
	public String getNumeroClasses() {
		return numeroClasses;
	}

	/**
	 * @param numeroClasses the numeroClasses to set
	 */
	public void setNumeroClasses(String numeroClasses) {
		this.numeroClasses = numeroClasses;
	}

	/**
	 * @return the listofEleves
	 */
	public Collection<Eleves> getListofEleves() {

		Comparator<Eleves> monComparator = new Comparator<Eleves>() {

			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n = 0;

				if(arg0.getNomsEleves().toLowerCase().compareTo(arg1.getNomsEleves().toLowerCase()) < 0) n = -1;

				if(arg0.getNomsEleves().toLowerCase().compareTo(arg1.getNomsEleves().toLowerCase()) > 0) n = 1;

				if(arg0.getNomsEleves().toLowerCase().compareTo(arg1.getNomsEleves().toLowerCase()) == 0) {
					if(arg0.getPrenomsEleves().toLowerCase().compareTo(arg1.getPrenomsEleves().toLowerCase()) < 0) n = -1;

					if(arg0.getPrenomsEleves().toLowerCase().compareTo(arg1.getPrenomsEleves().toLowerCase()) > 0) n = 1;

					if(arg0.getPrenomsEleves().toLowerCase().compareTo(arg1.getPrenomsEleves().toLowerCase()) == 0) {
						if(arg0.getDatenaissEleves().compareTo(arg1.getDatenaissEleves()) < 0) n = -1;

						if(arg0.getDatenaissEleves().compareTo(arg1.getDatenaissEleves()) > 0) n = 1;
					}
				}

				return n;
			}

		};

		Collections.sort((List<Eleves>)this.listofEleves, monComparator);

		return listofEleves;
	}

	/*******************************
	 * Cette méthode retourne la liste des élève dans l'ordre décroissant des notes finale sequentiel obtenu dans 
	 * un cours.
	 * @param idCours
	 * @param idSequence
	 * @return
	 */

	public Collection<Eleves> getNoteCoursOrdreDecroissantPourSequence(Long idCours, Long idSequence){
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {

			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n = 0;
				if(arg0.getValeurNotesFinaleEleve(idCours, idSequence)>=0 && 
						arg1.getValeurNotesFinaleEleve(idCours, idSequence)>=0){

					if(arg0.getValeurNotesFinaleEleve(idCours, idSequence) > 
							arg1.getValeurNotesFinaleEleve(idCours, idSequence)) n=-1;

					if(arg0.getValeurNotesFinaleEleve(idCours, idSequence) < 
					arg1.getValeurNotesFinaleEleve(idCours, idSequence)) n=1;

				}
				return n;
			}

		};

		List<Eleves> listofEleveregulierPourCoursDansSeq = this.getListofEleveRegulierPourCoursDansSeq(idCours, idSequence);
		Collections.sort(listofEleveregulierPourCoursDansSeq, monComparator);
		
		return listofEleveregulierPourCoursDansSeq;
	}


	public int getRangNoteSequentielElevePourCours(Long idCours, Long idSequence, Long idEleve){
		int rang = 0;
		int i = 1;
		List<Eleves> listofEleveParOrdreDecroissantNoteSeq = (List<Eleves>) this.getNoteCoursOrdreDecroissantPourSequence(idCours, idSequence);
		for(Eleves elv : listofEleveParOrdreDecroissantNoteSeq){
			if(elv.getIdEleves().longValue() == idEleve.longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		return rang;
	}



	/*********************************************
	 * Cette methode retourne la liste des élèves de la classe dans l'ordre décroissant des moyennes obtenu
	 * pour le groupe de cours dont la liste des id est passe en paramètre
	 * @param listofIdCours
	 * @param idSequence
	 * @return
	 */
	public Collection<Eleves> getMoyenneGrpCoursOrdreDecroissantPourSequence(List<Long>listofIdCours, Long idSequence){
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {

			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n = 0;
				if(arg0.getMoyenneSeqElevePourGroupeCours(listofIdCours, idSequence) > 
						arg1.getMoyenneSeqElevePourGroupeCours(listofIdCours, idSequence)) n=-1;
				
				if(arg0.getMoyenneSeqElevePourGroupeCours(listofIdCours, idSequence) < 
						arg1.getMoyenneSeqElevePourGroupeCours(listofIdCours, idSequence)) n=1;
				return n;
			}

		};

		List<Eleves> listofEleveRegulierPourGrpCoursDansSeq = this.getListofEleveRegulierPourGrpCoursDansSeq(listofIdCours, idSequence);
		Collections.sort((List<Eleves>)listofEleveRegulierPourGrpCoursDansSeq, monComparator);
		
		return listofEleveRegulierPourGrpCoursDansSeq;

	}
	
	/********************************************************************
	 * Cette methode va retourner la liste des élèves de la classe dans l'ordre décroissant de leur
	 * moyenne séquentiel respective. ce qui permettra de calculer aiséement le rang d'un eleve
	 * @param idSequence
	 * @return
	 */
	public Collection<Eleves> getMoyenneSequentielOrdreDecroissant(Long idSequence){
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {

			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n=0;
				if(arg0.getMoyenneSequentiel(idSequence)>=0 && arg1.getMoyenneSequentiel(idSequence)>=0){
					if(arg0.getMoyenneSequentiel(idSequence)>arg1.getMoyenneSequentiel(idSequence)) n=-1;
					
					if(arg0.getMoyenneSequentiel(idSequence)<arg1.getMoyenneSequentiel(idSequence)) n=1;
				}
				return n;
			}
			
		};
		
		List<Eleves> listofElevesregulier = this.getListofEleveRegulier(idSequence);
		Collections.sort((List<Eleves>)listofElevesregulier, monComparator);
		return listofElevesregulier;
	}
	
	
	public Collection<Eleves> getMoyenneSequentielOrdreDecroissant1(Long idSequence){
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {

			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n=0;
				if(arg0.getMoyenneSequentiel(idSequence)>=0 && arg1.getMoyenneSequentiel(idSequence)>=0){
					if(arg0.getMoyenneSequentiel(idSequence)>arg1.getMoyenneSequentiel(idSequence)) n=-1;
					
					if(arg0.getMoyenneSequentiel(idSequence)<arg1.getMoyenneSequentiel(idSequence)) n=1;
				}
				return n;
			}
			
		};
		
		List<Eleves> listofElevesayantcomposeaumoinsunefois = this.getListofEleveAyantComposeAuMoinsUneFoisDansSequence(idSequence);
		Collections.sort((List<Eleves>)listofElevesayantcomposeaumoinsunefois, monComparator);
		return listofElevesayantcomposeaumoinsunefois;
	}
	
	
	

	/********************************************************
	 * cette methode retourne le rang de l'eleve pour le groupe de cours passe en parametre. Donc on calcule la 
	 * moyenne de l'eleve pour ce groupe.
	 * @param listofIdCours
	 * @param idSequence
	 * @param idEleve
	 * @return
	 */
	public int getRangMoyenneSeqElevePourGroupe(List<Long> listofIdCours, Long idSequence, Long idEleve){
		int rangGrp=0;
		int i = 1;
		List<Eleves> listofEleveParOrdreDecroissantMoyenneGrpSeq = (List<Eleves>) this.getMoyenneGrpCoursOrdreDecroissantPourSequence(listofIdCours, idSequence);

		for(Eleves elv : listofEleveParOrdreDecroissantMoyenneGrpSeq){
			if(elv.getIdEleves().longValue() == idEleve.longValue()){
				rangGrp = i;
				break;
			}
			i = i+1;
		}

		return rangGrp;
	}
	
	
	public int getRangSequentielEleve(Long idSequence, Long idEleve){
		int rang=0;
		int i=1;
		List<Eleves> listofEleveParOrdreDecroissantMoyenneSeq = (List<Eleves>) this.getMoyenneSequentielOrdreDecroissant(idSequence);
		
		for(Eleves elv : listofEleveParOrdreDecroissantMoyenneSeq){
			if(elv.getIdEleves().longValue() == idEleve.longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		
		return rang;
	}
	
	public int getRangSequentielEleveAuMoinsUneNote(Long idSequence, Long idEleve){
		int rang=0;
		int i=1;
		List<Eleves> listofEleveParOrdreDecroissantMoyenneSeq = (List<Eleves>) this.getMoyenneSequentielOrdreDecroissant1(idSequence);
		
		for(Eleves elv : listofEleveParOrdreDecroissantMoyenneSeq){
			if(elv.getIdEleves().longValue() == idEleve.longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		
		return rang;
	}
	
	

	/***********************************************************************************
	 * Cette methode retourne le rang d'un éleve pour un cours. ce rang se calcule selon sa note
	 * @param listofIdCours
	 * @param idSequence
	 * @param idEleve
	 * @return
	 */
	public int getRangMoyenneSequentielGrpElevePourCours(List<Long>listofIdCours, Long idSequence, Long idEleve){
		int rang = 0;
		int i = 1;
		List<Eleves> listofEleveParOrdreDecroissantMoyGrpSeq = 
				(List<Eleves>) this.getMoyenneGrpCoursOrdreDecroissantPourSequence(listofIdCours, idSequence);
		for(Eleves elv : listofEleveParOrdreDecroissantMoyGrpSeq){
			if(elv.getIdEleves().longValue() == idEleve.longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		return rang;
	}
	
	
	public double getMoyenneGeneralSequence(Long idSequence){
		double moygen = 0;
		List<Eleves> listofEleveRegulier = this.getListofEleveRegulier(idSequence);
		double soeMoy=0;
		for(Eleves eleve:listofEleveRegulier){
			double moyElv = eleve.getMoyenneSequentiel(idSequence);
			soeMoy = soeMoy + moyElv;
		}
		
		moygen = soeMoy/listofEleveRegulier.size();
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return moygen;
	}
	
	

	/*****************************************************************************************
	 * Cette methode retourne la moyenne general de la classe pour un cours donne
	 * @param idCours
	 * @param idSequence
	 * @return
	 */
	public double getMoyenneGeneralCoursSeq(Long idCours, Long idSequence){
		double moygen = 0;
		//Tous les élèves de la classe puisque certain doivent avoir composé et d'autre pas
		List<Eleves> listofEleve = (List<Eleves>) this.getListofEleves();
		double soeNoteFinaleCours = 0;
		int nbreeleveCours = 0;
		for(Eleves elv : listofEleve){
			if(elv.getValeurNotesFinaleEleve(idCours, idSequence)>=0){
				soeNoteFinaleCours = soeNoteFinaleCours + elv.getValeurNotesFinaleEleve(idCours, idSequence);
				nbreeleveCours = nbreeleveCours+1;
			}
		}
		moygen = soeNoteFinaleCours/nbreeleveCours;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return moygen;
	}
	
	/************************
	 * Cette methode calcule la moyenne general de la classe pour un groupe de cours
	 * @param listofIdCours
	 * @param idSequence
	 * @return
	 */
	public double getMoyenneGeneralPourGroupeCours(List<Long> listofIdCours, Long idSequence){
		double moygen=0;
		List<Eleves> listofEleve = (List<Eleves>) this.getListofEleves();
		double soemoygrpCours = 0;
		int nbreeleveCours = 0;
		
		for(Eleves elv : listofEleve){
			double moygrpelv = elv.getMoyenneSeqElevePourGroupeCours(listofIdCours, idSequence);
			if(moygrpelv>0){
				soemoygrpCours = soemoygrpCours + moygrpelv;
				nbreeleveCours = nbreeleveCours+1;
			}
		}
		
		moygen = soemoygrpCours/nbreeleveCours;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return moygen;
	}
	
	/*************************
	 * Cette methode calcul le taux de reussite dans un groupe de cours dont les id sont passé en parametre
	 * @param listofIdCours
	 * @param idSequence
	 * @return
	 */
	public double getTauxReussitePourGroupeCours(List<Long> listofIdCours, Long idSequence){
		double tauxreussite = 0;
		
		List<Eleves> listofEleve = (List<Eleves>) this.getListofEleves();
		int nbremoygrpCours = 0;
		int nbresousmoygrpCours = 0;
		int nbreeleveCours = 0;
		
		for(Eleves elv : listofEleve){
			double moygrpelv = elv.getMoyenneSeqElevePourGroupeCours(listofIdCours, idSequence);
			if(moygrpelv>=0){
				if(moygrpelv>=10){
					nbremoygrpCours = nbremoygrpCours+1;
				}
				else{
					nbresousmoygrpCours = nbresousmoygrpCours+1;
				}
				nbreeleveCours = nbreeleveCours+1;
			}
		}
		
		if(nbreeleveCours>0) tauxreussite = (nbremoygrpCours/nbreeleveCours)*100;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			tauxreussite =df.parse(df.format(tauxreussite)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tauxreussite;
	}
	

	/***********************************
	 * Cette methode calcule la somme des coefficients de tous les cours qui passe dans la classe courante
	 * @return
	 */
	public double getSommeCoefCoursDansClasse(){
		double soeCoef = 0;
		List<Cours> listofCoursClasse = (List<Cours>) this.getListofCours();
		for(Cours cours:listofCoursClasse){
			soeCoef = soeCoef + cours.getCoefCours();
		}
		return soeCoef;
	}


	/**
	 * @return effectifSexe
	 */
	public int getEffectifofElevesDeSexe(int sexe) {
		String sexeString1 = "";
		String sexeString2 = "";
		String sexeString3 = "";
		String sexeString4 = "";
		int comptSexe = 0;
		if(sexe == 0) {
			sexeString1 = new String("masculin");
			sexeString2 = new String("masculin");
			sexeString3 = new String("M");
			sexeString4 = new String("male");
		}
		if(sexe == 1) {
			sexeString1 = new String("féminin");
			sexeString2 = new String("feminin");
			sexeString3 = new String("F");
			sexeString4 = new String("female");
		}
		List<Eleves> listofEleveClasse = (List<Eleves>) this.getListofEleves();
		for(Eleves elv : listofEleveClasse){
			if(elv.getSexeEleves().equalsIgnoreCase(sexeString1)==true||
					elv.getSexeEleves().equalsIgnoreCase(sexeString2)==true||
					elv.getSexeEleves().equalsIgnoreCase(sexeString3)==true||
					elv.getSexeEleves().equalsIgnoreCase(sexeString4)==true) {
				comptSexe = comptSexe + 1;
				//System.out.println("on a compter le sexe "+comptSexe);
			}
			//System.out.println("ce sexe est "+elv.getSexeEleves());
		}
		return comptSexe;
	}

	/**
	 * @param listofEleves the listofEleves to set
	 */
	public void setListofEleves(Collection<Eleves> listofEleves) {
		this.listofEleves = listofEleves;
	}

	/**
	 * @return the listofCours
	 */
	public Collection<Cours> getListofCours() {

		/*
		 * Il faut redefinir cette méthode pour que la liste des cours d'une classe soit retourne
		 * dans l'ordre croissant des codes des matières puis des code de cours
		 */
		Comparator<Cours> monComparator = new Comparator<Cours>() {
			@Override
			public int compare(Cours c1, Cours c2) {
				int n=0;
				if(c1.getMatiere().getCodeMatiere().toLowerCase().compareTo(c2.getMatiere().getCodeMatiere().toLowerCase()) < 0)  n = -1;
				if(c1.getMatiere().getCodeMatiere().toLowerCase().compareTo(c2.getMatiere().getCodeMatiere().toLowerCase()) > 0)  n = 1;
				if(c1.getMatiere().getCodeMatiere().toLowerCase().compareTo(c2.getMatiere().getCodeMatiere().toLowerCase()) == 0) {
					if(c1.getCodeCours().toLowerCase().compareTo(c2.getCodeCours().toLowerCase()) < 0) n = -1;
					if(c1.getCodeCours().toLowerCase().compareTo(c2.getCodeCours().toLowerCase()) > 0) n = 1;
				}
				return n;
			}
		};

		Collections.sort((List<Cours>)this.listofCours, monComparator);
		return listofCours;
	}

	public List<Cours> getListOfCoursEvalueDansSequence(Long idSequence){
		List<Cours> listofCoursEvalueSeq = new ArrayList<Cours>();
		int estEvalue=0;//on suppose au depart que pour un cours aucun eleve n'a encore été evalue
		for(Cours cours: this.getListofCours()){
			for(Eleves eleve: this.getListofEleves()){
				double valNote = eleve.getValeurNotesFinaleEleve(cours.getIdCours(), idSequence);
				if(valNote>0){
					estEvalue=1;//donc au moins 1 éleve a été evalue donc on doit ajouter le cours a la liste
				}
			}
			/*
			 * On scrute la note de tous les élèves, s'il y a un seul qui a une note (estEvalue=1) pour le cours dans la sequence
			 * alors le cours a été evalue.
			 */
			if(estEvalue == 1){
				listofCoursEvalueSeq.add(cours);
			}

			estEvalue=0;
		}
		return listofCoursEvalueSeq;
	}

	public List<Cours> getListofCoursScientifiqueEvalueDansSequence(Long idSequence){
		List<Cours> listofCoursScientifiqueEvalueDansSequence = new ArrayList<Cours>();
		List<Cours> listofCoursClasse = this.getListOfCoursEvalueDansSequence(idSequence);
		for(Cours cours : listofCoursClasse){
			if(cours.getGroupeCours().equals(new String("Scientifique"))==true){
				listofCoursScientifiqueEvalueDansSequence.add(cours);
			}
		}
		return listofCoursScientifiqueEvalueDansSequence;
	}
	
	public List<Cours> getListofCoursScientifiqueDansClasse(){
		List<Cours> listofCoursScientifiqueDansClasse = new ArrayList<Cours>();
		List<Cours> listofCoursClasse = (List<Cours>) this.getListofCours();
		for(Cours cours : listofCoursClasse){
			if(cours.getGroupeCours().equals(new String("Scientifique"))==true){
				listofCoursScientifiqueDansClasse.add(cours);
			}
		}
		return listofCoursScientifiqueDansClasse;
	}


	public List<Cours> getListofCoursLitteraireEvalueDansSequence(Long idSequence){
		List<Cours> listofCoursLitteraireEvalueDansSequence = new ArrayList<Cours>();
		List<Cours> listofCoursClasse = this.getListOfCoursEvalueDansSequence(idSequence);
		for(Cours cours : listofCoursClasse){
			if(cours.getGroupeCours().equals(new String("Litteraire"))==true){
				listofCoursLitteraireEvalueDansSequence.add(cours);
			}
		}
		return listofCoursLitteraireEvalueDansSequence;
	}

	
	public List<Cours> getListofCoursLitteraireDansClasse(){
		List<Cours> listofCoursLitteraireDansClasse = new ArrayList<Cours>();
		List<Cours> listofCoursClasse = (List<Cours>) this.getListofCours();
		for(Cours cours : listofCoursClasse){
			if(cours.getGroupeCours().equals(new String("Litteraire"))==true){
				listofCoursLitteraireDansClasse.add(cours);
			}
		}
		return listofCoursLitteraireDansClasse;
	}

	public List<Cours> getListofCoursDiversEvalueDansSequence(Long idSequence){
		List<Cours> listofCoursDiversEvalueDansSequence = new ArrayList<Cours>();
		List<Cours> listofCoursClasse = this.getListOfCoursEvalueDansSequence(idSequence);
		for(Cours cours : listofCoursClasse){
			if(cours.getGroupeCours().equals(new String("Divers"))==true){
				listofCoursDiversEvalueDansSequence.add(cours);
			}
		}
		return listofCoursDiversEvalueDansSequence;
	}

	
	public List<Cours> getListofCoursDiversDansClasse(){
		List<Cours> listofCoursDiversDansClasse = new ArrayList<Cours>();
		List<Cours> listofCoursClasse = (List<Cours>) this.getListofCours();
		for(Cours cours : listofCoursClasse){
			if(cours.getGroupeCours().equals(new String("Divers"))==true){
				listofCoursDiversDansClasse.add(cours);
			}
		}
		return listofCoursDiversDansClasse;
	}
	

	/**
	 * @param listofCours the listofCours to set
	 */
	public void setListofCours(Collection<Cours> listofCours) {
		this.listofCours = listofCours;
	}

	/**
	 * @return the niveau
	 */
	public Niveaux getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(Niveaux niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return the section
	 */
	public Sections getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(Sections section) {
		this.section = section;
	}

	/**
	 * @return the specialite
	 */
	public Specialites getSpecialite() {
		return specialite;
	}

	/**
	 * @param specialite the specialite to set
	 */
	public void setSpecialite(Specialites specialite) {
		this.specialite = specialite;
	}

	/**
	 * @return the proffesseur
	 */
	public Proffesseurs getProffesseur() {
		return proffesseur;
	}

	/**
	 * @param proffesseur the proffesseur to set
	 */
	public void setProffesseur(Proffesseurs proffesseur) {
		this.proffesseur = proffesseur;
	}

	/**
	 * @return the montantScolarite
	 */
	public double getMontantScolarite() {
		return montantScolarite;
	}

	/**
	 * @param montantScolarite the montantScolarite to set
	 */
	public void setMontantScolarite(double montantScolarite) {
		this.montantScolarite = montantScolarite;
	}


	/**
	 * @return the langueClasses
	 */
	public String getLangueClasses() {
		return langueClasses;
	}

	/**
	 * @param langueClasses the langueClasses to set
	 */
	public void setLangueClasses(String langueClasses) {
		this.langueClasses = langueClasses;
	}

	/********************
	 * Cette methode permettra de savoir si l'enseignant dont l'identifiant est passé en paramètre enseigne
	 * au moins un cours passant dans la classe courante. 
	 * Elle retourne 1  si l'enseignant enseigne au moins 1 cours dans la classe et 0 sinon
	 * @param idUsers
	 * @return
	 */
	public int estEnseignantDeClasse(Long idUsers){
		List<Cours> listofCoursDansClasse = (List<Cours>) this.getListofCours();
		for(Cours cours : listofCoursDansClasse){
			if(cours.getProffesseur().getIdUsers().longValue() == idUsers.longValue()){
				return 1;
			}
		}
		return 0;
	}

	public int estEnseignantTitulaireDeClasse(Long idUsers){
		try{
			System.err.println("prof titulaire "+this.getProffesseur().getNomsPers()+" de classe "+this.codeClasses);
			if(this.getProffesseur().getIdUsers().longValue() == idUsers.longValue()){
				return 1;
			}
			return 0;
		}
		catch(Exception e){
			return -1;
		}
	}

	/*******************************
	 * Cette methode retourne la valeur de la plus grande note obtenu dans la classe pour un cours donnée à une séquence 
	 * donnée. Si aucune note n'a enccore été enregistré pour le cours dans la séquence, la methode va retourner -1
	 * @param idCours
	 * @param idSequence
	 * @return
	 */
	public double getValeurNotePremierDeCoursDansSeq(Long idCours, Long idSequence){
		double valeurNotepremier = 0;
		int premierexist = 0;

		List<Eleves> listofEleveRegulierCours = this.getListofEleveRegulierPourCoursDansSeq(idCours, idSequence);
		
		for(Eleves elv : listofEleveRegulierCours){
			double valeurNote = elv.getValeurNotesFinaleEleve(idCours, idSequence);
			if(valeurNote>=0){
				premierexist = 1;
				if(valeurNote>valeurNotepremier){
					valeurNotepremier = valeurNote;
				}
			}
		}

		if (premierexist == 1) return valeurNotepremier; else return -1;
	}
	
	/***********************************
	 * Cette methode retourne la valeur de la plus grande moyenne pour un groupe de cours dont les 
	 * identifiants sont passe en paramètre dans une liste
	 * @param listofEleveRegulier
	 * @param listofIdCours
	 * @param idSequence
	 * @return
	 */
	public double getValeurMoyennePremierPourGrpDansSeq(List<Eleves> listofEleveRegulier, List<Long> listofIdCours, Long idSequence){
		double valeurMoyennepremier = 0;
		int premierexist = 0;
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = elv.getMoyenneSeqElevePourGroupeCours(listofIdCours, idSequence);
			if(valeurMoyenne>=0){
				premierexist = 1;
				if(valeurMoyenne>valeurMoyennepremier){
					valeurMoyennepremier = valeurMoyenne;
				}
			}
		}
		
		if (premierexist == 1) return valeurMoyennepremier; else return -1;
	}

	/*******************************
	 * Cette methode retourne la valeur de la plus grande moyenne obtenu dans la classe  à une séquence 
	 * donnée. Si aucune moyenne n'a enccore été calculé  dans la séquence, la methode va retourner -1
	 * @param idSequence
	 * @return
	 */
	public double getValeurMoyennePremierDansSeq(List<Eleves> listofEleveRegulier,	
			Long idSequence){
		double valeurMoyennepremier = 0;
		int premierexist = 0;
		//this.getListofEleves()
		//List<Eleves> listofEleveRegulier = this.getListofEleveRegulier(idSequence);
		
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = elv.getMoyenneSequentiel(idSequence);
			if(valeurMoyenne>=0){
				premierexist = 1;
				if(valeurMoyenne>valeurMoyennepremier){
					valeurMoyennepremier = valeurMoyenne;
				}
			}
		}

		if (premierexist == 1) return valeurMoyennepremier; else return -1;
	}
	
	
	public double getValeurMoyenneDernierPourGrpDansSeq(List<Eleves> listofEleveRegulier, List<Long> listofIdCours, Long idSequence){
		double valeurNotedernier = 200;
		int dernierexist = 0;
		
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = elv.getMoyenneSeqElevePourGroupeCours(listofIdCours, idSequence);
			if(valeurMoyenne>=0){
				dernierexist = 1;
				if(valeurMoyenne<valeurNotedernier){
					valeurNotedernier = valeurMoyenne;
				}
			}
		}
		
		if (dernierexist == 1) return valeurNotedernier; else return -1;
	}


	/*******************************
	 * Cette methode retourne la valeur de la plus petite note obtenu dans la classe pour un cours donnée à une séquence 
	 * donnée. Si aucune note n'a enccore été enregistré pour le cours dans la séquence, la methode va retourner -1
	 * @param idCours
	 * @param idSequence
	 * @return
	 */
	public double getValeurNoteDernierDeCoursDansSeq(Long idCours, Long idSequence){
		double valeurNotedernier = 200;
		int dernierexist = 0;

		List<Eleves> listofEleveRegulierCours = this.getListofEleveRegulierPourCoursDansSeq(idCours, idSequence);
		
		for(Eleves elv : listofEleveRegulierCours){
			double valeurNote = elv.getValeurNotesFinaleEleve(idCours, idSequence);
			if(valeurNote>=0){
				dernierexist = 1;
				if(valeurNote<valeurNotedernier){
					valeurNotedernier = valeurNote;
				}
			}
		}

		if (dernierexist == 1) return valeurNotedernier; else return -1;
	}

	/*******************************
	 * Cette methode retourne la valeur de la plus petite moyenne obtenu dans la classe  à une séquence 
	 * donnée. Si aucune moyenne n'a enccore été enregistré  dans la séquence, la methode va retourner -1
	 * @param idSequence
	 * @return
	 */
	public double getValeurMoyenneDernierDansSeq(List<Eleves> listofEleveRegulier, 
			Long idSequence){
		double valeurMoyennedernier = 200;
		int dernierexist = 0;

		//List<Eleves> listofEleveRegulier = this.getListofEleveRegulier(idSequence);
		
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = elv.getMoyenneSequentiel(idSequence);
			if(valeurMoyenne>=0){
				dernierexist = 1;
				if(valeurMoyenne<valeurMoyennedernier){
					valeurMoyennedernier = valeurMoyenne;
				}
			}
		}

		if (dernierexist == 1) return valeurMoyennedernier; else return -1;
	}


	/***************************
	 * Cette méthode retourne le nombre de note >=10 pour un cours donnée dans une sequence donnée.
	 * Si aucune note n'est enregistré la methode va retourne -1
	 * @param idCours
	 * @param idSequence
	 * @return
	 */
	public int getNbreNoteDansCourspourSeq(Long idCours, Long idSequence){
		int nbreNote = 0;
		int noteExist = -1;
		
		List<Eleves> listofEleveRegulierCours = this.getListofEleveRegulierPourCoursDansSeq(idCours, idSequence);
		
		for(Eleves elv : listofEleveRegulierCours){
			double valNote = elv.getValeurNotesFinaleEleve(idCours, idSequence);
			if(valNote>=0){
				noteExist = 1;
				if(valNote>=10){
					nbreNote = nbreNote+1;
				}
			}
		}
		if(noteExist == 1) return nbreNote; else return -1;
	}

	/***************************
	 * Cette méthode retourne le nombre de moyenne >=10 pour un cours donnée dans une sequence donnée.
	 * Si aucune moyenne n'est calcule la methode va retourne -1
	 * @param idCours
	 * @param idSequence
	 * @return
	 */
	public int getNbreMoyennePourSeq(Long idSequence){
		int nbreMoyenne = 0;
		int noteExist = -1;
		
		List<Eleves> listofEleveRegulier = this.getListofEleveRegulier(idSequence);
		
		for(Eleves elv : listofEleveRegulier){
			double valMoyenne = elv.getMoyenneSequentiel(idSequence);
			if(valMoyenne>=0){
				noteExist = 1;
				if(valMoyenne>=10){
					nbreMoyenne = nbreMoyenne+1;
				}
			}
		}
		if(noteExist == 1) return nbreMoyenne; else return -1;
	}

	/***************************
	 * Cette méthode retourne le nombre de note <10 pour un cours donnée dans une sequence donnée.
	 * Si aucune note n'est enregistré la methode va retourne -1
	 * @param idCours
	 * @param idSequence
	 * @return
	 */
	public int getNbreSousNoteDansCourspourSeq(Long idCours, Long idSequence){
		int nbreSousNote = 0;
		int noteExist = -1;
		
		List<Eleves> listofEleveRegulierCours = this.getListofEleveRegulierPourCoursDansSeq(idCours, idSequence);
		
		for(Eleves elv : listofEleveRegulierCours){
			double valNote = elv.getValeurNotesFinaleEleve(idCours, idSequence);
			if(valNote>=0){
				noteExist = 1;
				if(valNote<10){
					nbreSousNote = nbreSousNote+1;
				}
			}
		}
		if(noteExist == 1) return nbreSousNote; else return -1;
	}

	/***************************
	 * Cette méthode retourne le nombre de sous moyenne >=10 pour un cours donnée dans une sequence donnée.
	 * Si aucune moyenne n'est calcule la methode va retourne -1
	 * @param idCours
	 * @param idSequence
	 * @return
	 */
	public int getNbreSousMoyennePourSeq(Long idSequence){
		int nbreSousMoyenne = 0;
		int noteExist = -1;
		
		List<Eleves> listofEleveRegulier = this.getListofEleveRegulier(idSequence);
		
		for(Eleves elv : listofEleveRegulier){
			double valMoyenne = elv.getMoyenneSequentiel(idSequence);
			if(valMoyenne>=0){
				noteExist = 1;
				if(valMoyenne>=10){
					nbreSousMoyenne = nbreSousMoyenne+1;
				}
			}
		}
		if(noteExist == 1) return nbreSousMoyenne; else return -1;
	}

	/*******************************************************
	 * Cette methode fait la liste dans l'ordre alphabetique des élèves qui sont régulier pendant une séquence
	 * c'est a dire la liste des élèves qui ont une note sur toutes les évaluations qui sont passe dans la classe pour la sequence
	 * Donc une note pour chaque cours evalue dans la classe.
	 * @param idSequence
	 * @return
	 */
	public List<Eleves> getListofEleveRegulier(Long idSequence){
		List<Eleves> listofEleveRegulier = new ArrayList<Eleves>();
		List<Cours> listofCoursClasse = (List<Cours>) this.getListOfCoursEvalueDansSequence(idSequence);
		List<Long> listofIdCoursClasse = new ArrayList<Long>();

		for(Cours cours:listofCoursClasse){
			listofIdCoursClasse.add(cours.getIdCours());
		}

		List<Eleves> listofEleveOrdonne = (List<Eleves>) this.getListofEleves();
		
		for(Eleves elv: listofEleveOrdonne){
			int estRegulier = elv.estRegulierDansSequence(listofIdCoursClasse, idSequence);
			if(estRegulier == 1){
				listofEleveRegulier.add(elv);
			}
		}

		return listofEleveRegulier;
	}
	
	/*********************************************************
	 * Liste des élèves ayant compose au moins une note dans la séquence
	 * ie ayant composé au moins une fois dans la séquence car on veut dans 
	 * ce cas considerer que si quelqu'un a compose au moins une fois alors il est 
	 * regulier
	 * @param idSequence
	 * @return
	 */
	public List<Eleves> getListofEleveAyantComposeAuMoinsUneFoisDansSequence(Long idSequence){
		List<Eleves> listofEleveAyantComposeAuMoinsUneFois = new ArrayList<Eleves>();
		List<Cours> listofCoursClasse = (List<Cours>) this.getListOfCoursEvalueDansSequence(idSequence);
		List<Long> listofIdCoursClasse = new ArrayList<Long>();

		for(Cours cours:listofCoursClasse){
			listofIdCoursClasse.add(cours.getIdCours());
		}

		List<Eleves> listofEleveOrdonne = (List<Eleves>) this.getListofEleves();
		
		for(Eleves elv: listofEleveOrdonne){
			int estRegulier = elv.AcomposeAuMoinsUnCoursDansSequence(listofIdCoursClasse, idSequence);
			if(estRegulier == 1){
				listofEleveAyantComposeAuMoinsUneFois.add(elv);
			}
		}

		return listofEleveAyantComposeAuMoinsUneFois;
	}
	
	
	
	/****************************************************************************
	 * Cette methode retourne la liste des élèves qui sont régulier pour le cours passe en parametre
	 * dans la séquence donnée. Il est regulier si il a une note pour l'évaluation de ce cours
	 * @param idCours
	 * @param idSequence
	 * @return
	 */
	public List<Eleves> getListofEleveRegulierPourCoursDansSeq(Long idCours, Long idSequence){
		List<Eleves> listofEleveRegulierCours = new ArrayList<Eleves>();
		List<Long> listofIdCoursClasse = new ArrayList<Long>();
		listofIdCoursClasse.add(idCours);
		List<Eleves> listofEleveOrdonne = (List<Eleves>) this.getListofEleves();
		for(Eleves elv: listofEleveOrdonne){
			int estRegulier = elv.estRegulierDansSequence(listofIdCoursClasse, idSequence);
			if(estRegulier == 1){
				listofEleveRegulierCours.add(elv);
			}
		}
		
		return listofEleveRegulierCours;
	}
	
	
	/****************************************************************************
	 * Cette methode retourne la liste des élèves qui sont régulier pour la liste de cours passe en parametre
	 * dans la séquence donnée. Il est regulier si il a une note pour l'évaluation de ce cours
	 * @param idCours
	 * @param idSequence
	 * @return
	 */
	public List<Eleves> getListofEleveRegulierPourGrpCoursDansSeq(List<Long> listofCoursGrp, Long idSequence){
		List<Eleves> listofEleveRegulierCoursGrp = new ArrayList<Eleves>();
		List<Long> listofIdCoursClasse = new ArrayList<Long>();
		
		for(Long idCours:listofCoursGrp){
			listofIdCoursClasse.add(idCours);
		}
		
		List<Eleves> listofEleveOrdonne = (List<Eleves>) this.getListofEleves();
		
		for(Eleves elv: listofEleveOrdonne){
			int estRegulier = elv.estRegulierDansSequence(listofIdCoursClasse, idSequence);
			if(estRegulier == 1){
				listofEleveRegulierCoursGrp.add(elv);
			}
		}
		
		return listofEleveRegulierCoursGrp;
	}
	
	
	
	
	
	
	public double getTauxReussite(List<Eleves> listofEleveRegulier, Long idSequence){
		double taux=0;
		int nbreMoy = this.getNbreMoyennePourSeq(idSequence);
		
		int nbreElvRegulierSeq = listofEleveRegulier.size();
		System.err.println("nbreMoy=="+nbreMoy+"   taux calcule  "+(nbreMoy*100)/nbreElvRegulierSeq);
		if(nbreElvRegulierSeq>0){
			taux = (nbreMoy*100)/nbreElvRegulierSeq;
			System.err.println("taux calcule "+taux);
			java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
			try {
				taux =df.parse(df.format(taux)).doubleValue();
				System.err.println("taux calcule et tronquer "+taux);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.err.println("taux calcule et retourner "+taux);
		return taux;
	}
	
	

	public int geteffectifEleve(){
		return this.getListofEleves().size();
	}

	public int geteffectifEleveRegulier(Long idSequence){
		return this.getListofEleveRegulier(idSequence).size();
	}


	/******************************************
	 * Cette methode permet de calculer le taux de reussite (taux de moyenne) pour un cours dans une
	 * sequence
	 * @param idCours
	 * @param idSequence
	 * @return
	 */
	public double getTauxReussiteCoursSeq(Long idCours, Long idSequence){
		double pourcentage = 0;
		int nbreNote = this.getNbreNoteDansCourspourSeq(idCours, idSequence);
		int effectifregulier = this.geteffectifEleveRegulier(idSequence);

		pourcentage = (nbreNote/effectifregulier)*100;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			pourcentage =df.parse(df.format(pourcentage)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pourcentage;
	}

	public double getPourcentageMoyenneCoursSeq(Long idSequence){
		double pourcentage =0;
		int nbreMoyenne = this.getNbreMoyennePourSeq(idSequence);
		int effectifRegulier = this.geteffectifEleveRegulier(idSequence);

		pourcentage = (nbreMoyenne/effectifRegulier)*100;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			pourcentage =df.parse(df.format(pourcentage)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pourcentage;
	}

	public double getMoyenneSeqDernierPourGroupeCours(List<Long> listofIdCours, Long idSequence){
		double moyenneDernierpourGrp = 200;
		for(Eleves elv: this.getListofEleveRegulier(idSequence)){
			double moyGrp = elv.getMoyenneSeqElevePourGroupeCours(listofIdCours, idSequence);
			if(moyGrp<moyenneDernierpourGrp){
				moyenneDernierpourGrp = moyGrp;
			}
		}
		return moyenneDernierpourGrp;
	}

	public double getMoyenneSeqPremierPourGroupeCours(List<Long> listofIdCours, Long idSequence){
		double moyennePremierpourGrp = 0;
		for(Eleves elv: this.getListofEleveRegulier(idSequence)){
			double moyGrp = elv.getMoyenneSeqElevePourGroupeCours(listofIdCours, idSequence);
			if(moyGrp>moyennePremierpourGrp){
				moyennePremierpourGrp = moyGrp;
			}
		}
		return moyennePremierpourGrp;
	}

	
	
	public RapportSequentielCours getRapportSequentielCours(Long idCours, Long idSequence){
		RapportSequentielCours rapportSequentielCours = new RapportSequentielCours();
		
		double valeurNotepremier = 0;
		int premierexist = 0;
		
		double valeurNotedernier = 200;
		int dernierexist = 0;

		List<Eleves> listofEleveRegulierCours = this.getListofEleveRegulierPourCoursDansSeq(idCours, idSequence);
		
		for(Eleves elv : listofEleveRegulierCours){
			double valeurNote = elv.getValeurNotesFinaleEleve(idCours, idSequence);
			if(valeurNote>=0){
				dernierexist = 1;
				if(valeurNote<valeurNotedernier){
					valeurNotedernier = valeurNote;
				}
				premierexist = 1;
				if(valeurNote>valeurNotepremier){
					valeurNotepremier = valeurNote;
				}
			}
		}
		
		if(dernierexist == 1) rapportSequentielCours.setValeurNoteDernier(valeurNotedernier);
		if(premierexist == 1) rapportSequentielCours.setValeurNotePremier(valeurNotepremier);

		//if (dernierexist == 1) return valeurNotedernier; else return -1;
		
		

		//if (premierexist == 1) return valeurNotepremier; else return -1;
		
		
		double pourcentage = 0;
		int nbreNote = this.getNbreNoteDansCourspourSeq(idCours, idSequence);
		int effectifregulier = this.geteffectifEleveRegulier(idSequence);

		pourcentage = (nbreNote/effectifregulier)*100;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			pourcentage =df.parse(df.format(pourcentage)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rapportSequentielCours.setTauxReussiteCoursSeq(pourcentage);
		//return pourcentage;
		
		double moygen = 0;
		//Tous les élèves de la classe puisque certain doivent avoir composé et d'autre pas
		List<Eleves> listofEleve = (List<Eleves>) this.getListofEleves();
		double soeNoteFinaleCours = 0;
		int nbreeleveCours = 0;
		for(Eleves elv : listofEleve){
			double valeurNotesFinaleEleve = elv.getValeurNotesFinaleEleve(idCours, idSequence);
			if(valeurNotesFinaleEleve>=0){
				soeNoteFinaleCours = soeNoteFinaleCours + valeurNotesFinaleEleve;
				nbreeleveCours = nbreeleveCours+1;
			}
		}
		moygen = soeNoteFinaleCours/nbreeleveCours;

		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//return moygen;
		
		
		return rapportSequentielCours;
	}


	public RapportSequentielClasse getRapportSequentielClasse(List<Eleves> listofEleveRegulier, Long idSequence){
		RapportSequentielClasse rapportSequentielClasse = new RapportSequentielClasse();
		
		double valeurMoyennepremier = 0;
		int premierexist = 0;
		
		double valeurMoyennedernier = 200;
		int dernierexist = 0;
		
		int nbreMoyenne = 0;
		int noteExist = -1;
		
		int nbreElvRegulierSeq = listofEleveRegulier.size();
		rapportSequentielClasse.setEffectifEleveRegulier(nbreElvRegulierSeq);
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		
		double moygen = 0;
		double soeMoy=0;
		
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = elv.getMoyenneSequentiel(idSequence);
			if(valeurMoyenne>=0){
				premierexist = 1;
				if(valeurMoyenne>valeurMoyennepremier){
					valeurMoyennepremier = valeurMoyenne;
				}
				dernierexist = 1;
				if(valeurMoyenne<valeurMoyennedernier){
					valeurMoyennedernier = valeurMoyenne;
				}
				noteExist = 1;
				if(valeurMoyenne>=10){
					nbreMoyenne = nbreMoyenne+1;
				}
			}
			
			//double moyElv = elv.getMoyenneSequentiel(idSequence);
			soeMoy = soeMoy + valeurMoyenne;
			
		}

		if(premierexist == 1) rapportSequentielClasse.setValeurMoyennePremierDansSeq(valeurMoyennepremier);
		
		if(dernierexist == 1)  rapportSequentielClasse.setValeurMoyenneDernierDansSeq(valeurMoyennedernier);
		
		if(noteExist == 1) {
			rapportSequentielClasse.setNbreMoyennePourSeq(nbreMoyenne);
			double taux = (nbreMoyenne*100)/nbreElvRegulierSeq;
			try {
				taux =df.parse(df.format(taux)).doubleValue();
				//System.err.println("taux calcule et tronquer "+taux);
				rapportSequentielClasse.setTauxReussiteSequentiel(taux);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		moygen = soeMoy/nbreElvRegulierSeq;
		
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
			rapportSequentielClasse.setMoyenneGeneralSequence(moygen);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if (premierexist == 1) return valeurMoyennepremier; else return -1;
		

		//if (dernierexist == 1) return valeurMoyennedernier; else return -1;
	
		//if(noteExist == 1) return nbreMoyenne; else return -1;
	
		
		//return moygen;
		
		return rapportSequentielClasse;
	}
	
	


}
