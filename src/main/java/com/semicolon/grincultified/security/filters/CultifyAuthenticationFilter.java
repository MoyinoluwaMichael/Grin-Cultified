package com.semicolon.grincultified.security.filters;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.grincultified.data.models.Role;
import com.semicolon.grincultified.dtos.requests.LoginRequest;
import com.semicolon.grincultified.dtos.responses.AdminResponse;
import com.semicolon.grincultified.dtos.responses.UserResponse;
import com.semicolon.grincultified.exception.UserNotFoundException;
import com.semicolon.grincultified.services.adminService.AdminService;
import com.semicolon.grincultified.services.investorService.InvestorService;
import com.semicolon.grincultified.services.investorService.InvestorServiceImpl;
import com.semicolon.grincultified.services.userService.UserService;
import com.semicolon.grincultified.utilities.JwtUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.semicolon.grincultified.utilities.AppUtils.*;
import static java.time.Instant.now;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@AllArgsConstructor
public class CultifyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtil;

    private final InvestorService investorService;
    private final AdminService adminService;
    private final UserService userService;
    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email=EMPTY_SPACE_VALUE;
        try {
            LoginRequest loginRequest = mapper.readValue(request.getInputStream(), LoginRequest.class);
            email = loginRequest.getEmail();
            String password = loginRequest.getPassword();
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
            System.out.println(email);
            Authentication authResult = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            return authResult;
        }catch (IOException exception){
            throw new BadCredentialsException(String.format(AUTHENTICATION_FAILED_FOR_USER_WITH_EMAIL, email));
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        String accessToken = generateAccessToken(authResult.getAuthorities());
        authResult.getDetails();
        Map<String, Object> responseData = new HashMap<>();
        responseData.put(ACCESS_TOKEN_VALUE, accessToken);
        String email = (String) authResult.getPrincipal();
        UserResponse foundUser;
        try {
            foundUser = userService.findByEmail(email);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (foundUser.getRoles().contains(Role.INVESTOR)){
            responseData.put(USER, investorService.findByEmail(email));
        }else {
            AdminResponse admin = adminService.findByEmail(email);
            responseData.put(USER, admin);
        }
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write(mapper.writeValueAsBytes(
                responseData
        ));
    }


    private String generateAccessToken(Collection<? extends GrantedAuthority> authorities){
        Map<String, String> map = new HashMap<>();
        int count = 1;
        for (GrantedAuthority authority:authorities) {
            map.put(CLAIM_VALUE+count, authority.getAuthority());
            count++;
        }
        return JWT.create()
                .withIssuedAt(now())
                .withExpiresAt(now().plusSeconds(12000L))
                .withClaim(ROLES_VALUE, map)
                .sign(Algorithm.HMAC512(jwtUtil.getSecret().getBytes()));
    }


}
