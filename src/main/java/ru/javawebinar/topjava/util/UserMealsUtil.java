package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        System.out.println( getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(16,0), 2000).size());
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        boolean isExeeded;
        HashMap<LocalDate, Integer> allColoriesPerDayMap=new HashMap<>();
        ArrayList<UserMealWithExceed> listMeal=new ArrayList<>();

        for ( UserMeal meal:mealList ) {
              LocalDate date=meal.getDateTime().toLocalDate();
              if (allColoriesPerDayMap.get(date)==null) {
                  Integer realCaloriesPerDay=0;
                  for ( UserMeal meal2:mealList ) {
                      if  (meal2.getDateTime().toLocalDate().equals(date)) {
                          realCaloriesPerDay=realCaloriesPerDay+meal2.getCalories();
                      }
                  }
                  allColoriesPerDayMap.put(date,realCaloriesPerDay);
              }
        }

        for ( UserMeal meal:mealList ) {

            LocalTime date = meal.getDateTime().toLocalTime();
            if (date.isAfter(startTime)&& date.isBefore(endTime)) {
                int allRealCalories = allColoriesPerDayMap.get(meal.getDateTime().toLocalDate());
                isExeeded = caloriesPerDay < allRealCalories;
                listMeal.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), isExeeded));
            }
        }

        return listMeal;

    }
}
