/**
 * 
 */
package org.logesco.dao;


import org.logesco.entities.Administrateurs;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface AdministrateursRepository extends JpaRepository<Administrateurs, Long> {
	
}
