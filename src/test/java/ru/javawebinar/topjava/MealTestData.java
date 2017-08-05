package ru.javawebinar.topjava;


import java.util.Objects;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_1 = START_SEQ;
    public static final int MEAL_2 = START_SEQ + 1;

    public static final Meal LUNCH = new Meal(MEAL_1, DateTimeUtil.parseLocalDateTime("2015-06-1 20:38:40"),"LUNCH",510);
    public static final Meal DINNER = new Meal(MEAL_2, DateTimeUtil.parseLocalDateTime("2015-06-21 20:38:40"),"DINNER",1500);

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>((expected, actual) -> expected == actual ||
            (Objects.equals(expected.getId(), actual.getId())
                    && Objects.equals(expected.getDateTime(), actual.getDateTime())
                    && Objects.equals(expected.getDescription(), actual.getDescription())
                    && Objects.equals(expected.getCalories(), actual.getCalories())
            ));
}
