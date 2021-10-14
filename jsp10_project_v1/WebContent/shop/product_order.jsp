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
			<h3>Almost Yours!</h3>
		</div>
		<!-- prd_num, prd_stock, ord_qty의 값을 request parameter로 넘김 -->
		<c:choose>
			<c:when test="<%= member == null %>">
				<h4 class="bg-warning text-white">로그인 상태가 아닙니다. 로그인 후 이용하세요!</h4>
				<a href="/logInForm.sh" class="text-white btn btn-primary mt-sm-5 mr-sm-3">Log in</a>
				<a href="/" class="text-white btn btn-primary mt-sm-5"><i class="fas fa-home fa-lg"></i></a>		
			</c:when>
			<c:otherwise>
				<a href="/memberDetail.sh" class="btn btn-success float-right">
					<i class="fas fa-user-check" class="icon-cog" style="color:black"></i>&nbsp&nbsp<%=member.getMem_name()%>
				</a>
				<a href="/logOut.sh" class="text-white btn btn-warning login-btn float-right mr-sm-3">Log out</a>
				<a href="/" class="text-white btn btn-success float-right login-btn mr-sm-3"><i class="fas fa-home fa-lg"></i></a>	
				<a href="javascript:history.go(-1)" class="text-white btn btn-success login-btn float-right mr-sm-3"><i class="fas fa-chevron-circle-left fa-lg"></i></a>
				<br /><br />
				<form action="/productOrder.sh" method="post">
					<input name="prd_num" type="hidden" value="<%=product.getPrd_num()%>"/>
					<table class="table table-dark table-striped table-hover">
						<tr>
							<td style="width:20%">제품 이름</td>
							<td><input type="text" class="form-control" value="<%=product.getPrd_name()%>" readonly/></td>
						</tr>
						<tr>
							<td style="width:20%">제품 가격</td>
							<td><input type="text" class="form-control" value="<%=product.getPrd_price()%>" readonly/></td>
						</tr>
						<tr>
							<td style="width:20%">제품 재고량</td>
							<td><input name="prd_stock" type="text" class="form-control" value="<%=product.getPrd_stock()%>" readonly/></td>
						</tr>
						<tr>
							<td style="width:20%">주문 수량</td>
							<td><input name="ord_qty" type="text" class="form-control" value="1" required/></td>
						</tr>
					</table>
					<div class="form-group mt-md-5">
						<input type="reset" value="Reset" class="text-white btn btn-success float-right login-btn ml-sm-3"/>
						<input type="submit" value="Order" class="text-white btn btn-warning float-right login-btn"/>
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>