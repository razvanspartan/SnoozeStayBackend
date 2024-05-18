package com.example.snoozestaybackend2.Repos;

import com.example.snoozestaybackend2.api.model.Room;
import com.example.snoozestaybackend2.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.snoozestaybackend2.api.model.Hotel;
import java.util.List;
public interface RoomRepository extends JpaRepository<Room,Integer> {
    List<Room> findByHotel(Hotel hotel);
}
