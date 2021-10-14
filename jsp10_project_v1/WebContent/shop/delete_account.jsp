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
     		background-color: #df4759;
    		border: 0 !important;
    	}
    </style>   
</head>
<body>
	<div class="container" align="center">
		<div class="jumbotron">
			<h3>Delete Account!</h3>
		</div>
		<c:choose>
			<c:when test="<%= member == null %>">
				<h4 class="bg-warning text-white">로그인 상태가 아닙니다. 로그인 후 이용하세요!</h4>
				<a href="/logInForm.sh" class="text-white btn btn-primary login-btn mt-sm-5 mr-sm-3">Log in</a>
				<a href="/" class="text-white btn btn-primary login-btn mt-sm-5"><i class="fas fa-home fa-lg"></i></a>		
			</c:when>
			<c:otherwise>
				<h4 class="bg-danger text-white"><%=member.getMem_name()%>님, 탈퇴 확인을 위해 비밀번호를 다시 입력해 주세요!</h4>
				<br />
				<div class="container" align="center" style="width:362px">
					<form action="/deleteAccount.sh" method="post">
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user" class="icon-cog" style="color:white"></i></span>
							</div>
							<input name="id" value="<%=member.getMem_id()%>" type="text" class="form-control" readonly/>
						</div>
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key" class="icon-cog" style="color:white"></i></span>
							</div>
							<input name="pw" type="password" class="form-control" placeholder="PASSWORD..." required/>
						</div>
						<div class="form-group mt-md-5">
							<a href="javascript:history.go(-1)" class="text-white btn btn-success float-right login-btn">Previous</a>
							<input type="reset" value="Reset" class="text-white btn btn-success float-right login-btn mr-sm-3"/>
							<input type="submit" value="Delete" class="text-white btn btn-danger float-right login-btn mr-sm-3"/>
						</div>
					</form>
				</div>				
			</c:otherwise>
		</c:choose>					
	</div>
</body>
</html>