<%@ page import="shop.vo.PageInfo"%>
<%@ page import="shop.vo.MemberBean"%>
<%@ page import="shop.vo.ProductBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<ProductBean> productList = (ArrayList<ProductBean>) request.getAttribute("productList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
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
	<c:choose>
		<c:when test="<%= productList == null %>">
			<div class="container" align="center">
				<div class="jumbotron">
					<h3>You seem lost!</h3>
				</div>
				<h4 class="bg-warning text-white">정상적인 접근이 아닙니다. 홈페이지로 이동하세요!</h4>
				<a href="/" class="text-white login-btn btn btn-primary mt-sm-5"><i class="fas fa-home fa-lg"></i></a>
			</div>
		</c:when>
		<c:otherwise>
			<%	
				int totalPage = pageInfo.getTotalPage();
				int startPage = pageInfo.getStartPage();
				int endPage = pageInfo.getEndPage();
				int numOfPages = pageInfo.getNumOfPages();
				int limit = pageInfo.getLimit();
				int sort = pageInfo.getSort();
			%>
			<div class="container" align="center">
				<div class="jumbotron">
					<h3>Spend Money and Enjoy Your Life!</h3>
				</div>
				<div class="dropdown">
					<button class="text-black mr-sm-3 float-left btn btn-light login-btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						정렬
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="/productList.sh?sort=0&limit=<%= limit %>">기본 정렬</a>
						<a class="dropdown-item" href="/productList.sh?sort=1&limit=<%= limit %>">가격 높은 순</a>
						<a class="dropdown-item" href="/productList.sh?sort=2&limit=<%= limit %>">가격 낮은 순</a>
						<a class="dropdown-item" href="/productList.sh?sort=3&limit=<%= limit %>">재고 많은 순</a>
						<a class="dropdown-item" href="/productList.sh?sort=4&limit=<%= limit %>">재고 적은 순</a>
					</div>
				</div>
				<div class="dropdown">	
					<button class="text-black float-left btn btn-light login-btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						품목수
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="/productList.sh?limit=10&sort=<%= sort %>">10개</a>
						<a class="dropdown-item" href="/productList.sh?limit=15&sort=<%= sort %>">15개</a>
						<a class="dropdown-item" href="/productList.sh?limit=20&sort=<%= sort %>">20개</a>
					</div>
					<c:choose>
						<c:when test="<%= member == null %>">
							<a href="/logInForm.sh" class="text-white btn btn-primary login-btn float-right">Log in</a>							
							<a href="/" class="text-white btn btn-primary login-btn float-right mr-sm-3"><i class="fas fa-home fa-lg"></i></a>
						</c:when>		
						<c:otherwise>
							<a href="/memberDetail.sh" class="btn btn-success float-right">
								<i class="fas fa-user-check" class="icon-cog" style="color:black"></i>&nbsp&nbsp<%=member.getMem_name()%>
							</a>
							<a href="/logOut.sh" class="text-white btn btn-warning login-btn float-right mr-sm-3">Log out</a>
							<a href="/" class="text-white btn btn-success login-btn float-right mr-sm-3"><i class="fas fa-home fa-lg"></i></a>
						</c:otherwise>
					</c:choose>					
				</div>
				<br /><br />
				<table class="table table-dark table-striped table-hover">
					<tr class="table-dark">
						<th class="text-center">제품 이름</th>
						<th class="text-center">제품 가격</th>
						<th class="text-center">제품 재고</th>
						<th class="text-center">주문</th>
					</tr>
					<c:forEach var="product" items="${productList}">
						<tr>
							<td align="center"><a href="/productDetail.sh?prd_num=${product.getPrd_num()}">${product.getPrd_name()}</a></td>
							<td align="center">${product.getPrd_price()}</td>
							<td align="center">${product.getPrd_stock()}</td>
							<c:choose>
								<c:when test="${product.getPrd_stock() == 0}">
									<td align="center"><i class="fas fa-times fa-lg" class="icon-cog" style="color:gray"></i></td>
								</c:when>
								<c:otherwise>
									<td align="center"><a href="/productOrderForm.sh?prd_num=${product.getPrd_num()}"><i class="fas fa-shopping-cart fa-lg" class="icon-cog" style="color:#FF7F50"></i></a></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
				<br />
				<hr />
			</div>
			<div class="container">
				<ul class="pagination justify-content-center">
					<c:if test="<%= startPage != 1 %>">
						<li class="page-item"><a href="/productList.sh?page=1&limit=<%= limit %>&sort=<%= sort %>" class="page-link"><i class="fas fa-fast-backward"></i></a></li>
						<li class="page-item"><a href="/productList.sh?page=<%= startPage-numOfPages %>&limit=<%= limit %>&sort=<%= sort %>" class="page-link"><i class="fas fa-backward"></i></a></li>
					</c:if>

					<c:forEach var="page_num" begin="<%= startPage %>" end="<%= endPage %>">
						<li class="page-item"><a href="/productList.sh?page=${page_num}&limit=<%= limit %>&sort=<%= sort %>" class="page-link">${page_num}</a></li>
					</c:forEach>
					
					<c:if test="<%= endPage < totalPage %>">
						<li class="page-item"><a href="/productList.sh?page=<%= endPage+1 %>&limit=<%= limit %>&sort=<%= sort %>" class="page-link"><i class="fas fa-forward"></i></a></li>
						<li class="page-item"><a href="/productList.sh?page=<%= totalPage %>&limit=<%= limit %>&sort=<%= sort %>" class="page-link"><i class="fas fa-fast-forward"></i></a></li>
					</c:if>		
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>