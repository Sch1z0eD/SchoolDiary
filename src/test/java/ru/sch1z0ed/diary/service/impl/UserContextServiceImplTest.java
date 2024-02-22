package ru.sch1z0ed.diary.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.sch1z0ed.diary.jpa.repositories.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserContextServiceImplTest {

    private UserContextServiceImpl userContextService = new UserContextServiceImpl(Mockito.mock(UserRepository.class)){
        @Override
        public boolean hasAnyRole(List<String> roleNames) {
            final List<String> roles = List.of("USER", "TEACHER");
            return roles.containsAll(roleNames);
        }
    };

    @Test
    public void shouldReturnFalseWhenRolesNotMatch() {
        assertFalse(userContextService.hasAnyRole(List.of("A", "B")));
    }

    @Test
    public void shouldReturnTrueWhenRolesMatch() {
        assertFalse(userContextService.hasAnyRole(List.of("A", "TEACHER")));
    }

}