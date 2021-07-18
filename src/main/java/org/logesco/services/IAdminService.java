/**
 * 
 */
package org.logesco.services;

import java.util.Date;
import java.util.List;

import org.logesco.entities.*;
import org.springframework.data.domain.Page;

/**
 * @author cedrickiadjeu
 *
 */
public interface IAdminService {
	/**************
	 * Liste des methodes qui correspondent aux besoins fonctionnels de l'administrateur
	 * vis à vis de l'application
	 *******************/
	
	/***
	 * Recuperer la liste de tous les users enregistrer dans la base de données sauf l'administrateur. 
	 * Donc il faut recuperer tous les UtilisateursRole puis extraire celui qui le rôle admin.
	 * Ensuite il faut éliminer les doublons de la liste car un user peut avoir plusieurs role donc 
	 * plusieurs enregistrement userRole
	 */
	public List<Utilisateurs> findAllUsers();
	
	/**
	 * Recuperer un utilisateur dans la base de données
	 */
	public Utilisateurs getUsers(String username);
	
	/**
	 * Recuperer un administrateur dans la base de données
	 */
	public Administrateurs getUsersAdmin(String username);
	
	/**
	 * Modifier le mot de passe d'un utilisateur admin
	 * Retourne 1 si la modification a réussi
	 * 				0 si le passwordCourant n'est pas valide
	 * 				-1 si newPassord et newPasswordConfirm ne sont pas identique
	 * 				-2 si le username n'est pas valide
	 */
	public int updatePassword(String passwordCourant, String newPassword, 
			String newPasswordConfirm, String username);
	
	/**
	 * Reinitialise le mot de passe d'un utilisateur autre que l'admin en cas d'oubli par ce dernier
	 * Retourne 1 si la modification a réussi
	 * 					0 si newPassord et newPasswordConfirm ne sont pas identique
	 * 					-1 si le username n'est pas valide
	 */
	public int resetPassword( String newPassword, 
			String newPasswordConfirm, String username);
	
	/**
	 * Modifier les données de l'administrateur dans la base de donnée
	 * Retourne 1 si la modification a reussi
	 * 				0 sinon
	 */
	public int updateAdminUsers(String username, String emailAdmin, String numtel1Admin,
			String numtel2Admin);
	
	/**
	 * Modifier le nom d'utilisateur d'un utilisateur de l'application. Admin comme User
	 * Retourne 1 si la modification a reussi
	 * 				0 sinon
	 */
	public int updateUsername(String activeUsername, String newUsername);
	
	/**
	 * Recuperer l'établissement enregistré dans la base de données
	 * Retourne Etablissement si un établissement est enregistré
	 * 				NULL sinon
	 */
	public Etablissement getEtablissement();
	
	/**
	 * Enregistrer un établissement dans la base de donnée
	 * Retourne l'id de l'etablissement enregistré ou mis a jour
	 * On ne doit avoir qu'un seul etablissement donc en cas d'existence d'un 
	 * etablissement dans  la base de donnée celui ci est simplement mis à jour
	 */
	public Long saveEtablissement(Etablissement etab);
	
	/**
	 * Recherche le proviseur enregistrer dans la base de donnée et le retourne
	 * sinon retourne null
	 */
	public Proviseur getProviseur();
	
	/**
	 *  Recherche l'entite Proviseur associe à l'entite Proffesseurs
	 * Retourne null si elle n'existe pas
	 */
	public Proviseur getProviseur(Proffesseurs prof);
	
	/**
	 * Recherche l'entite Proffesseurs associe à l'entite Personnels
	 * Retourne null si elle n'existe pas
	 */
	public Proffesseurs getProffesseurs(Personnels personnel);
	
	/**
	 * Recherche l'entité Personnels associe à l'entite Utilisateurs
	 * Retourne null si elle n'existe pas
	 */
	public Personnels getPersonnels(Utilisateurs users);
	
	/**
	 * Recherche l'entité Utilisateurs à partir d'une cle de type Long
	 * Retourne null si elle n'existe pas
	 * Il y a deja un autre getUsers qui prend en paramètre un String username
	 */
	public Utilisateurs getUsers(Long idPers);
	
	/**
	 * Enregistrer un proviseur dans la base de donnée
	 * Retourne l'id du proviseur enregistré ou mis a jour
	 * On ne doit avoir qu'un seul proviseur donc en cas d'existence d'un proviseur dans 
	 * la base de donnée celui ci est simplement mis à jour
	 * 
	 *Cette méthode retourne
	 *					l'id du proviseur enregistrer si tout s'est bien passe
	 *					-1 si pour la mise a jour ou l'enregistrement la contrainte sur le numerocni est violé
	 *					-2 si pour la mise a jour ou l'enregistrement la contrainte sur le triplet noms prenoms datenaiss est violé
	 *					-3 si pour la mise a jour ou l'enregistrement la contrainte sur le username est violé
	 */
	public Long saveProviseur(Proviseur proviseur);
	
	/***
	 * Cette méthode retourne le personnel qui possède le numero de cni passe en parametre
	 * a partir du personnel retourner on peut maintenant regarder sa fonction et autre
	 * Elle retourne null si aucun personnel ne possède ce numerocni
	 */
	public Personnels findPersonnel(String numeroCni);
	
	/***
	 * Cette méthode retourne le personnel qui possède le triplet noms prenoms datenaiss
	 */
	public Personnels findPersonnel(String nomPers, String prenomsPers, Date datenaisspers);
	
	/**
	 * Retourne la liste de tous les cycles existant dans la base de donnée
	 */
	public List<Cycles> findAllCycles();
	
	/**
	 * Enregistre/update un cycle dans la base de données
	 * Retourne 1 si le cycle a été enregistré/modifié avec succes
	 * 				0 si la contrainte sera violé sur le numeroordre
	 * 				-1 si la contrainte sera violé sur le code
	 */
	public int saveCycles(Cycles cycles);
	
	/**
	 * Recherche un cycle dans la base de données à partir de son numero d'ordre
	 * Retourne le cycle trouve ou null
	 */
	public Cycles getCyclesByNumeroOrdreCycles(int numeroOrdre);
	
	/**
	 * Recherche un cycle dans la base de données à partir de son code
	 * Retourne le cycle trouve ou null
	 */
	public Cycles getCyclesByCodeCycles(String codeCycles);
	
	/**
	 * Recherche un cycle dans la base de données à partir de son id
	 * Retourne le cycle trouve ou null
	 */
	public Cycles getCyclesById(Long idCycles);
	
	/**
	 * Recherche la liste des niveaux contenus dans un cycle
	 * Retourne la liste des niveaux trouve ou null
	 */
	public List<Niveaux> findListNiveauxCycles(Long idCycles);
	
	/**
	 * Supprimer un cycle dans la base de données à partir de son id
	 * Retourne 1 si tout a été bien fait 
	 * 				0 si le cycles contient déjà des niveaux
	 */
	public int deleteCycles(Long idCycles);
	
	/**
	 * Retourne la liste de tous les niveaux enregistrés et classe par ordre des 
	 * numéro d'ordre de niveau
	 */
	public List<Niveaux> findAllNiveaux();
	
	/**
	 * Recherche un niveau dans la base de données à partir de son code
	 * Retourne le niveau trouve ou null
	 */
	public Niveaux getNiveauxByCodeNiveaux(String codeNiveaux);
	
	
	/**
	 * Enregistre/update un NIVEAU dans la base de données
	 * Retourne 1 si le Niveau a été enregistré/modifié avec succes
	 * 				0 si la contrainte sera violé sur le numeroordre
	 * 				-1 si la contrainte sera violé sur le code
	 */
	public int saveNiveaux(Niveaux niveau);
	
	/**
	 * Recherche la liste des classes contenues dans un niveau
	 * Retourne la liste des classes trouve ou null
	 */
	public List<Classes> findListClassesNiveaux(Long idNiveaux);
	
	/**
	 * Supprimer un niveau dans la base de données à partir de son id
	 * Retourne 1 si tout a été bien fait 
	 * 				0 si le niveau est déjà enregistré comme niveau sup d'un autre
	 * 				-1 si le niveau contient déjà des classes
	 */
	public int deleteNiveaux(Long idNiveaux);
	
	/**
	 * Retourne la liste de toutes les sections enregistrés et classe par ordre intitule
	 */
	public List<Sections> findAllSections();
	
	/**
	 * Enregistre/update une SECTION dans la base de données
	 * Retourne 1 si la Section a été enregistré/modifié avec succes
	 * 				0 si la contrainte sera violé sur le codeSection
	 */
	public int saveSections(Sections sections);
	
	/**
	 * Recherche la liste des classes contenues dans une section
	 * Retourne la liste des classes trouve ou null
	 */
	public List<Classes> findListClassesSections(Long idSections);
	
	/**
	 * Supprimer une section dans la base de données à partir de son id
	 * Retourne 1 si tout a été bien fait et
	 * 				0 si la section contient déjà des classes
	 */
	public int deleteSections(Long idSections);
	
	/**
	 * Retourne la liste de toutes les années scolaire enregistrés et classe par ordre intitule
	 */
	public List<Annee> findAllAnnee();
	
	/**
	 * Enregistre/update une 	ANNEE dans la base de données
	 * Retourne 1 si la Annee a été enregistré/modifié avec succes
	 * 				0 si la contrainte sera violé sur l'intitule
	 * 				-1 si la datedebut est ultérieure a la datefin
	 */
	public int saveAnnee(Annee annee);
	
	/***********************************************************************
	 * Etablir la liste de toutes les périodes de types année enregistrées dans la BD
	 ***********************************************************************/
	public List<Annee> listofAnnee();
	
	/**
	 * Recherche la liste des trimestres contenues dans une annee
	 * Retourne la liste des classes trouve ou null
	 */
	public List<Trimestres> findListTrimestresAnnee(Long idPeriodes);
	
	/**
	 * Supprimer une annee dans la base de données à partir de son id
	 * Retourne 1 si tout a été bien fait et 
	 * 				0 si l'année comporte des trimestres
	 */
	public int deleteAnnee(Long idPeriodes);
	
	/**
	 * Retourne la liste de tous les trimestres enregistrés et classe par ordre descendant
	 * des intitules de leurs années d'appartenance
	 */
	public List<Trimestres> findAllTrimestres();
	
	/**
	 * Enregistre/update un TRIMESTRE dans la base de données
	 * Retourne 1 si le Trimestres a été enregistré/modifié avec succes
	 * 				0 si l'année d'appartenance du trimestre contient déjà 3 trimestres
	 * 				-1 si un trimestre de même numéro existe déjà dans l'année d'appartenance
	 * 				-2 si numerotrim=1, datedebuttrim1<datedebutan ou datefintrim1>datefinan
	 * 				-3 si numerotrim=2 et trimestre1 n'existe pas dans l'année d'appartenance
	 * 				-4 si numerotrim=2, datedebuttrim2<=datefintrim1 ou datefintrim2>datefinan
	 * 				-5 si numerotrim=3 et trimestre2 n'existe pas dans l'année d'appartenance
	 * 				-6 si numerotrim=3, datedebuttrim3<=datefintrim2 ou datefintrim3>datefinan
	 * 				-7 si datedebuttrim>datefintrim
	 */
	public int saveTrimestres(Trimestres trimestre);
	
	/**
	 * Retourne l'année associe a un intitule
	 */
	public Annee getAnneeIntituleAnnee(String intituleAnnee);
	
	/**
	 * Retourne la liste des séquences contenues dans un trimestre
	 */
	public List<Sequences> findListSequencesTrim(Long idPeriodes);
	
	/**
	 * Supprimer un trimestre dans la base de données à partir de son id
	 * Retourne 1 si tout a été bien fait 
	 * 				0 si le trimestre contient déjà des séquences
	 * 				-1 si un trimestre de numéro supérieur au trimestre que l'on veut supprimer existe
	 * 						dans l'année
	 * 				-2 en cas d'erreur de suppression inconnue
	 */
	public int deleteTrimestres(Long idPeriodes);
	
	/**********************************************************************
	 * On doit faire la liste de toutes les séquences enregistrées classées par
	 * ordre descendant des intitules de leurs années d'appartenance puis dans l'année
	 * par ordre croissant des numéroTrim et par ordre croissant des numéroSeq
	 ***********************************************************************/
	public List<Sequences> findAllSequences();
	
	/****************************************************************************
	 * Enregistre/update une SEQUENCE dans la base de données
	 * Retourne 1 si le Trimestre a été enregistré/modifié avec succes
	 * 				0 si le trimestre choisi a déjà 2 sequences enregistrées
	 * 				-1 si on essaye d'enregistrer une séquence de même numéro dans le trimestre
	 * 				-2 si les dates de début et de fin de la séquence ne sont pas conforme 
	 * ie datedebutSeq>=datefinSeq
	 * 				-3 si le trimestre de numéro choisi n'existe pas encore dans l'année indiquée
	 * 				-4 si on essaye d'enregistrer une séquence de numéro 2 alors que la séquence de 
	 * numéro 1 n'existe pas encore dans le trimestre.
	 * 				-5 si ayant choisi d'enregistrer une première séquence(1ere sequence) 
	 * du trimestre de numero 1, 
	 * on a  (datedebutSeq1<datedebutTrim1 ou datefinSeq1>datefinTrim1)
	 * 				-6 si ayant choisi d'enregistrer une deuxieme séquence(2eme sequence) 
	 * du trimestre de numero 1, 
	 * on a (datedebutSeq2<datefinSeq1 ou datefinSeq2>datefinTrim1)
	 * 				-7 si ayant choisi d'enregistrer une première séquence(1ere sequence) 
	 * du trimestre de numero 2 (donc la séquence 3), 
	 * on a (datedebutSeq1<datedebutTrim2 ou datefinSeq1>datefinTrim2) avec Seq1=Seq3
	 * 				-8 si ayant choisi d'enregistrer une deuxieme séquence(2eme sequence) 
	 * du trimestre de numero 2 (donc la séquence 4), 
	 * on a (datedebutSeq2<datefinSeq1 ou datefinSeq2>datefinTrim2) avec seq1=Seq3 et 
	 * Seq2=Seq4
	 * 				-9 si ayant choisi d'enregistrer une première séquence(1ere sequence) 
	 * du trimestre de numero 3 (donc la séquence 5), 
	 * on a (datedebutSeq1<datedebutTrim3 ou datefinSeq1>datefinTrim3) avec Seq1=Seq5
	 * 				-10 si ayant choisi d'enregistrer une deuxieme séquence(2eme sequence) 
	 * du trimestre de numero 3 (donc la séquence 6), 
	 * on a (datedebutSeq2<datefinSeq1 ou datefinSeq2>datefinTrim3) avec seq1=Seq5 et 
	 * Seq2=Seq6
	 ****************************************************************************/
	public int saveSequences(Sequences sequence);
	
	/******************************************************
	 * Recherche le trimestre d'une année connaissant son numéro
	 * 	Retourne le trimestre trouve ou null s'il n'existe pas encore
	 ********************************************************/
	public Trimestres getTrimestreAnnee(Annee annee , int numeroTrim);
	
	/**
	 * Supprimer une séquence dans la base de données à partir de son id
	 * Retourne 1 si tout a été bien fait 
	 * 				0 si la séquence est active ie dans l'état 1
	 * 				-1 si la séquence contient des évaluations
	 * 				-2 si la séquence contient des rapport d'absences
	 * 				-3 si une séquence de numéro supérieur à celui de la séquence 
	 * que l'on veut supprimer existe dans le trimestre
	 * 				-4 en cas de toutes autres erreurs
	 */
	public int deleteSequences(Long idPeriodes);
	
	/**
	 * Retourne la liste des évaluations passé danss une séquence. 
	 * Elle retourne une liste vide si aucune évaluation n'est encore passé dans la période
	 * @param idPeriodes
	 * @return
	 */
	public List<Evaluations> findListEvalSeq(Long idPeriodes);
	
	/**
	 * Retourne la liste des Rapport d'absence enregistré dans une séquence. 
	 * Elle retourne une liste vide si aucun Rapport d'absence n'est encore passé dans la période
	 * @param idPeriodes
	 * @return
	 */
	public List<RapportDAbsence> findListRabsSeq(Long idPeriodes);
	
	/**
	 * Retourne la liste de toutes les spécialités page par page
	 * Elle retourne une liste null si aucune spécialité n'est enregistré en bd
	 */
	public Page<Specialites> findPageSpecialite(int numPage, int taillepage);
	
	/****
	 * Retourne la liste de toutes les specialites et null si aucune specialité n'existe
	 */
	public List<Specialites> findAllSpecialites();
	
	/****
	 * Retourne la specialité dont le code est envoye en parametre et null si cette specialite 
	 * n'existe pas
	 */
	public Specialites findSpecialites(String codeSpe);
	
	/****
	 * Retourne le cycle dont le code est envoye en parametre et null si ce cycle 
	 * n'existe pas
	 */
	public Cycles findCycles(String codeCycle);
	
	/****
	 * Retourne la specialité dont l'id est envoye en parametre et null si cette specialite 
	 * n'existe pas
	 */
	public Specialites findSpecialites(Long idSpecialite);
	
	/******
	 * Enregistre une specialite
	 * 	Retourne 	1 si tout s'est bien passe
	 * 					0 si on veut enregistrer une specialite avec un code existant
	 * 					-1 pour toutes autres erreur
	 */
	public int saveSpecialites(Specialites specialites);
	
	/******
	 * Update une specialite
	 * 	Retourne 	1 si tout s'est bien passe
	 * 					0 si on veut modifier une specialite avec un code existant
	 * 					-1 pour toutes autres erreur
	 */
	public int updateSpecialites(String codeSpeAModif, Specialites speModif);
	
	/******
	 * Update une cycle
	 * 	Retourne 	1 si tout s'est bien passe
	 * 					0 si on veut modifier un cycle avec un code existant
	 * 					-1 pour toutes autres erreur
	 */
	public int updateCycles(String codeCycleAModif, Cycles cycleModif);
	
	/**
	 * Retourne la liste des classes qui sont de la specialites dont l'id est passe en paramètre
	 * ou alors une liste vide si aucune classe n'est encore enregistré dans cette specialite
	 * @param idSpecialites
	 * @return
	 */
	public List<Classes> findListClassesSpecialites(Long idSpecialites);
	
	/**
	 * Supprimer une specialite dans la base de données à partir de son id
	 * Retourne 1 si tout a été bien fait 
	 * 				0 si la specialite contient des classes
	 * 				-1 en cas de toutes autres erreurs
	 */
	public int deleteSpecialites(Long idSpecialites);
	
	/************************************************************
	 * Retourne la liste de toutes les classes page par page
	 * Elle retourne une liste null si aucune classe n'est enregistré en bd
	 ************************************************************/
	public Page<Classes> findPageClasse(int numPage, int taillePage);
	
	/********************************************************************
	 * Retourne la classe dont l'id est envoye en parametre et null si cette classe 
	 * n'existe pas
	 ********************************************************************/
	public Classes findClasses(Long idClasse);
	
	/**********************************************************************************
	 * Retourne la classe dont le code et le numéro puis la specialité sont envoyées en parametre 
	 * et null si cette classe n'existe pas
	 **********************************************************************************/
	public Classes findClasses(String codeClasses, String numeroClasses, Specialites specialite);
	
	/**********************************************************************************************
	 * Méthode qui retourne la liste des cours d'une classe specifié enregistrés  page par page
	 ***********************************************************************************************/
	public Page<Cours> findAllCoursClasse(int numPage, int taillePage, Long idClasses);
	
	/**********************************************************************************************
	 * Méthode qui retourne la liste des cours qu'un prof enseigne dans une classe specifié  page par page.
	 * l'identifiant de la classe et du prof sont passés en paramètre
	 ***********************************************************************************************/
	public Page<Cours> findAllCoursProfClasse(int numPage, int taillePage, Long idClasses, Long idProf);
	
	/*************************************************************
	 * Methode qui retourne la liste de toutes les matières enregistrées
	 * classe par ordre alphabétique des intitulés des matières
	 * Elle retourne une liste vide si aucune matière n'est enregistrée
	 */
	public List<Matieres> findAllMatieres();
	
	/************************************************************
	 * Retourne la liste de toutes les classes ENRGISTREES
	 * Elle retourne une liste null si aucune classe n'est enregistré en bd
	 ************************************************************/
	public List<Classes> findAllClasse();
	
	/***********************************************************
	 * Methode qui retourne la liste de tous les proviseurs enregistrés 
	 * meme si on sait qu'il ne peut en avoir qu'un seul
	 * Elle retourne une liste vide si aucun n'existe
	 */
	public List<Proviseur> findAllProviseur();
	
	/********************************************************
	 * Methode qui retourne la liste de tous les censeurs enregistrés 
	 * meme si on sait qu'il ne peut en avoir qu'un seul
	 * Elle retourne une liste vide si aucun n'existe
	 */
	public List<Censeurs> findAllCenseurs();
	
	/********************************************************
	 * Methode qui retourne la liste de tous les SG enregistrés 
	 * meme si on sait qu'il ne peut en avoir qu'un seul
	 * Elle retourne une liste vide si aucun n'existe
	 */
	public List<SG> findAllSG();
	
	/********************************************************
	 * Methode qui retourne la liste de tous les Intendant enregistrés 
	 * meme si on sait qu'il ne peut en avoir qu'un seul
	 * Elle retourne une liste vide si aucun n'existe
	 */
	public List<Intendant> findAllIntendant();
	
	/***********************************************************
	 * Methode qui retourne la liste de tous les Proffesseurs enregistrés 
	 * meme si on sait qu'il ne peut en avoir qu'un seul
	 * Elle retourne une liste vide si aucun n'existe
	 */
	public List<Proffesseurs> findAllProffesseurs();
	
	/*********************
	 * Retourne la liste de tous les enseignants page par page classe par ordre alphabetique des noms et prenoms
	 * et par ordre descendant des datenaisse et par ordre croissant des numero dans la fonction
	 * 		retourne null si aucun n'existe
	 */
	public Page<Enseignants> findAllEnseignants(int numPage, int taillePage);
	
	/************************
	 * Retourne la liste de tous les enseignants enregistrés et null si aucun n'est encore enregistrés dans la base de donnée
	 * @return
	 */
	public List<Enseignants> findAllEnseignants();
	
	/*******************************************************************
	 * Methode qui retourne le cours dont l'identifiant est passe en parametre
	 */
	public Cours findCours(Long idCours);
	
	/****
	 * Retourne la section dont le code est envoyé en parametre 
	 * et null si cette section n'existe pas
	 */
	public Sections findSections(String codeSection);
	
	/**
	 * Recherche une section dans la base de données à partir de son id
	 * Retourne la section trouve ou null
	 */
	public Sections getSectionsById(Long idSections);
	
	/******
	 * Update une section
	 * 	Retourne 	1 si tout s'est bien passe
	 * 					0 si on veut modifier une section avec un code existant 
	 * 					-1 pour toutes autres erreur
	 */
	public int updateSections(String codeSectionAModif, Sections sectionModif);
	
	/**
	 * Recherche un niveau dans la base de données à partir de son id
	 * Retourne le niveau trouve ou null
	 */
	public Niveaux getNiveauById(Long idNiveaux);
	
	/****
	 * Retourne le Niveau dont le numero d'ordre est envoyé en parametre 
	 * et null si ce niveau n'existe pas
	 */
	public Niveaux findNiveau(int numeroOrdreNiveaux);
	
	/****
	 * Retourne le Niveau dont le code est envoyé en parametre 
	 * et null si ce niveau n'existe pas
	 */
	public Niveaux findNiveau(String codeNiveaux);
	
	/******
	 * Update un niveau
	 * 	Retourne 	1 si tout s'est bien passe
	 * 					0 si on veut modifier un niveau avec un code existant 
	 * 					-1 pour toutes autres erreur
	 */
	public int updateNiveaux(String codeNiveauxAModif, Niveaux niveauModif);
	
	/*****************************************************************************
	 * Enregistre une classe
	 * 	Retourne 	1 si tout s'est bien passe
	 * 					0 si le quintuplet codeClass, numeroClass, numeroNiveau, 
	 * codeSection, codeSpecialite
	 * 					-2 pour toutes autres erreur
	 ****************************************************************************/
	public int saveClasses(Classes classes);
	
	/*****************************************************************************
	 *Update une classe
	 * 	Retourne 	1 si tout s'est bien passe
	 * 					0 si on ne trouve pas la classe à modifier
	 * 					-1  pour toutes autres erreur
	 ****************************************************************************/
	public int updateClasses(String codeClasseAModif, String numeroClasseAModif, 
			Long idSpecialite, Classes classes);
	
	/**
	 * Supprimer une classe dans la base de données à partir de son id
	 * Retourne 1 si tout a été bien fait 
	 * 				0 si la classe contient des élèves
	 * 				-1 si la classe contient des cours ie la liste des cours qui passe dans la classe n'est
	 * pas vide
	 * 				-2 en cas de toutes autres erreurs
	 */
	public int deleteClasses(Long idClasses);
	
	/***
	 * Retourne la liste des élèves qui sont dans une classe
	 * ou une liste vide si la classe en question ne contient pas d'élève
	 */
	public List<Eleves> findListElevesClasses(Long idClasses);
	
	/***
	 * Retourne la liste des cours qui sont enseignés dans une classe
	 * ou une liste vide si aucun cours n'est encore enseigné dans la classe en question
	 */
	public List<Cours> findListCoursClasses(Long idClasses);
	
	/**
	 * Retourne la liste de tous les roles page par page
	 * Elle retourne une liste null si aucun role n'est enregistré en bd
	 */
	public Page<Roles> findPageRole(int numPage, int taillePage);
	
	/***
	 * Retourne la liste de tous les UsersRoles enregistrées dans la bd
	 * et null si aucun users n'a encore été associé a un role
	 */
	public List<UtilisateursRoles> findAllUsersRoles();
	
	/****
	 * Retourne le role donc la cle est passe en paramaètre ou null si le rôle n'existe pas
	 * n'existe pas
	 */
	public Roles findRoles(String role);
	
	/*****************************************************************************
	 * Enregistre un roles
	 * 	Retourne 	1 si tout s'est bien passe
	 * 					0 si un role de meme nom existe déjà
	 * 					-1 pour toutes autres erreur
	 ****************************************************************************/
	public int saveRoles(Roles role);
	
	/*****************************************************************************
	 *Update un role
	 * 	Retourne 	1 si tout s'est bien passe
	 * 					0 si on ne trouve pas le role à modifier
	 * 					-1  pour toutes autres erreur
	 ****************************************************************************/
	public int updateRoles(String titreroleAModif,  Roles role);
	
	/*******************************************************************************************
	 * Methode qui retourne la liste de toutes les matières enregistrés dans la base de données page par page
	 * Elle retourne une liste vide au cas ou aucune matière n'est encore enregistré
	 */
	public Page<Matieres> findAllMatieres(int numPage, int taillePage); 
	
	/*******************************************************************
	 * Methode qui retourne la matiere dont l'identifiant est passe en parametre
	 */
	public Matieres findMatieres(Long idMatiere);
	
	/******************************************************************************
	 * Methode qui enregistre une nouvelle matiere ou met à jour la matiere deja existante
	 * Elle retourne 
	 * 	2 lorsque la matière est nouvelle et a été enregistré avec succès
	 * 	1 lorsque la matière n'est pas nouvelle et par conséquent a été mis à jour avec succès
	 * 	0 lorsque le code qu'on veut donner à la matière violera la contrainte d'unicite du code 
	 * 		de la matiere
	 ******************************************************************************/
	public int updateMatiere(Matieres matiere);
	
	/**************************************************************************
	 * Methode qui supprime une matière(département) de la base de donnée
	 * Elle retourne 
	 * 	1 lorsque la matière est supprimée avec succès
	 * 	0 lorsque la matière contient déjà des cours 
	 * 	-1 si la matière indiquée n'existe pas dans la base de donnée
	 */
	public int deleteMatiere(Long idMatiere);
	
	/******************************************************************************
	 * Methode qui enregistre un nouveau cours ou met à jour le cours deja existant
	 * Elle retourne 
	 * 	2 lorsque le cours est nouveau et a été enregistré avec succès
	 * 	1 lorsque le cours n'est pas nouveau et par conséquent a été mis à jour avec succès
	 * 	-1 lorsque la classe, la matiere ou le prof associe au cours n'existe pas encore
	 ******************************************************************************/
	public int updateCours(Cours cours, Long idMatiereAssocie, Long idProfAssocie, Long idClasseAssocie);
	
	/**************************************************************************
	 * Methode qui supprime un cours de la base de donnée
	 * Elle retourne 
	 * 	1 lorsque le cours est supprimé avec succès
	 * 	0 lorsque le cours contient déjà des évaluations 
	 * 	-1 si le cours n'existe pas dans la base de donnée
	 */
	public int deleteCours(Long idCours);
	
	/****
	 * Retirer un role a un utilisateur
	 * retourne 1 si tout s'est bien passé et 0 si l'user n'a meme pas ce role et -1 si probleme
	 * @param idUser
	 * @param role
	 * @return
	 */
	public int retirerRoleUser(Long idUser, String role);
	
	/****
	 * Ajouter un role a un utilisateur
	 * retourne 1 si tout s'est bien passé et 0 sinon
	 * @param idUser
	 * @param role
	 * @return
	 */
	public int ajouterRoleUser(Long idUser, String role);
	
}
