/**
 * 
 */
package org.logesco.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.logesco.entities.Annee;
import org.logesco.entities.Classes;
import org.logesco.entities.Eleves;
import org.logesco.entities.Etablissement;
import org.logesco.entities.Niveaux;
import org.logesco.entities.RapportDAbsence;
import org.logesco.entities.Sequences;
import org.logesco.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author cedrickiadjeu
 *
 */
@Controller
@RequestMapping(path="/logesco/users/sg")
public class SGController {
	@Autowired
	private IUsersService usersService;
	/**
	 * 
	 */
////////////////////////////////////DEBUT DES REQUETES DE TYPE GET ////////////////////////////////////////////
	
	public void constructModelgetdonneesSaisieRAbsences(Model model,	HttpServletRequest request){
		
		/*
		 * On place la liste des niveaux dans le modele sachant qu'on retrouvera les classes
		 */
		List<Niveaux> listofNiveaux = usersService.findAllNiveaux();
		
		model.addAttribute("listofNiveaux", listofNiveaux);
		
		
		Annee anneeActive = usersService.findAnneeActive();
		if(anneeActive != null) {
			model.addAttribute("anneeActive", anneeActive);
			/*
			 * On place la liste des séquences de l'année active
			 */
			List<Sequences> listofSequence = usersService.findAllSequenceActive(anneeActive.getIdPeriodes());
			model.addAttribute("listofSequenceActive", listofSequence);
		}
		
		
	}
	
	@GetMapping(path="/getdonneesSaisieRAbsences")
	public String getdonneesSaisieRAbsences(Model model, HttpServletRequest request){
		
		this.constructModelgetdonneesSaisieRAbsences(model,	request);
		
		return "users/donneesSaisieRAbsences";
	}
	
	@GetMapping(path="/getdonneesSaisieRDiscipline")
	public String getdonneesSaisieRDiscipline(Model model, HttpServletRequest request){
		
		this.constructModelgetdonneesSaisieRAbsences(model,	request);
		
		return "users/donneesSaisieRDiscipline";
	}
	
	public void constructModelgetformSaisieRAbsences(Model model,	HttpServletRequest request, Long idSequenceConcerne,  Long idClassesConcerne,  
			Long idEleves,  int numPageEleves, int taillePage ){
		
		Sequences sequenceConcerne = usersService.findSequences(idSequenceConcerne);
		
		model.addAttribute("sequenceConcerneRabs", sequenceConcerne);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("sequenceConcerneRabs", sequenceConcerne);
		
		Page<Eleves> pageofEleves=usersService.findPageElevesClasse(idClassesConcerne,	
				numPageEleves, taillePage);
		
		if(pageofEleves != null){
			
			if(pageofEleves.getContent().size()!=0){
				
				model.addAttribute("listofEleves", pageofEleves.getContent());
				int[] listofPagesEleves=new int[pageofEleves.getTotalPages()];
					
				model.addAttribute("listofPagesEleves", listofPagesEleves);
					
				model.addAttribute("pageCouranteEleves", numPageEleves);
				//System.err.println("la liste des élève contient "+pageofEleves.getContent().size());
			}
		}
		
		/*
		 * Il faut donc la liste de tous les élèves de la classe dans le modele dans le même ordre qu'ils vont apparaitre dans les pages
		 */
		List<Eleves> listofAllEleve = usersService.findListElevesClasse(idClassesConcerne);
		
		/*for(Eleves e : listofAllEleve){
			//System.err.println("AFFx  Noms "+e.getNomsEleves()+" ID: "+e.getIdEleves().longValue());
		}*/
	
	if(listofAllEleve != null){
		model.addAttribute("effectifTotal", listofAllEleve.size());
		
		if((listofAllEleve.size() > 0)){
			model.addAttribute("listofAllEleve", listofAllEleve);
			
			/*
			 * On doit charger dans le modèle le premier élève de la liste et à chaque fois on va charger l'élève dont 
			 * le numero est passé en paramètre
			 */
			int numEleve = usersService.getNumeroEleve(listofAllEleve, idEleves);
			
			////System.err.println("numEleve  "+numEleve+ " idEleves "+idEleves.longValue());
			model.addAttribute("numEleve", numEleve);
			
			
			/*
			 * Au chargement de la page idEleves vaudra 0 et par conséquent getNumeroEleve va retourner 0. 
			 * Il faut donc charger le premier élève de la liste. 
			 */
			if(numEleve == 0) numEleve = 1;
			
			Eleves eleveCharge =  listofAllEleve.get(numEleve-1);
			
			//System.err.println("le numero du gar charge est "+numEleve);
			//System.err.println("et le gar lui même a pour nom  "+eleveCharge.getNomsEleves());
			
			model.addAttribute("eleveCharge",eleveCharge);//car l'élève 1 est à l'indice 0 de la liste
			
			////System.err.println("AFFx1  "+listofAllEleve.get(numEleve-1).getNomsEleves());
			/*
			 * Il faut charger du même coup le rapport d'absence existant pour cet élève dans la séquence indiquée
			 */
			RapportDAbsence rabsEleveCharge = eleveCharge.getRapportDAbsenceSeq(idSequenceConcerne);
			
			if(rabsEleveCharge != null) model.addAttribute("rabsEleveCharge", rabsEleveCharge);
			
			
			
			}
		
		}
		
	}
	
	
	@GetMapping(path="/getformSaisieRAbsences")
	public String getformSaisieRAbsences(
			Model model, HttpServletRequest request,
			@RequestParam(name="idSequenceConcerne", defaultValue="-1") Long idSequenceConcerne,
			@RequestParam(name="idClassesConcerne", defaultValue="0") Long idClassesConcerne,
			@RequestParam(name="idEleves", defaultValue="0") Long idEleves,
			@RequestParam(name="numPageEleves", defaultValue="0") int numPageEleves,
			@RequestParam(name="taillePage", defaultValue="5") int taillePage){
		
		this.constructModelgetformSaisieRAbsences(model,	request, idSequenceConcerne, idClassesConcerne,  
				idEleves, numPageEleves, taillePage);
		
		return "users/formSaisieRAbsences";
	}
	
	public void constructModelgetformSaisieRDiscipline(Model model,	HttpServletRequest request, Long idSequenceConcerne,  Long idClassesConcerne,  
			Long idEleves,  int numPageEleves, int taillePage ){
		
		/*
		 * Il faut donc la liste de tous les élèves de la classe dans le modele dans le même ordre qu'ils vont apparaitre dans les pages
		 */
		List<Eleves> listofAllEleve = usersService.findListElevesClasse(idClassesConcerne);
		
		if(listofAllEleve != null){
			model.addAttribute("effectifTotal", listofAllEleve.size());
			
			if((listofAllEleve.size() > 0)){
				model.addAttribute("listofAllEleve", listofAllEleve);
				
				Sequences sequenceConcerne = usersService.findSequences(idSequenceConcerne);
				Classes classeConcerne = usersService.findClasses(idClassesConcerne);
				
				if(sequenceConcerne!=null && classeConcerne!=null){
					model.addAttribute("sequenceConcerneRabs", sequenceConcerne);
					
					model.addAttribute("classeConcerne", classeConcerne);
					
					model.addAttribute("affichechoixclasse", "oui");
				}
				else{
					model.addAttribute("affichechoixclasse", "non");
				}
				
			}
			
		}
		
	}
	
	
	@GetMapping(path="/getformSaisieRDiscipline")
	public String getformSaisieRDiscipline(
			Model model, HttpServletRequest request,
			@RequestParam(name="idSequenceConcerne", defaultValue="-1") Long idSequenceConcerne,
			@RequestParam(name="idClassesConcerne", defaultValue="0") Long idClassesConcerne,
			@RequestParam(name="idEleves", defaultValue="0") Long idEleves,
			@RequestParam(name="numPageEleves", defaultValue="0") int numPageEleves,
			@RequestParam(name="taillePage", defaultValue="5") int taillePage){
		
		this.constructModelgetformSaisieRDiscipline(model,	request, idSequenceConcerne, idClassesConcerne,  
				idEleves, numPageEleves, taillePage);
		
		return "users/formSaisieDisciplineV1";
	}
	
	@GetMapping(path="/getUpdateRDiscipline")
	public String getUpdateRDiscipline(
			Model model, HttpServletRequest request,
			@RequestParam(name="idEleves[]", defaultValue="0") Long[] idEleves,
			@RequestParam(name="nbreheureJustifie[]", defaultValue="0") String[] nbreheureJustifie,
			@RequestParam(name="nbreheureNJustifie[]", defaultValue="0") String[] nbreheureNJustifie,
			/*@RequestParam(name="consigne[]", defaultValue="0") String[] consigne,
			@RequestParam(name="jourExclusion[]", defaultValue="0") String[] jourExclusion,
			@RequestParam(name="avertConduite[]", defaultValue="0") String[] avertConduite,
			@RequestParam(name="blameConduite[]", defaultValue="0") String[] blameConduite,*/
			@RequestParam(name="idSequenceConcerne", defaultValue="0") Long idSequenceConcerne,
			@RequestParam(name="idClassesConcerne", defaultValue="0") Long idClassesConcerne){
		
		//List<Eleves> listofAllEleve = usersService.findListElevesClasse(idClassesConcerne);
		
		int[] nbreHJustifie = new int[nbreheureJustifie.length];
		int[] nbreHNJustifie = new int[nbreheureNJustifie.length];
		/*int[] nbreHConsigne = new int[consigne.length];
		int[] nbrejourExclusion = new int[jourExclusion.length];
		boolean[] avert_conduite = new boolean[avertConduite.length];
		boolean[] blame_conduite = new boolean[blameConduite.length];*/
		
		/*//System.err.println("les tailles des tableaux==  "+nbreheureJustifie.length+
				" les tailles des tableaux==  "+idEleves.length);*/
		
		try{
			//Boucle de conversion afin de lancer tout lorsqu'on est sur ou d'en annuler tout
			for(int i=0; i<idEleves.length; i++){
				
				nbreHJustifie[i] = Integer.parseInt(nbreheureJustifie[i]);
				nbreHNJustifie[i] = Integer.parseInt(nbreheureNJustifie[i]);
				/*nbreHConsigne[i] = Integer.parseInt(consigne[i]);
				nbrejourExclusion[i] = Integer.parseInt(jourExclusion[i]);
				avert_conduite[i] = avertConduite[i].equals("oui");
				blame_conduite[i] = (blameConduite[i].equals("oui")?true:false);*/
				
				
			}
			//Boucle d'enregistrement puisque tout etant converti on peut tout enregistrer a la fois
			int ret = 0;
			for(int i=0; i<idEleves.length; i++){
				ret = usersService.saveRAbsenceSeqEleve(idEleves[i], idSequenceConcerne, nbreHJustifie[i], 
						nbreHNJustifie[i],	0,	0, false, false);
				
				/*//System.err.println("Enreg discipline de ==  "+idEleves[i]+
						" nbHANJ==  "+nbreHNJustifie[i]+" nbHAJ==  "+nbreHJustifie[i]);*/
			}
			
			if(ret == 0) return "redirect:/logesco/users/sg/getformSaisieRDiscipline?updateRDisciplineError"
				+ "&&idSequenceConcerne="+idSequenceConcerne
				+ "&&idClassesConcerne="+idClassesConcerne;
			
			
		}
		catch(Exception e){
			//System.err.println("Exception généré "+e.getMessage());
			e.printStackTrace();
			return "redirect:/logesco/users/sg/getformSaisieRDiscipline?updateRDisciplineErrorConvert"
					+ "&&idSequenceConcerne="+idSequenceConcerne
					+ "&&idClassesConcerne="+idClassesConcerne;
		}
		
		
		return "redirect:/logesco/users/sg/getformSaisieRDiscipline?updateRDisciplineSuccess"
				+ "&&idSequenceConcerne="+idSequenceConcerne
				+ "&&idClassesConcerne="+idClassesConcerne;
		
	}
	
	@GetMapping(path="/getUpdateRAbsences")
	public String getUpdateRAbsences(
			Model model, HttpServletRequest request,
			@RequestParam(name="idEleves", defaultValue="0") Long idEleves,
			@RequestParam(name="nbreheureJustifie", defaultValue="0") String nbreheureJustifie,
			@RequestParam(name="nbreheureNJustifie", defaultValue="0") String nbreheureNJustifie,
			@RequestParam(name="consigne", defaultValue="0") String consigne,
			@RequestParam(name="jourExclusion", defaultValue="0") String jourExclusion,
			@RequestParam(name="avertConduite", defaultValue="0") String avertConduite,
			@RequestParam(name="blameConduite", defaultValue="0") String blameConduite,
			@RequestParam(name="idSequenceConcerne", defaultValue="0") Long idSequenceConcerne,
			@RequestParam(name="numPageEleves", defaultValue="0") int numPageEleves){
		
		Eleves elv = usersService.findEleves(idEleves);
		
		Sequences sequenceConcerne = usersService.findSequences(idSequenceConcerne);
		
		model.addAttribute("sequenceConcerneRabs", sequenceConcerne);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("sequenceConcerneRabs", sequenceConcerne);
		
		//Sequences sequenceConcerne = usersService.findSequences(idSequenceConcerne);
		
		/*
		 * Il faut enregistrer les heures d'absences justifies et non justifies de l'eleve mais avant il faut se rassurer 
		 * que ce sont des nombres entier qui ont été saisi
		 */
		
		try{
			int nbreHJustifie = Integer.parseInt(nbreheureJustifie);
			int nbreNHJustifie = Integer.parseInt(nbreheureNJustifie);
			int nbreHconsigne = Integer.parseInt(consigne);
			int nbreJourExclusion = Integer.parseInt(jourExclusion);
			
			boolean avert_Conduite = (avertConduite.equals("oui")==true?true:false);
			boolean blame_Conduite = blameConduite.equals("oui");
			
			/*//System.err.println("avertConduiteeee= "+avertConduite+"  avert_Conduiteeee="+avert_Conduite);
			//System.err.println("blameConduiteeee= "+blameConduite+"  blame_Conduiteeee="+blame_Conduite);
			*/
			/*
			 * On peut donc enregistrer les heurs d'absences
			 */
			int ret = usersService.saveRAbsenceSeqEleve(idEleves, idSequenceConcerne, nbreHJustifie, 
					nbreNHJustifie,	nbreHconsigne,	nbreJourExclusion, avert_Conduite, blame_Conduite);
			
			if(ret == 0) return "redirect:/logesco/users/sg/getformSaisieRAbsences?updaterabssaisierrorNH"
					+ "&&idSequenceConcerne="+idSequenceConcerne
					+ "&&idClassesConcerne="+elv.getClasse().getIdClasses()
					+ "&&idEleves="+elv.getIdEleves()
					+ "&&numPageEleves="+numPageEleves;
			
			/*
			 * On regarde si on n'a pas atteint la fin de la liste des élèves
			 */
			
			int numEleve = usersService.getNumeroEleve(idEleves);
			
			//System.err.println("usersService.getNumeroEleve(idEleves) "+numEleve+"  "+usersService.getEffectifClasse(idEleves));
			
			if(numEleve == usersService.getEffectifClasse(idEleves)){
				/*
				 * Alors on doit chargé le même élève et précisé que la liste est terminé
				 */
				return "redirect:/logesco/users/sg/getformSaisieRAbsences?updaterabssaisiwarningElv"
						+ "&&idSequenceConcerne="+idSequenceConcerne
						+ "&&idClassesConcerne="+elv.getClasse().getIdClasses()
						+ "&&idEleves="+elv.getIdEleves()
						+ "&&numPageEleves="+numPageEleves;
			}
			else if(numEleve < usersService.getEffectifClasse(idEleves)){
				/*
				 * Alors on doit chargé l'élève qui suit. 
				 * Il faut donc la liste trié pour retirer l'élève qui suit celui qui est entreint d'etre traité reférencé par idEleves
				 */
				//System.err.println("Cherchons l'élève suivant ");
				int mode = 1;
				Eleves elvSvt = usersService.getElevesATraiter(idEleves, mode);
				
				/*if(elvSvt == null) //System.err.println("l'élève suivant n'est pas trouvable donc problème dans le code ou la requete");*/				
				//System.err.println("Cherchons l'élève suivant  de "+elv.getNomsEleves()+" id == "+elv.getIdEleves().longValue());
				//System.err.println("et c'est l'élève suivant  "+elvSvt.getNomsEleves()+" id == "+elvSvt.getIdEleves().longValue());
				
				/*
				 * On doit recalculer le numero de la page
				 */
				int taillePage = 5;
				int newnumPageEleves = usersService.getNumeroPageEleve(elvSvt.getIdEleves(),taillePage);
				
				//System.err.println("le numero de page du nouvel eleve est "+newnumPageEleves);
				
				//System.err.println("getformSaisieRAbsences "+idSequenceConcerne);
				
				return "redirect:/logesco/users/sg/getformSaisieRAbsences?updaterabsSucces"
						+ "&&idSequenceConcerne="+idSequenceConcerne
						+ "&&idClassesConcerne="+elv.getClasse().getIdClasses()
						+ "&&idEleves="+elvSvt.getIdEleves()
						+ "&&numPageEleves="+newnumPageEleves;
				
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			return "redirect:/logesco/users/sg/getformSaisieRAbsences?updaterabssaisiErrorNH"
					+ "&&idSequenceConcerne="+idSequenceConcerne
					+ "&&idClassesConcerne="+elv.getClasse().getIdClasses()
					+ "&&idEleves="+elv.getIdEleves()
					+ "&&numPageEleves="+numPageEleves;
		}
		
		return "redirect:/logesco/users/sg/getformSaisieRAbsences?updaterabssaisiError"
				+ "&&idSequenceConcerne="+idSequenceConcerne
				+ "&&idClassesConcerne="+elv.getClasse().getIdClasses()
				+ "&&idEleves="+elv.getIdEleves()
				+ "&&numPageEleves="+numPageEleves;
	}
	
	@GetMapping(path="/getPrecedentRabsSaisi")
	public String getPrecedentRabsSaisi(
			Model model, HttpServletRequest request,
			@RequestParam(name="idEleves", defaultValue="0") Long idEleves,
			@RequestParam(name="idSequenceConcerne", defaultValue="0") Long idSequenceConcerne,
			@RequestParam(name="numPageEleves", defaultValue="0") int numPageEleves){
		
		Eleves elv = usersService.findEleves(idEleves);
		
		Sequences sequenceConcerne = usersService.findSequences(idSequenceConcerne);
		
		model.addAttribute("sequenceConcerneRabs", sequenceConcerne);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("sequenceConcerneRabs", sequenceConcerne);
		
		
		/*if(elv == null)  //System.err.println("Eleve non trouve donc problème de passage de paramètre "
				+ "dans la requete initié par le clic sur le lien");*/
		
		int numEleve = usersService.getNumeroEleve(idEleves);
		
	/*	if(numEleve == 0) //System.err.println("L'élève n'existe pas dans la liste des élève de la classe donc il y a encore erreur lors du passage des "
				+ " paramètres dans la requete initié par le clic sur le lien");*/
		
		if(numEleve == 1){
			/*
			 * On a atteint le premier élève de la liste donc on ne peut pas aller plus bas
			 */
			return "redirect:/logesco/users/sg/getformSaisieRAbsences?updaterabswarningElvPre"
				+ "&&idSequenceConcerne="+idSequenceConcerne
				+ "&&idClassesConcerne="+elv.getClasse().getIdClasses()
				+ "&&idEleves="+elv.getIdEleves()
				+ "&&numPageEleves="+numPageEleves;
		}
		else if((numEleve > 1) && (numEleve <= usersService.getEffectifClasse(idEleves))){
			
			//System.err.println("Cherchons l'élève précédent ");
			int mode = 0;
			Eleves elvSvt = usersService.getElevesATraiter(idEleves, mode);
			
			/*if(elvSvt == null) //System.err.println("l'élève suivant n'est pas trouvable donc problème dans le code ou la requete");*/
			
			//System.err.println("Cherchons l'élève suivant  de "+elv.getNomsEleves()+" id == "+elv.getIdEleves().longValue());
			//System.err.println("et c'est l'élève suivant  "+elvSvt.getNomsEleves()+" id == "+elvSvt.getIdEleves().longValue());
			
			/*
			 * On doit recalculer le numero de la page
			 */
			int taillePage = 5;
			int newnumPageEleves = usersService.getNumeroPageEleve(elvSvt.getIdEleves(),taillePage);
			
			//System.err.println("le numero de page du nouvel eleve est "+newnumPageEleves);
			
			return "redirect:/logesco/users/sg/getformSaisieRAbsences?"
					+ "&&idSequenceConcerne="+idSequenceConcerne
					+ "&&idClassesConcerne="+elv.getClasse().getIdClasses()
					+ "&&idEleves="+elvSvt.getIdEleves()
					+ "&&numPageEleves="+newnumPageEleves;
			
		}
		
		return "redirect:/logesco/users/sg/getformSaisieRAbsences?updaterabsRien"
				+ "&&idSequenceConcerne="+idSequenceConcerne
				+ "&&idClassesConcerne="+elv.getClasse().getIdClasses()
				+ "&&idEleves="+elv.getIdEleves()
				+ "&&numPageEleves="+numPageEleves;
	}
	
	@GetMapping(path="/getRechercheEleveParNumeroRabs")
	public String getRechercheEleveParNumeroRabs(
			Model model, HttpServletRequest request,
			@RequestParam(name="numeroElv", defaultValue="0") String numeroElv,
			@RequestParam(name="idEleves", defaultValue="0") Long idEleves,
			@RequestParam(name="idSequenceConcerne", defaultValue="0") Long idSequenceConcerne,
			@RequestParam(name="numPageEleves", defaultValue="0") int numPageEleves){
		
		Eleves elv = usersService.findEleves(idEleves);
		
		Sequences sequenceConcerne = usersService.findSequences(idSequenceConcerne);
		
		model.addAttribute("sequenceConcerneRabs", sequenceConcerne);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("sequenceConcerneRabs", sequenceConcerne);
		
		try{
			int numeroEleve = Integer.parseInt(numeroElv);
			int effectifClasse = usersService.getEffectifClasse(idEleves);
			if((numeroEleve > 0) && (numeroEleve <= effectifClasse)){
				List<Eleves> listofAllEleve = usersService.findListElevesClasse(elv.getClasse().getIdClasses());
				Eleves eleveChercher = usersService.findEleveDansListe(listofAllEleve, numeroEleve);
				/*
				 * Il faut donc charger l'élève retrouver
				 */
				if(eleveChercher == null) return "redirect:/logesco/users/sg/getformSaisieRAbsences?rechEleveNull"
						+ "&&idSequenceConcerne="+idSequenceConcerne
						+ "&&idClassesConcerne="+elv.getClasse().getIdClasses()
						+ "&&idEleves="+elv.getIdEleves()
						+ "&&numPageEleves="+numPageEleves;
				
				/*
				 * Ici on est sur l'élève chercher n'est pas null
				 * on doit donc rechercher son numpage
				 */
				int taillePage = 5;
				int newnumPageEleves = usersService.getNumeroPageEleve(eleveChercher.getIdEleves(),taillePage);
				
				return "redirect:/logesco/users/sg/getformSaisieRAbsences?"
								+ "&&idSequenceConcerne="+idSequenceConcerne
								+ "&&idClassesConcerne="+eleveChercher.getClasse().getIdClasses()
								+ "&&idEleves="+eleveChercher.getIdEleves()
								+ "&&numPageEleves="+newnumPageEleves;
				
			}
		}
		catch(Exception e){
			return "redirect:/logesco/users/sg/getformSaisieRAbsences?numeroElvErrone"
					+ "&&idSequenceConcerne="+idSequenceConcerne
					+ "&&idClassesConcerne="+elv.getClasse().getIdClasses()
					+ "&&idEleves="+elv.getIdEleves()
					+ "&&numPageEleves="+numPageEleves;
		}
		
		return "redirect:/logesco/users/sg/getformSaisieRAbsences?"
				+ "&&idSequenceConcerne="+idSequenceConcerne
				+ "&&idClassesConcerne="+elv.getClasse().getIdClasses()
				+ "&&idEleves="+elv.getIdEleves()
				+ "&&numPageEleves="+numPageEleves;
	}
	
	
	/*******************************************
	 * Preparation des listes de toutes sortes à afficher
	 */
	@GetMapping(path="/getRabsSeqClasse")
	public String getRabsSeqClasse(
			Model model, HttpServletRequest request,
			@RequestParam(name="idClasseConcerne", defaultValue="0") long idClasseConcerne,
			@RequestParam(name="idSequenceConcerne", defaultValue="0") long idSequenceConcerne){
		
		Annee anneeScolaire = usersService.findAnneeActive();
		
		Etablissement etablissementConcerne = usersService.getEtablissement();
		
		Classes classeConcerne = usersService.findClasses(idClasseConcerne);
		//System.err.println("classeConcernelist "+classeConcerne.getCodeClasses());
		
		Sequences sequenceConcerne = usersService.findSequences(idSequenceConcerne);
		
		//liste des élèves classé par ordre alphabetique
		List<Eleves> listofAllEleveDeClasseConcerne = usersService.findListElevesClasse(classeConcerne.getIdClasses());
		
		HttpSession session=request.getSession();
		
		session.setAttribute("etablissementConcerne", etablissementConcerne);
		session.setAttribute("classeConcerneListofRAbsSeq", classeConcerne);
		session.setAttribute("sequenceConcerneListofRAbsSeq", sequenceConcerne);
		session.setAttribute("anneeScolaire", anneeScolaire);
		session.setAttribute("listofAllEleveDeClasseConcerne", listofAllEleveDeClasseConcerne);
		
		return "users/procesverbalRAbsSeq";
	}
	
	
////////////////////////////////////FIN DES REQUETES DE TYPE GET ////////////////////////////////////////////

}
