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
public class UpdatePeriodesSeqForm {

	@NotNull
	private int numeroSeq;
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datedebutPeriodes;
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datefinPeriodes;
	@NotNull
	private int numeroTrim;
	@NotNull
	@NotEmpty
	@Size(min = 9, max = 9)
	private String intituleAnneeSeq;
	
	
	
	/**
	 * @return the numeroSeq
	 */
	public int getNumeroSeq() {
		return numeroSeq;
	}
	/**
	 * @param numeroSeq the numeroSeq to set
	 */
	public void setNumeroSeq(int numeroSeq) {
		this.numeroSeq = numeroSeq;
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
	 * @return the intituleAnneeSeq
	 */
	public String getIntituleAnneeSeq() {
		return intituleAnneeSeq;
	}
	/**
	 * @param intituleAnneeSeq the intituleAnneeSeq to set
	 */
	public void setIntituleAnneeSeq(String intituleAnneeSeq) {
		this.intituleAnneeSeq = intituleAnneeSeq;
	}
	
	
	
	
	
}
