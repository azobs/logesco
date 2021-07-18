/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Classes;
import org.logesco.entities.Specialites;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author cedrickiadjeu
 *
 */
public interface ClassesRepository extends JpaRepository<Classes, Long> {
	
	public Classes findByCodeClassesAndNumeroClassesAndSpecialite
				(String codeClasses, String numeroClasses, Specialites specialite);
	
	public Page<Classes> findAllByOrderByCodeClassesAscSpecialiteCodeSpecialiteAscNumeroClassesAsc(
				Pageable pageable);
	
	@Query("SELECT c FROM Classes c ORDER BY c.codeClasses ASC, "
			+ " c.specialite.codeSpecialite ASC,  c.numeroClasses ASC")
	public Page<Classes> findAll(Pageable pageable);
	
	public List<Classes> findAllByOrderByCodeClassesAscSpecialiteCodeSpecialiteAscNumeroClassesAsc();
	
	@Query("SELECT c FROM Classes c ORDER BY c.codeClasses ASC, "
			+ " c.specialite.codeSpecialite ASC,  c.numeroClasses ASC")
	public List<Classes> findAll();
	
}
