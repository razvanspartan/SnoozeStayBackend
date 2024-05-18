package com.example.snoozestaybackend2.Repos;

import com.example.snoozestaybackend2.api.model.Hotel;
import com.example.snoozestaybackend2.api.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findByHotel(Hotel hotel);
    Review findById(int id);
}
