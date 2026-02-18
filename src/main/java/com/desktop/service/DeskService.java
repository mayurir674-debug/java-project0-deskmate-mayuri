package com.desktop.service;


import java.util.List;

import com.desktop.dao.DeskDao;
import com.desktop.model.Desk;
import com.desktop.util.ValidationUtil;
import com.util.exceptions.EntityNotFoundException;
import com.util.exceptions.ValidationException;

public class DeskService {
    private final DeskDao deskDao;

    public DeskService(DeskDao deskDao) {
        this.deskDao = deskDao;
    }

    public long addDesk(String code, String name) {
        ValidationUtil.requireNonBlank(code, "deskCode");
        ValidationUtil.requireNonBlank(name, "name");

        if (deskDao.findByCode(code).isPresent()) {
            throw new ValidationException("Desk code already exists: " + code);
        }
        return deskDao.insertDesk(code, name);
    }

    public void deactivateDesk(String code) {
        Desk d = deskDao.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Desk not found: " + code));
        deskDao.deactivateDesk(d.getDeskId());
    }

    public List<Desk> listActive() {
        return deskDao.listActive();
    }

    public Desk getByCodeOrThrow(String code) {
        return deskDao.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Desk not found: " + code));
    }
}