/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Intendant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface IntendantRepository extends JpaRepository<Intendant, Long> {
	
	public Intendant findByNumeroInt(int numeroInt);
	
	public Intendant findByNumcniPers(String numcniPers);
	
	public Intendant findByIdUsers(Long idUsers);
	
	public Page<Intendant> findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDescNumeroIntAsc(Pageable pageable);
	
	public List<Intendant> findAllByOrderByNumeroIntAsc();
	
	public List<Intendant> findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDescNumeroIntAsc();
	
}
