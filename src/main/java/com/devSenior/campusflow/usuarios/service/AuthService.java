package com.devSenior.campusflow.usuarios.service;

import com.devSenior.campusflow.common.exception.EmailDuplicadoException;
import com.devSenior.campusflow.common.exception.ResourceNotFoundException;
import com.devSenior.campusflow.security.JwtService;
import com.devSenior.campusflow.usuarios.dto.AuthResponse;
import com.devSenior.campusflow.usuarios.dto.LoginRequest;
import com.devSenior.campusflow.usuarios.dto.RegistroRequest;
import com.devSenior.campusflow.usuarios.model.RolUsuario;
import com.devSenior.campusflow.usuarios.model.Usuario;
import com.devSenior.campusflow.usuarios.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse registrar(RegistroRequest request) {
        usuarioRepository.findByEmail(request.getEmail()).ifPresent(usuarioExistente -> {
            throw new EmailDuplicadoException("Ya existe un usuario registrado con ese email");
        });

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(RolUsuario.ESTUDIANTE);
        usuarioRepository.save(usuario);

        String token = jwtService.generarToken(usuario.getEmail(), usuario.getRol().name());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("No existe un usuario con ese email"));
        String token = jwtService.generarToken(usuario.getEmail(), usuario.getRol().name());
        return new AuthResponse(token);
    }
}