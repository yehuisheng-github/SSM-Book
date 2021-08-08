<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="cartlogin">

	<!-- 
		用户名不为空：显示个人页面的功能
		用户名为空：显示注册登录功能
	 -->
	<c:if test="${!empty sessionScope.user}">
		<span style="color:blue">
	    	欢迎
	    	<span style="color:#cc00ff;font-size:30px">${sessionScope.user.username}</span>
	    	光临书城  	
    	</span>
    	<a href="user/logout.request">注销</a>
	</c:if>
	
	<c:if test="${empty sessionScope.user}">
		<a href="page/login.jsp">登录</a>&nbsp;&nbsp;
		<a href="page/regist.jsp">注册</a>&nbsp;&nbsp;
		<span style="color:#cc00ff;font-size:30px">${username}</span>
	</c:if>
	
    <a href="client/cart.jsp">购物车</a>
</div>