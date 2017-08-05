package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.DbPopulator;

import static ru.javawebinar.topjava.MealTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before

    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MEAL_1, AuthorizedUser.id());
        MATCHER.assertEquals(LUNCH, meal);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL_1,AuthorizedUser.id());
        MATCHER.assertCollectionEquals(Collections.singletonList(DINNER), service.getAll(AuthorizedUser.id()));
    }

    @Test
    public void getBetweenDates() throws Exception { // TODO Check time bounds (hardcode...I know that)
        Collection<Meal> mealCollection = service.getBetweenDates(LUNCH.getDate(), DINNER.getDate(),AuthorizedUser.id());
        MATCHER.assertCollectionEquals(mealCollection,service.getAll(AuthorizedUser.id()));
    }

    @Test
    public void getBetweenDateTimes() throws Exception { // TODO Check time bounds (hardcode...I know that)
        Collection<Meal> mealCollection = service.getBetweenDateTimes(LUNCH.getDateTime(),DINNER.getDateTime(),AuthorizedUser.id());
        MATCHER.assertCollectionEquals(mealCollection,service.getAll(AuthorizedUser.id()));
    }

    @Test
    public void getAll() throws Exception {
        Collection<Meal> all = service.getAll(AuthorizedUser.id());
        MATCHER.assertCollectionEquals(Arrays.asList(LUNCH, DINNER), all);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(LUNCH.getId(),LUNCH.getDateTime(),LUNCH.getDescription(),LUNCH.getCalories());
        updated.setDescription("UpdatedDescription");
        updated.setCalories(330);
        service.update(updated,AuthorizedUser.id());
        MATCHER.assertEquals(updated, service.get(MEAL_1,AuthorizedUser.id()));
    }

    @Test
    public void save() throws Exception {
        Meal newMeal = new Meal(null, DateTimeUtil.parseLocalDateTime("2015-06-1 20:38:40"), "newDescription", 1500);
        Meal created = service.save(newMeal,AuthorizedUser.id());
        newMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(LUNCH, newMeal, DINNER), service.getAll(AuthorizedUser.id()));
    }


}