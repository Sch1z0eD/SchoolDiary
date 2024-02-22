package ru.sch1z0ed.diary.service;

import org.springframework.http.ResponseEntity;
import ru.sch1z0ed.diary.dto.StudentResponse;
import ru.sch1z0ed.diary.dto.TeacherDTO;
import ru.sch1z0ed.diary.dto.UserRegisterForm;
import ru.sch1z0ed.diary.jpa.entities.User;

import java.util.List;

public interface UserService {

    void registerUser(UserRegisterForm userRegisterForm);
    ResponseEntity<List<StudentResponse>> getUsersList();
    void saveUser(User user);
    void save20users();
    ResponseEntity<StudentResponse> getStudentById(Long student_id);
    ResponseEntity deleteById(Long long_id);
    boolean checkIfUserExist(String email);
    List<User> getUserList();
    List<TeacherDTO> getSchoolTeachersList();
}
