package com.example.snoozestaybackend2.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Hotel")
public class Hotel {
    @Id
    private int id;

    private String name;
    private float latitude;
    private float longitude;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="hotel-rooms")
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotel")
    @JsonManagedReference(value="hotel-reviews")
    private List<Review> reviews;

    public Hotel(int id, String name, float latitude, float longitude, List<Review> reviews, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rooms = rooms;
        this.reviews = reviews;
    }
    public Hotel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getLatitude() {
        return latitude;
    }
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    public float getLongitude() {
        return longitude;
    }
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
        for (Room room : rooms) {
            if (room.getHotel() == null || !room.getHotel().equals(this)) {
                room.setHotel(this);
            }
        }
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public void setReviews(List<Review> reviews){
        this.reviews = reviews;
        for (Review review : reviews) {
            if (review.getHotel() == null || !review.getHotel().equals(this)) {
                review.setHotel(this);
            }
        }
    }
    public List<Review> getReviews(){
        return reviews;
    }


}
