package br.com.rodasdev.futebas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling()
			.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests()
				.antMatchers("/actuator/**").permitAll()
				// .antMatchers("/actuator/**").hasAuthority("ADMIN")
				.antMatchers(
						HttpMethod.GET, 
						"/api/**",
						"/v2/api-docs", 
						"/swagger-resources/**", 
						"/swagger-ui.html**",
						"/swagger-ui2.html**",
						"/webjars/**", 
						"favicon.ico")
						.permitAll()
				.antMatchers("/auth/**").permitAll()
				.anyRequest().authenticated()
			.and().httpBasic();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("futebas")
			.password(passwordEncoder().encode("futebas"))
			.authorities("ROLE_USER");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
