/**
 * 
 */
package org.logesco.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author cedrickiadjeu
 *
 */
public class UpdatePeriodesAnForm {

	@NotNull
	@NotEmpty
	@Size(min = 9, max = 9)
	private String intituleAnnee;
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datedebutPeriodes;
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datefinPeriodes;
	/**
	 * @return the intituleAnnee
	 */
	public String getIntituleAnnee() {
		return intituleAnnee;
	}
	/**
	 * @param intituleAnnee the intituleAnnee to set
	 */
	public void setIntituleAnnee(String intituleAnnee) {
		this.intituleAnnee = intituleAnnee;
	}
	/**
	 * @return the datedebutPeriodes
	 */
	public Date getDatedebutPeriodes() {
		return datedebutPeriodes;
	}
	/**
	 * @param datedebutPeriodes the datedebutPeriodes to set
	 */
	public void setDatedebutPeriodes(Date datedebutPeriodes) {
		this.datedebutPeriodes = datedebutPeriodes;
	}
	/**
	 * @return the datefinPeriodes
	 */
	public Date getDatefinPeriodes() {
		return datefinPeriodes;
	}
	/**
	 * @param datefinPeriodes the datefinPeriodes to set
	 */
	public void setDatefinPeriodes(Date datefinPeriodes) {
		this.datefinPeriodes = datefinPeriodes;
	}
	
	
	
}
