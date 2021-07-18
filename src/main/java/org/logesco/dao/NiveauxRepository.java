/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Niveaux;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface NiveauxRepository extends JpaRepository<Niveaux, Long> {

	/**
	 * Cette méthode est implicitement implémenté par Spring
	 * Elle recherche tous les Niveaux (findAllBy) et les retourne 
	 * dans l'ordre ascendant des numéro d'ordre (OrderByNumeroOrdreNiveauxAsc)
	 * 
	 * donc ca execute implicitement la requete suivante
	 * @Query("select n from Niveaux n order by n.numeroOrdreNiveaux ASC")
	 * @return
	 */
	public List<Niveaux> findAllByOrderByNumeroOrdreNiveauxAsc();
	
	public Page<Niveaux> findAllByOrderByNumeroOrdreNiveauxAsc(Pageable pageable);
	
	/**
	 * Recherche un niveau à partir de son code en respectant les conventions pour spring data
	 * injecte lui même la requete hql qu'il faut exécuter
	 */
	public Niveaux findByCodeNiveaux(String codeNiveaux);
	
	/**
	 * Recherche un niveau à partir de son numero d'ordre en respectant les conventions 
	 * pour spring data injecte lui même la requete hql qu'il faut exécuter
	 */
	public Niveaux findByNumeroOrdreNiveaux(int numeroOrdreNiveau);
	
}
