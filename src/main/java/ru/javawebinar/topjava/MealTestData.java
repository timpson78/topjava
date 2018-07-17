package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.Month;
import java.util.Arrays;

import static java.time.LocalDateTime.*;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ+2;


    public static final Meal MEAL1_U = new Meal(MEAL_ID, of(2016, Month.JULY, 16, 9, 11), "Завтрак", 600);
    public static final Meal MEAL2_U = new Meal(MEAL_ID + 1, of(2016, Month.JULY, 16, 13, 11), "Обед", 500);
    public static final Meal MEAL3_U = new Meal(MEAL_ID + 6, of(2016, Month.JULY, 16, 16, 15), "Ланч", 200);
    public static final Meal MEAL4_A = new Meal(MEAL_ID + 3, of(2017, Month.JULY, 16, 9, 13), "Завтрак", 800);
    public static final Meal MEAL5_A = new Meal(MEAL_ID + 4, of(2017, Month.JULY, 16, 14, 14), "Обед", 700);
    public static final Meal MEAL6_A = new Meal(MEAL_ID + 5, of(2017, Month.JULY, 16, 20, 14), "Ужин", 700);
    public static final Meal MEAL7_U = new Meal(MEAL_ID + 2, of(2016, Month.JULY, 16, 19, 10), "Ужин", 700);
    public static final Meal MEAL8_A = new Meal(MEAL_ID + 5, of(2018, Month.JULY, 16, 9, 16), "Завтрак", 900);
    public static final Meal MEAL9_A = new Meal(MEAL_ID + 5, of(2018, Month.JULY, 16, 13, 17), "Обед", 400);
    public static final Meal MEAL10_A = new Meal(MEAL_ID + 5, of(2018, Month.JULY, 16, 20, 17), "Ужин", 600);



    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
