package com.example.demo.Security;

import com.example.demo.Model.MyAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final MyAppUserService appUserService;

    @Bean
    public UserDetailsService userDetailsService() {
        return appUserService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(passwordEncoder());
        // TODO: You should set a PasswordEncoder
        // e.g., provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(httpForm -> httpForm
                .loginPage("/login")  // Must have a controller & template for "/login"
                .permitAll()
                .defaultSuccessUrl("/dragon-home")
            )
            .authorizeHttpRequests(registry -> registry
                .requestMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            )
            .build();
    }
}
