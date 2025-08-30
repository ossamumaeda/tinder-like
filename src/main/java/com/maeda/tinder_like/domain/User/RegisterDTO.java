package com.maeda.tinder_like.domain.User;

public record RegisterDTO(

    String username,
    String password,
    UserRole role

) {
    
}
