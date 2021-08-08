<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>会员登录页面</title>
		<link rel="shortcut icon" href="favicon.ico/uuu.ico"></link>
		<%@ include file="/page/common/baseScript.jsp" %>
		
		<script type="text/javascript">
			$(function(){
				//提交表单项时，判断用户名和密码是否为空
				$("#sub_btn").click(function(){
					var usernameText=$("#username").val().trim();
					var passwordText=$("#password").val().trim();
					if(usernameText==null || usernameText==""){
						$("#div0").html(
	"&nbsp;&nbsp;&nbsp;&nbsp;<img class='dp' alt='' src='page/re/dp.jpg'></img>&nbsp;&nbsp;用户名不能为空");
						return false;
					}
					if( passwordText==null || passwordText==""){
						$("#div0").html(
	"&nbsp;&nbsp;&nbsp;&nbsp;<img class='dp' alt='' src='page/re/dp.jpg'></img>&nbsp;&nbsp;密码不能为空");
						return false;
					}
				});
			});
		</script>
		
		<style type="text/css">
			<%@ include file="/page/common/testCss.jsp"%>
			a{
				text-decoration:none;
				color:red;
			}
			#div0{
				background-color:yellow;
			}
			.dp{
				background-color:yellow;
				width:11px;
				height:16px;
				margin:1px auto -3px auto;
			}
		</style>
	</head>
	<body>
		<%@ include file="/page/common/logo.jsp" %>
		<div class="login_banner">
			<div id="1_content">
				<span class="login_word">欢迎登录</span>
			</div>
			<div id="content">
				<div class="login_form">
					<div class="login_box">
						<div class="login_title">
							<h2>登录书城会员</h2>
							<span><a href="page/regist.jsp">立即注册</a></span>
						</div>
						<div id="div1"></div>
						<br><div id="div0">
							&nbsp;&nbsp;&nbsp;
							<img class="dp" alt="" src="page/re/dp.jpg"></img>
							&nbsp;
							
					<%-- 
						在servlet程序中判断用户名和密码是否正确，错误：设置错误信息并存放域对象中
								
						错误信息假如为空（这个时候是第一次进入登陆页面的状态），提示用户：输入用户名和密码
							（刚进去登陆页面的时候，还没有访问servlet程序，servlet程序还没有保存错误信息到域对象
							所以这个时候request.getAttribute("msg")的值为：null）
						
						不为空，跳转登陆页面，提示用户：用户名或密码错误，登陆失败
							（servlet程序判断登录信息错误，并且设置了错误信息，所以这个时候直接输出设置的错误信息）
							
	表达式脚本：<%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg")%> 
					--%>
				
				${empty requestScope.msg?"请输入用户名和密码":requestScope.msg}
		
						</div><br>
						<div id="div2"></div>
						<div class="form">
						
						<%-- 提交表单项给服务器中的servlet程序 --%>
							<form action="user/login.request" method="post">
								<label>用户名称：</label>
								<input class="itxt" type="text" placeholder="请输入用户名"
								autocomplete="off" tabindex="1" name="username" id="username"
		
		<%-- 
			value属性为文本框内的内容，初始化状态下，还没有访问servlet程序，
			servlet程序还没有保存表单项信息到域对象当中，所以文本框为空
			当提交表单时，假如登陆失败，servlet程序就会获取文本框的内容，保存到域对象当中
			并且跳回登陆页面，登陆页面判断域对象有值（值就是第一次登录时输入的用户名）
			就把域对象中的值赋值给文本框
			
表达式脚本：value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"/>

			当输出的值是null时，表达式脚本输出的是：null，EL表达式输出的是空字符串
			所以使用EL表达式不需要判断是否为空
		--%>
						value="${sessionScope.username}"/>
		
								<br><br>
								<label>用户密码：</label>
								<input class="itxt" type="password" placeholder="请输入密码"
								autocomplete="off" tabindex="1" name="password" id="password"/>
								<br><br>
								<input type="submit" value="登录" id="sub_btn"/>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
