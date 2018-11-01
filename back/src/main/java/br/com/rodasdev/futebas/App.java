package br.com.rodasdev.futebas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class App {

	@Bean
    public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		if (System.getenv("PROFILE") == null || System.getenv("PROFILE").equals("dev")) {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowCredentials(true);
			config.addAllowedOrigin("*");
			config.addAllowedHeader("*");
			config.addAllowedMethod("OPTIONS");
			config.addAllowedMethod("GET");
			config.addAllowedMethod("POST");
			config.addAllowedMethod("PUT");
			config.addAllowedMethod("DELETE");
			source.registerCorsConfiguration("/**", config);
		}
		
        return new CorsFilter(source);
    }
	
	public static void main(String[] args) {
		if (System.getenv("PROFILE") == null) {
			System.setProperty("spring.profiles.active", "dev");
		}
		
		SpringApplication.run(App.class, args);
	}
}
