package com.maeda.tinder_like.domain.Room;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserRoomId implements Serializable {
    
    private Long userId;
    private Long roomId;

    public UserRoomId() {}

    public UserRoomId(Long userId, Long roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }

    // equals() and hashCode() must be implemented!
}