/**
 * 
 */
package org.logesco.dao;

import org.logesco.entities.NotesEval;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface NotesEvalRepository extends JpaRepository<NotesEval, Long> {

	public NotesEval findByEleveIdElevesAndEvaluationIdEval(Long idEleves, Long idEval);
}
