/**
 * 
 */
package org.logesco.dao;

import java.util.Date;
import java.util.List;

import org.logesco.entities.Eleves;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author cedrickiadjeu
 *
 */
public interface ElevesRepository extends JpaRepository<Eleves, Long> {

	public Eleves findByMatriculeEleves(String matriculeEleves);
	
	public Eleves findByNomsElevesAndPrenomsElevesAndDatenaissEleves(String nomsEleves, String prenomsEleves, 
			Date datenaissEleves);
	
	public Page<Eleves> findAllByOrderByNomsElevesAscPrenomsElevesAscDatenaissElevesAscClasseCodeClassesAsc
		(Pageable pageable);
	
	@Query("SELECT e FROM Eleves e ORDER BY e.nomsEleves ASC, "
			+ " e.prenomsEleves ASC,  e.datenaissEleves ASC,  e.classe.codeClasses DESC")
	public Page<Eleves> findAll(Pageable pageable);
	
	@Query("SELECT e FROM Eleves e WHERE e.classe.idClasses=:idClasses ORDER BY e.nomsEleves ASC, "
			+ " e.prenomsEleves ASC,  e.datenaissEleves ASC")
	public Page<Eleves> findPageElevesOfClasse(@Param("idClasses") Long idClasses,	Pageable pageable);
	
	@Query("SELECT e FROM Eleves e WHERE e.nomsEleves LIKE :motifRech OR e.prenomsEleves LIKE :motifRech "
			+ "ORDER BY e.nomsEleves ASC, "
			+ " e.prenomsEleves ASC, e.datenaissEleves ASC")
	public Page<Eleves> findPageElevesSelonMotif(@Param("motifRech") String motifRech,	Pageable pageable);
	
	@Query("SELECT e FROM Eleves e WHERE e.classe.idClasses=:idClasses AND e.compteInscription.montant>=:montantMin"
			+ " ORDER BY e.nomsEleves ASC, "
			+ " e.prenomsEleves ASC,  e.datenaissEleves ASC")
	public Page<Eleves> findPageElevesDefOfClasse(@Param("idClasses") Long idClasses,	
			@Param("montantMin") double montantMin,	Pageable pageable);
	
	@Query("SELECT e FROM Eleves e WHERE e.classe.idClasses=:idClasses ORDER BY e.nomsEleves ASC, "
			+ " e.prenomsEleves ASC,  e.datenaissEleves ASC")
	public List<Eleves> findListElevesOfClasse(@Param("idClasses") Long idClasses);
	
	@Query("SELECT e FROM Eleves e WHERE e.classe.idClasses=:idClasses AND e.compteInscription.montant>=:montantMin"
			+ " ORDER BY e.nomsEleves ASC, "
			+ " e.prenomsEleves ASC,  e.datenaissEleves ASC")
	public List<Eleves> findListElevesDefOfClasse(@Param("idClasses") Long idClasses, 
			@Param("montantMin") double montantMin);
	
	@Query("SELECT e FROM Eleves e WHERE e.classe.idClasses=:idClasses AND e.etatInscEleves=:idEtat ORDER BY e.nomsEleves ASC, "
			+ " e.prenomsEleves ASC,  e.datenaissEleves ASC")
	public Page<Eleves> findPageElevesOfClasse(@Param("idClasses") Long idClasses,	
			@Param("idEtat") String idEtat, Pageable pageable);
	
	@Query("SELECT e FROM Eleves e WHERE e.classe.idClasses=:idClasses AND e.etatInscEleves=:idEtat ORDER BY e.nomsEleves ASC, "
			+ " e.prenomsEleves ASC,  e.datenaissEleves ASC")
	public List<Eleves> findListElevesOfClasse(@Param("idClasses") Long idClasses, @Param("idEtat") String idEtat);
	
	public Page<Eleves> findByClasseIdClassesOrderByNomsElevesAscPrenomsElevesAscDatenaissElevesAsc
		(Long idClasses,	Pageable pageable);
	
}
