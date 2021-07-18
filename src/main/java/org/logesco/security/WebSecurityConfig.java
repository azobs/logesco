package org.logesco.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/*import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
/*import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;*/
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
	
    
    
	@Bean
	public AuthenticationSuccessHandler successHandler() {
	    SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
	    handler.setUseReferer(true);
	    return handler;
	}
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/").permitAll()
                .antMatchers("/webjars").permitAll()
                .antMatchers("/css").permitAll()
                .antMatchers("/images").permitAll()
                .antMatchers("/logesco/login").permitAll()
                .antMatchers("/hello").hasAnyRole("ADMIN","PROVISEUR", "CENSEUR", "SG", "INTENDANT", "ENSEIGNANT","SUPERADMIN")
                .antMatchers("/home").hasAnyRole("ADMIN","PROVISEUR", "CENSEUR", "SG", "INTENDANT", "ENSEIGNANT","SUPERADMIN")
                .antMatchers("/logesco/users/*").hasAnyRole("PROVISEUR", "CENSEUR", "SG", "INTENDANT", "ENSEIGNANT","SUPERADMIN")
                .antMatchers("/logesco/admin/*").hasAnyRole("ADMIN","SUPERADMIN")
                /*.anyRequest().authenticated()*/
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                /*.defaultSuccessUrl("/index")*/
                /*.failureForwardUrl("/login")*/
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/logesco/index?logout")
            .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            .and()
            .csrf().disable()
            .sessionManagement()
            .invalidSessionUrl("/logesco/index?logout");// /logout
       
    }

    /*@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        
        
        return new InMemoryUserDetailsManager(user);
    }*/
    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
    		throws Exception {

    	//UserBuilder users = User.withDefaultPasswordEncoder();//ne marche qu'avec spring boot 2.0.0.release
    	
    	/*UserBuilder users1 = User.withDefaultPasswordEncoder();
    	UserBuilder users2 = User.withDefaultPasswordEncoder();*/
        /*auth.inMemoryAuthentication()
        		.withUser(users.username("users").password("pass").roles("USER"));
        auth.inMemoryAuthentication()
        	.withUser(users.username("admin").password("12345").roles("ADMIN"));
        auth.inMemoryAuthentication()
    		.withUser(users.username("user").password("p1").roles("USER"));*/
        
        
       /*auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select login as principal, password as credentials from utilisateurs where login=?")
			.authoritiesByUsernameQuery("select login as principal, role as role from users_roles where login=?")
			.rolePrefix("ROLE_")
			.passwordEncoder(PasswordEncoder.);*/
        
        
    }
    
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		//UserBuilder users = User.withDefaultPasswordEncoder();
		
		/*auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("select username as principal,password as credentials, enabled from users where username=?")
				.authoritiesByUsernameQuery("select username as principal, role as role from user_roles where username=?")
				.passwordEncoder(NoOpPasswordEncoder.getInstance())
				.rolePrefix("ROLE_");*/
		
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		/*.usersByUsernameQuery("select username as principal,password as credentials, enabled from users where username=?")
		.authoritiesByUsernameQuery("select username as principal, role as role from user_roles where username=?")*/
		.usersByUsernameQuery("select username as principal,password as credentials, enabled from utilisateurs where username=?")
		/*.authoritiesByUsernameQuery("select users_username as principal, role_associe_role as role from utilisateurs_roles where users_username=?")*/
		.authoritiesByUsernameQuery("select username as principal, role_associe_role as role from utilisateurs,utilisateurs_roles where utilisateurs.id_users=utilisateurs_roles.users_id_users and username=?")
		/*.passwordEncoder(NoOpPasswordEncoder.getInstance())*/
		.passwordEncoder(new Pbkdf2PasswordEncoder())
		/*.passwordEncoder(new StandardPasswordEncoder())*/
		.rolePrefix("ROLE_");
		
	}
    
}