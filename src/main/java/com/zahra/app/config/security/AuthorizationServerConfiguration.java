package com.zahra.app.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;



@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter  {
/*authorization server configuration that is responsible for generating authorization tokens*/
	private static final String CLIENT_ID = "my-app-client";
	private static final String CLIENT_SECRET = "$2a$10$xAKftFoT2PvDx6jHBwxzCOpfecZi5JSR8OSo2ILzkWBPXwWnVbnSy";

	
	@Autowired
	private AuthenticationManager authenticationManager;
/*	Instead of creating an authentication manager, we choose to inject an existing authentication manager from the Spring container.
	With this step, we can share the authentication manager with the Basic Authentication filter.*/
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("as466gf");
        return converter;
	 }	

	
	@Bean
	public TokenStore tokenStore() {
	     return new JwtTokenStore(accessTokenConverter());
    }
	
	
	@Override 
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		
		clients
		        .inMemory()
		        .withClient(CLIENT_ID)
		        .secret(CLIENT_SECRET)
			    .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
			    .authorities("ROLE_ADMIN")
			    .scopes("read", "write", "trust")
			    .accessTokenValiditySeconds(12000)
			    .refreshTokenValiditySeconds(60000);		
	}
	

	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		endpoints
					.tokenStore(tokenStore())
					.authenticationManager(authenticationManager)
					.accessTokenConverter(accessTokenConverter());
	}
	


}
