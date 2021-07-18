/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="cours")
public class Cours implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idCours;
	@NotNull
	@NotEmpty
	private String codeCours;
	@NotNull
	@NotEmpty
	private String intituleCours;
	@NotNull
	private double coefCours;
	
	/***
	 * Debut des ajouts du 19-08-2018
	 */
	@NotEmpty
	@Size(min= 2, max= 100)
	private String intitule2langueCours;
	
	/*
	 * Les cours sont repartis par module(groupe) dans une classe. 2 cours de la même matiere(departement) ne sont pas 
	 * forcement dans les memes groupes dses différentes classes.
	 *  les groupes dependront des classes dans lesquelles ils sont enseignés. Et dans ce cas 
	 * ils peuvent appartenir plutôt aux cours de specialite ou de divers. o 	 */
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 100)
	private String groupeCours;
	
	@NotNull
	private int numerogroupeCours;
	
	/***
	 * Fin des ajouts du 19-08-2018
	 */
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Evaluations
	 ******************************************/
	@OneToMany(mappedBy="cours")
	Collection<Evaluations> listofEval;
	
	/*
	 * Association avec la table Classes
	 ******************************************/
	@ManyToOne
	Classes classe;
	
	/*
	 * Association avec la table Matieres
	 ******************************************/
	@ManyToOne
	Matieres matiere;
	
	/*
	 * Association avec la table Proffesseurs
	 ******************************************/
	@ManyToOne
	Proffesseurs proffesseur;

	/**
	 * 
	 */
	public Cours() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codeCours
	 * @param intituleCours
	 * @param coefCours
	 */
	public Cours(String codeCours, String intituleCours, int coefCours) {
		super();
		this.codeCours = codeCours;
		this.intituleCours = intituleCours;
		this.coefCours = coefCours;
	}

	/**
	 * @return the idCours
	 */
	public Long getIdCours() {
		return idCours;
	}

	/**
	 * @param idCours the idCours to set
	 */
	public void setIdCours(Long idCours) {
		this.idCours = idCours;
	}

	/**
	 * @return the codeCours
	 */
	public String getCodeCours() {
		return codeCours;
	}

	/**
	 * @param codeCours the codeCours to set
	 */
	public void setCodeCours(String codeCours) {
		this.codeCours = codeCours;
	}

	/**
	 * @return the intituleCours
	 */
	public String getIntituleCours() {
		return intituleCours;
	}

	/**
	 * @param intituleCours the intituleCours to set
	 */
	public void setIntituleCours(String intituleCours) {
		this.intituleCours = intituleCours;
	}

	/**
	 * @return the coefCours
	 */
	public double getCoefCours() {
		return coefCours;
	}

	/**
	 * @param coefCours the coefCours to set
	 */
	public void setCoefCours(double coefCours) {
		this.coefCours = coefCours;
	}

	/**
	 * @return the listofEval
	 */
	public Collection<Evaluations> getListofEval() {
		return listofEval;
	}
	
	public List<Evaluations> getListofEvalDeCoursDansSeq(Long idSequence){
		List<Evaluations> listofEvalDeCoursDansSeq = new ArrayList<Evaluations>();
		List<Evaluations> listofEvalCours = (List<Evaluations>) this.getListofEval();
		if(listofEvalCours != null){
			for(Evaluations eval : listofEvalCours){
				if(eval.getSequence().getIdPeriodes().longValue() == idSequence.longValue()){
					listofEvalDeCoursDansSeq.add(eval);
				}
			}
		}
		return listofEvalDeCoursDansSeq;
	}
	
	
	

	/**
	 * @param listofEval the listofEval to set
	 */
	public void setListofEval(Collection<Evaluations> listofEval) {
		this.listofEval = listofEval;
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
	 * @return the matiere
	 */
	public Matieres getMatiere() {
		return matiere;
	}

	/**
	 * @param matiere the matiere to set
	 */
	public void setMatiere(Matieres matiere) {
		this.matiere = matiere;
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

	/*******************************************************************
	 *  * 
	 * Methode qui dit si le cours courant a deja une evaluation dans la sequence
	 * Elle retourne donc le nombre d'evaluation dans la séquence sachant que le max est de 2 évaluations
	 * @param idSeq
	 * @return
	 */
	public int gettailleListEvalSeq(long idSeq){
		List<Evaluations> listofEvalCours = (List<Evaluations>) this.getListofEval();
		List<Evaluations> listofEvalSeq = new ArrayList<Evaluations>();
		if(listofEvalCours != null){
			for(Evaluations eval : listofEvalCours){
				if(eval.getSequence().getIdPeriodes().longValue() == idSeq){
					listofEvalSeq.add(eval);
				}
			}
		}
		return listofEvalSeq.size();
	}
	
	/*******************************************************************
	 *  * 
	 * Methode qui dit si le cours courant a deja une evaluation au moins dans chacune des sequences d'un trimestre
	 * Elle retourne donc le nombre d'evaluation dans la séquence sachant que le max est de 2 évaluations
	 * @param idTrim
	 * @return
	 */
	public int gettailleListEvalTrim(long idTrim, List<Evaluations> listofEvalCours){
		//List<Evaluations> listofEvalCours = (List<Evaluations>) this.getListofEval();
		List<Evaluations> listofEvalTrim = new ArrayList<Evaluations>();
		if(listofEvalCours != null){
			for(Evaluations eval : listofEvalCours){
				if(eval.getSequence().getTrimestre().getIdPeriodes().longValue() == idTrim){
					if(eval.getCours().getIdCours().longValue() == this.getIdCours().longValue()){
						listofEvalTrim.add(eval);
					}
				}
			}
		}
		return listofEvalTrim.size();
	}

	/**
	 * @return the intitule2langueCours
	 */
	public String getIntitule2langueCours() {
		return intitule2langueCours;
	}

	/**
	 * @param intitule2langueCours the intitule2langueCours to set
	 */
	public void setIntitule2langueCours(String intitule2langueCours) {
		this.intitule2langueCours = intitule2langueCours;
	}

	/**
	 * @return the groupeCours
	 */
	public String getGroupeCours() {
		return groupeCours;
	}

	/**
	 * @param groupeCours the groupeCours to set
	 */
	public void setGroupeCours(String groupeCours) {
		this.groupeCours = groupeCours;
	}

	/**
	 * @return the numerogroupeCours
	 */
	public int getNumerogroupeCours() {
		return numerogroupeCours;
	}

	/**
	 * @param numerogroupeCours the numerogroupeCours to set
	 */
	public void setNumerogroupeCours(int numerogroupeCours) {
		this.numerogroupeCours = numerogroupeCours;
	}
	
	
	
}
