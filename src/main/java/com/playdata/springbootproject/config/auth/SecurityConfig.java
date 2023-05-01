package com.playdata.springbootproject.config.auth;

import com.playdata.springbootproject.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정들을 활성화시켜준다.
@Configuration      // Spring 설정 클래스라는 의미
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // csrf() 메소드는 CSRF(Cross-Site Request Forgery) 공격 방지를 위한 설정을 합니다.
            // 이 메소드의 disable()을 호출하여 비활성화합니다.
            .csrf().disable()
            // headers() 메소드는 HTTP 응답 헤더를 설정합니다.
            // 이 메소드의 frameOptions().disable()를 호출하여 X-Frame-Options 헤더 설정을 비활성화합니다.
            .headers().frameOptions().disable()
                .and()
                    // URL별 권한 관리를 설정하는 옵션의 시작점
                    .authorizeHttpRequests()
                    // 권한 관리 대상을 지정하는 옵션
                    // URL, HTTP 메소드별로 관리가 가능하다.
                    // "/" 등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 준다.
                    // "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 한다.
                    .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                    .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // 설정된 값들 이외 나머지 URL들을 나타낸다.
                    .anyRequest().authenticated()
                .and()
                    // 로그아웃 기능에 대한 여러 설정의 진입점
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                    .oauth2Login()
                        // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당한다.
                        .userInfoEndpoint()
                            // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다.
                            .userService(customOAuth2UserService);
        return http.build();
    }
}

