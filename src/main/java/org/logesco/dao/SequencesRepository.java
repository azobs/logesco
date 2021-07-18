/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Sequences;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface SequencesRepository extends JpaRepository<Sequences, Long> {

	/**********************************************************************
	 * On doit faire la liste de toutes les séquences enregistrées classées par
	 * ordre descendant des intitules de leurs années d'appartenance 
	 * (TrimestreAnneeIntituleAnneeDesc) car Sequences contient la propriété trimestre
	 * de type Trimestres qui a son tour contient la propriété annee de type Annee
	 * qui a son tour contient donc le champ intituleAnnee
	 *  puis dans cette année d'appartenance les séquences trouvé doivent être classé
	 * par ordre croissant des numéroTrim(TrimestreNumeroTrimAsc) car Sequences contient
	 * la propriété trimestre de type Trimestres qui lui contient la propriété numerotrim et 
	 * enfin par ordre croissant des numéroSeq dans ce trimestre(NumeroSeqAsc)
	 ***********************************************************************/
	public List<Sequences> 
	findAllByOrderByTrimestreAnneeIntituleAnneeDescTrimestreNumeroTrimAscNumeroSeqAsc();
	
	/****************************************************
	 * Cette methode permet de retourner la liste des séquences d'un trimestre dans l'ordre croissant des numero de sequence
	 * @param idPeriodes
	 * @return
	 */
	public List<Sequences> 
	findByTrimestreIdPeriodesOrderByTrimestreNumeroTrimAscNumeroSeqAsc(Long idPeriodes);
	
	public Page<Sequences> 
	findAllByOrderByTrimestreAnneeIntituleAnneeDescTrimestreNumeroTrimAscNumeroSeqAsc(Pageable pageable);
	
	public Sequences findByIdPeriodes(Long idPeriodes);
	
}
