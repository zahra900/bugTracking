package com.zahra.app.config.security;



import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;



@Configuration
@EnableResourceServer
@EnableConfigurationProperties(SecurityProperties.class)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter  {
	
/*Resource in our context is the REST API which we have exposed for the crud operation. */
/*To access these resources, the client must be authenticated.*/
	
private static final String RESOURCE_ID = "my_rest_api";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID);
	}
	

	 
	@Override
	public void configure(HttpSecurity http) throws Exception {      	  
		 http.
         anonymous().disable()
         .authorizeRequests()
         .antMatchers("/auth/**").authenticated()
         .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
	
	
}