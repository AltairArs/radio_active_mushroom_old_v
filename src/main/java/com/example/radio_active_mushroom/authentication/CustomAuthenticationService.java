package com.example.radio_active_mushroom.authentication;

import com.example.radio_active_mushroom.models.UserEntity;
import com.example.radio_active_mushroom.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder(5);};

    public CustomAuthenticationToken authenticate(CustomAuthenticationToken authentication) {
        Optional<UserEntity> user = userRepository.findByEmail(authentication.getEmail());
        if (!user.isPresent()) {
            user = userRepository.findByUsername(authentication.getUsername());
        }
        if (user.isPresent()) {
            log.info("[AUTHENTICATION] User found");
            if (passwordEncoder().matches(authentication.getPassword(), user.get().getPassword())) {
                log.info("[AUTHENTICATION] Password confirmed");
                return new CustomAuthenticationToken(modelMapper.map(user.get(), CustomUserDetails.class));
            }
            log.error("[AUTHENTICATION] Wrong password");
        }
        log.error("[AUTHENTICATION] User not found");
        return authentication;
    }
}
