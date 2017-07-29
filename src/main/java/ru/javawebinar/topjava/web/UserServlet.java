package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import ru.javawebinar.topjava.AuthorizedUser;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");

        String string = request.getParameter("select");

        System.out.println("This is the number - " + string);

//        int userId = Integer.parseInt(string);

//        AuthorizedUser.setId(userId);

        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
