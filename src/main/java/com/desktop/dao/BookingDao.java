package com.desktop.dao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Optional;

import com.desktop.constants.BookingStatus;
import com.desktop.model.Booking;

public interface BookingDao {
    long insertBooking(Connection conn, long deskId, String phone, LocalDateTime start, LocalDateTime end,
                       BigDecimal total, BookingStatus status);

    void updateStatus(Connection conn, long bookingId, BookingStatus status);
    Optional<Booking> findById(long bookingId);
}
