package ru.sch1z0ed.diary.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.sch1z0ed.diary.jpa.entities.SchoolClass;
import ru.sch1z0ed.diary.jpa.entities.User;
import ru.sch1z0ed.diary.jpa.repositories.SchoolClassRepository;
import ru.sch1z0ed.diary.jpa.repositories.UserRepository;
import ru.sch1z0ed.diary.service.RoleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
@RequiredArgsConstructor
public class RandomUserHelper {

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SchoolClassRepository schoolClassRepository;


    List<String> studentNamesM = new ArrayList<>(List.of(
            "Иван", "Александр", "Дмитрий", "Максим", "Артем",
            "Николай", "Сергей", "Андрей", "Алексей", "Михаил",
            "Кирилл", "Владимир", "Павел", "Егор", "Станислав"
    ));
    List<String> studentNamesF = new ArrayList<>(List.of(
            "Екатерина", "Ольга", "Татьяна", "Анастасия", "Елена",
            "Мария", "Ирина", "София", "Алиса", "Виктория",
            "Юлия", "Алина", "Наталья", "Ксения", "Евгения"
    ));
    List<String> studentLastNamesM = new ArrayList<>(List.of(
            "Иванов", "Петров", "Сидоров", "Козлов", "Новиков",
            "Морозов", "Волков", "Алексеев", "Лебедев", "Семенов",
            "Егоров", "Павлов", "Козлов", "Степанов", "Николаев"
    ));
    List<String> studentLastNamesF = new ArrayList<>(List.of(
            "Иванова", "Петрова", "Сидорова", "Козлова", "Новикова",
            "Морозова", "Волкова", "Алексеева", "Лебедева", "Семенова",
            "Егорова", "Павлова", "Козлова", "Степанова", "Николаева"
    ));
    List<String> emails = new ArrayList<>(List.of("@yandex.ru", "@gmail.com", "@mail.ru", "@ya.ru"));

    public void createAndSave20TestUsers() {
        final List<User> randomUserM = createRandomUser(studentNamesM, studentLastNamesM, emails);
        final List<User> randomUserF = createRandomUser(studentNamesF, studentLastNamesF, emails);
        randomUserM.addAll(randomUserF);
        userRepository.saveAll(randomUserM);
        log.info("random users created");
    }

    public SchoolClass getClassRandom() {
        List<SchoolClass> allClasses = schoolClassRepository.findAll();
        if (!allClasses.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(allClasses.size());
            return allClasses.get(randomIndex);
        } else {
            return null; // Обработайте ситуацию, если список классов пуст
        }
    }

    private List<User> createRandomUser(List<String> studentNames, List<String> studentLastNames, List<String> emails) {
        Collections.shuffle(studentNames);
        Collections.shuffle(studentLastNames);
        Collections.shuffle(emails);
        final List<User> users = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            final String simplifiedEmail = simplifyLatinChars(studentNames.get(i) + "." + studentLastNames.get(i)) + random.nextInt(1000) + emails.get(i % emails.size());
            final User user = User.builder()
                    .firstName(studentNames.get(i))
                    .lastName(studentLastNames.get(i))
                    .email(simplifiedEmail)
                    .birthDate(between())
                    .schoolClass(getClassRandom())
                    .passwordHash(passwordEncoder.encode(simplifiedEmail))
                    .build();
            roleService.getORCreateDefaultRole(user, RoleEnum.ROLE_STUDENT);
            users.add(user);
        }
        return users;
    }

    private LocalDate between() {
        final LocalDate startInclusive = LocalDate.of(1970, 1, 1);
        final LocalDate endExclusive = LocalDate.of(2005, 12, 30);
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private String simplifyLatinChars(final String inputString) {
        final char[] input = inputString.toCharArray();
        final StringBuilder output = new StringBuilder();
        int inputLength = input.length;
        for (int i = 0; i < inputLength; i++) {
            switch (input[i]) {
                case 'ą':
                    output.append('a');
                    break;
                case 'ć':
                    output.append('c');
                    break;
                case 'ę':
                    output.append('e');
                    break;
                case 'ł':
                    output.append('l');
                    break;
                case 'ń':
                    output.append('n');
                    break;
                case 'ó':
                    output.append('o');
                    break;
                case 'ś':
                    output.append('s');
                    break;
                case 'ź':
                    output.append('z');
                    break;
                case 'ż':
                    output.append('z');
                    break;
                case 'Ą':
                    output.append('A');
                    break;
                case 'Ć':
                    output.append('C');
                    break;
                case 'Ę':
                    output.append('E');
                    break;
                case 'Ł':
                    output.append('L');
                    break;
                case 'Ń':
                    output.append('N');
                    break;
                case 'Ó':
                    output.append('O');
                    break;
                case 'Ś':
                    output.append('S');
                    break;
                case 'Ź':
                    output.append('Z');
                    break;
                case 'Ż':
                    output.append('Z');
                    break;
                default:
                    output.append(input[i]);
                    break;
            }
        }
        return output.toString();
    }
}
