/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.SG;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface SGRepository extends JpaRepository<SG, Long> {
	public SG findByNumeroSg(int numeroSg);
	
	public SG findByNumcniPers(String numcniPers);
	
	public SG findByIdUsers(Long idUsers);
	
	public Page<SG> findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDescNumeroSgAsc(Pageable pageable);
	
	public List<SG> findAllByOrderByNumeroSgAsc();
	
	public List<SG> findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDescNumeroSgAsc();
	
}
