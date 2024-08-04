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

@WebServlet("/budgetplan")
public class BudgetPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost:3306/luminarprojects";
		final String user = "root";
		final String password = "Syamily@123";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement("select * from budget_plan");
			rs = pstmt.executeQuery();

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>Budget Details</title></head>");
		out.println("<body>");
		out.println("<a href='index.html'>Back To Home</a>");
		out.println("<br><br>");

		out.println("<h1>Add Budget Details</h1>");
		out.println("<br>");

		out.println("<form name='insertBudgetForm' method='post' action='insertBudget'>" );
	
			out.println("<label>Total Budget</label>&nbsp;&nbsp;");
			out.println("<input type='text' name='totalBudget'/>");
			
			out.println("<br><br>");
			
			out.println("<label>Amount Used</label>&nbsp;");
			out.println("<input type='text' name='amountUsed'/>");
			
			out.println("<br><br>");
			
		
			out.println("<input type='submit' name='Save'/>");
		
		out.println("</form");
		
		
		
		out.println("<br><br>");

		out.println("<h1>Budget Details</h1>");

		out.println("<table border='1'>");
		out.println("<tr><th>Budget ID</th><th>Total Budget</th><th>Amount Used</th><th>Balance Amount</th></tr>");

		try {
			while (rs.next()) {

				out.println("<tr>" + "<td>" + rs.getInt("budget_id") + "</td>" + "<td>" + rs.getDouble("total_budget")
						+ "</td>" + "<td>" + rs.getDouble("amount_used") + "</td>" + "<td>"
						+ rs.getDouble("balance_amount") + "</td>" + "</tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		out.println("</table></body></html>");

	}
}
