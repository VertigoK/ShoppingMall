<%@ page import="shop.vo.MemberBean"%>
<%@ page import="shop.vo.OrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	OrderBean order = (OrderBean) session.getAttribute("orderInfo");
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
		<c:choose>
			<c:when test="<%= member == null %>">
				<div class="jumbotron">
					<h3>로그인 정보 없음</h3>
				</div>
				<br />
				<h4 class="bg-warning text-white">로그인 상태가 아닙니다. 로그인 후 이용하세요!</h4>
				<a href="/logInForm.sh" class="text-white btn btn-primary login-btn mt-sm-5 mr-sm-3">Log in</a>
				<a href="/" class="text-white btn btn-primary login-btn mt-sm-5"><i class="fas fa-home fa-lg"></i></a>		
			</c:when>
			<c:when test="<%= order == null %>">
				<div class="jumbotron">
					<h3>주문 정보 없음</h3>
				</div>
				<br />
				<h4 class="bg-warning text-white">정상적인 접근이 아닙니다. 홈페이지로 이동하세요!</h4>
				<a href="/" class="text-white btn btn-primary login-btn mt-sm-5"><i class="fas fa-home fa-lg"></i></a>
			</c:when>
			<c:otherwise>
				<div class="jumbotron">
					<h3><%=member.getMem_name()%>님의 주문이 정상적으로 처리되었습니다.</h3>
				</div>
				<a href="/memberDetail.sh" class="btn btn-success float-right">
					<i class="fas fa-user-check" class="icon-cog" style="color:black"></i>&nbsp&nbsp<%=member.getMem_name()%>
				</a>
				<a href="/logOut.sh" class="text-white btn btn-warning login-btn float-right mr-sm-3">Log out</a>
				<a href="/" class="text-white btn btn-success login-btn float-right mr-sm-3"><i class="fas fa-home fa-lg"></i></a>
				<br /><br />
				<table class="table table-striped">
					<tr class="table-striped">
						<td align="center">주문 번호</td>
						<td align="center"><%=order.getOrd_num()%></td>
					</tr>
					<tr>
						<td align="center">제품 번호</td>
						<td align="center"><%=order.getOrd_prd_num()%></td>
					</tr>
					<tr>
						<td align="center">주문 수량</td>
						<td align="center"><%=order.getOrd_qty()%></td>
					</tr>
					<tr>
						<td align="center">주문 비용</td>
						<td align="center"><%=order.getOrd_cost()%></td>
					</tr>
					<tr>
						<td align="center">주문 일자</td>
						<td align="center"><%=order.getOrd_date()%></td>
					</tr>
				</table>		
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>