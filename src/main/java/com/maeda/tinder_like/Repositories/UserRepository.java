package com.maeda.tinder_like.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.maeda.tinder_like.Domain.User.User;


public interface UserRepository extends JpaRepository<User,Long>{
    UserDetails findByLogin(String login);
}
