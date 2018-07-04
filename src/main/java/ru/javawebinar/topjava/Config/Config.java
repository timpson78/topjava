package ru.javawebinar.topjava.Config;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MapConcurrentStorage;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;


public class Config {

    public static MapConcurrentStorage dataInitialization() {
        ArrayList<Meal> listMeals=new ArrayList<>(Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        ));

        ConcurrentHashMap<String, Meal> chm=new ConcurrentHashMap();
        for (Meal meal:listMeals) {
            chm.put(meal.getId(),meal);
        }
        return new MapConcurrentStorage(chm);
    }
}
