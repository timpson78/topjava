package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Config.Config;
import ru.javawebinar.topjava.Exeptions.MyNumberFormatException;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static Storage storage = Config.dataInitialization();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("doGet: start");
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(storage.getAll(), LocalTime.MIN,LocalTime.MAX, 2000));
            request.getRequestDispatcher(("/meals.jsp")
            ).forward(request, response);
        } else {
            switch (action) {
                case "delete":
                    storage.delete(id);
                    response.sendRedirect("meals");
                    return;
                case "view":
                    break;
                case "add":
                    request.getRequestDispatcher(("/editmeal.jsp")
                    ).forward(request, response);
                    break;
                case "edit":
                    request.setAttribute("meal", storage.get(id));
                    request.getRequestDispatcher(("/editmeal.jsp")
                    ).forward(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("doPost: start");
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        LocalDateTime datetime = DateUtil.stringDateTimeFormat(request.getParameter("datetime"));
        String description = request.getParameter("description");
        int calories = StringToInt(request.getParameter("calories"));

        final boolean isCreate = (id == null || id.length() == 0);

        Meal meal;
        if (isCreate) {
            meal = new Meal(datetime, description, calories);
            storage.save(meal);
            response.sendRedirect("meals");
        } else {
            meal = new Meal(id,datetime, description, calories);
            storage.update(meal);
            response.sendRedirect("meals");
        }

    }

    private int StringToInt(String s) throws NumberFormatException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException(e.getMessage());
        }
    }
}


