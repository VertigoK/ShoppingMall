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
			<h3>Edit Account!</h3>
		</div>
		<c:choose>
			<c:when test="<%= member == null %>">
				<h4 class="bg-warning text-white">로그인 상태가 아닙니다. 로그인 후 이용하세요!</h4>
				<a href="/logInForm.sh" class="text-white btn btn-primary login-btn mt-sm-5 mr-sm-3">Log in</a>
				<a href="/" class="text-white btn btn-primary login-btn mt-sm-5"><i class="fas fa-home fa-lg"></i></a>		
			</c:when>
			<c:otherwise>
				<a href="/memberDetail.sh" class="btn btn-success float-right">
					<i class="fas fa-user-check" class="icon-cog" style="color:black"></i>&nbsp&nbsp<%=member.getMem_name()%>
				</a>
				<a href="/logOut.sh" class="text-white btn btn-warning float-right login-btn mr-sm-3">Log out</a>
				<a href="/" class="text-white btn btn-success float-right login-btn mr-sm-3"><i class="fas fa-home fa-lg"></i></a>
				<a href="javascript:history.go(-1)" class="text-white btn btn-success login-btn float-right mr-sm-3"><i class="fas fa-chevron-circle-left fa-lg"></i></a>
				<br /><br />
				<form action="/memberModify.sh" method="post">
					<table class="table table-dark table-striped table-hover">
						<tr>
							<td style="width:20%">아이디</td>
							<td><input class="form-control" type="text" name="id" value="<%=member.getMem_id()%>" readonly/></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input class="form-control" type="text" name="pw" value="<%=member.getMem_pw()%>" required/></td>
						</tr>
						<tr>
							<td>이름</td>
							<td><input class="form-control" type="text" name="name" value="<%=member.getMem_name()%>" required/></td>
						</tr>
						<tr>
							<td>주소</td>
							<td><input class="form-control" type="text" name="addr" value="<%=member.getMem_addr()%>" required/></td>
						</tr>
						<tr>
							<td>전화번호</td>
							<td><input class="form-control" type="text" name="tel" value="<%=member.getMem_tel()%>" required/></td>
						</tr>
						<tr>
							<td>이메일</td>
							<td><input class="form-control" type="text" name="email" value="<%=member.getMem_email()%>" required/></td>
						</tr>
					</table>
					<input type="reset" value="Reset" class="text-white btn btn-success float-right login-btn mt-sm-5"/>
					<input type="submit" value="Edit" class="text-white btn btn-warning float-right login-btn mt-sm-5 mr-sm-3"/>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>