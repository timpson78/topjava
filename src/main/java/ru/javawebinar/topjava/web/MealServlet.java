package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Config.Config;
import ru.javawebinar.topjava.Exeptions.MyNumberFormatException;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.DateUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import storage.ListStorage;
import storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    public  static Storage storage = Config.dataInitialization();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("doGet: start");
        request.setCharacterEncoding("UTF-8");
        String id=request.getParameter("id");
        String action=request.getParameter("action");
        if (action==null) {
            request.setAttribute("meals",   MealsUtil.getAllFilteredWithExceeded(storage.getAllSorted(),2000));
            request.getRequestDispatcher(("/meals.jsp")
            ).forward(request, response);
        } else {
            switch (action) {
            case "delete":
                storage.delete(id);
                response.sendRedirect("/topjava/meals");
                return;
            case "view":
                break;
            case "add":
                request.getRequestDispatcher(("/edit.jsp")
                ).forward(request, response);
                break;
            case "edit":
                request.setAttribute("meal", storage.get(id));
                request.getRequestDispatcher(("/edit.jsp")
                ).forward(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("doPost: start");
        request.setCharacterEncoding("UTF-8");

        String id=request.getParameter("id");
        LocalDateTime datetime= DateUtil.StringDateTimeFormat(request.getParameter("datetime"));
        String description=request.getParameter("description");
        int calories=StringToInt(request.getParameter("calories"));

        final boolean isCreate=(id==null||id.length()==0);

        Meal meal;
        if (isCreate){
            meal=new Meal(datetime,description,calories);
            storage.save(meal);
            response.sendRedirect("/topjava/meals");
        }else {
            meal=storage.get(id);
            meal.setCalories(calories);
            meal.setDateTime(datetime);
            meal.setDescription(description);
            storage.update(meal);
            response.sendRedirect("/topjava/meals");
        }

    }

    private  int StringToInt(String s) throws NumberFormatException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw  new MyNumberFormatException(e.getMessage());
        }
    }
}


