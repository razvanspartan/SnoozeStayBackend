package com.example.snoozestaybackend2.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.example.snoozestaybackend2.Repos.BookingRepository;
import com.example.snoozestaybackend2.Repos.HotelRepository;
import com.example.snoozestaybackend2.Repos.ReviewRepository;
import com.example.snoozestaybackend2.Repos.RoomRepository;
import com.example.snoozestaybackend2.api.model.Booking;
import com.example.snoozestaybackend2.api.model.Hotel;
import com.example.snoozestaybackend2.api.model.Review;
import com.example.snoozestaybackend2.api.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelService {

    RoomRepository roomRepository;
    HotelRepository hotelRepository;
    ReviewRepository reviewRepository;
    BookingRepository bookingRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, ReviewRepository reviewRepository, RoomRepository roomRepository, BookingRepository bookingRepository){
        this.hotelRepository = hotelRepository;
        this.reviewRepository = reviewRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Hotel> getAllHotels(float radius) {
        List<Hotel> allHotels = hotelRepository.findAll();
        double[] hostLocation = getLatLongFromIpAddress(getCurrentIpAddress());
        return allHotels.stream()
                .filter(hotel -> {
                    double distance = calculateDistance((float) hostLocation[0], (float)hostLocation[1], (float)hotel.getLatitude(), (float)hotel.getLongitude());
                    System.out.println("Distance from host to hotel " + hotel.getName() + ": " + distance);
                    return distance <= radius;
                })
                .collect(Collectors.toList());
    }
    public List<Hotel> saveAllHotels(List<Hotel> hotels) {
        return hotelRepository.saveAll(hotels);
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }


    public Hotel getHotelById(int hotelId) {
        return hotelRepository.findById(hotelId).orElse(null);
    }

    public List<Review> getReviewsByHotel(Hotel hotel) {
        return reviewRepository.findByHotel(hotel);
    }
    public Booking saveBooking(Booking booking) {
        Room room = roomRepository.findById(booking.getRoom().getId())
                .orElseThrow(() -> new RuntimeException("Room not found"));


        room.setIsAvailable(false);
        roomRepository.save(room);


        Booking savedBooking = bookingRepository.save(booking);

        return savedBooking;
    }

    public String getCurrentIpAddress() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.ipify.org?format=json";
            Map<String, String> response = restTemplate.getForObject(url, Map.class);
            return response.get("ip");
        } catch (Exception e) {
            e.printStackTrace();
            return "IP address could not be retrieved";
        }
    }
    public double[] getLatLongFromIpAddress(String ipAddress1) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://ip-api.com/json/" +  ipAddress1;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        System.out.println("IP Address: " + ipAddress1);
        System.out.println("API Response: " + response);
        if (response.containsKey("lat") && response.containsKey("lon")) {
            double latitude = (double) response.get("lat");
            double longitude = (double) response.get("lon");
            return new double[]{latitude, longitude};
        } else {
            return new double[]{0, 0};
        }
    }
    public double calculateDistance(float lat1, float lon1, float lat2, float lon2) {
        int earthRadiusKm = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = (float) Math.toRadians(lat1);
        lat2 = (float) Math.toRadians(lat2);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadiusKm * c;
    }

    public List<Room> getRoomsByHotel(int hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        return hotel != null ? hotel.getRooms() : null;
    }
    public List<Booking> getBookingsByHotelIdAndUserId(int hotelId, int userId) {
        return bookingRepository.findBookingsByHotelIdAndUserId(hotelId, userId);
    }
    public void deleteBookingAndUpdateRoom(int bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime checkInTime = booking.getCheckInDate();
        long hoursUntilCheckIn = ChronoUnit.HOURS.between(now, checkInTime);

        if (hoursUntilCheckIn < 2) {
            throw new RuntimeException("Cannot cancel booking less than 2 hours before check-in");
        }

        Room room = booking.getRoom();
        room.setIsAvailable(true);
        roomRepository.save(room);

        bookingRepository.deleteById(bookingId);
    }
}
