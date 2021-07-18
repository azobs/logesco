/**
 * 
 */
package org.logesco.form;

import javax.validation.constraints.NotNull;

/**
 * @author cedrickiadjeu
 *
 */
public class UpdateEmblemeEtabForm {

	@NotNull
	private Long idEtab;
	private String logoEtab;//image vectorielle de preference
	private String banniereEtab;//image matricielle de preference
	/**
	 * @return the logoEtab
	 */
	public String getLogoEtab() {
		return logoEtab;
	}
	/**
	 * @param logoEtab the logoEtab to set
	 */
	public void setLogoEtab(String logoEtab) {
		this.logoEtab = logoEtab;
	}
	/**
	 * @return the banniereEtab
	 */
	public String getBanniereEtab() {
		return banniereEtab;
	}
	/**
	 * @param banniereEtab the banniereEtab to set
	 */
	public void setBanniereEtab(String banniereEtab) {
		this.banniereEtab = banniereEtab;
	}
	/**
	 * @return the idEtab
	 */
	public Long getIdEtab() {
		return idEtab;
	}
	/**
	 * @param idEtab the idEtab to set
	 */
	public void setIdEtab(Long idEtab) {
		this.idEtab = idEtab;
	}
	
	
	
}
