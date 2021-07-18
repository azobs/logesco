package org.logesco.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*@RestController*/
@Controller
/*@RequestMapping(path="/logesco")*/
public class HelloController {
	
	@Value("${langue}")
	private String langue;
    //
    @RequestMapping("/logesco/index")
    public String index(Model model, HttpServletRequest request,
    		@RequestParam(name="lang", defaultValue="fr") String lang) throws ParseException {
    	
    	DateFormat dfNameJour=new SimpleDateFormat("EEEE");
    	/*DateFormat dfJour=new SimpleDateFormat("dd");
    	DateFormat dfmois=new SimpleDateFormat("MMMM");
    	DateFormat dfyear=new SimpleDateFormat("yyyy");*/
    	DateFormat dfheure=new SimpleDateFormat("H:m:s");
    	
    	HttpSession session=request.getSession();
    	session.setAttribute("lang", lang);
    	////System.err.println("la langue choisi par le user est "+lang);
    	//System.err.println("quel sera la langue de la date ===  "+lang);
    	Date dateJour=new Date();
    	if(lang.equals("fr")==true){
    		Locale localeFr = new Locale("fr","FR");
    		dfNameJour = DateFormat.getDateInstance(DateFormat.FULL, localeFr);
    		dfheure = DateFormat.getTimeInstance(DateFormat.FULL, localeFr);
    		//System.out.println("Francais ===  "+dfNameJour.format(dateJour).toUpperCase());
    	}
    	else if(lang.equals("en")==true){
    		Locale localeEn = new Locale("en","EN");
    		dfNameJour = DateFormat.getDateInstance(DateFormat.FULL, localeEn);
    		dfheure = DateFormat.getTimeInstance(DateFormat.FULL, localeEn);
    		//System.out.println("Anglais ===  "+dfNameJour.format(dateJour).toUpperCase());
    	}
    	
    	
    	model.addAttribute("dfNameJour", dfNameJour.format(dateJour).toUpperCase());;
    	model.addAttribute("dfheure", dfheure.format(dateJour));
    	
    	session.setAttribute("dfNameJour", dfNameJour.format(dateJour).toUpperCase());
    	session.setAttribute("dfheure", dfheure.format(dateJour));
    	
        return "index";
    }
    
    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    
    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request,
    		@RequestParam(name="lang", defaultValue="fr") String lang) throws ParseException{
    	/*
    	 * LE MOT DE PASSE PAR DEFAUT DE L'ADMINISTRATEUR EST 12345
    	 */
    	
    	//String lang = (String) session.getAttribute("lang");
    	
    	/*Pbkdf2PasswordEncoder p=new Pbkdf2PasswordEncoder();*/
    	//StandardPasswordEncoder s=new StandardPasswordEncoder();
    	
		/*//System.out.println("p=="+p.encode("12345"));
		//System.out.println("MATCHESSSS==========="+p.matches("admin12345", "3b974605e4d35c747d69d85966ef988f61481a549184441af421ef3c9f2451b66aef9615220f4486"));*/
        
		/*//System.out.println("p=="+s.encode("12345"));
		//System.out.println("MATCHESSSS==========="+s.matches("admin12345", "3b974605e4d35c747d69d85966ef988f61481a549184441af421ef3c9f2451b66aef9615220f4486"));*/
		
    	
    	//System.err.println("lang choisi "+lang);
    	
    	DateFormat dfNameJour=new SimpleDateFormat("EEEE");
    	DateFormat dfJour=new SimpleDateFormat("dd");
    	DateFormat dfmois=new SimpleDateFormat("MMMM");
    	DateFormat dfyear=new SimpleDateFormat("yyyy");
    	DateFormat dfheure=new SimpleDateFormat("H:m:s");
    	Date dateJour=new Date();
    	
    	if(lang.equals("fr")==true){
    		Locale localeFr = new Locale("fr","FR");
    		dfNameJour = DateFormat.getDateInstance(DateFormat.FULL, localeFr);
    		dfmois = DateFormat.getDateInstance(DateFormat.FULL, localeFr);
    	}
    	else if(lang.equals("en")==true){
    		Locale localeEn = new Locale("en","EN");
    		dfNameJour = DateFormat.getDateInstance(DateFormat.FULL, localeEn);
    		dfmois = DateFormat.getDateInstance(DateFormat.FULL, localeEn);
    	}
    	
    	model.addAttribute("dfNameJour", dfNameJour.format(dateJour).toUpperCase());
    	model.addAttribute("dfJour", dfJour.format(dateJour));
    	model.addAttribute("dfmois", dfmois.format(dateJour));
    	model.addAttribute("dfyear", dfyear.format(dateJour));
    	model.addAttribute("dfheure", dfheure.format(dateJour));
    	
		return "login";
    }
    
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    @GetMapping("/logesco/403")
    public String error403() {
        return "/errors/403";
    }
    
    @PostMapping("/logoute")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
       ////System.err.println("Il souhaite se deconnecter");
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
        if (auth != null){    
        	 ////System.err.println("Nous le faisons donc et ... ");
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        ////System.err.println("on l'a fait par programmation et il sera donc redirectionne");
        return "redirect:/logesco/index?logout";
    }
    
}
