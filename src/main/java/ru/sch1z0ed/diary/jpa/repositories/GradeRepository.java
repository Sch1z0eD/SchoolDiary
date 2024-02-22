package ru.sch1z0ed.diary.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sch1z0ed.diary.jpa.entities.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
