package com.maeda.tinder_like.domain.Room;

import com.maeda.tinder_like.domain.User.User;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table (name = "userroom")
@Getter
public class UserRoom {
     
    @EmbeddedId
    private UserRoomId id = new UserRoomId();

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("roomId")
    private Room room;

    private String role;

    public UserRoom() {}

    public UserRoom(User user, Room room, String role) {
        this.user = user;
        this.room = room;
        this.role = role;
        this.id = new UserRoomId(user.getId(), room.getId());
    }

}
