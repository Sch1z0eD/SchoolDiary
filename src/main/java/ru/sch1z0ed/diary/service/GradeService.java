package ru.sch1z0ed.diary.service;

import ru.sch1z0ed.diary.dto.GradeDTO;

public interface GradeService {
    void addGradeToStudentById(final Long studentId, final GradeDTO gradeDTO);
}
