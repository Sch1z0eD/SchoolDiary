package ru.sch1z0ed.diary.service;

import java.util.List;

public interface StudentService {
    void initializeTestUsers();

    List<String> getGrades();
}
