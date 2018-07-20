package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.Month;

import static java.time.LocalDateTime.of;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {

        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MEAL_ID, USER_ID);
        assertMatch(MEAL1_U, meal);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFoundMeal() throws Exception {
       service.get(MEAL4_A.getId(), USER_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MealTestData.MEAL1_U.getId(), USER_ID);
        assertMatch(service.getAll(USER_ID), MEAL2_U, MEAL3_U, MEAL7_U);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFoundMeal() throws Exception {
        service.delete(MealTestData.MEAL4_A.getId(), USER_ID);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        assertMatch(service.getBetweenDateTimes(of(2016, Month.JULY, 16, 9, 11), of(2016, Month.JULY, 16, 14, 2), USER_ID), MEAL1_U, MEAL2_U);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), MEAL1_U, MEAL2_U, MEAL3_U, MEAL7_U);
    }

    @Test
    public void update() throws Exception {
        Meal mealupd = service.get(MEAL1_U.getId(), USER_ID);
        mealupd.setCalories(34);
        mealupd.setDescription("Updated  description");
        service.update(mealupd, USER_ID);
        assertMatch(service.get(MEAL1_U.getId(), USER_ID), mealupd);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        service.update(MEAL1_U, ADMIN_ID);
    }

    @Test
    public void create() throws Exception {
        Meal newmeal = new Meal(of(2014, Month.JANUARY, 25, 13, 17), "Новый миал", 450);
        service.create(newmeal, USER_ID);
        assertMatch(service.getAll(USER_ID), newmeal, MEAL1_U, MEAL2_U, MEAL3_U, MEAL7_U);
    }

}