/**
 * 
 */
package org.logesco.dao;

import java.util.List;

import org.logesco.entities.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author cedrickiadjeu
 *
 */
public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Long> {
	@Query("select u from Utilisateurs u where u.username=:x")
	public Utilisateurs getUtilisateursByUsername(@Param("x")String username);
	
	public Utilisateurs findByUsername(String username);
	
	@Query("select u from Utilisateurs u order by u.username")
	public List<Utilisateurs> findAllUtilisateurs();
}
