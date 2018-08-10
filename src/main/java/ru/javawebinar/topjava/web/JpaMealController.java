package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.AbstractMealController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/meals")
public class JpaMealController extends AbstractMealController {

    private static final int CALORIES_DEFAULT =1000 ;

    @Autowired
    public JpaMealController(MealService service) {
        super(service);

    }
    //  private AbstractMealCotroller controller;

    @GetMapping("")
    public String getall_(Model model) {
        model.addAttribute("meals", super.getAll());
        return "meals";
    }
    @GetMapping("/delete")
    public String delete_(@RequestParam("id") int id){
        super.delete(id);
        return "redirect:/meals";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update_(Model model,@RequestParam("id") int id){
        model.addAttribute("meal", super.get(id));
        return "mealForm";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatesave(HttpServletRequest request) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = CreateMealFromRequest(request);
        if (request.getParameter("id").isEmpty()) {
            super.create(meal);
        } else {
            super.update(meal, getId(request));
        }

       return "redirect:/meals";
    }


    @RequestMapping(value = "/create", method = {RequestMethod.GET})
    public String create(Model model) {
        Meal meal=new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", CALORIES_DEFAULT);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(HttpServletRequest request,Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }


    private Meal CreateMealFromRequest(HttpServletRequest request) {
        return new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }


}
