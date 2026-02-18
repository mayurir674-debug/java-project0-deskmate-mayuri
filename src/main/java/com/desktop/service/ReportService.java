package com.desktop.service;

import java.time.LocalDate;
import java.util.List;

import com.desktop.dao.ReportDao;
import com.desktop.model.report.DailyRevenueRow;
import com.desktop.model.report.DeskUtilizationRow;

public class ReportService {
    private final ReportDao reportDao;

    public ReportService(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    public DailyRevenueRow dailyRevenue(LocalDate date) {
        return reportDao.dailyRevenue(date);
    }

    public List<DeskUtilizationRow> deskUtilization(LocalDate date) {
        return reportDao.deskUtilization(date);
    }
}

