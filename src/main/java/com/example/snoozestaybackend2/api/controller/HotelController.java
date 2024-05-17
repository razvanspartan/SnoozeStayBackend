package com.example.snoozestaybackend2.api.controller;

import com.example.snoozestaybackend2.api.model.Hotel;
import com.example.snoozestaybackend2.api.model.Review;
import com.example.snoozestaybackend2.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/saveHotel")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.saveHotel(hotel);
    }

    @PostMapping("/saveReview")
    public Hotel addReview(@RequestBody Review review) {
        return hotelService.saveReview(review);
    }
    @GetMapping("/getReviews/{hotelId}")
    public ResponseEntity<?> getReviews(@PathVariable int hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotelService.getReviewsByHotel(hotel));
    }
    @GetMapping("/withinRadius")
    public List<Hotel> getAllHotels(@RequestParam float radius){
        return hotelService.getAllHotels(radius);
    }

}
