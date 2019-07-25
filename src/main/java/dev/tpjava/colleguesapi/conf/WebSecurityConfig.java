package dev.tpjava.colleguesapi.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().cors().and()
                .authorizeRequests()

                .antMatchers("/h2-console/**").permitAll()

                //.antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()

                .anyRequest().authenticated()

                .and().headers().frameOptions().disable()

                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)

                .logout()

                .logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))

                .deleteCookies(TOKEN_COOKIE); // Gestion de la déconnexion

    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/logout", config);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
