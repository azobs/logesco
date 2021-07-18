/**
 * 
 */
package org.logesco.services;

import java.util.List;
import java.util.Map;

/**
 * @author cedrickiadjeu
 *
 */
public interface IAdminServiceExport {

	/*************************************************************************
	 * Liste des méthodes qui exporte les données en BD demandées par l'administrateur
	 **************************************************************************/
	
	/*********************************
	 * Exportation de la liste des specialités enregistrées dans la base de données
	 */
	public List<Map<String,Object>> exportlisteSpecialite();
	
}
