package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, meal.getUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save meal {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        if (meal.getUserId() != null && meal.getId() == userId) {
            repository.put(meal.getId(), meal);
            return meal;
        }
        return null;
    }

    @Override
    public boolean delete(int mealId, int userId) { // TODO: Might be shorter (via streams...)
        log.info("delete meal {}", mealId);
        if (repository.get(mealId) != null
                && repository.get(mealId).getUserId() != null
                && repository.get(mealId).getUserId() == userId) {
            repository.remove(mealId);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public Meal get(int mealId, int userId) {
        log.info("get meal {}", mealId);
        if (repository.containsKey(mealId)
                && repository.get(mealId).getUserId() != null
                && repository.get(mealId).getUserId() == userId) {

            return repository.get(mealId);
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        log.info("getAll meals for{}", userId);
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

}



