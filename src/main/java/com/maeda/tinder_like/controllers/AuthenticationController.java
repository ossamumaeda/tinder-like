package com.maeda.tinder_like.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maeda.tinder_like.configurations.TokenService;
import com.maeda.tinder_like.domain.User.AuthenticationDTO;
import com.maeda.tinder_like.domain.User.LoginReponseDTO;
import com.maeda.tinder_like.domain.User.RegisterDTO;
import com.maeda.tinder_like.domain.User.User;
import com.maeda.tinder_like.domain.User.UserDetailsDTO;
import com.maeda.tinder_like.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginReponseDTO> postMethodName(@RequestBody AuthenticationDTO authenticationDTO) {
        
        var userPassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(userPassword);
        
        var token = tokenService.generateToken((User) auth.getPrincipal());

        LoginReponseDTO LoginReponseDTO = new LoginReponseDTO(token);

        return ResponseEntity.ok().body(LoginReponseDTO);

    }

    @PostMapping("/register")
    public ResponseEntity<UserDetailsDTO> register(@RequestBody RegisterDTO registerDTO) {
        
        if(this.userRepository.findByLogin(registerDTO.username()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User user = new User(registerDTO.username(),encryptedPassword,registerDTO.role());

        this.userRepository.save(user);
        UserDetailsDTO userdetails = new UserDetailsDTO(user.getUsername());
        return ResponseEntity.ok().body(userdetails);

    }
    
}
