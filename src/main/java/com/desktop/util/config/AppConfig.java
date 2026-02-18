package com.desktop.util.config;

import com.desktop.controller.BookingController;
import com.desktop.controller.DeskController;

import com.desktop.controller.ReportController;
import com.desktop.dao.BookingDao;
import com.desktop.dao.DeskDao;
import com.desktop.dao.PaymentDao;
import com.desktop.dao.ReportDao;
import com.desktop.dao.implementation.JdbcBookingDao;
import com.desktop.dao.implementation.JdbcDeskDao;
import com.desktop.dao.implementation.JdbcPaymentDao;
import com.desktop.dao.implementation.JdbcReportDao;

import com.desktop.dao.BookingDao;
import com.desktop.dao.DeskDao;
import com.desktop.dao.PaymentDao;
import com.desktop.dao.implementation.JdbcBookingDao;
import com.desktop.dao.implementation.JdbcDeskDao;
import com.desktop.dao.implementation.JdbcPaymentDao;

import com.desktop.service.BookingService;
import com.desktop.service.DeskService;
import com.desktop.service.ReportService;

public class AppConfig {
	 public DeskController deskController() {
	        DeskDao deskDao = new JdbcDeskDao();
	        DeskService deskService = new DeskService(deskDao);
	        return new DeskController(deskService);

	        
}
	 public BookingController bookingController() {
	        DeskDao deskDao = new JdbcDeskDao();
	        BookingDao bookingDao = new JdbcBookingDao();
	        PaymentDao paymentDao = new JdbcPaymentDao();
	        BookingService bookingService = new BookingService(deskDao, bookingDao, paymentDao);
	        return new BookingController(bookingService);
	    }

	  public ReportController reportController() {
	        ReportDao reportDao = new JdbcReportDao();
	        ReportService reportService = new ReportService(reportDao);
	        return new ReportController(reportService);
	    }

}	 
	 
