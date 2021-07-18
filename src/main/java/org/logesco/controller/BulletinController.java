/**
 * 
 */
package org.logesco.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.logesco.entities.Annee;
import org.logesco.entities.Classes;
import org.logesco.entities.Etablissement;
import org.logesco.entities.Niveaux;
import org.logesco.entities.Sequences;
import org.logesco.entities.Trimestres;
import org.logesco.entities.Utilisateurs;
import org.logesco.modeles.BulletinAnnuelBean;
import org.logesco.modeles.BulletinSequenceBean;
import org.logesco.modeles.BulletinTrimAnnuelBean;
import org.logesco.modeles.BulletinTrimestreBean;
import org.logesco.modeles.FicheConseilClasseBean;
import org.logesco.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;



/**
 * @author cedrickiadjeu
 *
 */
@Controller
@RequestMapping(path="/logesco/users/bulletins")
public class BulletinController {
	@Autowired
	private IUsersService usersService;
	@Autowired
	private ApplicationContext applicationContext;
	
////////////////////////////////////DEBUT DES REQUETES DE TYPE GET ////////////////////////////////////////////
	
	public void constructModelgetdonneesEditionsBulletinsSeq(Model model,	HttpServletRequest request){
		
		////System.err.println("dddddddddddddddddddddddddddddd");
		String profConnect = request.getUserPrincipal().getName();
		
		Utilisateurs usersProf = usersService.findByUsername(profConnect);
		
		model.addAttribute("profConnecte", usersProf);
		
		/*List<Niveaux> listofNiveauxDirigesEns = usersService.findAllNiveauxDirigesEns(usersProf.getIdUsers());
		model.addAttribute("listofNiveauxDirigesEns", listofNiveauxDirigesEns);*/
		
		String role1 = "PROVISEUR";
		String role2 = "CENSEUR";
		String role3 = "SUPERADMIN";
		int r1 = usersService.possedeRole(usersProf, role1);
		int r2 = usersService.possedeRole(usersProf, role2);
		int r3=usersService.possedeRole(usersProf, role3);
		
		if((r1==1)||(r2==1)||(r3==1)){
			/*
			 * Alors il s'agit d'un censeur ou d'un proviseur qui s'est connecté. 
			 * On charge donc la liste de tous les niveaux avec les classes pour 
			 * le permettre de tirer les bulletins de n'importe quel classe.
			 */
			List<Niveaux> listofNiveauxDirigesEns = usersService.findAllNiveaux();
			model.addAttribute("listofNiveauxDirigesEns", listofNiveauxDirigesEns);
			model.addAttribute("patron", "patron");
		}
		else{
			List<Niveaux> listofNiveauxDirigesEns = usersService.findAllNiveauxDirigesEns(usersProf.getIdUsers());
			model.addAttribute("listofNiveauxDirigesEns", listofNiveauxDirigesEns);
		}
		
		/*
		 * On doit placer l'année active dans le model
		 */
		Annee anneeActive = usersService.findAnneeActive();
		//System.err.println("anneeActive == "+anneeActive.getIntituleAnnee());
		if(anneeActive != null) {
			model.addAttribute("anneeActive", anneeActive);
			//List<Sequences> listofSequenceActive = usersService.findAllSequence(anneeActive.getIdPeriodes());
			List<Sequences> listofSequenceActive = usersService.findAllSequenceActive(anneeActive.getIdPeriodes());
			model.addAttribute("listofSequenceActive", listofSequenceActive);
		}
		
		
	}
	
	@GetMapping(path="/getdonneesEditionsBulletinsSeq")
	public String getdonneesEditionsBulletinsSeq(Model model, HttpServletRequest request){
		
		this.constructModelgetdonneesEditionsBulletinsSeq(model,	request);
		
		return "users/donneesEditionsBulletinsSeq";
	}
	
	
	@GetMapping(path="/lancerEditionsBulletinsSeq")
	public ModelAndView lancerEditionsBulletinsSeq(Model model, HttpServletRequest request,
			@RequestParam(name="idClasseConcerne", defaultValue="0") long idClasseConcerne,
			@RequestParam(name="idSequenceConcerne", defaultValue="0") long idSequenceConcerne,
			@RequestParam(name="idAnneeConcerne", defaultValue="0") long idAnneeConcerne){
		
		HttpSession session=request.getSession();
		
		Etablissement etablissementConcerne = usersService.getEtablissement();
		session.setAttribute("etablissementConcerne", etablissementConcerne);
		
		
		Classes classeConcerne = usersService.findClasses(idClasseConcerne);
		/*
		 * Cette donnee classe en session va aider pour la génération du rapport de conseil
		 */
		session.setAttribute("classeConcerneBulletinsSeq", classeConcerne);
		
		Sequences sequenceConcerne = usersService.findSequences(idSequenceConcerne);
		session.setAttribute("sequenceConcerneBulletinsSeq", sequenceConcerne);
		
		Annee anneeScolaire = usersService.findAnneeActive();
		session.setAttribute("anneeScolaireConcerneBulletinsSeq", anneeScolaire);
		
		if(etablissementConcerne == null || sequenceConcerne == null || anneeScolaire == null || 
				classeConcerne == null){
			return null;
		}
		
		
		//Il faut établir la liste des groupes qui passe dans la classe de façon à avoir tous les groupes avec les cours
		if(classeConcerne.getSection().getCodeSections().equals(new String("Général")) == true){
			/*Collection<BulletinSequence1Bean> listofBulletinSeqClasse = 
			 * 					usersService.generateCollectionofBulletinSequence1(classeConcerne.getIdClasses(), sequenceConcerne.getIdPeriodes());
			*/
			
			Map<String, Object> donnee = new HashMap<String, Object>();
			donnee = usersService.generateCollectionofBulletinSequence_opt(classeConcerne.getIdClasses(), 
					sequenceConcerne.getIdPeriodes());
			
			/*Collection<BulletinSequenceBean> listofBulletinSeqClasse = 
					usersService.generateCollectionofBulletinSequence_opt(classeConcerne.getIdClasses(), 
							sequenceConcerne.getIdPeriodes());*/
			
			@SuppressWarnings("unchecked")
			Collection<BulletinSequenceBean> listofBulletinSeqClasse = (Collection<BulletinSequenceBean>)
					donnee.get("collectionofBulletionSequence");
			
			FicheConseilClasseBean ficheCCS = (FicheConseilClasseBean)
					donnee.get("ficheconseilclassesequentiel");
			
			List<FicheConseilClasseBean> collectionficheCCS = new ArrayList<FicheConseilClasseBean>();
			collectionficheCCS.add(ficheCCS);
			
			session.setAttribute("getrapportseq", "oui");
			session.setAttribute("collectionficheCCS", collectionficheCCS);
			//System.err.println("fin affichage des bean bulletin et le Conseil classe "+collectionficheCCS.size());
			
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			parameters.put("SUBREPORT_DIR", "src/main/resources/reports/compiled/sequentiels/models1/");
			
			parameters.put("IMAGE_FOND", "src/main/resources/static/images/fondlogobekoko.png");
	        parameters.put("LOGO", "src/main/resources/static/images/logobekoko.png");
			
	        /*
			parameters.put("CHEMIN_JRXML_G1", "src/main/resources/reports/compiled/sequentiels/models2/MatiereGroupe1Sequence.jasper");
		    parameters.put("CHEMIN_JRXML_G2", "src/main/resources/reports/compiled/sequentiels/models2/MatiereGroupe2Sequence.jasper");
		    parameters.put("CHEMIN_JRXML_G3", "src/main/resources/reports/compiled/sequentiels/models2/MatiereGroupe3Sequence.jasper");
			*/
	        
	        parameters.put("datasource", listofBulletinSeqClasse);
			JasperReportsPdfView view = new JasperReportsPdfView();
			
			if(classeConcerne.getLangueClasses().equalsIgnoreCase("fr")==true){
			
				view.setUrl("classpath:/reports/compiled/sequentiels/models1/BulletinSequence.jasper");
				
			}
			else{
				/*//System.err.println("cette classe est une classe du sous système anglophone "
						+ "donc il faut le modèle anglais des bulletins séquentiels");*/
				
				view.setUrl("classpath:/reports/compiled/sequentiels/models1/BulletinSequence_en.jasper");
				
			}
			view.setApplicationContext(applicationContext);
			return new ModelAndView(view, parameters);
		}
		else{
			/*//System.err.println("Si on voit  ceci alors ce n'est pas une section general et par "
					+ "consequent aucun bulletin");*/
		}
		return null;
	}

	
	@SuppressWarnings("unchecked")
	@GetMapping(path="/lancerEditionsRapportConseilSeq")
	public ModelAndView lancerEditionsRapportConseilSeq(Model model, 
			HttpServletRequest request){
		
		HttpSession session=request.getSession();
		
		List<FicheConseilClasseBean> collectionficheCCS = new ArrayList<FicheConseilClasseBean>();
		collectionficheCCS = (List<FicheConseilClasseBean>)session.getAttribute("collectionficheCCS");
		
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("SUBREPORT_DIR", "src/main/resources/reports/compiled/fiches/");
		
        parameters.put("LOGO", "src/main/resources/static/images/logobekoko.png");
		
        parameters.put("datasource", collectionficheCCS);
		JasperReportsPdfView view = new JasperReportsPdfView();
		Classes classeConcerne = (Classes)session.getAttribute("classeConcerneBulletinsSeq");
		if(classeConcerne.getLangueClasses().equalsIgnoreCase("fr")==true){
			view.setUrl("classpath:/reports/compiled/fiches/FicheConseilClasseBean.jasper");
		}
		else{
			view.setUrl("classpath:/reports/compiled/fiches/FicheConseilClasseBean_en.jasper");
		}
		view.setApplicationContext(applicationContext);
		
		 
		
		return new ModelAndView(view, parameters);
	}
	
	
	public void constructModelgetdonneesEditionsBulletinsTrim(Model model,	HttpServletRequest request){
		
		////System.err.println("dddddddddddddddddddddddddddddd");
		String profConnect = request.getUserPrincipal().getName();
		
		Utilisateurs usersProf = usersService.findByUsername(profConnect);
		
		model.addAttribute("profConnecte", usersProf);
		
		/*List<Niveaux> listofNiveauxDirigesEns = usersService.findAllNiveauxDirigesEns(usersProf.getIdUsers());
		model.addAttribute("listofNiveauxDirigesEns", listofNiveauxDirigesEns);*/
		
		String role1 = "PROVISEUR";
		String role2 = "CENSEUR";
		int r1 = usersService.possedeRole(usersProf, role1);
		int r2 = usersService.possedeRole(usersProf, role2);
		
		if((r1==1)||(r2==1)){
			/*
			 * Alors il s'agit d'un censeur ou d'un proviseur qui s'est connecté. 
			 * On charge donc la liste de tous les niveaux avec les classes pour 
			 * le permettre de tirer les bulletins de n'importe quel classe.
			 */
			List<Niveaux> listofNiveauxDirigesEns = usersService.findAllNiveaux();
			model.addAttribute("listofNiveauxDirigesEns", listofNiveauxDirigesEns);
			model.addAttribute("patron", "patron");
		}
		else{
			List<Niveaux> listofNiveauxDirigesEns = usersService.findAllNiveauxDirigesEns(usersProf.getIdUsers());
			model.addAttribute("listofNiveauxDirigesEns", listofNiveauxDirigesEns);
		}
		
		/*
		 * On doit placer l'année active dans le model
		 */
		Annee anneeActive = usersService.findAnneeActive();
		//System.err.println("anneeActive == "+anneeActive.getIntituleAnnee());
		if(anneeActive != null) {
			model.addAttribute("anneeActive", anneeActive);
			
			List<Trimestres> listofTrimestreActive = usersService.findAllActiveTrimestre(anneeActive.getIdPeriodes());
			//System.err.println("liste------ "+listofTrimestreActive.size());
			model.addAttribute("listofTrimestreActive", listofTrimestreActive);
		}
		
		
	}
	
	@GetMapping(path="/getdonneesEditionsBulletinsTrim")
	public String getdonneesEditionsBulletinsTrim(Model model, HttpServletRequest request){
		
		this.constructModelgetdonneesEditionsBulletinsTrim(model,	request);
		
		return "users/donneesEditionsBulletinsTrim";
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping(path="/lancerEditionsRapportConseilTrim")
	public ModelAndView lancerEditionsRapportConseilTrim(Model model, 
			HttpServletRequest request){
		
		HttpSession session=request.getSession();
		
		List<FicheConseilClasseBean> collectionficheCCT = new ArrayList<FicheConseilClasseBean>();
		collectionficheCCT = (List<FicheConseilClasseBean>)session.getAttribute("collectionficheCCT");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("SUBREPORT_DIR", "src/main/resources/reports/compiled/fiches/");
		
        parameters.put("LOGO", "src/main/resources/static/images/logobekoko.png");
		
        parameters.put("datasource", collectionficheCCT);
		JasperReportsPdfView view = new JasperReportsPdfView();
		Classes classeConcerne = (Classes)session.getAttribute("classeConcerneBulletinsSeq");
		if(classeConcerne.getLangueClasses().equalsIgnoreCase("fr")==true){
		view.setUrl("classpath:/reports/compiled/fiches/FicheConseilClasseBean.jasper");
		}
		else{
			view.setUrl("classpath:/reports/compiled/fiches/FicheConseilClasseBean_en.jasper");
		}
		view.setApplicationContext(applicationContext);
		
		 
		
		return new ModelAndView(view, parameters);
		
	}
	
	
	
	@GetMapping(path="/lancerEditionsBulletinsTrim")
	public ModelAndView lancerEditionsBulletinsTrim(Model model, HttpServletRequest request,
			@RequestParam(name="idClasseConcerne", defaultValue="0") long idClasseConcerne,
			@RequestParam(name="idTrimestreConcerne", defaultValue="0") long idTrimestreConcerne,
			@RequestParam(name="idAnneeConcerne", defaultValue="0") long idAnneeConcerne){
		

		HttpSession session=request.getSession();
		
		Etablissement etablissementConcerne = usersService.getEtablissement();
		session.setAttribute("etablissementConcerne", etablissementConcerne);
		
		
		Classes classeConcerne = usersService.findClasses(idClasseConcerne);
		session.setAttribute("classeConcerneBulletinsTrim", classeConcerne);
		
		Trimestres trimestreConcerne = usersService.findTrimestres(idTrimestreConcerne);
		session.setAttribute("trimestreConcerneBulletinsSeq", trimestreConcerne);
		
		Annee anneeScolaire = usersService.findAnneeActive();
		session.setAttribute("anneeScolaireConcerneBulletinsSeq", anneeScolaire);
		
		if(etablissementConcerne == null || trimestreConcerne == null || anneeScolaire == null || 
				classeConcerne == null){
			return null;
		}

		/*******
		 * Debut des ajouts du 26-09-2018
		 */
		//Il faut établir la liste des groupes qui passe dans la classe de façon à avoir tous les groupes avec les cours
		if(classeConcerne.getSection().getCodeSections().equals(new String("Général")) == true){
			
			/*Collection<BulletinSequence1Bean> listofBulletinSeqClasse = 
			usersService.generateCollectionofBulletinSequence1(classeConcerne.getIdClasses(), sequenceConcerne.getIdPeriodes());
	*/
	
	/*Collection<BulletinTrimestreBean> listofBulletinTrimClasse = 
			usersService.generateCollectionofBulletinTrimestre_opt(classeConcerne.getIdClasses(), 
					trimestreConcerne.getIdPeriodes());*/
	
	Map<String, Object> donnee = new HashMap<String, Object>();
	donnee = usersService.generateCollectionofBulletinTrimestre_opt(classeConcerne.getIdClasses(), 
			trimestreConcerne.getIdPeriodes());
	

	@SuppressWarnings("unchecked")
	Collection<BulletinTrimestreBean> listofBulletinTrimClasse = (Collection<BulletinTrimestreBean>)
			donnee.get("collectionofBulletionTrimestre");
	
	FicheConseilClasseBean ficheCT = (FicheConseilClasseBean)
			donnee.get("ficheconseilclassetrimestriel");
	
	List<FicheConseilClasseBean> collectionficheCCT = new ArrayList<FicheConseilClasseBean>();
	collectionficheCCT.add(ficheCT);
	
	session.setAttribute("getrapporttrim", "oui");
	session.setAttribute("collectionficheCCT", collectionficheCCT);
	//System.err.println("fin affichage des bean bulletin et le Conseil classe "+collectionficheCCT.size());
	
	
	////System.err.println("fin affichage des bean bulletin "+listofBulletinSeqClasse.size());
	
	
	Map<String, Object> parameters = new HashMap<String, Object>();
	
	parameters.put("SUBREPORT_DIR", "src/main/resources/reports/compiled/trimestriels/models1/");
	
	parameters.put("IMAGE_FOND", "src/main/resources/static/images/fondlogobekoko.png");
    parameters.put("LOGO", "src/main/resources/static/images/logobekoko.png");
	
    parameters.put("datasource", listofBulletinTrimClasse);
	JasperReportsPdfView view = new JasperReportsPdfView();
			
			if(classeConcerne.getLangueClasses().equalsIgnoreCase("fr")==true){
				
				view.setUrl("classpath:/reports/compiled/trimestriels/models1/BulletinTrimestre.jasper");
				
			}
			else{
				//System.err.println("la on doit sortir les bulletins trimestriels en anglais car c'est une classe anglophone");
				
				
				view.setUrl("classpath:/reports/compiled/trimestriels/models1/BulletinTrimestre_en.jasper");
				
			}
			view.setApplicationContext(applicationContext);
			
			return new ModelAndView(view, parameters);
		}
		
		/********
		 * Fin des ajouts du 26-09-2018
		 */
		
		
		
		return null;
	}
	
	
	public void constructModelgetdonneesEditionsBulletinsAn(Model model,	
			HttpServletRequest request){
		
		////System.err.println("dddddddddddddddddddddddddddddd");
		String profConnect = request.getUserPrincipal().getName();
				
		Utilisateurs usersProf = usersService.findByUsername(profConnect);
				
		model.addAttribute("profConnecte", usersProf);
				
		/*List<Niveaux> listofNiveauxDirigesEns = usersService.findAllNiveauxDirigesEns(usersProf.getIdUsers());
		model.addAttribute("listofNiveauxDirigesEns", listofNiveauxDirigesEns);*/
		
		String role1 = "PROVISEUR";
		String role2 = "CENSEUR";
		int r1 = usersService.possedeRole(usersProf, role1);
		int r2 = usersService.possedeRole(usersProf, role2);
		
		if((r1==1)||(r2==1)){
			/*
			 * Alors il s'agit d'un censeur ou d'un proviseur qui s'est connecté. 
			 * On charge donc la liste de tous les niveaux avec les classes pour 
			 * le permettre de tirer les bulletins de n'importe quel classe.
			 */
			List<Niveaux> listofNiveauxDirigesEns = usersService.findAllNiveaux();
			model.addAttribute("listofNiveauxDirigesEns", listofNiveauxDirigesEns);
			model.addAttribute("patron", "patron");
		}
		else{
			List<Niveaux> listofNiveauxDirigesEns = usersService.findAllNiveauxDirigesEns(usersProf.getIdUsers());
			model.addAttribute("listofNiveauxDirigesEns", listofNiveauxDirigesEns);
		}
				
		/*
		 * On doit placer l'année active dans le model
		 */
		Annee anneeActive = usersService.findAnneeActive();
		//System.err.println("anneeActive == "+anneeActive.getIntituleAnnee());
		
		if(anneeActive != null) {
			model.addAttribute("anneeActive", anneeActive);
			
		}
		
	}
	
	@GetMapping(path="/getdonneesEditionsBulletinsAn")
	public String getdonneesEditionsBulletinsAn(Model model, HttpServletRequest request){
		
		this.constructModelgetdonneesEditionsBulletinsAn(model,	request);
		
		return "users/donneesEditionsBulletinsAn";
	}
	
	@GetMapping(path="/lancerEditionsBulletinsAn")
	public ModelAndView lancerEditionsBulletinsAn(Model model, HttpServletRequest request,
			@RequestParam(name="idClasseConcerne", defaultValue="0") long idClasseConcerne,
			@RequestParam(name="idAnneeConcerne", defaultValue="0") long idAnneeConcerne){
		
		HttpSession session=request.getSession();
		
		Etablissement etablissementConcerne = usersService.getEtablissement();
		session.setAttribute("etablissementConcerne", etablissementConcerne);
		
		
		Classes classeConcerne = usersService.findClasses(idClasseConcerne);
		session.setAttribute("classeConcerneBulletinsAn", classeConcerne);
		
		Annee anneeScolaire = usersService.findAnneeActive();
		session.setAttribute("anneeScolaireConcerneBulletinsSeq", anneeScolaire);
		
		if(etablissementConcerne == null ||  anneeScolaire == null || 
				classeConcerne == null){
			return null;
		}
		
		//Il faut établir la liste des groupes qui passe dans la classe de façon à avoir tous les groupes avec les cours
		if(classeConcerne.getSection().getCodeSections().equals(new String("Général")) == true){
			
			/*
			Collection<BulletinAnnuelBean> listofBulletinTrimClasse = 
					usersService.generateCollectionofBulletinAnnee(classeConcerne.getIdClasses(), anneeScolaire.getIdPeriodes());
			*/
			
			Map<String, Object> donnee = new HashMap<String, Object>();
			donnee = usersService.generateCollectionofBulletinAnnee(classeConcerne.getIdClasses(), 
					anneeScolaire.getIdPeriodes());
			

			@SuppressWarnings("unchecked")
			Collection<BulletinAnnuelBean> listofBulletinAnClasse = (Collection<BulletinAnnuelBean>)
					donnee.get("collectionofBulletionAnnuel");
			
			FicheConseilClasseBean ficheCA = (FicheConseilClasseBean)
					donnee.get("ficheconseilclasseannuel");
			
			List<FicheConseilClasseBean> collectionficheCCA = new ArrayList<FicheConseilClasseBean>();
			collectionficheCCA.add(ficheCA);
			
			session.setAttribute("getrapportan", "oui");
			session.setAttribute("collectionficheCCA", collectionficheCCA);
			//System.err.println("fin affichage des bean bulletin et le Conseil classe "+collectionficheCCA.size());
			
			

			Map<String, Object> parameters = new HashMap<String, Object>();
			
			parameters.put("SUBREPORT_DIR", "src/main/resources/reports/compiled/annuels/models1/");
			
			parameters.put("IMAGE_FOND", "src/main/resources/static/images/fondlogobekoko.png");
	        parameters.put("LOGO", "src/main/resources/static/images/logobekoko.png");
			
	        parameters.put("datasource", listofBulletinAnClasse);
			JasperReportsPdfView view = new JasperReportsPdfView();
			
			if(classeConcerne.getLangueClasses().equalsIgnoreCase("fr")==true){
				
				view.setUrl("classpath:/reports/compiled/annuels/models1/BulletinAnnuel.jasper");
				
			}
			else{
				//System.err.println("la langue de la classe c'est l'anglais donc on sort le modele anglophone");
				
				view.setUrl("classpath:/reports/compiled/annuels/models1/BulletinAnnuel_en.jasper");
				
			}
			view.setApplicationContext(applicationContext);
			
			 
			
			return new ModelAndView(view, parameters);
		}

		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path="/lancerEditionsRapportConseilAn")
	public ModelAndView lancerEditionsRapportConseilAn(Model model, 
			HttpServletRequest request){
		
		HttpSession session=request.getSession();
		
		List<FicheConseilClasseBean> collectionficheCCA = new ArrayList<FicheConseilClasseBean>();
		collectionficheCCA = (List<FicheConseilClasseBean>)session.getAttribute("collectionficheCCA");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
        parameters.put("LOGO", "src/main/resources/static/images/logobekoko.png");
        
        parameters.put("SUBREPORT_DIR", "src/main/resources/reports/compiled/fiches/");
		
        parameters.put("datasource", collectionficheCCA);
		JasperReportsPdfView view = new JasperReportsPdfView();
		Classes classeConcerne = (Classes)session.getAttribute("classeConcerneBulletinsSeq");
		if(classeConcerne.getLangueClasses().equalsIgnoreCase("fr")==true){
			view.setUrl("classpath:/reports/compiled/fiches/FicheConseilClasseBean.jasper");
		}
		else{
			view.setUrl("classpath:/reports/compiled/fiches/FicheConseilClasseBean_en.jasper");
		}
		view.setApplicationContext(applicationContext);
		
		 
		
		return new ModelAndView(view, parameters);
		
	}
	
	public void constructModelgetdonneesEditionsBulletinsTrimAn(Model model,	
			HttpServletRequest request){

		
		////System.err.println("dddddddddddddddddddddddddddddd");
		String profConnect = request.getUserPrincipal().getName();
				
		Utilisateurs usersProf = usersService.findByUsername(profConnect);
		
		model.addAttribute("profConnecte", usersProf);
		
		String role1 = "PROVISEUR";
		String role2 = "CENSEUR";
		int r1 = usersService.possedeRole(usersProf, role1);
		int r2 = usersService.possedeRole(usersProf, role2);
		
		if((r1==1)||(r2==1)){
			/*
			 * Alors il s'agit d'un censeur ou d'un proviseur qui s'est connecté. 
			 * On charge donc la liste de tous les niveaux avec les classes pour 
			 * le permettre de tirer les bulletins de n'importe quel classe.
			 */
			List<Niveaux> listofNiveauxDirigesEns = usersService.findAllNiveaux();
			model.addAttribute("listofNiveauxDirigesEns", listofNiveauxDirigesEns);
			model.addAttribute("patron", "patron");
		}
		else{
			List<Niveaux> listofNiveauxDirigesEns = usersService.findAllNiveauxDirigesEns(usersProf.getIdUsers());
			model.addAttribute("listofNiveauxDirigesEns", listofNiveauxDirigesEns);
		}	
		/*
		 * On doit placer l'année active dans le model
		 */
		Annee anneeActive = usersService.findAnneeActive();
		//System.err.println("anneeActive == "+anneeActive.getIntituleAnnee());
		
		if(anneeActive != null) {
			model.addAttribute("anneeActive", anneeActive);
			
			List<Trimestres> listofTrimestreActive = usersService.findAllActiveTrimestre(anneeActive.getIdPeriodes());
			//System.err.println("liste------ "+listofTrimestreActive.size());
			model.addAttribute("listofTrimestreActive", listofTrimestreActive);
		}
		
	
	}
	
	@GetMapping(path="/getdonneesEditionsBulletinsTrimAn")
	public String getdonneesEditionsBulletinsTrimAn(Model model, HttpServletRequest request){
		
		this.constructModelgetdonneesEditionsBulletinsTrimAn(model,	request);
		
		return "users/donneesEditionsBulletinsTrimAn";
	}
	
	@GetMapping(path="/lancerEditionsBulletinsTrimAn")
	public ModelAndView lancerEditionsBulletinsTrimAn(Model model, HttpServletRequest request,
			@RequestParam(name="idClasseConcerne", defaultValue="0") long idClasseConcerne,
			@RequestParam(name="idTrimestreConcerne", defaultValue="0") long idTrimestreConcerne,
			@RequestParam(name="idAnneeConcerne", defaultValue="0") long idAnneeConcerne){

		HttpSession session=request.getSession();
		
		Etablissement etablissementConcerne = usersService.getEtablissement();
		session.setAttribute("etablissementConcerne", etablissementConcerne);
		
		
		Classes classeConcerne = usersService.findClasses(idClasseConcerne);
		session.setAttribute("classeConcerneBulletinsTrimAn", classeConcerne);
		
		Trimestres trimestreConcerne = usersService.findTrimestres(idTrimestreConcerne);
		session.setAttribute("trimestreConcerneBulletinsTrimAn", trimestreConcerne);
		
		Annee anneeScolaire = usersService.findAnneeActive();
		session.setAttribute("anneeScolaireConcerneBulletinsTrimAn", anneeScolaire);


		if(etablissementConcerne == null || trimestreConcerne == null || anneeScolaire == null || 
				classeConcerne == null){
			return null;
		}
		
		/*******
		 * Debut des ajouts du 26-09-2018
		 */
		//Il faut établir la liste des groupes qui passe dans la classe de façon à avoir tous les groupes avec les cours
		if(classeConcerne.getSection().getCodeSections().equals(new String("Général")) == true){
			
			/*Collection<BulletinSequence1Bean> listofBulletinSeqClasse = 
			usersService.generateCollectionofBulletinSequence1(classeConcerne.getIdClasses(), sequenceConcerne.getIdPeriodes());
			 */
	
			/*Collection<BulletinTrimestreBean> listofBulletinTrimClasse = 
			usersService.generateCollectionofBulletinTrimestre_opt(classeConcerne.getIdClasses(), 
					trimestreConcerne.getIdPeriodes());*/
	
	Map<String, Object> donnee = new HashMap<String, Object>();
	donnee = usersService.generateCollectionofBulletinTrimAnnee(classeConcerne.getIdClasses(), 
			trimestreConcerne.getIdPeriodes());
	

	@SuppressWarnings("unchecked")
	Collection<BulletinTrimAnnuelBean> listofBulletinTrimAnnuelClasse = (Collection<BulletinTrimAnnuelBean>)
			donnee.get("collectionofBulletionTrimAnnuel");

	//System.err.println("la liste des bulletins contains keys"+donnee.containsKey("collectionofBulletionTrimAnnuel"));

	FicheConseilClasseBean ficheCT = (FicheConseilClasseBean)
			donnee.get("ficheconseilclassetriman");
	
	List<FicheConseilClasseBean> collectionficheCCTrim = new ArrayList<FicheConseilClasseBean>();
	collectionficheCCTrim.add(ficheCT);
	
	session.setAttribute("getrapporttriman", "oui");
	session.setAttribute("collectionficheCCT", collectionficheCCTrim);
	//System.err.println("fin affichage des bean bulletin et le Conseil classe "+collectionficheCCTrim.size());
	
	FicheConseilClasseBean ficheCA = (FicheConseilClasseBean)
			donnee.get("ficheconseilclasseantrim");

	List<FicheConseilClasseBean> collectionficheCCAn = new ArrayList<FicheConseilClasseBean>();
	collectionficheCCAn.add(ficheCA);
	
	session.setAttribute("getrapportantrim", "oui");
	session.setAttribute("collectionficheCCA", collectionficheCCAn);
	//System.err.println("fin affichage des bean bulletin et le Conseil classe "+collectionficheCCAn.size());
	
	

	Map<String, Object> parameters = new HashMap<String, Object>();
	
	parameters.put("SUBREPORT_DIR", "src/main/resources/reports/compiled/annuels/models2/");
	
	parameters.put("IMAGE_FOND", "src/main/resources/static/images/fondlogobekoko.png");
    parameters.put("LOGO", "src/main/resources/static/images/logobekoko.png");
	
    parameters.put("datasource", listofBulletinTrimAnnuelClasse);
	JasperReportsPdfView view = new JasperReportsPdfView();
			
			if(classeConcerne.getLangueClasses().equalsIgnoreCase("fr")==true){
				
				view.setUrl("classpath:/reports/compiled/annuels/models2/BulletinTrimAnnuel.jasper");
				
			}
			else{
				//System.err.println("On doit sortir les bulletins avec le modele anglophone car c'est une classe anglophone");
				
				
				view.setUrl("classpath:/reports/compiled/annuels/models2/BulletinTrimAnnuel_en.jasper");
			}
			
			view.setApplicationContext(applicationContext);
			
			 return new ModelAndView(view, parameters);
		
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path="/lancerEditionsRapportConseilTrimAn")
	public ModelAndView lancerEditionsRapportConseilTrimAn(Model model, 
			HttpServletRequest request){
		
		HttpSession session=request.getSession();
		
		List<FicheConseilClasseBean> collectionficheCCA = new ArrayList<FicheConseilClasseBean>();
		collectionficheCCA = (List<FicheConseilClasseBean>)session.getAttribute("collectionficheCCA");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("SUBREPORT_DIR", "src/main/resources/reports/compiled/fiches/");
		
        parameters.put("LOGO", "src/main/resources/static/images/logobekoko.png");
		
        parameters.put("datasource", collectionficheCCA);
		JasperReportsPdfView view = new JasperReportsPdfView();
		Classes classeConcerne = (Classes)session.getAttribute("classeConcerneBulletinsSeq");
		if(classeConcerne.getLangueClasses().equalsIgnoreCase("fr")==true){
			view.setUrl("classpath:/reports/compiled/fiches/FicheConseilClasseBean.jasper");
		}
		else{
			view.setUrl("classpath:/reports/compiled/fiches/FicheConseilClasseBean_en.jasper");
		}
		view.setApplicationContext(applicationContext);
		
		 
		
		return new ModelAndView(view, parameters);
		
	}
	
	
	
	
	
////////////////////////////////////FIN DES REQUETES DE TYPE GET ///////////////////////////////////////////////
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
////////////////////////////////////DEBUT DES REQUETES DE TYPE POST ///////////////////////////////////////////
	
////////////////////////////////////FIN DES REQUETES DE TYPE POST //////////////////////////////////////////////

}
