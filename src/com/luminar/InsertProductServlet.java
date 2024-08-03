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

@WebServlet("/insertProduct")
public class InsertProductServlet extends HttpServlet {
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
		String id = request.getParameter("productId");
		Integer productId = Integer.parseInt(id);

		String dryQuantity = request.getParameter("dry");
		Integer dry = Integer.parseInt(dryQuantity);

		String rawQuantity = request.getParameter("raw");
		Integer raw = Integer.parseInt(rawQuantity);

		String productPrice = request.getParameter("price");
		Integer price = Integer.parseInt(productPrice);

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(
					"INSERT INTO product_details (product_id, dry_quantity, raw_quantity, product_price) VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, productId);
			pstmt.setInt(2, dry);
			pstmt.setInt(3, raw);
			pstmt.setInt(4, price);
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
