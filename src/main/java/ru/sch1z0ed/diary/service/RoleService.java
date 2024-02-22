package ru.sch1z0ed.diary.service;

import ru.sch1z0ed.diary.jpa.entities.User;
import ru.sch1z0ed.diary.utils.RoleEnum;

public interface RoleService {
    void getORCreateDefaultRole(User user);

    void getORCreateDefaultRole(User user, RoleEnum roleEnum);
}
