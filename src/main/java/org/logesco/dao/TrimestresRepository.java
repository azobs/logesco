/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Trimestres;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author cedrickiadjeu
 *
 */
public interface TrimestresRepository extends JpaRepository<Trimestres, Long> {
	/**
	 * Cette méthode retourne la liste de tous les trimestres qui sont dans la base de données en 
	 * les rangeant dans l'ordre décroissant des intitule des années qui les contient. 
	 * @return
	 */
	public List<Trimestres> findAllByOrderByAnneeIntituleAnneeDesc();
	
	/**
	 * Cette méthode retourne la liste de tous les trimestres qui sont dans la base de données en 
	 * les rangeant dans l'ordre décroissant des intitule des années qui les contient et ensuite par
	 * ordre croissant des numero de trimestre. 
	 * @return
	 */
	public List<Trimestres> findAllByOrderByAnneeIntituleAnneeDescNumeroTrimAsc();
	
	/************************************************************************************************
	 * Cette méthode retourne la liste de tous les trimestres qui sont dans la base de données page par page en 
	 * les rangeant dans l'ordre décroissant des intitule des années qui les contient et ensuite par
	 * ordre croissant des numero de trimestre. 
	 * @return
	 */
	public Page<Trimestres> findAllByOrderByAnneeIntituleAnneeDescNumeroTrimAsc(Pageable pageable);
	
	/*******************
	 * Retourne la liste des trimestres qui ont pour numero celui passe en parametre
	 ****************/
	public List<Trimestres> findByNumeroTrim(int numeroTrim);
	
	/*****************************************************************************
	 * Retourne la liste des trimestres d'une année page par page
	 * @param pageable
	 * @param idPeriodes
	 * @return
	 */
	public Page<Trimestres> findAllByAnneeIdPeriodesOrderByNumeroTrimAsc(Pageable pageable, 
			Long idPeriodes);
	
	/****************************************************************************
	 * Retourne la liste des trimestres d'une année page par page
	 * @param pageable
	 * @param etatPeriodes
	 * @param idPeriodes
	 * @return
	 */
	@Query("select t from Trimestres t where t.etatPeriodes=:x and t.annee.idPeriodes=:y "
			+ "order by t.numeroTrim asc")
	public Page<Trimestres> findAllTrimestreActive(Pageable pageable,
			@Param("x")boolean etatPeriodes, @Param("y")Long idPeriodes);
	
	
	/*******************
	 *  Retourne la liste de tous les trimestres d'une année
	 * @param idPeriodes
	 * @return
	 */
	public List<Trimestres> findAllByAnneeIdPeriodesOrderByNumeroTrimAsc(Long idPeriodes);
	
	/****************************************
	 * Retourne la liste des trimestres d'une année page par page
	 * @param etatPeriode
	 * @param idPeriodes
	 * @return
	 */
	@Query("select t from Trimestres t where t.etatPeriodes=:x and t.annee.idPeriodes=:y "
			+ " order by t.numeroTrim asc")
	public List<Trimestres> findAllTrimestreActive(@Param("x")boolean etatPeriode,	
			@Param("y")Long idPeriodes);
	
	
	/**
	 * Retourne le trimestre donc l'identifiant est passé en paramètre
	 * @param idPeriodes
	 * @return
	 */
	public Trimestres findByIdPeriodes(Long idPeriodes);
	
}
