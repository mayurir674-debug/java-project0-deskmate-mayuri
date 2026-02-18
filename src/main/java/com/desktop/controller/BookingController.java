package com.desktop.controller;

import java.time.LocalDateTime;

import com.desktop.constants.PaymentMode;
import com.desktop.service.BookingService;
import com.desktop.util.DateUtil;
import com.desktop.util.InputUtil;
import com.desktop.util.MoneyUtil;

public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- Bookings ---");
            System.out.println("1. Create booking + payment (transaction)");
            System.out.println("0. Back");

            int c = InputUtil.readInt("Choose: ");
            switch (c) {
                case 1 -> createBooking();
                case 0 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void createBooking() {
        String deskCode = InputUtil.readString("deskCode: ");
        String phone = InputUtil.readString("customerPhone (10 digits): ");
        LocalDateTime start = DateUtil.parseDateTime(InputUtil.readString("slotStart (yyyy-MM-dd HH:mm): "));
        LocalDateTime end = DateUtil.parseDateTime(InputUtil.readString("slotEnd   (yyyy-MM-dd HH:mm): "));
        var total = MoneyUtil.parse(InputUtil.readString("totalAmount: "));

        System.out.println("Payment mode: 1) CASH  2) CARD  3) UPI");
        int pm = InputUtil.readInt("Choose: ");
        PaymentMode mode = switch (pm) {
            case 1 -> PaymentMode.CASH;
            case 2 -> PaymentMode.CARD;
            case 3 -> PaymentMode.UPI;
            default -> throw new IllegalArgumentException("Invalid payment mode");
        };

        var paid = MoneyUtil.parse(InputUtil.readString("paidAmount (must match total): "));
        long bookingId = bookingService.createBookingWithPayment(deskCode, phone, start, end, total, mode, paid);
        System.out.println("Booking created and PAID. bookingId=" + bookingId);
    }
}