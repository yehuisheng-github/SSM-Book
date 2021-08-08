<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<script type="text/javascript">
		$(function(){
			
			//AJAX请求，添加图书到购物车，并返回数据
			$(".addCart").click(function(){
				var bookId = $(this).attr("bookId");
				$.ajax({
					url:"${pageScope.basepath}cart/add.request",
					data:{
						id:bookId
					},
					type:"get",
					datatype:"json",
					success:function (event) {
						location.reload();
					}
				})
			});
			
		    $("#sub").click(function(){
				//获取跳转的页码
				var v=$("#tiaozhuan").val().trim();
					
				/*
				 *	JavaScript提供了一个location地址栏对象
				 *	有一个属性href，可以设置或获取浏览器地址栏的地址
				 */
				location.href="${pageScope.basepath}${requestScope.pageUrl}&pageNo="+v;
			});
		});
	</script>