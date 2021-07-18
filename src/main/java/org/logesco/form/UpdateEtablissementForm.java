/**
 * 
 */
package org.logesco.form;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cedrickiadjeu
 *
 */
public class UpdateEtablissementForm {
	@NotNull
	private Long idEtab;
	@NotNull
	@NotEmpty
	@Size(min= 4)
	private String nomsEtab;
	@NotNull
	@NotEmpty
	@Size(min= 4, max= 20)
	private String aliasEtab;
	@NotNull
	@NotEmpty
	@Size(min= 2, max= 20)
	private String matriculeEtab;
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 5)
	private String bpEtab;
	@NotNull
	@NotEmpty
	@Size(min = 9, max = 13)
	private String numtel1Etab;
	private String numtel2Etab;
	@Email
	private String emailEtab;
	@NotNull
	@NotEmpty
	@Size(min= 2)
	private String ministeretuteleEtab;
	private String logoEtab;//image vectorielle de preference
	private String banniereEtab;//image matricielle de preference
	@NotNull
	@NotEmpty
	@Size(min = 5)
	private String deviseEtab;
	
	/***
	 * Debut des ajouts du 19-08-2018
	 */
	@NotNull
	@NotEmpty
	@Size(min= 4)
	private String nomsanglaisEtab;
	@NotNull
	@NotEmpty
	@Size(min= 4, max= 20)
	private String aliasnomanglaisEtab;  
	@NotNull
	@NotEmpty
	@Size(min= 2)
	private String aliasministeretuteleEtab;
	@NotNull
	@NotEmpty
	@Size(min= 2)
	private String ministeretuteleanglaisEtab;
	@NotNull
	@NotEmpty
	@Size(min= 2)
	private String aliasministeretuteleanglaisEtab;
	@NotNull
	@NotEmpty
	@Size(min = 5)
	private String deviseanglaisEtab;
	
	private String deleguationregtuteleEtab;
	
	private String deleguationregtuteleanglaisEtab;
	
	private String deleguationdeptuteleEtab;
	
	private String deleguationdeptuteleanglaisEtab;
	@NotNull
	@NotEmpty
	@Size(min = 2, max=7)
	private String codeMatriculeEtab;
	private String villeEtab;
	/***
	 * Fin des ajouts du 19-08-2018
	 */
	
	/**
	 * @return the nomsEtab
	 */
	public String getNomsEtab() {
		return nomsEtab;
	}
	/**
	 * @param nomsEtab the nomsEtab to set
	 */
	public void setNomsEtab(String nomsEtab) {
		this.nomsEtab = nomsEtab;
	}
	/**
	 * @return the matriculeEtab
	 */
	public String getMatriculeEtab() {
		return matriculeEtab;
	}
	/**
	 * @param matriculeEtab the matriculeEtab to set
	 */
	public void setMatriculeEtab(String matriculeEtab) {
		this.matriculeEtab = matriculeEtab;
	}
	/**
	 * @return the bpEtab
	 */
	public String getBpEtab() {
		return bpEtab;
	}
	/**
	 * @param bpEtab the bpEtab to set
	 */
	public void setBpEtab(String bpEtab) {
		this.bpEtab = bpEtab;
	}
	/**
	 * @return the numtel1Etab
	 */
	public String getNumtel1Etab() {
		return numtel1Etab;
	}
	/**
	 * @param numtel1Etab the numtel1Etab to set
	 */
	public void setNumtel1Etab(String numtel1Etab) {
		this.numtel1Etab = numtel1Etab;
	}
	/**
	 * @return the numtel2Etab
	 */
	public String getNumtel2Etab() {
		return numtel2Etab;
	}
	/**
	 * @param numtel2Etab the numtel2Etab to set
	 */
	public void setNumtel2Etab(String numtel2Etab) {
		this.numtel2Etab = numtel2Etab;
	}
	/**
	 * @return the emailEtab
	 */
	public String getEmailEtab() {
		return emailEtab;
	}
	/**
	 * @param emailEtab the emailEtab to set
	 */
	public void setEmailEtab(String emailEtab) {
		this.emailEtab = emailEtab;
	}
	/**
	 * @return the ministeretuteleEtab
	 */
	public String getMinisteretuteleEtab() {
		return ministeretuteleEtab;
	}
	/**
	 * @param ministeretuteleEtab the ministeretuteleEtab to set
	 */
	public void setMinisteretuteleEtab(String ministeretuteleEtab) {
		this.ministeretuteleEtab = ministeretuteleEtab;
	}
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
	 * @return the deviseEtab
	 */
	public String getDeviseEtab() {
		return deviseEtab;
	}
	/**
	 * @param deviseEtab the deviseEtab to set
	 */
	public void setDeviseEtab(String deviseEtab) {
		this.deviseEtab = deviseEtab;
	}
	/**
	 * @return the aliasEtab
	 */
	public String getAliasEtab() {
		return aliasEtab;
	}
	/**
	 * @param aliasEtab the aliasEtab to set
	 */
	public void setAliasEtab(String aliasEtab) {
		this.aliasEtab = aliasEtab;
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
	/**
	 * @return the nomsanglaisEtab
	 */
	public String getNomsanglaisEtab() {
		return nomsanglaisEtab;
	}
	/**
	 * @param nomsanglaisEtab the nomsanglaisEtab to set
	 */
	public void setNomsanglaisEtab(String nomsanglaisEtab) {
		this.nomsanglaisEtab = nomsanglaisEtab;
	}
	/**
	 * @return the aliasanglaisEtab
	 */
	public String getAliasanglaisEtab() {
		return aliasnomanglaisEtab;
	}
	/**
	 * @param aliasanglaisEtab the aliasanglaisEtab to set
	 */
	public void setAliasanglaisEtab(String aliasnomanglaisEtab) {
		this.aliasnomanglaisEtab = aliasnomanglaisEtab;
	}
	/**
	 * @return the aliasministeretuteleEtab
	 */
	public String getAliasministeretuteleEtab() {
		return aliasministeretuteleEtab;
	}
	/**
	 * @param aliasministeretuteleEtab the aliasministeretuteleEtab to set
	 */
	public void setAliasministeretuteleEtab(String aliasministeretuteleEtab) {
		this.aliasministeretuteleEtab = aliasministeretuteleEtab;
	}
	/**
	 * @return the ministeretuteleanglaisEtab
	 */
	public String getMinisteretuteleanglaisEtab() {
		return ministeretuteleanglaisEtab;
	}
	/**
	 * @param ministeretuteleanglaisEtab the ministeretuteleanglaisEtab to set
	 */
	public void setMinisteretuteleanglaisEtab(String ministeretuteleanglaisEtab) {
		this.ministeretuteleanglaisEtab = ministeretuteleanglaisEtab;
	}
	/**
	 * @return the aliasministeretuteleanglaisEtab
	 */
	public String getAliasministeretuteleanglaisEtab() {
		return aliasministeretuteleanglaisEtab;
	}
	/**
	 * @param aliasministeretuteleanglaisEtab the aliasministeretuteleanglaisEtab to set
	 */
	public void setAliasministeretuteleanglaisEtab(String aliasministeretuteleanglaisEtab) {
		this.aliasministeretuteleanglaisEtab = aliasministeretuteleanglaisEtab;
	}
	/**
	 * @return the deviseanglaisEtab
	 */
	public String getDeviseanglaisEtab() {
		return deviseanglaisEtab;
	}
	/**
	 * @param deviseanglaisEtab the deviseanglaisEtab to set
	 */
	public void setDeviseanglaisEtab(String deviseanglaisEtab) {
		this.deviseanglaisEtab = deviseanglaisEtab;
	}
	/**
	 * @return the aliasnomanglaisEtab
	 */
	public String getAliasnomanglaisEtab() {
		return aliasnomanglaisEtab;
	}
	/**
	 * @param aliasnomanglaisEtab the aliasnomanglaisEtab to set
	 */
	public void setAliasnomanglaisEtab(String aliasnomanglaisEtab) {
		this.aliasnomanglaisEtab = aliasnomanglaisEtab;
	}
	/**
	 * @return the deleguationregtuteleEtab
	 */
	public String getDeleguationregtuteleEtab() {
		return deleguationregtuteleEtab;
	}
	/**
	 * @param deleguationregtuteleEtab the deleguationregtuteleEtab to set
	 */
	public void setDeleguationregtuteleEtab(String deleguationregtuteleEtab) {
		this.deleguationregtuteleEtab = deleguationregtuteleEtab;
	}
	/**
	 * @return the deleguationregtuteleanglaisEtab
	 */
	public String getDeleguationregtuteleanglaisEtab() {
		return deleguationregtuteleanglaisEtab;
	}
	/**
	 * @param deleguationregtuteleanglaisEtab the deleguationregtuteleanglaisEtab to set
	 */
	public void setDeleguationregtuteleanglaisEtab(String deleguationregtuteleanglaisEtab) {
		this.deleguationregtuteleanglaisEtab = deleguationregtuteleanglaisEtab;
	}
	/**
	 * @return the deleguationdeptuteleEtab
	 */
	public String getDeleguationdeptuteleEtab() {
		return deleguationdeptuteleEtab;
	}
	/**
	 * @param deleguationdeptuteleEtab the deleguationdeptuteleEtab to set
	 */
	public void setDeleguationdeptuteleEtab(String deleguationdeptuteleEtab) {
		this.deleguationdeptuteleEtab = deleguationdeptuteleEtab;
	}
	/**
	 * @return the deleguationdeptuteleanglaisEtab
	 */
	public String getDeleguationdeptuteleanglaisEtab() {
		return deleguationdeptuteleanglaisEtab;
	}
	/**
	 * @param deleguationdeptuteleanglaisEtab the deleguationdeptuteleanglaisEtab to set
	 */
	public void setDeleguationdeptuteleanglaisEtab(String deleguationdeptuteleanglaisEtab) {
		this.deleguationdeptuteleanglaisEtab = deleguationdeptuteleanglaisEtab;
	}
	/**
	 * @return the codeMatriculeEtab
	 */
	public String getCodeMatriculeEtab() {
		return codeMatriculeEtab;
	}
	/**
	 * @param codeMatriculeEtab the codeMatriculeEtab to set
	 */
	public void setCodeMatriculeEtab(String codeMatriculeEtab) {
		this.codeMatriculeEtab = codeMatriculeEtab;
	}
	/**
	 * @return the villeEtab
	 */
	public String getVilleEtab() {
		return villeEtab;
	}
	/**
	 * @param villeEtab the villeEtab to set
	 */
	public void setVilleEtab(String villeEtab) {
		this.villeEtab = villeEtab;
	}
	
	
	
	
	
}
