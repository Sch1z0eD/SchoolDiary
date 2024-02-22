package ru.sch1z0ed.diary.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sch1z0ed.diary.dto.GradeValueEnum;
import ru.sch1z0ed.diary.jpa.entities.SchoolClass;
import ru.sch1z0ed.diary.jpa.entities.User;
import ru.sch1z0ed.diary.jpa.repositories.SchoolClassRepository;
import ru.sch1z0ed.diary.jpa.repositories.UserRepository;
import ru.sch1z0ed.diary.service.RoleService;
import ru.sch1z0ed.diary.service.StudentService;
import ru.sch1z0ed.diary.service.UserService;
import ru.sch1z0ed.diary.utils.RoleEnum;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final SchoolClassRepository schoolClassRepository;
    private final UserService userService;

    @PostConstruct
    public void initializeTestUsers() {
        try {
            final User admin = User.builder()
                    .firstName("Admin")
                    .lastName("Admin")
                    .email("admin@gmail.com")
                    .passwordHash(passwordEncoder.encode("admin"))
                    .build();
            roleService.getORCreateDefaultRole(admin, RoleEnum.ROLE_USER);
            roleService.getORCreateDefaultRole(admin, RoleEnum.ROLE_ADMIN);

            final User teacher = User.builder()
                    .firstName("Teacher")
                    .lastName("Teacher")
                    .email("teacher@gmail.com")
                    .passwordHash(passwordEncoder.encode("teacher"))
                    .build();


            final User teacher2 = User.builder()
                    .firstName("Teacher2")
                    .lastName("Teacher2")
                    .email("Teacher2@gmail.com")
                    .passwordHash(passwordEncoder.encode("Teacher2"))
                    .build();

            roleService.getORCreateDefaultRole(teacher, RoleEnum.ROLE_USER);
            roleService.getORCreateDefaultRole(teacher, RoleEnum.ROLE_TEACHER);

            roleService.getORCreateDefaultRole(teacher2, RoleEnum.ROLE_USER);
            roleService.getORCreateDefaultRole(teacher2, RoleEnum.ROLE_TEACHER);

            SchoolClass schoolClass1 = SchoolClass.builder()
                    .schoolClassName("3-B")
                    .addedDate(LocalDateTime.now())
                    .classTeacher(teacher)
                    .build();

            SchoolClass schoolClass2 = SchoolClass.builder()
                    .schoolClassName("1-A")
                    .addedDate(LocalDateTime.now())
                    .classTeacher(teacher)
                    .build();

            userRepository.saveAll(List.of(admin, teacher, teacher2));
            schoolClassRepository.saveAll(List.of(schoolClass1, schoolClass2));
            userService.save20users();
            log.info("\n test users created");
        } catch (RuntimeException ex) {
            log.error("Error during user creation {}", ex.getMessage());
        }
    }

    public List<String> getGrades() {
        return Arrays.stream(GradeValueEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
