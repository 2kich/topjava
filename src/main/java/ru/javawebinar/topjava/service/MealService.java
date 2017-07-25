package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;

import java.util.Collection;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;


public interface MealService {

    Meal save(Meal meal);

    void delete(int id) throws NotFoundException;

    Meal get(int id) throws NotFoundException;

    void update(Meal meal);

    Collection<Meal> getAll();
}