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
			<h3>Account Info</h3>
		</div>
		<c:choose>
			<c:when test="<%= member == null %>">
				<h4 class="bg-warning text-white ">로그인 상태가 아닙니다. 로그인 후 이용하세요!</h4>
				<a href="/logInForm.sh" class="text-white btn btn-primary login-btn mt-sm-5 mr-sm-3">Log in</a>
				<a href="/" class="text-white btn btn-primary login-btn mt-sm-5"><i class="fas fa-home fa-lg"></i></a>		
			</c:when>
			<c:otherwise>
				<a href="/memberDetail.sh" class="btn btn-success float-right">
					<i class="fas fa-user-check" class="icon-cog" style="color:black"></i>&nbsp&nbsp<%=member.getMem_name()%>
				</a>
				<a href="/logOut.sh" class="text-white btn btn-warning float-right login-btn mr-sm-3">Log out</a>
				<a href="/" class="text-white btn btn-success float-right login-btn mr-sm-3"><i class="fas fa-home fa-lg"></i></a>
				<br /><br />
				<table class="table table-dark table-striped">
					<tr>
						<td style="width:30%">아이디</td>
						<td><%=member.getMem_id()%></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><%=member.getMem_name()%></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><%=member.getMem_addr()%></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><%=member.getMem_tel()%></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><%=member.getMem_email()%></td>
					</tr>
					<tr>
						<td>포인트</td>
						<td><%=member.getMem_pts()%></td>
					</tr>
					<tr>
						<td>가입일</td>
						<td><%=member.getMem_date()%></td>
					</tr>
				</table>
				<div class="mt-sm-5">
					<a href="/orderList.sh" class="text-white btn btn-success float-right login-btn ml-sm-3">Purchases</a>
					<a href="/memberModifyForm.sh" class="text-white btn btn-warning float-right login-btn ml-sm-3">Edit</a>
					<a href="/deleteAccountForm.sh" class="text-white btn btn-danger float-right login-btn">Delete</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>