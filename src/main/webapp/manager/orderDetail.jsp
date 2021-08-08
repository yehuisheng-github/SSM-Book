<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>订单管理</title>
		<%@ include file="../page/common/baseScript.jsp" %>
		<style type="text/css">
			#main{
				position:absolute;
				top:80px;
				left:200px;
			}
			#div0div0,#div0{
				font-size:20px;
				position:absolute;
				top:50px;
				left:200px;
			}
		</style>
</head>
	<body>
		
		<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<%-- 静态包含 manager管理模块的菜单  --%>
			<%@include file="/page/common/menudiv.jsp"%>
		</div>
		
		<div id="main">
			<table cellspacing="0" border="1">
				<tr>
					<th width="180px">订单号</th>
					<th width="180px">商品</th>
					<th width="120px">单价</th>
					<th width="120px">数量</th>
					<th width="120px">总价</th>
					<th width="180px">日期</th>
					<th width="80px">发货</th>
					<th width="120px" colspan="3">操作</th>
					
				</tr>	
				<!-- 转换日期格式一定要加<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> -->
				<c:forEach items="${orderItem}" var="i">
					<tr>
						<td>${i.orderId}</td>
						<td>${i.name}</td>
						<td>${i.price}</td>
						<td>${i.count}</td>
						<td>${i.totalPrice}</td>
						<td><fmt:formatDate value="${i.order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
							<c:choose>
								<c:when test="${i.order.status==0}">未发货</c:when>
								<c:when test="${i.order.status==1}">待签收</c:when>
								<c:when test="${i.order.status==2}">已签收</c:when>
							</c:choose>
						</td>
						<td width="40px">
							<a class="updateClass" href="#">
								修改
							</a>
						</td>
						<td width="40px">
							<a class="deleteClass" href="#">
								取消
							</a>
						</td>
						<td width="40px">
							<a class="deleteClass" href="#">
								售后
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
	</body>
</html>