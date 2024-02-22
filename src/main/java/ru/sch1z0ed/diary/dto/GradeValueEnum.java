package ru.sch1z0ed.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GradeValueEnum {
    EXCELLENT("celująca", 6, "96-100 процентов"),
    VERY_GOOD("bardzo dobra", 5, "86-95 процентов"),
    GOOD("dobra", 4, "71-85 процентов"),
    SATISFACTORY("dostateczna", 3, "56-70 процентов"),
    SUFFICIENT("dopuszczająca", 2, "41-55 процентов"),
    INSUFFICIENT("niedostateczna", 1, "от 0 до 40 процентов");

    private final String polishName;
    private final Integer gradeValueAsInt;
    private final String desc;

}
