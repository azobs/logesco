/**
 * 
 */
package org.logesco.dao;

import org.logesco.entities.RapportDAbsence;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface RapportDAbsenceRepository extends JpaRepository<RapportDAbsence, Long> {
	
	public RapportDAbsence findByEleveIdElevesAndSequenceIdPeriodes(Long idEleves, Long idPeriodes);
	
}
