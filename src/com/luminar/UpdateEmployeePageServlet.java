package com.luminar;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateEmployeePage")
public class UpdateEmployeePageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/luminarprojects";
        final String user = "root";
        final String password = "Syamily@123";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int empId = Integer.parseInt(request.getParameter("empId"));

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM employee WHERE emp_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();

                out.println("<html>");
                out.println("<head><title>Update Employee Details</title></head>");
                out.println("<body>");
                out.println("<a href='employees'>Back To Employee List</a>");
                out.println("<br><br>");
                out.println("<h2>Update Employee Details</h2>");
                out.println("<br>");
                out.println("<form name='updateEmployeeForm' method='post' action='updateEmployee'>");
                out.println("<input type='hidden' name='empId' value='" + rs.getInt("emp_id") + "'/>");
                out.println("<label>Name</label>&nbsp;");
                out.println("<input type='text' name='empName' value='" + rs.getString("emp_name") + "'/>");
                out.println("<br><br>");
                out.println("<label>Address</label>&nbsp;");
                out.println("<input type='text' name='empAddr' value='" + rs.getString("emp_address") + "'/>");
                out.println("<br><br>");
                out.println("<label>Aadhar</label>&nbsp;");
                out.println("<input type='text' name='empAadhar' value='" + rs.getString("emp_aadhar") + "'/>");
                out.println("<br><br>");
                out.println("<label>Phone</label>&nbsp;");
                out.println("<input type='text' name='empPhone' value='" + rs.getString("emp_phone") + "'/>");
                out.println("<br><br>");
                out.println("<label>Basic salary</label>&nbsp;");
                out.println("<input type='text' name='empSalary' value='" + rs.getString("emp_salary") + "'/>");
                out.println("<br><br>");
                out.println("<input type='submit' value='Update'/>");
                out.println("</form>");
                out.println("</body></html>");
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sql) {
            sql.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
