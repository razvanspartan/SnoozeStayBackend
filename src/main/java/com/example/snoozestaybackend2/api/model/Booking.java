package com.example.snoozestaybackend2.api.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name="Booking")
public class Booking {
    @Id
    private int id;

    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    public Booking(int id, LocalDateTime checkInDate, LocalDateTime checkOutDate, User user, Room room) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.user = user;
        this.room = room;
    }

    public Booking() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }
    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }



}
