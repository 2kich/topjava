package ru.javawebinar.topjava.dao;

import java.util.List;

import ru.javawebinar.topjava.model.Meal;

public interface MealsDao {
    public void addMeal(Meal meal);

    public void deleteMeal(int mealId);

    public void updateUser(Meal meal);

    public List<Meal> getAllMeals();

    public Meal getMealById(int mealId);
}
