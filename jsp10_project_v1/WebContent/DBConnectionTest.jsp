<%@ page import="db.JDBCUtility"%>
<%@ page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>   
</head>
<body>
	<div class="container" align="center">
		<h3>DB 연결 Test</h3>
		<%
			try(Connection conn = JDBCUtility.getConnection()) {
				out.println("<p class='bg bg-success text-white'>DB 연결 성공</p>");
			} catch(Exception e) {
				out.println("DB 연결 실패" + e.getMessage());
				application.log("DB 연결 실패", e);
			}
		%>
	</div>
</body>
</html>