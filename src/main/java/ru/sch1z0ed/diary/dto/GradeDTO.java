package ru.sch1z0ed.diary.dto;

import lombok.Data;

@Data
public class GradeDTO {
    private String subjectName;
    private GradeValueEnum gradeValueEnum;
}
