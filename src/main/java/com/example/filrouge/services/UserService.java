package com.example.filrouge.services;


import com.example.filrouge.dto.AuthResponse;
import com.example.filrouge.dto.LoginRequest;
import com.example.filrouge.dto.UserDto;
import com.example.filrouge.entities.User;
import com.example.filrouge.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    public AuthResponse register(UserDto request) {
        // Check if user already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setId((int) (Math.random() * 999999999) + 1);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        // Generate token
        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, user.getEmail(), user.getFirstName(), user.getLastName());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getPassword().equals(user.getPassword()) == false) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, user.getEmail(), user.getFirstName(), user.getLastName());
    }
}

