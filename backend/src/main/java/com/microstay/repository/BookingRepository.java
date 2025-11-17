package com.microstay.repository;

import com.microstay.model.Booking;
import com.microstay.model.Room;
import com.microstay.model.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select b from Booking b where b.room = :room and b.status <> com.microstay.model.enums.BookingStatus.CANCELLED and b.startTime < :end and b.endTime > :start")
    List<Booking> findOverlapping(@Param("room") Room room,
                                  @Param("start") OffsetDateTime start,
                                  @Param("end") OffsetDateTime end);

    List<Booking> findByStatus(BookingStatus status);
}



