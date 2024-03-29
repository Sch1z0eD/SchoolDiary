package ru.sch1z0ed.diary.service;

import java.util.List;

public interface UserContextService {
    String getLoggedAs();

    Long getLoggedId();

    boolean hasRole(String roleName);

    boolean hasAnyRole(List<String> roleNames);

    boolean isLogged();

    boolean isLoggedAsStudent();

    boolean isLoggedAsSchoolEmployee();

    boolean isLoggedAsAdmin();
}
