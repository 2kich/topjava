package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal) { //TODO Something wrong :c
        return repository.save(meal,meal.getUserId());
    }

    @Override
    public void delete(int id) throws NotFoundException { //TODO Something wrong :c
        checkNotFoundWithId(repository.delete(id,1), id);
    }

    @Override
    public Meal get(int id) throws NotFoundException { //TODO Something wrong :c
        return checkNotFoundWithId(repository.get(id,1), id);
    }

    @Override
    public void update(Meal meal) { //TODO Something wrong :c
        repository.save(meal,meal.getUserId());
    }

    @Override
    public Collection<Meal> getAll() { //TODO Something wrong :c
        return repository.getAll(1);
    }
}