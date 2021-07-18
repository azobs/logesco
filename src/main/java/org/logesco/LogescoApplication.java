package org.logesco;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.logesco.dao.EtablissementRepository;
import org.logesco.dao.MatriculeRepository;
import org.logesco.dao.RolesRepository;
import org.logesco.dao.UtilisateursRepository;
import org.logesco.dao.UtilisateursRolesRepository;
import org.logesco.entities.Administrateurs;
import org.logesco.entities.Etablissement;
import org.logesco.entities.Matricule;
import org.logesco.entities.Roles;
import org.logesco.entities.Utilisateurs;
import org.logesco.entities.UtilisateursRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/*import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;*/

//import org.logesco.dao.*;
//import org.logesco.entities.*;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@SpringBootApplication
public class LogescoApplication implements CommandLineRunner{
	
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private UtilisateursRepository usersRepository;
	/*
	@Autowired
	private ProffesseursRepository profRepository;
	
	@Autowired
	private PersonnelsRepository persRepository;
	
	@Autowired
	private ProviseursRepository provRepository;
	
	@Autowired
	private CenseursRepository censeurRepository;
	*/
	@Autowired
	private UtilisateursRolesRepository usersRoleRepository;
	
	@Autowired
	private EtablissementRepository etabRepository;
	
	@Autowired
	private MatriculeRepository matriculeRepository;
	
	/*
	@Autowired
	private ElevesRepository elevesRepository;
	
	@Autowired
	private CompteInscriptionRepository compteinscriptionRepository;*/
	
	@Value("${dir.racine}")
	private String racineDir;
	
	@Value("${dir.racineEmblemes}")
	private String racineEmblemesDir;
	
	@Value("${dir.racineImage}")
	private String racineImageDir;
	
	@Value("${dir.documents}")
	private String documentsDir;
	
	@Value("${dir.documents.modeles}")
	private String modelesDir;
	
	@Value("${dir.emblemes.banniere}")
	private String banniereDir;
	
	@Value("${dir.emblemes.logo}")
	private String logoDir;
	
	@Value("${dir.images.personnels}")
	private String photoPersonnelsDir;
	
	@Value("${dir.images.eleves}")
	private String photoElevesDir;
	
	@Value("${dir.logs}")
	private String logsDir;

	//public static Logger log = Logger.getLogger(LogescoApplication.class);
	//public static Logger log = LogManager.getLogger(LogescoApplication.class);
	Logger logger = LoggerFactory.getLogger(LogescoApplication.class);
	
	public static DateFormat formatdateop=new SimpleDateFormat("yyyy-MM-dd H:m:s");
	public static Date dateJour=new Date();
	
	public static void main(String[] args) {
		SpringApplication.run(LogescoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		

		/*logger.trace("This is a TRACE message.");
	    logger.debug("This is a DEBUG message.");
	    logger.info("This is an INFO message.");
	    logger.warn("This is a WARN message.");
	    logger.error("You guessed it, an ERROR message.");*/
		
		
		
		
		
		/*System.out.println(formatdateop.format(dateJour)+
				"****TRACE**** DEMARRAGE DE L'APPLICATION  ****TRACE**** ");*/
	    logger.info("====================================================================");
	    logger.info("====================================================================");
	    logger.info("DEBUT DU DEMARRAGE DE L'APPLICATION.");
	    logger.info("====================================================================");
		/**********************************************************
		 * On va ici créer les différents répertoire lorsqu'il n'existe pas
		 * Ce sont les dossiers de sauvegarde des images et des documents
		 * important pour le logiciels
		 */
	try{
		/*System.out.println(formatdateop.format(dateJour)+
				"****TRACE**** LANCEMENT DE LA CREATION DE L'ARBORESECENCE SE  TROUVANT DANS "+racineDir+"****TRACE**** ");*/
		logger.debug("LANCEMENT DE LA CREATION DE L'ARBORESECENCE SE  TROUVANT DANS "+racineDir);
		File f = new File(racineDir);
		f.mkdirs();
		new File(racineEmblemesDir).mkdirs();
		new File(documentsDir).mkdirs();
		new File(modelesDir).mkdirs();
		new File(racineImageDir).mkdirs();
		
		new File(banniereDir).mkdirs();
		new File(logoDir).mkdirs();
		
		new File(photoPersonnelsDir).mkdirs();
		new File(photoElevesDir).mkdirs();
		new File(logsDir).mkdirs();
		//Ici il faut donc cache ce dossier racine pour qu'il ne soit pas visible par les premiers venus
		String[] cmd = {"attrib","+s","+h",f.getAbsolutePath()};
		Process process = Runtime.getRuntime().exec(cmd);
		process.getOutputStream().close();
		process.getErrorStream().close();
		
		/*log.log(Level.DEBUG, "============ FIN DE LA CREATION DE L'ARBORESECENCE SE "
				+ "TROUVANT DANS"+ "  "+racineDir+"=============");*/
		logger.debug("LANCEMENT DE LA CREATION DE L'ARBORESECENCE SE  TROUVANT DANS "+racineDir);
	}
	catch(Exception e){
		/*log.log(Level.WARN, "==== Erreur lors de la création des dossiers necessaires au bon "
				+ "fonctionnement du logiciel. Exception "+e.getMessage());*/
		logger.error("Erreur lors de la création des dossiers necessaires au bon fonctionnement du logiciel. Exception "+e.getMessage());
	}
		
		
		/****************************
		 * Il faut faire les configurations de base au démarage et de façon automatique
		 * 1) Enregistrer les roles de base (ADMIN, SUPERADMIN)
		 * 2) Enregistrer les utilisateurs de base qui sont tous des administrateurs (admin, superadmin)
		 * L'un s'occupe de la configuration du serveur(admin) et l'autre de realiser dans sa session ce 
		 * que  les utilisateurs n'ont pas pu réaliser (superadmin)
		 * 3) Enregistrer les UtilisateurRoles qui permettra effectivement de donner les roles crées aux 
		 * utilisateurs crées
		 * 4) Créer les différents répertoires permettant d'upload
		 * 		a) documentsUpload: pour stocker les documents uploader
		 * 		b) emblemes/baniere et emblemes/logo: pour stocker les emblemes de l'etablissement gérer
		 * 		c)	imagesUpload/eleves et imagesUpload/personnels pour stocker les photos de profil des 
		 * élèves et des membres du personnel.
		 * 5) Apres les répertoires l'administrateur doit créer  les différents rôles que les utilisateurs 
		 * peuvent avoir dans le système car on ne peut pas avoir des utilisateurs sans role. Le rôle admin
		 * et superadmin ont deja ete crée donc il reste proviseur, censeur, sg, intendant et enseignant. 
		 * 6) Il faut enregistrer le proviseur (chose qui se fait grâce à l'administrateur)
		 */
			
		try{
			/*log.log(Level.DEBUG, "==== Création des supers rôles de l'application (admin et super admin) "
					+ "s'il n'existe pas déjà ");*/
			logger.debug("Création des supers rôles de l'application (admin et super admin) s'il n'existe pas déjà.");
			//On enregistre les role de base de l'aapplication
			rolesRepository.save(new Roles("ADMIN"));
			rolesRepository.save(new Roles("SUPERADMIN"));
		}
		catch(Exception e){
			/*log.log(Level.WARN, "les rôles de base pour la configuration de l'application existe déjà.  "
					+ "Exception "+e.getMessage());*/
			logger.error("les rôles de base pour la configuration de l'application existe déjà. Exception "+e.getMessage());
		}
		Utilisateurs admin = null;
		Utilisateurs superadmin = null;
		try{
			/*log.log(Level.DEBUG, "Création des utilisateurs de base assumant les "
					+ "rôles de base (admin et superadmin) ");*/
			logger.debug("Création des utilisateurs de base (admin et superadmin)");
			
			Pbkdf2PasswordEncoder p=new Pbkdf2PasswordEncoder();
			admin=usersRepository.save(new Administrateurs("admin@gmail.com", 
					"678470262", "695093228", "admin", p.encode("12345"), true));
			usersRoleRepository.save(new UtilisateursRoles(admin,
					rolesRepository.findByRole("ADMIN")));	
			superadmin=usersRepository.save(new Administrateurs("superadmin@gmail.com", 
					"678470262", "695093228", "superadmin", p.encode("12345"), true));
			usersRoleRepository.save(new UtilisateursRoles(superadmin,
					rolesRepository.findByRole("SUPERADMIN")));
		}
		catch(Exception e){
			/*log.log(Level.WARN, "==== Les users de base existe deja en BD. "
					+ " Exception "+e.getMessage());*/
			logger.error("Les users de base existe deja en BD (admin et superadmin). Exception "+e.getMessage());
		}
		
		
		try{
			List<Matricule> listofMatricule = matriculeRepository.findAll();
			if(listofMatricule!=null){
				if(listofMatricule.size()>0){
					/*System.err.println("Le champ permettant de créer le numero d'ordre dans la génération des "
							+ " matricules existe deja");*/
					logger.debug("Le champ permettant de créer le numero d'ordre dans la génération des matricules existe deja ");
				}
				else if(listofMatricule.size() == 0){
					/*System.err.println("enregistrement du premier numero qui permettra la génération des matricules");*/
					logger.debug("Enregistrement du premier numero qui permettra la génération des matricules ");
					Matricule mat = new Matricule();
					mat.setValeur(0);
					matriculeRepository.save(mat);
				}
			}
			else{
				/*System.err.println("la liste des matricules est null donc problème");*/
				logger.error("la liste des matricules est null donc problème ");
			}
		}
		catch(Exception e){
			/*System.err.println("erreur d'enregistrement des numero pour l'enreg des matricule "+e.getMessage());*/
			logger.error("Erreur d'enregistrement des numero pour l'enreg des matricule "+e.getMessage());
		}
		
		
		
		/*****************************
		 * On enregistre l'établissement car en fait on doit avoir un établissement
		 * dans chaque base de donnée. Tous ceci fait parti des configurations de 
		 * base. la configuration est telle qu'on aura toujours un seul établissement 
		 * dans la base de donnée. Car le logiciel doit gérer un établissement à la fois
		 */
		try{
			List<Etablissement> listofEtab = etabRepository.findAll();
			if(listofEtab != null){
				if(listofEtab.size()>0){
					/*log.log(Level.DEBUG, "==== L'ETABLISSEMENT A GERER EST DEJA ENREGISTRE DANS LA BD. ");*/
					logger.debug("L'ETABLISSEMENT A GERER EST DEJA ENREGISTRE DANS LA BD ");
				}
				else if(listofEtab.size() == 0){
					/*
					 * On va enregistrer le tout premier etablissement de tel sorte qu'il soit juste
					 * modifier pendant la configuration
					 */
					/*log.log(Level.DEBUG, "==== PREPARATION DE L'ENREGISTREMENT DE L'ETABLISSEMENT "
							+ " PAR DEFAUT QUI SERA MODIFIER PAR LA SUITE PAR LE CHEF DE L'ETABLISSEMENT "
							+ " CLIENTE ====");*/
					logger.debug("PREPARATION DE L'ENREGISTREMENT DE L'ETABLISSEMENT PAR DEFAUT QUI SERA MODIFIER PAR LA SUITE PAR LE CHEF DE L'ETABLISSEMENT CLIENTE");
					
					Etablissement etab = new Etablissement();
					etab.setAliasEtab("Alias");
					etab.setAliasministeretuteleanglaisEtab("MINESEC");
					etab.setAliasministeretuteleEtab("MINESEC");
					etab.setAliasnomanglaisEtab("Noms en anglais");
					etab.setBanniereEtab(" ");
					etab.setBpEtab("bp");
					etab.setCodeMatriculeEtab("code");
					etab.setDeleguationdeptuteleanglaisEtab("DDES");
					etab.setDeleguationdeptuteleEtab("DDES");
					etab.setDeleguationregtuteleanglaisEtab("DRES");
					etab.setDeleguationregtuteleEtab("DRES");
					etab.setDeviseanglaisEtab("Peace-Work-Fatherland");
					etab.setDeviseEtab("Paix-Travail-Patrie");
					etab.setEmailEtab("etablissement@gmail.com");
					etab.setLogoEtab("");
					etab.setMatriculeEtab("IM");
					etab.setMinisteretuteleanglaisEtab("MINESEC");
					etab.setMinisteretuteleEtab("MINESEC");
					etab.setNomsanglaisEtab("Noms etab en anglais");
					etab.setNomsEtab("Noms en français");
					etab.setNumtel1Etab("600000000");
					etab.setNumtel2Etab("");
					etab.setVilleEtab("Ville ");
					
					etabRepository.save(etab);
					
				}
			}
			else{
				/*log.log(Level.WARN, "***** LA LISTE DES ETABLISSEMENTS EST NULL DONC MAUVAISE "
						+ " RECUPERATION ");*/
				logger.error("LA LISTE DES ETABLISSEMENTS EST NULL DONC MAUVAISE RECUPERATION");
			}
		}
		catch(Exception e){
			/*log.log(Level.WARN, "==== ERREUR LORS DE LA RECHERCHE DE L'ETABLISSEMENT ENREGISTRER OU "
					+ " LORS DE SON ENREGISTREMENT. Exception : "+e.getMessage());*/
			logger.error("ERREUR LORS DE LA RECHERCHE DE L'ETABLISSEMENT ENREGISTRER OU LORS DE SON ENREGISTREMENT. Exception : "+e.getMessage());
			
		}
		
		
		/*
		 * On récupère tous les élèves et on leur attribue un compte
		 */
		/*List<Eleves> listofEleve = elevesRepository.findAll();
		
		for(Eleves eleves : listofEleve){
			CompteInscription compte = new CompteInscription(new Double(0));
			compteinscriptionRepository.save(compte);
			eleves.setCompteInscription(compte);
			elevesRepository.save(eleves);
		}*/
		
		
		/*
		 * Enregistrement du niveau superieure à tous les autres niveaux
		 */
		/*Niveaux niveauAucun=new Niveaux();
		niveauAucun.setCodeNiveaux("aucun");
		niveauAucun.setNumeroOrdreNiveaux(8);
		niveauAucun.setCycle(null);
		niveauAucun.setNiveau(null);
		niveauxRepository.save(niveauAucun);*/
		
			/*Roles roles1=rolesRepository.save(new Roles("ADMIN"));
			Roles roles2=rolesRepository.save(new Roles("PROVISEUR"));
			Roles roles3=rolesRepository.save(new Roles("CENSEUR"));
			Roles roles4=rolesRepository.save(new Roles("SG"));
			Roles roles5=rolesRepository.save(new Roles("ENSEIGNANT"));
			Roles roles6=rolesRepository.save(new Roles("USER"));
			Roles roles7=rolesRepository.save(new Roles("SUPERADMIN"));
			
			
			 // Un administrateur de l'application
			 
			Pbkdf2PasswordEncoder p=new Pbkdf2PasswordEncoder();
			Utilisateurs admin1=usersRepository.save(new Administrateurs("admin1@gmail.com", 
					"678470262", "695093228", "admin", p.encode("12345"), true));
			try{
				usersRoleRepository.save(new UtilisateursRoles(admin1,roles1));
			}
			catch(Exception e){
				System.err.println(e.getMessage());
			}
			
			
			 // Un utilisateur pour l'application qui aura le role PROVISEUR
			 
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Proviseur pr=provRepository.save(new Proviseur("000008624", df.parse("1989-05-15"), 
					"DIPET2", "ckia@gmail.com", "PLET", "fokoue", "camerounaise", "azobs", "678470262", 
					"695093228", "tof", "cedric", "sadi", "masculin", "fonctionnaire", "douala", "proviseur", 
					p.encode("proviseur"), true, "informatique"));
			  
			try{  
				usersRoleRepository.save(new UtilisateursRoles(pr,roles2));
			}
			catch(Exception e){
				System.err.println(e.getMessage());
			}
			
			
			 // Un utilisateur pour l'application qui aura le role CENSEUR
			 
			Censeurs ce=censeurRepository.save(new Censeurs("000008625",  df.parse("1989-05-15"), 
					"DIPET2", "ckia@gmail.com", "PLET", "fokoue", "camerounaise", "azobs", "678470262",
					"695093228", "tof",  "cedrico", "sadi", "masculin",
					"fonctionnaire", "douala", "censeur1", p.encode("censeur1"), true,
					"informatique", 1));
			  
			try{  
				usersRoleRepository.save(new UtilisateursRoles(ce,roles3));
			}
			catch(Exception e){
				System.err.println(e.getMessage());
			}
			
			try{
				usersRoleRepository.save(new UtilisateursRoles(ce,roles5));
			}
			catch(Exception e){
				System.err.println(e.getMessage());
			}*/
			/*log.log(Level.DEBUG, "FIN DU DEMARRAGE ET CONFIGURATION DE BASE EFFECTUEE");*/
			
		
		logger.info("FIN DU DEMARRAGE DE L'APPLICATION.");
	    logger.info("====================================================================");
		
		}
	
}
