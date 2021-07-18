/**
 * 
 */
package org.logesco.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.logesco.dao.*;
import org.logesco.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cedrickiadjeu
 *
 */
@Service
@Transactional
public class AdminServiceImplementation implements IAdminService {

	@Autowired
	private UtilisateursRepository usersRepository;
	@Autowired
	private ProviseurRepository provRepository;
	@Autowired
	private ProffesseursRepository profRepository;
	@Autowired
	private PersonnelsRepository persRepository;
	@Autowired
	private AdministrateursRepository adminRepository;
	@Autowired
	private EtablissementRepository etabRepository;
	@Autowired
	private CyclesRepository cyclesRepository;
	@Autowired
	private NiveauxRepository niveauxRepository;
	@Autowired
	private SectionsRepository sectionsRepository;
	@Autowired
	private AnneeRepository anneeRepository;
	@Autowired
	private TrimestresRepository trimestreRepository;
	@Autowired
	private SequencesRepository sequenceRepository;
	@Autowired
	private SpecialitesRepository specialitesRepository;
	@Autowired
	private ClassesRepository classesRepository;
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private UtilisateursRolesRepository usersrolesRepository;
	@Autowired
	private MatieresRepository matieresRepository;
	@Autowired
	private CoursRepository	coursRepository;
	@Autowired
	private ProffesseursRepository proffRepository;
	@Autowired
	private CenseursRepository censeurRepository;
	@Autowired
	private SGRepository sgRepository;
	@Autowired
	private IntendantRepository intendantRepository;
	@Autowired
	private EnseignantsRepository enseignantRepository;
	
	
	@Override
	public Utilisateurs getUsers(String username) {
		/*log.log(Level.DEBUG, "**** LANCEMENT DE LA METHODE  getUsers() **** "+username);*/
		
		Utilisateurs users=usersRepository.getUtilisateursByUsername(username);
		if(users==null) return null;// new RuntimeException("utilisateur "+username+" introuvable")
		
		/*log.log(Level.DEBUG, "**** FIN DE L'EXECUTION DE LA METHODE getUsers() **** "+username);*/
		
		return users;
	}
	
	@Override
	public int updatePassword(String passwordCourant, String newPassword, 
			String newPasswordConfirm, String username) {
		
		if(!(newPassword.equals(newPasswordConfirm))) return -1;
		Pbkdf2PasswordEncoder p=new Pbkdf2PasswordEncoder();
		Utilisateurs users=this.getUsers(username);
		if(users!=null){
			if(p.matches(passwordCourant, users.getPassword())){
				users.setPassword(p.encode(newPassword));
				usersRepository.save(users);
				return 1;
			}
			else{
				return 0;
			}
		}
		else{
			return -2;
		}
		
	}
	
	@Override
	public int resetPassword( String newPassword, 
			String newPasswordConfirm, String username){
		if(!(newPassword.equals(newPasswordConfirm))) return 0;
		Pbkdf2PasswordEncoder p=new Pbkdf2PasswordEncoder();
		Utilisateurs users=this.getUsers(username);
		if(users!=null){
				users.setPassword(p.encode(newPassword));
				usersRepository.save(users);
				return 1;
		}
		else{
			return -1;
		}
	}

	@Override
	public Administrateurs getUsersAdmin(String username) {
		
		Utilisateurs users=this.getUsers(username);
		Administrateurs admin=adminRepository.getOne(users.getIdUsers());
		if(admin==null) throw new RuntimeException("Administrateur "+username+" introuvable");
		
		return admin;
	}

	@Override
	public int updateAdminUsers(String username, String emailAdmin, String numtel1Admin,
			String numtel2Admin){
		Administrateurs admin=getUsersAdmin(username);
		if(admin!=null){
			admin.setEmailAdmin(emailAdmin);
			admin.setNumtel1Admin(numtel1Admin);
			admin.setNumtel2Admin(numtel2Admin);
			adminRepository.save(admin);
			return 1;
		}
		return 0;
	}

	@Override
	public int updateUsername(String activeUsername, String newUsername) {
		/*
		 * Il faut déjà recuperer le user qui a pour username activeUsername
		 */
		Utilisateurs users=this.getUsers(activeUsername);
		
		/*
		 * On peut essayer de modifier le nom d'utilisateur et s'il ya exception alors
		 * c'est probablement une violation de contrainte car c'est une cle primaire
		 */
		try{
			users.setUsername(newUsername);
			usersRepository.save(users);
			return 1;
		}
		catch(Exception e){
			System.err.println("Erreur de modification de Username "+e.getMessage());
			
		}
		
		return 0;
	}

	@Override
	public Etablissement getEtablissement() {
		ArrayList<Etablissement> listEtab=(ArrayList<Etablissement>) etabRepository.findAll();
		if(!(listEtab.isEmpty())) return listEtab.get(0);
		return null;
	}

	@Override
	public Long saveEtablissement(Etablissement etab) {
		Etablissement etabExistant=this.getEtablissement();
		if(etabExistant!=null){
			etabExistant.setAliasEtab(etab.getAliasEtab());
			etabExistant.setBpEtab(etab.getBpEtab());
			etabExistant.setDeviseEtab(etab.getDeviseEtab());
			etabExistant.setEmailEtab(etab.getEmailEtab());
			etabExistant.setMatriculeEtab(etab.getMatriculeEtab());
			etabExistant.setMinisteretuteleEtab(etab.getMinisteretuteleEtab());
			etabExistant.setNomsEtab(etab.getNomsEtab());
			etabExistant.setNumtel1Etab(etab.getNumtel1Etab());
			etabExistant.setNumtel2Etab(etab.getNumtel2Etab());
			
			/***
			 * Debut des ajouts du 19-08-2018
			 */
			etabExistant.setAliasnomanglaisEtab(etab.getAliasnomanglaisEtab());
			etabExistant.setAliasministeretuteleEtab(etab.getAliasministeretuteleEtab());
			etabExistant.setAliasministeretuteleanglaisEtab(etab.getAliasministeretuteleanglaisEtab());
			etabExistant.setDeviseanglaisEtab(etab.getDeviseanglaisEtab());
			etabExistant.setMinisteretuteleanglaisEtab(etab.getMinisteretuteleanglaisEtab());
			etabExistant.setNomsanglaisEtab(etab.getNomsanglaisEtab());
			
			etabExistant.setDeleguationdeptuteleanglaisEtab(etab.getDeleguationdeptuteleanglaisEtab());
			//System.err.println("etab-------------------"+etabExistant.getDeleguationdeptuteleanglaisEtab());
			etabExistant.setDeleguationdeptuteleEtab(etab.getDeleguationdeptuteleEtab());
			etabExistant.setDeleguationregtuteleanglaisEtab(etab.getDeleguationregtuteleanglaisEtab());
			etabExistant.setDeleguationregtuteleEtab(etab.getDeleguationregtuteleEtab());
			etabExistant.setVilleEtab(etab.getVilleEtab());
			etabExistant.setCodeMatriculeEtab(etab.getCodeMatriculeEtab());
			/****
			 * Fin des ajouts du 19-08-2018
			 */
			
			if(etab.getBanniereEtab()!=null){
				etabExistant.setBanniereEtab(etab.getBanniereEtab());
			}
			
			if(etab.getLogoEtab()!=null){
				etabExistant.setLogoEtab(etab.getLogoEtab());
			}
			
			etabRepository.save(etabExistant);
			return etabExistant.getIdEtab();
		}
		
		/*log.log(Level.DEBUG, "**** ENREGISTREMENT DE L'ETABLISSEMENT "
				+ "DANS saveEtablissement**** ");*/
		
		Etablissement etabEnregistre=etabRepository.save(etab);
		
		/*log.log(Level.DEBUG, "**** FIN DE L'ENREGISTREMENT DE L'ETABLISSEMENT "
				+ "DANS saveEtablissement**** ");*/
		
		return etabEnregistre.getIdEtab();
	}
	
	@Override
	public Proviseur getProviseur(){
		ArrayList<Proviseur> listProv=(ArrayList<Proviseur>) provRepository.findAll();
		if(!(listProv.isEmpty())) return listProv.get(0);
		return null;
	}
	
	@Override
	public Personnels findPersonnel(String numcniPers){
		return persRepository.findByNumcniPers(numcniPers);
	}
	
	@Override
	public Personnels findPersonnel(String nomPers, String prenomsPers, Date datenaisspers){
		return persRepository.findByNomsPersAndPrenomsPersAndDatenaissPers(nomPers, prenomsPers, datenaisspers);
	}

	@Override
	public Proviseur getProviseur(Proffesseurs prof) {
		Proviseur proviseur=provRepository.getOne(prof.getIdUsers());
		if(proviseur==null) throw new RuntimeException("Proffesseurs "+prof.getIdUsers()+
				" introuvable donc il n'est peut être pas un Proviseur");
		return proviseur;
	}

	@Override
	public Proffesseurs getProffesseurs(Personnels personnel) {
		Proffesseurs prof=profRepository.getOne(personnel.getIdUsers());
		if(prof==null) throw new RuntimeException("Personnels "+personnel.getIdUsers()+
				" introuvable donc il n'est peut être pas un Proffesseurs");
		return prof;
	}

	@Override
	public Personnels getPersonnels(Utilisateurs users) {
		Personnels personnels=persRepository.getOne(users.getIdUsers());
		if(personnels==null) throw new RuntimeException("Utilisateurs "+users.getIdUsers()+
				" introuvable donc il n'est peut être pas un Personnels");
		return personnels;
	}

	@Override
	public Utilisateurs getUsers(Long idPers) {
		Utilisateurs users = usersRepository.getOne(idPers);
		if(users==null) throw new RuntimeException("Utilisateurs "+idPers+
				" introuvable");
		
		return users;
		
	}

	@Override
	public Long saveProviseur(Proviseur proviseur){
		
		/*
		 * En fait puisqu'on ne doit avoir qu'une seule instance de proviseur alors chaque fois on regarde si
		 * une entite proviseur existe car dans l'affirmatif c'est celui la qu'on met à jour
		 */
		Proviseur proviseurExistant=this.getProviseur();
		Pbkdf2PasswordEncoder p=new Pbkdf2PasswordEncoder();
		if(proviseurExistant!=null){
			/*
			 * Ici cela signifie qu'il s'agit d'une mise à jour. 
			 * dans ce cas, Si les nouvelles donnees devant etre unique sont différentes de celle qui existe alors il faut 
			 * se rassurer que les contraintes resteront inviolé apres la dite mise a jour
			 */
			System.err.println("Nous voici a l'execution   111");
			/*
			 * Pour le triplet noms, prenoms, datenaiss
			 */
			
			if(!(proviseur.getNomsPers().equals(proviseurExistant.getNomsPers())) ||
					!(proviseur.getPrenomsPers().equals(proviseurExistant.getPrenomsPers()))||
					(proviseur.getDatenaissPers().getTime()!=proviseurExistant.getDatenaissPers().getTime())){
				
				System.err.println("Nous voici a l'execution   222");
				System.err.println("proviseur.getNomsPers()="+proviseur.getNomsPers()+" proviseurExistant.getNomsPers()="+proviseurExistant.getNomsPers());
				System.err.println("proviseur.getPrenomsPers()="+proviseur.getPrenomsPers()+" proviseurExistant.getPrenomsPers()="+proviseurExistant.getPrenomsPers());
				System.err.println("proviseur.getDatenaissPers().getTime()="+proviseur.getDatenaissPers().getTime()+" proviseurExistant.getDatenaissPers().getTime()="+proviseurExistant.getDatenaissPers().getTime());
				/*
				 * On doit vérifier l'unicite du nouveau triplet
				 */
				Personnels persExistantAvectriplet=this.findPersonnel(proviseur.getNomsPers(), proviseur.getPrenomsPers(), 
						proviseur.getDatenaissPers());
				if(persExistantAvectriplet!=null) return new Long(-2);
			}
			System.err.println("Nous voici a l'execution   333");
			/*
			 * Pour le numero de cni
			 */
			if(!(proviseur.getNumcniPers().equals(proviseurExistant.getNumcniPers()))){
				System.err.println("Nous voici a l'execution   444");
				/*
				 * Il faut vérifier unicité du nouveau numero de cni
				 */
				Personnels persExistantAvecNumcni=this.findPersonnel(proviseur.getNumcniPers());
				if(persExistantAvecNumcni!=null) return new Long(-1);
			}
			System.err.println("Nous voici a l'execution   555");
			/*
			 * Pour le username
			 */
			if(!(proviseur.getUsername().equals(proviseurExistant.getUsername()))){
				Utilisateurs usersExistAvecUsername=this.getUsers(proviseur.getUsername());
				if(usersExistAvecUsername!=null) return new Long(-3);
			}
			System.err.println("Nous voici a l'execution   666");
			/*
			 * Ici a ce stade du code on est sur que tout a été vérifier donc on peut faire les mises à jour sans souci
			 */
			proviseurExistant.setDatenaissPers(proviseur.getDatenaissPers());
			proviseurExistant.setDiplomePers(proviseur.getDiplomePers());
			proviseurExistant.setEmailPers(proviseur.getEmailPers());
			proviseurExistant.setGradePers(proviseur.getGradePers());
			proviseurExistant.setLieunaissPers(proviseur.getLieunaissPers());
			proviseurExistant.setNationalitePers(proviseur.getNationalitePers());
			proviseurExistant.setNomsPers(proviseur.getNomsPers());
			proviseurExistant.setNumcniPers(proviseur.getNumcniPers());
			proviseurExistant.setNumtel1Pers(proviseur.getNumtel1Pers());
			proviseurExistant.setNumtel2Pers(proviseur.getNumtel2Pers());
			proviseurExistant.setPassword(p.encode(proviseur.getPassword()));
			if(proviseur.getPhotoPers()!=null){
				proviseurExistant.setPhotoPers(proviseur.getPhotoPers());
			}
			proviseurExistant.setPrenomsPers(proviseur.getPrenomsPers());
			proviseurExistant.setQuartierPers(proviseur.getQuartierPers());
			proviseurExistant.setSexePers(proviseur.getSexePers());
			proviseurExistant.setSpecialiteProf(proviseur.getSpecialiteProf());
			proviseurExistant.setStatutPers(proviseur.getStatutPers());
			proviseurExistant.setUsername(proviseur.getUsername());
			proviseurExistant.setVillePers(proviseur.getVillePers());
			
			/*
			 * On peut maintenant faire la mise à jour
			 */
			provRepository.save(proviseurExistant);
			System.err.println("Nous voici a l'execution   777  password== "+proviseur.getPassword());
			return  proviseurExistant.getIdUsers();
			
			
		}
		/*
		 * Ici ca veut dire l'entite proviseur n'existe pas encore donc il faut la créer 
		 * mais avant il faut faire toutes les vérifications
		 */
		System.err.println("Nous voici a l'execution   888");
		/*
		 * Est ce que quelqu'un existe en bd avec le meme numerocni?
		 */
		Personnels persExistantAvecNumcni=this.findPersonnel(proviseur.getNumcniPers());
		if(persExistantAvecNumcni!=null) return new Long(-1);
		System.err.println("Nous voici a l'execution   999");
		/*
		 * Est ce que quelqu'un existe en bd avec le meme triplet noms, prenoms datenaiss
		 */
		Personnels persExistantAvectriplet=this.findPersonnel(proviseur.getNomsPers(), proviseur.getPrenomsPers(), 
				proviseur.getDatenaissPers());
		if(persExistantAvectriplet!=null) return new Long(-2);
		System.err.println("Nous voici a l'execution   101010");
		/*
		 * Est ce qu'un utilisateur existe donc avec le même username
		 */
		Utilisateurs usersExistAvecUsername=this.getUsers(proviseur.getUsername());
		if(usersExistAvecUsername!=null) return new Long(-3);
		System.err.println("Nous voici a l'execution   111111");
		/*
		 * Ici on est donc sur que tout est bon et qu'on peut effectuer l'enregistrement
		 */
		
		/*
		 * Il faut toujours encoder le mot de passe car il n'a pas été code avant d'être envoyé
		 */
		proviseur.setPassword(p.encode(proviseur.getPassword()));
		System.err.println("le mot de passe enregistré est "+p.matches("misse",p.encode(proviseur.getPassword())));
		Proviseur proviseurEnregistre=provRepository.save(proviseur);
		System.err.println("Nous voici a l'execution   121212");
		/*
		 * Avant de retourner il faut d'abord enregistrer cet utilisateur comme ayant le role PROVISEUR
		 */
		
		Roles roles1=rolesRepository.findByRole("PROVISEUR");
		if(roles1==null) return new Long(-5);
		UtilisateursRoles usersroles=new UtilisateursRoles();
		usersroles.setUsers((Utilisateurs)proviseurEnregistre);
		usersroles.setRoleAssocie(roles1);
		System.err.println("Nous voici a l'execution   131313");
		
		usersrolesRepository.save(usersroles);
		
		return proviseurEnregistre.getIdUsers();
	}


	@Override
	public List<Cycles> findAllCycles() {
		List<Cycles> listCycles=null;
		/*listCycles=(ArrayList<Cycles>)
				cyclesRepository.findAllByOrderByNumeroOrdreCyclesAsc();*/
		listCycles=cyclesRepository.findAllByOrderByNumeroOrdreCyclesAsc();
		return listCycles;
	}

	@Override
	public int saveCycles(Cycles cycles) {
		
		Cycles cycle1=this.getCyclesByNumeroOrdreCycles(cycles.getNumeroOrdreCycles());
		if(cycle1!=null) return 0;
		Cycles cycle2=this.getCyclesByCodeCycles(cycles.getCodeCycles());
		if(cycle2!=null) return -1;
		
		cyclesRepository.save(cycles);
		
		return 1;
	}

	@Override
	public Cycles getCyclesByNumeroOrdreCycles(int numeroOrdre) {
		return cyclesRepository.findByNumeroOrdreCycles(numeroOrdre);
	}

	@Override
	public Cycles getCyclesByCodeCycles(String codeCycles) {
		return cyclesRepository.findByCodeCycles(codeCycles);
	}

	@Override
	public int deleteCycles(Long idCycles) {
		/*
		 * On ne peut pas supprimer un cycle qui contient des niveaux
		 * Donc il faut récuperer la liste des niveaux contenu dans le cycle et si elle n'est pas 
		 * vide alors refuser la suppression
		 */
		List<Niveaux> listNiveauxCycles=this.findListNiveauxCycles(idCycles);
		if(listNiveauxCycles.size()!=0){
			return 0;
		}
		
		cyclesRepository.delete(idCycles);
		return 1;
		
	}

	@Override
	public List<Niveaux> findAllNiveaux() {
		List<Niveaux> listofNiveaux=null;
		listofNiveaux=niveauxRepository.findAllByOrderByNumeroOrdreNiveauxAsc();
		return listofNiveaux;
	}

	@Override
	public Cycles getCyclesById(Long idCycles) {
		return cyclesRepository.getOne(idCycles);
	}
	
	@Override
	public Sections getSectionsById(Long idSections){
		return sectionsRepository.getOne(idSections);
	}
	
	@Override
	public Niveaux getNiveauById(Long idNiveaux){
		return niveauxRepository.getOne(idNiveaux);
	}

	@Override
	public List<Niveaux> findListNiveauxCycles(Long idCycles) {
		List<Niveaux> listNiveauxCycles=null;
		Cycles cycle=this.getCyclesById(idCycles);
		listNiveauxCycles=(List<Niveaux>)cycle.getListofNiveaux();
		System.err.println("liste des niveaux contenus dans le cycle "+listNiveauxCycles.toString());
		return listNiveauxCycles;
	}

	@Override
	public Niveaux getNiveauxByCodeNiveaux(String codeNiveaux) {
		
		return niveauxRepository.findByCodeNiveaux(codeNiveaux);
	}

	@Override
	public int saveNiveaux(Niveaux niveau) {
		Niveaux niveau1=niveauxRepository.findByCodeNiveaux(niveau.getCodeNiveaux());
		if(niveau1!=null) return 0;
		Niveaux niveau2=niveauxRepository.findByNumeroOrdreNiveaux(
				niveau.getNumeroOrdreNiveaux());
		if(niveau2!=null) return -1;
		
		niveauxRepository.save(niveau);
		
		return 1;
	}
	
	@Override
	public int updateNiveaux(String codeNiveauxAModif, Niveaux niveauModif){
		Niveaux niveauAModif=null;
		niveauAModif=this.findNiveau(codeNiveauxAModif);
		
		if(niveauAModif==null) return 0;
		
		niveauAModif.setCodeNiveaux(niveauModif.getCodeNiveaux());
		niveauAModif.setCodeNiveaux_en(niveauModif.getCodeNiveaux_en());
		niveauAModif.setCycle(niveauModif.getCycle());
		niveauAModif.setNiveau(niveauModif.getNiveau());
		niveauAModif.setNumeroOrdreNiveaux(niveauModif.getNumeroOrdreNiveaux());
		
		Niveaux niveauUpdate=niveauxRepository.save(niveauAModif);
		
		if(niveauUpdate!=null) return 1;
		return -1;
	}

	@Override
	public List<Classes> findListClassesNiveaux(Long idNiveaux) {
		List<Classes> listClassesNiveaux=null;
		Niveaux niveau=niveauxRepository.getOne(idNiveaux);
		listClassesNiveaux=(List<Classes>)niveau.getListofClasses();
		System.err.println("liste des classes contenues dans le niveau "+listClassesNiveaux.toString());
		return listClassesNiveaux;
	}

	@Override
	public int deleteNiveaux(Long idNiveaux) {
		
		/*
		 * On ne peut pas supprimer un niveau qui est déja enregistré comme niveau superieure
		 * d'un autre niveau.
		 */
		List<Niveaux> listofNiveaux=this.findAllNiveaux();
		for(Niveaux niveau:listofNiveaux){
			if(niveau.getNiveau()!=null){
				if(niveau.getNiveau().getIdNiveaux().longValue()==idNiveaux.longValue()){
					return 0;
				}
			}
		}
		
		/*
		 * On ne peut pas supprimer un niveau qui contient des classes
		 */
		List<Classes> listClassesNiveaux=this.findListClassesNiveaux(idNiveaux);
		if(listClassesNiveaux.size()!=0){
			return -1;
		}
		
		niveauxRepository.delete(idNiveaux);
		return 1;
	}

	@Override
	public List<Sections> findAllSections() {
		List<Sections> listofSections=null;
		listofSections=sectionsRepository.findAllByOrderByIntituleSectionsAsc();
		return listofSections;
	}

	@Override
	public int saveSections(Sections sections) {
		Sections section1=sectionsRepository.findByCodeSections(sections.getCodeSections());
		if(section1!=null) return 0;
		sectionsRepository.save(sections);
		return 1;
	}
	
	@Override
	public int updateSections(String codeSectionAModif, Sections sectionModif){
		Sections sectionAModif=null;
		
		sectionAModif=this.findSections(codeSectionAModif);
		
		if(sectionAModif==null) return 0;
		
		
		sectionAModif.setCodeSections(sectionModif.getCodeSections());
		sectionAModif.setCodeSections_en(sectionModif.getCodeSections_en());
		sectionAModif.setIntituleSections(sectionModif.getIntituleSections());
		sectionAModif.setIntituleSections_en(sectionModif.getIntituleSections_en());
		
		Sections sectionUpdate=sectionsRepository.save(sectionAModif);
		
		if(sectionUpdate!=null) return 1;
		return -1;
	}
	
	

	@Override
	public List<Classes> findListClassesSections(Long idSections) {
		List<Classes> listClassesSections=null;
		Sections sections=sectionsRepository.getOne(idSections);
		listClassesSections=(List<Classes>)sections.getListofClasses();
		System.err.println("liste des classes contenues dans la sections "+
									listClassesSections.toString());
		return listClassesSections;
	}

	@Override
	public int deleteSections(Long idSections) {
		/*
		 * On ne peut pas supprimer une section qui englobe des classes
		 */
		List<Classes> listClassesSections=this.findListClassesSections(idSections);
		if(listClassesSections.size()!=0){
			return 0;
		}
		
		sectionsRepository.delete(idSections);
		return 1;
		
	}

	@Override
	public List<Annee> findAllAnnee() {
		List<Annee> listofAnnee=null;
		listofAnnee=anneeRepository.findAllByOrderByIntituleAnneeDesc();
		return listofAnnee;
	}

	@Override
	public int saveAnnee(Annee annee) {
		Annee annee1=anneeRepository.findByIntituleAnnee(annee.getIntituleAnnee());
		if(annee1!=null) return 0;
		if(annee.getDatedebutPeriodes().compareTo(annee.getDatefinPeriodes())>=0) return -1;
		
		/*
		 * On doit faire la liste de toutes les autres années enregistrées et les désactivées
		 */
		List<Annee> listofAnnee = anneeRepository.findAllByOrderByIntituleAnneeDesc();
		
		for(Annee an : listofAnnee){
			an.setEtatPeriodes(false);
			anneeRepository.save(an);
		}
		
		anneeRepository.save(annee);
		return 1;
	}
	
	@Override
	public List<Annee> listofAnnee(){
		return anneeRepository.findAllByOrderByIntituleAnneeDesc();
	}

	@Override
	public List<Trimestres> findListTrimestresAnnee(Long idPeriodes) {
		List<Trimestres> listTrimestresAnnee=null;
		Annee annee=anneeRepository.getOne(idPeriodes);
		listTrimestresAnnee=(List<Trimestres>)annee.getListoftrimestre();
		
		return listTrimestresAnnee;
	}

	@Override
	public int deleteAnnee(Long idPeriodes) {
		
		/*
		 * On ne peut pas supprimer une annee qui aggrege des trimestres
		 */
		List<Trimestres> listofTrimestresAnnee=this.findListTrimestresAnnee(idPeriodes);
		if(listofTrimestresAnnee.size()!=0){
			return 0;
		}
		anneeRepository.delete(idPeriodes);
		return 1;
		
	}

	@Override
	public List<Trimestres> findAllTrimestres() {
		List<Trimestres> listofTrimestres=null;
		listofTrimestres=trimestreRepository.findAllByOrderByAnneeIntituleAnneeDesc();
		
		return listofTrimestres;
	}

	@Override
	public int saveTrimestres(Trimestres trimestre) {
		Annee annee=trimestre.getAnnee();
		List<Trimestres> listTrimestreAnnee=(List<Trimestres>)annee.getListoftrimestre();
		
		if(listTrimestreAnnee.size()==3) return 0;
		
		for(Trimestres trim : listTrimestreAnnee){
			if(trim.getNumeroTrim()==trimestre.getNumeroTrim()) return -1;
		}
		
		/*
		 * date1.compareTo(date2) retourne 0 si date1=date2
		 * un nombre négatif(<0) si date1 est antérieur à date2
		 * un nombre positif(>0) si date1 est ultérieure à date2
		 */
		if(trimestre.getNumeroTrim()==1){
			if((trimestre.getDatedebutPeriodes().compareTo(annee.getDatedebutPeriodes())<0)||
					trimestre.getDatefinPeriodes().compareTo(annee.getDatefinPeriodes())>0){
				return -2;
			}
		}
		
		if(trimestre.getNumeroTrim()==2){
			Trimestres trimestre1existant=null;
			for (Trimestres trim : listTrimestreAnnee) {
				if(trim.getNumeroTrim()==1){
					trimestre1existant=trim;
					break;
				}
			}
			if(trimestre1existant==null) return -3;
			
			if((trimestre.getDatedebutPeriodes().compareTo(
					trimestre1existant.getDatefinPeriodes())<=0)||
					(trimestre.getDatefinPeriodes().compareTo(annee.getDatefinPeriodes())>0)){
				return -4;
			}
			
		}
		
		if(trimestre.getNumeroTrim()==3){
			Trimestres trimestre2existant=null;
			for (Trimestres trim : listTrimestreAnnee) {
				if(trim.getNumeroTrim()==2){
					trimestre2existant=trim;
					break;
				}
			}
			if(trimestre2existant==null) return -5;
			
			if((trimestre.getDatedebutPeriodes().compareTo(
					trimestre2existant.getDatefinPeriodes())<=0)||
					(trimestre.getDatefinPeriodes().compareTo(annee.getDatefinPeriodes())>0)){
				return -6;
			}
			
		}
		
		if(trimestre.getDatedebutPeriodes().compareTo(
				trimestre.getDatefinPeriodes())>=0) return -7;
		
		trimestreRepository.save(trimestre);
		
		return 1;
	}

	@Override
	public Annee getAnneeIntituleAnnee(String intituleAnnee) {
		Annee annee=anneeRepository.findByIntituleAnnee(intituleAnnee);
		return annee;
	}

	@Override
	public List<Sequences> findListSequencesTrim(Long idPeriodes) {
		List<Sequences> listSequencesTrim=null;
		Trimestres trim=trimestreRepository.getOne(idPeriodes);
		listSequencesTrim=(List<Sequences>)trim.getListofsequence();
		
		return listSequencesTrim;
	}

	@Override
	public int deleteTrimestres(Long idPeriodes) {
		List<Sequences> listofSequencesTrim=this.findListSequencesTrim(idPeriodes);
		if(listofSequencesTrim.size()!=0){
			return 0;
		}
		/*
		 * recherche du trimestre que l'on veut supprimer
		 */
		Trimestres trim=trimestreRepository.getOne(idPeriodes);
		Annee anTrim=trim.getAnnee();
		List<Trimestres> listofTrimAn=(List<Trimestres>)anTrim.getListoftrimestre();
		
		for(Trimestres trimAn : listofTrimAn){
			if(trimAn.getNumeroTrim()>trim.getNumeroTrim()) return -1;
		}
		
		trimestreRepository.delete(idPeriodes);
		return 1;
		
	}

	@Override
	public List<Sequences> findAllSequences() {
		List<Sequences> listofSequences=null;
		listofSequences=
		sequenceRepository.findAllByOrderByTrimestreAnneeIntituleAnneeDescTrimestreNumeroTrimAscNumeroSeqAsc();
		return listofSequences;
	}

	@Override
	public int saveSequences(Sequences sequence) {
		Trimestres trimestre=sequence.getTrimestre();
		List<Sequences> listSequenceTrim=(List<Sequences>)trimestre.getListofsequence();
		//si le trimestre a déjà 2 séquences
		if(listSequenceTrim.size()==2) return 0;
		
		//si dans la liste des séquences du trimestre il ya déjà une séquence de même numéro
		for(Sequences seq : listSequenceTrim){
			if(seq.getNumeroSeq()==sequence.getNumeroSeq()) return -1;
		}
		
		//si les dates de la sequence ne sont pas conforme
		if(sequence.getDatedebutPeriodes().compareTo(
				sequence.getDatefinPeriodes())>=0) return -2;
		
		//si le trimestre de numéro choisi n'existe pas encore dans l'année
		Annee annee=trimestre.getAnnee();
		List<Trimestres> listTrimestreAnnee=(List<Trimestres>)annee.getListoftrimestre();
		
		int trimestre1existant=0;
		for (Trimestres trim : listTrimestreAnnee) {
			if(trim.getNumeroTrim()==sequence.getTrimestre().getNumeroTrim()){
				trimestre1existant=1;
				break;
			}
		}
		if(trimestre1existant==0) return -3;
		
		if(sequence.getNumeroSeq()==2){
			//est ce que la séquence de numero 1 existe déjà
			int seqExiste=0;
			for(Sequences seq : listSequenceTrim){
				if(seq.getNumeroSeq()==1) seqExiste=1;
			}
			if(seqExiste==0) return -4;
		}
		else if(sequence.getNumeroSeq()==4){
			//est ce que la séquence de numero 3 existe déjà
			int seqExiste=0;
			for(Sequences seq : listSequenceTrim){
				if(seq.getNumeroSeq()==3) seqExiste=1;
			}
			if(seqExiste==0) return -4;
		}
		else if(sequence.getNumeroSeq()==6){
			//est ce que la séquence de numero 5 existe déjà
			int seqExiste=0;
			for(Sequences seq : listSequenceTrim){
				if(seq.getNumeroSeq()==5) seqExiste=1;
			}
			if(seqExiste==0) return -4;
		}
		
		//est ce que les dates de seq1 et seq2 sont conformes par rapport au date de trim1
		if(trimestre.getNumeroTrim()==1){
			if(sequence.getNumeroSeq()==1){
				if((sequence.getDatedebutPeriodes().compareTo(
						trimestre.getDatedebutPeriodes())<0) || 
						sequence.getDatefinPeriodes().compareTo(trimestre.getDatefinPeriodes())>0)
					return -5;
			}
			if(sequence.getNumeroSeq()==2){
				Sequences seq1detrim1=null;
				for(Sequences seq : listSequenceTrim){
					if(seq.getNumeroSeq()==1) seq1detrim1=seq;
				}
				if((sequence.getDatedebutPeriodes().compareTo(
						seq1detrim1.getDatefinPeriodes())<0)||
						(sequence.getDatefinPeriodes().compareTo(trimestre.getDatefinPeriodes())>0))
					return -6;
			}
		}
		
		//est ce que les dates de seq3 et seq4 sont conformes par rapport au date de trim2
		if(trimestre.getNumeroTrim()==2){
			if(sequence.getNumeroSeq()==3){
				if((sequence.getDatedebutPeriodes().compareTo(
						trimestre.getDatedebutPeriodes())<0) || 
						sequence.getDatefinPeriodes().compareTo(trimestre.getDatefinPeriodes())>0)
					return -7;
			}
			if(sequence.getNumeroSeq()==4){
				Sequences seq3detrim2=null;
				for(Sequences seq : listSequenceTrim){
					if(seq.getNumeroSeq()==3) seq3detrim2=seq;
				}
				if((sequence.getDatedebutPeriodes().compareTo(
					seq3detrim2.getDatefinPeriodes())<0)||
						(sequence.getDatefinPeriodes().compareTo(trimestre.getDatefinPeriodes())>0))
					return -8;
			}
		}
		
		//est ce que les dates de seq5 et seq6 sont conformes par rapport au date de trim3
		if(trimestre.getNumeroTrim()==3){
			if(sequence.getNumeroSeq()==5){
				if((sequence.getDatedebutPeriodes().compareTo(
						trimestre.getDatedebutPeriodes())<0) || 
						sequence.getDatefinPeriodes().compareTo(trimestre.getDatefinPeriodes())>0)
					return -9;
			}
			if(sequence.getNumeroSeq()==6){
				Sequences seq5detrim3=null;
				for(Sequences seq : listSequenceTrim){
					if(seq.getNumeroSeq()==5) seq5detrim3=seq;
				}
				if((sequence.getDatedebutPeriodes().compareTo(
					seq5detrim3.getDatefinPeriodes())<0)||
						(sequence.getDatefinPeriodes().compareTo(trimestre.getDatefinPeriodes())>0))
					return -10;
			}
		}
		
		sequenceRepository.save(sequence);
		
		return 1;
	}

	@Override
	public Trimestres getTrimestreAnnee(Annee annee, int numeroTrim) {
		
		List<Trimestres> listofTrimAnnee=(List<Trimestres>)annee.getListoftrimestre();
		for(Trimestres trim : listofTrimAnnee){
			if(trim.getNumeroTrim()==numeroTrim) return trim;
		}
		return null;
	}

	@Override
	public List<Evaluations> findListEvalSeq(Long idPeriodes) {
		Sequences seq=sequenceRepository.getOne(idPeriodes);
		List<Evaluations> listofEvalSeq=null;
		if(seq!=null){
			listofEvalSeq=(List<Evaluations>)seq.getListofEval();
		}
		return listofEvalSeq;
	}

	@Override
	public List<RapportDAbsence> findListRabsSeq(Long idPeriodes) {
		Sequences seq=sequenceRepository.getOne(idPeriodes);
		List<RapportDAbsence> listofRabsSeq=null;
		if(seq!=null){
			listofRabsSeq=(List<RapportDAbsence>)seq.getListofRabs();
		}
		return listofRabsSeq;
	}
	
	@Override
	public int deleteSequences(Long idPeriodes) {
		List<Evaluations> listofEvalSeq=this.findListEvalSeq(idPeriodes);
		List<RapportDAbsence> listofRabsSeq=this.findListRabsSeq(idPeriodes);
		if(listofEvalSeq.size()!=0) return -1;
		if(listofRabsSeq.size()!=0) return -2;
		
		Sequences seqASupprim=sequenceRepository.getOne(idPeriodes);
		/*
		 * On va regarder si une séquence de numero supérieur existe dans le trimestre associe
		 * à cette séquence
		 */
		Trimestres trimAssocie=seqASupprim.getTrimestre();
		List<Sequences> listofSeqTrim=(List<Sequences>)trimAssocie.getListofsequence();
		for(Sequences seq : listofSeqTrim){
			if(seq.getNumeroSeq()>seqASupprim.getNumeroSeq()) return -3;
		}
		
		if(seqASupprim.isEtatPeriodes()==true) return 0;
		
		sequenceRepository.delete(idPeriodes);
		
		return 1;
	}

	@Override
	public Page<Specialites> findPageSpecialite(int numPage, int taillePage) {
		return specialitesRepository.findAll(new PageRequest(numPage, taillePage));
	}

	@Override
	public Specialites findSpecialites(String codeSpe) {
		try{
			Specialites speRechercher=specialitesRepository.findByCodeSpecialite(codeSpe);
			return speRechercher;
		}
		catch(Exception e){
			return null;
		}
	}
	
	@Override
	public Cycles findCycles(String codeCycles) {
		try{
			Cycles cycleRechercher=cyclesRepository.findByCodeCycles(codeCycles);
			return cycleRechercher;
		}
		catch(Exception e){
			return null;
		}
	}
	
	@Override
	public Specialites findSpecialites(Long idSpecialite) {
		try{
			Specialites speRechercher=specialitesRepository.getOne(idSpecialite);
			return speRechercher;
		}
		catch(Exception e){
			System.err.println("Ici dans findSpecialites "+e.getMessage());
			return null;
		}
		
	}
	
	@Override
	public int saveSpecialites(Specialites specialites) {
		Specialites saveSpeExist=this.findSpecialites(specialites.getCodeSpecialite());
		if(saveSpeExist!=null) return 0;
		Specialites spe=specialitesRepository.save(specialites);
		if(spe!=null) return 1;
		return -1;
	}
	
	@Override
	public int updateSpecialites(String codeSpeAModif, Specialites speModif) {
		
		Specialites speAModif=null;
		
		speAModif=this.findSpecialites(codeSpeAModif);
		
		if(speAModif==null) {
			/*
			 * On doit essayer d'enregistrer cette spécialité au cas c'est une nouvelle qu'on veut enregistrer
			 */
			
			return this.saveSpecialites(speModif);
		}
		
		speAModif.setCodeSpecialite(speModif.getCodeSpecialite());
		speAModif.setLibelleSpecialite(speModif.getLibelleSpecialite());
		
		Specialites speUpdate=specialitesRepository.save(speAModif);
		
		if(speUpdate!=null) return 1;
		return -1;
	}
	
	@Override
	public int updateCycles(String codeCycleAModif, Cycles cycleModif){
		Cycles cycleAModif=null;
		
		cycleAModif=this.findCycles(codeCycleAModif);
		
		if(cycleAModif==null) return 0;
		
		
		cycleAModif.setCodeCycles(cycleModif.getCodeCycles());
		cycleAModif.setCodeCycles_en(cycleModif.getCodeCycles_en());
		cycleAModif.setNumeroOrdreCycles(cycleModif.getNumeroOrdreCycles());
		
		Cycles cycleUpdate=cyclesRepository.save(cycleAModif);
		
		if(cycleUpdate!=null) return 1;
		return -1;
	}

	@Override
	public List<Classes> findListClassesSpecialites(Long idSpecialites) {
		List<Classes> listClassesSpecialites=null;
		Specialites specialite=this.findSpecialites(idSpecialites);
		listClassesSpecialites=(List<Classes>)specialite.getListofClasses();
		
		return listClassesSpecialites;
	}
	
	@Override
	public int deleteSpecialites(Long idSpecialites) {
		/*
		 * On ne peut pas supprimer une specialite qui englobe des classes
		 */
		List<Classes> listClassesSpecialite=this.findListClassesSpecialites(idSpecialites);
		if(listClassesSpecialite.size()!=0){
			return 0;
		}
		
		specialitesRepository.delete(idSpecialites);
		return 1;
		
	}

	@Override
	public Page<Classes> findPageClasse(int numPage, int taillePage) {
		return classesRepository.findAll(new PageRequest(numPage, taillePage));
		/*return classesRepository.findAllByOrderByCodeClassesAscSpecialiteCodeSpecialiteAscNumeroClassesAsc(
				new PageRequest(numPage, taillePage));*/
	}

	@Override
	public Classes findClasses(Long idClasse) {
		try{
			Classes classeRechercher=classesRepository.getOne(idClasse);
			return classeRechercher;
		}
		catch(Exception e){
			/*log.log(Level.WARN, "**** EXCEPTION DANS findClasses **** "+e.getMessage());*/
			return null;
		}
	}

	@Override
	public Classes findClasses(String codeClasses, String numeroClasses, Specialites specialite) {
		try{
			Classes classeRechercher=classesRepository.
					findByCodeClassesAndNumeroClassesAndSpecialite(codeClasses, numeroClasses, specialite);
			return classeRechercher;
		}
		catch(Exception e){
			/*log.log(Level.WARN, "**** EXCEPTION DANS findClasses(code, numero, specialite) **** "
					+e.getMessage());*/
			return null;
		}
	}

	@Override
	public List<Specialites> findAllSpecialites() {
		List<Specialites> listofSpecialites=null;
		listofSpecialites=specialitesRepository.findAllByOrderByCodeSpecialiteAsc();
		return listofSpecialites;
	}

	@Override
	public Sections findSections(String codeSection) {
		try{
			Sections sectionRechercher=sectionsRepository.findByCodeSections(codeSection);
			return sectionRechercher;
		}
		catch(Exception e){
			/*log.log(Level.WARN, "**** EXCEPTION DANS findSections **** "+e.getMessage());*/
			return null;
		}
	}
	
	@Override
	public Niveaux findNiveau(String codeNiveaux){
		try{
			Niveaux niveauRechercher=niveauxRepository.findByCodeNiveaux(codeNiveaux);
			return niveauRechercher;
		}
		catch(Exception e){
			/*log.log(Level.WARN, "**** EXCEPTION DANS findNiveaux **** "+e.getMessage());*/
			return null;
		}
	}

	@Override
	public Niveaux findNiveau(int numeroOrdreNiveaux) {
		try{
			Niveaux niveauRechercher=niveauxRepository.
					findByNumeroOrdreNiveaux(numeroOrdreNiveaux);
			return niveauRechercher;
		}
		catch(Exception e){
			/*log.log(Level.WARN, "**** EXCEPTION DANS findNiveaux **** "+e.getMessage());*/
			return null;
		}
	}

	@Override
	public int saveClasses(Classes classes) {
		
		Classes classesCoupleCodeNumeroExist=this.findClasses(
				classes.getCodeClasses(), classes.getNumeroClasses(), classes.getSpecialite());
		
		if(classesCoupleCodeNumeroExist!=null) return 0;
		
		Classes classesenreg=classesRepository.save(classes);
		
		if(classesenreg!=null) return 1;
		/*log.log(Level.WARN, "**** ERREUR D'ENREGISTREMENT DE LA CLASSE DANS "
				+ "saveClasses ADMIN**** ");*/
		return -1;
	}
	
	@Override
	public int updateClasses(String codeClasseAModif, String numeroClasseAModif, 
			Long idSpecialite, Classes classes){
		
		Specialites ancienneSpeClasse=this.findSpecialites(idSpecialite);
		
		Classes classesAModif=this.findClasses(
				codeClasseAModif, numeroClasseAModif, ancienneSpeClasse);
		
		if(classesAModif==null) {
			/*
			 * Si on ne trouve pas de classe alors peut être c'est une nouvelle classe qu'on veut
			 * enregistrer 
			 */
			
			return this.saveClasses(classes);
		}
		
		classesAModif.setCodeClasses(classes.getCodeClasses());
		classesAModif.setNumeroClasses(classes.getNumeroClasses());
		classesAModif.setNiveau(classes.getNiveau());
		classesAModif.setSection(classes.getSection());
		classesAModif.setSpecialite(classes.getSpecialite());
		classesAModif.setLangueClasses(classes.getLangueClasses());
		
		Classes classesUpdate=classesRepository.save(classesAModif);
		
		if(classesUpdate!=null) return 1;
		/*log.log(Level.WARN, "**** ERREUR de mise a jour DE LA CLASSE DANS "
				+ "updateClasses ADMIN**** ");*/
		return -1;
	}

	@Override
	public int deleteClasses(Long idClasses) {
		/*
		 * On ne peut pas supprimer une classe qui englobe des élèves
		 */
		List<Eleves> listofElevesClasses=this.findListElevesClasses(idClasses);
		if(listofElevesClasses.size()!=0) return 0;
		/*
		 * On ne peut pas supprimer une classe qui englobe des Cours
		 */
		List<Cours> listofCoursClasses=this.findListCoursClasses(idClasses);
		if(listofCoursClasses.size()!=0) return -1;
		
		classesRepository.delete(idClasses);
		return 1;
	}

	@Override
	public List<Eleves> findListElevesClasses(Long idClasses) {
		List<Eleves> listElevesClasses=null;
		Classes classe=this.findClasses(idClasses);
		listElevesClasses=(List<Eleves>)classe.getListofEleves();
		return listElevesClasses;
	}

	@Override
	public List<Cours> findListCoursClasses(Long idClasses){
		List<Cours> listCoursClasses=null;
		Classes classe=this.findClasses(idClasses);
		listCoursClasses=(List<Cours>)classe.getListofCours();
		return listCoursClasses;
	}

	@Override
	public Page<Roles> findPageRole(int numPage, int taillePage) {
		return rolesRepository.findAll(new PageRequest(numPage, taillePage));
	}

	@Override
	public List<UtilisateursRoles> findAllUsersRoles() {
		return usersrolesRepository.findAll();
	}
	
	@Override
	public List<Utilisateurs> findAllUsers(){
		List<UtilisateursRoles> listofusersRoles = this.findAllUsersRoles();
		List<Utilisateurs> listofUsers = new ArrayList<Utilisateurs>();
		for(UtilisateursRoles userRole: listofusersRoles){
			if(userRole.getRoleAssocie().getRole().equalsIgnoreCase("ADMIN")==false){
				//Avant d'ajouter on regarde s'il n'est pas déjà dans la liste afin d'eliminer les doublons
				int compt = 0;
				for(Utilisateurs user : listofUsers){
					if(user.getUsername().equalsIgnoreCase(userRole.getUsers().getUsername())==true){
						compt+=1;
					}
				}
				if(compt==0){
					listofUsers.add(userRole.getUsers());
				}
			}
		}
		return listofUsers;
	}

	@Override
	public Roles findRoles(String role) {
		Roles roleRechercher=null;
		
			try{
			/*roleRechercher=rolesRepository.getOne(role);*/
			roleRechercher=rolesRepository.findByRole(role);
			if(roleRechercher==null) System.err.println("role inexistant");
			return roleRechercher;
		}
		catch(Exception e){
			/*log.log(Level.WARN, "**** ERREUR dans findRoles ADMIN**** "+e.getMessage());*/
		}
		return roleRechercher;
	}

	@Override
	public int saveRoles(Roles role) {
		 
		Roles roleExist=this.findRoles(role.getRole());
		 
		if(roleExist!=null) return 0;
		Roles roleEnreg=rolesRepository.save(role);
		if(roleEnreg!=null) return 1;
		/*log.log(Level.WARN, "**** ERREUR D'ENREGISTREMENT DE role DANS saveRoles ADMIN**** ");*/
		return -1;
	}

	@Override
	public int updateRoles(String titreroleAModif, Roles role) {
		Roles roleAModif=this.findRoles(titreroleAModif);
		if(roleAModif==null) return 0;
		roleAModif.setAliasRole(role.getAliasRole());
		Roles roleUpdate=rolesRepository.save(roleAModif);
		if(roleUpdate!=null) return 1;
		return 0;
	}
	
	
	@Override
	public Page<Matieres> findAllMatieres(int numPage, int taillePage) {
		
		return matieresRepository.findAllByOrderByIntituleMatiereAscCodeMatiereAsc(new PageRequest(numPage, taillePage));
	}
	
	@Override
	public Matieres findMatieres(Long idMatiere) {
		
		return matieresRepository.findOne(idMatiere);
	}
	
	@Override
	public int updateMatiere(Matieres matiere) {
		Matieres matiereExistCode = matieresRepository.findByCodeMatiere(matiere.getCodeMatiere());
		Matieres matiereAModif = matieresRepository.findOne(matiere.getIdMatiere());
		
		if(matiereAModif == null) {
			/*
			 * Alors on veut faire un nouvel enregistrement
			 */
			
			if(matiereExistCode!=null) return 0;
			
			Matieres matiereAEnreg = new Matieres();
			matiereAEnreg.setCodeMatiere(matiere.getCodeMatiere());
			matiereAEnreg.setIntituleMatiere(matiere.getIntituleMatiere());
			
			/**
			 * debut des ajouts du 19-08-2018
			 */
			matiereAEnreg.setIntitule2langueMatiere(matiere.getIntitule2langueMatiere());
			/***
			 * Fin des ajouts du 19-08-2018
			 */
			
			matieresRepository.save(matiereAEnreg);
			return 1;
		}
		/*
		 * A ce niveau cela signifie qu'on veut faire une mise à jour de la matière trouvé en BD
		 * donc matiereAModif est != null
		 * On va donc vérifier si le nouveau code est différent de l'ancien et le cas échéant vérifier que 
		 * la contrainte ne sera pas violé
		 */
		
		if(!(matiere.getCodeMatiere().equals(matiereAModif.getCodeMatiere()))){
			/*
			 * il ne sont pas égaux donc on veut aussi modifier le code 
			 * on va donc se rassurer que la contrainte ne sera pas violé
			 */
			if(matiereExistCode!=null) return 0;
			/*
			 * Ici on est sur que la contrainte ne sera pas violé malgré le fait qu'on veut modifier aussi le code
			 */
			matiereAModif.setCodeMatiere(matiere.getCodeMatiere());
			matiereAModif.setIntituleMatiere(matiere.getIntituleMatiere());
			
			/**
			 * debut des ajouts du 19-08-2018
			 */
			
			matiereAModif.setIntitule2langueMatiere(matiere.getIntitule2langueMatiere());
			
			/***
			 * Fin des ajouts du 19-08-2018
			 */
			
			matieresRepository.save(matiereAModif);
			return 2;
			
		}
		/*
		 * Ici on est sur que les 2 code sont égaux donc c'est pas le code qu'on veut modifier
		 * mais juste l'intitulé peut. 
		 */
		matiereAModif.setIntituleMatiere(matiere.getIntituleMatiere());
		/**
		 * debut des ajouts du 19-08-2018
		 */
		
		matiereAModif.setIntitule2langueMatiere(matiere.getIntitule2langueMatiere());
		
		/***
		 * Fin des ajouts du 19-08-2018
		 */
		
		matieresRepository.save(matiereAModif);
		return 2;
	}

	@Override
	public int deleteMatiere(Long idMatiere) {
		
		Matieres matiereASupprim = this.findMatieres(idMatiere);
		if(matiereASupprim == null)	return -1;
		if(matiereASupprim.getListofCours().size() != 0) return 0;
		
		matieresRepository.delete(idMatiere);
		return 1;
	}

	@Override
	public Page<Cours> findAllCoursClasse(int numPage, int taillePage, Long idClasses){
		
		return coursRepository.findAllByClasseIdClassesOrderByMatiereCodeMatiereAscCodeCoursAsc(
				new PageRequest(numPage, taillePage), idClasses);
	}

	@Override
	public Page<Cours> findAllCoursProfClasse(int numPage, int taillePage, Long idClasses, Long idProf){
		
		return coursRepository.findAllByClasseIdClassesAndProffesseurIdUsersOrderByMatiereCodeMatiereAscCodeCoursAsc(
				new PageRequest(numPage, taillePage), idClasses, idProf);
	}

	@Override
	public List<Matieres> findAllMatieres() {
		
		return matieresRepository.findAll();
	}

	@Override
	public List<Classes> findAllClasse() {
		return classesRepository.findAll();
	}

	@Override
	public List<Proviseur> findAllProviseur() {
		
		return provRepository.findAll();
	}


	@Override
	public List<Censeurs> findAllCenseurs() {
		
		return censeurRepository.findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDescNumeroCensAsc();
	}


	@Override
	public List<SG> findAllSG() {
		
		return sgRepository.findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDescNumeroSgAsc();
	}


	@Override
	public List<Intendant> findAllIntendant() {
		
		return intendantRepository.findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDescNumeroIntAsc();
	}


	@Override
	public List<Proffesseurs> findAllProffesseurs() {
		
		return proffRepository.findAll();
	}



	@Override
	public Page<Enseignants> findAllEnseignants(int numPage, int taillePage) {
		
		return enseignantRepository.findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDesc(
				new PageRequest(numPage, taillePage));
	}
	
	@Override
	public List<Enseignants> findAllEnseignants(){
		return enseignantRepository.findAllByOrderByNomsPersAscPrenomsPersAscDatenaissPersDesc();
	}

	@Override
	public Cours findCours(Long idCours) {
		
		return coursRepository.findOne(idCours);
	}

	@Override
	public int ajouterRoleUser(Long idUser, String role){
		Utilisateurs user = this.getUsers(idUser);
		if(user == null) return 0;
		Roles roles=rolesRepository.findByRole(role);
		if(roles==null) return 0;
		/*
		 * Avant d'ajouter, on regarde s'il a déja et si c'est le cas on laisse sinon on ajoute
		 */
		List<UtilisateursRoles> listofuserRolesAssocie = (List<UtilisateursRoles>) user.getListofusersRoles();
		int compt = 0;
		for(UtilisateursRoles userrole : listofuserRolesAssocie){
			if(userrole.getRoleAssocie().getRole().equalsIgnoreCase(role)==true) compt+=1;
		}
		
		if(compt == 0){
			/*
			 * Alors celui ci n'a pas encore le role enseignant et on souhaite comme ca l'attribuer un cours
			 * donc il faut lui ajouter le role enseignant
			 */
			UtilisateursRoles usersroles=new UtilisateursRoles();
			usersroles.setUsers(user);
			usersroles.setRoleAssocie(roles);
			usersrolesRepository.save(usersroles);
		}
		
		return 1;
	}
	
	@Override
	public int retirerRoleUser(Long idUser, String role){
		Utilisateurs user = this.getUsers(idUser);
		if(user == null) return -1;
		Roles roles=rolesRepository.findByRole(role);
		if(roles==null) return -1;
		UtilisateursRoles usersroles=usersrolesRepository.getUtilisateursRoles(idUser, role);
		if(usersroles == null) return 0;
		usersrolesRepository.delete(usersroles);
		return 1;
	}
	
	@Override
	public int updateCours(Cours cours, Long idMatiereAssocie, Long idProfAssocie, Long idClasseAssocie) {
		//Cours coursExistCode = coursRepository.findByCodeCours(cours.getCodeCours());
		
		Cours coursAModif = coursRepository.findOne(cours.getIdCours());
		
		Matieres matiereAssocie = matieresRepository.findOne(idMatiereAssocie);
		
		Proffesseurs profAssocie = proffRepository.findOne(idProfAssocie);
		
		Classes classeAssocie = classesRepository.findOne(idClasseAssocie);
		
		/*
		 * Il faut ajouter le role enseignant si le user n'a pas encore ce role
		 */
		String role = "ENSEIGNANT";
		int ret = this.ajouterRoleUser(profAssocie.getIdUsers(), role);
		
		if((matiereAssocie == null) || (profAssocie == null) || (classeAssocie == null)|| (ret != 1)){
			return -1;
		}
		
		if(coursAModif == null) {
			/*
			 * Alors on veut faire un nouvel enregistrement de cours
			 */
			//if(coursExistCode != null) return 0;
			
			Cours coursAEnreg = new Cours();
			coursAEnreg.setCodeCours(cours.getCodeCours());
			coursAEnreg.setIntituleCours(cours.getIntituleCours());
			/***
			 * Debut des ajouts du 19-08-2018
			 */
			coursAEnreg.setIntitule2langueCours(cours.getIntitule2langueCours());
			coursAEnreg.setGroupeCours(cours.getGroupeCours());
			
			coursAEnreg.setNumerogroupeCours(cours.getNumerogroupeCours());
			/***
			 * Fin des ajouts du 19-08-2018
			 */
			coursAEnreg.setCoefCours(cours.getCoefCours());
			/*
			 * Recherchons la matiere, la classe et le users associe
			 */
			
			//Ici on est sur que la classe, la matiere et le prof existe
			coursAEnreg.setMatiere(matiereAssocie);
			coursAEnreg.setProffesseur(profAssocie);
			coursAEnreg.setClasse(classeAssocie);
			
			
			
			coursRepository.save(coursAEnreg);
			
			return 1;
		}
		/*
		 * A ce niveau cela signifie qu'on veut faire une mise à jour du cours trouvé en BD
		 * donc matiereAModif est != null
		 */
		if(!(cours.getCodeCours().equals(coursAModif.getCodeCours()))){
			//if(coursExistCode != null) return 0;
			
			coursAModif.setCodeCours(cours.getCodeCours());
			coursAModif.setIntituleCours(cours.getIntituleCours());
			/***
			 * Debut des ajouts du 19-08-2018
			 */
			coursAModif.setIntitule2langueCours(cours.getIntitule2langueCours());
			coursAModif.setGroupeCours(cours.getGroupeCours());
			coursAModif.setNumerogroupeCours(cours.getNumerogroupeCours());
			/***
			 * Fin des ajouts du 19-08-2018
			 */
			coursAModif.setCoefCours(cours.getCoefCours());
			
			if(matiereAssocie.getIdMatiere().longValue() != coursAModif.getMatiere().getIdMatiere().longValue()){
				coursAModif.setMatiere(matiereAssocie);
			}
			
			if(profAssocie.getIdUsers().longValue() != coursAModif.getProffesseur().getIdUsers().longValue()){
				/*
				 * Lorsqu'on veut en fait changer de prof, il faut se rassurer que l'ancien a encore un cours 
				 * qu'il enseigne sinon, il faut lui retirer le role. Pour cela, il faut récuperer la liste de tous les 
				 * cours qu'il enseigne et voir si apres modification elle ne sera pas vide. En d'autres termes, il 
				 * faut vérifier si cette liste a une taille >1 car si c'est exactement 1 alors apres modif elle sera 
				 * vide
				 */
				List<Cours> listcoursEnseigne = (List<Cours>) coursAModif.getProffesseur().getListofCours();
				if(listcoursEnseigne.size()==1){
					String role1="ENSEIGNANT";
					this.retirerRoleUser(coursAModif.getProffesseur().getIdUsers(), role1);
				}
				coursAModif.setProffesseur(profAssocie);
			}
			
			if(classeAssocie.getIdClasses().longValue() != coursAModif.getClasse().getIdClasses().longValue()){
				coursAModif.setClasse(classeAssocie);
			}
			
			coursRepository.save(coursAModif);
			return 2;
			
		}
		
		/*
		 * Ici on est sur que ce n'est pas le code qu'on veut modifier mais peut être le reste
		 * des champs de l'entite cours
		 */
		coursAModif.setIntituleCours(cours.getIntituleCours());
		/***
		 * Debut des ajouts du 19-08-2018
		 */
		coursAModif.setIntitule2langueCours(cours.getIntitule2langueCours());
		coursAModif.setGroupeCours(cours.getGroupeCours());
		coursAModif.setNumerogroupeCours(cours.getNumerogroupeCours());
		/***
		 * Fin des ajouts du 19-08-2018
		 */
		coursAModif.setCoefCours(cours.getCoefCours());
		
		if(matiereAssocie.getIdMatiere().longValue() != coursAModif.getMatiere().getIdMatiere().longValue()){
			coursAModif.setMatiere(matiereAssocie);
		}
		
		if(profAssocie.getIdUsers().longValue() != coursAModif.getProffesseur().getIdUsers().longValue()){
			/*
			 * Lorsqu'on veut en fait changer de prof, il faut se rassurer que l'ancien a encore un cours 
			 * qu'il enseigne sinon, il faut lui retirer le role. Pour cela, il faut récuperer la liste de tous les 
			 * cours qu'il enseigne et voir si apres modification elle ne sera pas vide. En d'autres termes, il 
			 * faut vérifier si cette liste a une taille >1 car si c'est exactement 1 alors apres modif elle sera 
			 * vide
			 */
			List<Cours> listcoursEnseigne = (List<Cours>) coursAModif.getProffesseur().getListofCours();
			if(listcoursEnseigne.size()==1){
				String role1="ENSEIGNANT";
				this.retirerRoleUser(coursAModif.getProffesseur().getIdUsers(), role1);
			}
			coursAModif.setProffesseur(profAssocie);
		}
		
		if(classeAssocie.getIdClasses().longValue() != coursAModif.getClasse().getIdClasses().longValue()){
			coursAModif.setClasse(classeAssocie);
		}
		
		coursRepository.save(coursAModif);
		
		return 2;
		
	}



	@Override
	public int deleteCours(Long idCours) {
		Cours coursASupprim = this.findCours(idCours);
		if(coursASupprim == null) return -1;
		if(coursASupprim.getListofEval().size() != 0) return 0;
		
		coursRepository.delete(idCours);
		return 1;
	}
	
}
