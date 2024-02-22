package ru.sch1z0ed.diary.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sch1z0ed.diary.jpa.entities.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

}
