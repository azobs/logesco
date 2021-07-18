/**
 * 
 */
package org.logesco.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.logesco.entities.Etablissement;
import org.logesco.entities.Specialites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cedrickiadjeu
 *
 */
@Service
@Transactional
public class AdminServiceExportImpl implements IAdminServiceExport {

	@Autowired
	private IAdminService adminService;
	
	
	@Override
	public List<Map<String, Object>> exportlisteSpecialite() {
		/*log.log(Level.DEBUG, "****** LANCEMENT DE LA METHODE exportlisteSpecialite()");*/
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		/*
		 * On place dans cette liste le premier dictionnaire contenant les données d'entêtes
		 * Pour cela il faut recuperer les éléments de l'établissement
		 */
		Etablissement etab = adminService.getEtablissement();
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("ministereTutele", etab.getMinisteretuteleEtab());
		item1.put("nomsEtab", etab.getNomsEtab()+"("+etab.getAliasEtab()+")");
		item1.put("deviseEtab", etab.getDeviseEtab());
		item1.put("matriculeEtab", etab.getMatriculeEtab());
		item1.put("adresseEtab", "BP: "+etab.getBpEtab()+" TEL: "+etab.getNumtel1Etab()+"/"+
				etab.getNumtel2Etab()+" EMAIL: "+etab.getEmailEtab());
		
		result.add(item1);
		
		/*
		 * Il faut la liste des specialités enregistrées dans l'ordre alphabetique des codes puis des libelles
		 * pour construire le dictionnaire qu'on va envoyer au controller pour l'affichage du pdf
		 */
		List<Specialites> listofSpecialite = adminService.findAllSpecialites();
		
		for(Specialites specialite : listofSpecialite){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("codeSpecialite", specialite.getCodeSpecialite());
			item.put("libelleSpecialite", specialite.getLibelleSpecialite());
			
			result.add(item);
		}
		
		/*log.log(Level.DEBUG, "****** FIN DE L'EXECUTION DE LA METHODE exportlisteSpecialite()");*/
		return result;
	}

}
