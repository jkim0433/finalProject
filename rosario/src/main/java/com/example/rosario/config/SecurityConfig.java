package com.example.rosario.config;

import com.example.rosario.security.CustomAccessDeniedHandler;
import com.example.rosario.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

private final CustomUserDetailsService customUserDetailsService;

//    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
//        this.customUserDetailsService = customUserDetailsService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        return new CustomAccessDeniedHandler();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/seller/**").hasRole("SELLER")
//                        .requestMatchers("/user/**").hasRole("USER")
//                        .requestMatchers("/api/customers/register").permitAll() // 회원가입 URL 허용
//                        .requestMatchers(PathRequest.toH2Console()).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(httpBasic -> httpBasic.disable()) // HTTP Basic 인증 비활성화 (폼로그인으로 대체)
//                .formLogin(form -> form
//                        .loginPage("/loginPage")    // /login 설정시 문제발생, 회피할것
//                        .loginProcessingUrl("/perform_login")   //미설정시 POST /login 요청을 처리해야 하나 문제발생, 다음처럼 설정할것
//                        .defaultSuccessUrl("/", true)   //로그인 성공 시 이동 경로
//                        .permitAll()
//                )
//
//                //로그아웃은 /logout URL 이 기본값이며 동작함
//                .logout(logout -> logout
//                        .permitAll()
//                )
//                .exceptionHandling(exception -> exception
//                        .accessDeniedHandler(accessDeniedHandler())
//                )
//                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
//                .headers(headers -> headers
//                        //h2 db console 접속 시 클릭재킹 방지를 위해 X-Frame-Options 헤더가 DENY로 설정되어 아래 옵션으로 허용
//                        .frameOptions(frameOptions -> frameOptions.disable()) // 클릭재킹 차단 해제
//                );
//        return http.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().requestMatchers(
//                "/resources/**",
//                "/static/**",
//                "/css/**",
//                "/js/**",
//                "/assets/**",   /*리액트JS 빌드 기본 리소스*/
//                "/images/**",
//                "/error/**",
//                "/",
//                "/index.html",
//                "/favicon.*"
//        );
//    }

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http .cors()
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/seller/**").hasRole("SELLER")
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/api/customers/register").permitAll() // 회원가입 URL 허용
                        .requestMatchers("/api/sellers/register").permitAll() // 회원가입 URL 허용

                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // HTTP Basic 인증 비활성화 (폼로그인으로 대체)
                .formLogin(form -> form
                        .loginPage("http://localhost:5173/loginPage")    // /login 설정시 문제발생, 회피할것
                        .loginProcessingUrl("/perform_login")   //미설정시 POST /login 요청을 처리해야 하나 문제발생, 다음처럼 설정할것
                        .defaultSuccessUrl("/", true)   //로그인 성공 시 이동 경로
                        .permitAll()

                )
                // 로그아웃은 /logout URL 이 기본값이며 동작함
                .logout(logout -> logout
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .headers(headers -> headers
                        // h2 db console 접속 시 클릭재킹 방지를 위해 X-Frame-Options 헤더가 DENY로 설정되어 아래 옵션으로 허용
                        .frameOptions(frameOptions -> frameOptions.disable()) // 클릭재킹 차단 해제
                );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/resources/**",
                "/static/**",
                "/css/**",
                "/js/**",
                "/assets/**", // 리액트JS 빌드 기본 리소스
                "/images/**",
                "/error/**",
                "/",
                "/index.html",
                "/favicon.*"
        );
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173") // 허용할 출처
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 메서드
                        .allowedHeaders("*") // 허용할 헤더
                        .allowCredentials(true) // 인증정보 포함 여부
                        .maxAge(3600); // 캐시 유지 시간
            }
        };
    }
    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        return builder.build();
    }
}
