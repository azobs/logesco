/**
 * 
 */
package org.logesco.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.logesco.entities.*;
import org.logesco.form.*;
import org.logesco.modeles.PersonnelBean;
import org.logesco.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsXlsxView;

/**
 * @author cedrickiadjeu
 *
 */
@Controller
@RequestMapping(path="/logesco/users/chefetab")
public class ChefetabController {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private IAdminService adminService;

	@Autowired
	private IUsersService usersService;

	@Value("${dir.images.personnels}")
	private String photoPersonnelsDir;

	@Value("${dir.images.eleves}")
	private String photoElevesDir;

	@Value("${dir.emblemes.logo}")
	private String logoetabDir;

	@Value("${dir.documents.modeles}")
	private String modelesDir;

	@Autowired
	private Validator validator;


	////////////////////////////////////DEBUT DES REQUETES DE TYPE GET ////////////////////////////////////////////

	@GetMapping(path="/getupdateProviseur")
	public String getupdateProviseur(@ModelAttribute("updateProviseurForm") 
	UpdateProviseurForm updateProviseurForm,
	Model model, HttpServletRequest request) throws ParseException{

		/*
		 * Il faut recuperer le proviseur enregistrer dans la base de donnée 
		 * car on ne peut qu'en avoir un seul
		 */
		Proviseur proviseur=adminService.getProviseur();
		if(proviseur!=null){
			updateProviseurForm.setDatenaissPers(proviseur.getDatenaissPers());
			updateProviseurForm.setDiplomePers(proviseur.getDiplomePers());
			updateProviseurForm.setEmailPers(proviseur.getEmailPers());
			updateProviseurForm.setGradePers(proviseur.getGradePers());
			updateProviseurForm.setIdProviseur(proviseur.getIdUsers());
			updateProviseurForm.setLieunaissPers(proviseur.getLieunaissPers());
			updateProviseurForm.setNationalitePers(proviseur.getNationalitePers());
			updateProviseurForm.setNomsPers(proviseur.getNomsPers());
			updateProviseurForm.setNumcniPers(proviseur.getNumcniPers());
			updateProviseurForm.setNumtel1Pers(proviseur.getNumtel1Pers());
			updateProviseurForm.setNumtel2Pers(proviseur.getNumtel2Pers());
			updateProviseurForm.setPhotoPers(proviseur.getPhotoPers());
			updateProviseurForm.setPrenomsPers(proviseur.getPrenomsPers());
			updateProviseurForm.setQuartierPers(proviseur.getQuartierPers());
			updateProviseurForm.setSexePers(proviseur.getSexePers());
			updateProviseurForm.setSpecialiteProf(proviseur.getSpecialiteProf());
			updateProviseurForm.setStatutPers(proviseur.getStatutPers());
			updateProviseurForm.setUsername(proviseur.getUsername());
			updateProviseurForm.setVillePers(proviseur.getVillePers());
		}
		return "users/updateProviseur";
	}


	/***************************************************************
	 * Affichage de la page d'enregistrement des membres du personnel
	 ***************************************************************/
	@GetMapping(path="/getenregPersonnels")
	public String getenregPersonnels(@ModelAttribute("enregPersonnelsForm") 
	UpdatePersonnelsForm enregPersonnelsForm, 
	Model model, HttpServletRequest request){



		/*****
		 * Dans le model on a rien à mettre on attend juste le remplissage et la soumission du formulaire
		 * C'est pourquoi il n'ya pas de methode de construction du model pou cet enregistrement
		 */

		return "users/enregPersonnels";
	}

	public void constructModelEnregListPersonnels( Model model, HttpServletRequest request){
		/*
		 * On fait la liste des niveaux puisque les classes seront plutot chargé par niveaux
		 */
		List<Niveaux> listofNiveaux = usersService.findAllNiveaux();
		model.addAttribute("listofNiveaux", listofNiveaux);
	}
	
	@GetMapping(path="/getenregListPersonnels")
	public String getenregListPersonnels(Model model, 
	HttpServletRequest request){

		/*****
		 * On doit placer la liste des classes dans le modele 
		 * car elle sera utiliser pour construire les etiquetes
		 */
		this.constructModelEnregListPersonnels(model, request);


		return "users/enregListPersonnels";

	}


	public void constructModelConsulterUsers(Model model, int numPageCenseur, int numPageSg, 
			int numPageIntendant, int numPageEns, int taillePage){
		/*
		 * On doit faire la liste de tous les utilisateurs selon les roles et les placer dans le model
		 */

		//Pour le proviseur on est sur qu'on n'a qu'une seule page
		Page<Proviseur> pageofProviseur=usersService.findAllProviseur(0, 3);

		if(pageofProviseur != null){
			if(pageofProviseur.getContent().size()!=0){
				model.addAttribute("listofProviseur", pageofProviseur.getContent());
				int[] listofPagesProviseur= new int[pageofProviseur.getTotalPages()];

				model.addAttribute("listofPagesProviseur", listofPagesProviseur);

				model.addAttribute("pageCouranteProviseur", 0);

			}
		}


		Page<Censeurs> pageofCenseurs=usersService.findAllCenseurs(numPageCenseur, taillePage);
		if(pageofCenseurs != null){
			if(pageofCenseurs.getContent().size()!=0){
				model.addAttribute("listofCenseurs", pageofCenseurs.getContent());
				int[] listofPagesCenseurs=new int[pageofCenseurs.getTotalPages()];

				model.addAttribute("listofPagesCenseurs", listofPagesCenseurs);

				model.addAttribute("pageCouranteCenseurs", numPageCenseur);
				//System.err.println("numPageCenseurs  "+numPageCenseur);
			}
		}


		Page<SG> pageofSg=usersService.findAllSG(numPageSg, taillePage);
		if(pageofSg != null){
			if(pageofSg.getContent().size()!=0){
				model.addAttribute("listofSg", pageofSg.getContent());
				int[] listofPagesSg=new int[pageofSg.getTotalPages()];

				model.addAttribute("listofPagesSg", listofPagesSg);

				model.addAttribute("pageCouranteSG", numPageSg);
				//System.err.println("numPageSg  "+numPageSg);
			}
		}

		Page<Intendant> pageofIntendant=usersService.findAllIntendant(numPageIntendant, taillePage);
		if(pageofIntendant != null){
			if(pageofIntendant.getContent().size()!=0){
				model.addAttribute("listofIntendant", pageofIntendant.getContent());
				int[] listofPageIntendant=new int[pageofIntendant.getTotalPages()];

				model.addAttribute("listofPageIntendant", listofPageIntendant);

				model.addAttribute("pageCouranteIntendant", numPageIntendant);
				//System.err.println("numPageIntendant  "+numPageIntendant);
			}
		}
		Page<Enseignants> pageofEnseignants=usersService.findAllEnseignants(numPageEns, taillePage);
		if(pageofEnseignants != null){
			if(pageofEnseignants.getContent().size()!=0){
				model.addAttribute("listofEnseignants", pageofEnseignants.getContent());
				int[] listofPageEnseignants=new int[pageofEnseignants.getTotalPages()];

				model.addAttribute("listofPageEnseignants", listofPageEnseignants);

				model.addAttribute("pageCouranteEnseignant", numPageEns);
				//System.err.println("numPageEnseignants  "+numPageEns);
			}
		}
	}

	/***************************************************************
	 * Affichage de la page de consultation des membres du personnel
	 ***************************************************************/
	@GetMapping(path="/getconsulterPersonnels")
	public String getconsulterPersonnels(@ModelAttribute("consulterPersonnelsForm") 
	UpdatePersonnelsForm consulterPersonnelsForm, /*
	@RequestParam(name="username", defaultValue="-1") String username, 
	@RequestParam(name="numcniPers", defaultValue="-1") String numcniPers,*/
	@RequestParam(name="numPageCenseur", defaultValue="0") int numPageCenseur, 
	@RequestParam(name="numPageSg", defaultValue="0") int numPageSg, 
	@RequestParam(name="numPageIntendant", defaultValue="0") int numPageIntendant, 
	@RequestParam(name="numPageEns", defaultValue="0") int numPageEns, 
	@RequestParam(name="taillePage", defaultValue="5") int taillePage,
	Model model, HttpServletRequest request){

		/*
		 * On doit faire la recherche de tous les utilisateurs et mettre dans le modele puisque cette liste sera afficher 
		 * page par page dans la page de consultation des pages
		 */
		this.constructModelConsulterUsers(model, numPageCenseur, numPageSg, 
				numPageIntendant, numPageEns, taillePage);

		return "users/consulterPersonnels";

	}

	@GetMapping(path="/getimprimerPersonnels")
	public ModelAndView getimprimerPersonnels(
			@RequestParam(name="idUsers", defaultValue="0") long idUsers, 
			Model model, HttpServletRequest request){

		return null;
	}

	@GetMapping(path="/getsuppressionPersonnels")
	public String getsuppressionPersonnels(Long idUsers, 
			Model model, HttpServletRequest request){
		int repServeur=usersService.deleteUsers(idUsers);
		if(repServeur==0){
			return "redirect:/logesco/users/chefetab/getconsulterPersonnels?supprimUserserror";
		}
		return "redirect:/logesco/users/chefetab/getconsulterPersonnels?supprimUserssucces";
	}


	@GetMapping(path="/getupdatePersonnels")
	public String getupdatePersonnels(@ModelAttribute("updatePersonnelsForm") 
	UpdatePersonnelsForm updatePersonnelsForm,
	@RequestParam(name="numPageCenseur", defaultValue="0") int numPageCenseur, 
	@RequestParam(name="numPageSg", defaultValue="0") int numPageSg, 
	@RequestParam(name="numPageIntendant", defaultValue="0") int numPageIntendant, 
	@RequestParam(name="numPageEns", defaultValue="0") int numPageEns, 
	@RequestParam(name="taillePage", defaultValue="3") int taillePage,
	Long idUsers, Model model, HttpServletRequest request){

		/*
		 * Tous les paramètres d'affichage de consulter page doivent être placer dans la requete Post de mise 
		 * à jour car on s'en servira pour l'affichage de getConsulterPersonnel afin de laisser l'user sur les pages 
		 * qu'il regardait au moment où il a demandé la modification d'une page.
		 * 
		 * Il doivent être placé dans des champs cachés
		 */
		updatePersonnelsForm.setNumPageCenseur(numPageCenseur);
		updatePersonnelsForm.setNumPageSg(numPageSg);
		updatePersonnelsForm.setNumPageIntendant(numPageIntendant);
		updatePersonnelsForm.setNumPageEns(numPageEns);


		/*
		 * Il faut rechercher l'utilisateur dont le idUsers est passé en paramètre
		 * pour initialiser le formulaire de modification	
		 */
		Proffesseurs profAModif=usersService.findProffesseurs(idUsers);
		//System.err.println("Proffesseurs a modifier "+profAModif.getNomsPers());
		/*
		 * Il faut trouver les rôles associe à ce prof
		 */
		int roleCodeAssocie=usersService.getcodeUsersRole(profAModif);
		//System.err.println("Et son codeRole associe est  "+roleCodeAssocie);
		/*
		 * On met déjà à jour les boutons radio de choix des rôles avec les rôles retrouve
		 */
		updatePersonnelsForm.setRoleCode(roleCodeAssocie);
		updatePersonnelsForm.setRoleCodeAModif(roleCodeAssocie);
		/*
		 * En fonction du roleCodeAssocie du proffesseur on peut donc aller chercher son numero dans la fonction
		 * sauf dans le cas ou il est simple enseignant
		 */
		if(roleCodeAssocie==1){
			Censeurs censeurAssocie=usersService.findCenseurs(idUsers);
			//System.err.println("Censeurs a modifier "+censeurAssocie.getNomsPers());
			/*
			 * On met déjà les numeros de fonction qui correspond dans l'interface de modification
			 */
			updatePersonnelsForm.setNumeroPers(censeurAssocie.getNumeroCens());
		}

		if(roleCodeAssocie==3){
			SG sgAssocie=usersService.findSG(idUsers);
			//System.err.println("SG a modifier "+sgAssocie.getNomsPers());
			/*
			 * On met déjà les numeros de fonction qui correspond dans l'interface de modification
			 */
			updatePersonnelsForm.setNumeroPers(sgAssocie.getNumeroSG());
		}

		if(roleCodeAssocie==5){
			Intendant intAssocie=usersService.findIntendant(idUsers);
			//System.err.println("Intendant a modifier "+intAssocie.getNomsPers());
			/*
			 * On met déjà les numeros de fonction qui correspond dans l'interface de modification
			 */
			updatePersonnelsForm.setNumeroPers(intAssocie.getNumeroInt());
		}

		/*
		 * Maintenant on peut mettre l'interface de modification à jour avec le reste des données du prof à modifier
		 */
		updatePersonnelsForm.setDatenaissPers(profAModif.getDatenaissPers());
		updatePersonnelsForm.setDiplomePers(profAModif.getDiplomePers());
		updatePersonnelsForm.setEmailPers(profAModif.getEmailPers());
		updatePersonnelsForm.setGradePers(profAModif.getGradePers());
		updatePersonnelsForm.setIdPersonnels(profAModif.getIdUsers());
		updatePersonnelsForm.setLieunaissPers(profAModif.getLieunaissPers());
		updatePersonnelsForm.setNationalitePers(profAModif.getNationalitePers());
		updatePersonnelsForm.setNomsPers(profAModif.getNomsPers());
		updatePersonnelsForm.setNumcniOuUsernamePersAModif(profAModif.getNumcniPers());
		updatePersonnelsForm.setNumcniPers(profAModif.getNumcniPers());
		updatePersonnelsForm.setNumtel1Pers(profAModif.getNumtel1Pers());
		updatePersonnelsForm.setNumtel2Pers(profAModif.getNumtel2Pers());
		updatePersonnelsForm.setPassword(profAModif.getPassword());
		updatePersonnelsForm.setPrenomsPers(profAModif.getPrenomsPers());
		updatePersonnelsForm.setQuartierPers(profAModif.getQuartierPers());
		updatePersonnelsForm.setSexePers(profAModif.getSexePers());
		updatePersonnelsForm.setSpecialiteProf(profAModif.getSpecialiteProf());
		updatePersonnelsForm.setStatutPers(profAModif.getStatutPers());
		updatePersonnelsForm.setUsername(profAModif.getUsername());
		updatePersonnelsForm.setVillePers(profAModif.getVillePers());

		return "users/updatePersonnels";

	}


	public void constructModelEnregEleves( Model model, HttpServletRequest request, String idElevesString){
		/*
		 * Il faut faire la liste des classes et placer dans le model puisqu'on 
		 * va s'en servir pour les etiquetes des classes
		 */
		List<Classes> listofClasses=usersService.findListClasse();

		model.addAttribute("listofClasses", listofClasses);

		/*
		 * On fait la liste des niveaux puisque les classes seront plutot chargé par niveaux
		 */
		List<Niveaux> listofNiveaux = usersService.findAllNiveaux();
		model.addAttribute("listofNiveaux", listofNiveaux);

		if(!(idElevesString.equals(new String(" ")))){

			Long idEleves=Long.parseLong(idElevesString);
			/*
			 * Recherche de l'élève enregistré
			 */
			Eleves eleveEnreg=usersService.findEleves(idEleves);
			/*
			 * On place cet élève dans le model puisqu'il est utilisé pour l'effectif de la classe
			 * et pour imprimer le coupon d'inscription
			 */
			model.addAttribute("eleveEnreg", eleveEnreg);
			/*
			 * Il faut aussi placer la classe et l'éffectif de la classe aussi dans le model
			 */
			Classes classeConcerne=eleveEnreg.getClasse();
			model.addAttribute("classeConcerne", classeConcerne);
			int effectifprovClasse=usersService.getEffectifProvisoireClasse(classeConcerne.getIdClasses());
			model.addAttribute("effectifprovClasse", effectifprovClasse);
		}
	}

	/************
	 * On prend le idEleve en String puisqu'il y a de moment où il n'ya pas d'élève à mettre dans le model
	 * Mais pour travailler avec le idEleves en String alors on le convertit en Long
	 ************/
	@GetMapping(path="/getenregEleves")
	public String getenregEleves(@ModelAttribute("enregElevesForm") 
	UpdateElevesForm enregElevesForm,
	@RequestParam(name="idElevesString", defaultValue=" ") String idElevesString, 
	Model model, HttpServletRequest request){

		/*****
		 * On doit placer la liste des classes dans le modele 
		 * car elle sera utiliser pour construire les etiquetes
		 */
		this.constructModelEnregEleves(model, request, idElevesString);


		return "users/enregEleves";

	}

	public void constructModelEnregListEleves( Model model, HttpServletRequest request){
		/*
		 * On fait la liste des niveaux puisque les classes seront plutot chargé par niveaux
		 */
		List<Niveaux> listofNiveaux = usersService.findAllNiveaux();
		model.addAttribute("listofNiveaux", listofNiveaux);
	}

	@GetMapping(path="/getenregListEleves")
	public String getenregListEleves(@ModelAttribute("enregListElevesForm") 
	SaveListElevesForm enregListElevesForm, Model model, 
	HttpServletRequest request){

		/*****
		 * On doit placer la liste des classes dans le modele 
		 * car elle sera utiliser pour construire les etiquetes
		 */
		this.constructModelEnregListEleves(model, request);


		return "users/enregListEleves";

	}

	@GetMapping(path="/exportmodelRecrutement")
	public ModelAndView exportmodelRecrutement( Model model, 
			HttpServletRequest request){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("datasource", null);
		JasperReportsXlsxView view = new JasperReportsXlsxView();
		view.setUrl("classpath:/reports/compiled/fiches/modeleRecrutement.jasper");
		view.setApplicationContext(applicationContext);

		return new ModelAndView(view, parameters);
	}


	public void constructModelGestionEleves(Model model, HttpServletRequest request, 
			int numPageEleves, long idElevesAModif,	long idClasseSelect, 	
			UpdateElevesForm updateElevesForm){
		/*
		 * Il faut faire la liste des classes et placer dans le model puisqu'on 
		 * va s'en servir pour les etiquetes des classes
		 */
		List<Classes> listofClasses=usersService.findListClasse();

		model.addAttribute("listofClasses", listofClasses);

		/*
		 * On fait la liste des niveaux puisque les classes seront plutot chargé par niveaux
		 */
		List<Niveaux> listofNiveaux = usersService.findAllNiveaux();
		model.addAttribute("listofNiveaux", listofNiveaux);

		/*
		 * Il faut la liste de tous élèves dans le model. mais la clé qui sera associe sera la même 
		 * que lorsqu'on va plutot placer la liste des élèves d'une classe particulière passé dans une requete
		 */
		/*
		 * Si on n'a fait le choix d'une classe on ne doit envoyer que l'id de cette classe sinon on envoi null
		 * pour permettre à la fonction findPageElevesClasse de chercher donc la liste de tous les élèves enregistré
		 */
		Long idClasseAEnvoyer=null;
		if(idClasseSelect!=-1){
			idClasseAEnvoyer=new Long(idClasseSelect);
			/*
			 * On place idClasseSelect dans le modele puisqu'au niveau des liens de numerotation des pages il doit être
			 * utilisé pour savoir dans quel classe on se trouvait 
			 */
			model.addAttribute("idClasseSelect", idClasseSelect);

			/*
			 * On va rechercher la classe selectionné car on doit la placer aussi dans le model
			 */
			Classes classeSelect = usersService.findClasses(idClasseSelect);
			model.addAttribute("classeSelect", classeSelect);
		}

		/*
		 * On veut mettre aussi l'effectif total provisoire des élèves de la classe selectionné 
		 * ou des eleves deja enregistre dans l'etablissement
		 */
		int effectifprovClasse=usersService.getEffectifProvisoireClasse(idClasseSelect);
		model.addAttribute("effectifprovClasse", effectifprovClasse);

		int effectifprovFemininClasse = usersService.getEffectifProvisoireClasseParSexe(idClasseSelect, 1);
		model.addAttribute("effectifprovFemininClasse", effectifprovFemininClasse);

		int effectifprovMasculinClasse = usersService.getEffectifProvisoireClasseParSexe(idClasseSelect, 0);
		model.addAttribute("effectifprovMasculinClasse", effectifprovMasculinClasse);

		//On garde le numpageeleve dans la session puisqu'apres modification il va falloir revenir sur la page qui etait affiche
		HttpSession session=request.getSession();
		session.setAttribute("numPageEleves", numPageEleves);

		Page<Eleves> pageofEleves=usersService.findPageElevesClasse(idClasseAEnvoyer,	
				numPageEleves, 10);
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
		 * Il faut aussi placer dans le model l'élève choisi pour être modifier ainsi ces données seront utilisées
		 * pour initialiser le formulaire
		 */
		if(idElevesAModif != 0){
			//Ceci signifie qu'on a passé effectivement un identifian d'élève en parametre de la requete
			Eleves eleveAModif=usersService.findEleves(new Long(idElevesAModif));
			if(eleveAModif!=null){
				//on peut donc placer cet élève dans le model
				model.addAttribute("eleveAModif", eleveAModif);

				/*
				 * Grace aux données retrouvées on va donc renitialiser le formulaire
				 */
				updateElevesForm.setDatenaissEleves(eleveAModif.getDatenaissEleves());
				updateElevesForm.setEmailParent(eleveAModif.getEmailParent());
				updateElevesForm.setEtatInscEleves(eleveAModif.getEtatInscEleves());
				updateElevesForm.setIdClasse(eleveAModif.getClasse().getIdClasses());
				updateElevesForm.setIdClasseConcerne(eleveAModif.getClasse().getIdClasses());
				updateElevesForm.setIdEleves(eleveAModif.getIdEleves());
				updateElevesForm.setLieunaissEleves(eleveAModif.getLieunaissEleves());
				updateElevesForm.setMatriculeEleves(eleveAModif.getMatriculeEleves());
				updateElevesForm.setNationaliteEleves(eleveAModif.getNationaliteEleves());
				updateElevesForm.setNomsEleves(eleveAModif.getNomsEleves());
				updateElevesForm.setNumtel1Parent(eleveAModif.getNumtel1Parent());
				updateElevesForm.setPrenomsEleves(eleveAModif.getPrenomsEleves());
				updateElevesForm.setQuartierEleves(eleveAModif.getQuartierEleves());
				updateElevesForm.setRedoublant(eleveAModif.getRedoublant());
				updateElevesForm.setSexeEleves(eleveAModif.getSexeEleves());
				updateElevesForm.setStatutEleves(eleveAModif.getStatutEleves());
				updateElevesForm.setVilleEleves(eleveAModif.getVilleEleves());


			}
		}

	}


	@GetMapping(path="/getgestionEleves")
	public String getgestionEleves(@ModelAttribute("updateElevesForm") 
	UpdateElevesForm updateElevesForm, 
	@RequestParam(name="numPageEleves", defaultValue="0") int numPageEleves,
	@RequestParam(name="idElevesAModif", defaultValue="0") long idElevesAModif,
	@RequestParam(name="idClasseSelect", defaultValue="-1") long idClasseSelect,
	Model model, HttpServletRequest request){

		/****
		 * On doit placer dans le model au départ la liste des élèves complète page par page 
		 * car aucune classe n'est encore considéré mais par la suite il va falloir n'afficher que 
		 * ceux d'une salle de classe bien précisée
		 */



		this.constructModelGestionEleves(model,	request, numPageEleves,	 idElevesAModif,
				idClasseSelect,	updateElevesForm);

		return "users/gestionEleves";

	}


	@GetMapping(path="/getsupprimerEleves")
	public String getsupprimerEleves(Long idElevesASupprim, Long idClasseSelect){
		int repServeur=usersService.supprimerEleves(idElevesASupprim);
		if(repServeur==0) return "redirect:/logesco/users/chefetab/getgestionEleves?supprimEleveserror"
		+ "&&idClasseSelect="+idClasseSelect;

		return "redirect:/logesco/users/chefetab/getgestionEleves?supprimElevessucces"
		+ "&&idClasseSelect="+idClasseSelect;
	}

	@GetMapping(path="/exportlistMembrePersonnels")
	public ModelAndView exportlistMembrePersonnels(Model model, HttpServletRequest request){

		Etablissement etablissementConcerne = usersService.getEtablissement();

		Annee anneeScolaire = usersService.findAnneeActive();

		if(etablissementConcerne == null || anneeScolaire == null) return null;

		Collection<PersonnelBean> collectionofPersonnelBean = 
				usersService.generateCollectionofPersonnelBean();

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("delegation_fr", etablissementConcerne.getDeleguationdeptuteleEtab().toUpperCase());
		parameters.put("delegation_en", etablissementConcerne.getDeleguationdeptuteleanglaisEtab().toUpperCase());
		parameters.put("etablissement_fr", etablissementConcerne.getNomsEtab().toUpperCase());
		parameters.put("etablissement_en", etablissementConcerne.getNomsanglaisEtab().toUpperCase());
		String adresse = "BP "+etablissementConcerne.getBpEtab()+
				"  TEL: "+etablissementConcerne.getNumtel1Etab();
		parameters.put("adresse", adresse);
		parameters.put("annee_scolaire_fr", "Année Académique "+anneeScolaire.getIntituleAnnee());
		parameters.put("annee_scolaire_en", "Academic year "+anneeScolaire.getIntituleAnnee());
		parameters.put("ministere_fr", etablissementConcerne.getMinisteretuteleEtab());
		parameters.put("ministere_en", etablissementConcerne.getMinisteretuteleanglaisEtab());
		parameters.put("devise_fr", etablissementConcerne.getDeviseEtab());
		parameters.put("devise_en", etablissementConcerne.getDeviseanglaisEtab());
		String titre_liste = "LISTE DES MEMBRES DU PERSONNEL DE L'ETABLISSEMENT";
		parameters.put("titre_liste", titre_liste);
		parameters.put("ville", etablissementConcerne.getVilleEtab());

		File f=new File(logoetabDir+etablissementConcerne.getLogoEtab());
		////System.err.println("est ce que le fichier existe "+f.exists());

		if(f.exists()==true){
			parameters.put("logo", logoetabDir+etablissementConcerne.getLogoEtab());
		}
		else{
			parameters.put("logo", "src/main/resources/static/images/logobekoko.png");
		}

		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:/reports/compiled/fiches/ListePersonnel.jasper");
		view.setApplicationContext(applicationContext);

		parameters.put("datasource", collectionofPersonnelBean);

		return new ModelAndView(view, parameters);
	}


	////////////////////////////////////FIN DES REQUETES DE TYPE GET ////////////////////////////////////////////	

	////////////////////////////////////DEBUT DES REQUETES DE TYPE POST ////////////////////////////////////////////

	/**************************************************************
	 * Traitement du formulaire d'enregistrement/modification des données
	 * du chef d'établissement(Proviseur)
	 *************************************************************/
	@PostMapping(path="/postupdateProviseur")
	public String postupdateProviseur(@Valid @ModelAttribute("updateProviseurForm") 
	UpdateProviseurForm updateProviseurForm, 
	BindingResult bindingResult, Model model, 
	@RequestParam(name="picturephotoPers")MultipartFile filephotoPers, 
	HttpServletRequest request, HttpServletResponse response) 
			throws ParseException, Exception, IOException{



		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE");
			return "users/updateProviseur";
		}
		/*
		 * Tout est bien rempli dans le formulaire et on peut donc enregistrer l'etablissement
		 */


		Proviseur proviseur=new Proviseur();
		proviseur.setDatenaissPers(updateProviseurForm.getDatenaissPers());
		proviseur.setDiplomePers(updateProviseurForm.getDiplomePers());
		proviseur.setEmailPers(updateProviseurForm.getEmailPers());
		proviseur.setEnabled(true);
		proviseur.setGradePers(updateProviseurForm.getGradePers());
		proviseur.setLieunaissPers(updateProviseurForm.getLieunaissPers());
		proviseur.setNationalitePers(updateProviseurForm.getNationalitePers());
		proviseur.setNomsPers(updateProviseurForm.getNomsPers());
		proviseur.setNumcniPers(updateProviseurForm.getNumcniPers());
		proviseur.setNumtel1Pers(updateProviseurForm.getNumtel1Pers());
		proviseur.setNumtel2Pers(updateProviseurForm.getNumtel2Pers());
		proviseur.setPassword(updateProviseurForm.getPassword());
		proviseur.setPrenomsPers(updateProviseurForm.getPrenomsPers());
		proviseur.setQuartierPers(updateProviseurForm.getQuartierPers());
		proviseur.setSexePers(updateProviseurForm.getSexePers());
		proviseur.setSpecialiteProf(updateProviseurForm.getSpecialiteProf());
		proviseur.setStatutPers(updateProviseurForm.getStatutPers());
		proviseur.setUsername(updateProviseurForm.getUsername());
		proviseur.setVillePers(updateProviseurForm.getVillePers());


		if(!(filephotoPers.isEmpty())){
			proviseur.setPhotoPers(filephotoPers.getOriginalFilename());
		}



		Long idProviseur=adminService.saveProviseur(proviseur);

		if(idProviseur.longValue()==-1) 
			return "redirect:/logesco/users/chefetab/getupdateProviseur?updateproviseurerrorNumerocni";

		if(idProviseur.longValue()==-2) return "redirect:/logesco/users/chefetab/getupdateProviseur?"
		+ "updateproviseurerrorNomsPrenomsDatenaiss";

		if(idProviseur.longValue()==-3) 
			return "redirect:/logesco/users/chefetab/getupdateProviseur?updateproviseurerrorUsername";

		//System.err.println("TOUJOURS PAS DERREUR ON UPLOAD PHOTOPROVISEUR");
		if(!(filephotoPers.isEmpty())){
			filephotoPers.transferTo(new File(photoPersonnelsDir+idProviseur.longValue()));
		}


		return "redirect:/logesco/users/chefetab/getupdateProviseur?updateproviseursuccess";
	}

	@PostMapping(path="/postenregPersonnels")
	public String postenregPersonnels(@Valid @ModelAttribute("enregPersonnelsForm") 
	UpdatePersonnelsForm enregPersonnelsForm, 
	BindingResult bindingResult, Model model, 
	@RequestParam(name="picturephotoPers")MultipartFile filephotoPers, 
	HttpServletRequest request, HttpServletResponse response) 
			throws ParseException, Exception, IOException{



		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE enregPersonnels");
			return "users/enregPersonnels";
		}
		/*
		 * Tout est bien rempli dans le formulaire et on peut donc enregistrer l'etablissement
		 */


		Long idPersonnels=new Long(0);

		if((enregPersonnelsForm.getRoleCode()==1)||(enregPersonnelsForm.getRoleCode()==2)){
			//alors c'est un censeur qu'on enregistre ou qu'on met à jour
			Censeurs censeur=new Censeurs();
			censeur.setDatenaissPers(enregPersonnelsForm.getDatenaissPers());
			censeur.setDiplomePers(enregPersonnelsForm.getDiplomePers());
			censeur.setEmailPers(enregPersonnelsForm.getEmailPers());
			censeur.setEnabled(true);
			censeur.setGradePers(enregPersonnelsForm.getGradePers());
			censeur.setLieunaissPers(enregPersonnelsForm.getLieunaissPers());
			censeur.setNationalitePers(enregPersonnelsForm.getNationalitePers());
			censeur.setNomsPers(enregPersonnelsForm.getNomsPers());
			censeur.setNumcniPers(enregPersonnelsForm.getNumcniPers());
			/*
			 * On va appeler une fonction metier pour avoir le numero du censeur 
			 * car on considere qu'ils sont enregistré dans leur ordre
			 * 1 correspond au censeur
			 */
			censeur.setNumeroCens(usersService.chercherNumeroPers(1));
			censeur.setNumtel1Pers(enregPersonnelsForm.getNumtel1Pers());
			censeur.setNumtel2Pers(enregPersonnelsForm.getNumtel2Pers());
			censeur.setPassword(enregPersonnelsForm.getPassword());
			censeur.setPrenomsPers(enregPersonnelsForm.getPrenomsPers());
			censeur.setQuartierPers(enregPersonnelsForm.getQuartierPers());
			censeur.setSexePers(enregPersonnelsForm.getSexePers());
			censeur.setSpecialiteProf(enregPersonnelsForm.getSpecialiteProf());
			censeur.setStatutPers(enregPersonnelsForm.getStatutPers());
			censeur.setUsername(enregPersonnelsForm.getUsername());
			censeur.setVillePers(enregPersonnelsForm.getVillePers());


			if(!(filephotoPers.isEmpty())){
				censeur.setPhotoPers(filephotoPers.getOriginalFilename());
			}

			//System.err.println("TOUJOURS PAS DERREUR ON SAVE CENSEUR");

			int roleCode=enregPersonnelsForm.getRoleCode();
			idPersonnels=usersService.saveCenseurs(censeur, roleCode);

		}
		else if((enregPersonnelsForm.getRoleCode()==3)||(enregPersonnelsForm.getRoleCode()==4)){
			//alors c'est un sg qu'on enregistre ou qu'on met à jour
			SG sg=new SG();
			sg.setDatenaissPers(enregPersonnelsForm.getDatenaissPers());
			sg.setDiplomePers(enregPersonnelsForm.getDiplomePers());
			sg.setEmailPers(enregPersonnelsForm.getEmailPers());
			sg.setEnabled(true);
			sg.setGradePers(enregPersonnelsForm.getGradePers());
			sg.setLieunaissPers(enregPersonnelsForm.getLieunaissPers());
			sg.setNationalitePers(enregPersonnelsForm.getNationalitePers());
			sg.setNomsPers(enregPersonnelsForm.getNomsPers());
			sg.setNumcniPers(enregPersonnelsForm.getNumcniPers());
			/*
			 * On va appeler une fonction metier pour avoir le numero du SG 
			 * car on considere qu'ils sont enregistré dans leur ordre
			 * 3 correspond au SG
			 */
			sg.setNumeroSG(usersService.chercherNumeroPers(3));
			sg.setNumtel1Pers(enregPersonnelsForm.getNumtel1Pers());
			sg.setNumtel2Pers(enregPersonnelsForm.getNumtel2Pers());
			sg.setPassword(enregPersonnelsForm.getPassword());
			sg.setPrenomsPers(enregPersonnelsForm.getPrenomsPers());
			sg.setQuartierPers(enregPersonnelsForm.getQuartierPers());
			sg.setSexePers(enregPersonnelsForm.getSexePers());
			sg.setSpecialiteProf(enregPersonnelsForm.getSpecialiteProf());
			sg.setStatutPers(enregPersonnelsForm.getStatutPers());
			sg.setUsername(enregPersonnelsForm.getUsername());
			sg.setVillePers(enregPersonnelsForm.getVillePers());

			//System.err.println("TOUJOURS PAS DERREUR ON SETPHOTOPERS CENSEUR");
			if(!(filephotoPers.isEmpty())){
				sg.setPhotoPers(filephotoPers.getOriginalFilename());
			}

			//System.err.println("TOUJOURS PAS DERREUR ON SAVE CENSEUR");

			int roleCode=enregPersonnelsForm.getRoleCode();
			idPersonnels=usersService.saveSG(sg, roleCode);
		}
		else if((enregPersonnelsForm.getRoleCode()==5)||(enregPersonnelsForm.getRoleCode()==6)){
			//alors c'est un intendant qu'on enregistre ou qu'on met à jour
			Intendant intendant=new Intendant();
			intendant.setDatenaissPers(enregPersonnelsForm.getDatenaissPers());
			intendant.setDiplomePers(enregPersonnelsForm.getDiplomePers());
			intendant.setEmailPers(enregPersonnelsForm.getEmailPers());
			intendant.setEnabled(true);
			intendant.setGradePers(enregPersonnelsForm.getGradePers());
			intendant.setLieunaissPers(enregPersonnelsForm.getLieunaissPers());
			intendant.setNationalitePers(enregPersonnelsForm.getNationalitePers());
			intendant.setNomsPers(enregPersonnelsForm.getNomsPers());
			intendant.setNumcniPers(enregPersonnelsForm.getNumcniPers());
			/*
			 * On va appeler une fonction metier pour avoir le numero du Intendant 
			 * car on considere qu'ils sont enregistré dans leur ordre
			 * 5 correspond au Intendant
			 */
			intendant.setNumeroInt(usersService.chercherNumeroPers(5));
			intendant.setNumtel1Pers(enregPersonnelsForm.getNumtel1Pers());
			intendant.setNumtel2Pers(enregPersonnelsForm.getNumtel2Pers());
			intendant.setPassword(enregPersonnelsForm.getPassword());
			intendant.setPrenomsPers(enregPersonnelsForm.getPrenomsPers());
			intendant.setQuartierPers(enregPersonnelsForm.getQuartierPers());
			intendant.setSexePers(enregPersonnelsForm.getSexePers());
			intendant.setSpecialiteProf(enregPersonnelsForm.getSpecialiteProf());
			intendant.setStatutPers(enregPersonnelsForm.getStatutPers());
			intendant.setUsername(enregPersonnelsForm.getUsername());
			intendant.setVillePers(enregPersonnelsForm.getVillePers());

			//System.err.println("TOUJOURS PAS DERREUR ON SETPHOTOPERS CENSEUR");
			if(!(filephotoPers.isEmpty())){
				intendant.setPhotoPers(filephotoPers.getOriginalFilename());
			}

			//System.err.println("TOUJOURS PAS DERREUR ON SAVE CENSEUR");

			int roleCode=enregPersonnelsForm.getRoleCode();
			idPersonnels=usersService.saveIntendant(intendant, roleCode);
		}
		else if((enregPersonnelsForm.getRoleCode()==7)){
			//alors c'est un enseignant qu'on enregistre ou qu'on met à jour
			Enseignants enseignant=new Enseignants();
			enseignant.setDatenaissPers(enregPersonnelsForm.getDatenaissPers());
			enseignant.setDiplomePers(enregPersonnelsForm.getDiplomePers());
			enseignant.setEmailPers(enregPersonnelsForm.getEmailPers());
			enseignant.setEnabled(true);
			enseignant.setGradePers(enregPersonnelsForm.getGradePers());
			enseignant.setLieunaissPers(enregPersonnelsForm.getLieunaissPers());
			enseignant.setNationalitePers(enregPersonnelsForm.getNationalitePers());
			enseignant.setNomsPers(enregPersonnelsForm.getNomsPers());
			enseignant.setNumcniPers(enregPersonnelsForm.getNumcniPers());
			enseignant.setNumtel1Pers(enregPersonnelsForm.getNumtel1Pers());
			enseignant.setNumtel2Pers(enregPersonnelsForm.getNumtel2Pers());
			enseignant.setPassword(enregPersonnelsForm.getPassword());
			enseignant.setPrenomsPers(enregPersonnelsForm.getPrenomsPers());
			enseignant.setQuartierPers(enregPersonnelsForm.getQuartierPers());
			enseignant.setSexePers(enregPersonnelsForm.getSexePers());
			enseignant.setSpecialiteProf(enregPersonnelsForm.getSpecialiteProf());
			enseignant.setStatutPers(enregPersonnelsForm.getStatutPers());
			enseignant.setUsername(enregPersonnelsForm.getUsername());
			enseignant.setVillePers(enregPersonnelsForm.getVillePers());

			//System.err.println("TOUJOURS PAS DERREUR ON SETPHOTOPERS CENSEUR");
			if(!(filephotoPers.isEmpty())){
				enseignant.setPhotoPers(filephotoPers.getOriginalFilename());
			}

			//System.err.println("TOUJOURS PAS DERREUR ON SAVE CENSEUR");

			idPersonnels=usersService.saveEnseignants(enseignant);
		}
		else{
			return "redirect:/logesco/users/chefetab/getenregPersonnels?enregpersonnelserrorChoixRoles";
		}

		if(idPersonnels.longValue()==-1) 
			return "redirect:/logesco/users/chefetab/getenregPersonnels?enregpersonnelserrorNumeroCni";
		if(idPersonnels.longValue()==-2) 
			return "redirect:/logesco/users/chefetab/getenregPersonnels?enregpersonnelserrorNomsPrenomsDatenaiss";
		if(idPersonnels.longValue()==-3) 
			return "redirect:/logesco/users/chefetab/getenregPersonnels?enregpersonnelserrorUsername";
		if(idPersonnels.longValue()==-4) 
			return "redirect:/logesco/users/chefetab/getenregPersonnels?enregpersonnelserrorNumeroPers";
		if(idPersonnels.longValue()==-5) 
			return "redirect:/logesco/users/chefetab/getenregPersonnels?enregpersonnelserrorRole";
		if(idPersonnels.longValue()==-6) 
			return "redirect:/logesco/users/chefetab/getenregPersonnels?enregpersonnelserrorRoleEns";

		/*
		 * Ici on peut upload l'image sans problème puisqu'aucune erreur n'est constaté pendant le 
		 * processus d'enregistrement
		 */
		//System.err.println("TOUJOURS PAS DERREUR ON UPLOAD PHOTO PERSONNEL");
		if(!(filephotoPers.isEmpty())){
			filephotoPers.transferTo(new File(photoPersonnelsDir+idPersonnels.longValue()));
		}

		return "redirect:/logesco/users/chefetab/getconsulterPersonnels?enregpersonnelssuccess";
	}

	@SuppressWarnings({ "rawtypes", "resource", "unused" })
	@PostMapping(path="/postenregListPersonnels")
	public String postenregListPersonnels(Model model, 
	@RequestParam(name="filecheminFichier")MultipartFile filecheminFichier, 
	HttpServletRequest request, HttpServletResponse response) 
			throws ParseException, Exception, IOException{
		if(!(filecheminFichier.isEmpty())){
			String nomfich = modelesDir+filecheminFichier.getOriginalFilename();
			//Avant toutes chose, on upload le fichier sur le serveur
			filecheminFichier.transferTo(new File(nomfich));
			
			/*
			 * Lecture et fouille du fichier excel en vue des validations
			 */
			//On transforme le fichier uploader en fichier traitable dans java
			FileInputStream input = new FileInputStream(nomfich);
			//Maintenant on precise que c'est un fichier Excel
			XSSFWorkbook wb = new XSSFWorkbook(input);
			//Maintenant on recupere la première feuille du fichier excel
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator rows = sheet.rowIterator();
			int i = 1;
			
			List<Censeurs> listofCenseursAEnreg = new ArrayList<Censeurs>();
			List<SG> listofSGAEnreg = new ArrayList<SG>();
			List<Enseignants> listofEnseignantsAEnreg = new ArrayList<Enseignants>();
			List<String> listofErreur = new ArrayList<String>();
			
			SimpleDateFormat spd1 = new SimpleDateFormat("yyyy-MM-dd");//"dd-MM-yyyy"
			
			while(rows.hasNext()){
				List<String> listofData = new ArrayList<String>();
				////System.err.println("ligne numero "+i);
				XSSFRow row = (XSSFRow)rows.next();
				Iterator cells = row.cellIterator();
				int j = 1;
				//***Initialiser les champs de l'élève à partir des données lues dans le fichier
				if(i>=3){
					while(cells.hasNext()){
						XSSFCell cell = (XSSFCell)cells.next();
						if(j==1) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Noms");
						}
						
						if(j==2) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Prenoms");
						}
						
						if(j==3) {
							Date datenaiss = cell.getDateCellValue();
							String datenaissString = spd1.format(datenaiss);
							listofData.add(datenaissString);
							////System.err.println("Date de naissance");
						}
						
						if(j==4) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Lieu de naissance");
						}
						
						if(j==5) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							////System.err.println("Sexe");
							String sexe = "masculin";
							if(cell.getStringCellValue().equalsIgnoreCase("F")==true
									||cell.getStringCellValue().equalsIgnoreCase("feminin")==true
									||cell.getStringCellValue().equalsIgnoreCase("féminin")==true){
								sexe = "féminin";
							}
							listofData.add(sexe);
						}
						
						if(j==6) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Num cni");
						}
						
						if(j==7) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Nationalite");
						}
						
						if(j==8) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Statut");
						}
						
						if(j==9) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Grade");
						}
						
						if(j==10) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Diplome");
						}
						
						if(j==11) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Specialite");
						}
						
						if(j==12) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Fonction");
						}
						
						if(j==13) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Tel1");
						}
						
						if(j==14) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Tel2");
						}
						
						if(j==15) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Ville");
						}
						
						if(j==16) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Quatier");
						}
						
						if(j==17) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Email");
						}
						
						if(j==18) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Email");
						}
						
						/*
						 * On affiche donc les données du personnel si on les a tous lues
						 * Et on est sur de les avoir tous lues lorsqu'on a atteint la 18 ieme cellule
						 * donc j == 18
						 */
						if(j == 18){
							/*
							 * Ici dans listofData on a les éléments de construction d'une entite personnel
							 * quel que soit le rôle
							 * On peut donc construire, valider et ajouter dans la liste des personnels 
							 * qui correspond au rang lue dans la 12 ieme cellule a 
							 * enregistrer si l'entité est validée sinon on enregistre l'erreur
							 */
							if(listofData.size() == 18){
								//On est sur qu'on a un personnel et sa fonction est à l'entrée d'indice 11
								String fonction_pers = listofData.get(11);
								if(fonction_pers.equalsIgnoreCase("CENSEUR")==true){
									//Le personnel en question est un censeur
									Censeurs censeurAValider = new Censeurs();
									censeurAValider.setNomsPers(listofData.get(0));
									censeurAValider.setPrenomsPers(listofData.get(1));
									censeurAValider.setDatenaissPers(spd1.parse(listofData.get(2)));
									censeurAValider.setLieunaissPers(listofData.get(3));
									censeurAValider.setSexePers(listofData.get(4));
									censeurAValider.setNumcniPers(listofData.get(5));
									censeurAValider.setNationalitePers(listofData.get(6));
									censeurAValider.setStatutPers(listofData.get(7));
									censeurAValider.setGradePers(listofData.get(8));
									censeurAValider.setDiplomePers(listofData.get(9));
									censeurAValider.setSpecialiteProf(listofData.get(10));
									censeurAValider.setNumtel1Pers(listofData.get(12));
									//listofData.get(11) correspond a la fonction
									censeurAValider.setNumtel2Pers(listofData.get(13));
									censeurAValider.setVillePers(listofData.get(14));
									censeurAValider.setQuartierPers(listofData.get(15));
									censeurAValider.setEmailPers(listofData.get(16));
									censeurAValider.setUsername(listofData.get(17));
									censeurAValider.setPassword(listofData.get(17));
									int roleCode = 2;
									censeurAValider.setNumeroCens(usersService.chercherNumeroPers(roleCode));
									censeurAValider.setEnabled(true);
									
									//System.out.println("censeurAValider  "+i+"  "+censeurAValider.toString());
									/*
									 * Après construction il faut donc valider avant de placer dans la liste des censeurs 
									 * a enregistrer
									 */
									DataBinder binder = new DataBinder(censeurAValider);
									BindingResult results = binder.getBindingResult();
									validator.validate(censeurAValider, results);
									//System.out.println("censeur valide?----------------- "+results.toString()+" -------------------rrr");
									
									if (results.hasErrors()) {
										List<FieldError> listofError = results.getFieldErrors();
										String error = "ligne "+i+": ";
										for(FieldError fe : listofError){
											error += fe.getField()+"|";
										}
										listofErreur.add(error);
										model.addAttribute("erreurDansFichier", error);
										this.constructModelEnregListPersonnels(model, request);
										return "users/enregListPersonnels";
									}
									else{
										//Pas d'erreur donc entite censeur valide avec succes
										//System.err.println("censeur qu'on ajoute: "+censeurAValider.getNomsPers());
										listofCenseursAEnreg.add(censeurAValider);
									}
									
								}
								
								if(fonction_pers.equalsIgnoreCase("SG")==true){
									//Le personnel en question est un sg
									SG sgAValider = new SG();
									sgAValider.setNomsPers(listofData.get(0));
									sgAValider.setPrenomsPers(listofData.get(1));
									sgAValider.setDatenaissPers(spd1.parse(listofData.get(2)));
									sgAValider.setLieunaissPers(listofData.get(3));
									sgAValider.setSexePers(listofData.get(4));
									sgAValider.setNumcniPers(listofData.get(5));
									sgAValider.setNationalitePers(listofData.get(6));
									sgAValider.setStatutPers(listofData.get(7));
									sgAValider.setGradePers(listofData.get(8));
									sgAValider.setDiplomePers(listofData.get(9));
									sgAValider.setSpecialiteProf(listofData.get(10));
									sgAValider.setNumtel1Pers(listofData.get(12));
									//listofData.get(11) correspond a la fonction
									sgAValider.setNumtel2Pers(listofData.get(13));
									sgAValider.setVillePers(listofData.get(14));
									sgAValider.setQuartierPers(listofData.get(15));
									sgAValider.setEmailPers(listofData.get(16));
									sgAValider.setUsername(listofData.get(17));
									sgAValider.setPassword(listofData.get(17));
									sgAValider.setEnabled(true);
									int roleCode = 4;
									sgAValider.setNumeroSG(usersService.chercherNumeroPers(roleCode));
									
									//System.out.println("sgAValider  "+i+"  "+sgAValider.toString());
									/*
									 * Après construction il faut donc valider avant de placer dans la liste des sg 
									 * a enregistrer
									 */
									DataBinder binder = new DataBinder(sgAValider);
									BindingResult results = binder.getBindingResult();
									validator.validate(sgAValider, results);
									//System.out.println("sg valide?----------------- "+results.toString()+" -------------------rrr");
									
									if (results.hasErrors()) {
										List<FieldError> listofError = results.getFieldErrors();
										String error = "ligne "+i+": ";
										for(FieldError fe : listofError){
											error += fe.getField()+"|";
										}
										listofErreur.add(error);
										model.addAttribute("erreurDansFichier", error);
										this.constructModelEnregListPersonnels(model, request);
										return "users/enregListPersonnels";
									}
									else{
										//Pas d'erreur donc entite censeur valide avec succes
										//System.err.println("sg qu'on ajoute: "+sgAValider.getNomsPers());
										listofSGAEnreg.add(sgAValider);
									}
								}
								
								if(fonction_pers.equalsIgnoreCase("ENSEIGNANT")==true){
									//Le personnel en question est un simple enseignant
									Enseignants enseignantAValider = new Enseignants();
									enseignantAValider.setNomsPers(listofData.get(0));
									enseignantAValider.setPrenomsPers(listofData.get(1));
									enseignantAValider.setDatenaissPers(spd1.parse(listofData.get(2)));
									enseignantAValider.setLieunaissPers(listofData.get(3));
									enseignantAValider.setSexePers(listofData.get(4));
									enseignantAValider.setNumcniPers(listofData.get(5));
									enseignantAValider.setNationalitePers(listofData.get(6));
									enseignantAValider.setStatutPers(listofData.get(7));
									enseignantAValider.setGradePers(listofData.get(8));
									enseignantAValider.setDiplomePers(listofData.get(9));
									enseignantAValider.setSpecialiteProf(listofData.get(10));
									enseignantAValider.setNumtel1Pers(listofData.get(12));
									//listofData.get(11) correspond a la fonction
									enseignantAValider.setNumtel2Pers(listofData.get(13));
									enseignantAValider.setVillePers(listofData.get(14));
									enseignantAValider.setQuartierPers(listofData.get(15));
									enseignantAValider.setEmailPers(listofData.get(16));
									enseignantAValider.setUsername(listofData.get(17));
									enseignantAValider.setPassword(listofData.get(17));
									enseignantAValider.setEnabled(true);
									
									//System.out.println("enseignantAValider  "+i+"  "+enseignantAValider.toString());
									/*
									 * Après construction il faut donc valider avant de placer dans la liste des sg 
									 * a enregistrer
									 */
									DataBinder binder = new DataBinder(enseignantAValider);
									BindingResult results = binder.getBindingResult();
									validator.validate(enseignantAValider, results);
									//System.out.println("enseignant valide?----------------- "+results.toString()+" -------------------rrr");
									
									if (results.hasErrors()) {
										List<FieldError> listofError = results.getFieldErrors();
										String error = "ligne "+i+": ";
										for(FieldError fe : listofError){
											error += fe.getField()+"|";
										}
										listofErreur.add(error);
										model.addAttribute("erreurDansFichier", error);
										this.constructModelEnregListPersonnels(model, request);
										return "users/enregListPersonnels";
									}
									else{
										//Pas d'erreur donc entite censeur valide avec succes
										//System.err.println("enseignant qu'on ajoute: "+enseignantAValider.getNomsPers());
										listofEnseignantsAEnreg.add(enseignantAValider);
									}
								}
								
							}
							listofData.clear();
						}
						j++;
					}//fin du while sur les cellules
				}//fin du if i>3
				i++;
			}//fin du while sur les lignes
			wb.close();
			//System.err.println("TOUJOURS PAS DERREUR et IL RESTE A VOIR SI PAS D'ERREUR DANS LE FICHIER");

			/*
			 * On ne peut lancer l'enregistrement de la liste que si la liste des erreurs trouvés dans le 
			 * fichier est vide. 
			 */
			if(listofErreur.size() == 0){
				//On peut maintenant lancer la fonction qui enregistre une liste de personnel en BD
				
				List<String> listofErrorEnreg = new ArrayList<String>();
				List<String> listofErrorInter = new ArrayList<String>();
				
				//On appele les 03 methodes: une pour chaque type de user
				listofErrorInter = usersService.saveListCenseurs(listofCenseursAEnreg);
				//System.out.println("taille liste censeur a enreg "+listofCenseursAEnreg.size());
				for(Censeurs cae : listofCenseursAEnreg){
					//System.out.println(cae.getNomsPers());
				}
				for(String ei : listofErrorInter){
					listofErrorEnreg.add(ei);
				}
				listofErrorInter = usersService.saveListSG(listofSGAEnreg);
				//System.out.println("taille liste sg a enreg "+listofSGAEnreg.size());
				for(SG cae : listofSGAEnreg){
					//System.out.println(cae.getNomsPers());
				}
				for(String ei : listofErrorInter){
					listofErrorEnreg.add(ei);
				}
				listofErrorInter = usersService.saveListEnseignants(listofEnseignantsAEnreg);
				//System.out.println("taille liste enseignant a enreg "+listofEnseignantsAEnreg.size());
				for(Enseignants cae : listofEnseignantsAEnreg){
					//System.out.println(cae.getNomsPers());
				}
				for(String ei : listofErrorInter){
					listofErrorEnreg.add(ei);
				}
				
				if(listofErrorEnreg.size() == 0){
					model.addAttribute("enregListSucces", "successEnregList");
					
					this.constructModelEnregListPersonnels(model, request);
					return "users/enregListPersonnels";
				}
				else{
					model.addAttribute("listofErrorEnreg", listofErrorEnreg);
					this.constructModelEnregListPersonnels(model, request);
					return "users/enregListPersonnels";
				}
				
			}
			else{
				//System.err.println("il y a des erreurs lors du parcours du fichier ");
				for(String s: listofErreur){
					//System.err.println("erreur "+s);
				}
			}
			
		}
		return "redirect:/logesco/users/chefetab/getenregListPersonnels?"
				+ "enreglistpersonnelserrorfich";
	}

	@SuppressWarnings("unused")
	@PostMapping(path="/postupdatePersonnels")
	public String postupdatePersonnels(@Valid @ModelAttribute("updatePersonnelsForm") 
	UpdatePersonnelsForm updatePersonnelsForm, 
	BindingResult bindingResult, Model model, 
	@RequestParam(name="picturephotoPers")MultipartFile filephotoPers, 
	HttpServletRequest request, HttpServletResponse response) 
			throws ParseException, Exception, IOException{

		/*
		 * Vérification et affichage des messages d'erreur dans le formulaire
		 */
		//System.err.println("ICI AU DEBUT DE postUPDATEPersonnels USERSUSERSUSERS");

		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE updatePersonnels");
			/*
			 * En fait pour l'affichage de la page de modification, le paramètre idUsers ne peut pas être nul
			 * ET AUSSI LES AUTRES PARAMETRE D4AFFICHAGE DOIVENT ETRE AUSSI MEMORISER DANS LA REQUETE
			 */
			//System.err.println("errererererereeeeeeeeeeeeeeeeeeeeeee");
			return "users/updatePersonnels";

		}

		int numPageCenseur=updatePersonnelsForm.getNumPageCenseur();
		int numPageSg=updatePersonnelsForm.getNumPageSg();
		int numPageIntendant=updatePersonnelsForm.getNumPageIntendant();
		int numPageEns=updatePersonnelsForm.getNumPageEns();

		/*
		 * le roleCode ne doit pas être egale à 0 donc faut aussi signaler cette erreur car cela signifie que rien n'a été choisi
		 */
		if(updatePersonnelsForm.getRoleCode()==0){
			//System.err.println("erreur de choix de code ");
			return "redirect:/logesco/users/chefetab/getupdatePersonnels?idUsers="+updatePersonnelsForm.getIdPersonnels()
			+ "&&numPageCenseur="+numPageCenseur
			+ "&&numPageSg="+numPageSg
			+ "&&numPageIntendant="+numPageIntendant
			+ "&&numPageEns="+numPageEns
			+ "&&updatepersonnelserrorChoixRoles";
		}

		/*
		 * Tout est bien rempli dans le formulaire et on peut donc enregistrer l'etablissement
		 */
		/*//System.err.println("AUCUNE ERREUR DANS LE FORMULAIRE UPDATEPersonnels"
				+ "ON INSTANCIE ET INITIALISE  TOUT ET TOUT POUR LA MODIFICATION PROPREMENT DITE  "
				+updatePersonnelsForm.getRoleCode());*/

		Long idPersonnels=new Long(0);

		//System.err.println("il faut mettre à jour le proffesseur");

		if((updatePersonnelsForm.getRoleCodeAModif()==1)||(updatePersonnelsForm.getRoleCodeAModif()==2)){
			//Alors le personnel qu'on veut modifier assumait le role de censeur mais on peut lui faire changer de role

			Censeurs censeurAModif=new Censeurs();

			censeurAModif.setDatenaissPers(updatePersonnelsForm.getDatenaissPers());
			censeurAModif.setDiplomePers(updatePersonnelsForm.getDiplomePers());
			censeurAModif.setEmailPers(updatePersonnelsForm.getEmailPers());
			censeurAModif.setEnabled(true);
			censeurAModif.setGradePers(updatePersonnelsForm.getGradePers());
			censeurAModif.setLieunaissPers(updatePersonnelsForm.getLieunaissPers());
			censeurAModif.setNationalitePers(updatePersonnelsForm.getNationalitePers());
			censeurAModif.setNomsPers(updatePersonnelsForm.getNomsPers());
			censeurAModif.setNumcniPers(updatePersonnelsForm.getNumcniPers());
			censeurAModif.setNumtel1Pers(updatePersonnelsForm.getNumtel1Pers());
			censeurAModif.setNumtel2Pers(updatePersonnelsForm.getNumtel2Pers());
			censeurAModif.setPassword(updatePersonnelsForm.getPassword());
			censeurAModif.setPrenomsPers(updatePersonnelsForm.getPrenomsPers());
			censeurAModif.setQuartierPers(updatePersonnelsForm.getQuartierPers());
			censeurAModif.setSexePers(updatePersonnelsForm.getSexePers());
			censeurAModif.setSpecialiteProf(updatePersonnelsForm.getSpecialiteProf());
			censeurAModif.setStatutPers(updatePersonnelsForm.getStatutPers());
			censeurAModif.setUsername(updatePersonnelsForm.getUsername());
			censeurAModif.setVillePers(updatePersonnelsForm.getVillePers());

			//System.err.println("TOUJOURS PAS DERREUR ON SETPHOTOPERS CENSEUR");
			if(!(filephotoPers.isEmpty())){
				censeurAModif.setPhotoPers(filephotoPers.getOriginalFilename());
			}

			Long idUsers=updatePersonnelsForm.getIdPersonnels();
			idPersonnels=usersService.updateProffesseurs(idUsers, censeurAModif);

			/*int modifNumero=usersService.updateNumeroCenseurs(idUsers, updatePersonnelsForm.getNumeroPers());

			if(modifNumero==1){
				//System.err.println("le numero est bien modifie ");
			}
			else{
				//System.err.println("le nouveau numero appartient à quelqu'un");
			}*/

		}
		else if((updatePersonnelsForm.getRoleCodeAModif()==3)||(updatePersonnelsForm.getRoleCodeAModif()==4)){

			//Alors le personnel qu'on veut modifier assumait le role de SG mais on peut lui faire changer de role

			SG sgAModif=new SG();

			sgAModif.setDatenaissPers(updatePersonnelsForm.getDatenaissPers());
			sgAModif.setDiplomePers(updatePersonnelsForm.getDiplomePers());
			sgAModif.setEmailPers(updatePersonnelsForm.getEmailPers());
			sgAModif.setEnabled(true);
			sgAModif.setGradePers(updatePersonnelsForm.getGradePers());
			sgAModif.setLieunaissPers(updatePersonnelsForm.getLieunaissPers());
			sgAModif.setNationalitePers(updatePersonnelsForm.getNationalitePers());
			sgAModif.setNomsPers(updatePersonnelsForm.getNomsPers());
			sgAModif.setNumcniPers(updatePersonnelsForm.getNumcniPers());
			sgAModif.setNumtel1Pers(updatePersonnelsForm.getNumtel1Pers());
			sgAModif.setNumtel2Pers(updatePersonnelsForm.getNumtel2Pers());
			sgAModif.setPassword(updatePersonnelsForm.getPassword());
			sgAModif.setPrenomsPers(updatePersonnelsForm.getPrenomsPers());
			sgAModif.setQuartierPers(updatePersonnelsForm.getQuartierPers());
			sgAModif.setSexePers(updatePersonnelsForm.getSexePers());
			sgAModif.setSpecialiteProf(updatePersonnelsForm.getSpecialiteProf());
			sgAModif.setStatutPers(updatePersonnelsForm.getStatutPers());
			sgAModif.setUsername(updatePersonnelsForm.getUsername());
			sgAModif.setVillePers(updatePersonnelsForm.getVillePers());

			//System.err.println("TOUJOURS PAS DERREUR ON SETPHOTOPERS CENSEUR");
			if(!(filephotoPers.isEmpty())){
				sgAModif.setPhotoPers(filephotoPers.getOriginalFilename());
			}

			Long idUsers=updatePersonnelsForm.getIdPersonnels();
			idPersonnels=usersService.updateProffesseurs(idUsers, sgAModif);

			/*int modifNumero=usersService.updateNumeroSG(idUsers, updatePersonnelsForm.getNumeroPers());

			if(modifNumero==1){
				//System.err.println("le numero est bien modifie ");
			}
			else{
				//System.err.println("le nouveau numero appartient à quelqu'un");
			}*/

		}
		else if((updatePersonnelsForm.getRoleCodeAModif()==5)||(updatePersonnelsForm.getRoleCodeAModif()==6)){

			//Alors le personnel qu'on veut modifier assumait le role de SG mais on peut lui faire changer de role

			Intendant intendantAModif=new Intendant();

			intendantAModif.setDatenaissPers(updatePersonnelsForm.getDatenaissPers());
			intendantAModif.setDiplomePers(updatePersonnelsForm.getDiplomePers());
			intendantAModif.setEmailPers(updatePersonnelsForm.getEmailPers());
			intendantAModif.setEnabled(true);
			intendantAModif.setGradePers(updatePersonnelsForm.getGradePers());
			intendantAModif.setLieunaissPers(updatePersonnelsForm.getLieunaissPers());
			intendantAModif.setNationalitePers(updatePersonnelsForm.getNationalitePers());
			intendantAModif.setNomsPers(updatePersonnelsForm.getNomsPers());
			intendantAModif.setNumcniPers(updatePersonnelsForm.getNumcniPers());
			intendantAModif.setNumtel1Pers(updatePersonnelsForm.getNumtel1Pers());
			intendantAModif.setNumtel2Pers(updatePersonnelsForm.getNumtel2Pers());
			intendantAModif.setPassword(updatePersonnelsForm.getPassword());
			intendantAModif.setPrenomsPers(updatePersonnelsForm.getPrenomsPers());
			intendantAModif.setQuartierPers(updatePersonnelsForm.getQuartierPers());
			intendantAModif.setSexePers(updatePersonnelsForm.getSexePers());
			intendantAModif.setSpecialiteProf(updatePersonnelsForm.getSpecialiteProf());
			intendantAModif.setStatutPers(updatePersonnelsForm.getStatutPers());
			intendantAModif.setUsername(updatePersonnelsForm.getUsername());
			intendantAModif.setVillePers(updatePersonnelsForm.getVillePers());

			//System.err.println("TOUJOURS PAS DERREUR ON SETPHOTOPERS intendant");
			if(!(filephotoPers.isEmpty())){
				intendantAModif.setPhotoPers(filephotoPers.getOriginalFilename());
			}

			Long idUsers=updatePersonnelsForm.getIdPersonnels();
			idPersonnels=usersService.updateProffesseurs(idUsers, intendantAModif);

			/*int modifNumero=usersService.updateNumeroIntendant(idUsers, updatePersonnelsForm.getNumeroPers());

			if(modifNumero==1){
				//System.err.println("le numero est bien modifie ");
			}
			else{
				//System.err.println("le nouveau numero appartient à quelqu'un");
			}*/

		}
		else if((updatePersonnelsForm.getRoleCodeAModif()==7)){

			//Alors le personnel qu'on veut modifier assumait le role de SG mais on peut lui faire changer de role

			Enseignants ensAModif=new Enseignants();

			ensAModif.setDatenaissPers(updatePersonnelsForm.getDatenaissPers());
			ensAModif.setDiplomePers(updatePersonnelsForm.getDiplomePers());
			ensAModif.setEmailPers(updatePersonnelsForm.getEmailPers());
			ensAModif.setEnabled(true);
			ensAModif.setGradePers(updatePersonnelsForm.getGradePers());
			ensAModif.setLieunaissPers(updatePersonnelsForm.getLieunaissPers());
			ensAModif.setNationalitePers(updatePersonnelsForm.getNationalitePers());
			ensAModif.setNomsPers(updatePersonnelsForm.getNomsPers());
			ensAModif.setNumcniPers(updatePersonnelsForm.getNumcniPers());
			ensAModif.setNumtel1Pers(updatePersonnelsForm.getNumtel1Pers());
			ensAModif.setNumtel2Pers(updatePersonnelsForm.getNumtel2Pers());
			ensAModif.setPassword(updatePersonnelsForm.getPassword());
			ensAModif.setPrenomsPers(updatePersonnelsForm.getPrenomsPers());
			ensAModif.setQuartierPers(updatePersonnelsForm.getQuartierPers());
			ensAModif.setSexePers(updatePersonnelsForm.getSexePers());
			ensAModif.setSpecialiteProf(updatePersonnelsForm.getSpecialiteProf());
			ensAModif.setStatutPers(updatePersonnelsForm.getStatutPers());
			ensAModif.setUsername(updatePersonnelsForm.getUsername());
			ensAModif.setVillePers(updatePersonnelsForm.getVillePers());

			//System.err.println("TOUJOURS PAS DERREUR ON SETPHOTOPERS ensAModif");
			if(!(filephotoPers.isEmpty())){
				ensAModif.setPhotoPers(filephotoPers.getOriginalFilename());
			}

			Long idUsers=updatePersonnelsForm.getIdPersonnels();
			idPersonnels=usersService.updateProffesseurs(idUsers, ensAModif);

		}



		if(idPersonnels.longValue()>0){
			//System.err.println("la mise à jour du personnel s'est bien passe on doit maintenant fixer les rôles");
			//ie que la modification du proffesseur s'est bien passe
			String roleString=new String("ENSEIGNANT");
			Long idUsers=updatePersonnelsForm.getIdPersonnels();
			if((updatePersonnelsForm.getRoleCode()==1)||(updatePersonnelsForm.getRoleCode()==2)){
				/*
				 * Lorsqu'on est censeur on ne peut qu'assumer les roles de censeur ou alors de censeur et enseignant
				 * C'est pourquoi le test suivant est effectué car si ce test est vérifié alors on a le role censeur 
				 * Bref etant censeur on peut devenir censeur et enseignant et rester censeur
				 */
				if((updatePersonnelsForm.getRoleCodeAModif()==1)||(updatePersonnelsForm.getRoleCodeAModif()==2)){
					//System.err.println("On supprime d'abord tous les rôles que possedaient cet utilisateur ");

					int repServeur=usersService.supprimerAllRoleUsers(
							usersService.findUtilisateurs(updatePersonnelsForm.getIdPersonnels()));

					/*if(repServeur==0)  //System.err.println("erreur de suppression des rôles surtout s'il y avait aucun role");
*/
					//System.err.println("les rôles ont été tous supprimer  MAINTENANT ON FAIT LES MISES A JOUR");
					String roleString1=new String("CENSEUR");
					int repServ=usersService.saveUsersRoles(idUsers, roleString1);
					if(repServ==1) //System.err.println(" rôles censeur bien fixé");
					if(updatePersonnelsForm.getRoleCode()==1){
						repServ=usersService.saveUsersRoles(idUsers, roleString);
						/*if(repServ==1) //System.err.println(" rôles enseignant bien fixé");*/
					}
				}
				else{
					idPersonnels=new Long(-7);
				}
			}
			else if((updatePersonnelsForm.getRoleCode()==3)||(updatePersonnelsForm.getRoleCode()==4)){
				/*
				 * Lorsqu'on est SG on ne peut qu'assumer les roles de SG ou alors de SG et enseignant
				 * C'est pourquoi le test suivant est effectué car si ce test est vérifié alors on a le role SG 
				 * Bref etant SG on peut devenir SG et enseignant et rester SG
				 */
				if((updatePersonnelsForm.getRoleCodeAModif()==3)||(updatePersonnelsForm.getRoleCodeAModif()==4)){
					//System.err.println("On supprime d'abord tous les rôles que possedaient cet utilisateur ");

					int repServeur=usersService.supprimerAllRoleUsers(usersService.findUtilisateurs(updatePersonnelsForm.getIdPersonnels()));

				/*	if(repServeur==0)  //System.err.println("erreur de suppression des rôles surtout s'il y avait aucun role");*/
					//System.err.println("les rôles ont été tous supprimer  MAINTENANT ON FAIT LES MISES A JOUR");

					String roleString1=new String("SG");
					int repServ=usersService.saveUsersRoles(idUsers, roleString1);
					if(repServ==1) //System.err.println(" rôles sg bien fixé");
					if(updatePersonnelsForm.getRoleCode()==3){
						repServ=usersService.saveUsersRoles(idUsers, roleString);
						/*if(repServ==1) //System.err.println(" rôles enseignant bien fixé");*/
					}
				}
				else{
					idPersonnels=new Long(-7);
				}
			}
			else if((updatePersonnelsForm.getRoleCode()==5)||(updatePersonnelsForm.getRoleCode()==6)){
				/*
				 * Lorsqu'on est INTENDANT on ne peut qu'assumer les roles de INTENDANT ou alors de INTENDANT et enseignant
				 * C'est pourquoi le test suivant est effectué car si ce test est vérifié alors on a le role INTENDANT 
				 * Bref etant INTENDANT on peut devenir INTENDANT et enseignant et rester INTENDANT
				 */
				if((updatePersonnelsForm.getRoleCodeAModif()==5)||(updatePersonnelsForm.getRoleCodeAModif()==6)){

					//System.err.println("On supprime d'abord tous les rôles que possedaient cet utilisateur ");

					int repServeur=usersService.supprimerAllRoleUsers(usersService.findUtilisateurs(updatePersonnelsForm.getIdPersonnels()));

					/*if(repServeur==0)  //System.err.println("erreur de suppression des rôles surtout s'il y avait aucun role");*/

					//System.err.println("les rôles ont été tous supprimer  MAINTENANT ON FAIT LES MISES A JOUR");

					String roleString1=new String("INTENDANT");
					int repServ=usersService.saveUsersRoles(idUsers, roleString1);
					if(repServ==1) //System.err.println(" rôles intendant bien fixé");
					if(updatePersonnelsForm.getRoleCode()==5){
						repServ=usersService.saveUsersRoles(idUsers, roleString);
						/*if(repServ==1) //System.err.println(" rôles enseignant bien fixé");*/
					}
				}
				else{
					idPersonnels=new Long(-7);
				}
			}
			else if((updatePersonnelsForm.getRoleCode()==7)){
				/*
				 * Lorsqu'on est ENSEIGNANT on reste ENSEIGNANT sauf si on change de fonction
				 */
				if((updatePersonnelsForm.getRoleCodeAModif()==7)){

					//System.err.println("On supprime d'abord tous les rôles que possedaient cet utilisateur ");

					int repServeur=usersService.supprimerAllRoleUsers(usersService.findUtilisateurs(updatePersonnelsForm.getIdPersonnels()));

					/*if(repServeur==0)  //System.err.println("erreur de suppression des rôles surtout s'il y avait aucun role");*/

					//System.err.println("les rôles ont été tous supprimer  MAINTENANT ON FAIT LES MISES A JOUR");

					String roleString1=new String("ENSEIGNANT");
					int repServ=usersService.saveUsersRoles(idUsers, roleString1);
					/*if(repServ==1) //System.err.println(" rôles enseignant bien fixé");*/
				}
				else{
					idPersonnels=new Long(-7);
				}
			}
		}

		if(idPersonnels.longValue()==-1) 
			return "redirect:/logesco/users/chefetab/getupdatePersonnels?enregpersonnelserrorNumeroCni"
			+ "&&numPageCenseur="+numPageCenseur
			+ "&&numPageSg="+numPageSg
			+ "&&numPageIntendant="+numPageIntendant
			+ "&&numPageEns="+numPageEns;
		if(idPersonnels.longValue()==-2) 
			return "redirect:/logesco/users/chefetab/getupdatePersonnels?enregpersonnelserrorNomsPrenomsDatenaiss"
			+ "&&numPageCenseur="+numPageCenseur
			+ "&&numPageSg="+numPageSg
			+ "&&numPageIntendant="+numPageIntendant
			+ "&&numPageEns="+numPageEns;
		if(idPersonnels.longValue()==-3) 
			return "redirect:/logesco/users/chefetab/getupdatePersonnels?enregpersonnelserrorUsername"
			+ "&&numPageCenseur="+numPageCenseur
			+ "&&numPageSg="+numPageSg
			+ "&&numPageIntendant="+numPageIntendant
			+ "&&numPageEns="+numPageEns;
		if(idPersonnels.longValue()==-4) 
			return "redirect:/logesco/users/chefetab/getupdatePersonnels?enregpersonnelserrorNumeroPers"
			+ "&&numPageCenseur="+numPageCenseur
			+ "&&numPageSg="+numPageSg
			+ "&&numPageIntendant="+numPageIntendant
			+ "&&numPageEns="+numPageEns;
		if(idPersonnels.longValue()==-5) 
			return "redirect:/logesco/users/chefetab/getupdatePersonnels?enregpersonnelserrorRole"
			+ "&&numPageCenseur="+numPageCenseur
			+ "&&numPageSg="+numPageSg
			+ "&&numPageIntendant="+numPageIntendant
			+ "&&numPageEns="+numPageEns;
		if(idPersonnels.longValue()==-6) 
			return "redirect:/logesco/users/chefetab/getupdatePersonnels?enregpersonnelserrorRoleEns"
			+ "&&numPageCenseur="+numPageCenseur
			+ "&&numPageSg="+numPageSg
			+ "&&numPageIntendant="+numPageIntendant
			+ "&&numPageEns="+numPageEns;

		/*
		 * Ici on peut upload l'image sans problème puisqu'aucune erreur n'est constaté pendant le 
		 * processus d'enregistrement
		 */
		//System.err.println("TOUJOURS PAS DERREUR ON UPLOAD PHOTO PERSONNEL");
		if(!(filephotoPers.isEmpty())){
			filephotoPers.transferTo(new File(photoPersonnelsDir+idPersonnels.longValue()));
		}

		/*
		 * Les erreurs de changement de rôle ne doivent pas affecté la modification des données
		 * c'est pourquoi c'est a ce niveau qu'on va interpréter les erreurs de modifcation des roles
		 * 
		 * Voila pourquoi l'affichage de ces avectissement se fait sur la page de consultation car
		 * a ce niveau toutes les autres modifications ont réussi
		 */
		if(idPersonnels.longValue()==-7) 
			return "redirect:/logesco/users/chefetab/getconsulterPersonnels?enregpersonnelswarningRole"
			+ "&&numPageCenseur="+numPageCenseur
			+ "&&numPageSg="+numPageSg
			+ "&&numPageIntendant="+numPageIntendant
			+ "&&numPageEns="+numPageEns;




		return "redirect:/logesco/users/chefetab/getconsulterPersonnels?updatepersonnelssuccess"
		+ "&&numPageCenseur="+numPageCenseur
		+ "&&numPageSg="+numPageSg
		+ "&&numPageIntendant="+numPageIntendant
		+ "&&numPageEns="+numPageEns;

	}

	@SuppressWarnings({ "rawtypes", "resource" })
	@PostMapping(path="/postenregListEleves")
	public String postenregListEleves(@Valid @ModelAttribute("enregListElevesForm") 
	SaveListElevesForm enregListElevesForm, 
	BindingResult bindingResult, Model model, 
	@RequestParam(name="filecheminFichier")MultipartFile filecheminFichier, 
	HttpServletRequest request, HttpServletResponse response) 
			throws ParseException, Exception, IOException{

		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE enregListEleves "+bindingResult.getFieldError());
			this.constructModelEnregListEleves(model, request);
			return "users/enregListEleves";
		}
		/*
		 * on recherche la classe dans laquelle les élèves seront ajouté
		 */
		Classes classe = usersService.findClasses(enregListElevesForm.getIdClasseConcerne());

		if(classe == null) 
			return "redirect:/logesco/users/chefetab/getenregListEleves?enreglisteleveserrorClasse";

		Date dateJour = new Date();

		SimpleDateFormat spd = new SimpleDateFormat("yyyy");//"dd-MM-yyyy"
		SimpleDateFormat spd1 = new SimpleDateFormat("yyyy-MM-dd");//"dd-MM-yyyy"
		String anneeString = spd.format(dateJour);

		Etablissement etab = adminService.getEtablissement();
		String codeEtab="";
		if(etab != null) codeEtab = etab.getCodeMatriculeEtab();

		if(!(filecheminFichier.isEmpty())){
			String nomfich = modelesDir+filecheminFichier.getOriginalFilename();
			//Avant toutes chose, on upload le fichier sur le serveur
			filecheminFichier.transferTo(new File(nomfich));
			/*
			 * Lecture et fouille du fichier excel en vue des validations
			 */
			//On transforme le fichier uploader en fichier traitable dans java
			FileInputStream input = new FileInputStream(nomfich);
			//Maintenant on precise que c'est un fichier Excel
			XSSFWorkbook wb = new XSSFWorkbook(input);
			//Maintenant on recupere la première feuille du fichier excel
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator rows = sheet.rowIterator();
			int i = 1;
			List<Eleves> listofElevesAEnreg = new ArrayList<Eleves>();
			List<String> listofErreur = new ArrayList<String>();
			
			while(rows.hasNext()){
				List<String> listofData = new ArrayList<String>();
				////System.err.println("ligne numero "+i);
				XSSFRow row = (XSSFRow)rows.next();
				Iterator cells = row.cellIterator();
				int j = 1;
				//***Initialiser les champs de l'élève à partir des données lues dans le fichier
				if(i>=3){
					while(cells.hasNext()){
						XSSFCell cell = (XSSFCell)cells.next();

						////System.err.println("colonne numero "+j);
						if(j==1) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Noms");
						}
						if(j==2) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Prénoms");
						}
						if(j==3) {
							Date datenaiss = cell.getDateCellValue();
							String datenaissString = spd1.format(datenaiss);
							listofData.add(datenaissString);
							////System.err.println("Date de naissance");
						}
						if(j==4) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Lieu de naissance");
						}
						if(j==5) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							////System.err.println("Sexe");
							String sexe = "masculin";
							if(cell.getStringCellValue().equalsIgnoreCase("F")==true
									||cell.getStringCellValue().equalsIgnoreCase("feminin")==true
									||cell.getStringCellValue().equalsIgnoreCase("féminin")==true){
								sexe = "feminin";
							}
							listofData.add(sexe);
						}
						if(j==6) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Nationalite");
						}
						if(j==7) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Quartier");
						}
						if(j==8) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Ville");
						}
						if(j==9) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							String tel = ""+cell.getStringCellValue();
							listofData.add(""+tel);
							////System.err.println("Tel");
						}
						if(j==10) {
							//On met d'abord le type de la cellule en String pour eviter les problemes de lecture
							cell.setCellType(CellType.STRING);
							listofData.add(cell.getStringCellValue());
							////System.err.println("Email");
						}
						/*
						 * On affiche donc les données des élèves si on les a tous lues
						 * Et on est sur de les avoir tous lues lorsqu'on a atteint la 10 ieme cellule
						 * donc j == 10
						 */
						if(j == 10){
							/*
							 * Ici dans listofData on a les éléments de construction d'une entite élève
							 * On peut donc construire, valider et ajouter dans la liste des élèves a 
							 * enregistrer si l'entité est validée sinon on enregistre l'erreur
							 */
							Eleves eleveAValider = new Eleves();
							if(listofData.size() == 10){
								eleveAValider.setNomsEleves(listofData.get(0));
								eleveAValider.setPrenomsEleves(listofData.get(1));
								eleveAValider.setDatenaissEleves(spd1.parse(listofData.get(2)));
								eleveAValider.setLieunaissEleves(listofData.get(3));
								eleveAValider.setSexeEleves(listofData.get(4));
								eleveAValider.setNationaliteEleves(listofData.get(5));
								eleveAValider.setQuartierEleves(listofData.get(6));
								eleveAValider.setVilleEleves(listofData.get(7));
								eleveAValider.setNumtel1Parent(listofData.get(8));
								eleveAValider.setEmailParent(listofData.get(9));
								//les champs dont les valeurs sont connus
								eleveAValider.setEtatInscEleves("non inscrit");
								eleveAValider.setRedoublant("non");
								eleveAValider.setStatutEleves("nouveau");
								//les index de la liste des matricule a générer commence a partir de 0
								//int index = i+1;
								String matricule = usersService.getNextMatricule(codeEtab, anneeString);
								eleveAValider.setMatriculeEleves(matricule);
								////System.out.println("eleveAValider  "+i+"  "+eleveAValider.toString());
								/*
								 * Après construction il faut donc valider avant de placer dans la liste des élèves 
								 * a enregistrer
								 */
								DataBinder binder = new DataBinder(eleveAValider);
								BindingResult results = binder.getBindingResult();
								validator.validate(eleveAValider, results);
								////System.out.println("rrrr----------------- "+results.toString()+" -------------------rrr");
								if (results.hasErrors()) {
									List<FieldError> listofError = results.getFieldErrors();
									String error = "ligne "+i+": ";
									for(FieldError fe : listofError){
										error += fe.getField()+"|";
									}
									listofErreur.add(error);
									model.addAttribute("erreurDansFichier", error);
									this.constructModelEnregListEleves(model, request);
									return "users/enregListEleves";
								}
								else{
									//Pas d'erreur donc entite eleve valide avec succes
									listofElevesAEnreg.add(eleveAValider);
								}
							}
							/*//System.err.println("eleve "+i);
							//System.err.println("---  ");
							for(String s : listofData){
								//System.out.print(" "+s+" | ");
							}
							//System.err.println(" ---");*/
							listofData.clear();
						}
						j++;
					}//fin du while sur les cellule
				}//fin du if i>=3
				i++;
				
			}//fin du while sur les lignes
			wb.close();
			//System.err.println("TOUJOURS PAS DERREUR et IL RESTE A VOIR SI PAS D'ERREUR DANS LE FICHIER");

			/*
			 * On ne peut lancer l'enregistrement de la liste que si la liste des erreurs trouvés dans le 
			 * fichier est vide. 
			 */
			if(listofErreur.size() == 0){
				//On peut maintenant lancer la fonction qui enregistre une liste d'eleve en BD
				/*//System.out.println("lancer l'enregistrement de la liste des eleves "+listofElevesAEnreg.size()+
						" en classe de "+classe.getCodeClasses()+classe.getNumeroClasses());*/
				
				List<String> listofErrorEnreg = new ArrayList<String>();
				listofErrorEnreg = usersService.saveListEleves(listofElevesAEnreg, classe.getIdClasses());
				////System.err.println("resultat de l'enreglist "+listofErrorEnreg.size());
				if(listofErrorEnreg.size() == 0){
					model.addAttribute("enregListSucces", "successEnregList");
					String classename = classe.getCodeClasses()+classe.getSpecialite().getCodeSpecialite()+
							classe.getNumeroClasses();
					model.addAttribute("classeenregListSucces", classename);
					this.constructModelEnregListEleves(model, request);
					return "users/enregListEleves";
				}
				else{
					model.addAttribute("listofErrorEnreg", listofErrorEnreg);
					this.constructModelEnregListEleves(model, request);
					return "users/enregListEleves";
				}
			}
			
			
		}
		return "redirect:/logesco/users/chefetab/getenregListEleves?enreglisteleveserrorfich";
	}


	@PostMapping(path="/postenregEleves")
	public String postenregEleves(@Valid @ModelAttribute("enregElevesForm") 
	UpdateElevesForm enregElevesForm, 
	BindingResult bindingResult, Model model, 
	@RequestParam(name="picturephotoEleves")MultipartFile filephotoEleve, 
	HttpServletRequest request, HttpServletResponse response) 
			throws ParseException, Exception, IOException{

		/*
		 * Validation des données saisies dans le formulaire par le proviseur (user)
		 */
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE enregEleves");
			String idElevesString=" ";//puisque la methode en a besoin
			this.constructModelEnregEleves(model, request, idElevesString);
			return "users/enregEleves";
		}

		/*
		 * Tout est bien rempli dans le formulaire et on peut donc enregistrer l'etablissement
		 */
		/*//System.err.println("AUCUNE ERREUR DANS LE FORMULAIRE enregEleves"
				+ "ON INSTANCIE ET INITIALISE  ");*/

		Long idEleves=new Long(-10);

		/*
		 * Instantiation d'un objet élève
		 */
		Date dateJour = new Date();

		SimpleDateFormat spd = new SimpleDateFormat("yyyy");//"dd-MM-yyyy"
		String anneeString = spd.format(dateJour);

		Etablissement etab = adminService.getEtablissement();
		String codeEtab="";
		if(etab != null) codeEtab = etab.getCodeMatriculeEtab();

		String matricule = usersService.getNextMatricule(codeEtab, anneeString);

		//System.err.println("le matricule generer pour cet eleve est "+matricule);
		
		Eleves eleveAEnreg=new Eleves();

		/*
		 * Initialisation de l'objet élève instancié
		 * On va tout initialisé avec les données à enregistrer sauf 
		 * la Classe et le matricule qui sera fait coté metier
		 * Donc la fonction d'enregistrement de l'élève prendra en parammètre les données indiquant la classe
		 */
		eleveAEnreg.setDatenaissEleves(enregElevesForm.getDatenaissEleves());
		eleveAEnreg.setEmailParent(enregElevesForm.getEmailParent());
		eleveAEnreg.setEtatInscEleves("non inscrit");
		eleveAEnreg.setLieunaissEleves(enregElevesForm.getLieunaissEleves());
		eleveAEnreg.setMatriculeEleves(matricule);
		eleveAEnreg.setNationaliteEleves(enregElevesForm.getNationaliteEleves());
		eleveAEnreg.setNomsEleves(enregElevesForm.getNomsEleves());
		eleveAEnreg.setNumtel1Parent(enregElevesForm.getNumtel1Parent());
		if(!(filephotoEleve.isEmpty())){
			eleveAEnreg.setPhotoEleves(filephotoEleve.getOriginalFilename());
		}
		eleveAEnreg.setPrenomsEleves(enregElevesForm.getPrenomsEleves());
		eleveAEnreg.setQuartierEleves(enregElevesForm.getQuartierEleves());
		eleveAEnreg.setRedoublant("non");
		eleveAEnreg.setSexeEleves(enregElevesForm.getSexeEleves());
		eleveAEnreg.setStatutEleves("nouveau");
		eleveAEnreg.setVilleEleves(enregElevesForm.getVilleEleves());

		/*
		 * Appel du metier pour l'enregistrement
		 */
		idEleves= usersService.saveEleves(eleveAEnreg, enregElevesForm.getIdClasse());

		if(idEleves.longValue()==0) return "redirect:/logesco/users/chefetab/getenregEleves?enregeleveserrorNames";

		if(idEleves.longValue()==-1) return "redirect:/logesco/users/chefetab/getenregEleves?enregeleveserrorMatricule";

		if(idEleves.longValue()==-2) return "redirect:/logesco/users/chefetab/getenregEleves?enregeleveserrorClasse";

		/*
		 * Tout s'étant bien passé et bien enregistré il faut uploader la photos
		 */

		//System.err.println("TOUJOURS PAS DERREUR ON UPLOAD PHOTO ELEVES");
		if(!(filephotoEleve.isEmpty())){
			filephotoEleve.transferTo(new File(photoElevesDir+idEleves.longValue()));
		}

		/*
		 * Recherche de l'élève enregistré pour le positionner dans le modele
		 */
		Eleves eleveEnreg=usersService.findEleves(idEleves);
		/*
		 * On place cet élève dans le model puisqu'il est utilisé pour l'effectif de la classe
		 * et pour imprimer le coupon d'inscription
		 */
		model.addAttribute("eleveEnreg", eleveEnreg);
		/*
		 * Il faut aussi placer la classe et l'éffectif de la classe aussi dans le model
		 */
		Classes classeConcerne=eleveEnreg.getClasse();
		model.addAttribute("classeConcerne", classeConcerne);
		int effectifprovClasse=usersService.getEffectifProvisoireClasse(classeConcerne.getIdClasses());
		model.addAttribute("effectifprovClasse", effectifprovClasse);

		return "redirect:/logesco/users/chefetab/getenregEleves?enregelevessuccess"
		+ "&&idElevesString="+eleveEnreg.getIdEleves();
	}


	@PostMapping(path="/postupdateEleves")
	public String postupdateEleves(@Valid @ModelAttribute("updateElevesForm") 
	UpdateElevesForm updateElevesForm, 
	BindingResult bindingResult, Model model, 
	@RequestParam(name="picturephotoEleves")MultipartFile filephotoEleve, 
	HttpServletRequest request, HttpServletResponse response) 
			throws ParseException, Exception, IOException{

		//System.err.println("ICI AU DEBUT DE postupdateEleves par PROVISEUR");
		//System.err.println("eleves a modifier "+updateElevesForm.toString());

		int numPageEleves=(Integer)request.getSession().getAttribute("numPageEleves");
		long idElevesAModif=updateElevesForm.getIdEleves();
		long idClasseSelect=updateElevesForm.getIdClasse();

		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE updateEleves");


			this.constructModelGestionEleves(model,	request, numPageEleves,	 idElevesAModif,
					idClasseSelect,	updateElevesForm);

			/*//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE: parametres numPageEleves== "+numPageEleves
					+"  idElevesAModif=="+idElevesAModif+" idClasseSelect == "+idClasseSelect);*/

			return "users/gestionEleves";
		}

		/*
		 * Tout est bien rempli dans le formulaire et on peut donc enregistrer les mise a jour sur l'élève
		 */
		/*//System.err.println("AUCUNE ERREUR DANS LE FORMULAIRE updateEleves"
				+ "ON INSTANCIE ET INITIALISE  ");*/

		/*Date dateJour = new Date();

		SimpleDateFormat spd = new SimpleDateFormat("yyyy");//"dd-MM-yyyy"
		String anneeString = spd.format(dateJour);
		String codeEtab = "";
		Etablissement etab = adminService.getEtablissement();
		if(etab!=null) codeEtab = etab.getCodeMatriculeEtab();
		String matricule = usersService.getNextMatricule(codeEtab, anneeString);*/

		Eleves eleveAModif=new Eleves();


		eleveAModif.setDatenaissEleves(updateElevesForm.getDatenaissEleves());
		eleveAModif.setEmailParent(updateElevesForm.getEmailParent());
		eleveAModif.setIdEleves(updateElevesForm.getIdEleves());
		eleveAModif.setLieunaissEleves(updateElevesForm.getLieunaissEleves());
		//eleveAModif.setMatriculeEleves(matricule);
		eleveAModif.setNationaliteEleves(updateElevesForm.getNationaliteEleves());
		eleveAModif.setNomsEleves(updateElevesForm.getNomsEleves());
		eleveAModif.setNumtel1Parent(updateElevesForm.getNumtel1Parent());
		if(!(filephotoEleve.isEmpty())){
			eleveAModif.setPhotoEleves(filephotoEleve.getOriginalFilename());
		}
		eleveAModif.setPrenomsEleves(updateElevesForm.getPrenomsEleves());
		eleveAModif.setQuartierEleves(updateElevesForm.getQuartierEleves());
		eleveAModif.setSexeEleves(updateElevesForm.getSexeEleves());
		eleveAModif.setStatutEleves(updateElevesForm.getStatutEleves());

		//System.err.println("Paramètres eleveAModif.setSexeEleves=="+updateElevesForm.getSexeEleves());
		//System.err.println("Paramètres eleveAModif.setStatutEleves=="+updateElevesForm.getStatutEleves());

		/*
		 * Appel du metier pour l'enregistrement
		 */
		Long idElevesModif= usersService.updateEleves(eleveAModif, updateElevesForm.getIdClasse());

		if(idElevesModif.longValue()==0) 
			return "redirect:/logesco/users/chefetab/getgestionEleves?updateeleveserrorNames"
			+ "&&idElevesAModif="+idElevesAModif
			+ "&&idClasseSelect="+idClasseSelect
			+ "&&numPageEleves="+numPageEleves;

		if(idElevesModif.longValue()==-1) 
			return "redirect:/logesco/users/chefetab/getgestionEleves?enregeleveserrorMatricule"
			+ "&&idElevesAModif="+idElevesAModif
			+ "&&idClasseSelect="+idClasseSelect
			+ "&&numPageEleves="+numPageEleves;

		/*if(idElevesModif.longValue()==-2) 
				return "redirect:/logesco/users/chefetab/getgestionEleves?enregeleveserrorClasse"
				+ "&&idElevesAModif="+idElevesAModif
				+ "&&idClasseSelect="+idClasseSelect
				+ "&&numPageEleves="+numPageEleves;*/

		/*
		 * Tout s'étant bien passé et bien enregistré il faut uploader la photos
		 */

		//System.err.println("TOUJOURS PAS DERREUR ON UPLOAD PHOTO ELEVES");
		if(!(filephotoEleve.isEmpty())){
			filephotoEleve.transferTo(new File(photoElevesDir+updateElevesForm.getIdEleves().longValue()));
		}


		return "redirect:/logesco/users/chefetab/getgestionEleves?updateelevessuccess"
		+ "&&idElevesAModif="+idElevesAModif
		+ "&&idClasseSelect="+idClasseSelect
		+ "&&numPageEleves="+numPageEleves;

	}


	////////////////////////////////////FIN DES REQUETES DE TYPE POST ////////////////////////////////////////////	

}
