package com.example.snoozestaybackend2.Repos;
import com.example.snoozestaybackend2.api.model.Hotel;
import com.example.snoozestaybackend2.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {

}
