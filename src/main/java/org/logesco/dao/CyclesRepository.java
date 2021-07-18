/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Cycles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cedrickiadjeu
 *
 */
public interface CyclesRepository extends JpaRepository<Cycles, Long> {
	/**
	 * Cette méthode est implicitement implémenté par Spring
	 * Elle recherche tous les Cycles (findAllBy) et les retourne 
	 * dans l'ordre ascendant des numéro d'ordre (OrderByNumeroOrdreCyclesAsc)
	 * 
	 * donc ca execute implicitement la requete suivante
	 * @Query("select c from Cycles c order by c.numeroOrdreCycles ASC")
	 * @return
	 */
	public List<Cycles> findAllByOrderByNumeroOrdreCyclesAsc();
	
	/**
	 * Recherche un cycle par son numero d'ordre
	 */
	public Cycles findByNumeroOrdreCycles(int numeroOrdreCycles);
	
	/**
	 * Recherche un cycle par son code
	 */
	public Cycles findByCodeCycles(String codeCycles);
}
