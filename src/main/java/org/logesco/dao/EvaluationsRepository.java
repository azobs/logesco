/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Evaluations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author cedrickiadjeu
 *
 */
public interface EvaluationsRepository extends JpaRepository<Evaluations, Long> {

	public List<Evaluations> findAllByCoursIdCoursAndSequenceIdPeriodesOrderByDateenregEvalAsc(Long idCours, Long idPeriodes);
	
	@Query("SELECT eval FROM Evaluations eval WHERE "
			+ "eval.cours.idCours=:idCours "
			+" AND "
			+ "eval.sequence.idPeriodes=:idSequence "
			+" AND "
			+ "eval.typeEval=:typeEval ")
	public Evaluations findByCoursSequenceTypeEval(@Param("idCours") Long idCours, @Param("idSequence") Long idSequence, 
			@Param("typeEval") String typeEval);
	
}
