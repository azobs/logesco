/**
 * 
 */
package org.logesco.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.logesco.form.*;
import org.logesco.services.*;
import org.logesco.entities.*;
import org.logesco.views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;




/**
 * @author cedrickiadjeu
 *
 */
@Controller
@RequestMapping(path="/logesco/admin")
public class AdminController {

	//private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	/**
	 * Objet metier qui permettra au controleur d'avoir les méthodes metiers
	 */
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IAdminServiceExport adminServiceExport;
	
	@Value("${dir.emblemes.logo}")
	private String logoDir;
	
	@Value("${dir.emblemes.banniere}")
	private String banniereDir;
	
	@Value("${dir.images.personnels}")
	private String photoPersonnelsDir;


	DateFormat dfNameJour=new SimpleDateFormat("EEEE");
	DateFormat dfJour=new SimpleDateFormat("dd");
	DateFormat dfmois=new SimpleDateFormat("MMMM");
	DateFormat dfyear=new SimpleDateFormat("yyyy");
	DateFormat dfheure=new SimpleDateFormat("H:m:s");
	Date dateJour=new Date();


	//debut des requetes GET
	/**************************************************************
	 * Chargement de la page d'accueil des administrateurs de la plateforme
	 * Avec la page de modification du mot de passe
	 *************************************************************/
	@GetMapping(path="/index")
	public String indexAdmin(@ModelAttribute("modifpasswordForm") 
			ModifpasswordForm modifpasswordForm, 
			Model model, HttpServletRequest request) throws ParseException{

		
		HttpSession session=request.getSession();

		//model.addAttribute("dfNameJour", dfNameJour.format(dateJour).toUpperCase());
		session.setAttribute("dfNameJour", dfNameJour.format(dateJour).toUpperCase());
		//model.addAttribute("dfJour", dfJour.format(dateJour));
		session.setAttribute("dfJour", dfJour.format(dateJour));
		//model.addAttribute("dfmois", dfmois.format(dateJour));
		session.setAttribute("dfmois", dfmois.format(dateJour));
		//model.addAttribute("dfyear", dfyear.format(dateJour));
		session.setAttribute("dfyear", dfyear.format(dateJour));
		//model.addAttribute("dfheure", dfheure.format(dateJour));
		session.setAttribute("dfheure", dfheure.format(dateJour));

		/*
		 * Il faut recuperer le username de l'user connecte et placer dans la session
		 * avant toutes redirection vers la page demandée 
		 */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		/*//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
				+ "xxxxxxxxxxxxxxxxxxxxxxxxxxxx"+auth.getName());*/
		model.addAttribute("username", auth.getName());

		/*//System.out.println(String.format("Modèle=%s, Session[username]=%s", model, 
				session.getAttribute("username")));*/

		session.setAttribute("username", auth.getName());

		/*//System.out.println(String.format("Modèle=%s, Session[usernamess]=%s", model, 
				session.getAttribute("username")));*/

		
		/*
		 * On doit charger la liste des niveaux car dans le menu l'admin doit pouvoir
		 * selectionner la classe qu'il désire configurer les cours. Et cette liste doit être dans la session
		 */
		List<Niveaux> listofNiveaux = adminService.findAllNiveaux();
		model.addAttribute("listofNiveaux", listofNiveaux);
		
		if(listofNiveaux.size() > 0) session.setAttribute("listofNiveaux", listofNiveaux);
		

		/*
		 * le formulaire de modification du mot de passe sera donc dans la page indexAdmin
		 */
		return "admin/indexAdmin";//return "/admin/indexAdmin";
	}

	@GetMapping(path="/getupdateAdresse")
	public String getupdateAdresse(@ModelAttribute("modifadressForm") 
			ModifAdressForm modifadressForm, 
			Model model, HttpServletRequest request) throws ParseException{

		/*
		 * On doit aller dans la base de donnée recuperer les adresses courantes
		 * de l'admin et les afficher dans le formulaire
		 */
		HttpSession session=request.getSession();
		Administrateurs admin=adminService.getUsersAdmin(
				(String)session.getAttribute("username"));

		modifadressForm.setEmailAdmin(admin.getEmailAdmin());
		modifadressForm.setNumtel1Admin(admin.getNumtel1Admin());
		modifadressForm.setNumtel2Admin(admin.getNumtel2Admin());


		return "admin/updateAdresse";
	}
	
	@GetMapping(path="/getresetPassword")
	public String getresetPassword(@ModelAttribute("passwordResetForm") 
		PasswordResetForm passwordResetForm, 
			Model model, HttpServletRequest request) throws ParseException{
		
		//Il faut charger la liste de tous les users sauf l'administrateur dans le modèle de cette page
		List<Utilisateurs> listofUsers = adminService.findAllUsers();
		model.addAttribute("listofUsers", listofUsers);
		
		
		return "admin/resetPassword";
	}
	
	@GetMapping(path="/getupdateUsername")
	public String getupdateAdresse(@ModelAttribute("modifusernameForm") 
			ModifUsernameForm modifusernameForm, 
			Model model, HttpServletRequest request) throws ParseException{
		/*
		 * On doit recuperer le nom d'utilisateur courant
		 */
		HttpSession session=request.getSession();
		modifusernameForm.setActiveUsername((String)session.getAttribute("username"));
		/*//System.err.println("username dans session =="+(String)session.getAttribute("username"));*/
		
		return "admin/updateUsername";
	}
	
	@GetMapping(path="/getupdateEtablissement")
	public String getupdateEtablissement(@ModelAttribute("updateEtablissementForm") 
			UpdateEtablissementForm updateEtablissementForm, 
			Model model, HttpServletRequest request) throws ParseException{
		
		/*
		 * Recuperer l'établissement enregistré dans la base de donnée
		 */
		Etablissement etab=adminService.getEtablissement();
		if(etab!=null){
			updateEtablissementForm.setAliasEtab(etab.getAliasEtab());
			updateEtablissementForm.setBpEtab(etab.getBpEtab());
			updateEtablissementForm.setDeviseEtab(etab.getDeviseEtab());
			updateEtablissementForm.setEmailEtab(etab.getEmailEtab());
			updateEtablissementForm.setMatriculeEtab(etab.getMatriculeEtab());
			updateEtablissementForm.setMinisteretuteleEtab(etab.getMinisteretuteleEtab());
			updateEtablissementForm.setNomsEtab(etab.getNomsEtab());
			updateEtablissementForm.setNumtel1Etab(etab.getNumtel1Etab());
			updateEtablissementForm.setNumtel2Etab(etab.getNumtel2Etab());
			updateEtablissementForm.setLogoEtab(etab.getLogoEtab());
			updateEtablissementForm.setBanniereEtab(etab.getBanniereEtab());
			updateEtablissementForm.setIdEtab(etab.getIdEtab());
			
			
			/***
			 * Debut des ajouts du 19-08-2018
			 */
			updateEtablissementForm.setAliasnomanglaisEtab(etab.getAliasnomanglaisEtab());
			updateEtablissementForm.setAliasministeretuteleEtab(etab.getAliasministeretuteleEtab());
			updateEtablissementForm.setAliasministeretuteleanglaisEtab(etab.getAliasministeretuteleanglaisEtab());
			updateEtablissementForm.setDeviseanglaisEtab(etab.getDeviseanglaisEtab());
			updateEtablissementForm.setMinisteretuteleanglaisEtab(etab.getMinisteretuteleanglaisEtab());
			updateEtablissementForm.setNomsanglaisEtab(etab.getNomsanglaisEtab());
			
			updateEtablissementForm.setDeleguationdeptuteleanglaisEtab(etab.getDeleguationdeptuteleanglaisEtab());
			updateEtablissementForm.setDeleguationdeptuteleEtab(etab.getDeleguationdeptuteleEtab());
			updateEtablissementForm.setDeleguationregtuteleanglaisEtab(etab.getDeleguationregtuteleanglaisEtab());
			updateEtablissementForm.setDeleguationregtuteleEtab(etab.getDeleguationregtuteleEtab());
			
			updateEtablissementForm.setCodeMatriculeEtab(etab.getCodeMatriculeEtab());
			updateEtablissementForm.setVilleEtab(etab.getVilleEtab());
			/****
			 * Fin des ajouts du 19-08-2018
			 */
			
		}
		else{
			/*//System.out.println("*********************  Aucun établissement n'est enregistré "
					+ "or un et un seul devrait l'etre des l'installation du système **********************");*/
		}
		
		return "admin/updateEtablissement";
	}
	
	@GetMapping(path="/getupdateEmblemeEtab")
	public String getupdateEmblemeEtab(@ModelAttribute("updateEmblemeEtabForm") 
			UpdateEmblemeEtabForm updateEmblemeEtabForm, 
			Model model, HttpServletRequest request) throws ParseException{
		
		/*
		 * Recuperer l'établissement enregistré dans la base de donnée
		 */
		Etablissement etab=adminService.getEtablissement();
		if(etab!=null){
			updateEmblemeEtabForm.setLogoEtab(etab.getLogoEtab());
			updateEmblemeEtabForm.setBanniereEtab(etab.getBanniereEtab());
			updateEmblemeEtabForm.setIdEtab(etab.getIdEtab());
		}
		
		return "admin/updateEmblemeEtab";
	}

	@GetMapping(path="/getupdateProviseur")
	public String getupdateProviseur(@ModelAttribute("updateProviseurForm") 
	UpdateProviseurForm updateProviseurForm, 
	Model model, HttpServletRequest request){
		
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
		
		return "admin/updateProviseur";
	}

	public void constructModelUpdateCycles(Model model){
		/*
		 * On doit récupérer la liste des cycles et placer dans le modèle puisqu'elle sera affiché
		 */
		List<Cycles> listofCycles=(ArrayList<Cycles>)adminService.findAllCycles();
		/*
		 * On stocke cette liste dans le modèle pour que la vue puisse l'afficher
		 */
		model.addAttribute("listofCycles", listofCycles);
		return;
	}
	
	
	@GetMapping(path="/getupdateCycles")
	public String getupdateCycles(@ModelAttribute("updateCyclesForm") 
	UpdateCyclesForm updateCyclesForm, Long idCycles,
	Model model, HttpServletRequest request){
		
		constructModelUpdateCycles(model);
		
		/*
		 * Action par défaut à réaliser
		 */
		updateCyclesForm.setEnregOrmodif("enreg");
		
		/*
		 * Si on n'a cliquer sur un bouton modifier alors le paramètre idCycles existe dans 
		 * la requete donc 
		 * On recherche le cycle a modifier et on met à jour le formulaire
		 */
		
		if(idCycles!=null){
			Cycles cycleAModif=adminService. getCyclesById(idCycles);
			updateCyclesForm.setCodeCycles(cycleAModif.getCodeCycles());
			updateCyclesForm.setCodeCycles_en(cycleAModif.getCodeCycles_en());
			updateCyclesForm.setNumeroOrdreCycles(cycleAModif.getNumeroOrdreCycles());
			updateCyclesForm.setEnregOrmodif("modif");

			updateCyclesForm.setCodeCyclesAModif(cycleAModif.getCodeCycles());
		}
		
		
		return "admin/updateCycles";
	}

	@GetMapping(path="/getsupprimerCycle")
	public String getsupprimerCycle(Long idCycles){
		int repServeur=adminService.deleteCycles(idCycles);
		if(repServeur==0){
			return "redirect:/logesco/admin/getupdateCycles?supprimCycleserror";
		}
		
		return "redirect:/logesco/admin/getupdateCycles";
	} 
	
	public void constructModelUpdateNiveaux(Model model){
		/*
		 * On doit faire la liste des niveaux car elle sera afficher dans la vue
		 * surtout pour le choix du niveau supérieur du niveau qu'on souhaite enregistrer
		 */
		List<Niveaux> listofNiveaux=(ArrayList<Niveaux>)adminService.findAllNiveaux();
		
		
		/*
		 * On stocke cette liste dans le modèle pour que la vue puisse l'afficher
		 */
		model.addAttribute("listofNiveaux", listofNiveaux);
		
		
		/*
		 * Il faut aussi la liste des cycles dans ce formulaire donc il faut faire cette liste et 
		 * placer dans le modèle
		 */
		List<Cycles> listofCycles=(ArrayList<Cycles>)adminService.findAllCycles();
		
		model.addAttribute("listofCycles", listofCycles);
		
	}
	
	@GetMapping(path="/getupdateNiveaux")
	public String getupdateNiveaux(@ModelAttribute("updateNiveauxForm") 
	UpdateNiveauxForm updateNiveauxForm, Long idNiveaux,
	Model model, HttpServletRequest request){
		
		constructModelUpdateNiveaux(model);
		
		/*
		 * Action par défaut à réaliser
		 */
		updateNiveauxForm.setEnregOrmodif("enreg");
		
		/*
		 * Si on n'a cliquer sur un bouton modifier alors le paramètre idNiveaux existe dans 
		 * la requete donc 
		 * On recherche le niveaux a modifier et on met à jour le formulaire
		 */
		
		if(idNiveaux!=null){
			Niveaux niveauAModif=adminService. getNiveauById(idNiveaux);
			
			updateNiveauxForm.setCodeCycles(niveauAModif.getCycle().getCodeCycles());
			updateNiveauxForm.setCodeNiveaux(niveauAModif.getCodeNiveaux());
			updateNiveauxForm.setCodeNiveaux_en(niveauAModif.getCodeNiveaux_en());
			/*
			 * Un niveau superieur peut etre null comme normalement avec la terminale
			 */
			if(niveauAModif.getNiveau()!=null){
				updateNiveauxForm.setCodeNiveauxSup(niveauAModif.getNiveau().getCodeNiveaux());
			}
			updateNiveauxForm.setNumeroOrdreNiveaux(niveauAModif.getNumeroOrdreNiveaux());
			updateNiveauxForm.setEnregOrmodif("modif");
			
			updateNiveauxForm.setCodeNiveauxAModif(niveauAModif.getCodeNiveaux());
		}
		
		
		return "admin/updateNiveaux";
	}
	
	
	@GetMapping(path="/getsupprimerNiveau")
	public String getsupprimerNiveau(Long idNiveaux){
		
		int repServeur=adminService.deleteNiveaux(idNiveaux);
		/*
		 * On ne peut pas supprimer un niveau qui est déja enregistré comme niveau superieure
		 * d'un autre niveau.
		 */
		if(repServeur==0){
			return "redirect:/logesco/admin/getupdateNiveaux?"
							+ "supprimNiveauxerrorNiveaux";
		}
		
		if(repServeur==-1){
			return "redirect:/logesco/admin/getupdateNiveaux?supprimNiveauxerrorClasses";
		}
		
		return "redirect:/logesco/admin/getupdateNiveaux";
	}
	
	public void constructModelUpdateSections(Model model){
		/*
		 * On doit faire la liste des sections car elle sera afficher dans la vue
		 */
		List<Sections> listofSections=adminService.findAllSections();
		/*
		 * On stocke cette liste dans le modèle pour que la vue puisse l'afficher
		 */
		model.addAttribute("listofSections", listofSections);
	}
	
	@GetMapping(path="/getupdateSections")
	public String getupdateSections(@ModelAttribute("updateSectionsForm") 
	UpdateSectionsForm updateSectionsForm, Long idSections,
	Model model, HttpServletRequest request){
		
		this.constructModelUpdateSections(model);
		
		/*
		 * Action par défaut à réaliser
		 */
		updateSectionsForm.setEnregOrmodif("enreg");
		
		/*
		 * Si on n'a cliquer sur un bouton modifier alors le paramètre idSections existe dans 
		 * la requete donc 
		 * On recherche la sections a modifier et on met à jour le formulaire
		 */
		
		if(idSections!=null){
			Sections sectionAModif=adminService. getSectionsById(idSections);
			
			updateSectionsForm.setCodeSections(sectionAModif.getCodeSections());
			updateSectionsForm.setCodeSections_en(sectionAModif.getCodeSections_en());
			updateSectionsForm.setIntituleSections(sectionAModif.getIntituleSections());
			updateSectionsForm.setIntituleSections_en(sectionAModif.getIntituleSections_en());
			updateSectionsForm.setEnregOrmodif("modif");
			
			updateSectionsForm.setCodeSectionsAModif(sectionAModif.getCodeSections());
		}
		
		return "admin/updateSections";
	}
	
	@GetMapping(path="/getsupprimerSection")
	public String getsupprimerSection(Long idSections){
		
		int repServeur=adminService.deleteSections(idSections);
		
		if(repServeur==0){
			return "redirect:/logesco/admin/getupdateSections?supprimSectionserror";
		}
		
		return "redirect:/logesco/admin/getupdateSections";
	}

	public void constructModelUpdateAnnee(Model model){
		/*
		 * On doit faire la liste des Années scolaire déjà enregistré et afficher
		 */
		List<Annee> listofAnnee=adminService.findAllAnnee();
		
		/*
		 * On stocke cette liste dans le modèle pour que la vue puisse l'afficher
		 */
		model.addAttribute("listofAnnee", listofAnnee);
	}
	
	@GetMapping(path="/getupdateAnnee")
	public String getupdateAnnee(@ModelAttribute("updatePeriodesAnForm") 
	UpdatePeriodesAnForm updatePeriodesAnForm, 
	Model model, HttpServletRequest request){
		
		this.constructModelUpdateAnnee(model);
		
		return "admin/updatePeriodesAn";
	}
	
	@GetMapping(path="/getsupprimerAnnee")
	public String getsupprimerAnnee(Long idPeriodes){
		int repServeur=adminService.deleteAnnee(idPeriodes);
		
		if(repServeur==0){
			return "redirect:/logesco/admin/getupdateAnnee?supprimAnneeerrorTrimestres";
		}
		
		return "redirect:/logesco/admin/getupdateAnnee?supprimanneesuccess";
	}

	public void constructModelUpdateTrimestres(Model model){
		/*
		 * On doit faire la liste des Années scolaire déjà enregistré 
		 * car il faut choisir dans quel année enregistrer le trimestre
		 */
		List<Annee> listofAnnee=adminService.findAllAnnee();
		
		/*
		 * On stocke cette liste dans le modèle pour que la vue puisse l'afficher
		 */
		model.addAttribute("listofAnnee", listofAnnee);
		
		/*
		 * On doit faire la liste de tous les trimestres enregistrés classés par
		 * ordre descendant des intitules de leurs années d'appartenance
		 */
		List<Trimestres> listofTrimestres=adminService.findAllTrimestres();
		
		model.addAttribute("listofTrimestres", listofTrimestres);
	}
	
	@GetMapping(path="/getupdateTrimestres")
	public String getupdateTrimestres(@ModelAttribute("updatePeriodesTrimForm") 
	UpdatePeriodesTrimForm updatePeriodesTrimForm, 
	Model model, HttpServletRequest request){
		
		this.constructModelUpdateTrimestres(model);
		
		return "admin/updatePeriodesTrim";
	}
	
	@GetMapping(path="/getsupprimerTrimestre")
	public String getsupprimerTrimestre(Long idPeriodes){
		
		int repServeur=adminService.deleteTrimestres(idPeriodes);
		if(repServeur==0){
			return "redirect:/logesco/admin/getupdateTrimestres?supprimTrimestreserrorSequences";
		}
		
		if(repServeur==-1){
			return "redirect:/logesco/admin/getupdateTrimestres?supprimTrimestreserrorNumTrim";
		}
		
		return "redirect:/logesco/admin/getupdateTrimestres";
		
	}
	
	public void constructModelUpdateSequences(Model model){
		/*
		 * On doit faire la liste des Années scolaire déjà enregistré 
		 * car il faut choisir dans quel année enregistrer le trimestre
		 */
		List<Annee> listofAnnee=adminService.findAllAnnee();
		/*
		 * On stocke cette liste dans le modèle pour que la vue puisse l'afficher
		 */
		model.addAttribute("listofAnnee", listofAnnee);
		
		/*
		 * On doit faire la liste de tous les trimestres enregistrés classés par
		 * ordre descendant des intitules de leurs années d'appartenance
		 */
		List<Trimestres> listofTrimestres=adminService.findAllTrimestres();
		
		model.addAttribute("listofTrimestres", listofTrimestres);
		
		/*
		 * On doit faire la liste de toutes les séquences enregistrées classées par
		 * ordre descendant des intitules de leurs années d'appartenance puis dans l'année
		 * par ordre croissant des numéroTrim et par ordre croissant des numéroSeq
		 */
		List<Sequences> listofSequences=adminService.findAllSequences();
		model.addAttribute("listofSequences", listofSequences);
	}
	
	@GetMapping(path="/getupdateSequences")
	public String getupdateSequences(@ModelAttribute("updatePeriodesSeqForm") 
	UpdatePeriodesSeqForm updatePeriodesSeqForm, 
	Model model, HttpServletRequest request){
		
		this.constructModelUpdateSequences(model);
		
		return "admin/updatePeriodesSeq";
	}
	
	@GetMapping(path="/getsupprimerSequence")
	public String getsupprimerSequence(Long idPeriodes){
		int repServeur=adminService.deleteSequences(idPeriodes);
		
		if(repServeur==0) return "redirect:/logesco/admin/getupdateSequences?"
				+ "supprimSeqerrorSeqActive";
		if(repServeur==-1) return "redirect:/logesco/admin/getupdateSequences?"
		+ "supprimSeqerrorSeqContentEval";
		if(repServeur==-2) return "redirect:/logesco/admin/getupdateSequences?"
		+ "supprimSeqerrorSeqContentRabs";
		if(repServeur==-3) return "redirect:/logesco/admin/getupdateSequences?"
		+ "supprimSeqerrorNumSeq";
		
		return "redirect:/logesco/admin/getupdateSequences";
	}
	
	public void constructModelUpdateSpecialites(Model model, int numPage, int taillePage){
		/*
		 * On doit faire la liste de toutes les spécialités existantes dans la base de donnée
		 * car cette liste doit être paginée et affiché dans la vue page par page.
		 * Cette liste doit donc être dans le modèle
		 */
		Page<Specialites> pageofSpecialites=
				adminService.findPageSpecialite(numPage, taillePage);
		
		if(pageofSpecialites.getContent().size()!=0){
			model.addAttribute("listofSpecialites", pageofSpecialites.getContent());
			int[] listofPagesSpecialites=new int[pageofSpecialites.getTotalPages()];
			model.addAttribute("listofPagesSpecialites", listofPagesSpecialites);
			model.addAttribute("pageCourante", numPage);
			/*//System.err.println("vvvvvvvvvvvvvvv  "+numPage);*/
		}
		
	}
	
	
	
	@GetMapping(path="/getupdateSpecialites")
	public String getupdateSpecialites(@ModelAttribute("updateSpecialitesForm") 
	UpdateSpecialitesForm updateSpecialitesForm, 
	Model model, HttpServletRequest request, 
	Long idSpecialite,
	@RequestParam(name="numPage", defaultValue="0") int numPage, 
	@RequestParam(name="taillePage", defaultValue="3") int taillePage){
		
		this.constructModelUpdateSpecialites(model, numPage, taillePage);
		
		/*
		 * Action par défaut à réaliser
		 */
		updateSpecialitesForm.setEnregOrmodif("enreg");
		
		/*
		 * Si on n'a cliquer sur un bouton modifier alors le paramètre idSpecialite existe dans 
		 * la requete donc 
		 * On recherche la specialite a modifier et on met à jour le formulaire
		 */
		
		if(idSpecialite!=null){
			Specialites speAModif=adminService.findSpecialites(idSpecialite);
			updateSpecialitesForm.setCodeSpecialite(speAModif.getCodeSpecialite());
			updateSpecialitesForm.setLibelleSpecialite(speAModif.getLibelleSpecialite());
			updateSpecialitesForm.setEnregOrmodif("modif");

			updateSpecialitesForm.setCodeSpeAModif(speAModif.getCodeSpecialite());
		}
		
		
		return "admin/updateSpecialites";
	}
	
	@GetMapping(path="/getsupprimerSpecialites")
	public String getsupprimerSpecialites(Long idSpecialite){
		int repServeur=adminService.deleteSpecialites(idSpecialite);
		if(repServeur==0) return "redirect:/logesco/admin/getupdateSpecialites?"
		+ "supprimSpecialiteerrorClasses";
		return "redirect:/logesco/admin/getupdateSpecialites";
	}
	
	public void constructModelUpdateClasses(Model model, int numPage, int taillePage){
		/*
		 * On doit faire la liste de toutes les classes existantes dans la base de donnée
		 * car cette liste doit être paginée et affiché dans la vue page par page.
		 * Cette liste doit donc être dans le modèle
		 * Elle doit être classer par ordre descendant des codeClasse, ascendant des numeros,
		 *  ascendant des codeSpecialite et ascendant des numero ordre des niveaux
		 */
		Page<Classes> pageofClasses=
				adminService.findPageClasse(numPage, taillePage);
		
		if(pageofClasses.getContent().size()!=0){
			model.addAttribute("listofClasses", pageofClasses.getContent());
			int[] listofPagesClasses=new int[pageofClasses.getTotalPages()];
			
			model.addAttribute("listofPagesClasses", listofPagesClasses);
			
			model.addAttribute("pageCourante", numPage);
			/*//System.err.println("numPageClasses  "+numPage);*/
		}
		
		/*
		 * Il faut chargé la liste des specialites dans le model car on doit affiche ca dans 
		 * le formulaire d'enreg de la classe
		 */
		List<Specialites> listofSpecialite=adminService.findAllSpecialites();
		model.addAttribute("listofSpecialite", listofSpecialite);
		
		/*
		 * Il faut chargé la liste des niveaux dans le model car on doit affiche ca dans 
		 * le formulaire d'enreg de la classe
		 */
		List<Niveaux> listofNiveau=adminService.findAllNiveaux();
		model.addAttribute("listofNiveau", listofNiveau);
		
		/*
		 * Il faut chargé la liste des sections dans le model car on doit affiche ca dans 
		 * le formulaire d'enreg de la classe
		 */
		List<Sections> listofsection=adminService.findAllSections();
		model.addAttribute("listofsection", listofsection);
		
		
	}
	
	@GetMapping(path="/getupdateClasses")
	public String getupdateClasses(@ModelAttribute("updateClassesForm") 
	UpdateClassesForm updateClassesForm, 
	Model model, HttpServletRequest request, 
	Long idClasses,
	@RequestParam(name="numPage", defaultValue="0") int numPage, 
	@RequestParam(name="taillePage", defaultValue="5") int taillePage){
		
		this.constructModelUpdateClasses(model, numPage, taillePage);
		
		/*
		 * Action par défaut à réaliser
		 */
		updateClassesForm.setEnregOrmodif("enreg");
		
		/*
		 * Si on n'a cliquer sur un bouton modifier alors le paramètre idClasses existe dans 
		 * la requete donc 
		 * On recherche la Classe à modifier et on met à jour le formulaire
		 */
		if(idClasses!=null){
			Classes classeAModif=adminService.findClasses(idClasses);
			
		/*	//System.err.println("Ici on veut modif classes  "+classeAModif.getCodeClasses());*/
			
			updateClassesForm.setCodeClasse(classeAModif.getCodeClasses());
			updateClassesForm.setCodeClasseAModif(classeAModif.getCodeClasses());
			updateClassesForm.setNumeroClasse(classeAModif.getNumeroClasses());
			updateClassesForm.setNumeroClasseAModif(classeAModif.getNumeroClasses());
			updateClassesForm.setNumeroOrdreNiveauClasse(
					classeAModif.getNiveau().getNumeroOrdreNiveaux());
			updateClassesForm.setCodeSectionClasse(
					classeAModif.getSection().getCodeSections());
			updateClassesForm.setCodeSpecialiteClasse(
					classeAModif.getSpecialite().getCodeSpecialite());
			updateClassesForm.setIdSpecialiteAModif(
					classeAModif.getSpecialite().getIdSpecialite());
			
			updateClassesForm.setEnregOrmodif("modif");
			
			
		}
		
		return "admin/updateClasses";
	}
	
	@GetMapping(path="/getsupprimerClasses")
	public String getsupprimerClasses(Long idClasses){
		int repServeur=adminService.deleteClasses(idClasses);
		if(repServeur==0) return "redirect:/logesco/admin/getupdateClasses?"
		+ "supprimClasseerrorEleves";
		if(repServeur==-1) return "redirect:/logesco/admin/getupdateClasses?"
		+ "supprimClasseerrorCours";
		return "redirect:/logesco/admin/getupdateClasses";
	}
	
	public void constructModelUpdateMatiere(Model model,	HttpServletRequest request,	
			UpdateMatieresForm	updateMatieresForm,	Long idMatiere, 
			int numPageMatiere,	int taillePage){
		
		HttpSession session = request.getSession();
		/*
		 * On charge la liste des matieres dans le model
		 */
		Page<Matieres> pageofMatieres = adminService.findAllMatieres(numPageMatiere, taillePage);
		if(pageofMatieres != null){
			if(pageofMatieres.getContent().size()!=0){
				model.addAttribute("listofMatieres", pageofMatieres.getContent());
				int[] listofPagesMatieres=new int[pageofMatieres.getTotalPages()];
				
				model.addAttribute("listofPagesMatieres", listofPagesMatieres);
				
				model.addAttribute("pageCouranteMatiere", numPageMatiere);
				/*//System.err.println("numPageMatiere  "+numPageMatiere);*/
				session.setAttribute("numPageMatiere", numPageMatiere);
			}
		}
		/*
		 * On doit rechercher la matière dont le id est passe en paramètre au cas où ca existe
		 */
		
		Matieres matiereConcerne = adminService.findMatieres(idMatiere);
		if(matiereConcerne!=null){
			updateMatieresForm.setCodeMatiere(matiereConcerne.getCodeMatiere());
			updateMatieresForm.setIdMatiere(matiereConcerne.getIdMatiere());
			updateMatieresForm.setIntituleMatiere(matiereConcerne.getIntituleMatiere());
			updateMatieresForm.setIntitule2langueMatiere(matiereConcerne.getIntitule2langueMatiere());
			
			model.addAttribute("matiereConcerne", matiereConcerne);
			session.setAttribute("matiereConcerne", matiereConcerne);
		}
		else{
			//Car l'idMatiere ne doit pas être null lors de l'appel à la methode updateMatiere au niveau du metier 
			updateMatieresForm.setIdMatiere(new Long(0));
			/*
			 * Lors de l'affichage des numero de page on passe aussi dans la requete idmatiere de l'objet matiereConcerne
			 * qui est d'ailleurs le même que celui de updateMatieresForm
			 */
		}
		
	}
	
	
	@GetMapping(path="/getupdateMatieres")
	public String getupdateMatieres(@ModelAttribute("updateMatieresForm") 
	UpdateMatieresForm updateMatieresForm,  
	@RequestParam(name="idMatiere", defaultValue="0") Long idMatiere, 
	@RequestParam(name="numPageMatiere", defaultValue="0") int numPageMatiere,
	@RequestParam(name="taillePage", defaultValue="5") int taillePage,
	Model model, HttpServletRequest request){
		
		this.constructModelUpdateMatiere(model,	request,	updateMatieresForm,	idMatiere, 
				numPageMatiere,	taillePage);
		
		return "admin/updateMatieres";
	}
	
	
	
	
	
	
	
	@SuppressWarnings("unused")
	public void constructModelUpdateRoles(Model model, int numPage, int taillePage){
		/*
		 * On doit faire la liste des roles existant et affiché dans la vue et pour chaque role, 
		 * on doit afficher la liste des usernames de tous les utilisateurs qui le possède
		 */
		Page<Roles> pageofRoles=
				adminService.findPageRole(numPage, taillePage);
		
		if(pageofRoles.getContent().size()!=0){
			model.addAttribute("listofRoles", pageofRoles.getContent());
			int[] listofPagesRoles=new int[pageofRoles.getTotalPages()];
			
			model.addAttribute("listofPagesRoles", listofPagesRoles);
			
			model.addAttribute("pageCourante", numPage);
			/*//System.err.println("numPageRoles  "+numPage);*/
		}
		
		/*
		 * On doit faire la liste des usersroles existant et affiché dans la vue et pour chaque role, 
		 * on doit afficher la liste des usernames de tous les utilisateurs qui le possède
		 */
		List<UtilisateursRoles> listofUsersRoles=adminService.findAllUsersRoles();
		model.addAttribute("listofUsersRoles", listofUsersRoles);  
		/*//System.err.println("taille de listofUsersRoles "+listofUsersRoles.size());*/
		for(UtilisateursRoles ur:listofUsersRoles){
			/*//System.err.println("users = "+ur.getUsers().getUsername());*/
		}
		
	}
	
	@GetMapping(path="/getupdateRoles")
	public String getupdateRoles(@ModelAttribute("updateRolesForm") 
	UpdateRolesForm updateRolesForm, 
	Model model, HttpServletRequest request, 
	String role,
	@RequestParam(name="numPage", defaultValue="0") int numPage, 
	@RequestParam(name="taillePage", defaultValue="5") int taillePage){
		
		this.constructModelUpdateRoles(model, numPage, taillePage);
		
		/*
		 * Action par défaut à réaliser
		 */
		updateRolesForm.setEnregOrmodif("enreg");
		
		/*
		 * Si on n'a cliquer sur un bouton modifier alors le paramètre role existe dans 
		 * la requete donc 
		 * On recherche le role à modifier et on met à jour le formulaire
		 */
		if(role!=null){
			Roles roleAModif=adminService.findRoles(role);
			
			/*//System.err.println("Ici on veut modif roles  "+roleAModif.getAliasRole());*/
			
			updateRolesForm.setAliasRole(roleAModif.getAliasRole());
			updateRolesForm.setRole(roleAModif.getRole());
			updateRolesForm.setRoleAModif(roleAModif.getRole());
			
			updateRolesForm.setEnregOrmodif("modif");
			
		}
		
		return "admin/updateRoles";
	}
	
	
	/*****
	 * Requetes get pour l'exportation des rapports
	 */
	
	@GetMapping(path="/exportlistSpecialites", produces = "application/pdf")
	public ModelAndView exportlistSpecialites(HttpServletRequest request,  HttpServletResponse response){
		
		/*JasperReportsPdfView view = new JasperReportsPdfView();
		
		view.setReportDataKey("datasource");
		view.setUrl("classpath:/reports/listSpecialites.jrxml");
		view.setApplicationContext(applicationContext);
		Map<String, Object> params = new HashMap<String, Object>();
		//System.err.println("/////////////// ici deja ///////////");
		params.put("datasource", adminServiceExport.exportlisteSpecialite());
		//System.err.println("/////////////// PUIS LA ///////////");
		return new ModelAndView(view, params);*/
		
		/*
		 * Ici on peut donc faire les appels au serveur qui donne la liste des specialités avec toutes les données
		 * qui doivent être affiché sur cette liste. On place ces données dans un objet et on transmet cet objet
		 * dans une cle. La vue aura pour rôle de recuperer cet objet et d'en exploiter les données pour construire
		 * le pdf voulu
		 */
		
		Map<String, Object> datasource = new HashMap<String, Object>();
		datasource.put("col1", "ID");
		datasource.put("col2", "My First Name");
		datasource.put("col3", "Our First Name");
		
		List<Map<String, Object>> datasource1 = new ArrayList<Map<String, Object>>();
		datasource1.size();
		datasource1 = adminServiceExport.exportlisteSpecialite();
		
		/********************
		 * org.springframework.web.servlet.ModelAndView.ModelAndView(View view, String modelName, Object modelObject)
		 */
		
		return new ModelAndView(new ExportUserListReportView(),"datasource",datasource);
		
	}
	
	
	
	
	
	
	//fin des requetes GET/////////////////////fin des requetes GET////////////fin des requetes GET

	//les requetes POST/////////////////les requetes POST/////////////////////les requetes POST
	/**************************************************************
	 * Traitement du formulaire de modification de mot de passe
	 *************************************************************/
	@PostMapping(path="/updatePassword")
	public String updatePassword(@Valid ModifpasswordForm modifpasswordForm, 
			BindingResult bindingResult, Model model, 
			HttpServletRequest request, HttpServletResponse response) {



		if (bindingResult.hasErrors()) {
			return "admin/indexAdmin";
		}

		/*
		 * Suite d'instruction permettant de modifier le mot de passe
		 */
		int reponse=adminService.updatePassword(modifpasswordForm.getPasswordCourant(), 
				modifpasswordForm.getNewPassword(), 
				modifpasswordForm.getNewPasswordConfirm(), 
				modifpasswordForm.getUsername());

		if(reponse == -2) {
			//username invalide

			return "redirect:/logesco/admin/index?usernameerreur";
		}
		if(reponse == -1) {
			return "redirect:/logesco/admin/index?passwordnotconfirm";
		}
		if(reponse == 0) {
			return "redirect:/logesco/admin/index?activepassworderror";
		}

		/*
		 * On deconnecte proprement l'utilisateur pour qu'il se reconnecte avec son 
		 * nouveau mot de passe. Pour cela on recupere son authentification qui 
		 * normalement ne doit pas être null et là on peut grace a logout le 
		 * deconnecter proprement
		 */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login?updatepassword";

	}

	/**************************************************************
	 * Traitement du formulaire de modification de l'adresse de l'admin
	 *************************************************************/
	@PostMapping(path="/postupdateAdresse")
	public String postupdateAdresse(@Valid @ModelAttribute("modifadressForm") 
	ModifAdressForm modifadressForm, 
	BindingResult bindingResult, Model model) throws ParseException{

		if (bindingResult.hasErrors()) {
			return "admin/updateAdresse";
		}
		//On a les paramètres de la modification ici donc il faut faire appel au service
		int reponse=adminService.updateAdminUsers(modifadressForm.getUsername(), 
				modifadressForm.getEmailAdmin(), modifadressForm.getNumtel1Admin(), 
				modifadressForm.getNumtel2Admin());
		
		if(reponse==1){
			return "redirect:/logesco/admin/getupdateAdresse?updatesuccess";
		}

		return "admin/updateAdresse";
	}
	
	@PostMapping(path="/postresetPassword")
	public String postresetPassword(@Valid @ModelAttribute("passwordResetForm") 
	PasswordResetForm passwordResetForm, 
	BindingResult bindingResult, Model model) throws ParseException{
		
		if (bindingResult.hasErrors()) {
			return "admin/resetPassword";
		}
		//On peut effectuer la reinitialisation du mot de passe
		String newPassword = passwordResetForm.getNewPassword();
		String newPasswordConfirm = passwordResetForm.getNewPasswordConfirm();
		String username = passwordResetForm.getUsername();
		int reponse = adminService.resetPassword(newPassword, 
				newPasswordConfirm, username);
		if(reponse==1){
			return "redirect:/logesco/admin/getresetPassword?updatesuccess";
		}
		if(reponse==0){
			return "redirect:/logesco/admin/getresetPassword?updateerror";
		}
		return "admin/resetPassword";
	}

	/**************************************************************
	 * Traitement du formulaire de modification d'un nom d'utilisateur
	 *************************************************************/
	@PostMapping(path="/postupdateUsername")
	public String postupdateUsername(@Valid @ModelAttribute("modifusernameForm") 
	ModifUsernameForm modifusernameForm, 
	BindingResult bindingResult, Model model, 
	HttpServletRequest request, HttpServletResponse response) throws ParseException{
		
		if (bindingResult.hasErrors()) {
			return "admin/updateUsername";
		}
		//On a les paramètres de la modification ici donc il faut faire appel au service
		int reponseService=adminService.updateUsername(modifusernameForm.getActiveUsername(), 
				modifusernameForm.getNewUsername());
		if(reponseService==1){
			if(modifusernameForm.getActiveUsername().equals(
					(String)request.getSession().getAttribute("username"))==true){
				/*
				 * Dans ce cas c'est l'admin connecte qui vient de changer son username
				 * il faut modifier son username dans la session
				 */
				request.getSession().setAttribute("username", modifusernameForm.getNewUsername());

			}
			
			return "redirect:/logesco/admin/getupdateUsername?updateusernamesuccess";
			
		}
		
		return "redirect:/logesco/admin/getupdateUsername?updateusernameerror";
	}
	
	
	/****************************************************************
	 * Traitement du formulaire de mise à jour des données de l'établissement
	 *****************************************************************/
	
	@SuppressWarnings("unused")
	@PostMapping(path="/postupdateEtablissement")
	public String postupdateEtablissement(@Valid @ModelAttribute("updateEtablissementForm") 
			UpdateEtablissementForm updateEtablissementForm, 
			BindingResult bindingResult, Model model, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ParseException, Exception, IOException{
		
		//System.err.println("ICI AU DEBUT DE postupdateEtablissement");
		
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE");
			//System.out.println("les erreurs sont "+bindingResult.getFieldErrors().toString());
			//System.out.println("les erreurs sont "+bindingResult.getErrorCount());
			//System.out.println("les erreurs sont "+bindingResult.getFieldError().getDefaultMessage());
			//System.out.println("les erreurs sont "+bindingResult.getFieldError().getField());
			return "admin/updateEtablissement";
		}
		/*
		 * Tout est bien rempli dans le formulaire et on peut donc enregistrer l'etablissement
		 */
		/*//System.err.println("AUCUNE ERREUR DANS LE FORMULAIRE "
				+ "ON INSTANCIE ETABLISSEMENT");*/
		Etablissement etab=new Etablissement();
		etab.setAliasEtab(updateEtablissementForm.getAliasEtab());
		etab.setBpEtab(updateEtablissementForm.getBpEtab());
		etab.setDeviseEtab(updateEtablissementForm.getDeviseEtab());
		etab.setEmailEtab(updateEtablissementForm.getEmailEtab());
		etab.setMatriculeEtab(updateEtablissementForm.getMatriculeEtab());
		etab.setMinisteretuteleEtab(updateEtablissementForm.getMinisteretuteleEtab());
		etab.setNomsEtab(updateEtablissementForm.getNomsEtab());
		etab.setNumtel1Etab(updateEtablissementForm.getNumtel1Etab());
		etab.setNumtel2Etab(updateEtablissementForm.getNumtel2Etab());
		/***
		 * Debut des ajouts du 19-08-2018
		 */
		etab.setAliasnomanglaisEtab(updateEtablissementForm.getAliasnomanglaisEtab());
		etab.setAliasministeretuteleEtab(updateEtablissementForm.getAliasministeretuteleEtab());
		etab.setAliasministeretuteleanglaisEtab(updateEtablissementForm.getAliasministeretuteleanglaisEtab());
		etab.setDeviseanglaisEtab(updateEtablissementForm.getDeviseanglaisEtab());
		etab.setMinisteretuteleanglaisEtab(updateEtablissementForm.getMinisteretuteleanglaisEtab());
		etab.setNomsanglaisEtab(updateEtablissementForm.getNomsanglaisEtab());
		
		etab.setDeleguationdeptuteleanglaisEtab(updateEtablissementForm.getDeleguationdeptuteleanglaisEtab());
		etab.setDeleguationdeptuteleEtab(updateEtablissementForm.getDeleguationdeptuteleEtab());
		etab.setDeleguationregtuteleanglaisEtab(updateEtablissementForm.getDeleguationregtuteleanglaisEtab());
		etab.setDeleguationregtuteleEtab(updateEtablissementForm.getDeleguationregtuteleEtab());
		
		etab.setCodeMatriculeEtab(updateEtablissementForm.getCodeMatriculeEtab());
		etab.setVilleEtab(updateEtablissementForm.getVilleEtab());
		
		Long idEtab=adminService.saveEtablissement(etab);
		
		//System.out.println("**************  etablissement mis a jour  *********************"+idEtab);
		
		return "redirect:/logesco/admin/getupdateEtablissement?updateetabsuccess";
	}
	
	/******************************************************************
	 * Traitement du formulaire de mise à jour ou d'enregistrement des emblèmes
	 * de l'établissement
	 ******************************************************************/
	@PostMapping(path="/postupdateEmblemeEtab")
	public String postupdateEmblemeEtab(@Valid @ModelAttribute("updateEmblemeEtabForm") 
	UpdateEmblemeEtabForm updateEmblemeEtabForm, 
	BindingResult bindingResult, Model model, 
	@RequestParam(name="picturelogoEtab")MultipartFile filelogoEtab, 
	@RequestParam(name="picturebanniereEtab")MultipartFile filebanniereEtab,
	HttpServletRequest request, HttpServletResponse response) 
			throws ParseException, Exception, IOException{
		
		//System.err.println("ICI AU DEBUT DE postupdateEtablissement");
		
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE");
			return "admin/updateEmblemeEtab";
		}
		
		Etablissement etabExistant=adminService.getEtablissement();
		if(etabExistant!=null){
			if(!(filelogoEtab.isEmpty())){
				etabExistant.setLogoEtab(filelogoEtab.getOriginalFilename());
			}
			
			if(!(filebanniereEtab.isEmpty())){
				etabExistant.setBanniereEtab(filebanniereEtab.getOriginalFilename());
			}
			
			Long idEtab=adminService.saveEtablissement(etabExistant);
			
			//System.err.println("ON FAIT MAINTENANT L'UPLOAD DU LOGO");
			if(!(filelogoEtab.isEmpty())){
				filelogoEtab.transferTo(new File(logoDir+idEtab.longValue()));
			}
			
			//System.err.println("ON FAIT MAINTENANT L'UPLOAD DE LA BANNIERE");
			if(!(filebanniereEtab.isEmpty())){
				filebanniereEtab.transferTo(new File(banniereDir+idEtab.longValue()));
			}
			
		}
		
		return "redirect:/logesco/admin/getupdateEmblemeEtab?updateemblemeetabsuccess";
	}
	
	
	/****************************************************************
	 * Traitement de la requete de recherche de la banniere de l'etablissement
	 *****************************************************************/
	@RequestMapping(path="/getBanniere", produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getBaniereEtab(Long idEtab){
		File f=new File(banniereDir+idEtab);
		try{
			//System.err.println("nous voici ici et chemin fichier est ="+banniereDir+idEtab);
			return IOUtils.toByteArray(new FileInputStream(f));
		}
		catch(Exception e){
			//System.err.println("erreur sur idetab "+e.getMessage());
			return null;
		}
	}
	
	/**************************************************************
	 * Traitement de la requete de recherche du logo de l'établissement
	 *************************************************************/
	@RequestMapping(path="/getLogo", produces=MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] getLogoEtab(Long idEtab){
		File f=new File(logoDir+idEtab);
		try{
			//System.err.println("nous voici ici et chemin fichier est ="+logoDir+idEtab);
			return IOUtils.toByteArray(new FileInputStream(f));
		}
		catch(Exception e){
			//System.err.println("erreur sur idetab "+e.getMessage());
			return null;
		}
	}
	
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
		
		//System.err.println("ICI AU DEBUT DE postupdateProviseur");
		
		if (bindingResult.hasErrors()) {
			return "admin/updateProviseur";
		}
		/*
		 * Tout est bien rempli dans le formulaire et on peut donc enregistrer l'etablissement
		 */
		/*//System.err.println("AUCUNE ERREUR DANS LE FORMULAIRE "
				+ "ON INSTANCIE ET INITIALISE Proviseur");*/
		
		Proviseur proviseur=new Proviseur();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String ds=df.format(updateProviseurForm.getDatenaissPers());
		Date d=df.parse(ds);
		proviseur.setDatenaissPers(d);
		/*//System.err.println("AUCUNE ERREUR DANS LE FORMULAIRE "
				+ "VOICI SA DATE DE NAISSANCE  "+d);*/
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
		
		//System.err.println("TOUJOURS PAS DERREUR ON SETPHOTOPERS");
		if(!(filephotoPers.isEmpty())){
			proviseur.setPhotoPers(filephotoPers.getOriginalFilename());
		}
		
		//System.err.println("TOUJOURS PAS DERREUR ON SAVE PROVISEUR");
		
		Long idProviseur=adminService.saveProviseur(proviseur);
		
		if(idProviseur.longValue()==-1) return "redirect:/logesco/admin/getupdateProviseur?updateproviseurerrorNumerocni";
		
		if(idProviseur.longValue()==-2) return "redirect:/logesco/admin/getupdateProviseur?"
				+ "updateproviseurerrorNomsPrenomsDatenaiss";
		
		if(idProviseur.longValue()==-3) return "redirect:/logesco/admin/getupdateProviseur?updateproviseurerrorUsername";
		
		//System.err.println("TOUJOURS PAS DERREUR ON UPLOAD PHOTOPROVISEUR");
		if(!(filephotoPers.isEmpty())){
			filephotoPers.transferTo(new File(photoPersonnelsDir+idProviseur.longValue()));
		}
		
		
		return "redirect:/logesco/admin/getupdateProviseur?updateproviseursuccess";
	}
	
	/**************************************************************
	 * Traitement de la requete de recherche de la photo d'un utilisateur
	 *************************************************************/
	@RequestMapping(path="/getphotoPers", produces=MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] getphotoPers(Long idPers){
		//System.err.println("DEPART DE LA RECHERCHE DE LA PHOTO  "+idPers);
		File f=new File(photoPersonnelsDir+idPers);
		try{
			//System.err.println("nous voici ici et chemin fichier est ="+photoPersonnelsDir+idPers);
			return IOUtils.toByteArray(new FileInputStream(f));
		}
		catch(Exception e){
			//System.err.println("erreur sur idetab "+e.getMessage());
			return null;
		}
	}
	
	/**************************************************************
	 * Traitement du formulaire d'enregistrement/modification des cycles
	 *************************************************************/
	@PostMapping(path="/postupdateCycles")
	public String postupdateCycles(@Valid @ModelAttribute("updateCyclesForm") 
			UpdateCyclesForm updateCyclesForm, 
			BindingResult bindingResult, Model model, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ParseException, Exception{
		//System.err.println("DEBUT DE POSTUPDATECYCLES");
		
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE "+bindingResult.getFieldError());
			
			this.constructModelUpdateCycles(model);
			
			return "admin/updateCycles";
		}

		if(updateCyclesForm.getEnregOrmodif().equals("enreg")){
		Cycles cycle=new Cycles();
		cycle.setCodeCycles(updateCyclesForm.getCodeCycles());
		cycle.setNumeroOrdreCycles(updateCyclesForm.getNumeroOrdreCycles());
		cycle.setCodeCycles_en(updateCyclesForm.getCodeCycles_en());
		//System.err.println("AUCUNE ERREUR ET ON A LE CYCLE A ENREGISTRER");
		int reponseServeur=adminService.saveCycles(cycle);
		
		if(reponseServeur==0) 
			return "redirect:/logesco/admin/getupdateCycles?updatecycleserrorNumeroOrdre";
		if(reponseServeur==-1) 
			return "redirect:/logesco/admin/getupdateCycles?updatecycleserrorCode";
		
			//System.err.println("ON A ENREGISTRER LE CYCLE ET IL FAUT DONC RETOURNER");
			
			
		}
		else if(updateCyclesForm.getEnregOrmodif().equals("modif")){

			//System.err.println("FAUT DONC EFFECTUER LA MODIFICATION du cycle");
			//System.err.println(updateCyclesForm.getCodeCycles());
			//System.err.println(updateCyclesForm.getCodeCycles_en());
			
			Cycles cycleModif=new Cycles();
			cycleModif.setCodeCycles(updateCyclesForm.getCodeCycles());
			cycleModif.setCodeCycles_en(updateCyclesForm.getCodeCycles_en());
			cycleModif.setNumeroOrdreCycles(updateCyclesForm.getNumeroOrdreCycles());
			
			int repServeur=adminService.updateCycles(
					updateCyclesForm.getCodeCyclesAModif(), cycleModif);
			
			if(repServeur==0) 
				return "redirect:/logesco/admin/getupdateCycles?updatecycleserrormodif";
			if(repServeur==-1) 
				return "redirect:/logesco/admin/getupdateCycles?updatecycleserrorCode";
		
		}
		return "redirect:/logesco/admin/getupdateCycles?updatecyclessuccess";
	}
	
	/**************************************************************
	 * Traitement du formulaire d'enregistrement/modification des niveaux
	 *************************************************************/
	@PostMapping(path="/postupdateNiveaux")
	public String postupdateNiveaux(@Valid @ModelAttribute("updateNiveauxForm") 
			UpdateNiveauxForm updateNiveauxForm, 
			BindingResult bindingResult, Model model, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ParseException, Exception{
		//System.err.println("DEBUT DE POSTUPDATENIVEAU");
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE "+bindingResult.getFieldError());
			
			constructModelUpdateNiveaux(model);
			return "admin/updateNiveaux";
		}
		
		if(updateNiveauxForm.getEnregOrmodif().equals("enreg")){
			Niveaux niveau=new Niveaux();
			niveau.setCodeNiveaux(updateNiveauxForm.getCodeNiveaux());
			niveau.setNumeroOrdreNiveaux(updateNiveauxForm.getNumeroOrdreNiveaux());
			//System.err.println("RECUPERATION DU CYCLE SAISI POUR LE NIVEAU");
			Cycles cycle=adminService.getCyclesByCodeCycles(updateNiveauxForm.getCodeCycles());
			if(cycle!=null) niveau.setCycle(cycle);
			
			Niveaux niveauSup=adminService.getNiveauxByCodeNiveaux(
						updateNiveauxForm.getCodeNiveauxSup());
			
			if(niveauSup!=null)/*//System.err.println("NIVEAUX SUP TROUVE EST "+
					niveauSup.toString()+" POUR LE CODE "+
					updateNiveauxForm.getCodeNiveauxSup());*/
			
			niveau.setNiveau(niveauSup);
				
			//System.err.println("AUCUNE ERREUR JUSQUICI ON PEUT DONC SAVE LE NIVEAUX");
			int reponseSaveNiveau=4;
			reponseSaveNiveau=adminService.saveNiveaux(niveau);
			
			if(reponseSaveNiveau==0) return "redirect:/logesco/admin/getupdateNiveaux?"
					+ "updateniveauxerrorNumeroOrdre";
			if(reponseSaveNiveau==-1) return "redirect:/logesco/admin/getupdateNiveaux?"
					+ "updateniveauxerrorCode";
			//System.err.println("ON A ENREGISTRER LE niveau ET IL FAUT DONC RETOURNER");
		}
		
		else if(updateNiveauxForm.getEnregOrmodif().equals("modif")){

			//System.err.println("FAUT DONC EFFECTUER LA MODIFICATION du niveau");
			//System.err.println(updateNiveauxForm.getCodeNiveaux());
			//System.err.println(updateNiveauxForm.getCodeNiveaux_en());
			
			Niveaux niveauModif=new Niveaux();
			niveauModif.setCodeNiveaux(updateNiveauxForm.getCodeNiveaux());
			niveauModif.setCodeNiveaux_en(updateNiveauxForm.getCodeNiveaux_en());
			niveauModif.setCycle(adminService.findCycles(updateNiveauxForm.getCodeCycles()));
			niveauModif.setNiveau(adminService.findNiveau(updateNiveauxForm.getCodeNiveauxSup()));
			niveauModif.setNumeroOrdreNiveaux(updateNiveauxForm.getNumeroOrdreNiveaux());
			
			int repServeur=adminService.updateNiveaux(
					updateNiveauxForm.getCodeNiveauxAModif(), niveauModif);
			
			if(repServeur==0) 
				return "redirect:/logesco/admin/getupdateNiveaux?updateniveauxerrormodif";
			if(repServeur==-1) 
				return "redirect:/logesco/admin/getupdateNiveaux?updateniveauxerrorCode";
		
		}
		
		
		return "redirect:/logesco/admin/getupdateNiveaux?updateniveauxsuccess";
	}
	
	/**************************************************************
	 * Traitement du formulaire d'enregistrement/modification des sections
	 *************************************************************/
	@PostMapping(path="/postupdateSections")
	public String postupdateSections(@Valid @ModelAttribute("updateSectionsForm") 
			UpdateSectionsForm updateSectionsForm, 
			BindingResult bindingResult, Model model, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ParseException, Exception{
		//System.err.println("DEBUT DE POSTUPDATESECTION");
		
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE "+bindingResult.getFieldError());
			
			this.constructModelUpdateSections(model);
			return "admin/updateSections";
		}
		
		if(updateSectionsForm.getEnregOrmodif().equals("enreg")){
			Sections sections=new Sections();
			sections.setCodeSections(updateSectionsForm.getCodeSections());
			sections.setIntituleSections(updateSectionsForm.getIntituleSections());
			
			int reponseSaveSection=4;
			reponseSaveSection=adminService.saveSections(sections);
			if(reponseSaveSection==0) return "redirect:/logesco/admin/getupdateSections?"
					+ "updatesectionserrorCode";
			
			//System.err.println("ON A ENREGISTRER la section ET IL FAUT DONC RETOURNER");
		}
		else if(updateSectionsForm.getEnregOrmodif().equals("modif")){

			//System.err.println("FAUT DONC EFFECTUER LA MODIFICATION de la section");
			//System.err.println(updateSectionsForm.getCodeSections());
			//System.err.println(updateSectionsForm.getCodeSections_en());
			
			Sections sectionModif=new Sections();
			sectionModif.setCodeSections(updateSectionsForm.getCodeSections());
			sectionModif.setCodeSections_en(updateSectionsForm.getCodeSections_en());
			sectionModif.setIntituleSections(updateSectionsForm.getIntituleSections());
			sectionModif.setIntituleSections_en(updateSectionsForm.getIntituleSections_en());
			
			int repServeur=adminService.updateSections(
					updateSectionsForm.getCodeSectionsAModif(), sectionModif);
			
			if(repServeur==0) 
				return "redirect:/logesco/admin/getupdateSections?updatesectionserrormodif";
			if(repServeur==-1) 
				return "redirect:/logesco/admin/getupdateSections?updatesectionserrorCode";
		
		}
		
		return "redirect:/logesco/admin/getupdateSections?updatesectionssuccess";
	}
	
	/*******************************************************************
	 * Traitement du formulaire d'enregistrement/modification des annees scolaires
	 *******************************************************************/
	@PostMapping(path="/postupdatePeriodesAn")
	public String postupdatePeriodesAn(@Valid @ModelAttribute("updatePeriodesAnForm") 
			UpdatePeriodesAnForm updatePeriodesAnForm, 
			BindingResult bindingResult, Model model, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ParseException, Exception{
		//System.err.println("DEBUT DE POSTUPDATEPERIODEAN");
		
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE");
			this.constructModelUpdateAnnee(model);
			return "admin/updatePeriodesAn";
		}
		
		Annee annee=new Annee();
		annee.setDatedebutPeriodes(updatePeriodesAnForm.getDatedebutPeriodes());
		annee.setDatefinPeriodes(updatePeriodesAnForm.getDatefinPeriodes());
		annee.setIntituleAnnee(updatePeriodesAnForm.getIntituleAnnee());
		annee.setEtatPeriodes(true);
		
		
		
		int reponseSaveAnnee=4; 
		reponseSaveAnnee=adminService.saveAnnee(annee);
		if(reponseSaveAnnee==0) return "redirect:/logesco/admin/getupdateAnnee?"
				+ "updateperiodesanserrorIntitule";
		if(reponseSaveAnnee==-1) return "redirect:/logesco/admin/getupdateAnnee?"
		+ "updateperiodesanserrorDate";
		
		return "redirect:/logesco/admin/getupdateAnnee?updateperiodesanssuccess";
		
	}
	
	/*******************************************************************
	 * Traitement du formulaire d'enregistrement/modification des trimestres
	 *******************************************************************/
	@PostMapping(path="/postupdatePeriodesTrim")
	public String postupdatePeriodesTrim(@Valid @ModelAttribute("updatePeriodesTrimForm") 
			UpdatePeriodesTrimForm updatePeriodesTrimForm, 
			BindingResult bindingResult, Model model, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ParseException, Exception{
		//System.err.println("DEBUT DE POSTUPDATEPERIODETRIM");
		
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE");
			this.constructModelUpdateTrimestres(model);
			return "admin/updatePeriodesTrim";
		}
		
		Trimestres trimestre=new Trimestres();
		/*
		 * Recuperer l'annee choisi pour le trimestre qu'on veut enregistrer
		 */
		Annee anneeAssocie=adminService.getAnneeIntituleAnnee(
				updatePeriodesTrimForm.getIntituleAnneeTrim());
		
		//System.err.println("ANNEE DAPARTENANCE RECUPERER NORMALEMENT");
		
		trimestre.setAnnee(anneeAssocie);
		trimestre.setDatedebutPeriodes(updatePeriodesTrimForm.getDatedebutPeriodes());
		trimestre.setDatefinPeriodes(updatePeriodesTrimForm.getDatefinPeriodes());
		trimestre.setEtatPeriodes(true);
		trimestre.setNumeroTrim(updatePeriodesTrimForm.getNumeroTrim());
		
		//System.err.println("TRIMESTRE A ENREGISTRER INSTANCIER");
		
		int reponseSaveTrimestre=4;
		reponseSaveTrimestre=adminService.saveTrimestres(trimestre);
		
		//System.err.println("APPEL ENREG METHODE EFFECTUE: REP= "+reponseSaveTrimestre);
		
		if(reponseSaveTrimestre==0)
			return "redirect:/logesco/admin/getupdateTrimestres?"
					+ "updateperiodestrimerrorTrimestreAnnee";
		
		if(reponseSaveTrimestre==-1)
			return "redirect:/logesco/admin/getupdateTrimestres?"
					+ "updateperiodestrimerrorNumeroTrimAnnee";
		
		if(reponseSaveTrimestre==-2)
			return "redirect:/logesco/admin/getupdateTrimestres?"
					+ "updateperiodestrimerrorDateTrim1Annee";
		
		if(reponseSaveTrimestre==-3)
			return "redirect:/logesco/admin/getupdateTrimestres?"
					+ "updateperiodestrimerrorTrim1AnneeNotExist";
		
		if(reponseSaveTrimestre==-4)
			return "redirect:/logesco/admin/getupdateTrimestres?"
					+ "updateperiodestrimerrorDateTrim2Annee";
		
		if(reponseSaveTrimestre==-5)
			return "redirect:/logesco/admin/getupdateTrimestres?"
					+ "updateperiodestrimerrorTrim2AnneeNotExist";
		
		if(reponseSaveTrimestre==-6)
			return "redirect:/logesco/admin/getupdateTrimestres?"
					+ "updateperiodestrimerrorDateTrim3Annee";
		
		if(reponseSaveTrimestre==-7)
			return "redirect:/logesco/admin/getupdateTrimestres?"
					+ "updateperiodestrimerrorDate";
		
		
		return "redirect:/logesco/admin/getupdateTrimestres?updateperiodestrimsuccess";
	}
	
	
	/*******************************************************************
	 * Traitement du formulaire d'enregistrement/modification des séquences
	 *******************************************************************/
	@PostMapping(path="/postupdatePeriodesSeq")
	public String postupdatePeriodesSeq(@Valid @ModelAttribute("updatePeriodesSeqForm") 
			UpdatePeriodesSeqForm updatePeriodesSeqForm, 
			BindingResult bindingResult, Model model, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ParseException, Exception{
		//System.err.println("DEBUT DE POSTUPDATEPERIODESEQ");
		
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE");
			this.constructModelUpdateSequences(model);
			return "admin/updatePeriodesSeq";
		}
		
		Sequences sequence=new Sequences();
		
		/*
		 * Recuperer l'annee choisi pour la séquence qu'on veut enregistrer
		 */
		Annee anneeAssocie=adminService.getAnneeIntituleAnnee(
				updatePeriodesSeqForm.getIntituleAnneeSeq());
		
		/*
		 * Recuperer le trimestre de numero indiqué dans l'annee indiquée
		 */
		Trimestres trimestreAssocie=adminService.getTrimestreAnnee(anneeAssocie, 
				updatePeriodesSeqForm.getNumeroTrim());
		
		if(trimestreAssocie==null){
			return "redirect:/logesco/admin/getupdateSequences?"
					+ "updateperiodesseqerrorTrimNotExistInAnnee";
		}
		else{
			sequence.setDatedebutPeriodes(updatePeriodesSeqForm.getDatedebutPeriodes());
			sequence.setDatefinPeriodes(updatePeriodesSeqForm.getDatefinPeriodes());
			sequence.setEtatPeriodes(true);
			if(trimestreAssocie.getNumeroTrim()==1){
				sequence.setNumeroSeq(updatePeriodesSeqForm.getNumeroSeq());
			}
			else if(trimestreAssocie.getNumeroTrim()==2){
				if(updatePeriodesSeqForm.getNumeroSeq()==1) sequence.setNumeroSeq(3);
				if(updatePeriodesSeqForm.getNumeroSeq()==2) sequence.setNumeroSeq(4);
			}
			else if(trimestreAssocie.getNumeroTrim()==3){
				if(updatePeriodesSeqForm.getNumeroSeq()==1) sequence.setNumeroSeq(5);
				if(updatePeriodesSeqForm.getNumeroSeq()==2) sequence.setNumeroSeq(6);
			}
			
			sequence.setTrimestre(trimestreAssocie);
			
			int reponseSaveSequence=4;
			reponseSaveSequence=adminService.saveSequences(sequence);
			
			/*//System.err.println("APPEL ENREG SEQ METHODE EFFECTUE: REP= "+
					reponseSaveSequence);*/
			
			if(reponseSaveSequence==0) return "redirect:/logesco/admin/getupdateSequences?"
			+ "updateperiodesseqerrorTrimContent2Seq";
			
			if(reponseSaveSequence==-1) return "redirect:/logesco/admin/getupdateSequences?"
			+ "updateperiodesseqerrorNumeroSeqExist";
			
			if(reponseSaveSequence==-2) return "redirect:/logesco/admin/getupdateSequences?"
			+ "updateperiodesseqerrorDateSeqNonConforme";
			
			if(reponseSaveSequence==-3) return "redirect:/logesco/admin/getupdateSequences?"
			+ "updateperiodesseqerrorTrimNotExistInAnnee";
			
			if(reponseSaveSequence==-4) return "redirect:/logesco/admin/getupdateSequences?"
			+ "updateperiodesseqerrorSeq1NotExistInTrim";
			
			if(reponseSaveSequence==-5) return "redirect:/logesco/admin/getupdateSequences?"
			+ "updateperiodesseqerrorDateSeq1NonConformeInTrim1";
			
			if(reponseSaveSequence==-6) return "redirect:/logesco/admin/getupdateSequences?"
			+ "updateperiodesseqerrorDateSeq2NonConformeInTrim1";
			
			if(reponseSaveSequence==-7) return "redirect:/logesco/admin/getupdateSequences?"
			+ "updateperiodesseqerrorDateSeq3NonConformeInTrim2";
			
			if(reponseSaveSequence==-8) return "redirect:/logesco/admin/getupdateSequences?"
			+ "updateperiodesseqerrorDateSeq4NonConformeInTrim2";
			
			if(reponseSaveSequence==-9) return "redirect:/logesco/admin/getupdateSequences?"
			+ "updateperiodesseqerrorDateSeq5NonConformeInTrim3";
			
			if(reponseSaveSequence==-10) return "redirect:/logesco/admin/getupdateSequences?"
			+ "updateperiodesseqerrorDateSeq6NonConformeInTrim3";
			
			return "redirect:/logesco/admin/getupdateSequences?updateperiodesseqsuccess";
			
		}
		
	}
	
	/*****************************************************************
	 * Traitement du formulaire d'enregistrement/Modification des spécialités
	 ****************************************************************/
	@PostMapping(path="/postupdateSpecialites")
	public String postupdateSpecialites(@Valid @ModelAttribute("updateSpecialitesForm") 
	UpdateSpecialitesForm updateSpecialitesForm, 
	BindingResult bindingResult, Model model, 
	@RequestParam(name="numPage", defaultValue="0") int numPage,
	HttpServletRequest request, HttpServletResponse response) 
			throws ParseException, Exception{
		
		//System.err.println("DEBUT DE POSTUPDATE SPECIALITE");
		
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE");
			this.constructModelUpdateSpecialites(model, numPage, 3);
			return "admin/updateSpecialites";
		}
		
		if(updateSpecialitesForm.getEnregOrmodif().equals("enreg")){
			Specialites spe=new Specialites();
			spe.setCodeSpecialite(updateSpecialitesForm.getCodeSpecialite());
			spe.setLibelleSpecialite(updateSpecialitesForm.getLibelleSpecialite());
			
			int repServeur=adminService.saveSpecialites(spe);
			//System.err.println("repServeur == "+repServeur);
			if(repServeur==0) 
				return "redirect:/logesco/admin/getupdateSpecialites?updatespecialiteserror";
		}
		else if(updateSpecialitesForm.getEnregOrmodif().equals("modif")){
			//System.err.println("FAUT DONC EFFECTUER LA MODIFICATION");
			//System.err.println(updateSpecialitesForm.getCodeSpecialite());
			//System.err.println(updateSpecialitesForm.getLibelleSpecialite());
			
			/*Specialites speAModif=null;
			speAModif=adminService.findSpecialites(
					updateSpecialitesForm.getCodeSpeAModif());
			
			speAModif.setCodeSpecialite(updateSpecialitesForm.getCodeSpecialite());
			speAModif.setLibelleSpecialite(updateSpecialitesForm.getLibelleSpecialite());
			int repServeur=adminService.saveSpecialites(speAModif);*/
			Specialites speModif=new Specialites();
			speModif.setCodeSpecialite(updateSpecialitesForm.getCodeSpecialite());
			speModif.setLibelleSpecialite(updateSpecialitesForm.getLibelleSpecialite());
			
			int repServeur=adminService.updateSpecialites(
					updateSpecialitesForm.getCodeSpeAModif(), speModif);
			
			if(repServeur==0) 
				return "redirect:/logesco/admin/getupdateSpecialites?updatespecialiteserror";
		}
		
		return "redirect:/logesco/admin/getupdateSpecialites?updatespecialitessuccess";
	}
	
	/*****************************************************************
	 * Traitement du formulaire d'enregistrement/Modification des classes
	 ****************************************************************/
	@PostMapping(path="/postupdateClasses")
	public String postupdateClasses(@Valid @ModelAttribute("updateClassesForm") 
	UpdateClassesForm updateClassesForm, 
	BindingResult bindingResult, Model model, 
	@RequestParam(name="numPage", defaultValue="0") int numPage,
	HttpServletRequest request, HttpServletResponse response) 
			throws ParseException, Exception{
		
		//System.err.println("DEBUT DE POSTUPDATE CLASSES");
		
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE");
			this.constructModelUpdateClasses(model, numPage, 3);
			return "admin/updateClasses";
		}
		
		Classes classe=new Classes();
		classe.setCodeClasses(updateClassesForm.getCodeClasse());
		classe.setNumeroClasses(updateClassesForm.getNumeroClasse());
		Specialites specialiteClasse=adminService.findSpecialites(
				updateClassesForm.getCodeSpecialiteClasse());
		classe.setSpecialite(specialiteClasse);
		Sections sectionClasse=adminService.findSections(
				updateClassesForm.getCodeSectionClasse());
		classe.setSection(sectionClasse);
		Niveaux niveauClasse=adminService.findNiveau(
				updateClassesForm.getNumeroOrdreNiveauClasse());
		classe.setNiveau(niveauClasse);
		
		/**
		 *debut des Ajouts du 19-08-2018
		 */
		classe.setLangueClasses(updateClassesForm.getLangueClasses());
		//System.err.println("la langue de la classe est "+updateClassesForm.getLangueClasses());
		/**
		 * fin des Ajouts du 19-08-2018
		 */
		
		if(updateClassesForm.getEnregOrmodif().equals("enreg")){
			//System.err.println("ON EFFECTUE LENREGISTREMENT DE LA CLASSE");
			//Effectuer donc l'enregistrement
			int repServeur=adminService.saveClasses(classe);
			if(repServeur==0) return "redirect:/logesco/admin/getupdateClasses?"
					+ "updateclasseserrorClasseExist";
			
		}
		else if(updateClassesForm.getEnregOrmodif().equals("modif")){
			//System.err.println("FAUT DONC EFFECTUER LA MODIFICATION DE LA CLASSE");
			
			int repServeur=adminService.updateClasses(
					updateClassesForm.getCodeClasseAModif(),
					updateClassesForm.getNumeroClasseAModif(), 
					updateClassesForm.getIdSpecialiteAModif(), classe);
			
			if(repServeur==0) 
				return "redirect:/logesco/admin/getupdateClasses?updateclasseserror";
			
		}
		
		return "redirect:/logesco/admin/getupdateClasses?updateclassessuccess";
	}
	
	/*****************************************************************
	 * Traitement du formulaire d'enregistrement/Modification des roles
	 ****************************************************************/
	@PostMapping(path="/postupdateRoles")
	public String postupdateRoles(@Valid @ModelAttribute("updateRolesForm") 
	UpdateRolesForm updateRolesForm, 
	BindingResult bindingResult, Model model, 
	@RequestParam(name="numPage", defaultValue="0") int numPage,
	HttpServletRequest request, HttpServletResponse response) 
			throws ParseException, Exception{
		//System.err.println("DEBUT DE POSTUPDATE ROLES");
		
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE");
			this.constructModelUpdateRoles(model, numPage, 5);
			
			return "admin/updateRoles";
		}
		
		Roles role=new Roles();
		role.setAliasRole(updateRolesForm.getAliasRole());
		role.setRole(updateRolesForm.getRole());
		
		if(updateRolesForm.getEnregOrmodif().equals("enreg")){
			//System.err.println("ON EFFECTUE LENREGISTREMENT DU ROLES");
			//Effectuer donc l'enregistrement
			int repServeur=adminService.saveRoles(role);
			if(repServeur==0) return "redirect:/logesco/admin/getupdateRoles?"
					+ "updateroleserrorRolesExist";
		}
		else if(updateRolesForm.getEnregOrmodif().equals("modif")){
			//System.err.println("FAUT DONC EFFECTUER LA MODIFICATION DU ROLE");
			int repServeur=adminService.updateRoles(updateRolesForm.getRoleAModif(), role);
			if(repServeur==0) return "redirect:/logesco/admin/getupdateRoles?"
					+ "updateroleserror";
		}
		
		return "redirect:/logesco/admin/getupdateRoles?updaterolessuccess";
	}
	
	

	@SuppressWarnings("deprecation")
	@PostMapping(path="/postupdateMatieres")
	public String postupdateMatieres(@Valid @ModelAttribute("updateMatieresForm") 
			UpdateMatieresForm updateMatieresForm, 
			BindingResult bindingResult, Model model, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ParseException, Exception, IOException{
		
		//System.err.println("ICI AU DEBUT DE postUPDATEMatieres USERSUSERSUSERS");
		
		int cleExistante=0;
		int numPageMatiere = 0;
		Matieres matiereConcerne = null;
		for(String cle : request.getSession().getValueNames()){
			if(cle.equals("numPageMatiere")) cleExistante=cleExistante+1;
			if(cle.equals("matiereConcerne")) cleExistante=cleExistante+1;
		}
		
		//System.err.println("LES CLES RECHERCHEES EXISTENT BEL ET BIEN DANS "+cleExistante);
		if(cleExistante==2){
			numPageMatiere = (Integer)request.getSession().getAttribute("numPageMatiere");
			matiereConcerne = (Matieres)request.getSession().getAttribute("matiereConcerne");
		}
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE updateMatière");
			
			if(matiereConcerne!=null) {
				this.constructModelUpdateMatiere(model, request, updateMatieresForm, 
						matiereConcerne.getIdMatiere(), numPageMatiere, 3);
			}
			else{
				this.constructModelUpdateMatiere(model, request, updateMatieresForm, 
						new Long(0), 0, 3);
			}
			
			return "admin/updateMatieres";
			
		}
		
		/*
		 * Ici tout est bon et on peut aller enregistrer la matieres ou le département
		 */
		Matieres matiere = new Matieres();
		
		matiere.setCodeMatiere(updateMatieresForm.getCodeMatiere());
		matiere.setIntituleMatiere(updateMatieresForm.getIntituleMatiere());
		
		matiere.setIdMatiere(updateMatieresForm.getIdMatiere() );
		
		/***
		 * debut des ajouts du 19-08-2018
		 */
		//System.err.println("ici deja dans updateMatiere");
		matiere.setIntitule2langueMatiere(updateMatieresForm.getIntitule2langueMatiere());
		//System.err.println("ici deja dans updateMatiere  "+matiere.getIntitule2langueMatiere());
		/*****
		 * fin des ajouts du 19-08-2018
		 */
		
		int repServeur = adminService.updateMatiere(matiere);
		
		if(repServeur == 0) return "redirect:/logesco/admin/getupdateMatieres?updatematiereserrorCode"
				+ "&&idMatiere="+updateMatieresForm.getIdMatiere();
		if(repServeur == 2) return "redirect:/logesco/admin/getupdateMatieres?updatematieressuccess";
		return "redirect:/logesco/admin/getupdateMatieres?enregmatieressuccess";
	}
	
	@GetMapping(path="/getsupprimerMatieres")
	public String getsupprimerMatieres( 
			@RequestParam(name="idMatiere", defaultValue="0") Long idMatiere, 
			Model model, HttpServletRequest request){
		
		int repServeur = adminService.deleteMatiere(idMatiere);
		
		if(repServeur == -1) return "redirect:/logesco/admin/getupdateMatieres?supprimmatiereserrorMat";
		
		if(repServeur == 0) return "redirect:/logesco/admin/getupdateMatieres?supprimmatiereserrorCours";
		
		return "redirect:/logesco/admin/getupdateMatieres?supprimmatieressuccess";
	}
	
	
	
	
	public void constructModelUpdateCours(Model model,	HttpServletRequest request,	UpdateCoursForm	updateCoursForm,		
			Long idCours,	Long idMatiereAssocie,	long idUsersAssocie, Long idClasseAssocie,	int numPageCours,	int taillePage){
		
		HttpSession session = request.getSession();
		
		
		
		/*
		 * On doit chargé la classe associe dans le model
		 */
		Classes classeSelect = adminService.findClasses(idClasseAssocie);
		if(classeSelect != null) {
			//System.err.println("idclasseSelect *** "+classeSelect.getCodeClasses());
			
			updateCoursForm.setIdClasseSelect(classeSelect.getIdClasses());
			model.addAttribute("classeSelect", classeSelect);
		}
		
		
		
		Page<Cours> pageofCours = adminService.findAllCoursClasse(numPageCours, taillePage, idClasseAssocie);
		
		if(pageofCours != null){
			if(pageofCours.getContent().size()!=0){
				model.addAttribute("listofCours", pageofCours.getContent());
				int[] listofPagesCours=new int[pageofCours.getTotalPages()];
				
				model.addAttribute("listofPagesCours", listofPagesCours);
				
				model.addAttribute("pageCouranteCours", numPageCours);
				//System.err.println("numPageCours  "+numPageCours);
				session.setAttribute("numPageCours", numPageCours);
			}
		}
		
		
		//System.err.println("liste cours page par page charge ");
		/*
		 * On charge la liste des matieres dans le model pour le formulaire
		 */
		List<Matieres> listofMatiere = adminService.findAllMatieres();
		model.addAttribute("listofMatiere", listofMatiere);
		//System.err.println("liste matiere charge  === ");
		
		
		/*
		 * On fait la liste des niveaux puisque les classes seront plutot chargé par niveaux
		 */
		List<Niveaux> listofNiveaux = adminService.findAllNiveaux();
		model.addAttribute("listofNiveaux", listofNiveaux);
		//System.err.println("liste niveau chargé  === ");
		/*
		 * On charge la liste des classes dans le model pour le formulaire
		 */
		List<Classes> listofClasses = adminService.findAllClasse();
		model.addAttribute("listofClasses", listofClasses);
		//System.err.println("liste classe charge === ");
		/*
		 * On charge la liste des Proffesseurs dans le model pour le formulaire
		 */
		List<Proffesseurs> listofProf = adminService.findAllProffesseurs();
		model.addAttribute("listofProf", listofProf);
		
		/*
		 * On charge la liste des Proviseur dans le model pour le formulaire
		 */
		List<Proviseur> listofproviseur = adminService.findAllProviseur();
		if(listofproviseur.size() != 0)	model.addAttribute("listofproviseur", listofproviseur);
		
		/*
		 * On charge la liste des Censeurs dans le model pour le formulaire
		 */
		List<Censeurs> listofcenseurs = adminService.findAllCenseurs();
		if(listofcenseurs.size() != 0)	model.addAttribute("listofcenseurs", listofcenseurs);
		
		/*
		 * On charge la liste des SG dans le model pour le formulaire
		 */
		List<SG> listofsg = adminService.findAllSG();
		if(listofsg.size() != 0)	model.addAttribute("listofsg", listofsg);
		
		/*
		 * On charge la liste des Intendants dans le model pour le formulaire
		 */
		List<Intendant> listofintendant = adminService.findAllIntendant();
		if(listofintendant.size() != 0)	model.addAttribute("listofintendant", listofintendant);
		
		/*
		 * On charge la liste des Enseignants dans le model pour le formulaire
		 */
		List<Enseignants> listofenseignants = adminService.findAllEnseignants();
		if(listofenseignants.size()	!=	0)	model.addAttribute("listofenseignants", listofenseignants);
		
		//System.err.println("liste users chargé  === ");
		
		/*
		 * On doit rechercher le cours dont le id est passe en paramètre au cas où ca existe
		 */
		
		Cours coursConcerne = adminService.findCours(idCours);
		if(coursConcerne!=null){
			//System.err.println(" coursConcerne  === trouve");
			updateCoursForm.setCodeCours(coursConcerne.getCodeCours());
			updateCoursForm.setIntituleCours(coursConcerne.getIntituleCours());
			/***
			 * Debut des ajouts du 19-08-2018
			 */
			updateCoursForm.setIntitule2langueCours(coursConcerne.getIntitule2langueCours());
			updateCoursForm.setNumerogroupeCours(coursConcerne.getNumerogroupeCours());
			updateCoursForm.setGroupeCours(coursConcerne.getGroupeCours());
			updateCoursForm.setNumerogroupeCours(coursConcerne.getNumerogroupeCours());
			/***
			 * Fin des ajouts du 19-08-2018
			 */
			updateCoursForm.setCoefCours(coursConcerne.getCoefCours());
			updateCoursForm.setIdCours(coursConcerne.getIdCours());
			updateCoursForm.setIdClasseSelect(coursConcerne.getClasse().getIdClasses());
			updateCoursForm.setIdMatiereAssocie(coursConcerne.getMatiere().getIdMatiere());
			updateCoursForm.setIdUsersAssocie(coursConcerne.getProffesseur().getIdUsers());
			
			model.addAttribute("coursConcerne", coursConcerne);
			session.setAttribute("coursConcerne", coursConcerne);
		}
		else{
			//System.err.println(" coursConcerne  === NONtrouve");
			//Car l'idMatiere ne doit pas être null lors de l'appel à la methode updateMatiere au niveau du metier 
			updateCoursForm.setIdCours(new Long(0));
			/*updateCoursForm.setIdClasseAssocie(new Long(0));
			updateCoursForm.setIdMatiereAssocie(new Long(0));
			updateCoursForm.setIdUsersAssocie(new Long(0));*/
			/*
			 * Lors de l'affichage des numero de page on passe aussi dans la requete idmatiere de l'objet matiereConcerne
			 * qui est d'ailleurs le même que celui de updateMatieresForm
			 */
		}
		
		//System.err.println(" TOUS EST TERMINE");
		
	}
	
	
	
	@GetMapping(path="/getupdateCours")
	public String getupdateCours(@ModelAttribute("updateCoursForm") 
	UpdateCoursForm updateCoursForm,  
	@RequestParam(name="idCours", defaultValue="0") Long idCours,
	@RequestParam(name="idMatiereAssocie", defaultValue="0") Long idMatiereAssocie, 
	@RequestParam(name="idUsersAssocie", defaultValue="0") Long idUsersAssocie, 
	@RequestParam(name="idClasseAssocie", defaultValue="0") Long idClasseAssocie, 
	@RequestParam(name="numPageCours", defaultValue="0") int numPageCours,
	@RequestParam(name="taillePage", defaultValue="25") int taillePage,
	Model model, HttpServletRequest request){
		
		//System.err.println("idClasseAssocie *** "+idClasseAssocie);
		
		this.constructModelUpdateCours(model,	request,	updateCoursForm,		idCours,	idMatiereAssocie,	idUsersAssocie, 
				idClasseAssocie,	numPageCours,	taillePage);
		
		return "admin/updateCours";
	}
	
	@GetMapping(path="/getsupprimerCours")
	public String getsupprimerCours( 
			@RequestParam(name="idCours", defaultValue="0") Long idCours, 
			@RequestParam(name="idClasseAssocie", defaultValue="0") Long idClasseAssocie, 
			Model model, HttpServletRequest request){
		
		int repServeur = adminService.deleteCours(idCours);
		
		if(repServeur == -1) return "redirect:/logesco/admin/getupdateCours?supprimcourserrorCours";
		
		if(repServeur == 0) return "redirect:/logesco/admin/getupdateCours?supprimcourserrorCours";
		
		Classes classeConcerne = adminService.findClasses(idClasseAssocie);
		
		return "redirect:/logesco/admin/getupdateCours?supprimcourssuccess&&idClasseAssocie="+classeConcerne.getIdClasses();
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(path="/postupdateCours")
	public String postupdateCours(@Valid @ModelAttribute("updateCoursForm") 
			UpdateCoursForm updateCoursForm, 
			BindingResult bindingResult, Model model, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ParseException, Exception, IOException{
		
		//System.err.println("ICI AU DEBUT DE postupdateCours ADMIN");
		
		int cleExistante=0;
		int numPageCours = 0;
		Cours coursConcerne = null;
		for(String cle : request.getSession().getValueNames()){
			if(cle.equals("numPageCours")) cleExistante=cleExistante+1;
			if(cle.equals("coursConcerne")) cleExistante=cleExistante+1;
		}
		
		//System.err.println("LES CLES RECHERCHEES EXISTENT BEL ET BIEN DANS "+cleExistante);
		if(cleExistante==2){
			numPageCours = (Integer)request.getSession().getAttribute("numPageCours");
			coursConcerne = (Cours)request.getSession().getAttribute("coursConcerne");
		}
		
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE updateCours");
			
			if(coursConcerne	!=	null) {
				//System.err.println("ERREUR DE REMPLISSAGE coursConcerne  === ");
				/*this.constructModelUpdateCours(model, request, updateCoursForm, 
						coursConcerne.getIdCours(), coursConcerne.getMatiere().getIdMatiere(), 
						coursConcerne.getProffesseur().getIdUsers(), coursConcerne.getClasse().getIdClasses(), numPageCours, 3);*/
				
				return "redirect:/logesco/admin/getupdateCours?updateCourserror"
							+ "&&idCours="+updateCoursForm.getIdCours()
							+ "&&idMatiereAssocie="+updateCoursForm.getIdCours()
							+ "&&idUsersAssocie="+updateCoursForm.getIdCours()
							+ "&&idClasseAssocie="+updateCoursForm.getIdClasseSelect()
							+ "&&numPageCours="+numPageCours;
				
			}
			else{
				//System.err.println("ERREUR DE REMPLISSAGE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!coursConcerne  === ");
				/*this.constructModelUpdateCours(model, request, updateCoursForm, 
						new Long(0), new Long(0), new Long(0), new Long(0), 0, 3);*/
				return "redirect:/logesco/admin/getupdateCours?updateCourserror";
			}
			
			//return "admin/updateCours";
			
		}
		
		
			Cours cours = new Cours();
			
			cours.setCodeCours(updateCoursForm.getCodeCours());
			cours.setCoefCours(updateCoursForm.getCoefCours());
			cours.setIdCours(updateCoursForm.getIdCours());
			cours.setIntituleCours(updateCoursForm.getIntituleCours());
			/***
			 * Debut des ajouts du 19-08-2018
			 */
			cours.setIntitule2langueCours(updateCoursForm.getIntitule2langueCours());
			cours.setGroupeCours(updateCoursForm.getGroupeCours());
			cours.setNumerogroupeCours(updateCoursForm.getNumerogroupeCours());
			/***
			 * Fin des ajouts du 19-08-2018
			 */
			
			//System.err.println("fffffffffffffffff "+updateCoursForm.getNumerogroupeCours());
			
			int repServeur = adminService.updateCours(cours, updateCoursForm.getIdMatiereAssocie(), 
					updateCoursForm.getIdUsersAssocie(), updateCoursForm.getIdClasseSelect());
			
			if(repServeur == 0) return "redirect:/logesco/admin/getupdateCours?updatecourserrorCode"
					+ "&&idCours="+updateCoursForm.getIdCours()
					+ "&&idMatiereAssocie="+updateCoursForm.getIdCours()
					+ "&&idUsersAssocie="+updateCoursForm.getIdCours()
					+ "&&idClasseAssocie="+updateCoursForm.getIdClasseSelect()
					+ "&&numPageCours="+numPageCours;
			
			if(repServeur == 2) return "redirect:/logesco/admin/getupdateCours?updatecourssuccess"
					+ "&&idClasseAssocie="+updateCoursForm.getIdClasseSelect()
					+ "&&numPageCours="+numPageCours;
			
			//System.err.println("idClasseAssocieSelectionner *** "+updateCoursForm.getIdClasseSelect());
			
			return "redirect:/logesco/admin/getupdateCours?enregcourssuccess"
					+ "&&idClasseAssocie="+updateCoursForm.getIdClasseSelect()
					+ "&&numPageCours="+numPageCours;
		
		
	}
					
}
