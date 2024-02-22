package ru.sch1z0ed.diary.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sch1z0ed.diary.dto.GradeDTO;
import ru.sch1z0ed.diary.jpa.entities.Grade;
import ru.sch1z0ed.diary.jpa.entities.User;
import ru.sch1z0ed.diary.jpa.repositories.UserRepository;
import ru.sch1z0ed.diary.service.GradeService;
import ru.sch1z0ed.diary.utils.GradeMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final UserRepository userRepository;
    private final GradeMapper gradeMapper;

    public void addGradeToStudentById(final Long studentId, final GradeDTO gradeDTO) {
        log.info("addGradeToStudentById: \n{}", gradeDTO);
        final User user = userRepository
                .findById(studentId)
                .orElseGet(() -> User.builder()
                        .firstName("Error")
                        .build());
        final Grade grade = gradeMapper.mapDTOtoGradeEntity(gradeDTO);
        grade.setStudent(user.getId());
        user.addToGradeList(grade);
        userRepository.save(user);
    }
}