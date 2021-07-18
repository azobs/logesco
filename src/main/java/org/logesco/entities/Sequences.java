/**
 * 
 */
package org.logesco.entities;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@DiscriminatorValue("sequence")
public class Sequences extends Periodes{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private int numeroSeq;

	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table RapportDAbsence
	 ******************************************/
	@OneToMany(mappedBy="sequence")
	Collection<RapportDAbsence> listofRabs;
	
	/*
	 * Association avec la table Evaluations
	 ******************************************/
	@OneToMany(mappedBy="sequence")
	Collection<Evaluations> listofEval;
	
	/*
	 * Association avec la table BulletinsSeq
	 ******************************************/
	@OneToMany(mappedBy="sequence")
	Collection<BulletinsSeq> listofBullseq;
	
	/*
	 * Association avec la table Trimestre
	 ******************************************/
	@ManyToOne
	Trimestres trimestre;
	

	/**
	 * 
	 */
	public Sequences() {
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param numeroSeq
	 */
	public Sequences(int numeroSeq) {
		super();
		this.numeroSeq = numeroSeq;
	}



	/**
	 * @return the numeroSeq
	 */
	public int getNumeroSeq() {
		return numeroSeq;
	}



	/**
	 * @param numeroSeq the numeroSeq to set
	 */
	public void setNumeroSeq(int numeroSeq) {
		this.numeroSeq = numeroSeq;
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
	 * @return the listofEval
	 */
	public Collection<Evaluations> getListofEval() {
		return listofEval;
	}
	
	public List<Evaluations> getListofEvalSeqDeCours(Long idCoursConcerne){
		List<Evaluations> listofEvalSeqDeCours = new ArrayList<Evaluations>();
		
		List<Evaluations> listofEvalDeSeq = (List<Evaluations>) this.getListofEval();
		
		if(listofEvalDeSeq != null){
			for(Evaluations eval : listofEvalDeSeq){
				if(eval.getCours().getIdCours().longValue() == idCoursConcerne.longValue()){
					listofEvalSeqDeCours.add(eval);
				}
			}
		}
		
		return listofEvalSeqDeCours;
	}



	/**
	 * @param listofEval the listofEval to set
	 */
	public void setListofEval(Collection<Evaluations> listofEval) {
		this.listofEval = listofEval;
	}



	/**
	 * @return the listofBullseq
	 */
	public Collection<BulletinsSeq> getListofBullseq() {
		return listofBullseq;
	}

	public List<BulletinsSeq> getListofBulletinsSeqRegulierDeClasse(Long idClasse){
		//liste des bulletinsSeq régulier de la classe
		List<BulletinsSeq> listofBulletinsSeqRegulierDeClasse = new ArrayList<BulletinsSeq>();
		
		List<BulletinsSeq> listofBulletinsSeq = (List<BulletinsSeq>) this.getListofBullseq();
		if(listofBulletinsSeq != null){
			for(BulletinsSeq bulletinsSeq : listofBulletinsSeq){
				if(bulletinsSeq.estRegulier(this.getIdPeriodes()) == 1){
					listofBulletinsSeqRegulierDeClasse.add(bulletinsSeq);
				}
			}
		}
		
		/*
		 * Maintenant il faut classer cette liste de bulletins regulier dans l'ordre decroissant des moyennes
		 */
		Comparator<BulletinsSeq> monComparator = new Comparator<BulletinsSeq>() {

			@Override
			public int compare(BulletinsSeq arg0, BulletinsSeq arg1) {
				// TODO Auto-generated method stub
				int n = 0;
				
				if(arg0.getMoyenneBulletins() < arg1.getMoyenneBulletins()) n = -1;
				
				if(arg0.getMoyenneBulletins() > arg1.getMoyenneBulletins()) n = 1;
				
				if(arg0.getMoyenneBulletins() == arg1.getMoyenneBulletins()) {
					if(arg0.getEleve().getNomsEleves().compareTo(arg1.getEleve().getNomsEleves()) < 0 ) n = -1;
					
					if(arg0.getEleve().getNomsEleves().compareTo(arg1.getEleve().getNomsEleves()) > 0 ) n = 1;
					
					if(arg0.getEleve().getNomsEleves().compareTo(arg1.getEleve().getNomsEleves()) == 0 ) {
						if(arg0.getEleve().getPrenomsEleves().compareTo(arg1.getEleve().getPrenomsEleves()) < 0 ) n = -1;
						
						if(arg0.getEleve().getPrenomsEleves().compareTo(arg1.getEleve().getPrenomsEleves()) > 0 ) n = 1;
						
						if(arg0.getEleve().getPrenomsEleves().compareTo(arg1.getEleve().getPrenomsEleves()) == 0 ) {
							if(arg0.getEleve().getDatenaissEleves().compareTo(arg1.getEleve().getDatenaissEleves()) < 0 ) n = -1;
							
							if(arg0.getEleve().getDatenaissEleves().compareTo(arg1.getEleve().getDatenaissEleves()) > 0 ) n = 1;
						}
						
					}
				}
				
				return n;
			}
			
		};
		
		Collections.sort(listofBulletinsSeqRegulierDeClasse, monComparator);
		
		return listofBulletinsSeqRegulierDeClasse;
	}
	
	
	
	/**********************
	 * Cette methode retourne la liste des bulletins Sequentiels d'une classe (donc de tous les élèves d'une classe)
	 * classé dans l'ordre décroissant des moyennes qu'il porte. Ainsi on pourra inscrire les rangs sur ces bulletins.
	 * Cette methode sera donc utilisé lorsque voudra inscrire les rangs sur les bulletins. 
	 * @param idClasse
	 * @return
	 */
	public List<BulletinsSeq> getListofBulletinsSeqDeClasse(Long idClasse){
		List<BulletinsSeq> listofBulletinsSeqDeClasse = new ArrayList<BulletinsSeq>();
		List<BulletinsSeq> listofBulletinsSeq = (List<BulletinsSeq>) this.getListofBullseq();
		if(listofBulletinsSeq != null){
			for(BulletinsSeq bulletinsSeq : listofBulletinsSeq){
				if(bulletinsSeq.getEleve().getClasse().getIdClasses().longValue() == idClasse.longValue()){
					listofBulletinsSeqDeClasse.add(bulletinsSeq);
				}
			}
		}
		
		/*
		 * ici on a la liste des bulletinsSeq de la classe dont l'identifiant est passé en parametre.
		 * Il est question de les classer dans l'ordre décroissant des moyennes porter par ces bulletins
		 */
		Comparator<BulletinsSeq> monComparator = new Comparator<BulletinsSeq>() {

			@Override
			public int compare(BulletinsSeq arg0, BulletinsSeq arg1) {
				// TODO Auto-generated method stub
				int n = 0;
				
				if(arg0.getMoyenneBulletins() < arg1.getMoyenneBulletins()) n = -1;
				
				if(arg0.getMoyenneBulletins() > arg1.getMoyenneBulletins()) n = 1;
				
				if(arg0.getMoyenneBulletins() == arg1.getMoyenneBulletins()) {
					if(arg0.getEleve().getNomsEleves().compareTo(arg1.getEleve().getNomsEleves()) < 0 ) n = -1;
					
					if(arg0.getEleve().getNomsEleves().compareTo(arg1.getEleve().getNomsEleves()) > 0 ) n = 1;
					
					if(arg0.getEleve().getNomsEleves().compareTo(arg1.getEleve().getNomsEleves()) == 0 ) {
						if(arg0.getEleve().getPrenomsEleves().compareTo(arg1.getEleve().getPrenomsEleves()) < 0 ) n = -1;
						
						if(arg0.getEleve().getPrenomsEleves().compareTo(arg1.getEleve().getPrenomsEleves()) > 0 ) n = 1;
						
						if(arg0.getEleve().getPrenomsEleves().compareTo(arg1.getEleve().getPrenomsEleves()) == 0 ) {
							if(arg0.getEleve().getDatenaissEleves().compareTo(arg1.getEleve().getDatenaissEleves()) < 0 ) n = -1;
							
							if(arg0.getEleve().getDatenaissEleves().compareTo(arg1.getEleve().getDatenaissEleves()) > 0 ) n = 1;
						}
						
					}
				}
				
				return n;
			}
			
		};
		
		Collections.sort(listofBulletinsSeqDeClasse, monComparator);
		
		return listofBulletinsSeqDeClasse;
	}


	/**
	 * @param listofBullseq the listofBullseq to set
	 */
	public void setListofBullseq(Collection<BulletinsSeq> listofBullseq) {
		this.listofBullseq = listofBullseq;
	}



	/**
	 * @return the trimestre
	 */
	public Trimestres getTrimestre() {
		return trimestre;
	}



	/**
	 * @param trimestre the trimestre to set
	 */
	public void setTrimestre(Trimestres trimestre) {
		this.trimestre = trimestre;
	}
	
	

}
