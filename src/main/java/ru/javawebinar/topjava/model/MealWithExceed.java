package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.DateUtil;

import java.time.LocalDateTime;

public class MealWithExceed {

    private final String id;

    private  LocalDateTime dateTime;
    private  String description;
    private  int calories;
    private  boolean exceed;

    public MealWithExceed(String id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.id=id;
    }


    public String getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return DateUtil.dateTimeFormat(dateTime);
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }


    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setExceed(boolean exceed) {
        this.exceed = exceed;
    }


    @Override
    public String toString() {
        return "MealWithExceed{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}