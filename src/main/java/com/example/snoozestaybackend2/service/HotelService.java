package com.example.snoozestaybackend2.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import com.example.snoozestaybackend2.Repos.HotelRepository;
import com.example.snoozestaybackend2.Repos.ReviewRepository;
import com.example.snoozestaybackend2.api.model.Hotel;
import com.example.snoozestaybackend2.api.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelService {


    HotelRepository hotelRepository;
    ReviewRepository reviewRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, ReviewRepository reviewRepository) {
        this.hotelRepository = hotelRepository;
        this.reviewRepository = reviewRepository;
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

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel saveReview(Review review) {
        return reviewRepository.save(review).getHotel();
    }

    public Hotel getHotelById(int hotelId) {
        return hotelRepository.findById(hotelId).orElse(null);
    }

    public List<Review> getReviewsByHotel(Hotel hotel) {
        return reviewRepository.findByHotel(hotel);
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
}
