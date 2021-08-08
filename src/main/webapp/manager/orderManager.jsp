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
				left:400px;
			}
			#div0div0,#div0{
				font-size:20px;
				position:absolute;
				top:50px;
				left:400px;
			}
			#null{
				position: absolute;
				top: 45%;
				left: 50%;
				transform: translate(-50%,-50%);
			}
		</style>
</head>
	<body>
		
		<div id="header">
			<%-- 静态包含 manager管理模块的菜单  --%>
			<%@include file="/page/common/menudiv.jsp"%>
		</div>
		
		<div id="null">
			<%--如果订单记录空的情况--%>
			<c:if test="${empty order}">
				<a href="index.jsp" style="font-size: 24px;color:red">
					亲，当前订单记录为空！快跟小伙伴们去浏览商品吧！！！
				</a>
			</c:if>
		</div>
		
		<div id="main">
			<%--如果订单记录非空的情况--%>
			<c:if test="${not empty order}">
				<table cellspacing="0" border="1">
					<tr>
						<th width="180px">订单号</th>
						<th width="180px">日期</th>
						<th width="120px">金额</th>
						<th width="100px">详情</th>
						<th width="80px">发货</th>
						
					</tr>	
					<!-- 转换日期格式一定要加<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> -->
					<c:forEach items="${order}" var="i">
						<tr>
							<td>${i.orderId}</td>
							<td><fmt:formatDate value="${i.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${i.price}</td>
							<td><a href="${pageScope.basepath}order/detail/${i.orderId}">查看详情</a></td>
							<td>
								<c:choose>
									<c:when test="${i.status==0}">未发货</c:when>
									<c:when test="${i.status==1}">待签收</c:when>
									<c:when test="${i.status==2}">已签收</c:when>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		
	</body>
</html>