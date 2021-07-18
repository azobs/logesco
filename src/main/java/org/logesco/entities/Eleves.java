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
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.logesco.modeles.LigneSequentielCours;
import org.logesco.modeles.LigneSequentielGroupeCours;
import org.logesco.modeles.NoteFinaleCours;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="eleves", 
uniqueConstraints = {@UniqueConstraint(
		columnNames = {"nomsEleves", "prenomsEleves", "datenaissEleves"})})
public class Eleves implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idEleves;
	@NotNull
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datenaissEleves;
	@Email
	private String emailParent;
	@NotNull
	@NotEmpty
	@Size(min= 7, max= 11)
	private String etatInscEleves;//inscrit non_inscrit
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 20)
	private String lieunaissEleves;
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 20)
	private String nationaliteEleves;
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 50)
	private String nomsEleves;
	@NotNull
	@NotEmpty
	@Size(min= 9, max= 13)
	private String numtel1Parent;
	private String photoEleves;
	@Size(max= 50)
	private String prenomsEleves;
	@Size(max= 30)
	private String quartierEleves;
	@NotNull
	@NotEmpty
	@Size(min= 3, max= 3)
	private String redoublant;//oui non
	@NotNull
	@NotEmpty
	@Size(min= 1, max= 20)
	private String sexeEleves;
	@NotNull
	@NotEmpty
	@Size(min= 5, max= 7)
	private String statutEleves;//ancien nouveau exclu
	@Size(min= 2, max= 20)
	private String villeEleves;
	@NotEmpty
	@Size(min= 2, max= 20)
	@Column(unique=true)
	private String matriculeEleves;


	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table RapportDAbsence
	 ******************************************/
	@OneToMany(mappedBy="eleve")
	Collection<RapportDAbsence> listofRabs;

	/*
	 * Association avec la table NotesEval
	 ******************************************/
	@OneToMany(mappedBy="eleve")
	Collection<NotesEval> listofnotesEval;

	/*
	 * Association avec la table Classes
	 ******************************************/
	@ManyToOne
	Classes classe;

	/*
	 * Association avec la table Bulletins
	 ******************************************/
	@OneToMany(mappedBy="eleve")
	Collection<BulletinsSeq> listofBulletinsSeq;

	/*
	 * Association avec la table CompteInscription
	 ******************************************/
	@OneToOne
	CompteInscription compteInscription;



	/**
	 * 
	 */
	public Eleves() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param datenaissEleves
	 * @param emailParent
	 * @param etatInsc
	 * @param lieunaissEleves
	 * @param nationaliteEleves
	 * @param nomsEleves
	 * @param numtel1Parent
	 * @param prenomsEleves
	 * @param redoublant
	 * @param sexeEleves
	 * @param statutEleves
	 * @param matriculeEleves
	 */
	public Eleves(Date datenaissEleves, String emailParent, String etatInscEleves, String lieunaissEleves,
			String nationaliteEleves, String nomsEleves, String numtel1Parent, String prenomsEleves, String redoublant,
			String sexeEleves, String statutEleves, String matriculeEleves) {
		super();
		this.datenaissEleves = datenaissEleves;
		this.emailParent = emailParent;
		this.etatInscEleves = etatInscEleves;
		this.lieunaissEleves = lieunaissEleves;
		this.nationaliteEleves = nationaliteEleves;
		this.nomsEleves = nomsEleves;
		this.numtel1Parent = numtel1Parent;
		this.prenomsEleves = prenomsEleves;
		this.redoublant = redoublant;
		this.sexeEleves = sexeEleves;
		this.statutEleves = statutEleves;
		this.matriculeEleves = matriculeEleves;
	}

	/**
	 * @return the idEleves
	 */
	public Long getIdEleves() {
		return idEleves;
	}

	/**
	 * @param idEleves the idEleves to set
	 */
	public void setIdEleves(Long idEleves) {
		this.idEleves = idEleves;
	}

	/**
	 * @return the datenaissEleves
	 */
	public Date getDatenaissEleves() {
		return datenaissEleves;
	}

	/**
	 * @param datenaissEleves the datenaissEleves to set
	 */
	public void setDatenaissEleves(Date datenaissEleves) {
		this.datenaissEleves = datenaissEleves;
	}

	/**
	 * @return the emailParent
	 */
	public String getEmailParent() {
		return emailParent;
	}

	/**
	 * @param emailParent the emailParent to set
	 */
	public void setEmailParent(String emailParent) {
		this.emailParent = emailParent;
	}

	/**
	 * @return the nomsEleves
	 */
	public String getNomsEleves() {
		return nomsEleves;
	}

	/**
	 * @param nomsEleves the nomsEleves to set
	 */
	public void setNomsEleves(String nomsEleves) {
		this.nomsEleves = nomsEleves;
	}

	/**
	 * @return the etatInscEleves
	 */
	public String getEtatInscEleves() {
		return etatInscEleves;
	}

	/**
	 * @param etatInscEleves the etatInscEleves to set
	 */
	public void setEtatInscEleves(String etatInscEleves) {
		this.etatInscEleves = etatInscEleves;
	}

	/**
	 * @return the lieunaissEleves
	 */
	public String getLieunaissEleves() {
		return lieunaissEleves;
	}

	/**
	 * @param lieunaissEleves the lieunaissEleves to set
	 */
	public void setLieunaissEleves(String lieunaissEleves) {
		this.lieunaissEleves = lieunaissEleves;
	}

	/**
	 * @return the nationaliteEleves
	 */
	public String getNationaliteEleves() {
		return nationaliteEleves;
	}

	/**
	 * @param nationaliteEleves the nationaliteEleves to set
	 */
	public void setNationaliteEleves(String nationaliteEleves) {
		this.nationaliteEleves = nationaliteEleves;
	}

	/**
	 * @return the numtel1Parent
	 */
	public String getNumtel1Parent() {
		return numtel1Parent;
	}

	/**
	 * @param numtel1Parent the numtel1Parent to set
	 */
	public void setNumtel1Parent(String numtel1Parent) {
		this.numtel1Parent = numtel1Parent;
	}

	/**
	 * @return the photoEleves
	 */
	public String getPhotoEleves() {
		return photoEleves;
	}

	/**
	 * @param photoEleves the photoEleves to set
	 */
	public void setPhotoEleves(String photoEleves) {
		this.photoEleves = photoEleves;
	}

	/**
	 * @return the prenomsEleves
	 */
	public String getPrenomsEleves() {
		return prenomsEleves;
	}

	/**
	 * @param prenomsEleves the prenomsEleves to set
	 */
	public void setPrenomsEleves(String prenomsEleves) {
		this.prenomsEleves = prenomsEleves;
	}

	/**
	 * @return the quartierEleves
	 */
	public String getQuartierEleves() {
		return quartierEleves;
	}

	/**
	 * @param quartierEleves the quartierEleves to set
	 */
	public void setQuartierEleves(String quartierEleves) {
		this.quartierEleves = quartierEleves;
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
	 * @return the sexeEleves
	 */
	public String getSexeEleves() {
		return sexeEleves;
	}

	/**
	 * @param sexeEleves the sexeEleves to set
	 */
	public void setSexeEleves(String sexeEleves) {
		this.sexeEleves = sexeEleves;
	}

	/**
	 * @return the statutEleves
	 */
	public String getStatutEleves() {
		return statutEleves;
	}

	/**
	 * @param statutEleves the statutEleves to set
	 */
	public void setStatutEleves(String statutEleves) {
		this.statutEleves = statutEleves;
	}

	/**
	 * @return the villeEleves
	 */
	public String getVilleEleves() {
		return villeEleves;
	}

	/**
	 * @param villeEleves the villeEleves to set
	 */
	public void setVilleEleves(String villeEleves) {
		this.villeEleves = villeEleves;
	}

	/**
	 * @return the matriculeEleves
	 */
	public String getMatriculeEleves() {
		return matriculeEleves;
	}

	/**
	 * @param matriculeEleves the matriculeEleves to set
	 */
	public void setMatriculeEleves(String matriculeEleves) {
		this.matriculeEleves = matriculeEleves;
	}



	/**
	 * @return the listofBulletinsSeq
	 */
	public Collection<BulletinsSeq> getListofBulletinsSeq() {
		return listofBulletinsSeq;
	}

	/**
	 * @param listofBulletinsSeq the listofBulletinsSeq to set
	 */
	public void setListofBulletinsSeq(Collection<BulletinsSeq> listofBulletinsSeq) {
		this.listofBulletinsSeq = listofBulletinsSeq;
	}

	/******
	 * Cette methode retourne le bulletinsSeq d'un élève pour une séquence donnée
	 * @param idSequence
	 * @return
	 */
	public BulletinsSeq getBulletinsSeqDeSeq(Long idSequence){
		BulletinsSeq bulletinsDeSeq = null;
		List<BulletinsSeq> listofBulletinsSeq = (List<BulletinsSeq>) this.getListofBulletinsSeq(); 
		for(BulletinsSeq bulletinsSeq : listofBulletinsSeq){
			if(bulletinsSeq.getSequence().getIdPeriodes().longValue() == idSequence.longValue()){
				bulletinsDeSeq = bulletinsSeq;
			}
		}
		return bulletinsDeSeq;
	}


	/**
	 * @return the listofRabs
	 */
	public Collection<RapportDAbsence> getListofRabs() {
		return listofRabs;
	}

	/**
	 * @param listofRabs the listofRabs to set
	 */
	public void setListofRabs(Collection<RapportDAbsence> listofRabs) {
		this.listofRabs = listofRabs;
	}

	/**
	 * @return the listofnotesEval
	 * On doit retourner la liste des notes rangés dans l'ordre ascendant des codes des matieres 
	 * puis des codes des cours auquel elles correspondent. Ainsi on aura par exple toutes les notes 
	 * concernant les cours d'une matières avant les autres. 
	 * Et cette methode permettra dans l'affichage du bulletin
	 */
	public Collection<NotesEval> getListofnotesEval() {
		Comparator<NotesEval> monComparator = new Comparator<NotesEval>() {

			@Override
			public int compare(NotesEval n0, NotesEval n1) {
				int n = 0;

				if(n0.getEvaluation().getCours().getMatiere().getCodeMatiere().compareTo(
						n1.getEvaluation().getCours().getMatiere().getCodeMatiere()) < 0) n = -1;

				if(n0.getEvaluation().getCours().getMatiere().getCodeMatiere().compareTo(
						n1.getEvaluation().getCours().getMatiere().getCodeMatiere()) > 0) n = 1;

				if(n0.getEvaluation().getCours().getMatiere().getCodeMatiere().compareTo(
						n1.getEvaluation().getCours().getMatiere().getCodeMatiere()) == 0){

					if(n0.getEvaluation().getCours().getCodeCours().compareTo(
							n1.getEvaluation().getCours().getCodeCours()) < 0) n = -1;

					if(n0.getEvaluation().getCours().getCodeCours().compareTo(
							n1.getEvaluation().getCours().getCodeCours()) > 0) n = 1;
				}

				return n;
			}

		};

		Collections.sort((List<NotesEval>)this.listofnotesEval, monComparator);

		return listofnotesEval;
	}

	/*****
	 * cette methode retourne la liste des notesEval obtenus par l'élève courant pour la séquence 
	 * dont l'identifiant est pris en paramètre
	 * @param idSequence
	 * @return
	 */

	public List<NotesEval> getListofnotesEvalDeSeq(Long idSequence){
		List<NotesEval> listofnotesEvalDeSeq = new ArrayList<NotesEval>();
		/*
		 * On recupere la liste des notes sequentielles de l'élève classe dans l'ordre alphabetique des matieres
		 * puis des cours. Parmi ces notes il y a donc les notes de la séquence prise en paramètre et ce sont ces 
		 * notes qu'on reccherche
		 */
		List<NotesEval> listofnotesEvalSeq = (List<NotesEval>) this.getListofnotesEval();
		if(listofnotesEvalSeq != null){
			for(NotesEval notesEvalSeq : listofnotesEvalSeq){
				if(notesEvalSeq.getEvaluation().getSequence().getIdPeriodes().longValue() == idSequence.longValue()){
					listofnotesEvalDeSeq.add(notesEvalSeq);
				}
			}
		}
		return listofnotesEvalDeSeq;
	}


	/**
	 * @param listofnotesEval the listofnotesEval to set
	 */
	public void setListofnotesEval(Collection<NotesEval> listofnotesEval) {
		this.listofnotesEval = listofnotesEval;
	}

	/**
	 * @return the classe
	 */
	public Classes getClasse() {
		return classe;
	}

	/**
	 * @param classe the classe to set
	 */
	public void setClasse(Classes classe) {
		this.classe = classe;
	}


	/**
	 * @return the compteInscription
	 */
	public CompteInscription getCompteInscription() {
		return compteInscription;
	}

	/**
	 * @param compteInscription the compteInscription to set
	 */
	public void setCompteInscription(CompteInscription compteInscription) {
		this.compteInscription = compteInscription;
	}

	public RapportDAbsence getRapportDAbsenceSeq(Long idSequence){
		RapportDAbsence rapportdabsenceSeq = null;
		for(RapportDAbsence rabs : this.getListofRabs()){
			if(rabs.getSequence().getIdPeriodes().longValue() == idSequence.longValue()){
				rapportdabsenceSeq = rabs;
			}
		}
		return rapportdabsenceSeq;
	}

	/***
	 *  @return the NotesEval
	 * Cette méthode retourne pour un élève sa noteEval dans un cours donnée, 
	 * pour une séquence donnée et un type d'évaluation bien précis.
	 */
	public NotesEval getNotesEvalCoursSeq(Long idCours, Long idSequence, String typeEval){
		NotesEval notesEvalCoursSeq = null;
		for(NotesEval notesEval : this.getListofnotesEval()){
			if(notesEval.getEvaluation().getSequence().getIdPeriodes().longValue() == idSequence.longValue()){
				if(notesEval.getEvaluation().getCours().getIdCours().longValue() == idCours.longValue()){
					if(notesEval.getEvaluation().getTypeEval().equals(typeEval)){
						notesEvalCoursSeq = notesEval;
					}
				}
			}
		}
		return notesEvalCoursSeq;
	}

	public NotesEval getNotesEvalCoursSeq(Evaluations evalConcerne){
		NotesEval notesEvalCoursSeq = null;
		notesEvalCoursSeq = this.getNotesEvalCoursSeq(evalConcerne.getCours().getIdCours(),
				evalConcerne.getSequence().getIdPeriodes(), evalConcerne.getTypeEval());
		return notesEvalCoursSeq;
	}

	public double getNotesEvalSeq(Evaluations evalimpairConcerne, Evaluations evalpairConcerne){
		double noteEval = 0;
		double noteEval1 = 0;
		int p1=0;
		double noteEval2 = 0;
		int p2=0;

		if(evalimpairConcerne != null) {
			p1 = evalimpairConcerne.getProportionEval();
			NotesEval note = this.getNotesEvalCoursSeq(evalimpairConcerne);
			if(note != null){
				noteEval1 = noteEval1 + (note.getValeurnoteEval()); 
			}
		}

		if(evalpairConcerne != null) {
			p2 = evalpairConcerne.getProportionEval();
			NotesEval note = this.getNotesEvalCoursSeq(evalpairConcerne);
			if(note != null){
				noteEval2 = noteEval2 + (note.getValeurnoteEval());
			}
		}

		noteEval =((noteEval1 * p1) + (noteEval2 * p2))/100;


		return noteEval;
	}

	public int getNumero(List<Eleves> listofEleves){
		int numEleve = -1;
		int pos = 0;
		for(Eleves elv : listofEleves){
			pos = pos+1;
			if(elv.getIdEleves().longValue() == this.idEleves){
				numEleve = pos;
				//System.err.println("En cours de getNumero "+numEleve);
				break;
			}
		}
		return numEleve;
	}
	
	public int getNumero(Sequences seq){
		int numEleve = -1;
		List<Eleves> listofElevesRegulierInSeq = this.getClasse().getListofEleveRegulier(seq.getIdPeriodes());
		numEleve = this.getNumero(listofElevesRegulierInSeq);
		return numEleve;
	}

	public List<NotesEval> getNotesEvalEleve(Long idCours, Long idSequence){
		List<NotesEval> listofNotesEvalDeCoursSeq = new ArrayList<NotesEval>();
		List<NotesEval> listofNotesEvalDeSeq = new ArrayList<NotesEval>();
		listofNotesEvalDeSeq = getListofnotesEvalDeSeq(idSequence);
		for(NotesEval notesEval: listofNotesEvalDeSeq){
			if(notesEval.getEvaluation().getCours().getIdCours().longValue() == idCours.longValue()){
				listofNotesEvalDeCoursSeq.add(notesEval);
			}
		}
		return listofNotesEvalDeCoursSeq;
	}
	/******************************
	 * Cette methode retourne la valeur du coefficient du cours. En effet, si l'eleve n'a pas de note pour le cours dans la 
	 * sequence alors la methode va retourne 0 ce qui permettra d'annuler ce coefficient. En effet s'il n'a aucune 
	 * NoteEval dans la séquence alors aucune note n'a été enregistré pour lui pour aucune évaluation.
	 * @param idCours
	 * @param idSequence
	 * @return
	 */
	public double getValeurCoefCours(Long idCours, Long idSequence){
		double valeurCoef = 0; 
		List<NotesEval> listofNotesEvalDeCoursSeq = this.getNotesEvalEleve(idCours, idSequence);
		
		if(listofNotesEvalDeCoursSeq.size()>0)  
			valeurCoef = listofNotesEvalDeCoursSeq.get(0).getEvaluation().getCours().getCoefCours();
		return valeurCoef;
	}

	/*****************
	 * Cette methode retourne la valeur de la note finale (cc + ds) d'un élève à une séquence donnée.
	 * Si l'élève n'a aucune note dans aucune des évaluations de chaque type alors 
	 * la methode retournera -1 pour le signifier 
	 * @param idCours
	 * @param idSequence
	 * @return
	 */
	public double getValeurNotesFinaleEleve(Long idCours, Long idSequence) {
		double noteFinale = 0;
		int possedeunenote = -1;
		List<NotesEval> listofNotesEvalDeCoursSeq = this.getNotesEvalEleve(idCours, idSequence);
		//System.err.println(" taille de listofNotesEvalDeCoursSeq == "+listofNotesEvalDeCoursSeq.size());
		for(NotesEval noteEval : listofNotesEvalDeCoursSeq){
			double pour = (noteEval.getEvaluation().getProportionEval())*0.01;
			double valeur = noteEval.getValeurnoteEval();
			noteFinale = noteFinale + (pour * valeur);
			possedeunenote = 1;
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			noteFinale =df.parse(df.format(noteFinale)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(possedeunenote == 1)		return noteFinale; else return -1;
	}

	/************************
	 * cette methode retourne la moyenne d'un élève pour un groupe de matière 
	 * dont les id sont passse en parametre dans une liste. S'il n'a aucune note dans aucun des cours de ce groupe
	 * elle retrournera -1 pour le signifier
	 * @param listofIdCours
	 * @param idSequence
	 * @return
	 */
	public double getMoyenneSeqElevePourGroupeCours(List<Long>listofIdCours, Long idSequence){
		double moygrp = 0;
		double soeValeurNotegrp = 0;
		double soeCoefCoursgrp = 1;
		int possedeNote = 0;
		for(Long idCoursgrp : listofIdCours){
			double valeurNoteCoursgrp = this.getValeurNotesFinaleEleve(idCoursgrp, idSequence);
			if(valeurNoteCoursgrp >= 0){
				soeValeurNotegrp = soeValeurNotegrp + valeurNoteCoursgrp;
				soeCoefCoursgrp = soeCoefCoursgrp + this.getValeurCoefCours(idCoursgrp, idSequence);
				possedeNote = 1;
			}
		}
		//System.err.println("la somme des notes des cours du groupe est "+soeValeurNotegrp+" et la som des coef "+soeCoefCoursgrp);

		moygrp = soeValeurNotegrp/soeCoefCoursgrp;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moygrp =df.parse(df.format(moygrp)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.err.println("la moyenne de "+this.getNomsEleves()+" pour le groupe est donc "+moygrp);

		if(possedeNote == 1) return moygrp; else return -1;
	}

	
	/************************
	 * cette methode retourne le total des notes d'un élève pour un groupe de matière 
	 * dont les id sont passse en parametre dans une liste. S'il n'a aucune note dans aucun des cours de ce groupe
	 * elle retrournera -1 pour le signifier
	 * @param listofIdCours
	 * @param idSequence
	 * @return
	 */
	public double getTotalNoteSeqElevePourGroupeCours(List<Long>listofIdCours, Long idSequence){
		double soeValeurNotegrp = 0;
		int possedeNote = 0;
		for(Long idCoursgrp : listofIdCours){
			double valeurNoteCoursgrp = this.getValeurNotesFinaleEleve(idCoursgrp, idSequence);
			double coefCours = this.getValeurCoefCours(idCoursgrp, idSequence);
			if(valeurNoteCoursgrp >= 0){
				soeValeurNotegrp = soeValeurNotegrp + (valeurNoteCoursgrp*coefCours);
				possedeNote = 1;
			}
		}
		//System.err.println("la somme des notes des cours du groupe est "+soeValeurNotegrp);

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			soeValeurNotegrp =df.parse(df.format(soeValeurNotegrp)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.err.println("le total des notes de"+this.getNomsEleves()+" pour le groupe est donc "+soeValeurNotegrp);

		if(possedeNote == 1) return soeValeurNotegrp; else return -1;
	}


	/************************
	 * cette methode retourne le total des coefficient des cours q'un élève a compose pour un groupe de cours 
	 * dont les id sont passse en parametre dans une liste. S'il n'a aucune note dans aucun des cours de ce groupe
	 * elle retrournera -1 pour le signifier
	 * @param listofIdCours
	 * @param idSequence
	 * @return
	 */
	public double getTotalCoefElevePourGroupeCours(List<Long>listofIdCours, Long idSequence){
		double soeCoefCoursgrp = 0;
		int possedeNote = 0;
		for(Long idCoursgrp : listofIdCours){
			double valeurNoteCoursgrp = this.getValeurNotesFinaleEleve(idCoursgrp, idSequence);
			if(valeurNoteCoursgrp >= 0){
				soeCoefCoursgrp = soeCoefCoursgrp + this.getValeurCoefCours(idCoursgrp, idSequence);
				possedeNote = 1;
			}
		}
		//System.err.println("la somme des notes des cours du groupe est "+soeCoefCoursgrp);

		

		//System.err.println("le total des notes de"+this.getNomsEleves()+" pour le groupe est donc "+soeCoefCoursgrp);

		if(possedeNote == 1) return soeCoefCoursgrp; else return -1;
	}


	
	
	/********************************
	 * Cette methode retourne la somme des coefficients des cours ou l'élève courant possede une note. Elle retourne 
	 * 0 s'il n'a compose aucun cours
	 * @param idSequence
	 * @return
	 */
	public double getSommeCoefCoursCompose(Long idSequence){
		double soeCoef = 0;
		List<Cours> listofCoursDansClasse = (List<Cours>) this.getClasse().getListOfCoursEvalueDansSequence(idSequence);
		//System.err.println("liste des cours evalue :");
		for(Cours cours : listofCoursDansClasse){
			//System.err.println("  cours "+cours.getCodeCours()+" de coef == "+cours.getCoefCours()+" /// ");
			double valeurNoteCours = this.getValeurNotesFinaleEleve(cours.getIdCours(), idSequence);
			double coefCours = this.getValeurCoefCours(cours.getIdCours(), idSequence);
			//System.err.println("coefCours a sommer "+coefCours);
			if(valeurNoteCours >= 0){
				soeCoef = soeCoef+coefCours;
			}
		}
		//System.err.println();
		//System.err.println("la somme des coef compose est "+soeCoef);
		return soeCoef;
	}

	/***************************************************
	 * Cette méthode retourne le total de note séquentiel d'un élève. Il s'agit de récuperer sa note dans tous les cours 
	 * qui passe dans la classe, les sommer et diviser la somme par la somme des coefficient de tous les cous qui passe 
	 * dans la classe. 
	 * Sa moyenne dépend des cours ou il a au moins une note sur l'une des évaluations (CC ou DS). Ainsi on saura
	 * s'il sera classé ou pas.
	 * Elle va retourner -1 si l'élève n'a pas de moyenne ie n'a fait aucune évaluation
	 * 
	 * @param idSequence
	 * @return
	 */
	public double getTotalPointsSequentiel(Long idSequence){
		int possedeTotal =  0;
		List<Cours> listofCoursDansClasse = (List<Cours>) this.getClasse().getListOfCoursEvalueDansSequence(idSequence);
		double soeValeurNote = 0;
		for(Cours cours : listofCoursDansClasse){
			double valeurNoteCours = this.getValeurNotesFinaleEleve(cours.getIdCours(), idSequence);
			double valeurCoefCours = this.getValeurCoefCours(cours.getIdCours(), idSequence);
			if(valeurNoteCours >= 0){
				soeValeurNote = soeValeurNote+(valeurNoteCours*valeurCoefCours);
				possedeTotal = 1;
			}
		}

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			soeValeurNote =df.parse(df.format(soeValeurNote)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(possedeTotal == 1) return soeValeurNote; else return -1;
	}

	
	/***************************************************
	 * Cette méthode retourne la moyenne séquentiel d'un élève. Il s'agit de récuperer sa note dans tous les cours 
	 * qui passe dans la classe, les sommer et diviser la somme par la somme des coefficient de tous les cous qui passe 
	 * dans la classe. 
	 * Sa moyenne dépend des cours ou il a au moins une note sur l'une des évaluations (CC ou DS). Ainsi on saura
	 * s'il sera classé ou pas.
	 * Elle va retourner -1 si l'élève n'a pas de moyenne ie n'a fait aucune évaluation
	 * 
	 * @param idSequence
	 * @return
	 */
	public double getMoyenneSequentiel(Long idSequence){
		double moyenne = -1;
		int possedeMoy =  0;
		double soeValeurNote = 0;
		double soeCoef = 0;
		/*List<Cours> listofCoursDansClasse = (List<Cours>) this.getClasse().getListOfCoursEvalueDansSequence(idSequence);
		double soeValeurNote = 0;
		int soeCoef = 0;
		for(Cours cours : listofCoursDansClasse){
			double valeurNoteCours = this.getValeurNotesFinaleEleve(cours.getIdCours(), idSequence);
			int coefCours = this.getValeurCoefCours(cours.getIdCours(), idSequence);
			if(valeurNoteCours >= 0){
				soeValeurNote = soeValeurNote+valeurNoteCours;
				soeCoef = soeCoef+coefCours;
				possedeMoy = 1;
			}
		}*/
		
		soeValeurNote = this.getTotalPointsSequentiel(idSequence);
		soeCoef = this.getSommeCoefCoursCompose(idSequence);

		if(soeValeurNote>=0 && soeCoef>0) {
			moyenne = soeValeurNote/soeCoef;
			possedeMoy = 1;
		}

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moyenne =df.parse(df.format(moyenne)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(possedeMoy == 1) return moyenne; else return -1;
	}
	
	
	
	
	

	/********************************
	 * Cette méthode retourne les identifiants des cours dans lesquelle l'élève a une note séquentiel dans 
	 * l'ordre croissant des valeur de note obtenu. Ainsi on a l'identifiant du cours où il a eu la plus faible note en 
	 * tete. cela nous permettra de choisir les 3 cours où on lui recommendera des efforts lors de l'edition de son bulletin
	 * @param listofIdCours
	 * @param idSequence
	 * @return
	 */
	public List<Long> getListofIdCoursDansOrdreEffortAFournir(List<Long>listofIdCours, Long idSequence){
		List<Long> listofCoursEffortAFournir = new ArrayList<Long>();
		List<NoteFinaleCours> listofValeurNote = new ArrayList<NoteFinaleCours>();
		for(Long idCours : listofIdCours){
			double valeurNote = this.getValeurNotesFinaleEleve(idCours, idSequence);
			if((valeurNote>=0)&&(valeurNote<10)){
				NoteFinaleCours noteFinale = new NoteFinaleCours(idCours, valeurNote);
				listofValeurNote.add(noteFinale);    
			}
		}

		Comparator<NoteFinaleCours> monComparator = new Comparator<NoteFinaleCours>() {

			@Override
			public int compare(NoteFinaleCours arg0, NoteFinaleCours arg1) {
				int n= 0;
				if(arg0.getValeurNote() < arg1.getValeurNote()) n=-1;
				if(arg0.getValeurNote() > arg1.getValeurNote()) n=1;
				return n;
			}

		};

		Collections.sort((List<NoteFinaleCours>)listofValeurNote, monComparator);

		for(NoteFinaleCours noteFinale : listofValeurNote){
			listofCoursEffortAFournir.add(noteFinale.getIdCours());
		}

		return listofCoursEffortAFournir;
	}
	
	
	

	/*************************************
	 * Cette methode retourne 1  si l'eleve courant est régulier et 0 sinon.
	 * Il est regulier s'il a une note enregistré pour chacun des cours passe en paramètre (La liste des identifiants des
	 *  cours passant dans la classe sera passe en parametre) 
	 * @param idSequence
	 * @return
	 */
	public int estRegulierDansSequence(List<Long> listofIdCours, Long idSequence){
		int estregulier = 1;
		for(Long idCours : listofIdCours){
			double valNote = this.getValeurNotesFinaleEleve(idCours, idSequence);
			if(valNote<0){

				estregulier = 0;
			}
		}
		return estregulier;
	}
	
	public int AcomposeAuMoinsUnCoursDansSequence(List<Long> listofIdCours, Long idSequence){
		int acompose = 0;
		for(Long idCours : listofIdCours){
			double valNote = this.getValeurNotesFinaleEleve(idCours, idSequence);
			if(valNote>0){

				acompose = 1;
			}
		}
		return acompose;
	}


	public LigneSequentielCours getLigneSequentielCours(Long idCours, Long idSequence){
		LigneSequentielCours ligneSeqCours = new LigneSequentielCours();
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		int possedeTotal =  0;
		List<Cours> listofCoursDansClasse = (List<Cours>) this.getClasse().getListOfCoursEvalueDansSequence(idSequence);
		
		double soeValeurNote = 0;
		double soeCoef = 0;
		for(Cours cours : listofCoursDansClasse){
			double valeurNoteCours = this.getValeurNotesFinaleEleve(cours.getIdCours(), idSequence);
			double valeurCoefCours = this.getValeurCoefCours(cours.getIdCours(), idSequence);
			if(valeurNoteCours >= 0){
				soeValeurNote = soeValeurNote+(valeurNoteCours*valeurCoefCours);
				soeCoef = soeCoef+valeurCoefCours;
				possedeTotal = 1;
			}
		}

		ligneSeqCours.setSoeCoefCoursCompose(soeCoef);
		
		try {
			soeValeurNote =df.parse(df.format(soeValeurNote)).doubleValue();
			if(possedeTotal == 1) ligneSeqCours.setTotalPointsSequentiel(soeValeurNote);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//if(possedeTotal == 1) return soeValeurNote; else return -1;
		
		//return soeCoef;
		
		double noteFinale = 0;
		int possedeunenote = -1;
		List<NotesEval> listofNotesEvalDeCoursSeq = this.getNotesEvalEleve(idCours, idSequence);
		//System.err.println(" taille de listofNotesEvalDeCoursSeq == "+listofNotesEvalDeCoursSeq.size());
		for(NotesEval noteEval : listofNotesEvalDeCoursSeq){
			double pour = (noteEval.getEvaluation().getProportionEval())*0.01;
			double valeur = noteEval.getValeurnoteEval();
			noteFinale = noteFinale + (pour * valeur);
			possedeunenote = 1;
		}
		
		try {
			noteFinale =df.parse(df.format(noteFinale)).doubleValue();
			if(possedeunenote == 1) ligneSeqCours.setValeurNoteFinaleEleve(noteFinale); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//if(possedeunenote == 1)		return noteFinale; else return -1;
		
		
		return ligneSeqCours;
	}
	
	
	public LigneSequentielGroupeCours getLigneSequentielGroupeCours
			(List<Long>listofIdCours, Long idSequence){
		
		LigneSequentielGroupeCours ligneSequentielGroupeCours = new LigneSequentielGroupeCours();
		
		double moygrp = 0;
		double soeValeurNotegrp = 0;
		double soeCoefCoursgrp = 0;
		double soeCoefCoursgrpCompose=0;
		int possedeNote = 0;
		for(Long idCoursgrp : listofIdCours){
			double valeurNoteCoursgrp = this.getValeurNotesFinaleEleve(idCoursgrp, idSequence);
			double coefCours =  this.getValeurCoefCours(idCoursgrp, idSequence);
			if(valeurNoteCoursgrp >= 0){
				soeValeurNotegrp = soeValeurNotegrp + valeurNoteCoursgrp;
				soeCoefCoursgrpCompose = soeCoefCoursgrpCompose + coefCours;
				possedeNote = 1;
			}
			System.err.println("le coef du cours scruter est "+coefCours);
			soeCoefCoursgrp = soeCoefCoursgrp + coefCours;
		}
		System.err.println("la somme des notes des cours du groupe est "+soeValeurNotegrp+
				" et la som des coef "+soeCoefCoursgrp+" possedeNote == "+possedeNote);

		if(possedeNote==1){
			ligneSequentielGroupeCours.setTotalCoefElevePourGroupeCours(soeCoefCoursgrp);
			if(soeCoefCoursgrpCompose>0){
				moygrp = soeValeurNotegrp/soeCoefCoursgrpCompose;
			}
	
			java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
			try {
				moygrp =df.parse(df.format(moygrp)).doubleValue();
				soeValeurNotegrp =df.parse(df.format(soeValeurNotegrp)).doubleValue();
				
				
				ligneSequentielGroupeCours.setMoyenneSeqElevePourGroupeCours(moygrp);
				ligneSequentielGroupeCours.setTotalNoteSeqElevePourGroupeCours(soeValeurNotegrp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		return ligneSequentielGroupeCours;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Eleves [datenaissEleves=" + datenaissEleves + ", emailParent=" + emailParent + ", etatInscEleves="
				+ etatInscEleves + ", lieunaissEleves=" + lieunaissEleves + ", nationaliteEleves=" + nationaliteEleves
				+ ", nomsEleves=" + nomsEleves + ", numtel1Parent=" + numtel1Parent + ", photoEleves=" + photoEleves
				+ ", prenomsEleves=" + prenomsEleves + ", quartierEleves=" + quartierEleves + ", redoublant="
				+ redoublant + ", sexeEleves=" + sexeEleves + ", statutEleves=" + statutEleves + ", villeEleves="
				+ villeEleves + ", matriculeEleves=" + matriculeEleves + "]";
	}
	




}
