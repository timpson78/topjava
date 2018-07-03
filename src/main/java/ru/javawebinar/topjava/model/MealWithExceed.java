package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.DateUtil;

import java.time.LocalDateTime;
import java.util.UUID;

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
        return DateUtil.DateTimeFormat(dateTime);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealWithExceed)) return false;

        MealWithExceed that = (MealWithExceed) o;

        if (calories != that.calories) return false;
        if (exceed != that.exceed) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + calories;
        result = 31 * result + (exceed ? 1 : 0);
        return result;
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