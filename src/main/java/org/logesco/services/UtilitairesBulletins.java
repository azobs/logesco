/**
 * 
 */
package org.logesco.services;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.logesco.entities.Annee;
import org.logesco.entities.Classes;
import org.logesco.entities.Cours;
import org.logesco.entities.Eleves;
import org.logesco.entities.NotesEval;
import org.logesco.entities.Sequences;
import org.logesco.entities.Trimestres;
import org.logesco.modeles.LigneAnnuelGroupeCours;
import org.logesco.modeles.LigneSequentielGroupeCours;
import org.logesco.modeles.LigneTrimestrielGroupeCours;
import org.logesco.modeles.NoteFinaleCours;
import org.logesco.modeles.RapportAnnuelClasse;
import org.logesco.modeles.RapportAnnuelCours;
import org.logesco.modeles.RapportSequentielClasse;
import org.logesco.modeles.RapportSequentielCours;
import org.logesco.modeles.RapportTrimestrielClasse;
import org.logesco.modeles.RapportTrimestrielCours;
import org.logesco.modeles.SousRapport1ConseilBean;
import org.logesco.modeles.SousRapport2ConseilBean;
import org.logesco.modeles.SousRapport3ConseilBean;

/**
 * @author cedrickiadjeu
 *
 */
public class UtilitairesBulletins implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UtilitairesBulletins() {
		// TODO Auto-generated constructor stub
	}
	
public static List<Eleves> getListofeleveTrieparordrealphabetique(List<Eleves> listofEleves){
		
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {

			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n = 0;

				if(arg0.getNomsEleves().toLowerCase().compareTo(arg1.getNomsEleves().toLowerCase()) < 0) n = -1;

				if(arg0.getNomsEleves().toLowerCase().compareTo(arg1.getNomsEleves().toLowerCase()) > 0) n = 1;

				if(arg0.getNomsEleves().toLowerCase().compareTo(arg1.getNomsEleves().toLowerCase()) == 0) {
					if(arg0.getPrenomsEleves().toLowerCase().compareTo(arg1.getPrenomsEleves().toLowerCase()) < 0) n = -1;

					if(arg0.getPrenomsEleves().toLowerCase().compareTo(arg1.getPrenomsEleves().toLowerCase()) > 0) n = 1;

					if(arg0.getPrenomsEleves().toLowerCase().compareTo(arg1.getPrenomsEleves().toLowerCase()) == 0) {
						if(arg0.getDatenaissEleves().compareTo(arg1.getDatenaissEleves()) < 0) n = -1;

						if(arg0.getDatenaissEleves().compareTo(arg1.getDatenaissEleves()) > 0) n = 1;
					}
				}

				return n;
			}

		};
		
		Collections.sort((List<Eleves>)listofEleves, monComparator);

		return listofEleves;
	}
	
	
	public static Collection<Eleves> getMoyenneSequentielOrdreDecroissant_static(Classes classe, Sequences sequence){
		UtilitairesBulletins usi = new UtilitairesBulletins();
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {
			UtilitairesBulletins usi = new UtilitairesBulletins();
			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n=0;
				if(usi.getMoyenneSequentiel(arg0, sequence)>=0 && usi.getMoyenneSequentiel(arg1, sequence)>=0){
					if(usi.getMoyenneSequentiel(arg0, sequence)>usi.getMoyenneSequentiel(arg1, sequence)) n=-1;
					
					if(usi.getMoyenneSequentiel(arg0, sequence)<usi.getMoyenneSequentiel(arg1, sequence)) n=1;
				}
				return n;
			}
			
		};
		
		List<Eleves> listofElevesayantcomposeaumoinsunefois = 
				usi.getListofEleveAyantComposeAuMoinsUneFoisDansSequence(classe, sequence);
		Collections.sort((List<Eleves>)listofElevesayantcomposeaumoinsunefois, monComparator);
		
		return listofElevesayantcomposeaumoinsunefois;
	}
	
	
	public static Collection<Eleves> getMoyenneTrimestrielOrdreDecroissant1(Classes classe, Trimestres trimestre){
		UtilitairesBulletins usi = new UtilitairesBulletins();
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {
			UtilitairesBulletins usi = new UtilitairesBulletins();
			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n=0;
				if(usi.getMoyenneTrimestriel(arg0, trimestre)>=0 && usi.getMoyenneTrimestriel(arg1, trimestre)>=0){
					if(usi.getMoyenneTrimestriel(arg0, trimestre)>usi.getMoyenneTrimestriel(arg1, trimestre)) n=-1;
					
					if(usi.getMoyenneTrimestriel(arg0, trimestre)<usi.getMoyenneTrimestriel(arg1, trimestre)) n=1;
				}
				return n;
			}
			
		};
		
		List<Eleves> listofElevesayantcomposeaumoinsunefois = 
				usi.getListofEleveAyantComposeAuMoinsUneFoisDansTrimestre(classe, trimestre);
		Collections.sort((List<Eleves>)listofElevesayantcomposeaumoinsunefois, monComparator);
		
		return listofElevesayantcomposeaumoinsunefois;
	}
	
	public static Collection<Eleves> getMoyenneAnnuelOrdreDecroissant1(Classes classe, Annee annee){
		UtilitairesBulletins usi = new UtilitairesBulletins();
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {
			UtilitairesBulletins usi = new UtilitairesBulletins();

			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n=0;
				if(usi.getMoyenneAnnuel(arg0, annee)>=0 && usi.getMoyenneAnnuel(arg1, annee)>=0){
					if(usi.getMoyenneAnnuel(arg0, annee)>usi.getMoyenneAnnuel(arg1, annee)) n=-1;
					
					if(usi.getMoyenneAnnuel(arg0, annee)<usi.getMoyenneAnnuel(arg1, annee)) n=1;
				}
				return n;
			}
		};
		
		List<Eleves> listofElevesayantcomposeaumoinsunefois = 
				usi.getListofEleveAyantComposeAuMoinsUneFoisDansAnnee(classe, annee);
		Collections.sort((List<Eleves>)listofElevesayantcomposeaumoinsunefois, monComparator);
		
		return listofElevesayantcomposeaumoinsunefois;
	}

	
	
	public String calculAppreciation(Double note, String lang){
		String appreciation = "";
		if(note == null) return appreciation;
		//Double note = new Double(this.getNote_seq_g1());
		if (note <= 3.0) {
			appreciation = "NUL"+"(F)";
			appreciation = lang.equalsIgnoreCase("fr")==true?"NUL":"NULL"+" (F)";
		} else if((note >3.0) && (note < 6.0))  {
			appreciation = "MAUVAIS";
			appreciation = lang.equalsIgnoreCase("fr")==true?"MAUVAIS":"BAD"+" (F)";
		}else if((note >=6.0) && (note < 7.0))  {
			appreciation = "FAIBLE";
			appreciation = lang.equalsIgnoreCase("fr")==true?"FAIBLE":"POOR"+" (F)";
		}else if((note >=7.0) && (note < 8.0))  {
			appreciation = "FAIBLE";
			appreciation = lang.equalsIgnoreCase("fr")==true?"FAIBLE":"POOR"+" (E)";
		}else if((note >=8.0) && (note < 9.0))  {
			appreciation = "INSUFFISANT";
			appreciation = lang.equalsIgnoreCase("fr")==true?"INSUFFISANT":"INSUFFICIENT"+" (D/C-)";
		}else if((note >=9.0) && (note < 10.0))  {
			appreciation = "MEDIOCRE";
			appreciation = lang.equalsIgnoreCase("fr")==true?"MEDIOCRE":"MEDIOCRE"+" (C/C+)";
		}else if((note >=10.0) && (note < 11.0))  {
			appreciation = "PASSABLE";
			appreciation = lang.equalsIgnoreCase("fr")==true?"PASSABLE":"AVERAGE"+" (B-/B)";
		}else if((note >=11.0) && (note < 12.0))  {
			appreciation = "MOYEN";
			appreciation = lang.equalsIgnoreCase("fr")==true?"MOYEN":"MEDIUM"+" (B-/B)";
		}else if((note >=12.0) && (note < 14.0))  {
			appreciation = "ASSEZ BIEN";
			appreciation = lang.equalsIgnoreCase("fr")==true?"ASSEZ BIEN":"FAIRLY GOOD"+" (B+/A-)";
		}else if((note >=14.0) && (note < 16.0))  {
			appreciation = "BIEN";
			appreciation = lang.equalsIgnoreCase("fr")==true?"BIEN":"GOOD"+" (A)";
		}else if((note >=16.0) && (note < 18.0))  {
			appreciation = "TRES BIEN";
			appreciation = lang.equalsIgnoreCase("fr")==true?"TRES BIEN":"VERY GOOD"+" (A+)";
		}else if((note >=18.0) && (note < 20.0))  {
			appreciation = "EXCELLENT";
			appreciation = lang.equalsIgnoreCase("fr")==true?"EXCELLENT":"EXCELLENT"+" (A+)";
		}else if(note==20.0)  {
			appreciation = "PARFAIT";
			appreciation = lang.equalsIgnoreCase("fr")==true?"PARFAIT":"PERFECT"+" (A+)";
		}
		return appreciation;
	}
	
	
	
	public String calculDistinction(Double note){
		String distinction = "";
		if(note == null) return distinction;
		if(note>=12.0) {
			distinction = "TH";
		}
		else if(note>=14){
			distinction = "THE";
		}
		else if(note>=16){
			distinction = "THF";
		}
		return distinction;
	}
	
	public Map<String, Object> getEffectifSexeClasse(List<Eleves> listofEleve ){
		Map<String, Object> mapofEff = new HashMap<String, Object>();
		int nbreG = 0;
		int nbreF = 0;
		for(Eleves eleve : listofEleve){
			if(eleve.getSexeEleves().equalsIgnoreCase("masculin")==true){
				nbreG+=1;
			}
			else{
				nbreF+=1;
			}
		}
		mapofEff.put("nbreG", nbreG);
		mapofEff.put("nbreF", nbreF);
		
		return mapofEff;
	}
	
	
	public Map<String, Object> getEffectifMoyenneSexeClasse(List<Eleves> listofEleveClasseordreDecMoy, 
			Sequences sequence){

		Map<String, Object> mapofEff = new HashMap<String, Object>();
		int nbreG7_50 = 0;
		int nbreG10 = 0;
		int nbreplusG10 = 0;
		int nbreF7_50 = 0;
		int nbreF10 = 0;
		int nbreplusF10 = 0;
		
		int nbreMoyG5 = 0; //nombre de garcon avec une moyenne < à 5
		int nbreMoyG7 = 0; //nombre de garcon avec une moyenne comprise entre >=5 et <7
		int nbreMoyG8 = 0;//>=7 et <8
		int nbreMoyG9 = 0;//>=7 et <8
		int nbreMoyG10 = 0;//>=9 et <10
		int nbreMoyG12 = 0;
		//int nbreMoyG13 = 0;
		int nbreMoyG14 = 0;
		int nbreMoyG20 = 0;
		
		int nbreMoyF5 = 0; //nombre de fille avec une moyenne < à 5
		int nbreMoyF7 = 0; //nombre de fille avec une moyenne comprise entre >=5 et <7
		int nbreMoyF8 = 0;//>=7 et <8
		int nbreMoyF9 = 0;//>=8 et <9
		int nbreMoyF10 = 0;//>=9 et <10
		int nbreMoyF12 = 0;
		//int nbreMoyF13 = 0;
		int nbreMoyF14 = 0;
		int nbreMoyF20 = 0;
		
		double pourCG = 0;
		double pourCF = 0;
		
		
		for(Eleves elv : listofEleveClasseordreDecMoy){
			double moyElv = this.getMoyenneSequentiel(elv, sequence);
			if(elv.getSexeEleves().equals(new String("masculin"))==true){
				
				if(moyElv < 7.50){
					nbreG7_50 +=1;
				}
				else if(moyElv >= 7.50 && moyElv < 10){
					nbreG10 +=1;
				}
				else if(moyElv >= 10){
					nbreplusG10 +=1;
				}
				
				if(moyElv < 5 ){
					nbreMoyG5+=1;
				}
				else if(moyElv >= 5 && moyElv < 7){
					nbreMoyG7+=1;
				}
				else if(moyElv >= 7 && moyElv < 8){
					nbreMoyG8+=1;
				}
				else if(moyElv >= 8 && moyElv < 9){
					nbreMoyG9+=1;
				}
				else if(moyElv >= 9 && moyElv < 10){
					nbreMoyG10+=1;
				}
				else if(moyElv >= 10 && moyElv < 12){
					nbreMoyG12+=1;
				}
				else if(moyElv >= 12 && moyElv < 14){
					nbreMoyG14+=1;
				}
				else if(moyElv >= 14 && moyElv < 20){
					nbreMoyG20+=1;
				}
				
			}
			else {
				
				if(moyElv < 7.50){
					nbreF7_50 +=1;
				}
				else if(moyElv >= 7.50 && moyElv < 10){
					nbreF10 +=1;
				}
				else if(moyElv >= 10){
					nbreplusF10 +=1;
				}
				
				if(moyElv < 5 ){
					nbreMoyF5+=1;
				}
				else if(moyElv >= 5 && moyElv < 7){
					nbreMoyF7+=1;
				}
				else if(moyElv >= 7 && moyElv < 8){
					nbreMoyF8+=1;
				}
				else if(moyElv >= 8 && moyElv < 9){
					nbreMoyF9+=1;
				}
				else if(moyElv >= 9 && moyElv < 10){
					nbreMoyF10+=1;
				}
				else if(moyElv >= 10 && moyElv < 12){
					nbreMoyF12+=1;
				}
				else if(moyElv >= 12 && moyElv < 14){
					nbreMoyF14+=1;
				}
				else if(moyElv >= 14 && moyElv < 20){
					nbreMoyF20+=1;
				}
				
			}
		}
		
		int nbreG = nbreplusG10 + nbreG10 + nbreG7_50;
		int nbreF = nbreplusF10 + nbreF10 + nbreF7_50;
		if(nbreG>0){
			double nbreplus_G10 = nbreplusG10;
			double nbre_G = nbreG;
			pourCG = nbreplus_G10/nbre_G;
		}
		
		pourCG = pourCG*100;
		
		if(nbreF>0){
			double nbreplus_F10 = nbreplusF10;
			double nbre_F = nbreF;
			pourCF = nbreplus_F10/nbre_F;
		}
		
		pourCF = pourCF*100;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			pourCG =df.parse(df.format(pourCG)).doubleValue();
			pourCF =df.parse(df.format(pourCF)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mapofEff.put("nbreG7_50", nbreG7_50);
		mapofEff.put("nbreG10", nbreG10);
		mapofEff.put("nbreplusG10", nbreplusG10);
		mapofEff.put("pourCG", pourCG);
		
		mapofEff.put("nbreMoyG5", nbreMoyG5);
		mapofEff.put("nbreMoyG7", nbreMoyG7);
		mapofEff.put("nbreMoyG8", nbreMoyG8);
		mapofEff.put("nbreMoyG9", nbreMoyG9);
		mapofEff.put("nbreMoyG10", nbreMoyG10);
		mapofEff.put("nbreMoyG12", nbreMoyG12);
		mapofEff.put("nbreMoyG14", nbreMoyG14);
		mapofEff.put("nbreMoyG20", nbreMoyG20);
		
		mapofEff.put("nbreF7_50", nbreF7_50);
		mapofEff.put("nbreF10", nbreF10);
		mapofEff.put("nbreplusF10", nbreplusF10);
		mapofEff.put("pourCF", pourCF);
		

		mapofEff.put("nbreMoyF5", nbreMoyF5);
		mapofEff.put("nbreMoyF7", nbreMoyF7);
		mapofEff.put("nbreMoyF8", nbreMoyF8);
		mapofEff.put("nbreMoyF9", nbreMoyF9);
		mapofEff.put("nbreMoyF10", nbreMoyF10);
		mapofEff.put("nbreMoyF12", nbreMoyF12);
		mapofEff.put("nbreMoyF14", nbreMoyF14);
		mapofEff.put("nbreMoyF20", nbreMoyF20);
		
		return mapofEff;
	
	}
	
	//
	
	public Map<String, Object> getEffectifMoyenneSexeClasse(List<Eleves> listofEleveClasseordreDecMoy, 
			Trimestres trimestre){

		Map<String, Object> mapofEff = new HashMap<String, Object>();
		int nbreG7_50 = 0;
		int nbreG10 = 0;
		int nbreplusG10 = 0;
		int nbreF7_50 = 0;
		int nbreF10 = 0;
		int nbreplusF10 = 0;
		
		int nbreMoyG5 = 0; //nombre de garcon avec une moyenne < à 5
		int nbreMoyG7 = 0; //nombre de garcon avec une moyenne comprise entre >=5 et <7
		int nbreMoyG8 = 0;//>=7 et <8
		int nbreMoyG9 = 0;//>=7 et <8
		int nbreMoyG10 = 0;//>=9 et <10
		int nbreMoyG12 = 0;
		//int nbreMoyG13 = 0;
		int nbreMoyG14 = 0;
		int nbreMoyG20 = 0;
		
		int nbreMoyF5 = 0; //nombre de fille avec une moyenne < à 5
		int nbreMoyF7 = 0; //nombre de fille avec une moyenne comprise entre >=5 et <7
		int nbreMoyF8 = 0;//>=7 et <8
		int nbreMoyF9 = 0;//>=8 et <9
		int nbreMoyF10 = 0;//>=9 et <10
		int nbreMoyF12 = 0;
		//int nbreMoyF13 = 0;
		int nbreMoyF14 = 0;
		int nbreMoyF20 = 0;
		
		double pourCG = 0;
		double pourCF = 0;
		
		
		for(Eleves elv : listofEleveClasseordreDecMoy){
			double moyElv = this.getMoyenneTrimestriel(elv, trimestre);
			if(elv.getSexeEleves().equals(new String("masculin"))==true){
				
				if(moyElv < 7.50){
					nbreG7_50 +=1;
				}
				else if(moyElv >= 7.50 && moyElv < 10){
					nbreG10 +=1;
				}
				else if(moyElv >= 10){
					nbreplusG10 +=1;
				}
				
				if(moyElv < 5 ){
					nbreMoyG5+=1;
				}
				else if(moyElv >= 5 && moyElv < 7){
					nbreMoyG7+=1;
				}
				else if(moyElv >= 7 && moyElv < 8){
					nbreMoyG8+=1;
				}
				else if(moyElv >= 8 && moyElv < 9){
					nbreMoyG9+=1;
				}
				else if(moyElv >= 9 && moyElv < 10){
					nbreMoyG10+=1;
				}
				else if(moyElv >= 10 && moyElv < 12){
					nbreMoyG12+=1;
				}
				else if(moyElv >= 12 && moyElv < 14){
					nbreMoyG14+=1;
				}
				else if(moyElv >= 14 && moyElv < 20){
					nbreMoyG20+=1;
				}
				
			}
			else {
				
				if(moyElv < 7.50){
					nbreF7_50 +=1;
				}
				else if(moyElv >= 7.50 && moyElv < 10){
					nbreF10 +=1;
				}
				else if(moyElv >= 10){
					nbreplusF10 +=1;
				}
				
				if(moyElv < 5 ){
					nbreMoyF5+=1;
				}
				else if(moyElv >= 5 && moyElv < 7){
					nbreMoyF7+=1;
				}
				else if(moyElv >= 7 && moyElv < 8){
					nbreMoyF8+=1;
				}
				else if(moyElv >= 8 && moyElv < 9){
					nbreMoyF9+=1;
				}
				else if(moyElv >= 9 && moyElv < 10){
					nbreMoyF10+=1;
				}
				else if(moyElv >= 10 && moyElv < 12){
					nbreMoyF12+=1;
				}
				else if(moyElv >= 12 && moyElv < 14){
					nbreMoyF14+=1;
				}
				else if(moyElv >= 14 && moyElv < 20){
					nbreMoyF20+=1;
				}
				
			}
		}
		
		int nbreG = nbreplusG10 + nbreG10 + nbreG7_50;
		int nbreF = nbreplusF10 + nbreF10 + nbreF7_50;
		if(nbreG>0){
			double nbreplus_G10 = nbreplusG10;
			double nbre_G = nbreG;
			pourCG = nbreplus_G10/nbre_G;
		}
		
		pourCG = pourCG*100;
		
		if(nbreF>0){
			double nbreplus_F10 = nbreplusF10;
			double nbre_F = nbreF;
			pourCF = nbreplus_F10/nbre_F;
		}
		
		pourCF = pourCF*100;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			pourCG =df.parse(df.format(pourCG)).doubleValue();
			pourCF =df.parse(df.format(pourCF)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mapofEff.put("nbreG7_50", nbreG7_50);
		mapofEff.put("nbreG10", nbreG10);
		mapofEff.put("nbreplusG10", nbreplusG10);
		mapofEff.put("pourCG", pourCG);
		
		mapofEff.put("nbreMoyG5", nbreMoyG5);
		mapofEff.put("nbreMoyG7", nbreMoyG7);
		mapofEff.put("nbreMoyG8", nbreMoyG8);
		mapofEff.put("nbreMoyG9", nbreMoyG9);
		mapofEff.put("nbreMoyG10", nbreMoyG10);
		mapofEff.put("nbreMoyG12", nbreMoyG12);
		mapofEff.put("nbreMoyG14", nbreMoyG14);
		mapofEff.put("nbreMoyG20", nbreMoyG20);
		
		mapofEff.put("nbreF7_50", nbreF7_50);
		mapofEff.put("nbreF10", nbreF10);
		mapofEff.put("nbreplusF10", nbreplusF10);
		mapofEff.put("pourCF", pourCF);
		

		mapofEff.put("nbreMoyF5", nbreMoyF5);
		mapofEff.put("nbreMoyF7", nbreMoyF7);
		mapofEff.put("nbreMoyF8", nbreMoyF8);
		mapofEff.put("nbreMoyF9", nbreMoyF9);
		mapofEff.put("nbreMoyF10", nbreMoyF10);
		mapofEff.put("nbreMoyF12", nbreMoyF12);
		mapofEff.put("nbreMoyF14", nbreMoyF14);
		mapofEff.put("nbreMoyF20", nbreMoyF20);
		
		return mapofEff;
	
	}
	
	//
	
	public Map<String, Object> getEffectifMoyenneSexeClasse(List<Eleves> listofEleveClasseordreDecMoy, 
			Annee annee){

		Map<String, Object> mapofEff = new HashMap<String, Object>();
		int nbreG7_50 = 0;
		int nbreG10 = 0;
		int nbreplusG10 = 0;
		int nbreF7_50 = 0;
		int nbreF10 = 0;
		int nbreplusF10 = 0;
		
		int nbreMoyG5 = 0; //nombre de garcon avec une moyenne < à 5
		int nbreMoyG7 = 0; //nombre de garcon avec une moyenne comprise entre >=5 et <7
		int nbreMoyG8 = 0;//>=7 et <8
		int nbreMoyG9 = 0;//>=7 et <8
		int nbreMoyG10 = 0;//>=9 et <10
		int nbreMoyG12 = 0;
		//int nbreMoyG13 = 0;
		int nbreMoyG14 = 0;
		int nbreMoyG20 = 0;
		
		int nbreMoyF5 = 0; //nombre de fille avec une moyenne < à 5
		int nbreMoyF7 = 0; //nombre de fille avec une moyenne comprise entre >=5 et <7
		int nbreMoyF8 = 0;//>=7 et <8
		int nbreMoyF9 = 0;//>=8 et <9
		int nbreMoyF10 = 0;//>=9 et <10
		int nbreMoyF12 = 0;
		//int nbreMoyF13 = 0;
		int nbreMoyF14 = 0;
		int nbreMoyF20 = 0;
		
		double pourCG = 0;
		double pourCF = 0;
		
		
		for(Eleves elv : listofEleveClasseordreDecMoy){
			double moyElv = this.getMoyenneAnnuel(elv, annee);
			if(elv.getSexeEleves().equals(new String("masculin"))==true){
				
				if(moyElv < 7.50){
					nbreG7_50 +=1;
				}
				else if(moyElv >= 7.50 && moyElv < 10){
					nbreG10 +=1;
				}
				else if(moyElv >= 10){
					nbreplusG10 +=1;
				}
				
				if(moyElv < 5 ){
					nbreMoyG5+=1;
				}
				else if(moyElv >= 5 && moyElv < 7){
					nbreMoyG7+=1;
				}
				else if(moyElv >= 7 && moyElv < 8){
					nbreMoyG8+=1;
				}
				else if(moyElv >= 8 && moyElv < 9){
					nbreMoyG9+=1;
				}
				else if(moyElv >= 9 && moyElv < 10){
					nbreMoyG10+=1;
				}
				else if(moyElv >= 10 && moyElv < 12){
					nbreMoyG12+=1;
				}
				else if(moyElv >= 12 && moyElv < 14){
					nbreMoyG14+=1;
				}
				else if(moyElv >= 14 && moyElv < 20){
					nbreMoyG20+=1;
				}
				
			}
			else {
				
				if(moyElv < 7.50){
					nbreF7_50 +=1;
				}
				else if(moyElv >= 7.50 && moyElv < 10){
					nbreF10 +=1;
				}
				else if(moyElv >= 10){
					nbreplusF10 +=1;
				}
				
				if(moyElv < 5 ){
					nbreMoyF5+=1;
				}
				else if(moyElv >= 5 && moyElv < 7){
					nbreMoyF7+=1;
				}
				else if(moyElv >= 7 && moyElv < 8){
					nbreMoyF8+=1;
				}
				else if(moyElv >= 8 && moyElv < 9){
					nbreMoyF9+=1;
				}
				else if(moyElv >= 9 && moyElv < 10){
					nbreMoyF10+=1;
				}
				else if(moyElv >= 10 && moyElv < 12){
					nbreMoyF12+=1;
				}
				else if(moyElv >= 12 && moyElv < 14){
					nbreMoyF14+=1;
				}
				else if(moyElv >= 14 && moyElv < 20){
					nbreMoyF20+=1;
				}
				
			}
		}
		
		int nbreG = nbreplusG10 + nbreG10 + nbreG7_50;
		int nbreF = nbreplusF10 + nbreF10 + nbreF7_50;
		if(nbreG>0){
			double nbreplus_G10 = nbreplusG10;
			double nbre_G = nbreG;
			pourCG = nbreplus_G10/nbre_G;
		}
		
		pourCG = pourCG*100;
		
		if(nbreF>0){
			double nbreplus_F10 = nbreplusF10;
			double nbre_F = nbreF;
			pourCF = nbreplus_F10/nbre_F;
		}
		
		pourCF = pourCF*100;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			pourCG =df.parse(df.format(pourCG)).doubleValue();
			pourCF =df.parse(df.format(pourCF)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mapofEff.put("nbreG7_50", nbreG7_50);
		mapofEff.put("nbreG10", nbreG10);
		mapofEff.put("nbreplusG10", nbreplusG10);
		mapofEff.put("pourCG", pourCG);
		
		mapofEff.put("nbreMoyG5", nbreMoyG5);
		mapofEff.put("nbreMoyG7", nbreMoyG7);
		mapofEff.put("nbreMoyG8", nbreMoyG8);
		mapofEff.put("nbreMoyG9", nbreMoyG9);
		mapofEff.put("nbreMoyG10", nbreMoyG10);
		mapofEff.put("nbreMoyG12", nbreMoyG12);
		mapofEff.put("nbreMoyG14", nbreMoyG14);
		mapofEff.put("nbreMoyG20", nbreMoyG20);
		
		mapofEff.put("nbreF7_50", nbreF7_50);
		mapofEff.put("nbreF10", nbreF10);
		mapofEff.put("nbreplusF10", nbreplusF10);
		mapofEff.put("pourCF", pourCF);
		

		mapofEff.put("nbreMoyF5", nbreMoyF5);
		mapofEff.put("nbreMoyF7", nbreMoyF7);
		mapofEff.put("nbreMoyF8", nbreMoyF8);
		mapofEff.put("nbreMoyF9", nbreMoyF9);
		mapofEff.put("nbreMoyF10", nbreMoyF10);
		mapofEff.put("nbreMoyF12", nbreMoyF12);
		mapofEff.put("nbreMoyF14", nbreMoyF14);
		mapofEff.put("nbreMoyF20", nbreMoyF20);
		
		return mapofEff;
	
	}
	
	
	/******************
	 * Cette methode retourne la liste des NoteEval obtenu par un élève dans une séquence
	 * @param eleve
	 * @param sequence
	 * @return
	 */
	public List<NotesEval> getListofnotesEvalDeSeq(Eleves eleve, Sequences sequence){
		List<NotesEval> listofnotesEvalDeSeq = new ArrayList<NotesEval>();
		/*
		 * On recupere la liste des notes sequentielles de l'élève classe dans l'ordre alphabetique des matieres
		 * puis des cours. Parmi ces notes il y a donc les notes de la séquence prise en paramètre et ce sont ces 
		 * notes qu'on reccherche
		 */
		List<NotesEval> listofnotesEvalSeq = (List<NotesEval>) eleve.getListofnotesEval();
		if(listofnotesEvalSeq != null){
			for(NotesEval notesEvalSeq : listofnotesEvalSeq){
				if(notesEvalSeq.getEvaluation().getSequence().getIdPeriodes().longValue() == sequence.getIdPeriodes().longValue()){
					listofnotesEvalDeSeq.add(notesEvalSeq);
				}
			}
		}
		return listofnotesEvalDeSeq;
	}
	
	/***************************
	 * Cette methode retourne la liste des NoteEval obtenu par un élève pour un cours donnée dans une 
	 * sequence
	 * @param eleve
	 * @param cours
	 * @param sequence
	 * @return
	 */
	public List<NotesEval> getNotesEvalEleve(Eleves eleve, Cours cours, Sequences sequence){
		List<NotesEval> listofNotesEvalDeCoursSeq = new ArrayList<NotesEval>();
		List<NotesEval> listofNotesEvalDeSeq = new ArrayList<NotesEval>();
		listofNotesEvalDeSeq = this.getListofnotesEvalDeSeq(eleve, sequence);
		for(NotesEval notesEval: listofNotesEvalDeSeq){
			if(notesEval.getEvaluation().getCours().getIdCours().longValue() == cours.getIdCours().longValue()){
				listofNotesEvalDeCoursSeq.add(notesEval);
			}
		}
		return listofNotesEvalDeCoursSeq;
	}
	
	public List<NotesEval> getNotesEvalEleve(Eleves eleve, Cours cours, Trimestres trimestre){
		List<NotesEval> listofNotesEvalDeCoursSeq = new ArrayList<NotesEval>();
		List<NotesEval> listofNotesEvalDeSeq = new ArrayList<NotesEval>();
		
		for(Sequences seq : trimestre.getListofsequence()){
			listofNotesEvalDeSeq = this.getListofnotesEvalDeSeq(eleve, seq);
			for(NotesEval notesEval: listofNotesEvalDeSeq){
				if(notesEval.getEvaluation().getCours().getIdCours().longValue() == cours.getIdCours().longValue()){
					listofNotesEvalDeCoursSeq.add(notesEval);
				}
			}
		}
		
		return listofNotesEvalDeCoursSeq;
	}
	
	
	
	/*********************
	 * Cette methode retourne la valeur de la note finale séquentiel d'un élève pour un cours dans une 
	 * sequence
	 * @param eleve
	 * @param cours
	 * @param sequence
	 * @return
	 */
	public double getValeurNotesFinaleEleve(Eleves eleve, Cours cours, Sequences sequence){
		double noteFinale = 0;
		int possedeunenote = -1;
		List<NotesEval> listofNotesEvalDeCoursSeq = this.getNotesEvalEleve(eleve, cours, sequence);
		for(NotesEval noteEval : listofNotesEvalDeCoursSeq){
			double pour = (noteEval.getEvaluation().getProportionEval())*0.01;
			double valeur = noteEval.getValeurnoteEval();
			noteFinale = noteFinale + (pour * valeur);
			possedeunenote = 1;
		}
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			noteFinale =df.parse(df.format(noteFinale)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(possedeunenote == 1)	return noteFinale; else return -1;
	}
	
	
	
	public double getValeurNotesFinaleEleveTrimestre(Eleves eleve, Cours cours, Trimestres trimestre){
		double noteFinale = 0;
		double soenoteFinale = 0;
		int possedeunenote = -1;
		int nbreNote = 0;
		for(Sequences seq : trimestre.getListofsequence()){
			double noteSeq = this.getValeurNotesFinaleEleve(eleve, cours, seq);
			if(noteSeq>=0){
				soenoteFinale = soenoteFinale + noteSeq;
				possedeunenote = 1;
				nbreNote++;
			}
			
		}
		
		if(nbreNote>0){
			noteFinale = soenoteFinale/nbreNote;
		}
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			noteFinale =df.parse(df.format(noteFinale)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(possedeunenote == 1)		return noteFinale; else return -1;

		
	}
	
	public double getValeurNotesFinaleEleveAnnee(Eleves eleve, Cours cours, Annee annee){
		double noteFinale = 0;
		double soenoteFinale = 0;
		int possedeunenote = -1;
		int nbreNote = 0;
		for(Trimestres trim : annee.getListoftrimestre()){
			double noteTrim = this.getValeurNotesFinaleEleveTrimestre(eleve, cours, trim);
			if(noteTrim>=0){
				soenoteFinale = soenoteFinale + noteTrim;
				possedeunenote = 1;
				nbreNote++;
			}
			
		}
		
		if(nbreNote>0){
			noteFinale = soenoteFinale/nbreNote;
		}
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			noteFinale =df.parse(df.format(noteFinale)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(possedeunenote == 1)		return noteFinale; else return -1;

		
	}
	
	
	
	/********************
	 * Cette methode retourne la liste des cours qui ont été évalué dans une séquence pour une classe donné
	 * Cette liste est deja classe par ordre alphabetique car getListCours de l'entite classe retourne les cours 
	 * dans un certains ordre alphabetique
	 * @param classe
	 * @param sequence
	 * @return
	 */
	public List<Cours> getListOfCoursEvalueDansSequence(Classes classe, Sequences sequence){
		List<Cours> listofCoursEvalueSeq = new ArrayList<Cours>();
		int estEvalue=0;//on suppose au depart que pour un cours aucun eleve n'a encore été evalue
		
		for(Cours cours: classe.getListofCours()){
			for(Eleves eleve: classe.getListofEleves()){
				double valNote = this.getValeurNotesFinaleEleve(eleve, cours, sequence);
				if(valNote>0){
					estEvalue=1;//donc au moins 1 éleve a été evalue donc on doit ajouter le cours a la liste
				}
			}
			/*
			 * On scrute la note de tous les élèves, s'il y a un seul qui a une note (estEvalue=1) pour le cours dans la sequence
			 * alors le cours a été evalue.
			 */
			if(estEvalue == 1){
				listofCoursEvalueSeq.add(cours);
			}

			estEvalue=0;
		}
		
		return listofCoursEvalueSeq;
	}
	
	
	
	
	/********************************************************
	 * Cette methode retourne 1 lorsque le cours pris en paramètre est 
	 * deja dans la liste des cours prises en paramètre. 0 sinon
	 * @param listofCours
	 * @param cours
	 * @return
	 */
	public int estdejadansListeCours(List<Cours> listofCours, Cours cours){
		int r = 0;
		for(Cours c: listofCours){
			if(c.getIdCours().longValue() == cours.getIdCours().longValue()){
				r = 1;
				break;
			}
		}
		return r;
	}
	
	/*********************************************************************************
	 * Cette methode retourne la liste des cours qu'on peut considéré comme ayant été evalue
	 * dans un trimestre. Un cours est évalué dans un trimestre s'il est évalué dans au moins une 
	 * séquence de ce trimestre
	 * @param classe
	 * @param trimestre
	 * @return
	 */
	public List<Cours> getListOfCoursEvalueDansTrimestre(Classes classe, Trimestres trimestre){
		List<Cours> listofCoursEvalueTrim = new ArrayList<Cours>();
		for(Sequences seq : trimestre.getListofsequence()){
			List<Cours> listofCoursEvalueSeq = this.getListOfCoursEvalueDansSequence(classe, seq);
			for(Cours co: listofCoursEvalueSeq){
				int r = this.estdejadansListeCours(listofCoursEvalueTrim, co);
				if(r == 0){
					//si on est la alors le cours n'est pas encore dans la liste des cours evalue du trimestre
					listofCoursEvalueTrim.add(co);
				}
			}
		}
		return listofCoursEvalueTrim;
	}
	
	/*********************************************************************************
	 * Cette methode retourne la liste des cours considéré comme ayant été évalue au cours de 
	 * l'année scolaire. Un cours est considéré évalué s'il a été évalué dans au moins un trimestre de 
	 * cette année
	 * @param classe
	 * @param anneeScolaire
	 * @return
	 */
	public List<Cours> getListOfCoursEvalueDansAnnee(Classes classe, Annee anneeScolaire){
		List<Cours> listofCoursEvalueAn = new ArrayList<Cours>();
		for(Trimestres trim: anneeScolaire.getListoftrimestre()){
			List<Cours> listofCoursEvalueTrim = this.getListOfCoursEvalueDansTrimestre(classe, trim);
			for(Cours co: listofCoursEvalueTrim){
				int r = this.estdejadansListeCours(listofCoursEvalueAn, co);
				if(r == 0){
					//si on est la alors le cours n'est pas encore dans la liste des cours evalue du trimestre
					listofCoursEvalueAn.add(co);
				}
			}
		}
		return listofCoursEvalueAn;
	}
	
	
	/*********************************
	 * Cette methode retourne la liste des cours du groupe scientifique qui passe dans une classe
	 * @param classe
	 * @return
	 */
	public List<Cours> getListofCoursScientifiqueDansClasse(Classes classe){
		List<Cours> listofCoursScientifiqueDansClasse = new ArrayList<Cours>();
		List<Cours> listofCoursClasse = (List<Cours>) classe.getListofCours();
		for(Cours cours : listofCoursClasse){
			if(cours.getGroupeCours().equals(new String("Scientifique"))==true){
				listofCoursScientifiqueDansClasse.add(cours);
			}
		}
		return listofCoursScientifiqueDansClasse;
	}
	
	/**********************
	 * Cette methode retourne la liste des cours du groupe littéraire qui passe dans une classe
	 * @param classe
	 * @return
	 */
	public List<Cours> getListofCoursLitteraireDansClasse(Classes classe){
		List<Cours> listofCoursLitteraireDansClasse = new ArrayList<Cours>();
		List<Cours> listofCoursClasse = (List<Cours>) classe.getListofCours();
		for(Cours cours : listofCoursClasse){
			if(cours.getGroupeCours().equals(new String("Litteraire"))==true){
				listofCoursLitteraireDansClasse.add(cours);
			}
		}
		return listofCoursLitteraireDansClasse;
	}
	
	/***********************
	 * Cette methode retourne la liste des cours du groupe Divers qui passe dans une classe
	 * @param classe
	 * @return
	 */
	public List<Cours> getListofCoursDiversDansClasse(Classes classe){
		List<Cours> listofCoursDiversDansClasse = new ArrayList<Cours>();
		List<Cours> listofCoursClasse = (List<Cours>) classe.getListofCours();
		for(Cours cours : listofCoursClasse){
			if(cours.getGroupeCours().equals(new String("Divers"))==true){
				listofCoursDiversDansClasse.add(cours);
			}
		}
		return listofCoursDiversDansClasse;
	}
	
	public int estEvalueDansSequence (Classes classe, Cours cours, Sequences sequence){
		int estEvalue = 0;
		//System.err.println("est ce que le cours "+cours.getCodeCours()+" est evalue");
		List<Eleves> listofEleve = (List<Eleves>) classe.getListofEleves();
		for(Eleves elv : listofEleve){
			double valeurNote = this.getValeurNotesFinaleEleve(elv, cours, sequence);
			//System.err.println("elv "+elv.getNomsEleves()+" est evalue en "+cours.getCodeCours()+" et a eu "+valeurNote);
			if(valeurNote>=0){
				estEvalue = 1;
				break;
			}
		}
		//System.err.println("OK on sait si le cours "+cours.getCodeCours()+" est evalue "+estEvalue);
		return estEvalue;
	}
	
	public int estEvalueDansTrimestre (Classes classe, Cours cours, Trimestres trimestre){
		int estEvalue = 0;
		for(Sequences seq: trimestre.getListofsequence()){
			int estEvalueDansSeq = this.estEvalueDansSequence(classe, cours, seq);
			if(estEvalueDansSeq == 1){
				estEvalue = 1;
				break;
			}
		}
		
		return estEvalue;
	}
	
	/******************************
	 *  Cette methode retourne 1  si l'eleve en parametre est régulier et 0 sinon.
	 * Il est regulier s'il a une note enregistré pour au moins un des cours passe en paramètre 
	 * @param eleve
	 * @param listofCours
	 * @param sequence
	 * @return
	 */
	public int estRegulierDansSequence(Eleves eleve, List<Cours> listofCours, Sequences sequence){
		int estregulier = 1;
		int nbrecourscomposeeleve = 0;
		//List<Cours> listofCoursEvalue = new ArrayList<Cours>();
		//IL faut extraire de cette liste les cours qui n'ont pas encore été evalue dans une sequence pour la classe
		for(Cours cours : listofCours){
			int estEvalue = this.estEvalueDansSequence(eleve.getClasse(), cours, sequence);
			if(estEvalue == 1){
				double valNote = this.getValeurNotesFinaleEleve(eleve, cours, sequence);
				if(valNote<0){
					nbrecourscomposeeleve+=1;
				}
			}
		}
		if(nbrecourscomposeeleve == listofCours.size()){
			estregulier=0;
		}
		return estregulier;
	}
	
	
	/***************************
	 * Cette methode retourne la liste des élèves régulier d'une classe dans une séquence ie 
	 * les élèves qui ont une note>=0 dans au moins un des cours évalué dans cette séquence
	 * @param idSequence
	 * @return
	 */
	public List<Eleves> getListofEleveRegulier(Classes classe, Sequences sequence){
		List<Eleves> listofEleveRegulier = new ArrayList<Eleves>();
		List<Cours> listofCoursClasse = (List<Cours>) this.getListOfCoursEvalueDansSequence(classe, sequence);
		//System.err.println("nbre de cours evalue "+listofCoursClasse.size());
		List<Eleves> listofEleveOrdonne = (List<Eleves>) classe.getListofEleves();
		
		for(Eleves elv: listofEleveOrdonne){
			int estRegulier = this.estRegulierDansSequence(elv, listofCoursClasse, sequence);
			if(estRegulier == 1){
				listofEleveRegulier.add(elv);
			}
		}

		return listofEleveRegulier;
	}
	
	public int estdejadansListeEleve(List<Eleves> listofEleves, Eleves elv){
		int r = 0;
		for(Eleves e: listofEleves){
			if(e.getIdEleves().longValue() == elv.getIdEleves().longValue()){
				r = 1;
				break;
			}
		}
		return r;
	}
	
	/**********************************************************************************
	 * Un élève est régulier dans un trimestre s'il a été régulier dans au moins une séquence de ce 
	 * trimestre.
	 * @param classe
	 * @param trimestre
	 * @return
	 */
	public List<Eleves> getListofEleveRegulierTrimestre(Classes classe, Trimestres trimestre){
		List<Eleves> listofeleveregulierTrim = new ArrayList<Eleves>();
		for(Sequences seq: trimestre.getListofsequence()){
			List<Eleves> listofEleveregulierSeq = this.getListofEleveRegulier(classe, seq);
			for(Eleves elv:listofEleveregulierSeq){
				int r=this.estdejadansListeEleve(listofeleveregulierTrim, elv);
				if(r==0){
					listofeleveregulierTrim.add(elv);
				}
			}
		}
		
		//Il faut classer ces élèves par ordre alphabétique
		listofeleveregulierTrim = UtilitairesBulletins.getListofeleveTrieparordrealphabetique(listofeleveregulierTrim);
		
		return listofeleveregulierTrim;
	}
	
	
	public List<Eleves> getListofEleveRegulierAnnee(Classes classe, Annee annee){
		List<Eleves> listofeleveregulierAn = new ArrayList<Eleves>();
		
		for(Trimestres trim: annee.getListoftrimestre()){
			List<Eleves> listofEleveregulierTrim = this.getListofEleveRegulierTrimestre(classe, trim);
			for(Eleves elv:listofEleveregulierTrim){
				int r=this.estdejadansListeEleve(listofeleveregulierAn, elv);
				if(r==0){
					listofeleveregulierAn.add(elv);
				}
			}
		}
		
		//Il faut classer ces élèves par ordre alphabétique
		listofeleveregulierAn = UtilitairesBulletins.getListofeleveTrieparordrealphabetique(listofeleveregulierAn);
				
		return listofeleveregulierAn;
	}
	
	public double getTotalPointsSequentiel(Eleves eleve, Sequences sequence){
		int possedeTotal =  0;
		List<Cours> listofCoursDansClasse = (List<Cours>) eleve.getClasse().getListofCours();
		//List<Cours> listofCoursDansClasse =  this.getListOfCoursEvalueDansSequence(eleve.getClasse(), sequence);
		double soeValeurNote = 0;
		
		for(Cours cours : listofCoursDansClasse){
			double valeurNoteCours = this.getValeurNotesFinaleEleve(eleve, cours, sequence);
			if(valeurNoteCours >= 0){
				double valeurCoefCours = cours.getCoefCours();
				soeValeurNote = soeValeurNote+(valeurNoteCours*valeurCoefCours);
				possedeTotal = 1;
			}
		}
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			soeValeurNote =df.parse(df.format(soeValeurNote)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(possedeTotal == 1) return soeValeurNote; else return -1;
		
	}
	
	public double getTotalPointsTrimestriel(Eleves eleve, Trimestres trimestre){
		int possedeTotal =  0;
		double totalNoteTrim = 0;
		List<Cours> listofCoursDansClasse = (List<Cours>) eleve.getClasse().getListofCours();
		
		for(Cours cours : listofCoursDansClasse){
			double valeurNoteTrimCours = 
					this.getValeurNotesFinaleEleveTrimestre(eleve, cours, trimestre);
			if(valeurNoteTrimCours >= 0){
				double valeurCoefCours = cours.getCoefCours();
				totalNoteTrim = totalNoteTrim+(valeurNoteTrimCours*valeurCoefCours);
				possedeTotal = 1;
			}
		}
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			totalNoteTrim =df.parse(df.format(totalNoteTrim)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(possedeTotal == 1) return totalNoteTrim; else return -1;
	}
	
	public double getTotalPointsAnnuel(Eleves eleve, Annee annee){
		int possedeTotal =  0;
		double totalNoteAn = 0;
		List<Cours> listofCoursDansClasse = (List<Cours>) eleve.getClasse().getListofCours();
		
		for(Cours cours : listofCoursDansClasse){
			double valeurNoteTrimCours = 
					this.getValeurNotesFinaleEleveAnnee(eleve, cours, annee);
			if(valeurNoteTrimCours >= 0){
				double valeurCoefCours = cours.getCoefCours();
				totalNoteAn = totalNoteAn+(valeurNoteTrimCours*valeurCoefCours);
				possedeTotal = 1;
			}
		}
		
		if(possedeTotal == 1) return totalNoteAn; else return -1;
	}
	
	public double getSommeCoefCoursCompose(Eleves eleve, Sequences sequence){
		double soeCoefCoursCompose = 0;
		List<Cours> listofCoursDansClasse = (List<Cours>) eleve.getClasse().getListofCours();
		for(Cours cours : listofCoursDansClasse){
			double valeurNoteCours = this.getValeurNotesFinaleEleve(eleve, cours, sequence);
			if(valeurNoteCours >= 0){
				double valeurCoefCours = cours.getCoefCours();
				soeCoefCoursCompose = soeCoefCoursCompose+valeurCoefCours;
			}
		}
		//System.out.println("voici la somme des coef a retourner "+soeCoefCoursCompose);
		return soeCoefCoursCompose;
	}
	
	public double getSommeCoefCoursComposeD(Eleves eleve, Sequences sequence){
		double soeCoefCoursCompose = 0;
		List<Cours> listofCoursDansClasse = (List<Cours>) eleve.getClasse().getListofCours();
		for(Cours cours : listofCoursDansClasse){
			double valeurNoteCours = this.getValeurNotesFinaleEleve(eleve, cours, sequence);
			if(valeurNoteCours >= 0){
				double valeurCoefCours = 1.0*cours.getCoefCours();
				soeCoefCoursCompose = soeCoefCoursCompose+valeurCoefCours;
			}
		}
		//System.out.println("voici la somme des coef a retourner "+soeCoefCoursCompose);
		return soeCoefCoursCompose;
	}
	
	public double getSommeCoefCoursComposeTrimestre(Eleves eleve, Trimestres trimestre){
		double soeCoefCoursCompose = 0;
		//List<Cours> listofCoursDansClasse =  this.getListOfCoursEvalueDansTrimestre(eleve.getClasse(), trimestre);
		List<Cours> listofCoursDansClasse = (List<Cours>) eleve.getClasse().getListofCours();
		for(Cours cours : listofCoursDansClasse){
			double valeurNoteCours = this.getValeurNotesFinaleEleveTrimestre(eleve, cours, trimestre);
			if(valeurNoteCours >= 0){
				double valeurCoefCours = cours.getCoefCours();
				soeCoefCoursCompose = soeCoefCoursCompose+valeurCoefCours;
			}
		}
		return soeCoefCoursCompose;
	}
	
	public double getSommeCoefCoursComposeAnnee(Eleves eleve, Annee annee){
		double soeCoefCoursCompose = 0;
		List<Cours> listofCoursDansClasse = (List<Cours>) eleve.getClasse().getListofCours();
		for(Cours cours : listofCoursDansClasse){
			double valeurNoteCours = this.getValeurNotesFinaleEleveAnnee(eleve, cours, annee);
			if(valeurNoteCours >= 0){
				double valeurCoefCours = cours.getCoefCours();
				soeCoefCoursCompose = soeCoefCoursCompose+valeurCoefCours;
			}
		}
		return soeCoefCoursCompose;
	}
	
	
	public double getMoyenneSequentiel(Eleves eleve, Sequences sequence){
		double moyenne = -1;
		int possedeMoy =  0;
		double soeValeurNote = 0;
		double soeCoef = 0;
		
		soeValeurNote = this.getTotalPointsSequentiel(eleve, sequence);
		soeCoef = this.getSommeCoefCoursCompose(eleve, sequence);
		
		if(soeValeurNote>=0 && soeCoef>0) {
			moyenne = soeValeurNote/soeCoef;
			possedeMoy = 1;
		}
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moyenne =df.parse(df.format(moyenne)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(possedeMoy == 1) return moyenne; else return -1;
		
	}
	
	public double getMoyenneTrimestriel(Eleves eleve, Trimestres trimestre){
		double moyenne = -1;
		/*double soeMoySeq = 0;
		int possedeMoy =  0;
		int nbreMoy = 0;*/
		
		double totalPointsTrim = this.getTotalPointsTrimestriel(eleve, trimestre);
		double totalCoef = this.getSommeCoefCoursComposeTrimestre(eleve, trimestre);
		if(totalCoef>0)	moyenne = totalPointsTrim/totalCoef;
		/*for(Sequences seq : trimestre.getListofsequence()){
			double moySeq = this.getMoyenneSequentiel(eleve, seq);
			//System.err.println("moyseq "+seq.getNumeroSeq()+" est de "+moySeq);
			if(moySeq >= 0){
				soeMoySeq = soeMoySeq + moySeq;
				possedeMoy = 1;
				nbreMoy ++;
			}
		}
		
		if(nbreMoy == 1){
			moyenne = soeMoySeq;
		}
		else if(nbreMoy == 2){
			moyenne = soeMoySeq/2;
		}*/
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moyenne =df.parse(df.format(moyenne)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if(moyenne > -1) return moyenne; else return -1;
	}
	
	
	public double getMoyenneAnnuel(Eleves eleve, Annee annee){
		double moyenne = -1;
		/*double soeMoyTrim = 0;
		int possedeMoy =  0;
		int nbreMoy = 0;*/
		
		double totalPointsTrim = this.getTotalPointsAnnuel(eleve, annee);
		double totalCoef = this.getSommeCoefCoursComposeAnnee(eleve, annee);
		if(totalCoef>0)	moyenne = totalPointsTrim/totalCoef;
		
		/*for(Trimestres trim : annee.getListoftrimestre()){
			double moyTrim = this.getMoyenneTrimestriel(eleve, trim);
			if(moyTrim >= 0){
				soeMoyTrim = soeMoyTrim + moyTrim;
				possedeMoy = 1;
				nbreMoy ++;
			}
		}
		

		if(nbreMoy == 1){
			moyenne = soeMoyTrim;
		}
		else if(nbreMoy == 2){
			moyenne = soeMoyTrim/2;
		}
		else if(nbreMoy == 3){
			moyenne = soeMoyTrim/3;
		}*/
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moyenne =df.parse(df.format(moyenne)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		if(moyenne > -1) return moyenne; else return -1;
		//if(possedeMoy == 1) return moyenne; else return -1;
	}
	
	
	
	public RapportSequentielClasse getRapportSequentielClasse(Classes classe, 
			List<Eleves> listofEleveRegulier, Sequences sequence){
		RapportSequentielClasse rapportSequentielClasse = new RapportSequentielClasse();
		
		double valeurMoyennepremier = 0;
		int premierexist = 0;
		
		double valeurMoyennedernier = 200;
		int dernierexist = 0;
		
		int nbreMoyenne = 0;
		int noteExist = -1;
		
		int nbreElvRegulierSeq = listofEleveRegulier.size();
		rapportSequentielClasse.setEffectifEleveRegulier(nbreElvRegulierSeq);
		
		//java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		
		double moygen = 0;
		double soeMoy=0;
		
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = this.getMoyenneSequentiel(elv, sequence);
			
			if(valeurMoyenne>=0){
				premierexist = 1;
				if(valeurMoyenne>valeurMoyennepremier){
					valeurMoyennepremier = valeurMoyenne;
				}
				dernierexist = 1;
				if(valeurMoyenne<valeurMoyennedernier){
					valeurMoyennedernier = valeurMoyenne;
				}
				noteExist = 1;
				if(valeurMoyenne>=10){
					nbreMoyenne = nbreMoyenne+1;
				}
			}
			
			soeMoy = soeMoy + valeurMoyenne;
		}
		
		if(premierexist == 1) rapportSequentielClasse.setValeurMoyennePremierDansSeq(valeurMoyennepremier);
		
		if(dernierexist == 1)  rapportSequentielClasse.setValeurMoyenneDernierDansSeq(valeurMoyennedernier);
		
		if(noteExist == 1) {
			rapportSequentielClasse.setNbreMoyennePourSeq(nbreMoyenne);
			double nbre_Moyenne = nbreMoyenne;
			double nbreElvRegulier_Seq = nbreElvRegulierSeq;
			double taux = nbre_Moyenne/nbreElvRegulier_Seq;
			taux = taux * 100;
			rapportSequentielClasse.setTauxReussiteSequentiel(taux);
			/*try {
				taux =df.parse(df.format(taux)).doubleValue();
				////System.err.println("taux calcule et tronquer "+taux);
				rapportSequentielClasse.setTauxReussiteSequentiel(taux);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		
		double soe_Moy = soeMoy;
		double nbreElvRegulier_Seq = nbreElvRegulierSeq;
		
		moygen = soe_Moy/nbreElvRegulier_Seq;
		rapportSequentielClasse.setMoyenneGeneralSequence(moygen);
		/*try {
			moygen =df.parse(df.format(moygen)).doubleValue();
			rapportSequentielClasse.setMoyenneGeneralSequence(moygen);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		
		return rapportSequentielClasse;
	}
	
	
	public RapportTrimestrielClasse getRapportTrimestrielClasse(Classes classe, 
			List<Eleves> listofEleveRegulier, Trimestres trimestre){
		
		RapportTrimestrielClasse rapportTrimestreClasse = new RapportTrimestrielClasse();
		
		double valeurMoyennepremier = 0;
		int premierexist = 0;
		
		double valeurMoyennedernier = 200;
		int dernierexist = 0;
		
		int nbreMoyenne = 0;
		int noteExist = -1;
		
		int nbreelvayantMoy = 0;
		int nbreElvRegulierTrim = listofEleveRegulier.size();
		rapportTrimestreClasse.setEffectifEleveRegulier(nbreElvRegulierTrim);
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		
		double moygen = 0;
		double soeMoy=0;
		
		
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = this.getMoyenneTrimestriel(elv, trimestre);
			
			if(valeurMoyenne>=0){
				premierexist = 1;
				if(valeurMoyenne>valeurMoyennepremier){
					valeurMoyennepremier = valeurMoyenne;
				}
				dernierexist = 1;
				if(valeurMoyenne<valeurMoyennedernier){
					valeurMoyennedernier = valeurMoyenne;
				}
				noteExist = 1;
				if(valeurMoyenne>=10){
					nbreMoyenne = nbreMoyenne+1;
				}
				nbreelvayantMoy+=1;
				soeMoy = soeMoy + valeurMoyenne;
			}
			
		}
		
		if(premierexist == 1) rapportTrimestreClasse.setValeurMoyennePremierDansTrim(valeurMoyennepremier);
		
		if(dernierexist == 1)  rapportTrimestreClasse.setValeurMoyenneDernierDansTrim(valeurMoyennedernier);
		
		if(noteExist == 1) {
			rapportTrimestreClasse.setNbreMoyennePourTrim(nbreMoyenne);
			double nbre_Moyenne = nbreMoyenne;
			double nbreElvRegulier_Trim = nbreElvRegulierTrim;
			double taux = nbre_Moyenne/nbreElvRegulier_Trim;
			taux = taux * 100;
			try {
				taux =df.parse(df.format(taux)).doubleValue();
				////System.err.println("taux calcule et tronquer "+taux);
				rapportTrimestreClasse.setTauxReussiteTrimestriel(taux);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(nbreelvayantMoy>0){
			double soe_Moy = soeMoy;
			double nbreelvayant_Moy = nbreelvayantMoy;
			moygen = soe_Moy/nbreelvayant_Moy;
		}
		
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
			rapportTrimestreClasse.setMoyenneGeneralTrimestre(moygen);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return rapportTrimestreClasse;
	}
	
	
	public RapportAnnuelClasse getRapportAnnuelClasse(Classes classe, 
			List<Eleves> listofEleveRegulier, Annee anneeScolaire){
		
		RapportAnnuelClasse rapportAnneeClasse = new RapportAnnuelClasse();
		
		double valeurMoyennepremier = 0;
		int premierexist = 0;
		
		double valeurMoyennedernier = 200;
		int dernierexist = 0;
		
		int nbreMoyenne = 0;
		int noteExist = -1;
		
		int nbreelvayantMoy = 0;
		int nbreElvRegulierAn = listofEleveRegulier.size();
		rapportAnneeClasse.setEffectifEleveRegulier(nbreElvRegulierAn);
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		
		double moygen = 0;
		double soeMoy=0;
		
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = this.getMoyenneAnnuel(elv, anneeScolaire);
			
			if(valeurMoyenne>=0){
				premierexist = 1;
				if(valeurMoyenne>valeurMoyennepremier){
					valeurMoyennepremier = valeurMoyenne;
				}
				dernierexist = 1;
				if(valeurMoyenne<valeurMoyennedernier){
					valeurMoyennedernier = valeurMoyenne;
				}
				noteExist = 1;
				if(valeurMoyenne>=10){
					nbreMoyenne = nbreMoyenne+1;
				}
				nbreelvayantMoy+=1;
				soeMoy = soeMoy + valeurMoyenne;
			}
			
		}
		

		if(premierexist == 1) rapportAnneeClasse.setValeurMoyennePremierDansAn(valeurMoyennepremier);
		
		if(dernierexist == 1)  rapportAnneeClasse.setValeurMoyenneDernierDansAn(valeurMoyennedernier);
		
		if(noteExist == 1) {
			rapportAnneeClasse.setNbreMoyennePourAn(nbreMoyenne);
			double nbre_Moyenne = nbreMoyenne;
			double nbreElvRegulier_An = nbreElvRegulierAn;
			double taux = nbre_Moyenne/nbreElvRegulier_An;
			taux = taux * 100;
			try {
				taux =df.parse(df.format(taux)).doubleValue();
				////System.err.println("taux calcule et tronquer "+taux);
				rapportAnneeClasse.setTauxReussiteAnnuel(taux);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(nbreelvayantMoy>0){
			double soe_Moy = soeMoy;
			double nbreelvayant_Moy = nbreelvayantMoy;
			moygen = soe_Moy/nbreelvayant_Moy;
		}
		
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
			rapportAnneeClasse.setMoyenneGeneralAnnuel(moygen);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rapportAnneeClasse;
		
	}
	
	/*****
	 * masculin pour garçon 
	 * feminin pour fille
	 * @param classe
	 * @param sexe
	 * @return
	 */
	public List<Integer> geteffectifSexeDansClasse(Classes classe){
		List<Eleves> listofEleve = (List<Eleves>) classe.getListofEleves();
		List<Eleves> listofGarcon = new ArrayList<Eleves>();
		List<Eleves> listofFille = new ArrayList<Eleves>();
		List<Integer> listEff = new ArrayList<Integer>();
		for(Eleves elv : listofEleve){
			if(elv.getSexeEleves().equals(new String("masculin"))==true){
				listofGarcon.add(elv);
			}
			else{
				listofFille.add(elv);
			}
		}
		listEff.add(new Integer(listofGarcon.size()));//position 0 les garcons
		listEff.add(new Integer(listofFille.size()));//position 1 les filles
		
		return listEff;
	}
	
	public int geteffectifEleve(Classes classe){
		return classe.getListofEleves().size();
	}

	public List<Integer> geteffectifRegulierSexeDansClasse(Classes classe, Sequences sequence){
		List<Eleves> listofEleveRegulier = getListofEleveRegulier(classe, sequence);
		List<Eleves> listofGarcon = new ArrayList<Eleves>();
		List<Eleves> listofFille = new ArrayList<Eleves>();
		List<Integer> listEff = new ArrayList<Integer>();
		for(Eleves elv : listofEleveRegulier){
			if(elv.getSexeEleves().equals(new String("masculin"))==true){
				listofGarcon.add(elv);
			}
			else{
				listofFille.add(elv);
			}
		}
		listEff.add(new Integer(listofGarcon.size()));//position 0 les garcons
		listEff.add(new Integer(listofFille.size()));//position 1 les filles
		
		return listEff;
	}

	
	public List<Integer> geteffectifRegulierSexeDansClasse(Classes classe, Trimestres trimestre){
		List<Eleves> listofEleveRegulier = getListofEleveRegulierTrimestre(classe, trimestre);
		List<Eleves> listofGarcon = new ArrayList<Eleves>();
		List<Eleves> listofFille = new ArrayList<Eleves>();
		List<Integer> listEff = new ArrayList<Integer>();
		for(Eleves elv : listofEleveRegulier){
			if(elv.getSexeEleves().equals(new String("masculin"))==true){
				listofGarcon.add(elv);
			}
			else{
				listofFille.add(elv);
			}
		}
		listEff.add(new Integer(listofGarcon.size()));//position 0 les garcons
		listEff.add(new Integer(listofFille.size()));//position 1 les filles
		
		return listEff;
	}
	
	
	public List<Integer> geteffectifRegulierSexeDansClasse(Classes classe, Annee annee){
		List<Eleves> listofEleveRegulier = getListofEleveRegulierAnnee(classe, annee);
		List<Eleves> listofGarcon = new ArrayList<Eleves>();
		List<Eleves> listofFille = new ArrayList<Eleves>();
		List<Integer> listEff = new ArrayList<Integer>();
		for(Eleves elv : listofEleveRegulier){
			if(elv.getSexeEleves().equals(new String("masculin"))==true){
				listofGarcon.add(elv);
			}
			else{
				listofFille.add(elv);
			}
		}
		listEff.add(new Integer(listofGarcon.size()));//position 0 les garcons
		listEff.add(new Integer(listofFille.size()));//position 1 les filles
		
		return listEff;
	}
	
	
	public int geteffectifEleveRegulier(Classes classe, Sequences sequence){
		return this.getListofEleveRegulier(classe, sequence).size();
	}
	
	public int geteffectifEleveRegulierPourCoursDansSeq(Classes classe, Cours cours, 
			Sequences sequence){
		return this.getListofEleveRegulierPourCoursDansSeq(classe, cours, sequence).size();
	}
	
	public int geteffectifEleveRegulierTrimestre(Classes classe, Trimestres trimestre){
		return this.getListofEleveRegulierTrimestre(classe, trimestre).size();
	}
	
	public int geteffectifEleveRegulierPourCoursDansTrim(Classes classe, Cours cours, 
			Trimestres trimestre){
		return this.getListofEleveRegulierPourCoursDansTrim(classe, cours, trimestre).size();
	}
	
	public int geteffectifEleveRegulierAnnee(Classes classe, Annee annee){
		return this.getListofEleveRegulierAnnee(classe, annee).size();
	}
	
	public int geteffectifEleveRegulierPourCoursDansAn(Classes classe, Cours cours, 
			Annee annee){
		return this.getListofEleveRegulierPourCoursDansAn(classe, cours, annee).size();
	}
	
	public int AcomposeAuMoinsUnCoursDansSequence(Eleves eleve, List<Cours> listofCours, 
			Sequences sequence){
		int acompose = 0;
		for(Cours cours : listofCours){
			double valNote = this.getValeurNotesFinaleEleve(eleve, cours, sequence);
			if(valNote>0){

				acompose = 1;
			}
		}
		return acompose;
	}
	
	public List<Eleves> getListofEleveAyantComposeAuMoinsUneFoisDansSequence(Classes classe, 
			Sequences sequence){
		
		List<Eleves> listofEleveAyantComposeAuMoinsUneFois = new ArrayList<Eleves>();
		List<Cours> listofCoursClasse = this.getListOfCoursEvalueDansSequence(classe, sequence);
		
		List<Eleves> listofEleveOrdonne = (List<Eleves>) classe.getListofEleves();
		
		for(Eleves elv: listofEleveOrdonne){
			int estRegulier = this.AcomposeAuMoinsUnCoursDansSequence(elv, listofCoursClasse, sequence);
			if(estRegulier == 1){
				listofEleveAyantComposeAuMoinsUneFois.add(elv);
			}
		}
		return listofEleveAyantComposeAuMoinsUneFois;
	}
	
	public List<Eleves> getListofEleveAyantComposeAuMoinsUneFoisDansTrimestre(Classes classe, 
			Trimestres trimestre){
		List<Eleves> listofEleveAyantComposeAuMoinsUneFois = new ArrayList<Eleves>();
		
		for(Sequences seq : trimestre.getListofsequence()){
			List<Eleves> listofelevecompoSeq =
					this.getListofEleveAyantComposeAuMoinsUneFoisDansSequence(classe, seq);
			
			for(Eleves el : listofelevecompoSeq){
				int r = this.estdejadansListeEleve(listofEleveAyantComposeAuMoinsUneFois, el);
				if(r==0){
					listofEleveAyantComposeAuMoinsUneFois.add(el);
				}
			}
			
		}
		
		return listofEleveAyantComposeAuMoinsUneFois;
	}
	
	public List<Eleves> getListofEleveAyantComposeAuMoinsUneFoisDansAnnee(Classes classe, 
			Annee annee){
		
		List<Eleves> listofEleveAyantComposeAuMoinsUneFois = new ArrayList<Eleves>();
		
		for(Trimestres trim : annee.getListoftrimestre()){
			List<Eleves> listofelevecompoTrim =
					this.getListofEleveAyantComposeAuMoinsUneFoisDansTrimestre(classe, trim);
			for(Eleves el : listofelevecompoTrim){
				int r = this.estdejadansListeEleve(listofEleveAyantComposeAuMoinsUneFois, el);
				if(r==0){
					listofEleveAyantComposeAuMoinsUneFois.add(el);
				}
			}
		}
		
		return listofEleveAyantComposeAuMoinsUneFois;
	}
	
	public  Collection<Eleves> getMoyenneSequentielOrdreDecroissant1(Classes classe, Sequences sequence){
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {
			UtilitairesBulletins usi = new UtilitairesBulletins();
			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n=0;
				if(usi.getMoyenneSequentiel(arg0, sequence)>=0 && usi.getMoyenneSequentiel(arg1, sequence)>=0){
					if(usi.getMoyenneSequentiel(arg0, sequence)>usi.getMoyenneSequentiel(arg1, sequence)) n=-1;
					
					if(usi.getMoyenneSequentiel(arg0, sequence)<usi.getMoyenneSequentiel(arg1, sequence)) n=1;
				}
				return n;
			}
			
		};
		
		List<Eleves> listofElevesayantcomposeaumoinsunefois = 
				this.getListofEleveAyantComposeAuMoinsUneFoisDansSequence(classe, sequence);
		Collections.sort((List<Eleves>)listofElevesayantcomposeaumoinsunefois, monComparator);
		
		return listofElevesayantcomposeaumoinsunefois;
		
	}
	
	
	public int getRangSequentielEleveAuMoinsUneNote(Classes classe, Sequences sequence, Eleves eleve){
		int rang=0;
		int i=1;
		List<Eleves> listofEleveParOrdreDecroissantMoyenneSeq = (List<Eleves>) 
				this.getMoyenneSequentielOrdreDecroissant1(classe, sequence);
		
		for(Eleves elv : listofEleveParOrdreDecroissantMoyenneSeq){
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		
		return rang;
	}
	
	public List<Eleves> getListEleveOrdreDecroissantMoyenneSequentiel(Classes classe, Sequences sequence){
		return (List<Eleves>) this.getMoyenneSequentielOrdreDecroissant1(classe, sequence);
	}
	
	public List<Eleves> getListEleveOrdreDecroissantMoyenneTrimestriel(Classes classe, Trimestres trimestre){
		return (List<Eleves>) UtilitairesBulletins.getMoyenneTrimestrielOrdreDecroissant1(classe, trimestre);
	}
	
	public int getRangTrimestrielEleveAuMoinsUneNote(Classes classe, Trimestres trimestre, Eleves eleve){
		int rang=0;
		int i=1;
		List<Eleves> listofEleveParOrdreDecroissantMoyenneTrim = (List<Eleves>) 
				UtilitairesBulletins.getMoyenneTrimestrielOrdreDecroissant1(classe, trimestre);
		
		for(Eleves elv : listofEleveParOrdreDecroissantMoyenneTrim){
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		
		return rang;
	}
	
	
	public int getRangAnnuelEleveAuMoinsUneNote(Eleves eleve, List<Eleves> listofElevesClasseAnnee){
		int rang=0;
		int i=1;
		for(Eleves elv : listofElevesClasseAnnee){
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		return rang;
	}
	
	public int getRangTrimestrielEleveAuMoinsUneNote(Eleves eleve, 
			List<Eleves> listofElevesClasseTrimestre){
		int rang=0;
		int i=1;
		for(Eleves elv : listofElevesClasseTrimestre){
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		return rang;
	}
	
	public int getRangSequentielEleveAuMoinsUneNote(Eleves eleve, 
			List<Eleves> listofElevesClasseSequence){
		int rang=0;
		int i=1;
		for(Eleves elv : listofElevesClasseSequence){
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		return rang;
	}
	
	
	public List<Eleves> getListEleveOrdreDecroissantMoyenneAnnuel(Classes classe, Annee annee){
		return (List<Eleves>) UtilitairesBulletins.getMoyenneAnnuelOrdreDecroissant1(classe, annee);
	}
	
	public List<Cours> getListofCoursDansOrdreEffortAFournir(Eleves eleve, 
			List<Cours> listofCoursEvalue, Sequences sequence){
		List<Cours> listofCoursEffortAFournir = new ArrayList<Cours>();
		List<NoteFinaleCours> listofValeurNote = new ArrayList<NoteFinaleCours>();
		
		for(Cours cours : listofCoursEvalue){
			double valeurNote = this.getValeurNotesFinaleEleve(eleve, cours, sequence);
			if((valeurNote>=0)&&(valeurNote<10)){
				NoteFinaleCours noteFinale = new NoteFinaleCours(cours.getIdCours(), valeurNote);
				noteFinale.setCours(cours);
				listofValeurNote.add(noteFinale);    
			}
		}
		
		Comparator<NoteFinaleCours> monComparator = new Comparator<NoteFinaleCours>() {

			@Override
			public int compare(NoteFinaleCours arg0, NoteFinaleCours arg1) {
				int n= 0;
				if(arg0.getValeurNote() < arg1.getValeurNote()) n=-1;
				if(arg0.getValeurNote() > arg1.getValeurNote()) n=1;
				return n;
			}

		};
		
		Collections.sort((List<NoteFinaleCours>)listofValeurNote, monComparator);

		for(NoteFinaleCours noteFinale : listofValeurNote){
			listofCoursEffortAFournir.add(noteFinale.getCours());
		}
		
		
		return listofCoursEffortAFournir;
	}
	
	public List<Cours> getListofCoursDansOrdreEffortAFournirTrimestre(Eleves eleve, 
			List<Cours> listofCoursEvalue, Trimestres trimestre){
		
		List<Cours> listofCoursEffortAFournir = new ArrayList<Cours>();
		List<NoteFinaleCours> listofValeurNote = new ArrayList<NoteFinaleCours>();
		
		for(Cours cours : listofCoursEvalue){
			double valeurNote = this.getValeurNotesFinaleEleveTrimestre(eleve, cours, trimestre);
			if((valeurNote>=0)&&(valeurNote<10)){
				NoteFinaleCours noteFinale = new NoteFinaleCours(cours.getIdCours(), valeurNote);
				noteFinale.setCours(cours);
				listofValeurNote.add(noteFinale);    
			}
		}
		
		Comparator<NoteFinaleCours> monComparator = new Comparator<NoteFinaleCours>() {

			@Override
			public int compare(NoteFinaleCours arg0, NoteFinaleCours arg1) {
				int n= 0;
				if(arg0.getValeurNote() < arg1.getValeurNote()) n=-1;
				if(arg0.getValeurNote() > arg1.getValeurNote()) n=1;
				return n;
			}

		};
		
		Collections.sort((List<NoteFinaleCours>)listofValeurNote, monComparator);

		for(NoteFinaleCours noteFinale : listofValeurNote){
			listofCoursEffortAFournir.add(noteFinale.getCours());
		}
		
		
		return listofCoursEffortAFournir;

		
	}
	
	
	public List<Cours> getListofCoursDansOrdreEffortAFournirAnnee(Eleves eleve, 
			List<Cours> listofCoursEvalue, Annee annee){
		
		List<Cours> listofCoursEffortAFournir = new ArrayList<Cours>();
		List<NoteFinaleCours> listofValeurNote = new ArrayList<NoteFinaleCours>();
		
		for(Cours cours : listofCoursEvalue){
			double valeurNote = this.getValeurNotesFinaleEleveAnnee(eleve, cours, annee);
			if((valeurNote>=0)&&(valeurNote<10)){
				NoteFinaleCours noteFinale = new NoteFinaleCours(cours.getIdCours(), valeurNote);
				noteFinale.setCours(cours);
				listofValeurNote.add(noteFinale);    
			}
		}
		

		Comparator<NoteFinaleCours> monComparator = new Comparator<NoteFinaleCours>() {

			@Override
			public int compare(NoteFinaleCours arg0, NoteFinaleCours arg1) {
				int n= 0;
				if(arg0.getValeurNote() < arg1.getValeurNote()) n=-1;
				if(arg0.getValeurNote() > arg1.getValeurNote()) n=1;
				return n;
			}

		};
		
		Collections.sort((List<NoteFinaleCours>)listofValeurNote, monComparator);

		for(NoteFinaleCours noteFinale : listofValeurNote){
			listofCoursEffortAFournir.add(noteFinale.getCours());
		}
		
		
		return listofCoursEffortAFournir;
	}
	
	
	public LigneSequentielGroupeCours getLigneSequentielGroupeCours(Eleves eleve, 
			List<Cours> listofCours, Sequences sequence){
		////System.err.println("depart LigneSequentielGroupeCours");
		
		LigneSequentielGroupeCours ligneSequentielGroupeCours = new LigneSequentielGroupeCours();
		
		double moygrp = 0;
		double soeValeurNotegrp = 0;
		double soeCoefCoursgrp = 0;
		double soeCoefCoursgrpCompose=0;
		int possedeNote = 0;
		
		for(Cours coursgrp : listofCours){
			double valeurNoteCoursgrp = this.getValeurNotesFinaleEleve(eleve, coursgrp, sequence);
			double coefCours =  coursgrp.getCoefCours();
			//System.err.println("cours "+coursgrp.getCodeCours()+"|valeurNoteCoursgrp "+valeurNoteCoursgrp);
			if(valeurNoteCoursgrp>=0){
				soeValeurNotegrp = soeValeurNotegrp + (valeurNoteCoursgrp*coefCours);
				soeCoefCoursgrpCompose = soeCoefCoursgrpCompose + coefCours;
				possedeNote = 1;
			}
			////System.err.println("le coef du cours scruter est "+coefCours);
			soeCoefCoursgrp = soeCoefCoursgrp + coefCours;
		}
		
		/*//System.err.println("la somme des notes des cours du groupe est "+soeValeurNotegrp+
				" et la som des coef "+soeCoefCoursgrp+" possedeNote == "+possedeNote);*/
		
		//ligneSequentielGroupeCours.setTotalCoefElevePourGroupeCours(soeCoefCoursgrp);
		
		////System.err.println("soeCoefCoursgrp=="+soeCoefCoursgrp+"  soeCoefCoursgrpCompose== "+soeCoefCoursgrpCompose+" soeValeurNotegrp =="+soeValeurNotegrp);
		if(possedeNote==1){
			if(soeCoefCoursgrpCompose>0){
				moygrp = soeValeurNotegrp/soeCoefCoursgrpCompose;
				ligneSequentielGroupeCours.setTotalCoefElevePourGroupeCours(soeCoefCoursgrpCompose);
			}
			
			ligneSequentielGroupeCours.setMoyenneSeqElevePourGroupeCours(moygrp);
			ligneSequentielGroupeCours.setTotalNoteSeqElevePourGroupeCours(soeValeurNotegrp);
			
			java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
			
			try {
				moygrp =df.parse(df.format(moygrp)).doubleValue();
				soeValeurNotegrp =df.parse(df.format(soeValeurNotegrp)).doubleValue();
				
				ligneSequentielGroupeCours.setMoyenneSeqElevePourGroupeCours(moygrp);
				ligneSequentielGroupeCours.setTotalNoteSeqElevePourGroupeCours(soeValeurNotegrp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else{
			//possedeNote ==0 donc l'eleve n'a compose pour aucun cours dans le groupe
			ligneSequentielGroupeCours.setMoyenneSeqElevePourGroupeCours(-1);
			ligneSequentielGroupeCours.setTotalNoteSeqElevePourGroupeCours(-1);
		}
		
		return ligneSequentielGroupeCours;
	}
	
	public LigneTrimestrielGroupeCours getLigneTrimestrielGroupeCours(Eleves eleve, 
			List<Cours> listofCours, Trimestres trimestre){
		
		LigneTrimestrielGroupeCours ligneTrimestrielGroupeCours = new LigneTrimestrielGroupeCours();
		
		double moygrp = 0;
		double soeValeurNoteTrimgrp = 0;
		double soeCoefCoursgrp = 0;
		double soeCoefCoursgrpCompose=0;
		int possedeNote = 0;
		
		for(Cours coursgrp : listofCours){
			double valeurNoteTrimCoursgrp = this.getValeurNotesFinaleEleveTrimestre(eleve, coursgrp, trimestre);
			double coefCours =  coursgrp.getCoefCours();
			//System.err.println("cours "+coursgrp.getCodeCours()+"|valeurNoteCoursgrp "+valeurNoteCoursgrp);
			if(valeurNoteTrimCoursgrp>=0){
				soeValeurNoteTrimgrp = soeValeurNoteTrimgrp + (valeurNoteTrimCoursgrp*coefCours);
				soeCoefCoursgrpCompose = soeCoefCoursgrpCompose + coefCours;
				possedeNote = 1;
			}
			////System.err.println("le coef du cours scruter est "+coefCours);
			soeCoefCoursgrp = soeCoefCoursgrp + coefCours;
		}
		
		
		if(possedeNote==1){
			if(soeCoefCoursgrpCompose>0){
				moygrp = soeValeurNoteTrimgrp/soeCoefCoursgrpCompose;
				ligneTrimestrielGroupeCours.setTotalCoefElevePourGroupeCours(soeCoefCoursgrpCompose);
			}
			
			java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
			
			try {
				moygrp =df.parse(df.format(moygrp)).doubleValue();
				soeValeurNoteTrimgrp =df.parse(df.format(soeValeurNoteTrimgrp)).doubleValue();
				
				ligneTrimestrielGroupeCours.setMoyenneTrimElevePourGroupeCours(moygrp);
				ligneTrimestrielGroupeCours.setTotalNoteTrimElevePourGroupeCours(soeValeurNoteTrimgrp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else{
			//possedeNote ==0 donc l'eleve n'a compose pour aucun cours dans le groupe
			ligneTrimestrielGroupeCours.setMoyenneTrimElevePourGroupeCours(-1);
			ligneTrimestrielGroupeCours.setTotalNoteTrimElevePourGroupeCours(-1);
		}
		
		
		
		return ligneTrimestrielGroupeCours;
	}
	
	public LigneAnnuelGroupeCours getLigneAnnuelGroupeCours(Eleves eleve, 
			List<Cours> listofCours, Annee annee){
		
		LigneAnnuelGroupeCours ligneAnnuelGroupeCours = new LigneAnnuelGroupeCours();
		
		double moygrp = 0;
		double soeValeurNoteTrimgrp = 0;
		double soeCoefCoursgrp = 0;
		double soeCoefCoursgrpCompose=0;
		int possedeNote = 0;
		

		for(Cours coursgrp : listofCours){
			double valeurNoteAnCoursgrp = this.getValeurNotesFinaleEleveAnnee(eleve, coursgrp, annee);
			double coefCours =  coursgrp.getCoefCours();
			//System.err.println("cours "+coursgrp.getCodeCours()+"|valeurNoteCoursgrp "+valeurNoteCoursgrp);
			if(valeurNoteAnCoursgrp>=0){
				soeValeurNoteTrimgrp = soeValeurNoteTrimgrp + (valeurNoteAnCoursgrp*coefCours);
				soeCoefCoursgrpCompose = soeCoefCoursgrpCompose + coefCours;
				possedeNote = 1;
			}
			////System.err.println("le coef du cours scruter est "+coefCours);
			soeCoefCoursgrp = soeCoefCoursgrp + coefCours;
		}
		

		if(possedeNote==1){
			if(soeCoefCoursgrpCompose>0){
				moygrp = soeValeurNoteTrimgrp/soeCoefCoursgrpCompose;
				ligneAnnuelGroupeCours.setTotalCoefElevePourGroupeCours(soeCoefCoursgrpCompose);
			}
			
			java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
			
			try {
				moygrp =df.parse(df.format(moygrp)).doubleValue();
				soeValeurNoteTrimgrp =df.parse(df.format(soeValeurNoteTrimgrp)).doubleValue();
				
				ligneAnnuelGroupeCours.setMoyenneAnElevePourGroupeCours(moygrp);
				ligneAnnuelGroupeCours.setTotalNoteAnElevePourGroupeCours(soeValeurNoteTrimgrp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else{
			//possedeNote ==0 donc l'eleve n'a compose pour aucun cours dans le groupe
			ligneAnnuelGroupeCours.setMoyenneAnElevePourGroupeCours(-1);
			ligneAnnuelGroupeCours.setTotalNoteAnElevePourGroupeCours(-1);
		}
		
		return ligneAnnuelGroupeCours;
		
	}
	
	
	public double getMoyenneSeqElevePourGroupeCours(Eleves eleve, List<Cours>listofCours, 
			Sequences sequence){
		
		double moygrp = 0;
		double soeValeurNotegrp = 0;
		double soeCoefCoursgrp = 0;
		int possedeNote = 0;
		
		for(Cours coursgrp : listofCours){
			double valeurNoteCoursgrp = this.getValeurNotesFinaleEleve(eleve, coursgrp, sequence);
			////System.err.println("valeurNoteCoursgrp de cours "+coursgrp.getCodeCours()+" est "+valeurNoteCoursgrp);
			double coefcours = coursgrp.getCoefCours();
			if(valeurNoteCoursgrp >= 0){
				soeValeurNotegrp = soeValeurNotegrp + (valeurNoteCoursgrp*coefcours);
				soeCoefCoursgrp = soeCoefCoursgrp + coefcours;
				possedeNote = 1;
			}
		}
		
		if((possedeNote == 1)&&(soeCoefCoursgrp>0)){
			moygrp = soeValeurNotegrp/soeCoefCoursgrp;
			////System.err.println("soeValeurNotegrp=="+soeValeurNotegrp+"|soeCoefCoursgrp=="+soeCoefCoursgrp+"|moygrp calcule == "+moygrp);
			
			java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
			try {
				moygrp =df.parse(df.format(moygrp)).doubleValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(possedeNote == 1) return moygrp; else return -1;
	}
	
	
	public double getMoyenneTrimElevePourGroupeCours(Eleves eleve, List<Cours>listofCours, 
			Trimestres trimestre){
		double moygrp = 0;
		double soemoygrp = 0;
		int possedeNote = 0;
		int nbreMoy = 0;
		
		for(Sequences seq : trimestre.getListofsequence()){
			double moygrpinter = this.getMoyenneSeqElevePourGroupeCours(eleve, listofCours, seq);
			if(moygrpinter>0) {
				soemoygrp = soemoygrp + moygrpinter;
				possedeNote = 1;
				nbreMoy+=1;
			}
		}
		
		if(nbreMoy>0){
			moygrp = soemoygrp/nbreMoy;
			java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
			try {
				moygrp =df.parse(df.format(moygrp)).doubleValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(possedeNote == 1) return moygrp; else return -1;
	}
	
	public double getMoyenneAnElevePourGroupeCours(Eleves eleve, List<Cours>listofCours, 
			Annee annee){
		double moygrp = 0;
		double soemoygrp = 0;
		int possedeNote = 0;
		int nbreMoy = 0;
		
		for(Trimestres trim : annee.getListoftrimestre()){
			double moygrpinter = this.getMoyenneTrimElevePourGroupeCours(eleve, listofCours, trim);
			if(moygrpinter>0) {
				soemoygrp = soemoygrp + moygrpinter;
				possedeNote = 1;
				nbreMoy+=1;
			}
		}
		
		if(nbreMoy>0){
			moygrp = soemoygrp/nbreMoy;
			java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
			try {
				moygrp =df.parse(df.format(moygrp)).doubleValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(possedeNote == 1) return moygrp; else return -1;
	}
	
	
	
	public double getValeurMoyenneDernierPourGrpDansSeq(List<Eleves> listofEleveRegulier, 
			List<Cours> listofCours, Sequences sequence){
		double valeurMoydernier = 200;
		int dernierexist = 0;
		
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = this.getMoyenneSeqElevePourGroupeCours(elv, listofCours, sequence);
			if(valeurMoyenne>=0){
				dernierexist = 1;
				
				if(valeurMoyenne<valeurMoydernier){
					valeurMoydernier = valeurMoyenne;
				}
			}
		}
		
		if (dernierexist == 1) return valeurMoydernier; else return -1;
	}
	
	public double getValeurMoyennePremierPourGrpDansSeq(List<Eleves> listofEleveRegulier, 
			List<Cours> listofCours, Sequences sequence){
		double valeurMoyennepremier = 0;
		int premierexist = 0;
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = this.getMoyenneSeqElevePourGroupeCours(elv, listofCours, sequence);
			////System.err.println("valeurMoyenne == "+valeurMoyenne);
			if(valeurMoyenne>=0){
				premierexist = 1;
				if(valeurMoyenne>valeurMoyennepremier){
					valeurMoyennepremier = valeurMoyenne;
				}
			}
		}
		if (premierexist == 1) return valeurMoyennepremier; else return -1;
	}
	
	
	public double getValeurMoyenneDernierPourGrpDansTrim(List<Eleves> listofEleveRegulier, 
			List<Cours> listofCours, Trimestres trimestre){
		double valeurMoydernier = 200;
		int dernierexist = 0;
		
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = this.getMoyenneTrimElevePourGroupeCours(elv, listofCours, trimestre);
			if(valeurMoyenne>=0){
				dernierexist = 1;
				if(valeurMoyenne<valeurMoydernier){
					valeurMoydernier = valeurMoyenne;
				}
			}
		}
		if (dernierexist == 1) return valeurMoydernier; else return -1;
	}
	

	public double getValeurMoyenneDernierPourGrpDansAn(List<Eleves> listofEleveRegulier, 
			List<Cours> listofCours, Annee annee){
		double valeurMoydernier = 200;
		int dernierexist = 0;
		
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = this.getMoyenneAnElevePourGroupeCours(elv, listofCours, annee);
			if(valeurMoyenne>=0){
				dernierexist = 1;
				if(valeurMoyenne<valeurMoydernier){
					valeurMoydernier = valeurMoyenne;
				}
			}
		}
		if (dernierexist == 1) return valeurMoydernier; else return -1;
	}
	
	
	public double getValeurMoyennePremierPourGrpDansTrim(List<Eleves> listofEleveRegulier, 
			List<Cours> listofCours, Trimestres trimestre){
		
		double valeurMoyennepremier = 0;
		int premierexist = 0;
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = this.getMoyenneTrimElevePourGroupeCours(elv, listofCours, trimestre);
			////System.err.println("valeurMoyenne == "+valeurMoyenne);
			if(valeurMoyenne>=0){
				premierexist = 1;
				if(valeurMoyenne>valeurMoyennepremier){
					valeurMoyennepremier = valeurMoyenne;
				}
			}
		}
		
		if (premierexist == 1) return valeurMoyennepremier; else return -1;
	}
	
	
	
	public double getValeurMoyennePremierPourGrpDansAn(List<Eleves> listofEleveRegulier, 
			List<Cours> listofCours, Annee annee){
		
		double valeurMoyennepremier = 0;
		int premierexist = 0;
		for(Eleves elv : listofEleveRegulier){
			double valeurMoyenne = this.getMoyenneAnElevePourGroupeCours(elv, listofCours, annee);
			////System.err.println("valeurMoyenne == "+valeurMoyenne);
			if(valeurMoyenne>=0){
				premierexist = 1;
				if(valeurMoyenne>valeurMoyennepremier){
					valeurMoyennepremier = valeurMoyenne;
				}
			}
		}
		
		if (premierexist == 1) return valeurMoyennepremier; else return -1;
	}
	
	public List<Eleves> getListofEleveRegulierPourGrpCoursDansSeq(Classes classe, List<Cours> listofCours, 
			Sequences sequence){
		List<Eleves> listofEleveRegulierCoursGrp = new ArrayList<Eleves>();
		
		List<Eleves> listofEleveOrdonne = (List<Eleves>) classe.getListofEleves();
		
		for(Eleves elv: listofEleveOrdonne){
			int estRegulier = this.estRegulierDansSequence(elv, listofCours, sequence);
			if(estRegulier == 1){
				listofEleveRegulierCoursGrp.add(elv);
			}
		}
		////System.err.println("listofEleveRegulierCoursGrp=="+listofEleveRegulierCoursGrp.size());
		return listofEleveRegulierCoursGrp;
	}
	
	public List<Eleves> getListofEleveRegulierPourGrpCoursDansTrim(Classes classe, List<Cours> listofCours, 
			Trimestres trimestre){
		List<Eleves> listofEleveRegulierCoursGrp = new ArrayList<Eleves>();
		for(Sequences seq: trimestre.getListofsequence()){
			List<Eleves> listofEleveRegulierCoursGrpSeq = this.getListofEleveRegulierPourGrpCoursDansSeq(classe, listofCours, seq);
			for(Eleves elv : listofEleveRegulierCoursGrpSeq){
				int r = this.estdejadansListeEleve(listofEleveRegulierCoursGrp, elv);
				if(r==0){
					listofEleveRegulierCoursGrp.add(elv);
				}
			}
		}
		return listofEleveRegulierCoursGrp;
	}
	
	public List<Eleves> getListofEleveRegulierPourGrpCoursDansAn(Classes classe, List<Cours> listofCours, 
			Annee annee){
		List<Eleves> listofEleveRegulierCoursGrp = new ArrayList<Eleves>();
		for(Trimestres trim: annee.getListoftrimestre()){
			List<Eleves> listofEleveRegulierCoursGrpTrim = 
					this.getListofEleveRegulierPourGrpCoursDansTrim(classe, listofCours, trim);
			for(Eleves elv : listofEleveRegulierCoursGrpTrim){
				int r = this.estdejadansListeEleve(listofEleveRegulierCoursGrp, elv);
				if(r==0){
					listofEleveRegulierCoursGrp.add(elv);
				}
			}
		}
		return listofEleveRegulierCoursGrp;
	}
	
	
	
	public List<Eleves> getMoyenneGrpCoursOrdreDecroissantPourSequence(Classes classe, 
			List<Cours> listofCours, Sequences sequence){
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {
			UtilitairesBulletins usi = new UtilitairesBulletins();
			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n = 0;
				if(usi.getMoyenneSeqElevePourGroupeCours(arg0, listofCours, sequence) > 
						usi.getMoyenneSeqElevePourGroupeCours(arg1, listofCours, sequence)) n=-1;
				
				if(usi.getMoyenneSeqElevePourGroupeCours(arg0, listofCours, sequence) < 
						usi.getMoyenneSeqElevePourGroupeCours(arg1, listofCours, sequence)) n=1;
				return n;
			}
			
		};
		
		List<Eleves> listofEleveRegulierPourGrpCoursDansSeq = this.getListofEleveRegulierPourGrpCoursDansSeq(classe, listofCours, sequence);
		////System.err.println("listofEleveRegulierPourGrpCoursDansSeq "+listofEleveRegulierPourGrpCoursDansSeq.size());
		Collections.sort((List<Eleves>)listofEleveRegulierPourGrpCoursDansSeq, monComparator);
		
		return listofEleveRegulierPourGrpCoursDansSeq;
	}
	
	public List<Eleves> getMoyenneGrpCoursOrdreDecroissantPourTrimestre(Classes classe, 
			List<Cours> listofCours, Trimestres trimestre){
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {
			UtilitairesBulletins usi = new UtilitairesBulletins();
			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n = 0;
				if(usi.getMoyenneTrimElevePourGroupeCours(arg0, listofCours, trimestre) > 
						usi.getMoyenneTrimElevePourGroupeCours(arg1, listofCours, trimestre)) n=-1;
				
				if(usi.getMoyenneTrimElevePourGroupeCours(arg0, listofCours, trimestre) < 
						usi.getMoyenneTrimElevePourGroupeCours(arg1, listofCours, trimestre)) n=1;
				return n;
			}
			
		};
		
		List<Eleves> listofEleveRegulierPourGrpCoursDansTrim = 
				this.getListofEleveRegulierPourGrpCoursDansTrim(classe, listofCours, trimestre);
		////System.err.println("listofEleveRegulierPourGrpCoursDansSeq "+listofEleveRegulierPourGrpCoursDansSeq.size());
		Collections.sort((List<Eleves>)listofEleveRegulierPourGrpCoursDansTrim, monComparator);
		
		return listofEleveRegulierPourGrpCoursDansTrim;
	}
	
	public List<Eleves> getMoyenneGrpCoursOrdreDecroissantPourAnnee(Classes classe, 
			List<Cours> listofCours, Annee annee){
		
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {
			UtilitairesBulletins usi = new UtilitairesBulletins();
			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n = 0;
				if(usi.getMoyenneAnElevePourGroupeCours(arg0, listofCours, annee) > 
						usi.getMoyenneAnElevePourGroupeCours(arg1, listofCours, annee)) n=-1;
				
				if(usi.getMoyenneAnElevePourGroupeCours(arg0, listofCours, annee) < 
						usi.getMoyenneAnElevePourGroupeCours(arg1, listofCours, annee)) n=1;
				return n;
			}
			
		};
		
		List<Eleves> listofEleveRegulierPourGrpCoursDansAn = 
				this.getListofEleveRegulierPourGrpCoursDansAn(classe, listofCours, annee);
		////System.err.println("listofEleveRegulierPourGrpCoursDansSeq "+listofEleveRegulierPourGrpCoursDansSeq.size());
		Collections.sort((List<Eleves>)listofEleveRegulierPourGrpCoursDansAn, monComparator);
		
		return listofEleveRegulierPourGrpCoursDansAn;
		
	}
	
	
	
	public int getRangMoyenneSeqElevePourGroupe(Classes classe, List<Cours> listofCours, 
			Sequences sequence, Eleves eleve){
		int rangGrp=0;
		int i = 1;
		int estEvalue = 0;
		for(Cours cours : listofCours){
			if(this.estEvalueDansSequence(classe, cours, sequence)==1){
				estEvalue = 1;
			}
		}
		
		if(estEvalue == 0) return 0;
		
		List<Eleves> listofEleveParOrdreDecroissantMoyenneGrpSeq = (List<Eleves>) this.getMoyenneGrpCoursOrdreDecroissantPourSequence(classe, listofCours, sequence);

		for(Eleves elv : listofEleveParOrdreDecroissantMoyenneGrpSeq){
			////System.err.println("rang de elv ="+elv.getNomsEleves());
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rangGrp = i;
				break;
			}
			i = i+1;
		}
		return rangGrp;
	}
	
	
	public int getRangMoyenneTrimElevePourGroupe(Classes classe, List<Cours> listofCours, 
			Trimestres trimestre, Eleves eleve){
		
		int rangGrp=0;
		int i = 1;
		int estEvalue = 0;
		for(Sequences seq : trimestre.getListofsequence()){
			for(Cours cours : listofCours){
				if(this.estEvalueDansSequence(classe, cours, seq)==1){
					estEvalue = 1;
				}
			}
		}
		
		if(estEvalue == 0) return 0;
		
		List<Eleves> listofEleveParOrdreDecroissantMoyenneGrpTrim = (List<Eleves>) 
				this.getMoyenneGrpCoursOrdreDecroissantPourTrimestre(classe, listofCours, trimestre);

		for(Eleves elv : listofEleveParOrdreDecroissantMoyenneGrpTrim){
			////System.err.println("rang de elv ="+elv.getNomsEleves());
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rangGrp = i;
				break;
			}
			i = i+1;
		}
		return rangGrp;
		
	}
	
	
	public int getRangMoyenneAnElevePourGroupe(Classes classe, List<Cours> listofCours, 
			Annee annee, Eleves eleve){
		
		int rangGrp=0;
		int i = 1;
		int estEvalue = 0;
		for(Trimestres trim : annee.getListoftrimestre()){
			for(Cours cours : listofCours){
				if(this.estEvalueDansTrimestre(classe, cours, trim)==1){
					estEvalue = 1;
				}
			}
		}
		
		if(estEvalue == 0) return 0;
		
		List<Eleves> listofEleveParOrdreDecroissantMoyenneGrpTrim = (List<Eleves>) 
				this.getMoyenneGrpCoursOrdreDecroissantPourAnnee(classe, listofCours, annee);

		for(Eleves elv : listofEleveParOrdreDecroissantMoyenneGrpTrim){
			////System.err.println("rang de elv ="+elv.getNomsEleves());
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rangGrp = i;
				break;
			}
			i = i+1;
		}
		return rangGrp;
		
	}
	
	
	
	public double getMoyenneGeneralPourGroupeCours(Classes classe, List<Cours> listofCours, 
			Sequences sequence){
		
		double moygen=0;
		List<Eleves> listofEleve = (List<Eleves>) classe.getListofEleves();
		double soemoygrpCours = 0;
		int nbreeleveCours = 0;
		
		for(Eleves elv : listofEleve){
			double moygrpelv = this.getMoyenneSeqElevePourGroupeCours(elv, listofCours, sequence);
			////System.err.println(""+elv.getNomsEleves()+" a une moyenne de grp == "+moygrpelv);
			if(moygrpelv>0){
				soemoygrpCours = soemoygrpCours + moygrpelv;
				nbreeleveCours = nbreeleveCours+1;
			}
		}
		
		moygen = soemoygrpCours/nbreeleveCours;
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return moygen;
		
	}
	
	public double getMoyenneGeneralPourGroupeCoursTrim(Classes classe, List<Cours> listofCours, 
			Trimestres trimestre){
		double moygen=0;
		double soemoygen=0;
		int nbreMoy=0;
		
		for(Sequences seq : trimestre.getListofsequence()){
			double moygeninter = this.getMoyenneGeneralPourGroupeCours(classe, listofCours, seq);
			if(moygeninter>0){
				soemoygen = soemoygen +moygeninter;
				nbreMoy+=1;
			}
		}
		
		if(nbreMoy>0) moygen = soemoygen/nbreMoy;
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return moygen;
	}
	
	public double getMoyenneGeneralPourGroupeCoursAn(Classes classe, List<Cours> listofCours, 
			Annee annee){
		double moygen=0;
		double soemoygen=0;
		int nbreMoy = 0;
		
		for(Trimestres trim : annee.getListoftrimestre()){
			double moygeninter = this.getMoyenneGeneralPourGroupeCoursTrim(classe, listofCours, trim);
			if(moygeninter>0){
				soemoygen = soemoygen +moygeninter;
				nbreMoy+=1;
			}
		}
		
		if(nbreMoy>0) moygen = soemoygen/nbreMoy;
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return moygen;
	}
	
	
	public double getTauxReussitePourGroupeCours(Classes classe, List<Cours> listofCours, 
			Sequences sequence){
		double tauxreussite = 0;
		
		List<Eleves> listofEleve = (List<Eleves>) classe.getListofEleves();
		double nbremoygrpCours = 0;
		double nbresousmoygrpCours = 0;
		double nbreeleveCours = 0;
		
		for(Eleves elv : listofEleve){
			double moygrpelv = this.getMoyenneSeqElevePourGroupeCours(elv, listofCours, sequence);
			if(moygrpelv>=0){
				if(moygrpelv>=10){
					nbremoygrpCours = nbremoygrpCours+1;
				}
				else{
					nbresousmoygrpCours = nbresousmoygrpCours+1;
				}
				nbreeleveCours = nbreeleveCours+1;
			}
		}
		
		if(nbreeleveCours>0) tauxreussite = (nbremoygrpCours/nbreeleveCours)*100;
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			tauxreussite =df.parse(df.format(tauxreussite)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(nbreeleveCours>0) return tauxreussite; else return -1;
	}
	
	public double getTauxReussitePourGroupeCoursTrim(Classes classe, List<Cours> listofCours, 
			Trimestres trimestre){
		double tauxreussite = 0;
		double soetauxreussite = 0;
		double nbretaux = 0;
		
		for(Sequences seq : trimestre.getListofsequence()){
			double tauxinter = this.getTauxReussitePourGroupeCours(classe, listofCours, seq);
			if(tauxinter>0){
				soetauxreussite = soetauxreussite + tauxinter;
				nbretaux+=1;
			}
		}
		//System.err.println("tauxreussite trim "+trimestre.getNumeroTrim()+" == "+soetauxreussite);
		if(nbretaux>0) tauxreussite = soetauxreussite/nbretaux;
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			tauxreussite =df.parse(df.format(tauxreussite)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(nbretaux>0) return tauxreussite; else return -1;
	}
	
	public double getTauxReussitePourGroupeCoursAn(Classes classe, List<Cours> listofCours, 
			Annee annee){
		double tauxreussite = 0;
		double soetauxreussite = 0;
		double nbretaux = 0;
		
		for(Trimestres trim : annee.getListoftrimestre()){
			double tauxinter = this.getTauxReussitePourGroupeCoursTrim(classe, listofCours, trim);
			if(tauxinter>0){
				soetauxreussite = soetauxreussite + tauxinter;
				nbretaux+=1;
			}
		}
		//System.err.println("tauxreussite trim "+trimestre.getNumeroTrim()+" == "+soetauxreussite);
		if(nbretaux>0) tauxreussite = soetauxreussite/nbretaux;
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			tauxreussite =df.parse(df.format(tauxreussite)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(nbretaux>0) return tauxreussite; else return -1;
	}
	
	public List<Eleves> getListofEleveRegulierPourCoursDansSeq(Classes classe, Cours cours, 
			Sequences sequence){
		List<Eleves> listofEleveRegulierCours = new ArrayList<Eleves>();
		List<Cours> listofCoursClasse = new ArrayList<Cours>();
		if(this.estEvalueDansSequence(classe, cours, sequence)==1){
			listofCoursClasse.add(cours);
		}
		List<Eleves> listofEleveOrdonne = (List<Eleves>) classe.getListofEleves();
		for(Eleves elv: listofEleveOrdonne){
			int estRegulier = this.estRegulierDansSequence(elv, listofCoursClasse, sequence);
			if(estRegulier == 1){
				listofEleveRegulierCours.add(elv);
			}
		}
		return listofEleveRegulierCours;
	}
	
	public List<Eleves> getListofEleveRegulierPourCoursDansTrim(Classes classe, Cours cours, 
			Trimestres trimestre){
		List<Eleves> listofEleveRegulierCoursTrim = new ArrayList<Eleves>();
		for(Sequences seq : trimestre.getListofsequence()){
			List<Eleves> listofEleveRegulierCours = 
					this.getListofEleveRegulierPourCoursDansSeq(classe, cours, seq);
			for(Eleves elv : listofEleveRegulierCours){
				int r = this.estdejadansListeEleve(listofEleveRegulierCoursTrim, elv);
				if(r==0){
					listofEleveRegulierCoursTrim.add(elv);
				}
			}
		}
		return listofEleveRegulierCoursTrim;
	}
	
	public List<Eleves> getListofEleveRegulierPourCoursDansAn(Classes classe, Cours cours, 
			Annee annee){
		List<Eleves> listofEleveRegulierCoursAn = new ArrayList<Eleves>();
		for(Trimestres trim : annee.getListoftrimestre()){
			List<Eleves> listofEleveRegulierCours = 
					this.getListofEleveRegulierPourCoursDansTrim(classe, cours, trim);
			for(Eleves elv : listofEleveRegulierCours){
				int r = this.estdejadansListeEleve(listofEleveRegulierCoursAn, elv);
				if(r==0){
					listofEleveRegulierCoursAn.add(elv);
				}
			}
		}
		return listofEleveRegulierCoursAn;
	}
	
	
	/****************************************************************
	 * Cette methode retourne le nombre d'eleve d'une classe ayant une note >=10
	 * Il faut au prealable que l'eleve ait compose le cours dans cette séquence
	 * @param classe
	 * @param cours
	 * @param sequence
	 * @return
	 */
	public int getNbreNoteDansCourspourSeq(Classes classe, Cours cours, 
			Sequences sequence){
		int nbreNote = 0;
		int noteExist = -1;
		
		List<Eleves> listofEleveRegulierCours = this.getListofEleveRegulierPourCoursDansSeq(classe, cours, sequence);
		
		for(Eleves elv : listofEleveRegulierCours){
			double valNote = this.getValeurNotesFinaleEleve(elv, cours, sequence);
			if(valNote>=0){
				noteExist = 1;
				if(valNote>=10){
					nbreNote = nbreNote+1;
				}
			}
		}
		if(noteExist == 1) return nbreNote; else return -1;
		
	}
	
	public int getNbreSousNoteDansCourspourSeq(Classes classe, Cours cours, 
			Sequences sequence){
		int nbreSousNote = 0;
		int noteExist = -1;
		
		List<Eleves> listofEleveRegulierCours = this.getListofEleveRegulierPourCoursDansSeq(classe, cours, sequence);
		
		for(Eleves elv : listofEleveRegulierCours){
			double valNote = this.getValeurNotesFinaleEleve(elv, cours, sequence);
			if(valNote>=0){
				noteExist = 1;
				if(valNote<10){
					nbreSousNote = nbreSousNote+1;
				}
			}
		}
		if(noteExist == 1) return nbreSousNote; else return -1;
		
	}

	/************************************************************
	 * Cette methode retourne le nombre de note >=10 dans le trimestre
	 * @param classe
	 * @param cours
	 * @param trimestre
	 * @return
	 */
	public int getNbreNoteDansCourspourTrim(Classes classe, Cours cours, 
			Trimestres trimestre){
		int nbreNote = 0;
		int noteExist = -1;
		
		List<Eleves> listofEleveRegulierCoursTrim = 
				this.getListofEleveRegulierPourCoursDansTrim(classe, cours, trimestre);
		

		for(Eleves elv : listofEleveRegulierCoursTrim){
			double valNote = this.getValeurNotesFinaleEleveTrimestre(elv, cours, trimestre);
			if(valNote>=0){
				noteExist = 1;
				if(valNote>=10){
					nbreNote = nbreNote+1;
				}
			}
		}
		if(noteExist == 1) return nbreNote; else return -1;
		
	}
	
	public int getNbreSousNoteDansCourspourTrim(Classes classe, Cours cours, 
			Trimestres trimestre){
		int nbreSousNote = 0;
		int noteExist = -1;
		
		List<Eleves> listofEleveRegulierCoursTrim = 
				this.getListofEleveRegulierPourCoursDansTrim(classe, cours, trimestre);
		

		for(Eleves elv : listofEleveRegulierCoursTrim){
			double valNote = this.getValeurNotesFinaleEleveTrimestre(elv, cours, trimestre);
			if(valNote>=0){
				noteExist = 1;
				if(valNote<10){
					nbreSousNote = nbreSousNote+1;
				}
			}
		}
		if(noteExist == 1) return nbreSousNote; else return -1;
		
	}
	
	/*************************************************************************
	 * Cette methode retourne le nombre de moyenne >=10 dans l'année
	 * @param classe
	 * @param cours
	 * @param annee
	 * @return
	 */
	public int getNbreNoteDansCourspourAn(Classes classe, Cours cours, Annee annee){
		int nbreNote = 0;
		int noteExist = -1;
		
		List<Eleves> listofEleveRegulierCoursAn = 
				this.getListofEleveRegulierPourCoursDansAn(classe, cours, annee);
		

		for(Eleves elv : listofEleveRegulierCoursAn){
			double valNote = this.getValeurNotesFinaleEleveAnnee(elv, cours, annee);
			if(valNote>=0){
				noteExist = 1;
				if(valNote>=10){
					nbreNote = nbreNote+1;
				}
			}
		}
		
		if(noteExist == 1) return nbreNote; else return -1;

	}
	
	public int getNbreSousNoteDansCourspourAn(Classes classe, Cours cours, Annee annee){
		int nbreSousNote = 0;
		int noteExist = -1;
		
		List<Eleves> listofEleveRegulierCoursAn = 
				this.getListofEleveRegulierPourCoursDansAn(classe, cours, annee);
		

		for(Eleves elv : listofEleveRegulierCoursAn){
			double valNote = this.getValeurNotesFinaleEleveAnnee(elv, cours, annee);
			if(valNote>=0){
				noteExist = 1;
				if(valNote<10){
					nbreSousNote = nbreSousNote+1;
				}
			}
		}
		
		if(noteExist == 1) return nbreSousNote; else return -1;

	}
	

	
	
	/****************************************************************************
	 * Cette methode retourne la note du denier, du premier, du taux de réussite et de la
	 * moyenne générale de la classe sur un cours donnée dans la Séquence
	 * @param classe
	 * @param cours
	 * @param sequence
	 * @return
	 */
	public RapportSequentielCours getRapportSequentielCours(Classes classe, Cours cours, 
			Sequences sequence){
		RapportSequentielCours rapportSequentielCours = new RapportSequentielCours();
		
		double valeurNotepremier = 0;
		int premierexist = 0;
		
		double valeurNotedernier = 200;
		int dernierexist = 0;
		
		List<Eleves> listofEleveRegulierCours = this.getListofEleveRegulierPourCoursDansSeq(classe, cours, sequence);
		
		for(Eleves elv : listofEleveRegulierCours){
			double valeurNote = this.getValeurNotesFinaleEleve(elv, cours, sequence);
			if(valeurNote>=0){
				dernierexist = 1;
				if(valeurNote<valeurNotedernier){
					valeurNotedernier = valeurNote;
				}
				premierexist = 1;
				if(valeurNote>valeurNotepremier){
					valeurNotepremier = valeurNote;
				}
			}
		}
		

		if(dernierexist == 1) rapportSequentielCours.setValeurNoteDernier(valeurNotedernier);
		if(premierexist == 1) rapportSequentielCours.setValeurNotePremier(valeurNotepremier);

		double pourcentage = 0.0;
		
		int nbreNotes = this.getNbreNoteDansCourspourSeq(classe, cours, sequence);
		double nbreNote = new Double(nbreNotes).doubleValue();
		
		int effectifreguliers = this.geteffectifEleveRegulier(classe, sequence);
		double effectifregulier = new Double(effectifreguliers).doubleValue();
		
		if(effectifreguliers>0 && nbreNotes>=0)	{
			//System.err.println("NbreNote "+nbreNote);
			pourcentage = nbreNote*100;
			pourcentage = pourcentage/effectifregulier;
		}

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			pourcentage =df.parse(df.format(pourcentage)).doubleValue();
			//System.err.println("pourcentagessss "+pourcentage);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rapportSequentielCours.setTauxReussiteCoursSeq(pourcentage);
		//return pourcentage;
		
		double moygen = 0;
		//Tous les élèves de la classe puisque certain doivent avoir composé et d'autre pas
		List<Eleves> listofEleve = (List<Eleves>) classe.getListofEleves();
		double soeNoteFinaleCours = 0;
		int nbreeleveCours = 0;
		
		for(Eleves elv : listofEleve){
			double valeurNotesFinaleEleve = this.getValeurNotesFinaleEleve(elv, cours, sequence);
			if(valeurNotesFinaleEleve>=0){
				soeNoteFinaleCours = soeNoteFinaleCours + valeurNotesFinaleEleve;
				nbreeleveCours = nbreeleveCours+1;
			}
		}
		
		moygen = soeNoteFinaleCours/nbreeleveCours;
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
			rapportSequentielCours.setMoyenneGeneralCoursSeq(moygen);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return rapportSequentielCours;
	}
	
	/***************************************************************************
	 * Cette methode retourne la note du denier, du premier, du taux de réussite et de la
	 * moyenne générale de la classe sur un cours donnée dans le trimestre
	 * @param classe
	 * @param cours
	 * @param trimestre
	 * @return
	 */
	public RapportTrimestrielCours getRapportTrimestrielCours(Classes classe, Cours cours, 
			Trimestres trimestre){
		RapportTrimestrielCours rapportTrimestrielCours = new RapportTrimestrielCours();
		
		double valeurNotepremier = 0;
		int premierexist = 0;
		
		double valeurNotedernier = 200;
		int dernierexist = 0;
		
		List<Eleves> listofEleveRegulierCoursTrim = 
				this.getListofEleveRegulierPourCoursDansTrim(classe, cours, trimestre);
		
		for(Eleves elv : listofEleveRegulierCoursTrim){
			double valeurNote = this.getValeurNotesFinaleEleveTrimestre(elv, cours, trimestre);
			if(valeurNote>=0){
				dernierexist = 1;
				if(valeurNote<valeurNotedernier){
					valeurNotedernier = valeurNote;
				}
				premierexist = 1;
				if(valeurNote>valeurNotepremier){
					valeurNotepremier = valeurNote;
				}
			}
		}
		
		if(dernierexist == 1) rapportTrimestrielCours.setValeurNoteDernier(valeurNotedernier);
		if(premierexist == 1) rapportTrimestrielCours.setValeurNotePremier(valeurNotepremier);

		double pourcentage = 0;
		double nbreNote = this.getNbreNoteDansCourspourTrim(classe, cours, trimestre);
		
		double effectifregulier = this.geteffectifEleveRegulierTrimestre(classe, trimestre);
		
		pourcentage = (nbreNote/effectifregulier)*100;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			pourcentage =df.parse(df.format(pourcentage)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		rapportTrimestrielCours.setTauxReussiteCoursTrim(pourcentage);
		//return pourcentage;
		
		double moygen = 0;
		//Tous les élèves de la classe puisque certain doivent avoir composé et d'autre pas
		List<Eleves> listofEleve = (List<Eleves>) classe.getListofEleves();
		double soeNoteFinaleCours = 0;
		int nbreeleveCours = 0;
		
		for(Eleves elv : listofEleve){
			double valeurNotesFinaleEleveTrim = 
					this.getValeurNotesFinaleEleveTrimestre(elv, cours, trimestre);
			if(valeurNotesFinaleEleveTrim>=0){
				soeNoteFinaleCours = soeNoteFinaleCours + valeurNotesFinaleEleveTrim;
				nbreeleveCours = nbreeleveCours+1;
			}
		}
		
		moygen = soeNoteFinaleCours/nbreeleveCours;
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
			rapportTrimestrielCours.setMoyenneGeneralCoursTrim(moygen);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return rapportTrimestrielCours;
	}
	
	/***********************************************************************
	 * Cette methode retourne la note du denier, du premier, du taux de réussite et de la
	 * moyenne générale de la classe sur un cours donnée dans l'année
	 * @param classe
	 * @param cours
	 * @param annee
	 * @return
	 */
	public RapportAnnuelCours getRapportAnnuelCours(Classes classe, Cours cours, 
			Annee annee){
		RapportAnnuelCours rapportAnnuelCours = new RapportAnnuelCours();
		
		double valeurNotepremier = 0;
		int premierexist = 0;
		
		double valeurNotedernier = 200;
		int dernierexist = 0;
		
		List<Eleves> listofEleveRegulierCoursAn = 
				this.getListofEleveRegulierPourCoursDansAn(classe, cours, annee);
		
		

		for(Eleves elv : listofEleveRegulierCoursAn){
			double valeurNote = this.getValeurNotesFinaleEleveAnnee(elv, cours, annee);
			if(valeurNote>=0){
				dernierexist = 1;
				if(valeurNote<valeurNotedernier){
					valeurNotedernier = valeurNote;
				}
				premierexist = 1;
				if(valeurNote>valeurNotepremier){
					valeurNotepremier = valeurNote;
				}
			}
		}
		
		if(dernierexist == 1) rapportAnnuelCours.setValeurNoteDernier(valeurNotedernier);
		if(premierexist == 1) rapportAnnuelCours.setValeurNotePremier(valeurNotepremier);

		double pourcentage = 0;
		double nbreNote = this.getNbreNoteDansCourspourAn(classe, cours, annee);
		
		double effectifregulier = this.geteffectifEleveRegulierAnnee(classe, annee);
		
		pourcentage = (nbreNote/effectifregulier)*100;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			pourcentage =df.parse(df.format(pourcentage)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		rapportAnnuelCours.setTauxReussiteCoursAn(pourcentage);
		//return pourcentage;
		
		double moygen = 0;
		//Tous les élèves de la classe puisque certain doivent avoir composé et d'autre pas
		List<Eleves> listofEleve = (List<Eleves>) classe.getListofEleves();
		double soeNoteFinaleCours = 0;
		int nbreeleveCours = 0;
		
		for(Eleves elv : listofEleve){
			double valeurNotesFinaleEleveTrim = 
					this.getValeurNotesFinaleEleveAnnee(elv, cours, annee);
			if(valeurNotesFinaleEleveTrim>=0){
				soeNoteFinaleCours = soeNoteFinaleCours + valeurNotesFinaleEleveTrim;
				nbreeleveCours = nbreeleveCours+1;
			}
		}
		
		moygen = soeNoteFinaleCours/nbreeleveCours;
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
			rapportAnnuelCours.setMoyenneGeneralCoursAn(moygen);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		return rapportAnnuelCours;
	}
	
	
	/************************************************************
	 * Cette methode range les élèves d'une classe dans l'ordre décroissant des notes 
	 * obtenues pour le cours et la sequence passe en paramètre
	 * @param classe
	 * @param cours
	 * @param sequence
	 * @return
	 */
	public Collection<Eleves> getNoteCoursOrdreDecroissantPourSequence(Classes classe, Cours cours, 
			Sequences sequence){
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {
			UtilitairesBulletins usi = new UtilitairesBulletins();
			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				int n = 0;
				if(usi.getValeurNotesFinaleEleve(arg0,cours, sequence)>=0 && 
						usi.getValeurNotesFinaleEleve(arg1, cours, sequence)>=0){

					if(usi.getValeurNotesFinaleEleve(arg0, cours, sequence) > 
							usi.getValeurNotesFinaleEleve(arg1, cours, sequence)) n=-1;

					if(usi.getValeurNotesFinaleEleve(arg0, cours, sequence) < 
							usi.getValeurNotesFinaleEleve(arg1, cours, sequence)) n=1;

				}
				return n;
			}
		};
		
		List<Eleves> listofEleveregulierPourCoursDansSeq = this.getListofEleveRegulierPourCoursDansSeq(classe, cours, sequence);
		Collections.sort(listofEleveregulierPourCoursDansSeq, monComparator);
		
		return listofEleveregulierPourCoursDansSeq;
	}
	
	/*************************************************************************************
	 * Cette methode retourne une Map dont chaque entrée a comme cle l'identifiant d'un cours et 
	 * pour valeur la liste des élèves classé dans l'ordre décroissant des valeurs des notes obtenues
	 * dans la séquence. la classe et la séquence sont pris en paramètre de la methode
	 * @param classe
	 * @param sequence
	 * @return
	 */
	public Map<Long, List<Eleves>> getMapCoursElevesOrdreDecroissantSequence(Classes classe, 
			Sequences sequence){
		Map<Long, List<Eleves>> mapCoursElevesOrdreDecroissantSequence = 
				new HashMap<Long, List<Eleves>>();
		for(Cours cours : classe.getListofCours()){
			List<Eleves> listofEleveregulierPourCoursDansSeq = (List<Eleves>) 
					this.getNoteCoursOrdreDecroissantPourSequence(classe, cours, sequence);
			mapCoursElevesOrdreDecroissantSequence.put(cours.getIdCours(), 
					listofEleveregulierPourCoursDansSeq);
		}
		
		return mapCoursElevesOrdreDecroissantSequence;
	}
	
	public Collection<Eleves> getNoteCoursOrdreDecroissantPourTrimestre(Classes classe, 
			Cours cours, Trimestres trimestre){
		UtilitairesBulletins usi = new UtilitairesBulletins();
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {

			@Override
			public int compare(Eleves arg0, Eleves arg1) {
				
				int n = 0;
				if(usi.getValeurNotesFinaleEleveTrimestre(arg0,cours, trimestre)>=0 && 
						usi.getValeurNotesFinaleEleveTrimestre(arg1, cours, trimestre)>=0){

					if(usi.getValeurNotesFinaleEleveTrimestre(arg0, cours, trimestre) > 
							usi.getValeurNotesFinaleEleveTrimestre(arg1, cours, trimestre)) n=-1;

					if(usi.getValeurNotesFinaleEleveTrimestre(arg0, cours, trimestre) < 
							usi.getValeurNotesFinaleEleveTrimestre(arg1, cours, trimestre)) n=1;

				}
				return n;
			}
			
		};
		List<Eleves> listofEleveregulierPourCoursDansTrim = 
				this.getListofEleveRegulierPourCoursDansTrim(classe, cours, trimestre);
		Collections.sort(listofEleveregulierPourCoursDansTrim, monComparator);
		
		return listofEleveregulierPourCoursDansTrim;
	}
	
	
	public Collection<Eleves> getNoteCoursOrdreDecroissantPourAnnee(Classes classe, 
			Cours cours, Annee annee){
		UtilitairesBulletins usi = new UtilitairesBulletins();
		Comparator<Eleves> monComparator = new Comparator<Eleves>() {

			@Override
			public int compare(Eleves arg0, Eleves arg1) {

				int n = 0;
				if(usi.getValeurNotesFinaleEleveAnnee(arg0,cours, annee)>=0 && 
						usi.getValeurNotesFinaleEleveAnnee(arg1, cours, annee)>=0){

					if(usi.getValeurNotesFinaleEleveAnnee(arg0, cours, annee) > 
							usi.getValeurNotesFinaleEleveAnnee(arg1, cours, annee)) n=-1;

					if(usi.getValeurNotesFinaleEleveAnnee(arg0, cours, annee) < 
							usi.getValeurNotesFinaleEleveAnnee(arg1, cours, annee)) n=1;

				}
				return n;
			}
			
		};
		List<Eleves> listofEleveregulierPourCoursDansAn = 
				this.getListofEleveRegulierPourCoursDansAn(classe, cours, annee);
		Collections.sort(listofEleveregulierPourCoursDansAn, monComparator);
		
		return listofEleveregulierPourCoursDansAn;
	}
	
	
	
	public Map<Long, List<Eleves>> getMapCoursElevesOrdreDecroissantTrimestre(Classes classe, 
			Trimestres trimestre){
		Map<Long, List<Eleves>> mapCoursElevesOrdreDecroissantTrimestre = 
				new HashMap<Long, List<Eleves>>();
		for(Cours cours : classe.getListofCours()){
			List<Eleves> listofEleveregulierPourCoursDansTrim = (List<Eleves>) 
					this.getNoteCoursOrdreDecroissantPourTrimestre(classe, cours, trimestre);
			mapCoursElevesOrdreDecroissantTrimestre.put(cours.getIdCours(), 
					listofEleveregulierPourCoursDansTrim);
		}
		
		return mapCoursElevesOrdreDecroissantTrimestre;
	}
	
	
	public Map<Long, List<Eleves>> getMapCoursElevesOrdreDecroissantAnnee(Classes classe, 
			Annee annee){
		
		Map<Long, List<Eleves>> mapCoursElevesOrdreDecroissantAnnee = 
				new HashMap<Long, List<Eleves>>();
		
		for(Cours cours : classe.getListofCours()){
			List<Eleves> listofEleveregulierPourCoursDansAn = (List<Eleves>) 
					this.getNoteCoursOrdreDecroissantPourAnnee(classe, cours, annee);
			mapCoursElevesOrdreDecroissantAnnee.put(cours.getIdCours(), 
					listofEleveregulierPourCoursDansAn);
		}
		
		return mapCoursElevesOrdreDecroissantAnnee;
	}
	
	
	
	public int getRangNoteSequentielElevePourCours(Classes classe, Cours cours, Sequences sequence, Eleves eleve){
		int rang = 0;
		int i = 1;
		
		int estEvalue = this.estEvalueDansSequence(classe, cours, sequence);
		if(estEvalue == 0) return 0;
		
		List<Eleves> listofEleveParOrdreDecroissantNoteSeq = (List<Eleves>) this.getNoteCoursOrdreDecroissantPourSequence(classe, cours, sequence);
		
		for(Eleves elv : listofEleveParOrdreDecroissantNoteSeq){
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		
		return rang;
		
	}
	
	public int getRangNoteSequentielElevePourCours_opt( Eleves eleve, 
			List<Eleves> listofElevesOrdreDecroissantSequence){
		int rang = 0;
		int i = 1;
		
		for(Eleves elv : listofElevesOrdreDecroissantSequence){
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		
		return rang;
		
	}
	
	
	public int getRangNoteTrimestrielElevePourCours_opt( Eleves eleve, 
			List<Eleves> listofElevesOrdreDecroissantTrimestre){
		int rang = 0;
		int i = 1;
		
		for(Eleves elv : listofElevesOrdreDecroissantTrimestre){
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		
		return rang;
		
	}
	
	public int getRangNoteAnnuelElevePourCours( Eleves eleve, 
			List<Eleves> listofElevesOrdreDecroissantAnnee){
		int rang = 0;
		int i = 1;
		
		for(Eleves elv : listofElevesOrdreDecroissantAnnee){
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		
		return rang;
		
	}
	
	public int getRangNoteTrimestrielElevePourCours(Classes classe, Cours cours, 
			Trimestres trimestre, Eleves eleve){
		int rang = 0;
		int i = 1;
		int estEvalue = this.estEvalueDansTrimestre(classe, cours, trimestre);
		if(estEvalue == 0) return 0;
		
		List<Eleves> listofEleveParOrdreDecroissantNoteTrim = (List<Eleves>) 
				this.getNoteCoursOrdreDecroissantPourTrimestre(classe, cours, trimestre);
		
		for(Eleves elv : listofEleveParOrdreDecroissantNoteTrim){
			if(elv.getIdEleves().longValue() == eleve.getIdEleves().longValue()){
				rang = i;
				break;
			}
			i = i+1;
		}
		
		return rang;
	}
	
	public double getMoyenneGeneralCoursSeq(Classes classe, Cours cours, Sequences sequence){
		double moygen = 0;
		//Tous les élèves de la classe puisque certain doivent avoir composé et d'autre pas
		List<Eleves> listofEleve = (List<Eleves>) classe.getListofEleves();
		double soeNoteFinaleCours = 0;
		int nbreeleveCours = 0;
		
		for(Eleves elv : listofEleve){
			double valeurNote = this.getValeurNotesFinaleEleve(elv, cours, sequence);
			if(valeurNote>=0){
				soeNoteFinaleCours = soeNoteFinaleCours + valeurNote;
				nbreeleveCours = nbreeleveCours+1;
			}
		}
		
		moygen = soeNoteFinaleCours/nbreeleveCours;

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return moygen;
		
	}
	
	public double getMoyenneGeneralCoursTrim(Classes classe, Cours cours, 
			Trimestres trimestre){
		double moygen = 0;
		double soemoygen = 0;
		int nbremoygen=0;
		for(Sequences seq: trimestre.getListofsequence()){
			double moygenseq = this.getMoyenneGeneralCoursSeq(classe, cours, seq);
			if(moygenseq>0){
				soemoygen = soemoygen + moygenseq;
				nbremoygen+=1;
			}
		}
		
		if(nbremoygen>0){
			moygen = soemoygen/nbremoygen;
		}
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return moygen;
	}

	public double getMoyenneGeneralCoursAn(Classes classe, Cours cours, 
			Annee annee){
		double moygen = 0;
		double soemoygen = 0;
		int nbremoygen=0;
		
		for(Trimestres trim : annee.getListoftrimestre()){
			double moygentrim = this.getMoyenneGeneralCoursTrim(classe, cours, trim);
			if(moygentrim>0){
				soemoygen = soemoygen + moygentrim;
				nbremoygen+=1;
			}
		}

		if(nbremoygen>0){
			moygen = soemoygen/nbremoygen;
		}
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			moygen =df.parse(df.format(moygen)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return moygen;
	}
	
	public double getTauxReussiteCoursSeq(Classes classe, Cours cours, Sequences sequence){
		double pourcentage = 0.0;
		int nbreNotes = this.getNbreNoteDansCourspourSeq(classe, cours, sequence);
		int effectifreguliers = this.geteffectifEleveRegulierPourCoursDansSeq(classe, cours, sequence);

		double nbreNote = new Double(nbreNotes).doubleValue();
		double effectifregulier = new Double(effectifreguliers).doubleValue();
		
		
		if(effectifreguliers>0 && nbreNotes>=0)	pourcentage = (nbreNote/effectifregulier)*100;
		//System.out.println("nbreNote "+nbreNote+" effectifregulier "+effectifregulier+" pourcentage "+pourcentage);
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			pourcentage =df.parse(df.format(pourcentage)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pourcentage;
		
	}

	public double getTauxReussiteCoursTrim(Classes classe, Cours cours, Trimestres trimestre){
		double pourcentage = 0;
		
		int nbreNotes = this.getNbreNoteDansCourspourTrim(classe, cours, trimestre);
		int effectifreguliers = this.geteffectifEleveRegulierPourCoursDansTrim(classe, cours, trimestre);

		double nbreNote = new Double(nbreNotes).doubleValue();
		double effectifregulier = new Double(effectifreguliers).doubleValue();
		
		if(effectifreguliers>0 && nbreNotes>=0)	pourcentage = (nbreNote/effectifregulier)*100;
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			pourcentage =df.parse(df.format(pourcentage)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pourcentage;
	}
	
	public double getTauxReussiteCoursAn(Classes classe, Cours cours, Annee annee){
		double pourcentage = 0;
		
		int nbreNotes = this.getNbreNoteDansCourspourAn(classe, cours, annee);
		int effectifreguliers = this.geteffectifEleveRegulierPourCoursDansAn(classe,  cours, annee);

		double nbreNote = new Double(nbreNotes).doubleValue();
		double effectifregulier = new Double(effectifreguliers).doubleValue();
		
		if(effectifreguliers>0 && nbreNotes>=0)	pourcentage = (nbreNote/effectifregulier)*100;
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		try {
			pourcentage =df.parse(df.format(pourcentage)).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pourcentage;
		
	}

	public List<SousRapport1ConseilBean> getListofSousRapport1Conseil(Classes classe, 
			Sequences sequence){
		List<SousRapport1ConseilBean> listofSousRapport1Sequence = 
				new ArrayList<SousRapport1ConseilBean>();
		
		List<Cours> listofCoursEvalueDansSeq = this.getListOfCoursEvalueDansSequence(classe, sequence);
		
		for(Cours cours : listofCoursEvalueDansSeq){
			SousRapport1ConseilBean sr1cb = new  SousRapport1ConseilBean();
			String nomdisc = " "+cours.getCodeCours()+"("+cours.getMatiere().getIntituleMatiere()+")";
			sr1cb.setNom_discipline(nomdisc);
			String nomprof = " "+cours.getProffesseur().getNomsPers()+" "+cours.getProffesseur().getPrenomsPers();
			nomprof = nomprof.toUpperCase();
			sr1cb.setNom_professeur(nomprof);
			
			listofSousRapport1Sequence.add(sr1cb);
		}
		
		return listofSousRapport1Sequence;
	}
	
	public List<SousRapport2ConseilBean> getListofSousRapport2Conseil(Classes classe, 
			Sequences sequence){
		List<SousRapport2ConseilBean> listofSousRapport2Sequence = new ArrayList<SousRapport2ConseilBean>();
		
		List<Cours> listofCoursEvalueDansSeq = this.getListOfCoursEvalueDansSequence(classe, sequence);
		
		for(Cours cours : listofCoursEvalueDansSeq){
			SousRapport2ConseilBean sr2cb = new  SousRapport2ConseilBean();
			int nbre_moy = this.getNbreNoteDansCourspourSeq(classe, cours, sequence);
			int nbre_sousmoy =  this.getNbreSousNoteDansCourspourSeq(classe, cours, sequence);
			int nbre_present = nbre_moy+nbre_sousmoy;
			double pourcentage = this.getTauxReussiteCoursSeq(classe, cours, sequence);
			String nom_matiere = "  "+cours.getCodeCours();
			
			sr2cb.setNom_matiere(nom_matiere);
			sr2cb.setNbrepresent_mat(nbre_present);
			sr2cb.setNbre_moy(nbre_moy);
			sr2cb.setPourcentage(pourcentage);
			 
			listofSousRapport2Sequence.add(sr2cb);
		}
		
		return listofSousRapport2Sequence;
	}
	
	public List<SousRapport3ConseilBean> getListofSousRapport3Conseil(Classes classe, 
			Sequences sequence){
		List<SousRapport3ConseilBean> listofSousRapport3Sequence = new ArrayList<SousRapport3ConseilBean>();
		
		List<Cours> listofCoursEvalueDansSeq = this.getListOfCoursEvalueDansSequence(classe, sequence);
		
		for(Cours cours : listofCoursEvalueDansSeq){
			SousRapport3ConseilBean sr3cb = new  SousRapport3ConseilBean();
			int nbre_moy = this.getNbreNoteDansCourspourSeq(classe, cours, sequence);
			int nbre_sousmoy =  this.getNbreSousNoteDansCourspourSeq(classe, cours, sequence);
			int nbre_present = nbre_moy+nbre_sousmoy;
			double pourcentage = this.getTauxReussiteCoursSeq(classe, cours, sequence);
			String nom_matiere = "  "+cours.getCodeCours();
			
			sr3cb.setNom_matiere(nom_matiere);
			sr3cb.setNbrepresent_mat(nbre_present);
			sr3cb.setNbre_moy(nbre_moy);
			sr3cb.setPourcentage(pourcentage);
			 
			listofSousRapport3Sequence.add(sr3cb);
		}
		
		return listofSousRapport3Sequence;
	}
	
	public List<SousRapport1ConseilBean> getListofSousRapport1Conseil(Classes classe, 
			Trimestres trimestre){
		List<SousRapport1ConseilBean> listofSousRapport1Trimestre = 
				new ArrayList<SousRapport1ConseilBean>();
		
		List<Cours> listofCoursEvalueDansTrim = this.getListOfCoursEvalueDansTrimestre(classe, trimestre);
		
		for(Cours cours : listofCoursEvalueDansTrim){
			SousRapport1ConseilBean sr1cb = new  SousRapport1ConseilBean();
			String nomdisc = " "+cours.getCodeCours()+"("+cours.getMatiere().getIntituleMatiere()+")";
			sr1cb.setNom_discipline(nomdisc);
			String nomprof = " "+cours.getProffesseur().getNomsPers()+" "+cours.getProffesseur().getPrenomsPers();
			nomprof = nomprof.toUpperCase();
			sr1cb.setNom_professeur(nomprof);
			
			listofSousRapport1Trimestre.add(sr1cb);
		}
		
		return listofSousRapport1Trimestre;
	}
	
	public List<SousRapport2ConseilBean> getListofSousRapport2Conseil(Classes classe, 
			Trimestres trimestre){
		List<SousRapport2ConseilBean> listofSousRapport2Sequence = new ArrayList<SousRapport2ConseilBean>();
		
		List<Cours> listofCoursEvalueDansSeq = this.getListOfCoursEvalueDansTrimestre(classe, trimestre);
		
		for(Cours cours : listofCoursEvalueDansSeq){
			SousRapport2ConseilBean sr2cb = new  SousRapport2ConseilBean();
			int nbre_moy = this.getNbreNoteDansCourspourTrim(classe, cours, trimestre);
			int nbre_sousmoy =  this.getNbreSousNoteDansCourspourTrim(classe, cours, trimestre);
			int nbre_present = nbre_moy+nbre_sousmoy;
			double pourcentage = this.getTauxReussiteCoursTrim(classe, cours, trimestre);
			String nom_matiere = "  "+cours.getCodeCours();
			
			sr2cb.setNom_matiere(nom_matiere);
			sr2cb.setNbrepresent_mat(nbre_present);
			sr2cb.setNbre_moy(nbre_moy);
			sr2cb.setPourcentage(pourcentage);
			 
			listofSousRapport2Sequence.add(sr2cb);
		}
		
		return listofSousRapport2Sequence;
	}
	
	public List<SousRapport3ConseilBean> getListofSousRapport3Conseil(Classes classe, 
			Trimestres trimestre){
		List<SousRapport3ConseilBean> listofSousRapport3Sequence = new ArrayList<SousRapport3ConseilBean>();
		
		List<Cours> listofCoursEvalueDansSeq = this.getListOfCoursEvalueDansTrimestre(classe, trimestre);
		
		for(Cours cours : listofCoursEvalueDansSeq){
			SousRapport3ConseilBean sr3cb = new  SousRapport3ConseilBean();
			int nbre_moy = this.getNbreNoteDansCourspourTrim(classe, cours, trimestre);
			int nbre_sousmoy =  this.getNbreSousNoteDansCourspourTrim(classe, cours, trimestre);
			int nbre_present = nbre_moy+nbre_sousmoy;
			double pourcentage = this.getTauxReussiteCoursTrim(classe, cours, trimestre);
			String nom_matiere = "  "+cours.getCodeCours();
			
			sr3cb.setNom_matiere(nom_matiere);
			sr3cb.setNbrepresent_mat(nbre_present);
			sr3cb.setNbre_moy(nbre_moy);
			sr3cb.setPourcentage(pourcentage);
			 
			listofSousRapport3Sequence.add(sr3cb);
		}
		
		return listofSousRapport3Sequence;
	}
	
	public List<SousRapport1ConseilBean> getListofSousRapport1Conseil(Classes classe, 
			Annee annee){
		List<SousRapport1ConseilBean> listofSousRapport1Annee = 
				new ArrayList<SousRapport1ConseilBean>();
		
		List<Cours> listofCoursEvalueDansAn = this.getListOfCoursEvalueDansAnnee(classe, annee);
		
		for(Cours cours : listofCoursEvalueDansAn){
			SousRapport1ConseilBean sr1cb = new  SousRapport1ConseilBean();
			String nomdisc = " "+cours.getCodeCours()+"("+cours.getMatiere().getIntituleMatiere()+")";
			sr1cb.setNom_discipline(nomdisc);
			String nomprof = " "+cours.getProffesseur().getNomsPers()+" "+cours.getProffesseur().getPrenomsPers();
			nomprof = nomprof.toUpperCase();
			sr1cb.setNom_professeur(nomprof);
			
			listofSousRapport1Annee.add(sr1cb);
		}
		
		return listofSousRapport1Annee;
	}
	
	public List<SousRapport2ConseilBean> getListofSousRapport2Conseil(Classes classe, 
			Annee annee){
		List<SousRapport2ConseilBean> listofSousRapport2Sequence = new ArrayList<SousRapport2ConseilBean>();
		
		List<Cours> listofCoursEvalueDansAn = this.getListOfCoursEvalueDansAnnee(classe, annee);
		
		for(Cours cours : listofCoursEvalueDansAn){
			SousRapport2ConseilBean sr2cb = new  SousRapport2ConseilBean();
			int nbre_moy = this.getNbreNoteDansCourspourAn(classe, cours, annee);
			int nbre_sousmoy =  this.getNbreSousNoteDansCourspourAn(classe, cours, annee);
			int nbre_present = nbre_moy+nbre_sousmoy;
			double pourcentage = this.getTauxReussiteCoursAn(classe, cours, annee);
			String nom_matiere = "  "+cours.getCodeCours();
			
			sr2cb.setNom_matiere(nom_matiere);
			sr2cb.setNbrepresent_mat(nbre_present);
			sr2cb.setNbre_moy(nbre_moy);
			sr2cb.setPourcentage(pourcentage);
			 
			listofSousRapport2Sequence.add(sr2cb);
		}
		
		return listofSousRapport2Sequence;
	}
	
	public List<SousRapport3ConseilBean> getListofSousRapport3Conseil(Classes classe, 
			Annee annee){
		List<SousRapport3ConseilBean> listofSousRapport3Sequence = new ArrayList<SousRapport3ConseilBean>();
		
		List<Cours> listofCoursEvalueDansAn = this.getListOfCoursEvalueDansAnnee(classe, annee);
		
		for(Cours cours : listofCoursEvalueDansAn){
			SousRapport3ConseilBean sr3cb = new  SousRapport3ConseilBean();
			int nbre_moy = this.getNbreNoteDansCourspourAn(classe, cours, annee);
			int nbre_sousmoy =  this.getNbreSousNoteDansCourspourAn(classe, cours, annee);
			int nbre_present = nbre_moy+nbre_sousmoy;
			double pourcentage = this.getTauxReussiteCoursAn(classe, cours, annee);
			String nom_matiere = "  "+cours.getCodeCours();
			
			sr3cb.setNom_matiere(nom_matiere);
			sr3cb.setNbrepresent_mat(nbre_present);
			sr3cb.setNbre_moy(nbre_moy);
			sr3cb.setPourcentage(pourcentage);
			 
			listofSousRapport3Sequence.add(sr3cb);
		}
		
		return listofSousRapport3Sequence;
	}
	

}
