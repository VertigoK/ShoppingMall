<%@ page import="shop.vo.MemberBean"%>
<%@ page import="shop.vo.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ProductBean product = (ProductBean) request.getAttribute("product");
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
			<h3>Product Detail</h3>
		</div>
		<c:choose>
			<c:when test="<%= product == null %>">
				<div class="jumbotron">
					<h3>제품 정보 없음</h3>
				</div>
				<br />
				<h4 class="bg-warning text-white">정상적인 접근이 아닙니다. 홈페이지로 이동하세요!</h4>
				<a href="/" class="text-white btn btn-primary login-btn mt-sm-5"><i class="fas fa-home fa-lg"></i></a>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="<%= member == null %>">
						<a href="/logInForm.sh" class="text-white btn btn-primary float-right login-btn">Log in</a>
						<a href="/" class="text-white btn btn-primary float-right login-btn mr-sm-3"><i class="fas fa-home fa-lg"></i></a>
						<a href="javascript:history.go(-1)" class="text-white btn btn-primary float-right login-btn mr-sm-3"><i class="fas fa-chevron-circle-left fa-lg"></i></a>
					</c:when>
					<c:otherwise>
						<a href="/memberDetail.sh" class="btn btn-success float-right">
							<i class="fas fa-user-check" class="icon-cog" style="color:black"></i>&nbsp&nbsp<%=member.getMem_name()%>
						</a>
						<a href="/logOut.sh" class="text-white btn btn-warning login-btn float-right mr-sm-3">Log out</a>
						<a href="/" class="text-white btn btn-success float-right login-btn mr-sm-3"><i class="fas fa-home fa-lg"></i></a>	
						<a href="javascript:history.go(-1)" class="text-white btn btn-success login-btn float-right mr-sm-3"><i class="fas fa-chevron-circle-left fa-lg"></i></a>
					</c:otherwise>				
				</c:choose>
				<br /><br />
				<table class="table table-dark table-striped table-hover">
					<tr class="table-dark">
						<th class="text-center">제품 번호</th>
						<th class="text-center">제품 이름</th>
						<th class="text-center">제품 가격</th>
						<th class="text-center">제품 재고</th>
						<th class="text-center">제조 회사</th>
						<th class="text-center">제조 일자</th>
						<th class="text-center">주문</th>
					</tr>
					<tr>
						<td align="center">${product.getPrd_num()}</td>
						<td align="center">${product.getPrd_name()}</td>
						<td align="center">${product.getPrd_price()}</td>
						<td align="center">${product.getPrd_stock()}</td>
						<td align="center">${product.getPrd_com()}</td>
						<td align="center">${product.getPrd_date()}</td>
						<c:choose>
							<c:when test="${product.getPrd_stock() == 0}">
								<td align="center"><i class="fas fa-times fa-lg" class="icon-cog" style="color:gray"></i></td>
							</c:when>
							<c:otherwise>
								<td align="center"><a href="/productOrderForm.sh?prd_num=${product.getPrd_num()}"><i class="fas fa-shopping-cart fa-lg" class="icon-cog" style="color:#FF7F50"></i></a></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>