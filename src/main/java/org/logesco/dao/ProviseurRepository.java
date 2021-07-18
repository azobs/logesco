/**
 * 
 */
package org.logesco.dao;

import org.logesco.entities.Proviseur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface ProviseurRepository extends JpaRepository<Proviseur, Long> {
	public Page<Proviseur> findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDesc(Pageable pageable);
}
