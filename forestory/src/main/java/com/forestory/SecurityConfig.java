package com.forestory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.forestory.client.service.CustomOAuth2UserService;
import com.forestory.client.service.CustomUserDetailsServiceImpl;
import com.forestory.custom.CustomAccessDeniedHandler;
import com.forestory.custom.CustomAuthFailureHandler;
import com.forestory.custom.CustomAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
	private final CustomOAuth2UserService customOAuth2UserService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

//		http
//				.csrf((auth) -> auth.disable());
		
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/auth/*", "/resources/**", "/board/**", "/**").permitAll()
//                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated() //다른요청들은().인정되어야한다()
                		);
        
        http
        		.formLogin((auth)-> auth
        				.loginProcessingUrl("/auth/loginProc")
        				.loginPage("/auth/loginPage")
        				.usernameParameter("userEmail")
        				.passwordParameter("userPw")
        				.defaultSuccessUrl("/")
        				.failureHandler(new CustomAuthFailureHandler())
        				.permitAll()
        				);
        
        http
        		.rememberMe((rememberMe)-> rememberMe
        				.rememberMeParameter("remember-me") // default: "remember-me"
    					.tokenValiditySeconds(86400) //ONE_DAY default: 14일
    					.alwaysRemember(false) // default: false
    					.userDetailsService(customUserDetailsServiceImpl)
        				);
        
        http
        		.logout((auth)-> auth
        				.logoutUrl("/logout")
        				.logoutSuccessUrl("/")
        				.deleteCookies("remember-me")
        				.permitAll()
        				);
        
        http	
        		.sessionManagement((session) -> session
        				// 하나의 아이디에 대한 다중 로그인 허용 갯수
        				.maximumSessions(1)
        				
        				/** 다중 로그인 갯수를 초과하였을 경우 처리 방법
        				 	true: 초과시 새로운 로그인 차단
        				 	false: 초과시 기존 세션 하나 삭제 */
        				.maxSessionsPreventsLogin(true)	
        				);
        
        http
        		.sessionManagement((session) -> session
        				.sessionFixation()
        				.changeSessionId()
        				);
        
        http
        		.exceptionHandling((auth) -> auth
        				.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        				.accessDeniedHandler(new CustomAccessDeniedHandler())
        				);
        
        http
        		.oauth2Login((oauth2) -> oauth2
        				.loginPage("/auth/loginPage")
        				.userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
        						.userService(customOAuth2UserService)
        						));
        
        return http.build();
    }
	
}
