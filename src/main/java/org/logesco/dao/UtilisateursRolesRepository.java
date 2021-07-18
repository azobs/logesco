/**
 * 
 */
package org.logesco.dao;

import org.logesco.entities.UtilisateursRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author cedrickiadjeu
 *
 */
public interface UtilisateursRolesRepository extends JpaRepository<UtilisateursRoles, Long> {
	
	@Query("select ur from UtilisateursRoles ur where ur.users.idUsers=:x and ur.roleAssocie.role=:y")
	public UtilisateursRoles getUtilisateursRoles(@Param("x")Long idUsers, @Param("y")String role);
	
}
