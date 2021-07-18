/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="evaluations")
public class Evaluations implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idEval;
	private String contenuEval;
	@NotNull
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateenregEval;
	@NotNull
	private int proportionEval;
	@NotNull
	@NotEmpty
	private String typeEval;//DS = Devoir de séquence CC = Contrôle continu
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Sequences
	 ******************************************/
	@ManyToOne
	Sequences sequence;
	
	/*
	 * Association avec la table NotesEval
	 ******************************************/
	@OneToMany(mappedBy="evaluation")
	Collection<NotesEval> listofnotesEval;
	
	/*
	 * Association avec la table Cours
	 ******************************************/
	@ManyToOne
	Cours cours;

	/**
	 * 
	 */
	public Evaluations() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param dateenregEval
	 * @param proportionEval
	 * @param typeEval
	 */
	public Evaluations(Date dateenregEval, int proportionEval, String typeEval) {
		super();
		this.dateenregEval = dateenregEval;
		this.proportionEval = proportionEval;
		this.typeEval = typeEval;
	}

	/**
	 * @return the idEval
	 */
	public Long getIdEval() {
		return idEval;
	}

	/**
	 * @param idEval the idEval to set
	 */
	public void setIdEval(Long idEval) {
		this.idEval = idEval;
	}

	/**
	 * @return the contenuEval
	 */
	public String getContenuEval() {
		return contenuEval;
	}

	/**
	 * @param contenuEval the contenuEval to set
	 */
	public void setContenuEval(String contenuEval) {
		this.contenuEval = contenuEval;
	}

	/**
	 * @return the dateenregEval
	 */
	public Date getDateenregEval() {
		return dateenregEval;
	}

	/**
	 * @param dateenregEval the dateenregEval to set
	 */
	public void setDateenregEval(Date dateenregEval) {
		this.dateenregEval = dateenregEval;
	}

	/**
	 * @return the proportionEval
	 */
	public int getProportionEval() {
		return proportionEval;
	}

	/**
	 * @param proportionEval the proportionEval to set
	 */
	public void setProportionEval(int proportionEval) {
		this.proportionEval = proportionEval;
	}

	/**
	 * @return the typeEval
	 */
	public String getTypeEval() {
		return typeEval;
	}

	/**
	 * @param typeEval the typeEval to set
	 */
	public void setTypeEval(String typeEval) {
		this.typeEval = typeEval;
	}

	/**
	 * @return the sequence
	 */
	public Sequences getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Sequences sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the listofnotesEval
	 */
	public Collection<NotesEval> getListofnotesEval() {
		Comparator<NotesEval> monComparator = new Comparator<NotesEval>() {

			@Override
			public int compare(NotesEval arg0, NotesEval arg1) {
				int n = 0;
				
				if(arg0.getEleve().getNomsEleves().toLowerCase().compareTo(
						arg1.getEleve().getNomsEleves().toLowerCase()) < 0) n=-1;
				
				if(arg0.getEleve().getNomsEleves().toLowerCase().compareTo(
						arg1.getEleve().getNomsEleves().toLowerCase()) > 0) n=1;
				
				if(arg0.getEleve().getNomsEleves().toLowerCase().compareTo(
						arg1.getEleve().getNomsEleves().toLowerCase()) == 0) {
					
					if(arg0.getEleve().getPrenomsEleves().toLowerCase().compareTo(
							arg1.getEleve().getPrenomsEleves().toLowerCase()) < 0) n=-1;
					
					if(arg0.getEleve().getPrenomsEleves().toLowerCase().compareTo(
							arg1.getEleve().getPrenomsEleves().toLowerCase()) > 0) n=1;
					
					if(arg0.getEleve().getPrenomsEleves().toLowerCase().compareTo(
							arg1.getEleve().getPrenomsEleves().toLowerCase()) == 0) {
						
						if(arg0.getEleve().getDatenaissEleves().compareTo(
								arg1.getEleve().getDatenaissEleves()) < 0) n=-1;
						
						if(arg0.getEleve().getDatenaissEleves().compareTo(
								arg1.getEleve().getDatenaissEleves()) < 0) n=1;
						
					}
					
				};
				
				return n;
			}
			
		};
		
		Collections.sort((List<NotesEval>)this.listofnotesEval, monComparator);
		return listofnotesEval;
	}

	/**
	 * @param listofnotesEval the listofnotesEval to set
	 */
	public void setListofnotesEval(Collection<NotesEval> listofnotesEval) {
		this.listofnotesEval = listofnotesEval;
	}

	/**
	 * @return the cours
	 */
	public Cours getCours() {
		return cours;
	}

	/**
	 * @param cours the cours to set
	 */
	public void setCours(Cours cours) {
		this.cours = cours;
	}
	
	

}
