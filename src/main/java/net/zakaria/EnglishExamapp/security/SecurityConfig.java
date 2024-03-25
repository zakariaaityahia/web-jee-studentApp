package net.zakaria.EnglishExamapp.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build()
        );
    }

    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity
                .formLogin(form -> form
                        .loginPage("/login") // Specify custom login page
                        .permitAll() // Allow access to the login page for everyone
                )

                .authorizeHttpRequests(ar -> ar
                        .requestMatchers("/deleteStudent/**").hasRole("ADMIN")
                        //.requestMatchers("/admin/**").hasRole("ADMIN")
                        //.requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/webjars/**").permitAll()
                        //.requestMatchers("/login").rememberMe()
                        .anyRequest().authenticated())
                .exceptionHandling(e -> e.accessDeniedPage("/notAuthorized"));

        return httpSecurity.build();
    }



}


