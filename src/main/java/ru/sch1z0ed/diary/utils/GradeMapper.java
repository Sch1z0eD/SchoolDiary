package ru.sch1z0ed.diary.utils;

import org.springframework.stereotype.Component;
import ru.sch1z0ed.diary.dto.GradeDTO;
import ru.sch1z0ed.diary.jpa.entities.Grade;

@Component
public class GradeMapper {

    public Grade mapDTOtoGradeEntity(final GradeDTO gradeDTO){
        return Grade.builder()
                .gradeValueEnum(gradeDTO.getGradeValueEnum())
                .subject(gradeDTO.getSubjectName())
                .build();
    }
}
