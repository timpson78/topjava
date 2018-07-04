package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {

    void update(Meal m);

    void save(Meal m);

    Meal get(String id);

    void delete(String searchkey);

    List<Meal> getAll();

}
