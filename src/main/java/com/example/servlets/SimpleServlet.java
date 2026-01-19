package com.example.servlets;

import java.io.IOException;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.services.DatabaseService;
import com.example.services.QueueService;

@WebServlet(name = "SimpleServlet", urlPatterns = {"/test"})
public class SimpleServlet extends HttpServlet {

    @Inject
    private DatabaseService dbService;

    @Inject
    private QueueService queueService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String param = request.getParameter("message"); 
        String text = null;
        String output = null;

        if (param != null && !param.isEmpty()) {
            // Parameter is present and has a non-empty value
            text = param;
        } else {
            // Parameter is either not present, or its value is empty
            text = "Hello World";
        }

        try {
            output = queueService.sendMessage(text);
            dbService.saveMessage(text);
        }
        catch (Exception ex) {
            response.getWriter().print(ex);
            response.setStatus(500);
            return;
        }

        response.setContentType("text/html;charset=UTF-8");

        var writer = response.getWriter();
        writer.println("<h1>Sent message: " + text +  "</h1>");  
        writer.println("<h1>Powered by: " + output +  "</h1>");  

    }
}
