package com.desktop.dao;

import java.time.LocalDate;
import java.util.List;

import com.desktop.model.report.DailyRevenueRow;
import com.desktop.model.report.DeskUtilizationRow;

public interface ReportDao {
    DailyRevenueRow dailyRevenue(LocalDate date);
    List<DeskUtilizationRow> deskUtilization(LocalDate date);
}
