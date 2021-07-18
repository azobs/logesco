/**
 * 
 */
package org.logesco.dao;

import org.logesco.entities.Proffesseurs;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface ProffesseursRepository extends JpaRepository<Proffesseurs	, Long> {
	public Proffesseurs findByNumcniPers(String numcniPers);
	
	public Proffesseurs findByIdUsers(Long idUsers);
	
	
}
