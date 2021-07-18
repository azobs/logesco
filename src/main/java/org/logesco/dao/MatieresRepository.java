/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Matieres;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author cedrickiadjeu
 *
 */
public interface MatieresRepository extends JpaRepository<Matieres, Long> {
	public Page<Matieres> findAllByOrderByIntituleMatiereAscCodeMatiereAsc(Pageable pageable);
	
	public Matieres findByCodeMatiere(String codeMatiere);
	
	@Query("SELECT m FROM Matieres m ORDER BY m.intituleMatiere ASC, "
			+ " m.codeMatiere ASC")
	public List<Matieres> findAll();
	
}
