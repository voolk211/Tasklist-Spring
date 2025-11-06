package org.example.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tasklist.domain.user.User;
import org.example.tasklist.service.AuthService;
import org.example.tasklist.service.UserService;
import org.example.tasklist.web.dto.auth.JwtRequest;
import org.example.tasklist.web.dto.auth.JwtResponse;
import org.example.tasklist.web.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        try {
            JwtResponse jwtResponse = new JwtResponse();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            User user = userService.getByUsername(loginRequest.getUsername());
            jwtResponse.setUsername(user.getUsername());
            jwtResponse.setId(user.getId());
            jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles()));
            jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()));
            return jwtResponse;
        }
        catch (Exception  e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}
