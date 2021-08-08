<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- 只有管理员账号才可以对售卖图书进行管理 -->
	<c:if test="${sessionScope.user.username=='admin'}">
		<div id="div0">
			<a class="xx" href="book/manager.request" >图书管理</a>
			<a class="xx" href="${pageScope.basepath}order/manager/${sessionScope.user.id}">订单管理</a>
			<a class="xxx" href="${pageScope.basepath}">返回商城</a>
		</div>
	</c:if>
	
	<c:if test="${sessionScope.user.username!='admin'}">
		<div id="div0div0">
			<a class="xx" href="${pageScope.basepath}order/manager/${sessionScope.user.id}" >订单管理</a>
			<a class="xxx" href="${pageScope.basepath}">返回商城</a>
		</div>
	</c:if>