package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Config.Config;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import storage.ListStorage;
import storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to users");
        List<Meal> meals = Config.dataInitialization();
        Storage storage = new ListStorage(MealsUtil.getAllFilteredWithExceeded(meals, 2000));
        request.setAttribute("meals", storage.getAllSorted());
        request.getRequestDispatcher(("/meals.jsp")
        ).forward(request, response);
    }
}


