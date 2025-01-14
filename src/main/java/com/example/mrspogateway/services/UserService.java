package com.example.mrspogateway.services;

import com.example.mrspogateway.dto.requests.RegistrationUserRequestDto;
import com.example.mrspogateway.entities.UserEntity;
import com.example.mrspogateway.exceptions.UserAlreadyExistsException;
import com.example.mrspogateway.exceptions.UserNotFoundException;
import com.example.mrspogateway.exceptions.UserUnauthorizedException;
import com.example.mrspogateway.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Пользователь с таким именем не найден"));
    }


    private UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Пользователь с таким именем не найден"));
    }

    @Transactional
    public UserDetails createUser(RegistrationUserRequestDto registrationUserRequestDto) {
        if (userRepository.existsByUsername(registrationUserRequestDto.getUsername()))
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");

        UserEntity userEntity = UserEntity.builder()
                .username(registrationUserRequestDto.getUsername())
                .password(passwordEncoder.encode(registrationUserRequestDto.getPassword()))
                .authorities("ROLE_USER")
                .build();

        return userRepository.save(userEntity);
    }

    public UserEntity currentUser() {
        log.debug("UserService#currentUser");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("authentication: {}", authentication);
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserUnauthorizedException("Пользователь не аутентифицирован");
        }

        return findByUsername(authentication.getName());
    }

    public Long currentUserId() {
        log.debug("UserService#currentUserId");
        return currentUser().getId();
    }

    public Collection<UserEntity> allUsers() {
        log.debug("UserService#allUsers");
        return userRepository.findAll();
    }

}
