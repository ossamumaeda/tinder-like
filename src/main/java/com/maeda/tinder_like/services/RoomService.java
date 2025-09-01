package com.maeda.tinder_like.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.maeda.tinder_like.configurations.TokenService;
import com.maeda.tinder_like.domain.Room.CreateRoomDTO;
import com.maeda.tinder_like.domain.Room.InviteUserDTO;
import com.maeda.tinder_like.domain.Room.Room;
import com.maeda.tinder_like.domain.Room.UserRoom;
import com.maeda.tinder_like.domain.Room.UserRoomId;
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

    public ResponseEntity<Object> inviteUser(InviteUserDTO inviteUserDTO){

        User invitedUser = userRepository.findById(inviteUserDTO.user_id())
            .orElseThrow(() -> new RuntimeException("User not found"));

        Room room = roomRepository.findById(inviteUserDTO.room_id())
            .orElseThrow(() -> new RuntimeException("User not found"));

                    
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) auth.getPrincipal();
        Long user_id = principal.getId();

        User user = userRepository.findById(user_id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if user is a ADMIN in the room
        UserRoomId id = new UserRoomId(user.getId(), inviteUserDTO.room_id());

        UserRoom userRoom = userRoomRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if(userRoom.getRole().equals("ADMIN") == false){
            return ResponseEntity.badRequest().build();
        }
        
        // Add invited user to the room
        UserRoom newUserRoom = new UserRoom(invitedUser,room,"PARTICIPANT");
        this.userRoomRepository.save(userRoom);

        return ResponseEntity.ok().body(newUserRoom);

    }

    // Create list items


}
