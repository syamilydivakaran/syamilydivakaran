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

@WebServlet("/insertBudget")
public class InsertBudgetServlet extends HttpServlet {
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

		String total = request.getParameter("totalBudget");
		double totalBudget = Double.parseDouble(total);

		String amtUsed = request.getParameter("amountUsed");
		double amountUsed = Double.parseDouble(amtUsed);

		double balanceAmount = totalBudget - amountUsed;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(
					"INSERT INTO budget_plan (total_budget, amount_used, balance_amount) VALUES (?, ?, ?)");
			pstmt.setDouble(1, totalBudget);
			pstmt.setDouble(2, amountUsed);
			pstmt.setDouble(3, balanceAmount);
			pstmt.executeUpdate();
			flag = true;

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}

		if (flag) {
			response.sendRedirect("index.html");
		} else {
			response.sendRedirect("error.html");
		}

	}
}