package ru.sch1z0ed.diary.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sch1z0ed.diary.dto.SchoolClassDTO;
import ru.sch1z0ed.diary.jpa.repositories.SchoolClassRepository;
import ru.sch1z0ed.diary.service.SchoolClassService;
import ru.sch1z0ed.diary.utils.SchoolClassMapper;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchoolClassServiceImpl implements SchoolClassService {
    private final SchoolClassRepository schoolClassRepository;
    private final SchoolClassMapper schoolClassMapper;

    @Override
    public void postNewSchoolClass(final SchoolClassDTO schoolClassDTO) {
        schoolClassRepository.save(schoolClassMapper.dtoToSchoolClass(schoolClassDTO));
    }

    @Override
    public List<SchoolClassDTO> getSchoolClassList() {
        return schoolClassRepository.findAll().stream()
                .map(schoolClassMapper::schoolClassToDTO)
                .collect(Collectors.toList());
    }

    //todo to test
    @Override
    public void deleteSchoolClassById(final Long id) {
        schoolClassRepository.deleteById(id);
    }
}
