/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Specialites;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author cedrickiadjeu
 *
 */
public interface SpecialitesRepository extends JpaRepository<Specialites, Long> {
	@Query("SELECT sp FROM Specialites sp ORDER BY sp.codeSpecialite ASC")
	public Page<Specialites> findAll(Pageable pageable);
	
	public Specialites findByCodeSpecialite(String codeSpe);
	
	public List<Specialites> findAllByOrderByCodeSpecialiteAsc();
}
