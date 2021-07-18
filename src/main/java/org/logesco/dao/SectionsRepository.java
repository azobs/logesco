/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Sections;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface SectionsRepository extends JpaRepository<Sections, Long> {
	
	public List<Sections> findAllByOrderByIntituleSectionsAsc();
	
	/**
	 * Recherche une section par son code
	 */
	public Sections findByCodeSections(String codeSections);

}
