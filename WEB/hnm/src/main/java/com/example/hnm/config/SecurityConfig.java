package com.example.hnm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 스프링 설정 객체야!!!
@EnableWebSecurity // 스피링 시큐리티 설정 객체야!!
// @Controller의 Method(Servlet)에서 
// 해당 어노테이션 활성화 -> @Secured, @PreAuthorize
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
    
    @Bean // 해당 메서드의 리턴되는 오브젝트(암호화 객체)를 IoC로 등록.
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http.csrf(AbstractHttpConfigurer::disable); // 개발용 
        http
            // url path에 대한 접근 권한 설정 
            .authorizeHttpRequests(authorize -> authorize
                // 인증(로그인)이 성공했을 때만 접속가능한 사이트 //
                .requestMatchers("/userhome/**").authenticated()
                .requestMatchers("/buylist/**").authenticated() 
                .requestMatchers("/ai/**").authenticated() 
                .requestMatchers("/mypage/**").authenticated() 
                // 로그인시 role -> admin 인경우 관리자 사이트로 이동 // 
                .requestMatchers("/admin/**")
                    .hasAnyAuthority("ADMIN")
                // 그 외의 모든 url은 다 허락함 //
                .anyRequest().permitAll()
            )
            // 인증(로그인)에 대한 세부 설정 // 
            .formLogin(formLogin -> formLogin
                // 로그인 접속 url path 
                .loginPage("/loginpage")
                .loginProcessingUrl("/login")
                // 로그인 후 이동할 url
                .defaultSuccessUrl("/userhome")
                .permitAll()
            );

        return http.build();
    }
}
