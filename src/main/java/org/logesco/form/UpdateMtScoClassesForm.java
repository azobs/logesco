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
public class UpdateMtScoClassesForm {

	@NotNull
	private long idclasseAConfig;
	@NotNull
	private double montantScolarite;
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 20)
	private String labelclasseAConfig;
	private int numPageClasses;
	
	
	
	/**
	 * @return the idclasseAConfig
	 */
	public long getIdclasseAConfig() {
		return idclasseAConfig;
	}
	/**
	 * @param idclasseAConfig the idclasseAConfig to set
	 */
	public void setIdclasseAConfig(long idclasseAConfig) {
		this.idclasseAConfig = idclasseAConfig;
	}
	/**
	 * @return the montantScolarite
	 */
	public double getMontantScolarite() {
		return montantScolarite;
	}
	/**
	 * @param montantScolarite the montantScolarite to set
	 */
	public void setMontantScolarite(double montantScolarite) {
		this.montantScolarite = montantScolarite;
	}
	
	
	
	/**
	 * @return the labelclasseAConfig
	 */
	public String getLabelclasseAConfig() {
		return labelclasseAConfig;
	}
	/**
	 * @param labelclasseAConfig the labelclasseAConfig to set
	 */
	public void setLabelclasseAConfig(String labelclasseAConfig) {
		this.labelclasseAConfig = labelclasseAConfig;
	}
	/**
	 * @return the numPageClasses
	 */
	public int getNumPageClasses() {
		return numPageClasses;
	}
	/**
	 * @param numPageClasses the numPageClasses to set
	 */
	public void setNumPageClasses(int numPageClasses) {
		this.numPageClasses = numPageClasses;
	}
	
	

}
