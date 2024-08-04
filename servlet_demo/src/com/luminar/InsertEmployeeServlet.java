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

@WebServlet("/insertEmployee")
public class InsertEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost:3306/luminarprojects";
		final String user = "root";
		final String password = "Syamily@123";

		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean flag = false;

		String id = request.getParameter("empId");
		int empId = Integer.parseInt(id);

		String empName = request.getParameter("empName");
		String empAddr = request.getParameter("empAddr");
		String empAadhar = request.getParameter("empAadhar");
		String empPhone = request.getParameter("empPhone");
		String empSalary = request.getParameter("empSalary");

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(
					"INSERT INTO employee (emp_id, emp_name, emp_address, emp_aadhar, emp_phone, emp_salary) VALUES (?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, empId);
			pstmt.setString(2, empName);
			pstmt.setString(3, empAddr);
			pstmt.setString(4, empAadhar);
			pstmt.setString(5, empPhone);
			pstmt.setString(6, empSalary);
			pstmt.executeUpdate();
			flag = true;

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}

		if (flag) {
			response.sendRedirect("employees");
		} else {
			response.sendRedirect("error.html");
		}

	}
}
