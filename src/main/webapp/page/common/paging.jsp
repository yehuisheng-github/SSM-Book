<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<div id="dispage">
		<br><br>
		<a href="${pageUrl}&pageNo=1">首页</a>
		<a href="${pageUrl}&pageNo=${page.pageNum-1}">上一页</a>
		
		<%-- 页码输出的开始，根据条件创建变量begin、end，并存放相应的数值--%>
		<c:choose>
			
			<%-- 页码输出的情况一：页码<=5页 --%>
			<c:when test="${page.pages<=5}">
				<c:set var="begin" value="1"/>
				<c:set var="end" value="${page.pages}"/>
			</c:when>
					
			<%-- 页码输出的情况二：页码>5页，遍历输出，从当前页码开始到当前页码+4结束 --%>
			<c:when test="${page.pages>5}">
				<c:choose>
						
					<%-- 假如当前页码为总页码的前三页 --%>
					<c:when test="${page.pageNum<=3}">
						<c:set var="begin" value="1"/>
						<c:set var="end" value="5"/>
					</c:when>
							
					<%-- 假如当前页码为当前页码的最后三页 --%>
					<c:when test="${page.pageNum>page.pages-3}">
						<%-- 尽管只对后三页进行操作，但是分页页码也要显示5页 --%>
						<c:set var="begin" value="${page.pages-4}"/>
						<c:set var="end" value="${page.pages}"/>
					</c:when>
						
					<%-- 假如当前页码不是当前页码的前三页，也不是后三页 --%>
					<c:otherwise>
						<c:set var="begin" value="${page.pageNum-2}"/>
						<c:set var="end" value="${page.pageNum+2}"/>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
			
		<%-- 调用判断后的赋值，使用a标签，遍历输出begin到end的数值 --%>
		<c:forEach begin="${begin}" end="${end}" var="i">
			<c:if test="${i==page.pageNum}">
				<a href="${pageUrl}&pageNo=${i}">【${i}】</a>
			</c:if>
			<c:if test="${i!=page.pageNum}">
				<a href="${pageUrl}&pageNo=${i}">${i}</a>
			</c:if>
		</c:forEach>
		<%-- 页码输出的结束 --%>
	
		<a href="${pageUrl}&pageNo=${page.pageNum+1}">下一页</a>
		<a href="${pageUrl}&pageNo=${page.pages}">末页</a>
			
		共${page.pages}页，
		${page.total}条记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		到第<input value="${page.pageNum}" name="tiaozhuan" id="tiaozhuan" size="2px"/>页
		<input type="button" value="跳转" id="sub"/>
	</div>
	
	