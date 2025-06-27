package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * Autentica o usuário e gera um token JWT se as credenciais forem válidas.
     *
     * @param username    Nome de usuário
     * @param rawPassword Senha em texto plano
     * @return Token JWT
     * @throws BadCredentialsException Se usuário não existir ou senha estiver incorreta
     */
    public String authenticateUserAndGenerateToken(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado."));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Senha incorreta.");
        }

        return jwtService.generateToken(user.getUsername(), user.getRole().name()); // Convert enum to String
    }

    /**
     * Registra um novo usuário no sistema, com role padrão USER (se não especificado).
     *
     * @param request Objeto contendo username, senha e role (opcional)
     * @throws IllegalArgumentException se o username já estiver em uso ou role inválida
     */
   public void register(RegisterRequest request) {
    if (userRepository.findByUsername(request.getUsername()).isPresent()) {
        throw new IllegalArgumentException("Nome de usuário já está em uso.");
    }

    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));

    // Usa diretamente o enum UserRole vindo do DTO
    UserRole role = request.getRole() != null ? request.getRole() : UserRole.USER;

    user.setRole(role);

    userRepository.save(user);
   }
}


