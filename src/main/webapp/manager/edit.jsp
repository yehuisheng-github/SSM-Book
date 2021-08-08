<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>图书编辑</title>
		<link rel="shortcut icon" href="favicon.ico/abc.ico"></link>
		<%@ include file="../page/common/baseScript.jsp" %>
		
		<script type="text/javascript">
			$(function(){
				
				// 给商品库存、销量和价格实现有效边境处理
				$("#sales,#stock,#price").change(function () {
					
					//正则表达式
					var count = this.value.trim();
					var patt=new RegExp("^[0-9]d*","");
					if(!patt.test(count)){
						alert("文本框不能输入小于0的数字！");
						return false;
					}
					
				});
			});
		</script>
		
		<style type="text/css">
			#main{
				position:absolute;
				top:120px;
				left:250px;
			}
			#div0{
				font-size:20px;
				position:absolute;
				top:90px;
				left:250px;
			}
			.xx{
				color:red;
			}
			.yy{
				border:none;
				BORDER-TOP-STYLE:none;
				BORDER-RIGHT-STYLE:none;
				BORDER-LEFT-STYLE:none;
				BORDER-BOTTOM-STYLE:none;
			}
		</style>
		
	</head>
	<body>
		<%@ include file="/page/common/menudiv.jsp" %>
		<br><br>
			
		<div id="main">
			<table cellspacing="0" border="1">
				<%--
					添加和修改，两个功能共用edit.jsp
						1、可通过bookManager.jsp中a标签内的浏览器地址栏参数获取哪个方法
						2、可以判断浏览器地址栏是否包含id参数
							value=${empty param.id?"add":"update"}
				 --%>
				<form action="book/manager/${param.meth}.request">
					<input type="hidden" name="pageNo"value="${param.pageNo}"/>
					<input type="hidden" name="id" value="${bookData.id}"/>
					<tr>
						<th>名字</th>
						<th>价格</th>
						<th>作者</th>
						<th>销量</th>
						<th>库存</th>
						<th colspan="2">操作</th>
					</tr>
					<tr>
			<td>
				<input type="text" name="name" id="name" value="${bookData.name}" size="40px" class="yy"/>
			</td>
			<td>
				<input type="text" name="price" id="price" hiddenPageNo="${param.pageNo}"
				hiddenId="${bookData.id}" value="${bookData.price}" class="yy"/>
			</td>
			<td>
				<input type="text" name="author" id="author" value="${bookData.author}" class="yy"/>
			</td>
			<td>
				<input type="text" name="sales" id="sales" hiddenPageNo="${param.pageNo}"
				hiddenId="${bookData.id}" value="${bookData.sales}" class="yy"/>
			</td>
			<td>
				<input type="text" name="stock" id="stock" hiddenPageNo="${param.pageNo}"
				hiddenId="${bookData.id}" value="${bookData.stock}" class="yy"/>
			</td>
			<td colspan="2">
				<input type="submit" value="提交" id="sub" class="yy"/>
			</td>
					</tr>
				</form>
			</table>
		</div>
	</body>
</html>