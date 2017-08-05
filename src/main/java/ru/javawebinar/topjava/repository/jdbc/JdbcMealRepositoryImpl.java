package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    private static final BeanPropertyRowMapper<Meal> MEAL_ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertMeal;

    public JdbcMealRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    }

    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("dateTime", meal.getDateTime())
                .addValue("description", meal.getDescription())
                .addValue("calories", meal.getCalories());

//        if (meal.getId() != userId) { //TODO check userId here!
//            return null;
//        }
        if (meal.isNew()) {
            Number newKey = insertMeal.executeAndReturnKey(map);
            meal.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE meals SET datetime=:dateTime, description=:description, calories=:calories WHERE id=:id", map);
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) { //TODO one request should be
//        Meal meal = DataAccessUtils.singleResult(jdbcTemplate.query("SELECT * FROM meals WHERE id=?",MEAL_ROW_MAPPER,id));
//        if (meal.getId() != userId) {
//            return false;
//        }
        return jdbcTemplate.update("DELETE FROM meals WHERE id=?", id) != 0;
    }

    @Override
    public Meal get(int id, int userId) { //TODO check userId here!
        List<Meal> meal = jdbcTemplate.query("SELECT * FROM meals WHERE id=?", MEAL_ROW_MAPPER, id);
//        if (meal.get(id).getId() != userId) {
//            return null;
//        }
        return DataAccessUtils.singleResult(meal);
    }

    @Override
    public List<Meal> getAll(int userId) {//TODO check userId here!
        return jdbcTemplate.query("SELECT * FROM meals ORDER BY datetime", MEAL_ROW_MAPPER);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) { //TODO check userId here!
        Timestamp st = Timestamp.valueOf(startDate);
        Timestamp et = Timestamp.valueOf(endDate);
        return jdbcTemplate.query("SELECT * FROM meals WHERE datetime > ? AND datetime < ?",MEAL_ROW_MAPPER,st,et);
    }
}
