package com.example.snoozestaybackend2.Repos;

import com.example.snoozestaybackend2.api.model.Room;
import com.example.snoozestaybackend2.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {
}
