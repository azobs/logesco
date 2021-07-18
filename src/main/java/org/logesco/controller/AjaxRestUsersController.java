/**
 * 
 */
package org.logesco.controller;

import javax.servlet.http.HttpServletRequest;

import org.logesco.entities.Eleves;
import org.logesco.entities.Evaluations;
import org.logesco.entities.Sequences;
import org.logesco.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cedrickiadjeu
 *
 */
@RestController
@RequestMapping(path="/logesco/users/ajaxrestusers")
public class AjaxRestUsersController {

	@Autowired
	private IUsersService usersService;
	
	public String mention(double note, String lang){
		/*System.err.println("Voici donc la langue de la mention "+lang);*/
		if(note>=0 && note<5) return lang.equalsIgnoreCase("fr")==true?"NUL":"NULL";
		else if(note>=5 && note<7) return lang.equalsIgnoreCase("fr")==true?"TRES FAIBLE":"VERY POOR";
		else if(note>=7 && note<8) return lang.equalsIgnoreCase("fr")==true?"FAIBLE":"POOR";
		else if(note>=8 && note<9) return lang.equalsIgnoreCase("fr")==true?"INSUFFISANT":"INSUFFICIENT";
		else if(note>=9 && note<10) return lang.equalsIgnoreCase("fr")==true?"MEDIOCRE":"MEDIOCRE";
		else if(note>=10 && note<12) return lang.equalsIgnoreCase("fr")==true?"PASSABLE":"FAIR";
		else if(note>=12 && note<14) return lang.equalsIgnoreCase("fr")==true?"ASSEZ BIEN":"FAIRLY GOOD";
		else if(note>=14 && note<16) return lang.equalsIgnoreCase("fr")==true?"BIEN":"GOOD";
		else if(note>=16 && note<18) return lang.equalsIgnoreCase("fr")==true?"TRES BIEN":"VERY GOOD";
		else if(note>=18 && note<20) return "EXCELLENT";
		else if(note==20) return lang.equalsIgnoreCase("fr")==true?"PARFAIT":"PERFECT";
		else  return "IMPOSSIBLE"; 
	}
	
	@SuppressWarnings("unused")
	@GetMapping(path="/enregNote/{idEleve}/{idEval}/{proportionEval}/{noteSaisi}/{lang}", produces=MimeTypeUtils.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> enregNote_Ajax(HttpServletRequest request,
			@PathVariable(name="idEleve") Long idEleve,
			@PathVariable(name="idEval") Long idEval,
			@PathVariable(name="proportionEval") String proportionEval,
			@PathVariable(name="noteSaisi") String noteSaisi,
			@PathVariable(name="lang") String lang){
		try{
			
			//String texteReponse = "**** les parametres passes a la requete sont: idEleves="+idEleve+" idEval="+idEval+" noteSaisi="+noteSaisi;
			
			int reponse = 0;
			
			//Eleves elv = usersService.findEleves(idEleve);
			
			/*
			 * Recuperer l'évaluation
			 */
			Evaluations evalConcerne = usersService.findEvaluations(idEval);
			/*if(evalConcerne == null) System.err.println("yyyyyyyyyyyyyyyyy evalConcerne non trouve");*/
			
			try{
				int newproportionEval = Integer.parseInt(proportionEval);
				double valNoteSaisi = Double.parseDouble(noteSaisi);
				/*
				 * On met à jour la nouvelle proportion de l'évaluation
				 */
				evalConcerne.setProportionEval(newproportionEval);
				
				int r = usersService.saveEvaluation(evalConcerne.getContenuEval(), evalConcerne.getCours(), evalConcerne.getDateenregEval(), 
						evalConcerne.getProportionEval(), evalConcerne.getSequence(), evalConcerne.getTypeEval());
				
				/*System.err.println("le r de saveEvalsssAjax == "+r);*/
				if(r == 0) reponse = -1;
				
				/*
				 * On peut donc enregistrer la note de l'élève
				 */
				int ret = usersService.saveNoteEvalEleve(idEval, idEleve, valNoteSaisi);
				reponse = ret;
				/*System.err.println("le r de saveNoteEvalAjax == "+reponse);*/
				if(ret == 0) reponse = -2;
				
				String mention = this.mention(valNoteSaisi,lang);
				ResponseEntity<String> responseEntity = new ResponseEntity<String>(new String(""+mention), HttpStatus.OK);
				return responseEntity;
			}
			catch(Exception e){
				reponse = -3;
				String mention = "IMPOSSIBLE";
				ResponseEntity<String> responseEntity = new ResponseEntity<String>(new String(""+mention), HttpStatus.OK);
				return responseEntity;
			}
			
		}
		catch(Exception e){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(path="/enregProportionEval/{idEval}/{proportionEval}", produces=MimeTypeUtils.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> enregProportionEval_Ajax(HttpServletRequest request,
			@PathVariable(name="idEval") Long idEval,
			@PathVariable(name="proportionEval") String proportionEval){
		try{
			int reponse = 0;
			int newproportionEval_associe = -1;
			
			int newproportionEval = Integer.parseInt(proportionEval);
				
			if(newproportionEval<=100){
				newproportionEval_associe = 100 - newproportionEval;
				/*
				 * On appele la fonction de mise à jour de l'évaluation
				 */
				int majE = usersService.updateProportionEvaluation(idEval, newproportionEval);
				if(majE == -1){
					reponse = majE;
					ResponseEntity<String> responseEntity = new ResponseEntity<String>(new String(""+reponse), HttpStatus.OK);
					return responseEntity;
				}
				reponse = newproportionEval_associe;
				ResponseEntity<String> responseEntity = new ResponseEntity<String>(new String(""+reponse), HttpStatus.OK);
				return responseEntity;
			}
			reponse = -1;//la proportion saisie est superieur a 100 ce qui est impossible
			ResponseEntity<String> responseEntity = new ResponseEntity<String>(new String(""+reponse), HttpStatus.OK);
			return responseEntity;
			
		}
		catch(Exception e){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(path="/getNumeroEleveSuivant/{idEleve}/{idEval}", produces=MimeTypeUtils.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getNumeroEleveSuivant_Ajax(HttpServletRequest request,
			@PathVariable(name="idEleve") Long idEleve,
			@PathVariable(name="idEval") Long idEval){
		long reponse = 0;
		Evaluations evalConcerne = usersService.findEvaluations(idEval);
		Sequences seq = evalConcerne.getSequence();
		Eleves elvSvt = usersService.findElevesSuivant(idEleve, seq.getIdPeriodes());
		if(elvSvt == null) {
			reponse = -1;
		}
		else{
			reponse = elvSvt.getIdEleves();
		}
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(new String(""+reponse), HttpStatus.OK);
		return responseEntity;
	}

	
	
	
}
