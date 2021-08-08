package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import pojo.Book;
import service.BookService;
import utils.ConvertUtils;

@Controller
@RequestMapping("/book")
public class BookClientController {

	@Autowired
	private BookService bookService;
	
	/**
	 * 	显示所有图书，并且分页处理
	 * 	show.request：图书显示请求
	 * 	manager.request：图书管理请求
	 */
	@RequestMapping(value={"/show.request","/manager.request"})
	public ModelAndView doShow(HttpServletRequest request) {

		//创建Boolean类型变量，图书显示请求flag为true，管理请求为false
		StringBuffer requestURL=request.getRequestURL();
		boolean flag=true;
		if(requestURL.toString().contains("manager")) {
			flag=false;
		}
		//设置当前页码
		int pageNo=ConvertUtils.paraInt(request.getParameter("pageNo"), 1);
		ModelAndView mv=new ModelAndView();
		
		/**
		 * 	设置分页插件功能：
		 * 		PageHelper.startPage(pageNum, pageSize);
		 * 		pageNum：当前页码
		 * 		pageSize：每页有多少行数据
		 */
		if(flag) {
			PageHelper.startPage(pageNo, 4);
		}else {
			PageHelper.startPage(pageNo, 8);
		}
		
		//1、查询所有图书，显示在首页
		List <Book> books=bookService.queryBooks();
		//2、封装分页后的结果集
		PageInfo pageInfo=new PageInfo(books);
		
//		System.out.println("当前页码："+pageInfo.getPageNum());
//		System.out.println("总页码："+pageInfo.getPages());
//		System.out.println("总记录数："+pageInfo.getTotal());
//		System.out.println("当前页面行数："+pageInfo.getSize());
//		System.out.println("每页有多少行数据："+pageInfo.getPageSize());
//		System.out.println("上一页："+pageInfo.getPrePage());
//		System.out.println("数据："+pageInfo.getList());
		
		//3、保存list对象到request域中
		mv.addObject("page", pageInfo);
		
		if(flag) {
			/**
			 * 	设置URL，分页处理时可以同一个功能指向多个请求
			 */
			mv.addObject("pageUrl","book/show.request?x=x");
			//4、请求转发到图书显示页面
			mv.setViewName("forward:/client/show.jsp");
		}else{
			mv.addObject("pageUrl","book/manager.request?x=x");
			//4、请求转发到图书管理页面
			mv.setViewName("forward:/manager/bookManager.jsp");
		}
		
		return mv;
	}
	
	/**
	 * 	根据价格进行查询
	 */
	@RequestMapping("/byPrice.request")
	public ModelAndView doQueryByPrice(HttpServletRequest request) {
		
		ModelAndView mv=new ModelAndView();
		int pageNo=ConvertUtils.paraInt(request.getParameter("pageNo"), 1);
		int min=ConvertUtils.paraInt(request.getParameter("min"), 0);
		int max=ConvertUtils.paraInt(request.getParameter("max"),Integer.MAX_VALUE);
		
		PageHelper.startPage(pageNo, 4);
		List <Book> books=bookService.queryByPrice(min, max);
		PageInfo pageInfo=new PageInfo(books);
		mv.addObject("page", pageInfo);
		
		mv.addObject("min", min);
		mv.addObject("max", max);
		mv.addObject("pageUrl","book/byPrice.request?min="+min+"&max="+max);
		mv.setViewName("forward:/client/show.jsp");
		return mv;
	}
	
}
