/**
 * 
 */
package org.logesco.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
public class UpdateRolesForm {
	
	@NotEmpty
	@Size(min = 1, max = 17)
	private String role;
	@NotEmpty
	@Size(min = 1, max = 17)
	private String aliasRole;
	
	private String roleAModif;
	
	@NotNull
	@NotEmpty
	private String enregOrmodif;
	
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the aliasRole
	 */
	public String getAliasRole() {
		return aliasRole;
	}
	/**
	 * @param aliasRole the aliasRole to set
	 */
	public void setAliasRole(String aliasRole) {
		this.aliasRole = aliasRole;
	}
	/**
	 * @return the roleAModif
	 */
	public String getRoleAModif() {
		return roleAModif;
	}
	/**
	 * @param roleAModif the roleAModif to set
	 */
	public void setRoleAModif(String roleAModif) {
		this.roleAModif = roleAModif;
	}
	/**
	 * @return the enregOrmodif
	 */
	public String getEnregOrmodif() {
		return enregOrmodif;
	}
	/**
	 * @param enregOrmodif the enregOrmodif to set
	 */
	public void setEnregOrmodif(String enregOrmodif) {
		this.enregOrmodif = enregOrmodif;
	}
	
	
	
}
