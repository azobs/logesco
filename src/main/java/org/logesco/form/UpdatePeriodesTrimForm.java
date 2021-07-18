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
public class UpdatePeriodesTrimForm {
	
	@NotNull
	@NotEmpty
	@Size(min = 9, max = 9)
	private String intituleAnneeTrim;
	@NotNull
	private int numeroTrim;
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datedebutPeriodes;
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datefinPeriodes;
	/**
	 * @return the intituleAnneeTrim
	 */
	public String getIntituleAnneeTrim() {
		return intituleAnneeTrim;
	}
	/**
	 * @param intituleAnneeTrim the intituleAnneeTrim to set
	 */
	public void setIntituleAnneeTrim(String intituleAnneeTrim) {
		this.intituleAnneeTrim = intituleAnneeTrim;
	}
	/**
	 * @return the numeroTrim
	 */
	public int getNumeroTrim() {
		return numeroTrim;
	}
	/**
	 * @param numeroTrim the numeroTrim to set
	 */
	public void setNumeroTrim(int numeroTrim) {
		this.numeroTrim = numeroTrim;
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
