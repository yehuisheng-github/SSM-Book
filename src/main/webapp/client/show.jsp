<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>图书显示</title>
		<link rel="shortcut icon" href="favicon.ico/m.ico"></link>
		<%@ include file="../page/common/baseScript.jsp" %>
		
		<style type="text/css">
			#div0{
				position:absolute;
				top:70px;
				left:1100px;
				font-size:20px;
			}
			#div0div0{
				position:absolute;
				top:70px;
				left:1180px;
				font-size:20px;
			}
			#cartlogin{
				position:absolute;
				top:60px;
				left:150px;
				font-size:20px;
			}
			.xx,.xxx{
				color:red;
			}
			#main{
				border:2px solid black;
				width: 1200px;
				height: 500px;
				position:absolute;
				top:100px;
				left:150px;
			}
			#div1{
				width:220px;
				float:left;
				border:1px solid #c6c6c6;
				margin:0px 31px 0px 31px;
			}
			#dispage{
				clear:both;
				text-align:center;
			}
			.fon{
				text-align:center;
			}
			.none{
				width:31px;
				height:100px;
				float:left;
			}
			#img{
				width:159px;
				border:1px solid #c6c6c6;
				/*
				 *	margin属性：每个div都是一个方块，margin就是设置div方块与下一个元素的间隔
				 *	margin:上 右 下 左
				 */
				margin:17px 30px 10px 30px;
			}
		</style>
		
		<%@ include file="/page/common/pageSubmit.jsp"%>
		
	</head>
	<body>
	
		<%@ include file="/page/common/menudiv.jsp" %>
		<%@ include file="/page/common/cartLogin.jsp" %>
		
		<div id="main">
			<br>
			<form action="book/byPrice.request">
				<div class="fon">
					价格：<input type="text" name="min" value="${min}" size="3px">
					元&nbsp;—&nbsp;
					<input type="text" name="max"  value="${max}" size="3px">元
					<input type="submit" value="查询"/>
				</div>
			</form><br>
			
			<!-- 假如购物车为空 -->
			<c:if test="${empty sessionScope.cart.items}">
				<div class="fon" style="color:#993333">
					亲，当前购物车为空！
				</div><br>
				<div class="fon" style="color:#993333">
					快跟小伙伴们去浏览商品吧！！！
				</div><br>
			</c:if>
			
			<!-- 假如购物车不为空 -->
			<c:if test="${!empty sessionScope.cart.items}">
				<div class="fon">
					您的购物车中有<span id="carttotal"> ${sessionScope.cart.totalCount} </span>件商品
				</div><br>
				<div class="fon">
					您刚刚把<span id="cartname">《${sessionScope.lastName}》</span>加入到购物车中
				</div><br>
			</c:if>
			
			<div class="none"></div>
			
			<c:forEach items="${page.list}" var="i">
				<div id="div1">
					<div>
						<img  id="img" alt="" src="page/re/img.jpg"/>
					</div>
					<div>名字：${i.name}</div>
					<div>价格：${i.price}</div>
					<div>作者：${i.author}</div>
					<div>销量：${i.sales}</div>
					<div>库存：${i.stock}</div>
					<div><button bookId="${i.id}" class="addCart">加入购物车</button></div>
				</div>
			</c:forEach>
				
			<%@ include file="/page/common/paging.jsp" %>	
			
		</div>
	</body>
</html>