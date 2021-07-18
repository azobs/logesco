/**
 * 
 */
package org.logesco.dao;

import java.util.Date;

import org.logesco.entities.Personnels;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface PersonnelsRepository extends JpaRepository<Personnels, Long> {
	
	public Personnels findByNumcniPers(String numcniPers);
	
	public Personnels findByNomsPersAndPrenomsPersAndDatenaissPers(
			String nomPers, String prenomsPers, Date datenaisspers);
	
}
