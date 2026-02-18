package com.deskmate.service.fakes;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import com.desktop.constants.BookingStatus;
import com.desktop.dao.BookingDao;
import com.desktop.dao.DeskDao;
import com.desktop.dao.PaymentDao;
import com.desktop.model.Desk;
import com.desktop.model.Payment;

public class FakeDaos {

    public static class FakeDeskDao implements DeskDao {
        private final Map<String, Desk> byCode = new HashMap<>();
        private final AtomicLong ids = new AtomicLong(1);

        public FakeDeskDao seedDesk(String code, boolean active) {
            byCode.put(code, new Desk(ids.getAndIncrement(), code, code, active, LocalDateTime.now()));
            return this;
        }

        @Override public long insertDesk(String code, String name) { return 1; }
        @Override public void deactivateDesk(long deskId) { }
        @Override public Optional<Desk> findByCode(String code) { return Optional.ofNullable(byCode.get(code)); }
        @Override public Optional<Desk> findById(long deskId) { return Optional.empty(); }
        @Override public List<Desk> listActive() { return byCode.values().stream().filter(Desk::isActive).toList(); }
    }

    public static class FakeBookingDao implements BookingDao {
        private final Set<String> unique = new HashSet<>();
        private final AtomicLong ids = new AtomicLong(10);
        private BookingStatus lastStatus;

        @Override
        public long insertBooking(Connection conn, long deskId, String phone, LocalDateTime start, LocalDateTime end,
                                  BigDecimal total, BookingStatus status) {
            String key = deskId + "|" + start.toString();
            if (!unique.add(key)) throw new RuntimeException("UNIQUE_VIOLATION");
            lastStatus = status;
            return ids.getAndIncrement();
        }

        @Override public void updateStatus(Connection conn, long bookingId, BookingStatus status) { lastStatus = status; }
        @Override public Optional<com.desktop.model.Booking> findById(long bookingId) { return Optional.empty(); }

        public BookingStatus lastStatus() { return lastStatus; }
    }

    public static class FakePaymentDao implements PaymentDao {
        @Override public long insertPayment(Connection conn, Payment payment) { return 1; }
        @Override public Optional<Payment> findByBookingId(long bookingId) { return Optional.empty(); }
    }
}
