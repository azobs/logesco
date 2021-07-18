/**
 * 
 */
package org.logesco.dao;

import org.logesco.entities.CompteInscription;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface CompteInscriptionRepository extends JpaRepository<CompteInscription, Long> {
	
}
