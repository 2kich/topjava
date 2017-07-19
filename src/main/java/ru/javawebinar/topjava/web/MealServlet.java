package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("meals servlet");
        final List<Meal> meals = MealsUtil.getMeals();
        final List<MealWithExceed> mealsWithExceeded = MealsUtil.getRandomWithExceededByCycle(meals, 700);

//        response.sendRedirect("meals.jsp");
        request.setAttribute("meals",mealsWithExceeded);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/meals.jsp");
        requestDispatcher.forward(request,response);
    }
}
