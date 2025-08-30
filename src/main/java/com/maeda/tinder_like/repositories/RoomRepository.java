package com.maeda.tinder_like.repositories;

import com.maeda.tinder_like.domain.Room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
    
}
 