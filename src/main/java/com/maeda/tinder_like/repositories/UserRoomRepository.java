package com.maeda.tinder_like.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maeda.tinder_like.domain.Room.UserRoom;
import com.maeda.tinder_like.domain.Room.UserRoomId;
import com.maeda.tinder_like.domain.User.User;

public interface UserRoomRepository extends JpaRepository<UserRoom, UserRoomId>{
    
    // If you want to query by the composite key (UserRoomId)
    List<UserRoom> findByIdUserId(Long userId);

    // If you want to query by the relationship to User entity
    List<UserRoom> findByUserId(Long userId);

    // Or even by the User object itself
    List<UserRoom> findByUser(User user);

    Optional<UserRoom> findById(UserRoomId id);
    
}
