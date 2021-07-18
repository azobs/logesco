/**
 * 
 */
package org.logesco.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.logesco.entities.Classes;
import org.logesco.entities.Eleves;
import org.logesco.entities.Etablissement;
import org.logesco.entities.Niveaux;
import org.logesco.form.UpdateMtScoClassesForm;
import org.logesco.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author cedrickiadjeu
 *
 */
@Controller
@RequestMapping(path="/logesco/users/intendant")
public class IntendantController {
	@Autowired
	private IUsersService usersService;
	
////////////////////////////////////DEBUT DES REQUETES DE TYPE GET ////////////////////////////////////////////
	public void constructModelInscriptionEleves(Model model, HttpServletRequest request, int numPageEleves,
			 long idClasseSelect){
		
		/*
		 * Il faut faire la liste des classes et placer dans le model puisqu'on 
		 * va s'en servir pour les etiquetes des classes et cette liste se fait lorsque la page se charge
		 * c'est à dire lorsque idClasseSelect==-1 ou montantScolarite==0
		 */
			List<Classes> listofClasses=usersService.findListClasse();
			
			model.addAttribute("listofClasses", listofClasses);
			
			List<Niveaux> listofNiveaux = usersService.findAllNiveaux();
		
			model.addAttribute("listofNiveaux", listofNiveaux);
			
			/*
			 * Ici on est sur que la page est déjà chargé et qu'on veut en fait la liste des élèves d'une classe particulière 
			 * page par page
			 */
			Long idClasseAEnvoyer=new Long(idClasseSelect);
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
			
			//System.err.println("Voici la classe choisi "+classeSelect);
			
			/*
			 * Il faut vérifier si le montant de la scolarité pour les élèves de la classe selectionnée
			 * est déjà spécifié. Sinon on doit plutôt signaler une erreur pour exiger la configuration
			 * Il est configuré lorsqu'il est strictement supérieur à 0
			 */
			if(classeSelect != null){
				//System.err.println("Voici la classe choisi "+classeSelect.getMontantScolarite());
				if(classeSelect.getMontantScolarite() > 0){
				/*
				 * Il faut maintenant la liste des élèves de cette classe
				 */
				int effectifprovClasse=usersService.getEffectifProvisoireClasse(idClasseSelect);
				model.addAttribute("effectifprovClasse", effectifprovClasse);
				
				
				
				//On garde le numpageeleve dans la session puisqu'apres modification il va falloir revenir sur la page qui etait affiche
				HttpSession session=request.getSession();
				session.setAttribute("numPageEleves", numPageEleves);
				
				Page<Eleves> pageofEleves=usersService.findPageElevesClasse(idClasseAEnvoyer,	
						numPageEleves, 10);
				
				//System.err.println("liste des élèves dans la liste "+pageofEleves.getContent().size());
				
				if(pageofEleves != null){
					if(pageofEleves.getContent().size()!=0){
						model.addAttribute("listofEleves", pageofEleves.getContent());
						int[] listofPagesEleves=new int[pageofEleves.getTotalPages()];
							
						model.addAttribute("listofPagesEleves", listofPagesEleves);
							
						model.addAttribute("pageCouranteEleves", numPageEleves);
						//System.err.println("la liste des élève contient "+pageofEleves.getContent().size());
					}
					else{
						//System.err.println("cette classe ne contient encore aucun élève");
						model.addAttribute("erreurClasseVide", "erreurClasseVide");
					}
			    }
			}
			else{
				model.addAttribute("erreurClasseNonConfig", "erreurClasseNonConfig");
			}
		}
	}
	
	@GetMapping(path="/getinscriptionEleves")
	public String getinscriptionEleves(Model model, HttpServletRequest request,	
			@RequestParam(name="numPageEleves", defaultValue="0") int numPageEleves,
			@RequestParam(name="idClasseSelect", defaultValue="-1") long idClasseSelect) throws ParseException{
		
		/*
		 * Il faut la liste des classes dans le modèle et puis lors du clic sur le bouton recherche on doit 
		 * afficher la liste des élèves de la classe choisi avec un bouton permettant d'inscrire un élève ou d'annuler une inscription
		 */
		this.constructModelInscriptionEleves(model, request, numPageEleves, idClasseSelect);
		
		return "users/inscrireEleves";
	}
	
	@GetMapping(path="/versementSco")
	public String versementSco(Model model, HttpServletRequest request,	
			@RequestParam(name="numPageEleves", defaultValue="0") int numPageEleves,
			@RequestParam(name="montantScolarite", defaultValue="0") double montantScolarite,
			@RequestParam(name="montantAVerse", defaultValue="0") double montantAVerse,
			@RequestParam(name="idEleveAModif", defaultValue="0") long idEleveAModif,
			@RequestParam(name="idClasseSelect", defaultValue="-1") long idClasseSelect) throws ParseException{
		
		/*
		 * Il faut rechercher l'élève dont on va modifier l'état d'inscription au cas où le montant 
		 * deja versé de sa scolarité atteint au moins le montant de la scolarité. 
		 */
		//System.err.println("Appel à la fonction d'enregistrement de versement ");
		
		int repServeur = usersService.enregVersementSco(idEleveAModif, montantAVerse, montantScolarite);
		
		
		/*//System.err.println("idEleveAModif=="+idEleveAModif+"  montantAVerse=="+montantAVerse
				+"  montantScolarite=="+montantScolarite+" repServeur=="+repServeur);*/
		
		if(repServeur == -1) return "redirect:/logesco/users/intendant/getinscriptionEleves?updateetatInsceleveserror"
				+ "&&montantScolarite="+montantScolarite
				+ "&&idClasseSelect="+idClasseSelect
				+ "&&numPageEleves="+numPageEleves;
		
		if(repServeur == -2) return "redirect:/logesco/users/intendant/getinscriptionEleves?updateetatInsceleveserrorMt"
				+ "&&montantScolarite="+montantScolarite
				+ "&&idClasseSelect="+idClasseSelect
				+ "&&numPageEleves="+numPageEleves;
		
		return "redirect:/logesco/users/intendant/getinscriptionEleves?updateetatInscelevessuccess"
				+ "&&montantScolarite="+montantScolarite
				+ "&&idClasseSelect="+idClasseSelect
				+ "&&numPageEleves="+numPageEleves;
		
	}
	
	public void constructModelConfigMtScoClasse(UpdateMtScoClassesForm updateMtScoClassesForm,	Model model, 
			HttpServletRequest request, int numPageClasses){
		
		List<Classes> listofClasses=usersService.findListClasse();
		
		model.addAttribute("listofClasses", listofClasses);
		
		/*
		 * Ces deux valeurs doivent être modifie après le clic sur le bouton modifier pour les valeurs soient bien mise
		 * dans l'interface et non les valeurs vides comme indiqué par défaut ici. 
		 */
		model.addAttribute("labelClasseAConfig", "");
		model.addAttribute("idClasseAConfig", "0");
		
		updateMtScoClassesForm.setIdclasseAConfig(0);
		updateMtScoClassesForm.setLabelclasseAConfig("");
		updateMtScoClassesForm.setNumPageClasses(numPageClasses);
		updateMtScoClassesForm.setMontantScolarite(0);
		
		Page<Classes> pageofClasses=
				usersService.findPageClasse(numPageClasses, 5);
		
		if(pageofClasses.getContent().size()!=0){
			model.addAttribute("listpageofClasses", pageofClasses.getContent());
			int[] listofPagesClasses=new int[pageofClasses.getTotalPages()];
			
			model.addAttribute("listofPagesClasses", listofPagesClasses);
			
			model.addAttribute("pageCourante", numPageClasses);
			//System.err.println("numPageClasses  "+numPageClasses);
		}
		
	}
	
	@GetMapping(path="/getconfigClasses")
	public String getconfigClasses(UpdateMtScoClassesForm updateMtScoClassesForm,	Model model, HttpServletRequest request,
			@RequestParam(name="numPageClasses", defaultValue="0") int numPageClasses) throws ParseException{
		/*
		 * Il faut la liste des classes dans le modèle pour qu'on puisse pour chaque classe définir le montant de la scolarité voulu
		 */
		this.constructModelConfigMtScoClasse(updateMtScoClassesForm,	model, request, numPageClasses);
		return "users/configMtScoClasse";
	}
	
	public void constructModelModifMtScoClasse(UpdateMtScoClassesForm updateMtScoClassesForm, Model model, 
			HttpServletRequest request, long idClassesAConfig, int numPageClasses){
		
		/*
		 * On va rechercher la classe selectionné car on doit l'utiliser pour initialiser la page
		 */
		Classes classeAConfig = usersService.findClasses(idClassesAConfig);
		
		
		/*
		 * On doit donc modifier dans le modèle les valeurs idClasseAConfig et labelClasseAConfig afin que le formulaire recoivent
		 * les valeurs de la classe qu'on souhaite configurer le montant de la scolarité
		 */
		String labelClasseAConfig = classeAConfig.getCodeClasses()+" "+classeAConfig.getSpecialite().getCodeSpecialite()+" "
				+ classeAConfig.getNumeroClasses();
		Long idClasseAConfig = classeAConfig.getIdClasses();
		
		model.addAttribute("labelClasseAConfig", labelClasseAConfig);
		model.addAttribute("idClasseAConfig", idClasseAConfig);
		
		updateMtScoClassesForm.setIdclasseAConfig(idClasseAConfig.longValue());
		updateMtScoClassesForm.setLabelclasseAConfig(labelClasseAConfig);
		updateMtScoClassesForm.setNumPageClasses(numPageClasses);
		updateMtScoClassesForm.setMontantScolarite(classeAConfig.getMontantScolarite());
		
		Page<Classes> pageofClasses=
				usersService.findPageClasse(numPageClasses, 5);
		
		if(pageofClasses.getContent().size()!=0){
			model.addAttribute("listpageofClasses", pageofClasses.getContent());
			int[] listofPagesClasses=new int[pageofClasses.getTotalPages()];
			
			model.addAttribute("listofPagesClasses", listofPagesClasses);
			
			model.addAttribute("pageCourante", numPageClasses);
			//System.err.println("numPageClasses  "+numPageClasses);
		}
		
	}
	
	@GetMapping(path="/getmodifMtScoClasses")
	public String getmodifMtScoClasses(@ModelAttribute("updateMtScoClassesForm") 
			UpdateMtScoClassesForm updateMtScoClassesForm,	Model model, HttpServletRequest request,
			@RequestParam(name="idClassesAConfig", defaultValue="0") long idClassesAConfig,
			@RequestParam(name="numPageClasses", defaultValue="0") int numPageClasses) throws ParseException{
		/*
		 * Il faut la liste des classes dans le modèle pour qu'on puisse pour chaque classe définir le montant de la scolarité voulu
		 */
		this.constructModelModifMtScoClasse(updateMtScoClassesForm, model, request, idClassesAConfig, numPageClasses);
		
		return "users/configMtScoClasse";
	}
	
	/*@GetMapping(path="/getenregMtClasses")
	public String getenregMtClasses(Model model, HttpServletRequest request,
			@RequestParam(name="idClassesAConfig", defaultValue="0") long idClassesAConfig,
			@RequestParam(name="montantScolarite", defaultValue="0") double montantScolarite,
			@RequestParam(name="numPageClasses", defaultValue="0") int numPageClasses) throws ParseException{
		
		//System.err.println("il faut configurer la classe d'id "+idClassesAConfig+" en fixant le montant sco a "+montantScolarite);
		
		return "redirect:/logesco/users/intendant/getmodifMtScoClasses?updateclassesuccess"
				+ "&&idClassesAConfig="+idClassesAConfig
				+ "&&numPageClasses="+numPageClasses;
	}*/
	
	
	@GetMapping(path="/getrapportVersement")
	public String getrapportVersement(Model model, HttpServletRequest request,
			@RequestParam(name="idClassesConcerne", defaultValue="0") long idClassesConcerne) throws ParseException{
		
		Etablissement etablissementConcerne = usersService.getEtablissement();
		/*
		 * On va rechercher la classe selectionné car on doit la placer aussi dans le model
		 */
		Classes classeConcerne = usersService.findClasses(idClassesConcerne);
		
		List<Eleves> listofElevesDeClasse =usersService.findListElevesClasse(classeConcerne.getIdClasses());
		
		HttpSession session=request.getSession();
		
		
		session.setAttribute("etablissementConcerne", etablissementConcerne);
		session.setAttribute("classeConcerne", classeConcerne);
		session.setAttribute("listofElevesDeClasse", listofElevesDeClasse);
		
		return "users/rapportversementSco";
	}
	
////////////////////////////////////FIN DES REQUETES DE TYPE GET ////////////////////////////////////////////	

////////////////////////////////////DEBUT DES REQUETES DE TYPE POST ////////////////////////////////////////////
	@PostMapping(path="/postenregMtClasses")
	public String postenregMtClasses( @Valid @ModelAttribute("updateMtScoClassesForm") 
		UpdateMtScoClassesForm updateMtScoClassesForm,	BindingResult bindingResult,	Model model, 
		HttpServletRequest request, HttpServletResponse response) 
				throws ParseException, Exception{
		
		/*//System.err.println("classe == "+updateMtScoClassesForm.getIdclasseAConfig()+
				"  Montant == "+updateMtScoClassesForm.getMontantScolarite());*/
		
		if (bindingResult.hasErrors()) {
			//System.err.println("ERREUR DE REMPLISSAGE DU FORMULAIRE  "+bindingResult);
			return "redirect:/logesco/users/intendant/getmodifMtScoClasses?updatemtclasseerror"
				+ "&&idClassesAConfig="+updateMtScoClassesForm.getIdclasseAConfig()
				+ "&&numPageClasses="+updateMtScoClassesForm.getNumPageClasses();
		}
		
		/*
		 * On va appeler la methode du service metier pour fixer les montants 
		 */
		
		int ret = usersService.setMontantScoClasse(updateMtScoClassesForm.getIdclasseAConfig(), 
				updateMtScoClassesForm.getMontantScolarite());
		
		/*//System.err.println("classe == "+updateMtScoClassesForm.getIdclasseAConfig()+
				"  Montant == "+updateMtScoClassesForm.getMontantScolarite()+" ret == "+ret);*/
		
		if(ret == 0) return "redirect:/logesco/users/intendant/getmodifMtScoClasses?updatemtclasseerrorclasse"
				+ "&&idClassesAConfig="+updateMtScoClassesForm.getIdclasseAConfig()
				+ "&&numPageClasses="+updateMtScoClassesForm.getNumPageClasses();
		
		
		return "redirect:/logesco/users/intendant/getmodifMtScoClasses?updatemtclassesuccess"
				+ "&&idClassesAConfig="+updateMtScoClassesForm.getIdclasseAConfig()
				+ "&&numPageClasses="+updateMtScoClassesForm.getNumPageClasses();
	}
////////////////////////////////////FIN DES REQUETES DE TYPE POST ////////////////////////////////////////////		
	
}
