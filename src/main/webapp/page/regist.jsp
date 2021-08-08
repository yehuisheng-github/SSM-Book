<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>会员注册页面</title>
		<link rel="shortcut icon" href="favicon.ico/aaa.ico"></link>
		<%@ include file="/page/common/baseScript.jsp" %>
		
		<script type="text/javascript">

			$(function(){

				$("#sub").click(function(){
					location.href="${pageScope.basepath}page/login.jsp";
				})
				
				
				//正则表达式这里加双引号下面会报错：usernamePatt.test is not a function
				var patt=new RegExp("[a-zA-Z0-9_]{5,12}","");
				
				// 给验证码的图片绑定单击事件
				$("#code_img").click(function () {
					// src属性表示验证码img标签的 图片路径。它可读，可写
					this.src = "${basePath}kaptcha.jpg?d=" + new Date();
				});
				
				//给注册按钮绑定鼠标点击事件
				$("#sub_btn").click(function(){
					
					var usernameText=$("#username").val().trim();
					if(!patt.test(usernameText)){
						$("span.errorMsg").text("用户名不合法！");
						return false;
					}

					//验证密码：必须由字母、数字和下划线组成，长度5-12位
					var passwordText=$("#password").val().trim();
					if(!patt.test(passwordText)){
						$("span.errorMsg").text("密码不合法！");
						return false;
					}

					//确认密码
					var repwdText=$("#repwd").val().trim();
					if(passwordText!=repwdText){
						$("span.errorMsg").text("确认密码和密码不一致！");
						return false;
					}

					//验证邮箱：xxxxx@xxx.com
					var emailText=$("#email").val().trim();
					var emailPatt=new RegExp("[a-zA-Z0-9_]+@[a-zA-Z0-9_]{2,}(\.[a-zA-Z0-9_]+)","");
					if(!emailPatt.test(emailText)){
						$("span.errorMsg").text("邮箱地址不合法！");
						return false;
					}
					
					//验证码：现在只需要验证用户是否有输入，验证正确性需要服务器去实现
					var codeText=$("#code").val().trim();
					if(codeText==null || codeText==""){
						$("span.errorMsg").text("验证码不能为空！");
						return false;
					}

					//把错误提示去除
					$("span.errorMsg").text("");
				});
			});
		</script>
		
		<style type="text/css">
			<%@ include file="/page/common/testCss.jsp"%>
			span.errorMsg{
				font-size:18px;
				color:red;
			}
			#divdiv{
				display:flex;
				justify-content:flex-start;
				height:20px;
			}
		</style>
		
	</head>
	<body>
		<%@ include file="/page/common/logo.jsp" %>
		<div class="login_banner">
			<div id="1_content">
				<span class="login_word">欢迎注册</span>
			</div>
			<div id="content">
				<div class="login_form">
					<div class="login_box">
					
						<div class="login_title">
							<h2>注册书城会员</h2>
							<span class="errorMsg">
								${requestScope.msg}
							</span>
						</div>
						
						<div class="form">
							<form action="user/regist.request" method="post">
								
								<label>用户名称：</label>
								<input class="itxt" type="text" placeholder="请输入用户名" size="25px"
									autocomplete="off" tabindex="1" name="username" id="username"
									value="${requestScope.username}"/>
								<br><br>
								
								<label>用户密码：</label>
								<input class="itxt" type="password" placeholder="请输入密码" size="25px"
									autocomplete="off" tabindex="1" name="password" id="password"/>
								<br><br>
								
								<label>确认密码：</label>
								<input class="itxt" type="password" placeholder="确认密码" size="25px"
									autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
								<br><br>
								
								<label>电子邮箱：</label>
								<input class="itxt" type="text" placeholder="请输入邮箱地址" size="25px"
									autocomplete="off" tabindex="1" name="email" id="email"
									value="${requestScope.email}"/>
								<br><br>
								
								<div id="divdiv">
									<label>验证码：</label>
									<input class="itxt" type="text" tabindex="1" placeholder="请输入验证码" 
									size="14px" name="code" id="code">
									&nbsp;
									<img id="code_img" alt="" src="kaptcha.jpg"  size="11px"
									style="width:80px;border:1px solid #9900ff"/>
								</div>
								
								<br><br>
								<input type="submit" value="注册" id="sub_btn"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="登录" id="sub"/>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
    