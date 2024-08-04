<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Students List</h1>
	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Mark</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="st" items="${list}">
			<tr>
				<td>${st.id}</td>
				<td>${st.name}</td>
				<td>${st.mark}</td>
				<td><a href="editstudent/${st.id}">Edit</a></td>
				<td><a href="deletestudent/${st.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="student">Add New Student</a>

</body>
</html>