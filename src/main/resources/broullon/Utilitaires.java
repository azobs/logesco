/**
 * 
 */
package org.logesco.services;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.logesco.entities.Annee;
import org.logesco.entities.BulletinsSeq;
import org.logesco.entities.Classes;
import org.logesco.entities.Cours;
import org.logesco.entities.Eleves;
import org.logesco.entities.NotesEval;
import org.logesco.entities.Sequences;
import org.logesco.entities.Trimestres;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

/**
 * @author cedrickiadjeu
 *
 */
public class Utilitaires implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UtilitairesBulletins ub;
	/**
	 * 
	 */
	public Utilitaires() {
		// TODO Auto-generated constructor stub
		ub = new UtilitairesBulletins();
	}
	
	public static List<Eleves> getListofeleveTrieparordrealphabetique(List<Eleves> listofEleves){
		
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
		
		Collections.sort((List<Eleves>)listofEleves, monComparator);

		return listofEleves;
	}
	
	
	public static Collection<Eleves> getMoyenneSequentielOrdreDecroissant1(Classes classe, Sequences sequence){
		UtilitairesBulletins ub = new UtilitairesBulletins();
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {
			UtilitairesBulletins ub = new UtilitairesBulletins();
			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n=0;
				if(ub.getMoyenneSequentiel(arg0, sequence)>=0 && ub.getMoyenneSequentiel(arg1, sequence)>=0){
					if(ub.getMoyenneSequentiel(arg0, sequence)>ub.getMoyenneSequentiel(arg1, sequence)) n=-1;
					
					if(ub.getMoyenneSequentiel(arg0, sequence)<ub.getMoyenneSequentiel(arg1, sequence)) n=1;
				}
				return n;
			}
			
		};
		
		List<Eleves> listofElevesayantcomposeaumoinsunefois = 
				ub.getListofEleveAyantComposeAuMoinsUneFoisDansSequence(classe, sequence);
		Collections.sort((List<Eleves>)listofElevesayantcomposeaumoinsunefois, monComparator);
		
		return listofElevesayantcomposeaumoinsunefois;
	}
	
	
	public static Collection<Eleves> getMoyenneTrimestrielOrdreDecroissant1(Classes classe, Trimestres trimestre){
		UtilitairesBulletins ub = new UtilitairesBulletins();
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {
			UtilitairesBulletins ub = new UtilitairesBulletins();
			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n=0;
				if(ub.getMoyenneTrimestriel(arg0, trimestre)>=0 && ub.getMoyenneTrimestriel(arg1, trimestre)>=0){
					if(ub.getMoyenneTrimestriel(arg0, trimestre)>ub.getMoyenneTrimestriel(arg1, trimestre)) n=-1;
					
					if(ub.getMoyenneTrimestriel(arg0, trimestre)<ub.getMoyenneTrimestriel(arg1, trimestre)) n=1;
				}
				return n;
			}
			
		};
		
		List<Eleves> listofElevesayantcomposeaumoinsunefois = 
				ub.getListofEleveAyantComposeAuMoinsUneFoisDansTrimestre(classe, trimestre);
		Collections.sort((List<Eleves>)listofElevesayantcomposeaumoinsunefois, monComparator);
		
		return listofElevesayantcomposeaumoinsunefois;
	}
	
	public static Collection<Eleves> getMoyenneAnnuelOrdreDecroissant1(Classes classe, Annee annee){
		UtilitairesBulletins ub = new UtilitairesBulletins();
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {
			UtilitairesBulletins ub = new UtilitairesBulletins();

			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n=0;
				if(ub.getMoyenneAnnuel(arg0, annee)>=0 && ub.getMoyenneAnnuel(arg1, annee)>=0){
					if(ub.getMoyenneAnnuel(arg0, annee)>ub.getMoyenneAnnuel(arg1, annee)) n=-1;
					
					if(ub.getMoyenneAnnuel(arg0, annee)<ub.getMoyenneAnnuel(arg1, annee)) n=1;
				}
				return n;
			}
		};
		
		List<Eleves> listofElevesayantcomposeaumoinsunefois = 
				ub.getListofEleveAyantComposeAuMoinsUneFoisDansAnnee(classe, annee);
		Collections.sort((List<Eleves>)listofElevesayantcomposeaumoinsunefois, monComparator);
		
		return listofElevesayantcomposeaumoinsunefois;
	}

	
	
	@Override
	public double calculTotalNotesCoefDeNotesEvalDansListess(List<NotesEval> listofnotesEvalDeSeq){
		double totalNotesCoef = 0;
		////System.err.println("debut de calculTotalNotesCoefDeNotesEvalDansListe avec totalNotesCoef == "+totalNotesCoef);
		for(NotesEval neSeq : listofnotesEvalDeSeq){
			////System.err.println("NoteEval du cours "+neSeq.getEvaluation().getCours().getCodeCours()+" dans l'eval de type "+neSeq.getEvaluation().getTypeEval());
			double notesACoefficie =  neSeq.getValeurnoteEval();
			////System.err.println("la valeur de cette note est "+notesACoefficie);
			double proportionNote = neSeq.getEvaluation().getProportionEval() * 0.01;
			////System.err.println("cette note compte pour  "+proportionNote);
			double coefficiant = neSeq.getEvaluation().getCours().getCoefCours();
			////System.err.println("et est coefficie par  "+coefficiant);
			double notesCoefficie = notesACoefficie * proportionNote * coefficiant;
			////System.err.println("la valeur de la note coefficie est donc  "+notesCoefficie);
			totalNotesCoef = totalNotesCoef + notesCoefficie;
		}
		////System.err.println("fin de calculTotalNotesCoefDeNotesEvalDansListe avec la valeur avec totalNotesCoef == "+totalNotesCoef);
		return totalNotesCoef;
	}
	
	
	@Override
	public double calculTotalCoefficientDeCoursDansListe(List<NotesEval> listofnotesEvalDeSeq){
		double totalCoef = 0;
		////System.err.println("debut de calculTotalCoefficientDeCoursDansListe avec totalCoef == "+totalCoef);
		List<Cours> listofCoursAssocieAuxNoteEvalDeListe = new ArrayList<Cours>();
		for(NotesEval neSeq : listofnotesEvalDeSeq){

			Cours coursAssocie = neSeq.getEvaluation().getCours();
			int r = 0;
			//est ce que ce cours est deja dans la liste?
			for(Cours c : listofCoursAssocieAuxNoteEvalDeListe){
				if(c.getIdCours().longValue() == coursAssocie.getIdCours().longValue()) {
					r = 1;
					break;
				}
			}

			if(r == 0) listofCoursAssocieAuxNoteEvalDeListe.add(coursAssocie);

		}

		for(Cours coursNoteEval : listofCoursAssocieAuxNoteEvalDeListe){
			double coefCoursAssocieNotesEval = coursNoteEval.getCoefCours();
			////System.err.println("coef de "+coursNoteEval.getCodeCours()+" == "+coursNoteEval.getCoefCours());
			totalCoef = totalCoef + coefCoursAssocieNotesEval;
		}

		////System.err.println("fin de calculTotalCoefficientDeCoursDansListe avec totalCoef == "+totalCoef);
		return totalCoef;
	}
	
	

	public int associerlesNotesEvalDeListeAuBulletinsSeq(List<NotesEval> listofnotesEvalDeSeq, BulletinsSeq bulletinsSeq){
		int retour = 0;
		////System.err.println("debut de associerlesNotesEvalDeListeAuBulletinsSeq avec retour == "+retour);
		for(NotesEval neSeq : listofnotesEvalDeSeq){
			if(neSeq.getBulletinSeq() == null){
				neSeq.setBulletinSeq(bulletinsSeq);
				try{
					notesEvalRepository.save(neSeq);
					retour = 1;
				}
				catch(Exception e){
					retour = 0;
					e.printStackTrace();
				}
			}

		}
		////System.err.println("fin de associerlesNotesEvalDeListeAuBulletinsSeq avec retour == "+retour);
		return retour;
	}
	
	


	@Override
	public List<BulletinsSeq> calculerListofBulletinsSeqDeClasse(Long idClasseConcerne, Long idSequenceConcerne){
		List<BulletinsSeq> listofBulletinsSeqDeClasse = new ArrayList<BulletinsSeq>();
		////System.err.println("debut de calculerListofBulletinsSeqDeClasse avec listofBulletinsSeqDeClasse == "+listofBulletinsSeqDeClasse.size());
		Sequences sequenceConcerne = this.findSequences(idSequenceConcerne);

		Classes classeConcerne = this.findClasses(idClasseConcerne);

		List<Eleves> listofEleveDeClasseConcerne = (List<Eleves>) classeConcerne.getListofEleves();

		for(Eleves eleve : listofEleveDeClasseConcerne){

			////System.err.println("**********************debut du calcul du bulletin de "+eleve.getNomsEleves()+" ****************");
			/*
			 * Est ce que l'eleve dont on dispose a deja un bulletin pour la sequence concerne?
			 */
			BulletinsSeq bulletinsSeqEleveExist = eleve.getBulletinsSeqDeSeq(sequenceConcerne.getIdPeriodes());

			List<NotesEval> listofnotesEvalDeSeq = eleve.getListofnotesEvalDeSeq(sequenceConcerne.getIdPeriodes());

			double totalValeurNoteDeListNoteEvalEleve = this.calculTotalNotesCoefDeNotesEvalDansListe(listofnotesEvalDeSeq);
			double totalcoefCoursDeListNoteEvalEleve = this.calculTotalCoefficientDeCoursDansListe(listofnotesEvalDeSeq);

			double moyenneEleve_flotante = 0;
			double moyenneEleve = 0;

			java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
			/*double nombre = 2.45646;
			System.out.println("nombre est : " + df.format(nombre));*/

			if(totalcoefCoursDeListNoteEvalEleve > 0) {

				moyenneEleve_flotante = totalValeurNoteDeListNoteEvalEleve/totalcoefCoursDeListNoteEvalEleve;
				// moyenneEleve = Double.parseDouble(df.format(moyenneEleve_flotante));
				try {
					moyenneEleve =df.parse(df.format(moyenneEleve_flotante)).doubleValue();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			////System.err.println("la moyenne de l'eleve "+eleve.getNomsEleves()+" pour les evaluations composées est "+moyenneEleve);

			if(bulletinsSeqEleveExist == null){
				BulletinsSeq bulletinsSeqEleve = new BulletinsSeq();

				bulletinsSeqEleve.setEleve(eleve);
				bulletinsSeqEleve.setSequence(sequenceConcerne);
				bulletinsSeqEleve.setMoyenneBulletins(moyenneEleve);
				bulletinsSeqEleve.setRangBulletins(0);

				bulletinsSeqEleve.setTotalcoef(totalcoefCoursDeListNoteEvalEleve);
				try {
					//moyenneEleve =df.parse(df.format(totalValeurNoteDeListNoteEvalEleve)).doubleValue();
					bulletinsSeqEleve.setTotalnotecoef(df.parse(df.format(totalValeurNoteDeListNoteEvalEleve)).doubleValue());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				bulletinsSeqRepository.save(bulletinsSeqEleve);

				/*
				 * Maintenant il faut associer toutes les notesEval au bulletinsSeq qu'on vient d'enregistrer
				 * afin que le bulletin puisse etre facilement construit lors de l'affichage
				 */
				this.associerlesNotesEvalDeListeAuBulletinsSeq(listofnotesEvalDeSeq, bulletinsSeqEleve);
				////System.err.println("l'association des notes au bulletin a retourner "+ret);
				listofBulletinsSeqDeClasse.add(bulletinsSeqEleve);
			}
			else{
				bulletinsSeqEleveExist.setMoyenneBulletins(moyenneEleve);
				bulletinsSeqEleveExist.setRangBulletins(0);

				bulletinsSeqEleveExist.setTotalcoef(totalcoefCoursDeListNoteEvalEleve);
				// bulletinsSeqEleveExist.setTotalnotecoef(Double.parseDouble(df.format(totalValeurNoteDeListNoteEvalEleve)));
				try {
					//moyenneEleve =df.parse(df.format(totalValeurNoteDeListNoteEvalEleve)).doubleValue();
					bulletinsSeqEleveExist.setTotalnotecoef(df.parse(df.format(totalValeurNoteDeListNoteEvalEleve)).doubleValue());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				bulletinsSeqRepository.save(bulletinsSeqEleveExist);

				/*
				 * Maintenant il faut associer toutes les notesEval au bulletinsSeq qu'on vient d'enregistrer
				 * afin que le bulletin puisse etre facilement construit lors de l'affichage
				 */
				this.associerlesNotesEvalDeListeAuBulletinsSeq(listofnotesEvalDeSeq, bulletinsSeqEleveExist);
				////System.err.println("l'association des notes au bulletin a retourner "+ret);
				listofBulletinsSeqDeClasse.add(bulletinsSeqEleveExist);
			}
			////System.err.println("===================== fin du calcul du bulletin de "+eleve.getNomsEleves()+"========================");
		}
		////System.err.println("fin de calculerListofBulletinsSeqDeClasse avec listofBulletinsSeqDeClasse == "+listofBulletinsSeqDeClasse.size());


		/*for(BulletinsSeq bullSeq : listofBulletinsSeqDeClasse){
			//System.err.println("bullSeq.getEleve().getNomsEleves()===="+bullSeq.getEleve().getNomsEleves()+
					"   "+"bullSeq.getMoyenneBulletins()===="+bullSeq.getMoyenneBulletins()+
					"  "+"bullSeq.getDecisionBulletins()===="+bullSeq.getDecisionBulletins());
			System.err.println();
			System.err.println();
		}*/

		return listofBulletinsSeqDeClasse;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	switch(j){
		case 1:{
			try{
				String nomsElv = cell.getStringCellValue();
				System.err.println("nomseleve "+nomsElv);
				eleveAValider.setNomsEleves(nomsElv);
			}
			catch(Exception e){
				listofError.add("ligne "+i+" colonne "+j);
			}
		}
		case 2:{
			try{
				String prenomsElv = cell.getStringCellValue();
				System.err.println("prenomsElv "+prenomsElv);
				eleveAValider.setPrenomsEleves(prenomsElv);
			}
			catch(Exception e){
				listofError.add("ligne "+i+" colonne "+j);
			}
		}
		case 3:{
			try{
				Date dateJ = cell.getDateCellValue();
				SimpleDateFormat spdj = new SimpleDateFormat("yyyy-MM-dd");//"dd-MM-yyyy"
				String datString = spdj.format(dateJ);
				Date dateFormat = spdj.parse(datString);
				
				eleveAValider.setDatenaissEleves(dateFormat);
				
				System.err.println("la cellule "+j+" de la ligne "+i+" est de type "
					+cell.getCellType()+" et sa valeur est "+cell.getDateCellValue());
			}
			catch(Exception e){
				/*
				 * la colonne de date a un souci donc faut signaler une erreur dans le 
				 * fichier pour cette ligne et cette colonne
				 */
				listofError.add("ligne "+i+" colonne "+j);
			}
		}
		case 4:{
			try{
				String lieunaissance = cell.getStringCellValue();
				System.err.println("lieunaissance "+lieunaissance);
				eleveAValider.setLieunaissEleves(lieunaissance);
			}
			catch(Exception e){
				listofError.add("ligne "+i+" colonne "+j);
			}
		}
		case 5:{
			try{
				String sexeElv = cell.getStringCellValue();
				System.err.println("sexeElv "+sexeElv);
				String sexeEleves = "";
				if(sexeElv.equalsIgnoreCase("F")==true || 
						sexeElv.equalsIgnoreCase("FEMININ")==true || 
						sexeElv.equalsIgnoreCase("féminin")==true){
					sexeEleves = "feminin";
				}
				else if(sexeElv.equalsIgnoreCase("M")==true || 
						sexeElv.equalsIgnoreCase("MASCULIN")==true){
					sexeEleves = "masculin";
				}
				eleveAValider.setSexeEleves(sexeEleves);
			}
			catch(Exception e){
				listofError.add("ligne "+i+" colonne "+j);
			}
		}
		case 6:{
			try{
				String nationalite = cell.getStringCellValue();
				System.err.println("nationalite "+nationalite);
				eleveAValider.setNationaliteEleves(nationalite);
			}
			catch(Exception e){
				listofError.add("ligne "+i+" colonne "+j);
			}
		}
		case 7:{
			try{
				String quartier = cell.getStringCellValue();
				System.err.println("quartier "+quartier);
				eleveAValider.setQuartierEleves(quartier);
			}
			catch(Exception e){
				listofError.add("ligne "+i+" colonne "+j);
			}
		}
		case 8:{
			try{
				String ville = cell.getStringCellValue();
				System.err.println("ville "+ville);
				eleveAValider.setVilleEleves(ville);
			}
			catch(Exception e){
				listofError.add("ligne "+i+" colonne "+j);
			}
		}
		case 9:{
			try{
				String tel = cell.getStringCellValue();
				System.err.println("tel "+tel);
				eleveAValider.setNumtel1Parent(tel);
			}
			catch(Exception e){
				listofError.add("ligne "+i+" colonne "+j);
			}
		}
		case 10:{
			try{
				String email = cell.getStringCellValue();
				System.err.println("email "+email);
				eleveAValider.setEmailParent(email);
			}
			catch(Exception e){
				listofError.add("ligne "+i+" colonne "+j);
			}
		}
		default: 
			try{
				System.err.println("la cellule "+j+" de la ligne "+i+" est de type "
						+cell.getCellType()+" et sa valeur est "+cell.getStringCellValue());
			}
			catch(Exception e){
				
			}
	}

	
	
	
	//***Valider l'objet eleveAValider ainsi construit et preciser les erreurs s'il y a lieu
	/*
	 * Il faut d'abord completer les champs manquants de l'entite eleve
	 */
	eleveAValider.setEtatInscEleves("non inscrit");
	eleveAValider.setRedoublant("non");
	eleveAValider.setStatutEleves("nouveau");
	String matricule = usersService.getNextMatricule(codeEtab, anneeString);
	eleveAValider.setMatriculeEleves(matricule);
	System.err.println("eleveAValider  "+eleveAValider.toString());
	//Maintenant on peut passer a la validation
	DataBinder binder = new DataBinder(eleveAValider);
	BindingResult results = binder.getBindingResult();
	validator.validate(eleveAValider, results);
	System.err.println("rrrr----------------- "+results.toString()+" -------------------rrr");
	if (results.hasErrors()) {
		/*
		 * On redirectionne sur la même page de choix du fichier en precisant les
		 * éventuels erreurs dans le fichier
		 */
		System.err.println("erreur de ligne "+i+" colonne "+j);
		String erreur = " Certaines colonnes du fichier sélectionné ne sont pas valides";
		model.addAttribute("enregListError", erreur);
		this.constructModelEnregListEleves(model, request);
		return "users/enregListEleves";
	}
	else{
		//***si il n'y a pas d'erreur alors placer l'objet ainsi construit dans la liste
		listofElevesAEnreg.add(eleveAValider);
		System.err.println("on a ajoute l'eleve de la ligne "+i+" de la colonne "+j);
	}
	
	
}
