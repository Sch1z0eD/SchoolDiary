package ru.sch1z0ed.diary.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sch1z0ed.diary.dto.StudentResponse;
import ru.sch1z0ed.diary.dto.TeacherDTO;
import ru.sch1z0ed.diary.dto.UserRegisterForm;
import ru.sch1z0ed.diary.jpa.entities.Role;
import ru.sch1z0ed.diary.jpa.entities.User;
import ru.sch1z0ed.diary.jpa.repositories.RoleRepository;
import ru.sch1z0ed.diary.jpa.repositories.UserRepository;
import ru.sch1z0ed.diary.service.MailService;
import ru.sch1z0ed.diary.service.UserService;
import ru.sch1z0ed.diary.utils.RandomUserHelper;
import ru.sch1z0ed.diary.utils.RoleEnum;
import ru.sch1z0ed.diary.utils.UserMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MailService mailService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RandomUserHelper randomUserHelper;
    private final UserMapper userMapper;

    public void registerUser(final UserRegisterForm userRegisterForm) {
        final User user = User.builder()
                .firstName(userRegisterForm.getFormName())
                .lastName(userRegisterForm.getFormLastName())
                .email(userRegisterForm.getEmail())
                .passwordHash(passwordEncoder.encode(userRegisterForm.getPassword()))
                .build();
        user.setAddedDate(LocalDateTime.now());
        getORCreateDefaultRole(user);
        userRepository.save(user);
        log.info("User saved: {}", user);
//        mailService.createRegistrationMail(userRegisterForm.getEmail());
    }

    public ResponseEntity<List<StudentResponse>> getUsersList() {
        return ResponseEntity.ok(userRepository
                .findAll()
                .stream()
                .map(userMapper::mapUserToStudentResponse)
                .collect(Collectors.toList()));
    }

    public void saveUser(final User user) {
        userRepository.save(user);
    }

    public void save20users() {
        randomUserHelper.createAndSave20TestUsers();
    }

    public ResponseEntity<StudentResponse> getStudentById(final Long student_id) {
        final Optional<User> byId = userRepository.findById(student_id);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.mapUserToStudentResponse(byId.get()));
    }

    public ResponseEntity<Void> deleteById(final Long long_id) {
        final Optional<User> byId = userRepository.findById(long_id);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(long_id);
        return ResponseEntity.ok().build();
    }

    public boolean checkIfUserExist(final String email) {
        return userRepository
                .findFirstByEmail(email)
                .isPresent();
    }

    protected void getORCreateDefaultRole(final User user) {
        final Role role = roleRepository
                .findByRoleName(RoleEnum.ROLE_USER.toString())
                .orElseGet(() -> roleRepository.save(new Role(RoleEnum.ROLE_USER.toString())));
        user.addRole(role);
    }

    protected void getORCreateDefaultRole(final User user, final RoleEnum roleEnum) {
        final Role role = roleRepository
                .findByRoleName(roleEnum.toString())
                .orElseGet(() -> roleRepository.save(new Role(roleEnum.toString())));
        user.addRole(role);
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public List<TeacherDTO> getSchoolTeachersList() {
        return userRepository.findAll()
                .stream()
                .filter(this::isTeacher)
                .map(userMapper::mapUserToTeacherDTO)
                .collect(Collectors.toList());
    }

    private boolean isTeacher(final User user) {
        return user.getRoleSet().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList())
                .contains("ROLE_TEACHER");
    }

}
