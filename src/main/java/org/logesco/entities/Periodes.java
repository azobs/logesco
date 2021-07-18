/**
 * 
 */
package org.logesco.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author cedrickiadjeu
 *
 */
@Entity
@Table(name="periodes")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typePeriodes", 
discriminatorType=DiscriminatorType.STRING, length=15)
public abstract class Periodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idPeriodes;
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datedebutPeriodes;
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datefinPeriodes;
	@NotNull
	private boolean etatPeriodes;

	/**
	 * 
	 */
	public Periodes() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param datedebutPeriodes
	 * @param datefinPeriodes
	 * @param etatPeriodes
	 */
	public Periodes(Date datedebutPeriodes, Date datefinPeriodes, boolean etatPeriodes) {
		super();
		this.datedebutPeriodes = datedebutPeriodes;
		this.datefinPeriodes = datefinPeriodes;
		this.etatPeriodes = etatPeriodes;
	}

	/**
	 * @return the idPeriodes
	 */
	public Long getIdPeriodes() {
		return idPeriodes;
	}

	/**
	 * @param idPeriodes the idPeriodes to set
	 */
	public void setIdPeriodes(Long idPeriodes) {
		this.idPeriodes = idPeriodes;
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
	 * @return the etatPeriodes
	 */
	public boolean isEtatPeriodes() {
		return etatPeriodes;
	}

	/**
	 * @param etatPeriodes the etatPeriodes to set
	 */
	public void setEtatPeriodes(boolean etatPeriodes) {
		this.etatPeriodes = etatPeriodes;
	}
	
	

}
