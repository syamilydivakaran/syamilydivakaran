package com.luminar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteEmployee")

public class DeleteEmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		RequestDispatcher dis = null;
		boolean flag = false;
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost:3306/luminarprojects";
		final String user = "root";
		final String password = "Syamily@123";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			
			 String empID = request.getParameter("empId");
			 int empId=Integer.parseInt(empID);
			pstmt = conn.prepareStatement("DELETE FROM employee WHERE emp_id = ?");
			pstmt.setInt(1, empId);
			pstmt.executeUpdate();
			flag = true;
			if (flag) {
				dis = request.getRequestDispatcher("index.html");
				dis.forward(request, response);
			} else {
				dis = request.getRequestDispatcher("error.html");
				dis.forward(request, response);
			}

			pstmt.close();
			conn.close();
			response.getWriter().println("Employee " + empId + " deleted successfully from table ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.getWriter().println("Database driver not found.");
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().println("Error executing SQL: " + e.getMessage());
		}
		 if(flag){
	            response.sendRedirect("employees");
	       }else{
	    	   response.sendRedirect("error.html");
	       }
	}
}

