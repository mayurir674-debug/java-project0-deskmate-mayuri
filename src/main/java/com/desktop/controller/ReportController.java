package com.desktop.controller;

import java.time.LocalDate;

import com.desktop.model.report.DeskUtilizationRow;
import com.desktop.service.ReportService;
import com.desktop.util.DateUtil;
import com.desktop.util.InputUtil;

public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- Reports ---");
            System.out.println("1. Daily revenue summary (date)");
            System.out.println("2. Desk utilization (date)");
            System.out.println("0. Back");

            int c = InputUtil.readInt("Choose: ");
            switch (c) {
                case 1 -> dailyRevenue();
                case 2 -> deskUtil();
                case 0 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void dailyRevenue() {
        LocalDate d = DateUtil.parseDate(InputUtil.readString("date (yyyy-MM-dd): "));
        var row = reportService.dailyRevenue(d);
        System.out.println("Paid bookings: " + row.getPaidBookings());
        System.out.println("Total revenue: " + row.getTotalRevenue());
    }

    private void deskUtil() {
        LocalDate d = DateUtil.parseDate(InputUtil.readString("date (yyyy-MM-dd): "));
        for (DeskUtilizationRow r : reportService.deskUtilization(d)) {
            System.out.println(r.getDeskCode() + " -> " + r.getPaidBookingsCount());
        }
    }
}

