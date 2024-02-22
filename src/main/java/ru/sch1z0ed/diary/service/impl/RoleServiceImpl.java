package ru.sch1z0ed.diary.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sch1z0ed.diary.jpa.entities.Role;
import ru.sch1z0ed.diary.jpa.entities.User;
import ru.sch1z0ed.diary.jpa.repositories.RoleRepository;
import ru.sch1z0ed.diary.service.RoleService;
import ru.sch1z0ed.diary.utils.RoleEnum;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public void getORCreateDefaultRole(final User user) {
        final Role role = roleRepository
                .findByRoleName(RoleEnum.ROLE_USER.toString())
                .orElseGet(() -> roleRepository.save(new Role(RoleEnum.ROLE_USER.toString())));
        user.addRole(role);
    }

    public void getORCreateDefaultRole(final User user,final RoleEnum roleEnum) {
        final Role role = roleRepository
                .findByRoleName(roleEnum.toString())
                .orElseGet(() -> roleRepository.save(new Role(roleEnum.toString())));
        user.addRole(role);
    }
}
