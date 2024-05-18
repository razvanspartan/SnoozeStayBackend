package com.example.snoozestaybackend2.api.controller;

import com.example.snoozestaybackend2.api.model.Booking;
import com.example.snoozestaybackend2.api.model.Hotel;
import com.example.snoozestaybackend2.api.model.Review;
import com.example.snoozestaybackend2.api.model.Room;
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

    @PostMapping("/saveAllHotels")
        public List<Hotel> addAllHotels (@RequestBody List < Hotel > hotels) {
            return hotelService.saveAllHotels(hotels);
        }

        @PostMapping("/saveReview")
        public Review addReview (@RequestBody Review review){
            return hotelService.saveReview(review);
        }
        @GetMapping("/getReviews/{hotelId}")
        public ResponseEntity<?> getReviews ( @PathVariable int hotelId){
            Hotel hotel = hotelService.getHotelById(hotelId);
            return ResponseEntity.ok(hotelService.getReviewsByHotel(hotel));
        }
        @GetMapping("/withinRadius")
        public List<Hotel> getAllHotels ( @RequestParam float radius){
            return hotelService.getAllHotels(radius);
        }

        @PostMapping("/bookRoom")
        public ResponseEntity<Booking> bookRoom (@RequestBody Booking booking){
            Booking savedBooking = hotelService.saveBooking(booking);
            return ResponseEntity.ok(savedBooking);
        }
    @GetMapping("/getRooms/{hotelId}")
    public ResponseEntity<List<Room>> getRoomsByHotel(@PathVariable int hotelId) {
        List<Room> rooms = hotelService.getRoomsByHotel(hotelId);
        return ResponseEntity.ok(rooms);
    }
    @GetMapping("/{hotelId}/bookings")
    public ResponseEntity<List<Booking>> getBookingsByHotelIdAndUserId(@PathVariable int hotelId, @RequestParam int userId) {
        List<Booking> bookings = hotelService.getBookingsByHotelIdAndUserId(hotelId, userId);
        return ResponseEntity.ok(bookings);
    }
    @DeleteMapping("/bookings/{bookingId}")
    public void deleteBooking(@PathVariable int bookingId) {
        hotelService.deleteBookingAndUpdateRoom(bookingId);
    }

}
