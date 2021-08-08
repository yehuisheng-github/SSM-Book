<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>购物车</title>
		<link rel="shortcut icon" href="favicon.ico/m.ico"></link>
		<%@ include file="../page/common/baseScript.jsp" %>
		
		
		<script type="text/javascript">
			$(function () {
					
				// 删除购物车商品
				$(".deleteItem").click(function () {
					var flag=confirm("你确定要删除《" + 
							$(this).parent().parent().find("td:first").text() +"》吗?")
					if(flag==true) {
						var bookId = $(this).attr("bookId");
						$.ajax({
							url:"${pageScope.basepath}cart/delete.request",
							data:{
								id:bookId
							},
							type:"get",
							datatype:"json",
							success:function (event) {
								//删除成功后刷新页面
								location.reload();
								alert(event);
							}
						});
					}else{
						return false;
					}
				});
					
				// 清空购物车
				$("#clearCart").click(function () {
					if(confirm("你确定要清空购物车吗?")) {
						$.ajax({
							url:"${pageScope.basepath}cart/clear.request",
							type:"get",
							datatype:"json",
							success:function (event) {
								//清空后刷新页面
								location.reload();
								alert(event);
							}
						});
					}else{
						return false;
					}
				});
					
				// 修改购物车的商品数量
				$(".updateCount").change(function () {
					
					//正则表达式
					var count = this.value.trim();
					var patt=new RegExp("^[1-9]d*","");
					if(!patt.test(count)){
						alert("文本框不能输入大于1的数字！");
						location.replace("${pageScope.basepath}client/cart.jsp");
						return false;
					}
					
					var id = $(this).attr('bookId');
					$.ajax({
						url:"${pageScope.basepath}cart/update.request",
						data:{
							id:id,
							count:count
						},
						type:"get",
						datatype:"json",
						success:function (event) {
							location.reload();
						}
					});
				});
	
			});
		</script>
			
		<style type="text/css">
			#header,#main{
				position: absolute;
				top: 45%;
				left: 50%;
				transform: translate(-50%,-50%);
			}
		</style>
	</head>
	<body>
		<div id="header">
			<%--如果购物车空的情况--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5">
						<a href="index.jsp" style="font-size: 30px;color:red">
							亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！
						</a>
					</td>
				</tr>
			</c:if>
		</div>
		<div id="main">	
			<%--如果购物车非空的情况--%>
			<c:if test="${not empty sessionScope.cart.items}">
			
				<a href="index.jsp" style="font-size:24px">返回商城</a>
				<table  cellspacing="0" border="1">
					<tr>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>金额</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${sessionScope.cart.items}" var="entry">
						<tr>
							<td  width="220px">${entry.value.name}</td>
							<td>
								<input class="updateCount" style="width: 80px;"
								bookId="${entry.value.id}" stoke="${sessionScope.cart.items.stoke}"
								type="text" value="${entry.value.count}"/>
							</td>
							<td width="100px">${entry.value.price}</td>
							<td width="100px">${entry.value.totalPrice}</td>
							<td>
								<button class="deleteItem" BookId="${entry.value.id}">
									删除
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			
			<%--如果购物车非空才输出页面的内容--%>
			<c:if test="${not empty sessionScope.cart.items}">
				<br>
				<div class="cart_info">
					<span class="cart_span">
						购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品
					</span>
					&nbsp;&nbsp;
					<span class="cart_span">
						总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元
					</span>
					&nbsp;&nbsp;
					<span class="cart_span">
						<button id="clearCart">清空购物车</button>
					</span>
					&nbsp;&nbsp;
					<span class="cart_span">
						<a href="order/add.request">去结账</a>
					</span>
				</div>
			</c:if>
		</div>
		
	</body>
</html>