package com.group1.dev.app.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.group1.dev.app.Jwt.JwtAuthFilter;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
        		

                (authorize) -> authorize.anyRequest().authenticated())
                .cors()
                .and()
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuth(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
    }
  
 
  
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer(HandlerMappingIntrospector introspector) {
      MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
      return (web) -> web.ignoring()
              .requestMatchers(PathRequest.toH2Console())
//              .requestMatchers(mvcMatcherBuilder.pattern("/users/all"))
              .requestMatchers(mvcMatcherBuilder.pattern("/auth/login"))
          ;
	}
  
  @Bean
  public JwtAuthFilter jwtAuth() {
      return new JwtAuthFilter(secretKey());
  }
  
  @Bean
  public SecretKey secretKey() {
      SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
      return secretKey;
  }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // Cambia esto al origen de tu frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Especifica los métodos permitidos
                        .allowCredentials(true);
            }
        };
    }

}