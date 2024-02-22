package ru.sch1z0ed.diary.jpa.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sch1z0ed.diary.jpa.entities.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findFirstByEmail(String email);
}
