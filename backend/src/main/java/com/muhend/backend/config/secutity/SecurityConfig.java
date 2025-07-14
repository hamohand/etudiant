package com.muhend.backend.config.secutity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Appliquer la configuration CORS définie ci-dessous
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // Désactiver la protection CSRF, car elle n'est pas adaptée aux API REST sans état
                // (d'autres mécanismes comme CORS et l'authentification par jeton la remplacent)
                .csrf(AbstractHttpConfigurer::disable)

                // Définir les autorisations de requêtes
                .authorizeHttpRequests(authz -> authz
                        // Autoriser toutes les requêtes sur "/api/**".
                        // Vous pourrez affiner cela plus tard si vous ajoutez de l'authentification.
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/api/students/**").permitAll()
                        // Exiger une authentification pour toutes les autres requêtes (par sécurité)
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // --- IMPORTANT ---
        // Remplacez par le domaine exact de votre frontend
        configuration.setAllowedOrigins(List.of("https://etudiant.enclume-numerique.com", "https://www.etudiant.enclume-numerique.com"));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
