package com.example.MangaHaven.configuration;

import com.example.MangaHaven.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/", "/css/**", "/js/**", "/api/**", "/registration").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout").permitAll()
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        // Adding users from userService
        List<UserDetails> userDetailsList = new ArrayList<>(userService.findAll().stream()
                .map(user -> buildUserDetails(user.getUsername(), user.getPassword(), "USER"))
                .toList());

        // Adding admin user
        UserDetails adminUser = buildUserDetails("Alim", "Bek",
                "USER", "ADMIN");
        userDetailsList.add(adminUser);

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    private UserDetails buildUserDetails(String username, String password, String... roles) {
        return User.withUsername(username)
                .password(password)
                .roles(roles)
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
