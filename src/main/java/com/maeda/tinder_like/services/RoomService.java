package com.maeda.tinder_like.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maeda.tinder_like.domain.Room.CreateRoomDTO;
import com.maeda.tinder_like.domain.Room.Room;
import com.maeda.tinder_like.domain.Room.UserRoom;
import com.maeda.tinder_like.domain.User.User;
import com.maeda.tinder_like.repositories.RoomRepository;
import com.maeda.tinder_like.repositories.UserRepository;
import com.maeda.tinder_like.repositories.UserRoomRepository;

@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoomRepository userRoomRepository;

    public ResponseEntity<Object> createRoom(CreateRoomDTO createRoomDTO){
        
        // Check if user exists
        User user = userRepository.findById(createRoomDTO.user_id())
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Create new room
        Room room = roomRepository.save(new Room(createRoomDTO.name()));

        // Create UserRoom relation
        UserRoom userRoom = new UserRoom(user,room,"ADMIN");
        this.userRoomRepository.save(userRoom);
        
        return ResponseEntity.ok().body(room);

    }

    // Create list items


}
