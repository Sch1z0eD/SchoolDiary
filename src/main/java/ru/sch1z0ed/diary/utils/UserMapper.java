package ru.sch1z0ed.diary.utils;

import org.springframework.stereotype.Component;
import ru.sch1z0ed.diary.dto.StudentResponse;
import ru.sch1z0ed.diary.dto.TeacherDTO;
import ru.sch1z0ed.diary.jpa.entities.User;

@Component
public class UserMapper {
    public StudentResponse mapUserToStudentResponse(final User user){
        return StudentResponse.builder()
                .id(user.getId())
                .birthDate(user.getBirthDate())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .schoolClass(user.getSchoolClass().getSchoolClassName())
                .gradeList(user.getGradeList())
                .email(user.getEmail())
                .build();
    }

    public TeacherDTO mapUserToTeacherDTO(final User user){
        return TeacherDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .schoolClass(user.getSchoolClass().getSchoolClassName())
                .build();
    }
}
