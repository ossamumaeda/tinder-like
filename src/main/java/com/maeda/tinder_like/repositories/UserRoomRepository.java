package com.maeda.tinder_like.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maeda.tinder_like.domain.Room.UserRoom;
import com.maeda.tinder_like.domain.Room.UserRoomId;

public interface UserRoomRepository extends JpaRepository<UserRoom, UserRoomId>{
    
}
