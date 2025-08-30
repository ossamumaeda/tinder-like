package com.maeda.tinder_like.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maeda.tinder_like.domain.Room.CreateRoomDTO;
import com.maeda.tinder_like.services.RoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("room")
public class RoomController {
    
    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<Object> postMethodName(@RequestBody CreateRoomDTO createRoomDTO) {
        
        return this.roomService.createRoom(createRoomDTO);
    }
    

}
