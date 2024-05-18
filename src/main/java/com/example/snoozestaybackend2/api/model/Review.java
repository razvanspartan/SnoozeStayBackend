package com.example.snoozestaybackend2.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name="Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int rating;
    private String comment;

    @OneToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    @JsonBackReference(value="hotel-reviews")
    private Hotel hotel;

    public Review(int id, int rating, String comment, User user, Hotel hotel) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.user = user;
        this.hotel = hotel;
    }
    public Review() {

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Hotel getHotel() {
        return hotel;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public void setUserId(int userId) {
        this.user = new User();
        this.user.setId(userId);
    }

    public void setHotelId(int hotelId) {
        this.hotel = new Hotel();
        this.hotel.setId(hotelId);
    }


}
