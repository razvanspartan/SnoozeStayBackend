package com.example.snoozestaybackend2.Repos;

import com.example.snoozestaybackend2.api.model.Booking;
import com.example.snoozestaybackend2.api.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.snoozestaybackend2.api.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    @Query("SELECT b FROM Booking b JOIN b.room r JOIN r.hotel h WHERE h.id = :hotelId AND b.user.id = :userId")
    List<Booking> findBookingsByHotelIdAndUserId(@Param("hotelId") int hotelId, @Param("userId") int userId);

}
