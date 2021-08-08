<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>图书管理</title>
		<link rel="shortcut icon" href="favicon.ico/u.ico"></link>
		<%@ include file="../page/common/baseScript.jsp" %>
		
		<script type="text/javascript">
			$(function(){
				$(".deleteClass").click(function(){
					/*	confirm是确认提示框函数
					 *	点击确定，返回true，点击取消，返回false
					 *	return false：阻止元素的默认行为，即不删除该图书
					 */
		return confirm("你是要删除《"+ $(this).parent().parent().find("td:first").text()+"》图书吗？");
				});
			});
		</script>
		<%@ include file="/page/common/pageSubmit.jsp"%>
		
		<%--
			书城页面的进入步骤：
				注册--->登录--->首页--->后台管理--->图书管理
	 	--%>
	
		<style type="text/css">
			#main{
				position:absolute;
				top:160px;
				left:400px;
			}
			#div0{
				font-size:20px;
				position:absolute;
				top:130px;
				left:400px;
			}
			.xx,.xxx{
				color:red;
			}
			
		</style>
	
	</head>
	<body>
		<%@ include file="/page/common/menudiv.jsp" %>
		<br><br>
		
		<div id="main">
			<table cellspacing="0" border="1">
				<tr>
					<th width="180px">名字</th>
					<th width="60px">价格</th>
					<th width="90px">作者</th>
					<th width="60px">销量</th>
					<th width="60px">库存</th>
					<th colspan="2" width="128px">操作</th>
				</tr>
				<c:forEach items="${page.list}" var="book">
					<tr>
						<td>${book.name}</td>
						<td>${book.price}</td>
						<td>${book.author}</td>
						<td>${book.sales}</td>
						<td>${book.stock}</td>
						<td width="60px">
						
						<%--
								jsp页面无法直接获取数据库的图书信息显示到表单中
								需要跳转到servlet程序，查询数据库的数据，再回传表单
						 --%>
						
							<a class="updateClass" 
							href="book/manager/updateQuery?id=${book.id}&meth=update&pageNo=${page.pageNum}">
								修改
							</a>
						</td>
						<td width="60px">
							<a class="deleteClass"
							 href="book/manager/delete?id=${book.id}&pageNo=${page.pageNum}">
								删除
							</a>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="7" style="text-align:center;height:30px">
						<a href="manager/edit.jsp?meth=add&pageNo=${page.total}">添加图书</a>
					</td>
				</tr>
				
				
				<%-- 没有进入图书显示的页面前（没有进入servlet程序前），隐藏分页 --%>
				<c:if test="${empty page.total?false:true}">
					<tr>
						<td colspan="7" style="text-align:center;height:50px">
							
							<%-- 静态包含 --%>
							<%@ include file="/page/common/paging.jsp" %>	
							
						</td>
					</tr>
				</c:if>
				
			</table>
		</div>
	</body>
</html>