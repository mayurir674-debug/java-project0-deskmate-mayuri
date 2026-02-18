package com.desktop.util.config;

import com.desktop.controller.DeskController;
import com.desktop.dao.DeskDao;
import com.desktop.dao.implementation.JdbcDeskDao;
import com.desktop.service.DeskService;

public class AppConfig {
	 public DeskController deskController() {
	        DeskDao deskDao = new JdbcDeskDao();
	        DeskService deskService = new DeskService(deskDao);
	        return new DeskController(deskService);

}
}	 
	 
