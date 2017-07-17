package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> userMealWithExceeds;
        List<UserMeal> userMealList = mealList.stream()
                .filter(userMeal -> userMeal.getDateTime()
                        .toLocalTime()
                        .isAfter(startTime) && userMeal.getDateTime()
                        .toLocalTime()
                        .isBefore(endTime))
                .collect(Collectors.toList());

        int sumOfCaloriesBetweenStartAndEndTimes;
        boolean isDietOverload;

        sumOfCaloriesBetweenStartAndEndTimes = userMealList.stream()
                .mapToInt(UserMeal::getCalories)
                .sum();

        if (sumOfCaloriesBetweenStartAndEndTimes > caloriesPerDay) {
            isDietOverload = false;
        } else {
            isDietOverload = true;
        }

        userMealWithExceeds = userMealList.stream()
                .map(userMeal -> new UserMealWithExceed(userMeal.getDateTime(),
                        userMeal.getDescription(),
                        userMeal.getCalories(),
                        isDietOverload))
                .collect(Collectors.toList());

        return userMealWithExceeds;

    }
}
