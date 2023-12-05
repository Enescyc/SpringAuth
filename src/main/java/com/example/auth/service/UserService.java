package com.example.auth.service;

import com.example.auth.dto.CreateUserRequest;
import com.example.auth.model.Role;
import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User loadUserByUsername(String username) {
        Optional<User> user = this.getByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }

    private Optional<User> getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }




    public User createUser(CreateUserRequest request) {

        User user = User.builder()
                .username("enes")
                .password(passwordEncoder.encode("1234"))
                .authorities(Set.of(Role.ROLE_USER))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .build();
        return this.userRepository.save(user);
    }

}
