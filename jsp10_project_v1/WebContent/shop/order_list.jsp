<%@ page import="shop.vo.MemberBean"%>
<%@ page import="shop.vo.OrderBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<OrderBean> orderList = (ArrayList<OrderBean>) request.getAttribute("orderList");
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
			<h3>Purchase History</h3>
		</div>
		<!-- <주의> c:choose 안에 주석을 달면 에러가 발생하므로 밖에 위치시켜야 함!!! -->
		<!-- 1. 로그인이 되어 있고 구매 내역도 있는 경우 -->
		<!-- 2. 로그인은 되었지만 구매 내역이 없는 경우 -->
		<!-- 3. 로그인이 되어 있지 않은 경우 -->
		<c:choose>
			<c:when test="<%= member != null && orderList != null %>">
				<a href="/memberDetail.sh" class="btn btn-success float-right">
					<i class="fas fa-user-check" class="icon-cog" style="color:black"></i>&nbsp&nbsp<%=member.getMem_name()%>
				</a>
				<a href="/logOut.sh" class="text-white btn btn-warning login-btn float-right mr-sm-3">Log out</a>
				<a href="/" class="text-white btn btn-success login-btn float-right mr-sm-3"><i class="fas fa-home fa-lg"></i></a>
				<br /><br />
				<table class="table table-dark table-striped table-hover">
					<tr class="table-dark">
						<th class="text-center">주문 번호</th>
						<th class="text-center">제품 번호</th>
						<th class="text-center">주문 수량</th>
						<th class="text-center">총 주문 비용</th>
						<th class="text-center">주문 일자</th>
						<th class="text-center">주문 취소</th>
					</tr>
					<c:forEach var="order" items="${orderList}">
						<tr>
							<td align="center">${order.getOrd_num()}</td>
							<td align="center"><a href="/productDetail.sh?prd_num=${order.getOrd_prd_num()}">${order.getOrd_prd_num()}</a></td>
							<td align="center">${order.getOrd_qty()}</td>
							<td align="center">${order.getOrd_cost()}</td>
							<td align="center">${order.getOrd_date()}</td>
							<td align="center"><a href="/cancelOrder.sh?ord_num=${order.getOrd_num()}&prd_num=${order.getOrd_prd_num()}"><i class="fas fa-undo-alt fa-lg" class="icon-cog" style="color:#FF7F50"></i></a></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:when test="<%= member != null && orderList == null %>">
				<a href="/memberDetail.sh" class="btn btn-success float-right">
					<i class="fas fa-user-check" class="icon-cog" style="color:black"></i>&nbsp&nbsp<%=member.getMem_name()%>
				</a>
				<a href="/logOut.sh" class="text-white btn btn-warning login-btn float-right mr-sm-3">Log out</a>
				<a href="/" class="text-white btn btn-success login-btn float-right mr-sm-3"><i class="fas fa-home fa-lg"></i></a>
				<br /><br /><br />
				<h4 class="bg-success text-white"><%= member.getMem_name() %>님의 구매 내역이 없습니다!</h4>
			</c:when>
			<c:otherwise>	
				<h4 class="bg-warning text-white">로그인 상태가 아닙니다. 로그인 후 이용하세요!</h4>
				<a href="/logInForm.sh" class="text-white btn btn-primary login-btn mt-sm-5 mr-sm-3">Log in</a>
				<a href="/" class="text-white btn btn-primary login-btn mt-sm-5"><i class="fas fa-home fa-lg"></i></a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>