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
    	.input-group-prepend span {
    		color: black;
    		width:40px;
    		background-color: #FFC312;
    		border: 0 !important;
    	}
    </style> 
</head>
<body>
	<div class="container" align="center">
		<div class="jumbotron">
			<h3>Sign Up</h3>
		</div>
		<c:choose>
			<c:when test="<%= member != null %>">
				<a href="/memberDetail.sh" class="btn btn-success float-right">
						<i class="fas fa-user-check" class="icon-cog" style="color:black"></i>&nbsp&nbsp<%=member.getMem_name()%>
				</a>
				<a href="/logOut.sh" class="text-white btn btn-warning login-btn float-right mr-sm-3">Log out</a>
				<a href="/" class="text-white btn btn-success login-btn float-right mr-sm-3"><i class="fas fa-home fa-lg"></i></a>
			</c:when>
			<c:otherwise>
				<div class="container" align="center" style="width:364px">
					<form action="/signUp.sh" class="form-group" method="post">
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input name="id" type="text" class="form-control" placeholder="ID..." required/>
						</div>
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input name="pw" type="password" class="form-control" placeholder="PASSWORD..." required/>
						</div>
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user-tag"></i></span>
							</div>
							<input name="name" type="text" class="form-control" placeholder="이름..." required/>
						</div>
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-map-marked-alt"></i></span>
							</div>
							<textarea name="addr" class="form-control" cols="38" rows="3" placeholder="주소..." required></textarea>
						</div>
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-phone-square"></i></span>
							</div>
							<input name="tel" type="text" class="form-control" placeholder="전화번호..." required/>
						</div>
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-envelope"></i></span>
							</div>
							<input name="email" type="text" class="form-control" placeholder="EMAIL..." required/>
						</div>
						<div class="form-group mt-md-5">
							<a href="/" class="text-white btn btn-primary float-right login-btn"><i class="fas fa-home fa-lg"></i></a>
							<input type="reset" value="Reset" class="text-white btn btn-primary float-right login-btn mr-sm-3"/>
							<input type="submit" value="Sign up" class="text-white btn btn-warning float-right login-btn mr-sm-3"/>
						</div>
					</form>
				</div>		
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>