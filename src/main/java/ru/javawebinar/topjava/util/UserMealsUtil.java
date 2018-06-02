package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        List<UserMealWithExceed> firstList = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(16, 0), 2000);
        List<UserMealWithExceed> secondList = getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(16, 0), 2000);
        System.out.println(firstList.equals(secondList));
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        HashMap<LocalDate, Integer> allColoriesPerDayMap = new HashMap<>();
        for (UserMeal meal : mealList) {
            allColoriesPerDayMap.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), (s1, s2) -> s1 + s2);
        }

        ArrayList<UserMealWithExceed> listMeal = new ArrayList<>();
        for (UserMeal meal : mealList) {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                int allRealCalories = allColoriesPerDayMap.get(meal.getDateTime().toLocalDate());
                boolean isExceeded = caloriesPerDay < allRealCalories;
                listMeal.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), isExceeded));
            }
        }
        return listMeal;
    }


    public static List<UserMealWithExceed> getFilteredWithExceededStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> sumPerDay = mealList.stream()
                .collect(Collectors.groupingBy(u -> u.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));
        return mealList.stream()
                .filter(userMeal -> (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)))
                .map(userMeal -> {
                    boolean isExceeded = sumPerDay.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay;
                    return new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), isExceeded);
                })
                .collect(Collectors.toList());
    }
}
