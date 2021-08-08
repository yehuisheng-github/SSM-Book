<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>结算页面</title>
		<%@ include file="../page/common/baseScript.jsp" %>
		<style type="text/css">
			h1 {
				text-align: center;
				margin-top: 140px;
				color: #996600;
			}
			#orderdetail{
				font-size:36px;
				text-align: center;
				margin-top: 180px;
				color: #996600;
			}
			#div0{
				margin-top: 100px;
				margin-left: 100px;
				font-size: 26px;
			}
			#div0div0{
				margin-top: 100px;
				margin-left: 100px;
				font-size: 26px;
			}
		</style>
	</head>
	<body>
		<%@ include file="/page/common/menudiv.jsp" %>
		<c:if test="${empty sessionScope.orderId}">
			<h1>错误！库存不足或数量小于零！请重新修改购物车中的图书数量！</h1>
		</c:if>
		<c:if test="${!empty sessionScope.orderId}">
			<div id="orderdetail">
				<a href="" style="color:#996600;">你的订单已结算！订单号为: ${sessionScope.orderId}！</a>
			</div>
		</c:if>
		
	</body>
</html>


