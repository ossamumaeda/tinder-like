package com.maeda.tinder_like.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maeda.tinder_like.Configurations.TokenService;
import com.maeda.tinder_like.Domain.User.AuthenticationDTO;
import com.maeda.tinder_like.Domain.User.LoginReponseDTO;
import com.maeda.tinder_like.Domain.User.RegisterDTO;
import com.maeda.tinder_like.Domain.User.User;
import com.maeda.tinder_like.Repositories.UserRepository;

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
    public ResponseEntity<Object> postMethodName(@RequestBody AuthenticationDTO authenticationDTO) {
        
        var userPassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(userPassword);
        
        var token = tokenService.generateToken((User) auth.getPrincipal());

        LoginReponseDTO LoginReponseDTO = new LoginReponseDTO(token);

        return ResponseEntity.ok().body(LoginReponseDTO);

    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDTO registerDTO) {
        
        if(this.userRepository.findByLogin(registerDTO.username()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User user = new User(registerDTO.username(),encryptedPassword,registerDTO.role());

        this.userRepository.save(user);

        return ResponseEntity.ok().build();

    }
    
    

}
