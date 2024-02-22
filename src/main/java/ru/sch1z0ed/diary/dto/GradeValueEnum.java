package ru.sch1z0ed.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GradeValueEnum {
    EXCELLENT("отлично", 6, "96-100 процентов"),
    VERY_GOOD("очень хорошо", 5, "86-95 процентов"),
    GOOD("хорошо", 4, "71-85 процентов"),
    SATISFACTORY("достаточно", 3, "56-70 процентов"),
    SUFFICIENT("допустимо", 2, "41-55 процентов"),
    INSUFFICIENT("недостаточно", 1, "от 0 до 40 процентов");

    private final String name;
    private final Integer gradeValueAsInt;
    private final String desc;

}
