package com.julspringsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// 모든 요청URL이 SpringSecurity 제어를 받도록. 스프링 시큐리티를 활성화
public class SecurityConfig {

    @Autowired
    UsersUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 설정
        // CSRF 토큰 비활성화
        // http.csrf(csrf->csrf.disable());
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/member/**").authenticated()
                    // member와 하위경로에 대해(,를 통해 여러경로 사용가능).인증된()
                    .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
                    // manager와 하위경로에 대해."MANAGER"와 "ADMIN" 중 하나 이상의 권한이 있는()
                    .requestMatchers("/list/**").hasAnyRole("ADMIN")
                    .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                    // admin과 하위경로에 대해."MANAGER"와 "ADMIN" 중 하나의 권한이 있는()
                    .anyRequest().permitAll();
            // 나머지 요청에 대해 모두 허가.
        });

        // 커스텀한 로그인 사용
        http.formLogin((formLogin) -> {
            formLogin.loginPage("/login")
                    .defaultSuccessUrl("/loginSuccess");
        }).logout((logout) -> {
            logout.logoutUrl("/logout")
                    .invalidateHttpSession(true) // 세션에 저장된 사용자 정보 제거
                    // Sesstion : 사용자의 상태를 유지하고 관리하는 객체
                    .logoutSuccessUrl("/");
        }).exceptionHandling((exception) -> exception.accessDeniedPage("/accessDenied"));
        // 권한이 없어서 에러가 발생시

        // http.formLogin();
        // security가 기본제공하는 로그인화면

        http.userDetailsService(userDetailsService);// loadUserByUsername()호출

        return http.build();
        // 해당 설정정보를 반영한 Filter 리턴

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // //사용자 정보
    // @Autowired
    // public void authenticate(AuthenticationManagerBuilder auth) throws Exception
    // {
    // auth.inMemoryAuthentication().withUser("manager")
    // .password("{noop}manager123") //{noop} : 암호화 x
    // .roles("MANAGER"); // 해당 권한 부여

    // auth.inMemoryAuthentication().withUser("admin")
    // .password("{noop}admin123")
    // .roles("ADMIN");
    // }

    // ---GPT---
    // @Bean
    // public InMemoryUserDetailsManager userDetailsService() {
    // UserDetails user = User.withDefaultPasswordEncoder()
    // .username("user")
    // .password("1234")
    // .roles("USER")
    // .build();
    // return new InMemoryUserDetailsManager(user);
    // }

}
