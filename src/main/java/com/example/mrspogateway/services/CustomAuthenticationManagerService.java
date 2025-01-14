package com.example.mrspogateway.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationManagerService implements AuthenticationManager {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("CustomAuthenticationManagerService#authenticate: {}", authentication);
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        log.debug("username: {}", username);
        log.debug("password: {}", password);

        var userDetails = userService.loadUserByUsername(username);
        log.debug("userDetails: {}", userDetails);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            log.debug("password verified");

            var authToken = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
            log.debug("authToken: {}", authToken);
            SecurityContextHolder.getContext().setAuthentication(authToken);
            log.debug("Authentication successfully set in SecurityContext");
            return authToken;
        } else {
            log.debug("password not verified");
            throw new AuthenticationException("Authentication failed") {};
        }
    }

    public boolean authenticate(String username, String password) {
        log.debug("CustomAuthenticationManagerService#authenticate: {}, {}", username, password);
        try {
            var authToken = new UsernamePasswordAuthenticationToken(username, password);
            log.debug("authToken: {}", authToken);

            authenticate(authToken);

            return true;
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user: {}", username, e);
            return false;
        }
    }

    public boolean manualAuthenticate(String username, String password) {
        log.debug("CustomAuthenticationManagerService#manualAuthenticate: username={}, password={}", username, password);
        try {
            var authToken = new UsernamePasswordAuthenticationToken(username, password);
            log.debug("authToken: {}", authToken);
            authenticate(authToken);

            log.debug("SecurityContextHolder.getContext().getAuthentication(); {}", SecurityContextHolder.getContext().getAuthentication());
            log.debug("SecurityContextHolder.getContext().getAuthentication().isAuthenticated(); {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            log.debug("User {} successfully authenticated", username);
            return true;
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user {}", username);
            return false;
        }
    }
}
