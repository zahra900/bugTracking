package com.zahra.app.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;





@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /*to bootstrap the authorization server and resource server configuration, we have WebSecurityConfiguration. */
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImp();
	}
	
   @Override
   @Bean
   public AuthenticationManager authenticationManagerBean() throws Exception {
	   return super.authenticationManagerBean();
   }
      
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	

	@Autowired
	    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService())
	                .passwordEncoder(passwordEncoder());
	}
	  
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers(HttpMethod.OPTIONS, "/oauth/token");
	}  
	
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .csrf().disable()
	                .anonymous().disable()
	                .authorizeRequests()
	                .antMatchers("/auth/**").permitAll();
	   }



	 


}
