package ru.javawebinar.topjava.dao;

import java.util.List;

import ru.javawebinar.topjava.model.Meal;

public interface MealDao {

    Meal create(Meal meal);

    Meal get(Integer id);

    List<Meal> getAll();

    Meal update(Meal meal);

    Meal delete(Integer id);

}
