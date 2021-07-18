/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.logesco.modeles.NoteFinale;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@DiscriminatorValue("sequentiel")
public class BulletinsSeq extends Bulletins implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/******************************************
	 * Mapping des associations avec les autres tables
	 ******************************************/
	/*
	 * Association avec la table Sequences
	 ******************************************/
	@ManyToOne
	Sequences sequence;
	
	/*
	 * Association avec la table BulletinsTrim
	 ******************************************/
	@ManyToOne
	BulletinsTrim bulletinTrim;
	
	/*
	 * Association avec la table NotesEval
	 ******************************************/
	@OneToMany(mappedBy="bulletinSeq")
	Collection<NotesEval> listofnotesEval;  
	
	/*
	 * Association avec la table Eleves
	 ******************************************/
	@ManyToOne
	Eleves eleve;

	/**
	 * 
	 */
	public BulletinsSeq() {
		// TODO Auto-generated constructor stub
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
	 * @return the bulletinTrim
	 */
	public BulletinsTrim getBulletinTrim() {
		return bulletinTrim;
	}

	/**
	 * @param bulletinTrim the bulletinTrim to set
	 */
	public void setBulletinTrim(BulletinsTrim bulletinTrim) {
		this.bulletinTrim = bulletinTrim;
	}

	/**
	 * @return the listofnotesEval
	 */
	public Collection<NotesEval> getListofnotesEval() {
		return listofnotesEval;
	}

	public int estRegulier(Long idSequence){
		/*
		 * liste des notes d'evaluation dans le bulletin sequentiel. Ce qui correspond aux notes d'evaluation de l'élève qui possède le
		 * bulletin. la taille de cette liste doit donc être egale à celle des évaluations passe dans cette classe. 
		 */
		List<NotesEval> listofNotesEvalDeBulletinsSeq = (List<NotesEval>) this.getListofnotesEval();
		/*
		 * On va mettre dans listofEvaluationsDeSeq la liste de toutes les evaluations de tous les cours passant dans la classe
		 * de l'eleve possedant ce bulletins. La taille de cette liste doit donc etre egale à celle des notesEval pour que l'eleve
		 * soit regulier.
		 */
		List<Evaluations> listofEvaluationsDeSeq = new ArrayList<Evaluations>();
		List<Cours> listofCoursDeClasseEleveBullSeq = (List<Cours>) this.getEleve().getClasse().getListofCours();
		
		if(listofCoursDeClasseEleveBullSeq != null){
			for(Cours cours : listofCoursDeClasseEleveBullSeq){
				List<Evaluations> listofEvalCoursDansSeq = cours.getListofEvalDeCoursDansSeq(idSequence);
				if(listofEvalCoursDansSeq != null){
					for(Evaluations eval : listofEvalCoursDansSeq){
						listofEvaluationsDeSeq.add(eval);
					}
				}
			}
		}
		
		if(listofNotesEvalDeBulletinsSeq.size() == listofEvaluationsDeSeq.size()) return 1;
		
		return 0;
	}
	
	/**
	 * @param listofnotesEval the listofnotesEval to set
	 */
	public void setListofnotesEval(Collection<NotesEval> listofnotesEval) {
		this.listofnotesEval = listofnotesEval;
	}

	/**
	 * @return the eleve
	 */
	public Eleves getEleve() {
		return eleve;
	}

	/**
	 * @param eleve the eleve to set
	 */
	public void setEleve(Eleves eleve) {
		this.eleve = eleve;
	}
	
	public List<NotesEval> getAllNoteEvalDeCours(Long idCours){
		List<NotesEval> listofNotesEvalDeCours = new ArrayList<NotesEval>();
		System.err.println("Nombre de notesEval existant "+this.getListofnotesEval().size());
		for(NotesEval noteEval: this.getListofnotesEval()){
			System.err.println("idCours == "+idCours.longValue()+"  et le cours scruter est == "+noteEval.getEvaluation().getCours().getIdCours().longValue());
			if(noteEval.getEvaluation().getCours().getIdCours().longValue() == idCours.longValue()){
				listofNotesEvalDeCours.add(noteEval);
			}
		}
		System.err.println("Nombre de notesEval trouve "+listofNotesEvalDeCours.size());
		return listofNotesEvalDeCours;
	}
	
	public double getNoteFinaleCours(Long idCours){
		double noteFinale = 0;
		NoteFinale noteF = new NoteFinale();
		for(NotesEval noteEval: this.getAllNoteEvalDeCours(idCours)){
			
			System.err.println("noteEval de "+noteEval.getEvaluation().getTypeEval()+" valeur "+noteEval.getValeurnoteEval());
			if(noteEval.getEvaluation().getCours().getIdCours().longValue() == idCours.longValue()){
				if(noteEval.getEvaluation().getTypeEval().equals("CC")==true){
					noteF.setNoteCC(noteEval.getValeurnoteEval());
					noteF.setPourcentageCC(noteEval.getEvaluation().getProportionEval());
					System.err.println("noteF.pourCC == "+noteF.getPourcentageCC());
					System.err.println("noteF.noteCC == "+noteF.getNoteCC());
				}
				else if(noteEval.getEvaluation().getTypeEval().equals("DS")==true){
					noteF.setNoteDS(noteEval.getValeurnoteEval());
					noteF.setPourcentageDS(noteEval.getEvaluation().getProportionEval());
					System.err.println("noteF.pourDS == "+noteF.getPourcentageDS());
					System.err.println("noteF.noteDS == "+noteF.getNoteDS());
				}
				
				noteFinale = noteF.calculNoteFinale();
				System.err.println("noteFinale ==> "+noteFinale);
				
			}
		}
		return noteFinale;
	}

	
	

}
