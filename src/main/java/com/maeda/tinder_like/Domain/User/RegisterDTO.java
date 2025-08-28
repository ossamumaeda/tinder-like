package com.maeda.tinder_like.Domain.User;

public record RegisterDTO(

    String username,
    String password,
    UserRole role

) {
    
}
