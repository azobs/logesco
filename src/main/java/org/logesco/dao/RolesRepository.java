/**
 * 
 */
package org.logesco.dao;

import org.logesco.entities.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author cedrickiadjeu
 *
 */
public interface RolesRepository extends JpaRepository<Roles, String> {
	@Query("SELECT r FROM Roles r ORDER BY r.role ASC")
	public Page<Roles> findAll(Pageable pageable);
	
	public Roles findByRole(String role);
}
