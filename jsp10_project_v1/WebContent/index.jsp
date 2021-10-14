<%@ page import="shop.vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberBean member = (MemberBean) session.getAttribute("loginInfo");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
		.jumbotron
		{
 		background-color: #262626;
		color: white;
		}
    	.login-btn {
    		color: black;
    		width:100px;
    	}
    	.login-btn:hover {
    		color: black;
    		background-color: #D9E1E5;
    	}
    </style>   
</head>
<body>
	<div class="container" align="center">
		<div class="jumbotron">
			<h3>Welcome to KIM'S</h3>
		</div>
		<c:choose>
			<c:when test="<%= member != null %>">
				<a href="/memberDetail.sh" class="btn btn-success float-right">
					<i class="fas fa-user-check" class="icon-cog" style="color:black"></i>&nbsp&nbsp<%=member.getMem_name()%>
				</a>
				<a href="/logOut.sh" class="text-white btn btn-warning login-btn float-right mr-sm-3">Log out</a>
				<a href="/productList.sh" class="text-white btn btn-success login-btn float-right mr-sm-3"><i class="fas fa-gift fa-lg"></i></a>
			</c:when>
			<c:otherwise>
				<a href="/logInForm.sh" class="text-white btn btn-primary login-btn float-right ml-sm-3">Log in</a>
				<a href="/signUpForm.sh" class="text-white btn btn-primary login-btn float-right ml-sm-3">Sign up</a>
				<a href="/productList.sh" class="text-white btn btn-primary login-btn float-right"><i class="fas fa-gift fa-lg"></i></a>
			</c:otherwise>
		</c:choose>
		<br /><br />
		<div>
			<img src="https://images.unsplash.com/photo-1607083206869-4c7672e72a8a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1500&q=80"
				 alt="home" width="100%"/>
		</div>
	</div>
</body>
</html>