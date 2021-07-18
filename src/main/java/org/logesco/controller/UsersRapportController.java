/**
 * 
 */
package org.logesco.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.logesco.entities.Etablissement;
import org.logesco.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

/**
 * @author cedrickiadjeu
 *
 */
@Controller
@RequestMapping(path="/logesco/users/rapports")
public class UsersRapportController {

	@Autowired
	private IUsersService usersService;
	@Autowired
	private ApplicationContext applicationContext;
	
	@GetMapping(path="/test2")
	public ModelAndView test2(Model model, HttpServletRequest request){
		
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:/reports/test2.jrxml");
		view.setApplicationContext(applicationContext);
		
		Etablissement etab = usersService.getEtablissement();
		
		List<Map<String, Object>> dataSource = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("nomsEtab", etab.getNomsEtab());
		item.put("aliasEtab", etab.getAliasEtab());
		item.put("matriculeEtab", etab.getMatriculeEtab());
		item.put("adresseEtab", etab.getBpEtab()+"/"+etab.getNumtel1Etab());
		item.put("ministeretuteleEtab", etab.getMinisteretuteleEtab());
		item.put("deviseEtab", etab.getDeviseEtab());
		item.put("nomsanglaisEtab", etab.getNomsanglaisEtab());
		item.put("aliasnomanglaisEtab", etab.getAliasnomanglaisEtab());
		item.put("aliasministeretuteleEtab", etab.getAliasministeretuteleEtab());
		item.put("ministeretuteleanglaisEtab", etab.getMinisteretuteleanglaisEtab());
		item.put("aliasministeretuteleanglaisEtab", etab.getAliasministeretuteleanglaisEtab());
		item.put("deviseanglaisEtab", etab.getDeviseanglaisEtab());
		item.put("deleguationregtuteleEtab", etab.getDeleguationregtuteleEtab());
		item.put("deleguationregtuteleanglaisEtab", etab.getDeleguationregtuteleanglaisEtab());
		item.put("deleguationdeptuteleEtab", etab.getDeleguationdeptuteleEtab());
		item.put("deleguationdeptuteleanglaisEtab", etab.getDeleguationdeptuteleanglaisEtab());
		
		dataSource.add(item);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("datasource", dataSource);
		
		return new ModelAndView(view, params);
	}
	
	
	
	
	
	
	
}
