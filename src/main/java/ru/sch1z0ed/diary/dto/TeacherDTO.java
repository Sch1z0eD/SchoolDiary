package ru.sch1z0ed.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String schoolClass;
}
