package ru.javawebinar.topjava.dao;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import ru.javawebinar.topjava.model.Meal;

public class MealDaoImpl implements MealDao {

    private Map<Integer, Meal> map = new ConcurrentHashMap<Integer, Meal>();
    private AtomicInteger counter = new AtomicInteger(0);


    // Just like population script
    public MealDaoImpl() {
        map.put(0, new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        map.put(1, new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        map.put(2, new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        map.put(3, new Meal(3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        map.put(4, new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        map.put(5, new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        counter.set(6); // Set actual count for counter
    }

    @Override
    public Meal create(Meal meal) {
        Integer id = counter.getAndIncrement();
        Meal createdMeal = new Meal(id, meal);
        return map.put(id, createdMeal);
    }

    @Override
    public Meal get(Integer id) {
        return map.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<Meal>(map.values());
    }

    @Override
    public Meal update(Meal meal) {
        return map.put(meal.getId(), meal);
    }

    @Override
    public Meal delete(Integer id) {
        return map.remove(id);
    }
}
