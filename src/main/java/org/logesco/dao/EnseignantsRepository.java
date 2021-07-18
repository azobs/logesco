/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Enseignants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface EnseignantsRepository extends JpaRepository<Enseignants, Long> {
	
	public Enseignants findByNumcniPers(String numcniPers);
	
	public Enseignants findByIdUsers(Long idUsers);
	
	public Page<Enseignants> findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDesc(Pageable pageable);
	
	public List<Enseignants> findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDesc();
	
}
