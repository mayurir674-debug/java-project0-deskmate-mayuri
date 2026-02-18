package com.desktop.dao;
import java.sql.Connection;
import java.util.Optional;

import com.desktop.model.Payment;

public interface PaymentDao {
    long insertPayment(Connection conn, Payment payment);
    Optional<Payment> findByBookingId(long bookingId);
}