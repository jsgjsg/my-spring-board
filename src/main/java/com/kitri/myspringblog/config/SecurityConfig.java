package com.kitri.myspringblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select username, password, enabled from users where username=?");
        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT u.username, a.authority FROM authorities a " +
                "JOIN users u ON a.user_id = u.id WHERE u.username = ?");

        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.ignoringRequestMatchers("/board/**"))
            .csrf(csrf -> csrf.ignoringRequestMatchers("/comment/**"))
            .csrf(csrf -> csrf.ignoringRequestMatchers("/signup"))
            .csrf(csrf -> csrf.ignoringRequestMatchers("/login"))
            .csrf(csrf -> csrf.ignoringRequestMatchers("/logout"))
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/signup", "/WEB-INF/**").permitAll()
                    .requestMatchers("/login").permitAll()
                    .anyRequest().authenticated()
            )
//            .formLogin(withDefaults()); // 스프링 시큐리티 기본 로그인 페이지 사용
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/board/list", true)
                .failureUrl("/login?error")
                .permitAll()
            );
        return http.build();
    }
}
