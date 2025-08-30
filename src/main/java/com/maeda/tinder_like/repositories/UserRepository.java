package com.maeda.tinder_like.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.maeda.tinder_like.domain.User.User;


public interface UserRepository extends JpaRepository<User,Long>{
    UserDetails findByLogin(String login);
}
