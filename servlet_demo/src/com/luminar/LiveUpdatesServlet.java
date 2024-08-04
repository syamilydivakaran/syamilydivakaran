package com.luminar;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/liveupdates")
public class LiveUpdatesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Indian Spices Official Site for Live Update </title></head>");
        out.println("<body>");
        out.println("<a href='index.html'>Back To Home</a>"); 
        out.println("<br><br>");
        
        out.println("<h1>Welcome to live updates</h1>");
        out.println("<p>Click the link below to visit Indian spices:</p>");
        out.println("<a href='https://www.indianspices.com/marketing/price/domestic/daily-price.html'>Indian spices</a>");
        out.println("</body>");
        out.println("</html>");
    }

   
}
