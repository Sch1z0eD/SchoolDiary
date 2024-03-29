package ru.sch1z0ed.diary.rest.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sch1z0ed.diary.dto.GradeDTO;
import ru.sch1z0ed.diary.service.GradeService;

@RestController
@RequestMapping("/rest")
@Slf4j
@RequiredArgsConstructor
public class GradeRestController {

    private final GradeService gradeService;

    @PostMapping("/grade/{studentId}")
    public ResponseEntity<Void> addGradeToStudentById(@PathVariable final Long studentId, @RequestBody final GradeDTO gradeDTO) {
        log.debug("url= /rest/grade/{student}, method=addGradeToStudentById(), STUDENT: " + studentId + " , GRADE: " + gradeDTO);
        gradeService.addGradeToStudentById(studentId, gradeDTO);
        return ResponseEntity.ok().build();
    }
}

