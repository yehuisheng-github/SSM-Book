<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
    <%-- 
    	静态包含页面:
    		<base href="http://localhost:8080/book/">
    		当其他IP地址的计算机访问此工程时，base标签指向的资源地址是访问的客户端IP地址
    		因此需要动态获取服务器的IP地址来访问网页的资源
    --%>
    <%
    	String hre= request.getScheme()+
    				"://"+
    				request.getServerName()+   
    				":"+
    				request.getServerPort()+
    				request.getContextPath()+
    				"/";
    	
    	/*
		 *	jsp页面的页面加载事件后，base标签对内部的函数不起作用
		 *	所以把base标签的地址存放在pageContext对象中，提供给回调函数使用
		*/
   		pageContext.setAttribute("basepath",hre);
    %>
   	<base href="<%=hre%>">
	<script type="text/javascript" src="page/js/jquery-1.8.3.js"></script>

	