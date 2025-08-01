package com.julspringsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UsersUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CSRF 토큰 비활성화
        // http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/member/**","/api/auth/**","/api/answers").authenticated()
                    .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll();
        });
        // 커스텀한 로그인 사용
        http.formLogin((formLogin) -> {
            formLogin.loginPage("/login")
                    .defaultSuccessUrl("/");
        }).logout((logout) -> {
            logout.logoutUrl("/logout")
                    .invalidateHttpSession(true).logoutSuccessUrl("/");
        }).exceptionHandling((exception) -> exception.accessDeniedPage("/accessDenied"));

        http.userDetailsService(userDetailsService); // loadUserByUsername()호출
		

        return http.build();
    }

    // @Autowired
    // public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.inMemoryAuthentication().withUser("manager")
    //             .password("{noop}manager123")
    //             .roles("MANAGER");

    //     auth.inMemoryAuthentication().withUser("admin")
    //             .password("{noop}admin123")
    //             .roles("ADMIN");
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
