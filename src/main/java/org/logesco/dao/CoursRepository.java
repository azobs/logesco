/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Cours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author cedrickiadjeu
 *
 */
public interface CoursRepository extends JpaRepository<Cours, Long> {

	public Page<Cours> findAllByOrderByMatiereIntituleMatiereAsc(Pageable pageable);
	
	public Page<Cours> findAllByOrderByMatiereCodeMatiereAscCodeCoursAsc(Pageable pageable);
	
	@Query("SELECT c FROM Cours c ORDER BY "
			+ "c.classe.codeClasses ASC,"
			+ "c.classe.specialite.codeSpecialite ASC,"
			+ "c.classe.numeroClasses ASC,"
			+ "c.matiere.codeMatiere ASC,"
			+ "c.codeCours ASC")
	public Page<Cours> findAllCoursOrderByClasseMatiere(Pageable pageable);
	
	public Page<Cours> findAllByClasseIdClassesOrderByMatiereCodeMatiereAscCodeCoursAsc(Pageable pageable, Long idClasses);
	
	public List<Cours> findAllByClasseIdClassesOrderByMatiereCodeMatiereAscCodeCoursAsc(Long idClasses);
	
	public Page<Cours> findAllByClasseIdClassesAndProffesseurIdUsersOrderByMatiereCodeMatiereAscCodeCoursAsc(
			Pageable pageable, Long idClasses, Long idProf);
	
	public List<Cours> findAllByClasseIdClassesAndProffesseurIdUsersOrderByMatiereCodeMatiereAscCodeCoursAsc(Long idClasses, Long idProf);
	
	public Cours	findByCodeCours(String codeCours);
	
}
