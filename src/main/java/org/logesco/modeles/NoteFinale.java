/**
 * 
 */
package org.logesco.modeles;

import java.io.Serializable;
import java.text.ParseException;


/**
 * @author cedrickiadjeu
 *
 */
public class NoteFinale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double noteCC;
	private int pourcentageCC;
	private double noteDS;
	private int pourcentageDS;
	

	/**
	 * 
	 */
	public NoteFinale() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param noteCC
	 * @param pourcentageCC
	 * @param noteDS
	 * @param pourcentageDS
	 */
	public NoteFinale(double noteCC, int pourcentageCC, double noteDS, int pourcentageDS) {
		super();
		this.noteCC = noteCC;
		this.pourcentageCC = pourcentageCC;
		this.noteDS = noteDS;
		this.pourcentageDS = pourcentageDS;
	}

	/**
	 * @return the noteCC
	 */
	public double getNoteCC() {
		return noteCC;
	}

	/**
	 * @param noteCC the noteCC to set
	 */
	public void setNoteCC(double noteCC) {
		this.noteCC = noteCC;
	}

	/**
	 * @return the pourcentageCC
	 */
	public int getPourcentageCC() {
		return pourcentageCC;
	}

	/**
	 * @param pourcentageCC the pourcentageCC to set
	 */
	public void setPourcentageCC(int pourcentageCC) {
		this.pourcentageCC = pourcentageCC;
	}

	/**
	 * @return the noteDS
	 */
	public double getNoteDS() {
		return noteDS;
	}

	/**
	 * @param noteDS the noteDS to set
	 */
	public void setNoteDS(double noteDS) {
		this.noteDS = noteDS;
	}

	/**
	 * @return the pourcentageDS
	 */
	public int getPourcentageDS() {
		return pourcentageDS;
	}

	/**
	 * @param pourcentageDS the pourcentageDS to set
	 */
	public void setPourcentageDS(int pourcentageDS) {
		this.pourcentageDS = pourcentageDS;
	}
	
	public double calculNoteFinale(){
		 java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		double noteFinale = 0;
		double pourCC = this.pourcentageCC / 100;
		System.err.println("pourCC == "+pourCC+"");
		double noteFCC = this.noteCC * pourCC;
		System.err.println("noteFCC == "+noteFCC+"");
		double pourDS = this.pourcentageDS / 100;
		System.err.println("pourDS == "+pourDS+"");
		double noteFDS = this.noteDS * pourDS;
		System.err.println("noteFDS == "+noteFDS+"");
		
		noteFinale = (noteFCC + noteFDS)/2;
		System.err.println("noteFinalenoteFinale == "+noteFinale+"");
		
		try {
			noteFinale = df.parse(df.format(noteFinale)).doubleValue();
			System.err.println("noteFinaletrytrytry == "+noteFinale);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return noteFinale;
	}

	
	
	
}
