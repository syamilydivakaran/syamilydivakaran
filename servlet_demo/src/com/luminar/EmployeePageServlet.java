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

@WebServlet("/employees")
public class EmployeePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			pstmt = conn.prepareStatement("select * from employee");
			rs = pstmt.executeQuery();

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>Employee Details</title>");
		out.println("<body>");
		out.println("<a href='index.html'>Back To Home</a>");
		out.println("<br><br>");

		// ---------add employee----------

		out.println("<h2>Add Employee Details</h2>");
		out.println("<br>");
		
		
		
		
		
		
		out.println("<script>"
				+ "function valForm()"
				+ "{"
				+ "if(document.insertEmployeeForm.empName.value=='')"
						+ "{"
						+ "alert('Enter Employee name');"
								+ "document.insertEmployeeForm.empName.focus();"
								+ "return false;"
								+ "}"
								+ "if(document.insertEmployeeForm.empAddr.value=='')"
										+ "{"
										+ "alert('Enter Employee Address');"
										+ "document.insertEmployeeForm.empAddr.focus();"
										+ "return false;"
										+ "}"
										+ "if(document.insertEmployeeForm.empAadhar.value=='')"
												+ "{"
												+ "alert('Enter Employee Aadhar');"
												+ "document.insertEmployeeForm.empAadhar.focus();"
												+ "return false;"
												+ "}"
												+ "if(document.insertEmployeeForm.empPhone.value=='')"
														+ "{"
														+ "alert('Enter Employee Phone number');"
														+ "document.insertEmployeeForm.empPhone.focus();"
														+ "return false;"
														+ "}"
														+ "if(document.insertEmployeeForm.empSalary.value=='')"
																+ "{"
																+ "alert('Enter Employee Salary');"
																+ "document.insertEmployeeForm.empSalary.focus();"
																+ "return false;"
																+ "}"
																+ "if(isNaN(document.insertEmployeeForm.empAadhar.value))"
																+ "{"
																+ "alert('Enter valid Aadhar');"
																+ "document.insertEmployeeForm.empAadhar.focus();"
																+ "return false;"
																+ "}"
																+ "if(isNaN(document.insertEmployeeForm.empPhone.value))"
																+ "{"
																+ "alert('Enter valid Phone number');"
																		+ "document.insertEmployeeForm.empPhone.focus();"
																		+ "return false;"
																		+ "}"
																		+ "document.insertEmployeeForm.submit();"
																		+ "}"
																		+ "</script></head>");

	out.println("<form name='insertEmployeeForm' method='post' action='insertEmployee'>");

	out.println("<label>ID</label>&nbsp;&nbsp;");out.println("<input type='text' name='empId'/>");

	out.println("<br><br>");

	out.println("<label>Name</label>&nbsp;");out.println("<input type='text' name='empName'/>");

	out.println("<br><br>");

	out.println("<label>Address</label>&nbsp;");out.println("<input type='text' name='empAddr'/>");

	out.println("<br><br>");

	out.println("<label>Aadhar</label>&nbsp;");out.println("<input type='text' name='empAadhar'/>");

	out.println("<br><br>");

	out.println("<label>Phone</label>&nbsp;");out.println("<input type='text' name='empPhone'/>");

	out.println("<br><br>");

	out.println("<label>Basic salary</label>&nbsp;");out.println("<input type='text' name='empSalary'/>");

	out.println("<br><br>");

	out.println("<input type='submit' name='Save'  onclick='return valForm();'/>");

	out.println("</form");

	// ---------employee details----------

	out.println("<br><br>");

	out.println("<h1>Employee Details</h1>");out.println("<table border='1'>");out.println("<tr><th>ID</th>"+"<th>Name</th>"+"<th>Address</th>"+"<th>Aadhar</th>"+"<th>Phone no.</th>"+"<th>Salary</th>"+"<th></th>"+"<th></th></tr>");

	try

	{
		while (rs.next()) {
			out.println("<tr>" + "<td>" + rs.getInt(1) + "</td>" + "<td>" + rs.getString(2) + "</td>" + "<td>"
					+ rs.getString(3) + "</td>" + "<td>" + rs.getString(4) + "</td>" + "<td>" + rs.getString(5)
					+ "</td>" + "<td>" + rs.getString(6) + "</td>" + "<form method='post' action='deleteEmployee'>"
					+ "<input type='hidden' name='empId' value='" + rs.getInt(1) + "'/>"
					+ "<td><input type='submit' value='Delete'/></td>" + "</form></td>"
					+ "<td><form method='get' action='updateEmployeePage'>"
					+ "<input type='hidden' name='empId' value='" + rs.getInt(1) + "'/>"
					+ "<td><input type='submit' value='Update'/></td>" + "</form></td>" + "</tr>");

		}
	}catch(
	SQLException e)
	{
		e.printStackTrace();
	}

	out.println("</table>");

	out.println("</body></html>");

}
}
