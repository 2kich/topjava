package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private static final MealDao DAO = new MealDaoImpl();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null)

            switch (action) {
                case "delete": {
                    LOG.debug("delete");
                    DAO.delete(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect("meals");
                    break;
                }
                case "update": {
                    Meal meal = DAO.get(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("meal", new Meal(meal.getId(), meal));
                }
                case "create":
                    request.getRequestDispatcher("/edit.jsp").forward(request, response);
            }
        else {
            LOG.debug("get all meals");
            request.setAttribute("mealList",
                    MealsUtil.getFilteredWithExceeded(DAO.getAll(),
                            LocalTime.MIN,
                            LocalTime.MAX,
                            2000));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime")
                .replace('T', ' '), formatter);
        String description = request.getParameter("description");
        Integer calories = Integer.parseInt(request.getParameter("calories"));

        if (id == null || id.isEmpty()) {
            LOG.debug("create");
            DAO.create(new Meal(dateTime, description, calories));
        } else {
            LOG.debug("update");
            DAO.update(new Meal(Integer.parseInt(id), dateTime, description, calories));
        }

        response.sendRedirect("meals");
    }

}
