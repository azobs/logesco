/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Annee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author cedrickiadjeu
 *
 */
public interface AnneeRepository extends JpaRepository<Annee, Long> {
	public List<Annee> findAllByOrderByIntituleAnneeDesc();
	public Annee findByIntituleAnnee(String intituleAnnee);
	public Page<Annee> findAllByOrderByIntituleAnneeDesc(Pageable pageable);
	
	@Query("SELECT a FROM Annee a WHERE a.etatPeriodes=:x")
	public Annee findAnneeActive(@Param("x")boolean active);
}
