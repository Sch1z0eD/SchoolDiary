package ru.sch1z0ed.diary.service;

import ru.sch1z0ed.diary.dto.SchoolClassDTO;

import java.util.List;

public interface SchoolClassService {
    void postNewSchoolClass(SchoolClassDTO schoolClassDTO);
    List<SchoolClassDTO> getSchoolClassList();
    void deleteSchoolClassById(Long id);
}
