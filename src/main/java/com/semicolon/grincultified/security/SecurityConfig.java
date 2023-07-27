package com.semicolon.grincultified.security;

import com.semicolon.grincultified.security.filters.CultifyAuthenticationFilter;
import com.semicolon.grincultified.security.filters.CultifyAuthorizationFilter;
import com.semicolon.grincultified.services.adminService.AdminService;
import com.semicolon.grincultified.services.investorService.InvestorService;
import com.semicolon.grincultified.services.userService.UserService;
import com.semicolon.grincultified.utilities.JwtUtility;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.semicolon.grincultified.data.models.Role.ORDINARY_ADMIN;
import static com.semicolon.grincultified.data.models.Role.SUPER_ADMIN;
import static com.semicolon.grincultified.utilities.AppUtils.*;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
  private final AuthenticationManager authenticationManager;
  private final JwtUtility jwtUtil;
  private final InvestorService investorService;
  private final AdminService adminService;
  private final UserService userService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      UsernamePasswordAuthenticationFilter authenticationFilter = new CultifyAuthenticationFilter(authenticationManager, jwtUtil, investorService, adminService, userService);
      authenticationFilter.setFilterProcessesUrl(LOGIN_ENDPOINT);
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new CultifyAuthorizationFilter(jwtUtil), CultifyAuthenticationFilter.class)
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(c->c.requestMatchers(POST, INVESTOR_INITIAL_REGISTRATION_API_VALUE)
                        .permitAll())
                .authorizeHttpRequests(c->c.requestMatchers(POST, ADMIN_REGISTRATION_API_VALUE)
                        .permitAll())
                .authorizeHttpRequests(c->c.requestMatchers(POST, COMPLETE_REGISTRATION_ENDPOINT)
                        .permitAll())
                .authorizeHttpRequests(c->c.requestMatchers(POST, LOGIN_ENDPOINT)
                        .permitAll())
                .authorizeHttpRequests(c->c.requestMatchers(POST, SEND_RESET_PASSWORD_LINK_ENDPOINT)
                        .permitAll())
                .authorizeHttpRequests(c->c.requestMatchers(POST, UPDATE_PASSWORD_ENDPOINT)
                        .permitAll())
                .authorizeHttpRequests(c->c.requestMatchers(POST, ADMIN_ENDPOINTS)
                        .hasAnyRole(ORDINARY_ADMIN.name(), SUPER_ADMIN.name()))
                .authorizeHttpRequests(c->c.anyRequest().authenticated())
                .build();
    }
}
