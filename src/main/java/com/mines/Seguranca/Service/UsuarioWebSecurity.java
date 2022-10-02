package com.mines.Seguranca.Service;

import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableWebSecurity
public class UsuarioWebSecurity extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.cors().disable()
        .csrf().disable()
        .authorizeRequests().antMatchers("*/").permitAll().and().httpBasic();
    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("*"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);

            return config;
			}
		}).and().csrf().ignoringAntMatchers("/*").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().
		authorizeRequests().antMatchers("/*").permitAll().and().httpBasic();

        //http.authorizeRequests()
        //.anyRequest().permitAll()
        //.and()
        //.csrf()
        //.disable()
        //.sessionManagement()
        //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //.and()
        //.httpBasic();
    }*/

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin123").roles("USER", "ADMIN");
    }*/
    
}
