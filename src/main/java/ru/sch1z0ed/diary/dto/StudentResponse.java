package ru.sch1z0ed.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sch1z0ed.diary.jpa.entities.Grade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String schoolClass;
    private List<Grade> gradeList = new ArrayList<>();
}

