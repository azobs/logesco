/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;
import java.util.List;

/**
 * @author cedrickiadjeu
 *
 */
public class EleveBean2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    private List<EleveBean> listofEleve;

	/**
	 * 
	 */
	public EleveBean2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the listofEleve
	 */
	public List<EleveBean> getListofEleve() {
		return listofEleve;
	}

	/**
	 * @param listofEleve the listofEleve to set
	 */
	public void setListofEleve(List<EleveBean> listofEleve) {
		this.listofEleve = listofEleve;
	}

	
	
}
