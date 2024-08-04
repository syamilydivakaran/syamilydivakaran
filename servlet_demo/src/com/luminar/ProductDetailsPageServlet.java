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

@WebServlet("/production")
public class ProductDetailsPageServlet extends HttpServlet {
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
			pstmt = conn.prepareStatement("select * from product_details");
			rs = pstmt.executeQuery();

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>Employee Details</title></head>");
		out.println("<body>");
		out.println("<a href='index.html'>Back To Home</a>");
		out.println("<br><br>");

		out.println("<h1>Add Product Details</h1>");
		out.println("<br>");

		out.println("<form name='insertProductForm' method='post' action='insertProduct'>");

		out.println("<label>Product ID</label>&nbsp;&nbsp;");
		out.println("<input type='text' name='productId'/>");

		out.println("<br><br>");

		out.println("<label>Dry Quantity</label>&nbsp;");
		out.println("<input type='text' name='dry'/>");

		out.println("<br><br>");

		out.println("<label>Raw Quantity</label>&nbsp;");
		out.println("<input type='text' name='raw'/>");

		out.println("<br><br>");

		out.println("<label>Price</label>&nbsp;");
		out.println("<input type='text' name='price'/>");

		out.println("<br><br>");

		out.println("<input type='submit' name='Save'/>");

		out.println("</form");

		out.println("<h1>Product List</h1>");
		out.println("<table border='1'>");
		out.println("<tr><th>Product ID</th><th>Dry Quantity</th><th>Raw Quantity</th><th>Price</th></tr>");

		try {
			while (rs.next()) {

				out.println("<tr>");
				try {
					out.println("<td>" + rs.getInt("product_id") + "</td>");
				} catch (SQLException e) {

					e.printStackTrace();
				}
				out.println("<td>" + rs.getInt("dry_quantity") + "</td>");
				out.println("<td>" + rs.getInt("raw_quantity") + "</td>");
				out.println("<td>" + rs.getInt("product_price") + "</td>");
				out.println("</tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		out.println("</table>");
		out.println("</body>");
		out.println("</html>");

	}

}
