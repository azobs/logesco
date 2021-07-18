/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Censeurs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface CenseursRepository extends JpaRepository<Censeurs, Long> {
	public Censeurs findByNumeroCens(int numeroCens);
	
	public Censeurs findByNumcniPers(String numcniPers);
	
	public Page<Censeurs> findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDescNumeroCensAsc(Pageable pageable);
	
	public Censeurs findByIdUsers(Long idUsers);
	
	public List<Censeurs> findAllByOrderByNumeroCensAsc();
	
	public List<Censeurs> findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDescNumeroCensAsc();
	
}
