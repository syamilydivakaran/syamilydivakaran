package com.luminar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateEmployee")

public class UpdateEmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost:3306/luminarprojects";
		final String user = "root";
		final String password = "Syamily@123";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);

			int empId = Integer.parseInt(request.getParameter("empId"));
			String empName = request.getParameter("empName");
			String empAddress = request.getParameter("empAddr");
			String empAadhar = request.getParameter("empAadhar");
			String empPhone = request.getParameter("empPhone");
			String empSalary = request.getParameter("empSalary");

			String updateSQL = "UPDATE employee SET emp_name = ?, emp_address = ?, emp_aadhar = ?, emp_phone = ?, emp_salary = ? WHERE emp_id = ?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, empName);
			pstmt.setString(2, empAddress);
			pstmt.setString(3, empAadhar);
			pstmt.setString(4, empPhone);
			pstmt.setString(5, empSalary);
			pstmt.setInt(6, empId);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.getWriter().println("Database driver not found.");
		} catch (SQLException sql) {
			sql.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (flag) {
				response.sendRedirect("index.html");
			} else {
				response.sendRedirect("employees");
			}
		}
	}
}
